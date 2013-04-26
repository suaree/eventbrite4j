package net.suaree.eventbrite.model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateMidnight;
import org.joda.time.Weeks;

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
 * Represents a weekly schedule for an event.
 *
 * @author roger
 */
public class WeeklySchedule extends RepeatSchedule {
    private static final Log log = LogFactory.getLog(WeeklySchedule.class);

    private static final Pattern RegexScheduleType =
            Pattern.compile("^(\\d+)\\-([YN](?:,[YN]){6})\\-(\\d{2}/\\d{2}/\\d{4})$");

    private static final DateFormat EndDateFormat = new SimpleDateFormat("MM/dd/yyyy");

    private int frequency;

    private final boolean[] occurrs = new boolean[7];

    private Calendar endDate;

    public WeeklySchedule(TimeZone timeZone) {
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

        frequency = Integer.parseInt(matcher.group(1));

        try {
            endDate = Calendar.getInstance(getTimeZone());
            endDate.setTime(EndDateFormat.parse(matcher.group(3)));
        } catch (ParseException ex) {
            log.error("ParseException:", ex);

            throw new IllegalArgumentException("rawScheduleRemainder", ex);
        }

        String[] occurrsParts = matcher.group(2).split(",");
        assert occurrsParts.length == occurrs.length;

        for (int i = 0; i < occurrs.length; i++) {
            occurrs[i] = "Y".equals(occurrsParts[i]);
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
        Calendar firstDateFirstDoW = Calendar.getInstance(getTimeZone());
        Calendar current = startDate;
        Calendar tmp;

        firstDateFirstDoW.setTimeInMillis(firstDate.getTimeInMillis());
        firstDateFirstDoW.add(Calendar.DATE, -(firstDate.get(Calendar.DAY_OF_WEEK) - 1));

        // TODO: There must be a more efficient way to do this.
        while (!current.after(endDate)) {
            if (matchesSchedule(firstDateFirstDoW, current)) {
                result.add(current);
            }

            tmp = current;
            current = Calendar.getInstance(getTimeZone());
            current.setTimeInMillis(tmp.getTimeInMillis());
            current.add(Calendar.DATE, 1);
        }

        return result;
    }

    private boolean matchesSchedule(Calendar firstDate, Calendar testDate) {
        DateMidnight dmFirst = new DateMidnight(firstDate.getTimeInMillis());
        DateMidnight dmTest = new DateMidnight(testDate.getTimeInMillis());

        Weeks weeks = Weeks.weeksBetween(dmFirst, dmTest);
        if (0 != weeks.getWeeks() % frequency) {
            return false;
        }

        int dow = testDate.get(Calendar.DAY_OF_WEEK);
        int dayIndex = ((dow - Calendar.SUNDAY) + 6) % 7;

        return occurrs[dayIndex];
    }
}
