package factory_cw;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;

public class ThresholdOperation implements ImageOperation {

   private ImageProcessor processor;
   private BufferedImage image;

   public ThresholdOperation(BufferedImage image, ImageProcessor procesor) {
      this.processor = procesor;
      this.image = image;

      doImgOperation();
   }

   public void doImgOperation() {
      final OperationDialog dialog = new OperationDialog(this.processor, ImageFactory.thresholdUI);
      dialog.setVisible(true);

      if (!dialog.wasCancelled()) {
         int threshold = ImageFactory.thresholdUI.getThreshold();

         for (int x = 0; x < this.image.getWidth(); x++) {
            for (int y = 0; y < this.image.getHeight(); y++) {
               int inputRGB = OperationUtilities.getRGB(x, y, this.image);
               int outputRGB = OperationUtilities.threshold(inputRGB, threshold);
               OperationUtilities.setRGB(x, y, outputRGB, this.image);
            }
         }
      }

      this.processor.setImage(this.image);
   }
}