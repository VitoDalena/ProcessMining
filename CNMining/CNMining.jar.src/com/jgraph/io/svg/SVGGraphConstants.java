package com.jgraph.io.svg;

import java.util.Map;

public class SVGGraphConstants
{
  public static final int NO_SHAPE_SPECIFIED = -1;
  public static final int SHAPE_RECTANGLE = 0;
  public static final int SHAPE_ELLIPSE = 1;
  public static final int SHAPE_ROUNDRECT = 2;
  public static final int SHAPE_CYLINDER = 3;
  public static final int SHAPE_DIAMOND = 4;
  protected static int defaultShape = 0;
  public static final String VERTEXSHAPE = "vertexShape";
  public static final String VERTEXSHADOW = "vertexShadow";
  
  public static void setShape(Map paramMap, int paramInt)
  {
    Integer localInteger = new Integer(paramInt);
    paramMap.put("vertexShape", localInteger);
  }
  
  public static int getShape(Map paramMap)
  {
    Integer localInteger = (Integer)paramMap.get("vertexShape");
    if (localInteger != null) {
      return localInteger.intValue();
    }
    return -1;
  }
  
  public static void setShadow(Map paramMap, boolean paramBoolean)
  {
    paramMap.put("vertexShadow", new Boolean(paramBoolean));
  }
  
  public static boolean isShadow(Map paramMap)
  {
    Boolean localBoolean = (Boolean)paramMap.get("vertexShadow");
    if (localBoolean != null) {
      return localBoolean.booleanValue();
    }
    return false;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/io/svg/SVGGraphConstants.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */