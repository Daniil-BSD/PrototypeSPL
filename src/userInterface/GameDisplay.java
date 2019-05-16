package userInterface;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

import gameLogic.Car;
import gameLogic.Segment;
import gameLogic.vec2;
/**
 * This class is responsible for drawing the level 
 * and all the objects inside it. 
 */
@SuppressWarnings("serial")
public class GameDisplay extends Canvas {
	/**
	 * This list stores the ui elements that draw cars.
	 */
	private LinkedList<UIcar> uIcars;
	/**
	 * This list stores all the UI elements that draw segments.
	 */
	private LinkedList<UIsegment> uIsegments;
	private static final Color background = new Color(102, 157, 49);

	private float scale;
	private vec2 origin = new vec2(0.0f,0.0f);
	private vec2 offset = new vec2(0.0f,0.0f);

	public vec2 getOffset() {
		return offset;
	}

	public void setOffset(vec2 offset) {
		this.offset = offset;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}
	/**
	 * Camera actions.
	 */
	public void zoomIn() {
		scale *= 1.25f;
		Update();
	}

	public void zoomOut() {
		scale *= 0.8f;
		Update();
	}

	public void moveUp() {
		offset.y -= 5;
		Update();
	}

	public void moveDown() {
		offset.y += 5;
		Update();
	}

	public void moveLeft() {
		offset.x -= 5;
		Update();
	}

	public void moveRight() {
		offset.x += 5;
		Update();
	}
	/**
	 * Gets position of the vec2 relative to the screen.
	 */
	public vec2 GetScreenPosition( vec2 globalPosition) {
		return vec2.sum(vec2.sum(globalPosition, offset).scaledCopy(scale), origin);
	}
	/**
	 * Gets position of the vec2 relative to the global coordinates.
	 */
	public vec2 GetWorldPosition( vec2 screenPosition) {
		return vec2.difference(vec2.difference(screenPosition, origin).scaledCopy(1 / scale), offset);
	}

	public vec2 updateOrigin() {
		return origin = new vec2(this.getWidth() / 2f, this.getHeight() / 2f);
	}

	public GameDisplay() {
		uIcars = new LinkedList<UIcar>();
		uIsegments = new LinkedList<UIsegment>();
		scale = 50;
		offset = new vec2();
	}
	/**
	 * Updates components in the display.
	 */
	public void Update() {
		updateOrigin();
		for (UIsegment uIsegment : uIsegments) {
			uIsegment.calculate(this);
		}for (UIcar uIcar : uIcars) {
			uIcar.calculate(this);
		}
		repaint();
	}
	/**
	 * This method draws an empty canvas,
	 *  ready for other objects to be drawn.
	 */
	public void paint(Graphics g) {
		setBackground(background);
		setForeground(Color.BLACK);
		for (UIsegment uIsegment : uIsegments) {
			uIsegment.paint(g, this);
		}for (UIcar uIcar : uIcars) {
			uIcar.paint(g, this);
		}
	}
	/**
	 * This method deletes the current list elements and populates them with new objects, 
	 * given as parameters in the form of arraylists.	
	 */
	public void FullRedraw(ArrayList<? extends Segment> segments, ArrayList<? extends Car> trains) {
		uIcars = new LinkedList<UIcar>();
		uIsegments = new LinkedList<UIsegment>();
		for (Car car : trains) {
			AddCar(car);
		}
		for (Segment segment : segments) {
			AddSegment(segment);
		}
		Update();
	}
	/**
	 * This method adds a new UICar element with the car 
	 * given as  the constructor parameter.
	 */
	public void AddCar(Car car) {
		uIcars.add(new UIcar(car));
		if (car.getAttachedCar() != null)
			this.AddCar(car.getAttachedCar());
	}
	/**
	 *  This method adds a new UISegment element with the 
	 *  segment given as  the constructor parameter.
	 */
	public void AddSegment(Segment segment) {
		uIsegments.add(new UIsegment(segment));
	}
}
