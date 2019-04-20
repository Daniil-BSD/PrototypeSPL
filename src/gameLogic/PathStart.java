package gameLogic;

import java.util.*;

/**
 * This class is needed to set the paths for the trains.
 */
class PathStart implements CellLogic {

    /**
     * Copy constructor
     */
    public PathStart(Path path) {
    	this.path = path;
    }

    /**
     *  This attribute stores the path along which the train will 
     *  advance further into the level.
     */
    private Path path;


    /**
     * This method executes the logic of the first cell of the path.
     */
    public boolean LogicRequest(Car car) {
    	car.SetPath(path);
    	return true;
    }
    
    @Override
    public String toString() {
    	return "PathStart";
    }

}