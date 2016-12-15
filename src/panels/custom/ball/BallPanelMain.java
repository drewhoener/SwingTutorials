package panels.custom.ball;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Written for understanding the very basics of Swing
 * @author Drew Hoener
 * */
public class BallPanelMain {

	public static void main(String[] args) {

		new BallPanelMain().invokeWithJava8();
		//new BallPanelMain().invokeWithJava7();
	}

	public void invokeWithJava8(){
		SwingUtilities.invokeLater(this::start);
	}

	@SuppressWarnings({"Convert2Lambda", "Anonymous2MethodRef"})
	public void invokeWithJava7(){

		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				BallPanelMain.this.start();
			}
		};

		SwingUtilities.invokeLater(runnable);
	}

	private void start(){

		JFrame frame = new JFrame("Bouncing Ball");
		CustomPanelBall panel = new CustomPanelBall();

		frame.add(panel);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);

		timerWithJava8(panel, 10);

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
