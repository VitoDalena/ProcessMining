package org.jgraph.graph;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;
import javax.swing.JComponent;
import javax.swing.UIManager;
import org.jgraph.JGraph;

public class PortRenderer
  extends JComponent
  implements CellViewRenderer, Serializable
{
  protected transient PortView view;
  protected Color graphBackground = Color.white;
  protected transient boolean hasFocus;
  protected transient boolean selected;
  protected transient boolean preview;
  protected transient boolean xorEnabled;
  
  public PortRenderer()
  {
    setForeground(UIManager.getColor("MenuItem.selectionBackground"));
    setBackground(UIManager.getColor("Tree.selectionBorderColor"));
  }
  
  public Component getRendererComponent(JGraph paramJGraph, CellView paramCellView, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    if (((paramCellView instanceof PortView)) && (paramJGraph != null))
    {
      this.graphBackground = paramJGraph.getBackground();
      this.view = ((PortView)paramCellView);
      this.hasFocus = paramBoolean2;
      this.selected = paramBoolean1;
      this.preview = paramBoolean3;
      this.xorEnabled = paramJGraph.isXorEnabled();
      return this;
    }
    return null;
  }
  
  public void paint(Graphics paramGraphics)
  {
    Dimension localDimension = getSize();
    if (this.xorEnabled)
    {
      paramGraphics.setColor(this.graphBackground);
      paramGraphics.setXORMode(this.graphBackground);
    }
    super.paint(paramGraphics);
    if (this.preview) {
      paramGraphics.fill3DRect(0, 0, localDimension.width, localDimension.height, true);
    } else {
      paramGraphics.fillRect(0, 0, localDimension.width, localDimension.height);
    }
    int i = GraphConstants.getOffset(this.view.getAllAttributes()) != null ? 1 : 0;
    paramGraphics.setColor(getForeground());
    if (i == 0) {
      paramGraphics.fillRect(1, 1, localDimension.width - 2, localDimension.height - 2);
    } else if (!this.preview) {
      paramGraphics.drawRect(1, 1, localDimension.width - 3, localDimension.height - 3);
    }
  }
  
  public void validate() {}
  
  public void revalidate() {}
  
  public void repaint(long paramLong, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {}
  
  public void repaint(Rectangle paramRectangle) {}
  
  protected void firePropertyChange(String paramString, Object paramObject1, Object paramObject2)
  {
    if (paramString == "text") {
      super.firePropertyChange(paramString, paramObject1, paramObject2);
    }
  }
  
  public void firePropertyChange(String paramString, byte paramByte1, byte paramByte2) {}
  
  public void firePropertyChange(String paramString, char paramChar1, char paramChar2) {}
  
  public void firePropertyChange(String paramString, short paramShort1, short paramShort2) {}
  
  public void firePropertyChange(String paramString, int paramInt1, int paramInt2) {}
  
  public void firePropertyChange(String paramString, long paramLong1, long paramLong2) {}
  
  public void firePropertyChange(String paramString, float paramFloat1, float paramFloat2) {}
  
  public void firePropertyChange(String paramString, double paramDouble1, double paramDouble2) {}
  
  public void firePropertyChange(String paramString, boolean paramBoolean1, boolean paramBoolean2) {}
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/graph/PortRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */