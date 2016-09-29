import static org.junit.Assert.assertEquals;
import org.junit.Test;
/**
 * Linear boarding time tests.
 *
 * @author  Dr. Jody Paul
 * @version 1.0 ($Id: LinearBoardingTest.java 349 2016-05-01 18:46:56Z joshua $)
 */
public class LinearBoardingTest {
    @Test
    public void boardingTimeTest() {
        LinearBoarding linearBo1 = new LinearBoarding(10, new Time(0, 0, 20));
        assertEquals(new Time(0, 0, 0), linearBo1.timeToBoard(0));
        assertEquals(new Time(0, 0, 20), linearBo1.timeToBoard(1));
        assertEquals(new Time(0, 0, 20), linearBo1.timeToBoard(9));
        assertEquals(new Time(0, 0, 20), linearBo1.timeToBoard(10));
        assertEquals(new Time(0, 0, 40), linearBo1.timeToBoard(11));
        assertEquals(new Time(0, 0, 40), linearBo1.timeToBoard(19));
        assertEquals(new Time(0, 0, 40), linearBo1.timeToBoard(20));
        assertEquals(new Time(0, 1, 0), linearBo1.timeToBoard(21));
        assertEquals(new Time(0, 1, 20), linearBo1.timeToBoard(35));
    }
}