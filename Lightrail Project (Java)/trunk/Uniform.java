/**
 * A uniform distribution.
 *
 * @author Dr. Jody Paul
 * @version 1.0 ($Id: Uniform.java 343 2016-05-01 17:57:33Z joshua $)
 */
public class Uniform implements ProbabilityDistribution {
    /**
     * Returns a random integer from a uniform distribution [0, 2 * mean].
     * @param  mean the mean of the distribution
     * @return a random integer from a uniform distribution [0, 2 * mean]
     * @throws IllegalArgumentException if <tt>mean &le; 0.0</tt>
     */
    public int nextSample(final double mean) {
        if (mean < 0.0) {
            throw new IllegalArgumentException(
                        "Parameter mean must be positive");
        }
        return (int) Math.round(2.0 * mean * Math.random());
    }
}
