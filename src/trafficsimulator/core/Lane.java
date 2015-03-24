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
public class Lane {

  public static final double LANE_WIDTH = 25;

  public enum Direction {

    IDENTICAL, OPPOSITE
  }

  private Road road;
  private List<Vehicle> vehicles = new ArrayList<>();
  private Junction junction;
  private Direction direction;
  private Point startPoint;
  private Point endPoint;
  private ExitPoint exitPoint;

  public Lane(Direction direction, Point startPoint, Point endPoint) {
    this.direction = direction;
    this.startPoint = startPoint;
    this.endPoint = endPoint;
    exitPoint = new ExitPoint(this);
  }

  public Point getStartPoint() {
    return startPoint;
  }

  public Point getEndPoint() {
    return endPoint;
  }
  
  public double getLength(){
    return startPoint.distance(endPoint);
  }

  public void setStartPoint(Point startPoint) {
    this.startPoint = startPoint;
  }

  public void setEndPoint(Point endPoint) {
    this.endPoint = endPoint;
  }

  public void enter(Vehicle vehicle) {
    vehicles.add(vehicle);
  }

  public void exit(Vehicle vehicle) {
    vehicles.remove(vehicle);
  }

  public Junction getJunction() {
    return junction;
  }

  public void setJunction(Junction junction) {
    this.exitPoint = null;
    this.junction = junction;
  }

  public ExitPoint getExitPoint() {
    return exitPoint;
  }

  public Road getRoad() {
    return road;
  }

  public void setRoad(Road road) {
    this.road = road;
  }

  public Direction getDirection() {
    return direction;
  }

  public void setDirection(Direction direction) {
    this.direction = direction;
  }
  
  public Point getDirectionVector() {
    return getEndPoint().minus(getStartPoint());
  }
  
  private Vehicle getVehicleInFront(Vehicle vehicle){
    double minDistance = Double.MAX_VALUE;
    Vehicle vehicleInFront = null;

    for (Vehicle v : vehicles) {
      if (vehicle == v) {
        continue;
      }

      double distance = vehicle.getPosition().distance(v.getPosition());
      if (distance < minDistance) {
        Point dir = v.getPosition().minus(vehicle.getPosition());
        if (dir.inSameQuadrant(getDirectionVector())) {
          minDistance = distance;
          vehicleInFront = v;
        }
      }
    }

    return vehicleInFront;
  }

  public double getDistanceFromVehicleInFront(Vehicle vehicle) {
    Vehicle vehicleInFront = getVehicleInFront(vehicle);
    if(vehicleInFront == null) return Double.MAX_VALUE;
    double distance = vehicle.getPosition().distance(vehicleInFront.getPosition());
    distance -= vehicle.getSize().width;
    return distance;
  }
  
  public Vehicle getLastVehicle(){
    Vehicle vehicle = null;
    double minDistance = Double.MAX_VALUE;
    for(Vehicle v:vehicles){
      double distance = v.getPosition().distance(startPoint);
      if(distance < minDistance){
        minDistance = distance;
        vehicle = v;
      }
    }
    return vehicle;
  }
  
  public double getFreeSpace(){
    Vehicle lastVehicle = getLastVehicle();
    if(lastVehicle != null){
      return lastVehicle.getPosition().distance(startPoint) - lastVehicle.getSize().height;
    }else{
      return getLength();
    }
  }

}
