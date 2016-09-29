/**
 * A Poisson distribution.
 * Algorithm described by Donald Knuth
 * (see: <A HREF="http://en.wikipedia.org/wiki/Poisson_distribution">
 * Poisson Distribution</A>)
 * and translated into Java by Robert Sedgewick and Kevin Wayne
 * (see: <A HREF="http://introcs.cs.princeton.edu/java/stdlib">
 * Standard Libraries</A>).
 * @author Adapted by Jody Paul
 * @version 1.2 ($Id: Poisson.java 348 2016-05-01 18:40:48Z joshua $)
 */
public final class Poisson implements ProbabilityDistribution {
    /**
     * Returns a random integer from a Poisson distribution with specified mean.
     * @param  mean the mean of the Poisson distribution
     * @return a random integer from a Poisson distribution of given mean
     * @throws IllegalArgumentException unless <tt>mean &gt; 0.0</tt>
     *           and not infinite
     */
    public int nextSample(final double mean) {
        if (!(mean > 0.0)) {
            throw new IllegalArgumentException(
                        "Parameter mean must be positive");
        }
        if (Double.isInfinite(mean)) {
            throw new IllegalArgumentException(
                        "Parameter mean must not be infinite");
        }
        // Use Knuth's algorithm to approximate exponential from uniform.
        int k = 0;
        double p = 1.0;
        double expNMean = Math.exp(-mean);
        do {
            k++;
            p *= Math.random();
        } while (p >= expNMean);
        return k - 1;
    }
}