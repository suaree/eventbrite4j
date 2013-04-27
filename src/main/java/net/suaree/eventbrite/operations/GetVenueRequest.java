package net.suaree.eventbrite.operations;

import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a request to the venue_get API.
 *
 * @author roger
 */
public class GetVenueRequest extends RequestBase {
    private Long id;

    /**
     * Gets the ID of the venue to retrieve.
     *
     * @return A Long representing the ID of the venue to retrieve.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the venue to retrieve.
     *
     * @param id The ID of the venue to retrieve.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the Eventbrite API this request is for.
     *
     * @return A string that represents the Eventbrite API name.
     */
    @Override
    protected String getApiName() {
        return "venue_get";
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

        return params;
    }

    /**
     * Defines whether or not this API request requires a user_key parameter.
     *
     * @return Always returns true.
     */
    @Override
    protected boolean requiresUserKey() {
        return true;
    }
}
