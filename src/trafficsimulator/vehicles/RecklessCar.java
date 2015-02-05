/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.vehicles;

import trafficsimulator.core.Lane;
import trafficsimulator.utils.Point;

/**
 *
 * @author snorri
 */
public class RecklessCar extends Car {
    private int maxAcceleration = 3;
    private String type = "Reckless Car";

    public RecklessCar(Lane lane, Point position) {
        super(lane, position);
        topSpeed = 15;
    }
    
    public int getMaxAcceleration() {
        return maxAcceleration;
    }
    
    public void setMaxAcceleration(int maxAcceleration) {
        this.maxAcceleration = maxAcceleration;
    }
    
    public String getType() {
        return type;
    }
    
}