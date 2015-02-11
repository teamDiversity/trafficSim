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
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
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
    
        static Road r1 = new Road(new Point(0, 0), new Point(400, 0));
        static Lane l11 = new Lane(Direction.IDENTICAL);
        static Lane l12 = new Lane(Direction.OPPOSITE);
        
        static Road r2 = new Road(new Point(400, 0), new Point(400, 400));
        static Lane l21 = new Lane(Direction.IDENTICAL);
        static Lane l22 = new Lane(Direction.OPPOSITE);
        
        static Road r3 = new Road(new Point(400, 400), new Point(0, 0));
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
        
        Image car_image = new Image("pic/car_tran.gif",50,0,true,false);
    
    
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
        Canvas canvas = new Canvas(500,500);
        //final GraphicsContext road = canvas.getGraphicsContext2D();
        final GraphicsContext car = canvas.getGraphicsContext2D();
        //drawRoad(road);
        Runnable run = new Runnable(){
            @Override
            public void run() {
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
                gc.clearRect(0, 0, 700, 700);
                /* set road width */
        gc.setLineWidth(5);
        /*draw road1 */
        gc.strokeLine(r1.getStartPoint().getX()+ 50, r1.getStartPoint().getY() + 50, r1.getEndPoint().getX() +50, r1.getEndPoint().getY() +50);
        /*draw road3 */
        gc.strokeLine(r2.getStartPoint().getX()+50, r2.getStartPoint().getY()+50, r2.getEndPoint().getX()+50, r2.getEndPoint().getY()+50);
        /*draw road3 */
        gc.strokeLine(r3.getStartPoint().getX()+50, r3.getStartPoint().getY()+50, r3.getEndPoint().getX()+50, r3.getEndPoint().getY()+50);
        /*Direction string*/
        String dir = "IDENTICAL";
        /* create car1 */
                if(dir.equalsIgnoreCase(v1.getLane().getDirection().name())){
                    /*do nothing*/
                }else{
                    dir = "OPPOSITE";
                }
                
                if(dir.equals("IDENTICAL")){
                    //hv to calculate road's angle beforehand (compare to 0deg line)
                    double theta = calcTheta(v1.getLane());
                    if(theta > 0 && theta < 90){
                        theta = 180 - theta;
                    }else{
                        theta = 360 - theta;
                    }
                    drawRotatedImage(gc, car_image, theta, (v1.getPosition().getX()-car_image.getWidth()/2)+50, (v1.getPosition().getY()-car_image.getHeight()/2)+50);
                }else{
                    double theta = calcTheta(v1.getLane());
                    if(theta > 0 && theta < 90){
                        theta = theta - 90;
                    }else{
                        theta = 180 - theta;
                    }
                    drawRotatedImage(gc, car_image, theta, (v1.getPosition().getX()-car_image.getWidth()/2)+50, (v1.getPosition().getY()-car_image.getHeight()/2)+50);
                }

        /* create car2 */
                if(dir.equalsIgnoreCase(v2.getLane().getDirection().name())){
                    /*do nothing*/
                }else{
                    dir = "OPPOSITE";
                }
                
                if(dir.equals("IDENTICAL")){
                    //hv to calculate road's angle beforehand (compare to 0deg line)
                    double theta = calcTheta(v2.getLane());
                    if(theta > 0 && theta < 90){
                        theta = 180 - theta;
                    }else{
                        theta = 360 - theta;
                    }
                    drawRotatedImage(gc, car_image, theta, (v2.getPosition().getX()-car_image.getWidth()/2)+50, (v2.getPosition().getY()-car_image.getHeight()/2)+50);
                }else{
                    double theta = calcTheta(v2.getLane());
                    if(theta > 0 && theta < 90){
                        theta = theta - 90;
                    }else{
                        theta = 180 - theta;
                    }
                    drawRotatedImage(gc, car_image, theta, (v2.getPosition().getX()-car_image.getWidth()/2)+50, (v2.getPosition().getY()-car_image.getHeight()/2)+50);
                }
        /* create car3 */
        if(dir.equalsIgnoreCase(v3.getLane().getDirection().name())){
                    /*do nothing*/
                }else{
                    dir = "OPPOSITE";
                }
                
                if(dir.equals("IDENTICAL")){
                    //hv to calculate road's angle beforehand (compare to 0deg line)
                    double theta = calcTheta(v3.getLane());
                    if(theta > 0 && theta < 90){
                        theta = 180 - theta;
                    }else{
                        theta = 360 - theta;
                    }
                    drawRotatedImage(gc, car_image, theta, (v3.getPosition().getX()-car_image.getWidth()/2) +50, (v3.getPosition().getY()-car_image.getHeight()/2)+50);
                }else{
                    double theta = calcTheta(v3.getLane());
                    if(theta > 0 && theta < 90){
                        theta = theta - 90;
                    }else{
                        theta = 180 - theta;
                    }
                    drawRotatedImage(gc, car_image, theta, (v3.getPosition().getX()-car_image.getWidth()/2)+50, (v3.getPosition().getY()-car_image.getHeight()/2)+50);
                }    
            }
        });
    }
    
    public double calcTheta(Lane l){
        double l_start_x = l.getStartPoint().getX();
        double l_start_y = l.getStartPoint().getY();
        double l_end_x = l.getEndPoint().getX();
        double l_end_y = l.getEndPoint().getY();
        double value = ((Math.abs(l_end_y - l_start_y))/Math.sqrt(Math.pow((l_end_x - l_start_x), 2)+(Math.pow((l_end_y - l_start_y), 2))));
        System.out.println(value + ", " + l_start_x + ", " + l_start_y + ", " + l_end_x + ", " + l_end_y + ", " + (Math.abs(l_end_y - l_start_y)) + ", " + Math.sqrt(Math.pow((l_end_x - l_start_x), 2)+(Math.pow((l_end_y - l_start_y), 2))) + ", " + Math.acos(value) + ", "+ Math.acos(value)*(180/Math.PI));
        return Math.acos(value)*(180/Math.PI);
    }
    
    private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }
    
    private void drawRotatedImage(GraphicsContext gc, Image image, double angle, double tlpx, double tlpy) {
        gc.save(); // saves the current state on stack, including the current transform
        rotate(gc, angle, tlpx + image.getWidth() / 2, tlpy + image.getHeight() / 2);
        gc.drawImage(image, tlpx, tlpy);
        gc.restore(); // back to original state (before rotation)
    }

}
