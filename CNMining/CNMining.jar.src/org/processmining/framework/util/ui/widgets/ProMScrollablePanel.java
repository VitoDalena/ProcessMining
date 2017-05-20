package org.processmining.framework.util.ui.widgets;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.Scrollable;
import javax.swing.SwingConstants;

public class ProMScrollablePanel extends JPanel implements Scrollable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Dimension getPreferredScrollableViewportSize() {
		return getPreferredSize();
	}

	@Override
	public int getScrollableBlockIncrement(final Rectangle visibleRect, final int orientation, final int direction) {
		return orientation == SwingConstants.VERTICAL ? getParent().getHeight() : getParent().getWidth();
	}

	@Override
	public boolean getScrollableTracksViewportHeight() {
		return false;
	}

	@Override
	public boolean getScrollableTracksViewportWidth() {
		return true;
	}

	@Override
	public int getScrollableUnitIncrement(final Rectangle visibleRect, final int orientation, final int direction) {
		final int hundredth = (orientation == SwingConstants.VERTICAL ? getParent().getHeight() / 100 : getParent()
				.getWidth()) / 100;
		return hundredth == 0 ? 1 : hundredth;
	}

}