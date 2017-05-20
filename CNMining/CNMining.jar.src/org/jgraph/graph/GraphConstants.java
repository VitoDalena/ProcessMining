package org.jgraph.graph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Stroke;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.swing.Icon;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.Border;

public class GraphConstants
{
  public static Font DEFAULTFONT = null;
  public static int DEFAULTDECORATIONSIZE = 10;
  public static int DEFAULTINSET = 0;
  public static final int PERMILLE = 1000;
  protected static float[] dash = { 5.0F, 5.0F };
  public static Stroke SELECTION_STROKE = new BasicStroke(1.0F, 0, 0, 10.0F, dash, 0.0F);
  public static final int ARROW_NONE = 0;
  public static final int ARROW_CLASSIC = 1;
  public static final int ARROW_TECHNICAL = 2;
  public static final int ARROW_SIMPLE = 4;
  public static final int ARROW_CIRCLE = 5;
  public static final int ARROW_LINE = 7;
  public static final int ARROW_DOUBLELINE = 8;
  public static final int ARROW_DIAMOND = 9;
  public static final int STYLE_ORTHOGONAL = 11;
  public static final int STYLE_BEZIER = 12;
  public static final int STYLE_SPLINE = 13;
  public static final int X_AXIS = 1;
  public static final int Y_AXIS = 2;
  public static Edge.Routing ROUTING_DEFAULT = new DefaultEdge.LoopRouting();
  public static final Edge.Routing ROUTING_SIMPLE = new DefaultEdge.DefaultRouting();
  public static final String SIZEABLEAXIS = "sizeableAxis";
  public static final String MOVEABLEAXIS = "moveableAxis";
  public static final String REPLACEATTRIBUTES = "replaceAttributes";
  public static final String REMOVEATTRIBUTES = "removeAttributes";
  public static final String REMOVEALL = "removeAll";
  public static final String ICON = "icon";
  public static final String FONT = "font";
  public static final String OPAQUE = "opaque";
  public static final String GROUPOPAQUE = "groupOpaque";
  public static final String BORDER = "border";
  public static final String LINECOLOR = "linecolor";
  public static final String BORDERCOLOR = "bordercolor";
  public static final String LINEWIDTH = "linewidth";
  public static final String FOREGROUND = "foregroundColor";
  public static final String BACKGROUND = "backgroundColor";
  public static final String GRADIENTCOLOR = "gradientColor";
  public static final String VERTICAL_ALIGNMENT = "verticalAlignment";
  public static final String HORIZONTAL_ALIGNMENT = "horizontalAlignment";
  public static final String VERTICAL_TEXT_POSITION = "verticalTextPosition";
  public static final String ORIENTATION = "orientation";
  public static final String HORIZONTAL_TEXT_POSITION = "horizontalTextPosition";
  public static final String DASHPATTERN = "dashPattern";
  public static final String DASHOFFSET = "dashOffset";
  public static final String LINESTYLE = "lineStyle";
  public static final String LINEBEGIN = "lineBegin";
  public static final String LINEEND = "lineEnd";
  public static final String BEGINSIZE = "beginSize";
  public static final String ENDSIZE = "endSize";
  public static final String BEGINFILL = "beginFill";
  public static final String ENDFILL = "endFill";
  public static final String VALUE = "value";
  public static final String EDITABLE = "editable";
  public static final String MOVEABLE = "moveable";
  public static final String SIZEABLE = "sizeable";
  public static final String AUTOSIZE = "autosize";
  public static final String RESIZE = "resize";
  public static final String INSET = "inset";
  public static final String CONSTRAINED = "constrained";
  public static final String SELECTABLE = "selectable";
  public static final String CHILDRENSELECTABLE = "childrenSelectable";
  public static final String MOVEHIDDENCHILDREN = "childrenSelectable";
  public static final String BENDABLE = "bendable";
  public static final String CONNECTABLE = "connectable";
  public static final String DISCONNECTABLE = "disconnectable";
  public static final String BOUNDS = "bounds";
  public static final String POINTS = "points";
  public static final String ROUTING = "routing";
  public static final String LABELPOSITION = "labelposition";
  public static final String EXTRALABELS = "extraLabels";
  public static final String EXTRALABELPOSITIONS = "extraLabelPositions";
  public static final String LABELALONGEDGE = "labelAlongEdge";
  public static final String ABSOLUTEX = "absoluteX";
  public static final String ABSOLUTEY = "absoluteY";
  public static final String OFFSET = "offset";
  public static final String SIZE = "size";
  public static final String LINK = "link";
  public static final String LABELENABLED = "labelEnabled";
  public static final String EXACTSEGMENTRELATIVE = "labelEnabled";
  
