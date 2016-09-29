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
 * Displays x-y chart of probability distributions.
 *
 * Requires jcommon-1.0.23.jar and jfreechart-1.0.19.jar.
 * @see <a href="http://www.jfree.org/jfreechart/api/javadoc/">
 *       http://www.jfree.org/jfreechart/api/javadoc/</a>
 * @author Dr. Jody Paul
 * @version 1.2
 * ($Id: ProbabilityDistributionChart.java 346 2016-05-01 18:34:39Z joshua $)
 */
public class ProbabilityDistributionChart extends ApplicationFrame {
    /** Serial version ID. */
    private static final long serialVersionUID = 2016032401L;

    /** Chart window width. */
    private static final int WIDTH = 500;

    /** Chart window height. */
    private static final int HEIGHT = 350;

    /** Amount of inset for graph area. */
    private static final double INSET = 5.0;

    /** Mean for distributions. */
    public static final int MEAN_OF_DISTRIBUTION = 100;

    /** Value large enough to accommodate sample space. */
    public static final int SAMPLE_SPACE_SIZE = 1000;

    /** Number of samples per distribution. */
    public static final int NUMBER_OF_SAMPLES = 100000;

    /**
     * Constructs a chart to display probability distributions.
     * @param title  the frame title.
     */
    public ProbabilityDistributionChart(final String title) {
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
            "Probability Distribution Sampling",  // title
            "Domain (mean = " + MEAN_OF_DISTRIBUTION + ")",  // x-axis label
            "Range (" + NUMBER_OF_SAMPLES + " samples)",     // y-axis label
            dataset,   // data
            PlotOrientation.VERTICAL, // orientation
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
        Poisson p1 = new Poisson();
        int[] samples1 = new int[SAMPLE_SPACE_SIZE + 1];
        for (int i = 0; i < NUMBER_OF_SAMPLES; i++) {
            int sample = p1.nextSample(MEAN_OF_DISTRIBUTION);
            samples1[sample]++;
        }
        XYSeries s1 = new XYSeries("Poisson Distribution");
        for (int i = 0; i < samples1.length; i++) {
            if (samples1[i] != 0) {
                s1.add(i, samples1[i]);
            }
        }

        Uniform u1 = new Uniform();
        int[] samples2 = new int[SAMPLE_SPACE_SIZE + 1];
        for (int i = 0; i < NUMBER_OF_SAMPLES; i++) {
            int sample = u1.nextSample(MEAN_OF_DISTRIBUTION);
            samples2[sample]++;
        }
        XYSeries s2 = new XYSeries("Uniform Distribution");
        for (int i = 0; i < samples2.length; i++) {
            if (samples2[i] != 0) {
                s2.add(i, samples2[i]);
            }
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(s1);
        dataset.addSeries(s2);
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
        ProbabilityDistributionChart chart
            = new ProbabilityDistributionChart(
                    "Probability Distribution Comparison");
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }
}
