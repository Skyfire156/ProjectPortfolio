
import java.util.List;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Tests the Station class.
 *
 * @author Dr. Jody Paul
 * @version 1.1 ($Id: StationTest.java 373 2016-05-02 02:55:12Z joshua $)
 */
public class StationTest {

    private final Station testArrival;
    private final Station testDestination;

    public StationTest() {
        testArrival = new Station("Test Arrival");
        testDestination = new Station("Test Destination");
    }

    /**
     * Verifies adding passengers to a station.
     */
    @Test
    public void addMultiplePassengersTest() {

        String failedMsg = "Passenger failed to be added";
        int numberOfPassengers = 10;

        for (int i = 0; i < numberOfPassengers; i++) {
            Passenger toAdd = new Passenger(testArrival, testDestination);
            assertTrue(failedMsg, testArrival.addPassenger(toAdd));
        }

        failedMsg = "The number of passengers waiting was incorrect";
        int expectedWaiting = numberOfPassengers;
        int actualWaiting = testArrival.numberOfPassengersWaiting();

        assertEquals(failedMsg, expectedWaiting, actualWaiting);

    }

    /**
     * Verifies removing passengers from the station queue.
     */
    @Test
    public void nextPassengerTest() {

        String failedMsg;

        List<Passenger> passengerList = new ArrayList<>();
        int numberOfPassengers = 10;

        for (int i = 0; i < numberOfPassengers; i++) {
            Passenger toAdd = new Passenger(testArrival, testDestination);
            passengerList.add(toAdd);
            testArrival.addPassenger(toAdd);
        }

        failedMsg = "There was a passenger mismatch on iteration ";
        for (int i = 0; i < numberOfPassengers; i++) {

            Passenger fromList = passengerList.get(i);
            Passenger fromStation = testArrival.nextPassenger();

            assertEquals(failedMsg + i, fromList, fromStation);
            assertEquals(9 - i, testArrival.numberOfPassengersWaiting());

        }

        assertEquals(0, testArrival.numberOfPassengersWaiting());
    }

    /**
     * Tests to see if the passenger arrival function is null or not.
     */
    @Test
    public void nullPassengerArrivalFuncTest() {
        Station station1 = new Station("Station One");

        String failedMsg = "There was no passenger function";
        assertNotNull(failedMsg, station1.passengerArrivalFn());
    }

    /**
     * Verifies passenger arrival function attribute.
     */
    @Test
    public void passengerArrivalFunctionTest() {

        PassengerArrivalFunction func1 = new PassengerArrivalFunction(1000);
        Station station1 = new Station("Station One", func1);

        PassengerArrivalFunction func2 = new PassengerArrivalFunction(2000);
        Station station2 = new Station("Station Two", func2);

        String failedMsg = "Arrival Functions didn't match";
        assertEquals(failedMsg, func2, station2.passengerArrivalFn());

        failedMsg = "Arrival Functions were expected to be different";
        assertFalse(failedMsg, func2.equals(station1.passengerArrivalFn()));
    }

}
