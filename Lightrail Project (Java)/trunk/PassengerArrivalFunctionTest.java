import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
/**
 * The test class PassengerArrivalFunctionTest.
 *
 * @author  Dr. Jody Paul
 * @version 1.0 ($Id: PassengerArrivalFunctionTest.java 365 2016-05-01 22:46:13Z joshua $)
 */
public class PassengerArrivalFunctionTest {
    @Test
    public void arrivalDataAccessTest() {
        PassengerArrivalFunction paf = new PassengerArrivalFunction();
        int[] allArrivals = paf.passengerArrivals();
        assertEquals((Time.MINUTES_PER_HOUR * Time.HOURS_PER_DAY), allArrivals.length);
        paf = new PassengerArrivalFunction(100000);
        allArrivals = paf.passengerArrivals();
        assertEquals((Time.MINUTES_PER_HOUR * Time.HOURS_PER_DAY), allArrivals.length);
        for (int i : allArrivals) {
            assertTrue("May fail due to random sequence.",
                       0 < i);
        }
    }

    @Test
    public void uniformArrivalsTest() {
        final int expectedArrivals = 10000;
        final double tolerance = 0.05; // Accept 5% over/under.
        Uniform uniformDist = new Uniform();
        PassengerArrivalFunction paf = new PassengerArrivalFunction(expectedArrivals,
                                                                    uniformDist,
                                                                    uniformDist,
                                                                    uniformDist);
        double totalArrivals = paf.numberOfArrivingPassengers(new Time(0, 0), new Time(23, 59));
        assertEquals("May fail due to random sequence.",
                     expectedArrivals, totalArrivals, tolerance * expectedArrivals);
    }

    @Test
    public void uniformArrivalsBoundaryTest() {
        final int maxExpectedArrivals = 500;
        final double mean = 250;
        Uniform uniformDist = new Uniform();
        final int actualArrivals = uniformDist.nextSample(mean);

        assertTrue("Actual passenger arrivals should "
                + "not be greater than the max expected arrivals "
                + "since this is the upper boundary of our uniform interval",
                  actualArrivals <= maxExpectedArrivals);
    }

    @Test
    public void poissonArrivalsBoundaryTest() {
        final int maxExpectedArrivals = 500;
        final double mean = 250;
        Poisson poissonDist = new Poisson();
        final int actualArrivals = poissonDist.nextSample(mean);

        assertTrue("Actual passenger arrivals should "
                +  "not be greater than the max expected arrivals "
                +  "since this is the upper boundary of our poisson interval",
                actualArrivals <= maxExpectedArrivals);
    }

    @Test
    public void poissonArrivalsTest() {
        final int expectedArrivals = 10000;
        final double tolerance = 0.05;
        Poisson poissonDist = new Poisson();
        PassengerArrivalFunction paf = new PassengerArrivalFunction(
                                           expectedArrivals,
                                           poissonDist,
                                           poissonDist,
                                           poissonDist);
        double totalArrivals = paf.numberOfArrivingPassengers(new Time(0, 0),
                                                              new Time(23, 59));
        assertEquals("May fail due to random sequence.",
                 expectedArrivals, totalArrivals, tolerance * expectedArrivals);
    }

    @Test
    public void standardArrivalsTest() {
        PassengerArrivalFunction paf = new PassengerArrivalFunction(10000);
        int offPeakHourArrivals = paf.numberOfArrivingPassengers(new Time(2, 0), new Time(3, 0));
        int morningPeakHourArrivals = paf.numberOfArrivingPassengers(new Time(7, 0), new Time(8, 0));
        int eveningPeakHourArrivals = paf.numberOfArrivingPassengers(new Time(16, 30), new Time(17, 30));
        assertTrue(offPeakHourArrivals < eveningPeakHourArrivals);
        assertTrue(eveningPeakHourArrivals < morningPeakHourArrivals);
    }
}
