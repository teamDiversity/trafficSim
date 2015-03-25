/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.utils;

import java.util.List;

/**
 *
 * @author balazs
 */
public class Point {

  public double x, y;

  public Point() {
    x = 0;
    y = 0;
  }
  
  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }
  
  @Override
  public boolean equals(Object o){
    if(o == null) return false;
    if(!(o instanceof Point)) return false;
    Point p = (Point)o;
    return x==p.x && y==p.y;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 59 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
    hash = 59 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
    return hash;
  }

  @Override
  public String toString(){
    return "("+x+", "+y+")";
  }

  public double getX() {
    return x;
  }

  public void setX(double x) {
    this.x = x;
  }

  public double getY() {
    return y;
  }

  public void setY(double y) {
    this.y = y;
  }

  public Point plus(Point p) {
    return new Point(this.x + p.x, this.y + p.y);
  }

  public Point minus(Point p) {
    return new Point(this.x - p.x, this.y - p.y);
  }

  public Point mult(double k) {
    return new Point(this.x * k, this.y * k);
  }

  public Point div(double k) {
    return new Point(this.x / k, this.y / k);
  }

  public double distanceFromOrigin() {
    Point origin = new Point();
    return distance(origin);
  }
  
  public Point unitVector(){
    return div(distanceFromOrigin());
  }

  public double distance(Point p) {
    double dx = x - p.x;
    double dy = y - p.y;
    double distance = Math.sqrt(dx * dx + dy * dy);
    return distance;
  }

  public boolean inSameQuadrant(Point p) {
    if (getX() > 0 && p.getX() < 0) {
      return false;
    }
    if (getX() < 0 && p.getX() > 0) {
      return false;
    }
    if (getY() > 0 && p.getY() < 0) {
      return false;
    }
    if (getY() < 0 && p.getY() > 0) {
      return false;
    }
    return true;
  }

  public Point rotateVector(double degrees) {
    double X = Math.round(this.x * Math.cos(degrees) - this.y * Math.sin(degrees));
    double Y = Math.round(this.x * Math.sin(degrees) + this.y * Math.cos(degrees));
    return new Point(X, Y);
  }

  public double angleVector() {
    if (y == 0) {
      if (x < 0) {
        return Math.PI;
      } else {
        return 0;
      }
    } else if (x < 0) {
      if (y > 0) {
        return Math.atan(this.y / this.x) + Math.PI;
      } else {
        return Math.atan(this.y / this.x) - Math.PI;
      }
    } else {
      return Math.atan(this.y / this.x);
    }
  }
  
  public double angleVectorDegree() {
    if (y == 0) {
      if (x < 0) {
        return Math.PI*(180/Math.PI);
      } else {
        return 0;
      }
    } else if (x < 0) {
      if (y > 0) {
        return (Math.atan(this.y / this.x) + Math.PI)*(180/Math.PI);
      } else {
        return (Math.atan(this.y / this.x) - Math.PI)*(180/Math.PI);
      }
    } else {
      return Math.atan(this.y / this.x)*(180/Math.PI);
    }
  }
  
  
  public static Point centroid(List<Point> points){
    double x = 0.;
    double y = 0.;
    
    for (Point point : points) {
      x += point.getX();
      y += point.getY();
    }

    x = x/points.size();
    y = y/points.size();

    return new Point(x, y);
  }
  
  public  static double distanceBetweenPoints(Point x, Point y){
      return Math.sqrt(Math.pow((y.getX()-x.getX()),2) + Math.pow((y.getY()-x.getX()),2)); 
  }
}
