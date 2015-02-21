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
  //The road is initialised by specifying the left paramiters of the road.
  //Each lane will be added to the right these paramiters and the right
  //paramiters of the road will be calculated by the numbers of lanes on the road.
  private Point leftStartPoint;
  private Point leftEndPoint;
  
  public Road(Point leftStartPoint, Point leftEndPoint){
    lanes = new ArrayList<>();
    this.leftStartPoint = leftStartPoint;
    this.leftEndPoint = leftEndPoint;
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
  
  public Point getRandomPosition(){
    Point dir = leftEndPoint.minus(leftStartPoint);
    return leftStartPoint.plus(dir.mult(Math.random()));
  }
  
  public Point getDirectionVector(){
    return leftEndPoint.minus(leftStartPoint);
  }
  /*
  TODO: 1. Each lane should have different position within the road
        2. Create test for all the senarios, sem ég skrifaði í stílabókina um roads.
        3. Gera test fyrir óleglegar akreinar.
        
  */
  
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
  
  private Point rightPointsUnitDirectionVector() {
    Point dir = getDirectionVector();
    Point unitDir = dir.div(dir.distanceFromOrigin());
    //Vector rotated by 90 degrees clockvise
    Point rotateUnitDir = unitDir.rotateVector(Math.PI/2);
    return rotateUnitDir;
  }
  
  public Point getRightStartPoint() {
    Point rightStartPoint = leftStartPoint.plus(rightPointsUnitDirectionVector().mult(calculateWidth()));
    return rightStartPoint;
  }
  
    public Point getRightEndPoint() {
    Point rightEndPoint = leftEndPoint.plus(rightPointsUnitDirectionVector().mult(calculateWidth()));
    return rightEndPoint;
  }
}
