 /**
@author Ming Ni
@version 1.0

COP5007 Project #: 4
File Name: MainMenu.java
*/

import java.io.File;
import java.util.Scanner;

public class MainMenu extends Menu {
	/**
	 * 
	 * @param s
	 * @return
	 */
	private static String getFileName(String s) {
		String[] ss = s.split("\\\\");
		return ss[ss.length - 1];
	}
	
	@Override
	public boolean listenerUserInput() {
		Scanner scan = new Scanner(System.in);
		menuLoop: while (true) {
			printPromptMessage();
			String input = scan.nextLine();
			switch (input) {
			case "1":
				print("please enter the directory of images:\n");
				input = scan.nextLine();
				ManipulationMenu mm = new ManipulationMenu(false, input);
				boolean b = mm.listenerUserInput();
				if (b) break menuLoop;
				break;
			case "2":
				print("please enter the image path:\n");
				input = scan.nextLine();
				ManipulationMenu sm = new ManipulationMenu(true, input);
				boolean sb = sm.listenerUserInput();
				if (sb) break menuLoop;
				break;
			case "3":
				break menuLoop;
			default:
				print(input + " is not supported, please enter number from 1 to 3");
			}
		}
		return false;
	}

	@Override
	public void printPromptMessage() {
		print("\n---------------Main Menu--------------\n" + "1. Create an album from directory\n"
				+ "2. Read an individual file\n"
				+ "3. Exit the program\n"
				+ "Please enter a number for one of the options given above:\n");
	}

}
