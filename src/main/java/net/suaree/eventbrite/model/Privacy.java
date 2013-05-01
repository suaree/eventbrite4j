package net.suaree.eventbrite.model;

/**
 * Represents Privacy values for the Eventbrite APIs.
 *
 * @author roger
 */
public enum Privacy {
    Private("Private"),
    Public("Public");

    private final String value;

    private Privacy(String value) {
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
