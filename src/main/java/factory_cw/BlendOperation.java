package factory_cw;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BlendOperation implements ImageOperation {
   private ImageProcessor processor;
   private BufferedImage image;

   public BlendOperation(BufferedImage image, ImageProcessor processor) {
      this.image = image;
      this.processor = processor;
      doImgOperation();
   }

   public void doImgOperation() {
      final OperationDialog dialog = new OperationDialog(this.processor, ImageFactory.blendUI);
      dialog.setVisible(true);

      if(!dialog.wasCancelled()) {
         try {
            double alphaValue = ImageFactory.blendUI.getAlphaValue();
            BufferedImage otherImage = ImageIO.read(ImageFactory.blendUI.getAnotherImagePath());
            
            BufferedImage output = new BufferedImage(this.image.getWidth(), this.image.getHeight(), this.image.getType());

            for (int x = 0; x < output.getWidth(); x++) {
               for (int y = 0; y < output.getHeight(); y++) {
                  int inputRGB = OperationUtilities.getRGB(x, y, this.image);
                  int otherRGB = OperationUtilities.getRGB(x, y, otherImage);
                  int outputRGB = OperationUtilities.blend(inputRGB, otherRGB, alphaValue);
                  OperationUtilities.setRGB(x, y, outputRGB, output);
               }
            }

            this.processor.setImage(output);

         } catch (IOException ex) {
            ex.printStackTrace();
            this.processor.setImage(this.image);
         }
      } else {
         this.processor.setImage(this.image);
      }
   }
}