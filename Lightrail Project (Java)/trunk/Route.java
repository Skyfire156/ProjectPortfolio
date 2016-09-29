import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
/**
 * A light-rail route.
 *
 * @author Dr. Jody Paul
 * @version 1.0 ($Id: Route.java 360 2016-05-01 21:45:37Z joshua $)
 */
public class Route {
    /** The list of all stations on this Route. */
    private List<Station> stations;

    /** The list of all arrivals times between this route. */
    private List<Time> travelTimesList;

    /**
     * Constructs a Route with no stations.
     */
    public Route() {
        stations = new ArrayList<>();
        travelTimesList = new ArrayList<>();
    }

    /**
     * Constructs a Route with one station.
     * @param firstStation the first station on this route
     */
    public Route(final Station firstStation) {
        stations = new ArrayList<>();
        stations.add(firstStation);
        travelTimesList = new ArrayList<>();
    }

    /**
     * Copy constructor.
     * @param orig the route to be copied
     */
    public Route(final Route orig) {
        stations = orig.stations();
        travelTimesList = orig.travelTimes();
    }

    /**
     * Add a station to the end of this route.
     * (Handles the special case in which the route has not
     * existing stations by adding this station as the
     * starting station of the route, ignoring the travel time.)
     * @param station the station to be appended; ignored if <tt>null</tt>
     * @param travelTime the time from the previous endpoint of the route
     *                   to the new station; must not be <tt>null</tt> unless
     *                   this is the special case of adding the initial
     *                   station
     */
    public void addStation(final Station station, final Time travelTime) {

        if (travelTime == null && !stations.isEmpty()) {
            throw new IllegalArgumentException("Time can't be null");
        }

        if (station != null) {
            if (travelTime != null) {
                travelTimesList.add(travelTime);
            }
            stations.add(station);
        }
    }

    /**
     * Returns the stations on this route as a deep copy
     * of the original stations List.
     * @return the stations on this route
     */
    public List<Station> stations() {
        List<Station> copyStationList = new ArrayList<Station>();
        Station stationCopy;
        Queue<Passenger> stationCopyPassengers;

        for (int i = 0; i < stations.size(); i++) {
                stationCopyPassengers = createCopiedPassenger(
                                         stations.get(i).passengers(),
                                         stations.get(i));

                stationCopy = createCopiedStation(stations.get(i));
                copyStationList.add(stationCopy);
        }

        return copyStationList;
    }

    /**
     * Creates a copy of Station object for security purposes.
     * @param origStation Station from which to copy
     * @return an Obecjt copy of the original Station
     */
    private Station createCopiedStation(final Station origStation) {
        Station stationCopy;
        PassengerArrivalFunction stationCopyPaf =
                                 origStation.passengerArrivalFn();

        String stationName = origStation.getName();
        stationCopy = new Station(stationName, stationCopyPaf);

        return stationCopy;
    }

    /**
     * Creates a copy of a Passenger Queue from the original station for
     * security purposes.
     * @param origPassengerQueue passengers at the station to be copied
     * @param origStation station being copied
     * @return a copy of the passenger Queue at teh original Station
     */
    private Queue<Passenger> createCopiedPassenger(
                             Queue<Passenger> origPassengerQueue,
                             final Station origStation) {
        Station copyStartStation, copyDestinationStation;
        Passenger copyPassenger;
        Queue<Passenger> copyQueue = new LinkedList<>();
        origPassengerQueue = new LinkedList<>(origPassengerQueue);
        Passenger[] passengers =
                        origPassengerQueue.toArray(
                        new Passenger[origPassengerQueue.size()]);

        for (int i = 0; i < passengers.length - 1; i++) {
                copyStartStation = passengers[i].start();
                copyDestinationStation = passengers[i].destination();
                copyPassenger = new Passenger(copyStartStation,
                copyDestinationStation);
                copyQueue.add(copyPassenger);
        }
        return copyQueue;
    }

    /**
     * Returns the times between stops on this route as an ordered list
     * which was a deep copy of this object's travelTimeList.
     * @return the durations between stops on this route
     */
    public List<Time> travelTimes() {
       List<Time> copyTravelTimes = new ArrayList<Time>();

        for (int i = 0; i < travelTimesList.size(); i++) {
            Time copyTime = createCopiedTime(travelTimesList.get(i));
            copyTravelTimes.add(copyTime);
        }
        return copyTravelTimes;
    }

    /**
     * Creates a copy of a Time object for security purposes.
     * @param origTime Time object being copied
     * @return copyTime Time a copy of the original Time object
    */
    private Time createCopiedTime(final Time origTime) {
        Time copyTime = new Time(origTime);
        return copyTime;
    }
}
