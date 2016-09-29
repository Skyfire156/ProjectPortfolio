/**
 * A stepwise-linear boarding function.
 *
 * @author Dr. Jody Paul
 * @version 1.0 ($Id: LinearBoarding.java 372 2016-05-02 02:44:20Z joshua $)
 */
public class LinearBoarding extends BoardingFunction {
    /** Time it takes for a group to board in hours. */
    private int groupBoardingTimeHours = 0;
    /** Time it takes for a group to board in minutes. */
    private int groupBoardingTimeMinutes = 1;

    /** Constant time to board a group of passengers. */
    private Time groupBoardingTime = new Time(groupBoardingTimeHours,
                                              groupBoardingTimeMinutes);

    /** Size of passenger group that can board simultaneously. */
    private int groupSize = 1;

    /**
     * Constructs a stepwise-linear boarding function.
     * @param numberOfSimultaneousBoarders the number of passengers that
     *            can board simultaneously
     * @param boardingTime the amount of time it takes for a group of
     *            passengers to board simultaneously
     */
    public LinearBoarding(final Integer numberOfSimultaneousBoarders,
                          final Time boardingTime) {
        this.groupSize = numberOfSimultaneousBoarders.intValue();
        this.groupBoardingTime = boardingTime;
    }

    /**
     * Computes the time required to board a specified number of passengers.
     * @param numberOfPassengers the number of passengers
     * @return the amount of time needed to board the specified
     *             number of passengers
     */
    public Time timeToBoard(final Integer numberOfPassengers) {
        int totalBoardingTimeHours = 0;
        int totalBoardingTimeMinutes = 0;
        int totalBoardingTimeSeconds = 0;
        Time totalBoardingTime = new Time(totalBoardingTimeHours,
                                        totalBoardingTimeMinutes,
                                        totalBoardingTimeSeconds);
        int numberOfGroups = (int) Math.ceil(numberOfPassengers.doubleValue()
                                             / this.groupSize);
        for (int i = 0; i < numberOfGroups; i++) {
            totalBoardingTime.increment(this.groupBoardingTime);
        }
        return totalBoardingTime;
    }
}

