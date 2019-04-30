package gameLogic;


//This class is responsible for the x and y coordinates of the level. 
//Using these coordinates, segments and trains will be drawn.
/*public class vec2 {
	public float x;
	public float y;
	
	public vec2() {	}
	public vec2(float X, float Y) {
		x = X;
		y = Y;
	}
	

}*/
public class vec2 {
	public float x;
	public float y;
	
	public vec2() { x=y=0.0f;}
	public vec2(float X, float Y) {
		this.x = X;
		this.y = Y;
	}
	public static final double sqrt2 = Math.sqrt(2);
	
	public static vec2[] getPolygon(float width, vec2 p1, vec2 p2)
	{
		if (width == 0 || p1 == null || p2 == null)
		{
			return null;
		}
		vec2[] v = new vec2[4];
		for(int i = 0; i < 4; i++)
		{
			v[i] = new vec2();
		}
		float d = width / 2;
		if(p2.x == p1.x)
		{
			v[0].x = p1.x - d;
			v[0].y = p1.y + d;
			
			v[1].x = p1.x + d;
			v[1].y = p1.y + d;
			
			v[2].x = p2.x + d;
			v[2].y = p2.y - d;
			
			v[3].x = p2.x - d;
			v[3].y = p2.y - d;
		}
		else if(p2.y == p1.y) {
			v[0].x = p1.x - d;
			v[0].y = p1.y + d;
			
			v[1].x = p2.x + d;
			v[1].y = p2.y + d;
			
			v[2].x = p2.x + d;
			v[2].y = p2.y - d;
			
			v[3].x = p1.x - d;
			v[3].y = p1.y - d;
		}
		else
		{
			double alpha = Math.atan((p2.y - p1.y)/(p2.x - p1.x));
			v[0].x = (float)(p1.x - sqrt2 * d * Math.cos(sqrt2 / 2 - alpha));
			v[0].y = (float)(p1.y + sqrt2 * d * Math.sin(sqrt2 / 2 - alpha));
			
			v[1].x = (float)(p1.x + sqrt2 * d * Math.cos(sqrt2 / 2 + alpha));
			v[1].y = (float)(p1.y + sqrt2 * d * Math.sin(sqrt2 / 2 + alpha));
			
			v[2].x = (float)(p1.x + sqrt2 * d * Math.cos(sqrt2 / 2 - alpha));
			v[2].y = (float)(p1.y - sqrt2 * d * Math.sin(sqrt2 / 2 - alpha));
			
			v[3].x = (float)(p1.x - sqrt2 * d * Math.cos(sqrt2 / 2 + alpha));
			v[3].y = (float)(p1.y - sqrt2 * d * Math.sin(sqrt2 / 2 + alpha));
		}
		return v;
	}
	public static void main (String[] args) {
		vec2 p1 = new vec2(1.0f,1.0f);
		vec2 p2 = new vec2(2.0f,1.0f);
		vec2[] pts = getPolygon(0.5f, p1, p2);
		if(pts == null)
		{
			
		}
		for(vec2 v : pts)
		{
			System.out.println("X:" + v.x + "Y:" + v.y);
		}
	}
}