/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.gui;


import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
  public SimulationResults(Stage primaryStage, Simulation simulation, int round, String map_no, String policy, String duration, boolean isPeaktime){
    double temp;
    if(isPeaktime){
      temp = Double.parseDouble(duration)/0.5;
    }else{
      temp = Double.parseDouble(duration)/1.5;
    }
    int totalVehicle = (int)temp - 1;
    initModality(Modality.NONE);
    initOwner(primaryStage);
    GridPane pane = new GridPane();
    RowConstraints row1 = new RowConstraints();
    RowConstraints row2 = new RowConstraints();
    RowConstraints row3 = new RowConstraints();
    RowConstraints row4 = new RowConstraints();
    RowConstraints row5 = new RowConstraints();
    RowConstraints row6 = new RowConstraints();
    RowConstraints row7 = new RowConstraints();
    RowConstraints row8 = new RowConstraints();
    RowConstraints row9 = new RowConstraints();
    row1.setPercentHeight(100/9);
    row2.setPercentHeight(100/9);
    row3.setPercentHeight(100/9);
    row4.setPercentHeight(100/9);
    row5.setPercentHeight(100/9);
    row6.setPercentHeight(100/9);
    row7.setPercentHeight(100/9);
    row8.setPercentHeight(100/9);
    row9.setPercentHeight(100/9);
    pane.getRowConstraints().addAll(row1, row2, row3, row4, row5, row6, row7, row8, row9);
    ColumnConstraints column1 = new ColumnConstraints();
    column1.setPercentWidth(60);
    ColumnConstraints column2 = new ColumnConstraints();
    column2.setPercentWidth(40);
    pane.getColumnConstraints().addAll(column1, column2);
    pane.setGridLinesVisible(true);
    pane.add(new Text("Simulation#"), 0, 0);
    pane.add(new Text(" " + round), 1, 0);
    pane.add(new Text("Map:"), 0, 1);
    pane.add(new Text(" " + map_no.substring(map_no.lastIndexOf('_')+1, map_no.length())), 1, 1);
    pane.add(new Text("Policy:"), 0, 2);
    pane.add(new Text(" " + policy), 1, 2);
    pane.add(new Text("Duration:"), 0, 3);
    pane.add(new Text(" " + duration + " seconds"), 1, 3);
    pane.add(new Text("Total vehicle: "), 0, 4);
    pane.add(new Text(" " + totalVehicle), 1, 4);
    pane.add(new Text("Number of vehicle(s) exited: "), 0, 5);
    pane.add(new Text(" " + simulation.getExitedVehicles().size()), 1, 5);
    pane.add(new Text("Average time:"), 0, 6);
    pane.add(simulation.averageTime(), 1, 6);
    pane.add(new Text("Longest time:"), 0, 7);
    pane.add(simulation.longestTime(), 1, 7);
    pane.add(new Text("Shortest time:"), 0, 8);
    pane.add(simulation.shortestTime(), 1, 8);
    
    VBox dialogVbox = new VBox(20);
    dialogVbox.setSpacing(1.5);
    dialogVbox.getChildren().add(simulation.totalCar());
    dialogVbox.getChildren().add(simulation.averageTime());
    dialogVbox.getChildren().add(simulation.longestTime());
    dialogVbox.getChildren().add(simulation.shortestTime());
    Scene dialogScene = new Scene(pane, 450, 240, Color.WHITE);
    setScene(dialogScene);
    Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
    setX((primScreenBounds.getWidth() - getWidth()) / 2); 
    setY((primScreenBounds.getHeight() - getHeight()) / 4); 
    show();
  }
}
