package panels.custom.pong.graphical.generic;

import java.awt.*;
import java.util.Random;

/**
 * Written for understanding the very basics of Swing
 * @author Drew Hoener
 * */
public abstract class Movable implements IDrawable{

	protected int genericX;
	protected int genericY;
	protected int width;
	protected int height;
	protected Color color = Color.BLACK;

	protected int velocityX = 3, velocityY = 2;


	public void reverseY(){
		this.velocityY *= -1;
		this.randomizeColor();
	}

	public void reverseX(){
		this.velocityX *= -1;
		this.randomizeColor();
	}

	public void translate(int x, int y){
		this.genericX += x;
		this.genericY += y;
	}

	public void randomizeColor(){
		Random rand = new Random();

		color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat(), 1F);
	}

	//Default methods, you'll see that we now override these in our PongBall class
	public int getLeftSide() {
		return this.genericX;
	}

	public int getRightSide() {
		return this.genericX + width;
	}

	public int getTop() {
		return this.genericY;
	}

	public int getBottom() {
		return this.genericY + height;
	}

	public void setVelocityY(int velocityY) {
		this.velocityY = velocityY;
	}

	public void setVelocityX(int velocityX) {
		this.velocityX = velocityX;
	}

	//Simple intersects method for a rectangle, you'll see that we override this in the circle method
	//Since they have a different way of dealing with intersections
	public boolean intersects(int x2, int y2){
		Rectangle rect = new Rectangle(this.genericX, genericY, width, height);

		return rect.contains(x2, y2);
	}

	//We can't predict every object that's extending this, better to let them
	//decide how to move their pieces
	public abstract void move();

	public abstract void intersectOther(Movable other);
}
