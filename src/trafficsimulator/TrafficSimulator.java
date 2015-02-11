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
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import trafficsimulator.simulations.Simulation1;

/**
 *
 * @author balazs
 */
public class TrafficSimulator extends Application {
  
  private Simulation1 simulation = new Simulation1();
  
  @Override
  public void start(Stage primaryStage) {
    Button btn = new Button();
    btn.setText("Start Simulation");
    btn.setOnAction(new EventHandler<ActionEvent>() {
      
      @Override
      public void handle(ActionEvent event) {
        System.out.println("Starting simulation...");
        simulation.start();
      }
    });
    
    StackPane root = new StackPane();
    root.getChildren().add(btn);
    
    Scene scene = new Scene(root, 300, 250);
    
    primaryStage.setTitle("TrafficSimulator");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }
  
}
