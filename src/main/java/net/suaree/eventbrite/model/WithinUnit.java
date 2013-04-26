package net.suaree.eventbrite.model;

/**
 * Represents within_unit values used by the event_search API.
 *
 * @author roger
 */
public enum WithinUnit {
    Miles("M"),
    Kilometers("K");

    private final String value;

    private WithinUnit(String value) {
        this.value = value;
    }

    /**
     * Gets the within_unit value as used in the event_search API.
     *
     * @return A String that represents the within_unit value for the event_search API.
     */
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
