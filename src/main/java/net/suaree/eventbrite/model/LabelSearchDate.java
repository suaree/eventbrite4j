package net.suaree.eventbrite.model;

/**
 * Represents a label search date (e.g. "Today") for the event_search API.
 *
 * @author roger
 */
public class LabelSearchDate extends SearchDate {
    private final DateLabel label;

    public LabelSearchDate(DateLabel label) {
        this.label = label;
    }

    /**
     * Gets the DateLabel used by this LabelSearchDate.
     *
     * @return An instance of DateLabel.
     */
    public DateLabel getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label.toString();
    }
}
