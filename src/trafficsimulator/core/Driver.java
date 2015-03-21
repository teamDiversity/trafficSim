/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.core;

import trafficsimulator.junctions.TrafficLight;
import trafficsimulator.junctions.TrafficLightJunction;

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
    distance = Math.max(distance, 0.0);
    double time = Math.sqrt((2*distance)/getOptimalDeceleration());
    return getOptimalDeceleration() * time;
  }

  public double getOptimalFollowingDistance() {
    double speed2 = vehicle.getCurrentSpeed()*vehicle.getCurrentSpeed();
    double stoppingDistance = speed2 / (getOptimalDeceleration()*2);
    return 10 + stoppingDistance;
  }

  private void changeSpeed() {
    double speedDelta = getOptimalAcceleration();
    
    // Change speed based on following distance
    double nextVehicleDist = vehicle.getLane().getDistanceFromVehicleInFront(vehicle);
    if (nextVehicleDist <= getOptimalFollowingDistance()) {
      double dist = nextVehicleDist - getOptimalFollowingDistance();
      double optimalSpeed = getOptimalSpeedForDistance(dist);
      double newSpeedDelta = optimalSpeed - vehicle.getCurrentSpeed();
      speedDelta = Math.min(speedDelta, newSpeedDelta);
    }
    
    //Change speed based on traffic lights
    Junction junction = vehicle.getLane().getJunction();
    if(junction instanceof TrafficLightJunction){
      TrafficLightJunction trafficLightJunction = (TrafficLightJunction)junction;
      TrafficLight light = trafficLightJunction.getTrafficLightForLane(vehicle.getLane());
      if(light != null){
        boolean greenLight = light.getState() == TrafficLight.State.GREEN;
        double dist = Math.max(vehicle.getDistanceFromEndOfLane() - 30, 0);
        
        //Vehicle is waiting for green light
        if(dist == 0 && !greenLight && vehicle.getCurrentSpeed()==0){
          speedDelta = Math.min(speedDelta, 0);
        }else{
          double opSpeed = getOptimalSpeedForDistance(dist);
          boolean shouldSlowDown = opSpeed < vehicle.getCurrentSpeed();
          boolean canStop = (vehicle.getCurrentSpeed() - vehicle.getMaxDeceleration()*dist) < 0;

          if(!greenLight && shouldSlowDown){
            double time = Math.sqrt((2*dist)/getOptimalDeceleration());
            double newSpeedDelta = - (vehicle.getCurrentSpeed() / time);
            speedDelta = Math.min(speedDelta, newSpeedDelta);
          }
        }
        
      }
    }
    
    vehicle.changeSpeed(speedDelta);
    
  }

  @Override
  public void step(long step) {
    // Change speed of vehicle
    changeSpeed();
  }

}
