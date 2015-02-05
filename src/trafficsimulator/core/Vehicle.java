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
public abstract class Vehicle {
  private Lane lane;
  private Point position;
  private double currentSpeed;
  protected double topSpeed;
  protected double acceleration;
  protected double maxDeceleration;
  protected double optimalDeceleration;
  
  
  public Vehicle(Lane lane, Point position){
    this.position = position;
    this.currentSpeed = 0;
    this.setLane(lane);
  }

  public double getTopSpeed() {
    return topSpeed;
  }
  
  public void setTopSpeed(double topSpeed) {
    this.topSpeed = topSpeed;
  }
  
  public double getAcceleration() {
    return acceleration;
  }
  
  public void setAcceleration(double acceleration) {
    this.acceleration = acceleration;
  }
  
  public double getMaxDeceleration() {
    return maxDeceleration;
  }
  
  public void setMaxDeceleration(double maxDeceleration) {
    this.maxDeceleration = maxDeceleration;
  }
  
  public double getOptimalDeceleration() {
    return optimalDeceleration;  
  }
  public void SetOptimalDeceleration(int optimalDeceleration) {
    this.optimalDeceleration = optimalDeceleration;
  }

  public abstract int getSize(); 
  public abstract String getType();
  
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
      if(speedDifference < getAcceleration()){
        setCurrentSpeed(getCurrentSpeed() + speedDifference);
      }else{
        setCurrentSpeed(getCurrentSpeed() + getAcceleration());
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
    System.out.print(getType() + " #"+hashCode());
    
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
