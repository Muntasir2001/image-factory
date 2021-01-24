package factory_cw;

import java.awt.image.BufferedImage;

public class NegativeOperation implements ImageOperation {

   private ImageProcessor processor;
   private BufferedImage image;

   public NegativeOperation(BufferedImage image, ImageProcessor processor) {
      this.processor = processor;
      this.image = image;
      doImgOperation();
   }

   public void doImgOperation() {
      final OperationDialog dialog = new OperationDialog(this.processor, ImageFactory.negativeUI);
      dialog.setVisible(true);

      if (!dialog.wasCancelled()) {
         for (int x = 0; x < this.image.getWidth(); x++) {
            for (int y = 0; y < this.image.getWidth(); y++) {
               final int inputRGB = OperationUtilities.getRGB(x, y, this.image);
               final int outputRGB = OperationUtilities.negative(inputRGB);
               OperationUtilities.setRGB(x, y, outputRGB, this.image);
            }
         }
      }

      this.processor.setImage(this.image);
   }
}