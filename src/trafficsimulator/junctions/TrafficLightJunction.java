/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.junctions;

import java.util.ArrayList;
import java.util.List;
import trafficsimulator.core.Junction;
import trafficsimulator.core.Lane;
import trafficsimulator.core.Vehicle;
import trafficsimulator.policies.CongestionControlPolicy;
import trafficsimulator.policies.TrafficPolicy;

/**
 *
 * @author balazs
 */
public class TrafficLightJunction extends Junction {

  private final TrafficPolicy policy;
  private final List<TrafficLight> trafficLights = new ArrayList<>();
  private TrafficLight activeTrafficLight;
  private int stepCounter = 0;

  public TrafficLightJunction(TrafficPolicy policy) {
    this.policy = policy;
  }
  
  public List<TrafficLight> getTrafficLights(){
    return trafficLights;
  }

  public TrafficLight getTrafficLightForLane(Lane lane) {
    for (TrafficLight trafficLight : trafficLights) {
      if (trafficLight.getLane() == lane) {
        return trafficLight;
      }
    }
    return null;
  }

  @Override
  public void connect(Lane source, Lane destination) {
    super.connect(source, destination);

    if (getTrafficLightForLane(source) == null) {
      TrafficLight trafficLight = new TrafficLight(source, policy);
      trafficLights.add(trafficLight);
    }
  }

  @Override
  public boolean shouldVehicleEnterJunction(Vehicle vehicle) {
    TrafficLight trafficLight = getTrafficLightForLane(vehicle.getLane());
    if (trafficLight.getState() == TrafficLight.State.GREEN) {
      return true;
    } else {
      return false;
    }
  }

  private void activateTrafficLight(TrafficLight activeTrafficLight) {
    // Making sure all traffic lights are red
    for (TrafficLight trafficLight : trafficLights) {
      trafficLight.setState(TrafficLight.State.RED);
    }

    // Activating light
    this.activeTrafficLight = activeTrafficLight;
    activeTrafficLight.nextState();
    stepCounter = 0;
  }

  private void activateNextTrafficLight() {
    int index = trafficLights.indexOf(activeTrafficLight);
    if (index == trafficLights.size() - 1) {
      activateTrafficLight(trafficLights.get(0));
    } else {
      activateTrafficLight(trafficLights.get(index + 1));
    }
  }

  @Override
  public void step(long step) {
    if (activeTrafficLight == null) {
      activateTrafficLight(trafficLights.get(0));
      return;
    }
    
    if(!policy.isFixedTime()){
        
        for(TrafficLight tf : trafficLights){
            if (checkForCongestion(tf.getLane())){
            tf.setState(TrafficLight.State.GREEN);
        }else{
            tf.setState(TrafficLight.State.RED);
        }
        }
//        CongestionControlPolicy ccp = new CongestionControlPolicy(trafficLights.getLane());
//        if (ccp.checkForCongestion()){
//            activeTrafficLight.setState(TrafficLight.State.GREEN);
//        }else{
//            activeTrafficLight.setState(TrafficLight.State.RED);
//        }
    }else{
    stepCounter++;

    if (activeTrafficLight.getState() == TrafficLight.State.GREEN && stepCounter == activeTrafficLight.getLights().getGreenLightDuration()) {
      activeTrafficLight.nextState();
      stepCounter = 0;
    } else if (activeTrafficLight.getState() == TrafficLight.State.YELLOW && stepCounter == activeTrafficLight.getLights().getYellowLightDuration()) {
      activateNextTrafficLight();
    } else if (activeTrafficLight.getState() == TrafficLight.State.REDYELLOW && stepCounter == activeTrafficLight.getLights().getRedYellowDuration()) {
      activeTrafficLight.nextState();
      stepCounter = 0;
    } else if (activeTrafficLight.getState() == TrafficLight.State.RED && stepCounter == activeTrafficLight.getLights().getRedLightDuration()) {
      activeTrafficLight.nextState();
      stepCounter = 0;
    }
    }
  }
  
  private boolean checkForCongestion(Lane lane){
  
      List<Vehicle> vehiclesOnLane = lane.getVehicles();
        double totalLengthOfVehicle = 0;
        for(Vehicle v : vehiclesOnLane){
             totalLengthOfVehicle = totalLengthOfVehicle + v.getSize().height;
        }
        
       
        return (totalLengthOfVehicle == (lane.getLaneLength()*0.1))||(totalLengthOfVehicle > (lane.getLaneLength()*0.1));
  }
}
