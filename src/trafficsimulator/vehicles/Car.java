/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.vehicles;

import trafficsimulator.core.Lane;
import trafficsimulator.core.Vehicle;
import trafficsimulator.utils.Point;

/**
 *
 * @author snorri
 */
public abstract class Car extends Vehicle {
    
    private final int size = 5;

    public Car(Lane lane, Point position) {
        super(lane, position);
        maxDeceleration = 4;
        optimalDeceleration = 3;
    }
    
    public abstract String getType();

        
    public int getSize() {
        return size;
    }
    
   
}
