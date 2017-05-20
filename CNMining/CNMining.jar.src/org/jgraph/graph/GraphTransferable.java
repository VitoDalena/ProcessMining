package org.jgraph.graph;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.Map;
import org.jgraph.plaf.basic.BasicGraphTransferable;

public class GraphTransferable
  extends BasicGraphTransferable
  implements Serializable, ClipboardOwner
{
  public static DataFlavor dataFlavor;
  protected Object[] cells;
  protected ConnectionSet cs;
  protected ParentMap pm;
  protected Map attributeMap;
  protected Rectangle2D bounds;
  
  public GraphTransferable(Object[] paramArrayOfObject, Map paramMap, Rectangle2D paramRectangle2D, ConnectionSet paramConnectionSet, ParentMap paramParentMap)
  {
    this.attributeMap = paramMap;
    this.bounds = paramRectangle2D;
    this.cells = paramArrayOfObject;
    this.cs = paramConnectionSet;
    this.pm = paramParentMap;
  }
  
  public Object[] getCells()
  {
    return this.cells;
  }
  
  public ConnectionSet getConnectionSet()
  {
    return this.cs;
  }
  
  public ParentMap getParentMap()
  {
    return this.pm;
  }
  
  public Map getAttributeMap()
  {
    return this.attributeMap;
  }
  
  public Rectangle2D getBounds()
  {
    return this.bounds;
  }
  
  public void lostOwnership(Clipboard paramClipboard, Transferable paramTransferable) {}
  
  public DataFlavor[] getRicherFlavors()
  {
    return new DataFlavor[] { dataFlavor };
  }
  
  public Object getRicherData(DataFlavor paramDataFlavor)
    throws UnsupportedFlavorException
  {
    if (paramDataFlavor.equals(dataFlavor)) {
      return this;
    }
    throw new UnsupportedFlavorException(paramDataFlavor);
  }
  
  public boolean isPlainSupported()
  {
    return (this.cells != null) && (this.cells.length == 1);
  }
  
  public String getPlainData()
  {
    if ((this.cells[0] instanceof DefaultGraphCell))
    {
      Object localObject = ((DefaultGraphCell)this.cells[0]).getUserObject();
      if (localObject != null) {
        return localObject.toString();
      }
    }
    return this.cells[0].toString();
  }
  
  public boolean isHTMLSupported()
  {
    return isPlainSupported();
  }
  
  public String getHTMLData()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("<html><body><p>");
    localStringBuffer.append(getPlainData());
    localStringBuffer.append("</p></body></html>");
    return localStringBuffer.toString();
  }
  
  static
  {
    DataFlavor localDataFlavor;
    try
    {
      localDataFlavor = new DataFlavor("application/x-java-jvm-local-objectref; class=org.jgraph.graph.GraphTransferable");
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      localDataFlavor = null;
    }
    dataFlavor = localDataFlavor;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/graph/GraphTransferable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */