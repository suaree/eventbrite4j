package net.suaree.eventbrite.model;

/**
 * Represents the sort_by values used by the event_search API.
 *
 * @author roger
 */
public enum SortBy {
    Id("id"),
    Date("date"),
    Name("name"),
    City("city");

    private final String value;

    private SortBy(String value) {
        this.value = value;
    }

    /**
     * Gets the sort_by value as used in the event_search API.
     *
     * @return A String that represents the sort_by value for the event_search API.
     */
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
