/**
 * A boarding function in which passengers that board
 * later require increasingly more time to board.
 *
 * @author Dr. Jody Paul
 * @version 1.0 ($Id: DelayedBoarding.java 363 2016-05-01 22:21:33Z joshua $)
 */
public class DelayedBoarding extends BoardingFunction {
    /** Value for the groupBoardingTime hour. */
    private int groupBoardingTimeHour = 0;

    /** Value for the groupBoardingTime minute. */
    private int groupBoardingTimeMinute = 1;

    /** Values for the incrementalDelay hour. */
    private int incrementalDelayHour = 0;

    /** Values for the incrementalDelay minute. */
    private int incrementalDelayMinute = 0;

    /** Values for the incrementalDelay second. */
    private int incrementalDelaySecond = 15;

   /** Size of passenger group that can board simultaneously. */
    private int groupSize = 10;

    /** Time to board a group of passengers. */
    private Time groupBoardingTime = new Time(groupBoardingTimeHour,
                                    groupBoardingTimeMinute);

    /** Incremental delay for subsequent groups. */
    private Time incrementalDelay = new Time(incrementalDelayHour,
                                             incrementalDelayMinute,
                                             incrementalDelaySecond);


    /**
     * Constructs a boarding function with incremental delays.
     * @param numberOfSimultaneousBoarders the number of passengers that
     *            can board simultaneously
     * @param boardingTime the amount of time it takes for an initial
     *            group of passengers to board simultaneously
     * @param delayIncrement the amount of time to add for each subsequent
     *            group of passengers to board
     */
    public DelayedBoarding(final Integer numberOfSimultaneousBoarders,
                           final Time boardingTime,
                           final Time delayIncrement) {
        this.groupSize = numberOfSimultaneousBoarders;
        this.groupBoardingTime = boardingTime;
        this.incrementalDelay = delayIncrement;
    }

    /**
     * Computes the time required to board a specified number of passengers.
     * @param numberOfPassengers the number of passengers
     * @return the amount of time needed to board the specified
     *             number of passengers
     */
    @Override
    public Time timeToBoard(final Integer numberOfPassengers) {
        /** Values for the totalBoardingTime object. */
        int totalBoardingTimeHour = 0;
        int totalBoardingTimeMinute = 0;
        int totalBoardingTimeSeconds = 0;

        /** Values for the bardingDelay object. */
        int boardingDelayHours = 0;
        int boardingDelayMinutes = 0;
        int boardingDelaySeconds = 0;

        Time totalBoardingTime = new Time(totalBoardingTimeHour,
                                          totalBoardingTimeMinute,
                                          totalBoardingTimeSeconds);
        Time boardingDelay = new Time(boardingDelayHours,
                                      boardingDelayMinutes,
                                      boardingDelaySeconds);
        int numberOfGroups = (int) Math.ceil(numberOfPassengers.doubleValue()
                                             / this.groupSize);
        for (int i = 0; i < numberOfGroups; i++) {
            totalBoardingTime.increment(this.groupBoardingTime);
            totalBoardingTime.increment(boardingDelay);
            boardingDelay.increment(this.incrementalDelay);
        }
        return totalBoardingTime;
    }
}
