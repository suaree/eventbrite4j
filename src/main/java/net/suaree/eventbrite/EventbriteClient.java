package net.suaree.eventbrite;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.suaree.eventbrite.exception.RequestErrorException;
import net.suaree.eventbrite.exception.RequestException;
import net.suaree.eventbrite.model.*;
import net.suaree.eventbrite.serialization.EventSearchDataDeserializer;
import net.suaree.eventbrite.serialization.LowercaseEnumTypeAdapterFactory;
import net.suaree.eventbrite.serialization.ResultBaseDeserializer;
import net.suaree.eventbrite.serialization.TimeZoneDeserializer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.TimeZone;

/**
 * A client for the Eventbrite APIs.
 *
 * @author roger
 */
public class EventbriteClient {
    private static final Log log = LogFactory.getLog(EventbriteClient.class);

    /**
     * Holds the credentials for accessing the Eventbrite APIs.
     */
    private final Credentials credentials;

    /**
     * Holds the HttpClient to use with this instance.
     */
    private final HttpClient httpClient;

    /**
     * Holds the Gson JSON serializer/deserializer to use with this instance.
     */
    private final Gson gson;

    /**
     * Initializes a new instance of EventbriteClient using the given Credentials.
     *
     * @param credentials The Credentials to use when calling the Eventbrite APIs.
     */
    public EventbriteClient(Credentials credentials) {
        this(credentials, new DefaultHttpClient());
    }

    /**
     * Initializes a new instance of EventbriteClient using the given Credentials.
     *
     * @param credentials The Credentials to use when calling the Eventbrite APIs.
     * @param httpClient  The HttpClient to use when invoking the Eventbrite APIs.
     */
    public EventbriteClient(Credentials credentials, HttpClient httpClient) {
        this.credentials = credentials;
        this.httpClient = httpClient;

        this.gson = new GsonBuilder()
                .registerTypeAdapter(ResultBase.class, new ResultBaseDeserializer())
                .registerTypeAdapter(EventData.class, new EventSearchDataDeserializer())
                .registerTypeAdapter(TimeZone.class, new TimeZoneDeserializer())
                .registerTypeAdapterFactory(new LowercaseEnumTypeAdapterFactory())
                .create();
    }

    /**
     * Searches for events using the Eventbrite event_search API.
     *
     * @param request The parameters for the search request.
     * @return An instance of EventsResult that describes the result of the event_search API call.
     * @throws RequestException
     */
    public EventsResult search(SearchRequest request) throws RequestException {
        ResultBase result = sendRequest(request);

        return (EventsResult) result;
    }

    /**
     * Gets the event described by the GetEventRequest using the Eventbrite event_get API.
     *
     * @param request The parameters for the get request.
     * @return An instance of EventResult that describes the result of the event_get API call.
     * @throws RequestException
     */
    public EventResult get(GetEventRequest request) throws RequestException {
        ResultBase result = sendRequest(request);

        return (EventResult) result;
    }

    /**
     * Sends the given request to the service and returns the resulting HttpResponse.
     *
     * @param request The RequestBase that describes the request to send to the service.
     * @return An instance of ResultBase that represents the response from the service.
     * @throws RequestException
     */
    private ResultBase sendRequest(RequestBase request) throws RequestException {
        assert null != request;

        try {
            URI requestUri = request.getUri(credentials);
            HttpGet get = new HttpGet(requestUri);

            if (log.isDebugEnabled()) {
                log.debug(String.format("GET %s", get.getURI()));
            }

            HttpResponse response = httpClient.execute(get);
            InputStream in = response.getEntity().getContent();
            Reader reader;

            if (log.isDebugEnabled()) {
                log.debug("Response Headers:");

                for (Header header : response.getAllHeaders()) {
                    log.debug(String.format("%s: %s", header.getName(), header.getValue()));
                }
            }

            reader = new InputStreamReader(in);

            if (log.isTraceEnabled()) {
                // If tracing is enabled for this class, wrap the InputStreamReader in a LoggingInputStreamReader so we
                // can log the request body.
                reader = new LoggingInputStreamReader(new BufferedReader(reader));
            }

            ResultBase result = gson.fromJson(reader, ResultBase.class);

            if (result instanceof ErrorResult) {
                throw new RequestErrorException((ErrorResult) result);
            }

            return result;
        } catch (URISyntaxException ex) {
            log.error("URISyntaxException:", ex);

            throw new RequestException(ex);
        } catch (ClientProtocolException ex) {
            log.error("ClientProtocolException:", ex);

            throw new RequestException(ex);
        } catch (IOException ex) {
            log.error("IOException:", ex);

            throw new RequestException(ex);
        }
    }
}
