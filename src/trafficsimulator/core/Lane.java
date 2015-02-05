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
  private List<Vehicle> vehicles;
  private Junction junction;
  private Direction direction;
  
  public Lane(Direction direction){
    vehicles = new ArrayList<>();
    this.direction = direction;
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
    this.junction = junction;
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
  
}
