package net.suaree.eventbrite.operations;

import net.suaree.eventbrite.model.Event;

/**
 * Represents the result from a request to the event_get API.
 *
 * @author roger
 */
public class EventResult extends ResultBase {
    private Event event;

    /**
     * Gets the Event for this EventResult.
     *
     * @return An instance of Event describing the Event for this EventResult.
     */
    public Event getEvent() {
        return event;
    }
}
