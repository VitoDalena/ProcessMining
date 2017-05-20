package org.jgraph.graph;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.UIManager;
import org.jgraph.JGraph;

public class VertexRenderer
  extends JLabel
  implements CellViewRenderer, Serializable
{
  protected transient VertexView view;
  protected transient boolean hasFocus;
  protected transient boolean selected;
  protected transient boolean preview;
  protected transient boolean childrenSelected;
  protected transient Color defaultForeground = UIManager.getColor("Tree.textForeground");
  protected transient Color defaultBackground = UIManager.getColor("Tree.textBackground");
  protected transient Color bordercolor;
  protected transient int borderWidth;
  protected transient boolean isDoubleBuffered = false;
  protected transient boolean labelEnabled;
  protected transient Color gradientColor = null;
  protected transient Color gridColor = Color.black;
  protected transient Color highlightColor = Color.black;
  protected transient Color lockedHandleColor = Color.black;
  
  public Component getRendererComponent(JGraph paramJGraph, CellView paramCellView, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    this.gridColor = paramJGraph.getGridColor();
    this.highlightColor = paramJGraph.getHighlightColor();
    this.lockedHandleColor = paramJGraph.getLockedHandleColor();
    this.isDoubleBuffered = paramJGraph.isDoubleBuffered();
    if ((paramCellView instanceof VertexView))
    {
      this.view = ((VertexView)paramCellView);
      setComponentOrientation(paramJGraph.getComponentOrientation());
      if (paramJGraph.getEditingCell() != paramCellView.getCell())
      {
        String str = paramJGraph.convertValueToString(paramCellView);
        if (str != null) {
          setText(str.toString());
        } else {
          setText(null);
        }
      }
      else
      {
        setText(null);
      }
      this.hasFocus = paramBoolean2;
      this.childrenSelected = paramJGraph.getSelectionModel().isChildrenSelected(paramCellView.getCell());
      this.selected = paramBoolean1;
      this.preview = paramBoolean3;
      if ((this.view.isLeaf()) || (GraphConstants.isGroupOpaque(paramCellView.getAllAttributes()))) {
        installAttributes(paramCellView);
      } else {
        resetAttributes();
      }
      return this;
    }
    return null;
  }
  
  protected void resetAttributes()
  {
    setText(null);
    setBorder(null);
    setOpaque(false);
    setGradientColor(null);
    setIcon(null);
  }
  
  protected void installAttributes(CellView paramCellView)
  {
    AttributeMap localAttributeMap = paramCellView.getAllAttributes();
    setIcon(GraphConstants.getIcon(localAttributeMap));
    setOpaque(GraphConstants.isOpaque(localAttributeMap));
    setBorder(GraphConstants.getBorder(localAttributeMap));
    setVerticalAlignment(GraphConstants.getVerticalAlignment(localAttributeMap));
    setHorizontalAlignment(GraphConstants.getHorizontalAlignment(localAttributeMap));
    setVerticalTextPosition(GraphConstants.getVerticalTextPosition(localAttributeMap));
    setHorizontalTextPosition(GraphConstants.getHorizontalTextPosition(localAttributeMap));
    this.bordercolor = GraphConstants.getBorderColor(localAttributeMap);
    this.borderWidth = Math.max(1, Math.round(GraphConstants.getLineWidth(localAttributeMap)));
    if ((getBorder() == null) && (this.bordercolor != null)) {
      setBorder(BorderFactory.createLineBorder(this.bordercolor, this.borderWidth));
    }
    Color localColor1 = GraphConstants.getForeground(localAttributeMap);
    setForeground(localColor1 != null ? localColor1 : this.defaultForeground);
    Color localColor2 = GraphConstants.getGradientColor(localAttributeMap);
    setGradientColor(localColor2);
    Color localColor3 = GraphConstants.getBackground(localAttributeMap);
    setBackground(localColor3 != null ? localColor3 : this.defaultBackground);
    setFont(GraphConstants.getFont(localAttributeMap));
    this.labelEnabled = GraphConstants.isLabelEnabled(localAttributeMap);
  }
  
  public void paint(Graphics paramGraphics)
  {
    try
    {
      if ((this.gradientColor != null) && (!this.preview) && (isOpaque()))
      {
        setOpaque(false);
        Graphics2D localGraphics2D = (Graphics2D)paramGraphics;
        localGraphics2D.setPaint(new GradientPaint(0.0F, 0.0F, getBackground(), getWidth(), getHeight(), this.gradientColor, true));
        localGraphics2D.fillRect(0, 0, getWidth(), getHeight());
      }
      super.paint(paramGraphics);
      paintSelectionBorder(paramGraphics);
    }
    catch (IllegalArgumentException localIllegalArgumentException) {}
  }
  
  protected void paintSelectionBorder(Graphics paramGraphics)
  {
    Graphics2D localGraphics2D = (Graphics2D)paramGraphics;
    Stroke localStroke = localGraphics2D.getStroke();
    localGraphics2D.setStroke(GraphConstants.SELECTION_STROKE);
    if ((this.childrenSelected) || (this.selected))
    {
      if (this.childrenSelected) {
        paramGraphics.setColor(this.gridColor);
      } else if ((this.hasFocus) && (this.selected)) {
        paramGraphics.setColor(this.lockedHandleColor);
      } else if (this.selected) {
        paramGraphics.setColor(this.highlightColor);
      }
      Dimension localDimension = getSize();
      paramGraphics.drawRect(0, 0, localDimension.width - 1, localDimension.height - 1);
    }
    localGraphics2D.setStroke(localStroke);
  }
  
  public Point2D getPerimeterPoint(VertexView paramVertexView, Point2D paramPoint2D1, Point2D paramPoint2D2)
  {
    Rectangle2D localRectangle2D = paramVertexView.getBounds();
    double d1 = localRectangle2D.getX();
    double d2 = localRectangle2D.getY();
    double d3 = localRectangle2D.getWidth();
    double d4 = localRectangle2D.getHeight();
    double d5 = d1 + d3 / 2.0D;
    double d6 = d2 + d4 / 2.0D;
    double d7 = paramPoint2D2.getX() - d5;
    double d8 = paramPoint2D2.getY() - d6;
    double d9 = Math.atan2(d8, d7);
    double d10 = 0.0D;
    double d11 = 0.0D;
    double d12 = 3.141592653589793D;
    double d13 = 1.5707963267948966D;
    double d14 = d13 - d9;
    double d15 = Math.atan2(d4, d3);
    if ((d9 < -d12 + d15) || (d9 > d12 - d15))
    {
      d10 = d1;
      d11 = d6 - d3 * Math.tan(d9) / 2.0D;
    }
    else if (d9 < -d15)
    {
      d11 = d2;
      d10 = d5 - d4 * Math.tan(d14) / 2.0D;
    }
    else if (d9 < d15)
    {
      d10 = d1 + d3;
      d11 = d6 + d3 * Math.tan(d9) / 2.0D;
    }
    else
    {
      d11 = d2 + d4;
      d10 = d5 + d4 * Math.tan(d14) / 2.0D;
    }
    return new Point2D.Double(d10, d11);
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
  
  public Color getGradientColor()
  {
    return this.gradientColor;
  }
  
  public void setGradientColor(Color paramColor)
  {
    this.gradientColor = paramColor;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/graph/VertexRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */