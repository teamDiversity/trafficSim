/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.drivers;

import trafficsimulator.core.Driver;

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
    return 0.5 * vehicle.getMaxDeceleration();
  }

  @Override
  public double getOptimalAcceleration() {
    return 0.5 * vehicle.getMaxAcceleration();
  }

}
