package gameLogic;

import java.util.*;

/**
 * This class implements the logic of passengers embarking and
 *  disembarking depending on the colors of the train and the station. 
 */
class StationLogic implements CellLogic {
	
	/**
	 * This attribute stores the boolean flag to identify the FinalStation.
	 */
	private boolean finalReportFlag = false;

	
	 /**
     * This attribute stores the station to which the logic belongs.
     */
    private Station parentStation;

    /**
     * This constructor assigns the given station as the parent station.
     */
    public StationLogic(Station parentStation) {
    	this.parentStation = parentStation;
    }

    /**
     * This method executes the corresponding logic.
     */
    @Override
    public boolean LogicRequest(Car car) {
		Colors colors[] = parentStation.GetColors();
		if(car.CurrentlyAtTheStation_Locomotive(colors)) {
			return false;
		}else {
			if(parentStation.IsFinal()) {
				if(!finalReportFlag) {
					finalReportFlag = true;
					LevelContainer.FinalReport(car);
				}
				return false;
			}
			return true;
		}
    }
    
    @Override
    public String toString() {
    	return "StationLogic";
    }
}