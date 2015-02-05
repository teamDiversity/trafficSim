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
public abstract class Bus extends Vehicle {
    
    private int maxDeceleration = 3;
    private int optimalDeceleration = 2;
    private final int size = 10;

    public Bus(Lane lane, Point position) {
        super(lane, position);
    }
    
    public abstract int getTopSpeed();
    public abstract void setTopSpeed(int topSpeed);
    public abstract int getMaxAcceleration();
    public abstract void setMaxAcceleration(int maxAcceleration);
    public abstract String getType();

    
    public int getMaxDeceleration() {
        return maxDeceleration;
    }
    
    public void setMaxDeceleration(int maxDeceleration) {
        this.maxDeceleration = maxDeceleration;
    }
    
    public int getOptimalDeceleration() {
        return optimalDeceleration;
    }
    
    public void setOptimalDeceleration(int optimalDeceleration) {
        this.optimalDeceleration = optimalDeceleration;
    }
    
    public int getSize() {
        return size;
    }
    
   
}
