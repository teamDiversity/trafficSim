/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.core;

import trafficsimulator.vehicles.RecklessCar;
import trafficsimulator.vehicles.CautiousCar;
import trafficsimulator.vehicles.CautiousBus;
import trafficsimulator.vehicles.RecklessBus;
import trafficsimulator.vehicles.NormalCar;
import trafficsimulator.core.Lane.Direction;
import trafficsimulator.utils.Point;
import trafficsimulator.vehicles.NormalBus;

/**
 *
 * @author balazs
 */
public class TrafficSimulator {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    Map m = new Map();
    
    Road r1 = new Road(new Point(0, 0), new Point(200, 0));
    Lane l11 = new Lane(Direction.IDENTICAL);
    Lane l12 = new Lane(Direction.OPPOSITE);
    r1.addLane(l11);
    r1.addLane(l12);
    Road r2 = new Road(new Point(200, 0), new Point(100, 100));
    Lane l21 = new Lane(Direction.IDENTICAL);
    Lane l22 = new Lane(Direction.OPPOSITE);
    r2.addLane(l21);
    r2.addLane(l22);
    Road r3 = new Road(new Point(100, 100), new Point(0, 0));
    Lane l31 = new Lane(Direction.IDENTICAL);
    Lane l32 = new Lane(Direction.OPPOSITE);
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
    
    m.addRoad(r1);
    m.addRoad(r2);
    m.addRoad(r3);
    m.addJunction(j1);
    m.addJunction(j2);
    m.addJunction(j3);
    
    
    Vehicle v1 = new RecklessCar(l11, new Point(0,0));
    //v1.setTopSpeed(7);
    Vehicle v2 = new NormalCar(l11, new Point(10,0));
    Vehicle v3 = new CautiousCar(l12, r1.getRandomPosition());
    Vehicle v4 = new RecklessBus(l12, r1.getRandomPosition());
    Vehicle v5 = new NormalBus(l12, r1.getRandomPosition());
    Vehicle v6 = new CautiousBus(l12, r1.getRandomPosition());
            
    
    Simulation s = new Simulation();
    s.setMap(m);
    s.addVehicle(v1);
    s.addVehicle(v2);
    s.addVehicle(v3);
    s.addVehicle(v4);
    s.addVehicle(v5);
    s.addVehicle(v6);
    s.start();
  }
  
}
