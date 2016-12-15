package panels.buildable.panels;

import panels.buildable.advanced.GBConstraints;

import javax.swing.*;
import java.awt.*;

public class LabelPanel extends JPanel {

	private Font bigFont = new Font("Ariel", Font.TRUETYPE_FONT, 30);
	private Font smolFont = new Font("Ariel", Font.TRUETYPE_FONT, 10);

	JLabel label = new JLabel("WOW THIS IS BIG TEXT");
	JLabel subText = new JLabel("wow this is smol text");
	JCheckBox labelBox = new JCheckBox("BIG");
	JCheckBox subBox = new JCheckBox("smol");

	public LabelPanel(){
		this.init();
	}

	public void init(){

		this.setLayout(new GridBagLayout());

		label.setFont(bigFont);
		subText.setFont(smolFont);

		labelBox.setSelected(true);
		subBox.setSelected(true);

		this.add(this.label, new GBConstraints(0, 0).gridSize(4, 1).anchor(GridBagConstraints.CENTER).create());
		this.add(this.subText, new GBConstraints(3, 1).gridSize(1, 1).anchor(GridBagConstraints.CENTER).create());
		this.add(this.labelBox, new GBConstraints(0, 2).gridSize(2, 1).anchor(GridBagConstraints.BASELINE).create());
		this.add(Box.createHorizontalGlue(), new GBConstraints(2, 2).gridSize(1, 1).anchor(GridBagConstraints.BASELINE).create());
		this.add(this.subBox, new GBConstraints(3, 2).gridSize(2, 1).anchor(GridBagConstraints.BASELINE).create());

		this.addBigListener();
		this.addSmallListener();

	}

	public void addBigListener(){
		this.labelBox.addActionListener(e -> {
			label.setEnabled(labelBox.isSelected());
			label.setVisible(labelBox.isSelected());
			updateUI();
		});
	}

	public void addSmallListener(){
		this.subBox.addActionListener(e -> {
			subText.setEnabled(subBox.isSelected());
			subText.setVisible(subBox.isSelected());
			updateUI();
		});
	}


}
