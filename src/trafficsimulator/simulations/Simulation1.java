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
    Road r4 = new Road(new Point(500, 20), new Point(600, 20));
    Lane l41 = new Lane(Lane.Direction.IDENTICAL);
    Lane l42 = new Lane(Lane.Direction.OPPOSITE);
    r4.addLane(l41);
    r4.addLane(l42);
    Road r5 = new Road(new Point(600, 20), new Point(600, 450));
    Lane l51 = new Lane(Lane.Direction.IDENTICAL);
    Lane l52 = new Lane(Lane.Direction.OPPOSITE);
    r5.addLane(l51);
    r5.addLane(l52);
    Road r6 = new Road(new Point(600, 450), new Point(500, 450));
    Lane l61 = new Lane(Lane.Direction.IDENTICAL);
    Lane l62 = new Lane(Lane.Direction.OPPOSITE);
    r6.addLane(l61);
    r6.addLane(l62);
    
    Junction j1 = new Junction();
    j1.connect(l11, l21);
    j1.connect(l11, l41);
    j1.connect(l22, l12);
    j1.connect(l22, l41);
    j1.connect(l42, l12);
    j1.connect(l42, l21);
    Junction j2 = new Junction();
    j2.connect(l21, l31);
    j2.connect(l21, l62);
    j2.connect(l32, l22);
    j2.connect(l32, l62);
    j2.connect(l61, l22);
    j2.connect(l61, l31);
    Junction j3 = new Junction();
    j3.connect(l31, l11);
    j3.connect(l12, l32);
    Junction j4 = new Junction();
    j4.connect(l41, l51);
    j4.connect(l52, l42);
    Junction j5 = new Junction();
    j5.connect(l51, l61);
    j5.connect(l62, l52);
    
    map.addRoad(r1);
    map.addRoad(r2);
    map.addRoad(r3);
    map.addRoad(r4);
    map.addRoad(r5);
    map.addRoad(r6);
    map.addJunction(j1);
    map.addJunction(j2);
    map.addJunction(j3);
    map.addJunction(j4);
    map.addJunction(j5);
    
    
    Vehicle v1 = new RecklessCar(l11, new Point(20,20));
    //v1.setTopSpeed(7);
    Vehicle v2 = new NormalCar(l11, new Point(60,20));
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
