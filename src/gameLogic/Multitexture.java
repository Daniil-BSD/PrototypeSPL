package gameLogic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class Multitexture{
	
	private BufferedImage images[] = new BufferedImage[4];
	private static Map<String,Multitexture> buffer = new HashMap<String,Multitexture>();
	
	public static Multitexture getTexture(String path) {
		if(buffer.containsKey(path))
			return buffer.get(path);
		try {
			Multitexture image;
			if(path.equals("NONE"))
				image = new Multitexture(new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR_PRE));
			else
			image = new Multitexture(ImageIO.read(new File(path)));
			buffer.put(path, image);
			return image;
		} catch (IOException e) {
			System.out.println("Texture is Missing!!");	
			return null;	
		}
	}
	

	public Multitexture(BufferedImage bufferedImage) {
		images[0] = bufferedImage;
	}

	public BufferedImage getImage() {
		return images[0];
	}

	public BufferedImage getImage(int rotation) {
		int index = (rotation + 1024) % 4;
		if (images[index] == null) {
			switch (index) {
			case 1:
				images[1] = rotate90ToLeft(images[0]);
				break;
			case 2:
				images[2] = rotate180(images[0]);
				break;
			case 3:
				images[3] = rotate90ToRight(images[0]);
				break;
			default:
				break;
			}
		}
		return images[index];
	}

	public BufferedImage rotate180(BufferedImage inputImage) {
		int width = inputImage.getWidth();
		int height = inputImage.getHeight();
		BufferedImage returnImage = new BufferedImage(width, height, inputImage.getType());
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				returnImage.setRGB(width - x - 1, height - y - 1, inputImage.getRGB(x, y));
			}
		}
		return returnImage;
	}

	public BufferedImage rotate90ToLeft(BufferedImage inputImage) {
		int width = inputImage.getWidth();
		int height = inputImage.getHeight();
		BufferedImage returnImage = new BufferedImage(height, width, inputImage.getType());
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				returnImage.setRGB(y, width - x - 1, inputImage.getRGB(x, y));
			}
		}
		return returnImage;
	}

	public BufferedImage rotate90ToRight(BufferedImage inputImage) {
		int width = inputImage.getWidth();
		int height = inputImage.getHeight();
		BufferedImage returnImage = new BufferedImage(height, width, inputImage.getType());
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				returnImage.setRGB(height - y - 1, x, inputImage.getRGB(x, y));
			}
		}
		return returnImage;
	}
}
