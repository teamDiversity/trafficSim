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
public abstract class Simulation extends TimerTask{
  protected Timer timer = new Timer();
  protected Map map = new Map();
  protected List<Vehicle> vehicles = new ArrayList<>();
  
  public Simulation(){

  }
  
  protected abstract void init();
  
  public void start(){
    init();
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

  public List<Vehicle> getVehicles() {
    return vehicles;
  }

  protected void addVehicle(Vehicle vehicle) {
    vehicles.add(vehicle);
  }

}
