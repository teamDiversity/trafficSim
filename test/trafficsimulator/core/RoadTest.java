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
public class RoadTest {

  private Road road;

  public void setUp(Point start, Point end) {
    road = new Road(start, end);
    Lane lane1 = road.addLane(Lane.Direction.IDENTICAL);
    Lane lane2 = road.addLane(Lane.Direction.OPPOSITE);

  }

  /**
   * This test checks if right parameters are calculated correctly if a
   * horizontal road is created and its direction is to the right, like this: ->
   * .
   */
  @Test
  public void testRoadHorizontalRight() {
    System.out.println("Road horizontal right");

    setUp(new Point(100, 100), new Point(400, 100));

    double expYStart = road.getLeftStartPoint().getY() + road.calculateWidth();
    double expXStart = road.getLeftStartPoint().getX();
    double expYEnd = road.getLeftEndPoint().getY() + road.calculateWidth();
    double expXEnd = road.getLeftEndPoint().getX();

    double resultYStart = road.getRightStartPoint().getY();
    double resultXStart = road.getRightStartPoint().getX();
    double resultYEnd = road.getRightEndPoint().getY();
    double resultXEnd = road.getRightEndPoint().getX();

    assertEquals(expYStart, resultYStart, 0.001);
    assertEquals(expXStart, resultXStart, 0.001);
    assertEquals(expYEnd, resultYEnd, 0.001);
    assertEquals(expXEnd, resultXEnd, 0.001);

  }

  /**
   * This test checks if right parameters are calculated correctly if a
   * horizontal road is created and its direction is to the left, like this: <-
   * .
   */
  @Test
  public void testRoadHorizontalLeft() {
    System.out.println("Road horizontal left");

    setUp(new Point(400, 100), new Point(100, 100));

    double expYStart = road.getLeftStartPoint().getY() - road.calculateWidth();
    double expXStart = road.getLeftStartPoint().getX();
    double expYEnd = road.getLeftEndPoint().getY() - road.calculateWidth();
    double expXEnd = road.getLeftEndPoint().getX();

    double resultYStart = road.getRightStartPoint().getY();
    double resultXStart = road.getRightStartPoint().getX();
    double resultYEnd = road.getRightEndPoint().getY();
    double resultXEnd = road.getRightEndPoint().getX();

    assertEquals(expYStart, resultYStart, 0.001);
    assertEquals(expXStart, resultXStart, 0.001);
    assertEquals(expYEnd, resultYEnd, 0.001);
    assertEquals(expXEnd, resultXEnd, 0.001);

  }

  /**
   * This test checks if right parameters are calculated correctly if a vertical
   * road is created and its direction is to the up, like this: ^ | .
   */
  @Test
  public void testRoadVerticalUp() {
    System.out.println("Road vertical up");

    setUp(new Point(100, 400), new Point(100, 100));

    double expYStart = road.getLeftStartPoint().getY();
    double expXStart = road.getLeftStartPoint().getX() + road.calculateWidth();
    double expYEnd = road.getLeftEndPoint().getY();
    double expXEnd = road.getLeftEndPoint().getX() + road.calculateWidth();

    double resultYStart = road.getRightStartPoint().getY();
    double resultXStart = road.getRightStartPoint().getX();
    double resultYEnd = road.getRightEndPoint().getY();
    double resultXEnd = road.getRightEndPoint().getX();

    assertEquals(expYStart, resultYStart, 0.001);
    assertEquals(expXStart, resultXStart, 0.001);
    assertEquals(expYEnd, resultYEnd, 0.001);
    assertEquals(expXEnd, resultXEnd, 0.001);

  }

  /**
   * This test checks if right parameters are calculated correctly if a vertical
   * road is created and its direction is to the right, like this: | v .
   */
  @Test
  public void testRoadVerticalDown() {
    System.out.println("Road vertical down");

    setUp(new Point(100, 100), new Point(100, 400));

    double expYStart = road.getLeftStartPoint().getY();
    double expXStart = road.getLeftStartPoint().getX() - road.calculateWidth();
    double expYEnd = road.getLeftEndPoint().getY();
    double expXEnd = road.getLeftEndPoint().getX() - road.calculateWidth();

    double resultYStart = road.getRightStartPoint().getY();
    double resultXStart = road.getRightStartPoint().getX();
    double resultYEnd = road.getRightEndPoint().getY();
    double resultXEnd = road.getRightEndPoint().getX();

    assertEquals(expYStart, resultYStart, 0.001);
    assertEquals(expXStart, resultXStart, 0.001);
    assertEquals(expYEnd, resultYEnd, 0.001);
    assertEquals(expXEnd, resultXEnd, 0.001);

  }

