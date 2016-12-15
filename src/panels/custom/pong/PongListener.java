package panels.custom.pong;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * We want to be able to move our little paddle up and down right?
 * */
public class PongListener implements KeyListener {

	CustomPanelPong panelPong;

	public PongListener(CustomPanelPong panelPong) {
		this.panelPong = panelPong;
	}

	/**
	 * Works for ascii keys typed to produce a character output
	 * Doesn't work for action keys like enter, delete, arrows, etc.
	 * */
	@Override
	public void keyTyped(KeyEvent e) {

	}

	/**
	 * If you're going to make a game, better to just use this method for key presses
	 * */
	@Override
	public void keyPressed(KeyEvent e) {

		if(e.getKeyCode() == KeyEvent.VK_UP){

			this.panelPong.getPaddle().moveUp();

		}else if(e.getKeyCode() == KeyEvent.VK_DOWN){

			this.panelPong.getPaddle().moveDown();

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
