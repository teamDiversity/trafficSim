/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author balazs
 */
public class EntryPoint implements ISteppable{

  private Lane lane;
  private Map<Long, List<Vehicle>> steps = new HashMap<>();
  private Map<Vehicle, Long> vehicles = new HashMap<>();

  public EntryPoint(Lane lane) {
    this.lane = lane;
  }

  public Lane getLane() {
    return lane;
  }

  public void addVehicle(Vehicle vehicle, long step) {
    vehicles.put(vehicle, step);

    List stepList = steps.get(step);
    if (stepList == null) {
      stepList = new ArrayList<Vehicle>();
      steps.put(step, stepList);
    }
    stepList.add(vehicle);
    vehicle.startTime = System.currentTimeMillis();
  }

  public int numberOfVehicles() {
    return vehicles.size();
  }

  public void step(long step) {
    List<Vehicle> vehiclesForStep = steps.get(step);
    if (vehiclesForStep == null) {
      return;
    }
    for (Vehicle vehicle : vehiclesForStep) {
      if(lane.getFreeSpace() > vehicle.getSize().height){
        //Add vehicle to system
        System.out.println(vehicle + " entered the system");
        vehicle.setLane(lane);
      }
    }
  }
}
