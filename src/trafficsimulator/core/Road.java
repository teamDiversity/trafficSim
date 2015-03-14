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
  private Point StartPoint;
  private Point EndPoint;

  public Road(Point StartPoint, Point EndPoint) {
    lanes = new ArrayList<>();
    this.StartPoint = StartPoint;
    this.EndPoint = EndPoint;
  }

  public void addLane(Lane lane) {
    lanes.add(lane);
    lane.setRoad(this);
  }

  public List<Lane> getLanes() {
    return lanes;
  }

  public void setLanes(List<Lane> lanes) {
    this.lanes = lanes;
  }

  public Point getStartPoint() {
    return StartPoint;
  }

  public void setStartPoint(Point StartPoint) {
    this.StartPoint = StartPoint;
  }

  public Point getEndPoint() {
    return EndPoint;
  }

  public void setEndPoint(Point EndPoint) {
    this.EndPoint = EndPoint;
  }

  public Point getRandomPosition() {
    Point dir = EndPoint.minus(StartPoint);
    return StartPoint.plus(dir.mult(Math.random()));
  }

  public Point getDirectionVector() {
    return EndPoint.minus(StartPoint);
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
    Point rightStartPoint = StartPoint.plus(acrossRoadVector().div(2));
    return rightStartPoint;
  }

  public Point getRightEndPoint() {
    Point rightEndPoint = EndPoint.plus(acrossRoadVector().div(2));
    return rightEndPoint;
  }
  
  public Point getLeftStartPoint() {
    Point rightStartPoint = StartPoint.minus(acrossRoadVector().div(2));
    return rightStartPoint;
  }

  public Point getLeftEndPoint() {
    Point rightEndPoint = EndPoint.minus(acrossRoadVector().div(2));
    return rightEndPoint;
  }
  
  
}
