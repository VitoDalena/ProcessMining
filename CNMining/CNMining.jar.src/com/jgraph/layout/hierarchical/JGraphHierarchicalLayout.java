package com.jgraph.layout.hierarchical;

import com.jgraph.layout.JGraphFacade;
import com.jgraph.layout.JGraphLayout;
import com.jgraph.layout.JGraphLayout.Stoppable;
import com.jgraph.layout.JGraphLayoutProgress;
import com.jgraph.layout.hierarchical.model.JGraphHierarchyModel;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.GraphModel;

public class JGraphHierarchicalLayout
  implements JGraphLayout, JGraphLayout.Stoppable
{
  private static final double INITIAL_X_POSITION = 100.0D;
  protected double intraCellSpacing = 30.0D;
  protected double interRankCellSpacing = 50.0D;
  protected double interHierarchySpacing = 60.0D;
  protected double parallelEdgeSpacing = 10.0D;
  protected int orientation = 1;
  protected boolean fineTuning = true;
  protected boolean compactLayout = false;
  protected boolean deterministic = false;
  protected boolean fixRoots = false;
  protected boolean layoutFromSinks = true;
  protected JGraphHierarchyModel model = null;
  protected JGraphHierarchicalLayoutStep cycleStage = null;
  protected JGraphHierarchicalLayoutStep layeringStage = null;
  protected JGraphHierarchicalLayoutStep crossingStage = null;
  protected JGraphHierarchicalLayoutStep placementStage = null;
  protected JGraphLayoutProgress progress = new JGraphLayoutProgress();
  private static Logger logger = Logger.getLogger("com.jgraph.layout.hierarchical.JGraphHierarchicalLayout");
  
  public JGraphHierarchicalLayout()
  {
    this(true);
  }
  
  public JGraphHierarchicalLayout(boolean paramBoolean)
  {
    this.deterministic = paramBoolean;
  }
  
  public void run(JGraphFacade paramJGraphFacade)
  {
    List localList1 = DefaultGraphModel.getDescendants(paramJGraphFacade.getModel(), paramJGraphFacade.getVertices().toArray());
    int i = 0;
    Iterator localIterator1;
    HashSet localHashSet2;
    if ((paramJGraphFacade.getRoots() == null) || (paramJGraphFacade.getRoots().size() == 0))
    {
      localArrayList1 = new ArrayList();
      localArrayList2 = new ArrayList();
      int j = 0;
      localObject1 = paramJGraphFacade.getVertices().toArray();
      for (int k = 0; k < localObject1.length; k++)
      {
        HashSet localHashSet1 = new HashSet();
        List localList2 = paramJGraphFacade.getIncomingEdges(localObject1[k], null, true, false);
        localIterator1 = localList2.iterator();
        while (localIterator1.hasNext())
        {
          Object localObject2 = DefaultGraphModel.getSourceVertex(paramJGraphFacade.getModel(), localIterator1.next());
          if (localList1.contains(localObject2)) {
            localHashSet1.add(localObject2);
          }
        }
        int i1 = localHashSet1.size();
        if (i1 == 0)
        {
          localArrayList1.add(localObject1[k]);
        }
        else
        {
          localHashSet2 = new HashSet();
          localObject4 = paramJGraphFacade.getOutgoingEdges(localObject1[k], null, true, false);
          localIterator1 = ((List)localObject4).iterator();
          while (localIterator1.hasNext())
          {
            Object localObject5 = DefaultGraphModel.getTargetVertex(paramJGraphFacade.getModel(), localIterator1.next());
            if (localList1.contains(localObject5)) {
              localHashSet2.add(localObject5);
            }
          }
          int i3 = localHashSet2.size();
          int i4 = i3 - i1;
          if (i4 > j)
          {
            localArrayList2 = new ArrayList();
            localArrayList2.add(localObject1[k]);
            j = i4;
          }
          else if (i4 == j)
          {
            localArrayList2.add(localObject1[k]);
          }
        }
      }
      if (localArrayList1.size() > 0) {
        paramJGraphFacade.setRoots(localArrayList1);
      } else if (localArrayList2.size() > 0) {
        paramJGraphFacade.setRoots(localArrayList2);
      } else {
        return;
      }
      i = 1;
    }
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = null;
    ArrayList localArrayList3 = null;
    Object localObject1 = null;
    if (this.fixRoots)
    {
      localArrayList2 = new ArrayList();
      localArrayList3 = new ArrayList();
      localObject1 = new ArrayList();
    }
    Object[] arrayOfObject = paramJGraphFacade.getRoots().toArray();
    Object localObject6;
    Object localObject7;
    for (int m = 0; m < arrayOfObject.length; m++)
    {
      int n = 1;
      localIterator1 = localArrayList1.iterator();
      while ((n != 0) && (localIterator1.hasNext())) {
        if (((Set)localIterator1.next()).contains(arrayOfObject[m])) {
          n = 0;
        }
      }
      if (n != 0)
      {
        localObject3 = new Stack();
        ((Stack)localObject3).push(arrayOfObject[m]);
        localHashSet2 = null;
        if (this.fixRoots)
        {
          localArrayList2.add(arrayOfObject[m]);
          localObject4 = paramJGraphFacade.getLocation(arrayOfObject[m]);
          localArrayList3.add(localObject4);
          localHashSet2 = new HashSet();
        }
        localObject4 = new HashSet();
        while (!((Stack)localObject3).isEmpty())
        {
          localObject6 = ((Stack)localObject3).pop();
          if (!((Set)localObject4).contains(localObject6))
          {
            ((Set)localObject4).add(localObject6);
            boolean bool = paramJGraphFacade.isDirected();
            paramJGraphFacade.setDirected(false);
            localObject7 = paramJGraphFacade.getNeighbours(localObject6, (Set)localObject4, false);
            HashSet localHashSet3 = new HashSet();
            Iterator localIterator2 = ((List)localObject7).iterator();
            while (localIterator2.hasNext())
            {
              Object localObject8 = localIterator2.next();
              localObject8 = findParent(paramJGraphFacade, localList1, localObject8);
              if ((localObject8 != null) && (localObject8 != localObject6)) {
                localHashSet3.add(localObject8);
              }
            }
            localIterator2 = localHashSet3.iterator();
            if (this.fixRoots) {
              localHashSet2.addAll(paramJGraphFacade.getIncomingEdges(localObject6, null, true, false));
            }
            paramJGraphFacade.setDirected(bool);
            while (localIterator2.hasNext()) {
              ((Stack)localObject3).push(localIterator2.next());
            }
          }
        }
        localArrayList1.add(localObject4);
        if (this.fixRoots) {
          ((List)localObject1).add(localHashSet2);
        }
      }
    }
    this.progress.reset(localArrayList1.size() * 4 + 1);
    m = 1;
    double d1 = 100.0D;
    Object localObject3 = localArrayList1.iterator();
    int i2 = 0;
    while (((Iterator)localObject3).hasNext())
    {
      localObject4 = (Set)((Iterator)localObject3).next();
      this.model = new JGraphHierarchyModel(paramJGraphFacade, ((Set)localObject4).toArray(), false, this.deterministic, this.layoutFromSinks);
      this.cycleStage = new JGraphMinimumCycleRemover();
      this.model = this.cycleStage.run(paramJGraphFacade, this.model);
      if (this.model == null) {
        throw new RuntimeException("Could not remove cycles in hierarchical layout");
      }
      this.progress.setProgress(++m);
      if (!this.progress.isStopped())
      {
        this.layeringStage = new JGraphLongestPathLayering();
        this.model = this.layeringStage.run(paramJGraphFacade, this.model);
      }
      this.progress.setProgress(++m);
      if (!this.progress.isStopped())
      {
        this.crossingStage = new JGraphMedianHybridCrossingReduction();
        this.model = this.crossingStage.run(paramJGraphFacade, this.model);
      }
      this.progress.setProgress(++m);
      if (!this.progress.isStopped())
      {
        this.placementStage = new JGraphCoordinateAssignment(this.intraCellSpacing, this.interRankCellSpacing, this.orientation, this.compactLayout, d1, this.parallelEdgeSpacing);
        this.model = this.placementStage.run(paramJGraphFacade, this.model);
        d1 = ((JGraphCoordinateAssignment)this.placementStage).getLimitX() + this.interHierarchySpacing;
      }
      this.progress.setProgress(++m);
      if (this.fixRoots)
      {
        localObject6 = localArrayList2.get(i2);
        Point2D localPoint2D = (Point2D)localArrayList3.get(i2);
        localObject7 = paramJGraphFacade.getLocation(localObject6);
        double d2 = localPoint2D.getX() - ((Point2D)localObject7).getX();
        double d3 = localPoint2D.getY() - ((Point2D)localObject7).getY();
        paramJGraphFacade.translateCells((Collection)localObject4, d2, d3);
        Set localSet = (Set)((List)localObject1).get(i2++);
        paramJGraphFacade.translateCells(localSet, d2, d3);
      }
    }
    if (i == 1) {
      paramJGraphFacade.setRoots(null);
    }
    Object localObject4 = paramJGraphFacade.getVertices().iterator();
    while (((Iterator)localObject4).hasNext())
    {
      localObject6 = ((Iterator)localObject4).next();
      paramJGraphFacade.getCache().setVisible(localObject6, false);
    }
  }
  
  private Object findParent(JGraphFacade paramJGraphFacade, List paramList, Object paramObject)
  {
    if (paramObject == null) {
      return null;
    }
    Object localObject = findParent(paramJGraphFacade, paramList, paramJGraphFacade.getModel().getParent(paramObject));
    if (localObject == null)
    {
      if (paramList.contains(paramObject)) {
        return paramObject;
      }
      return null;
    }
    return localObject;
  }
  
  public String toString()
  {
    return "Hierarchical";
  }
  
  public JGraphLayoutProgress getProgress()
  {
    return this.progress;
  }
  
  public double getIntraCellSpacing()
  {
    return this.intraCellSpacing;
  }
  
  public void setIntraCellSpacing(double paramDouble)
  {
    this.intraCellSpacing = paramDouble;
  }
  
  public double getInterRankCellSpacing()
  {
    return this.interRankCellSpacing;
  }
  
  public void setInterRankCellSpacing(double paramDouble)
  {
    this.interRankCellSpacing = paramDouble;
  }
  
  public int getOrientation()
  {
    return this.orientation;
  }
  
  public void setOrientation(int paramInt)
  {
    this.orientation = paramInt;
  }
  
  public double getInterHierarchySpacing()
  {
    return this.interHierarchySpacing;
  }
  
  public void setInterHierarchySpacing(double paramDouble)
  {
    this.interHierarchySpacing = paramDouble;
  }
  
  public double getParallelEdgeSpacing()
  {
    return this.parallelEdgeSpacing;
  }
  
  public void setParallelEdgeSpacing(double paramDouble)
  {
    this.parallelEdgeSpacing = paramDouble;
  }
  
  public boolean isFineTuning()
  {
    return this.fineTuning;
  }
  
  public void setFineTuning(boolean paramBoolean)
  {
    this.fineTuning = paramBoolean;
  }
  
  public boolean isDeterministic()
  {
    return this.deterministic;
  }
  
  public void setDeterministic(boolean paramBoolean)
  {
    this.deterministic = paramBoolean;
  }
  
  public boolean isCompactLayout()
  {
    return this.compactLayout;
  }
  
  public void setCompactLayout(boolean paramBoolean)
  {
    this.compactLayout = paramBoolean;
  }
  
  public boolean isFixRoots()
  {
    return this.fixRoots;
  }
  
  public void setFixRoots(boolean paramBoolean)
  {
    this.fixRoots = paramBoolean;
  }
  
  public boolean isLayoutFromSinks()
  {
    return this.layoutFromSinks;
  }
  
  public void setLayoutFromSinks(boolean paramBoolean)
  {
    this.layoutFromSinks = paramBoolean;
  }
  
  public void setLoggerLevel(Level paramLevel)
  {
    try
    {
      logger.setLevel(paramLevel);
    }
    catch (SecurityException localSecurityException) {}
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/layout/hierarchical/JGraphHierarchicalLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */