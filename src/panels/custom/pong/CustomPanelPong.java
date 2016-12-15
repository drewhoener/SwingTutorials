package panels.custom.pong;

import panels.custom.pong.graphical.Paddle;
import panels.custom.pong.graphical.PongBall;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Written for understanding the very basics of Swing
 * @author Drew Hoener
 * */
public class CustomPanelPong extends JPanel {

	public static final Font bigFont = new Font("Arial", Font.TRUETYPE_FONT, 30);
	public static final int HEIGHT = 800;
	public static final int WIDTH = 1000;

	private PongBall ball;
	private Paddle paddle;

	public static int SCORE = 0;

	public CustomPanelPong(){
		super();
		this.init();
	}

	public void init(){
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(new Color(.8f, .8f, .8f, 1f));
		Random rand = new Random();

		//Setting a random velocity and location every time, easy enough
		this.ball = new PongBall(rand.nextInt(150) + 100, rand.nextInt(150) + 100, 15);
		this.ball.setVelocityY(rand.nextInt(2) + 2);
		this.ball.setVelocityX(rand.nextInt(2) + 2);

		this.paddle = new Paddle(15, 400, 30, 200);
	}

	public Paddle getPaddle(){
		return this.paddle;
	}

	public void paintComponent(Graphics graphics){
		super.paintComponent(graphics);
		this.paintRepaint(((Graphics2D) graphics));
	}

	private void paintRepaint(Graphics2D graphics) {

		//Draw both.
		paddle.draw(graphics);
		ball.draw(graphics);
		//Check for intersections, we do this before moving becuase we don't want a ball to get stuck
		//somewhere, the intersect method will take care of this so we can move properly
		ball.intersectOther(paddle);
		//MOvEs!
		ball.move();

		//Setting the font to the predefined font. Always load fonts before the program and store them
		//Because the game will lag while loading fonts
		graphics.setColor(Color.BLACK);
		graphics.setFont(bigFont);
		String text = "Score " + SCORE;
		//Figure out how many pixels a string takes up with the font that you've set to the graphics offset
		int widthOffset = graphics.getFontMetrics().stringWidth(text);
		int heightOffset = graphics.getFontMetrics().getHeight();
		//Put the string onscreen so that it's at the very top right
		graphics.drawString(text, WIDTH - widthOffset - 5, heightOffset);

	}

}
