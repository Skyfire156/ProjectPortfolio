import org.junit.Test;
/**
 * Invokes the main method of LightRailSimulationDriver
 * with defaulted parameters.
 * Useful for determining which lines of the driver
 * are actually executed (see cobertura results).
 *
 * @author  Dr. Jody Paul
 * @version $Revision: 349 $ ($Id: LightRailSimulationDriverTest.java 349 2016-05-01 18:46:56Z joshua $)
 */
public class LightRailSimulationDriverTest {
    /** Invokes LightRailSimulationDriver.main. */
    @Test
    public void driverTest() {
        String[] parameters = new String[0];
        LightRailSimulationDriver.main(parameters);
    }
}
