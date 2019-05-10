package userInterface;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import gameLogic.Segment;
import gameLogic.vec2;


public class UIsegment {
	
	private final Segment segment;
	public UIsegment(Segment segment) {
		this.segment = segment;
	}
	
	public void paint (Graphics g, GameDisplay gameDisplay) {
		
		vec2 p = gameDisplay.GetScreenPosition(vec2.sum(segment.getPosition(), new vec2(-3.5, -3.5)));
		vec2 s = gameDisplay.GetScreenPosition(vec2.sum(segment.getPosition(), new vec2(3.5, 3.5)));
		s = vec2.differance(s, p);
		Random random = new Random();
		g.setColor(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat(), 0.25f));
		//g.fillRect(Math.round(p.x), Math.round(p.y),Math.round(s.x), Math.round(s.y));
		g.drawImage(segment.getImage(), Math.round(p.x), Math.round(p.y),Math.round(s.x), Math.round(s.y), null);
	}
}
