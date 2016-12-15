package frames;

import javax.swing.*;
import java.awt.*;

/**
 * Written for understanding the very basics of Swing
 * @author Drew Hoener
 * */
public class FrameContentPaneTutorial {

	public static void main(String[] args) {

		new FrameContentPaneTutorial().invokeWithJava8();
		//new FrameContentPaneTutorial().invokeWithJava7();
	}

	public void invokeWithJava8(){
		SwingUtilities.invokeLater(this::start);
	}

	@SuppressWarnings({"Convert2Lambda", "Anonymous2MethodRef"})
	public void invokeWithJava7(){

		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				FrameContentPaneTutorial.this.start();
			}
		};

		SwingUtilities.invokeLater(runnable);
	}

	private void start(){

		//Same old, Same old
		JFrame jFrame = new JFrame("Exploring the Content Pane");
		//jFrame.setPreferredSize(new Dimension(500, 500));//Default

		/*
		* NEW STUFF!
		* ANALOGY!
		* A rectangular piece of glass in the window or door that is your frame.
		* Might take up just a little portion, might take up the whole area, it's up to you and how you pack the frame
		* Double buffered by default, I never bother with a non-default constructor with a pane
		*/
		JPanel mainPanel = new JPanel();
		/*
		* Preferred sizes of components (the stuff inside the frame) usually override what the frame asked for at the beginning
		* The exception of this being the content pane if it doesn't have elements that actually *use* that space.
		* You'll find that this does nothing unless you comment out jFrame.setPreferredSize
		* */
		mainPanel.setPreferredSize(new Dimension(900, 900));
		//Now this works!
		mainPanel.setBackground(Color.MAGENTA);

		//There's 2 other panes, layered and glass. However they're not really in the scope of these simple tutorials
		//And you won't really need them unless you make something insanely complex
		jFrame.setContentPane(mainPanel);

		//Old stuff
		jFrame.pack();
		jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jFrame.setVisible(true);

		//To be explained when we get to games and custom drawn panels
		//new Timer(100, e -> jFrame.repaint()).start();
	}
}
