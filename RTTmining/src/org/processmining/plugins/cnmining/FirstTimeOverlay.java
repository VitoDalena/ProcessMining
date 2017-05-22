package org.processmining.plugins.cnmining;

import javax.swing.JLabel;

import org.deckfour.uitopia.ui.main.Overlayable;
import org.deckfour.uitopia.ui.overlay.TwoButtonOverlayDialog;

class FirstTimeOverlay extends TwoButtonOverlayDialog
{
	private static final long serialVersionUID = 494237962617678531L;
  
	public FirstTimeOverlay(Overlayable mainView){
		super(mainView, "Starting ProM", "Cancel", "  OK  ", new JLabel("<html>All packages have been downloaded.<BR>Please wait while starting ProM<BR>for the first time.<BR><BR>If this is the first time you run ProM on this computer, please be patient.</html>"));
    
		getCancelButton().setEnabled(true);
		getOKButton().setEnabled(false);
	}
  
	public void close(boolean okayed){
		if (!okayed) {
			System.exit(0);
		}
		super.close(okayed);
	}
}
