import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.ArrayList;

/**
 * Tests for class Route.
 *
 * @author  Dr. Jody Paul
 * @version 1.1 ($Id: RouteTest.java 340 2016-05-01 17:45:51Z joshua $)
 */
public class RouteTest {

    /**
     * Used to hold station list from Route double
     */
    private List<Station> theStations;

    /**
     * Used to hold time list from Route Double
     */
    private List<Time> theTimes;

    /**
     * Test for empty route object through station list.
     */
    @Test
    public void testStaionListSizeFromEmptyObject() {
        Route emptyRoute = new Route();
        theStations = emptyRoute.stations();

        assertEquals(0, theStations.size());
    }

    /**
     * Test for empty route object through time list.
     */
    @Test
    public void testTimeListSizeFromEmptyObject() {
        Route emptyRoute = new Route();
        theTimes = emptyRoute.travelTimes();

        assertEquals(0, theTimes.size());
    }

    /**
     * When a station is added to the route the size of the station list
     * shoulf return 1.
     */
    @Test
    public void stationListSizeTestWithOneStation() {
        Route buildARoute = new Route();
        Station station0 = new Station("Initial Station");
        buildARoute.addStation(station0, new Time());

        assertEquals(1, buildARoute.stations().size());
    }

    /**
     * Compares object in the route object's station list with the actual
     * initialized station object, to verify they are the same.
     */
    @Test
    public void ensureObjectInRouteObjectStationListIsStationObject() {
        Route buildARoute = new Route();
        Station station0 = new Station("Initial Station");
        buildARoute.addStation(station0, new Time());

        assertEquals(station0.getClass(), buildARoute.stations().get(0).getClass());
    }

    /**
     * Verifies station list contains the added objects.
     */
    @Test
    public void testStationListForAddedObjects() {
        Route buildARoute = new Route();
        Station station0 = new Station("Initial Station");
        buildARoute.addStation(station0, new Time());
        Station station1 = new Station("Station One");
        buildARoute.addStation(station1, new Time(0, 15));
        theStations = buildARoute.stations();

        assertEquals(theStations.get(0).getName(), station0.getName());
        assertEquals(theStations.get(1).getName(), station1.getName());
    }

    /**
     * Retrieves object from stations list and verifies it is the right
     * station object added.
     */
    @Test
    public void ensureStationListElementsEqualStationObjectsAdded() {
        Route buildARoute = new Route();
        Station station0 = new Station("Initial Station");
        buildARoute.addStation(station0, new Time());
        Station station1 = new Station("Station One");
        buildARoute.addStation(station1, new Time(0, 15));
        theStations = buildARoute.stations();

        assertEquals(station0.getName(), theStations.get(0).getName());
        assertEquals(station1.getName(), theStations.get(1).getName());
    }

    /**
     * Time list should only have object from station 1.
     */
    @Test
    public void testTimesListHasAddedObjectfromOnlyNonEmptyTimeObject() {
        Route buildARoute = new Route();
        Station station0 = new Station("Initial Station");
        buildARoute.addStation(station0, null);
        Station station1 = new Station("Station One");
        buildARoute.addStation(station1, new Time(0, 15));
        theTimes = buildARoute.travelTimes();

        assertEquals(1, theTimes.size());
    }

    /**
     * Object in Times List should equal the Time object from station 1.
     */
    @Test
    public void testTimesListObjectIsNonEmptyTimeObject() {
        Route buildARoute = new Route();
        Station station0 = new Station("Initial Station");
        buildARoute.addStation(station0, null);
        Station station1 = new Station("Station One");
        buildARoute.addStation(station1, new Time(0, 15));
        theTimes = buildARoute.travelTimes();

        assertEquals(new Time(0, 15), theTimes.get(0));
    }

    /**
     * List should have no issues taking multitude of objects
     */
    @Test
    public void ensureListTakesThreeStationObjects() {
        Route buildARoute = new Route();
        Station station0 = new Station("Initial Station");
        buildARoute.addStation(station0, new Time());
        Station station1 = new Station("Station One");
        buildARoute.addStation(station1, new Time(0, 15));
        Station station2 = new Station("Station Two");
        buildARoute.addStation(station2, new Time(1, 10));
        theStations = buildARoute.stations();

        assertEquals(3, theStations.size());
    }

