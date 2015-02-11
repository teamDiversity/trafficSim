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

/**
 *
 * @author yukolthep
 */
public class SimulationRenderer implements IRenderer{
    
  private Stage stage;
  private Simulation simulation;
  private GraphicsContext gc;
  
  Image car_image = new Image("pic/car_tran.gif",20,0,true,false);
  
  public SimulationRenderer(GraphicsContext gc, Simulation simulation){
    this.stage = stage;
    this.simulation = simulation;
    this.gc = gc;
  }
  
  public void render(){
    Platform.runLater(new Runnable() {

      @Override
      public void run() {
        clear();
        drawRoads();
        drawVehicles();
      }
    });
    
  }
  
  private void clear(){
    gc.clearRect(0, 0, 700, 700);
  }

  private void drawRoads(){
    gc.setLineWidth(5);

    List<Road> roads = this.simulation.getMap().getRoads();
    for(Road road : roads){
      gc.strokeLine(road.getStartPoint().getX(), road.getStartPoint().getY(), road.getEndPoint().getX(), road.getEndPoint().getY());
    }
  }
  
  private void drawVehicles(){
    List<Vehicle> vehicles = this.simulation.getVehicles();
    for(Vehicle vehicle : vehicles){
    
      String dir = "IDENTICAL";
      if(dir.equalsIgnoreCase(vehicle.getLane().getDirection().name())){
          /*do nothing*/
      }else{
          dir = "OPPOSITE";
      }

      if(dir.equals("IDENTICAL")){
        //hv to calculate road's angle beforehand (compare to 0deg line)
        double theta = calcTheta(vehicle.getLane());
        if(theta > 0 && theta < 90){
            theta = 180 - theta;
        }else{
            theta = 360 - theta;
        }
        drawRotatedImage(gc, car_image, theta, (vehicle.getPosition().getX()-car_image.getWidth()/2)+50, (vehicle.getPosition().getY()-car_image.getHeight()/2)+50);
      }else{
        double theta = calcTheta(vehicle.getLane());
        if(theta > 0 && theta < 90){
            theta = theta - 90;
        }else{
            theta = 180 - theta;
        }
        drawRotatedImage(gc, car_image, theta, (vehicle.getPosition().getX()-car_image.getWidth()/2)+50, (vehicle.getPosition().getY()-car_image.getHeight()/2)+50);
      }
    }

  }
    
  private double calcTheta(Lane l){
      double l_start_x = l.getStartPoint().getX();
      double l_start_y = l.getStartPoint().getY();
      double l_end_x = l.getEndPoint().getX();
      double l_end_y = l.getEndPoint().getY();
      double value = ((Math.abs(l_end_y - l_start_y))/Math.sqrt(Math.pow((l_end_x - l_start_x), 2)+(Math.pow((l_end_y - l_start_y), 2))));
      System.out.println(value + ", " + l_start_x + ", " + l_start_y + ", " + l_end_x + ", " + l_end_y + ", " + (Math.abs(l_end_y - l_start_y)) + ", " + Math.sqrt(Math.pow((l_end_x - l_start_x), 2)+(Math.pow((l_end_y - l_start_y), 2))) + ", " + Math.acos(value) + ", "+ Math.acos(value)*(180/Math.PI));
      return Math.acos(value)*(180/Math.PI);
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
