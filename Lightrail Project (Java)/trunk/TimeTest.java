import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
/**
 * Tests for class Time.
 *
 * @author  Dr. Jody Paul
 * @version 1.4 ($Id: TimeTest.java 344 2016-05-01 18:00:37Z joshua $)
 */
public class TimeTest {
    @Test
    public void rolloverMinutesTest() {
        Time time1 = new Time(11, 58);
        assertEquals(11, time1.hour());
        assertEquals(58, time1.minute());
        time1.incrementOneMinute();
        assertEquals(11, time1.hour());
        assertEquals(59, time1.minute());
        time1.incrementOneMinute();
        assertEquals(12, time1.hour());
        assertEquals(00, time1.minute());
        time1.incrementMinutes(59);
        assertEquals(12, time1.hour());
        assertEquals(59, time1.minute());
        time1.incrementMinutes(2);
        assertEquals(13, time1.hour());
        assertEquals(01, time1.minute());
        time1.incrementMinutes(122);
        assertEquals(15, time1.hour());
        assertEquals(03, time1.minute());
        time1.setTime(22, 45);
        assertEquals(22, time1.hour());
        assertEquals(45, time1.minute());
        time1.incrementMinutes(90);
        assertEquals(00, time1.hour());
        assertEquals(15, time1.minute());
    }

    @Test
    public void rolloverSecondsTest() {
        Time time1 = new Time(9, 11, 58);
        assertEquals("09:11:58", time1.toString());
        time1.incrementOneSecond();
        assertEquals("09:11:59", time1.toString());
        time1.incrementOneSecond();
        assertEquals("09:12:00", time1.toString());
        time1.incrementSeconds(59);
        assertEquals("09:12:59", time1.toString());
        time1.incrementSeconds(2);
        assertEquals("09:13:01", time1.toString());
        time1.incrementSeconds(122);
        assertEquals("09:15:03", time1.toString());
        time1.setTime(9, 58, 45);
        assertEquals("09:58:45", time1.toString());
        time1.incrementSeconds(90);
        assertEquals("10:00:15", time1.toString());
        time1.setTime(23, 59, 45);
        assertEquals("23:59:45", time1.toString());
        time1.incrementSeconds(90);
        assertEquals("00:01:15", time1.toString());
    }

    @Test
    public void comparisonHoursMinutesTest() {
        Time time1 = new Time(11, 45);
        Time time2 = new Time(12, 00);
        Time time3 = new Time(11, 45);
        Time time4 = new Time(12, 21);
        assertTrue(time1.compareTo(time2) < 0);
        assertTrue(0 < time2.compareTo(time1));
        assertEquals(0, time1.compareTo(time1));
        assertEquals(0, time3.compareTo(time1));
        assertTrue(time2.compareTo(time4) < 0);
        assertTrue(0 < time4.compareTo(time2));
    }

    @Test
    public void comparisonHoursMinutesSecondsTest() {
        Time time1 = new Time(9, 11, 45);
        Time time2 = new Time(9, 12, 00);
        Time time3 = new Time(9, 11, 45);
        Time time4 = new Time(9, 12, 21);
        assertTrue(time1.compareTo(time2) < 0);
        assertTrue(0 < time2.compareTo(time1));
        assertEquals(0, time1.compareTo(time1));
        assertEquals(0, time3.compareTo(time1));
        assertTrue(time2.compareTo(time4) < 0);
        assertTrue(0 < time4.compareTo(time2));
    }

    @Test
    public void durationTest() {
        Time time1 = new Time(11, 45);
        Time time2 = new Time(12, 15);
        Time time3 = new Time(13, 14, 58);
        Time time4 = new Time(14, 15, 0);
        Time time5 = new Time(23, 40, 10);
        Time time6 = new Time(23, 59, 05);
        Time time7 = new Time(0, 0, 0);
        Time duration = Time.duration(time1, time2);
        assertEquals(0, duration.hour());
        assertEquals(30, duration.minute());
        assertEquals(0, duration.second());
        duration = Time.duration(time2, time3);
        assertEquals(0, duration.hour());
        assertEquals(59, duration.minute());
        assertEquals(58, duration.second());
        duration = Time.duration(time3, time4);
        assertEquals(1, duration.hour());
        assertEquals(0, duration.minute());
        assertEquals(2, duration.second());
        duration = Time.duration(time5, time6);
        assertEquals(0, duration.hour());
        assertEquals(18, duration.minute());
        assertEquals(55, duration.second());
        duration = Time.duration(time6, time7);
        assertEquals(0, duration.hour());
        assertEquals(0, duration.minute());
        assertEquals(55, duration.second());
        Time time8 = new Time(1, 0, 0);
        duration = Time.duration(time6, time8);
        assertEquals(1, duration.hour());
        assertEquals(0, duration.minute());
        assertEquals(55, duration.second());
        duration = Time.duration(time2, time1);
        assertEquals(23, duration.hour());
        assertEquals(30, duration.minute());
        assertEquals(0, duration.second());
    }

    @Test
    public void asMinutesTest() {
        assertEquals(0, (new Time(0, 0, 0)).asMinutes());
        assertEquals(0, (new Time(0, 0, 28)).asMinutes());
        assertEquals(1, (new Time(0, 0, 31)).asMinutes());
        assertEquals(25, (new Time(0, 25, 0)).asMinutes());
        assertEquals(60, (new Time(1, 0, 0)).asMinutes());
        assertEquals(84, (new Time(1, 24, 0)).asMinutes());
        assertEquals(640, (new Time(10, 39, 41)).asMinutes());
        assertEquals(780, (new Time(13, 0, 0)).asMinutes());
    }
}
