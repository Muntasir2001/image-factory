package factory_cw;

import javax.swing.JPanel;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;

public class BlendUI extends JPanel {
   
   private final JTextField otherImagePath = new JTextField(60);
   private final JButton fileChooserButton = new JButton("Open");
   private final JSlider alphaSlider = new JSlider(0, 100);
   
   private File file;
   private final JFileChooser chooser;

   public BlendUI(final JFileChooser chooser) {
      super(new BorderLayout());

      this.chooser = chooser;

      final JPanel pathPanel = new JPanel();
      pathPanel.add(this.otherImagePath);
      pathPanel.add(this.fileChooserButton);
      pathPanel.setBorder(BorderFactory.createTitledBorder("Image to blend"));
      alphaSlider.setBorder(BorderFactory.createTitledBorder("How close to match the colour to blend through"));

      add(pathPanel, BorderLayout.NORTH);
      add(this.alphaSlider, BorderLayout.SOUTH);

      this.otherImagePath.setEditable(false);

      this.fileChooserButton.addActionListener(ev -> showChooser());
   }

   private void showChooser() {
      if (this.chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
         this.file = this.chooser.getSelectedFile();
         this.otherImagePath.setText(this.file.getPath());
      }
   }

   public File getAnotherImagePath() {
      return this.file;
   }

   public double getAlphaValue() {
      System.out.println(this.alphaSlider.getValue());
      return this.alphaSlider.getValue() / 100.0;
   }
}
