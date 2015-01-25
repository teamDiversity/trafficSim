/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.core;

import java.util.List;
import java.util.Random;
import trafficsimulator.utils.Point;


/**
 *
 * @author balazs
 */
public class Vehicle {
  private Lane lane;
  private Point position;
  private int speed;
  
  public Vehicle(Lane lane, Point position){
    this.position = position;
    this.speed = 2;
    this.setLane(lane);
  }
  
  public Point getPosition() {
    return position;
  }

  public void setPosition(Point position) {
    this.position = position;
  }

  public Lane getLane() {
    return lane;
  }

  public void setLane(Lane lane) {
    this.lane = lane;
    this.lane.enter(this);
  }

  public int getSpeed() {
    return speed;
  }

  public void setSpeed(int speed) {
    this.speed = speed;
  }
  
  
  
  private boolean leftRoad(Point oldPosition, Point newPosition){
    Point endPoint = lane.getEndPoint();
    if(oldPosition.getX() <= endPoint.getX() && newPosition.getX() > endPoint.getX()){
      return true;
    }
    if(oldPosition.getX() >= endPoint.getX() && newPosition.getX() < endPoint.getX()){
      return true;
    }
    if(oldPosition.getY() <= endPoint.getY() && newPosition.getY() > endPoint.getY()){
      return true;
    }
    if(oldPosition.getY() >= endPoint.getY() && newPosition.getY() < endPoint.getY()){
      return true;
    }
    return false;
  }
  
  private Lane chooseRandomNewLane(){
    List<Lane> lanes = lane.getJunction().getConnectedLanes(lane);
    Random randomGenerator = new Random();
    int index = randomGenerator.nextInt(lanes.size());
    return lanes.get(index);
  }
  
  public void step(){
    Point dir = getLane().getDirectionVector();
    Point newPosition = position.plus(dir.div(dir.distanceFromOrigin()).mult(speed));
    if(leftRoad(this.position, newPosition)){
      
      Lane newLane = chooseRandomNewLane();
      this.lane.exit(this);
      this.position = newLane.getStartPoint();
      this.setLane(newLane);
      
    }else{
      position = newPosition;
    }
    System.out.println("Vehicle #"+hashCode()+" position: "+position.getX()+", "+position.getY());
  }


  
  
}
