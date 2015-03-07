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
  private TrafficLight activeTrafficLight;
  private int stepCounter = 0;
  
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
  
  private void activateTrafficLight(TrafficLight activeTrafficLight){
    // Making sure all traffic lights are red
    for(TrafficLight trafficLight : trafficLights){
      trafficLight.setState(TrafficLight.State.RED);
    }
    
    // Activating light
    this.activeTrafficLight = activeTrafficLight;
    activeTrafficLight.nextState();
    stepCounter = 0;
  }
  
  private void activateNextTrafficLight(){
    int index = trafficLights.indexOf(activeTrafficLight);
    if(index == trafficLights.size()-1){
      activateTrafficLight(trafficLights.get(0));
    }else{
      activateTrafficLight(trafficLights.get(index+1));
    }
  }

  public void step(long step) {
    if(activeTrafficLight == null){
      activateTrafficLight(trafficLights.get(0));
      return;
    }
    
    stepCounter++;
    
    if(activeTrafficLight.getState() == TrafficLight.State.GREEN && stepCounter == TrafficLight.GREEN_DURATION){
      activeTrafficLight.nextState();
      stepCounter = 0;
    }else if(activeTrafficLight.getState() == TrafficLight.State.YELLOW && stepCounter == TrafficLight.YELLOW_DURATION){
      activateNextTrafficLight();
    }else if(activeTrafficLight.getState() == TrafficLight.State.REDYELLOW && stepCounter == TrafficLight.REDYELLOW_DURATION){
      activeTrafficLight.nextState();
      stepCounter = 0;
    }else if(activeTrafficLight.getState() == TrafficLight.State.RED && stepCounter == TrafficLight.RED_DURATION){
      activeTrafficLight.nextState();
      stepCounter = 0;
    }
  }
}
