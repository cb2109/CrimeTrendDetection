package ic.cb2109.crimetrend.trenddetection;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Author: Christopher Bates
 * Date: 28/10/13
 */
public class DoubleSeries implements Series<DoubleSeries.DoubleSeriesElement>{

    private List<DoubleSeriesElement> series;

    public DoubleSeries() {
        this.series = new ArrayList<DoubleSeriesElement>();
    }

    @Override
    public boolean isEmpty() {
        return series.isEmpty();
    }

    public void addElement(double x, double y) {
        series.add(new DoubleSeriesElement(x, y));
    }

    @Override
    public Iterator<DoubleSeriesElement> iterator() {
        return series.iterator();
    }

    public class DoubleSeriesElement extends Point2D.Double {

        protected DoubleSeriesElement(double x, double y) {
            super(x, y);
        }

    }

}
