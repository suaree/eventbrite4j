package net.suaree.eventbrite.model;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a repeating schedule for an event.
 *
 * @author roger
 */
public abstract class RepeatSchedule {
    private static final Pattern RegexScheduleType = Pattern.compile("^(daily|weekly|monthly|custom)\\-(.+)$");

    private final TimeZone timeZone;

    protected RepeatSchedule(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public final TimeZone getTimeZone() {
        return timeZone;
    }

    /**
     * Tries to parses the given raw schedule spec and returns the resulting RepeatSchedule on success.
     *
     * @param timeZone    The TimeZone to use for date/time parsing.
     * @param rawSchedule The raw schedule spec to parse.
     * @return An instance of RepeatSchedule on success or false otherwise.
     */
    public static RepeatSchedule parse(TimeZone timeZone, String rawSchedule) {
        if (null == rawSchedule) {
            return null;
        }

        Matcher matcher = RegexScheduleType.matcher(rawSchedule);

        if (!matcher.find()) {
            return null;
        }

        String type = matcher.group(1);
        String remainder = matcher.group(2);
        RepeatSchedule schedule;

        if ("daily".equals(type)) {
            schedule = new DailySchedule(timeZone);
        } else if ("weekly".equals(type)) {
            schedule = new WeeklySchedule(timeZone);
        } else if ("monthly".equals(type)) {
            schedule = new MonthlySchedule(timeZone);
        } else if ("custom".equals(type)) {
            schedule = new CustomSchedule(timeZone);
        } else {
            throw new IllegalArgumentException("rawSchedule");
        }

        schedule.parseSchedule(remainder);

        return schedule;
    }

    /**
     * Parses the remainder of the schedule spec.
     *
     * @param rawScheduleRemainder The remainder of the schedule spec to parse.
     */
    protected abstract void parseSchedule(String rawScheduleRemainder);

    /**
     * Gets all dates that match this schedule.
     *
     * @param firstDate The first date for the schedule.
     * @param startDate The start date for the schedule.
     * @return A List of Calendar objects representing the dates that match this schedule.
     */
    protected abstract List<Calendar> getAllDates(Calendar firstDate, Calendar startDate);
}
