package panels.custom.pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomPongMain {

	public static void main(String[] args) {

		new CustomPongMain().invokeWithJava8();
		//new CustomPongMain().invokeWithJava7();
	}

	public void invokeWithJava8(){
		SwingUtilities.invokeLater(this::start);
	}

	@SuppressWarnings({"Convert2Lambda", "Anonymous2MethodRef"})
	public void invokeWithJava7(){

		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				CustomPongMain.this.start();
			}
		};

		SwingUtilities.invokeLater(runnable);
	}

	private void start(){

		JFrame frame = new JFrame("One Player Pong");
		CustomPanelPong panel = new CustomPanelPong();

		panel.addKeyListener(new PongListener(panel));

		frame.add(panel);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);

		panel.grabFocus();

		timerWithJava8(panel, 6);

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
