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
public class Car extends Vehicle{
    final double CAR_WIDTH;
    final double CAR_HEIGHT;
    public Car(Lane lane, Point position) {
        super(lane, position);
        CAR_WIDTH = 15.0;
        CAR_HEIGHT = 10.0;
        this.setSpeed(2);
    }
}
