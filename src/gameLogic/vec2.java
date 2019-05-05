package gameLogic;

import java.awt.Polygon;

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

	public vec2() {
		x = y = 0.0f;
	}

	public vec2(float X, float Y) {
		this.x = X;
		this.y = Y;
	}

	public static final double sqrt2 = (double) Math.sqrt(2);

	@SuppressWarnings("unused")
	public static Polygon getPolygon(float width, vec2 p1, vec2 p2) {
		if (width == 0 || p1 == null || p2 == null) {
			return null;
		}
		Polygon ret = new Polygon();
		float d = width / 2;
		if (p2.x == p1.x && false && p2.y < p1.y) {
			// FIX THIS: consider both p2.y > p1.y and p2.y < p1.y
			ret.addPoint(Math.round(p1.x - d), Math.round(p1.y + d));
			ret.addPoint(Math.round(p1.x + d), Math.round(p1.y + d));
			ret.addPoint(Math.round(p2.x + d), Math.round(p2.y - d));
			ret.addPoint(Math.round(p2.x - d), Math.round(p2.y - d));
		} else if (p2.x == p1.x && false && p2.y > p1.y) {
			// FIX THIS: consider both p2.x > p1.x and p2.x < p1.x
			ret.addPoint(Math.round(p1.x - d), Math.round(p1.y - d));
			ret.addPoint(Math.round(p1.x + d), Math.round(p1.y - d));
			ret.addPoint(Math.round(p2.x + d), Math.round(p2.y + d));
			ret.addPoint(Math.round(p2.x - d), Math.round(p2.y + d));
		} else if (p2.y == p1.y && false && p2.x < p1.x) {
			// FIX THIS: consider both p2.x > p1.x and p2.x < p1.x
			ret.addPoint(Math.round(p1.x + d), Math.round(p1.y - d));
			ret.addPoint(Math.round(p1.x + d), Math.round(p1.y + d));
			ret.addPoint(Math.round(p2.x - d), Math.round(p2.y + d));
			ret.addPoint(Math.round(p2.x - d), Math.round(p2.y - d));
		} else if (p2.y == p1.y && false && p2.x > p1.x) {
			// FIX THIS: consider both p2.x > p1.x and p2.x < p1.x
			ret.addPoint(Math.round(p1.x - d), Math.round(p1.y - d));
			ret.addPoint(Math.round(p1.x - d), Math.round(p1.y + d));
			ret.addPoint(Math.round(p2.x + d), Math.round(p2.y + d));
			ret.addPoint(Math.round(p2.x + d), Math.round(p2.y - d));
		} else if (p2.y != p1.y && p1.x < p2.x){
			float alpha = (float) Math.atan((p2.y - p1.y) / (p2.x - p1.x));
			float temp_cosp = (float) (sqrt2 * d * Math.cos(sqrt2 / 2 + alpha));
			float temp_sinp = (float) (sqrt2 * d * Math.sin(sqrt2 / 2 + alpha));
			float temp_cosm = (float) (sqrt2 * d * Math.cos(sqrt2 / 2 - alpha));
			float temp_sinm = (float) (sqrt2 * d * Math.sin(sqrt2 / 2 - alpha));
			ret.addPoint(Math.round(p1.x - temp_cosm), Math.round(p1.y + temp_sinm));
			ret.addPoint(Math.round(p2.x + temp_cosp), Math.round(p2.y + temp_sinp));
			ret.addPoint(Math.round(p2.x + temp_cosm), Math.round(p2.y - temp_sinm));
			ret.addPoint(Math.round(p1.x - temp_cosp), Math.round(p1.y - temp_sinp));
		} else if (p2.y != p1.y && p1.x > p2.x){
			float alpha = (float) Math.atan((p2.y - p1.y) / (p2.x - p1.x));
			float temp_cosp = (float) (sqrt2 * d * Math.cos(sqrt2 / 2 + alpha));
			float temp_sinp = (float) (sqrt2 * d * Math.sin(sqrt2 / 2 + alpha));
			float temp_cosm = (float) (sqrt2 * d * Math.cos(sqrt2 / 2 - alpha));
			float temp_sinm = (float) (sqrt2 * d * Math.sin(sqrt2 / 2 - alpha));
			ret.addPoint(Math.round(p2.x - temp_cosm), Math.round(p2.y + temp_sinm));
			ret.addPoint(Math.round(p1.x + temp_cosp), Math.round(p1.y + temp_sinp));
			ret.addPoint(Math.round(p1.x + temp_cosm), Math.round(p1.y - temp_sinm));
			ret.addPoint(Math.round(p2.x - temp_cosp), Math.round(p2.y - temp_sinp));
		}
		return ret;
	}

	@SuppressWarnings("unused")
	public static vec2[] getPolygonPoints(float width, vec2 p1, vec2 p2) {
		if (width == 0 || p1 == null || p2 == null) {
			return null;
		}
		vec2[] v = new vec2[4];
		for (int i = 0; i < 4; i++) {
			v[i] = new vec2();
		}
		float d = width / 2;
		if (p2.x == p1.x && false && p2.y > p1.y) {
			// FIX THIS: consider both p2.y > p1.y and p2.y < p1.y
			v[0].x = p1.x - d;
			v[0].y = p1.y - d;

			v[1].x = p1.x + d;
			v[1].y = p1.y - d;

			v[2].x = p2.x + d;
			v[2].y = p2.y + d;

			v[3].x = p2.x - d;
			v[3].y = p2.y + d;
		} else if (p2.x == p1.x && false && p2.y < p1.y) {
			// FIX THIS: consider both p2.x > p1.x and p2.x < p1.x
			v[0].x = p1.x - d;
			v[0].y = p1.y + d;

			v[1].x = p1.x + d;
			v[1].y = p1.y + d;

			v[2].x = p2.x + d;
			v[2].y = p2.y - d;

			v[3].x = p2.x - d;
			v[3].y = p2.y - d;
		}  else if (p2.y == p1.y && false && p2.x > p1.x) {
			// FIX THIS: consider both p2.x > p1.x and p2.x < p1.x
			v[0].x = p1.x - d;
			v[0].y = p1.y - d;

			v[1].x = p1.x - d;
			v[1].y = p1.y + d;

			v[2].x = p2.x + d;
			v[2].y = p2.y + d;

			v[3].x = p2.x + d;
			v[3].y = p2.y - d;
		}  else if (p2.y == p1.y && false && p2.x < p1.x) {
			// FIX THIS: consider both p2.x > p1.x and p2.x < p1.x
			v[0].x = p1.x + d;
			v[0].y = p1.y - d;

			v[1].x = p1.x + d;
			v[1].y = p1.y + d;

			v[2].x = p2.x - d;
			v[2].y = p2.y + d;

			v[3].x = p2.x - d;
			v[3].y = p2.y - d;
		}  else if (p2.y != p1.y && p1.x < p2.x) {
			double alpha = Math.atan((p2.y - p1.y) / (p2.x - p1.x));
			v[0].x = (float) (p1.x - sqrt2 * d * Math.cos(sqrt2 / 2 - alpha));
			v[0].y = (float) (p1.y + sqrt2 * d * Math.sin(sqrt2 / 2 - alpha));

			v[1].x = (float) (p2.x + sqrt2 * d * Math.cos(sqrt2 / 2 + alpha));
			v[1].y = (float) (p2.y + sqrt2 * d * Math.sin(sqrt2 / 2 + alpha));

			v[2].x = (float) (p2.x + sqrt2 * d * Math.cos(sqrt2 / 2 - alpha));
			v[2].y = (float) (p2.y - sqrt2 * d * Math.sin(sqrt2 / 2 - alpha));

			v[3].x = (float) (p1.x - sqrt2 * d * Math.cos(sqrt2 / 2 + alpha));
			v[3].y = (float) (p1.y - sqrt2 * d * Math.sin(sqrt2 / 2 + alpha));
		} else if (p2.y != p1.y && p1.x > p2.x) {
			double alpha = Math.atan((p2.y - p1.y) / (p2.x - p1.x));
			v[0].x = (float) (p2.x - sqrt2 * d * Math.cos(sqrt2 / 2 - alpha));
			v[0].y = (float) (p2.y + sqrt2 * d * Math.sin(sqrt2 / 2 - alpha));

			v[1].x = (float) (p1.x + sqrt2 * d * Math.cos(sqrt2 / 2 + alpha));
			v[1].y = (float) (p1.y + sqrt2 * d * Math.sin(sqrt2 / 2 + alpha));

			v[2].x = (float) (p1.x + sqrt2 * d * Math.cos(sqrt2 / 2 - alpha));
			v[2].y = (float) (p1.y - sqrt2 * d * Math.sin(sqrt2 / 2 - alpha));

			v[3].x = (float) (p2.x - sqrt2 * d * Math.cos(sqrt2 / 2 + alpha));
			v[3].y = (float) (p2.y - sqrt2 * d * Math.sin(sqrt2 / 2 + alpha));
		}
		return v;
	}
}