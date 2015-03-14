/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.simulations;

import trafficsimulator.core.Simulation;

/**
 *
 * @author snorri
 */
public class SimulationChosen extends Simulation {
  private String selectedMap;
  Simulation sim1 = new Simulation1();
  Simulation sim2 = new Simulation2();
  Simulation sim3 = new Simulation3();
  
  
  
  public SimulationChosen(String selectedMap) {
    this.selectedMap = selectedMap;
  }

  @Override
  protected void init() {
    /*
    switch (selectedMap) {
      case "Map 1":
        sim1.init();
        break;
      case "Map 2":
        sim2.init();
        break;
      case "Map 3":
        sim3.init();
        break;
      default:
        sim1.init();
        break;
    }
    */
  }
  
  
}
