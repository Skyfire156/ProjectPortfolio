import java.util.Map;
import java.awt.Color;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;
/**
 * Displays x-y chart of an arrival function.
 *
 * ToDo: Change X-axis labels to show clock time instead of minutes
 *
 * Requires jcommon-1.0.23.jar and jfreechart-1.0.19.jar.
 * @see <a href="http://www.jfree.org/jfreechart/api/javadoc/">
 *       http://www.jfree.org/jfreechart/api/javadoc/</a>
 *
 * @author Dr. Jody Paul
 * @version 1.1 ($Id:PassengerArrivalChart.java 200 2016-04-18 03:25:07Z sean $)
 */
public class PassengerArrivalChart extends ApplicationFrame {

    /** Number of arrivals per station per day. */
    private static final int ARRIVALS = 4000;

    /** Serial version ID. */
    private static final long serialVersionUID = 2016032701L;

    /** Chart window width. */
    private static final int WIDTH = 600;

    /** Chart window height. */
    private static final int HEIGHT = 400;

    /** Amount of inset for graph area. */
    private static final double INSET = 5.0;

    /** Title of the chart. */
    private static final String CHART_TITLE = "Passenger Arrival Function";

    /** Label for the x - axis. */
    private static final String CHART_XAXIS_LABEL = "Arrival Time ("
                                                     + "minutes since 00:00)";
    /** Label for the y - axis. */
    private static final String CHART_YAXIS_LABEL = "Number of Passengers";
    /**
     * Constructs a chart to display probability distributions.
     * @param title the frame title
     */
    public PassengerArrivalChart(final String title) {
        super(title);
        ChartPanel chartPanel = (ChartPanel) createDisplayPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(WIDTH, HEIGHT));
        setContentPane(chartPanel);
    }

    /**
     * Creates the chart.
     * @param dataset  a dataset.
     * @return the chart
     */
    private static JFreeChart createChart(final XYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(
            CHART_TITLE, CHART_XAXIS_LABEL, CHART_YAXIS_LABEL,
            dataset, PlotOrientation.VERTICAL,
            true,      // create legend
            true,      // generate tooltips
            false      // do not generate URLs
        );

        chart.setBackgroundPaint(Color.WHITE);

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.LIGHT_GRAY);
        plot.setDomainGridlinePaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.WHITE);
        plot.setAxisOffset(new RectangleInsets(INSET, INSET, INSET, INSET));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setBaseShapesVisible(true);
            renderer.setBaseShapesFilled(true);
            renderer.setDrawSeriesLineAsPath(true);
        }

        return chart;
    }


    /**
     * Creates the dataset with samples from
     * probability distributions.
     *
     * @return the dataset
     */
    private static XYDataset createDataset() {
        PassengerArrivalFunction paf = new PassengerArrivalFunction(ARRIVALS);
        int[] samples = paf.passengerArrivals();
        XYSeries s1 = new XYSeries("Passenger Arrivals (Composite)");
        int sum1 = 0;
        for (int i = 0; i < samples.length; i++) {
            if (samples[i] != 0) {
                s1.add(i, samples[i]);
                sum1 += samples[i];
            }
        }

        Map<PassengerArrivalFunction.TimePeriod, int[]> periodArrivals
        = paf.composingValues();

        int[] offPeakSamples
        = periodArrivals.get(PassengerArrivalFunction.TimePeriod.OFF_PEAK);

        int[] morningPeakSamples
        = periodArrivals.get(PassengerArrivalFunction.TimePeriod.MORNING_PEAK);

        int[] eveningPeakSamples
        = periodArrivals.get(PassengerArrivalFunction.TimePeriod.EVENING_PEAK);

        XYSeries s2 = new XYSeries("Off-Peak Arrivals");
        XYSeries s3 = new XYSeries("Morning-Peak Arrivals");
        XYSeries s4 = new XYSeries("Evening-Peak Arrivals");

        int sum2 = 0;
        int sum3 = 0;
        int sum4 = 0;
        for (int i = 0; i < offPeakSamples.length; i++) {
                s2.add(i, offPeakSamples[i]);
                s3.add(i, morningPeakSamples[i]);
                s4.add(i, eveningPeakSamples[i]);
                sum2 += offPeakSamples[i];
                sum3 += morningPeakSamples[i];
                sum4 += eveningPeakSamples[i];
        }

        System.out.println("Expected Arrivals: " + ARRIVALS);
        System.out.println("  Actual Arrivals: " + sum1);
        System.out.println("     Uniform Arrivals: " + sum2);
        System.out.println("Morning Peak Arrivals: " + sum3);
        System.out.println("Evening Peak Arrivals: " + sum4);

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(s4);
        dataset.addSeries(s3);
        dataset.addSeries(s2);
        dataset.addSeries(s1);
        return dataset;
    }

    /**
     * Creates a display panel for the chart.
     * @return a panel
     */
    public static JPanel createDisplayPanel() {
        JFreeChart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setFillZoomRectangle(true);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    /**
     * Collect and plot data from probability distributions.
     * @param args ignored
     */
    public static void main(final String[] args) {
        PassengerArrivalChart chart
            = new PassengerArrivalChart(
                    "Probability Distribution Comparison");
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }
}
