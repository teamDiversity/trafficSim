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
public abstract class Driver implements ISteppable {

  protected String name;
  protected Vehicle vehicle;

  public Driver(String name) {
    this.name = name;
  }

  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }

  abstract public double getOptimalDeceleration();
  abstract public double getOptimalAcceleration();

  private double getOptimalSpeedForDistance(double distance) {
    double speed = getOptimalDeceleration() * distance;
    return speed;
  }

  public double getOptimalFollowingDistance() {
    double stoppingDistance = vehicle.getCurrentSpeed() / getOptimalDeceleration();
    return 30.0 + stoppingDistance;
  }

  private void changeSpeed() {
    double speedDelta = getOptimalAcceleration();
    
    // Change speed based on following distance
    if (vehicle.getLane().getDistanceFromNextVehicle(vehicle) <= getOptimalFollowingDistance()) {
      double dist = vehicle.getLane().getDistanceFromNextVehicle(vehicle) - getOptimalFollowingDistance();
      double optimalSpeed = getOptimalSpeedForDistance(dist);
      speedDelta = Math.min(speedDelta, optimalSpeed - vehicle.getCurrentSpeed());
    }
    
    //Change speed based on traffic lights
    if (getOptimalSpeedForDistance(vehicle.getDistanceFromEndOfLane()) < vehicle.getCurrentSpeed()) {
      
    }
    
    vehicle.changeSpeed(speedDelta);
    
  }

  @Override
  public void step(long step) {
    // Change speed of vehicle
    changeSpeed();
  }

}
