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
     * Gets up to max dates after startDate that match this schedule which started on firstDate.
     *
     * @param firstDate The first date for the schedule.
     * @param startDate The start date for the schedule; only results on or after this date must be returned.
     * @param max       The maximum number of schedule dates to return;
     * @return A List of Calendar objects representing the dates that match this schedule.
     */
    @Override
    protected List<Calendar> getDates(Calendar firstDate, Calendar startDate, int max) {
        assert 0 < max;

        throw new UnsupportedOperationException();
    }

    /**
     * Gets the ID of the custom schedule.
     *
     * @return A long value that represents the ID of the custom schedule.
     */
    public long getId() {
        return id;
    }
}
