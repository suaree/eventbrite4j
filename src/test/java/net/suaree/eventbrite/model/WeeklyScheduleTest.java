package net.suaree.eventbrite.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

/**
 * Unit Tests for WeeklySchedule.
 *
 * @author roger
 */
public class WeeklyScheduleTest {
    @Test
    public void testWeeklyEveryDay() {
        WeeklySchedule schedule = new WeeklySchedule(TimeZone.getDefault());
        Calendar first = Calendar.getInstance(TimeZone.getDefault());
        Calendar start = Calendar.getInstance(TimeZone.getDefault());

        first.clear();
        first.set(2013, Calendar.JANUARY, 1);

        start.clear();
        start.set(2013, Calendar.JUNE, 28);

        schedule.parseSchedule("1-Y,Y,Y,Y,Y,Y,Y-07/02/2013");
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
    public void testBiWeeklyEveryDay() {
        WeeklySchedule schedule = new WeeklySchedule(TimeZone.getDefault());
        Calendar first = Calendar.getInstance(TimeZone.getDefault());
        Calendar start = Calendar.getInstance(TimeZone.getDefault());

        first.clear();
        first.set(2013, Calendar.JANUARY, 1);

        start.clear();
        start.set(2013, Calendar.JANUARY, 6);

        schedule.parseSchedule("2-Y,Y,Y,Y,Y,Y,Y-01/16/2013");
        List<Calendar> dates = schedule.getDates(first, start, Integer.MAX_VALUE);

        Assert.assertNotNull(dates);
        Assert.assertEquals(4, dates.size());

        for (int i = 0, day = 13; i < dates.size(); i++, day++) {
            Assert.assertEquals(2013, dates.get(i).get(Calendar.YEAR));
            Assert.assertEquals(Calendar.JANUARY, dates.get(i).get(Calendar.MONTH));
            Assert.assertEquals(day, dates.get(i).get(Calendar.DATE));
        }
    }

    @Test
    public void testBiWeeklySomeDays() {
        WeeklySchedule schedule = new WeeklySchedule(TimeZone.getDefault());
        Calendar first = Calendar.getInstance(TimeZone.getDefault());
        Calendar start = Calendar.getInstance(TimeZone.getDefault());

        first.clear();
        first.set(2013, Calendar.JANUARY, 1);

        start.clear();
        start.set(2013, Calendar.JANUARY, 6);

        schedule.parseSchedule("2-N,Y,N,Y,N,Y,N-01/23/2013");
        List<Calendar> dates = schedule.getDates(first, start, Integer.MAX_VALUE);

        Assert.assertNotNull(dates);
        Calendar[] expectedDates = {
                new GregorianCalendar(2013, Calendar.JANUARY, 15),
                new GregorianCalendar(2013, Calendar.JANUARY, 17),
                new GregorianCalendar(2013, Calendar.JANUARY, 19),
        };

        Assert.assertEquals(expectedDates.length, dates.size());

        for (int i = 0; i < expectedDates.length; i++) {
            Assert.assertEquals(expectedDates[i], dates.get(i));
        }
    }
}
