package org.processmining.plugins.rttmining;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
   		FileNameExtensionFilter filter = new FileNameExtensionFilter("OWL Ontology", new String[] { "owl" });
   		chooser.setFileFilter(filter);
    
   		JLabel lblTitle = new JLabel("Search for an ontology file");
   		lblTitle.setFont(new Font("Lucida Grande", 1, 12));
    
   		final JButton btnSelect = new JButton("Browse...");
    
   		final JLabel labelFilename = new JLabel("");
    
   		btnSelect.addActionListener(new ActionListener()
   		{
   			public void actionPerformed(ActionEvent e)
   			{
   				int returnVal = chooser.showOpenDialog(PannelloOntologia.this.getParent());
   				if (returnVal == 0)
   				{
   					System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
   					labelFilename.setText(chooser.getSelectedFile().getAbsolutePath());
   					PannelloOntologia.this.filePath = chooser.getSelectedFile().getAbsolutePath();
   				}
   			}
   		});
    
   		this.add(lblTitle);
   		this.add(btnSelect);
   		this.add(labelFilename);   		

		Dimension size = this.getSize();
		size.height = 30;
		this.setSize(size);
	}
	
	public String getFilePath()
	{
		return this.filePath;
	}
}
