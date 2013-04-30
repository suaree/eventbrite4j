package net.suaree.eventbrite.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

/**
 * Unit Tests for DailySchedule.
 *
 * @author roger
 */
public class DailyScheduleTest {
    @Test
    public void testDailyWithMonthWrap() {
        DailySchedule schedule = new DailySchedule(TimeZone.getDefault());
        Calendar first = Calendar.getInstance(TimeZone.getDefault());
        Calendar start = Calendar.getInstance(TimeZone.getDefault());

        first.clear();
        first.set(2013, Calendar.JANUARY, 1);

        start.clear();
        start.set(2013, Calendar.JUNE, 28);

        schedule.parseSchedule("1-07/02/2013");
        List<Calendar> dates = schedule.getDates(first, start, Integer.MAX_VALUE);

        Assert.assertNotNull(dates);
        Assert.assertEquals(5, dates.size());

        for (int i = 0, day = 28, month = Calendar.JUNE; i < dates.size(); i++, day++) {
            if (day > 30) {
                day -= 30;
                month++;
            }

            Assert.assertEquals(2013, dates.get(i).get(Calendar.YEAR));
            Assert.assertEquals(month, dates.get(i).get(Calendar.MONTH));
            Assert.assertEquals(day, dates.get(i).get(Calendar.DATE));
        }
    }

    @Test
    public void testEveryOtherDay() {
        DailySchedule schedule = new DailySchedule(TimeZone.getDefault());
        Calendar first = Calendar.getInstance(TimeZone.getDefault());
        Calendar start = Calendar.getInstance(TimeZone.getDefault());

        first.clear();
        first.set(2013, Calendar.JUNE, 26);

        start.clear();
        start.set(2013, Calendar.JUNE, 28);

        schedule.parseSchedule("2-07/02/2013");
        List<Calendar> dates = schedule.getDates(first, start, Integer.MAX_VALUE);

        Assert.assertNotNull(dates);
        Assert.assertEquals(3, dates.size());

        for (int i = 0, day = 28, month = Calendar.JUNE; i < dates.size(); i++, day += 2) {
            if (day > 30) {
                day -= 30;
                month++;
            }

            Assert.assertEquals(2013, dates.get(i).get(Calendar.YEAR));
            Assert.assertEquals(month, dates.get(i).get(Calendar.MONTH));
            Assert.assertEquals(day, dates.get(i).get(Calendar.DATE));
        }
    }

    @Test
    public void testEveryOtherDayStartOffset() {
        DailySchedule schedule = new DailySchedule(TimeZone.getDefault());
        Calendar first = Calendar.getInstance(TimeZone.getDefault());
        Calendar start = Calendar.getInstance(TimeZone.getDefault());

        first.clear();
        first.set(2013, Calendar.JUNE, 27);

        start.clear();
        start.set(2013, Calendar.JUNE, 28);

        schedule.parseSchedule("2-07/02/2013");
        List<Calendar> dates = schedule.getDates(first, start, Integer.MAX_VALUE);

        Assert.assertNotNull(dates);
        Assert.assertEquals(2, dates.size());

        for (int i = 0, day = 29, month = Calendar.JUNE; i < dates.size(); i++, day += 2) {
            if (day > 30) {
                day -= 30;
                month++;
            }

            Assert.assertEquals(2013, dates.get(i).get(Calendar.YEAR));
            Assert.assertEquals(month, dates.get(i).get(Calendar.MONTH));
            Assert.assertEquals(day, dates.get(i).get(Calendar.DATE));
        }
    }

    @Test
    public void testMondayThroughFriday() {
        DailySchedule schedule = new DailySchedule(TimeZone.getDefault());
        Calendar first = Calendar.getInstance(TimeZone.getDefault());
        Calendar start = Calendar.getInstance(TimeZone.getDefault());

        first.clear();
        first.set(2013, Calendar.JANUARY, 1);

        start.clear();
        start.set(2013, Calendar.JUNE, 23);

        schedule.parseSchedule("mf-07/02/2013");
        List<Calendar> dates = schedule.getDates(first, start, Integer.MAX_VALUE);

        Assert.assertNotNull(dates);

        Calendar[] expectedDates = {
                new GregorianCalendar(2013, Calendar.JUNE, 24),
                new GregorianCalendar(2013, Calendar.JUNE, 25),
                new GregorianCalendar(2013, Calendar.JUNE, 26),
                new GregorianCalendar(2013, Calendar.JUNE, 27),
                new GregorianCalendar(2013, Calendar.JUNE, 28),
                new GregorianCalendar(2013, Calendar.JULY, 1),
                new GregorianCalendar(2013, Calendar.JULY, 2),
        };

        Assert.assertEquals(expectedDates.length, dates.size());

        for (int i = 0; i < expectedDates.length; i++) {
            Assert.assertEquals(expectedDates[i], dates.get(i));
        }
    }
}
