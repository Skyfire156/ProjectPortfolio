import java.util.HashMap;
import java.util.Map;
/**
 * Defines a passenger arrival function.
 * An arrival function provides the number of passengers
 * expected to arrive during a specified interval of time.
 * Resolution is by minute (1440 discrete minutes per day).
 * <P>
 * The arrival function is comprised of
 * constituent functions corresponding to
 * specific time periods:
 * Off-Peak, Morning-Peak, and Evening-Peak.
 * Off-Peak is modeled by uniform distribution.
 * Morning-Peak and Evening-Peak are modeled by
 * Poisson distributions about their respective means
 * of 07:30 and 17:00.
 * </P><P>
 * The function chooses the maximum of the
 * values of each of the constituent functions.
 * </P>
 * @author Dr. Jody Paul
 * @version 1.1
 * ($Id: PassengerArrivalFunction.java 379 2016-05-02 03:55:08Z joshua $)
 */

public class PassengerArrivalFunction {
    /** Maximum arrival time. */
    private static final int MAXIMUM_ARRIVAL_TIME = 1440;
    /** Number of arrival times that occur. */
    private static final int NUMBER_OF_ARRIVAL_TIMES
                             = Time.HOURS_PER_DAY * Time.MINUTES_PER_HOUR;

    /** Default number of passenger arrivals per station per day. */
    public static final int DEFAULT_ARRIVALS_PER_DAY = 5000;

    /** Default fraction of passenger arrivals during morning peak period. */
    public static final double MORNING_PEAK_FRACTION = 0.40;

    /** Default fraction of passenger arrivals during evening peak periods. */
    public static final double EVENING_PEAK_FRACTION = 0.35;

    /** Default fraction of passenger arrivals during non-peak times. */
    public static final double OFF_PEAK_FRACTION
                               = 1.00 - (MORNING_PEAK_FRACTION
                                        + EVENING_PEAK_FRACTION);
    /** Default probability distribution for Off-Peak. */
    public static final ProbabilityDistribution DEFAULT_OFF_PEAK_DIST
    = new Uniform();

    /** Default probability distribution for Morning-Peak. */
    public static final ProbabilityDistribution DEFAULT_MORNING_PEAK_DIST
    = new Poisson();

    /** Default probability distribution for Evening-Peak. */
    public static final ProbabilityDistribution DEFAULT_EVENING_PEAK_DIST
    = new Poisson();

    /** Start time for Off-Peak. */
    public static final Time OFF_PEAK_START = new Time(0, 00);

    /** End time for Off-Peak. */
    public static final Time OFF_PEAK_END = new Time(23, 59);

    /** Mean time for Off-Peak. */
    public static final Time MEAN_OFF_PEAK_TIME = new Time(12, 00);

    /** Start time for Morning-Peak. */
    public static final Time MORNING_PEAK_START = new Time(6, 00);

    /**End time for Morning-Peak. */
    public static final Time MORNING_PEAK_END = new Time(9, 00);

    /** Mean time for Morning-Peak. */
    public static final Time MEAN_MORNING_PEAK_TIME = new Time(7, 30);

    /** Start time for Evening-Peak. */
    public static final Time EVENING_PEAK_START = new Time(15, 30);

    /** End time for Evening-Peak. */
    public static final Time EVENING_PEAK_END = new Time(18, 30);

    /**Mean time for Evening-Peak. */
    public static final Time MEAN_EVENING_PEAK_TIME = new Time(17, 0);

    /** Enumeration of time periods. */
    public enum TimePeriod {
        /** Off Peak enum. */
        OFF_PEAK("Off Peak"),
        /** Morning Peak enum. */
        MORNING_PEAK("Morning Peak"),
        /** Evening Peak. */
        EVENING_PEAK("Evening Peak"),
        /** All Day enum. */
        ALL_DAY("All Day");
        /** Human-oriented description. */
        private String descriptor;
        /**
         * Constructs a time period with an associated description.
         * @param description the description
         */
        TimePeriod(final String description) {
            this.descriptor = description;
        }
    }

    /** Expected number of passenger arrivals per day. */
    private int passengersExpectedPerDay;

    /** Off peak time probability distribution. */
    private ProbabilityDistribution distributionOffPeak;

    /** Morning peak time probability distribution. */
    private ProbabilityDistribution distributionMorningPeak;

    /** Evening peak time probability distribution. */
    private ProbabilityDistribution distributionEveningPeak;

    /** Fraction of passengers arriving during off peak time. */
    private double offPeakArrivalFraction;

    /** Fraction of passengers arriving during morning peak time. */
    private double morningPeakArrivalFraction;

