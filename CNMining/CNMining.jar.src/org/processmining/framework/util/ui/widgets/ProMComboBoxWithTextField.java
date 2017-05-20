package org.processmining.framework.util.ui.widgets;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Collection;

import javax.swing.JPanel;

public class ProMComboBoxWithTextField extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3750834515241351840L;
	private final ProMComboBox comboBox;
	private final ProMTextField textField;
	
	public ProMComboBoxWithTextField(final Collection<Object> items) {
		this(items.toArray());
	}
	
	public ProMComboBoxWithTextField(final Object[] items) {
		this(items,"");
	}
	
	public ProMComboBoxWithTextField(final Collection<Object> items, String text) {
		this(items.toArray(), text);
	}
	
	public ProMComboBoxWithTextField(Object[] items, String text) {
		
		comboBox = new ProMComboBox(items);
		textField = new ProMTextField(text);
		
		comboBox.setMinimumSize(new Dimension(50,30));
		comboBox.setPreferredSize(new Dimension(150,30));
		
		textField.setMinimumSize(new Dimension(50,30));
		textField.setPreferredSize(new Dimension(250,30));
		
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		
		this.add(comboBox, BorderLayout.WEST);
		this.add(textField, BorderLayout.EAST);
	}
	
	public ProMComboBox getComboBox() {
		return comboBox;
	}
	
	public ProMTextField getTextField() {
		return textField;
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
	
	public String getText() {
		return textField.getText();
	}
	
	public void setText(String text) {
		textField.setText(text);
	}
	
}
