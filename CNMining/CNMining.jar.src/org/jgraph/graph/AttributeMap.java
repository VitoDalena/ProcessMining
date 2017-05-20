package org.jgraph.graph;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class AttributeMap
  extends Hashtable
  implements Cloneable
{
  public static transient AttributeMap emptyAttributeMap = new AttributeMap(0)
  {
    public Object clone()
    {
      return this;
    }
  };
  
  public AttributeMap()
  {
    super(8);
  }
  
  public AttributeMap(int paramInt)
  {
    super(paramInt);
  }
  
  public AttributeMap(int paramInt, float paramFloat)
  {
    super(paramInt, paramFloat);
  }
  
  public AttributeMap(Map paramMap)
  {
    super(paramMap);
  }
  
  public Point2D createPoint()
  {
    return new SerializablePoint2D();
  }
  
  public Point2D createPoint(Point2D paramPoint2D)
  {
    if (paramPoint2D != null) {
      return createPoint(paramPoint2D.getX(), paramPoint2D.getY());
    }
    return null;
  }
  
  public Point2D createPoint(double paramDouble1, double paramDouble2)
  {
    return new SerializablePoint2D(paramDouble1, paramDouble2);
  }
  
  public Rectangle2D createRect()
  {
    return new SerializableRectangle2D();
  }
  
  public Rectangle2D createRect(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    return new SerializableRectangle2D(paramDouble1, paramDouble2, paramDouble3, paramDouble4);
  }
  
  public Rectangle2D createRect(Point2D paramPoint2D)
  {
    return createRect(paramPoint2D, 0.0D);
  }
  
  public Rectangle2D createRect(Point2D paramPoint2D, double paramDouble)
  {
    if (paramPoint2D != null) {
      return createRect(paramPoint2D.getX(), paramPoint2D.getY(), paramDouble, paramDouble);
    }
    return null;
  }
  
  public Rectangle2D createRect(Rectangle2D paramRectangle2D)
  {
    if (paramRectangle2D != null) {
      return createRect(paramRectangle2D.getX(), paramRectangle2D.getY(), paramRectangle2D.getWidth(), paramRectangle2D.getHeight());
    }
    return null;
  }
  
  public Rectangle2D createRect(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6)
  {
    return createRect(paramDouble1 - paramDouble5, paramDouble2 - paramDouble5, paramDouble3 + paramDouble5 + paramDouble6, paramDouble4 + paramDouble5 + paramDouble6);
  }
  
  public Rectangle2D createRect(Rectangle2D paramRectangle2D, double paramDouble1, double paramDouble2)
  {
    if (paramRectangle2D != null) {
      return createRect(paramRectangle2D.getX(), paramRectangle2D.getY(), paramRectangle2D.getWidth(), paramRectangle2D.getHeight(), paramDouble1, paramDouble2);
    }
    return null;
  }
  
  public AttributeMap applyMap(Map paramMap)
  {
    AttributeMap localAttributeMap = new AttributeMap();
    if (paramMap != null)
    {
      if (GraphConstants.isRemoveAll(paramMap))
      {
        localAttributeMap.putAll(this);
        clear();
      }
      Object[] arrayOfObject = GraphConstants.getRemoveAttributes(paramMap);
      if (arrayOfObject != null) {
        for (int i = 0; i < arrayOfObject.length; i++)
        {
          localObject1 = remove(arrayOfObject[i]);
          if (localObject1 != null) {
            localAttributeMap.put(arrayOfObject[i], localObject1);
          }
        }
      }
      HashSet localHashSet = null;
      Object localObject1 = paramMap.entrySet().iterator();
      while (((Iterator)localObject1).hasNext())
      {
        Map.Entry localEntry = (Map.Entry)((Iterator)localObject1).next();
        Object localObject2 = localEntry.getKey();
        if ((!localObject2.equals("removeAll")) && (!localObject2.equals("removeAttributes")) && (!localObject2.equals("value")))
        {
          Object localObject3 = applyValue(localObject2, localEntry.getValue());
          if (localObject3 == null)
          {
            if (localHashSet == null) {
              localHashSet = new HashSet();
            }
            localHashSet.add(localObject2);
          }
          else
          {
            localAttributeMap.put(localObject2, localObject3);
          }
        }
      }
      if ((localHashSet != null) && (!localHashSet.isEmpty())) {
        GraphConstants.setRemoveAttributes(localAttributeMap, localHashSet.toArray());
      }
    }
    return localAttributeMap;
  }
  
  public Object applyValue(Object paramObject1, Object paramObject2)
  {
    if ((paramObject2 instanceof Rectangle2D)) {
      paramObject2 = createRect((Rectangle2D)paramObject2);
    }
    if ((paramObject2 instanceof Point2D)) {
      paramObject2 = createPoint((Point2D)paramObject2);
    }
    if ((paramObject2 instanceof Point2D[])) {
      paramObject2 = clonePoints((Point2D[])paramObject2);
    }
    if ((paramObject2 instanceof List)) {
      paramObject2 = clonePoints((List)paramObject2);
    }
    return put(paramObject1, paramObject2);
  }
  
  public Point2D[] clonePoints(Point2D[] paramArrayOfPoint2D)
  {
    List localList = clonePoints(paramArrayOfPoint2D, true);
    Point2D[] arrayOfPoint2D = new Point2D[localList.size()];
    localList.toArray(arrayOfPoint2D);
    return arrayOfPoint2D;
  }
  
  public List clonePoints(List paramList)
  {
    return clonePoints(paramList.toArray(), true);
  }
  
  public List clonePoints(Object[] paramArrayOfObject, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList(paramArrayOfObject.length);
    for (int i = 0; i < paramArrayOfObject.length; i++)
    {
      Object localObject = paramArrayOfObject[i];
      if (((localObject instanceof PortView)) && (paramBoolean)) {
        localObject = createPoint(((PortView)localObject).getLocation());
      } else if ((localObject instanceof Point2D)) {
        localObject = createPoint((Point2D)localObject);
      }
      localArrayList.add(localObject);
    }
    return localArrayList;
  }
  
  public static void translate(Collection paramCollection, double paramDouble1, double paramDouble2)
  {
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      if ((localObject instanceof AttributeMap)) {
        ((AttributeMap)localObject).translate(paramDouble1, paramDouble2);
      }
    }
  }
  
  public void translate(double paramDouble1, double paramDouble2)
  {
    if (GraphConstants.isMoveable(this))
    {
      Rectangle2D localRectangle2D = GraphConstants.getBounds(this);
      if (localRectangle2D != null)
      {
        int i = GraphConstants.getMoveableAxis(this);
        if (i == 1) {
          paramDouble2 = 0.0D;
        } else if (i == 2) {
          paramDouble1 = 0.0D;
        }
        localRectangle2D.setFrame(localRectangle2D.getX() + paramDouble1, localRectangle2D.getY() + paramDouble2, localRectangle2D.getWidth(), localRectangle2D.getHeight());
      }
      List localList = GraphConstants.getPoints(this);
      if (localList != null) {
        for (int j = 0; j < localList.size(); j++)
        {
          Object localObject = localList.get(j);
          if ((localObject instanceof Point2D))
          {
            Point2D localPoint2D = (Point2D)localObject;
            localPoint2D.setLocation(localPoint2D.getX() + paramDouble1, localPoint2D.getY() + paramDouble2);
          }
        }
      }
    }
  }
  
  public void scale(double paramDouble1, double paramDouble2, Point2D paramPoint2D)
  {
    Rectangle2D localRectangle2D = GraphConstants.getBounds(this);
    Object localObject2;
    if (localRectangle2D != null)
    {
      localObject1 = createPoint(localRectangle2D.getX(), localRectangle2D.getY());
      localObject2 = (Point2D)((Point2D)localObject1).clone();
      ((Point2D)localObject1).setLocation(paramPoint2D.getX() + Math.round((((Point2D)localObject1).getX() - paramPoint2D.getX()) * paramDouble1), paramPoint2D.getY() + Math.round((((Point2D)localObject1).getY() - paramPoint2D.getY()) * paramDouble2));
      if (!((Point2D)localObject1).equals(localObject2)) {
        translate(((Point2D)localObject1).getX() - ((Point2D)localObject2).getX(), ((Point2D)localObject1).getY() - ((Point2D)localObject2).getY());
      }
      int i = GraphConstants.getSizeableAxis(this);
      if (i == 1) {
        paramDouble2 = 1.0D;
      } else if (i == 2) {
        paramDouble1 = 1.0D;
      }
      double d1 = Math.max(1L, Math.round(localRectangle2D.getWidth() * paramDouble1));
      double d2 = Math.max(1L, Math.round(localRectangle2D.getHeight() * paramDouble2));
      localRectangle2D.setFrame(localRectangle2D.getX(), localRectangle2D.getY(), d1, d2);
    }
    Object localObject1 = GraphConstants.getPoints(this);
    if (localObject1 != null)
    {
      localObject2 = ((List)localObject1).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        Object localObject3 = ((Iterator)localObject2).next();
        if ((localObject3 instanceof Point2D))
        {
          Point2D localPoint2D1 = (Point2D)localObject3;
          Point2D localPoint2D2 = (Point2D)localPoint2D1.clone();
          localPoint2D2.setLocation(paramPoint2D.getX() + Math.round((localPoint2D2.getX() - paramPoint2D.getX()) * paramDouble1), paramPoint2D.getY() + Math.round((localPoint2D2.getY() - paramPoint2D.getY()) * paramDouble2));
          localPoint2D1.setLocation(localPoint2D2);
        }
      }
    }
  }
  
  public Map diff(Map paramMap)
  {
    Hashtable localHashtable = new Hashtable();
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      Object localObject1 = localEntry.getKey();
      Object localObject2 = localEntry.getValue();
      Object localObject3 = get(localObject1);
      if ((localObject3 == null) || (!localObject3.equals(localObject2))) {
        localHashtable.put(localObject1, localObject2);
      }
    }
    return localHashtable;
  }
  
  public Object clone()
  {
    return cloneEntries((AttributeMap)super.clone());
  }
  
  public AttributeMap cloneEntries(AttributeMap paramAttributeMap)
  {
    Rectangle2D localRectangle2D = GraphConstants.getBounds(paramAttributeMap);
    if (localRectangle2D != null) {
      GraphConstants.setBounds(paramAttributeMap, (Rectangle2D)localRectangle2D.clone());
    }
    List localList = GraphConstants.getPoints(paramAttributeMap);
    if (localList != null) {
      GraphConstants.setPoints(paramAttributeMap, clonePoints(localList));
    }
    Point2D[] arrayOfPoint2D = GraphConstants.getExtraLabelPositions(paramAttributeMap);
    if (arrayOfPoint2D != null) {
      GraphConstants.setExtraLabelPositions(paramAttributeMap, clonePoints(arrayOfPoint2D));
    }
    Point2D localPoint2D = GraphConstants.getLabelPosition(paramAttributeMap);
    if (localPoint2D != null) {
      GraphConstants.setLabelPosition(paramAttributeMap, (Point2D)localPoint2D.clone());
    }
    return paramAttributeMap;
  }
  
  public static class SerializableRectangle2D
    extends Rectangle2D.Double
    implements Serializable
  {
    public SerializableRectangle2D() {}
    
    public SerializableRectangle2D(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
    {
      super(paramDouble2, paramDouble3, paramDouble4);
    }
    
    public void setX(double paramDouble)
    {
      setFrame(paramDouble, getY(), getWidth(), getHeight());
    }
    
    public void setY(double paramDouble)
    {
      setFrame(getX(), paramDouble, getWidth(), getHeight());
    }
    
    public void setWidth(double paramDouble)
    {
      setFrame(getX(), getY(), paramDouble, getHeight());
    }
    
    public void setHeight(double paramDouble)
    {
      setFrame(getX(), getY(), getWidth(), paramDouble);
    }
    
    private void writeObject(ObjectOutputStream paramObjectOutputStream)
      throws IOException
    {
      paramObjectOutputStream.defaultWriteObject();
      paramObjectOutputStream.writeObject(new Double(getX()));
      paramObjectOutputStream.writeObject(new Double(getY()));
      paramObjectOutputStream.writeObject(new Double(getWidth()));
      paramObjectOutputStream.writeObject(new Double(getHeight()));
    }
    
    private void readObject(ObjectInputStream paramObjectInputStream)
      throws IOException, ClassNotFoundException
    {
      paramObjectInputStream.defaultReadObject();
      Double localDouble1 = (Double)paramObjectInputStream.readObject();
      Double localDouble2 = (Double)paramObjectInputStream.readObject();
      Double localDouble3 = (Double)paramObjectInputStream.readObject();
      Double localDouble4 = (Double)paramObjectInputStream.readObject();
      setFrame(localDouble1.doubleValue(), localDouble2.doubleValue(), localDouble3.doubleValue(), localDouble4.doubleValue());
    }
  }
  
  public static class SerializablePoint2D
    extends Point2D.Double
    implements Serializable
  {
    public SerializablePoint2D() {}
    
    public SerializablePoint2D(double paramDouble1, double paramDouble2)
    {
      super(paramDouble2);
    }
    
    public void setX(double paramDouble)
    {
      setLocation(paramDouble, getY());
    }
    
    public void setY(double paramDouble)
    {
      setLocation(getX(), paramDouble);
    }
    
    private void writeObject(ObjectOutputStream paramObjectOutputStream)
      throws IOException
    {
      paramObjectOutputStream.defaultWriteObject();
      paramObjectOutputStream.writeObject(new Double(getX()));
      paramObjectOutputStream.writeObject(new Double(getY()));
    }
    
    private void readObject(ObjectInputStream paramObjectInputStream)
      throws IOException, ClassNotFoundException
    {
      paramObjectInputStream.defaultReadObject();
      Double localDouble1 = (Double)paramObjectInputStream.readObject();
      Double localDouble2 = (Double)paramObjectInputStream.readObject();
      setLocation(localDouble1.doubleValue(), localDouble2.doubleValue());
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/graph/AttributeMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */