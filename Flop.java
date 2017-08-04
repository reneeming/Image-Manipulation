 /**
@author Ming Ni
@version 1.0

COP5007 Project #: 4
File Name: Flop.java
*/

public class Flop implements Filter {
	/**
	 * Impelemtnt flop.
	 */
	@Override
	public Image apply(Image image) {
		Image ppm = new PPM();
		ppm.setName(image.getName());
		ppm.setDemensions(image.getHeight(), image.getWidth());
		for (int i = 0; i < image.getHeight(); i++) {
			for (int j = 0; j < image.getWidth(); j++) {
				ppm.setPixel(i, j, image.getPixel(i, image.getWidth() - 1 - j));
			}
		}
		return ppm;
	}

}
