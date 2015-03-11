/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.core;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author balazs
 */
public class ExitPoint {

  private Lane lane;
  private List<Vehicle> vehicles = new ArrayList<>();

  ExitPoint(Lane lane) {
    this.lane = lane;
  }

  public int numberOfVehicles() {
    return vehicles.size();
  }

  void addVehicle(Vehicle vehicle) {
    System.out.println(vehicle + " exited the system");
    vehicles.add(vehicle);
    vehicle.endTime = System.currentTimeMillis();
  }
}
