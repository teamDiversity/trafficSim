/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import trafficsimulator.gui.SimulationRenderer;
import trafficsimulator.simulations.Simulation1;
import trafficsimulator.simulations.Simulation2;

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
    
    //main layout
    BorderPane root = new BorderPane();
    //canvas layout (white bg)
    StackPane canvas_holder = new StackPane();
    canvas_holder.setStyle("-fx-background-color: white");
    //create a control panel
    StackPane control_panel = new StackPane();
    control_panel.setStyle("-fx-backgrond-color: blue");
    //create canvas
    Canvas canvas = new Canvas(800,600);
    //add canvas to its holder
    canvas_holder.getChildren().add(canvas);
    //create a GraphicsContext
    GraphicsContext gc = canvas.getGraphicsContext2D();
    //add canvas layout into main layout
    root.setLeft(canvas_holder);
    //create set of option selectors (buttons, textfields, radio buttons...)
    Button startSim = new Button("Start");
    startSim.setPrefSize(100, 50);
    //add option selectors into their panel
    control_panel.getChildren().add(startSim);
    //add control panel into main layout
    root.setCenter(control_panel);
    //create simulation
    final Simulation1 simulation = new Simulation1();
    SimulationRenderer renderer = new SimulationRenderer(gc, simulation);
    simulation.setRenderer(renderer);
    //simulation.start();
    
    //add function to option selectors
    startSim.setOnAction(new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            simulation.start();
        }
    });
    //set stage config
    primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

        @Override
        public void handle(WindowEvent event) {
            System.exit(0);
        }
    });
    primaryStage.setTitle("TrafficSimulator");
    primaryStage.setScene(new Scene(root,1200,700,Color.LIGHTGRAY));
    //show stage
    primaryStage.show();
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }
  
}
