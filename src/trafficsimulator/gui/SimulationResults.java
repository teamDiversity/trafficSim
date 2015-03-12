/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.gui;


import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import trafficsimulator.core.Simulation;

/**
 *
 * @author yukolthep
 */
public class SimulationResults extends Stage{
  public SimulationResults(Stage primaryStage, Simulation simulation){
    initModality(Modality.NONE);
    initOwner(primaryStage);
    VBox dialogVbox = new VBox(20);
    dialogVbox.getChildren().add(simulation.averageTime());
    dialogVbox.getChildren().add(simulation.longestTime());
    dialogVbox.getChildren().add(simulation.shortestTime());
    Scene dialogScene = new Scene(dialogVbox, 300, 200);
    setScene(dialogScene);
    Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
    setX((primScreenBounds.getWidth() - getWidth()) / 2); 
    setY((primScreenBounds.getHeight() - getHeight()) / 4); 
    show();
  }
}