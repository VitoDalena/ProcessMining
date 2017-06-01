package org.processmining.plugins.rttmining;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PannelloOntologia extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String filePath = "";
	
	public PannelloOntologia(){
		setBackground(Color.GRAY);
	    
		final JFileChooser chooser = new JFileChooser(".");
   		FileNameExtensionFilter filter = new FileNameExtensionFilter("Constraints", new String[] { "xml" });
   		chooser.setFileFilter(filter);
        
   		final JButton btnSelect = new JButton("Browse an ontology file");
   		btnSelect.setEnabled(false);
   		final JLabel label = new JLabel("");
        
   		btnSelect.addActionListener(new ActionListener()
   		{
   			public void actionPerformed(ActionEvent e)
   			{
   				int returnVal = chooser.showOpenDialog(PannelloOntologia.this.getParent());
   				if (returnVal == 0)
   				{
   					System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
   					label.setText(chooser.getSelectedFile().getAbsolutePath());
   					PannelloOntologia.this.filePath = chooser.getSelectedFile().getAbsolutePath();
   				}
   			}
   		});
   		GroupLayout groupLayout = new GroupLayout(this);
    
   		setLayout(groupLayout);
	}
	
	public String getFilePath()
	{
		return this.filePath;
	}
	
	public boolean isOnologyLoaded(){
		return this.filePath.isEmpty() == false;
	}

}
