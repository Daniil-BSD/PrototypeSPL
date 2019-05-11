package userInterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import gameLogic.*;

public class UIcar {

	public static final float WIDTH = 1f;
	
	private final Car car;
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
	
	public void paint (Graphics g, GameDisplay gameDisplay) {
		if(draw) {
			g.setColor(color);
			g.fillPolygon(polygon);
		}
	}
}