package userInterface;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import gameLogic.Segment;
import gameLogic.vec2;


public class UIsegment {
	
	private final Segment segment;
	private vec2 s,p;
	
	public UIsegment(Segment segment) {
		this.segment = segment;
	}
	
	public void  calculate( GameDisplay gameDisplay) {
		p = gameDisplay.GetScreenPosition(vec2.sum(segment.getPosition(), new vec2(-3.5, -3.5)));
		s = gameDisplay.GetScreenPosition(vec2.sum(segment.getPosition(), new vec2(3.5, 3.5)));
		s = vec2.differance(s, p);
	}
	
	public void paint (Graphics g, GameDisplay gameDisplay) {
		Random random = new Random();
		g.setColor(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat(), 0.25f));
		//g.fillRect(Math.round(p.x), Math.round(p.y),Math.round(s.x), Math.round(s.y));
		g.drawImage(segment.getTexture().getImage(segment.getRotation()), Math.round(p.x), Math.round(p.y),Math.round(s.x), Math.round(s.y), null);
	}
}
