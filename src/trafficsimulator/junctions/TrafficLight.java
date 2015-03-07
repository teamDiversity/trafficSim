/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.junctions;

import trafficsimulator.core.Lane;

/**
 *
 * @author balazs
 */
public class TrafficLight {
  public enum State {

    GREEN, YELLOW, RED
  }
  
  private State state = State.RED;
  private Lane lane;
  
  public TrafficLight(Lane lane){
    this.lane = lane;
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
  
}
