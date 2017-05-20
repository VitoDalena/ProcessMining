package com.jgraph.components.labels;

import java.util.Map;
import org.jgraph.graph.GraphConstants;

public class CellConstants
  extends GraphConstants
{
  public static final String STRETCHIMAGE = "stretchImage";
  public static final String VERTEXSHAPE = "vertexShape";
  
  public static final boolean isStretchImage(Map paramMap)
  {
    Boolean localBoolean = (Boolean)paramMap.get("stretchImage");
    if (localBoolean != null) {
      return localBoolean.booleanValue();
    }
    return false;
  }
  
  public static final void setStretchImage(Map paramMap, boolean paramBoolean)
  {
    paramMap.put("stretchImage", new Boolean(paramBoolean));
  }
  
  public static final void setVertexShape(Map paramMap, int paramInt)
  {
    paramMap.put("vertexShape", new Integer(paramInt));
  }
  
  public static final int getVertexShape(Map paramMap)
  {
    Integer localInteger = (Integer)paramMap.get("vertexShape");
    if (localInteger != null) {
      return localInteger.intValue();
    }
    return 0;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/components/labels/CellConstants.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */