package org.processmining.framework.util.ui.widgets;

import java.awt.BorderLayout;

public class ProMCheckBoxWithComboBox extends ProMCheckBoxWithPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5157157777410895997L;

	private ProMComboBox comboBox;
		
	public <T> ProMCheckBoxWithComboBox(final Iterable<T> items) {
		this(true, true, items);
	}
	
	public <T> ProMCheckBoxWithComboBox(boolean checked, final Iterable<T> items) {
		this(checked, true, items);
	}
	
	public <T> ProMCheckBoxWithComboBox(final T[]... items) {
		this(true, true, items);
	}
	
	public <T> ProMCheckBoxWithComboBox(boolean checked, boolean hideIfNotChecked, final Iterable<T> items) {
		this(checked, hideIfNotChecked, ProMComboBox.toArray(items));
	}
	
	public <T> ProMCheckBoxWithComboBox(boolean checked, final T[]... items) {
		this(checked, true, items);
	}
	
	public <T> ProMCheckBoxWithComboBox(boolean checked, boolean hideIfNotChecked, final T[]... items) {
		super(checked, hideIfNotChecked);
		
		comboBox = new ProMComboBox(items);
		getPanel().setLayout(new BorderLayout());
		getPanel().add(comboBox, BorderLayout.CENTER);
		
	}
	
	public ProMComboBox getComboBox() {
		return comboBox;
	}	
	
	public Object getSelectedItem() {
		return comboBox.getSelectedItem();
	}
	
	public void setSelectedItem(Object item) {
		comboBox.setSelectedItem(item);
	}
	
	public int getSelectedIndex() {
		return comboBox.getSelectedIndex();
	}
	
	public void setSelectedIndex(int index) {
		comboBox.setSelectedIndex(index);
	}
}
