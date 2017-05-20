package com.jgraph.io.svg;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.jgraph.graph.CellView;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.GraphModel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class SVGGraphWriter
{
  public static Font TITLE_FONT = new Font("Dialog", 0, 16);
  public static int TITLE_VSPACING = 10;
  public static double FONT_PROPORTION_FACTOR = 1.5D;
  public static String TITLE_HEXCOLOR = SVGUtils.getHexEncoding(Color.DARK_GRAY);
  protected SVGVertexWriter vertexFactory = new SVGVertexWriter();
  protected SVGEdgeWriter edgeFactory = new SVGEdgeWriter();
  protected Map gradients = new Hashtable();
  protected Rectangle viewBox = new Rectangle(0, 0, 0, 0);
  
  public void write(OutputStream paramOutputStream, String paramString, GraphLayoutCache paramGraphLayoutCache, int paramInt)
  {
    try
    {
      Document localDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
      localDocument.appendChild(createNode(localDocument, paramString, paramGraphLayoutCache, paramInt));
      TransformerFactory.newInstance().newTransformer().transform(new DOMSource(localDocument), new StreamResult(paramOutputStream));
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  protected Node createNode(Document paramDocument, String paramString, GraphLayoutCache paramGraphLayoutCache, int paramInt)
    throws ParserConfigurationException
  {
    Rectangle2D localRectangle2D = GraphLayoutCache.getBounds(paramGraphLayoutCache.getAllViews());
    double d1 = localRectangle2D.getX() - paramInt;
    double d2 = localRectangle2D.getY() - paramInt;
    int i = 0;
    int j = 0;
    Node localNode1 = null;
    if ((paramString != null) && (paramString.length() > 0))
    {
      Font localFont = TITLE_FONT;
      i = localFont.getSize() + TITLE_VSPACING;
      j = (int)(paramString.length() * localFont.getSize() / FONT_PROPORTION_FACTOR);
      localNode1 = createTextNode(paramDocument, paramString, null, localFont, TITLE_HEXCOLOR, paramInt, paramInt + i - TITLE_VSPACING);
    }
    double d3 = Math.max(j, localRectangle2D.getWidth() + 2 * paramInt);
    double d4 = localRectangle2D.getHeight() + 2 * paramInt + i;
    Node localNode2 = createRoot(paramDocument, d3, d4, paramInt);
    Element localElement1 = paramDocument.createElement("defs");
    Element localElement2 = paramDocument.createElement("g");
    localElement2.setAttribute("id", "arrowMarker");
    localElement1.appendChild(localElement2);
    Element localElement3 = paramDocument.createElement("g");
    localElement3.setAttribute("stroke-width", "0");
    localElement2.appendChild(localElement3);
    Element localElement4 = paramDocument.createElement("path");
    localElement4.setAttribute("d", "M 4 -2 L 0 0 L 4 2 L 3 1 L 3 -1 L 4 -2");
    localElement3.appendChild(localElement4);
    Element localElement5 = paramDocument.createElement("marker");
    localElement5.setAttribute("id", "startMarker");
    localElement5.setAttribute("markerWidth", "48");
    localElement5.setAttribute("markerHeight", "24");
    localElement5.setAttribute("viewBox", "-4 -4 25 5");
    localElement5.setAttribute("orient", "auto");
    localElement5.setAttribute("refX", "0");
    localElement5.setAttribute("refY", "0");
    localElement5.setAttribute("markerUnits", "strokeWidth");
    localElement1.appendChild(localElement5);
    localElement2 = paramDocument.createElement("g");
    localElement5.appendChild(localElement2);
    Element localElement6 = paramDocument.createElement("use");
    localElement6.setAttribute("xlink:href", "#arrowMarker");
    localElement6.setAttribute("transform", "rotate(180)");
    localElement6.setAttribute("stroke-width", "1");
    localElement2.appendChild(localElement6);
    localElement5 = paramDocument.createElement("marker");
    localElement5.setAttribute("id", "endMarker");
    localElement5.setAttribute("markerWidth", "48");
    localElement5.setAttribute("markerHeight", "24");
    localElement5.setAttribute("viewBox", "-4 -4 25 5");
    localElement5.setAttribute("orient", "auto");
    localElement5.setAttribute("refX", "0");
    localElement5.setAttribute("refY", "0");
    localElement5.setAttribute("markerUnits", "strokeWidth");
    localElement1.appendChild(localElement5);
    localElement2 = paramDocument.createElement("g");
    localElement5.appendChild(localElement2);
    localElement6 = paramDocument.createElement("use");
    localElement6.setAttribute("xlink:href", "#arrowMarker");
    localElement6.setAttribute("stroke-width", "1");
    localElement2.appendChild(localElement6);
    localNode2.appendChild(localElement1);
    if (localNode1 != null)
    {
      localNode2.appendChild(localNode1);
      d2 -= i;
    }
    GraphModel localGraphModel = paramGraphLayoutCache.getModel();
    CellView[] arrayOfCellView = paramGraphLayoutCache.getAllViews();
    Object localObject;
    for (int k = 0; k < arrayOfCellView.length; k++)
    {
      localObject = arrayOfCellView[k].getCell();
      if (!localGraphModel.isPort(localObject))
      {
        Node localNode3 = localGraphModel.isEdge(localObject) ? this.edgeFactory.createNode(this, paramDocument, arrayOfCellView[k], d1, d2) : this.vertexFactory.createNode(this, paramDocument, paramGraphLayoutCache, arrayOfCellView[k], d1, d2);
        if (localNode3 != null) {
          localNode2.appendChild(localNode3);
        }
      }
    }
    Iterator localIterator = this.gradients.values().iterator();
    while (localIterator.hasNext())
    {
      localObject = localIterator.next();
      if ((localObject instanceof Node)) {
        localElement1.appendChild((Node)localObject);
      }
    }
    return localNode2;
  }
  
  protected Node createRoot(Document paramDocument, double paramDouble1, double paramDouble2, int paramInt)
  {
    Element localElement = paramDocument.createElement("svg");
    localElement.setAttribute("width", String.valueOf(paramDouble1));
    localElement.setAttribute("height", String.valueOf(paramDouble2));
    localElement.setAttribute("viewBox", "0 0 " + paramDouble1 + " " + paramDouble2);
    localElement.setAttribute("allowZoomAndPan", "true");
    localElement.setAttribute("version", "1.1");
    localElement.setAttribute("xmlns", "http://www.w3.org/2000/svg");
    localElement.setAttribute("xmlns:xlink", "http://www.w3.org/1999/xlink");
    this.viewBox.setFrame(paramInt, paramInt, paramDouble1, paramDouble2);
    return localElement;
  }
  
  public Node getGradient(Document paramDocument, String paramString1, String paramString2)
  {
    Object localObject = null;
    if ((paramString1 != null) || (paramString2 != null))
    {
      String str = "gradient" + (paramString1 != null ? paramString1.substring(1) : "none") + "_" + (paramString2 != null ? paramString2.substring(1) : "none");
      localObject = (Element)this.gradients.get(str);
      if (localObject == null)
      {
        localObject = createGradient(paramDocument, str, paramString1, paramString2);
        this.gradients.put(str, localObject);
      }
    }
    return (Node)localObject;
  }
  
  protected Node createGradient(Document paramDocument, String paramString1, String paramString2, String paramString3)
  {
    Element localElement1 = paramDocument.createElement("linearGradient");
    localElement1.setAttribute("id", paramString1);
    Element localElement2 = paramDocument.createElement("stop");
    localElement2.setAttribute("offset", "5%");
    localElement2.setAttribute("stop-color", paramString2);
    localElement2.setAttribute("stop-opacity", "1");
    localElement1.appendChild(localElement2);
    Element localElement3 = paramDocument.createElement("stop");
    localElement3.setAttribute("offset", "95%");
    localElement3.setAttribute("stop-color", paramString3);
    localElement3.setAttribute("stop-opacity", "1");
    localElement1.appendChild(localElement3);
    return localElement1;
  }
  
  public Node createShapeNode(Document paramDocument, int paramInt, Rectangle2D paramRectangle2D, double paramDouble1, double paramDouble2, String paramString1, String paramString2, String paramString3, float paramFloat, double paramDouble3, boolean paramBoolean)
  {
    int i = paramInt == 1 ? 1 : 0;
    Element localElement = paramDocument.createElement(i != 0 ? "ellipse" : "rect");
    double d1 = paramRectangle2D.getWidth();
    double d2 = paramRectangle2D.getHeight();
    if (i != 0)
    {
      localElement.setAttribute("cx", String.valueOf(paramRectangle2D.getX() + d1 / 2.0D - paramDouble1));
      localElement.setAttribute("cy", String.valueOf(paramRectangle2D.getY() + d2 / 2.0D - paramDouble2));
      localElement.setAttribute("rx", String.valueOf(d1 / 2.0D));
      localElement.setAttribute("ry", String.valueOf(d2 / 2.0D));
    }
    else
    {
      localElement.setAttribute("x", String.valueOf(paramRectangle2D.getX() - paramDouble1));
      localElement.setAttribute("y", String.valueOf(paramRectangle2D.getY() - paramDouble2));
      if (paramInt == 2)
      {
        localElement.setAttribute("rx", "5");
        localElement.setAttribute("ry", "5");
      }
      localElement.setAttribute("width", String.valueOf(d1));
      localElement.setAttribute("height", String.valueOf(d2));
    }
    if (paramString2 != null)
    {
      Node localNode = getGradient(paramDocument, paramString1, paramString2);
      String str = localNode.getAttributes().getNamedItem("id").getNodeValue();
      localElement.setAttribute("fill", "url(#" + str + ")");
    }
    else if (paramString1 != null)
    {
      localElement.setAttribute("fill", paramString1);
    }
    localElement.setAttribute("opacity", String.valueOf(paramDouble3));
    localElement.setAttribute("stroke", paramString3);
    localElement.setAttribute("stroke-width", String.valueOf(paramFloat));
    return localElement;
  }
  
  public Node createTextNode(Document paramDocument, String paramString1, String paramString2, Font paramFont, String paramString3, int paramInt1, int paramInt2)
  {
    Element localElement = paramDocument.createElement("text");
    localElement.appendChild(paramDocument.createTextNode(paramString1));
    int i = 11;
    if (paramFont != null)
    {
      localElement.setAttribute("font-family", paramFont.getFamily());
      localElement.setAttribute("font-size", String.valueOf(paramFont.getSize2D()));
      i = paramFont.getSize();
    }
    else
    {
      localElement.setAttribute("font-family", "Dialog");
      localElement.setAttribute("font-size", "11");
    }
    localElement.setAttribute("fill", paramString3);
    double d = i / FONT_PROPORTION_FACTOR;
    int j = (int)(paramString1.length() * d / 2.0D);
    if (paramString2 != null) {
      if (paramInt1 - j < this.viewBox.getX())
      {
        localElement.setAttribute("text-anchor", "start");
        paramInt1 = (int)this.viewBox.getX();
      }
      else if (paramInt1 + j > this.viewBox.getWidth() - 2.0D)
      {
        localElement.setAttribute("text-anchor", "end");
        paramInt1 = (int)this.viewBox.getWidth() - 2;
      }
      else
      {
        localElement.setAttribute("text-anchor", paramString2);
      }
    }
    localElement.setAttribute("x", String.valueOf(paramInt1));
    localElement.setAttribute("y", String.valueOf(Math.max(this.viewBox.getY() + i, paramInt2)));
    return localElement;
  }
  
  protected Node createDropShadowFilter(Document paramDocument, int paramInt1, int paramInt2, int paramInt3)
  {
    Element localElement1 = paramDocument.createElement("filter");
    localElement1.setAttribute("id", "dropShadow");
    localElement1.setAttribute("x", "0");
    localElement1.setAttribute("y", "0");
    localElement1.setAttribute("width", "1");
    localElement1.setAttribute("height", "1");
    localElement1.setAttribute("filterMarginsUnits", "userSpaceOnUse");
    localElement1.setAttribute("dx", "0");
    localElement1.setAttribute("dy", "0");
    localElement1.setAttribute("dw", "5");
    localElement1.setAttribute("dh", "5");
    Element localElement2 = paramDocument.createElement("feGaussianBlur");
    localElement2.setAttribute("stdDeviation", String.valueOf(paramInt1));
    localElement2.setAttribute("in", "SourceAlpha");
    localElement1.appendChild(localElement2);
    Element localElement3 = paramDocument.createElement("feOffset");
    localElement3.setAttribute("dx", String.valueOf(paramInt2));
    localElement3.setAttribute("dy", String.valueOf(paramInt3));
    localElement1.appendChild(localElement3);
    Element localElement4 = paramDocument.createElement("feMerge");
    localElement4.appendChild(paramDocument.createElement("feMergeNode"));
    Element localElement5 = paramDocument.createElement("feMergeNode");
    localElement5.setAttribute("in", "SourceGraphic");
    localElement4.appendChild(localElement5);
    localElement1.appendChild(localElement4);
    return localElement1;
  }
  
  public Object[] getLabels(CellView paramCellView)
  {
    return new Object[0];
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/io/svg/SVGGraphWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */