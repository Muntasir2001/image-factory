package factory_cw;

import javax.swing.*;

public class ThresholdUI extends JPanel {
   
   private final JLabel label = new JLabel("Choose threshold");
   private final JSlider threshold = new JSlider(0, 50);

   public ThresholdUI() {
      add(this.threshold);
   }

   public int getThreshold() {
      return this.threshold.getValue();
   }
}
