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
public class NormalDriver extends Driver {

    public NormalDriver(String name) {
        
        super.name = name;
        super.carTopSpeed = 10;
        super.carMaxAcceleration = 2;
        super.busTopSpeed = 5;
        super.busMaxAcceleration = 1;
    }
    
    
}
