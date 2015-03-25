/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.simulations;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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

/**
 *
 * @author yukolthep
 */
public class Simulation3 extends Simulation {
  
  private boolean peaktime;
  private boolean congestionControl;
  
  private List<Lane> entryLanes = new ArrayList<>();
  private List<String> vehicleTypes = new ArrayList<>();
  private Random randomGenerator = new Random();

  public Simulation3(boolean peaktime, boolean congestionControl) {
    this.peaktime = peaktime;
    this.congestionControl = congestionControl;
  }  
  
  @Override
  protected void init() {
    Road r1 = new Road(new Point(200, 0), new Point(200, 100));
    Lane l11 = r1.addLane(Lane.Direction.IDENTICAL);
    entryLanes.add(l11);
    Lane l12 = r1.addLane(Lane.Direction.OPPOSITE);
    
    Road r2 = new Road(new Point(200, 150), new Point(200, 300));
    Lane l21 = r2.addLane(Lane.Direction.IDENTICAL);
    Lane l22 = r2.addLane(Lane.Direction.OPPOSITE);
    
    Road r3 = new Road(new Point(200, 325), new Point(190, 400));
    Lane l31 = r3.addLane(Lane.Direction.IDENTICAL);
    Lane l32 = r3.addLane(Lane.Direction.OPPOSITE);
    
    Road r4 = new Road(new Point(185,423), new Point(130,600));
    Lane l41 = r4.addLane(Lane.Direction.IDENTICAL);
    Lane l42 = r4.addLane(Lane.Direction.OPPOSITE);
    entryLanes.add(l42);
    
    Road r5 = new Road(new Point(0,0), new Point(150, 100));
    Lane l51 = r5.addLane(Lane.Direction.IDENTICAL);
    entryLanes.add(l51);
    
    Road r6 = new Road(new Point(150,150), new Point(0,250));
    Lane l61 = r6.addLane(Lane.Direction.IDENTICAL);
    
    Road r7 = new Road(new Point(0, 400), new Point(140, 400));
    Lane l71 = r7.addLane(Lane.Direction.IDENTICAL);
    entryLanes.add(l71);
    
    Road r8 = new Road(new Point(200, 100), new Point(275, 100));
    Lane l81 = r8.addLane(Lane.Direction.IDENTICAL);
    Lane l82 = r8.addLane(Lane.Direction.OPPOSITE);
    
    Road r9 = new Road(new Point(200, 300), new Point(275, 300));
    Lane l91 = r9.addLane(Lane.Direction.IDENTICAL);
    
    Road r10 = new Road(new Point(290, 100), new Point(400, 0));
    Lane l101 = r10.addLane(Lane.Direction.IDENTICAL);
    
    Road r11 = new Road(new Point(325, 100), new Point(400, 100));
    Lane l111 = r11.addLane(Lane.Direction.IDENTICAL);
    Lane l112 = r11.addLane(Lane.Direction.OPPOSITE);
    
    Road r12 = new Road (new Point(325, 150), new Point(325, 300));
    Lane l121 = r12.addLane(Lane.Direction.IDENTICAL);
    Lane l122 = r12.addLane(Lane.Direction.OPPOSITE);
    
    Road r13 = new Road (new Point(325, 325), new Point(250, 600));
    Lane l131 = r13.addLane(Lane.Direction.IDENTICAL);
    Lane l132 = r13.addLane(Lane.Direction.OPPOSITE);
    entryLanes.add(l132);
    
    Road r14 = new Road(new Point(535, 0), new Point(425, 100));
    Lane l141 = r14.addLane(Lane.Direction.IDENTICAL);
    entryLanes.add(l141);

    Road r15 = new Road(new Point(425, 150), new Point(500, 260));
    Lane l151 = r15.addLane(Lane.Direction.IDENTICAL);
    
    Road r16 = new Road(new Point(325, 300), new Point(375, 300));
    Lane l161 = r16.addLane(Lane.Direction.IDENTICAL);

    Road r17 = new Road(new Point(425, 300), new Point(485, 275));
    Lane l171 = r17.addLane(Lane.Direction.IDENTICAL);
    Lane l172 = r17.addLane(Lane.Direction.OPPOSITE);
    
    Road r18 = new Road(new Point(425, 350), new Point(425, 600));
    Lane l181 = r18.addLane(Lane.Direction.IDENTICAL);
    Lane l182 = r18.addLane(Lane.Direction.OPPOSITE);
    entryLanes.add(l182);
    
    Road r19 = new Road(new Point(530, 300), new Point(605, 400));
    Lane l191 = r19.addLane(Lane.Direction.IDENTICAL);
    
    Road r20 = new Road(new Point(425, 100), new Point(605, 100));
    Lane l201 = r20.addLane(Lane.Direction.IDENTICAL);
    Lane l202 = r20.addLane(Lane.Direction.OPPOSITE);
    
    Road r21 = new Road(new Point(655, 0), new Point(655, 100));
    Lane l211 = r21.addLane(Lane.Direction.IDENTICAL);
    entryLanes.add(l211);
    Lane l212 = r21.addLane(Lane.Direction.OPPOSITE);
    
    Road r22 = new Road(new Point(655, 150), new Point(655, 400));
    Lane l221 = r22.addLane(Lane.Direction.IDENTICAL);
    Lane l222 = r22.addLane(Lane.Direction.OPPOSITE);
    
    Road r23 = new Road(new Point(655, 450), new Point(655, 600));
    Lane l231 = r23.addLane(Lane.Direction.IDENTICAL);
    Lane l232 = r23.addLane(Lane.Direction.OPPOSITE);
    entryLanes.add(l232);
    
    Road r24 = new Road(new Point(655, 100), new Point(800, 100));
    Lane l241 = r24.addLane(Lane.Direction.IDENTICAL);
    Lane l242 = r24.addLane(Lane.Direction.OPPOSITE);
    entryLanes.add(l242);
    
    Road r25 = new Road(new Point(800, 450), new Point(655, 450));
    Lane l251 = r25.addLane(Lane.Direction.IDENTICAL);
    entryLanes.add(l251);
    Lane l252 = r25.addLane(Lane.Direction.OPPOSITE);
            

    
    TrafficPolicy policy = new TrafficPolicy(peaktime,congestionControl);
    Junction j1 = new TrafficLightJunction(policy);
    j1.connect(l11, l21);
    j1.connect(l11, l61);
    j1.connect(l11, l81);
    j1.connect(l22, l12);
    j1.connect(l22, l81);
    j1.connect(l51, l21);
    j1.connect(l51, l81);
    j1.connect(l82, l12);
    j1.connect(l82, l21);
    j1.connect(l82, l61);
    
    Junction j2 = new TrafficLightJunction(policy);
    j2.connect(l21, l31);
    j2.connect(l21, l91);
    j2.connect(l32, l22);
    j2.connect(l32, l91);
    
    Junction j3 = new TrafficLightJunction(policy);
    j3.connect(l31, l41);
    j3.connect(l42, l32);
    j3.connect(l71, l32);
    j3.connect(l71, l41);
    
    Junction j4 = new TrafficLightJunction(policy);
    j4.connect(l81, l101);
    j4.connect(l81, l111);
    j4.connect(l81, l121);
    j4.connect(l112, l82);
    j4.connect(l112, l121);
    j4.connect(l122, l82);
    j4.connect(l122, l101);
    j4.connect(l122, l111);
    
    Junction j5 = new TrafficLightJunction(policy);
    j5.connect(l91, l122);
    j5.connect(l91, l161);
    j5.connect(l121, l131);
    j5.connect(l121, l161);
    j5.connect(l132, l122);
    j5.connect(l132, l161);
        
    Junction j6 = new TrafficLightJunction(policy);
    j6.connect(l111, l151);
    j6.connect(l111, l201);
    j6.connect(l141, l112);
    j6.connect(l202, l112);
    
    Junction j7 = new TrafficLightJunction(policy);
    j7.connect(l161, l171);
    j7.connect(l161, l181);
    j7.connect(l172, l181);
    j7.connect(l182, l171);
    
    Junction j8 = new TrafficLightJunction(policy);
    j8.connect(l151, l172);
    j8.connect(l151, l191);
    j8.connect(l171, l191);
    
    Junction j9 = new TrafficLightJunction(policy);
    j9.connect(l201, l212);
    j9.connect(l201, l221);
    j9.connect(l201, l241);
    j9.connect(l211, l221);
    j9.connect(l211, l202);
    j9.connect(l211, l241);
    j9.connect(l222, l212);
    j9.connect(l222, l202);
    j9.connect(l222, l241);
    j9.connect(l242, l212);
    j9.connect(l242, l221);
    j9.connect(l242, l202);
    
    Junction j10 = new TrafficLightJunction(policy);
    j10.connect(l191, l231);
    j10.connect(l191, l252);
    j10.connect(l221, l231);
    j10.connect(l221, l252);
    j10.connect(l232, l222);
    j10.connect(l232, l252);
    j10.connect(l251, l222);
    j10.connect(l251, l231);
    
    
    
    map.addRoad(r1);
    map.addRoad(r2);
    map.addRoad(r3);
    map.addRoad(r4);
    map.addRoad(r5);
    map.addRoad(r6);
    map.addRoad(r7);
    map.addRoad(r8);
    map.addRoad(r9);
    map.addRoad(r10);
    map.addRoad(r11);
    map.addRoad(r12);
    map.addRoad(r13);
    map.addRoad(r14);
    map.addRoad(r15);
    map.addRoad(r16);
    map.addRoad(r17);
    map.addRoad(r18);
    map.addRoad(r19);
    map.addRoad(r20);
    map.addRoad(r21);
    map.addRoad(r22);
    map.addRoad(r23);
    map.addRoad(r24);
    map.addRoad(r25);
    map.addJunction(j1);
    map.addJunction(j2);
    map.addJunction(j3);
    map.addJunction(j4);
    map.addJunction(j5);
    map.addJunction(j6);
    map.addJunction(j7);
    map.addJunction(j8);
    map.addJunction(j9);
    map.addJunction(j10);
    
    int longestSimulationTime = 5000;
    int vehicleFrequency;
    if(peaktime) {
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
