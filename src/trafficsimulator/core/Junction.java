/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author balazs
 */
public abstract class Junction implements ISteppable {

  private HashMap<Lane, List<Lane>> connections;
  private List<Lane> lanes = new ArrayList<Lane>();

  public Junction() {
    connections = new HashMap<>();
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
  }
  
  public List<Lane> getLanes(){
    return lanes;
  }

  public List<Lane> getConnectedLanes(Lane lane) {
    return connections.get(lane);
  }

  public boolean shouldVehicleEnterJunction(Vehicle vehicle) {
    return true;
  }
}
