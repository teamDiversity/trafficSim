/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.core;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import trafficsimulator.utils.Point;
import trafficsimulator.vehicles.*;

/**
 *
 * @author snorri
 */
public class RoadSystemTest {
  private Lane lane1;
  private Lane lane2;
  
  @Before
  public void RoadSystemSetUp() {
    
    Road r1 = new Road(new Point(20, 20), new Point(500, 20));
    lane1 = new Lane(Lane.Direction.IDENTICAL);
    lane2 = new Lane(Lane.Direction.IDENTICAL);
    r1.addLane(lane1);
    r1.addLane(lane2);

  }

  /**
   * This test is supposed to check if it is possible to 
   * create lanes on top of each other. This test is not complete.
   */
  @Test
  public void testLanesOnTopOfEachOther() {
    System.out.println("Lanes on top of each other");
    
    assertTrue(lane1.getDirection() == lane2.getDirection() &&
               lane1.getRoad() == lane2.getRoad());
    
  }
  
  /**
   * Test whether a lane can connect to itself at a junction.
   * This test is not complete
   */
  @Test
  public void testLanesJunction1() {
    System.out.println("Opposite lanes at a junction");

    Junction junction = new Junction();
    junction.connect(lane1, lane1);

    fail(); // We shouldn't be able to get to this point
    
  }

  
}
