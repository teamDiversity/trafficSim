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

  private boolean shouldAccelerate() {
    boolean choice;
    //no car ahead
    if (vehicle.getDistanceFromEndOfLane() == Double.MAX_VALUE) {
      choice = true;
    }
    if (vehicle.getLane().getDistanceFromNextVehicle(vehicle) <= getOptimalFollowingDistance()) {
      choice = false;
    } else {
      choice = true;
    }

    return choice;
  }

  private boolean shouldDecelerate() {
    boolean choice;
    if (vehicle.getDistanceFromEndOfLane() == Double.MAX_VALUE) {
      //This will depend on the state of the traffic light
    }
    if (vehicle.getLane().getDistanceFromNextVehicle(vehicle) <= getOptimalFollowingDistance()) {
      choice = true;
    } else {
      choice = false;
    }

    return choice;

  }
  
  private void changeSpeed() {
    if (shouldAccelerate()) {
      vehicle.accelerate();
    } else if (shouldDecelerate()) {
      vehicle.decelerate();
    }
  }

  @Override
  public void step(long step) {
    // Change speed of vehicle
    changeSpeed();
  }

}
