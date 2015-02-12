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
 */
public class VehicleTest {
    
    public VehicleTest() {
    }
    
    /**
     * Test the height of a reckless car.
     */
    @Test
    public void testHeightRecklessCar() {
        System.out.println("Height of a reckless car");
        
        Lane lane = new Lane(Lane.Direction.IDENTICAL);
        Vehicle recklessCar = new RecklessCar(lane, new Point(0,0));
        
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
        Vehicle recklessBus = new RecklessBus(lane, new Point(0,0));
        
        Size expResult = new Size(20, 10);
        Size result = recklessBus.getSize();
        
        assertEquals(expResult.height, result.height, 0.001);
    }
    

    
}
