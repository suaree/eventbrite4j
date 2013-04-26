package net.suaree.eventbrite.model;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a custom schedule. As per Eventbrite's API documentation, the details of this schedule cannot be retrieved
 * through the APIs.
 *
 * @author roger
 */
public class CustomSchedule extends RepeatSchedule {
    private static final Pattern RegexScheduleType = Pattern.compile("^(\\d+)$");

    private long id;

    public CustomSchedule(TimeZone timeZone) {
        super(timeZone);
    }

    /**
     * Parses the remainder of the schedule spec.
     *
     * @param rawScheduleRemainder The remainder of the schedule spec to parse.
     */
    @Override
    protected void parseSchedule(String rawScheduleRemainder) {
        Matcher matcher = RegexScheduleType.matcher(rawScheduleRemainder);

        if (!matcher.find()) {
            throw new IllegalArgumentException("rawScheduleRemainder");
        }

        id = Long.parseLong(matcher.group(1));
    }

    /**
     * Gets all dates that match this schedule.
     *
     * @param firstDate The first date for the schedule.
     * @param startDate The start date for the schedule.
     * @return A List of Calendar objects representing the dates that match this schedule.
     */
    @Override
    protected List<Calendar> getAllDates(Calendar firstDate, Calendar startDate) {
        throw new UnsupportedOperationException();
    }

    /**
     * Gets the ID of the custom schedule.
     *
     * @return
     */
    public long getId() {
        return id;
    }
}
