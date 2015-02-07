/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.core;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import trafficsimulator.core.Lane.Direction;
import trafficsimulator.simulations.Simulation1;
import trafficsimulator.utils.Point;
import trafficsimulator.vehicles.NormalCar;

/**
 *
 * @author yukolthep
 */
public class GUIsimulation extends Application {

        
    static Simulation1 s = new Simulation1();
        
    
    @Override
    public void start(Stage stage) {
        //stage.setResizable(false);
        BorderPane root = new BorderPane();
        //Group root = new Group();
        Canvas canvas = new Canvas(400,400);
        //final GraphicsContext road = canvas.getGraphicsContext2D();
        final GraphicsContext car = canvas.getGraphicsContext2D();
        //drawRoad(road);
        Runnable run = new Runnable(){
            @Override
            public void run() {
                //car.clearRect(0, 0, 400, 400);
                drawCar(car);
            }
        };
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(run, 0, 500, TimeUnit.MILLISECONDS);
        root.setCenter(canvas);
        stage.setScene(new Scene(root,600,600));
        stage.show();
        s.start();
    } 

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(args);
    }
    
    public void drawRoad(GraphicsContext gc){
      
      // SHOULD NOT DRAW EVERY SINGLE ROAD SEPARATELY
      // IT SHOULD DRAW THE ROADS IN A LOOP DYNAMICALLY
      
        /* set road width */
        gc.setLineWidth(2);
        /*draw road1 */
//        gc.strokeLine(r1.getStartPoint().getX(), r1.getStartPoint().getY(), r1.getEndPoint().getX(), r1.getEndPoint().getY());
        /*draw road3 */
//        gc.strokeLine(r2.getStartPoint().getX(), r2.getStartPoint().getY(), r2.getEndPoint().getX(), r2.getEndPoint().getY());
        /*draw road3 */
//        gc.strokeLine(r3.getStartPoint().getX(), r3.getStartPoint().getY(), r3.getEndPoint().getX(), r3.getEndPoint().getY());
    }
    public void drawCar(final GraphicsContext gc){
      
      
      // SHOULD NOT DRAW EVERY SINGLE CAR SEPARATELY
      // IT SHOULD DRAW THE CARS IN A LOOP DYNAMICALLY
        
        Platform.runLater(new Runnable(){
            @Override
            public void run(){
                gc.clearRect(0, 0, 400, 400);
                /* set road width */
        gc.setLineWidth(2);
        /*draw road1 */
//        gc.strokeLine(r1.getStartPoint().getX(), r1.getStartPoint().getY(), r1.getEndPoint().getX(), r1.getEndPoint().getY());
        /*draw road3 */
//        gc.strokeLine(r2.getStartPoint().getX(), r2.getStartPoint().getY(), r2.getEndPoint().getX(), r2.getEndPoint().getY());
        /*draw road3 */
//        gc.strokeLine(r3.getStartPoint().getX(), r3.getStartPoint().getY(), r3.getEndPoint().getX(), r3.getEndPoint().getY());
        /* create car1 */
                gc.setFill(Color.BLUE);
//                gc.fillOval(v1.getPosition().getX()-(CAR_WIDTH/2), v1.getPosition().getY()-(CAR_HEIGHT/2), CAR_WIDTH, CAR_HEIGHT);
        /* create car2 */
                gc.setFill(Color.RED);
//                gc.fillOval(v2.getPosition().getX()-(CAR_WIDTH/2), v2.getPosition().getY()-(CAR_HEIGHT/2), CAR_WIDTH, CAR_HEIGHT);
        /* create car3 */
                gc.setFill(Color.GREEN);
//                gc.fillOval(v3.getPosition().getX()-(CAR_WIDTH/2), v3.getPosition().getY()-(CAR_HEIGHT/2), CAR_WIDTH, CAR_HEIGHT);
            }
        });
    }
    


}
