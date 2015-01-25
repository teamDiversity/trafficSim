/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.core;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author balazs
 */
public class Map {
  private List<Road> roads;
  private List<Junction> junctions;
  
  public Map(){
    roads = new ArrayList<>();
    junctions = new ArrayList<>();
  }

  public List<Road> getRoads() {
    return roads;
  }

  public void addRoad(Road road) {
    roads.add(road);
  }

  public List<Junction> getJunctions() {
    return junctions;
  }

  public void addJunction(Junction junction) {
    junctions.add(junction);
  }
  
  
}
