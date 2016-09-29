import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class SimulationTest {
    /** Empty Simulation for testing. */
    private Simulation emptyModel;

    /** Example Simulation for testing. */
    private Simulation example;

    /** Boarding function for testing. */
    private BoardingFunction delayBrdFn1 = new DelayedBoarding(10,
                                                               new Time(0, 2, 0),
                                                               new Time(0, 0, 1));
    /** Route for testing. */
    private Route testRoute1 = new Route();

    /** Schedule for testing. */
    private Schedule testSchedule1 = new Schedule();

    /** Minimum wait time for testing. */
    private Time minWaitTime1 = new Time(0, 5, 0);

    /** Maximum passengers for testing. */
    private Integer maxPassengers1 = new Integer(50);

    /** Passenger arrival function for testing. */
    private PassengerArrivalFunction arrivalFn1 = new PassengerArrivalFunction();

    /**
     * Set up the simulation object.
     */
    @Before
    public void setUp() {
        emptyModel = new Simulation();
        emptyModel.initializeModel(testRoute1, testSchedule1, minWaitTime1,
                                maxPassengers1, delayBrdFn1, arrivalFn1);
    }

    /**
     * Test an empty model.
     */
    @Test
    public void testEmptyModel() {
        assertEquals(0, emptyModel.stations().size());
        assertEquals(0, emptyModel.trains().size());
        assertEquals(0, (int) emptyModel.numberOfClumps());
    }

    @Test
    public void testRoute() {
        Station station0 = new Station("Station 1.");
        Station station1 = new Station("Station 2.");
        Station station2 = new Station("Station 3.");
        Station station3 = new Station("Station 4.");

        testRoute1 = new Route(station0);
        testRoute1.addStation(station1, new Time(0, 10, 0));
        testRoute1.addStation(station2, new Time(0, 10, 0));
        testRoute1.addStation(station3, new Time(0, 10, 0));

        example = new Simulation();
        example.initializeModel(testRoute1, testSchedule1, minWaitTime1,
                                maxPassengers1, delayBrdFn1, arrivalFn1);

        Station[] stationArray = example.stations().toArray(new Station[example.stations().size()]);

        assertEquals(stationArray[0].getName(), station0.getName());
        assertEquals(stationArray[1].getName(), station1.getName());
        assertEquals(stationArray[2].getName(), station2.getName());
        assertEquals(stationArray[3].getName(), station3.getName());
    }

    @Test
    public void testTrainDepartures() {
        testSchedule1.addDepartureTime(new Time(0, 20, 0));
        example = new Simulation();
        example.initializeModel(testRoute1, testSchedule1, minWaitTime1,
                                maxPassengers1, delayBrdFn1, arrivalFn1);

        example.advanceSimulation(new Time(0, 19, 0));
        assertEquals(0, example.trains().size());

        example.advanceSimulation(new Time(0, 2, 0));
        assertEquals(1, example.trains().size());
    }

    @Test
    public void testMultipleDepartures() {
        testSchedule1.addDepartureTime(new Time(0, 5, 0));
        testSchedule1.addDepartureTime(new Time(0, 10, 0));
        testSchedule1.addDepartureTime(new Time(0, 15, 0));

        example = new Simulation();
        example.initializeModel(testRoute1, testSchedule1, minWaitTime1,
                maxPassengers1, delayBrdFn1, arrivalFn1);

        example.advanceSimulation(new Time(0, 5, 0));
        assertEquals(1, example.trains().size());
        example.advanceSimulation(new Time(0, 5, 0));
        assertEquals(2, example.trains().size());
        example.advanceSimulation(new Time(0, 5, 0));
        assertEquals(3, example.trains().size());
    }
}
