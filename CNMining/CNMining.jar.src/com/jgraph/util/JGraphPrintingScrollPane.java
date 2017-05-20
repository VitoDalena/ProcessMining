package com.jgraph.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.RepaintManager;
import org.jgraph.JGraph;

public class JGraphPrintingScrollPane
  extends JScrollPane
  implements Printable
{
  public static final double DEFAULT_PAGESCALE = 1.5D;
  protected PageFormat pageFormat = new PageFormat();
  protected boolean isPageVisible = true;
  protected double pageScale = 1.5D;
  protected JGraph graph;
  public static String PROPERTY_METRIC = "metric";
  public static String PROPERTY_PAGEVISIBLE = "pageVisible";
  public static String PROPERTY_BACKGROUNDIMAGE = "backgroundImage";
  public static String PROPERTY_RULERSVISIBLE = "rulersVisible";
  public static String PROPERTY_PAGEFORMAT = "pageFormat";
  public static String PROPERTY_AUTOSCALEPOLICY = "autoScalePolicy";
  public static String PROPERTY_PAGESCALE = "pageScale";
  
  public JGraph getGraph()
  {
    return this.graph;
  }
  
  public PageFormat getPageFormat()
  {
    return this.pageFormat;
  }
  
  public void setPageFormat(PageFormat paramPageFormat)
  {
    PageFormat localPageFormat = this.pageFormat;
    this.pageFormat = paramPageFormat;
    updateMinimumSize();
    firePropertyChange(PROPERTY_PAGEFORMAT, localPageFormat, paramPageFormat);
  }
  
  public double getPageScale()
  {
    return this.pageScale;
  }
  
  public void setPageScale(double paramDouble)
  {
    double d = this.pageScale;
    this.pageScale = paramDouble;
    firePropertyChange(PROPERTY_PAGESCALE, d, paramDouble);
  }
  
  protected void updateMinimumSize()
  {
    if ((isPageVisible()) && (this.pageFormat != null))
    {
      Rectangle2D localRectangle2D = this.graph.getCellBounds(this.graph.getRoots());
      Dimension localDimension = localRectangle2D != null ? new Dimension((int)(localRectangle2D.getX() + localRectangle2D.getWidth()), (int)(localRectangle2D.getY() + localRectangle2D.getHeight())) : new Dimension(1, 1);
      int i = (int)(this.pageFormat.getWidth() * this.pageScale);
      int j = (int)(this.pageFormat.getHeight() * this.pageScale);
      int k = (int)Math.ceil((localDimension.width - 5) / i);
      int m = (int)Math.ceil((localDimension.height - 5) / j);
      localDimension = new Dimension(Math.max(k, 1) * i + 5, Math.max(m, 1) * j + 5);
      this.graph.setMinimumSize(localDimension);
    }
    else
    {
      this.graph.setMinimumSize(null);
    }
    this.graph.revalidate();
  }
  
  protected double computeWindowScale(int paramInt)
  {
    Dimension localDimension = getViewport().getExtentSize();
    Rectangle2D localRectangle2D = getGraph().getCellBounds(getGraph().getRoots());
    if (localRectangle2D != null) {
      return Math.min(localDimension.getWidth() / (localRectangle2D.getX() + localRectangle2D.getWidth() + paramInt), localDimension.getHeight() / (localRectangle2D.getY() + localRectangle2D.getHeight() + paramInt));
    }
    return 0.0D;
  }
  
  protected double computePageScale()
  {
    Dimension localDimension1 = getViewport().getExtentSize();
    Dimension localDimension2 = getGraph().getMinimumSize();
    if ((localDimension2 != null) && ((localDimension2.getWidth() != 0.0D) || (localDimension2.getHeight() != 0.0D))) {
      return Math.min(localDimension1.getWidth() / localDimension2.getWidth(), localDimension1.getHeight() / localDimension2.getHeight());
    }
    return 0.0D;
  }
  
  protected double computePageWidthScale(int paramInt)
  {
    Dimension localDimension1 = getViewport().getExtentSize();
    Dimension localDimension2 = getGraph().getMinimumSize();
    if ((localDimension2 != null) && ((localDimension2.getWidth() != 0.0D) || (localDimension2.getHeight() != 0.0D)))
    {
      localDimension1.width -= paramInt;
      return localDimension1.getWidth() / localDimension2.getWidth();
    }
    return 0.0D;
  }
  
  public int print(Graphics paramGraphics, PageFormat paramPageFormat, int paramInt)
  {
    Dimension localDimension = this.graph.getPreferredSize();
    int i = (int)(paramPageFormat.getWidth() * this.pageScale);
    int j = (int)(paramPageFormat.getHeight() * this.pageScale);
    int k = (int)Math.max(Math.ceil((localDimension.width - 5) / i), 1.0D);
    int m = (int)Math.max(Math.ceil((localDimension.height - 5) / j), 1.0D);
    if (paramInt < k * m)
    {
      RepaintManager localRepaintManager = RepaintManager.currentManager(this);
      localRepaintManager.setDoubleBufferingEnabled(false);
      double d = getGraph().getScale();
      getGraph().setScale(1.0D / this.pageScale);
      int n = (int)(paramInt % k * paramPageFormat.getWidth());
      int i1 = (int)(paramInt % m * paramPageFormat.getHeight());
      paramGraphics.translate(-n, -i1);
      paramGraphics.setClip(n, i1, (int)(n + paramPageFormat.getWidth()), (int)(i1 + paramPageFormat.getHeight()));
      getGraph().paint(paramGraphics);
      paramGraphics.translate(n, i1);
      this.graph.setScale(d);
      localRepaintManager.setDoubleBufferingEnabled(true);
      return 0;
    }
    return 1;
  }
  
  public boolean isPageVisible()
  {
    return this.isPageVisible;
  }
  
  public void setPageVisible(boolean paramBoolean)
  {
    boolean bool = this.isPageVisible;
    this.isPageVisible = paramBoolean;
    updateMinimumSize();
    firePropertyChange(PROPERTY_PAGEVISIBLE, bool, paramBoolean);
  }
  
  public class Viewport
    extends JViewport
  {
    public Viewport() {}
    
    public void paint(Graphics paramGraphics)
    {
      if (JGraphPrintingScrollPane.this.isPageVisible()) {
        paintBackgroundPages((Graphics2D)paramGraphics);
      } else {
        setBackground(JGraphPrintingScrollPane.this.graph.getBackground());
      }
      if (JGraphPrintingScrollPane.this.graph.getBackgroundImage() != null) {
        paintBackgroundImage((Graphics2D)paramGraphics);
      }
      setOpaque((!JGraphPrintingScrollPane.this.isPageVisible()) && (JGraphPrintingScrollPane.this.graph.getBackgroundImage() == null));
      super.paint(paramGraphics);
      setOpaque(true);
    }
    
    protected void paintBackgroundImage(Graphics2D paramGraphics2D)
    {
      if (!JGraphPrintingScrollPane.this.isPageVisible())
      {
        paramGraphics2D.setColor(JGraphPrintingScrollPane.this.graph.getBackground());
        paramGraphics2D.fillRect(0, 0, JGraphPrintingScrollPane.this.graph.getWidth(), JGraphPrintingScrollPane.this.graph.getHeight());
      }
      AffineTransform localAffineTransform = paramGraphics2D.getTransform();
      Point localPoint = getViewPosition();
      paramGraphics2D.translate(-localPoint.x, -localPoint.y);
      paramGraphics2D.scale(JGraphPrintingScrollPane.this.graph.getScale(), JGraphPrintingScrollPane.this.graph.getScale());
      Image localImage = JGraphPrintingScrollPane.this.graph.getBackgroundImage().getImage();
      paramGraphics2D.drawImage(localImage, 0, 0, JGraphPrintingScrollPane.this.graph);
      paramGraphics2D.setTransform(localAffineTransform);
    }
    
    protected void paintBackgroundPages(Graphics2D paramGraphics2D)
    {
      Point2D localPoint2D = JGraphPrintingScrollPane.this.graph.toScreen(new Point2D.Double(JGraphPrintingScrollPane.this.pageFormat.getWidth(), JGraphPrintingScrollPane.this.pageFormat.getHeight()));
      Dimension localDimension = JGraphPrintingScrollPane.this.graph.getPreferredSize();
      int i = (int)(localPoint2D.getX() * JGraphPrintingScrollPane.this.pageScale);
      int j = (int)(localPoint2D.getY() * JGraphPrintingScrollPane.this.pageScale);
      int k = (int)Math.max(Math.ceil((localDimension.width - 5) / i), 1.0D);
      int m = (int)Math.max(Math.ceil((localDimension.height - 5) / j), 1.0D);
      paramGraphics2D.setColor(JGraphPrintingScrollPane.this.graph.getHandleColor());
      Point localPoint = getViewPosition();
      paramGraphics2D.translate(-localPoint.x, -localPoint.y);
      paramGraphics2D.fillRect(0, 0, JGraphPrintingScrollPane.this.graph.getWidth(), JGraphPrintingScrollPane.this.graph.getHeight());
      paramGraphics2D.setColor(Color.darkGray);
      paramGraphics2D.fillRect(3, 3, k * i, m * j);
      paramGraphics2D.setColor(JGraphPrintingScrollPane.this.getGraph().getBackground());
      paramGraphics2D.fillRect(1, 1, k * i - 1, m * j - 1);
      Stroke localStroke = paramGraphics2D.getStroke();
      paramGraphics2D.setStroke(new BasicStroke(1.0F, 0, 0, 10.0F, new float[] { 1.0F, 2.0F }, 0.0F));
      paramGraphics2D.setColor(Color.darkGray);
      for (int n = 1; n < k; n++) {
        paramGraphics2D.drawLine(n * i, 1, n * i, m * j - 1);
      }
      for (n = 1; n < m; n++) {
        paramGraphics2D.drawLine(1, n * j, k * i - 1, n * j);
      }
      paramGraphics2D.setStroke(localStroke);
      paramGraphics2D.translate(localPoint.x, localPoint.y);
      paramGraphics2D.clipRect(0, 0, k * i - 1 - localPoint.x, m * j - 1 - localPoint.y);
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/util/JGraphPrintingScrollPane.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */