/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator.policies;

import java.util.List;
import trafficsimulator.core.Lane;
import trafficsimulator.core.Vehicle;

/**
 *
 * @author Eddy
 */
public class CongestionControlPolicy {
    
    private Lane lane;
    
    public CongestionControlPolicy(Lane lane){
        
        this.lane = lane;
    }
    
    public boolean checkForCongestion(){
        List<Vehicle> vehiclesOnLane = lane.getVehicles();
        double totalLengthOfVehicle = 0;
        for(Vehicle v : vehiclesOnLane){
             totalLengthOfVehicle = totalLengthOfVehicle + v.getSize().height;
        }
        return (totalLengthOfVehicle == (lane.getLaneLength()*0.8))||(totalLengthOfVehicle > (lane.getLaneLength()*0.8));
    }
}
