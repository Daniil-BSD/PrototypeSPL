package userInterface;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

import gameLogic.Car;
import gameLogic.Segment;

public class GameDisplay extends Canvas {
	
	private LinkedList<UIcar> uIcars;
	private LinkedList<UIsegment> uIsegments;
	
	
	public GameDisplay() {
		uIcars = new LinkedList<UIcar>();
		uIsegments = new LinkedList<UIsegment>();
	}
	
	public void paint(Graphics g) {  
		//will be changed
		
        g.drawString("Hello",40,40);  
        setBackground(Color.WHITE);  
        g.fillRect(130, 30,100, 80);  
        g.drawOval(30,130,50, 60);  
        setForeground(Color.RED);  
        g.fillOval(130,130,50, 60);  
        g.drawArc(30, 200, 40,50,90,60);  
        g.fillArc(30, 130, 40,50,180,40);  
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
	}
	
	public void AddCar(Car car) {
		uIcars.add(new UIcar(car));
		if(car.getAttachedCar() != null)
			this.AddCar(car.getAttachedCar());
	}
	
	public void AddSegment(Segment segment) {
		uIsegments.add(new UIsegment(segment));
	}
}