    /** Fraction of passengers arriving during evening peak time. */
    private double eveningPeakArrivalFraction;

    /**
     * Constructs a default passenger arrival function.
     */
    public PassengerArrivalFunction() {
        this(PassengerArrivalFunction.DEFAULT_ARRIVALS_PER_DAY,
             PassengerArrivalFunction.DEFAULT_OFF_PEAK_DIST,
             PassengerArrivalFunction.DEFAULT_MORNING_PEAK_DIST,
             PassengerArrivalFunction.DEFAULT_EVENING_PEAK_DIST,
             PassengerArrivalFunction.OFF_PEAK_FRACTION,
             PassengerArrivalFunction.MORNING_PEAK_FRACTION,
             PassengerArrivalFunction.EVENING_PEAK_FRACTION);
    }
    /**
     * Constructs a passenger arrival function,
     * using specified expected number of passenger arrivals per day.
     * Defaults used for amount and distributions of passenger arrivals
     * associated with time periods.
     *
     * @param expectedPassengersPerDay the total number of passengers expected
     * per day
     */
    public PassengerArrivalFunction(final int expectedPassengersPerDay) {
        this(expectedPassengersPerDay,
             PassengerArrivalFunction.DEFAULT_OFF_PEAK_DIST,
             PassengerArrivalFunction.DEFAULT_MORNING_PEAK_DIST,
             PassengerArrivalFunction.DEFAULT_EVENING_PEAK_DIST,
             PassengerArrivalFunction.OFF_PEAK_FRACTION,
             PassengerArrivalFunction.MORNING_PEAK_FRACTION,
             PassengerArrivalFunction.EVENING_PEAK_FRACTION);
    }
    /**
     * Constructs a passenger arrival function,
     * using specified expected number of passenger arrivals per day
     * and probability distributions for time periods.
     * Default used for portion of total passenger arrivals
     * occurring during each time period.
     *
     * @param expectedPassengersPerDay the total number of passengers expected
     * per day
     * @param offPeakDistribution the probability distribution class for
     * off-peak periods
     * @param morningPeakDistribution the probability distribution class for
     * morning-peak
     * @param eveningPeakDistribution the probability distribution class for
     * evening-peak
     */
    public PassengerArrivalFunction(final int expectedPassengersPerDay,
                        final ProbabilityDistribution offPeakDistribution,
                        final ProbabilityDistribution morningPeakDistribution,
                        final ProbabilityDistribution eveningPeakDistribution) {
        this(expectedPassengersPerDay,
             offPeakDistribution,
             morningPeakDistribution,
             eveningPeakDistribution,
             PassengerArrivalFunction.OFF_PEAK_FRACTION,
             PassengerArrivalFunction.MORNING_PEAK_FRACTION,
             PassengerArrivalFunction.EVENING_PEAK_FRACTION);
    }
    /**
     * Constructs a passenger arrival function,
     * using specified expected number of passenger arrivals per day,
     * probability distributions for time periods, and fraction of
     * arrivals for time periods.
     *
     * @param expectedPassengersPerDay the total number of passengers expected
     * per day
     * @param offPeakDistribution the probability distribution class for
     * off-peak periods
     * @param morningPeakDistribution the probability distribution class for
     * morning-peak
     * @param eveningPeakDistribution the probability distribution class for
     * evening-peak
     * @param fractionOffPeak the fraction of arrivals occuring during off-peak
     * periods
     * @param fractionMorningPeak the fraction of arrivals occuring during the
     * morning-peak
     * @param fractionEveningPeak the fraction of arrivals occuring during the
     * evening-peak
     */
    public PassengerArrivalFunction(final int expectedPassengersPerDay,
                        final ProbabilityDistribution offPeakDistribution,
                        final ProbabilityDistribution morningPeakDistribution,
                        final ProbabilityDistribution eveningPeakDistribution,
                        final double fractionOffPeak,
                        final double fractionMorningPeak,
                        final double fractionEveningPeak) {
        passengersExpectedPerDay = expectedPassengersPerDay;
        distributionOffPeak = offPeakDistribution;
        distributionMorningPeak = morningPeakDistribution;
        distributionEveningPeak = eveningPeakDistribution;
        morningPeakArrivalFraction = fractionMorningPeak;
        eveningPeakArrivalFraction = fractionEveningPeak;
        offPeakArrivalFraction = fractionOffPeak;
    }
    /**
     * Retrieves the number of passengers arriving
     * at each discrete minute throughout the day.
     * @return all computed values of the function
     */
    public int[] passengerArrivals() {
        int minutes = Time.HOURS_PER_DAY * Time.MINUTES_PER_HOUR;
        int[] retval = new int[minutes];
        double mornPass = this.passengersExpectedPerDay
                * this.morningPeakArrivalFraction;
        double evePass = this.passengersExpectedPerDay
                * this.eveningPeakArrivalFraction;
        double offPass = this.passengersExpectedPerDay
                * this.offPeakArrivalFraction;
        for (int i = 0; i < mornPass; i++) {
            int arrivalTime = this.distributionMorningPeak.nextSample(
                              Time.duration(MORNING_PEAK_START,
                              MEAN_MORNING_PEAK_TIME).asMinutes());
            retval[arrivalTime + MORNING_PEAK_START.asMinutes()]++;
        }
        for (int i = 0; i < evePass; i++) {
            int arrivalTime = this.distributionEveningPeak.nextSample(
                              Time.duration(EVENING_PEAK_START,
                              MEAN_EVENING_PEAK_TIME).asMinutes());
            retval[arrivalTime + EVENING_PEAK_START.asMinutes()]++;
        }
        int mornstart = MORNING_PEAK_START.asMinutes();
        int mornend = MORNING_PEAK_END.asMinutes();
        int evestart = EVENING_PEAK_START.asMinutes();
        int eveend = EVENING_PEAK_END.asMinutes();
        for (int i = 0; i < offPass; i++) {
            int arrivalTime = this.distributionOffPeak.nextSample(
                              MEAN_OFF_PEAK_TIME.asMinutes());
            if (arrivalTime == MAXIMUM_ARRIVAL_TIME) {
                arrivalTime--;
            }
            if ((arrivalTime >= mornstart && arrivalTime <= mornend) || (
                 arrivalTime >= evestart && arrivalTime <= eveend)) {
                i--;
            } else {
                retval[arrivalTime]++;
            }
        }
        return retval;
    }
    /**
     * Determines the number of passengers arriving
     * in the specified interval.
     * @param startTime the beginning of the interval (inclusive)
     * @param endTime the end of the interval (exclusive)
     * @return the number of passengers arriving during the specified interval
     */
    public Integer numberOfArrivingPassengers(final Time startTime,
                                             final Time endTime) {
        int retval = 0;
        int[] dist = this.passengerArrivals();
        while (startTime.compareTo(endTime) < 0) {
            retval += dist[startTime.asMinutes()];
            startTime.incrementOneMinute();
        }
        return retval;
    }
    /**
     * Retrieves the underlying functions values.
     * @return all computed values of the composing functions
     */
    public Map<TimePeriod, int[]> composingValues() {
        int[] dist = this.passengerArrivals();
        int minPerDay = Time.HOURS_PER_DAY * Time.MINUTES_PER_HOUR;
        int mornDuration = MORNING_PEAK_END.asMinutes()
                - MORNING_PEAK_START.asMinutes();
        int eveDuration = EVENING_PEAK_END.asMinutes()
                - EVENING_PEAK_START.asMinutes();
        int offDuration = minPerDay - mornDuration - eveDuration;
        int[] mornPeak = new int[minPerDay];
        int[] evePeak = new int[minPerDay];
        int[] offPeak = new int[minPerDay];
        for (int i = 0; i < mornDuration; i++) {
            mornPeak[MORNING_PEAK_START.asMinutes() + i] = dist[
                     MORNING_PEAK_START.asMinutes() + i];
        }
        for (int i = 0; i < eveDuration; i++) {
            evePeak[EVENING_PEAK_START.asMinutes() + i] = dist[
                    EVENING_PEAK_START.asMinutes() + i];
        }
        for (int i = 0; i < offDuration; i++) {
            int offset = 0;
            if (i < MORNING_PEAK_START.asMinutes()) {
                offset = 0;
            } else if (i + offset < EVENING_PEAK_START.asMinutes()) {
                offset += mornDuration;
            } else if (i + offset >= EVENING_PEAK_START.asMinutes()) {
                offset += eveDuration;
            }
            offPeak[i + offset] = dist[i + offset];
        }
        Map<TimePeriod, int[]> retval = new HashMap();
        retval.put(TimePeriod.ALL_DAY, dist);
        retval.put(TimePeriod.MORNING_PEAK, mornPeak);
        retval.put(TimePeriod.EVENING_PEAK, evePeak);
        retval.put(TimePeriod.OFF_PEAK, offPeak);
        return retval;
    }
    /**
     * Determines the maximum of three values.
     * @param value1 first value
     * @param value2 second value
     * @param value3 third value
     * @return the maximum of the three values
     */
    private static double maximum(final double value1, final double value2,
                                 final double value3) {
        return Math.max(value1, Math.max(value2, value3));
    }
}