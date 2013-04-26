package net.suaree.eventbrite.model;

import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Represents the event summary as retrieved through the event_search API.
 *
 * @author roger
 */
public class EventSummaryData extends EventData {
    @SerializedName("total_items")
    private Integer totalItems;

    @SerializedName("first_event")
    private Long firstEvent;

    @SerializedName("last_event")
    private Long lastEvent;

    // TODO: filters

    @SerializedName("num_showing")
    private Integer numShowing;

    public static Type getType() {
        return TypeToken.get(EventSummaryData.class).getType();
    }

    public Long getFirstEvent() {
        return firstEvent;
    }

    public Long getLastEvent() {
        return lastEvent;
    }

    public Integer getNumShowing() {
        return numShowing;
    }

    public Integer getTotalItems() {
        return totalItems;
    }
}
