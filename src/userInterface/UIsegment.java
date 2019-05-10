package userInterface;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.IOException;

import gameLogic.Segment;
import gameLogic.vec2;
import segments.Fork;
import segments.Straight;

public class UIsegment {
	
	private final Segment segment;
	private BufferedImage image;
	public UIsegment(Segment segment) {
		this.segment = segment;
	}
	
	public void paint (Graphics g, GameDisplay gameDisplay) {
		if(segment.getClass().equals(Straight.class)) {
			try {
				image = ImageIO.read(UIsegment.class.getResource("/Textures/FullStraight.png"));
			} catch (IOException e) {
			// TODO Auto-generated catch block
				System.out.println("Incorrect Image!");
			}
		}
		if(segment.getClass().equals(Fork.class)) {
			try {
				image = ImageIO.read(UIsegment.class.getResource("/Textures/Fork.png"));
			} catch (IOException e) {
			// TODO Auto-generated catch block
				System.out.println("Incorrect Image!");
			}
			
		}
		vec2 p = gameDisplay.GetScreenPosition(vec2.sum(segment.getPosition(), new vec2(-3.5, -3.5)));
		vec2 s = gameDisplay.GetScreenPosition(vec2.sum(segment.getPosition(), new vec2(3.5, 3.5)));
		s = vec2.differance(s, p);
		Random random = new Random();
		g.setColor(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat(), 0.25f));
		//g.fillRect(Math.round(p.x), Math.round(p.y),Math.round(s.x), Math.round(s.y));
		g.drawImage(image, Math.round(p.x), Math.round(p.y),Math.round(s.x), Math.round(s.y), null);
	}
}
