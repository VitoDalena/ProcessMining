package org.processmining.framework.util.ui.widgets;

import java.awt.BorderLayout;

public class ProMCheckBoxWithTextField extends ProMCheckBoxWithPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3331182375174654223L;
	private final ProMTextField textField;
	
	public ProMCheckBoxWithTextField() {
		this(true, true, "");
	}
	
	public ProMCheckBoxWithTextField(String text) {
		this(true, true, text);
	}
	
	public ProMCheckBoxWithTextField(boolean checked, String text) {
		this(checked, true, text);
	}
	
	public ProMCheckBoxWithTextField(boolean checked, boolean hideIfNotSelected, String text) {
		super(checked, hideIfNotSelected);
		
		textField = new ProMTextField(text);
		getPanel().setLayout(new BorderLayout());
		getPanel().add(textField, BorderLayout.CENTER);
		
	}
	
	public void setText(String text) {
		textField.setText(text);
	}
	
	public String getText() {
		return textField.getText();
	}

}
