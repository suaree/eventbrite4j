package net.suaree.eventbrite.model;

import java.util.List;

/**
 * Represents the result from a request to the event_search API.
 *
 * @author roger
 */
public class EventsResult extends ResultBase {
    private List<EventData> events;

    /**
     * Gets a List of EventData objects.
     *
     * @return A List of EventData objects for this result.
     */
    public List<EventData> getEvents() {
        return events;
    }
}
