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
  private Point startPoint;
  private Point endPoint;
  
  public Road(Point startPoint, Point endPoint){
    lanes = new ArrayList<>();
    this.startPoint = startPoint;
    this.endPoint = endPoint;
  }

  public void addLane(Lane lane) {
    lanes.add(lane);
    lane.setRoad(this);
  }
  
  public List<Lane> getLanes(){
    return lanes;
  }

  public void setLanes(List<Lane> lanes) {
    this.lanes = lanes;
  }

  public Point getStartPoint() {
    return startPoint;
  }

  public void setStartPoint(Point startPoint) {
    this.startPoint = startPoint;
  }

  public Point getEndPoint() {
    return endPoint;
  }

  public void setEndPoint(Point endPoint) {
    this.endPoint = endPoint;
  }
  
  public Point getRandomPosition(){
    Point dir = endPoint.minus(startPoint);
    return startPoint.plus(dir.mult(Math.random()));
  }
  
  public Point getDirectionVector(){
    return endPoint.minus(startPoint);
  }
}
