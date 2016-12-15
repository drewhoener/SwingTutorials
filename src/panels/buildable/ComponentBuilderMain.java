package panels.buildable;

import panels.buildable.advanced.GBConstraints;
import panels.buildable.panels.AudioPanel;
import panels.buildable.panels.ImagePanel;
import panels.buildable.panels.LabelPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Written for understanding the very basics of Swing
 * @author Drew Hoener
 * */
public class ComponentBuilderMain {

	public static void main(String[] args) {

		new ComponentBuilderMain().invokeWithJava8();
		//new ComponentBuilderMain().invokeWithJava7();
	}

	public void invokeWithJava8(){
		SwingUtilities.invokeLater(this::start);
	}

	@SuppressWarnings({"Convert2Lambda", "Anonymous2MethodRef"})
	public void invokeWithJava7(){

		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				ComponentBuilderMain.this.start();
			}
		};

		SwingUtilities.invokeLater(runnable);
	}

	/**
	 * Starting to take a look at layouts
	 * As a general note, laying out components to look perfect in swing is a bitch and a half
	 * If you can't get it at first, keep trying, and definitely use other layouts if they make more sense
	 * */
	private void start(){

		JFrame frame = new JFrame("Multifunction panel");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		JPanel verticalPanel = new JPanel();

		//First look at layouts, this one is pretty simple.
		//North, east, south, west. That jazz
		frame.setLayout(new BorderLayout());


		//Add the frame to the main panel, but we have work to do!
		frame.add(mainPanel, BorderLayout.CENTER);

		verticalPanel.add(new JSeparator(SwingConstants.VERTICAL));

		mainPanel.add(new AudioPanel(), new GBConstraints(0, 0).gridSize(5, 3).weight(.4, .4).create());
		mainPanel.add(verticalPanel, new GBConstraints(6, 0).gridSize(1, 3).fill(GridBagConstraints.BOTH).weight(.3, .3).create());
		mainPanel.add(new ImagePanel(), new GBConstraints(7, 0).gridSize(5, 3).weight(.4, .4).create());
		mainPanel.add(new LabelPanel(), new GBConstraints(0, 4).gridSize(5, 3).weight(.4, .4).create());


		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(false);
		//This is where pack becomes ESSENTIAL. You're placing and aligning so many different things that all need space
		//frame.pack makes this a lot less stressful
		frame.pack();
		frame.setVisible(true);

		mainPanel.grabFocus();

	}

	@SuppressWarnings("Convert2Lambda")
	public void timerWithJava7(Component component, int time){
		new Timer(time, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				component.repaint();
			}
		}).start();
	}

	public void timerWithJava8(Component component, int time){
		new Timer(time, e -> component.repaint()).start();
	}



}
