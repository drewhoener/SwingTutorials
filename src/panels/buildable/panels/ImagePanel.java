package panels.buildable.panels;

import panels.buildable.advanced.GBConstraints;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImagePanel extends JPanel{

	BufferedImage[] imageList = new BufferedImage[3];
	ButtonGroup buttonGroup = new ButtonGroup();
	JRadioButton buttonOne, buttonTwo, buttonThree;
	JButton imageButton = new JButton("Display Image");

	public ImagePanel(){
		this.init();
	}

	public void init(){

		//Oh Joy the GridBagLayout...
		//But I like it, more info in the GBConstraints class
		this.setLayout(new GridBagLayout());
		try {
			imageList[0] = ImageIO.read(this.getClass().getResourceAsStream("/resources/images/cutecat.jpg"));
			imageList[1] = ImageIO.read(this.getClass().getResourceAsStream("/resources/images/piano.jpg"));
			imageList[2] = ImageIO.read(this.getClass().getResourceAsStream("/resources/images/smashbros.jpg"));
		}catch(IOException e){
			e.printStackTrace();
		}

		//I'm 100% hardcoding the values right now, I know.
		//But this can be done dynamically in a variety of ways
		buttonOne = new JRadioButton("Button One");
		buttonOne.setActionCommand("0");
		buttonTwo = new JRadioButton("Button Two");
		buttonOne.setActionCommand("1");
		buttonThree = new JRadioButton("Button Three");
		buttonOne.setActionCommand("2");

		this.addListener(this.imageButton);

		this.buttonGroup.add(buttonOne);
		this.buttonGroup.add(buttonTwo);
		this.buttonGroup.add(buttonThree);


		//Add to panel, this is where stuff gets confusing, pay attention, refer to GBConstraints
		this.add(buttonOne, new GBConstraints(0, 0).weight(0, .2).gridSize(2, 1).fill(GridBagConstraints.NONE).anchor(GridBagConstraints.FIRST_LINE_START).insets(3, 5, 2, 5).create());
		this.add(buttonTwo, new GBConstraints(0, 1).weight(0, .2).gridSize(2, 1).fill(GridBagConstraints.NONE).anchor(GridBagConstraints.FIRST_LINE_START).insets(3, 5, 2, 5).create());
		this.add(buttonThree, new GBConstraints(0, 2).weight(0, .2).gridSize(2, 1).fill(GridBagConstraints.NONE).anchor(GridBagConstraints.FIRST_LINE_START).insets(3, 5, 2, 5).create());
		this.add(this.imageButton, new GBConstraints(2, 1).gridSize(1, 1).fill(GridBagConstraints.HORIZONTAL).anchor(GridBagConstraints.BASELINE).insets(3, 2, 2, 5).create());

	}

	public void addListener(JButton button){
		button.addActionListener(e -> {

			int selected = 0;
			if(buttonTwo.isSelected())
				selected = 1;
			if(buttonThree.isSelected())
				selected = 2;

			try {

				BufferedImage image = imageList[selected];
				Component parent = ImagePanel.this;
				int tries = 0;
				while(tries < 10 && !(parent instanceof JFrame)){
					parent = parent.getParent();
					tries++;
				}
				JDialog dialog = new JDialog((Frame) parent, "Image");
				JPanel imagePanel = new BufferedPanel(image);
				imagePanel.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
				dialog.add(imagePanel);
				dialog.pack();
				dialog.setVisible(true);

			}catch(NumberFormatException | IndexOutOfBoundsException exception){
				exception.printStackTrace();
			}
		});
	}
}
