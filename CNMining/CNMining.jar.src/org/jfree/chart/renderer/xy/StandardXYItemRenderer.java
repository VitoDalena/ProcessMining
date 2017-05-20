/*      */ package org.jfree.chart.renderer.xy;
/*      */ 
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Image;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Point;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.geom.GeneralPath;
/*      */ import java.awt.geom.Line2D;
/*      */ import java.awt.geom.Line2D.Double;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.awt.geom.Rectangle2D.Double;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import org.jfree.chart.ChartRenderingInfo;
/*      */ import org.jfree.chart.LegendItem;
/*      */ import org.jfree.chart.axis.ValueAxis;
/*      */ import org.jfree.chart.entity.EntityCollection;
/*      */ import org.jfree.chart.labels.XYSeriesLabelGenerator;
/*      */ import org.jfree.chart.labels.XYToolTipGenerator;
/*      */ import org.jfree.chart.plot.CrosshairState;
/*      */ import org.jfree.chart.plot.Plot;
/*      */ import org.jfree.chart.plot.PlotOrientation;
/*      */ import org.jfree.chart.plot.PlotRenderingInfo;
/*      */ import org.jfree.chart.plot.XYPlot;
/*      */ import org.jfree.chart.urls.XYURLGenerator;
/*      */ import org.jfree.data.xy.XYDataset;
/*      */ import org.jfree.io.SerialUtilities;
/*      */ import org.jfree.ui.RectangleEdge;
/*      */ import org.jfree.util.BooleanList;
/*      */ import org.jfree.util.BooleanUtilities;
/*      */ import org.jfree.util.ObjectUtilities;
/*      */ import org.jfree.util.PublicCloneable;
/*      */ import org.jfree.util.ShapeUtilities;
/*      */ import org.jfree.util.UnitType;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class StandardXYItemRenderer
/*      */   extends AbstractXYItemRenderer
/*      */   implements XYItemRenderer, Cloneable, PublicCloneable, Serializable
/*      */ {
/*      */   private static final long serialVersionUID = -3271351259436865995L;
/*      */   public static final int SHAPES = 1;
/*      */   public static final int LINES = 2;
/*      */   public static final int SHAPES_AND_LINES = 3;
/*      */   public static final int IMAGES = 4;
/*      */   public static final int DISCONTINUOUS = 8;
/*      */   public static final int DISCONTINUOUS_LINES = 10;
/*      */   private boolean baseShapesVisible;
/*      */   private boolean plotLines;
/*      */   private boolean plotImages;
/*      */   private boolean plotDiscontinuous;
/*  192 */   private UnitType gapThresholdType = UnitType.RELATIVE;
/*      */   
/*      */ 
/*  195 */   private double gapThreshold = 1.0D;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   private Boolean shapesFilled;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private BooleanList seriesShapesFilled;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean baseShapesFilled;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean drawSeriesLineAsPath;
/*      */   
/*      */ 
/*      */ 
/*      */   private transient Shape legendLine;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public StandardXYItemRenderer()
/*      */   {
/*  229 */     this(2, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public StandardXYItemRenderer(int type)
/*      */   {
/*  240 */     this(type, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public StandardXYItemRenderer(int type, XYToolTipGenerator toolTipGenerator)
/*      */   {
/*  254 */     this(type, toolTipGenerator, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public StandardXYItemRenderer(int type, XYToolTipGenerator toolTipGenerator, XYURLGenerator urlGenerator)
/*      */   {
/*  272 */     setBaseToolTipGenerator(toolTipGenerator);
/*  273 */     setURLGenerator(urlGenerator);
/*  274 */     if ((type & 0x1) != 0) {
/*  275 */       this.baseShapesVisible = true;
/*      */     }
/*  277 */     if ((type & 0x2) != 0) {
/*  278 */       this.plotLines = true;
/*      */     }
/*  280 */     if ((type & 0x4) != 0) {
/*  281 */       this.plotImages = true;
/*      */     }
/*  283 */     if ((type & 0x8) != 0) {
/*  284 */       this.plotDiscontinuous = true;
/*      */     }
/*      */     
/*  287 */     this.shapesFilled = null;
/*  288 */     this.seriesShapesFilled = new BooleanList();
/*  289 */     this.baseShapesFilled = true;
/*  290 */     this.legendLine = new Line2D.Double(-7.0D, 0.0D, 7.0D, 0.0D);
/*  291 */     this.drawSeriesLineAsPath = false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getBaseShapesVisible()
/*      */   {
/*  302 */     return this.baseShapesVisible;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBaseShapesVisible(boolean flag)
/*      */   {
/*  314 */     if (this.baseShapesVisible != flag) {
/*  315 */       this.baseShapesVisible = flag;
/*  316 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getItemShapeFilled(int series, int item)
/*      */   {
/*  339 */     if (this.shapesFilled != null) {
/*  340 */       return this.shapesFilled.booleanValue();
/*      */     }
/*      */     
/*      */ 
/*  344 */     Boolean flag = this.seriesShapesFilled.getBoolean(series);
/*  345 */     if (flag != null) {
/*  346 */       return flag.booleanValue();
/*      */     }
/*      */     
/*  349 */     return this.baseShapesFilled;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public Boolean getShapesFilled()
/*      */   {
/*  366 */     return this.shapesFilled;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public void setShapesFilled(boolean filled)
/*      */   {
/*  385 */     setShapesFilled(BooleanUtilities.valueOf(filled));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public void setShapesFilled(Boolean filled)
/*      */   {
/*  403 */     this.shapesFilled = filled;
/*  404 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Boolean getSeriesShapesFilled(int series)
/*      */   {
/*  416 */     return this.seriesShapesFilled.getBoolean(series);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setSeriesShapesFilled(int series, Boolean flag)
/*      */   {
/*  429 */     this.seriesShapesFilled.setBoolean(series, flag);
/*  430 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getBaseShapesFilled()
/*      */   {
/*  441 */     return this.baseShapesFilled;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBaseShapesFilled(boolean flag)
/*      */   {
/*  453 */     this.baseShapesFilled = flag;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getPlotLines()
/*      */   {
/*  464 */     return this.plotLines;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setPlotLines(boolean flag)
/*      */   {
/*  477 */     if (this.plotLines != flag) {
/*  478 */       this.plotLines = flag;
/*  479 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public UnitType getGapThresholdType()
/*      */   {
/*  491 */     return this.gapThresholdType;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setGapThresholdType(UnitType thresholdType)
/*      */   {
/*  503 */     if (thresholdType == null) {
/*  504 */       throw new IllegalArgumentException("Null 'thresholdType' argument.");
/*      */     }
/*      */     
/*  507 */     this.gapThresholdType = thresholdType;
/*  508 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getGapThreshold()
/*      */   {
/*  519 */     return this.gapThreshold;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setGapThreshold(double t)
/*      */   {
/*  531 */     this.gapThreshold = t;
/*  532 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getPlotImages()
/*      */   {
/*  543 */     return this.plotImages;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setPlotImages(boolean flag)
/*      */   {
/*  556 */     if (this.plotImages != flag) {
/*  557 */       this.plotImages = flag;
/*  558 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getPlotDiscontinuous()
/*      */   {
/*  569 */     return this.plotDiscontinuous;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setPlotDiscontinuous(boolean flag)
/*      */   {
/*  582 */     if (this.plotDiscontinuous != flag) {
/*  583 */       this.plotDiscontinuous = flag;
/*  584 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getDrawSeriesLineAsPath()
/*      */   {
/*  597 */     return this.drawSeriesLineAsPath;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDrawSeriesLineAsPath(boolean flag)
/*      */   {
/*  609 */     this.drawSeriesLineAsPath = flag;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Shape getLegendLine()
/*      */   {
/*  620 */     return this.legendLine;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLegendLine(Shape line)
/*      */   {
/*  632 */     if (line == null) {
/*  633 */       throw new IllegalArgumentException("Null 'line' argument.");
/*      */     }
/*  635 */     this.legendLine = line;
/*  636 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LegendItem getLegendItem(int datasetIndex, int series)
/*      */   {
/*  648 */     XYPlot plot = getPlot();
/*  649 */     if (plot == null) {
/*  650 */       return null;
/*      */     }
/*  652 */     LegendItem result = null;
/*  653 */     XYDataset dataset = plot.getDataset(datasetIndex);
/*  654 */     if ((dataset != null) && 
/*  655 */       (getItemVisible(series, 0))) {
/*  656 */       String label = getLegendItemLabelGenerator().generateLabel(dataset, series);
/*      */       
/*  658 */       String description = label;
/*  659 */       String toolTipText = null;
/*  660 */       if (getLegendItemToolTipGenerator() != null) {
/*  661 */         toolTipText = getLegendItemToolTipGenerator().generateLabel(dataset, series);
/*      */       }
/*      */       
/*  664 */       String urlText = null;
/*  665 */       if (getLegendItemURLGenerator() != null) {
/*  666 */         urlText = getLegendItemURLGenerator().generateLabel(dataset, series);
/*      */       }
/*      */       
/*  669 */       Shape shape = lookupLegendShape(series);
/*  670 */       boolean shapeFilled = getItemShapeFilled(series, 0);
/*  671 */       Paint paint = lookupSeriesPaint(series);
/*  672 */       Paint linePaint = paint;
/*  673 */       Stroke lineStroke = lookupSeriesStroke(series);
/*  674 */       result = new LegendItem(label, description, toolTipText, urlText, this.baseShapesVisible, shape, shapeFilled, paint, !shapeFilled, paint, lineStroke, this.plotLines, this.legendLine, lineStroke, linePaint);
/*      */       
/*      */ 
/*      */ 
/*  678 */       result.setLabelFont(lookupLegendTextFont(series));
/*  679 */       Paint labelPaint = lookupLegendTextPaint(series);
/*  680 */       if (labelPaint != null) {
/*  681 */         result.setLabelPaint(labelPaint);
/*      */       }
/*  683 */       result.setDataset(dataset);
/*  684 */       result.setDatasetIndex(datasetIndex);
/*  685 */       result.setSeriesKey(dataset.getSeriesKey(series));
/*  686 */       result.setSeriesIndex(series);
/*      */     }
/*      */     
/*  689 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static class State
/*      */     extends XYItemRendererState
/*      */   {
/*      */     public GeneralPath seriesPath;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     private int seriesIndex;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     private boolean lastPointGood;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public State(PlotRenderingInfo info)
/*      */     {
/*  717 */       super();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public boolean isLastPointGood()
/*      */     {
/*  727 */       return this.lastPointGood;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public void setLastPointGood(boolean good)
/*      */     {
/*  737 */       this.lastPointGood = good;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public int getSeriesIndex()
/*      */     {
/*  746 */       return this.seriesIndex;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public void setSeriesIndex(int index)
/*      */     {
/*  755 */       this.seriesIndex = index;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public XYItemRendererState initialise(Graphics2D g2, Rectangle2D dataArea, XYPlot plot, XYDataset data, PlotRenderingInfo info)
/*      */   {
/*  781 */     State state = new State(info);
/*  782 */     state.seriesPath = new GeneralPath();
/*  783 */     state.seriesIndex = -1;
/*  784 */     return state;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void drawItem(Graphics2D g2, XYItemRendererState state, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState, int pass)
/*      */   {
/*  819 */     boolean itemVisible = getItemVisible(series, item);
/*      */     
/*      */ 
/*  822 */     Shape entityArea = null;
/*  823 */     EntityCollection entities = null;
/*  824 */     if (info != null) {
/*  825 */       entities = info.getOwner().getEntityCollection();
/*      */     }
/*      */     
/*  828 */     PlotOrientation orientation = plot.getOrientation();
/*  829 */     Paint paint = getItemPaint(series, item);
/*  830 */     Stroke seriesStroke = getItemStroke(series, item);
/*  831 */     g2.setPaint(paint);
/*  832 */     g2.setStroke(seriesStroke);
/*      */     
/*      */ 
/*  835 */     double x1 = dataset.getXValue(series, item);
/*  836 */     double y1 = dataset.getYValue(series, item);
/*  837 */     if ((Double.isNaN(x1)) || (Double.isNaN(y1))) {
/*  838 */       itemVisible = false;
/*      */     }
/*      */     
/*  841 */     RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
/*  842 */     RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
/*  843 */     double transX1 = domainAxis.valueToJava2D(x1, dataArea, xAxisLocation);
/*  844 */     double transY1 = rangeAxis.valueToJava2D(y1, dataArea, yAxisLocation);
/*      */     
/*  846 */     if (getPlotLines()) {
/*  847 */       if (this.drawSeriesLineAsPath) {
/*  848 */         State s = (State)state;
/*  849 */         if (s.getSeriesIndex() != series)
/*      */         {
/*  851 */           s.seriesPath.reset();
/*  852 */           s.lastPointGood = false;
/*  853 */           s.setSeriesIndex(series);
/*      */         }
/*      */         
/*      */ 
/*  857 */         if ((itemVisible) && (!Double.isNaN(transX1)) && (!Double.isNaN(transY1)))
/*      */         {
/*  859 */           float x = (float)transX1;
/*  860 */           float y = (float)transY1;
/*  861 */           if (orientation == PlotOrientation.HORIZONTAL) {
/*  862 */             x = (float)transY1;
/*  863 */             y = (float)transX1;
/*      */           }
/*  865 */           if (s.isLastPointGood())
/*      */           {
/*  867 */             s.seriesPath.lineTo(x, y);
/*      */           }
/*      */           else {
/*  870 */             s.seriesPath.moveTo(x, y);
/*      */           }
/*  872 */           s.setLastPointGood(true);
/*      */         }
/*      */         else {
/*  875 */           s.setLastPointGood(false);
/*      */         }
/*  877 */         if ((item == dataset.getItemCount(series) - 1) && 
/*  878 */           (s.seriesIndex == series))
/*      */         {
/*  880 */           g2.setStroke(lookupSeriesStroke(series));
/*  881 */           g2.setPaint(lookupSeriesPaint(series));
/*  882 */           g2.draw(s.seriesPath);
/*      */         }
/*      */         
/*      */ 
/*      */       }
/*  887 */       else if ((item != 0) && (itemVisible))
/*      */       {
/*  889 */         double x0 = dataset.getXValue(series, item - 1);
/*  890 */         double y0 = dataset.getYValue(series, item - 1);
/*  891 */         if ((!Double.isNaN(x0)) && (!Double.isNaN(y0))) {
/*  892 */           boolean drawLine = true;
/*  893 */           if (getPlotDiscontinuous())
/*      */           {
/*      */ 
/*  896 */             int numX = dataset.getItemCount(series);
/*  897 */             double minX = dataset.getXValue(series, 0);
/*  898 */             double maxX = dataset.getXValue(series, numX - 1);
/*  899 */             if (this.gapThresholdType == UnitType.ABSOLUTE) {
/*  900 */               drawLine = Math.abs(x1 - x0) <= this.gapThreshold;
/*      */             }
/*      */             else {
/*  903 */               drawLine = Math.abs(x1 - x0) <= (maxX - minX) / numX * getGapThreshold();
/*      */             }
/*      */           }
/*      */           
/*  907 */           if (drawLine) {
/*  908 */             double transX0 = domainAxis.valueToJava2D(x0, dataArea, xAxisLocation);
/*      */             
/*  910 */             double transY0 = rangeAxis.valueToJava2D(y0, dataArea, yAxisLocation);
/*      */             
/*      */ 
/*      */ 
/*  914 */             if ((Double.isNaN(transX0)) || (Double.isNaN(transY0)) || (Double.isNaN(transX1)) || (Double.isNaN(transY1)))
/*      */             {
/*  916 */               return;
/*      */             }
/*      */             
/*  919 */             if (orientation == PlotOrientation.HORIZONTAL) {
/*  920 */               state.workingLine.setLine(transY0, transX0, transY1, transX1);
/*      */ 
/*      */             }
/*  923 */             else if (orientation == PlotOrientation.VERTICAL) {
/*  924 */               state.workingLine.setLine(transX0, transY0, transX1, transY1);
/*      */             }
/*      */             
/*      */ 
/*  928 */             if (state.workingLine.intersects(dataArea)) {
/*  929 */               g2.draw(state.workingLine);
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  939 */     if (!itemVisible) {
/*  940 */       return;
/*      */     }
/*      */     
/*  943 */     if (getBaseShapesVisible())
/*      */     {
/*  945 */       Shape shape = getItemShape(series, item);
/*  946 */       if (orientation == PlotOrientation.HORIZONTAL) {
/*  947 */         shape = ShapeUtilities.createTranslatedShape(shape, transY1, transX1);
/*      */ 
/*      */       }
/*  950 */       else if (orientation == PlotOrientation.VERTICAL) {
/*  951 */         shape = ShapeUtilities.createTranslatedShape(shape, transX1, transY1);
/*      */       }
/*      */       
/*  954 */       if (shape.intersects(dataArea)) {
/*  955 */         if (getItemShapeFilled(series, item)) {
/*  956 */           g2.fill(shape);
/*      */         }
/*      */         else {
/*  959 */           g2.draw(shape);
/*      */         }
/*      */       }
/*  962 */       entityArea = shape;
/*      */     }
/*      */     
/*      */ 
/*  966 */     if (getPlotImages()) {
/*  967 */       Image image = getImage(plot, series, item, transX1, transY1);
/*  968 */       if (image != null) {
/*  969 */         Point hotspot = getImageHotspot(plot, series, item, transX1, transY1, image);
/*      */         
/*  971 */         g2.drawImage(image, (int)(transX1 - hotspot.getX()), (int)(transY1 - hotspot.getY()), null);
/*      */         
/*  973 */         entityArea = new Rectangle2D.Double(transX1 - hotspot.getX(), transY1 - hotspot.getY(), image.getWidth(null), image.getHeight(null));
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  980 */     double xx = transX1;
/*  981 */     double yy = transY1;
/*  982 */     if (orientation == PlotOrientation.HORIZONTAL) {
/*  983 */       xx = transY1;
/*  984 */       yy = transX1;
/*      */     }
/*      */     
/*      */ 
/*  988 */     if (isItemLabelVisible(series, item)) {
/*  989 */       drawItemLabel(g2, orientation, dataset, series, item, xx, yy, y1 < 0.0D);
/*      */     }
/*      */     
/*      */ 
/*  993 */     int domainAxisIndex = plot.getDomainAxisIndex(domainAxis);
/*  994 */     int rangeAxisIndex = plot.getRangeAxisIndex(rangeAxis);
/*  995 */     updateCrosshairValues(crosshairState, x1, y1, domainAxisIndex, rangeAxisIndex, transX1, transY1, orientation);
/*      */     
/*      */ 
/*      */ 
/*  999 */     if ((entities != null) && (isPointInRect(dataArea, xx, yy))) {
/* 1000 */       addEntity(entities, entityArea, dataset, series, item, xx, yy);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean equals(Object obj)
/*      */   {
/* 1014 */     if (obj == this) {
/* 1015 */       return true;
/*      */     }
/* 1017 */     if (!(obj instanceof StandardXYItemRenderer)) {
/* 1018 */       return false;
/*      */     }
/* 1020 */     StandardXYItemRenderer that = (StandardXYItemRenderer)obj;
/* 1021 */     if (this.baseShapesVisible != that.baseShapesVisible) {
/* 1022 */       return false;
/*      */     }
/* 1024 */     if (this.plotLines != that.plotLines) {
/* 1025 */       return false;
/*      */     }
/* 1027 */     if (this.plotImages != that.plotImages) {
/* 1028 */       return false;
/*      */     }
/* 1030 */     if (this.plotDiscontinuous != that.plotDiscontinuous) {
/* 1031 */       return false;
/*      */     }
/* 1033 */     if (this.gapThresholdType != that.gapThresholdType) {
/* 1034 */       return false;
/*      */     }
/* 1036 */     if (this.gapThreshold != that.gapThreshold) {
/* 1037 */       return false;
/*      */     }
/* 1039 */     if (!ObjectUtilities.equal(this.shapesFilled, that.shapesFilled)) {
/* 1040 */       return false;
/*      */     }
/* 1042 */     if (!this.seriesShapesFilled.equals(that.seriesShapesFilled)) {
/* 1043 */       return false;
/*      */     }
/* 1045 */     if (this.baseShapesFilled != that.baseShapesFilled) {
/* 1046 */       return false;
/*      */     }
/* 1048 */     if (this.drawSeriesLineAsPath != that.drawSeriesLineAsPath) {
/* 1049 */       return false;
/*      */     }
/* 1051 */     if (!ShapeUtilities.equal(this.legendLine, that.legendLine)) {
/* 1052 */       return false;
/*      */     }
/* 1054 */     return super.equals(obj);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object clone()
/*      */     throws CloneNotSupportedException
/*      */   {
/* 1066 */     StandardXYItemRenderer clone = (StandardXYItemRenderer)super.clone();
/* 1067 */     clone.seriesShapesFilled = ((BooleanList)this.seriesShapesFilled.clone());
/*      */     
/* 1069 */     clone.legendLine = ShapeUtilities.clone(this.legendLine);
/* 1070 */     return clone;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Image getImage(Plot plot, int series, int item, double x, double y)
/*      */   {
/* 1096 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Point getImageHotspot(Plot plot, int series, int item, double x, double y, Image image)
/*      */   {
/* 1119 */     int height = image.getHeight(null);
/* 1120 */     int width = image.getWidth(null);
/* 1121 */     return new Point(width / 2, height / 2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void readObject(ObjectInputStream stream)
/*      */     throws IOException, ClassNotFoundException
/*      */   {
/* 1135 */     stream.defaultReadObject();
/* 1136 */     this.legendLine = SerialUtilities.readShape(stream);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void writeObject(ObjectOutputStream stream)
/*      */     throws IOException
/*      */   {
/* 1147 */     stream.defaultWriteObject();
/* 1148 */     SerialUtilities.writeShape(this.legendLine, stream);
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/xy/StandardXYItemRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */