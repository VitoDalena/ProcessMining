package org.deckfour.uitopia.api.model;

import java.awt.Image;
import javax.swing.JComponent;

public abstract interface View
{
  public abstract String getCustomName();
  
  public abstract void setCustomName(String paramString);
  
  public abstract Resource getResource();
  
  public abstract Image getPreview(int paramInt1, int paramInt2);
  
  public abstract JComponent getViewComponent();
  
  public abstract void destroy();
  
  public abstract ViewType getType();
  
  public abstract void captureNow();
  
  public abstract void refresh();
  
  public abstract boolean isReady();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/api/model/View.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */