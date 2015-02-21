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
public class NormalCar extends Car {

    public NormalCar(Lane lane) {
        super(lane);
        type = "Normal Car";
        topSpeed = 10;
        maxAcceleration = 2;
    }
}
