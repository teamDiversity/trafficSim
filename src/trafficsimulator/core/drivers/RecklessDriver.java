/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.core.drivers;

import trafficsimulator.core.Driver;

/**
 *
 * @author Eddy
 */
public class RecklessDriver extends Driver{

    public RecklessDriver(String name) {
        
        super.name = name;
        super.carTopSpeed = 15;
        super.carMaxAcceleration = 3;
        super.busTopSpeed = 10;
        super.busMaxAcceleration = 2;
    }
    
    
    
}
