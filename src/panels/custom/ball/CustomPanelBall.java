package panels.custom.ball;

import panels.custom.ball.graphical.GraphicBall;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Written for understanding the very basics of Swing
 * @author Drew Hoener
 * */
public class CustomPanelBall extends JPanel {

	public static final int HEIGHT = 600;
	public static final int WIDTH = 600;

	private GraphicBall ball;

	public CustomPanelBall(){
		super();
		this.init();
	}

	public void init(){
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.WHITE);
		int randX = new Random().nextInt(100) + 100;
		int randY = new Random().nextInt(100) + 100;
		ball = new GraphicBall(randX, randY, 30);
	}

	public void paintComponent(Graphics graphics){
		super.paintComponent(graphics);
		this.paintRepaint(((Graphics2D) graphics));
	}

	private void paintRepaint(Graphics2D graphics) {

		ball.move();
		ball.draw(graphics);

	}

}
