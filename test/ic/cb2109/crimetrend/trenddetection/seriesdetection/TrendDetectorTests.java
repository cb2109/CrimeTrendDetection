package ic.cb2109.crimetrend.trenddetection.seriesdetection;

import ic.cb2109.crimetrend.trenddetection.DoubleSeries;
import ic.cb2109.crimetrend.trenddetection.trends.DownwardTrend;
import ic.cb2109.crimetrend.trenddetection.trends.NoTrend;
import ic.cb2109.crimetrend.trenddetection.trends.Trend;
import ic.cb2109.crimetrend.trenddetection.trends.UpwardTrend;
import junit.framework.Assert;
import org.testng.annotations.Test;

import java.util.Random;

/**
 * Author: Christopher Bates
 * Date: 28/10/13
 */
@Test
public class TrendDetectorTests {

    public void noDataTest() {

        DoubleSeries series = new DoubleSeries();

        TrendDetector detector = new TrendDetector<DoubleSeries.DoubleSeriesElement>(series);

        Trend t = detector.findTrend();

        Assert.assertEquals("There was a returned trend even though there was no data", t.getClass(), NoTrend.class);

    }

    public void smallSampleTest() {

        DoubleSeries series = new DoubleSeries();
        series.addElement(3, 9);
        series.addElement(2, 6);
        series.addElement(1, 3);

        TrendDetector detector = new TrendDetector<DoubleSeries.DoubleSeriesElement>(series);

        Trend t = detector.findTrend();

        Assert.assertEquals("The trend returned was not an upward trend as expected. Trend message: " + t,
                t.getClass(), UpwardTrend.class);

    }

    public void complexSampleTest() {

        DoubleSeries series = new DoubleSeries();
        series.addElement(1, 9);
        series.addElement(2, 3);
        series.addElement(3, 6);

        TrendDetector detector = new TrendDetector<DoubleSeries.DoubleSeriesElement>(series);

        Trend t = detector.findTrend();

        Assert.assertEquals("The trend returned was not an downward trend as expected. Trend message: " + t,
                t.getClass(), DownwardTrend.class);
    }

    public void largeDataSetTest() {

        DoubleSeries series = new DoubleSeries();
        Random r = new Random();
        // should generate a semi randomly distributed number that hovers about 50 units from i each time, so an upward trend is generated
        for(int i = 0; i < 1000; i++) {
            double d = r.nextDouble() * 100 + i - 50;
            series.addElement(i, d);
        }

        TrendDetector detector = new TrendDetector<DoubleSeries.DoubleSeriesElement>(series);

        Trend t = detector.findTrend();

        Assert.assertEquals("The trend returned was not an upward trend as expected. Trend message: " + t,
                t.getClass(), UpwardTrend.class);

    }


}
