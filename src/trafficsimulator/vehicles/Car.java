/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.vehicles;

import trafficsimulator.core.Lane;
import trafficsimulator.core.Vehicle;
import trafficsimulator.utils.Size;
import trafficsimulator.core.Driver;

/**
 *
 * @author snorri
 */
public class Car extends Vehicle {

  public Car() {
    this(null);
  }

  public Car(Driver driver) {
    super(driver);
    topSpeed = 10;
    maxAcceleration = 2;
    maxDeceleration = 4;
    optimalDeceleration = 3;
    size = new Size(14, 8);
  }

  @Override
  public String getType() {
    return "Car";
  }

}
