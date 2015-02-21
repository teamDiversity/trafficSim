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
public class RecklessBus extends Car {
    
    private String type = "Reckless Bus";

    public RecklessBus(Lane lane) {
        super(lane);
        topSpeed = 10;
        maxAcceleration = 2;
    }
        
    public String getType() {
        return type;
    }
    
}
