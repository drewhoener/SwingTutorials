package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Written for understanding the very basics of Swing
 * @author Drew Hoener
 * */
public class PanelIntroMain {

	public static void main(String[] args) {

		new PanelIntroMain().invokeWithJava8();
		//new PanelIntroMain().invokeWithJava7();
	}

	public void invokeWithJava8(){
		SwingUtilities.invokeLater(this::start);
	}

	@SuppressWarnings({"Convert2Lambda", "Anonymous2MethodRef"})
	public void invokeWithJava7(){

		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				PanelIntroMain.this.start();
			}
		};

		SwingUtilities.invokeLater(runnable);
	}

	private void start(){

		JFrame frame = new JFrame("Working with a customized Panel");
		PanelIntro panel = new PanelIntro();

		//Do stuff to the panel that isn't already implemented in the panel layouts

		//Add base panels directly to the frame, all other components should be added to those sub components
		//This is also where layouts come into play, see those in the buildable and layout packages
		frame.add(panel);

		frame.pack();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);

		/*
		* Starts an anonymous timer thread (runs independently of the swing thread) that calls a repaint on the frame
		* Frames don't generally repaint themselves unless you call specific methods (like setBackground(Color color))
		* So here, we repaint automatically becuase we're doing our own custom drawing.
		* Timings are in milliseconds
		* */
		//TODO try adjusting the timer value each time before running and see what happens
		//this.timerWithJava7(frame);
		this.timerWithJava8(frame, 200);
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
