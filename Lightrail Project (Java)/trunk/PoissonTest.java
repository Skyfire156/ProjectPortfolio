import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
/**
 * The test class PoissonTest.
 *
 * @author  Dr. Jody Paul
 * @version 1.0 ($Id: PoissonTest.java 348 2016-05-01 18:40:48Z joshua $)
 */
public class PoissonTest {
    @Test
    public void smallMeanSampleTest() {
        Poisson p1 = new Poisson();
        int[] samples = new int[100];
        for (int i = 0; i < 10000; i++) {
            int sample = p1.nextSample(1);
            samples[sample]++;
            assertTrue(0 <= sample);
        }
        assertFalse(0 == samples[0]);
        assertFalse(0 == samples[1]);
        samples = new int[100];
        for (int i = 0; i < 10000; i++) {
            int sample = p1.nextSample(1.5);
            samples[sample]++;
            assertTrue(0 <= sample);
        }
        for (int i = 0; i < 3; i++) {
            assertFalse(0 == samples[i]);
        }
        samples = new int[100];
        for (int i = 0; i < 10000; i++) {
            int sample = p1.nextSample(2.5);
            samples[sample]++;
            assertTrue(0 <= sample);
        }
        for (int i = 0; i < 5; i++) {
            assertFalse(0 == samples[i]);
        }
    }

    @Test
    public void fiftyMeanSampleTest() {
        Poisson p1 = new Poisson();
        int[] samples = new int[1000];
        long sum = 0L;
        for (int i = 0; i < 100000; i++) {
            int sample = p1.nextSample(50);
            samples[sample]++;
            sum += sample;
        }
        for (int i = 40; i < 61; i++) {
            assertFalse(0 == samples[i]);
        }
        for (int i = 0; i < 10; i++) {
            assertTrue(samples[10 - i] < samples[50 - i]);
            assertTrue(samples[100 + i] < samples[50 + i]);
        }
        assertEquals("May fail due to random sequence", 50.0, sum / 100000.0, 0.25);
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeMeanThrowsIllegalArgumentException() {
        Poisson p = new Poisson();
        p.nextSample(-2.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void zeroMeanThrowsIllegalArgumentException() {
        Poisson p = new Poisson();
        p.nextSample(0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void positiveInfinityMeanThrowsIllegalArgumentException() {
        Poisson p = new Poisson();
        p.nextSample(Double.POSITIVE_INFINITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeInfinityMeanThrowsIllegalArgumentException() {
        Poisson p = new Poisson();
        p.nextSample(Double.NEGATIVE_INFINITY);
    }
}
