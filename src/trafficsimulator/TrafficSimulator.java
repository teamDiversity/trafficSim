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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import trafficsimulator.core.Simulation;
import trafficsimulator.gui.SceneComponents;
import trafficsimulator.gui.SimulationRenderer;
import trafficsimulator.gui.SimulationResults;
import trafficsimulator.simulations.Simulation1;
import trafficsimulator.simulations.Simulation2;
import trafficsimulator.simulations.Simulation3;

/**
 *
 * @author balazs
 */
public class TrafficSimulator extends Application {

  private Simulation simulation;

  @Override
  public void start(final Stage primaryStage) {
    final SceneComponents scene = new SceneComponents();
    scene.startSim.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent event) {
        String selectedMap = scene.map_list.getValue().toString();
        switch (selectedMap) {
          case "Map_1":
            simulation = new Simulation1();
            break;
          case "Map_2":
            simulation = new Simulation2();
            break;
          case "Map_3":
            simulation = new Simulation3();
            break;
          default:
            simulation = new Simulation1();
            break;
        }
        SimulationRenderer renderer = new SimulationRenderer(scene.gc, simulation);
        simulation.setRenderer(renderer);
        simulation.setDuration(Long.parseLong(scene.duration_field.getText()));
        simulation.start();
        scene.disableStartButton();
        scene.enableResultButton();
      }
    });

    scene.showResults.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {

        new SimulationResults(primaryStage, simulation);
        scene.disableResultButton();
        scene.enableStartButton();
      }
    });

    //set stage config
    primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
      @Override
      public void handle(WindowEvent event) {
        System.exit(0);
      }
    });
    primaryStage.setTitle("Traffic Simulator");
    primaryStage.setScene(new Scene(scene, 1200, 700, Color.LIGHTGRAY));
    primaryStage.show();

  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }

}
