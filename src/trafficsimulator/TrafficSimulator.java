/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import trafficsimulator.gui.SimulationRenderer;
import trafficsimulator.gui.SimulationResults;
import trafficsimulator.simulations.Simulation1;
import trafficsimulator.simulations.Simulation2;

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
    duration_field.setText("120");
    duration_box.getChildren().add(duration_field);
    duration_box.getChildren().add(new Text("seconds"));
    
    HBox map_box = new HBox();
    map_box.setPadding(new Insets(10,15,10,15));
    map_box.setSpacing(10);
    map_box.getChildren().add(new Text("Map: "));
    ObservableList<String> options = FXCollections.observableArrayList("Map 1","Map 2","Map 3");
    ComboBox map_list = new ComboBox(options);
    map_list.setValue("Map 1");
    map_box.getChildren().add(map_list);
    
    
    VBox container = new VBox();
    container.setPadding(new Insets(10,15,10,15));
    container.setSpacing(15);
    container.getChildren().addAll(policy_box, duration_box, map_box, button_pane);
    //add control panel into main layout
    root.setCenter(container);
    //create simulation
    final Simulation1 simulation = new Simulation1();
    SimulationRenderer renderer = new SimulationRenderer(gc, simulation);
    simulation.setRenderer(renderer);
    //simulation.start();
    
    
    //add function to option selectors
    startSim.setOnAction(new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            simulation.setDuration(Long.parseLong(duration_field.getText()));
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
