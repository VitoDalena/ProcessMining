package org.deckfour.uitopia.ui.main;

import javax.swing.JComponent;
import org.deckfour.uitopia.ui.overlay.TwoButtonOverlayDialog;

public abstract interface Overlayable
{
  public abstract void showOverlay(JComponent paramJComponent);
  
  public abstract boolean showOverlayDialog(TwoButtonOverlayDialog paramTwoButtonOverlayDialog);
  
  public abstract void hideOverlay();
  
  public abstract boolean hideOverlay(JComponent paramJComponent);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/main/Overlayable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */