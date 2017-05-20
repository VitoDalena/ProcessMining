package edu.uci.ics.jung.visualization.renderers;

import java.awt.Component;
import java.awt.Font;
import javax.swing.JComponent;

public abstract interface VertexLabelRenderer
{
  public abstract <T> Component getVertexLabelRendererComponent(JComponent paramJComponent, Object paramObject, Font paramFont, boolean paramBoolean, T paramT);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/renderers/VertexLabelRenderer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */