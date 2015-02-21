/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.core;

import java.util.List;
import java.util.Random;
import trafficsimulator.utils.Point;
import trafficsimulator.utils.Size;


/**
 *
 * @author balazs
 */
public abstract class Vehicle {
  private Lane lane;
  private Point position;
  private double currentSpeed = 0;
  protected double topSpeed;
  protected double maxAcceleration;
  protected double maxDeceleration;
  protected double optimalDeceleration;
  protected Size size;
  protected String type = "Vehicle Base Object";
  
  public Vehicle(){
  }
  
  public Size getSize() {
    return size;
  }

  public double getTopSpeed() {
    return topSpeed;
  }
  
  public double getMaxAcceleration() {
    return maxAcceleration;
  }
  
  public double getMaxDeceleration() {
    return maxDeceleration;
  }
  
  public double getOptimalDeceleration() {
    return optimalDeceleration;  
  }
  
  public void SetOptimalDeceleration(int optimalDeceleration) {
    this.optimalDeceleration = optimalDeceleration;
  }

  public String getType(){
    return type;
  };
  
  public Point getPosition() {
    return position;
  }

  public Lane getLane() {
    return lane;
  }
  
  public boolean isInSystem(){
    return lane != null;
  }

  public void setLane(Lane lane) {
    if(lane == null){
      this.lane = null;
      return;
    }
    if(! isInSystem()){
      this.position = lane.getLeftStartPoint();
    }
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
    double stoppingDistance = getCurrentSpeed() / getOptimalDeceleration();
    return 30.0+stoppingDistance;
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
    Point endPoint = lane.getLeftEndPoint();
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
    Junction junction = lane.getJunction();
    if(junction == null) return null;
    List<Lane> lanes = junction.getConnectedLanes(lane);
    if(lanes.isEmpty()) return null;
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
      if(newLane!= null){
        this.lane.exit(this);
        this.position = newLane.getLeftStartPoint();
        this.setLane(newLane);
      }else{
        this.lane.exit(this);
        this.lane.getExitPoint().addVehicle(this);
        this.setLane(null);
      }
    }else{
      //Move vehicle
      position = newPosition;
    }
    
    System.out.println(" position: "+Math.round(position.getX())+", "+Math.round(position.getY())+" speed: "+Math.round(currentSpeed));
  }
}