  /**
   * This test checks if right parameters are calculated correctly if a road
   * that has a downward slope is created and its direction is to the right,
   * like this: \ v .
   */
  @Test
  public void testRoadDownwardRight() {
    System.out.println("Road downward right");

    setUp(new Point(100, 100), new Point(400, 400));

    double expYStart = road.getLeftStartPoint().getY() + 35;
    double expXStart = road.getLeftStartPoint().getX() - 35;
    double expYEnd = road.getLeftEndPoint().getY() + 35;
    double expXEnd = road.getLeftEndPoint().getX() - 35;

    double resultYStart = road.getRightStartPoint().getY();
    double resultXStart = road.getRightStartPoint().getX();
    double resultYEnd = road.getRightEndPoint().getY();
    double resultXEnd = road.getRightEndPoint().getX();

    assertEquals(expYStart, resultYStart, 0.001);
    assertEquals(expXStart, resultXStart, 0.001);
    assertEquals(expYEnd, resultYEnd, 0.001);
    assertEquals(expXEnd, resultXEnd, 0.001);

  }

  /**
   * This test checks if right parameters are calculated correctly if a road
   * that has a downward slope is created and its direction is to the left, like
   * this: / v .
   */
  @Test
  public void testRoadDownwardLeft() {
    System.out.println("Road downward left");

    setUp(new Point(400, 100), new Point(100, 400));

    double expYStart = road.getLeftStartPoint().getY() - 35;
    double expXStart = road.getLeftStartPoint().getX() - 35;
    double expYEnd = road.getLeftEndPoint().getY() - 35;
    double expXEnd = road.getLeftEndPoint().getX() - 35;

    double resultYStart = road.getRightStartPoint().getY();
    double resultXStart = road.getRightStartPoint().getX();
    double resultYEnd = road.getRightEndPoint().getY();
    double resultXEnd = road.getRightEndPoint().getX();

    assertEquals(expYStart, resultYStart, 0.001);
    assertEquals(expXStart, resultXStart, 0.001);
    assertEquals(expYEnd, resultYEnd, 0.001);
    assertEquals(expXEnd, resultXEnd, 0.001);

  }

  /**
   * This test checks if right parameters are calculated correctly if a road
   * that has an upward slope is created and its direction is to the right, like
   * this: ^ / .
   */
  @Test
  public void testRoadUpwardRight() {
    System.out.println("Road upward right");

    setUp(new Point(100, 400), new Point(400, 100));

    double expYStart = road.getLeftStartPoint().getY() + 35;
    double expXStart = road.getLeftStartPoint().getX() + 35;
    double expYEnd = road.getLeftEndPoint().getY() + 35;
    double expXEnd = road.getLeftEndPoint().getX() + 35;

    double resultYStart = road.getRightStartPoint().getY();
    double resultXStart = road.getRightStartPoint().getX();
    double resultYEnd = road.getRightEndPoint().getY();
    double resultXEnd = road.getRightEndPoint().getX();

    assertEquals(expYStart, resultYStart, 0.001);
    assertEquals(expXStart, resultXStart, 0.001);
    assertEquals(expYEnd, resultYEnd, 0.001);
    assertEquals(expXEnd, resultXEnd, 0.001);

  }

  /**
   * This test checks if right parameters are calculated correctly if a road
   * that has an upward slope is created and its direction is to the left, like
   * this: ^ \.
   */
  @Test
  public void testRoadUpwardLeft() {
    System.out.println("Road upward left");

    setUp(new Point(400, 400), new Point(100, 100));

    double expYStart = road.getLeftStartPoint().getY() - 35;
    double expXStart = road.getLeftStartPoint().getX() + 35;
    double expYEnd = road.getLeftEndPoint().getY() - 35;
    double expXEnd = road.getLeftEndPoint().getX() + 35;

    double resultYStart = road.getRightStartPoint().getY();
    double resultXStart = road.getRightStartPoint().getX();
    double resultYEnd = road.getRightEndPoint().getY();
    double resultXEnd = road.getRightEndPoint().getX();

    assertEquals(expYStart, resultYStart, 0.001);
    assertEquals(expXStart, resultXStart, 0.001);
    assertEquals(expYEnd, resultYEnd, 0.001);
    assertEquals(expXEnd, resultXEnd, 0.001);

  }

}
