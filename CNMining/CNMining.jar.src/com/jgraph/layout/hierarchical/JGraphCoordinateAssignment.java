package com.jgraph.layout.hierarchical;

import com.jgraph.layout.JGraphFacade;
import com.jgraph.layout.hierarchical.model.JGraphAbstractHierarchyCell;
import com.jgraph.layout.hierarchical.model.JGraphHierarchyEdge;
import com.jgraph.layout.hierarchical.model.JGraphHierarchyModel;
import com.jgraph.layout.hierarchical.model.JGraphHierarchyNode;
import com.jgraph.layout.hierarchical.model.JGraphHierarchyRank;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JGraphCoordinateAssignment
  implements JGraphHierarchicalLayoutStep
{
  protected double intraCellSpacing = 30.0D;
  protected double interRankCellSpacing = 30.0D;
  protected double parallelEdgeSpacing = 10.0D;
  protected int maxIterations = 8;
  protected int orientation = 1;
  protected double initialX;
  protected double limitX;
  protected double currentXDelta;
  protected int widestRank;
  protected double widestRankValue;
  protected double[] rankWidths;
  protected double[] rankY;
  protected boolean fineTuning = true;
  protected boolean compactLayout = false;
  protected JGraphAbstractHierarchyCell[][] nextLayerConnectedCache;
  protected JGraphAbstractHierarchyCell[][] previousLayerConnectedCache;
  private static Logger logger = Logger.getLogger("com.jgraph.layout.hierarchical.JGraphCoordinateAssignment");
  
  public JGraphCoordinateAssignment(double paramDouble1, double paramDouble2, int paramInt, boolean paramBoolean, double paramDouble3, double paramDouble4)
  {
    this.intraCellSpacing = paramDouble1;
    this.interRankCellSpacing = paramDouble2;
    this.orientation = paramInt;
    this.compactLayout = paramBoolean;
    this.initialX = paramDouble3;
    this.parallelEdgeSpacing = paramDouble4;
    setLoggerLevel(Level.OFF);
  }
  
  public JGraphHierarchyModel run(JGraphFacade paramJGraphFacade, JGraphHierarchyModel paramJGraphHierarchyModel)
  {
    this.currentXDelta = 0.0D;
    initialise(paramJGraphHierarchyModel);
    initialCoords(paramJGraphFacade, paramJGraphHierarchyModel);
    if (this.fineTuning) {
      minNode(paramJGraphHierarchyModel);
    }
    double d = 1.0E8D;
    if (this.fineTuning) {
      for (int i = 0; i < this.maxIterations; i++)
      {
        if (i != 0)
        {
          medianPos(i, paramJGraphHierarchyModel);
          minNode(paramJGraphHierarchyModel);
        }
        int j;
        JGraphHierarchyRank localJGraphHierarchyRank;
        Iterator localIterator;
        JGraphAbstractHierarchyCell localJGraphAbstractHierarchyCell;
        if (this.currentXDelta < d)
        {
          for (j = 0; j < paramJGraphHierarchyModel.ranks.size(); j++)
          {
            localJGraphHierarchyRank = (JGraphHierarchyRank)paramJGraphHierarchyModel.ranks.get(new Integer(j));
            localIterator = localJGraphHierarchyRank.iterator();
            while (localIterator.hasNext())
            {
              localJGraphAbstractHierarchyCell = (JGraphAbstractHierarchyCell)localIterator.next();
              localJGraphAbstractHierarchyCell.setX(j, localJGraphAbstractHierarchyCell.getGeneralPurposeVariable(j));
            }
          }
          d = this.currentXDelta;
        }
        else
        {
          for (j = 0; j < paramJGraphHierarchyModel.ranks.size(); j++)
          {
            localJGraphHierarchyRank = (JGraphHierarchyRank)paramJGraphHierarchyModel.ranks.get(new Integer(j));
            localIterator = localJGraphHierarchyRank.iterator();
            while (localIterator.hasNext())
            {
              localJGraphAbstractHierarchyCell = (JGraphAbstractHierarchyCell)localIterator.next();
              localJGraphAbstractHierarchyCell.setGeneralPurposeVariable(j, (int)localJGraphAbstractHierarchyCell.getX(j));
            }
          }
        }
        this.currentXDelta = 0.0D;
      }
    }
    if (this.compactLayout) {}
    setCellLocations(paramJGraphFacade, paramJGraphHierarchyModel);
    return paramJGraphHierarchyModel;
  }
  
  private void minNode(JGraphHierarchyModel paramJGraphHierarchyModel)
  {
    LinkedList localLinkedList = new LinkedList();
    Hashtable localHashtable = new Hashtable();
    Object[][] arrayOfObject = new Object[paramJGraphHierarchyModel.maxRank + 1][];
    Object localObject1;
    Object localObject2;
    for (int i = 0; i <= paramJGraphHierarchyModel.maxRank; i++)
    {
      JGraphHierarchyRank localJGraphHierarchyRank = (JGraphHierarchyRank)paramJGraphHierarchyModel.ranks.get(new Integer(i));
      arrayOfObject[i] = localJGraphHierarchyRank.toArray();
      for (k = 0; k < arrayOfObject[i].length; k++)
      {
        localObject1 = (JGraphAbstractHierarchyCell)arrayOfObject[i][k];
        localObject2 = new WeightedCellSorter((JGraphAbstractHierarchyCell)localObject1, i);
        ((WeightedCellSorter)localObject2).rankIndex = k;
        ((WeightedCellSorter)localObject2).visited = true;
        localLinkedList.add(localObject2);
        localHashtable.put(localObject1, localObject2);
      }
    }
    i = localLinkedList.size() * 10;
    int j = 0;
    int k = 1;
    while ((!localLinkedList.isEmpty()) && (j <= i))
    {
      localObject1 = (WeightedCellSorter)localLinkedList.getFirst();
      localObject2 = ((WeightedCellSorter)localObject1).cell;
      int m = ((WeightedCellSorter)localObject1).weightedValue;
      int n = ((WeightedCellSorter)localObject1).rankIndex;
      Object[] arrayOfObject1 = ((JGraphAbstractHierarchyCell)localObject2).getNextLayerConnectedCells(m).toArray();
      Object[] arrayOfObject2 = ((JGraphAbstractHierarchyCell)localObject2).getPreviousLayerConnectedCells(m).toArray();
      int i1 = arrayOfObject1.length;
      int i2 = arrayOfObject2.length;
      int i3 = medianXValue(arrayOfObject1, m + 1);
      int i4 = medianXValue(arrayOfObject2, m - 1);
      int i5 = i1 + i2;
      int i6 = ((JGraphAbstractHierarchyCell)localObject2).getGeneralPurposeVariable(m);
      double d = i6;
      if (i5 > 0) {
        d = (i3 * i1 + i4 * i2) / i5;
      }
      int i7 = 0;
      int i8;
      JGraphAbstractHierarchyCell localJGraphAbstractHierarchyCell2;
      if (d < i6 - k)
      {
        if (n == 0)
        {
          ((JGraphAbstractHierarchyCell)localObject2).setGeneralPurposeVariable(m, (int)d);
          i7 = 1;
        }
        else
        {
          JGraphAbstractHierarchyCell localJGraphAbstractHierarchyCell1 = (JGraphAbstractHierarchyCell)arrayOfObject[m][(n - 1)];
          int i9 = localJGraphAbstractHierarchyCell1.getGeneralPurposeVariable(m);
          i9 = i9 + (int)localJGraphAbstractHierarchyCell1.width / 2 + (int)this.intraCellSpacing + (int)((JGraphAbstractHierarchyCell)localObject2).width / 2;
          if (i9 < d)
          {
            ((JGraphAbstractHierarchyCell)localObject2).setGeneralPurposeVariable(m, (int)d);
            i7 = 1;
          }
          else if (i9 < ((JGraphAbstractHierarchyCell)localObject2).getGeneralPurposeVariable(m) - k)
          {
            ((JGraphAbstractHierarchyCell)localObject2).setGeneralPurposeVariable(m, i9);
            i7 = 1;
          }
        }
      }
      else if (d > i6 + k)
      {
        i8 = arrayOfObject[m].length;
        if (n == i8 - 1)
        {
          ((JGraphAbstractHierarchyCell)localObject2).setGeneralPurposeVariable(m, (int)d);
          i7 = 1;
        }
        else
        {
          localJGraphAbstractHierarchyCell2 = (JGraphAbstractHierarchyCell)arrayOfObject[m][(n + 1)];
          int i10 = localJGraphAbstractHierarchyCell2.getGeneralPurposeVariable(m);
          i10 = i10 - (int)localJGraphAbstractHierarchyCell2.width / 2 - (int)this.intraCellSpacing - (int)((JGraphAbstractHierarchyCell)localObject2).width / 2;
          if (i10 > d)
          {
            ((JGraphAbstractHierarchyCell)localObject2).setGeneralPurposeVariable(m, (int)d);
            i7 = 1;
          }
          else if (i10 > ((JGraphAbstractHierarchyCell)localObject2).getGeneralPurposeVariable(m) + k)
          {
            ((JGraphAbstractHierarchyCell)localObject2).setGeneralPurposeVariable(m, i10);
            i7 = 1;
          }
        }
      }
      if (i7 != 0)
      {
        WeightedCellSorter localWeightedCellSorter;
        for (i8 = 0; i8 < arrayOfObject1.length; i8++)
        {
          localJGraphAbstractHierarchyCell2 = (JGraphAbstractHierarchyCell)arrayOfObject1[i8];
          localWeightedCellSorter = (WeightedCellSorter)localHashtable.get(localJGraphAbstractHierarchyCell2);
          if ((localWeightedCellSorter != null) && (!localWeightedCellSorter.visited))
          {
            localWeightedCellSorter.visited = true;
            localLinkedList.add(localWeightedCellSorter);
          }
        }
        for (i8 = 0; i8 < arrayOfObject2.length; i8++)
        {
          localJGraphAbstractHierarchyCell2 = (JGraphAbstractHierarchyCell)arrayOfObject2[i8];
          localWeightedCellSorter = (WeightedCellSorter)localHashtable.get(localJGraphAbstractHierarchyCell2);
          if ((localWeightedCellSorter != null) && (!localWeightedCellSorter.visited))
          {
            localWeightedCellSorter.visited = true;
            localLinkedList.add(localWeightedCellSorter);
          }
        }
      }
      localLinkedList.removeFirst();
      ((WeightedCellSorter)localObject1).visited = false;
      j++;
    }
  }
  
  private void medianPos(int paramInt, JGraphHierarchyModel paramJGraphHierarchyModel)
  {
    int i = paramInt % 2 == 0 ? 1 : 0;
    int j;
    if (i != 0) {
      for (j = paramJGraphHierarchyModel.maxRank; j > 0; j--) {
        rankMedianPosition(j - 1, paramJGraphHierarchyModel, j);
      }
    } else {
      for (j = 0; j < paramJGraphHierarchyModel.maxRank - 1; j++) {
        rankMedianPosition(j + 1, paramJGraphHierarchyModel, j);
      }
    }
  }
  
  protected void rankMedianPosition(int paramInt1, JGraphHierarchyModel paramJGraphHierarchyModel, int paramInt2)
  {
    JGraphHierarchyRank localJGraphHierarchyRank = (JGraphHierarchyRank)paramJGraphHierarchyModel.ranks.get(new Integer(paramInt1));
    Object[] arrayOfObject1 = localJGraphHierarchyRank.toArray();
    WeightedCellSorter[] arrayOfWeightedCellSorter = new WeightedCellSorter[arrayOfObject1.length];
    Hashtable localHashtable = new Hashtable(arrayOfObject1.length);
    Object localObject;
    for (int i = 0; i < arrayOfObject1.length; i++)
    {
      JGraphAbstractHierarchyCell localJGraphAbstractHierarchyCell1 = (JGraphAbstractHierarchyCell)arrayOfObject1[i];
      arrayOfWeightedCellSorter[i] = new WeightedCellSorter();
      arrayOfWeightedCellSorter[i].cell = localJGraphAbstractHierarchyCell1;
      arrayOfWeightedCellSorter[i].rankIndex = i;
      localHashtable.put(localJGraphAbstractHierarchyCell1, arrayOfWeightedCellSorter[i]);
      localObject = null;
      if (paramInt2 < paramInt1) {
        localObject = localJGraphAbstractHierarchyCell1.getPreviousLayerConnectedCells(paramInt1);
      } else {
        localObject = localJGraphAbstractHierarchyCell1.getNextLayerConnectedCells(paramInt1);
      }
      arrayOfWeightedCellSorter[i].weightedValue = calculatedWeightedValue(localJGraphAbstractHierarchyCell1, (Collection)localObject);
    }
    Arrays.sort(arrayOfWeightedCellSorter);
    for (i = 0; i < arrayOfWeightedCellSorter.length; i++)
    {
      int j = 0;
      localObject = arrayOfWeightedCellSorter[i].cell;
      Object[] arrayOfObject2 = null;
      int k = 0;
      if (paramInt2 < paramInt1) {
        arrayOfObject2 = ((JGraphAbstractHierarchyCell)localObject).getPreviousLayerConnectedCells(paramInt1).toArray();
      } else {
        arrayOfObject2 = ((JGraphAbstractHierarchyCell)localObject).getNextLayerConnectedCells(paramInt1).toArray();
      }
      if (arrayOfObject2 != null)
      {
        j = arrayOfObject2.length;
        if (j > 0) {
          k = medianXValue(arrayOfObject2, paramInt2);
        } else {
          k = ((JGraphAbstractHierarchyCell)localObject).getGeneralPurposeVariable(paramInt1);
        }
      }
      double d1 = 0.0D;
      double d2 = -1.0E8D;
      int m = arrayOfWeightedCellSorter[i].rankIndex - 1;
      while (m >= 0)
      {
        WeightedCellSorter localWeightedCellSorter1 = (WeightedCellSorter)localHashtable.get(arrayOfObject1[m]);
        if (localWeightedCellSorter1 != null)
        {
          JGraphAbstractHierarchyCell localJGraphAbstractHierarchyCell2 = localWeightedCellSorter1.cell;
          if (localWeightedCellSorter1.visited)
          {
            d2 = localJGraphAbstractHierarchyCell2.getGeneralPurposeVariable(paramInt1) + localJGraphAbstractHierarchyCell2.width / 2.0D + this.intraCellSpacing + d1 + ((JGraphAbstractHierarchyCell)localObject).width / 2.0D;
            m = -1;
          }
          else
          {
            d1 += localJGraphAbstractHierarchyCell2.width + this.intraCellSpacing;
            m--;
          }
        }
      }
      double d3 = 0.0D;
      double d4 = 1.0E8D;
      int n = arrayOfWeightedCellSorter[i].rankIndex + 1;
      while (n < arrayOfWeightedCellSorter.length)
      {
        WeightedCellSorter localWeightedCellSorter2 = (WeightedCellSorter)localHashtable.get(arrayOfObject1[n]);
        if (localWeightedCellSorter2 != null)
        {
          JGraphAbstractHierarchyCell localJGraphAbstractHierarchyCell3 = localWeightedCellSorter2.cell;
          if (localWeightedCellSorter2.visited)
          {
            d4 = localJGraphAbstractHierarchyCell3.getGeneralPurposeVariable(paramInt1) - localJGraphAbstractHierarchyCell3.width / 2.0D - this.intraCellSpacing - d3 - ((JGraphAbstractHierarchyCell)localObject).width / 2.0D;
            n = arrayOfWeightedCellSorter.length;
          }
          else
          {
            d3 += localJGraphAbstractHierarchyCell3.width + this.intraCellSpacing;
            n++;
          }
        }
      }
      if ((k >= d2) && (k <= d4))
      {
        ((JGraphAbstractHierarchyCell)localObject).setGeneralPurposeVariable(paramInt1, k);
      }
      else if (k < d2)
      {
        ((JGraphAbstractHierarchyCell)localObject).setGeneralPurposeVariable(paramInt1, (int)d2);
        this.currentXDelta += d2 - k;
      }
      else if (k > d4)
      {
        ((JGraphAbstractHierarchyCell)localObject).setGeneralPurposeVariable(paramInt1, (int)d4);
        this.currentXDelta += k - d4;
      }
      arrayOfWeightedCellSorter[i].visited = true;
    }
  }
  
  private int calculatedWeightedValue(JGraphAbstractHierarchyCell paramJGraphAbstractHierarchyCell, Collection paramCollection)
  {
    int i = 0;
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext())
    {
      JGraphAbstractHierarchyCell localJGraphAbstractHierarchyCell = (JGraphAbstractHierarchyCell)localIterator.next();
      if ((paramJGraphAbstractHierarchyCell.isVertex()) && (localJGraphAbstractHierarchyCell.isVertex())) {
        i++;
      } else if ((paramJGraphAbstractHierarchyCell.isEdge()) && (localJGraphAbstractHierarchyCell.isEdge())) {
        i += 8;
      } else {
        i += 2;
      }
    }
    return i;
  }
  
  private int medianXValue(Object[] paramArrayOfObject, int paramInt)
  {
    if (paramArrayOfObject.length == 0) {
      return 0;
    }
    int[] arrayOfInt = new int[paramArrayOfObject.length];
    for (int i = 0; i < paramArrayOfObject.length; i++) {
      arrayOfInt[i] = ((JGraphAbstractHierarchyCell)paramArrayOfObject[i]).getGeneralPurposeVariable(paramInt);
    }
    Arrays.sort(arrayOfInt);
    if (paramArrayOfObject.length % 2 == 1) {
      return arrayOfInt[(paramArrayOfObject.length / 2)];
    }
    i = paramArrayOfObject.length / 2;
    int j = arrayOfInt[(i - 1)];
    int k = arrayOfInt[i];
    return (j + k) / 2;
  }
  
  private void initialise(JGraphHierarchyModel paramJGraphHierarchyModel) {}
  
  private void initialCoords(JGraphFacade paramJGraphFacade, JGraphHierarchyModel paramJGraphHierarchyModel)
  {
    calculateWidestRank(paramJGraphFacade, paramJGraphHierarchyModel);
    for (int i = this.widestRank; i >= 0; i--) {
      if (i < paramJGraphHierarchyModel.maxRank) {
        rankCoordinates(i, paramJGraphFacade, paramJGraphHierarchyModel);
      }
    }
    for (i = this.widestRank + 1; i <= paramJGraphHierarchyModel.maxRank; i++) {
      if (i > 0) {
        rankCoordinates(i, paramJGraphFacade, paramJGraphHierarchyModel);
      }
    }
  }
  
  protected void rankCoordinates(int paramInt, JGraphFacade paramJGraphFacade, JGraphHierarchyModel paramJGraphHierarchyModel)
  {
    JGraphHierarchyRank localJGraphHierarchyRank = (JGraphHierarchyRank)paramJGraphHierarchyModel.ranks.get(new Integer(paramInt));
    double d1 = (this.widestRankValue - this.rankWidths[paramInt]) / (localJGraphHierarchyRank.size() + 1);
    double d2 = this.intraCellSpacing + d1;
    if (d1 * (localJGraphHierarchyRank.size() + 1) + this.rankWidths[paramInt] > this.widestRankValue) {
      d2 = this.intraCellSpacing;
    }
    double d3 = 0.0D;
    double d4 = this.initialX + d1;
    Iterator localIterator = localJGraphHierarchyRank.iterator();
    int i = 0;
    while (localIterator.hasNext())
    {
      JGraphAbstractHierarchyCell localJGraphAbstractHierarchyCell = (JGraphAbstractHierarchyCell)localIterator.next();
      Object localObject;
      if (localJGraphAbstractHierarchyCell.isVertex())
      {
        localObject = (JGraphHierarchyNode)localJGraphAbstractHierarchyCell;
        Rectangle2D localRectangle2D = paramJGraphFacade.getBounds(((JGraphHierarchyNode)localObject).cell);
        if (localRectangle2D != null)
        {
          if ((this.orientation == 1) || (this.orientation == 5))
          {
            localJGraphAbstractHierarchyCell.width = localRectangle2D.getWidth();
            localJGraphAbstractHierarchyCell.height = localRectangle2D.getHeight();
          }
          else
          {
            localJGraphAbstractHierarchyCell.width = localRectangle2D.getHeight();
            localJGraphAbstractHierarchyCell.height = localRectangle2D.getWidth();
          }
        }
        else {
          i = 1;
        }
        d3 = Math.max(d3, localJGraphAbstractHierarchyCell.height);
      }
      else if (localJGraphAbstractHierarchyCell.isEdge())
      {
        localObject = (JGraphHierarchyEdge)localJGraphAbstractHierarchyCell;
        int j = 1;
        if (((JGraphHierarchyEdge)localObject).edges != null) {
          j = ((JGraphHierarchyEdge)localObject).edges.size();
        } else {
          logger.info("edge.edges is null");
        }
        localJGraphAbstractHierarchyCell.width = ((j - 1) * this.parallelEdgeSpacing);
      }
      d4 += localJGraphAbstractHierarchyCell.width / 2.0D;
      localJGraphAbstractHierarchyCell.setX(paramInt, d4);
      localJGraphAbstractHierarchyCell.setGeneralPurposeVariable(paramInt, (int)d4);
      d4 += localJGraphAbstractHierarchyCell.width / 2.0D;
      d4 += d2;
    }
    if (i == 1) {
      logger.info("At least one cell has no bounds");
    }
  }
  
  protected void calculateWidestRank(JGraphFacade paramJGraphFacade, JGraphHierarchyModel paramJGraphHierarchyModel)
  {
    double d1 = -this.interRankCellSpacing;
    double d2 = 0.0D;
    this.rankWidths = new double[paramJGraphHierarchyModel.maxRank + 1];
    this.rankY = new double[paramJGraphHierarchyModel.maxRank + 1];
    for (int i = paramJGraphHierarchyModel.maxRank; i >= 0; i--)
    {
      double d3 = 0.0D;
      JGraphHierarchyRank localJGraphHierarchyRank = (JGraphHierarchyRank)paramJGraphHierarchyModel.ranks.get(new Integer(i));
      double d4 = this.initialX;
      Iterator localIterator = localJGraphHierarchyRank.iterator();
      int j = 0;
      while (localIterator.hasNext())
      {
        JGraphAbstractHierarchyCell localJGraphAbstractHierarchyCell1 = (JGraphAbstractHierarchyCell)localIterator.next();
        Object localObject;
        if (localJGraphAbstractHierarchyCell1.isVertex())
        {
          localObject = (JGraphHierarchyNode)localJGraphAbstractHierarchyCell1;
          Rectangle2D localRectangle2D = paramJGraphFacade.getBounds(((JGraphHierarchyNode)localObject).cell);
          if (localRectangle2D != null)
          {
            if ((this.orientation == 1) || (this.orientation == 5))
            {
              localJGraphAbstractHierarchyCell1.width = localRectangle2D.getWidth();
              localJGraphAbstractHierarchyCell1.height = localRectangle2D.getHeight();
            }
            else
            {
              localJGraphAbstractHierarchyCell1.width = localRectangle2D.getHeight();
              localJGraphAbstractHierarchyCell1.height = localRectangle2D.getWidth();
            }
          }
          else {
            j = 1;
          }
          d3 = Math.max(d3, localJGraphAbstractHierarchyCell1.height);
        }
        else if (localJGraphAbstractHierarchyCell1.isEdge())
        {
          localObject = (JGraphHierarchyEdge)localJGraphAbstractHierarchyCell1;
          int k = 1;
          if (((JGraphHierarchyEdge)localObject).edges != null) {
            k = ((JGraphHierarchyEdge)localObject).edges.size();
          } else {
            logger.info("edge.edges is null");
          }
          localJGraphAbstractHierarchyCell1.width = ((k - 1) * this.parallelEdgeSpacing);
        }
        d4 += localJGraphAbstractHierarchyCell1.width / 2.0D;
        localJGraphAbstractHierarchyCell1.setX(i, d4);
        localJGraphAbstractHierarchyCell1.setGeneralPurposeVariable(i, (int)d4);
        d4 += localJGraphAbstractHierarchyCell1.width / 2.0D;
        d4 += this.intraCellSpacing;
        if (d4 > this.widestRankValue)
        {
          this.widestRankValue = d4;
          this.widestRank = i;
        }
        this.rankWidths[i] = d4;
      }
      if (j == 1) {
        logger.info("At least one cell has no bounds");
      }
      this.rankY[i] = d1;
      double d5 = d3 / 2.0D + d2 / 2.0D + this.interRankCellSpacing;
      d2 = d3;
      if ((this.orientation == 1) || (this.orientation == 7)) {
        d1 += d5;
      } else {
        d1 -= d5;
      }
      localIterator = localJGraphHierarchyRank.iterator();
      while (localIterator.hasNext())
      {
        JGraphAbstractHierarchyCell localJGraphAbstractHierarchyCell2 = (JGraphAbstractHierarchyCell)localIterator.next();
        localJGraphAbstractHierarchyCell2.setY(i, d1);
      }
    }
  }
  
  private void setCellLocations(JGraphFacade paramJGraphFacade, JGraphHierarchyModel paramJGraphHierarchyModel)
  {
    double d1 = 0.0D;
    ArrayList localArrayList1 = new ArrayList();
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < paramJGraphHierarchyModel.ranks.size(); j++)
      {
        JGraphHierarchyRank localJGraphHierarchyRank = (JGraphHierarchyRank)paramJGraphHierarchyModel.ranks.get(new Integer(j));
        Iterator localIterator = localJGraphHierarchyRank.iterator();
        while (localIterator.hasNext())
        {
          JGraphAbstractHierarchyCell localJGraphAbstractHierarchyCell = (JGraphAbstractHierarchyCell)localIterator.next();
          Object localObject1;
          Object localObject2;
          double d2;
          if ((i == 0) && (localJGraphAbstractHierarchyCell.isVertex()))
          {
            localObject1 = (JGraphHierarchyNode)localJGraphAbstractHierarchyCell;
            localObject2 = ((JGraphHierarchyNode)localObject1).cell;
            localArrayList1.add(localObject2);
            d2 = localObject1.x[0] - ((JGraphHierarchyNode)localObject1).width / 2.0D;
            double d3 = localObject1.y[0] - ((JGraphHierarchyNode)localObject1).height / 2.0D;
            if ((this.orientation == 1) || (this.orientation == 5)) {
              paramJGraphFacade.setLocation(localObject2, d2, d3);
            } else {
              paramJGraphFacade.setLocation(localObject2, d3, d2);
            }
            this.limitX = Math.max(this.limitX, d2 + ((JGraphHierarchyNode)localObject1).width);
            if (d2 + 1.0D < this.initialX) {
              d1 = this.initialX - d2;
            }
          }
          else if ((i == 1) && (localJGraphAbstractHierarchyCell.isEdge()))
          {
            localObject1 = (JGraphHierarchyEdge)localJGraphAbstractHierarchyCell;
            localObject2 = ((JGraphHierarchyEdge)localObject1).edges.iterator();
            d2 = 0.0D;
            if (localObject1.temp[0] != 101207)
            {
              while (((Iterator)localObject2).hasNext())
              {
                Object localObject3 = ((Iterator)localObject2).next();
                List localList = paramJGraphFacade.getPoints(localObject3);
                ArrayList localArrayList2 = new ArrayList(((JGraphHierarchyEdge)localObject1).x.length + 2);
                localArrayList2.add(localList.get(0));
                int k;
                double d4;
                if (((JGraphHierarchyEdge)localObject1).isReversed())
                {
                  for (k = 0; k < ((JGraphHierarchyEdge)localObject1).x.length; k++)
                  {
                    d4 = localObject1.x[k] + d2 + d1;
                    if ((this.orientation == 1) || (this.orientation == 5)) {
                      localArrayList2.add(new Point2D.Double(d4, localObject1.y[k]));
                    } else {
                      localArrayList2.add(new Point2D.Double(localObject1.y[k], d4));
                    }
                    this.limitX = Math.max(this.limitX, d4);
                  }
                  processReversedEdge((JGraphHierarchyEdge)localObject1, localObject3);
                }
                else
                {
                  for (k = ((JGraphHierarchyEdge)localObject1).x.length - 1; k >= 0; k--)
                  {
                    d4 = localObject1.x[k] + d2 + d1;
                    if ((this.orientation == 1) || (this.orientation == 5)) {
                      localArrayList2.add(new Point2D.Double(d4, localObject1.y[k]));
                    } else {
                      localArrayList2.add(new Point2D.Double(localObject1.y[k], d4));
                    }
                    this.limitX = Math.max(this.limitX, d4);
                  }
                }
                localArrayList2.add(localList.get(localList.size() - 1));
                paramJGraphFacade.setPoints(localObject3, localArrayList2);
                paramJGraphFacade.disableRouting(localObject3);
                if (d2 == 0.0D) {
                  d2 = this.parallelEdgeSpacing;
                } else if (d2 > 0.0D) {
                  d2 = -d2;
                } else {
                  d2 = -d2 + this.parallelEdgeSpacing;
                }
              }
              ((JGraphHierarchyEdge)localObject1).temp[0] = 101207;
            }
          }
        }
      }
    }
    if (d1 >= 1.0D) {
      if ((this.orientation == 1) || (this.orientation == 5)) {
        paramJGraphFacade.translateCells(localArrayList1, d1, 0.0D);
      } else if ((this.orientation == 3) || (this.orientation == 7)) {
        paramJGraphFacade.translateCells(localArrayList1, 0.0D, d1);
      }
    }
    this.limitX += d1;
  }
  
  private void processReversedEdge(JGraphHierarchyEdge paramJGraphHierarchyEdge, Object paramObject) {}
  
  public double getInterRankCellSpacing()
  {
    return this.interRankCellSpacing;
  }
  
  public void setInterRankCellSpacing(double paramDouble)
  {
    this.interRankCellSpacing = paramDouble;
  }
  
  public double getIntraCellSpacing()
  {
    return this.intraCellSpacing;
  }
  
  public void setIntraCellSpacing(double paramDouble)
  {
    this.intraCellSpacing = paramDouble;
  }
  
  public int getOrientation()
  {
    return this.orientation;
  }
  
  public void setOrientation(int paramInt)
  {
    this.orientation = paramInt;
  }
  
  public double getLimitX()
  {
    return this.limitX;
  }
  
  public void setLimitX(double paramDouble)
  {
    this.limitX = paramDouble;
  }
  
  public boolean isFineTuning()
  {
    return this.fineTuning;
  }
  
  public void setFineTuning(boolean paramBoolean)
  {
    this.fineTuning = paramBoolean;
  }
  
  public boolean isCompactLayout()
  {
    return this.compactLayout;
  }
  
  public void setCompactLayout(boolean paramBoolean)
  {
    this.compactLayout = paramBoolean;
  }
  
  public void setLoggerLevel(Level paramLevel)
  {
    try
    {
      logger.setLevel(paramLevel);
    }
    catch (SecurityException localSecurityException) {}
  }
  
  protected class AreaSpatialCache
    extends Rectangle2D.Double
  {
    public Set cells = new HashSet();
    
    protected AreaSpatialCache() {}
  }
  
  protected class WeightedCellSorter
    implements Comparable
  {
    public int weightedValue = 0;
    public boolean visited = false;
    public int rankIndex;
    public JGraphAbstractHierarchyCell cell = null;
    
    public WeightedCellSorter()
    {
      this(null, 0);
    }
    
    public WeightedCellSorter(JGraphAbstractHierarchyCell paramJGraphAbstractHierarchyCell, int paramInt)
    {
      this.cell = paramJGraphAbstractHierarchyCell;
      this.weightedValue = paramInt;
    }
    
    public int compareTo(Object paramObject)
    {
      if ((paramObject instanceof WeightedCellSorter)) {
        return Double.compare(((WeightedCellSorter)paramObject).weightedValue, this.weightedValue);
      }
      return 0;
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/layout/hierarchical/JGraphCoordinateAssignment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */