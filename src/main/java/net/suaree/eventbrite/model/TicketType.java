package net.suaree.eventbrite.model;

/**
 * Represents ticket type values used by tickets from the Eventbrite APIs.
 *
 * @author roger
 */
public enum TicketType {
    FixedPrice(0),
    Donation(1);

    private final int value;

    private TicketType(int value) {
        this.value = value;
    }

    /**
     * Gets the ticket value as used in the Eventbrite APIs.
     *
     * @return An int value that represents the ticket for the Eventbrite APIs.
     */
    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
