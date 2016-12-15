package panels.custom.pong.graphical;

import panels.custom.pong.CustomPanelPong;
import panels.custom.pong.graphical.generic.Movable;

import java.awt.*;

/**
 * Written for understanding the very basics of Swing
 * @author Drew Hoener
 * */
public class Paddle extends Movable {

	public static final int GENERIC_MOVE = 30;

	/**
	 * Default constructor, sets everything up.
	 * You'll notice that we're setting variable values but we haven't
	 * defined any of our own
	 * {@link Movable} defines these variables and since they're {@code protected} we can access them
	 * since we {@code extend} {@link Movable}
	 *
	 * Also if you're using intellij, turn on {@code display doc on hover} so that when you hover over method names you can
	 * see these fun javadocs formatted nicely
	 * */
	public Paddle(int cornerX, int cornerY, int width, int height){
		this.genericX = cornerX;
		this.genericY = cornerY;
		this.width = width;
		this.height = height;
	}

	public void moveDown(){
		int moveAmount = GENERIC_MOVE;
		if(this.getBottom() + moveAmount > CustomPanelPong.HEIGHT){
			this.genericY = CustomPanelPong.HEIGHT - this.height;
			return;
		}
		this.translate(0, moveAmount);
	}

	public void moveUp(){
		int moveAmount = -GENERIC_MOVE;
		//ADDING A NEGATIVE NUMBER PAY THE FUCK ATTENTION
		if(this.getTop() + moveAmount < 0){
			this.genericY = 0;
			return;
		}
		this.translate(0, moveAmount);
	}

	@Override
	public void draw(Graphics2D graphics2D) {

		graphics2D.setColor(this.color);
		graphics2D.fillRect(this.genericX, this.genericY, this.width, this.height);
	}

	@Override
	public void move() {

		//NOTHING TO SEE HERE, IT DOESN'T MOVE ON ITS OWN

	}

	@Override
	public void intersectOther(Movable other) {

		//NOTHING TO SEE HERE, BALLS INTERSECT WITH SHIT BECAUSE BALLS MOVE

	}
}
