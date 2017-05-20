package com.jgraph.io.svg;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.geom.Point2D;
import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.CellView;
import org.jgraph.graph.EdgeView;
import org.jgraph.graph.GraphConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class SVGEdgeWriter
{
  public static String DEFAULT_LINE_COLOR = SVGUtils.HEXCOLOR_BLACK;
  
  public Node createNode(SVGGraphWriter paramSVGGraphWriter, Document paramDocument, CellView paramCellView, double paramDouble1, double paramDouble2)
  {
    AttributeMap localAttributeMap = paramCellView.getAllAttributes();
    Element localElement1 = paramDocument.createElement("a");
    String str1 = GraphConstants.getLink(localAttributeMap);
    if (str1 != null) {
      localElement1.setAttribute("xlink:href", str1);
    }
    if ((paramCellView instanceof EdgeView))
    {
      Element localElement2 = paramDocument.createElement("path");
      EdgeView localEdgeView = (EdgeView)paramCellView;
      localElement2.setAttribute("fill", "none");
      Color localColor1 = GraphConstants.getLineColor(localAttributeMap);
      String str2 = null;
      if (localColor1 != null) {
        str2 = SVGUtils.getHexEncoding(localColor1);
      } else {
        str2 = DEFAULT_LINE_COLOR;
      }
      localElement2.setAttribute("stroke", str2);
      float f = GraphConstants.getLineWidth(localAttributeMap);
      localElement2.setAttribute("stroke-width", String.valueOf(f));
      float[] arrayOfFloat = GraphConstants.getDashPattern(localAttributeMap);
      if (arrayOfFloat != null)
      {
        localObject1 = "";
        for (int i = 0; i < arrayOfFloat.length; i++)
        {
          Float localFloat = new Float(arrayOfFloat[i]);
          localObject1 = (String)localObject1 + localFloat.toString();
          if (i != arrayOfFloat.length - 1) {
            localObject1 = (String)localObject1 + ", ";
          }
        }
        localElement2.setAttribute("stroke-dasharray", (String)localObject1);
      }
      Object localObject1 = localEdgeView.getPoint(0);
      String str3 = "M " + (((Point2D)localObject1).getX() - paramDouble1) + " " + (((Point2D)localObject1).getY() - paramDouble2);
      for (int j = 1; j < localEdgeView.getPointCount(); j++)
      {
        localObject1 = localEdgeView.getPoint(j);
        str3 = str3 + " L " + (((Point2D)localObject1).getX() - paramDouble1) + " " + (((Point2D)localObject1).getY() - paramDouble2);
      }
      localElement2.setAttribute("d", str3);
      j = GraphConstants.getLineBegin(localAttributeMap);
      int k = GraphConstants.getLineEnd(localAttributeMap);
      String str4 = new String("");
      str4 = str4 + "marker-start: url(#endMarker);";
      str4 = str4 + "marker-end: url(#startMarker);";
      str4 = str4 + " stroke: " + str2 + ";";
      localElement2.setAttribute("style", str4);
      Point localPoint = null;
      int m = localEdgeView.getPointCount() / 2;
      Point2D localPoint2D;
      if (localEdgeView.isLoop())
      {
        localObject2 = localEdgeView.getPoint(0);
        localPoint2D = localEdgeView.getLabelVector();
        localPoint = new Point((int)(((Point2D)localObject2).getX() + localPoint2D.getX() - paramDouble1), (int)(((Point2D)localObject2).getY() + localPoint2D.getY() - paramDouble2));
      }
      else if (localEdgeView.getPointCount() % 2 == 1)
      {
        localObject2 = localEdgeView.getPoint(m);
        localPoint = new Point((int)(((Point2D)localObject2).getX() - paramDouble1), (int)(((Point2D)localObject2).getY() - paramDouble2));
      }
      else
      {
        localObject2 = localEdgeView.getPoint(m - 1);
        localPoint2D = localEdgeView.getPoint(m);
        localPoint = new Point((int)(((Point2D)localObject2).getX() + (localPoint2D.getX() - ((Point2D)localObject2).getX()) / 2.0D - paramDouble1), (int)(((Point2D)localObject2).getY() + (localPoint2D.getY() - ((Point2D)localObject2).getY()) / 2.0D - paramDouble2));
      }
      localElement1.appendChild(localElement2);
      Object localObject2 = paramSVGGraphWriter.getLabels(localEdgeView);
      int n = 0;
      for (int i1 = 0; i1 < localObject2.length; i1++)
      {
        String str5 = String.valueOf(localObject2[i1]);
        Font localFont = GraphConstants.getFont(localAttributeMap);
        Color localColor2 = GraphConstants.getForeground(localAttributeMap);
        String str6 = null;
        if (localColor2 != null) {
          str6 = SVGUtils.getHexEncoding(localColor2);
        }
        int i2 = localPoint.y + n;
        localElement1.appendChild(paramSVGGraphWriter.createTextNode(paramDocument, str5, "middle", localFont, str6, localPoint.x, i2));
        n += (localFont != null ? localFont.getSize() : 11) + SVGUtils.LINESPACING;
      }
    }
    return localElement1;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/io/svg/SVGEdgeWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */