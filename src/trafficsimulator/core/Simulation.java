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
import trafficsimulator.gui.IRenderer;

/**
 *
 * @author balazs
 */
public abstract class Simulation extends TimerTask{
  private long stepCounter = 0;
  protected Timer timer = new Timer();
  protected Map map = new Map();
  protected List<Vehicle> vehicles = new ArrayList<>();
  protected List<EntryPoint> entryPoints = new ArrayList<>();
  protected IRenderer renderer;
  
  public Simulation(){

  }
  
  public Simulation(IRenderer renderer){
    this.renderer = renderer;
  }
  
  protected abstract void init();
  
  

  @Override
  public void run() {
    stepCounter++;
    System.out.println("Step " + stepCounter);
    
    for(EntryPoint ep :entryPoints){
      ep.step(stepCounter);
    }
    
    for(Vehicle vehicle : getVehicles()){
      vehicle.step();
    }
    
    if(renderer != null){
      renderer.render();
    }
  }
  
  private EntryPoint getEntryPointForLane(Lane lane){
    for(EntryPoint ep : entryPoints){
      if(ep.getLane() == lane) return ep;
    }
    EntryPoint ep = new EntryPoint(lane);
    entryPoints.add(ep);
    return ep;
  }
  
  protected void addVehicle(Vehicle vehicle, Lane lane, long step){
    EntryPoint ep = getEntryPointForLane(lane);
    ep.addVehicle(vehicle, step);
    vehicles.add(vehicle);
  }
  
  
  
  public void start(){
    init();
    timer.scheduleAtFixedRate(this, 0, 100);
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
    for(Vehicle vehicle : vehicles){
      if(!vehicle.isInSystem()) continue;
      vehiclesInSystem.add(vehicle);
    }
    return vehiclesInSystem;
  }

}
