package panels.buildable.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BufferedPanel extends JPanel {

	private BufferedImage image;

	public BufferedPanel(BufferedImage image){
		this.image = image;
	}

	public void paintComponent(Graphics graphics){
		graphics.drawImage(this.image, 0, 0, null);
		repaint();
	}
}
