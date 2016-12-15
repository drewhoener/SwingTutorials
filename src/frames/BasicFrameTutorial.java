package frames;

import javax.swing.*;
import java.awt.*;

/**
 * Written for understanding the very basics of Swing
 * @author Drew Hoener
 * */
public class BasicFrameTutorial {

	public static void main(String[] args) {

		new BasicFrameTutorial().invokeWithJava8();
		//new BasicFrameTutorial().invokeWithJava7();
	}

	public void invokeWithJava8(){

		/*
		* Interfaces classes normally can't be instantiated
		* But, thanks to something in java 8 called the Lambda, we can instantiate an interface
		* with only one method, called a FunctionalInterface and immediately define its behavior without
		* having to create a different class
		* */

		//Alternate: only use one
		//Runnable runnable = this::start;
		Runnable runnable = () -> start();

		//Swing components and their actions/listeners should never be start on the main thread
		//That is to say, don't do it without invoking it on the Swing Utilities thread.
		//invokeLater takes a Runnable class, any subclass or lambda of Runnable will work.
		SwingUtilities.invokeLater(runnable);
	}

	@SuppressWarnings({"Convert2Lambda", "Anonymous2MethodRef"})
	public void invokeWithJava7(){

		/*
		* Actually creates an instance of the Runnable Interface, this could be done with an actual class
		* But I prefer to do it this way if I don't need any other utility methods
		* */

		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				BasicFrameTutorial.this.start();
			}
		};

		//Swing components and their actions/listeners should never be start on the main thread
		//That is to say, don't do it without invoking it on the Swing Utilities thread.
		//invokeLater takes a Runnable class, any subclass or lambda of Runnable will work.
		SwingUtilities.invokeLater(runnable);
	}

	private void start() {

		//Window component, most barebones thing you can get
		JFrame jframe = new JFrame("Title of the Window");
		//TODO try running, each time uncommenting a line. Watch how it changes
		jframe.setPreferredSize(new Dimension(500, 500));//Width x Height
		//This should actually be called last buuut since we're not adding anything to the frames in this example it doesn't matter
		jframe.pack();
		//You can see a flash of color for a frame or two but this isn't for a frame, it's for a content pane or a panel
		jframe.setBackground(Color.PINK);
		//There are several other of these, explore for yourself. This one actually stops the program running
		jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//Actually starts showing the things you want, mere instance creation isn't enough
		jframe.setVisible(true);

		//To be explained when we get to games and custom drawn panels
		//new Timer(100, e -> jframe.repaint()).start();

	}
}
