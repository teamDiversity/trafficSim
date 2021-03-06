/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.core;

import java.util.List;
import java.util.Random;
import trafficsimulator.drivers.NormalDriver;
import trafficsimulator.utils.Point;
import trafficsimulator.utils.Size;

/**
 *
 * @author balazs
 */
public abstract class Vehicle implements ISteppable {

  private Lane lane;
  private Lane nextLane;
  private Point position;
  private double currentSpeed = 0;
  private double acceleration = 0;
  protected double topSpeed;
  protected double maxAcceleration;
  protected double maxDeceleration;
  protected Size size;
  protected Driver driver;

  protected String type = "Vehicle Base Object";
  public long startTime = 0;
  public long endTime = 0;

  public Vehicle(Driver driver) {
    this.currentSpeed = 0;
    if (driver == null) {
      this.driver = new NormalDriver("Default Driver");
    } else {
      this.driver = driver;
    }
    this.driver.setVehicle(this);
  }

  public Driver getDriver() {
    return driver;
  }

  public Size getSize() {
    return size;
  }

  public double getTopSpeed() {
    return topSpeed;
  }

  public double getMaxAcceleration() {
    return maxAcceleration;
  }

  public double getMaxDeceleration() {
    return maxDeceleration;
  }

  public String getType() {
    return type;
  }

  public Point getPosition() {
    return position;
  }

  public Lane getLane() {
    return lane;
  }

  public boolean isInSystem() {
    return lane != null;
  }

  public void setLane(Lane lane) {
    if (lane == null) {
      this.lane = null;
      return;
    }
    if (!isInSystem()) {
      this.position = lane.getStartPoint();
    }
    this.lane = lane;
    this.lane.enter(this);
    setNextLane(this.lane.getNextLane());
  }

  public double getCurrentSpeed() {
    return currentSpeed;
  }

  private void setCurrentSpeed(double speed) {
    if (speed > getTopSpeed()) {
      currentSpeed = getTopSpeed();
    } else if (speed < 0) {
      currentSpeed = 0;
    } else {
      currentSpeed = speed;
    }
  }

  public double getDistanceFromEndOfLane() {
    double distance = getLane().getEndPoint().distance(this.getPosition());
    return distance;
  }

  private boolean leftRoad(Point oldPosition, Point newPosition) {
    Point endPoint = lane.getEndPoint();
    if (oldPosition.getX() <= endPoint.getX() && newPosition.getX() > endPoint.getX()) {
      return true;
    }
    if (oldPosition.getX() >= endPoint.getX() && newPosition.getX() < endPoint.getX()) {
      return true;
    }
    if (oldPosition.getY() <= endPoint.getY() && newPosition.getY() > endPoint.getY()) {
      return true;
    }
    if (oldPosition.getY() >= endPoint.getY() && newPosition.getY() < endPoint.getY()) {
      return true;
    }
    return false;
  }
  
  private void setNextLane(Lane nextLane) {
    this.nextLane = nextLane;
  }
  
  private Lane getNextLane() {
    return nextLane;
  }

  public Point getDirectionVector() {
    Point dir = getLane().getDirectionVector();
    return dir.unitVector();
  }

  private Point getDisplacementVector() {
    double angleVector = getDirectionVector().angleVector();
    double x = (getCurrentSpeed() + acceleration / 2) * Math.cos(angleVector);
    double y = (getCurrentSpeed() + acceleration / 2) * Math.sin(angleVector);
    return new Point(x, y);
  }

  public double timeSpentInSystem() {
    return (endTime - startTime) / 1000;
  }

  @Override
  public void step(long stepCounter) {
    if (!isInSystem()) {
      return;
    }

    System.out.print(getType() + " #" + hashCode());

    // Calculate new position
    Point newPosition = position.plus(getDisplacementVector());
    setCurrentSpeed(getCurrentSpeed() + acceleration);

    // Check if vehicle has to change lane
    if (leftRoad(this.position, newPosition)) {
      // Move vehicle to random next lane
      Lane newLane = nextLane;
      if (newLane != null) {
        this.lane.exit(this);
        this.position = newLane.getStartPoint();
        this.setLane(newLane);
      } else {
        this.lane.exit(this);
        this.lane.getExitPoint().addVehicle(this);
        this.setLane(null);
      }
    } else {
      //Move vehicle
      position = newPosition;
    }

    //System.out.println(" position: " + Math.round(position.getX()) + ", " + Math.round(position.getY()) + " speed: " + Math.round(currentSpeed));
  }

  public void changeSpeed(double speedDelta) {
    if (speedDelta > getMaxAcceleration()) {
      speedDelta = getMaxAcceleration();
    }
    if (speedDelta < 0 - getMaxDeceleration()) {
      speedDelta = 0 - getMaxDeceleration();
    }

    acceleration = speedDelta;
  }

}
