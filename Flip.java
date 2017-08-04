 /**
@author Ming Ni
@version 1.0

COP5007 Project #: 4
File Name: Flip.java
*/

public class Flip implements Filter {

	/**
	 * Impelemtnt flip.
	 */
	@Override
	public Image apply(Image image) {
		Image ppm = new PPM();
		ppm.setName(image.getName());
		ppm.setDemensions(image.getHeight(), image.getWidth());
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				ppm.setPixel(j, i, image.getPixel(image.getHeight() - 1 - j, i));
			}
		}
		return ppm;
	}

}
