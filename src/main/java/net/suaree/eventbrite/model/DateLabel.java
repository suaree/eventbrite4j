package net.suaree.eventbrite.model;

/**
 * Represents Date Labels supported by the Eventbrite APIs.
 *
 * @author roger
 */
public enum DateLabel {
    All("All"),
    Future("Future"),
    Past("Past"),
    Today("Today"),
    Yesterday("Yesterday"),
    LastWeek("Last Week"),
    ThisWeek("This Week"),
    NextWeek("Next week"), /* sic */
    ThisMonth("This Month"),
    NextMonth("Next Month"),

    January("January"),
    February("February"),
    March("March"),
    April("April"),
    May("May"),
    June("June"),
    July("July"),
    August("August"),
    September("September"),
    October("October"),
    November("November"),
    December("December");

    private final String value;

    private DateLabel(String value) {
        this.value = value;
    }

    /**
     * Gets the label name as used in the Eventbrite APIs.
     *
     * @return A String that represents the label name for the Eventbrite APIs.
     */
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
