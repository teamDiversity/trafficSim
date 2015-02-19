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
//    gc.save();
//    gc.setFill(Color.GRAY);
//    gc.rotate(43.22);
//    gc.fillRect(20 - (500-20)/2, (450-20)/2, (500-20)/Math.cos(45), car_image.getHeight());
//    gc.restore();
    
    List<Road> roads = this.simulation.getMap().getRoads();
    for(Road road : roads){
      gc.strokeLine(road.getStartPoint().getX(), road.getStartPoint().getY(), road.getEndPoint().getX(), road.getEndPoint().getY());
      gc.setFill(Color.GRAY);
      if(road.getStartPoint().getY() == road.getEndPoint().getY()){
          if(road.getStartPoint().getX() < road.getEndPoint().getX()){
              gc.fillRect(road.getStartPoint().getX() - (car_image.getWidth()), road.getStartPoint().getY() - (car_image.getHeight()/2), road.getEndPoint().getX() - road.getStartPoint().getX() + 2*car_image.getWidth(), car_image.getHeight());
          }else{
              gc.fillRect(road.getEndPoint().getX() - (car_image.getWidth()), road.getEndPoint().getY() - (car_image.getHeight()/2), road.getStartPoint().getX() - road.getEndPoint().getX() + 2*car_image.getWidth(), car_image.getHeight());
          }
      }else if(road.getStartPoint().getX() == road.getEndPoint().getX()){
          if(road.getStartPoint().getY() < road.getEndPoint().getY()){
              gc.fillRect(road.getStartPoint().getX() - (car_image.getHeight()/2), road.getStartPoint().getY() - car_image.getHeight(), car_image.getHeight(), road.getEndPoint().getY() + car_image.getHeight());
          }else{
              gc.fillRect(road.getStartPoint().getX() - (car_image.getHeight()/2), road.getEndPoint().getY() - car_image.getHeight(), car_image.getHeight(), road.getStartPoint().getY() + car_image.getHeight());
          }
      }else{
          double road_angle = calcRoadAngle(road);
          
          double temp = 47.92;
          if(road.getStartPoint().getX() < road.getEndPoint().getX() && road.getStartPoint().getY() < road.getEndPoint().getY()){
              drawRotatedRoad(gc, road_angle, road.getStartPoint().getX(), road.getStartPoint().getY(), road.getEndPoint().getX() - road.getStartPoint().getX() + 2*car_image.getWidth(), car_image.getHeight());
          }else if(road.getStartPoint().getX() > road.getEndPoint().getX() && road.getStartPoint().getY() < road.getEndPoint().getY()){
              drawRotatedRoad(gc, road_angle, road.getEndPoint().getX(), road.getStartPoint().getY(), road.getStartPoint().getX() - road.getEndPoint().getX() + 2*car_image.getWidth(), car_image.getHeight());
          }else if(road.getStartPoint().getX() < road.getEndPoint().getX() && road.getStartPoint().getY() > road.getEndPoint().getY()){
              drawRotatedRoad(gc, road_angle, road.getStartPoint().getX(), road.getStartPoint().getY(), road.getEndPoint().getX() - road.getStartPoint().getX() + 2*car_image.getWidth(), car_image.getHeight());
          }else{
              drawRotatedRoad(gc, road_angle, road.getEndPoint().getX() + car_image.getWidth(), road.getEndPoint().getY() - car_image.getHeight()/3, ((road.getStartPoint().getX() - road.getEndPoint().getX())/Math.abs(Math.cos(road_angle))), car_image.getHeight());
          }
      }
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
        drawRotatedImage(gc, car_image, theta, (vehicle.getPosition().getX()-car_image.getWidth()/2), (vehicle.getPosition().getY()-car_image.getHeight()/2));
      }else{
        double theta = calcTheta(vehicle.getLane());
        if(theta > 0 && theta < 90){
            theta = theta - 90;
        }else{
            theta = 180 - theta;
        }
        drawRotatedImage(gc, car_image, theta, (vehicle.getPosition().getX()-car_image.getWidth()/2), (vehicle.getPosition().getY()-car_image.getHeight()/2));
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
  
  private double calcRoadAngle(Road r){
      double r_start_x = r.getStartPoint().getX();
      double r_start_y = r.getStartPoint().getY();
      double r_end_x = r.getEndPoint().getX();
      double r_end_y = r.getEndPoint().getY();
      double value = ((Math.abs(r_end_y - r_start_y))/Math.sqrt(Math.pow(r_end_x - r_start_x, 2)+(Math.pow(r_end_y - r_start_y, 2))));
      System.out.println(Math.acos(value)*(180/Math.PI));
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
  
  private void drawRotatedRoad(GraphicsContext gc, double angle, double tlpx, double tlpy, double road_width, double road_height){
      gc.save();
      rotate(gc, angle, tlpx, tlpy - road_height/2);
      gc.fillRect(tlpx, tlpy, road_width, road_height);
      gc.restore();
  }

}
