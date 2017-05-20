package org.processmining.framework.util.ui.widgets;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import com.fluxicon.slickerbox.factory.SlickerFactory;

public class ProMCheckBoxWithPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1605514658715195558L;
	private JCheckBox checkBox;
	private JPanel panel;
	
	public ProMCheckBoxWithPanel() {
		this(true,true);
	}
	
	public ProMCheckBoxWithPanel(boolean checked) {
		this(checked, true);
	}
		
	public ProMCheckBoxWithPanel(boolean checked, boolean hideIfNotChecked) {
		
		panel = new JPanel();
		checkBox = SlickerFactory.instance().createCheckBox("", checked);
		
		if (hideIfNotChecked) {
			checkBox.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					toggleVisibility();
				}
			});
		}
		
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		
		checkBox.setPreferredSize(new Dimension(30,30));
		checkBox.setMinimumSize(checkBox.getPreferredSize());
		checkBox.setMaximumSize(checkBox.getPreferredSize());
		
		//panel.setMinimumSize(new Dimension(600,30));
		panel.setPreferredSize(new Dimension(530,30));
		
		this.add(checkBox, BorderLayout.WEST);
		this.add(panel, BorderLayout.EAST);
	}
	
	protected JPanel getPanel() {
		return panel;
	}
	
	private void toggleVisibility() {
		panel.setVisible(!panel.isVisible());
	}
	
	public JCheckBox getCheckBox() {
		return checkBox;
	}
	
	public boolean isSelected() {
		return checkBox.isSelected();
	}
	
	public void setSelected(boolean checked) {
		checkBox.setSelected(checked);
	}
}
