package gameLogic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

import cellLogic.PathEnd;
import userInterface.UIsegment;

/**
 * This class is responsible for bigger units of space of the level than cells -
 * segments. A segment is a part of the level that is either a tunnel, a
 * station, or a fork. Straight and Turn classes are also derived from segment,
 * but their purpose is mainly in graphically representing the level. Other than
 * that, a segment is a sequence of cells along two paths in opposite directions
 * for the trains to traverse.
 */
public abstract class Segment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 114076703858761588L;

	/**
	 * Default constructor
	 */
	protected Segment() {
		this.id = this.getClass().getSimpleName() + System.identityHashCode(this);
		rotation = 0;
		position = new vec2();
	}

	/**
	 * This constructor sets the given id to the segment.
	 */

	public Segment(String id) {
		this.id = id;
		rotation = 0;
		position = new vec2(0,0);
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
	
	private vec2 position;
	
	private double rotation;

	private BufferedImage image;
	
	
	public vec2 getPosition() {
		return position;
	}

	public void setPosition(vec2 position) {
		this.position = position;
		UpdateCellPositions();
	}

	public double getRotation() {
		return rotation;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
		UpdateCellPositions();
	}
	
	public void UpdateCellPositions() {
		for (Cell cell : cells) {
			cell.setGlobalPosition(vec2.sum( cell.getLocalPosition().rotatedCopy(rotation), position));
		}
	}

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
			return path10;
		if (endID == 1)
			return path01;
		return null;

	}

	public void printFull() {
		System.out.print("\t path01");
		path01.print();
		System.out.print("\t path10");
		path10.print();
	};
	
	public UIsegment getUIssegment() {
		return new UIsegment(this);
	}
	
	public BufferedImage getImage() {
		return image;
		
	}
	
	public void setImage(String path) {
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("incorrect Image!");		
			
		}
		
	}
	
}