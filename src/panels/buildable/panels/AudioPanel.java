package panels.buildable.panels;

import panels.buildable.advanced.Audio;
import panels.buildable.advanced.GBConstraints;

import javax.swing.*;
import java.awt.*;

public class AudioPanel extends JPanel {

	Audio[] audioList = new Audio[3];
	JComboBox<Audio> box = new JComboBox<>();
	JButton playButton = new JButton("Play");
	JButton pauseButton = new JButton("Pause");

	public AudioPanel(){
		this.init();
	}

	public void init(){

		//Oh Joy the GridBagLayout...
		//But I like it, more info in the GBConstraints class
		this.setLayout(new GridBagLayout());

		audioList[0] = new Audio(this.getClass().getResourceAsStream("/resources/music/tetris.wav"));
			audioList[0].name = "Audio: Tetris Theme";
		audioList[1] = new Audio(this.getClass().getResourceAsStream("/resources/music/songofstorms.wav"));
			audioList[1].name = "Audio: Song of Storms";
		audioList[2]=  new Audio(this.getClass().getResourceAsStream("/resources/music/whatchild.wav"));
			audioList[2].name = "What Child is This";

		box.addItem(audioList[0]);
		box.addItem(audioList[1]);
		box.addItem(audioList[2]);

		box.setEditable(false);
		box.setEnabled(true);
		box.setSelectedIndex(0);

		//Add listeners

		//Add to panel, this is where stuff gets confusing, pay attention, refer to GBConstraints
		this.add(box, new GBConstraints(0, 0).gridSize(3, 1).fill(GridBagConstraints.NONE).anchor(GridBagConstraints.FIRST_LINE_START).insets(3, 5, 2, 5).create());
		this.add(this.playButton, new GBConstraints(0, 1).weight(.5, 0).gridSize(1, 1).fill(GridBagConstraints.HORIZONTAL).anchor(GridBagConstraints.WEST).insets(3, 5, 2, 2).create());
		this.add(Box.createHorizontalGlue(), new GBConstraints(1, 1).weight(.1, 0).gridSize(1, 1).fill(GridBagConstraints.BOTH).anchor(GridBagConstraints.SOUTH).create());
		this.add(this.pauseButton, new GBConstraints(2, 1).weight(.3, 0).gridSize(1, 1).fill(GridBagConstraints.HORIZONTAL).anchor(GridBagConstraints.EAST).insets(3, 2, 2, 5).create());

		this.addBoxListener();
		this.addPauseListener();
		this.addPlayListener();

	}

	public void addBoxListener(){
		this.box.addActionListener(e -> {
			for(Audio audio : audioList){
				if(audio.isRunning())
					audio.stop();
			}
		});

		//This is the java 7 way, you'll notice that the variable 'e' shows up in both cases
		//Use this as an example for what the other listeners will entail
		/*
		this.box.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		*/

	}

	public void addPlayListener(){
		this.playButton.addActionListener(e -> {

			Object o = this.box.getSelectedItem();
			if(o instanceof Audio){
				Audio audio = ((Audio) o);
				audio.resume();
			}

		});
	}

	public void addPauseListener(){
		this.pauseButton.addActionListener(e -> {

			Object o = this.box.getSelectedItem();
			if(o instanceof Audio){
				Audio audio = ((Audio) o);
				audio.pause();
			}

		});
	}

}
