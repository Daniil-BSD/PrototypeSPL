package userInterface;
import java.awt.Graphics;
import gameLogic.Segment;
import gameLogic.vec2;

//This class is responsible for drawing the segments. Stations, tunnels, 
//straights’ appearance will be implemented by this class.
public class UIsegment {
	/**
	 * Stores the segments that is drawn when a draw method is called.
	 */
	private final Segment segment;
	private vec2 s,p;
	private boolean ready = false;
	
	public UIsegment(Segment segment) {
		this.segment = segment;
	}
	/**
	 * Calculates the positions of the graphical components.
	 */
	public void  calculate( GameDisplay gameDisplay) {
		vec2 v = segment.getSize().scaledCopy(0.5);
		p = gameDisplay.GetScreenPosition(vec2.difference(segment.getPosition(), v));
		s = gameDisplay.GetScreenPosition(vec2.sum(segment.getPosition(), v));
		s = vec2.difference(s, p);
		ready = true;
	}
	/**
	 *  Paints the segments on display.
	 */
	public void paint (Graphics g, GameDisplay gameDisplay) {
		if(!ready)
			calculate(gameDisplay);
		g.drawImage(segment.getTexture().getImage(segment.getRotation()), Math.round(p.x), Math.round(p.y),Math.round(s.x), Math.round(s.y), null);
	}
}
