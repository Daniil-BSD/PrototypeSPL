package gameLogic;

import java.util.*;

import javax.xml.stream.events.EndDocument;

/**
 * This class is responsible for bigger units of space of the level than cells -
 * segments. A segment is a part of the level that is either a tunnel, a
 * station, or a fork. Straight and Turn classes are also derived from segment,
 * but their purpose is mainly in graphically representing the level. Other than
 * that, a segment is a sequence of cells along two paths in opposite directions
 * for the trains to traverse.
 */
abstract class Segment {

	/**
	 * Default constructor
	 */
	protected Segment() {
		this.id = this.getClass().getSimpleName() + System.identityHashCode(this);
	}

	/**
	 * This constructor sets the given id to the segment.
	 */

	public Segment(String id) {
		this.id = id;
	}

	/**
	 * This attribute stores the cell of the segment.
	 */

	protected Cell cells[];

	/**
	 * This attribute stores the first cell of the segment
	 */
	protected PathEnd end0;

	/**
	 * This attribute stores the last cell of the segment.
	 */
	protected PathEnd end1;

	/**
	 * A path from B to A is stored in this attribute.
	 */
	protected Path path10;

	/**
	 * A path from A to B is stored in this attribute.
	 */
	protected Path path01;

	/**
	 * This attribute is needed to find a needed segment in the level by its string
	 * identifier. It is needed in order to build the level by connecting segments.
	 */

	public final String id;

	/**
	 * This method compares the given string id with the segments string id and
	 * returns true if they match. False otherwise.
	 * 
	 */
	public boolean AreYou(String segmentID) {
		return id.equals(segmentID);
	}

	/**
	 * This method compares the given segment with the current segment and returns
	 * true if they match. False otherwise.
	 * 
	 */
	public boolean AreYou(Segment segment) {
		return this == segment;
	}

	/**
	 * This method returns a cell that is an edge of the segment if this cell is
	 * free.
	 */
	public Cell GetFreeEnd(int endID) {
		if (!IsEndFree(endID))
			return null;
		if (endID == 0)
			return path01.GetStart();
		if (endID == 1)
			return path10.GetStart();
		System.out.println("Incorrect end(s)\n");
		return null;
	}

	/**
	 * This method returns true if the edge of the segment that is identified by the
	 * integer parameter is free.
	 * 
	 */

	public boolean IsEndFree(int endID) {
		if (endID == 0)
			return !end0.HasConnection();
		if (endID == 1)
			return !end1.HasConnection();
		return false;
	}

	/**
	 *
	 * This method connects the segments by its ends, one of which is passed on as a
	 * parameter to this method.
	 */
	public void ConnectTo(int endID, Cell end) {
		if (!IsEndFree(endID))
			return;
		if (endID == 0)
			end0.Connect(end);
		if (endID == 1)
			end1.Connect(end);
	}

	/**
	 * This method is needed to store the segment which was selected by the player
	 * to construct a tunnel, or to control a switch at the chosen fork.
	 */
	public void Select() {}

	/**
	 * This method gets the path that ends with the cell identified by the given integer id.
	 */
	public Path GetPathEndingWith(int endID) {

		if (endID == 0)
			return path01;
		if (endID == 1)
			return path10;
		return null;

	}

	public abstract void printFull();
	
}