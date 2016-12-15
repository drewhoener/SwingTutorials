package panels;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Written for understanding the very basics of Swing
 * @author Drew Hoener
 * */
public class PanelIntro extends JPanel{

	int rgb = 0;

	public PanelIntro(){
		super(); //Ensures double buffering on a repaint
		this.init();
	}

	/**
	 * Method entirely unnecessary, it can be done in the constructor but it makes sense to me to do it here
	 * */
	public void init(){
		//I like to do this here so you don't have to worry about setting up outside of the panel
		setPreferredSize(new Dimension(500, 500));
		setBackground(new Color(.8F, .8F, .8F, 1.0F));
	}

	/**
	 * Easiest way to redraw the components, this is where that timer you might have seen earlier comes in
	 * The components don't repaint themselves on their own *in most cases* so the timer is the easiest way
	 * I prefer to pass this off to a method that uses a {@link Graphics2D} object since it provides more functionality
	 * */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//System.out.println("repaint called?");
		this.paintRepaint(((Graphics2D) g));
	}

	/**
	 * Let's do this! Here we can dynamically change the rendering of the panel
	 * I'll do more with this as time goes on, in the custom package you'll see a ball bounce around
	 * the screen.
	 * For now let's just change up some colors
	 * */
	private void paintRepaint(Graphics2D g) {
		//This showcases the updating functionality of a component
		Random rand = new Random();
		int randx = rand.nextInt(this.getWidth()) + 1;
		int randy = rand.nextInt(this.getHeight()) + 1;

		g.setColor(new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat(), .9F));
		//Draws a rectangle, filling with the color specified in setColor
		g.fillRect(randx, randy, 30, 30);

	}
}
