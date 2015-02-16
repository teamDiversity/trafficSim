/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import trafficsimulator.gui.SimulationRenderer;
import trafficsimulator.simulations.Simulation1;

/**
 *
 * @author balazs
 */
public class TrafficSimulator extends Application {
  
  
  @Override
  public void start(Stage primaryStage) {
    
//    Button btn = new Button();
//    btn.setText("Start Simulation");
//    btn.setOnAction(new EventHandler<ActionEvent>() {
//      @Override
//      public void handle(ActionEvent event) {
//        System.out.println("Starting simulation...");
//        simulation.start();
//      }
//    });
//    
//    StackPane root = new StackPane();
//    root.getChildren().add(btn);
    
    BorderPane root = new BorderPane();
    Canvas canvas = new Canvas(1024,768);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    root.setCenter(canvas);
    primaryStage.setTitle("TrafficSimulator");
    primaryStage.setScene(new Scene(root,1024,768));
    primaryStage.show();
    
    Simulation1 simulation = new Simulation1();
    SimulationRenderer renderer = new SimulationRenderer(gc, simulation);
    simulation.setRenderer(renderer);
    simulation.start();
    
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }
  
}
