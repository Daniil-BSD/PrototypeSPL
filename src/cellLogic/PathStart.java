package cellLogic;

import gameLogic.Car;
import gameLogic.CellLogic;
import gameLogic.Path;

/**
 * This class is needed to set the paths for the trains.
 */
@SuppressWarnings("serial")
public class PathStart implements CellLogic {

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