/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.core;

import java.util.ArrayList;
import java.util.List;
import trafficsimulator.utils.Point;

/**
 *
 * @author balazs
 */
public class Road {

  private List<Lane> lanes;
  
  private Point leftStartPoint;
  private Point leftEndPoint;

  public Road(Point leftStartPoint, Point leftEndPoint) {
    lanes = new ArrayList<>();
    this.leftStartPoint = leftStartPoint;
    this.leftEndPoint = leftEndPoint;
  }

  public Lane addLane(Lane.Direction direction) {
    double offsetX = Lane.laneWidth/2 * Math.cos(acrossRoadUnitVector().angleVector());
    double offsetY = Lane.laneWidth/2 * Math.cos(acrossRoadUnitVector().angleVector());
    Point startPoint = leftStartPoint.plus(new Point(offsetX, offsetY));
    Point endPoint = leftEndPoint.plus(new Point(offsetX, offsetY));
    Lane lane = new Lane(direction, startPoint, endPoint);
    lanes.add(lane);
    lane.setRoad(this);
    return lane;
  }

  public List<Lane> getLanes() {
    return lanes;
  }

  public Point getLeftStartPoint() {
    return leftStartPoint;
  }

  public void setLeftStartPoint(Point leftStartPoint) {
    this.leftStartPoint = leftStartPoint;
  }

  public Point getLeftEndPoint() {
    return leftEndPoint;
  }

  public void setLeftEndPoint(Point leftEndPoint) {
    this.leftEndPoint = leftEndPoint;
  }

  public Point getRandomPosition() {
    Point dir = leftEndPoint.minus(leftStartPoint);
    return leftStartPoint.plus(dir.mult(Math.random()));
  }

  public Point getDirectionVector() {
    return leftEndPoint.minus(leftStartPoint);
  }

  public int getLaneIndexPosition(Lane l) {
    return lanes.indexOf(l);
  }

  public double calculateWidth() {
    double width = 0;
    for (Lane l : lanes) {
      width += Lane.laneWidth;
    }
    return width;
  }

  private Point acrossRoadUnitVector() {
    Point dir = getDirectionVector();
    Point unitDir = dir.div(dir.distanceFromOrigin());
    Point rotateUnitDir = unitDir.rotateVector(Math.PI / 2);
    return rotateUnitDir;
  }

  private Point acrossRoadVector() {
    double x = Math.round(calculateWidth() * Math.cos(acrossRoadUnitVector().angleVector()));
    double y = Math.round(calculateWidth() * Math.sin(acrossRoadUnitVector().angleVector()));
    return new Point(x, y);
  }

  public Point getRightStartPoint() {
    Point rightStartPoint = leftStartPoint.plus(acrossRoadVector());
    return rightStartPoint;
  }

  public Point getRightEndPoint() {
    Point rightEndPoint = leftEndPoint.plus(acrossRoadVector());
    return rightEndPoint;
  }
}
