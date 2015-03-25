
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
public class TrafficPolicy {
    
    private int greenLightDuration;
    private int yellowLightDuration;
    private int redYellowDuration;
    private int redLightDuration;

    
    private final boolean fixedTime;

    

    public boolean isFixedTime() {
        return fixedTime;
    }

    public TrafficPolicy(boolean peaktime) {
        this.fixedTime = peaktime;
        if(peaktime){
            
            this.setGreenLightDuration(100); 
            this.setYellowLightDuration(10); 
            this.setRedLightDuration(100); 
            this.setRedYellowDuration(30);
        }else{
            this.setGreenLightDuration(50); 
            this.setYellowLightDuration(20); 
            this.setRedLightDuration(50);
            this.setRedYellowDuration(30);
        }
    }
    
    public int getGreenLightDuration() {
        return greenLightDuration;
    }

    private void setGreenLightDuration(int greenLightDuration) {
        this.greenLightDuration = greenLightDuration;
    }

    public int getYellowLightDuration() {
        return yellowLightDuration;
    }

    private void setYellowLightDuration(int yellowLightDuration) {
        this.yellowLightDuration = yellowLightDuration;
    }

    public int getRedYellowDuration() {
        return redYellowDuration;
    }

    private void setRedYellowDuration(int redYellowDuration) {
        this.redYellowDuration = redYellowDuration;
    }
    
    public int getRedLightDuration() {
        return redLightDuration;
    }

    private void setRedLightDuration(int redLightDuration) {
        this.redLightDuration = redLightDuration;
    }
}
