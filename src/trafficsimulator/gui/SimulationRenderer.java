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
      for (Lane lane : road.getLanes()) {
        Point startPoint = lane.getCenterStartPoint();
        Point endPoint = lane.getCenterEndPoint();
        gc.strokeLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
      }
    }
  }

  private void drawVehicles() {
    List<Vehicle> vehicles = this.simulation.getVehicles();
    for (Vehicle vehicle : vehicles) {
      if (Car.class.isInstance(vehicle)) {
        String dir = "IDENTICAL";
        if (dir.equalsIgnoreCase(vehicle.getLane().getDirection().name())) {
          /*do nothing*/
        } else {
          dir = "OPPOSITE";
        }

        if (dir.equals("IDENTICAL")) {
          //hv to calculate road's angle beforehand (compare to 0deg line)
          Point startPoint = vehicle.getLane().getLeftStartPoint();
          Point endPoint = vehicle.getLane().getLeftEndPoint();
          double theta = calcTheta(vehicle.getLane());
          if (startPoint.getX() == endPoint.getX()) {
            if (theta > 0 && theta < 90) {
              theta = 180 - theta;
            } else {
              theta = 360 - theta;
            }
          } else if (startPoint.getY() == endPoint.getY()) {
            if (theta > 0 && theta < 90) {
              theta = 180 - theta;
            } else {
              theta = 360 - theta;
            }
          } else {
            if (startPoint.getX() < endPoint.getX() && startPoint.getY() > endPoint.getY()) {
              if (theta > 0 && theta < 90) {
                theta = 180 - theta;
              } else {
                theta = 360 - theta;
              }
              theta -= 270;
            } else if (startPoint.getX() < endPoint.getX() && startPoint.getY() < endPoint.getY()) {
              if (theta > 0 && theta < 90) {
                theta = 180 - theta;
              } else {
                theta = 360 - theta;
              }
              theta -= 180;
            } else if (startPoint.getX() > endPoint.getX() && startPoint.getY() < endPoint.getY()) {
              if (theta > 0 && theta < 90) {
                theta = 180 - theta;
              } else {
                theta = 360 - theta;
              }
              theta -= 90;
            } else {
              if (theta > 0 && theta < 90) {
                theta = 180 - theta;
              } else {
                theta = 360 - theta;
              }
            }
          }

          drawRotatedImage(gc, car, theta, (vehicle.getPosition().getX() - car.getWidth() / 2), (vehicle.getPosition().getY() - car.getHeight() / 2));
        } else {
          Point startPoint = vehicle.getLane().getLeftStartPoint();
          Point endPoint = vehicle.getLane().getLeftEndPoint();
          double theta = calcTheta(vehicle.getLane());
          if (startPoint.getX() == endPoint.getX()) {
            if (theta > 0 && theta < 90) {
              theta = 180 - theta;
            } else {
              theta = 360 - theta;
            }
          } else if (startPoint.getY() == endPoint.getY()) {
            if (theta > 0 && theta < 90) {
              theta = 180 - theta;
            } else {
              theta = 360 - theta;
            }
          } else {
            if (startPoint.getX() < endPoint.getX() && startPoint.getY() > endPoint.getY()) {
              if (theta > 0 && theta < 90) {
                theta = 180 - theta;
              } else {
                theta = 360 - theta;
              }
              theta += 90;
            } else if (startPoint.getX() < endPoint.getX() && startPoint.getY() < endPoint.getY()) {
              if (theta > 0 && theta < 90) {
                theta = 180 - theta;
              } else {
                theta = 360 - theta;
              }
              theta -= 180;
            } else if (startPoint.getX() > endPoint.getX() && startPoint.getY() < endPoint.getY()) {
              if (theta > 0 && theta < 90) {
                theta = 180 - theta;
              } else {
                theta = 360 - theta;
              }
              theta -= 90;
            } else {
              if (theta > 0 && theta < 90) {
                theta = 180 - theta;
              } else {
                theta = 360 - theta;
              }
            }
          }

          drawRotatedImage(gc, car, theta, (vehicle.getPosition().getX() - car.getWidth() / 2), (vehicle.getPosition().getY() - car.getHeight() / 2));
        }
      } else if (Bus.class.isInstance(vehicle)) {
        String dir = "IDENTICAL";
        if (dir.equalsIgnoreCase(vehicle.getLane().getDirection().name())) {
          /*do nothing*/
        } else {
          dir = "OPPOSITE";
        }

        if (dir.equals("IDENTICAL")) {
          //hv to calculate road's angle beforehand (compare to 0deg line)
          Point startPoint = vehicle.getLane().getLeftStartPoint();
          Point endPoint = vehicle.getLane().getLeftEndPoint();
          double theta = calcTheta(vehicle.getLane());
          if (startPoint.getX() == endPoint.getX()) {
            if (theta > 0 && theta < 90) {
              theta = 180 - theta;
            } else {
              theta = 360 - theta;
            }
          } else if (startPoint.getY() == endPoint.getY()) {
            if (theta > 0 && theta < 90) {
              theta = 180 - theta;
            } else {
              theta = 360 - theta;
            }
          } else {
            if (startPoint.getX() < endPoint.getX() && startPoint.getY() > endPoint.getY()) {
              if (theta > 0 && theta < 90) {
                theta = 180 - theta;
              } else {
                theta = 360 - theta;
              }
              theta -= 270;
            } else if (startPoint.getX() < endPoint.getX() && startPoint.getY() < endPoint.getY()) {
              if (theta > 0 && theta < 90) {
                theta = 180 - theta;
              } else {
                theta = 360 - theta;
              }
              theta -= 180;
            } else if (startPoint.getX() > endPoint.getX() && startPoint.getY() < endPoint.getY()) {
              if (theta > 0 && theta < 90) {
                theta = 180 - theta;
              } else {
                theta = 360 - theta;
              }
              theta -= 90;
            } else {
              if (theta > 0 && theta < 90) {
                theta = 180 - theta;
              } else {
                theta = 360 - theta;
              }
            }
          }

          drawRotatedImage(gc, bus, theta, (vehicle.getPosition().getX() - bus.getWidth() / 2), (vehicle.getPosition().getY() - bus.getHeight() / 2));
        } else {
          Point startPoint = vehicle.getLane().getLeftStartPoint();
          Point endPoint = vehicle.getLane().getLeftEndPoint();
          double theta = calcTheta(vehicle.getLane());
          if (startPoint.getX() == endPoint.getX()) {
            if (theta > 0 && theta < 90) {
              theta = 180 - theta;
            } else {
              theta = 360 - theta;
            }
          } else if (startPoint.getY() == endPoint.getY()) {
            if (theta > 0 && theta < 90) {
              theta = 180 - theta;
            } else {
              theta = 360 - theta;
            }
          } else {
            if (startPoint.getX() < endPoint.getX() && startPoint.getY() > endPoint.getY()) {
              if (theta > 0 && theta < 90) {
                theta = 180 - theta;
              } else {
                theta = 360 - theta;
              }
              theta += 90;
            } else if (startPoint.getX() < endPoint.getX() && startPoint.getY() < endPoint.getY()) {
              if (theta > 0 && theta < 90) {
                theta = 180 - theta;
              } else {
                theta = 360 - theta;
              }
              theta -= 180;
            } else if (startPoint.getX() > endPoint.getX() && startPoint.getY() < endPoint.getY()) {
              if (theta > 0 && theta < 90) {
                theta = 180 - theta;
              } else {
                theta = 360 - theta;
              }
              theta -= 90;
            } else {
              if (theta > 0 && theta < 90) {
                theta = 180 - theta;
              } else {
                theta = 360 - theta;
              }
            }
          }

          drawRotatedImage(gc, bus, theta, (vehicle.getPosition().getX() - bus.getWidth() / 2), (vehicle.getPosition().getY() - bus.getHeight() / 2));
        }
      }
    }
  }

  private double calcTheta(Lane l) {
    Point startPoint = l.getLeftStartPoint();
    Point endPoint = l.getLeftEndPoint();
    double value;
    double l_start_x = startPoint.getX();
    double l_start_y = startPoint.getY();
    double l_end_x = endPoint.getX();
    double l_end_y = endPoint.getY();
    value = ((Math.abs(l_end_y - l_start_y)) / Math.sqrt(Math.pow((l_end_x - l_start_x), 2) + (Math.pow((l_end_y - l_start_y), 2))));
    //System.out.println(value + ", " + l_start_x + ", " + l_start_y + ", " + l_end_x + ", " + l_end_y + ", " + (Math.abs(l_end_y - l_start_y)) + ", " + Math.sqrt(Math.pow((l_end_x - l_start_x), 2)+(Math.pow((l_end_y - l_start_y), 2))) + ", " + Math.acos(value) + ", "+ Math.acos(value)*(180/Math.PI));
    return Math.acos(value) * (180 / Math.PI);
  }

  private double calcRoadAngle(Road r) {
    Point startPoint = r.getLeftStartPoint();
    Point endPoint = r.getLeftEndPoint();
    double r_start_x = startPoint.getX();
    double r_start_y = startPoint.getY();
    double r_end_x = endPoint.getX();
    double r_end_y = endPoint.getY();
    double value = ((Math.abs(r_end_y - r_start_y)) / Math.sqrt(Math.pow(r_end_x - r_start_x, 2) + (Math.pow(r_end_y - r_start_y, 2))));
    //System.out.println(Math.acos(value)*(180/Math.PI));
    return Math.acos(value) * (180 / Math.PI);
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

  private void drawRotatedRoad(GraphicsContext gc, double angle, double tlpx, double tlpy, double road_width, double road_height) {
    gc.save();
    rotate(gc, angle, tlpx, tlpy - road_height / 2);
    gc.fillRect(tlpx, tlpy, road_width, road_height);
    gc.restore();
  }

}
