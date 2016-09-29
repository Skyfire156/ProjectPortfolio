import java.util.LinkedList;
import java.util.Queue;
/**
 * A station along a light-rail route.
 * <P>
 * Each station has a queue of passengers.
 * Passengers arrive in accordance with the
 * station's passenger arrival function.
 * </P>
 * @author Dr. Jody Paul
 * @author Richard DeSilvey
 * @version 1.0 ($Id: Station.java 374 2016-05-02 03:06:45Z joshua $)
 */
public class Station {

    /** The name of the station. */
    private String name;

    /** Initial passengerArrivalFunction. */
    private PassengerArrivalFunction arrivalFn;

    /** A queue of the passengers at the station. */
    private Queue<Passenger> passengers;

    /**
     * Constructs a light-rail station with a given name
     * and a default passenger arrival function.
     * The initial queue of waiting passengers is empty.
     * @param name the name of this station
     */
    public Station(final String name) {
        this.name = name;
        passengers = new LinkedList<>();
        arrivalFn = new PassengerArrivalFunction();
    }

    /**
     * Constructs a light-rail station with a given name
     * and specified passenger arrival function.
     * The initial queue of waiting passengers is empty.
     * @param name the name of this station
     * @param arrivalFn the passenger arrival function
     */
    public Station(final String name,
                   final PassengerArrivalFunction arrivalFn) {
        this.arrivalFn = arrivalFn;
        this.name = name;
        passengers = new LinkedList<>();
    }

    /**
     * Add a new passenger to the waiting queue.
     * @param passenger the passenger to be queued
     * @return <tt>true</tt> if the passenger was added to the queue;
     *         <tt>false</tt> otherwise.
     */
    public boolean addPassenger(final Passenger passenger) {
        return passengers.add(passenger);
    }

    /**
     * Retrieves and removes the next passenger from the waiting queue.
     * @return the passenger at the head of the queue;
     *         <tt>null</tt> if there are no passengers waiting
     */
    public Passenger nextPassenger() {
        return passengers.poll();
    }

    /**
     * Retrieves a copy of the passengers waiting board a train as a queue.
     * @return the passengers ordered by arrival at this station
     */
    public Queue<Passenger> passengers() {

        Queue<Passenger> copyQueue = new LinkedList<>();

        passengers.forEach(passenger -> {
            copyQueue.add(new Passenger(passenger));
        });

        return copyQueue;
    }

    /**
     * Retrieves the number of passengers waiting at this station.
     * @return the number of passengers
     */
    public int numberOfPassengersWaiting() {
        return passengers.size();
    }

    /**
     * Retrieves the passenger arrival function for this station.
     * @return the passenger arrival function
     */
    public PassengerArrivalFunction passengerArrivalFn() {
        return arrivalFn;
    }
    /**
     * Retrieves the name of this station.
     * @return the name of the station
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        String str = "[" + name + "]";
        return str;
    }
}
