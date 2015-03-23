/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import trafficsimulator.core.Junction;
import trafficsimulator.core.Lane;
import trafficsimulator.core.Road;
import trafficsimulator.junctions.TrafficLight;
import trafficsimulator.junctions.TrafficLightJunction;
import trafficsimulator.utils.Point;
import trafficsimulator.utils.PointCWComparator;

/**
 *
 * @author balazs
 */
public class JunctionRenderer implements IRenderer {

  private Junction junction;
  private GraphicsContext gc;

  public JunctionRenderer(GraphicsContext gc, Junction junction) {
    this.gc = gc;
    this.junction = junction;
  }
  
  private class RoadCWComparator implements Comparator<Road> {

    private final Junction junction;

    public RoadCWComparator(Junction junction) {
      this.junction = junction;
    }

    @Override
    public int compare(Road o1, Road o2) {
      List<Point> cPoints1 = junction.getPointsForRoad(o1);
      List<Point> cPoints2 = junction.getPointsForRoad(o2);

      Point cPoint1 = Point.centroid(cPoints1);
      Point cPoint2 = Point.centroid(cPoints2);

      PointCWComparator comparator = new PointCWComparator(junction.getCenterPoint());
      return comparator.compare(cPoint1, cPoint2);
    }

  }

  @Override
  public void render() {
    List<Road> roads = junction.getRoads();
    Collections.sort(roads, new RoadCWComparator(junction));

    List<Point> points = new ArrayList<>();
    for (Road road : roads) {
      points.add(junction.getPointsForRoad(road).get(0));
      points.add(junction.getPointsForRoad(road).get(1));
    }

    double[] xPoints = new double[points.size()];
    double[] yPoints = new double[points.size()];
    for (int i = 0; i < points.size(); i++) {
      xPoints[i] = points.get(i).getX();
      yPoints[i] = points.get(i).getY();
    }
    gc.setFill(Color.GRAY);
    gc.fillPolygon(xPoints, yPoints, points.size());

    for (Lane lane : junction.getLanes()) {
      gc.setLineWidth(1);
      if (lane.getDirection() == Lane.Direction.IDENTICAL) {
        gc.setStroke(Color.RED);
      } else {
        gc.setStroke(Color.YELLOW);
      }
      gc.strokeLine(lane.getStartPoint().x, lane.getStartPoint().y, lane.getEndPoint().x, lane.getEndPoint().y);
    }
    
    renderTrafficLights();
  }
  
  private void renderTrafficLights(){
    if(junction instanceof TrafficLightJunction){
      TrafficLightJunction trafficLightJunction = (TrafficLightJunction)junction;
      List<TrafficLight> lights = trafficLightJunction.getTrafficLights();
      for(TrafficLight light : lights){
        Point pos = light.getPosition();
        if(light.getState() == TrafficLight.State.GREEN){
          gc.setFill(Color.GREEN);
        }else if(light.getState() == TrafficLight.State.RED){
          gc.setFill(Color.RED);
        }else if(light.getState() == TrafficLight.State.REDYELLOW){
          gc.setFill(Color.YELLOW);
        }else if(light.getState() == TrafficLight.State.YELLOW){
          gc.setFill(Color.YELLOW);
        }
        gc.fillOval(pos.getX()-5, pos.getY()-5, 10, 10);
      }
    }
  }

}
