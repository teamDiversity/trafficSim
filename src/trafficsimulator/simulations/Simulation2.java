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
public class Simulation2 extends Simulation {

  private boolean peaktime;
  private boolean offPeakTime;
  private boolean congestionControl;
  private List<Lane> entryLanes = new ArrayList<>();
  private List<String> vehicleTypes = new ArrayList<>();
  private Random randomGenerator = new Random();
  
  public Simulation2(boolean peaktime, boolean congestionControl) {
    this.peaktime = peaktime;
    this.congestionControl = congestionControl;
  }
  @Override
  protected void init() {
    Road r1 = new Road(new Point(0, 100), new Point(100, 100));
    Lane l11 = r1.addLane(Lane.Direction.IDENTICAL);
    entryLanes.add(l11);
    Lane l12 = r1.addLane(Lane.Direction.IDENTICAL);
    entryLanes.add(l12);
    Lane l13 = r1.addLane(Lane.Direction.OPPOSITE);
    Lane l14 = r1.addLane(Lane.Direction.OPPOSITE);
    
    Road r2 = new Road(new Point(150, 100), new Point(350, 100));
    Lane l21 = r2.addLane(Lane.Direction.IDENTICAL);
    Lane l22 = r2.addLane(Lane.Direction.IDENTICAL);
    Lane l23 = r2.addLane(Lane.Direction.OPPOSITE);
    Lane l24 = r2.addLane(Lane.Direction.OPPOSITE);
    
    Road r3 = new Road(new Point(450, 100), new Point(650, 100));
    Lane l31 = r3.addLane(Lane.Direction.IDENTICAL);
    Lane l32 = r3.addLane(Lane.Direction.IDENTICAL);
    Lane l33 = r3.addLane(Lane.Direction.OPPOSITE);
    Lane l34 = r3.addLane(Lane.Direction.OPPOSITE);
    
    Road r4 = new Road(new Point(700, 100), new Point(800, 100));
    Lane l41 = r4.addLane(Lane.Direction.IDENTICAL);
    Lane l42 = r4.addLane(Lane.Direction.IDENTICAL);
    Lane l43 = r4.addLane(Lane.Direction.OPPOSITE);
    entryLanes.add(l43);
    Lane l44 = r4.addLane(Lane.Direction.OPPOSITE);
    entryLanes.add(l44);
    
    Road r5 = new Road(new Point(0, 400), new Point(100, 400));
    Lane l51 = r5.addLane(Lane.Direction.IDENTICAL);
    entryLanes.add(l51);
    Lane l52 = r5.addLane(Lane.Direction.IDENTICAL);
    entryLanes.add(l52);
    Lane l53 = r5.addLane(Lane.Direction.OPPOSITE);
    Lane l54 = r5.addLane(Lane.Direction.OPPOSITE);
    
    Road r6 = new Road(new Point(150, 400), new Point(350, 400));
    Lane l61 = r6.addLane(Lane.Direction.IDENTICAL);
    Lane l62 = r6.addLane(Lane.Direction.IDENTICAL);
    Lane l63 = r6.addLane(Lane.Direction.OPPOSITE);
    Lane l64 = r6.addLane(Lane.Direction.OPPOSITE);
    
    Road r7 = new Road(new Point(450, 400), new Point(650, 400));
    Lane l71 = r7.addLane(Lane.Direction.IDENTICAL);
    Lane l72 = r7.addLane(Lane.Direction.IDENTICAL);
    Lane l73 = r7.addLane(Lane.Direction.OPPOSITE);
    Lane l74 = r7.addLane(Lane.Direction.OPPOSITE);
    
    Road r8 = new Road(new Point(700, 400), new Point(800, 400));
    Lane l81 = r8.addLane(Lane.Direction.IDENTICAL);
    Lane l82 = r8.addLane(Lane.Direction.IDENTICAL);
    Lane l83 = r8.addLane(Lane.Direction.OPPOSITE);
    entryLanes.add(l83);
    Lane l84 = r8.addLane(Lane.Direction.OPPOSITE);
    entryLanes.add(l84);
    
    Road r9 = new Road(new Point(150, 0), new Point(150, 100));
    Lane l91 = r9.addLane(Lane.Direction.IDENTICAL);
    entryLanes.add(l91);
    Lane l92 = r9.addLane(Lane.Direction.OPPOSITE);
    
    Road r10 = new Road(new Point(150, 200), new Point(150, 400));
    Lane l101 = r10.addLane(Lane.Direction.IDENTICAL);
    Lane l102 = r10.addLane(Lane.Direction.OPPOSITE);
    
    Road r11 = new Road(new Point(150, 500), new Point(150, 600));
    Lane l111 = r11.addLane(Lane.Direction.IDENTICAL);
    Lane l112 = r11.addLane(Lane.Direction.OPPOSITE);
    entryLanes.add(l112);
    
    Road r12 = new Road(new Point(450,0), new Point(450,100));
    Lane l121 = r12.addLane(Lane.Direction.IDENTICAL);
    entryLanes.add(l121);
    Lane l122 = r12.addLane(Lane.Direction.IDENTICAL);
    entryLanes.add(l122);
    Lane l123 = r12.addLane(Lane.Direction.OPPOSITE);
    Lane l124 = r12.addLane(Lane.Direction.OPPOSITE);
    
    Road r13 = new Road(new Point(450, 200), new Point(450,400));
    Lane l131 = r13.addLane(Lane.Direction.IDENTICAL);
    Lane l132 = r13.addLane(Lane.Direction.IDENTICAL);
    Lane l133 = r13.addLane(Lane.Direction.OPPOSITE);
    Lane l134 = r13.addLane(Lane.Direction.OPPOSITE);
    
    Road r14 = new Road(new Point(450, 500), new Point(450, 600));
    Lane l141 = r14.addLane(Lane.Direction.IDENTICAL);
    Lane l142 = r14.addLane(Lane.Direction.IDENTICAL);
    Lane l143 = r14.addLane(Lane.Direction.OPPOSITE);
    entryLanes.add(l143);
    Lane l144 = r14.addLane(Lane.Direction.OPPOSITE);
    entryLanes.add(l144);
    
    Road r15 = new Road(new Point(700, 0), new Point(700, 100));
    Lane l151 = r15.addLane(Lane.Direction.IDENTICAL);
    entryLanes.add(l151);
    Lane l152 = r15.addLane(Lane.Direction.OPPOSITE);
    
    Road r16 = new Road(new Point(700, 200), new Point(700, 400));
    Lane l161 = r16.addLane(Lane.Direction.IDENTICAL);
    Lane l162 = r16.addLane(Lane.Direction.OPPOSITE);
    
    Road r17 = new Road(new Point(700, 500), new Point(700, 600));
    Lane l171 = r17.addLane(Lane.Direction.IDENTICAL);
    Lane l172 = r17.addLane(Lane.Direction.OPPOSITE);
    entryLanes.add(l172);
    
   TrafficPolicy policy = new TrafficPolicy(peaktime,congestionControl);

    Junction j1 = new TrafficLightJunction(policy);
    j1.connect(l11, l21);
    j1.connect(l11, l92);
    j1.connect(l12, l22);
    j1.connect(l12, l101);
    j1.connect(l23, l13);
    j1.connect(l23, l92);
    j1.connect(l24, l14);
    j1.connect(l24, l101);
    j1.connect(l91, l13);
    j1.connect(l91, l21);
    j1.connect(l91, l101);
    j1.connect(l102, l14);
    j1.connect(l102, l92);
    j1.connect(l102, l22);
    
    
    Junction j2 = new TrafficLightJunction(policy);
    j2.connect(l21, l31);
    j2.connect(l21, l124);
    j2.connect(l21, l131);
    j2.connect(l22, l32);
    j2.connect(l22, l123);
    j2.connect(l22, l132);
    j2.connect(l33, l23);
    j2.connect(l33, l123);
    j2.connect(l33, l132);
    j2.connect(l34, l24);
    j2.connect(l34, l124);
    j2.connect(l34, l131);
    j2.connect(l121, l24);
    j2.connect(l121, l31);
    j2.connect(l121, l131);
    j2.connect(l122, l23);
    j2.connect(l122, l32);
    j2.connect(l122, l132);
    j2.connect(l133, l23);
    j2.connect(l133, l32);
    j2.connect(l133, l123);
    j2.connect(l134, l24);
    j2.connect(l134, l31);
    j2.connect(l134, l124);
            
    Junction j3 = new TrafficLightJunction(policy);
    j3.connect(l31, l41);
    j3.connect(l31, l152);
    j3.connect(l32, l42);
    j3.connect(l32, l161);
    j3.connect(l43, l33);
    j3.connect(l43, l152);
    j3.connect(l44, l34);
    j3.connect(l44, l161);
    j3.connect(l151, l33);
    j3.connect(l151, l41);
    j3.connect(l151, l161);
    j3.connect(l162, l34);
    j3.connect(l162, l152);
    j3.connect(l162, l42);
    
    Junction j4 = new TrafficLightJunction(policy);
    j4.connect(l51, l61);
    j4.connect(l51, l102);
    j4.connect(l52, l62);
    j4.connect(l52, l111);
    j4.connect(l63, l53);
    j4.connect(l63, l102);
    j4.connect(l64, l54);
    j4.connect(l64, l111);
    j4.connect(l101, l61);
    j4.connect(l101, l53);
    j4.connect(l101, l111);
    j4.connect(l112, l54);
    j4.connect(l112, l102);
    j4.connect(l112, l62);
    
    Junction j5 = new TrafficLightJunction(policy);
    j5.connect(l61, l71);
    j5.connect(l61, l134);
    j5.connect(l61, l141);
    j5.connect(l62, l72);
    j5.connect(l62, l133);
    j5.connect(l62, l142);
    j5.connect(l73, l63);
    j5.connect(l73, l133);
    j5.connect(l73, l142);
    j5.connect(l74, l64);
    j5.connect(l74, l134);
    j5.connect(l74, l141);
    j5.connect(l131, l64);
    j5.connect(l131, l71);
    j5.connect(l131, l141);
    j5.connect(l132, l63);
    j5.connect(l132, l72);
    j5.connect(l132, l142);
    j5.connect(l143, l63);
    j5.connect(l143, l72);
    j5.connect(l143, l133);
    j5.connect(l144, l64);
    j5.connect(l144, l71);
    j5.connect(l144, l134);
    
    Junction j6 = new TrafficLightJunction(policy);
    j6.connect(l71, l81);
    j6.connect(l71, l162);
    j6.connect(l72, l82);
    j6.connect(l72, l171);
    j6.connect(l83, l73);
    j6.connect(l83, l162);
    j6.connect(l84, l74);
    j6.connect(l84, l171);
    j6.connect(l161, l73);
    j6.connect(l161, l81);
    j6.connect(l161, l171);
    j6.connect(l172, l74);
    j6.connect(l172, l162);
    j6.connect(l172, l82);
    
    
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
    map.addJunction(j1);
    map.addJunction(j2);
    map.addJunction(j3);
    map.addJunction(j4);
    map.addJunction(j5);
    map.addJunction(j6);

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
