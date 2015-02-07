/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.core;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import trafficsimulator.core.Lane.Direction;
import trafficsimulator.utils.Point;

/**
 *
 * @author yukolthep
 */
public class GUIsimulation extends Application {
    static Map m = new Map();
    
        static Road r1 = new Road(new Point(0, 0), new Point(100, 0));
        static Lane l11 = new Lane(Direction.IDENTICAL);
        static Lane l12 = new Lane(Direction.OPPOSITE);
        
        static Road r2 = new Road(new Point(100, 0), new Point(100, 100));
        static Lane l21 = new Lane(Direction.IDENTICAL);
        static Lane l22 = new Lane(Direction.OPPOSITE);
        
        static Road r3 = new Road(new Point(100, 100), new Point(0, 0));
        static Lane l31 = new Lane(Direction.IDENTICAL);
        static Lane l32 = new Lane(Direction.OPPOSITE);
        
    
        static Junction j1 = new Junction();
        
        static Junction j2 = new Junction();
        
        static Junction j3 = new Junction();
        
    
        

        static Car v1 = new Car(l11, r1.getRandomPosition());
        static Car v2 = new Car(l21, r2.getRandomPosition());
        static Car v3 = new Car(l12, r1.getRandomPosition());
        
        static Simulation s = new Simulation();
        
        
        final static double CAR_WIDTH = 10.0;
        final static double CAR_HEIGHT = 10.0;
    
    
    @Override
    public void start(Stage stage) {
        //stage.setResizable(false);
        r1.addLane(l11);
        r1.addLane(l12);
        r2.addLane(l21);
        r2.addLane(l22);
        r3.addLane(l31);
        r3.addLane(l32);
        j1.connect(l11, l21);
        j1.connect(l22, l12);
        j2.connect(l21, l31);
        j2.connect(l32, l22);
        j3.connect(l31, l11);
        j3.connect(l12, l32);
        m.addRoad(r1);
        m.addRoad(r2);
        m.addRoad(r3);
        m.addJunction(j1);
        m.addJunction(j2);
        m.addJunction(j3);
        s.setMap(m);
        s.addVehicle(v1);
        s.addVehicle(v2);
        s.addVehicle(v3);
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
        /* set road width */
        gc.setLineWidth(2);
        /*draw road1 */
        gc.strokeLine(r1.getStartPoint().getX(), r1.getStartPoint().getY(), r1.getEndPoint().getX(), r1.getEndPoint().getY());
        /*draw road3 */
        gc.strokeLine(r2.getStartPoint().getX(), r2.getStartPoint().getY(), r2.getEndPoint().getX(), r2.getEndPoint().getY());
        /*draw road3 */
        gc.strokeLine(r3.getStartPoint().getX(), r3.getStartPoint().getY(), r3.getEndPoint().getX(), r3.getEndPoint().getY());
    }
    public void drawCar(final GraphicsContext gc){
        
        Platform.runLater(new Runnable(){
            @Override
            public void run(){
                gc.clearRect(0, 0, 400, 400);
                /* set road width */
        gc.setLineWidth(2);
        /*draw road1 */
        gc.strokeLine(r1.getStartPoint().getX(), r1.getStartPoint().getY(), r1.getEndPoint().getX(), r1.getEndPoint().getY());
        /*draw road3 */
        gc.strokeLine(r2.getStartPoint().getX(), r2.getStartPoint().getY(), r2.getEndPoint().getX(), r2.getEndPoint().getY());
        /*draw road3 */
        gc.strokeLine(r3.getStartPoint().getX(), r3.getStartPoint().getY(), r3.getEndPoint().getX(), r3.getEndPoint().getY());
        /* create car1 */
                gc.setFill(Color.BLUE);
                gc.fillOval(v1.getPosition().getX()-(CAR_WIDTH/2), v1.getPosition().getY()-(CAR_HEIGHT/2), CAR_WIDTH, CAR_HEIGHT);
        /* create car2 */
                gc.setFill(Color.RED);
                gc.fillOval(v2.getPosition().getX()-(CAR_WIDTH/2), v2.getPosition().getY()-(CAR_HEIGHT/2), CAR_WIDTH, CAR_HEIGHT);
        /* create car3 */
                gc.setFill(Color.GREEN);
                gc.fillOval(v3.getPosition().getX()-(CAR_WIDTH/2), v3.getPosition().getY()-(CAR_HEIGHT/2), CAR_WIDTH, CAR_HEIGHT);
            }
        });
    }
    


}