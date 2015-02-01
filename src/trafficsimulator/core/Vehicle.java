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
  private double currentSpeed = 0;
  private int topSpeed = 5;
  private int maxAcceleration = 1;
  private int maxDeceleration = 3;
  private int optimalDeceleration = 2;
  
  public Vehicle(Lane lane, Point position){
    this.position = position;
    this.currentSpeed = 0;
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

  public double getCurrentSpeed() {
    return currentSpeed;
  }
  
  private void setCurrentSpeed(double speed){
    if(speed > getTopSpeed()){
      currentSpeed = getTopSpeed();
    }else if(speed < 0){
      currentSpeed = 0;
    }else{
      currentSpeed = speed;
    }
  }

  public int getTopSpeed() {
    return topSpeed;
  }

  public void setTopSpeed(int topSpeed) {
    this.topSpeed = topSpeed;
  }

  public int getMaxAcceleration() {
    return maxAcceleration;
  }

  public void setMaxAcceleration(int maxAcceleration) {
    this.maxAcceleration = maxAcceleration;
  }

  public int getMaxDeceleration() {
    return maxDeceleration;
  }

  public void setMaxDeceleration(int maxDeceleration) {
    this.maxDeceleration = maxDeceleration;
  }

  public int getOptimalDeceleration() {
    return optimalDeceleration;
  }

  public void setOptimalDeceleration(int optimalDeceleration) {
    this.optimalDeceleration = optimalDeceleration;
  }
  
  private double getOptimalSpeedForDistance(double distance){
    double speed = getOptimalDeceleration() * distance;
    
    // Capping for max speed
    if(speed > getTopSpeed()){
      speed = getTopSpeed();
    }
    
    return speed;
  }
  
  private double getOptimalFollowingDistance(){
    return 30.0;
  }
  
  private void changeSpeed(){
    double dist = getLane().getDistanceFromNextVehicle(this) - getOptimalFollowingDistance();
    double optimalSpeed = getOptimalSpeedForDistance(dist);
    
    if(optimalSpeed > getCurrentSpeed()){
      double speedDifference = optimalSpeed - getCurrentSpeed();
      if(speedDifference < getMaxAcceleration()){
        setCurrentSpeed(getCurrentSpeed() + speedDifference);
      }else{
        setCurrentSpeed(getCurrentSpeed() + getMaxAcceleration());
      }
    }else if(optimalSpeed < getCurrentSpeed()){
      double speedDifference = getCurrentSpeed() - optimalSpeed;
      if(speedDifference < getMaxDeceleration()){
        setCurrentSpeed(getCurrentSpeed() - speedDifference);
      }else{
        setCurrentSpeed(getCurrentSpeed() - getMaxDeceleration());
      }
    }
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
    System.out.print("Vehicle #"+hashCode());
    
    // Change speed of vehicle
    changeSpeed();
    
    // Calculate new position
    Point dir = getLane().getDirectionVector();
    Point newPosition = position.plus(dir.div(dir.distanceFromOrigin()).mult(getCurrentSpeed()));
    
    // Check if vehicle has to change lane
    if(leftRoad(this.position, newPosition)){
      // Move vehicle to random next lane
      Lane newLane = chooseRandomNewLane();
      this.lane.exit(this);
      this.position = newLane.getStartPoint();
      this.setLane(newLane);
    }else{
      //Move vehicle
      position = newPosition;
    }
    
    System.out.println(" position: "+Math.round(position.getX())+", "+Math.round(position.getY())+" speed: "+Math.round(currentSpeed));
  }


  
  
}
