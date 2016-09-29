import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
/**
 * Tests of the uniform distribution.
 *
 * @author  Dr. Jody Paul
 * @version 1.1 ($Id: UniformTest.java 342 2016-05-01 17:55:12Z joshua $)
 */
public class UniformTest {

    /** Uniform distribution object. */
    private Uniform u1;

    /** Distribution samples. */
    private int[] samples;

    @Before
    public void setUp() {
        u1 = new Uniform();
    }

    @Test
    public void oneMeanSampleTest() {
        samples = new int[3];
        for (int i = 0; i < 10000; i++) {
            int sample = u1.nextSample(1);
            samples[sample]++;
            assertTrue((0 == sample) || (1 == sample) || (2 == sample));
        }
        assertFalse(0 == samples[0]);
        assertFalse(0 == samples[1]);
        assertFalse(0 == samples[2]);
    }

    @Test
    public void onePointFiveMeanSampleTest() {
        samples = new int[4];
        for (int i = 0; i < 10000; i++) {
            int sample = u1.nextSample(1.5);
            samples[sample]++;
            assertTrue((0 <= sample) && (sample <= 3));
        }
        for (int i = 0; i < samples.length; i++) {
            assertFalse(0 == samples[i]);
        }
    }

    @Test
    public void twoPointFiveMeanSampleTest() {
        samples = new int[6];
        for (int i = 0; i < 10000; i++) {
            int sample = u1.nextSample(2.5);
            samples[sample]++;
            assertTrue((0 <= sample) && (sample <= 5));
        }
        for (int i = 0; i < samples.length; i++) {
            assertFalse(0 == samples[i]);
        }
    }

    @Test
    public void fiftyMeanSampleTest() {
        samples = new int[101];
        long sum = 0L;
        for (int i = 0; i < 100000; i++) {
            int sample = u1.nextSample(50);
            samples[sample]++;
            assertTrue(0 <= sample);
            sum += sample;
        }
        assertEquals("May fail due to random sequence",
                     50.0, sum / 100000.0, 0.25);
    }
}
