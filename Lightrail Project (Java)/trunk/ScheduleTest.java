import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
/**
 * Tests for the Schedule class.
 *
 * @author  Dr. Jody Paul
 * @version 1.0 ($Id: ScheduleTest.java 376 2016-05-02 03:14:22Z joshua $)
 */
public class ScheduleTest {

    private Schedule schedule = new Schedule();

    /**
     * Default Schedule constructor should return an empty departure times
     * list.
     */
    @Test
    public void emptyTimeListTest() {

        assertEquals(0, schedule.departureTimes().size());
    }

    /**
     * Ensures that when that the copy constructore properly copies the
     * Schedule object by comparing their list elements of departure times.
     */
    @Test
    public void testCopyConstructor() {

        schedule.addDepartureTime(new Time(0, 30));
        schedule.addDepartureTime(new Time(1, 30));
        schedule.addDepartureTime(new Time(2, 30));

        Schedule copySchedule = new Schedule(schedule);

    for (int i = 0; i < schedule.departureTimes().size(); i++) {

            assertEquals(schedule.departureTimes().get(i),
                         copySchedule.departureTimes().get(i));
    	}
    }

    /**
     * Tests for an expected number of elements in the departure times list.
     */
    @Test
    public void addDepartureTimeTest() {

        schedule.addDepartureTime(new Time(0, 30));
        schedule.addDepartureTime(new Time(1, 30));
        schedule.addDepartureTime(new Time(2, 30));

        assertEquals(3, schedule.departureTimes().size());
    }

    /**
     * After departure times list has elements it should be able to take more
     * elements by looping the function to fill it.
     */
    @Test
    public void additionalAddDepartureTest() {

        schedule.addDepartureTime(new Time(0, 30));
        schedule.addDepartureTime(new Time(1, 30));
        schedule.addDepartureTime(new Time(2, 30));

        for (int i = 0; i < 24; i++) {
            schedule.addDepartureTime(new Time(i, 15));
        }

        assertEquals(27, schedule.departureTimes().size());
    }

    /**
     * Departure times list should sort in chronological order, this verifies
     * every element is earlier than its next.
     */
    @Test
    public void chronologicalOrderTest() {

        schedule.addDepartureTime(new Time(0, 30));
        schedule.addDepartureTime(new Time(1, 30));
        schedule.addDepartureTime(new Time(2, 30));

        for (int i = 0; i < 24; i++) {
            schedule.addDepartureTime(new Time(i, 15));
        }
        Time latest = new Time(0, 0, 0);
        for (Time t : schedule.departureTimes()) {
            assertTrue(0 < t.compareTo(latest));
            latest = t;
        }
    }
    /**
     * Adds multiple depature time to Schedule object, two of the 5 are
     * duplicate times and should there never be added.
     */
    @Test
    public void testForDuplicateDepartureTimesByListSize() {

    	schedule.addDepartureTime(new Time(0, 30));
        schedule.addDepartureTime(new Time(1, 30));
        schedule.addDepartureTime(new Time(2, 30));
        schedule.addDepartureTime(new Time(2, 30));
        schedule.addDepartureTime(new Time(0, 30));

        assertEquals(3, schedule.departureTimes().size());
    }
}
