package net.suaree.eventbrite.model;

import net.suaree.eventbrite.Credentials;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Represents the base class for Eventbrite API requests.
 *
 * @author roger
 */
public abstract class RequestBase {
    /**
     * Gets the URI to use to invoke this API.
     *
     * @param credentials The Credentials to use when invoking the Eventbrite API.
     * @return An instance of URI that represents the URI of this request.
     * @throws URISyntaxException
     */
    public URI getUri(Credentials credentials) throws URISyntaxException {
        URIBuilder builder = new URIBuilder(String.format("https://www.eventbrite.com/json/%s", getApiName()));

        builder.addParameter("app_key", credentials.getAppKey());

        for (NameValuePair param : getQueryParameters()) {
            builder.addParameter(param.getName(), param.getValue());
        }

        return builder.build();
    }

    /**
     * Gets the name of the Eventbrite API this request is for.
     *
     * @return A string that represents the Eventbrite API name.
     */
    protected abstract String getApiName();

    /**
     * Gets the query parameters for this Eventbrite API request.
     *
     * @return A List of NameValuePair instances that define the request parameters.
     */
    protected abstract List<NameValuePair> getQueryParameters();

    /**
     * Adds the given parameter to the list of parameters, iff the value is not null.
     *
     * @param name   The name of the parameter to add.
     * @param value  The value of the parameter to add.
     * @param params The list of parameters to add the parameter to.
     * @param <T>    The type of the parameter's value.
     */
    protected <T> void addParameter(String name, T value, List<NameValuePair> params) {
        if (null == name) {
            throw new IllegalArgumentException("name must not be null.");
        }
        if (null == params) {
            throw new IllegalArgumentException("params must not be null.");
        }

        if (null != value) {
            params.add(new BasicNameValuePair(name, value.toString()));
        }
    }

    /**
     * Adds the given parameter to the list of parameters, iff the value is not null or empty.
     *
     * @param name   The name of the parameter to add.
     * @param value  The value of the parameter to add.
     * @param params The list of parameters to add the parameter to.
     */
    protected void addParameter(String name, String value, List<NameValuePair> params) {
        if (null == name) {
            throw new IllegalArgumentException("name must not be null.");
        }
        if (null == params) {
            throw new IllegalArgumentException("params must not be null.");
        }

        if (null != value && !value.isEmpty()) {
            params.add(new BasicNameValuePair(name, value));
        }
    }

    /**
     * Returns the values of the given input array as a string with the values comma-separated.
     *
     * @param values The values to convert.
     * @param <T>    The type of values to convert.
     * @return A String that represents the comma-separated list of input values.
     */
    protected <T> String convertToCommaSeparatedList(T[] values) {
        StringBuilder sb = new StringBuilder();

        for (T value : values) {
            if (0 < sb.length()) {
                sb.append(',');
            }

            sb.append(value);
        }

        return sb.toString();
    }
}
