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
public class FixedTime {
    
    
    private Lights lights;
    
    public FixedTime(boolean peaktime){
        lights = new Lights();
        if(peaktime){
            
            this.lights.setGreenLightDuration(10); 
            this.lights.setYellowLightDuration(2); 
            this.lights.setRedLightDuration(10); 
            this.lights.setRedYellowDuration(3);
        }else{
            this.lights.setGreenLightDuration(5); 
            this.lights.setYellowLightDuration(2); 
            this.lights.setRedLightDuration(5);
            this.lights.setRedYellowDuration(3);
        }
        
    }
    
    //incase you need to override the set cofigurations
    public FixedTime(int greenLightDuration, int yellowLightDuration, int redLightDuration){
        
            this.lights.setGreenLightDuration(greenLightDuration); 
            this.lights.setYellowLightDuration(yellowLightDuration); 
            this.lights.setRedLightDuration(redLightDuration);
    }
    
    public Lights getCurrentConf(){
        return this.lights;
    }
}