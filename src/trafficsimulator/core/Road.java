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
    double offsetX = (lanes.size() * Lane.LANE_WIDTH + Lane.LANE_WIDTH/2) * Math.cos(acrossRoadUnitVector().angleVector());
    double offsetY = (lanes.size() * Lane.LANE_WIDTH + Lane.LANE_WIDTH/2) * Math.sin(acrossRoadUnitVector().angleVector());
    double offsetX_2 = (lanes.size() * Lane.LANE_WIDTH + Lane.LANE_WIDTH) * Math.cos(acrossRoadUnitVector().angleVector());
    double offsetY_2 = (lanes.size() * Lane.LANE_WIDTH + Lane.LANE_WIDTH) * Math.sin(acrossRoadUnitVector().angleVector());
    Point startPoint;
    Point endPoint;
    Point lanePointStart;
    Point lanePointStop;
    if(direction == Lane.Direction.IDENTICAL){
      startPoint = leftStartPoint.plus(new Point(offsetX, offsetY));
      endPoint = leftEndPoint.plus(new Point(offsetX, offsetY));
      lanePointStart = leftStartPoint.plus(new Point(offsetX_2, offsetY_2));
      lanePointStop = leftEndPoint.plus(new Point(offsetX_2, offsetY_2));
    }else{
      startPoint = leftEndPoint.plus(new Point(offsetX, offsetY));
      endPoint = leftStartPoint.plus(new Point(offsetX, offsetY));
      lanePointStart = leftEndPoint.plus(new Point(offsetX_2, offsetY_2));
      lanePointStop = leftStartPoint.plus(new Point(offsetX_2, offsetY_2));
    }
    Lane lane = new Lane(direction, startPoint, endPoint, lanePointStart, lanePointStop);
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
    return lanes.size()*Lane.LANE_WIDTH;
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
