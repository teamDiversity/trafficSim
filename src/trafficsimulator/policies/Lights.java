/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.policies;

/**
 *
 * @author Eddy
 */
public class Lights {
    
    private int greenLightDuration;
    private int yellowLightDuration;
    private int redYellowDuration;

    
    private int redLightDuration;

    public Lights() {
    }

    public Lights(int greenLightDuration, int yellowLightDuration, int redLightDuration) {
        this.greenLightDuration = greenLightDuration;
        this.yellowLightDuration = yellowLightDuration;
        this.redLightDuration = redLightDuration;
    }

    public int getGreenLightDuration() {
        return greenLightDuration;
    }

    public void setGreenLightDuration(int greenLightDuration) {
        this.greenLightDuration = greenLightDuration;
    }

    public int getYellowLightDuration() {
        return yellowLightDuration;
    }

    public void setYellowLightDuration(int yellowLightDuration) {
        this.yellowLightDuration = yellowLightDuration;
    }

    public int getRedLightDuration() {
        return redLightDuration;
    }

    public void setRedLightDuration(int redLightDuration) {
        this.redLightDuration = redLightDuration;
    }
    
    public int getRedYellowDuration() {
        return redYellowDuration;
    }

    public void setRedYellowDuration(int redYellowDuration) {
        this.redYellowDuration = redYellowDuration;
    }
}