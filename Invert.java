 /**
@author Ming Ni
@version 1.0

COP5007 Project #: 4
File Name: Invert.java
*/

public class Invert implements Filter {

	/**
	 * Impelemtnt invert.
	 */
	@Override
	public Image apply(Image image) {
		Flip flip = new Flip();
		Flop flop = new Flop();
		return flip.apply(flop.apply(image));
	}
}
