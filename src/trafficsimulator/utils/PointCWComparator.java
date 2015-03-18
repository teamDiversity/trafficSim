/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.utils;

import java.util.Comparator;

/**
 *
 * @author balazs
 */
public class PointCWComparator implements Comparator<Point> {

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
