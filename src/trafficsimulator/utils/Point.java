/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.utils;

/**
 *
 * @author balazs
 */
public class Point {
  public double x, y;
  
  public Point(){
    x = 0;
    y = 0;
  }
  
  public Point(double x, double y){
    this.x = x;
    this.y = y;
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
  
  public Point plus(Point p){
    return new Point(this.x + p.x, this.y + p.y);
  }
  
  public Point minus(Point p){
    return new Point(this.x - p.x, this.y - p.y);
  }
  
  public Point mult(double k){
    return new Point(this.x*k, this.y*k);
  }
  
  public Point div(double k){
    return new Point(this.x/k, this.y/k);
  }
  
  public double distanceFromOrigin(){
    Point origin = new Point();
		return distance(origin);
  }
  
  public double distance(Point p){
    double dx = x - p.x;
		double dy = y - p.y;
		double distance = Math.sqrt(dx*dx + dy*dy);
		return distance;
  }
  
  public boolean inSameQuadrant(Point p){
    if(getX() > 0 && p.getX() < 0) return false;
    if(getX() < 0 && p.getX() > 0) return false;
    if(getY() > 0 && p.getY() < 0) return false;
    if(getY() < 0 && p.getY() > 0) return false;
    return true;
  }
}
