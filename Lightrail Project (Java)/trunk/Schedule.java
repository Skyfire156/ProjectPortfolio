import java.util.List;
import java.util.TreeSet;
import java.util.ArrayList;
/**
 * Schedule for departures of trains from the starting point of a route.
 * A schedule is comprised of departure times.
 * All departure times are unique.
 *
 * @author Dr. Jody Paul
 * @author Daniel Evans
 * @version 1.0 ($Id: Schedule.java 375 2016-05-02 03:06:59Z joshua $)
 */
public class Schedule {
    /** A set of all the departure times on the schedule. */
    private TreeSet<Time> departureTimesSet;

    /**
     * Constructs an empty schedule.
     */
    public Schedule() {
        departureTimesSet = new TreeSet<>();
    }

    /**
     * Copy constructor.
     * @param orig the schedule to be duplicated
     */
    public Schedule(final Schedule orig) {
        this();
        List<Time> departureTimes = orig.departureTimes();
        Time departureTime = new Time();
        for (int index = 0; index < departureTimes.size(); index++) {
            departureTime = departureTimes.get(index);
            departureTimesSet.add(departureTime);
        }
    }

    /**
     * Add a departure time to the schedule.
     * @param departureTime the departure time
     */
    public void addDepartureTime(final Time departureTime) {
        departureTimesSet.add(departureTime);
    }

    /**
     * Returns the schedule as a list of departure times
     * in ascending order by time.
     * @return list of departure times
     */
    public List<Time> departureTimes() {
        ArrayList<Time> listOfTimes = new ArrayList<>();
        if (departureTimesSet.isEmpty()) {
            return listOfTimes;
        }
        Time setPointer = departureTimesSet.first();
        for (int index = 0; index < departureTimesSet.size(); index++) {
            listOfTimes.add(setPointer);
            setPointer = departureTimesSet.higher(setPointer);
        }
        return listOfTimes;
    }
}
