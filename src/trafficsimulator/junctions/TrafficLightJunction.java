/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.junctions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import trafficsimulator.core.Junction;
import trafficsimulator.core.Lane;
import trafficsimulator.core.Vehicle;

/**
 *
 * @author balazs
 */
public class TrafficLightJunction extends Junction{
  
  private List<TrafficLight> trafficLights = new ArrayList();
  
  private TrafficLight getTrafficLightForLane(Lane lane){
    for(TrafficLight trafficLight : trafficLights){
      if(trafficLight.getLane()==lane){
        return trafficLight;
      }
    }
    return null;
  }
  
  public void connect(Lane source, Lane destination) {
    super.connect(source, destination);
    
    if (getTrafficLightForLane(source) == null) {
      TrafficLight trafficLight = new TrafficLight(source);
      trafficLights.add(trafficLight);
    }
  }
  
  public boolean shouldVehicleEnterJunction(Vehicle vehicle){
    TrafficLight trafficLight = getTrafficLightForLane(vehicle.getLane());
    if(trafficLight.getState() == TrafficLight.State.GREEN){
      return true;
    }else{
      return false;
    }
  }
}
