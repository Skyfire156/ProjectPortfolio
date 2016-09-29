/**
 * A discrete probability distribution.
 *
 * @author Dr. Jody Paul
 * @version 1.0
 * ($Id: ProbabilityDistribution.java 347 2016-05-01 18:34:58Z joshua $)
 */
public interface ProbabilityDistribution {
    /**
     * Returns a random integer from the probability distribution.
     * @param mean the mean of the distribution
     * @return a random value using the associated probability distribution
     */
    int nextSample(final double mean);
}
