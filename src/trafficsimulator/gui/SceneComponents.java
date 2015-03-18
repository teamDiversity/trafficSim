/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author yukolthep
 */
public class SceneComponents extends BorderPane {
    
    protected StackPane canvas_panel;
    protected Canvas canvas;
    protected StackPane setting_panel;
    
    protected BorderPane button_pane;
    protected HBox button_box;
    protected Button startSim;
    protected Button showResults;
    
    protected HBox policy_box;
    protected VBox policy_radio_button_box;
    protected ToggleGroup policies_selector;
    protected RadioButton fixed_time;
    protected RadioButton congestion_control;
    
    protected HBox duration_box;
    
    protected HBox map_box;
    
    protected VBox container;
    
    
    public SceneComponents(){
      
    }
}
