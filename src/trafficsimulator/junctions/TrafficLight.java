/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.junctions;

import trafficsimulator.core.Lane;
import trafficsimulator.policies.Lights;
import trafficsimulator.policies.TrafficPolicy;
import trafficsimulator.utils.Point;

/**
 *
 * @author balazs
 */
public class TrafficLight {

//  public static final int GREEN_DURATION = 10;
//  public static final int YELLOW_DURATION = 2;
//  public static final int RED_DURATION = 10;
  public enum State {

    GREEN, YELLOW, RED, REDYELLOW
  }
  private Lights lights;
  private State state = State.RED;
  private Lane lane;

  public TrafficLight(Lane lane, TrafficPolicy policy) {
    this.lane = lane;
    this.lights = policy.getActiveConfigFromActivePolicy();
  }

  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }

  public Lane getLane() {
    return lane;
  }

  public Lights getLights() {
    return lights;
  }

  public Point getPosition() {
    return lane.getEndPoint();
  }

  public void nextState() {
    switch (state) {
      case GREEN:
        setState(State.YELLOW);
        break;
      case YELLOW:
        setState(State.RED);
        break;
      case RED:
        setState(State.REDYELLOW);
        break;
      case REDYELLOW:
        setState(State.GREEN);
        break;
    }
  }

}
