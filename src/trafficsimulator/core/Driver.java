/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.core;

/**
 *
 * @author Eddy
 */
public abstract class Driver {
    
    protected String name;
    protected double carTopSpeed;
    protected double busTopSpeed;
    protected double carMaxAcceleration;
    protected double busMaxAcceleration;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCarTopSpeed() {
        return carTopSpeed;
    }

    public void setCarTopSpeed(double carTopSpeed) {
        this.carTopSpeed = carTopSpeed;
    }

    public double getBusTopSpeed() {
        return busTopSpeed;
    }

    public void setBusTopSpeed(double busTopSpeed) {
        this.busTopSpeed = busTopSpeed;
    }

    public double getCarMaxAcceleration() {
        return carMaxAcceleration;
    }

    public void setCarMaxAcceleration(double carMaxAcceleration) {
        this.carMaxAcceleration = carMaxAcceleration;
    }

    public double getBusMaxAcceleration() {
        return busMaxAcceleration;
    }

    public void setBusMaxAcceleration(double busMaxAcceleration) {
        this.busMaxAcceleration = busMaxAcceleration;
    }

    
    
    
   
    
    
}
