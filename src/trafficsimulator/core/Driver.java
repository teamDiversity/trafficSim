/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.core;

/**
 *
 * @author Eddy
 */
public abstract class Driver implements ISteppable{

  protected String name;
  protected Vehicle vehicle;

  public Driver(String name){
    this.name = name;
  }
  
  public void setVehicle(Vehicle vehicle){
    this.vehicle = vehicle;
  }
  
  abstract public double getOptimalDeceleration();
  
  public double getOptimalSpeedForDistance(double distance) {
    double speed = getOptimalDeceleration() * distance;

    // Capping for max speed
    if (speed > vehicle.getTopSpeed()) {
      speed = vehicle.getTopSpeed();
    }

    return speed;
  }
  
  public double getOptimalFollowingDistance() {
    double stoppingDistance = vehicle.getCurrentSpeed() / getOptimalDeceleration();
    return 30.0 + stoppingDistance;
  }

  public boolean shouldAccelerate(double currentSpeed, double optimalFollowingDist, double distanceFromNextVechicle, double distanceFromEOLane) {
    boolean choice;
    //no car ahead
    if (distanceFromEOLane == Double.MAX_VALUE) {
      choice = true;
    }
    if (distanceFromNextVechicle <= optimalFollowingDist) {
      choice = false;
    } else {
      choice = true;
    }

    return choice;
  }

  public boolean shouldDecelerate(double currentSpeed, double optimalFollowingDist, double distanceFromNextVechicle, double distanceFromEOLane) {
    boolean choice;
    if (distanceFromEOLane == Double.MAX_VALUE) {
      //This will depend on the state of the traffic light
    }
    if (distanceFromNextVechicle <= optimalFollowingDist) {
      choice = true;
    } else {
      choice = false;
    }

    return choice;

  }
  
  private void changeSpeed() {
    boolean accelerate = shouldAccelerate(vehicle.getCurrentSpeed(), getOptimalFollowingDistance(), vehicle.getLane().getDistanceFromNextVehicle(vehicle), vehicle.getDistanceFromEndOfLane());
    boolean decelerate = shouldDecelerate(vehicle.getCurrentSpeed(), getOptimalFollowingDistance(), vehicle.getLane().getDistanceFromNextVehicle(vehicle), vehicle.getDistanceFromEndOfLane());

    if (accelerate) {
      vehicle.accelerate();
    } else if (decelerate) {
      vehicle.decelerate();
    }
  }

  @Override
  public void step(long step) {
    // Change speed of vehicle
    changeSpeed();
  }

}
