package net.suaree.eventbrite.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Represents a range (start and end) date for the event_search API.
 *
 * @author roger
 */
public class RangeSearchDate extends SearchDate {
    private static final SimpleDateFormat DateOnlyFormat = new SimpleDateFormat("yyyy-MM-dd");

    private Calendar start;
    private Calendar end;

    public RangeSearchDate(Calendar end, Calendar start) {
        this.end = end;
        this.start = start;
    }

    /**
     * Gets the Calendar that represents the start date for the range.
     *
     * @return An instance of Calendar representing the range's start date.
     */
    public Calendar getStart() {
        return start;
    }

    /**
     * Gets the Calendar that represents the end date for the range.
     *
     * @return An instance of Calendar representing the range's end date.
     */
    public Calendar getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return String.format("%s %s", DateOnlyFormat.format(start.getTime()), DateOnlyFormat.format(end.getTime()));
    }
}
