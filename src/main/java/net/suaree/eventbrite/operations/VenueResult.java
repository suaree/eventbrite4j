package net.suaree.eventbrite.operations;

import com.google.gson.annotations.SerializedName;
import net.suaree.eventbrite.model.Venue;

import java.util.List;

/**
 * Represents the result from a request to the venue_get API.
 *
 * @author roger
 */
public class VenueResult extends ResultBase {
    // TODO: Verify that we're really getting a list back, not just a single result.
    @SerializedName("venue")
    private List<Venue> venues;

    /**
     * Gets the Venue for this VenueResult.
     *
     * @return An instance of Venue describing the Venue for this VenueResult.
     */
    public List<Venue> getVenues() {
        return venues;
    }

    public Venue getFirstVenue() {
        if (null == venues || 0 >= venues.size()) {
            return null;
        }

        return venues.get(0);
    }
}
