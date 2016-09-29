/**
 * Light rail model initialization and
 * simulation activation.
 * Depends on Simulation.toString() for display
 * of model state.
 *
 * @author Dr. Jody Paul
 * @version $Revision: 361 $
 * ($Id: LightRailSimulationDriver.java 361 2016-05-01 21:49:57Z joshua $)
 */
public final class LightRailSimulationDriver {

   /** Hidden constructor for utility class. */
    private LightRailSimulationDriver() { }

   /**
     * Initializes model parameters and activates simulation.
     * @param args To Be Defined
     */
    public static void main(final String[] args) {
        // Show product name.
        System.out.println("+==============================+\n"
                           + "| Light Rail Simulation Driver |\n"
                           + "+==============================+\n");

        // Create simulation model.
        Simulation sim = new Simulation();

        // Establish route.
        Route rte = new Route(new Station("STN#1"));
        rte.addStation(new Station("STN#2"), new Time(0, 10));
        rte.addStation(new Station("STN#3"), new Time(0, 12));
        rte.addStation(new Station("STN#4"), new Time(0, 8));
        rte.addStation(new Station("STN#5"), new Time(0, 7));

        // Create schedule.
        Schedule sched = new Schedule();
        Time firstTime = new Time(0, 0);
        Time lastTime = new Time(23, 59);
        Time interval = new Time(0, 20);
        Time schedTime = new Time(firstTime);
        boolean scheduleComplete = false;
        while (schedTime.compareTo(lastTime) < 0  &&  !scheduleComplete) {
            sched.addDepartureTime(schedTime);
            Time prevTime = new Time(schedTime);
            schedTime.increment(interval);
            if (schedTime.compareTo(prevTime) <= 0) {
                scheduleComplete = true;
            }
        }

        // Initialize model.
        Time minWaitTime = new Time(0, 2);
        Integer maxPassengers = 110;
        BoardingFunction boardingFunc
                = new LinearBoarding(10, new Time(0, 0, 14));
        PassengerArrivalFunction arrivalFunc = new PassengerArrivalFunction();
        sim.initializeModel(rte,
                            sched,
                            minWaitTime,
                            maxPassengers,
                            boardingFunc,
                            arrivalFunc);

        // Show initial state.
        System.out.println("[Initial] " + sim);

        // Step through simulation.
        Time step = new Time(0, 1, 0);
        for (int i = 0; i < 1439; i++) {
            sim.advanceSimulation(step);
        }

        // Show final state.
        System.out.println("\n[Final] " + sim);
    }

  }

