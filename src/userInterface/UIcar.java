package userInterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import gameLogic.*;
//This class is responsible for drawing the trains. 
//It will also be possible to know if the train is full or empty

public class UIcar {

	public static final float WIDTH = 1f;
	/**
	 * This attribute stores the car 
	 * that is drawn when the draw method is called.
	 */
	private final Car car;
	/**
	 * This attribute is a polygon of 4 points that is needed 
	 * to draw the car using the drawPolygon(Polygon p) method 
	 * of the Graphics class. (java.awt.Polygon)
	 */
	private Polygon polygon;
	private boolean draw = false;
	private Color color;
	
	public UIcar(Car car) {
		this.car = car;
	}
	
	public void calculate(GameDisplay gameDisplay) {
		Cell c1 = car.getCell();
		Cell c2 = car.getRearCell();
		draw = c1.IsVisisble() && c2.IsVisisble();
		if(draw) {
			vec2 p1 = gameDisplay.GetScreenPosition(c1.getGlobalPosition());
			vec2 p2 = gameDisplay.GetScreenPosition(c2.getGlobalPosition());
			polygon = vec2.getPolygon(WIDTH * gameDisplay.getScale(), p1, p2);
			color = car.getColor();
		}
	}
	/**
	 * This method gets the position and rotation from the 
	 * stored car and draws the car which is a polygon.
	 */
	public void paint (Graphics g, GameDisplay gameDisplay) {
		if(draw) {
			g.setColor(color);
			g.fillPolygon(polygon);
		}
	}
}