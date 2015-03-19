/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.gui;

import javafx.geometry.Insets;
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
import javafx.scene.text.Text;

/**
 *
 * @author yukolthep
 */
public class SceneComponents extends BorderPane {
    
    protected StackPane canvas_panel;
    protected Canvas canvas;
    protected StackPane setting_panel;
    public GraphicsContext gc;
    
    protected HBox policy_box;
    protected VBox policy_radio_button_box;
    protected ToggleGroup policies_selector;
    protected RadioButton fixed_time;
    protected RadioButton congestion_control;
    
    protected HBox duration_box;
    public TextField duration_field;
    
    protected HBox map_box;
    public ComboBox map_list;
    protected Label selection_label;

    protected BorderPane button_pane;
    protected HBox button_box;
    public Button startSim;
    public Button showResults;
    
    protected VBox container;
    
    
    public SceneComponents(){
      this.setLeft(getCanvasPanel());
      this.setCenter(getContainerBox());
    }
    
    private StackPane getCanvasPanel(){
      canvas_panel = new StackPane();
      canvas_panel.setStyle("-fx-background-color: white");
      canvas = new Canvas(800,600);
      canvas_panel.getChildren().add(canvas);
      gc = canvas.getGraphicsContext2D();
      return canvas_panel;
    }
    
    private HBox getPolicyBox(){
      fixed_time = new RadioButton("Fixed Time policy");
      congestion_control = new RadioButton("Congestion Control policy");
      policies_selector = new ToggleGroup();
      fixed_time.setToggleGroup(policies_selector);
      congestion_control.setToggleGroup(policies_selector);
      fixed_time.setSelected(true);
      policy_radio_button_box = new VBox();
      policy_radio_button_box.setSpacing(15);
      policy_radio_button_box.getChildren().addAll(fixed_time, congestion_control);
      policy_box = new HBox();
      policy_box.setPadding(new Insets(10, 15, 10, 15));
      policy_box.setSpacing(10);
      policy_box.getChildren().addAll(new Text("Policy: "), policy_radio_button_box);
      return policy_box;
    }
    
    private HBox getDurationBox(){
      duration_field = new TextField();
      duration_field.setText("120");
      duration_box = new HBox();
      duration_box.setPadding(new Insets(10, 15, 10, 15));
      duration_box.setSpacing(10);
      duration_box.getChildren().addAll(new Text("Duration: "), duration_field, new Text("seconds"));
      return duration_box;
    }
    
    private HBox getMapBox(){
      map_list = new ComboBox();
      map_list.getItems().addAll("Map_1", "Map_2", "Map_3");
      map_list.setValue("Map_1");
      map_box = new HBox();
      map_box.getChildren().addAll(new Text("Map: "),map_list);
      return map_box;
    }
    
    private BorderPane getButtonPane(){
      startSim = new Button("Start");
      showResults = new Button("Result");
      startSim.setPrefSize(100, 50);
      showResults.setPrefSize(100, 50);
      showResults.setDisable(true);
      button_box = new HBox();
      button_box.setPadding(new Insets(10, 15, 10, 15));
      button_box.setSpacing(25);
      button_box.getChildren().addAll(startSim, showResults);
      button_pane = new BorderPane();
      button_pane.setCenter(button_box);
      return button_pane;
    }
    
    private VBox getContainerBox(){
      container = new VBox();
      container.setPadding(new Insets(10, 15, 10, 15));
      container.setSpacing(15);
      container.getChildren().addAll(getPolicyBox(), getDurationBox(), getMapBox(), getButtonPane());
      return container;
    }
    
    public String getMapValue(){
      return this.map_list.getValue().toString();
    }
    
    public void disableStartButton(){
      this.startSim.setDisable(true);
    }
    
    public void enableStartButton(){
      this.startSim.setDisable(false);
    }
    
    public void disableResultButton(){
      this.showResults.setDisable(true);
    }
    
    public void enableResultButton(){
      this.showResults.setDisable(false);
    }
    
    public String getSelectedRadioButton(){
      RadioButton temp = (RadioButton)this.policies_selector.getSelectedToggle();
      return temp.getText();
    }
}
