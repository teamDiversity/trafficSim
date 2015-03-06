/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.core;

/**
 *
 * @author Eddy
 */
public abstract class Driver {

  protected String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean AccelerationStatus(double currentSpeed, double optimalFollowingDist, double distanceFromNextVechicle, double distanceFromEOLane) {
    boolean choice;
    //no car ahead
    if (distanceFromEOLane == Double.MAX_VALUE) {
      choice = true;
    }
    if (distanceFromNextVechicle <= optimalFollowingDist) {
      choice = false;
    } else {
      choice = true;
    }

    return choice;
  }

  public boolean DecelerationStatus(double currentSpeed, double optimalFollowingDist, double distanceFromNextVechicle, double distanceFromEOLane) {
    boolean choice;
    if (distanceFromEOLane == Double.MAX_VALUE) {
      //This will depend on the state of the traffic light
    }
    if (distanceFromNextVechicle <= optimalFollowingDist) {
      choice = true;
    } else {
      choice = false;
    }

    return choice;

  }

}
