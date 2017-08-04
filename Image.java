 /**
@author Ming Ni
@version 1.0

COP5007 Project #: 4
File Name: Image.java
*/


import java.util.*;

public abstract class Image implements ImageReader, ImageWriter {
	/**
	 * file name of this image.
	 */
	private String name;
	
	/**
	 * pixels of this image.
	 */
	private List<List<Pixel>> pixels;

	public void setDemensions(int row, int column) {
		pixels = new ArrayList<>();
		for (int i = 0; i < row; i++) {
			List<Pixel> pixelRow = new ArrayList<Pixel>();
			for (int j = 0; j < column; j++) {
				pixelRow.add(new Pixel("0", "0", "0"));
			}
			pixels.add(pixelRow);
		}
	}

	public String getName() {
		return name;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public int getWidth() {
		return pixels.get(0).size();
	}

	public int getHeight() {
		return pixels.size();
	}

	public void setPixels(List<List<Pixel>> ps) {
		pixels = ps;
	}

	public void setPixel(int row, int column, Pixel pixel) {
		pixels.get(row).set(column, pixel);
	}

	public Pixel getPixel(int row, int column) {
		return pixels.get(row).get(column);
	}
	
	public Pixel swapRedAndBlue(int row, int column) {
		 Pixel pixel = pixels.get(row).get(column);
		 String red = pixel.getRed();
		 pixel.setRed(pixel.getBlue());
		 pixel.setBlue(red);
		 return pixel;
	}

	class Pixel {
		private String red;
		private String green;
		private String blue;

		Pixel(String r, String g, String b) {
			setRed(r);
			setGreen(g);
			setBlue(b);
		}

		public void setRed(String r) {
			red = r;
		}

		public String getRed() {
			return red;
		}

		public void setGreen(String g) {
			green = g;
		}

		public String getGreen() {
			return green;
		}

		public void setBlue(String b) {
			blue = b;
		}

		public String getBlue() {
			return blue;
		}

	}
}
