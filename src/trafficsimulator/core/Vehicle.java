/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.core;

import java.util.List;
import java.util.Random;
import trafficsimulator.utils.Point;
import trafficsimulator.utils.Size;

/**
 *
 * @author balazs
 */
public abstract class Vehicle implements ISteppable{

  private Lane lane;
  private Point position;
  private double currentSpeed = 0;
  protected double topSpeed;
  protected double maxAcceleration;
  protected double maxDeceleration;
  protected double optimalDeceleration;
  protected Size size;
  protected Driver driver;
  protected boolean accelerate;
  protected boolean decelerate;

  protected String type = "Vehicle Base Object";
  public long startTime = 0;
  public long endTime = 0;

  public Vehicle(Driver driver) {
    this.currentSpeed = 0;
    if (driver == null) {
      this.driver = NormalDriver("Default Driver");
    } else {
      this.driver = driver;
    }
    this.driver.setVehicle(this);
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
      this.position = lane.getCenterStartPoint();
    }
    this.lane = lane;
    this.lane.enter(this);
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

  private double getDistanceFromEOLane() {

    double distance = getLane().getLeftEndPoint().distance(this.getPosition());
    return distance;
  }

  private void changeSpeed() {
    accelerate = driver.AccelerationStatus(this.currentSpeed, driver.getOptimalFollowingDistance(), getLane().getDistanceFromNextVehicle(this), getDistanceFromEOLane());
    decelerate = driver.DecelerationStatus(this.currentSpeed, driver.getOptimalFollowingDistance(), getLane().getDistanceFromNextVehicle(this), getDistanceFromEOLane());

    if (accelerate) {
      accelerate();
    } else if (decelerate) {
      decelerate();
    } else {
      currentSpeed = currentSpeed;
    }
  }

  private boolean leftRoad(Point oldPosition, Point newPosition) {
    Point endPoint = lane.getCenterEndPoint();
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

  private Lane chooseRandomNewLane() {
    Junction junction = lane.getJunction();
    if (junction == null) {
      return null;
    }
    List<Lane> lanes = junction.getConnectedLanes(lane);
    if (lanes.isEmpty()) {
      return null;
    }
    Random randomGenerator = new Random();
    int index = randomGenerator.nextInt(lanes.size());
    return lanes.get(index);
  }

  private Point getDisplacementVector() {
    Point dir = getLane().getDirectionVector();
    Point unitDir = dir.div(dir.distanceFromOrigin());
    double x = getCurrentSpeed() * Math.cos(unitDir.angleVector());
    double y = getCurrentSpeed() * Math.sin(unitDir.angleVector());
    return new Point(x, y);
  }

  public double timeSpentInSystem() {
    return (endTime - startTime) / 1000;
  }

  public void step(long stepCounter) {
    System.out.print(getType() + " #" + hashCode());

    // Change speed of vehicle
    changeSpeed();

    // Calculate new position
    Point newPosition = position.plus(getDisplacementVector());

    // Check if vehicle has to change lane
    if (leftRoad(this.position, newPosition)) {
      // Move vehicle to random next lane
      Lane newLane = chooseRandomNewLane();
      if (newLane != null) {
        this.lane.exit(this);
        this.position = newLane.getCenterStartPoint();
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

    System.out.println(" position: " + Math.round(position.getX()) + ", " + Math.round(position.getY()) + " speed: " + Math.round(currentSpeed));
  }

  protected void accelerate() {
    double dist = getLane().getDistanceFromNextVehicle(this) - driver.getOptimalFollowingDistance();

    double optimalSpeed = driver.getOptimalSpeedForDistance(dist);

    if (optimalSpeed > getCurrentSpeed()) {
      double speedDifference = optimalSpeed - getCurrentSpeed();
      if (speedDifference < getMaxAcceleration()) {
        setCurrentSpeed(getCurrentSpeed() + speedDifference);
      } else {
        setCurrentSpeed(getCurrentSpeed() + getMaxAcceleration());
      }
    }
  }

  protected void decelerate() {

    double dist = getLane().getDistanceFromNextVehicle(this) - driver.getOptimalFollowingDistance();

    double optimalSpeed = driver.getOptimalSpeedForDistance(dist);

    if (optimalSpeed < getCurrentSpeed()) {
      double speedDifference = getCurrentSpeed() - optimalSpeed;
      if (speedDifference < getMaxDeceleration()) {
        setCurrentSpeed(getCurrentSpeed() - speedDifference);
      } else {
        setCurrentSpeed(getCurrentSpeed() - getMaxDeceleration());
      }
    }
  }

  private Driver NormalDriver(String default_Driver) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
}
