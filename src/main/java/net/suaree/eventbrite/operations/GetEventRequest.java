package net.suaree.eventbrite.operations;

import net.suaree.eventbrite.model.Display;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a request to the event_get API.
 *
 * @author roger
 */
public class GetEventRequest extends RequestBase {
    private Long id;

    private Display[] display;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Display[] getDisplay() {
        return display;
    }

    public void setDisplay(Display[] display) {
        this.display = display;
    }

    public void setAllDisplay(Display... display) {
        this.display = display;
    }

    /**
     * Gets the name of the Eventbrite API this request is for.
     *
     * @return A string that represents the Eventbrite API name.
     */
    @Override
    protected String getApiName() {
        return "event_get";
    }

    /**
     * Gets the query parameters for this Eventbrite API request.
     *
     * @return A List of NameValuePair instances that define the request parameters.
     */
    @Override
    protected List<NameValuePair> getQueryParameters() {
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>(4);

        addParameter("id", id, params);

        if (null != display && 0 < display.length) {
            params.add(new BasicNameValuePair("display", convertToCommaSeparatedList(display)));
        }

        return params;
    }
}
