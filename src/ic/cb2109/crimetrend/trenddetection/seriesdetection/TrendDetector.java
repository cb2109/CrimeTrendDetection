package ic.cb2109.crimetrend.trenddetection.seriesdetection;

import ic.cb2109.crimetrend.trenddetection.Series;
import ic.cb2109.crimetrend.trenddetection.trends.DownwardTrend;
import ic.cb2109.crimetrend.trenddetection.trends.NoTrend;
import ic.cb2109.crimetrend.trenddetection.trends.Trend;
import ic.cb2109.crimetrend.trenddetection.trends.UpwardTrend;
import org.apache.commons.math.stat.regression.SimpleRegression;

import java.awt.geom.Point2D;

/**
 * Author: Christopher Bates
 * Date: 28/10/13
 */
public class TrendDetector<O extends Point2D> {

    private Series<O> series;

    public TrendDetector(Series<O> series) {

        this.series = series;

    }


    public Trend<Double> findTrend() {

        if(series == null || series.isEmpty()) {
            return new NoTrend<Double>();
        }

        SimpleRegression regressionChecker = new SimpleRegression();
        for(O data : series) {
            regressionChecker.addData(data.getX(), data.getY());
        }

        double ans = regressionChecker.getSlope();
        if(ans > 0) {
            return new UpwardTrend<Double>();
        } else if (ans < 0) {
            return new DownwardTrend<Double>();
        } else {
            return new NoTrend<Double>();
        }

    }

}
