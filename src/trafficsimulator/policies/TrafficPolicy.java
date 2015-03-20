
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
    private boolean fixedTime;

    public TrafficPolicy(boolean fixedTime) {
        this.fixedTime = fixedTime;
    }
    
    
    
    public Lights getActiveConfigFromActivePolicy(){
        if(fixedTime){
            FixedTimePolicy fixedTime = new FixedTimePolicy(true);
            return fixedTime.getCurrentConf();
        }else{
            //congestion control
        }
        return null;
    } 
    
}
