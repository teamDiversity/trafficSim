/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.vehicles;

import trafficsimulator.core.Lane;
import trafficsimulator.core.Vehicle;
import trafficsimulator.utils.Point;
import trafficsimulator.utils.Size;
import trafficsimulator.core.Driver;

/**
 *
 * @author snorri
 */
public class Car extends Vehicle {

    public Car(Lane lane, Point position, Driver driver) {
        super(lane, position);
        super.topSpeed = driver.getCarTopSpeed();
        super.maxAcceleration = driver.getBusMaxAcceleration();
        super.maxDeceleration = 4;
        super.optimalDeceleration = 3;
        super.size = new Size(14, 8);
    }

    @Override
    public String getType() {
        
        return "Car";
        }
    
}
