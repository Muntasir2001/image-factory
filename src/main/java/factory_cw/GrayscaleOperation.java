package factory_cw;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GrayscaleOperation implements ImageOperation {
	
	
	private ImageProcessor processor;
	private BufferedImage image;
	
   public GrayscaleOperation(BufferedImage image, ImageProcessor processor) {
		System.out.println("I am here!!");
		this.image = image;
		this.processor = processor;
		doImgOperation();
		System.out.println("I am here2!!");
   }

   public void doImgOperation() {
		System.out.println("I am here3!!");
      final OperationDialog dialog = new OperationDialog( this.processor, ImageFactory.grayscaleUI);
		dialog.setVisible( true );
		if (!dialog.wasCancelled()) {
			for (int x = 0; x < this.image.getWidth(); x++) {
				for (int y = 0; y < this.image.getHeight(); y++) {
					final int inputRGB = OperationUtilities.getRGB(x, y, this.image);
					final int outputRGB = OperationUtilities.grayscale(inputRGB);
					OperationUtilities.setRGB(x, y, outputRGB, this.image);
				}
			}
		}

		this.processor.setImage(this.image);
   }
}
