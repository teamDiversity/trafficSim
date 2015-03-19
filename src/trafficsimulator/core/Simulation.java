/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.text.Text;
import trafficsimulator.gui.IRenderer;

/**
 *
 * @author balazs
 */
public abstract class Simulation extends TimerTask {

  private long stepCounter = 0;
  protected Timer timer = new Timer();
  protected Map map = new Map();
  protected List<Vehicle> vehicles = new ArrayList<>();
  protected List<EntryPoint> entryPoints = new ArrayList<>();
  protected List<ExitPoint> exitPoints = new ArrayList<>();
  protected IRenderer renderer;
  private long duration;

  public Simulation() {

  }

  public Simulation(IRenderer renderer) {
    this.renderer = renderer;
  }

  protected abstract void init();

  @Override
  public void run() {

    stepCounter++;
    System.out.println("Step " + stepCounter);

    if (!isRunning()) {
      printStats();
      System.out.println("Simulation end");
      timer.cancel();
      return;
    }

    for (ISteppable ep : entryPoints) {
      ep.step(stepCounter);
    }
    
    for (ISteppable junction : map.getJunctions()) {
      junction.step(stepCounter);
    }
    
    for (Vehicle vehicle : getVehicles()) {
      vehicle.getDriver().step(stepCounter);
    }

    for (ISteppable vehicle : getVehicles()) {
      vehicle.step(stepCounter);
    }

    if (renderer != null) {
      renderer.render();
    }
  }
  
  public boolean isRunning(){
    if (numberOfVehiclesAtExitPoints() == vehicles.size()) {
      return false;
    }

    if (stepCounter/10 >= duration) {
      return false;
    };
    
    return true;
  }

  private EntryPoint getEntryPointForLane(Lane lane) {
    for (EntryPoint ep : entryPoints) {
      if (ep.getLane() == lane) {
        return ep;
      }
    }
    EntryPoint ep = new EntryPoint(lane);
    entryPoints.add(ep);
    return ep;
  }

  protected void addVehicle(Vehicle vehicle, Lane lane, long step) {
    EntryPoint ep = getEntryPointForLane(lane);
    ep.addVehicle(vehicle, step);
    vehicles.add(vehicle);
  }

  private List<ExitPoint> getExitPoints() {
    List<ExitPoint> exitPoints = new ArrayList<>();
    for (Road road : getMap().getRoads()) {
      for (Lane lane : road.getLanes()) {
        ExitPoint ep = lane.getExitPoint();
        if (ep == null) {
          continue;
        }
        exitPoints.add(ep);
      }
    }
    return exitPoints;
  }

  private int numberOfVehiclesAtExitPoints() {
    int n = 0;
    for (ExitPoint ep : exitPoints) {
      n += ep.numberOfVehicles();
    }
    return n;
  }

  public void start() {
    init();
    this.exitPoints = getExitPoints();
    timer.scheduleAtFixedRate(this, 0, 100);
  }
  
  public void setDuration(long duration){
    this.duration = duration;
  }

  public IRenderer getRenderer() {
    return renderer;
  }

  public void setRenderer(IRenderer renderer) {
    this.renderer = renderer;
  }

  public Map getMap() {
    return map;
  }

  public List<Vehicle> getVehicles() {
    List<Vehicle> vehiclesInSystem = new ArrayList<>();
    for (Vehicle vehicle : vehicles) {
      if (!vehicle.isInSystem()) {
        continue;
      }
      vehiclesInSystem.add(vehicle);
    }
    return vehiclesInSystem;
  }

  public List<Vehicle> getExitedVehicles() {
    List<Vehicle> exitedVehicles = new ArrayList<>();
    for (ExitPoint ep : exitPoints) {
      exitedVehicles.addAll(ep.getExitedVehicles());
    }
    return exitedVehicles;
  }

  public void printStats() {
    for (Vehicle vehicle : getExitedVehicles()) {
      System.out.println(vehicle.getType() + " was in the system for " + vehicle.timeSpentInSystem() + " seconds");
    }
  }
  
  public Text averageTime() {
    double total = 0;
    double average = 0;
    for (Vehicle vehicle : getExitedVehicles()) {
      total += vehicle.timeSpentInSystem();
    }
    average = total/getExitedVehicles().size();
    
    if ( getExitedVehicles().isEmpty() ) return new Text("Average time: 0");
    else return new Text(String.valueOf("Average time: " + average));
  }
  
  public Text longestTime() {
    double longest = 0;
    for (Vehicle vehicle : getExitedVehicles()) {
      if (longest < vehicle.timeSpentInSystem()) {
        longest = vehicle.timeSpentInSystem();
      }
    }
    if ( getExitedVehicles().isEmpty() ) return new Text("Longest time: 0");
    else return new Text(String.valueOf("Longest time: " + longest));
  }
  
  public Text shortestTime() {
    double shortest = Integer.MAX_VALUE;
    for (Vehicle vehicle : getExitedVehicles()) {
      if (shortest > vehicle.timeSpentInSystem()) {
        shortest = vehicle.timeSpentInSystem();
      }
    }
    if ( getExitedVehicles().isEmpty() ) return new Text("Shortest time: 0");
    else return new Text(String.valueOf("Shortest time: " + shortest));
  }
  
  public Text totalCar(){
      return new Text("Number of vehicles in simulation: "+this.vehicles.size());
  }
}
