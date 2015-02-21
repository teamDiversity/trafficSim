/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.vehicles;

import trafficsimulator.core.Lane;

/**
 *
 * @author snorri
 */
public class RecklessBus extends Car {

    public RecklessBus() {
        super();
        type = "Reckless Bus";
        topSpeed = 10;
        maxAcceleration = 2;
    }
}
