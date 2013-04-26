package net.suaree.eventbrite;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author roger
 */
public class ResourceBasedHttpClient implements HttpClient {
    private final String resourceName;

    public ResourceBasedHttpClient(String resourceName) {
        this.resourceName = resourceName;
    }

    public HttpParams getParams() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ClientConnectionManager getConnectionManager() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public HttpResponse execute(HttpUriRequest httpUriRequest) throws IOException, ClientProtocolException {
        InputStream in = ResourceBasedHttpClient.class.getResourceAsStream(resourceName);

        HttpResponse response = new BasicHttpResponse(
                new ProtocolVersion("HTTP", 1, 1),
                200,
                "OK");

        BasicHttpEntity entity = new BasicHttpEntity();

        entity.setContent(in);

        response.setEntity(entity);

        return response;
    }

    public HttpResponse execute(HttpUriRequest httpUriRequest, HttpContext httpContext) throws IOException, ClientProtocolException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public HttpResponse execute(HttpHost httpHost, HttpRequest httpRequest) throws IOException, ClientProtocolException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public HttpResponse execute(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) throws IOException, ClientProtocolException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public <T> T execute(HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler) throws IOException, ClientProtocolException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public <T> T execute(HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) throws IOException, ClientProtocolException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public <T> T execute(HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler) throws IOException, ClientProtocolException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public <T> T execute(HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) throws IOException, ClientProtocolException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
