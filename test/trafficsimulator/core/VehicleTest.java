/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.core;

import org.junit.Test;
import static org.junit.Assert.*;

import trafficsimulator.utils.Point;
import trafficsimulator.utils.Size;
import trafficsimulator.vehicles.*;

/**
 *
 * @author snorri
 * This class will be changing a lot. After the changes more thorough testing
 * will be done.
 */
public class VehicleTest {
  
    
       
  /**
   * Test the height of a reckless car.
   */
  @Test
  public void testHeightRecklessCar() {
    System.out.println("Height of a reckless car");
        
    Lane lane = new Lane(Lane.Direction.IDENTICAL);
    Vehicle recklessCar = new RecklessCar();
        
    Size expResult = new Size(14, 8);
    Size result = recklessCar.getSize();
        
    assertEquals(expResult.height, result.height, 0.001);
    }
    
  /**
   * Test the height of a reckless bus.
   */
  @Test
  public void testHeightRecklessBus() {
    System.out.println("Height of a reckless bus");
    
    Lane lane = new Lane(Lane.Direction.IDENTICAL);
    Vehicle recklessBus = new RecklessBus();
        
    Size expResult = new Size(20, 10);
    Size result = recklessBus.getSize();
        
    assertEquals(expResult.height, result.height, 0.001);
  }
    
  /**
   * Test if a reckless bus moves
   */
  @Test
  public void testRecklessBusMovement() {
    System.out.println("Movement of a reckless bus");
        
    Road road = new Road(new Point(20, 20), new Point(500, 20));
    Lane lane = new Lane(Lane.Direction.IDENTICAL);
    road.addLane(lane);
    Vehicle recklessBus = new RecklessBus();
        
    double initialPos = recklessBus.getPosition().getX();
    recklessBus.step();
    double finalPos = recklessBus.getPosition().getX();
              
    assertTrue(finalPos > initialPos);
  }
    
  /**
   * Test vehicle outside of road boundaries
   */
  @Test
  public void testRecklessBusOutsideRoad1() {
    System.out.println("Creation of vehicle outside of Road parameter");
        
    Road road = new Road(new Point(20, 20), new Point(500, 20));
    Lane lane = new Lane(Lane.Direction.IDENTICAL);
    road.addLane(lane);
    Vehicle recklessBus = new RecklessBus();
        
    double roadStartX = road.getLeftStartPoint().getX();
    double roadStartY = road.getLeftStartPoint().getY();
    double roadEndX = road.getLeftEndPoint().getX();
    double roadEndY = road.getLeftEndPoint().getY();
    double recklessBusX = recklessBus.getPosition().getX();
    double recklessBusY = recklessBus.getPosition().getY();
        
    assertTrue((recklessBusX >= roadStartX && recklessBusX <= roadEndX) ||
               (recklessBusY >= roadStartY && recklessBusY <= roadEndY));
    }
    
  @Test
  public void testRecklessBusOutsideRoad2() {
    System.out.println("Movement of a vehicle outside of road");
        
    
    final Road road = new Road(new Point(20, 20), new Point(500, 20));
    final Lane lane = new Lane(Lane.Direction.IDENTICAL);
    road.addLane(lane);
    final Vehicle recklessBus = new RecklessBus();
    
    Simulation s = new Simulation() {

      @Override
      protected void init() {
        map.addRoad(road);
        addVehicle(recklessBus, lane, 1);
      }
    };
    
    double roadStartX = road.getLeftStartPoint().getX();
    double roadStartY = road.getLeftStartPoint().getY();
    double roadEndX = road.getLeftEndPoint().getX();
    double roadEndY = road.getLeftEndPoint().getY();
    double recklessBusX = recklessBus.getPosition().getX();
    double recklessBusY = recklessBus.getPosition().getY();
        
    assertTrue((recklessBusX >= roadStartX && recklessBusX <= roadEndX) ||
                 (recklessBusY >= roadStartY && recklessBusY <= roadEndY));
  }  
}
