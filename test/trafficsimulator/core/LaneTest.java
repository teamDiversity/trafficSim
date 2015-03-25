/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.core;

import org.junit.Test;
import static org.junit.Assert.*;
import trafficsimulator.utils.Point;

/**
 *
 * @author snorri
 */
public class LaneTest {

  private Lane lane1;
  private Lane lane2;
  private Road road;

  public void setUp(Point start, Point end, Lane.Direction dir1, Lane.Direction dir2) {

    road = new Road(start, end);
    lane1 = road.addLane(dir1);
    lane2 = road.addLane(dir2);

  }

  @Test
  public void testLanesHorizontal() {
    System.out.println("Lanes horizontal");

    Point start = new Point(100, 100);
    Point end = new Point(400, 100);

    setUp(start, end, Lane.Direction.IDENTICAL, Lane.Direction.IDENTICAL);

    double expXStartLane1 = start.getX();
    double expYStartLane1 = start.getY() + lane1.LANE_WIDTH/2;
    double expXEndLane1 = end.getX();
    double expYEndLane1 = end.getY() + lane1.LANE_WIDTH/2;
    double expXStartLane2 = start.getX();
    double expYStartLane2 = start.getY() + lane2.LANE_WIDTH + lane2.LANE_WIDTH/2;
    double expXEndLane2 = end.getX();
    double expYEndLane2 = end.getY() + lane2.LANE_WIDTH  + lane2.LANE_WIDTH/2;

    double resultXStartLane1 = lane1.getStartPoint().getX();
    double resultYStartLane1 = lane1.getStartPoint().getY();
    double resultXEndLane1 = lane1.getEndPoint().getX();
    double resultYEndLane1 = lane1.getEndPoint().getY();
    double resultXStartLane2 = lane2.getStartPoint().getX();
    double resultYStartLane2 = lane2.getStartPoint().getY();
    double resultXEndLane2 = lane2.getEndPoint().getX();
    double resultYEndLane2 = lane2.getEndPoint().getY();

    assertEquals(expXStartLane1, resultXStartLane1, 0.001);
    assertEquals(expYStartLane1, resultYStartLane1, 0.001);
    assertEquals(expXEndLane1, resultXEndLane1, 0.001);
    assertEquals(expYEndLane1, resultYEndLane1, 0.001);
    assertEquals(expXStartLane2, resultXStartLane2, 0.001);
    assertEquals(expYStartLane2, resultYStartLane2, 0.001);
    assertEquals(expXEndLane2, resultXEndLane2, 0.001);
    assertEquals(expYEndLane2, resultYEndLane2, 0.001);


  }

  @Test
  public void testLanesVertical() {
    System.out.println("Lanes Vertical");

    Point start = new Point(100, 100);
    Point end = new Point(100, 400);

    setUp(start, end, Lane.Direction.IDENTICAL, Lane.Direction.OPPOSITE);

    double expXStartLane1 = start.getX() - lane1.LANE_WIDTH/2;
    double expYStartLane1 = start.getY();
    double expXEndLane1 = end.getX() - lane1.LANE_WIDTH/2;
    double expYEndLane1 = end.getY();
    double expXStartLane2 = end.getX() - lane2.LANE_WIDTH - lane2.LANE_WIDTH/2;
    double expYStartLane2 = end.getY();
    double expXEndLane2 = start.getX() - lane2.LANE_WIDTH  - lane2.LANE_WIDTH/2;
    double expYEndLane2 = start.getY();
    
    double resultXStartLane1 = lane1.getStartPoint().getX();
    double resultYStartLane1 = lane1.getStartPoint().getY();
    double resultXEndLane1 = lane1.getEndPoint().getX();
    double resultYEndLane1 = lane1.getEndPoint().getY();
    double resultXStartLane2 = lane2.getStartPoint().getX();
    double resultYStartLane2 = lane2.getStartPoint().getY();
    double resultXEndLane2 = lane2.getEndPoint().getX();
    double resultYEndLane2 = lane2.getEndPoint().getY();


    assertEquals(expXStartLane1, resultXStartLane1, 0.001);
    assertEquals(expYStartLane1, resultYStartLane1, 0.001);
    assertEquals(expXEndLane1, resultXEndLane1, 0.001);
    assertEquals(expYEndLane1, resultYEndLane1, 0.001);
    assertEquals(expXStartLane2, resultXStartLane2, 0.001);
    assertEquals(expYStartLane2, resultYStartLane2, 0.001);
    assertEquals(expXEndLane2, resultXEndLane2, 0.001);
    assertEquals(expYEndLane2, resultYEndLane2, 0.001);

  }

}
