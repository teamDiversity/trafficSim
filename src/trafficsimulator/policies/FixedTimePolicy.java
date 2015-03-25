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
public class FixedTimePolicy {
    
    
    private Lights lights;
    
    public FixedTimePolicy(boolean peaktime){
        lights = new Lights();
        if(peaktime){
            
            this.lights.setGreenLightDuration(100); 
            this.lights.setYellowLightDuration(10); 
            this.lights.setRedLightDuration(100); 
            this.lights.setRedYellowDuration(30);
        }else{
            this.lights.setGreenLightDuration(50); 
            this.lights.setYellowLightDuration(20); 
            this.lights.setRedLightDuration(50);
            this.lights.setRedYellowDuration(30);
        }
        
    }
    
    //incase you need to override the set cofigurations
    public FixedTimePolicy(int greenLightDuration, int yellowLightDuration, int redLightDuration){
        
            this.lights.setGreenLightDuration(greenLightDuration); 
            this.lights.setYellowLightDuration(yellowLightDuration); 
            this.lights.setRedLightDuration(redLightDuration);
    }
    
    public Lights getCurrentConf(){
        return this.lights;
    }
}