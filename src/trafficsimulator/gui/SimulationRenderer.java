/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import trafficsimulator.core.Junction;
import trafficsimulator.core.Lane;

import trafficsimulator.core.Lane.Direction;
import trafficsimulator.core.Road;
import trafficsimulator.core.Simulation;
import trafficsimulator.core.Vehicle;
import trafficsimulator.utils.Point;
import trafficsimulator.vehicles.Bus;
import trafficsimulator.vehicles.Car;

/**
 *
 * @author yukolthep
 */
public class SimulationRenderer implements IRenderer {

  private Stage stage;
  private Simulation simulation;
  private GraphicsContext gc;

  Image car_image = new Image("pic/car_tran.gif", 20, 0, true, false);
  Image car = new Image("pic/car.jpg");
  Image bus = new Image("pic/bus.jpg");

  public SimulationRenderer(GraphicsContext gc, Simulation simulation) {
    this.stage = stage;
    this.simulation = simulation;
    this.gc = gc;
  }

  public void render() {
    Platform.runLater(new Runnable() {

      @Override
      public void run() {
        clear();
        drawRoads();
        drawLanes();
        drawJunctions();
        drawVehicles();
      }
    });

  }
  
  /*Clear canvas before painting updated components*/
  private void clear() {
    gc.clearRect(0, 0, 700, 700);
  }

  private void drawRoads() {
    List<Road> roads = this.simulation.getMap().getRoads();
    for (Road road : roads) {
      Point leftStartPoint = road.getLeftStartPoint();
      Point rightStartPoint = road.getRightStartPoint();
      Point leftEndPoint = road.getLeftEndPoint();
      Point rightEndPoint = road.getRightEndPoint();
      gc.setFill(Color.GRAY);
      gc.fillPolygon(new double[] {leftStartPoint.getX(),leftEndPoint.getX(),rightEndPoint.getX(),rightStartPoint.getX()}, new double[] {leftStartPoint.getY(), leftEndPoint.getY(), rightEndPoint.getY(), rightStartPoint.getY()}, 4);
    }
  }
  
  private void drawLanes(){
    List<Road> roads = this.simulation.getMap().getRoads();
    for (Road road : roads) {
      for(Lane lane : road.getLanes()){
        gc.setLineWidth(1);
        if(lane.getDirection() == Lane.Direction.IDENTICAL){
          gc.setStroke(Color.RED);
        }else{
          gc.setStroke(Color.YELLOW);
        }
        gc.strokeLine(lane.getStartPoint().x, lane.getStartPoint().y, lane.getEndPoint().x, lane.getEndPoint().y); 
      }
    }
  }
  
  
  
  
  class PointCWComparator implements Comparator<Point>{

    private final Point center;
    
    public PointCWComparator(Point center){
      this.center = center;
    }

    private boolean less(Point a, Point b) {
      if (a.x - center.x >= 0 && b.x - center.x < 0)
          return true;
      if (a.x - center.x < 0 && b.x - center.x >= 0)
          return false;
      if (a.x - center.x == 0 && b.x - center.x == 0) {
          if (a.y - center.y >= 0 || b.y - center.y >= 0)
              return a.y > b.y;
          return b.y > a.y;
      }

      // compute the cross product of vectors (center -> a) x (center -> b)
      double det = (a.x - center.x) * (b.y - center.y) - (b.x - center.x) * (a.y - center.y);
      if (det < 0)
          return true;
      if (det > 0)
          return false;

      // points a and b are on the same line from the center
      // check which point is closer to the center
      double d1 = (a.x - center.x) * (a.x - center.x) + (a.y - center.y) * (a.y - center.y);
      double d2 = (b.x - center.x) * (b.x - center.x) + (b.y - center.y) * (b.y - center.y);
      return d1 > d2;
    }

    @Override
    public int compare(Point o1, Point o2) {
      if(less(o1,o2)) return -1;
      return 1;
    }
  }
  
  class RoadCWComparator implements Comparator<Road>{
    private final Junction junction;

    public RoadCWComparator(Junction junction){
      this.junction = junction;
    }
    
    @Override
    public int compare(Road o1, Road o2) {
      List<Point> cPoints1 = junction.getPointsForRoad(o1);
      List<Point> cPoints2 = junction.getPointsForRoad(o1);
      
      Point cPoint1 = Point.centroid(cPoints1);
      Point cPoint2 = Point.centroid(cPoints2);
      
      PointCWComparator comparator = new PointCWComparator(junction.getCenterPoint());
      return comparator.compare(cPoint1, cPoint2);
    }
  
  }
  
  private void drawJunctions(){
    List<Junction> junctions = this.simulation.getMap().getJunctions();
    for(Junction junction: junctions){
      
      System.out.println("Rendering junction");
      System.out.println(junction.getCenterPoint());
      
      List<Road> roads = junction.getRoads();
      for(Road road:roads){
        System.out.println(Point.centroid(junction.getPointsForRoad(road)));
      }
      Collections.sort(roads, new RoadCWComparator(junction));
      for(Road road:roads){
        System.out.println(Point.centroid(junction.getPointsForRoad(road)));
      }
      
      List<Point> points = new ArrayList<Point>();
      for(Road road:roads){
        points.add(junction.getPointsForRoad(road).get(0));
        points.add(junction.getPointsForRoad(road).get(1));
      }
      
      double[] xPoints = new double[points.size()];
      double[] yPoints = new double[points.size()];
      for(int i=0; i<points.size(); i++){
        xPoints[i]=points.get(i).getX();
        yPoints[i]=points.get(i).getY();
      }
      gc.fillPolygon(xPoints, yPoints, points.size());
      
      for(Lane lane: junction.getLanes()){
        gc.setLineWidth(1);
        if(lane.getDirection() == Lane.Direction.IDENTICAL){
          gc.setStroke(Color.RED);
        }else{
          gc.setStroke(Color.YELLOW);
        }
        gc.strokeLine(lane.getStartPoint().x, lane.getStartPoint().y, lane.getEndPoint().x, lane.getEndPoint().y);
      }
    }
  }

  private void drawVehicles() {
    List<Vehicle> vehicles = this.simulation.getVehicles();
    for (Vehicle vehicle : vehicles) {
      if (Car.class.isInstance(vehicle)) {
        Double angle = vehicle.getDisplacementVector().angleVectorDegree();
        drawRotatedImage(gc, car, angle, (vehicle.getPosition().getX() - car.getWidth() / 2), (vehicle.getPosition().getY() - car.getHeight() / 2));
      }else if (Bus.class.isInstance(vehicle)) {
        Double angle = vehicle.getDisplacementVector().angleVectorDegree();
        drawRotatedImage(gc, bus, angle, (vehicle.getPosition().getX() - bus.getWidth() / 2), (vehicle.getPosition().getY() - bus.getHeight() / 2));
      }
    }
  }
 

  private void rotate(GraphicsContext gc, double angle, double px, double py) {
    Rotate r = new Rotate(angle, px, py);
    gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
  }

  private void drawRotatedImage(GraphicsContext gc, Image image, double angle, double tlpx, double tlpy) {
    gc.save(); // saves the current state on stack, including the current transform
    rotate(gc, angle, tlpx + image.getWidth() / 2, tlpy + image.getHeight() / 2);
    gc.drawImage(image, tlpx, tlpy);
    gc.restore(); // back to original state (before rotation)
  }
}
