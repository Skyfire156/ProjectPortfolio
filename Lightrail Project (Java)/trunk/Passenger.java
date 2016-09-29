/**
 * Light-rail passenger.
 * Each passenger is associated with a
 * starting station and a destination station.
 *
 * @author Dr. Jody Paul
 * @author Richard DeSilvey
 * @version 1.0 ($Id: Passenger.java 378 2016-05-02 03:32:47Z joshua $)
 */
public class Passenger {

    /** A passengers starting station. */
    private Station start;

    /** A passengers destination. */
    private Station destination;

    /**
     * Constructs a passenger with specified source and destination stations.
     *
     * @param start the start station; must not be null
     * @param destination the destination station; must not be null; must not be
     * identical to the start station
     * @throws IllegalArgumentException start or destination stations can't be
     * null
     */
    public Passenger(final Station start, final Station destination) {
        setPassenger(start, destination);
    }


    /**
     * Copy constructor that copies the given passenger but the start and
     * destination stations are also copies.
     * @param toCopy the Passenger being copied.
     * @throws IllegalArgumentException start or destination stations can't be
     * null
     */
    public Passenger(final Passenger toCopy) {
        Station startCopy = new Station(toCopy.start.getName());
        Station destinationCopy = new Station(toCopy.destination.getName());
        setPassenger(startCopy, destinationCopy);
    }

   /**
    * Set the passenger start and end destination.
    * @param start station the passengers starting station
    * @param destination the passengers destination
    * @throws IllegalArgumentException if start or destination is null
    */
    private void setPassenger(final Station start, final Station destination)
            throws IllegalArgumentException {
        if (start == null) {
            throw new IllegalArgumentException("Start Station can't be null");
        }

        if (destination == null) {
            throw new IllegalArgumentException("Destination can't be null");
        }

        this.start = start;
        this.destination = destination;
    }

    /**
     * Retrieves the passenger's starting station.
     * @return the start station
     */
    public Station start() {
        return start;
    }

    /**
     * Retrieves the passenger's destination station.
     * @return the destination station
     */
    public Station destination() {
        return destination;
    }
}
