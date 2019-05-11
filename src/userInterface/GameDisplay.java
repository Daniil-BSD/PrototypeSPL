package userInterface;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

import gameLogic.Car;
import gameLogic.Segment;
import gameLogic.vec2;

@SuppressWarnings("serial")
public class GameDisplay extends Canvas {

	private LinkedList<UIcar> uIcars;
	private LinkedList<UIsegment> uIsegments;

	private float scale;
	private vec2 origin;
	private vec2 offset;

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
	
	public vec2 GetScreenPosition( vec2 globalPosition) {
		return vec2.sum(vec2.sum(globalPosition, offset).scaledCopy(scale), origin);
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
	
	public void Update() {
		updateOrigin();
		for (UIsegment uIsegment : uIsegments) {
			uIsegment.calculate(this);
		}for (UIcar uIcar : uIcars) {
			uIcar.calculate(this);
		}
		repaint();
	}

	public void paint(Graphics g) {
		setBackground(Color.WHITE);
		setForeground(Color.BLACK);
		for (UIsegment uIsegment : uIsegments) {
			uIsegment.paint(g, this);
		}for (UIcar uIcar : uIcars) {
			uIcar.paint(g, this);
		}
	}

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

	public void AddCar(Car car) {
		uIcars.add(new UIcar(car));
		if (car.getAttachedCar() != null)
			this.AddCar(car.getAttachedCar());
	}

	public void AddSegment(Segment segment) {
		uIsegments.add(new UIsegment(segment));
	}
}
