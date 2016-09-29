/**
 * Boarding function that maps a number of passengers
 * to a duration.
 *
 * @author Dr. Jody Paul
 * @version 1.0 ($Id: BoardingFunction.java 350 2016-05-01 18:51:14Z joshua $)
 */
public abstract class BoardingFunction {
    /**
     * Returns the time to board a given number of passengers.
     * @param numberOfPassengers the number of passengers
     * @return the time required to board
     */
    public abstract Time timeToBoard(Integer numberOfPassengers);
}
