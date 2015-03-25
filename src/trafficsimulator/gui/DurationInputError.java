/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author yukolthep
 */
public class DurationInputError extends Stage{
  
  private Button OK;
  private GridPane pane;
  private Text t;
  private Scene scene;
  private VBox container;
  private BorderPane subContainer;
  
  public DurationInputError(Stage primaryStage){
    initModality(Modality.WINDOW_MODAL);
    initOwner(primaryStage);
    OK = new Button("OK");
    OK.setPrefSize(75, 25);
    OK.setOnAction(new ButtonHandler());
    OK.setOnKeyPressed(new KeyHandler());
    t = new Text("Please input a value between 1 - 1000!");
    t.setStyle("-fx-font-size: 25px;");
    subContainer = new BorderPane();
    subContainer.setCenter(OK);
    container = new VBox();
    container.setPadding(new Insets(10,10,10,10));
    container.setSpacing(40);
    container.getChildren().addAll(t, subContainer);
    pane = new GridPane();
    RowConstraints row1 = new RowConstraints();
    RowConstraints row2 = new RowConstraints();
    RowConstraints row3 = new RowConstraints();
    row1.setPercentHeight(15);
    row2.setPercentHeight(70);
    row3.setPercentHeight(15);
    ColumnConstraints col1 = new ColumnConstraints();
    ColumnConstraints col2 = new ColumnConstraints();
    ColumnConstraints col3 = new ColumnConstraints();
    col1.setPercentWidth(15);
    col2.setPercentWidth(70);
    col3.setPercentWidth(15);
    pane.getRowConstraints().addAll(row1, row2, row3);
    pane.getColumnConstraints().addAll(col1, col2, col3);
    pane.add(container, 1, 1);
    scene = new Scene(pane,600,200);
    setScene(scene);
    Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
    setX((primScreenBounds.getWidth()/2) - scene.getWidth()/2); 
    setY((primScreenBounds.getHeight()/2) - scene.getHeight()/2); 
    show();
  }
  
  class ButtonHandler implements EventHandler<ActionEvent>{
    @Override
    public void handle(ActionEvent event) {
      close();
    }    
  }
  
  class KeyHandler implements EventHandler<KeyEvent>{
    @Override
    public void handle(KeyEvent event) {
      if(event.getCode().equals(KeyCode.ENTER)){
        OK.fire();
      }
    } 
  }
}