    /**
     * Time list should only hold non-empty time objects so it should have
     * no object for station0.
     */
    @Test
    public void testTimeListForOnlyTwoNonEmtpyTimeObjects() {
        Route buildARoute = new Route();
        Station station0 = new Station("Initial Station");
        buildARoute.addStation(station0, null);
        Station station1 = new Station("Station One");
        buildARoute.addStation(station1, new Time(0, 15));
        Station station2 = new Station("Station Two");
        buildARoute.addStation(station2, new Time(1, 10));
        theTimes = buildARoute.travelTimes();

        assertEquals(2, theTimes.size());
    }

    /**
     * Time list elements should contain time objects created in Station 1
     * and station 2, in order from 1-2.
     */
    @Test
    public void ensureTimeListElementsEqualsTheNonEmptyTimeObjectsCreated() {
        Route buildARoute = new Route();
        Station station0 = new Station("Initial Station");
        buildARoute.addStation(station0, null);
        Station station1 = new Station("Station One");
        buildARoute.addStation(station1, new Time(0, 15));
        Station station2 = new Station("Station Two");
        buildARoute.addStation(station2, new Time(1, 10));
        theTimes = buildARoute.travelTimes();

        assertEquals(new Time(0, 15), theTimes.get(0));
        assertEquals(new Time(1, 10), theTimes.get(1));
    }

    /**
     * The list returned from the method stations() should
     * be the same as the original stations list but is.
     * a copy instead
     */
    @Test
    public void ensureDeepCopyOfStationsIsSameAsOriginal() {
        Route buildARoute = new Route();
        Station station0 = new Station("Initial Station");
        buildARoute.addStation(station0, new Time(8, 0));
        Station station1 = new Station("Station One");
        buildARoute.addStation(station1, new Time(8, 15));
        Station station2 = new Station("Station Two");
        buildARoute.addStation(station2, new Time(8, 30));

        List<Station> origStationList = new ArrayList<Station>();
        origStationList.add(station0);
        origStationList.add(station1);
        origStationList.add(station2);

        List<Station> deepCopyStationsList = buildARoute.stations();

        assertEquals(origStationList.size(), deepCopyStationsList.size());

        assertEquals(station0.getName(), deepCopyStationsList.get(0).getName());
        assertEquals(station0.passengerArrivalFn(), deepCopyStationsList.get(0).passengerArrivalFn());
        assertEquals(station0.passengers().size(), deepCopyStationsList.get(0).passengers().size());

        assertEquals(station1.getName(), deepCopyStationsList.get(1).getName());
        assertEquals(station1.passengerArrivalFn(), deepCopyStationsList.get(1).passengerArrivalFn());
        assertEquals(station1.passengers().size(), deepCopyStationsList.get(1).passengers().size());

        assertEquals(station2.getName(), deepCopyStationsList.get(2).getName());
        assertEquals(station2.passengerArrivalFn(), deepCopyStationsList.get(2).passengerArrivalFn());
        assertEquals(station2.passengers().size(), deepCopyStationsList.get(2).passengers().size());
    }

    /**
     * The list returned from the method travelTimes() should
     * be the same as the original travel times list but is
     * a copy instead
     */
    @Test
    public void ensureDeepCopyOfTravelTimesIsSameAsOriginal() {
        Route buildARoute = new Route();
        Station station0 = new Station("Initial Station");
        Time station0Time = new Time(8, 0);
        buildARoute.addStation(station0, station0Time);
        Station station1 = new Station("Station One");
        Time station1Time = new Time(8, 15);
        buildARoute.addStation(station1, station1Time);
        Station station2 = new Station("Station Two");
        Time station2Time = new Time(8, 15);
        buildARoute.addStation(station2, station2Time);

        List<Time> origTravelTimesList = new ArrayList<Time>();
        origTravelTimesList.add(station0Time);
        origTravelTimesList.add(station1Time);
        origTravelTimesList.add(station2Time);

        List<Time> deepCopyTravelTimesList = buildARoute.travelTimes();

        assertEquals(origTravelTimesList.size(), deepCopyTravelTimesList.size());

        assertEquals(station0Time.hour(), deepCopyTravelTimesList.get(0).hour());
        assertEquals(station0Time.minute(), deepCopyTravelTimesList.get(0).minute());

        assertEquals(station1Time.hour(), deepCopyTravelTimesList.get(1).hour());
        assertEquals(station1Time.minute(), deepCopyTravelTimesList.get(1).minute());

        assertEquals(station2Time.hour(), deepCopyTravelTimesList.get(2).hour());
        assertEquals(station2Time.minute(), deepCopyTravelTimesList.get(2).minute());
    }
}
