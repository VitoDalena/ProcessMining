package com.jgraph.layout.organic;

import com.jgraph.layout.JGraphFacade;
import com.jgraph.layout.JGraphLayout;
import com.jgraph.layout.JGraphLayout.Stoppable;
import com.jgraph.layout.JGraphLayoutProgress;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JGraphOrganicLayout
  implements JGraphLayout, JGraphLayout.Stoppable
{
  protected boolean isOptimizeEdgeDistance = true;
  protected boolean isOptimizeEdgeCrossing = true;
  protected boolean isOptimizeEdgeLength = true;
  protected boolean isOptimizeBorderLine = true;
  protected boolean isOptimizeNodeDistribution = true;
  protected double minMoveRadius = 2.0D;
  protected double moveRadius;
  protected double initialMoveRadius = 0.0D;
  protected double radiusScaleFactor = 0.75D;
  protected double averageNodeArea = 0.0D;
  protected double fineTuningRadius = 40.0D;
  protected int maxIterations = 100;
  protected double edgeDistanceCostFactor = 4000.0D;
  protected double edgeCrossingCostFactor = 2000.0D;
  protected double nodeDistributionCostFactor = 300000.0D;
  protected double borderLineCostFactor = 5.0D;
  protected double edgeLengthCostFactor = 0.02D;
  protected double boundsX = 0.0D;
  protected double boundsY = 0.0D;
  protected double boundsWidth = 0.0D;
  protected double boundsHeight = 0.0D;
  protected int iteration;
  protected int triesPerCell = 8;
  protected double minDistanceLimit = 2.0D;
  protected double minDistanceLimitSquared;
  protected double maxDistanceLimit = 100.0D;
  protected double maxDistanceLimitSquared;
  protected int unchangedEnergyRoundCount;
  protected int unchangedEnergyRoundTermination = 5;
  protected boolean isDeterministic = true;
  protected boolean approxNodeDimensions = true;
  protected CellWrapper[] v;
  protected CellWrapper[] e;
  protected boolean isFineTuning = true;
  protected JGraphFacade facade;
  protected JGraphLayoutProgress progress = new JGraphLayoutProgress();
  private static Logger logger = Logger.getLogger("com.jgraph.layout.organic.JGraphOrganicLayout");
  
  public JGraphOrganicLayout() {}
  
  public JGraphOrganicLayout(Rectangle2D paramRectangle2D)
  {
    this.boundsX = paramRectangle2D.getX();
    this.boundsY = paramRectangle2D.getY();
    this.boundsWidth = paramRectangle2D.getWidth();
    this.boundsHeight = paramRectangle2D.getHeight();
  }
  
  public JGraphLayoutProgress getProgress()
  {
    return this.progress;
  }
  
  public void run(JGraphFacade paramJGraphFacade)
  {
    this.facade = paramJGraphFacade;
    boolean bool = this.facade.isDirected();
    this.facade.setDirected(false);
    Object[] arrayOfObject1 = paramJGraphFacade.getVertices().toArray();
    Object[] arrayOfObject2 = paramJGraphFacade.getEdges().toArray();
    Rectangle2D localRectangle2D = this.facade.getGraphBounds();
    double d2;
    double d3;
    if (this.averageNodeArea == 0.0D)
    {
      if ((this.boundsWidth == 0.0D) && (localRectangle2D != null))
      {
        this.boundsX = localRectangle2D.getX();
        this.boundsY = localRectangle2D.getY();
        this.boundsWidth = localRectangle2D.getWidth();
        this.boundsHeight = localRectangle2D.getHeight();
      }
    }
    else
    {
      double d1 = this.averageNodeArea * arrayOfObject1.length;
      d2 = Math.sqrt(d1);
      double d4;
      if (localRectangle2D != null)
      {
        d3 = localRectangle2D.getX() + localRectangle2D.getWidth() / 2.0D;
        d4 = localRectangle2D.getY() + localRectangle2D.getHeight() / 2.0D;
        this.boundsX = (d3 - d2 / 2.0D);
        this.boundsY = (d4 - d2 / 2.0D);
      }
      else
      {
        logger.info("facade.getGraphBounds() returned null");
        this.boundsX = 0.0D;
        this.boundsY = 0.0D;
      }
      this.boundsWidth = d2;
      this.boundsHeight = d2;
      if ((this.boundsX < 0.0D) || (this.boundsY < 0.0D))
      {
        d3 = Math.min(this.boundsX, this.boundsY);
        d4 = -d3;
        this.boundsX += d4;
        this.boundsY += d4;
      }
    }
    if (this.initialMoveRadius == 0.0D) {
      this.initialMoveRadius = (Math.max(this.boundsWidth, this.boundsHeight) / 2.0D);
    }
    this.moveRadius = this.initialMoveRadius;
    this.minDistanceLimitSquared = (this.minDistanceLimit * this.minDistanceLimit);
    this.maxDistanceLimitSquared = (this.maxDistanceLimit * this.maxDistanceLimit);
    this.unchangedEnergyRoundCount = 0;
    this.progress.reset(this.maxIterations);
    Hashtable localHashtable = new Hashtable();
    this.v = new CellWrapper[arrayOfObject1.length];
    for (int i = 0; i < arrayOfObject1.length; i++)
    {
      this.v[i] = new CellWrapper(arrayOfObject1[i]);
      localHashtable.put(arrayOfObject1[i], new Integer(i));
      localRectangle2D = paramJGraphFacade.getBounds(arrayOfObject1[i]);
      d2 = localRectangle2D.getWidth();
      d3 = localRectangle2D.getHeight();
      this.v[i].x = (localRectangle2D.getX() + d2 / 2.0D);
      this.v[i].y = (localRectangle2D.getY() + d3 / 2.0D);
      if (this.approxNodeDimensions)
      {
        this.v[i].radiusSquared = Math.min(d2, d3);
        this.v[i].radiusSquared *= this.v[i].radiusSquared;
      }
      else
      {
        this.v[i].radiusSquared = (d2 * d2);
        this.v[i].heightSquared = (d3 * d3);
      }
    }
    this.e = new CellWrapper[arrayOfObject2.length];
    for (i = 0; i < this.e.length; i++)
    {
      this.e[i] = new CellWrapper(arrayOfObject2[i]);
      Object localObject1 = paramJGraphFacade.getSource(arrayOfObject2[i]);
      Object localObject2 = paramJGraphFacade.getTarget(arrayOfObject2[i]);
      Integer localInteger1 = null;
      Integer localInteger2 = null;
      if (localObject1 != null) {
        localInteger1 = (Integer)localHashtable.get(localObject1);
      }
      if (localObject2 != null) {
        localInteger2 = (Integer)localHashtable.get(localObject2);
      }
      if (localInteger1 != null) {
        this.e[i].source = localInteger1.intValue();
      } else {
        this.e[i].source = -1;
      }
      if (localInteger2 != null) {
        this.e[i].target = localInteger2.intValue();
      } else {
        this.e[i].target = -1;
      }
    }
    for (i = 0; i < this.v.length; i++)
    {
      this.v[i].relevantEdges = getRelevantEdges(i);
      this.v[i].connectedEdges = getConnectedEdges(i);
    }
    for (this.iteration = 0; (this.iteration < this.maxIterations) && (!this.progress.isStopped()); this.iteration += 1)
    {
      this.progress.setProgress(this.iteration);
      performRound();
    }
    double[][] arrayOfDouble = new double[this.v.length][2];
    for (int j = 0; j < this.v.length; j++)
    {
      arrayOfObject1[j] = this.v[j].cell;
      localRectangle2D = paramJGraphFacade.getBounds(arrayOfObject1[j]);
      arrayOfDouble[j][0] = (this.v[j].x - localRectangle2D.getWidth() / 2.0D);
      arrayOfDouble[j][1] = (this.v[j].y - localRectangle2D.getHeight() / 2.0D);
    }
    paramJGraphFacade.setLocations(arrayOfObject1, arrayOfDouble);
    this.facade.setDirected(bool);
  }
  
  protected void performRound()
  {
    int[] arrayOfInt = null;
    if (!this.isDeterministic)
    {
      arrayOfInt = new int[this.v.length];
      arrayOfInt = createPermutation(this.v.length);
    }
    int i = 0;
    for (int j = 0; j < this.v.length; j++)
    {
      int k;
      if (arrayOfInt == null) {
        k = j;
      } else {
        k = arrayOfInt[j];
      }
      double d2 = getNodeDistribution(k);
      double d3 = getEdgeDistanceFromNode(k);
      d3 += getEdgeDistanceAffectedNodes(k);
      double d4 = getEdgeCrossingAffectedEdges(k);
      double d5 = getBorderline(k);
      double d6 = getEdgeLengthAffectedEdges(k);
      double d7 = getAdditionFactorsEnergy(k);
      double d8 = 0.0D;
      if (!this.isDeterministic) {
        d8 = Math.random() * 2.0D * 3.141592653589793D;
      }
      for (int m = 0; m < this.triesPerCell; m++)
      {
        if (this.progress.isStopped()) {
          return;
        }
        double d9 = m * (6.283185307179586D / this.triesPerCell);
        d9 += d8;
        double d10 = this.moveRadius * Math.cos(d9);
        double d11 = this.moveRadius * Math.sin(d9);
        double d12 = this.v[k].x;
        double d13 = this.v[k].y;
        this.v[k].x += d10;
        this.v[k].y += d11;
        double d14 = calcEnergyDelta(k, d2, d3, d4, d5, d6, d7);
        if (d14 < 0.0D)
        {
          i = 1;
          break;
        }
        this.v[k].x = d12;
        this.v[k].y = d13;
      }
    }
    if (i != 0)
    {
      this.unchangedEnergyRoundCount = 0;
    }
    else
    {
      this.unchangedEnergyRoundCount += 1;
      this.moveRadius /= 2.0D;
    }
    if (this.unchangedEnergyRoundCount >= this.unchangedEnergyRoundTermination) {
      this.iteration = this.maxIterations;
    }
    double d1 = this.moveRadius * this.radiusScaleFactor;
    if (this.moveRadius - d1 < this.minMoveRadius) {
      d1 = this.moveRadius - this.minMoveRadius;
    }
    if (d1 <= this.minMoveRadius) {
      this.iteration = this.maxIterations;
    }
    if (d1 < this.fineTuningRadius) {
      this.isFineTuning = true;
    }
    this.moveRadius = d1;
  }
  
  protected double calcEnergyDelta(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6)
  {
    double d = 0.0D;
    d += getNodeDistribution(paramInt) * 2.0D;
    d -= paramDouble1 * 2.0D;
    d += getBorderline(paramInt);
    d -= paramDouble4;
    d += getEdgeDistanceFromNode(paramInt);
    d += getEdgeDistanceAffectedNodes(paramInt);
    d -= paramDouble2;
    d -= paramDouble5;
    d += getEdgeLengthAffectedEdges(paramInt);
    d -= paramDouble3;
    d += getEdgeCrossingAffectedEdges(paramInt);
    d -= paramDouble6;
    d += getAdditionFactorsEnergy(paramInt);
    return d;
  }
  
  protected int[] createPermutation(int paramInt)
  {
    int[] arrayOfInt = new int[paramInt];
    for (int i = 0; i < arrayOfInt.length; i++) {
      arrayOfInt[i] = i;
    }
    for (i = 0; i < arrayOfInt.length; i++)
    {
      int j = arrayOfInt[i];
      int k = (int)(Math.random() * (paramInt - 1));
      arrayOfInt[i] = arrayOfInt[k];
      arrayOfInt[k] = j;
    }
    return arrayOfInt;
  }
  
  protected double getNodeDistribution(int paramInt)
  {
    double d1 = 0.0D;
    if (this.isOptimizeNodeDistribution == true)
    {
      int i;
      double d2;
      double d3;
      double d4;
      if (this.approxNodeDimensions) {
        for (i = 0; i < this.v.length; i++) {
          if (paramInt != i)
          {
            d2 = this.v[paramInt].x - this.v[i].x;
            d3 = this.v[paramInt].y - this.v[i].y;
            d4 = d2 * d2 + d3 * d3;
            d4 -= this.v[paramInt].radiusSquared;
            d4 -= this.v[i].radiusSquared;
            if (d4 < this.minDistanceLimitSquared) {
              d4 = this.minDistanceLimitSquared;
            }
            d1 += this.nodeDistributionCostFactor / d4;
          }
        }
      } else {
        for (i = 0; i < this.v.length; i++) {
          if (paramInt != i)
          {
            d2 = this.v[paramInt].x - this.v[i].x;
            d3 = this.v[paramInt].y - this.v[i].y;
            d4 = d2 * d2 + d3 * d3;
            d4 -= this.v[paramInt].radiusSquared;
            d4 -= this.v[i].radiusSquared;
            if (d4 < this.minDistanceLimitSquared) {
              d4 = this.minDistanceLimitSquared;
            }
            d1 += this.nodeDistributionCostFactor / d4;
          }
        }
      }
    }
    return d1;
  }
  
  protected double getBorderline(int paramInt)
  {
    double d1 = 0.0D;
    if (this.isOptimizeBorderLine)
    {
      double d2 = this.v[paramInt].x - this.boundsX;
      if (d2 < this.minDistanceLimit) {
        d2 = this.minDistanceLimit;
      }
      double d3 = this.v[paramInt].y - this.boundsY;
      if (d3 < this.minDistanceLimit) {
        d3 = this.minDistanceLimit;
      }
      double d4 = this.boundsX + this.boundsWidth - this.v[paramInt].x;
      if (d4 < this.minDistanceLimit) {
        d4 = this.minDistanceLimit;
      }
      double d5 = this.boundsY + this.boundsHeight - this.v[paramInt].y;
      if (d5 < this.minDistanceLimit) {
        d5 = this.minDistanceLimit;
      }
      d1 += this.borderLineCostFactor * (1000000.0D / (d3 * d3) + 1000000.0D / (d2 * d2) + 1000000.0D / (d5 * d5) + 1000000.0D / (d4 * d4));
    }
    return d1;
  }
  
  protected double getEdgeLengthAffectedEdges(int paramInt)
  {
    double d = 0.0D;
    for (int i = 0; i < this.v[paramInt].connectedEdges.length; i++) {
      d += getEdgeLength(this.v[paramInt].connectedEdges[i]);
    }
    return d;
  }
  
  protected double getEdgeLength(int paramInt)
  {
    if (this.isOptimizeEdgeLength)
    {
      double d = Point2D.distance(this.v[this.e[paramInt].source].x, this.v[this.e[paramInt].source].y, this.v[this.e[paramInt].target].x, this.v[this.e[paramInt].target].y);
      return this.edgeLengthCostFactor * d * d;
    }
    return 0.0D;
  }
  
  protected double getEdgeCrossingAffectedEdges(int paramInt)
  {
    double d = 0.0D;
    for (int i = 0; i < this.v[paramInt].connectedEdges.length; i++) {
      d += getEdgeCrossing(this.v[paramInt].connectedEdges[i]);
    }
    return d;
  }
  
  protected double getEdgeCrossing(int paramInt)
  {
    int i = 0;
    if (this.isOptimizeEdgeCrossing)
    {
      double d9 = this.v[this.e[paramInt].source].x;
      double d10 = this.v[this.e[paramInt].source].y;
      double d11 = this.v[this.e[paramInt].target].x;
      double d12 = this.v[this.e[paramInt].target].y;
      for (int j = 0; j < this.e.length; j++)
      {
        double d13 = this.v[this.e[j].source].x;
        double d14 = this.v[this.e[j].source].y;
        double d15 = this.v[this.e[j].target].x;
        double d16 = this.v[this.e[j].target].y;
        if (j != paramInt)
        {
          double d3;
          double d7;
          if (d9 < d11)
          {
            d3 = d9;
            d7 = d11;
          }
          else
          {
            d3 = d11;
            d7 = d9;
          }
          double d1;
          double d5;
          if (d13 < d15)
          {
            d1 = d13;
            d5 = d15;
          }
          else
          {
            d1 = d15;
            d5 = d13;
          }
          if ((d7 >= d1) && (d3 <= d5))
          {
            double d4;
            double d8;
            if (d10 < d12)
            {
              d4 = d10;
              d8 = d12;
            }
            else
            {
              d4 = d12;
              d8 = d10;
            }
            double d2;
            double d6;
            if (d14 < d16)
            {
              d2 = d14;
              d6 = d16;
            }
            else
            {
              d2 = d16;
              d6 = d14;
            }
            if ((d8 >= d2) && (d4 <= d6) && (d9 != d13) && (d10 != d14) && (d9 != d15) && (d10 != d16) && (d11 != d13) && (d12 != d14) && (d11 != d15) && (d12 != d16))
            {
              int k = (Line2D.relativeCCW(d9, d10, d11, d12, d13, d14) != Line2D.relativeCCW(d9, d10, d11, d12, d15, d16)) && (Line2D.relativeCCW(d13, d14, d15, d16, d9, d10) != Line2D.relativeCCW(d13, d14, d15, d16, d11, d12)) ? 1 : 0;
              if (k != 0) {
                i++;
              }
            }
          }
        }
      }
    }
    return this.edgeCrossingCostFactor * i;
  }
  
  protected double getEdgeDistanceFromNode(int paramInt)
  {
    double d1 = 0.0D;
    if ((this.isOptimizeEdgeDistance) && (this.isFineTuning))
    {
      int[] arrayOfInt = this.v[paramInt].relevantEdges;
      for (int i = 0; i < arrayOfInt.length; i++)
      {
        double d2 = Line2D.ptSegDistSq(this.v[this.e[arrayOfInt[i]].source].x, this.v[this.e[arrayOfInt[i]].source].y, this.v[this.e[arrayOfInt[i]].target].x, this.v[this.e[arrayOfInt[i]].target].y, this.v[paramInt].x, this.v[paramInt].y);
        d2 -= this.v[paramInt].radiusSquared;
        if (d2 < this.minDistanceLimitSquared) {
          d2 = this.minDistanceLimitSquared;
        }
        if (d2 < this.maxDistanceLimitSquared) {
          d1 += this.edgeDistanceCostFactor / d2;
        }
      }
    }
    return d1;
  }
  
  protected double getEdgeDistanceAffectedNodes(int paramInt)
  {
    double d = 0.0D;
    for (int i = 0; i < this.v[paramInt].connectedEdges.length; i++) {
      d += getEdgeDistanceFromEdge(this.v[paramInt].connectedEdges[i]);
    }
    return d;
  }
  
  protected double getEdgeDistanceFromEdge(int paramInt)
  {
    double d1 = 0.0D;
    if ((this.isOptimizeEdgeDistance) && (this.isFineTuning)) {
      for (int i = 0; i < this.v.length; i++) {
        if ((this.e[paramInt].source != i) && (this.e[paramInt].target != i))
        {
          double d2 = Line2D.ptSegDistSq(this.v[this.e[paramInt].source].x, this.v[this.e[paramInt].source].y, this.v[this.e[paramInt].target].x, this.v[this.e[paramInt].target].y, this.v[i].x, this.v[i].y);
          d2 -= this.v[i].radiusSquared;
          if (d2 < this.minDistanceLimitSquared) {
            d2 = this.minDistanceLimitSquared;
          }
          if (d2 < this.maxDistanceLimitSquared) {
            d1 += this.edgeDistanceCostFactor / d2;
          }
        }
      }
    }
    return d1;
  }
  
  protected double getAdditionFactorsEnergy(int paramInt)
  {
    return 0.0D;
  }
  
  protected int[] getRelevantEdges(int paramInt)
  {
    ArrayList localArrayList = new ArrayList(this.e.length);
    for (int i = 0; i < this.e.length; i++) {
      if ((this.e[i].source != paramInt) && (this.e[i].target != paramInt)) {
        localArrayList.add(new Integer(i));
      }
    }
    int[] arrayOfInt = new int[localArrayList.size()];
    Iterator localIterator = localArrayList.iterator();
    for (int j = 0; j < arrayOfInt.length; j++) {
      if (localIterator.hasNext()) {
        arrayOfInt[j] = ((Integer)localIterator.next()).intValue();
      }
    }
    return arrayOfInt;
  }
  
  protected int[] getConnectedEdges(int paramInt)
  {
    ArrayList localArrayList = new ArrayList(this.e.length);
    for (int i = 0; i < this.e.length; i++) {
      if ((this.e[i].source == paramInt) || (this.e[i].target == paramInt)) {
        localArrayList.add(new Integer(i));
      }
    }
    int[] arrayOfInt = new int[localArrayList.size()];
    Iterator localIterator = localArrayList.iterator();
    for (int j = 0; j < arrayOfInt.length; j++) {
      if (localIterator.hasNext()) {
        arrayOfInt[j] = ((Integer)localIterator.next()).intValue();
      }
    }
    return arrayOfInt;
  }
  
  public String toString()
  {
    return "Annealing";
  }
  
  public double getAverageNodeArea()
  {
    return this.averageNodeArea;
  }
  
  public void setAverageNodeArea(double paramDouble)
  {
    this.averageNodeArea = paramDouble;
  }
  
  public double getBorderLineCostFactor()
  {
    return this.borderLineCostFactor;
  }
  
  public void setBorderLineCostFactor(double paramDouble)
  {
    this.borderLineCostFactor = paramDouble;
  }
  
  public double getEdgeCrossingCostFactor()
  {
    return this.edgeCrossingCostFactor;
  }
  
  public void setEdgeCrossingCostFactor(double paramDouble)
  {
    this.edgeCrossingCostFactor = paramDouble;
  }
  
  public double getEdgeDistanceCostFactor()
  {
    return this.edgeDistanceCostFactor;
  }
  
  public void setEdgeDistanceCostFactor(double paramDouble)
  {
    this.edgeDistanceCostFactor = paramDouble;
  }
  
  public double getEdgeLengthCostFactor()
  {
    return this.edgeLengthCostFactor;
  }
  
  public void setEdgeLengthCostFactor(double paramDouble)
  {
    this.edgeLengthCostFactor = paramDouble;
  }
  
  public double getFineTuningRadius()
  {
    return this.fineTuningRadius;
  }
  
  public void setFineTuningRadius(double paramDouble)
  {
    this.fineTuningRadius = paramDouble;
  }
  
  public double getInitialMoveRadius()
  {
    return this.initialMoveRadius;
  }
  
  public void setInitialMoveRadius(double paramDouble)
  {
    this.initialMoveRadius = paramDouble;
  }
  
  public boolean isFineTuning()
  {
    return this.isFineTuning;
  }
  
  public void setFineTuning(boolean paramBoolean)
  {
    this.isFineTuning = paramBoolean;
  }
  
  public boolean isOptimizeBorderLine()
  {
    return this.isOptimizeBorderLine;
  }
  
  public void setOptimizeBorderLine(boolean paramBoolean)
  {
    this.isOptimizeBorderLine = paramBoolean;
  }
  
  public boolean isOptimizeEdgeCrossing()
  {
    return this.isOptimizeEdgeCrossing;
  }
  
  public void setOptimizeEdgeCrossing(boolean paramBoolean)
  {
    this.isOptimizeEdgeCrossing = paramBoolean;
  }
  
  public boolean isOptimizeEdgeDistance()
  {
    return this.isOptimizeEdgeDistance;
  }
  
  public void setOptimizeEdgeDistance(boolean paramBoolean)
  {
    this.isOptimizeEdgeDistance = paramBoolean;
  }
  
  public boolean isOptimizeEdgeLength()
  {
    return this.isOptimizeEdgeLength;
  }
  
  public void setOptimizeEdgeLength(boolean paramBoolean)
  {
    this.isOptimizeEdgeLength = paramBoolean;
  }
  
  public boolean isOptimizeNodeDistribution()
  {
    return this.isOptimizeNodeDistribution;
  }
  
  public void setOptimizeNodeDistribution(boolean paramBoolean)
  {
    this.isOptimizeNodeDistribution = paramBoolean;
  }
  
  public int getMaxIterations()
  {
    return this.maxIterations;
  }
  
  public void setMaxIterations(int paramInt)
  {
    this.maxIterations = paramInt;
  }
  
  public double getMinDistanceLimit()
  {
    return this.minDistanceLimit;
  }
  
  public void setMinDistanceLimit(double paramDouble)
  {
    this.minDistanceLimit = paramDouble;
  }
  
  public double getMinMoveRadius()
  {
    return this.minMoveRadius;
  }
  
  public void setMinMoveRadius(double paramDouble)
  {
    this.minMoveRadius = paramDouble;
  }
  
  public double getNodeDistributionCostFactor()
  {
    return this.nodeDistributionCostFactor;
  }
  
  public void setNodeDistributionCostFactor(double paramDouble)
  {
    this.nodeDistributionCostFactor = paramDouble;
  }
  
  public double getRadiusScaleFactor()
  {
    return this.radiusScaleFactor;
  }
  
  public void setRadiusScaleFactor(double paramDouble)
  {
    this.radiusScaleFactor = paramDouble;
  }
  
  public int getTriesPerCell()
  {
    return this.triesPerCell;
  }
  
  public void setTriesPerCell(int paramInt)
  {
    this.triesPerCell = paramInt;
  }
  
  public int getUnchangedEnergyRoundTermination()
  {
    return this.unchangedEnergyRoundTermination;
  }
  
  public void setUnchangedEnergyRoundTermination(int paramInt)
  {
    this.unchangedEnergyRoundTermination = paramInt;
  }
  
  public double getMaxDistanceLimit()
  {
    return this.maxDistanceLimit;
  }
  
  public void setMaxDistanceLimit(double paramDouble)
  {
    this.maxDistanceLimit = paramDouble;
  }
  
  public boolean isDeterministic()
  {
    return this.isDeterministic;
  }
  
  public void setDeterministic(boolean paramBoolean)
  {
    this.isDeterministic = paramBoolean;
  }
  
  public void setLoggerLevel(Level paramLevel)
  {
    try
    {
      logger.setLevel(paramLevel);
    }
    catch (SecurityException localSecurityException) {}
  }
  
  public boolean isApproxNodeDimensions()
  {
    return this.approxNodeDimensions;
  }
  
  public void setApproxNodeDimensions(boolean paramBoolean)
  {
    this.approxNodeDimensions = paramBoolean;
  }
  
  public class CellWrapper
  {
    protected Object cell;
    protected int[] relevantEdges = null;
    protected int[] connectedEdges = null;
    protected double x;
    protected double y;
    protected double radiusSquared;
    protected double heightSquared;
    protected int source;
    protected int target;
    
    public CellWrapper(Object paramObject)
    {
      this.cell = paramObject;
    }
    
    public int[] getRelevantEdges()
    {
      return this.relevantEdges;
    }
    
    public void setRelevantEdges(int[] paramArrayOfInt)
    {
      this.relevantEdges = paramArrayOfInt;
    }
    
    public int[] getConnectedEdges()
    {
      return this.connectedEdges;
    }
    
    public void setConnectedEdges(int[] paramArrayOfInt)
    {
      this.connectedEdges = paramArrayOfInt;
    }
    
    public double getX()
    {
      return this.x;
    }
    
    public void setX(double paramDouble)
    {
      this.x = paramDouble;
    }
    
    public double getY()
    {
      return this.y;
    }
    
    public void setY(double paramDouble)
    {
      this.y = paramDouble;
    }
    
    public double getRadiusSquared()
    {
      return this.radiusSquared;
    }
    
    public void setRadiusSquared(double paramDouble)
    {
      this.radiusSquared = paramDouble;
    }
    
    public double getHeightSquared()
    {
      return this.heightSquared;
    }
    
    public void setHeightSquared(double paramDouble)
    {
      this.heightSquared = paramDouble;
    }
    
    public int getSource()
    {
      return this.source;
    }
    
    public void setSource(int paramInt)
    {
      this.source = paramInt;
    }
    
    public int getTarget()
    {
      return this.target;
    }
    
    public void setTarget(int paramInt)
    {
      this.target = paramInt;
    }
    
    public Object getCell()
    {
      return this.cell;
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/layout/organic/JGraphOrganicLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */