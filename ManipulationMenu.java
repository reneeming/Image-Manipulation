 /**
@author Ming Ni
@version 1.0

COP5007 Project #: 4
File Name: ManipulationMenu.java
*/

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManipulationMenu extends Menu {
	/**
	 * isSingleFile to determine if it's a single file.
	 */
	private boolean isSingleFile;
	/**
	 * Save the list of images
	 */
	List<Image> images;
	
	/**
	 * Save the single image info
	 */
	Image image;

	ManipulationMenu(boolean b, String path) {
		isSingleFile = b;
		if (b) {
			image = new PPM();
			image.read(path);
			image.setName(getFileName(path));
		} else {
			images = getImages(path);
		}
	}

	private String getFileName(String s) {
		String[] ss = s.split("\\\\");
		return ss[ss.length - 1];
	}

	private List<Image> getImages(String path) {
		List<Image> res = new ArrayList<>();
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			String file = listOfFiles[i].getAbsolutePath();
			if (file.endsWith(".ppm")) {
				Image image = new PPM();
				image.read(file);
				image.setName(getFileName(file));
				res.add(image);
			}
		}
		return res;
	}

	@Override
	public boolean listenerUserInput() {
		Scanner scan = new Scanner(System.in);
		printPromptMessage();
		String input = scan.nextLine();
		switch (input) {
		case "1":
			handleFilter(input, scan);
			break;
		case "2":
			handleFilter(input, scan);
			break;
		case "3":
			handleFilter(input, scan);
			break;
		case "4":
			handleFilter(input, scan);
			break;
		case "5":
			return false;
		case "6":
			return true;
		default:
			print(input + " is not supported, please enter number from 1 to 6");
		}
		return false;
	}

	private void handleFilter(String option, Scanner scan) {
		Filter filter = null;
		switch (option) {
		case "1":
			filter = new SwapRedAndBlue();
			break;
		case "2":
			filter = new Flip();
			break;
		case "3":
			filter = new Flop();
			break;
		case "4":
			filter = new Invert();
			break;
		}
		if (isSingleFile) {
			image = filter.apply(image);
		} else {
			for (int i = 0; i < images.size(); i++) {
				Image image = images.get(i);
				images.set(i, filter.apply(image));
			}
		}

		if (isSingleFile) {
			print("Please enter the new image path:\n");
		} else {
			print("Please enter the new direcotry for images:\n");
		}
		String path = scan.nextLine();
		if (isSingleFile) {
			if (!path.endsWith(".ppm")) {
				path += "\\" + image.getName();
			}
			image.write(path);
		} else {
			for (Image image : images) {
				image.write(path + "\\" + image.getName());
			}
		}
	}

	@Override
	public void printPromptMessage() {
		print("\n---------------Manipulation Menu--------------\n" + "1. Swap red and blue color values\n"
				+ "2. Flip the image(s)\n" + "3. Flop the image(s)\n" + "4. Invert the image(s)\n"
				+ "5. Return to main menu\n" + "6. Exit the program\n"
				+ "Please enter a number for one of the options given above:\n");
	}

}
