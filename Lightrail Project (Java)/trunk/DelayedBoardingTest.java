import static org.junit.Assert.assertEquals;
import org.junit.Test;
/**
 * Delayed boarding time tests.
 *
 * @author  Dr. Jody Paul
 * @version $Revision: 350 $ ($Id: DelayedBoardingTest.java 350 2016-05-01 18:51:14Z joshua $)
 */
public class DelayedBoardingTest {
    @Test
    public void boardingTimeTest() {
        DelayedBoarding delayedBo1 = new DelayedBoarding(10,
                                                        new Time(0, 0, 20),
                                                        new Time(0, 0, 10));
        assertEquals(new Time(0, 0, 0), delayedBo1.timeToBoard(0));
        assertEquals(new Time(0, 0, 20), delayedBo1.timeToBoard(1));
        assertEquals(new Time(0, 0, 20), delayedBo1.timeToBoard(9));
        assertEquals(new Time(0, 0, 20), delayedBo1.timeToBoard(10));
        assertEquals(new Time(0, 0, 50), delayedBo1.timeToBoard(11));
        assertEquals(new Time(0, 0, 50), delayedBo1.timeToBoard(19));
        assertEquals(new Time(0, 0, 50), delayedBo1.timeToBoard(20));
        assertEquals(new Time(0, 1, 30), delayedBo1.timeToBoard(21));
        assertEquals(new Time(0, 2, 20), delayedBo1.timeToBoard(35));
    }
}
