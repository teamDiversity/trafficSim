/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.vehicles;

import trafficsimulator.core.Lane;
import trafficsimulator.core.Vehicle;
import trafficsimulator.utils.Size;

/**
 *
 * @author snorri
 */
public abstract class Car extends Vehicle {
    
    public Car() {
        super();
        maxDeceleration = 4;
        optimalDeceleration = 3;
        size = new Size(14, 8);
    }
}
