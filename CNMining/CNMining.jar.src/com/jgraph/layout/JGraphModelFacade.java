package com.jgraph.layout;

import com.jgraph.algebra.JGraphAlgebra;
import com.jgraph.algebra.cost.JGraphCostFunction;
import com.jgraph.algebra.cost.JGraphDistanceCostFunction;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.GraphModel;

public class JGraphModelFacade
  extends JGraphFacade
{
  public JGraphModelFacade(GraphModel paramGraphModel)
  {
    this(paramGraphModel, null);
  }
  
  public JGraphModelFacade(GraphModel paramGraphModel, Object[] paramArrayOfObject)
  {
    this(paramGraphModel, paramArrayOfObject, true, false, true, true);
  }
  
  public JGraphModelFacade(GraphModel paramGraphModel, Object[] paramArrayOfObject, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
  {
    this(paramGraphModel, paramArrayOfObject, paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4, new JGraphDistanceCostFunction(null), JGraphAlgebra.getSharedInstance());
  }
  
  public JGraphModelFacade(GraphModel paramGraphModel, Object[] paramArrayOfObject, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, JGraphCostFunction paramJGraphCostFunction, JGraphAlgebra paramJGraphAlgebra)
  {
    super(paramGraphModel, paramArrayOfObject, paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4, paramJGraphCostFunction, paramJGraphAlgebra);
  }
  
  public List getNeighbours(Object paramObject, boolean paramBoolean)
  {
    return getNeighbours(paramObject, null, paramBoolean);
  }
  
  public List getNeighbours(Object paramObject, Set paramSet, boolean paramBoolean)
  {
    Object[] arrayOfObject = this.directed ? DefaultGraphModel.getOutgoingEdges(this.model, paramObject) : DefaultGraphModel.getEdges(this.model, new Object[] { paramObject }).toArray();
    ArrayList localArrayList = new ArrayList(arrayOfObject.length);
    HashSet localHashSet = new HashSet(arrayOfObject.length + 8, 0.75F);
    for (int i = 0; i < arrayOfObject.length; i++)
    {
      Object localObject = DefaultGraphModel.getOpposite(this.model, arrayOfObject[i], paramObject);
      if ((localObject != null) && ((paramSet == null) || (!paramSet.contains(localObject))) && (!localHashSet.contains(localObject)))
      {
        localHashSet.add(localObject);
        localArrayList.add(localObject);
      }
    }
    if ((paramBoolean) && (this.order != null)) {
      Collections.sort(localArrayList, this.order);
    }
    return localArrayList;
  }
  
  public List getOutgoingEdges(Object paramObject, Set paramSet, boolean paramBoolean1, boolean paramBoolean2)
  {
    Object[] arrayOfObject = DefaultGraphModel.getEdges(this.model, paramObject, false);
    ArrayList localArrayList = new ArrayList(arrayOfObject.length);
    HashSet localHashSet = new HashSet(arrayOfObject.length);
    for (int i = 0; i < arrayOfObject.length; i++) {
      if (((paramSet == null) || (!paramSet.contains(arrayOfObject[i]))) && (!localHashSet.contains(arrayOfObject[i])))
      {
        if ((paramBoolean2 == true) || (this.model.getSource(arrayOfObject[i]) != this.model.getTarget(arrayOfObject[i]))) {
          localArrayList.add(arrayOfObject[i]);
        }
        localHashSet.add(arrayOfObject[i]);
      }
    }
    return localArrayList;
  }
  
  public List getIncomingEdges(Object paramObject, Set paramSet, boolean paramBoolean1, boolean paramBoolean2)
  {
    Object[] arrayOfObject = DefaultGraphModel.getEdges(this.model, paramObject, true);
    ArrayList localArrayList = new ArrayList(arrayOfObject.length);
    HashSet localHashSet = new HashSet(arrayOfObject.length);
    for (int i = 0; i < arrayOfObject.length; i++) {
      if (((paramSet == null) || (!paramSet.contains(arrayOfObject[i]))) && (!localHashSet.contains(arrayOfObject[i])))
      {
        if ((paramBoolean2 == true) || (this.model.getSource(arrayOfObject[i]) != this.model.getTarget(arrayOfObject[i]))) {
          localArrayList.add(arrayOfObject[i]);
        }
        localHashSet.add(arrayOfObject[i]);
      }
    }
    return localArrayList;
  }
  
  public Rectangle2D getGraphBounds()
  {
    return getCellBounds();
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/layout/JGraphModelFacade.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */