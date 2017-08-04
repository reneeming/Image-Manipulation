 /**
@author Ming Ni
@version 1.0

COP5007 Project #: 4
File Name: PPM.java
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PPM extends Image {
	/**
	 * write the image to path.
	 */
	@Override
	public void write(String string) {
		List<String> lines = new ArrayList<>();
		lines.add("P3");
		lines.add(getWidth() + " " + getHeight());
		lines.add("255");
		for (int i = 0; i < getHeight(); i++) {
			StringBuffer sb = new StringBuffer();
			for (int j = 0; j < getWidth(); j++) {
				Pixel pixel = getPixel(i, j);
				sb.append(pixel.getRed() + " ");
				sb.append(pixel.getGreen() + " ");
				sb.append(pixel.getBlue() + " ");
			}
			lines.add(sb.toString());
		}
		
		Path file = Paths.get(string);
		try {
			File f = new File(string);
			if (f.getParentFile() != null) {
				f.getParentFile().mkdirs();
			}
			Files.write(file, lines, Charset.forName("UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * read the image from path.
	 */
	@Override
	public void read(String string) {
		int width = 0;
		int height = 0;
		List<Pixel> container = new ArrayList<>();
		try {
			Scanner in = new Scanner(new FileReader(string));
			int counter = 0;
			while(in.hasNext()) {
			    String line = in.nextLine().trim();
			    if (line.startsWith("#") || line.length() == 0) continue;
			    if (counter == 1) {
			    	String[] demension = line.split(" ");
			    	width = Integer.parseInt(demension[0]);
			    	height = Integer.parseInt(demension[1]);
			    } else if (counter >= 3) {
			    	parseLine(line, container);
			    }
			    counter++;
			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<List<Pixel>> pixels = new ArrayList<List<Pixel>>();
		int counter = 0;
		for (int i = 0; i < height; i++) {
			List<Pixel> row = new ArrayList<>();
			for (int j = 0; j < width; j++) {
				row.add(container.get(counter));
				counter++;
			}
			pixels.add(row);
		}
		setPixels(pixels);
	}
	
	private void parseLine(String string, List<Pixel> row) {
		String[] strings = string.split(" ");
		for (int i = 0; i < strings.length; i = i + 3) {
			String r = strings[i];
			String g = strings[i + 1];
			String b = strings[i + 2];
			Pixel pixel = new Pixel(r,g,b);
			row.add(pixel);
		}
	}

}
