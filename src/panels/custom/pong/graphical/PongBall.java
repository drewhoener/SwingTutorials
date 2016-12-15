package panels.custom.pong.graphical;

import panels.custom.pong.CustomPanelPong;
import panels.custom.pong.graphical.generic.IDrawable;
import panels.custom.pong.graphical.generic.Movable;

import java.awt.*;
import java.util.Random;

/**
 * Written for understanding the very basics of Swing
 * @author Drew Hoener
 * */
public class PongBall extends Movable {

	private int radius;

	public PongBall(int centerX, int centerY, int radius) {
		//This works becuase the variables genericX and genericY are protected, so we can
		//set them because this class extends Movable. If they were private this wouldn't be possible
		this.genericX = centerX;
		this.genericY = centerY;
		//Radius is a variable unique to this class
		this.radius = radius;
	}

	//Simple getter methods for the cardinal sides of the circle, remember: our x and y represent the CENTER
	//Remember, Swing coordinates aren't like regular coordinates
	/*-----------------
	* |       0       |
	* |               |
	* | 0       Max X |
	* |               |
	* |     Max Y     |
	* -----------------
	* */
	@Override
	public int getLeftSide() {
		return this.genericX - radius;
	}

	@Override
	public int getRightSide() {
		return this.genericX + radius;
	}

	@Override
	public int getTop() {
		return this.genericY - radius;
	}

	@Override
	public int getBottom() {
		return this.genericY + radius;
	}


	/**
	 * Becuase {@link IDrawable} is an interface but doesn't implement draw, it makes every class that extends it
	 * Define how it's used. {@link Movable} is {@code abstract} so it doesn't have to
	 * But this isn't abstract so here we are
	 * */
	public void draw(Graphics2D graphics2D){

		graphics2D.setColor(color);

		//Self Explanatory, I think
		//it's centerx - radius becuase the x and y are actually located at the TOP LEFT of the tangents to the circle
		//SOOOO we use our center point to draw it at the proper position
		graphics2D.fillOval(this.genericX - radius, this.genericY - radius, radius * 2, radius * 2);
	}

	public boolean intersects(int x2, int y2) {

		double dist = Math.sqrt(Math.pow(this.genericX - x2, 2) + Math.pow(this.genericY - y2, 2));

		return dist <= radius;

	}

	/**
	 * This functions the same as {@link #draw(Graphics2D)} in that we have do define what to do here
	 * Since {@link Movable} doesn't know how every single extension wants to move a piece, it's easier to let them decide
	 * */
	public void move(){

		//If it goes off the screen, randomize and reset the score because YOU FUCKING LOST
		if(this.getRightSide() <= 0){
			this.randomizeBall();
			this.randomizeColor();
			CustomPanelPong.SCORE = 0;
			return;
		}

		//If you hit any other wall, reverse
		if (getTop() + velocityY < -2 || getBottom() + velocityY > CustomPanelPong.HEIGHT) {
			reverseY();
			//Copy paste? Me? NEVER
			//randomizeColor();
		}
		//We've removed the 0 bit from the move portion becuase if it goes over there you fail!
		if (getRightSide() + velocityX > CustomPanelPong.WIDTH) {
			reverseX();
			//randomizeColor();
		}

		//Carry on our merry way
		this.translate(velocityX, velocityY);


	}

	public void randomizeBall(){
		Random rand = new Random();
		this.genericX = rand.nextInt(150) + 100;
		this.genericY = rand.nextInt(150) + 100;
		this.setVelocityY(rand.nextInt(2) + 2);
		this.setVelocityX(rand.nextInt(2) + 2);
	}

	/**
	 * Defined by {@link Movable} but not implemented by the superclass {@link Movable} because it doesn't know
	 * what every single subclass wants to do when intersecting with another object
	 * */
	@Override
	public void intersectOther(Movable other) {

		//This is a *VERY* basic example that doesn't even account for the fact that the circle is rounded, we can fix that in a later
		//example. You'll notice that in some cases, the ball will go THROUGH the paddle.
		//The simple (shitty) fix for this simple (shitty) method is to use while loops to make sure that the ball is completely
		//outside of the paddle before continuing with the render.

		if(other instanceof Paddle){

			Paddle paddle = ((Paddle) other);

			//Since we don't return after each if statement, it might go through several to make sure
			// that the ball doesn't get stuck on the paddle
			boolean incrementScore = false;

			if(paddle.intersects(getLeftSide(), this.genericY)){
				this.reverseX();
				while(getLeftSide() <= paddle.getRightSide())
					translate(1, 0);
				incrementScore = true;
			}

			if(paddle.intersects(getRightSide(), this.genericY)){
				this.reverseX();
				while(getRightSide() >= paddle.getLeftSide())
					translate(-1, 0);
				incrementScore = true;
			}

			if(paddle.intersects(this.genericX, this.getBottom())){
				this.reverseY();
				while(getBottom() >= paddle.getTop())
					translate(0, -1);
				incrementScore = true;
			}

			if(paddle.intersects(this.genericX, this.getTop())){
				this.reverseY();
				while(getTop() <= paddle.getBottom())
					translate(0, 1);
				incrementScore = true;
			}

			if(incrementScore)
				CustomPanelPong.SCORE++;

		}
	}
}
