package net.suaree.eventbrite.model;

/**
 * Represents Event Status values for the Eventbrite APIs.
 *
 * @author roger
 */
public enum EventStatus {
    Live("Live"),
    Started("Started"),
    Ended("Ended"),
    Canceled("Canceled");

    private final String value;

    private EventStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
