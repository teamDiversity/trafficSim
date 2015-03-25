/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.simulations;

import java.util.ArrayList;
import java.util.List;
import trafficsimulator.core.Driver;
import trafficsimulator.core.Junction;
import trafficsimulator.core.Lane;
import trafficsimulator.core.Road;
import trafficsimulator.core.Simulation;
import trafficsimulator.drivers.CautiousDriver;
import trafficsimulator.drivers.NormalDriver;
import trafficsimulator.drivers.RecklessDriver;
import trafficsimulator.junctions.TrafficLightJunction;
import trafficsimulator.policies.TrafficPolicy;
import trafficsimulator.utils.Point;
import trafficsimulator.vehicles.Bus;
import trafficsimulator.vehicles.Car;
import java.util.Random;


/**
 *
 * @author balazs
 */
public class Simulation1 extends Simulation{
  
  private boolean isPeaktime;
  private List<Lane> entryLanes = new ArrayList<>();
  private List<String> vehicleTypes = new ArrayList<>();
  private Random randomGenerator = new Random();
  
  public Simulation1(boolean isPeaktime) {
    this.isPeaktime = isPeaktime;
  }

  @Override
  protected void init() {
    Road r1 = new Road(new Point(425, 50), new Point(425, 275));
    Lane l11 = r1.addLane(Lane.Direction.IDENTICAL);
    entryLanes.add(l11);
    Lane l12 = r1.addLane(Lane.Direction.OPPOSITE);
    Road r2 = new Road(new Point(425, 325), new Point(425, 550));
    Lane l21 = r2.addLane(Lane.Direction.IDENTICAL);
    Lane l22 = r2.addLane(Lane.Direction.OPPOSITE);
    entryLanes.add(l22);
    Road r3 = new Road(new Point(150, 275), new Point(375, 275));
    Lane l31 = r3.addLane(Lane.Direction.IDENTICAL);
    entryLanes.add(l31);
    Lane l32 = r3.addLane(Lane.Direction.OPPOSITE);
    Road r4 = new Road(new Point(425, 275), new Point(650, 275));
    Lane l41 = r4.addLane(Lane.Direction.IDENTICAL);
    Lane l42 = r4.addLane(Lane.Direction.OPPOSITE);
    entryLanes.add(l42);
        
    TrafficPolicy policy = new TrafficPolicy(false);
    Junction j1 = new TrafficLightJunction(policy);
    j1.connect(l11, l21);
    j1.connect(l11, l32);
    j1.connect(l11, l41);
    j1.connect(l22, l12);
    j1.connect(l22, l32);
    j1.connect(l22, l41);
    j1.connect(l31, l12);
    j1.connect(l31, l21);
    j1.connect(l31, l41);
    j1.connect(l42, l12);
    j1.connect(l42, l21);
    j1.connect(l42, l32);
    
    map.addRoad(r1);
    map.addRoad(r2);
    map.addRoad(r3);
    map.addRoad(r4);  
    map.addJunction(j1);
        
    int longestSimulationTime = 5000;
    int vehicleFrequency;
    if(isPeaktime) {
      vehicleFrequency = 5;
    } else {
      vehicleFrequency = 15;
    }
    
    vehicleTypes.add("cautiousCar");
    vehicleTypes.add("normalCar");
    vehicleTypes.add("recklessCar");
    vehicleTypes.add("cautiousBus");
    vehicleTypes.add("normalBus");
    vehicleTypes.add("recklessBus");
    
    for (int i = 0; i < longestSimulationTime; i += vehicleFrequency) {
      int randomLaneindex = randomGenerator.nextInt(entryLanes.size());
      int randomVehicleIndex = randomGenerator.nextInt(vehicleTypes.size());
      String vehicleType = vehicleTypes.get(randomVehicleIndex);
        switch (vehicleType) {
          case "cautiousCar":
            Driver cautiousC = new CautiousDriver(Integer.toString(i));
            addVehicle(new Car(cautiousC), entryLanes.get(randomLaneindex), i);
            break;
          case "normalCar":
            Driver normalC = new NormalDriver(Integer.toString(i));
            addVehicle(new Car(normalC), entryLanes.get(randomLaneindex), i);
            break;
          case "recklessCar":
            Driver recklessC = new RecklessDriver(Integer.toString(i));
            addVehicle(new Car(recklessC), entryLanes.get(randomLaneindex), i);
            break;
          case "cautiousBus":
            Driver cautiousB = new CautiousDriver(Integer.toString(i));
            addVehicle(new Bus(cautiousB), entryLanes.get(randomLaneindex), i);
            break;
          case "normalBus":
            Driver normalB = new NormalDriver(Integer.toString(i));
            addVehicle(new Bus(normalB), entryLanes.get(randomLaneindex), i);
            break;
          case "recklessBus":
            Driver recklessB = new RecklessDriver(Integer.toString(i));
            addVehicle(new Bus(recklessB), entryLanes.get(randomLaneindex), i);
            break;
        }
    }

  }
  
}
