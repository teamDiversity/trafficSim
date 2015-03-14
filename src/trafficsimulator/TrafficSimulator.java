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
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import trafficsimulator.core.Simulation;
import trafficsimulator.gui.SimulationRenderer;
import trafficsimulator.gui.SimulationResults;
import trafficsimulator.simulations.Simulation1;
import trafficsimulator.simulations.Simulation2;
import trafficsimulator.simulations.Simulation3;
import trafficsimulator.simulations.SimulationChosen;

/**
 *
 * @author balazs
 */
public class TrafficSimulator extends Application {
  
  @Override
  public void start(final Stage primaryStage) {
    
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
    final Button startSim = new Button("Start");
    startSim.setPrefSize(100, 50);
    BorderPane button_pane = new BorderPane();
    final Button showResults = new Button("Result");
    showResults.setPrefSize(100, 50);
    //showResults.setDisable(true);
    HBox button_box = new HBox();
    button_box.setPadding(new Insets(10,15,10,15));
    button_box.setSpacing(25);
    button_box.getChildren().addAll(startSim, showResults);
    button_pane.setCenter(button_box);

    
    ToggleGroup policies_selector = new ToggleGroup();
    RadioButton fixed_time = new RadioButton("Fixed time policy");
    RadioButton congestion_control = new RadioButton("Congestion control policy");
    fixed_time.setToggleGroup(policies_selector);
    congestion_control.setToggleGroup(policies_selector);
    fixed_time.setSelected(true);
    VBox policy_radio_box = new VBox();
    policy_radio_box.setSpacing(15);
    policy_radio_box.getChildren().addAll(fixed_time, congestion_control);
    HBox policy_box = new HBox();
    policy_box.setPadding(new Insets(10,15,10,15));
    policy_box.setSpacing(10);
    policy_box.getChildren().add(new Text("Policy: "));
    policy_box.getChildren().add(policy_radio_box);
    
    HBox duration_box = new HBox();
    duration_box.setPadding(new Insets(10,15,10,15));
    duration_box.setSpacing(10);
    duration_box.getChildren().add(new Text("Duration: "));
    final TextField duration_field = new TextField();
    duration_box.getChildren().add(duration_field);
    duration_box.getChildren().add(new Text("seconds"));
    
    HBox map_box = new HBox();
    map_box.setPadding(new Insets(10,15,10,15));
    map_box.setSpacing(10);
    map_box.getChildren().add(new Text("Map: "));
    
    ComboBox mapList = new ComboBox();
    mapList.getItems().addAll("Map_1","Map_2","Map_3");
    mapList.setValue("Map_1");
    map_box.getChildren().add(mapList);
    
    final Label selectionLabel = new Label();
    mapList.getSelectionModel().selectedItemProperty().addListener(
      new ChangeListener() {
      public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        selectionLabel.setText((String) newValue);
       }
      });
       
    VBox container = new VBox();
    container.setPadding(new Insets(10,15,10,15));
    container.setSpacing(15);
    container.getChildren().addAll(policy_box, duration_box, map_box, button_pane);
    root.setCenter(container);
    
    
    String selectedMap = mapList.getValue().toString();
    //final Simulation simulation = new SimulationChosen(selectedMap);
    final Simulation simulation = new Simulation1();
    //final Simulation simulation = new Simulation2();
    //final Simulation simulation = new Simulation3();
    
    SimulationRenderer renderer = new SimulationRenderer(gc, simulation);
    simulation.setRenderer(renderer);
 
    startSim.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {        
        simulation.duration = duration_field.getText();
        simulation.start();
        startSim.setDisable(true);
      }
    });
    
    showResults.setOnAction(new EventHandler<ActionEvent>(){
      @Override
      public void handle(ActionEvent event){
        new SimulationResults(primaryStage, simulation);
        showResults.setDisable(true);
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
    primaryStage.show();

  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }

}
