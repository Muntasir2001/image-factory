package factory_cw;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ChromaKeyOperation implements ImageOperation {

   private ImageProcessor processor;
	private BufferedImage image;
	
	public ChromaKeyOperation(BufferedImage image, ImageProcessor processor) {
		this.image = image;
		this.processor = processor;
		doImgOperation();
	}

   public void doImgOperation() {
		final OperationDialog dialog = new OperationDialog( this.processor, ImageFactory.chromaKeyUI);
		dialog.setVisible( true);    	
		if (!dialog.wasCancelled()) {
			try {
				double sensitivity = ImageFactory.chromaKeyUI.getSensitivity();
				BufferedImage otherImage = ImageIO.read(ImageFactory.chromaKeyUI.getOtherImagePath());

				int targetRGB = ImageFactory.chromaKeyUI.getTargetColor().getRGB();

				BufferedImage output = new BufferedImage(this.image.getWidth(), this.image.getHeight(), this.image.getType());
				for (int x = 0; x < output.getWidth(); x++) {
					for (int y = 0; y < output.getHeight(); y++) {
						int inputRGB = OperationUtilities.getRGB(x, y, this.image);
						int otherRGB = OperationUtilities.getRGB(x, y, otherImage);
						int outputRGB = OperationUtilities.chromaKey(inputRGB, otherRGB, targetRGB, sensitivity);
						OperationUtilities.setRGB(x, y, outputRGB, output);
					}
				}

				this.processor.setImage(output);
				// return output;
			} catch (IOException ex) {
				ex.printStackTrace();
				this.processor.setImage(this.image);
				// return this.image;
			}
		} else {
			this.processor.setImage(this.image);
			// return this.image;
		}
	}
}
