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
public class NormalBus extends Car {
    
    private String type = "Normal Bus";

    public NormalBus(Lane lane) {
        super(lane);
        topSpeed = 5;
        maxAcceleration = 1;
    }
        
    public String getType() {
        return type;
    }
    
}
