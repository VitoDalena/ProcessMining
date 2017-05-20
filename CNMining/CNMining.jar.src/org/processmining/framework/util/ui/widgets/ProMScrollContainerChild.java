package org.processmining.framework.util.ui.widgets;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.fluxicon.slickerbox.factory.SlickerFactory;


/**
 * Child that can be displayed in the ProMScrollContainer.
 * To update the user interface of a child, one can use the functions
 * 
 *  - getTitlePanel()
 *  	Returns the top panel to display a title of the child
 *   
 *  - getContentPanel()
 *  	Returns the panel in which one can add the real content. This panel
 *  	will be hidden when the min/max button  is hit.
 *  
 *  - setContentSize()
 *  	Sets the size of the content panel when maximized.  
 * 
 * @author jvdwerf
 *
 */
public class ProMScrollContainerChild extends BorderPanel {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -81027020923152280L;
	
	private JPanel topPanel;
	private JPanel contentPanel;
	
	private JButton minimizeButton;
	private JButton deleteButton;
	private JPanel buttonPanel;
	
	private JPanel topContentPanel;
	
	private boolean minimized = false;
	
	private int contentSize = 100;
	
	private final ProMScrollContainer parent;
	
	public ProMScrollContainerChild(final ProMScrollContainer parent, final boolean startminimized, final boolean addDeleteButton) {
		super(10,10);
		this.parent = parent;
		initComponents(addDeleteButton);
		
		setMinimized(startminimized);
	}
	
	public ProMScrollContainerChild(final ProMScrollContainer parent, final boolean startminimized) {
		this(parent, startminimized, true);
	}
	
	public ProMScrollContainerChild(final ProMScrollContainer parent) {
		this(parent, false);
	}
	
	public JPanel getContentPanel() {
		return this.contentPanel;
	}
	
	public JPanel getTitlePanel() {
		return topContentPanel;
	}
	
	
	private void initComponents(boolean addDeleteButton) {
		
		this.setLayout(new BorderLayout());
		this.setOpaque(true);
		this.setBackground(WidgetColors.COLOR_LIST_BG);
		//this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		
		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		topPanel.setOpaque(false);
		
		
		topContentPanel = new JPanel();
		topContentPanel.setLayout(new BorderLayout());
		topContentPanel.setOpaque(false);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.setOpaque(false);
		
		topPanel.add(topContentPanel, BorderLayout.WEST);
		
		minimizeButton = SlickerFactory.instance().createButton("-");
		minimizeButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				toggleMinimized();
			}
		});
		
		if (addDeleteButton) {
			deleteButton = SlickerFactory.instance().createButton("x");
			deleteButton.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					removeMe();
				}
			});
			
			buttonPanel.add(deleteButton, BorderLayout.WEST);
		}
		
		buttonPanel.add(minimizeButton, BorderLayout.EAST);
		
		
		topPanel.add(buttonPanel, BorderLayout.EAST);
		
		
		this.add(topPanel, BorderLayout.NORTH);
		
		contentPanel = SlickerFactory.instance().createRoundedPanel(8, WidgetColors.COLOR_LIST_FG);
		contentPanel.setLayout(new BorderLayout());
		
		updateHeights();
		
		this.add(contentPanel, BorderLayout.CENTER);
		
		
		
	}
	
	private void removeMe() {
		parent.removeChild(this);
	}
		
	private void toggleMinimized() {
		setMinimized( ! isMinimized() );
	}


	public void setMinimized(boolean minimized) {
		this.minimized = minimized;
		
		if (contentPanel != null && minimizeButton != null) {
			if (isMinimized()) {
				contentPanel.setVisible(false);
				minimizeButton.setText("+");
			} else {
				contentPanel.setVisible(true);
				minimizeButton.setText("-");
			}
		}
		
		revalidate();
	}


	public boolean isMinimized() {
		return minimized;
	}
	
	public void setContentSize(Dimension size) {
		//// lelijk :-S
		setContentSize( (int) size.getHeight() );
	}

	public void setContentSize(int minimalContentSize) {
		this.contentSize = minimalContentSize;
		
		updateHeights();
	}
	
	private void updateHeights() {
		if (this.contentSize > contentPanel.getMaximumSize().getHeight()) {
			contentPanel.setMaximumSize(new Dimension(100, getContentSize()));
		}
				
		contentPanel.setPreferredSize(new Dimension(100,getContentSize()));
		contentPanel.setSize(new Dimension(100,getContentSize()));
		
		revalidate();

	}

	public void revalidate() {
		super.revalidate();
		
		if (parent != null) {
			parent.revalidate();
		}
	}
	

	public int getContentSize() {
		return contentSize;
	}

}
