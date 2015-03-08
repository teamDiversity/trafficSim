/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.gui;

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
      List<Lane> lanes = road.getLanes();
      for (int index = 0 ; index < lanes.size()-1 ; index++) {
        Lane lane = lanes.get(index);
        Point leftStartPoint = lane.getLeftStartPoint();
        Point leftEndPoint = lane.getLeftEndPoint();
        Point rightStartPoint = lane.getRightStartPoint();
        Point rightEndPoint = lane.getRightEndPoint();
        gc.setLineWidth(1);
        gc.setStroke(Color.BLACK);
        if(index == lanes.size()-1){
          break;
        }
        if(lane.getDirection() == Direction.IDENTICAL){
          gc.strokeLine(rightStartPoint.getX(), rightStartPoint.getY(), rightEndPoint.getX(), rightEndPoint.getY());
        }else{
          gc.strokeLine(leftStartPoint.getX(), leftStartPoint.getY(), leftEndPoint.getX(), leftEndPoint.getY());
        }  
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
