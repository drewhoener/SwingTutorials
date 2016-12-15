package panels.custom.ball.graphical;

import panels.custom.ball.CustomPanelBall;

import java.awt.*;
import java.util.Random;

/**
 * Written for understanding the very basics of Swing
 * @author Drew Hoener
 * */
public class GraphicBall {

	private int centerX;
	private int centerY;
	private int radius;
	private Color color = Color.BLACK;

	public int xMove = 3, yMove = 2;

	public GraphicBall(int centerX, int centerY, int radius) {
		this.centerX = centerX;
		this.centerY = centerY;
		this.radius = radius;
	}

	//Simple getter methods for the cardinal sides of the circle, remember: our x and y represent the CENTER
	public int getLeftSide() {
		return this.centerX - radius;
	}

	public int getRightSide() {
		return this.centerX + radius;
	}

	public int getTop() {
		return this.centerY - radius;
	}

	public int getBottom() {
		return this.centerY + radius;
	}

	public void draw(Graphics2D graphics2D){

		graphics2D.setColor(color);

		//Self Explanatory, I think
		//it's centerx - radius becuase the x and y are actually located at the TOP LEFT of the tangents to the circle
		//SOOOO we use our center point to draw it at the proper position
		graphics2D.fillOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
	}

	public void move(){
		//-2 to allow for the few extra pixels that swing adds to x=0 and y=0
		//I've never bothered to figure out why, but 0 is always slightly inset from the edge
		if (getTop() + yMove < -2 || getBottom() + yMove > CustomPanelBall.HEIGHT) {
			yMove *= -1;
			randomizeColor();
		}
		if (getLeftSide() + xMove < -2 || getRightSide() + xMove > CustomPanelBall.WIDTH) {
			xMove *= -1;
			randomizeColor();
		}
		this.centerX += xMove;
		this.centerY += yMove;
	}

	public void randomizeColor(){
		Random rand = new Random();

		color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat(), .9F);
	}
}
