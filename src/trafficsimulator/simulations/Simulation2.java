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
import trafficsimulator.utils.Point;
import trafficsimulator.vehicles.Car;

/**
 *
 * @author yukolthep
 */
public class Simulation2 extends Simulation {

  @Override
  protected void init() {
    Road r1 = new Road(new Point(70, 300), new Point(270, 100));
    Lane l11 = new Lane(Lane.Direction.IDENTICAL);
    Lane l12 = new Lane(Lane.Direction.OPPOSITE);
    r1.addLane(l11);
    r1.addLane(l12);
    Road r2 = new Road(new Point(270, 100), new Point(470, 300));
    Lane l21 = new Lane(Lane.Direction.IDENTICAL);
    Lane l22 = new Lane(Lane.Direction.OPPOSITE);
    r2.addLane(l21);
    r2.addLane(l22);
    Road r3 = new Road(new Point(470, 300), new Point(270, 500));
    Lane l31 = new Lane(Lane.Direction.IDENTICAL);
    Lane l32 = new Lane(Lane.Direction.OPPOSITE);
    r3.addLane(l31);
    r3.addLane(l32);
    Road r4 = new Road(new Point(270, 500), new Point(70, 300));
    Lane l41 = new Lane(Lane.Direction.IDENTICAL);
    Lane l42 = new Lane(Lane.Direction.OPPOSITE);
    r4.addLane(l41);
    r4.addLane(l42);

    Junction j1 = new Junction();
    j1.connect(l11, l21);
    j1.connect(l22, l12);
    Junction j2 = new Junction();
    j2.connect(l21, l31);
    j2.connect(l32, l22);
    Junction j3 = new Junction();
    j3.connect(l31, l41);
    j3.connect(l42, l32);
    Junction j4 = new Junction();
    j4.connect(l41, l11);
    j4.connect(l12, l42);

    map.addRoad(r1);
    map.addRoad(r2);
    map.addRoad(r3);
    map.addRoad(r4);
    map.addJunction(j1);
    map.addJunction(j2);
    map.addJunction(j3);
    map.addJunction(j4);

    Driver tom = new CautiousDriver("Tom");
    Driver jerry = new RecklessDriver("Jerry");

    Vehicle olo = new Car(tom);
    Vehicle olo_v2 = new Car(jerry);

    addVehicle(olo, l11, 1);
    addVehicle(olo_v2, l22, 1);

  }

}