  public static Map createAttributes(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    return createAttributes(new Object[] { paramObject1 }, new Object[] { paramObject2 }, new Object[] { paramObject3 });
  }
  
  public static Map createAttributes(Object[] paramArrayOfObject, Object paramObject1, Object paramObject2)
  {
    return createAttributes(paramArrayOfObject, new Object[] { paramObject1 }, new Object[] { paramObject2 });
  }
  
  public static Map createAttributes(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, Object[] paramArrayOfObject3)
  {
    if ((paramArrayOfObject2 != null) && (paramArrayOfObject3 != null) && (paramArrayOfObject2.length != paramArrayOfObject3.length)) {
      throw new IllegalArgumentException("Keys and values must have same length");
    }
    Hashtable localHashtable1 = new Hashtable();
    for (int i = 0; i < paramArrayOfObject1.length; i++) {
      if (paramArrayOfObject1[i] != null)
      {
        Hashtable localHashtable2 = new Hashtable();
        for (int j = 0; j < paramArrayOfObject2.length; j++) {
          if ((paramArrayOfObject2[j] != null) && (paramArrayOfObject3[j] != null)) {
            localHashtable2.put(paramArrayOfObject2[j], paramArrayOfObject3[j]);
          }
        }
        localHashtable1.put(paramArrayOfObject1[i], localHashtable2);
      }
    }
    return localHashtable1;
  }
  
  public static Map createAttributes(Object[] paramArrayOfObject, CellMapper paramCellMapper)
  {
    Hashtable localHashtable = new Hashtable();
    for (int i = 0; i < paramArrayOfObject.length; i++)
    {
      CellView localCellView = null;
      Object localObject = paramArrayOfObject[i];
      if ((localObject instanceof CellView))
      {
        localCellView = (CellView)localObject;
        localObject = localCellView.getCell();
      }
      else if (paramCellMapper != null)
      {
        localCellView = paramCellMapper.getMapping(localObject, false);
      }
      if (localCellView != null) {
        localHashtable.put(localObject, localCellView.getAllAttributes().clone());
      }
    }
    return localHashtable;
  }
  
  public static Map createAttributesFromModel(Object[] paramArrayOfObject, GraphModel paramGraphModel)
  {
    Hashtable localHashtable = new Hashtable();
    for (int i = 0; i < paramArrayOfObject.length; i++)
    {
      AttributeMap localAttributeMap = paramGraphModel.getAttributes(paramArrayOfObject[i]);
      if ((localAttributeMap != null) && (localAttributeMap.size() > 0)) {
        localHashtable.put(paramArrayOfObject[i], localAttributeMap.clone());
      }
    }
    return localHashtable;
  }
  
