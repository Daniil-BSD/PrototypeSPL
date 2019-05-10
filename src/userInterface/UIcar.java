package userInterface;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import gameLogic.*;

public class UIcar {

	public static final float WIDTH = 1f;
	
	private final Car car;
	
	public UIcar(Car car) {
		this.car = car;
	}
	
	public void paint (Graphics g, GameDisplay gameDisplay) {
		Cell c1 = car.getCell();
		Cell c2 = car.getRearCell();
		if(c1.IsVisisble() && c2.IsVisisble()) {
			vec2 p1 = gameDisplay.GetScreenPosition(c1.getGlobalPosition());
			vec2 p2 = gameDisplay.GetScreenPosition(c2.getGlobalPosition());
			Random random = new Random();
			g.setColor(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat()));
			g.fillPolygon(vec2.getPolygon(WIDTH * gameDisplay.getScale(), p1, p2));
		}
	}
}