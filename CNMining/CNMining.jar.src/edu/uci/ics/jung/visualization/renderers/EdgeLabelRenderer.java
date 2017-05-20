package edu.uci.ics.jung.visualization.renderers;

import java.awt.Component;
import java.awt.Font;
import javax.swing.JComponent;

public abstract interface EdgeLabelRenderer
{
  public abstract <T> Component getEdgeLabelRendererComponent(JComponent paramJComponent, Object paramObject, Font paramFont, boolean paramBoolean, T paramT);
  
  public abstract boolean isRotateEdgeLabels();
  
  public abstract void setRotateEdgeLabels(boolean paramBoolean);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/renderers/EdgeLabelRenderer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */