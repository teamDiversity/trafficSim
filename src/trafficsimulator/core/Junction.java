/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import trafficsimulator.utils.Point;

/**
 *
 * @author balazs
 */
public abstract class Junction implements ISteppable {

  private final HashMap<Lane, List<Lane>> connections = new HashMap<>();
  private final List<Lane> lanes = new ArrayList<>();
  private final HashMap<Road, List<Point>> roadPoints = new HashMap<>();

  public Junction() {
  }

  public void connect(Lane source, Lane destination) {
    if (!connections.containsKey(source)) {
      connections.put(source, new ArrayList<Lane>());
    }
    List<Lane> lanes = connections.get(source);
    Lane junctionLane = new Lane(source.getDirection(), source.getEndPoint(), destination.getStartPoint());
    junctionLane.setJunction(this);
    this.lanes.add(junctionLane);
    lanes.add(junctionLane);
    List<Lane> junctionLaneDestinations = new ArrayList();
    junctionLaneDestinations.add(destination);
    connections.put(junctionLane, junctionLaneDestinations);
    source.setJunction(this);
    
    //Store points
    if(!roadPoints.containsKey(source.getRoad())){
      if(source.getDirection() == Lane.Direction.IDENTICAL){
        List<Point> points = new ArrayList<>();
        
        points.add(source.getRoad().getLeftEndPoint());
        points.add(source.getRoad().getRightEndPoint());
        roadPoints.put(source.getRoad(), points);
      }else if(source.getDirection() == Lane.Direction.OPPOSITE){
        List<Point> points = new ArrayList<>();
        
        points.add(source.getRoad().getRightStartPoint());
        points.add(source.getRoad().getLeftStartPoint());
        roadPoints.put(source.getRoad(), points);
      }
    }
    if(!roadPoints.containsKey(destination.getRoad())){
      if(destination.getDirection() == Lane.Direction.OPPOSITE){
        List<Point> points = new ArrayList<>();
        
        points.add(destination.getRoad().getLeftEndPoint());
        points.add(destination.getRoad().getRightEndPoint());
        roadPoints.put(destination.getRoad(), points);
      }else if(destination.getDirection() == Lane.Direction.IDENTICAL){
        List<Point> points = new ArrayList<>();
        
        points.add(destination.getRoad().getRightStartPoint());
        points.add(destination.getRoad().getLeftStartPoint());
        roadPoints.put(destination.getRoad(), points);
      }
    }
  }
  
  public List<Lane> getLanes(){
    return lanes;
  }
  
  public List<Road> getRoads(){
    return new ArrayList<>(roadPoints.keySet());
  }
  
  public List<Point> getPointsForRoad(Road road){
    return roadPoints.get(road);
  }
  
  public Point getCenterPoint(){
    Set<Point> allPoints = new HashSet<>();
    for(Road road: getRoads()){
      for(Point point:getPointsForRoad(road)){
        allPoints.add(point);
      }
    }
    return Point.centroid(new ArrayList(allPoints));
  }

  public List<Lane> getConnectedLanes(Lane lane) {
    return connections.get(lane);
  }

  public boolean shouldVehicleEnterJunction(Vehicle vehicle) {
    return true;
  }
}
