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
  
  public enum Direction {
    IDENTICAL, OPPOSITE
  }
  
  private Road road;
  private List<Vehicle> vehicles = new ArrayList<>();
  private Junction junction;
  private Direction direction;
  private ExitPoint exitPoint;
  
  public Lane(Direction direction){
    this.direction = direction;
    exitPoint = new ExitPoint(this);
  }
  
  public void enter(Vehicle vehicle){
    vehicles.add(vehicle);
  }
  
  public void exit(Vehicle vehicle){
    vehicles.remove(vehicle);
  }

  public Junction getJunction() {
    return junction;
  }

  public void setJunction(Junction junction) {
    this.exitPoint = null;
    this.junction = junction;
  }
  
  public ExitPoint getExitPoint(){
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
  
  public Point getStartPoint(){
    Road road = getRoad();
    if(getDirection() == Direction.IDENTICAL){
      return road.getStartPoint();
    }else{
      return road.getEndPoint();
    }
  }
  
  public Point getEndPoint(){
    Road road = getRoad();
    if(getDirection() == Direction.IDENTICAL){
      return road.getEndPoint();
    }else{
      return road.getStartPoint();
    }
  }
  
  public Point getDirectionVector(){
    Road road = getRoad();
    if(getDirection() == Direction.IDENTICAL){
      return road.getEndPoint().minus(road.getStartPoint());
    }else{
      return road.getStartPoint().minus(road.getEndPoint());
    }
  }
  
  public double getDistanceFromNextVehicle(Vehicle vehicle){
    double minDistance = Double.MAX_VALUE;
            
    for(Vehicle v : vehicles){
      if(vehicle == v) continue;
      
      double distance = vehicle.getPosition().distance(v.getPosition());
      if(distance < minDistance){
        Point dir = v.getPosition().minus(vehicle.getPosition());
        if(dir.inSameQuadrant(getDirectionVector())){
          minDistance = distance;
        }
      }
    }
    
    return minDistance;
  }
  
}
