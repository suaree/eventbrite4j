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
     * Gets the start date of the next max occurrences in this repeat schedule.
     *
     * @param firstDate    The Calendar which defines the first date of this repeating schedule.
     * @param earliestDate The Calendar which defines the earliest occurrence to return.
     * @param max          The maximum number of occurrences to return;
     * @return An instance of Calendar that describes the next occurrence of this schedule on or after earliestDate.
     */
    public List<Calendar> getNextOccurrences(Calendar firstDate, Calendar earliestDate, int max) {
        return getDates(firstDate, earliestDate, max);
    }

    /**
     * Gets the start date of the next occurrence in this repeat schedule.
     *
     * @param firstDate The Calendar which defines the first date of this repeating schedule.
     * @return An instance of Calendar that describes the next occurrence of this schedule on or after earliestDate.
     */
    public Calendar getNextOccurrence(Calendar firstDate) {
        List<Calendar> results = getNextOccurrences(firstDate, Calendar.getInstance(timeZone), 1);

        if (null != results && 1 == results.size()) {
            return results.get(0);
        } else {
            return null;
        }
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
     * Gets up to max dates after startDate that match this schedule which started on firstDate.
     *
     * @param firstDate The first date for the schedule.
     * @param startDate The start date for the schedule; only results on or after this date must be returned.
     * @param max       The maximum number of schedule dates to return;
     * @return A List of Calendar objects representing the dates that match this schedule.
     */
    protected abstract List<Calendar> getDates(Calendar firstDate, Calendar startDate, int max);
}
