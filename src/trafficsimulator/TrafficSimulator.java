/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Toggle;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import trafficsimulator.core.Simulation;
import trafficsimulator.gui.DurationInputError;
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
  
  
  public boolean peaktime = true;
  public boolean congestionControl;
  private Simulation simulation;
  private SceneComponents scene;
  private int simulation_round = 0;
  
  @Override
  public void start(final Stage primaryStage) {

    scene = new SceneComponents();
    scene.startSim.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent event) {
        String selectedMap = scene.map_list.getValue().toString();
        try{
          int duration = Integer.parseInt(scene.duration_field.getText());
          switch (selectedMap) {
            case "Small Town":
              simulation = new Simulation1(peaktime, congestionControl, duration);
              break;
            case "New York":
              simulation = new Simulation2(peaktime, congestionControl, duration);
              break;
            case "London":
              simulation = new Simulation3(peaktime, congestionControl, duration);
              break;
            default:
              simulation = new Simulation1(peaktime, congestionControl, duration);
              break;
          }
          SimulationRenderer renderer = new SimulationRenderer(scene.gc, simulation);
          simulation.setRenderer(renderer);
          simulation.setDuration(Long.parseLong(scene.duration_field.getText()));
          simulation_round += 1;
          simulation.start();
          scene.disableStartButton();
          scene.enableResultButton();
        }catch(NumberFormatException e){
          new DurationInputError(primaryStage);
      }
      }
    });
    
    scene.duration_field.setOnKeyPressed(new EventHandler<KeyEvent>(){
      @Override
      public void handle(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)){
          scene.startSim.fire();
        }
      }
    });
    
    scene.peakTimeSelector.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
      @Override
      public void changed(ObservableValue<? extends Toggle> ov, Toggle toggle,Toggle new_toggle) {
        peaktime = (boolean) scene.peakTimeSelector.getSelectedToggle().getUserData();
      }
    });
    
    scene.policySelector.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
      @Override
      public void changed(ObservableValue<? extends Toggle> ov, Toggle toggle,Toggle new_toggle) {
        congestionControl = (boolean) scene.policySelector.getSelectedToggle().getUserData();
      }
    });    
            
    scene.showResults.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {

        new SimulationResults(primaryStage, simulation, simulation_round, scene.map_list.getValue().toString(),scene.getSelectedPolicyText(), scene.duration_field.getText(), peaktime);
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
