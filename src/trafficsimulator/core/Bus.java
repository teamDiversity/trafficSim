/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.core;

import trafficsimulator.utils.Point;

/**
 *
 * @author yukolthep
 */
public class Bus extends Vehicle{
    final double BUS_WIDTH;
    final double BUS_HEIGHT;
    public Bus(Lane lane, Point position) {
        super(lane, position);
        this.setSpeed(1);
        BUS_WIDTH = 25.0;
        BUS_HEIGHT = 10.0;
        
    }
    
    
}
