/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.vehicles;

import trafficsimulator.core.Driver;
import trafficsimulator.core.Lane;
import trafficsimulator.core.Vehicle;
import trafficsimulator.utils.Point;
import trafficsimulator.utils.Size;

/**
 *
 * @author snorri
 */
public class Bus extends Vehicle {
   

    public Bus(Lane lane, Point position, Driver driver) {
        super(lane, position);
        super.topSpeed = driver.getBusTopSpeed();
        super.maxAcceleration = driver.getBusMaxAcceleration();
        maxDeceleration = 3;
        optimalDeceleration = 2;
        size = new Size(20, 10);
    }

    @Override
    public String getType() {
        
    return "Bus";
    }
    
    
  
}
