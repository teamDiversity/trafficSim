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
        drawGrass();
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
      gc.fillPolygon(new double[]{leftStartPoint.getX(), leftEndPoint.getX(), rightEndPoint.getX(), rightStartPoint.getX()}, new double[]{leftStartPoint.getY(), leftEndPoint.getY(), rightEndPoint.getY(), rightStartPoint.getY()}, 4);
    }
  }

  private void drawLanes() {
    List<Road> roads = this.simulation.getMap().getRoads();
    for (Road road : roads) {
      for (Lane lane : road.getLanes()) {
        gc.setLineWidth(1);
        if (lane.getDirection() == Lane.Direction.IDENTICAL) {
          gc.setStroke(Color.RED);
        } else {
          gc.setStroke(Color.YELLOW);
        }
        gc.strokeLine(lane.getStartPoint().x, lane.getStartPoint().y, lane.getEndPoint().x, lane.getEndPoint().y);
      }
    }
  }

  private void drawJunctions() {
    List<Junction> junctions = this.simulation.getMap().getJunctions();
    
    for (Junction junction : junctions) {

      JunctionRenderer renderer = new JunctionRenderer(gc, junction);
      renderer.render();
      
    }
  }

  private void drawVehicles() {
    List<Vehicle> vehicles = this.simulation.getVehicles();
    for (Vehicle vehicle : vehicles) {
      if (Car.class.isInstance(vehicle)) {
        Double angle = vehicle.getLane().getDirectionVector().angleVectorDegree();
        drawRotatedImage(gc, car, angle, (vehicle.getPosition().getX() - car.getWidth() / 2), (vehicle.getPosition().getY() - car.getHeight() / 2));
      } else if (Bus.class.isInstance(vehicle)) {
        Double angle = vehicle.getLane().getDirectionVector().angleVectorDegree();
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
  
  private void drawGrass(){
      gc.setFill(Color.GREEN);
      gc.fillRect(0, 0, 800, 600);
  }
}
