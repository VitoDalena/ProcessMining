package com.jgraph.components.labels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D.Double;
import java.awt.geom.Ellipse2D.Float;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import org.jgraph.JGraph;
import org.jgraph.graph.AbstractCellView;
import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.CellView;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphModel;
import org.jgraph.graph.VertexRenderer;
import org.jgraph.graph.VertexView;

public class MultiLineVertexRenderer
  extends VertexRenderer
{
  public static String CLIENTPROPERTY_SHOWFOLDINGICONS = "showFoldingIcons";
  public static Dimension ZERO_DIMENSION = new Dimension(0, 0);
  public static int INSET = 4;
  public static Rectangle handle = new Rectangle(0, 0, 7, 7);
  protected JGraph graph;
  public static final int SHAPE_RECTANGLE = 0;
  public static final int SHAPE_CIRCLE = 1;
  public static final int SHAPE_DIAMOND = 2;
  public static final int SHAPE_ROUNDED = 3;
  public static final int SHAPE_CYLINDER = 4;
  public static final int SHAPE_TRIANGLE = 5;
  public static JTextPane textPane = new JTextPane();
  protected static JComponent wrapperRenderer;
  protected Object userObject = null;
  protected int shape = 0;
  protected boolean isRichText = false;
  protected boolean stretchImage = false;
  protected boolean isEditing = false;
  protected boolean showFoldingIcons = true;
  protected boolean isGroup = false;
  protected Color graphBackground = Color.white;
  protected Color graphForeground = Color.black;
  protected Component valueComponent;
  protected Area cylinderArea = null;
  protected Polygon diamond = null;
  protected int roundRectArc = 0;
  protected transient boolean showHeavyweight = true;
  
  public MultiLineVertexRenderer()
  {
    textPane.setOpaque(false);
    textPane.setBorder(BorderFactory.createEmptyBorder(INSET, INSET, INSET, INSET));
    wrapperRenderer = new JComponent()
    {
      public void paint(Graphics paramAnonymousGraphics)
      {
        if (MultiLineVertexRenderer.this.showHeavyweight)
        {
          MultiLineVertexRenderer.this.valueComponent.setSize(getSize());
          if (!MultiLineVertexRenderer.this.isEditing) {
            MultiLineVertexRenderer.this.valueComponent.paint(paramAnonymousGraphics);
          }
        }
        else
        {
          paramAnonymousGraphics.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
          paramAnonymousGraphics.drawLine(0, 0, getWidth() - 1, getHeight() - 1);
          paramAnonymousGraphics.drawLine(getWidth() - 1, 0, 0, getHeight() - 1);
        }
      }
    };
    wrapperRenderer.setDoubleBuffered(false);
  }
  
  public Component getRendererComponent(JGraph paramJGraph, CellView paramCellView, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    this.graph = paramJGraph;
    Component localComponent = super.getRendererComponent(paramJGraph, paramCellView, paramBoolean1, paramBoolean2, paramBoolean3);
    this.graphBackground = paramJGraph.getBackground();
    this.graphForeground = paramJGraph.getForeground();
    this.isEditing = (paramJGraph.getEditingCell() == paramCellView.getCell());
    this.isGroup = DefaultGraphModel.isGroup(paramJGraph.getModel(), paramCellView.getCell());
    Boolean localBoolean = (Boolean)paramJGraph.getClientProperty(CLIENTPROPERTY_SHOWFOLDINGICONS);
    if (localBoolean != null) {
      this.showFoldingIcons = localBoolean.booleanValue();
    } else {
      this.showFoldingIcons = true;
    }
    if (this.valueComponent != null)
    {
      this.valueComponent.setSize(getSize());
      this.valueComponent.setBackground(getBackground());
      this.showHeavyweight = ((!paramBoolean3) || (paramJGraph.getScale() == 1.0D));
      if ((this.valueComponent instanceof JComponent))
      {
        JComponent localJComponent = (JComponent)this.valueComponent;
        localJComponent.setBorder(getBorder());
        localJComponent.setOpaque(isOpaque());
        localJComponent.setDoubleBuffered(false);
        if ((localJComponent.getComponentCount() > 0) && (!this.isEditing) && (this.showHeavyweight)) {
          return localJComponent;
        }
      }
      return wrapperRenderer;
    }
    return localComponent;
  }
  
  public void paint(Graphics paramGraphics)
  {
    Border localBorder = getBorder();
    paintBackground(paramGraphics);
    Dimension localDimension = getSize();
    int i = this.borderWidth;
    Shape localShape = paramGraphics.getClip();
    Object localObject;
    if ((this.shape == 2) || (this.shape == 5))
    {
      localObject = new Area(this.diamond);
      ((Area)localObject).intersect(new Area(localShape));
      paramGraphics.setClip((Shape)localObject);
    }
    else if (this.shape == 4)
    {
      localObject = new Area(this.cylinderArea);
      this.cylinderArea.intersect(new Area(localShape));
      paramGraphics.setClip((Shape)localObject);
    }
    else if (this.shape == 1)
    {
      localObject = new Area(new Ellipse2D.Float(-i, -i, localDimension.width + i, localDimension.height + i));
      ((Area)localObject).intersect(new Area(localShape));
      paramGraphics.setClip((Shape)localObject);
    }
    if (this.stretchImage)
    {
      localObject = null;
      Icon localIcon = getIcon();
      if (localIcon != null) {
        localObject = ((ImageIcon)localIcon).getImage();
      }
      if ((localObject != null) && (!this.preview)) {
        paramGraphics.drawImage((Image)localObject, 0, 0, localDimension.width - 1, localDimension.height - 1, this);
      }
      setIcon(null);
    }
    boolean bool1 = this.selected;
    boolean bool2 = isOpaque();
    if (this.shape != 0)
    {
      setBorder(null);
      setOpaque(false);
      this.selected = false;
    }
    if (this.isRichText) {
      setText("");
    }
    super.paint(paramGraphics);
    setBorder(localBorder);
    setOpaque(bool2);
    this.selected = bool1;
    if (this.isRichText) {
      paintRichText(paramGraphics);
    }
    paramGraphics.setClip(localShape);
    Graphics2D localGraphics2D = (Graphics2D)paramGraphics;
    Stroke localStroke = localGraphics2D.getStroke();
    if ((this.shape != 0) && (getBorder() != null))
    {
      paramGraphics.setColor(this.bordercolor);
      localGraphics2D.setStroke(new BasicStroke(i));
      paintShapeBorder(paramGraphics);
    }
    if ((this.selected) || (this.childrenSelected))
    {
      if (this.childrenSelected) {
        paramGraphics.setColor(this.gridColor);
      } else {
        paramGraphics.setColor(this.highlightColor);
      }
      localGraphics2D.setStroke(GraphConstants.SELECTION_STROKE);
      paintShapeBorder(paramGraphics);
    }
    localGraphics2D.setStroke(localStroke);
    if (this.showFoldingIcons) {
      paintFoldingIcon(paramGraphics);
    }
  }
  
  protected void paintBackground(Graphics paramGraphics)
  {
    Dimension localDimension = getSize();
    int i = this.borderWidth;
    if (this.shape != 0)
    {
      int j;
      int k;
      int m;
      int[] arrayOfInt2;
      if (this.shape == 2)
      {
        j = localDimension.width - i;
        k = localDimension.height - i;
        m = (localDimension.width - i) / 2;
        int n = (localDimension.height - i) / 2;
        arrayOfInt2 = new int[] { m, j, m, 0 };
        int[] arrayOfInt3 = { 0, n, k, n };
        this.diamond = new Polygon(arrayOfInt2, arrayOfInt3, 4);
      }
      if (this.shape == 5)
      {
        j = localDimension.width - i;
        k = localDimension.height - i;
        m = (localDimension.height - i) / 2;
        int[] arrayOfInt1 = { 0, j, 0 };
        arrayOfInt2 = new int[] { 0, m, k };
        this.diamond = new Polygon(arrayOfInt1, arrayOfInt2, 3);
      }
      else if (this.shape == 4)
      {
        j = (int)(localDimension.getHeight() / 4.0D);
        k = localDimension.width - i - 1;
        this.cylinderArea = new Area(new Rectangle(i, (j - i) / 2 + i, k, localDimension.height - j - i));
        this.cylinderArea.add(new Area(new Ellipse2D.Double(i, i, k, j - i)));
        this.cylinderArea.add(new Area(new Ellipse2D.Double(i, localDimension.height - j - i, k, j)));
      }
      else if (this.shape == 3)
      {
        this.roundRectArc = getArcSize(localDimension.width - i, localDimension.height - i);
      }
      if (isOpaque())
      {
        paramGraphics.setColor(super.getBackground());
        if ((this.gradientColor != null) && (!this.preview)) {
          ((Graphics2D)paramGraphics).setPaint(new GradientPaint(0.0F, 0.0F, getBackground(), getWidth(), getHeight(), this.gradientColor, true));
        }
        if (this.shape == 1) {
          paramGraphics.fillOval(i - 1, i - 1, localDimension.width - i, localDimension.height - i);
        } else if (this.shape == 4) {
          ((Graphics2D)paramGraphics).fill(this.cylinderArea);
        } else if ((this.shape == 2) || (this.shape == 5)) {
          paramGraphics.fillPolygon(this.diamond);
        } else if (this.shape == 3) {
          paramGraphics.fillRoundRect(i / 2, i / 2, localDimension.width - (int)(i * 1.5D), localDimension.height - (int)(i * 1.5D), this.roundRectArc, this.roundRectArc);
        }
      }
    }
  }
  
  protected void paintRichText(Graphics paramGraphics)
  {
    textPane.setSize(getSize());
    int i = 0;
    if (getVerticalAlignment() == 0) {
      i = (int)((getHeight() - textPane.getPreferredSize().getHeight()) / 2.0D) + 2 * INSET;
    } else if (getVerticalAlignment() == 3) {
      i = (int)(getHeight() - textPane.getPreferredSize().getHeight() + 3 * INSET);
    }
    paramGraphics.translate(0, i);
    textPane.paint(paramGraphics);
    paramGraphics.translate(0, -i);
  }
  
  protected void paintShapeBorder(Graphics paramGraphics)
  {
    Dimension localDimension = getSize();
    int i = this.borderWidth;
    if (this.shape == 1)
    {
      paramGraphics.drawOval(i - 1, i - 1, localDimension.width - i, localDimension.height - i);
    }
    else if (this.shape == 4)
    {
      int j = (int)(localDimension.getHeight() / 4.0D);
      int k = localDimension.width - i - 1;
      paramGraphics.drawOval(i, i, k, j - i);
      paramGraphics.drawLine(i, (j - i) / 2 + 2 + i, i, localDimension.height - (j - i) / 2 - 2 - i);
      paramGraphics.drawLine(localDimension.width - (i + 1) / 2, (j - i) / 2 + 2 + i, localDimension.width - (i + 1) / 2, localDimension.height - (j - i) / 2 - 2 - i);
      paramGraphics.drawArc(i, localDimension.height - j - i, k, j, 0, isOpaque() ? 65356 : 360);
    }
    else if ((this.shape == 2) || (this.shape == 5))
    {
      paramGraphics.drawPolygon(this.diamond);
    }
    else if (this.shape == 3)
    {
      paramGraphics.drawRoundRect(i / 2, i / 2, localDimension.width - (int)(i * 1.5D) - 1, localDimension.height - (int)(i * 1.5D), this.roundRectArc, this.roundRectArc);
    }
  }
  
  protected void paintFoldingIcon(Graphics paramGraphics)
  {
    if (this.isGroup)
    {
      paramGraphics.setColor(this.graphBackground);
      paramGraphics.fill3DRect(handle.x, handle.y, handle.width, handle.height, true);
      paramGraphics.setColor(this.graphForeground);
      paramGraphics.drawRect(handle.x, handle.y, handle.width, handle.height);
      int i = handle.y + handle.height / 2;
      paramGraphics.drawLine(handle.x + 1, i, handle.x + handle.width - 2, i);
      if (this.view.isLeaf())
      {
        int j = handle.x + handle.width / 2;
        paramGraphics.drawLine(j, handle.y + 1, j, handle.y + handle.height - 2);
      }
    }
  }
  
  public static int getArcSize(int paramInt1, int paramInt2)
  {
    int i;
    if (paramInt1 <= paramInt2)
    {
      i = paramInt2 / 5;
      if (i > paramInt1 / 2) {
        i = paramInt1 / 2;
      }
    }
    else
    {
      i = paramInt1 / 5;
      if (i > paramInt2 / 2) {
        i = paramInt2 / 2;
      }
    }
    return i;
  }
  
  public Point2D getPerimeterPoint(VertexView paramVertexView, Point2D paramPoint2D1, Point2D paramPoint2D2)
  {
    int i = CellConstants.getVertexShape(paramVertexView.getAllAttributes());
    if (i == 2) {
      return getDiamondPerimeterPoint(paramVertexView, paramPoint2D1, paramPoint2D2);
    }
    if (i == 1) {
      return getCirclePerimeterPoint(paramVertexView, paramPoint2D1, paramPoint2D2);
    }
    if (i == 5) {
      return getTrianglePerimeterPoint(paramVertexView, paramPoint2D1, paramPoint2D2);
    }
    return super.getPerimeterPoint(paramVertexView, paramPoint2D1, paramPoint2D2);
  }
  
  public Point2D getCirclePerimeterPoint(VertexView paramVertexView, Point2D paramPoint2D1, Point2D paramPoint2D2)
  {
    Rectangle2D localRectangle2D = paramVertexView.getBounds();
    double d1 = localRectangle2D.getX();
    double d2 = localRectangle2D.getY();
    double d3 = (localRectangle2D.getWidth() + 1.0D) / 2.0D;
    double d4 = (localRectangle2D.getHeight() + 1.0D) / 2.0D;
    double d5 = d1 + d3;
    double d6 = d2 + d4;
    double d7 = paramPoint2D2.getX();
    double d8 = paramPoint2D2.getY();
    double d9 = d7 - d5;
    double d10 = d8 - d6;
    if (d9 == 0.0D) {
      return new Point((int)d5, (int)(d6 + d4 * d10 / Math.abs(d10)));
    }
    double d11 = d10 / d9;
    double d12 = d6 - d11 * d5;
    double d13 = d3 * d3 * d11 * d11 + d4 * d4;
    double d14 = -2.0D * d5 * d13;
    double d15 = d3 * d3 * d11 * d11 * d5 * d5 + d4 * d4 * d5 * d5 - d3 * d3 * d4 * d4;
    double d16 = Math.sqrt(d14 * d14 - 4.0D * d13 * d15);
    double d17 = (-d14 + d16) / (2.0D * d13);
    double d18 = (-d14 - d16) / (2.0D * d13);
    double d19 = d11 * d17 + d12;
    double d20 = d11 * d18 + d12;
    double d21 = Math.sqrt(Math.pow(d17 - d7, 2.0D) + Math.pow(d19 - d8, 2.0D));
    double d22 = Math.sqrt(Math.pow(d18 - d7, 2.0D) + Math.pow(d20 - d8, 2.0D));
    double d23;
    double d24;
    if (d21 < d22)
    {
      d23 = d17;
      d24 = d19;
    }
    else
    {
      d23 = d18;
      d24 = d20;
    }
    return new Point2D.Double(d23, d24);
  }
  
  public Point2D getDiamondPerimeterPoint(VertexView paramVertexView, Point2D paramPoint2D1, Point2D paramPoint2D2)
  {
    Rectangle2D localRectangle2D = paramVertexView.getBounds();
    Point2D localPoint2D1 = AbstractCellView.getCenterPoint(paramVertexView);
    double d1 = localRectangle2D.getWidth() / 2.0D;
    double d2 = localRectangle2D.getHeight() / 2.0D;
    Point2D.Double localDouble1 = new Point2D.Double(localPoint2D1.getX(), localPoint2D1.getY() - d2);
    Point2D.Double localDouble2 = new Point2D.Double(localPoint2D1.getX(), localPoint2D1.getY() + d2);
    Point2D.Double localDouble3 = new Point2D.Double(localPoint2D1.getX() - d1, localPoint2D1.getY());
    Point2D.Double localDouble4 = new Point2D.Double(localPoint2D1.getX() + d1, localPoint2D1.getY());
    if (localPoint2D1.getX() == paramPoint2D2.getX())
    {
      if (localPoint2D1.getY() > paramPoint2D2.getY()) {
        return localDouble1;
      }
      return localDouble2;
    }
    if (localPoint2D1.getY() == paramPoint2D2.getY())
    {
      if (localPoint2D1.getX() > paramPoint2D2.getX()) {
        return localDouble3;
      }
      return localDouble4;
    }
    Point2D localPoint2D2;
    if (paramPoint2D2.getX() < localPoint2D1.getX())
    {
      if (paramPoint2D2.getY() < localPoint2D1.getY()) {
        localPoint2D2 = intersection(paramPoint2D2, localPoint2D1, localDouble1, localDouble3);
      } else {
        localPoint2D2 = intersection(paramPoint2D2, localPoint2D1, localDouble2, localDouble3);
      }
    }
    else if (paramPoint2D2.getY() < localPoint2D1.getY()) {
      localPoint2D2 = intersection(paramPoint2D2, localPoint2D1, localDouble1, localDouble4);
    } else {
      localPoint2D2 = intersection(paramPoint2D2, localPoint2D1, localDouble2, localDouble4);
    }
    return localPoint2D2;
  }
  
  public Point2D getTrianglePerimeterPoint(VertexView paramVertexView, Point2D paramPoint2D1, Point2D paramPoint2D2)
  {
    Rectangle2D localRectangle2D = paramVertexView.getBounds();
    double d1 = localRectangle2D.getX();
    double d2 = localRectangle2D.getY();
    double d3 = localRectangle2D.getWidth();
    double d4 = localRectangle2D.getHeight();
    double d5 = d1 + d3 / 2.0D;
    double d6 = d2 + d4 / 2.0D;
    Point2D localPoint2D = AbstractCellView.getCenterPoint(paramVertexView);
    Point2D.Double localDouble1 = new Point2D.Double(d1, d2);
    Point2D.Double localDouble2 = new Point2D.Double(d1, d2 + d4);
    Point2D.Double localDouble3 = new Point2D.Double(d1 + d3, d6);
    double d7 = paramPoint2D2.getX() - d5;
    double d8 = paramPoint2D2.getY() - d6;
    double d9 = Math.atan2(d8, d7);
    double d10 = Math.atan2(d4, d3);
    Object localObject;
    if ((d9 < -3.141592653589793D + d10) || (d9 > 3.141592653589793D - d10)) {
      localObject = new Point2D.Double(d1, d6 - d3 * Math.tan(d9) / 2.0D);
    } else if (d6 > paramPoint2D2.getY()) {
      localObject = intersection(paramPoint2D2, localPoint2D, localDouble1, localDouble3);
    } else {
      localObject = intersection(paramPoint2D2, localPoint2D, localDouble2, localDouble3);
    }
    return (Point2D)localObject;
  }
  
  protected Point2D intersection(Point2D paramPoint2D1, Point2D paramPoint2D2, Point2D paramPoint2D3, Point2D paramPoint2D4)
  {
    double d1 = (paramPoint2D2.getY() - paramPoint2D1.getY()) / (paramPoint2D2.getX() - paramPoint2D1.getX());
    double d2 = paramPoint2D1.getY() - d1 * paramPoint2D1.getX();
    double d3 = (paramPoint2D4.getY() - paramPoint2D3.getY()) / (paramPoint2D4.getX() - paramPoint2D3.getX());
    double d4 = paramPoint2D3.getY() - d3 * paramPoint2D3.getX();
    double d5 = (d2 - d4) / (d3 - d1);
    double d6 = d1 * d5 + d2;
    Point2D.Double localDouble = new Point2D.Double(d5, d6);
    return localDouble;
  }
  
  public Dimension getPreferredSize()
  {
    Dimension localDimension = super.getPreferredSize();
    if (this.shape == 1)
    {
      localDimension.width += localDimension.width / 8;
      localDimension.height += localDimension.height / 2;
    }
    else if (this.shape == 3)
    {
      localDimension.width += localDimension.height / 5;
    }
    else
    {
      if (this.isRichText)
      {
        textPane.setSize(ZERO_DIMENSION);
        return textPane.getPreferredSize();
      }
      if (this.valueComponent != null) {
        return this.valueComponent.getPreferredSize();
      }
    }
    return localDimension;
  }
  
  protected void resetAttributes()
  {
    super.resetAttributes();
    this.shape = CellConstants.getVertexShape(this.view.getAllAttributes());
    this.isRichText = false;
    this.valueComponent = null;
  }
  
  public void installAttributes(CellView paramCellView)
  {
    super.installAttributes(paramCellView);
    AttributeMap localAttributeMap = paramCellView.getAllAttributes();
    this.shape = CellConstants.getVertexShape(paramCellView.getAllAttributes());
    this.stretchImage = CellConstants.isStretchImage(localAttributeMap);
    int i = GraphConstants.getInset(localAttributeMap);
    Border localBorder = i > 0 ? BorderFactory.createEmptyBorder(i, i, i, i) : null;
    if (localBorder != null) {
      if (getBorder() == null) {
        setBorder(localBorder);
      } else {
        setBorder(BorderFactory.createCompoundBorder(getBorder(), localBorder));
      }
    }
    this.userObject = this.graph.getModel().getValue(paramCellView.getCell());
    Object localObject;
    if ((this.userObject instanceof RichTextBusinessObject))
    {
      localObject = (RichTextBusinessObject)this.userObject;
      this.isRichText = ((RichTextBusinessObject)localObject).isRichText();
      this.valueComponent = (((RichTextBusinessObject)localObject).isComponent() ? (Component)((RichTextBusinessObject)localObject).getValue() : null);
    }
    else
    {
      this.isRichText = false;
      this.valueComponent = null;
    }
    if (this.isRichText)
    {
      localObject = (StyledDocument)textPane.getDocument();
      ((RichTextValue)((RichTextBusinessObject)this.userObject).getValue()).insertInto((Document)localObject);
      if (localBorder != null) {
        textPane.setBorder(localBorder);
      } else {
        textPane.setBorder(BorderFactory.createEmptyBorder(INSET, INSET, INSET, INSET));
      }
      int j = getHorizontalAlignment();
      SimpleAttributeSet localSimpleAttributeSet = new SimpleAttributeSet();
      j = j == 4 ? 2 : j == 0 ? 1 : 0;
      StyleConstants.setAlignment(localSimpleAttributeSet, j);
      ((StyledDocument)localObject).setParagraphAttributes(0, ((StyledDocument)localObject).getLength(), localSimpleAttributeSet, true);
    }
  }
  
  public boolean inHitRegion(Point2D paramPoint2D)
  {
    if (this.showFoldingIcons) {
      return handle.contains(Math.max(0.0D, paramPoint2D.getX() - 1.0D), Math.max(0.0D, paramPoint2D.getY() - 1.0D));
    }
    return false;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/components/labels/MultiLineVertexRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */