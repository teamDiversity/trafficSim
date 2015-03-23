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
    lane1 = new Lane(dir1);
    lane2 = new Lane(dir2);
    road.addLane(lane1);
    road.addLane(lane2);

  }

  /**
   * This test checks if right and left parameters are calculated correctly if a
   * horizontal road is created and its direction is to the right like this: ->
   * with two lanes that are both IDENTICAL.
   */
  @Test
  public void testLanesHorizontalRight() {
    System.out.println("Lanes horizontal right");

    Point startLeft = new Point(100, 100);
    Point endLeft = new Point(400, 100);

    setUp(startLeft, endLeft, Lane.Direction.IDENTICAL, Lane.Direction.IDENTICAL);
    /*
     System.out.println("lane1LeftStartPointX: " + lane1.getLeftStartPoint().getX());
     System.out.println("lane1LeftStartPointY: " + lane1.getLeftStartPoint().getY());
     System.out.println("lane1LeftEndPointX: " + lane1.getLeftEndPoint().getX());
     System.out.println("lane1LeftEndPointY: " + lane1.getLeftEndPoint().getY());
     System.out.println("lane1RightStartPointX: " + lane1.getRightStartPoint().getX());
     System.out.println("lane1RightStartPointY: " + lane1.getRightStartPoint().getY());
     System.out.println("lane1RightEndPointX: " + lane1.getRightEndPoint().getX());
     System.out.println("lane1RightEndPointY: " + lane1.getRightEndPoint().getY());
     System.out.println("lane2LeftStartPointX: " + lane2.getLeftStartPoint().getX());
     System.out.println("lane2LeftStartPointY: " + lane2.getLeftStartPoint().getY());
     System.out.println("lane2LeftEndPointX: " + lane2.getLeftEndPoint().getX());
     System.out.println("lane2LeftEndPointY: " + lane2.getLeftEndPoint().getY());
     System.out.println("lane2RightStartPointX: " + lane2.getRightStartPoint().getX());
     System.out.println("lane2RightStartPointY: " + lane2.getRightStartPoint().getY());
     System.out.println("lane2RightEndPointX: " + lane2.getRightEndPoint().getX());
     System.out.println("lane2RightEndPointY: " + lane2.getRightEndPoint().getY());
     */
    double expXLeftStartLane1 = startLeft.getX();
    double expYLeftStartLane1 = startLeft.getY();
    double expXRightStartLane1 = startLeft.getX();
    double expYRightStartLane1 = startLeft.getY() + lane1.LANE_WIDTH;
    double expXLeftEndLane1 = endLeft.getX();
    double expYLeftEndLane1 = endLeft.getY();
    double expXRightEndLane1 = endLeft.getX();
    double expYRightEndLane1 = endLeft.getY() + lane1.LANE_WIDTH;
    double expXLeftStartLane2 = startLeft.getX();
    double expYLeftStartLane2 = startLeft.getY() + lane1.LANE_WIDTH;
    double expXRightStartLane2 = startLeft.getX();
    double expYRightStartLane2 = startLeft.getY() + lane1.LANE_WIDTH + lane2.LANE_WIDTH;
    double expXLeftEndLane2 = endLeft.getX();
    double expYLeftEndLane2 = endLeft.getY() + lane1.LANE_WIDTH;
    double expXRightEndLane2 = endLeft.getX();
    double expYRightEndLane2 = endLeft.getY() + lane1.LANE_WIDTH + lane2.LANE_WIDTH;

    double resultXLeftStartLane1 = lane1.getLeftStartPoint().getX();
    double resultYLeftStartLane1 = lane1.getLeftStartPoint().getY();
    double resultXRightStartLane1 = lane1.getRightStartPoint().getX();
    double resultYRightStartLane1 = lane1.getRightStartPoint().getY();
    double resultXLeftEndLane1 = lane1.getLeftEndPoint().getX();
    double resultYLeftEndLane1 = lane1.getLeftEndPoint().getY();
    double resultXRightEndLane1 = lane1.getRightEndPoint().getX();
    double resultYRightEndLane1 = lane1.getRightEndPoint().getY();
    double resultXLeftStartLane2 = lane2.getLeftStartPoint().getX();
    double resultYLeftStartLane2 = lane2.getLeftStartPoint().getY();
    double resultXRightStartLane2 = lane2.getRightStartPoint().getX();
    double resultYRightStartLane2 = lane2.getRightStartPoint().getY();
    double resultXLeftEndLane2 = lane2.getLeftEndPoint().getX();
    double resultYLeftEndLane2 = lane2.getLeftEndPoint().getY();
    double resultXRightEndLane2 = lane2.getRightEndPoint().getX();
    double resultYRightEndLane2 = lane2.getRightEndPoint().getY();

    assertEquals(expXLeftStartLane1, resultXLeftStartLane1, 2.1);
    assertEquals(expYLeftStartLane1, resultYLeftStartLane1, 2.1);
    assertEquals(expXRightStartLane1, resultXRightStartLane1, 2.1);
    assertEquals(expYRightStartLane1, resultYRightStartLane1, 2.1);
    assertEquals(expXLeftEndLane1, resultXLeftEndLane1, 2.1);
    assertEquals(expYLeftEndLane1, resultYLeftEndLane1, 2.1);
    assertEquals(expXRightEndLane1, resultXRightEndLane1, 2.1);
    assertEquals(expYRightEndLane1, resultYRightEndLane1, 2.1);

    assertEquals(expXLeftStartLane2, resultXLeftStartLane2, 2.1);
    assertEquals(expYLeftStartLane2, resultYLeftStartLane2, 2.1);
    assertEquals(expXRightStartLane2, resultXRightStartLane2, 2.1);
    assertEquals(expYRightStartLane2, resultYRightStartLane2, 2.1);
    assertEquals(expXLeftEndLane2, resultXLeftEndLane2, 2.1);
    assertEquals(expYLeftEndLane2, resultYLeftEndLane2, 2.1);
    assertEquals(expXRightEndLane2, resultXRightEndLane2, 2.1);
    assertEquals(expYRightEndLane2, resultYRightEndLane2, 2.1);

  }

  /**
   * This test checks if right and left parameters are calculated correctly if a
   * horizontal road is created and its direction is to the right like this: ->
   * with two lanes where one is IDENTICAL and the other OPPOSITE.
   */
  @Test
  public void testLanesHorizontalRight2() {
    System.out.println("Lanes horizontal right");

    Point startLeft = new Point(100, 100);
    Point endLeft = new Point(400, 100);

    setUp(startLeft, endLeft, Lane.Direction.IDENTICAL, Lane.Direction.OPPOSITE);
    /*
     System.out.println("lane1LeftStartPointX: " + lane1.getLeftStartPoint().getX());
     System.out.println("lane1LeftStartPointY: " + lane1.getLeftStartPoint().getY());
     System.out.println("lane1LeftEndPointX: " + lane1.getLeftEndPoint().getX());
     System.out.println("lane1LeftEndPointY: " + lane1.getLeftEndPoint().getY());
     System.out.println("lane1RightStartPointX: " + lane1.getRightStartPoint().getX());
     System.out.println("lane1RightStartPointY: " + lane1.getRightStartPoint().getY());
     System.out.println("lane1RightEndPointX: " + lane1.getRightEndPoint().getX());
     System.out.println("lane1RightEndPointY: " + lane1.getRightEndPoint().getY());
     System.out.println("lane2LeftStartPointX: " + lane2.getLeftStartPoint().getX());
     System.out.println("lane2LeftStartPointY: " + lane2.getLeftStartPoint().getY());
     System.out.println("lane2LeftEndPointX: " + lane2.getLeftEndPoint().getX());
     System.out.println("lane2LeftEndPointY: " + lane2.getLeftEndPoint().getY());
     System.out.println("lane2RightStartPointX: " + lane2.getRightStartPoint().getX());
     System.out.println("lane2RightStartPointY: " + lane2.getRightStartPoint().getY());
     System.out.println("lane2RightEndPointX: " + lane2.getRightEndPoint().getX());
     System.out.println("lane2RightEndPointY: " + lane2.getRightEndPoint().getY());
     */
    double expXLeftStartLane1 = startLeft.getX();
    double expYLeftStartLane1 = startLeft.getY();
    double expXRightStartLane1 = startLeft.getX();
    double expYRightStartLane1 = startLeft.getY() + lane1.LANE_WIDTH;
    double expXLeftEndLane1 = endLeft.getX();
    double expYLeftEndLane1 = endLeft.getY();
    double expXRightEndLane1 = endLeft.getX();
    double expYRightEndLane1 = endLeft.getY() + lane1.LANE_WIDTH;
    double expXLeftStartLane2 = road.getRightEndPoint().getX();
    double expYLeftStartLane2 = road.getRightEndPoint().getY();
    double expXRightStartLane2 = road.getRightEndPoint().getX();
    double expYRightStartLane2 = road.getRightEndPoint().getY() - 22;
    double expXLeftEndLane2 = road.getRightStartPoint().getX();
    double expYLeftEndLane2 = road.getRightStartPoint().getY();
    double expXRightEndLane2 = road.getRightStartPoint().getX();
    double expYRightEndLane2 = road.getRightStartPoint().getY() - 22;

    double resultXLeftStartLane1 = lane1.getLeftStartPoint().getX();
    double resultYLeftStartLane1 = lane1.getLeftStartPoint().getY();
    double resultXRightStartLane1 = lane1.getRightStartPoint().getX();
    double resultYRightStartLane1 = lane1.getRightStartPoint().getY();
    double resultXLeftEndLane1 = lane1.getLeftEndPoint().getX();
    double resultYLeftEndLane1 = lane1.getLeftEndPoint().getY();
    double resultXRightEndLane1 = lane1.getRightEndPoint().getX();
    double resultYRightEndLane1 = lane1.getRightEndPoint().getY();
    double resultXLeftStartLane2 = lane2.getLeftStartPoint().getX();
    double resultYLeftStartLane2 = lane2.getLeftStartPoint().getY();
    double resultXRightStartLane2 = lane2.getRightStartPoint().getX();
    double resultYRightStartLane2 = lane2.getRightStartPoint().getY();
    double resultXLeftEndLane2 = lane2.getLeftEndPoint().getX();
    double resultYLeftEndLane2 = lane2.getLeftEndPoint().getY();
    double resultXRightEndLane2 = lane2.getRightEndPoint().getX();
    double resultYRightEndLane2 = lane2.getRightEndPoint().getY();

    assertEquals(expXLeftStartLane1, resultXLeftStartLane1, 2.1);
    assertEquals(expYLeftStartLane1, resultYLeftStartLane1, 2.1);
    assertEquals(expXRightStartLane1, resultXRightStartLane1, 2.1);
    assertEquals(expYRightStartLane1, resultYRightStartLane1, 2.1);
    assertEquals(expXLeftEndLane1, resultXLeftEndLane1, 2.1);
    assertEquals(expYLeftEndLane1, resultYLeftEndLane1, 2.1);
    assertEquals(expXRightEndLane1, resultXRightEndLane1, 2.1);
    assertEquals(expYRightEndLane1, resultYRightEndLane1, 2.1);

    assertEquals(expXLeftStartLane2, resultXLeftStartLane2, 2.1);
    assertEquals(expYLeftStartLane2, resultYLeftStartLane2, 2.1);
    assertEquals(expXRightStartLane2, resultXRightStartLane2, 2.1);
    assertEquals(expYRightStartLane2, resultYRightStartLane2, 2.1);
    assertEquals(expXLeftEndLane2, resultXLeftEndLane2, 2.1);
    assertEquals(expYLeftEndLane2, resultYLeftEndLane2, 2.1);
    assertEquals(expXRightEndLane2, resultXRightEndLane2, 2.1);
    assertEquals(expYRightEndLane2, resultYRightEndLane2, 2.1);

  }

}
