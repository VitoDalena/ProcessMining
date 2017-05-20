package org.jgraph.graph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D.Float;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import javax.swing.JComponent;
import javax.swing.UIManager;
import org.jgraph.JGraph;
import org.jgraph.util.Bezier;
import org.jgraph.util.Spline2D;

public class EdgeRenderer
  extends JComponent
  implements CellViewRenderer, Serializable
{
  public static boolean HIT_LABEL_EXACT = false;
  protected static transient Graphics fontGraphics;
  public static double LABELWIDTHBUFFER = 1.1D;
  public boolean simpleExtraLabels = true;
  public Font extraLabelFont = null;
  protected transient FontMetrics metrics;
  protected transient WeakReference graph;
  protected transient EdgeView view;
  protected transient int beginDeco;
  protected transient int endDeco;
  protected transient int beginSize;
  protected transient int endSize;
  protected transient int lineStyle;
  protected transient float lineWidth;
  protected transient boolean labelsEnabled;
  protected transient boolean labelBorder;
  protected transient boolean beginFill;
  protected transient boolean endFill;
  protected transient boolean focus;
  protected transient boolean selected;
  protected transient boolean preview;
  protected transient boolean opaque;
  protected transient boolean childrenSelected;
  protected transient boolean labelTransformEnabled;
  protected transient boolean isMoveBelowZero;
  protected transient Color borderColor;
  protected transient Color defaultForeground = UIManager.getColor("Tree.textForeground");
  protected transient Color defaultBackground = UIManager.getColor("Tree.textBackground");
  protected transient Color fontColor;
  protected transient float[] lineDash;
  protected transient float dashOffset = 0.0F;
  protected transient Color gradientColor = null;
  protected transient Color gridColor = null;
  protected transient Color lockedHandleColor = null;
  protected transient Color highlightColor = null;
  protected transient Bezier bezier;
  protected transient Spline2D spline;
  
  void setView(CellView paramCellView)
  {
    if ((paramCellView instanceof EdgeView))
    {
      this.view = ((EdgeView)paramCellView);
      installAttributes(this.view);
    }
    else
    {
      this.view = null;
    }
  }
  
  public Component getRendererComponent(JGraph paramJGraph, CellView paramCellView, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    if (((paramCellView instanceof EdgeView)) && (paramJGraph != null))
    {
      this.gridColor = paramJGraph.getGridColor();
      this.lockedHandleColor = paramJGraph.getLockedHandleColor();
      this.highlightColor = paramJGraph.getHighlightColor();
      this.isMoveBelowZero = paramJGraph.isMoveBelowZero();
      this.graph = new WeakReference(paramJGraph);
      this.focus = paramBoolean2;
      this.selected = paramBoolean1;
      this.preview = paramBoolean3;
      this.childrenSelected = paramJGraph.getSelectionModel().isChildrenSelected(paramCellView.getCell());
      setView(paramCellView);
      return this;
    }
    return null;
  }
  
  public boolean intersects(JGraph paramJGraph, CellView paramCellView, Rectangle paramRectangle)
  {
    if (((paramCellView instanceof EdgeView)) && (paramJGraph != null) && (paramCellView != null))
    {
      setView(paramCellView);
      Graphics2D localGraphics2D = (Graphics2D)paramJGraph.getGraphics();
      EdgeView localEdgeView = (EdgeView)paramCellView;
      if ((localGraphics2D == null) || (localEdgeView.getPointCount() == 2))
      {
        localObject1 = localEdgeView.getPoint(0);
        Point2D localPoint2D1 = localEdgeView.getPoint(1);
        if (paramRectangle.intersectsLine(((Point2D)localObject1).getX(), ((Point2D)localObject1).getY(), localPoint2D1.getX(), localPoint2D1.getY())) {
          return true;
        }
      }
      else if ((localGraphics2D != null) && (localGraphics2D.hit(paramRectangle, this.view.getShape(), true)))
      {
        return true;
      }
      Object localObject1 = getLabelBounds(paramJGraph, this.view);
      if ((localObject1 != null) && (((Rectangle2D)localObject1).intersects(paramRectangle)))
      {
        boolean bool1 = true;
        if (HIT_LABEL_EXACT)
        {
          AffineTransform localAffineTransform = localGraphics2D.getTransform();
          try
          {
            String str = paramJGraph.convertValueToString(this.view);
            Point2D localPoint2D2 = getLabelPosition(this.view);
            Dimension localDimension = getLabelSize(this.view, str);
            Rectangle localRectangle = new Rectangle((int)localPoint2D2.getX(), (int)localPoint2D2.getY(), localDimension.width, localDimension.height);
            double d1 = localRectangle.getCenterX();
            double d2 = localRectangle.getCenterY();
            localGraphics2D.translate(-localDimension.width / 2, -localDimension.height * 0.75D - this.metrics.getDescent());
            boolean bool2 = isLabelTransform(str);
            double d3 = 0.0D;
            if (bool2)
            {
              d3 = getLabelAngle(str);
              localGraphics2D.rotate(d3, d1, d2);
            }
            bool1 = localGraphics2D.hit(paramRectangle, localRectangle, false);
          }
          finally
          {
            localGraphics2D.setTransform(localAffineTransform);
          }
        }
        if (bool1) {
          return true;
        }
      }
      Object[] arrayOfObject = GraphConstants.getExtraLabels(this.view.getAllAttributes());
      if (arrayOfObject != null) {
        for (int i = 0; i < arrayOfObject.length; i++)
        {
          localObject1 = getExtraLabelBounds(paramJGraph, this.view, i);
          if ((localObject1 != null) && (((Rectangle2D)localObject1).intersects(paramRectangle))) {
            return true;
          }
        }
      }
    }
    return false;
  }
  
  public Rectangle2D getBounds(CellView paramCellView)
  {
    if (((paramCellView instanceof EdgeView)) && (paramCellView != null))
    {
      this.view = ((EdgeView)paramCellView);
      Rectangle2D localRectangle2D1 = getPaintBounds(this.view);
      JGraph localJGraph = null;
      if (this.graph != null) {
        localJGraph = (JGraph)this.graph.get();
      }
      Rectangle2D localRectangle2D2 = getLabelBounds(localJGraph, this.view);
      if (localRectangle2D2 != null) {
        Rectangle2D.union(localRectangle2D1, localRectangle2D2, localRectangle2D1);
      }
      Object[] arrayOfObject = GraphConstants.getExtraLabels(this.view.getAllAttributes());
      if (arrayOfObject != null) {
        for (i = 0; i < arrayOfObject.length; i++)
        {
          localRectangle2D2 = getExtraLabelBounds(localJGraph, this.view, i);
          if (localRectangle2D2 != null) {
            Rectangle2D.union(localRectangle2D1, localRectangle2D2, localRectangle2D1);
          }
        }
      }
      int i = (int)Math.ceil(this.lineWidth);
      localRectangle2D1.setFrame(localRectangle2D1.getX() - i, localRectangle2D1.getY() - i, localRectangle2D1.getWidth() + 2 * i, localRectangle2D1.getHeight() + 2 * i);
      return localRectangle2D1;
    }
    return null;
  }
  
  private boolean isLabelTransformEnabled()
  {
    return this.labelTransformEnabled;
  }
  
  private boolean isLabelTransform(String paramString)
  {
    if (!isLabelTransformEnabled()) {
      return false;
    }
    Point2D localPoint2D1 = getLabelPosition(this.view);
    if ((localPoint2D1 != null) && (paramString != null) && (paramString.length() > 0))
    {
      int i = this.metrics.stringWidth(paramString);
      Point2D localPoint2D2 = this.view.getPoint(0);
      Point2D localPoint2D3 = this.view.getPoint(this.view.getPointCount() - 1);
      double d = Math.sqrt((localPoint2D3.getX() - localPoint2D2.getX()) * (localPoint2D3.getX() - localPoint2D2.getX()) + (localPoint2D3.getY() - localPoint2D2.getY()) * (localPoint2D3.getY() - localPoint2D2.getY()));
      if ((d > NaN.0D) && (d >= i)) {
        return true;
      }
    }
    return false;
  }
  
  private double getLabelAngle(String paramString)
  {
    Point2D localPoint2D1 = getLabelPosition(this.view);
    double d1 = 0.0D;
    if ((localPoint2D1 != null) && (paramString != null) && (paramString.length() > 0))
    {
      int i = this.metrics.stringWidth(paramString);
      Point2D localPoint2D2 = this.view.getPoint(0);
      Point2D localPoint2D3 = this.view.getPoint(this.view.getPointCount() - 1);
      double d2 = Math.sqrt((localPoint2D3.getX() - localPoint2D2.getX()) * (localPoint2D3.getX() - localPoint2D2.getX()) + (localPoint2D3.getY() - localPoint2D2.getY()) * (localPoint2D3.getY() - localPoint2D2.getY()));
      if ((d2 > NaN.0D) && (d2 >= i))
      {
        double d3 = (localPoint2D3.getX() - localPoint2D2.getX()) / d2;
        double d4 = (localPoint2D3.getY() - localPoint2D2.getY()) / d2;
        d1 = Math.acos(d3);
        if (d4 < 0.0D) {
          d1 = 6.283185307179586D - d1;
        }
      }
      if ((d1 > 1.5707963267948966D) && (d1 <= 4.71238898038469D)) {
        d1 -= 3.141592653589793D;
      }
    }
    return d1;
  }
  
  public Rectangle2D getLabelBounds(JGraph paramJGraph, EdgeView paramEdgeView)
  {
    if ((paramJGraph == null) && (this.graph != null))
    {
      localObject = (JGraph)this.graph.get();
      paramJGraph = (JGraph)localObject;
    }
    Object localObject = paramJGraph != null ? paramJGraph.convertValueToString(paramEdgeView) : String.valueOf(paramEdgeView.getCell());
    if (localObject != null)
    {
      Point2D localPoint2D = getLabelPosition(paramEdgeView);
      Dimension localDimension = getLabelSize(paramEdgeView, (String)localObject);
      return getLabelBounds(localPoint2D, localDimension, (String)localObject);
    }
    return null;
  }
  
  public Rectangle2D getExtraLabelBounds(JGraph paramJGraph, EdgeView paramEdgeView, int paramInt)
  {
    if ((paramJGraph == null) && (this.graph != null))
    {
      localObject = (JGraph)this.graph.get();
      paramJGraph = (JGraph)localObject;
    }
    setView(paramEdgeView);
    Object localObject = GraphConstants.getExtraLabels(paramEdgeView.getAllAttributes());
    if ((localObject != null) && (paramInt < localObject.length))
    {
      Point2D localPoint2D = getExtraLabelPosition(this.view, paramInt);
      Dimension localDimension = getExtraLabelSize(paramJGraph, this.view, paramInt);
      String str = paramJGraph != null ? paramJGraph.convertValueToString(localObject[paramInt]) : String.valueOf(localObject[paramInt]);
      return getLabelBounds(localPoint2D, localDimension, str);
    }
    return new Rectangle2D.Double(getX(), getY(), 0.0D, 0.0D);
  }
  
  public Rectangle2D getLabelBounds(Point2D paramPoint2D, Dimension paramDimension, String paramString)
  {
    double d1;
    double d2;
    if ((paramString != null) && (isLabelTransform(paramString)))
    {
      d1 = getLabelAngle(paramString);
      if (d1 < 0.0D) {
        d1 = -d1;
      }
      if (d1 > 1.5707963267948966D) {
        d1 %= 1.5707963267948966D;
      }
      d2 = Math.abs(Math.cos(d1) * paramDimension.height + Math.sin(d1) * paramDimension.width);
      double d3 = Math.abs(paramDimension.width * Math.cos(d1) + paramDimension.height * Math.sin(d1));
      if (d3 > d2) {
        d2 = d3;
      }
      if (d2 > d3) {
        d3 = d2;
      }
      d1 = getLabelAngle(paramString);
      paramDimension.width = ((int)d3 + paramDimension.height);
      paramDimension.height = ((int)d2 + paramDimension.height);
    }
    if ((paramPoint2D != null) && (paramDimension != null))
    {
      d1 = Math.max(0.0D, paramPoint2D.getX() - paramDimension.width / 2);
      d2 = Math.max(0.0D, paramPoint2D.getY() - paramDimension.height / 2);
      return new Rectangle2D.Double(d1, d2, paramDimension.width + 1, paramDimension.height + 1);
    }
    return null;
  }
  
  public Point2D getLabelPosition(EdgeView paramEdgeView)
  {
    setView(paramEdgeView);
    return getLabelPosition(paramEdgeView.getLabelPosition());
  }
  
  public Point2D getExtraLabelPosition(EdgeView paramEdgeView, int paramInt)
  {
    setView(paramEdgeView);
    Point2D[] arrayOfPoint2D = GraphConstants.getExtraLabelPositions(paramEdgeView.getAllAttributes());
    if ((arrayOfPoint2D != null) && (paramInt < arrayOfPoint2D.length)) {
      return getLabelPosition(arrayOfPoint2D[paramInt]);
    }
    return null;
  }
  
  protected Point2D getLabelPosition(Point2D paramPoint2D)
  {
    Rectangle2D localRectangle2D = getPaintBounds(this.view);
    int i = 1000;
    Point2D localPoint2D1 = this.view.getPoint(0);
    if ((paramPoint2D != null) && (localRectangle2D != null) && (localPoint2D1 != null))
    {
      if (!isLabelTransformEnabled()) {
        return this.view.getAbsoluteLabelPositionFromRelative(paramPoint2D);
      }
      Point2D localPoint2D2 = this.view.getLabelVector();
      double d1 = localPoint2D2.getX();
      double d2 = localPoint2D2.getY();
      double d3 = Math.sqrt(d1 * d1 + d2 * d2);
      if (d3 > 0.0D)
      {
        int j = this.view.getFirstPointOfSegment();
        if ((j >= 0) && (j < this.view.getPointCount() - 1)) {
          localPoint2D1 = this.view.getPoint(j);
        }
        double d4 = localPoint2D1.getX() + d1 * paramPoint2D.getX() / i;
        double d5 = localPoint2D1.getY() + d2 * paramPoint2D.getX() / i;
        d4 += -d2 * paramPoint2D.getY() / d3;
        d5 += d1 * paramPoint2D.getY() / d3;
        return new Point2D.Double(d4, d5);
      }
      return new Point2D.Double(localPoint2D1.getX() + paramPoint2D.getX(), localPoint2D1.getY() + paramPoint2D.getY());
    }
    return null;
  }
  
  public Dimension getExtraLabelSize(JGraph paramJGraph, EdgeView paramEdgeView, int paramInt)
  {
    Object[] arrayOfObject = GraphConstants.getExtraLabels(paramEdgeView.getAllAttributes());
    if ((arrayOfObject != null) && (paramInt < arrayOfObject.length))
    {
      String str = paramJGraph != null ? paramJGraph.convertValueToString(arrayOfObject[paramInt]) : String.valueOf(arrayOfObject[paramInt]);
      return getLabelSize(paramEdgeView, str);
    }
    return null;
  }
  
  public Dimension getLabelSize(EdgeView paramEdgeView, String paramString)
  {
    if ((paramString != null) && (fontGraphics != null))
    {
      fontGraphics.setFont(GraphConstants.getFont(paramEdgeView.getAllAttributes()));
      this.metrics = fontGraphics.getFontMetrics();
      int i = (int)(this.metrics.stringWidth(paramString) * LABELWIDTHBUFFER);
      int j = this.metrics.getHeight();
      return new Dimension(i, j);
    }
    return null;
  }
  
  protected void installAttributes(CellView paramCellView)
  {
    AttributeMap localAttributeMap = paramCellView.getAllAttributes();
    this.beginDeco = GraphConstants.getLineBegin(localAttributeMap);
    this.beginSize = GraphConstants.getBeginSize(localAttributeMap);
    this.beginFill = ((GraphConstants.isBeginFill(localAttributeMap)) && (isFillable(this.beginDeco)));
    this.endDeco = GraphConstants.getLineEnd(localAttributeMap);
    this.endSize = GraphConstants.getEndSize(localAttributeMap);
    this.endFill = ((GraphConstants.isEndFill(localAttributeMap)) && (isFillable(this.endDeco)));
    this.lineWidth = GraphConstants.getLineWidth(localAttributeMap);
    Edge.Routing localRouting = GraphConstants.getRouting(localAttributeMap);
    this.lineStyle = ((localRouting != null) && ((paramCellView instanceof EdgeView)) ? localRouting.getPreferredLineStyle((EdgeView)paramCellView) : -1);
    if (this.lineStyle == -1) {
      this.lineStyle = GraphConstants.getLineStyle(localAttributeMap);
    }
    this.lineDash = GraphConstants.getDashPattern(localAttributeMap);
    this.dashOffset = GraphConstants.getDashOffset(localAttributeMap);
    this.borderColor = GraphConstants.getBorderColor(localAttributeMap);
    Color localColor1 = GraphConstants.getLineColor(localAttributeMap);
    setForeground(localColor1 != null ? localColor1 : this.defaultForeground);
    Color localColor2 = GraphConstants.getBackground(localAttributeMap);
    setBackground(localColor2 != null ? localColor2 : this.defaultBackground);
    Color localColor3 = GraphConstants.getGradientColor(localAttributeMap);
    setGradientColor(localColor3);
    setOpaque(GraphConstants.isOpaque(localAttributeMap));
    setFont(GraphConstants.getFont(localAttributeMap));
    Color localColor4 = GraphConstants.getForeground(localAttributeMap);
    this.fontColor = (localColor4 != null ? localColor4 : getForeground());
    this.labelTransformEnabled = GraphConstants.isLabelAlongEdge(localAttributeMap);
    this.labelsEnabled = GraphConstants.isLabelEnabled(localAttributeMap);
  }
  
  protected boolean isFillable(int paramInt)
  {
    return (paramInt != 4) && (paramInt != 7) && (paramInt != 8);
  }
  
  public Rectangle2D getPaintBounds(EdgeView paramEdgeView)
  {
    Object localObject = null;
    setView(paramEdgeView);
    if (paramEdgeView.getShape() != null) {
      localObject = paramEdgeView.getShape().getBounds();
    } else {
      localObject = new Rectangle2D.Double(0.0D, 0.0D, 0.0D, 0.0D);
    }
    return (Rectangle2D)localObject;
  }
  
  public void paint(Graphics paramGraphics)
  {
    if (this.view.isLeaf())
    {
      Shape localShape = this.view.getShape();
      if (localShape != null)
      {
        Graphics2D localGraphics2D = (Graphics2D)paramGraphics;
        localGraphics2D.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        setOpaque(false);
        super.paint(paramGraphics);
        translateGraphics(paramGraphics);
        beforeEdgePaint(paramGraphics);
        paintEdge(paramGraphics);
        paintSelection(paramGraphics);
        paintLabels(paramGraphics);
        afterEdgePaint(paramGraphics);
      }
    }
    else
    {
      paintSelectionBorder(paramGraphics);
    }
  }
  
  protected void paintLabels(Graphics paramGraphics)
  {
    Graphics2D localGraphics2D = (Graphics2D)paramGraphics;
    localGraphics2D.setStroke(new BasicStroke(1.0F));
    paramGraphics.setFont(this.extraLabelFont != null ? this.extraLabelFont : getFont());
    Object[] arrayOfObject = GraphConstants.getExtraLabels(this.view.getAllAttributes());
    JGraph localJGraph = (JGraph)this.graph.get();
    if (arrayOfObject != null) {
      for (int i = 0; i < arrayOfObject.length; i++) {
        paintLabel(paramGraphics, localJGraph.convertValueToString(arrayOfObject[i]), getExtraLabelPosition(this.view, i), !this.simpleExtraLabels);
      }
    }
    if (localJGraph.getEditingCell() != this.view.getCell())
    {
      paramGraphics.setFont(getFont());
      String str = localJGraph.convertValueToString(this.view);
      if (str != null) {
        paintLabel(paramGraphics, str.toString(), getLabelPosition(this.view), true);
      }
    }
  }
  
  protected void paintEdge(Graphics paramGraphics)
  {
    paramGraphics.setColor(getForeground());
    if (this.lineWidth > 0.0F)
    {
      Graphics2D localGraphics2D = (Graphics2D)paramGraphics;
      int i = 0;
      int j = 0;
      localGraphics2D.setStroke(new BasicStroke(this.lineWidth, i, j));
      if ((this.gradientColor != null) && (!this.preview)) {
        localGraphics2D.setPaint(new GradientPaint(0.0F, 0.0F, getBackground(), getWidth(), getHeight(), this.gradientColor, true));
      }
      if (this.view.beginShape != null)
      {
        if (this.beginFill) {
          localGraphics2D.fill(this.view.beginShape);
        }
        localGraphics2D.draw(this.view.beginShape);
      }
      if (this.view.endShape != null)
      {
        if (this.endFill) {
          localGraphics2D.fill(this.view.endShape);
        }
        localGraphics2D.draw(this.view.endShape);
      }
      if (this.lineDash != null) {
        localGraphics2D.setStroke(new BasicStroke(this.lineWidth, i, j, 10.0F, this.lineDash, this.dashOffset));
      }
      if (this.view.lineShape != null) {
        localGraphics2D.draw(this.view.lineShape);
      }
    }
  }
  
  protected void paintSelection(Graphics paramGraphics)
  {
    if (this.selected)
    {
      Graphics2D localGraphics2D = (Graphics2D)paramGraphics;
      localGraphics2D.setStroke(GraphConstants.SELECTION_STROKE);
      localGraphics2D.setColor(this.highlightColor);
      if (this.view.beginShape != null) {
        localGraphics2D.draw(this.view.beginShape);
      }
      if (this.view.lineShape != null) {
        localGraphics2D.draw(this.view.lineShape);
      }
      if (this.view.endShape != null) {
        localGraphics2D.draw(this.view.endShape);
      }
    }
  }
  
  protected void beforeEdgePaint(Graphics paramGraphics) {}
  
  protected void afterEdgePaint(Graphics paramGraphics) {}
  
  protected void paintSelectionBorder(Graphics paramGraphics)
  {
    ((Graphics2D)paramGraphics).setStroke(GraphConstants.SELECTION_STROKE);
    if (this.childrenSelected) {
      paramGraphics.setColor(this.gridColor);
    } else if ((this.focus) && (this.selected)) {
      paramGraphics.setColor(this.lockedHandleColor);
    } else if (this.selected) {
      paramGraphics.setColor(this.highlightColor);
    }
    if ((this.childrenSelected) || (this.selected))
    {
      Dimension localDimension = getSize();
      paramGraphics.drawRect(0, 0, localDimension.width - 1, localDimension.height - 1);
    }
  }
  
  protected void translateGraphics(Graphics paramGraphics)
  {
    paramGraphics.translate(-getX(), -getY());
  }
  
  protected void paintLabel(Graphics paramGraphics, String paramString, Point2D paramPoint2D, boolean paramBoolean)
  {
    if ((this.labelsEnabled) && (paramPoint2D != null) && (paramString != null) && (paramString.length() > 0) && (this.metrics != null))
    {
      int i = this.metrics.stringWidth(paramString);
      int j = this.metrics.getHeight();
      Graphics2D localGraphics2D = (Graphics2D)paramGraphics;
      boolean bool = isLabelTransform(paramString);
      double d = 0.0D;
      int k = -i / 2;
      int m = (this.isMoveBelowZero) || (bool) ? 0 : Math.min(0, (int)(k + paramPoint2D.getX()));
      localGraphics2D.translate(paramPoint2D.getX() - m, paramPoint2D.getY());
      if (bool)
      {
        d = getLabelAngle(paramString);
        localGraphics2D.rotate(d);
      }
      if ((isOpaque()) && (paramBoolean))
      {
        paramGraphics.setColor(getBackground());
        paramGraphics.fillRect(-i / 2 - 1, -j / 2 - 1, i + 2, j + 2);
      }
      if ((this.borderColor != null) && (paramBoolean))
      {
        paramGraphics.setColor(this.borderColor);
        paramGraphics.drawRect(-i / 2 - 1, -j / 2 - 1, i + 2, j + 2);
      }
      int n = j / 4;
      paramGraphics.setColor(this.fontColor);
      if ((bool) && (this.borderColor == null) && (!isOpaque())) {
        n = -this.metrics.getDescent();
      }
      paramGraphics.drawString(paramString, k, n);
      if (bool) {
        localGraphics2D.rotate(-d);
      }
      localGraphics2D.translate(-paramPoint2D.getX() + m, -paramPoint2D.getY());
    }
  }
  
  protected Shape createShape()
  {
    int i = this.view.getPointCount();
    if (i > 1)
    {
      EdgeView localEdgeView = this.view;
      Point2D[] arrayOfPoint2D = null;
      arrayOfPoint2D = new Point2D[i];
      for (int j = 0; j < i; j++)
      {
        localPoint2D2 = localEdgeView.getPoint(j);
        if (localPoint2D2 == null) {
          return null;
        }
        arrayOfPoint2D[j] = new Point2D.Double(localPoint2D2.getX(), localPoint2D2.getY());
      }
      if (this.view != localEdgeView)
      {
        this.view = localEdgeView;
        installAttributes(this.view);
      }
      if (this.view.sharedPath == null) {
        this.view.sharedPath = new GeneralPath(1, i);
      } else {
        this.view.sharedPath.reset();
      }
      this.view.beginShape = (this.view.lineShape = this.view.endShape = null);
      Point2D localPoint2D1 = arrayOfPoint2D[0];
      Point2D localPoint2D2 = arrayOfPoint2D[(i - 1)];
      Point2D localPoint2D3 = arrayOfPoint2D[1];
      Point2D localPoint2D4 = arrayOfPoint2D[(i - 2)];
      Object localObject1;
      if ((this.lineStyle == 12) && (i > 2))
      {
        this.bezier = new Bezier(arrayOfPoint2D);
        localPoint2D4 = this.bezier.getPoint(this.bezier.getPointCount() - 1);
      }
      else if ((this.lineStyle == 13) && (i > 2))
      {
        this.spline = new Spline2D(arrayOfPoint2D);
        localObject1 = this.spline.getPoint(0.9875D);
        double d2 = localPoint2D2.getX() - (localPoint2D2.getX() - localObject1[0]) * 128.0D;
        double d3 = localPoint2D2.getY() - (localPoint2D2.getY() - localObject1[1]) * 128.0D;
        localPoint2D4.setLocation(d2, d3);
      }
      if (this.beginDeco != 0) {
        this.view.beginShape = createLineEnd(this.beginSize, this.beginDeco, localPoint2D3, localPoint2D1);
      }
      if (this.endDeco != 0) {
        this.view.endShape = createLineEnd(this.endSize, this.endDeco, localPoint2D4, localPoint2D2);
      }
      this.view.sharedPath.moveTo((float)localPoint2D1.getX(), (float)localPoint2D1.getY());
      double[] arrayOfDouble;
      if ((this.lineStyle == 12) && (i > 2))
      {
        localObject1 = this.bezier.getPoints();
        this.view.sharedPath.quadTo((float)localObject1[0].getX(), (float)localObject1[0].getY(), (float)localPoint2D3.getX(), (float)localPoint2D3.getY());
        for (int m = 2; m < i - 1; m++)
        {
          arrayOfDouble = localObject1[(2 * m - 3)];
          Object localObject2 = localObject1[(2 * m - 2)];
          this.view.sharedPath.curveTo((float)arrayOfDouble.getX(), (float)arrayOfDouble.getY(), (float)((Point2D)localObject2).getX(), (float)((Point2D)localObject2).getY(), (float)arrayOfPoint2D[m].getX(), (float)arrayOfPoint2D[m].getY());
        }
        this.view.sharedPath.quadTo((float)localObject1[(localObject1.length - 1)].getX(), (float)localObject1[(localObject1.length - 1)].getY(), (float)arrayOfPoint2D[(i - 1)].getX(), (float)arrayOfPoint2D[(i - 1)].getY());
      }
      else if ((this.lineStyle == 13) && (i > 2))
      {
        for (double d1 = 0.0D; d1 <= 1.0D; d1 += 0.0125D)
        {
          arrayOfDouble = this.spline.getPoint(d1);
          this.view.sharedPath.lineTo((float)arrayOfDouble[0], (float)arrayOfDouble[1]);
        }
      }
      else
      {
        for (int k = 1; k < i - 1; k++) {
          this.view.sharedPath.lineTo((float)arrayOfPoint2D[k].getX(), (float)arrayOfPoint2D[k].getY());
        }
        this.view.sharedPath.lineTo((float)localPoint2D2.getX(), (float)localPoint2D2.getY());
      }
      this.view.sharedPath.moveTo((float)localPoint2D2.getX(), (float)localPoint2D2.getY());
      if ((this.view.endShape == null) && (this.view.beginShape == null))
      {
        this.view.lineShape = this.view.sharedPath;
      }
      else
      {
        this.view.lineShape = ((GeneralPath)this.view.sharedPath.clone());
        if (this.view.endShape != null) {
          this.view.sharedPath.append(this.view.endShape, true);
        }
        if (this.view.beginShape != null) {
          this.view.sharedPath.append(this.view.beginShape, true);
        }
      }
      return this.view.sharedPath;
    }
    return null;
  }
  
  protected Shape createLineEnd(int paramInt1, int paramInt2, Point2D paramPoint2D1, Point2D paramPoint2D2)
  {
    if ((paramPoint2D1 == null) || (paramPoint2D2 == null)) {
      return null;
    }
    int i = (int)Math.max(1.0D, paramPoint2D2.distance(paramPoint2D1));
    int j = (int)-(paramInt1 * (paramPoint2D2.getX() - paramPoint2D1.getX()) / i);
    int k = (int)-(paramInt1 * (paramPoint2D2.getY() - paramPoint2D1.getY()) / i);
    Object localObject;
    Point2D localPoint2D;
    if (paramInt2 == 9)
    {
      localObject = new Polygon();
      ((Polygon)localObject).addPoint((int)paramPoint2D2.getX(), (int)paramPoint2D2.getY());
      ((Polygon)localObject).addPoint((int)(paramPoint2D2.getX() + j / 2 + k / 3), (int)(paramPoint2D2.getY() + k / 2 - j / 3));
      localPoint2D = (Point2D)paramPoint2D2.clone();
      paramPoint2D2.setLocation(paramPoint2D2.getX() + j, paramPoint2D2.getY() + k);
      ((Polygon)localObject).addPoint((int)paramPoint2D2.getX(), (int)paramPoint2D2.getY());
      ((Polygon)localObject).addPoint((int)(localPoint2D.getX() + j / 2 - k / 3), (int)(localPoint2D.getY() + k / 2 + j / 3));
      return (Shape)localObject;
    }
    if ((paramInt2 == 2) || (paramInt2 == 1))
    {
      localObject = new Polygon();
      ((Polygon)localObject).addPoint((int)paramPoint2D2.getX(), (int)paramPoint2D2.getY());
      ((Polygon)localObject).addPoint((int)(paramPoint2D2.getX() + j + k / 2), (int)(paramPoint2D2.getY() + k - j / 2));
      localPoint2D = (Point2D)paramPoint2D2.clone();
      if (paramInt2 == 1)
      {
        paramPoint2D2.setLocation((int)(paramPoint2D2.getX() + j * 2 / 3), (int)(paramPoint2D2.getY() + k * 2 / 3));
        ((Polygon)localObject).addPoint((int)paramPoint2D2.getX(), (int)paramPoint2D2.getY());
      }
      else if (paramInt2 == 9)
      {
        paramPoint2D2.setLocation(paramPoint2D2.getX() + 2 * j, paramPoint2D2.getY() + 2 * k);
        ((Polygon)localObject).addPoint((int)paramPoint2D2.getX(), (int)paramPoint2D2.getY());
      }
      else
      {
        paramPoint2D2.setLocation((int)(paramPoint2D2.getX() + j), (int)(paramPoint2D2.getY() + k));
      }
      ((Polygon)localObject).addPoint((int)(localPoint2D.getX() + j - k / 2), (int)(localPoint2D.getY() + k + j / 2));
      return (Shape)localObject;
    }
    if (paramInt2 == 4)
    {
      localObject = new GeneralPath(1, 4);
      ((GeneralPath)localObject).moveTo((float)(paramPoint2D2.getX() + j + k / 2), (float)(paramPoint2D2.getY() + k - j / 2));
      ((GeneralPath)localObject).lineTo((float)paramPoint2D2.getX(), (float)paramPoint2D2.getY());
      ((GeneralPath)localObject).lineTo((float)(paramPoint2D2.getX() + j - k / 2), (float)(paramPoint2D2.getY() + k + j / 2));
      return (Shape)localObject;
    }
    if (paramInt2 == 5)
    {
      localObject = new Ellipse2D.Float((float)(paramPoint2D2.getX() + j / 2 - paramInt1 / 2), (float)(paramPoint2D2.getY() + k / 2 - paramInt1 / 2), paramInt1, paramInt1);
      paramPoint2D2.setLocation(paramPoint2D2.getX() + j, paramPoint2D2.getY() + k);
      return (Shape)localObject;
    }
    if ((paramInt2 == 7) || (paramInt2 == 8))
    {
      localObject = new GeneralPath(1, 4);
      ((GeneralPath)localObject).moveTo((float)(paramPoint2D2.getX() + j / 2 + k / 2), (float)(paramPoint2D2.getY() + k / 2 - j / 2));
      ((GeneralPath)localObject).lineTo((float)(paramPoint2D2.getX() + j / 2 - k / 2), (float)(paramPoint2D2.getY() + k / 2 + j / 2));
      if (paramInt2 == 8)
      {
        ((GeneralPath)localObject).moveTo((float)(paramPoint2D2.getX() + j / 3 + k / 2), (float)(paramPoint2D2.getY() + k / 3 - j / 2));
        ((GeneralPath)localObject).lineTo((float)(paramPoint2D2.getX() + j / 3 - k / 2), (float)(paramPoint2D2.getY() + k / 3 + j / 2));
      }
      return (Shape)localObject;
    }
    return null;
  }
  
  public Color getGradientColor()
  {
    return this.gradientColor;
  }
  
  public void setGradientColor(Color paramColor)
  {
    this.gradientColor = paramColor;
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
  
  static
  {
    try
    {
      fontGraphics = new BufferedImage(1, 1, 1).getGraphics();
    }
    catch (Error localError)
    {
      fontGraphics = null;
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/graph/EdgeRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */