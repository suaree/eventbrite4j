package net.suaree.eventbrite.model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateMidnight;
import org.joda.time.Days;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a daily schedule for an event.
 *
 * @author roger
 */
public class DailySchedule extends RepeatSchedule {
    private static final Log log = LogFactory.getLog(DailySchedule.class);

    private static final Pattern RegexScheduleType =
            Pattern.compile("^(\\d+|mf)\\-(\\d{2}/\\d{2}/\\d{4})$");

    private static final DateFormat EndDateFormat = new SimpleDateFormat("MM/dd/yyyy");

    private boolean mondayThroughFriday;
    private Integer frequency;

    private Calendar endDate;

    public DailySchedule(TimeZone timeZone) {
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

        String rawFrequency = matcher.group(1);

        mondayThroughFriday = "mf".equals(rawFrequency);

        if (!mondayThroughFriday) {
            frequency = Integer.parseInt(matcher.group(1));
        }

        try {
            endDate = Calendar.getInstance(getTimeZone());
            endDate.setTime(EndDateFormat.parse(matcher.group(2)));
        } catch (ParseException ex) {
            log.error("ParseException:", ex);

            throw new IllegalArgumentException("rawScheduleRemainder", ex);
        }
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
        ArrayList<Calendar> result = new ArrayList<Calendar>();
        Calendar current = startDate;
        Calendar tmp;
        int dayAdd = 1;

        // TODO: There must be a more efficient way to do this.
        while (!current.after(endDate)) {
            if (matchesSchedule(firstDate, current)) {
                result.add(current);

                if (!mondayThroughFriday) {
                    dayAdd = frequency;
                }
            }

            tmp = current;
            current = Calendar.getInstance(getTimeZone());
            current.setTimeInMillis(tmp.getTimeInMillis());
            current.add(Calendar.DATE, dayAdd);
        }

        return result;
    }

    private boolean matchesSchedule(Calendar firstDate, Calendar testDate) {
        if (mondayThroughFriday) {
            int dow = testDate.get(Calendar.DAY_OF_WEEK);

            return dow >= Calendar.MONDAY && dow <= Calendar.FRIDAY;
        } else {
            DateMidnight dmFirst = new DateMidnight(firstDate.getTimeInMillis());
            DateMidnight dmTest = new DateMidnight(testDate.getTimeInMillis());

            Days days = Days.daysBetween(dmFirst, dmTest);
            return 0 == days.getDays() % frequency;
        }
    }
}