  public static Map replaceKeys(Map paramMap1, Map paramMap2)
  {
    Hashtable localHashtable = new Hashtable();
    Iterator localIterator = paramMap2.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if ((localEntry.getValue() instanceof Map))
      {
        Object localObject = paramMap1.get(localEntry.getKey());
        if (localObject != null)
        {
          AttributeMap localAttributeMap = (AttributeMap)((AttributeMap)localEntry.getValue()).clone();
          localHashtable.put(localObject, localAttributeMap);
        }
      }
    }
    return localHashtable;
  }
  
  public static Map merge(Map paramMap1, Map paramMap2)
  {
    if ((paramMap1 != null) && (paramMap2 != null))
    {
      paramMap1 = new Hashtable(paramMap1);
      Iterator localIterator = paramMap2.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        Map localMap1 = (Map)localEntry.getValue();
        Map localMap2 = (Map)paramMap1.remove(localEntry.getKey());
        if (localMap2 != null) {
          localMap1.putAll(localMap2);
        }
      }
      paramMap2.putAll(paramMap1);
    }
    return paramMap2;
  }
  
  public static void setFont(Map paramMap, Font paramFont)
  {
    paramMap.put("font", paramFont);
  }
  
  public static Font getFont(Map paramMap)
  {
    Font localFont = (Font)paramMap.get("font");
    if (localFont == null) {
      localFont = DEFAULTFONT;
    }
    return localFont;
  }
  
  public static final void setRemoveAttributes(Map paramMap, Object[] paramArrayOfObject)
  {
    paramMap.put("removeAttributes", paramArrayOfObject);
  }
  
  public static final Object[] getRemoveAttributes(Map paramMap)
  {
    return (Object[])paramMap.get("removeAttributes");
  }
  
  public static final void setMoveableAxis(Map paramMap, int paramInt)
  {
    paramMap.put("moveableAxis", new Integer(paramInt));
  }
  
  public static final int getMoveableAxis(Map paramMap)
  {
    Integer localInteger = (Integer)paramMap.get("moveableAxis");
    if (localInteger != null) {
      return localInteger.intValue();
    }
    return 0;
  }
  
  public static final void setSizeableAxis(Map paramMap, int paramInt)
  {
    paramMap.put("sizeableAxis", new Integer(paramInt));
  }
  
  public static final int getSizeableAxis(Map paramMap)
  {
    Integer localInteger = (Integer)paramMap.get("sizeableAxis");
    if (localInteger != null) {
      return localInteger.intValue();
    }
    return 0;
  }
  
  public static final void setIcon(Map paramMap, Icon paramIcon)
  {
    paramMap.put("icon", paramIcon);
  }
  
  public static final Icon getIcon(Map paramMap)
  {
    return (Icon)paramMap.get("icon");
  }
  
  public static final void setOpaque(Map paramMap, boolean paramBoolean)
  {
    paramMap.put("opaque", new Boolean(paramBoolean));
  }
  
  public static final boolean isOpaque(Map paramMap)
  {
    Boolean localBoolean = (Boolean)paramMap.get("opaque");
    if (localBoolean != null) {
      return localBoolean.booleanValue();
    }
    return false;
  }
  
  public static final void setGroupOpaque(Map paramMap, boolean paramBoolean)
  {
    paramMap.put("groupOpaque", new Boolean(paramBoolean));
  }
  
  public static final boolean isGroupOpaque(Map paramMap)
  {
    Boolean localBoolean = (Boolean)paramMap.get("groupOpaque");
    if (localBoolean != null) {
      return localBoolean.booleanValue();
    }
    return false;
  }
  
  public static final void setBorder(Map paramMap, Border paramBorder)
  {
    paramMap.put("border", paramBorder);
  }
  
  public static final Border getBorder(Map paramMap)
  {
    return (Border)paramMap.get("border");
  }
  
  public static final void setLineColor(Map paramMap, Color paramColor)
  {
    paramMap.put("linecolor", paramColor);
  }
  
  public static final Color getLineColor(Map paramMap)
  {
    return (Color)paramMap.get("linecolor");
  }
  
  public static final void setBorderColor(Map paramMap, Color paramColor)
  {
    paramMap.put("bordercolor", paramColor);
  }
  
  public static final Color getBorderColor(Map paramMap)
  {
    return (Color)paramMap.get("bordercolor");
  }
  
  public static final void setLineWidth(Map paramMap, float paramFloat)
  {
    paramMap.put("linewidth", new Float(paramFloat));
  }
  
  public static final float getLineWidth(Map paramMap)
  {
    Float localFloat = (Float)paramMap.get("linewidth");
    if (localFloat != null) {
      return localFloat.floatValue();
    }
    return 1.0F;
  }
  
  public static final void setForeground(Map paramMap, Color paramColor)
  {
    paramMap.put("foregroundColor", paramColor);
  }
  
  public static final Color getForeground(Map paramMap)
  {
    return (Color)paramMap.get("foregroundColor");
  }
  
  public static final void setBackground(Map paramMap, Color paramColor)
  {
    paramMap.put("backgroundColor", paramColor);
  }
  
  public static final Color getBackground(Map paramMap)
  {
    return (Color)paramMap.get("backgroundColor");
  }
  
  public static final void setGradientColor(Map paramMap, Color paramColor)
  {
    paramMap.put("gradientColor", paramColor);
  }
  
  public static final Color getGradientColor(Map paramMap)
  {
    return (Color)paramMap.get("gradientColor");
  }
  
  public static final void setVerticalAlignment(Map paramMap, int paramInt)
  {
    paramMap.put("verticalAlignment", new Integer(paramInt));
  }
  
  public static final int getVerticalAlignment(Map paramMap)
  {
    Integer localInteger = (Integer)paramMap.get("verticalAlignment");
    if (localInteger != null) {
      return localInteger.intValue();
    }
    return 0;
  }
  
  public static final void setHorizontalAlignment(Map paramMap, int paramInt)
  {
    paramMap.put("horizontalAlignment", new Integer(paramInt));
  }
  
  public static final int getHorizontalAlignment(Map paramMap)
  {
    Integer localInteger = (Integer)paramMap.get("horizontalAlignment");
    if (localInteger != null) {
      return localInteger.intValue();
    }
    return 0;
  }
  
  public static final void setVerticalTextPosition(Map paramMap, int paramInt)
  {
    paramMap.put("verticalTextPosition", new Integer(paramInt));
  }
  
  public static final int getVerticalTextPosition(Map paramMap)
  {
    Integer localInteger = (Integer)paramMap.get("verticalTextPosition");
    if (localInteger != null) {
      return localInteger.intValue();
    }
    return 3;
  }
  
  public static final void setOrientation(Map paramMap, int paramInt)
  {
    paramMap.put("orientation", new Integer(paramInt));
  }
  
  public static final int getOrientation(Map paramMap)
  {
    Integer localInteger = (Integer)paramMap.get("orientation");
    if (localInteger != null) {
      return localInteger.intValue();
    }
    return 1;
  }
  
  public static final void setHorizontalTextPosition(Map paramMap, int paramInt)
  {
    paramMap.put("horizontalTextPosition", new Integer(paramInt));
  }
  
  public static final int getHorizontalTextPosition(Map paramMap)
  {
    Integer localInteger = (Integer)paramMap.get("horizontalTextPosition");
    if (localInteger != null) {
      return localInteger.intValue();
    }
    return 0;
  }
  
  public static final void setDashPattern(Map paramMap, float[] paramArrayOfFloat)
  {
    paramMap.put("dashPattern", paramArrayOfFloat);
  }
  
  public static final float[] getDashPattern(Map paramMap)
  {
    return (float[])paramMap.get("dashPattern");
  }
  
  public static final void setDashOffset(Map paramMap, float paramFloat)
  {
    paramMap.put("dashOffset", new Float(paramFloat));
  }
  
  public static final float getDashOffset(Map paramMap)
  {
    Float localFloat = (Float)paramMap.get("dashOffset");
    if (localFloat != null) {
      return localFloat.floatValue();
    }
    return 1.0F;
  }
  
  public static final void setLineStyle(Map paramMap, int paramInt)
  {
    paramMap.put("lineStyle", new Integer(paramInt));
  }
  
  public static final int getLineStyle(Map paramMap)
  {
    Integer localInteger = (Integer)paramMap.get("lineStyle");
    if (localInteger != null) {
      return localInteger.intValue();
    }
    return 11;
  }
  
  public static final void setBeginSize(Map paramMap, int paramInt)
  {
    paramMap.put("beginSize", new Integer(paramInt));
  }
  
  public static final int getBeginSize(Map paramMap)
  {
    Integer localInteger = (Integer)paramMap.get("beginSize");
    if (localInteger != null) {
      return localInteger.intValue();
    }
    return DEFAULTDECORATIONSIZE;
  }
  
  public static final void setEndSize(Map paramMap, int paramInt)
  {
    paramMap.put("endSize", new Integer(paramInt));
  }
  
  public static final int getEndSize(Map paramMap)
  {
    Integer localInteger = (Integer)paramMap.get("endSize");
    if (localInteger != null) {
      return localInteger.intValue();
    }
    return DEFAULTDECORATIONSIZE;
  }
  
  public static final void setLineBegin(Map paramMap, int paramInt)
  {
    paramMap.put("lineBegin", new Integer(paramInt));
  }
  
  public static final int getLineBegin(Map paramMap)
  {
    Integer localInteger = (Integer)paramMap.get("lineBegin");
    if (localInteger != null) {
      return localInteger.intValue();
    }
    return 0;
  }
  
  public static final void setLineEnd(Map paramMap, int paramInt)
  {
    paramMap.put("lineEnd", new Integer(paramInt));
  }
  
  public static final int getLineEnd(Map paramMap)
  {
    Integer localInteger = (Integer)paramMap.get("lineEnd");
    if (localInteger != null) {
      return localInteger.intValue();
    }
    return 0;
  }
  
  public static final void setValue(Map paramMap, Object paramObject)
  {
    paramMap.put("value", paramObject);
  }
  
  public static final Object getValue(Map paramMap)
  {
    return paramMap.get("value");
  }
  
  public static final void setLabelPosition(Map paramMap, Point2D paramPoint2D)
  {
    paramMap.put("labelposition", paramPoint2D);
  }
  
  public static final Point2D getLabelPosition(Map paramMap)
  {
    return (Point2D)paramMap.get("labelposition");
  }
  
  public static final void setExtraLabels(Map paramMap, Object[] paramArrayOfObject)
  {
    paramMap.put("extraLabels", paramArrayOfObject);
  }
  
  public static final Object[] getExtraLabels(Map paramMap)
  {
    return (Object[])paramMap.get("extraLabels");
  }
  
  public static final void setExtraLabelPositions(Map paramMap, Point2D[] paramArrayOfPoint2D)
  {
    paramMap.put("extraLabelPositions", paramArrayOfPoint2D);
  }
  
  public static final Point2D[] getExtraLabelPositions(Map paramMap)
  {
    return (Point2D[])paramMap.get("extraLabelPositions");
  }
  
  public static final void setLabelAlongEdge(Map paramMap, boolean paramBoolean)
  {
    paramMap.put("labelAlongEdge", new Boolean(paramBoolean));
  }
  
  public static final boolean isLabelAlongEdge(Map paramMap)
  {
    Boolean localBoolean = (Boolean)paramMap.get("labelAlongEdge");
    if (localBoolean != null) {
      return localBoolean.booleanValue();
    }
    return false;
  }
  
  public static final void setEditable(Map paramMap, boolean paramBoolean)
  {
    paramMap.put("editable", new Boolean(paramBoolean));
  }
  
  public static final boolean isEditable(Map paramMap)
  {
    Boolean localBoolean = (Boolean)paramMap.get("editable");
    if (localBoolean != null) {
      return localBoolean.booleanValue();
    }
    return true;
  }
  
  public static final void setMoveable(Map paramMap, boolean paramBoolean)
  {
    paramMap.put("moveable", new Boolean(paramBoolean));
  }
  
  public static final boolean isMoveable(Map paramMap)
  {
    Boolean localBoolean = (Boolean)paramMap.get("moveable");
    if (localBoolean != null) {
      return localBoolean.booleanValue();
    }
    return true;
  }
  
  public static final void setSizeable(Map paramMap, boolean paramBoolean)
  {
    paramMap.put("sizeable", new Boolean(paramBoolean));
  }
  
  public static final boolean isSizeable(Map paramMap)
  {
    Boolean localBoolean = (Boolean)paramMap.get("sizeable");
    if (localBoolean != null) {
      return localBoolean.booleanValue();
    }
    return true;
  }
  
  public static final void setAutoSize(Map paramMap, boolean paramBoolean)
  {
    paramMap.put("autosize", new Boolean(paramBoolean));
  }
  
  public static final boolean isAutoSize(Map paramMap)
  {
    Boolean localBoolean = (Boolean)paramMap.get("autosize");
    if (localBoolean != null) {
      return localBoolean.booleanValue();
    }
    return false;
  }
  
  public static final void setResize(Map paramMap, boolean paramBoolean)
  {
    paramMap.put("resize", new Boolean(paramBoolean));
  }
  
  public static final boolean isResize(Map paramMap)
  {
    Boolean localBoolean = (Boolean)paramMap.get("resize");
    if (localBoolean != null) {
      return localBoolean.booleanValue();
    }
    return false;
  }
  
  public static final void setConstrained(Map paramMap, boolean paramBoolean)
  {
    paramMap.put("constrained", new Boolean(paramBoolean));
  }
  
  public static final boolean isConstrained(Map paramMap)
  {
    Boolean localBoolean = (Boolean)paramMap.get("constrained");
    if (localBoolean != null) {
      return localBoolean.booleanValue();
    }
    return false;
  }
  
  public static final void setSelectable(Map paramMap, boolean paramBoolean)
  {
    paramMap.put("selectable", new Boolean(paramBoolean));
  }
  
  public static final boolean isSelectable(Map paramMap)
  {
    Boolean localBoolean = (Boolean)paramMap.get("selectable");
    if (localBoolean != null) {
      return localBoolean.booleanValue();
    }
    return true;
  }
  
  public static final void setChildrenSelectable(Map paramMap, boolean paramBoolean)
  {
    paramMap.put("childrenSelectable", new Boolean(paramBoolean));
  }
  
  public static final boolean isChildrenSelectable(Map paramMap)
  {
    Boolean localBoolean = (Boolean)paramMap.get("childrenSelectable");
    if (localBoolean != null) {
      return localBoolean.booleanValue();
    }
    return true;
  }
  
  public static final void setBendable(Map paramMap, boolean paramBoolean)
  {
    paramMap.put("bendable", new Boolean(paramBoolean));
  }
  
  public static final boolean isBendable(Map paramMap)
  {
    Boolean localBoolean = (Boolean)paramMap.get("bendable");
    if (localBoolean != null) {
      return localBoolean.booleanValue();
    }
    return true;
  }
  
  public static final void setConnectable(Map paramMap, boolean paramBoolean)
  {
    paramMap.put("connectable", new Boolean(paramBoolean));
  }
  
  public static final boolean isConnectable(Map paramMap)
  {
    Boolean localBoolean = (Boolean)paramMap.get("connectable");
    if (localBoolean != null) {
      return localBoolean.booleanValue();
    }
    return true;
  }
  
  public static final void setDisconnectable(Map paramMap, boolean paramBoolean)
  {
    paramMap.put("disconnectable", new Boolean(paramBoolean));
  }
  
  public static final boolean isDisconnectable(Map paramMap)
  {
    Boolean localBoolean = (Boolean)paramMap.get("disconnectable");
    if (localBoolean != null) {
      return localBoolean.booleanValue();
    }
    return true;
  }
  
  public static final void setPoints(Map paramMap, List paramList)
  {
    paramMap.put("points", paramList);
  }
  
  public static final List getPoints(Map paramMap)
  {
    return (List)paramMap.get("points");
  }
  
  public static final void setRouting(Map paramMap, Edge.Routing paramRouting)
  {
    paramMap.put("routing", paramRouting);
  }
  
  public static final Edge.Routing getRouting(Map paramMap)
  {
    Edge.Routing localRouting = (Edge.Routing)paramMap.get("routing");
    if (localRouting == null) {
      localRouting = ROUTING_DEFAULT;
    }
    return localRouting;
  }
  
  public static final void setBounds(Map paramMap, Rectangle2D paramRectangle2D)
  {
    paramMap.put("bounds", paramRectangle2D);
  }
  
  public static final Rectangle2D getBounds(Map paramMap)
  {
    return (Rectangle2D)paramMap.get("bounds");
  }
  
  public static final void setInset(Map paramMap, int paramInt)
  {
    paramMap.put("inset", new Integer(paramInt));
  }
  
  public static final int getInset(Map paramMap)
  {
    Integer localInteger = (Integer)paramMap.get("inset");
    if (localInteger != null) {
      return localInteger.intValue();
    }
    return DEFAULTINSET;
  }
  
  public static final void setSize(Map paramMap, Dimension paramDimension)
  {
    paramMap.put("size", paramDimension);
  }
  
  public static final Dimension getSize(Map paramMap)
  {
    return (Dimension)paramMap.get("size");
  }
  
  public static final void setOffset(Map paramMap, Point2D paramPoint2D)
  {
    paramMap.put("offset", paramPoint2D);
  }
  
  public static final Point2D getOffset(Map paramMap)
  {
    return (Point2D)paramMap.get("offset");
  }
  
  public static final void setBeginFill(Map paramMap, boolean paramBoolean)
  {
    paramMap.put("beginFill", new Boolean(paramBoolean));
  }
  
  public static final boolean isBeginFill(Map paramMap)
  {
    Boolean localBoolean = (Boolean)paramMap.get("beginFill");
    if (localBoolean != null) {
      return localBoolean.booleanValue();
    }
    return false;
  }
  
  public static final void setEndFill(Map paramMap, boolean paramBoolean)
  {
    paramMap.put("endFill", new Boolean(paramBoolean));
  }
  
  public static final boolean isEndFill(Map paramMap)
  {
    Boolean localBoolean = (Boolean)paramMap.get("endFill");
    if (localBoolean != null) {
      return localBoolean.booleanValue();
    }
    return false;
  }
  
  public static final void setAbsolute(Map paramMap, boolean paramBoolean)
  {
    setAbsoluteX(paramMap, paramBoolean);
    setAbsoluteY(paramMap, paramBoolean);
  }
  
  public static final void setAbsoluteY(Map paramMap, boolean paramBoolean)
  {
    paramMap.put("absoluteY", new Boolean(paramBoolean));
  }
  
  public static final boolean isAbsoluteY(Map paramMap)
  {
    Boolean localBoolean = (Boolean)paramMap.get("absoluteY");
    if (localBoolean != null) {
      return localBoolean.booleanValue();
    }
    return false;
  }
  
  public static final void setAbsoluteX(Map paramMap, boolean paramBoolean)
  {
    paramMap.put("absoluteX", new Boolean(paramBoolean));
  }
  
  public static final boolean isAbsoluteX(Map paramMap)
  {
    Boolean localBoolean = (Boolean)paramMap.get("absoluteX");
    if (localBoolean != null) {
      return localBoolean.booleanValue();
    }
    return false;
  }
  
  public static final void setRemoveAll(Map paramMap, boolean paramBoolean)
  {
    paramMap.put("removeAll", new Boolean(paramBoolean));
  }
  
  public static final boolean isRemoveAll(Map paramMap)
  {
    Boolean localBoolean = (Boolean)paramMap.get("removeAll");
    if (localBoolean != null) {
      return localBoolean.booleanValue();
    }
    return false;
  }
  
  public static Edge.Routing getROUTING_SIMPLE()
  {
    return ROUTING_SIMPLE;
  }
  
  public static Edge.Routing getROUTING_DEFAULT()
  {
    return ROUTING_DEFAULT;
  }
  
  public static void setLink(Map paramMap, String paramString)
  {
    paramMap.put("link", paramString);
  }
  
  public static String getLink(Map paramMap)
  {
    String str = (String)paramMap.get("link");
    return str;
  }
  
  public static void setLabelEnabled(Map paramMap, boolean paramBoolean)
  {
    paramMap.put("labelEnabled", new Boolean(paramBoolean));
  }
  
  public static boolean isLabelEnabled(Map paramMap)
  {
    Boolean localBoolean = (Boolean)paramMap.get("labelEnabled");
    if (localBoolean != null) {
      return localBoolean.booleanValue();
    }
    return true;
  }
  
  public static void setExactSegmentLabel(Map paramMap, boolean paramBoolean)
  {
    paramMap.put("labelEnabled", new Boolean(paramBoolean));
  }
  
  public static boolean isExactSegmentLabel(Map paramMap)
  {
    Boolean localBoolean = (Boolean)paramMap.get("labelEnabled");
    if (localBoolean != null) {
      return localBoolean.booleanValue();
    }
    return false;
  }
  
  static
  {
    try
    {
      DEFAULTFONT = UIManager.getDefaults().getFont("Label.font");
    }
    catch (InternalError localInternalError) {}
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/graph/GraphConstants.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */