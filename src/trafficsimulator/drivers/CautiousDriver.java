/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.drivers;

import trafficsimulator.core.Driver;
import trafficsimulator.vehicles.Bus;
import trafficsimulator.vehicles.Car;

/**
 *
 * @author Eddy
 */
public class CautiousDriver extends Driver {

  public CautiousDriver(String name) {
    super(name);
  }

  @Override
  public double getOptimalDeceleration() {
    if (Car.class.isInstance(vehicle)) {
      return 3;
    } else if (Bus.class.isInstance(vehicle)) {
      return 2;
    } else {
      return 1;
    }
  }

}
