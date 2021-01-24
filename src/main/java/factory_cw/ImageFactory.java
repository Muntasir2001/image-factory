package factory_cw;

import java.awt.image.BufferedImage;
import javax.swing.JFileChooser;
import java.awt.image.BufferedImage;

public class ImageFactory {
   public enum OperationType { 
      GRAYSCALE, TINT, CHROMAKEY, NEGATIVE, THRESHOLD, BLEND
   }

   public static JFileChooser chooser = new JFileChooser();

   public static final GrayscaleUI grayscaleUI = new GrayscaleUI();
	public static final TintUI tintUI = new TintUI();
	public static final ChromaKeyUI chromaKeyUI = new ChromaKeyUI( chooser);
	public static final NegativeOperationUI negativeUI = new NegativeOperationUI();
	public static final ThresholdUI thresholdUI = new ThresholdUI();
	public static final BlendUI blendUI = new BlendUI(chooser);

   public ImageOperation createOperation(OperationType type, BufferedImage image, ImageProcessor processor) {
      switch(type) {
         case GRAYSCALE:
            return new GrayscaleOperation(image, processor);
         case TINT:
            return new TintOperation(image, processor);
         case CHROMAKEY:
            return new ChromaKeyOperation(image, processor);
         case NEGATIVE:
            return new NegativeOperation(image, processor);
         case THRESHOLD:
            return new ThresholdOperation(image, processor);
         case BLEND:
            return new BlendOperation(image, processor);
         default:
            throw new RuntimeException("Unknown case type: " + type); 
      }
   }
}
