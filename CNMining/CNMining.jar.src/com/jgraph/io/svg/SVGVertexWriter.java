package com.jgraph.io.svg;

import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Rectangle2D;
import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.CellView;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphLayoutCache;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class SVGVertexWriter
{
  public static String HEXCOLOR_SHADOW = SVGUtils.getHexEncoding(Color.DARK_GRAY);
  public static double SHADOW_OPACITY = 0.5D;
  public static int SHADOW_DISTANCE = 3;
  
  public Node createNode(SVGGraphWriter paramSVGGraphWriter, Document paramDocument, GraphLayoutCache paramGraphLayoutCache, CellView paramCellView, double paramDouble1, double paramDouble2)
  {
    Rectangle2D localRectangle2D = paramCellView.getBounds();
    AttributeMap localAttributeMap = paramCellView.getAllAttributes();
    Element localElement = paramDocument.createElement("a");
    String str1 = GraphConstants.getLink(localAttributeMap);
    if (str1 != null) {
      localElement.setAttribute("xlink:href", str1);
    }
    int i = SVGGraphConstants.getShape(localAttributeMap);
    Color localColor1 = GraphConstants.getBackground(localAttributeMap);
    String str2 = null;
    if (localColor1 != null) {
      str2 = SVGUtils.getHexEncoding(localColor1);
    }
    Color localColor2 = GraphConstants.getGradientColor(localAttributeMap);
    String str3 = null;
    if (localColor2 != null) {
      str2 = SVGUtils.getHexEncoding(localColor2);
    }
    Color localColor3 = GraphConstants.getBorderColor(localAttributeMap);
    String str4 = null;
    if (localColor3 != null) {
      str4 = SVGUtils.getHexEncoding(localColor3);
    }
    float f = GraphConstants.getLineWidth(localAttributeMap);
    boolean bool = SVGGraphConstants.isShadow(localAttributeMap);
    if (bool)
    {
      int j = SHADOW_DISTANCE;
      localElement.appendChild(paramSVGGraphWriter.createShapeNode(paramDocument, i, localRectangle2D, paramDouble1 - j, paramDouble2 - j, HEXCOLOR_SHADOW, null, "none", f, SHADOW_OPACITY, false));
    }
    localElement.appendChild(paramSVGGraphWriter.createShapeNode(paramDocument, i, localRectangle2D, paramDouble1, paramDouble2, str2, str3, str4, f, 1.0D, false));
    Object[] arrayOfObject = paramSVGGraphWriter.getLabels(paramCellView);
    int k = (int)(localRectangle2D.getX() - paramDouble1 + localRectangle2D.getWidth() / 2.0D);
    Font localFont = GraphConstants.getFont(localAttributeMap);
    int m = localFont != null ? localFont.getSize() : 11;
    int n = (m + SVGUtils.LINESPACING) * arrayOfObject.length;
    int i1 = (int)((localRectangle2D.getHeight() - n) / 2.0D) + m;
    for (int i2 = 0; i2 < arrayOfObject.length; i2++)
    {
      Color localColor4 = GraphConstants.getForeground(localAttributeMap);
      String str5 = null;
      if (localColor4 != null) {
        str5 = SVGUtils.getHexEncoding(localColor4);
      }
      Object localObject;
      if ((arrayOfObject[i2] instanceof Node))
      {
        localObject = paramDocument.importNode((Node)arrayOfObject[i2], true);
        Node localNode = paramSVGGraphWriter.createTextNode(paramDocument, "", "middle", localFont, str5, k, (int)(localRectangle2D.getY() + i1 - paramDouble2));
        localElement.appendChild(localNode);
        localNode.appendChild((Node)localObject);
      }
      else
      {
        localObject = String.valueOf(arrayOfObject[i2]);
        localElement.appendChild(paramSVGGraphWriter.createTextNode(paramDocument, (String)localObject, "middle", localFont, str5, k, (int)(localRectangle2D.getY() + i1 - paramDouble2)));
      }
      i1 += m + SVGUtils.LINESPACING;
    }
    return localElement;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/io/svg/SVGVertexWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */