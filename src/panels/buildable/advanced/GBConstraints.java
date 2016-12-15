package panels.buildable.advanced;

import java.awt.*;

/**
 * Class made for chaining references to a GridBagConstraint instance
 * Created for my senior year math project
 * @author Drew Hoener
 * */
public class GBConstraints {

	private GridBagConstraints gridBagConstraints;

	/**
	 * Creates a new instance ready for chains, starts out the component where you want it to be placed
	 * The grid system is as follows
	 * ------------------
	 * |   0   |    1   |
	 * |       |        |
	 * |----------------|
	 * |   1   |   1    |
	 * |       |        |
	 * ------------------
	 * This is infinitely expandable based on what how far you reach with {@link #gridPos(int, int)}
	 *
	 * */
	public GBConstraints(int gridx, int gridy) {

		this.gridBagConstraints = new GridBagConstraints();
		this.gridBagConstraints.gridx = gridx;
		this.gridBagConstraints.gridy = gridy;
	}

	public GBConstraints gridPos(int gridx, int gridy) {
		this.gridBagConstraints.gridx = gridx;
		this.gridBagConstraints.gridy = gridy;
		return this;
	}

	/**
	 * Sets how many grid spaces this piece takes up, starting at the current pos which should be set in the constructor
	 * */
	public GBConstraints gridSize(int gridwidth, int gridheight) {
		this.gridBagConstraints.gridwidth = gridwidth;
		this.gridBagConstraints.gridheight = gridheight;
		return this;
	}

	/**
	 * Either {@link GridBagConstraints#HORIZONTAL} or {@link GridBagConstraints#VERTICAL}
	 * */
	public GBConstraints fill(int fill) {
		this.gridBagConstraints.fill = fill;
		return this;
	}

	/**
	 * Where the Layout snaps to. Can be any number of things, all found in
	 * {@link GridBagConstraints}. ctrl + click that link to go look if you're using intellij
	 * */
	public GBConstraints anchor(int anchor) {
		this.gridBagConstraints.anchor = anchor;
		return this;
	}

	/**
	 * Padding! But different from insets...Don't rememer if I've ever used this
	 * */
	public GBConstraints ipad(int ipadx, int ipady) {
		this.gridBagConstraints.ipadx = ipadx;
		this.gridBagConstraints.ipady = ipady;
		return this;
	}

	public GBConstraints insets(Insets i) {
		this.gridBagConstraints.insets = i;
		return this;
	}

	public GBConstraints insets(int top, int left, int bottom, int right) {
		this.gridBagConstraints.insets = new Insets(top, left, bottom, right);
		return this;
	}

	public GBConstraints weight(double weightx, double weighty) {
		this.gridBagConstraints.weightx = weightx;
		this.gridBagConstraints.weighty = weighty;
		return this;
	}

	public GridBagConstraints create() {
		return this.gridBagConstraints;
	}

}
