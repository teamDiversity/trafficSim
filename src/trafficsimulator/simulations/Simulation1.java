/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.simulations;

import trafficsimulator.core.Junction;
import trafficsimulator.core.Lane;
import trafficsimulator.core.Road;
import trafficsimulator.core.Simulation;
import trafficsimulator.core.Vehicle;
import trafficsimulator.utils.Point;
import trafficsimulator.vehicles.CautiousBus;
import trafficsimulator.vehicles.CautiousCar;
import trafficsimulator.vehicles.NormalBus;
import trafficsimulator.vehicles.NormalCar;
import trafficsimulator.vehicles.RecklessBus;
import trafficsimulator.vehicles.RecklessCar;

/**
 *
 * @author balazs
 */
public class Simulation1 extends Simulation{

  @Override
  protected void init() {
    Road r1 = new Road(new Point(20, 20), new Point(500, 20));
    Lane l11 = new Lane(Lane.Direction.IDENTICAL);
    Lane l12 = new Lane(Lane.Direction.OPPOSITE);
    r1.addLane(l11);
    r1.addLane(l12);
    Road r2 = new Road(new Point(500, 20), new Point(500, 450));
    Lane l21 = new Lane(Lane.Direction.IDENTICAL);
    Lane l22 = new Lane(Lane.Direction.OPPOSITE);
    r2.addLane(l21);
    r2.addLane(l22);
    Road r3 = new Road(new Point(500, 450), new Point(20, 20));
    Lane l31 = new Lane(Lane.Direction.IDENTICAL);
    Lane l32 = new Lane(Lane.Direction.OPPOSITE);
    r3.addLane(l31);
    r3.addLane(l32);
    
    Junction j1 = new Junction();
    j1.connect(l11, l21);
    j1.connect(l22, l12);
    Junction j2 = new Junction();
    j2.connect(l21, l31);
    j2.connect(l32, l22);
    Junction j3 = new Junction();
    j3.connect(l31, l11);
    j3.connect(l12, l32);
    
    map.addRoad(r1);
    map.addRoad(r2);
    map.addRoad(r3);
    map.addJunction(j1);
    map.addJunction(j2);
    map.addJunction(j3);
    
    
    Vehicle v1 = new RecklessCar(l11, new Point(0,0));
    //v1.setTopSpeed(7);
    Vehicle v2 = new NormalCar(l11, new Point(10,0));
    Vehicle v3 = new CautiousCar(l12, r1.getRandomPosition());
    Vehicle v4 = new RecklessBus(l12, r1.getRandomPosition());
    Vehicle v5 = new NormalBus(l12, r1.getRandomPosition());
    Vehicle v6 = new CautiousBus(l12, r1.getRandomPosition());
    
    addVehicle(v1);
    addVehicle(v2);
    addVehicle(v3);
    addVehicle(v4);
    addVehicle(v5);
    addVehicle(v6);
  }
  
}
