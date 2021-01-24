package factory_cw;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class TintOperation implements ImageOperation {

	private ImageProcessor processor;
	private BufferedImage image;

	public TintOperation(BufferedImage image, ImageProcessor processor) {
		this.image = image;
		this.processor = processor;
		doImgOperation();
	}

   public void doImgOperation() {
		final OperationDialog dialog = new OperationDialog( this.processor, ImageFactory.tintUI);
		dialog.setVisible( true);
		if (!dialog.wasCancelled()) {
			final ColourComponent band = ImageFactory.tintUI.getChosenColor();
			final double alpha = ImageFactory.tintUI.getAlpha() / 100.0;
			for (int x = 0; x < this.image.getWidth(); x++) {
				for (int y = 0; y < this.image.getHeight(); y++) {
					final int inputRGB = OperationUtilities.getRGB(x, y, this.image);
					final int outputRGB = OperationUtilities.tint(inputRGB, alpha, band);
					OperationUtilities.setRGB(x, y, outputRGB, this.image);
				}
			}
		}

		this.processor.setImage(this.image);
		// return this.image;
	} 
}
