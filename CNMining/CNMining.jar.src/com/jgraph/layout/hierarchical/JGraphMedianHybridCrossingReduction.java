package com.jgraph.layout.hierarchical;

import com.jgraph.layout.JGraphFacade;
import com.jgraph.layout.JGraphLayout.Stoppable;
import com.jgraph.layout.JGraphLayoutProgress;
import com.jgraph.layout.hierarchical.model.JGraphAbstractHierarchyCell;
import com.jgraph.layout.hierarchical.model.JGraphHierarchyModel;
import com.jgraph.layout.hierarchical.model.JGraphHierarchyRank;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JGraphMedianHybridCrossingReduction
  implements JGraphHierarchicalLayoutStep, JGraphLayout.Stoppable
{
  protected int maxIterations = 24;
  protected Object[][] nestedBestRanks = (Object[][])null;
  protected int currentBestCrossings = 0;
  protected int iterationsWithoutImprovement = 0;
  protected int maxNoImprovementIterations = 2;
  protected JGraphLayoutProgress progress = new JGraphLayoutProgress();
  
  public JGraphHierarchyModel run(JGraphFacade paramJGraphFacade, JGraphHierarchyModel paramJGraphHierarchyModel)
  {
    if (paramJGraphHierarchyModel == null) {
      return null;
    }
    this.nestedBestRanks = new Object[paramJGraphHierarchyModel.ranks.size()][];
    for (int i = 0; i < this.nestedBestRanks.length; i++)
    {
      JGraphHierarchyRank localJGraphHierarchyRank1 = (JGraphHierarchyRank)paramJGraphHierarchyModel.ranks.get(new Integer(i));
      this.nestedBestRanks[i] = localJGraphHierarchyRank1.toArray();
    }
    this.progress.reset(this.maxIterations);
    this.iterationsWithoutImprovement = 0;
    this.currentBestCrossings = calculateCrossings(paramJGraphHierarchyModel);
    Object localObject;
    for (i = 0; (i < this.maxIterations) && (!this.progress.isStopped()) && (this.iterationsWithoutImprovement < this.maxNoImprovementIterations); i++)
    {
      this.progress.setProgress(i);
      weightedMedian(i, paramJGraphHierarchyModel);
      transpose(i, paramJGraphHierarchyModel);
      int j = calculateCrossings(paramJGraphHierarchyModel);
      JGraphHierarchyRank localJGraphHierarchyRank2;
      int n;
      JGraphAbstractHierarchyCell localJGraphAbstractHierarchyCell;
      if (j < this.currentBestCrossings)
      {
        this.currentBestCrossings = j;
        this.iterationsWithoutImprovement = 0;
        for (k = 0; k < this.nestedBestRanks.length; k++)
        {
          localJGraphHierarchyRank2 = (JGraphHierarchyRank)paramJGraphHierarchyModel.ranks.get(new Integer(k));
          localObject = localJGraphHierarchyRank2.iterator();
          for (n = 0; n < localJGraphHierarchyRank2.size(); n++)
          {
            localJGraphAbstractHierarchyCell = (JGraphAbstractHierarchyCell)((Iterator)localObject).next();
            this.nestedBestRanks[k][localJGraphAbstractHierarchyCell.getGeneralPurposeVariable(k)] = localJGraphAbstractHierarchyCell;
          }
        }
      }
      else
      {
        this.iterationsWithoutImprovement += 1;
        for (k = 0; k < this.nestedBestRanks.length; k++)
        {
          localJGraphHierarchyRank2 = (JGraphHierarchyRank)paramJGraphHierarchyModel.ranks.get(new Integer(k));
          localObject = localJGraphHierarchyRank2.iterator();
          for (n = 0; n < localJGraphHierarchyRank2.size(); n++)
          {
            localJGraphAbstractHierarchyCell = (JGraphAbstractHierarchyCell)((Iterator)localObject).next();
            localJGraphAbstractHierarchyCell.setGeneralPurposeVariable(k, n);
          }
        }
      }
      if (this.currentBestCrossings == 0) {
        break;
      }
    }
    LinkedHashMap localLinkedHashMap = new LinkedHashMap(paramJGraphHierarchyModel.maxRank + 1);
    JGraphHierarchyRank[] arrayOfJGraphHierarchyRank = new JGraphHierarchyRank[paramJGraphHierarchyModel.maxRank + 1];
    for (int k = 0; k < paramJGraphHierarchyModel.maxRank + 1; k++)
    {
      arrayOfJGraphHierarchyRank[k] = new JGraphHierarchyRank();
      localLinkedHashMap.put(new Integer(k), arrayOfJGraphHierarchyRank[k]);
    }
    for (k = 0; k < this.nestedBestRanks.length; k++) {
      for (int m = 0; m < this.nestedBestRanks[k].length; m++)
      {
        localObject = (JGraphAbstractHierarchyCell)this.nestedBestRanks[k][m];
        arrayOfJGraphHierarchyRank[k].add(localObject);
      }
    }
    paramJGraphHierarchyModel.ranks = localLinkedHashMap;
    return paramJGraphHierarchyModel;
  }
  
  private int calculateCrossings(JGraphHierarchyModel paramJGraphHierarchyModel)
  {
    int i = paramJGraphHierarchyModel.ranks.size();
    int j = 0;
    for (int k = 1; k < i; k++) {
      j += calculateRankCrossing(k, paramJGraphHierarchyModel);
    }
    return j;
  }
  
  protected int calculateRankCrossing(int paramInt, JGraphHierarchyModel paramJGraphHierarchyModel)
  {
    int i = 0;
    JGraphHierarchyRank localJGraphHierarchyRank1 = (JGraphHierarchyRank)paramJGraphHierarchyModel.ranks.get(new Integer(paramInt));
    JGraphHierarchyRank localJGraphHierarchyRank2 = (JGraphHierarchyRank)paramJGraphHierarchyModel.ranks.get(new Integer(paramInt - 1));
    int j = localJGraphHierarchyRank1.size();
    int k = localJGraphHierarchyRank2.size();
    int[][] arrayOfInt = new int[j][k];
    Iterator localIterator1 = localJGraphHierarchyRank1.iterator();
    int n;
    while (localIterator1.hasNext())
    {
      JGraphAbstractHierarchyCell localJGraphAbstractHierarchyCell1 = (JGraphAbstractHierarchyCell)localIterator1.next();
      n = localJGraphAbstractHierarchyCell1.getGeneralPurposeVariable(paramInt);
      List localList = localJGraphAbstractHierarchyCell1.getPreviousLayerConnectedCells(paramInt);
      Iterator localIterator2 = localList.iterator();
      while (localIterator2.hasNext())
      {
        JGraphAbstractHierarchyCell localJGraphAbstractHierarchyCell2 = (JGraphAbstractHierarchyCell)localIterator2.next();
        int i3 = localJGraphAbstractHierarchyCell2.getGeneralPurposeVariable(paramInt - 1);
        if ((n < j) && (i3 >= k)) {}
        arrayOfInt[n][i3] = 201207;
      }
    }
    for (int m = 0; m < j; m++) {
      for (n = 0; n < k; n++) {
        if (arrayOfInt[m][n] == 201207)
        {
          int i2;
          for (int i1 = m + 1; i1 < j; i1++) {
            for (i2 = 0; i2 < n; i2++) {
              if (arrayOfInt[i1][i2] == 201207) {
                i++;
              }
            }
          }
          for (i1 = 0; i1 < m; i1++) {
            for (i2 = n + 1; i2 < k; i2++) {
              if (arrayOfInt[i1][i2] == 201207) {
                i++;
              }
            }
          }
        }
      }
    }
    return i / 2;
  }
  
  private void transpose(int paramInt, JGraphHierarchyModel paramJGraphHierarchyModel)
  {
    int i = 1;
    int j = 0;
    int k = 10;
    while ((i != 0) && (j++ < k))
    {
      int m = 0;
      if ((paramInt % 2 == 1) && (j % 2 == 1)) {
        m = 1;
      }
      i = 0;
      for (int n = 0; n < paramJGraphHierarchyModel.ranks.size(); n++)
      {
        JGraphHierarchyRank localJGraphHierarchyRank = (JGraphHierarchyRank)paramJGraphHierarchyModel.ranks.get(new Integer(n));
        JGraphAbstractHierarchyCell[] arrayOfJGraphAbstractHierarchyCell = new JGraphAbstractHierarchyCell[localJGraphHierarchyRank.size()];
        Iterator localIterator = localJGraphHierarchyRank.iterator();
        for (int i1 = 0; i1 < arrayOfJGraphAbstractHierarchyCell.length; i1++)
        {
          localObject2 = (JGraphAbstractHierarchyCell)localIterator.next();
          arrayOfJGraphAbstractHierarchyCell[localObject2.getGeneralPurposeVariable(n)] = localObject2;
        }
        Object localObject1 = null;
        Object localObject2 = null;
        Object localObject3 = null;
        Object localObject4 = null;
        Object localObject5 = null;
        Object localObject6 = null;
        Object localObject7 = null;
        Object localObject8 = null;
        Object localObject9 = null;
        Object localObject10 = null;
        for (int i2 = 0; i2 < localJGraphHierarchyRank.size() - 1; i2++)
        {
          if (i2 == 0)
          {
            localObject9 = arrayOfJGraphAbstractHierarchyCell[i2];
            localObject1 = ((JGraphAbstractHierarchyCell)localObject9).getNextLayerConnectedCells(n);
            localObject2 = ((JGraphAbstractHierarchyCell)localObject9).getPreviousLayerConnectedCells(n);
            localObject5 = new int[((List)localObject1).size()];
            localObject6 = new int[((List)localObject2).size()];
            for (i3 = 0; i3 < localObject5.length; i3++) {
              localObject5[i3] = ((JGraphAbstractHierarchyCell)((List)localObject1).get(i3)).getGeneralPurposeVariable(n + 1);
            }
            for (i3 = 0; i3 < localObject6.length; i3++) {
              localObject6[i3] = ((JGraphAbstractHierarchyCell)((List)localObject2).get(i3)).getGeneralPurposeVariable(n - 1);
            }
          }
          else
          {
            localObject1 = localObject3;
            localObject2 = localObject4;
            localObject5 = localObject7;
            localObject6 = localObject8;
            localObject9 = localObject10;
          }
          localObject10 = arrayOfJGraphAbstractHierarchyCell[(i2 + 1)];
          localObject3 = ((JGraphAbstractHierarchyCell)localObject10).getNextLayerConnectedCells(n);
          localObject4 = ((JGraphAbstractHierarchyCell)localObject10).getPreviousLayerConnectedCells(n);
          localObject7 = new int[((List)localObject3).size()];
          localObject8 = new int[((List)localObject4).size()];
          for (int i3 = 0; i3 < localObject7.length; i3++) {
            localObject7[i3] = ((JGraphAbstractHierarchyCell)((List)localObject3).get(i3)).getGeneralPurposeVariable(n + 1);
          }
          for (i3 = 0; i3 < localObject8.length; i3++) {
            localObject8[i3] = ((JGraphAbstractHierarchyCell)((List)localObject4).get(i3)).getGeneralPurposeVariable(n - 1);
          }
          i3 = 0;
          int i4 = 0;
          int i6;
          for (int i5 = 0; i5 < localObject5.length; i5++) {
            for (i6 = 0; i6 < localObject7.length; i6++)
            {
              if (localObject5[i5] > localObject7[i6]) {
                i3++;
              }
              if (localObject5[i5] < localObject7[i6]) {
                i4++;
              }
            }
          }
          for (i5 = 0; i5 < localObject6.length; i5++) {
            for (i6 = 0; i6 < localObject8.length; i6++)
            {
              if (localObject6[i5] > localObject8[i6]) {
                i3++;
              }
              if (localObject6[i5] < localObject8[i6]) {
                i4++;
              }
            }
          }
          if ((i4 < i3) || ((i4 == i3) && (m != 0)))
          {
            i5 = ((JGraphAbstractHierarchyCell)localObject9).getGeneralPurposeVariable(n);
            ((JGraphAbstractHierarchyCell)localObject9).setGeneralPurposeVariable(n, ((JGraphAbstractHierarchyCell)localObject10).getGeneralPurposeVariable(n));
            ((JGraphAbstractHierarchyCell)localObject10).setGeneralPurposeVariable(n, i5);
            localObject3 = localObject1;
            localObject4 = localObject2;
            localObject7 = localObject5;
            localObject8 = localObject6;
            localObject10 = localObject9;
            if (m == 0) {
              i = 1;
            }
          }
        }
      }
    }
  }
  
  private void weightedMedian(int paramInt, JGraphHierarchyModel paramJGraphHierarchyModel)
  {
    boolean bool = paramInt % 2 == 0;
    int i;
    if (bool) {
      for (i = paramJGraphHierarchyModel.maxRank - 1; i >= 0; i--) {
        medianRank(i, bool);
      }
    } else {
      for (i = 1; i < paramJGraphHierarchyModel.maxRank; i++) {
        medianRank(i, bool);
      }
    }
  }
  
  private void medianRank(int paramInt, boolean paramBoolean)
  {
    int i = this.nestedBestRanks[paramInt].length;
    MedianCellSorter[] arrayOfMedianCellSorter = new MedianCellSorter[i];
    for (int j = 0; j < i; j++)
    {
      JGraphAbstractHierarchyCell localJGraphAbstractHierarchyCell = (JGraphAbstractHierarchyCell)this.nestedBestRanks[paramInt][j];
      arrayOfMedianCellSorter[j] = new MedianCellSorter();
      arrayOfMedianCellSorter[j].cell = localJGraphAbstractHierarchyCell;
      arrayOfMedianCellSorter[j].nudge = (!paramBoolean);
      List localList;
      if (paramBoolean) {
        localList = localJGraphAbstractHierarchyCell.getNextLayerConnectedCells(paramInt);
      } else {
        localList = localJGraphAbstractHierarchyCell.getPreviousLayerConnectedCells(paramInt);
      }
      int k;
      if (paramBoolean) {
        k = paramInt + 1;
      } else {
        k = paramInt - 1;
      }
      if ((localList != null) && (localList.size() != 0)) {
        arrayOfMedianCellSorter[j].medianValue = medianValue(localList, k);
      } else {
        arrayOfMedianCellSorter[j].medianValue = -1.0D;
      }
    }
    Arrays.sort(arrayOfMedianCellSorter);
    for (j = 0; j < i; j++) {
      arrayOfMedianCellSorter[j].cell.setGeneralPurposeVariable(paramInt, j);
    }
  }
  
  private double medianValue(Collection paramCollection, int paramInt)
  {
    double[] arrayOfDouble = new double[paramCollection.size()];
    int i = 0;
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext()) {
      arrayOfDouble[(i++)] = ((JGraphAbstractHierarchyCell)localIterator.next()).getGeneralPurposeVariable(paramInt);
    }
    Arrays.sort(arrayOfDouble);
    if (i % 2 == 1) {
      return arrayOfDouble[(i / 2)];
    }
    if (i == 2) {
      return (arrayOfDouble[0] + arrayOfDouble[1]) / 2.0D;
    }
    int j = i / 2;
    double d1 = arrayOfDouble[(j - 1)] - arrayOfDouble[0];
    double d2 = arrayOfDouble[(i - 1)] - arrayOfDouble[j];
    return (arrayOfDouble[(j - 1)] * d2 + arrayOfDouble[j] * d1) / (d1 + d2);
  }
  
  public JGraphLayoutProgress getProgress()
  {
    return this.progress;
  }
  
  protected class MedianCellSorter
    implements Comparable
  {
    public double medianValue = 0.0D;
    public boolean nudge = false;
    JGraphAbstractHierarchyCell cell = null;
    
    protected MedianCellSorter() {}
    
    public int compareTo(Object paramObject)
    {
      if ((paramObject instanceof MedianCellSorter))
      {
        if (this.medianValue < ((MedianCellSorter)paramObject).medianValue) {
          return -1;
        }
        if (this.medianValue > ((MedianCellSorter)paramObject).medianValue) {
          return 1;
        }
        if (this.nudge) {
          return -1;
        }
        return 1;
      }
      return 0;
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/layout/hierarchical/JGraphMedianHybridCrossingReduction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */