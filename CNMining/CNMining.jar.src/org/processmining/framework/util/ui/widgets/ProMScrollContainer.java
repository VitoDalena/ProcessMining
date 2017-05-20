package org.processmining.framework.util.ui.widgets;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.fluxicon.slickerbox.factory.SlickerDecorator;

/**
 * This class implements a Scroll Container. In the container, childs with a
 * minimize / maximize button can be added. In this way, one can group
 * properties.
 * 
 * Furthermore, childs can be added during run time.
 * 
 * @author jvdwerf
 * 
 */
public class ProMScrollContainer extends BorderPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3112303906580355823L;

	private JPanel scrollablePanel;

	private JScrollPane scrollPane;

	private final List<ProMScrollContainerChild> children = new ArrayList<ProMScrollContainerChild>();

	public ProMScrollContainer() {
		this(10, 10);

	}

	public ProMScrollContainer(final int size, final int borderWidth) {
		super(size, borderWidth);

		initComponents();
	}
	
	public void addChild(final ProMScrollContainerChild child) {
		addChild(child, children.size());
	}
	
	public void addChild(ProMScrollContainerChild child, int index) {
		children.add(child);

		if (children.size() > 0) {
			scrollablePanel.add(Box.createVerticalStrut(3));
		}

		scrollablePanel.add(child, index);
		scrollablePanel.revalidate();

		revalidate();
	}

	public void clearChildren() {
		for (final ProMScrollContainerChild child : children) {
			scrollablePanel.remove(child);
		}

		children.clear();

		revalidate();
	}

	public ProMScrollContainerChild getChild(final int i) {
		return children.get(i);
	}

	public List<ProMScrollContainerChild> getChildren() {
		return children;
	}

	public void removeChild(final ProMScrollContainerChild child) {
		scrollablePanel.remove(child);
		children.remove(child);

		revalidate();
	}

	public void showScrollbar(final boolean show) {
		if (show) {
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		} else {
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		}
	}

	private void initComponents() {

		setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

		setOpaque(true);
		setBackground(WidgetColors.COLOR_ENCLOSURE_BG);

		setLayout(new BorderLayout());

		scrollablePanel = new ProMScrollablePanel();
		scrollablePanel.setOpaque(true);
		scrollablePanel.setBackground(WidgetColors.COLOR_LIST_FG);
		scrollablePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		scrollablePanel.setLayout(new BoxLayout(scrollablePanel, BoxLayout.Y_AXIS));

		scrollPane = new JScrollPane(scrollablePanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		SlickerDecorator.instance().decorate(scrollPane, WidgetColors.COLOR_ENCLOSURE_BG, WidgetColors.COLOR_LIST_BG,
				WidgetColors.COLOR_LIST_FG);

		scrollPane.setOpaque(false);
		//scrollPane.setBackground(Color.GREEN);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		//scrollPane.setViewport(new MyViewPort(5,5));
		scrollPane.getViewport().setOpaque(true);
		scrollPane.getViewport().setBackground(WidgetColors.COLOR_LIST_FG);
		scrollPane.setViewportBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		this.add(scrollPane, BorderLayout.CENTER);

		revalidate();
	}
}
