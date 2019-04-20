package gameLogic;

import java.util.*;

/**
 * This interface is responsible for „decisions” or actions that must be
 * performed at certain cells.
 */
interface CellLogic {

	/**
	 * 
	 * This method triggers actions bound to the respective cell, and decides if the
	 * train can leave the current cell and advance to the next cell along the path.
	 */
	public boolean LogicRequest(Car car);
	
	@Override
	public String toString();

}