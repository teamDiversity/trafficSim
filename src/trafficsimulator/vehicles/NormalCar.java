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
public class NormalCar extends Car {

    public NormalCar() {
        super();
        type = "Normal Car";
        topSpeed = 10;
        maxAcceleration = 2;
    }
}
