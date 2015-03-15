/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.simulations;

import trafficsimulator.core.Driver;
import trafficsimulator.core.Junction;
import trafficsimulator.core.Lane;
import trafficsimulator.core.Road;
import trafficsimulator.core.Simulation;
import trafficsimulator.core.Vehicle;
import trafficsimulator.drivers.CautiousDriver;
import trafficsimulator.drivers.NormalDriver;
import trafficsimulator.drivers.RecklessDriver;
import trafficsimulator.junctions.TrafficLightJunction;
import trafficsimulator.policies.TrafficPolicy;
import trafficsimulator.utils.Point;
import trafficsimulator.vehicles.Bus;
import trafficsimulator.vehicles.Car;


/**
 *
 * @author balazs
 */
public class Simulation1 extends Simulation{

  @Override
  protected void init() {
    Road r1 = new Road(new Point(70, 20), new Point(500, 20));
    Lane l11 = r1.addLane(Lane.Direction.IDENTICAL);
    Lane l12 = r1.addLane(Lane.Direction.OPPOSITE);
    Road r2 = new Road(new Point(500, 20), new Point(500, 450));
    Lane l21 = r2.addLane(Lane.Direction.IDENTICAL);
    Lane l22 = r2.addLane(Lane.Direction.OPPOSITE);
    Road r3 = new Road(new Point(500, 450), new Point(20, 100));
    Lane l31 = r3.addLane(Lane.Direction.IDENTICAL);
    Lane l32 = r3.addLane(Lane.Direction.OPPOSITE);
    Road r4 = new Road(new Point(500, 20), new Point(600, 20));
    Lane l41 = r4.addLane(Lane.Direction.IDENTICAL);
    Lane l42 = r4.addLane(Lane.Direction.OPPOSITE);
    Road r5 = new Road(new Point(600, 20), new Point(600, 450));
    Lane l51 = r5.addLane(Lane.Direction.IDENTICAL);
    Lane l52 = r5.addLane(Lane.Direction.OPPOSITE);
    Road r6 = new Road(new Point(600, 450), new Point(500, 450));
    Lane l61 = r6.addLane(Lane.Direction.IDENTICAL);
    Lane l62 = r6.addLane(Lane.Direction.OPPOSITE);
    Road r7 = new Road(new Point(600, 450), new Point(650, 450));
    Lane l71 = r7.addLane(Lane.Direction.IDENTICAL);
    
    TrafficPolicy policy = new TrafficPolicy(true);
    Junction j1 = new TrafficLightJunction(policy);
    j1.connect(l11, l21);
    j1.connect(l11, l41);
    j1.connect(l22, l12);
    j1.connect(l22, l41);
    j1.connect(l42, l12);
    j1.connect(l42, l21);
    Junction j2 = new TrafficLightJunction(policy);
    j2.connect(l21, l31);
    j2.connect(l21, l62);
    j2.connect(l32, l22);
    j2.connect(l32, l62);
    j2.connect(l61, l22);
    j2.connect(l61, l31);
    Junction j3 = new TrafficLightJunction(policy);
    j3.connect(l31, l11);
    j3.connect(l12, l32);
    Junction j4 = new TrafficLightJunction(policy);
    j4.connect(l41, l51);
    j4.connect(l52, l42);
    Junction j5 = new TrafficLightJunction(policy);
    j5.connect(l51, l61);
    j5.connect(l51, l71);
    j5.connect(l62, l52);
    j5.connect(l62, l71);
    
    map.addRoad(r1);
    map.addRoad(r2);
    map.addRoad(r3);
    map.addRoad(r4);
    map.addRoad(r5);
    map.addRoad(r6);
    map.addRoad(r7);
    map.addJunction(j1);
    map.addJunction(j2);
    map.addJunction(j3);
    map.addJunction(j4);
    map.addJunction(j5);
    
    Driver tom = new CautiousDriver("Tom");
    Driver mary = new NormalDriver("Mary");
    Driver jerry = new RecklessDriver("Jerry");
    

    addVehicle(new Car(tom), l11, 1);
    addVehicle(new Bus(jerry), l12, 20);

  }
  
}
