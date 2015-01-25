/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author balazs
 */
public class Simulation extends TimerTask{
  private Timer timer;
  private Map map;
  private List<Vehicle> vehicles;
  
  public Simulation(){
    timer = new Timer();
    vehicles = new ArrayList<>();
  }
  
  public void start(){
    timer.scheduleAtFixedRate(this, 0, 500);
  }

  @Override
  public void run() {
    System.out.println("Tick " + new Date());
    for(Vehicle vehicle : vehicles){
      vehicle.step();
    }
  }

  public Map getMap() {
    return map;
  }

  public void setMap(Map map) {
    this.map = map;
  }

  public List<Vehicle> getVehicles() {
    return vehicles;
  }

  public void addVehicle(Vehicle vehicle) {
    vehicles.add(vehicle);
  }

}
