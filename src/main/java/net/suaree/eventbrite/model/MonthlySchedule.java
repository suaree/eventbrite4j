package net.suaree.eventbrite.model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a monthly schedule for an event.
 *
 * @author roger
 */
public class MonthlySchedule extends RepeatSchedule {
    private static final Log log = LogFactory.getLog(MonthlySchedule.class);

    private static final Pattern RegexScheduleType =
            Pattern.compile("^(\\d+)\\-(.+)\\-(\\d{2}/\\d{2}/\\d{4})$");

    private static final DateFormat EndDateFormat = new SimpleDateFormat("MM/dd/yyyy");

    private int frequency;

    private Calendar endDate;

    public MonthlySchedule(TimeZone timeZone) {
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

        // TODO: Parse day of month spec.
        // monthly-2-10-06/30/2012          => Every other month, on the 10th.
        // monthly-2-second/sat-06/30/2012  => Every other month, on the second Saturday.
        // What other formats are there? Eventbrite doesn't specify ...

        try {
            endDate = Calendar.getInstance(getTimeZone());
            endDate.setTime(EndDateFormat.parse(matcher.group(3)));
        } catch (ParseException ex) {
            log.error("ParseException:", ex);

            throw new IllegalArgumentException("rawScheduleRemainder", ex);
        }
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

        return null;
    }
}
