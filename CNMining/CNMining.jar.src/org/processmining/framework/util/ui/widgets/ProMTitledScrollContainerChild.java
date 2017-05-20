package org.processmining.framework.util.ui.widgets;

import java.awt.BorderLayout;

public class ProMTitledScrollContainerChild extends ProMScrollContainerChild {

	private final String title;

	/**
	 * 
	 */
	private static final long serialVersionUID = -1497690974696615552L;

	public ProMTitledScrollContainerChild(final String title, final ProMScrollContainer parent) {
		this(title, parent, false);
	}

	public ProMTitledScrollContainerChild(final String title, final ProMScrollContainer parent, final boolean minimized) {
		super(parent, minimized);

		this.title = title;

		initialize();
	}

	public String getTitle() {
		return title;
	}

	private void initialize() {
		final LeftAlignedHeader header = new LeftAlignedHeader(getTitle());

		getTitlePanel().setLayout(new BorderLayout());
		getTitlePanel().setBackground(WidgetColors.PROPERTIES_BACKGROUND);
		getTitlePanel().add(header);

	}

}
