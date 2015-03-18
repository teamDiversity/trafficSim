/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import trafficsimulator.core.Junction;
import trafficsimulator.core.Lane;
import trafficsimulator.core.Road;
import trafficsimulator.utils.Point;

/**
 *
 * @author balazs
 */
public class JunctionRenderer implements IRenderer {

  private Junction junction;
  private GraphicsContext gc;

  public JunctionRenderer(GraphicsContext gc, Junction junction) {
    this.gc = gc;
    this.junction = junction;
  }

  private class PointCWComparator implements Comparator<Point> {

    private final Point center;

    public PointCWComparator(Point center) {
      this.center = center;
    }

    private boolean less(Point a, Point b) {
      if (a.x - center.x >= 0 && b.x - center.x < 0) {
        return true;
      }
      if (a.x - center.x < 0 && b.x - center.x >= 0) {
        return false;
      }
      if (a.x - center.x == 0 && b.x - center.x == 0) {
        if (a.y - center.y >= 0 || b.y - center.y >= 0) {
          return a.y > b.y;
        }
        return b.y > a.y;
      }

      // compute the cross product of vectors (center -> a) x (center -> b)
      double det = (a.x - center.x) * (b.y - center.y) - (b.x - center.x) * (a.y - center.y);
      if (det < 0) {
        return true;
      }
      if (det > 0) {
        return false;
      }

    // points a and b are on the same line from the center
      // check which point is closer to the center
      double d1 = (a.x - center.x) * (a.x - center.x) + (a.y - center.y) * (a.y - center.y);
      double d2 = (b.x - center.x) * (b.x - center.x) + (b.y - center.y) * (b.y - center.y);
      return d1 > d2;
    }

    @Override
    public int compare(Point o1, Point o2) {
      if (less(o1, o2)) {
        return -1;
      }
      return 1;
    }
  }

  private class RoadCWComparator implements Comparator<Road> {

    private final Junction junction;

    public RoadCWComparator(Junction junction) {
      this.junction = junction;
    }

    @Override
    public int compare(Road o1, Road o2) {
      List<Point> cPoints1 = junction.getPointsForRoad(o1);
      List<Point> cPoints2 = junction.getPointsForRoad(o2);

      Point cPoint1 = Point.centroid(cPoints1);
      Point cPoint2 = Point.centroid(cPoints2);

      PointCWComparator comparator = new PointCWComparator(junction.getCenterPoint());
      return comparator.compare(cPoint1, cPoint2);
    }

  }

  @Override
  public void render() {
    List<Road> roads = junction.getRoads();
    Collections.sort(roads, new RoadCWComparator(junction));

    List<Point> points = new ArrayList<Point>();
    for (Road road : roads) {
      points.add(junction.getPointsForRoad(road).get(0));
      points.add(junction.getPointsForRoad(road).get(1));
    }

    double[] xPoints = new double[points.size()];
    double[] yPoints = new double[points.size()];
    for (int i = 0; i < points.size(); i++) {
      xPoints[i] = points.get(i).getX();
      yPoints[i] = points.get(i).getY();
    }
    gc.fillPolygon(xPoints, yPoints, points.size());

    for (Lane lane : junction.getLanes()) {
      gc.setLineWidth(1);
      if (lane.getDirection() == Lane.Direction.IDENTICAL) {
        gc.setStroke(Color.RED);
      } else {
        gc.setStroke(Color.YELLOW);
      }
      gc.strokeLine(lane.getStartPoint().x, lane.getStartPoint().y, lane.getEndPoint().x, lane.getEndPoint().y);
    }
  }

}
