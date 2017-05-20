/*      */ package org.jfree.chart.renderer.xy;
/*      */ 
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.geom.GeneralPath;
/*      */ import java.awt.geom.Line2D;
/*      */ import java.awt.geom.Line2D.Double;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import org.jfree.chart.ChartRenderingInfo;
/*      */ import org.jfree.chart.LegendItem;
/*      */ import org.jfree.chart.axis.ValueAxis;
/*      */ import org.jfree.chart.entity.EntityCollection;
/*      */ import org.jfree.chart.labels.XYSeriesLabelGenerator;
/*      */ import org.jfree.chart.plot.CrosshairState;
/*      */ import org.jfree.chart.plot.PlotOrientation;
/*      */ import org.jfree.chart.plot.PlotRenderingInfo;
/*      */ import org.jfree.chart.plot.XYPlot;
/*      */ import org.jfree.data.xy.XYDataset;
/*      */ import org.jfree.io.SerialUtilities;
/*      */ import org.jfree.ui.RectangleEdge;
/*      */ import org.jfree.util.BooleanList;
/*      */ import org.jfree.util.BooleanUtilities;
/*      */ import org.jfree.util.ObjectUtilities;
/*      */ import org.jfree.util.PublicCloneable;
/*      */ import org.jfree.util.ShapeUtilities;
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
/*      */ public class XYLineAndShapeRenderer
/*      */   extends AbstractXYItemRenderer
/*      */   implements XYItemRenderer, Cloneable, PublicCloneable, Serializable
/*      */ {
/*      */   private static final long serialVersionUID = -7435246895986425885L;
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   private Boolean linesVisible;
/*      */   private BooleanList seriesLinesVisible;
/*      */   private boolean baseLinesVisible;
/*      */   private transient Shape legendLine;
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   private Boolean shapesVisible;
/*      */   private BooleanList seriesShapesVisible;
/*      */   private boolean baseShapesVisible;
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   private Boolean shapesFilled;
/*      */   private BooleanList seriesShapesFilled;
/*      */   private boolean baseShapesFilled;
/*      */   private boolean drawOutlines;
/*      */   private boolean useFillPaint;
/*      */   private boolean useOutlinePaint;
/*      */   private boolean drawSeriesLineAsPath;
/*      */   
/*      */   public XYLineAndShapeRenderer()
/*      */   {
/*  194 */     this(true, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public XYLineAndShapeRenderer(boolean lines, boolean shapes)
/*      */   {
/*  204 */     this.linesVisible = null;
/*  205 */     this.seriesLinesVisible = new BooleanList();
/*  206 */     this.baseLinesVisible = lines;
/*  207 */     this.legendLine = new Line2D.Double(-7.0D, 0.0D, 7.0D, 0.0D);
/*      */     
/*  209 */     this.shapesVisible = null;
/*  210 */     this.seriesShapesVisible = new BooleanList();
/*  211 */     this.baseShapesVisible = shapes;
/*      */     
/*  213 */     this.shapesFilled = null;
/*  214 */     this.useFillPaint = false;
/*  215 */     this.seriesShapesFilled = new BooleanList();
/*  216 */     this.baseShapesFilled = true;
/*      */     
/*  218 */     this.drawOutlines = true;
/*  219 */     this.useOutlinePaint = false;
/*      */     
/*      */ 
/*  222 */     this.drawSeriesLineAsPath = false;
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
/*  234 */     return this.drawSeriesLineAsPath;
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
/*      */   public void setDrawSeriesLineAsPath(boolean flag)
/*      */   {
/*  247 */     if (this.drawSeriesLineAsPath != flag) {
/*  248 */       this.drawSeriesLineAsPath = flag;
/*  249 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getPassCount()
/*      */   {
/*  261 */     return 2;
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
/*      */   public boolean getItemLineVisible(int series, int item)
/*      */   {
/*  276 */     Boolean flag = this.linesVisible;
/*  277 */     if (flag == null) {
/*  278 */       flag = getSeriesLinesVisible(series);
/*      */     }
/*  280 */     if (flag != null) {
/*  281 */       return flag.booleanValue();
/*      */     }
/*      */     
/*  284 */     return this.baseLinesVisible;
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public Boolean getLinesVisible()
/*      */   {
/*  300 */     return this.linesVisible;
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public void setLinesVisible(Boolean visible)
/*      */   {
/*  316 */     this.linesVisible = visible;
/*  317 */     fireChangeEvent();
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public void setLinesVisible(boolean visible)
/*      */   {
/*  333 */     setLinesVisible(BooleanUtilities.valueOf(visible));
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
/*      */   public Boolean getSeriesLinesVisible(int series)
/*      */   {
/*  347 */     return this.seriesLinesVisible.getBoolean(series);
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
/*      */   public void setSeriesLinesVisible(int series, Boolean flag)
/*      */   {
/*  360 */     this.seriesLinesVisible.setBoolean(series, flag);
/*  361 */     fireChangeEvent();
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
/*      */   public void setSeriesLinesVisible(int series, boolean visible)
/*      */   {
/*  374 */     setSeriesLinesVisible(series, BooleanUtilities.valueOf(visible));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getBaseLinesVisible()
/*      */   {
/*  385 */     return this.baseLinesVisible;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBaseLinesVisible(boolean flag)
/*      */   {
/*  397 */     this.baseLinesVisible = flag;
/*  398 */     fireChangeEvent();
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
/*  409 */     return this.legendLine;
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
/*  421 */     if (line == null) {
/*  422 */       throw new IllegalArgumentException("Null 'line' argument.");
/*      */     }
/*  424 */     this.legendLine = line;
/*  425 */     fireChangeEvent();
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
/*      */   public boolean getItemShapeVisible(int series, int item)
/*      */   {
/*  444 */     Boolean flag = this.shapesVisible;
/*  445 */     if (flag == null) {
/*  446 */       flag = getSeriesShapesVisible(series);
/*      */     }
/*  448 */     if (flag != null) {
/*  449 */       return flag.booleanValue();
/*      */     }
/*      */     
/*  452 */     return this.baseShapesVisible;
/*      */   }
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
/*      */   public Boolean getShapesVisible()
/*      */   {
/*  467 */     return this.shapesVisible;
/*      */   }
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
/*      */   public void setShapesVisible(Boolean visible)
/*      */   {
/*  481 */     this.shapesVisible = visible;
/*  482 */     fireChangeEvent();
/*      */   }
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
/*      */   public void setShapesVisible(boolean visible)
/*      */   {
/*  496 */     setShapesVisible(BooleanUtilities.valueOf(visible));
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
/*      */   public Boolean getSeriesShapesVisible(int series)
/*      */   {
/*  510 */     return this.seriesShapesVisible.getBoolean(series);
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
/*      */   public void setSeriesShapesVisible(int series, boolean visible)
/*      */   {
/*  523 */     setSeriesShapesVisible(series, BooleanUtilities.valueOf(visible));
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
/*      */   public void setSeriesShapesVisible(int series, Boolean flag)
/*      */   {
/*  536 */     this.seriesShapesVisible.setBoolean(series, flag);
/*  537 */     fireChangeEvent();
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
/*  548 */     return this.baseShapesVisible;
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
/*  560 */     this.baseShapesVisible = flag;
/*  561 */     fireChangeEvent();
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
/*      */   public boolean getItemShapeFilled(int series, int item)
/*      */   {
/*  580 */     Boolean flag = this.shapesFilled;
/*  581 */     if (flag == null) {
/*  582 */       flag = getSeriesShapesFilled(series);
/*      */     }
/*  584 */     if (flag != null) {
/*  585 */       return flag.booleanValue();
/*      */     }
/*      */     
/*  588 */     return this.baseShapesFilled;
/*      */   }
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
/*  601 */     setShapesFilled(BooleanUtilities.valueOf(filled));
/*      */   }
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
/*  613 */     this.shapesFilled = filled;
/*  614 */     fireChangeEvent();
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
/*      */   public Boolean getSeriesShapesFilled(int series)
/*      */   {
/*  628 */     return this.seriesShapesFilled.getBoolean(series);
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
/*      */   public void setSeriesShapesFilled(int series, boolean flag)
/*      */   {
/*  641 */     setSeriesShapesFilled(series, BooleanUtilities.valueOf(flag));
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
/*  654 */     this.seriesShapesFilled.setBoolean(series, flag);
/*  655 */     fireChangeEvent();
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
/*  666 */     return this.baseShapesFilled;
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
/*  678 */     this.baseShapesFilled = flag;
/*  679 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getDrawOutlines()
/*      */   {
/*  691 */     return this.drawOutlines;
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
/*      */   public void setDrawOutlines(boolean flag)
/*      */   {
/*  707 */     this.drawOutlines = flag;
/*  708 */     fireChangeEvent();
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
/*      */   public boolean getUseFillPaint()
/*      */   {
/*  725 */     return this.useFillPaint;
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
/*      */   public void setUseFillPaint(boolean flag)
/*      */   {
/*  738 */     this.useFillPaint = flag;
/*  739 */     fireChangeEvent();
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
/*      */   public boolean getUseOutlinePaint()
/*      */   {
/*  753 */     return this.useOutlinePaint;
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
/*      */   public void setUseOutlinePaint(boolean flag)
/*      */   {
/*  769 */     this.useOutlinePaint = flag;
/*  770 */     fireChangeEvent();
/*      */   }
/*      */   
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
/*      */ 
/*      */     private boolean lastPointGood;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public State(PlotRenderingInfo info)
/*      */     {
/*  795 */       super();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public boolean isLastPointGood()
/*      */     {
/*  805 */       return this.lastPointGood;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public void setLastPointGood(boolean good)
/*      */     {
/*  815 */       this.lastPointGood = good;
/*      */     }
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
/*      */     public void startSeriesPass(XYDataset dataset, int series, int firstItem, int lastItem, int pass, int passCount)
/*      */     {
/*  831 */       this.seriesPath.reset();
/*  832 */       this.lastPointGood = false;
/*  833 */       super.startSeriesPass(dataset, series, firstItem, lastItem, pass, passCount);
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
/*      */ 
/*      */ 
/*      */   public XYItemRendererState initialise(Graphics2D g2, Rectangle2D dataArea, XYPlot plot, XYDataset data, PlotRenderingInfo info)
/*      */   {
/*  861 */     State state = new State(info);
/*  862 */     state.seriesPath = new GeneralPath();
/*  863 */     return state;
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
/*      */ 
/*      */   public void drawItem(Graphics2D g2, XYItemRendererState state, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState, int pass)
/*      */   {
/*  899 */     if (!getItemVisible(series, item)) {
/*  900 */       return;
/*      */     }
/*      */     
/*      */ 
/*  904 */     if (isLinePass(pass)) {
/*  905 */       if (getItemLineVisible(series, item)) {
/*  906 */         if (this.drawSeriesLineAsPath) {
/*  907 */           drawPrimaryLineAsPath(state, g2, plot, dataset, pass, series, item, domainAxis, rangeAxis, dataArea);
/*      */         }
/*      */         else
/*      */         {
/*  911 */           drawPrimaryLine(state, g2, plot, dataset, pass, series, item, domainAxis, rangeAxis, dataArea);
/*      */         }
/*      */         
/*      */       }
/*      */       
/*      */     }
/*  917 */     else if (isItemPass(pass))
/*      */     {
/*      */ 
/*  920 */       EntityCollection entities = null;
/*  921 */       if (info != null) {
/*  922 */         entities = info.getOwner().getEntityCollection();
/*      */       }
/*      */       
/*  925 */       drawSecondaryPass(g2, plot, dataset, pass, series, item, domainAxis, dataArea, rangeAxis, crosshairState, entities);
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
/*      */   protected boolean isLinePass(int pass)
/*      */   {
/*  939 */     return pass == 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected boolean isItemPass(int pass)
/*      */   {
/*  951 */     return pass == 1;
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
/*      */   protected void drawPrimaryLine(XYItemRendererState state, Graphics2D g2, XYPlot plot, XYDataset dataset, int pass, int series, int item, ValueAxis domainAxis, ValueAxis rangeAxis, Rectangle2D dataArea)
/*      */   {
/*  980 */     if (item == 0) {
/*  981 */       return;
/*      */     }
/*      */     
/*      */ 
/*  985 */     double x1 = dataset.getXValue(series, item);
/*  986 */     double y1 = dataset.getYValue(series, item);
/*  987 */     if ((Double.isNaN(y1)) || (Double.isNaN(x1))) {
/*  988 */       return;
/*      */     }
/*      */     
/*  991 */     double x0 = dataset.getXValue(series, item - 1);
/*  992 */     double y0 = dataset.getYValue(series, item - 1);
/*  993 */     if ((Double.isNaN(y0)) || (Double.isNaN(x0))) {
/*  994 */       return;
/*      */     }
/*      */     
/*  997 */     RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
/*  998 */     RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
/*      */     
/* 1000 */     double transX0 = domainAxis.valueToJava2D(x0, dataArea, xAxisLocation);
/* 1001 */     double transY0 = rangeAxis.valueToJava2D(y0, dataArea, yAxisLocation);
/*      */     
/* 1003 */     double transX1 = domainAxis.valueToJava2D(x1, dataArea, xAxisLocation);
/* 1004 */     double transY1 = rangeAxis.valueToJava2D(y1, dataArea, yAxisLocation);
/*      */     
/*      */ 
/* 1007 */     if ((Double.isNaN(transX0)) || (Double.isNaN(transY0)) || (Double.isNaN(transX1)) || (Double.isNaN(transY1)))
/*      */     {
/* 1009 */       return;
/*      */     }
/*      */     
/* 1012 */     PlotOrientation orientation = plot.getOrientation();
/* 1013 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 1014 */       state.workingLine.setLine(transY0, transX0, transY1, transX1);
/*      */     }
/* 1016 */     else if (orientation == PlotOrientation.VERTICAL) {
/* 1017 */       state.workingLine.setLine(transX0, transY0, transX1, transY1);
/*      */     }
/*      */     
/* 1020 */     if (state.workingLine.intersects(dataArea)) {
/* 1021 */       drawFirstPassShape(g2, pass, series, item, state.workingLine);
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
/*      */   protected void drawFirstPassShape(Graphics2D g2, int pass, int series, int item, Shape shape)
/*      */   {
/* 1036 */     g2.setStroke(getItemStroke(series, item));
/* 1037 */     g2.setPaint(getItemPaint(series, item));
/* 1038 */     g2.draw(shape);
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
/*      */   protected void drawPrimaryLineAsPath(XYItemRendererState state, Graphics2D g2, XYPlot plot, XYDataset dataset, int pass, int series, int item, ValueAxis domainAxis, ValueAxis rangeAxis, Rectangle2D dataArea)
/*      */   {
/* 1071 */     RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
/* 1072 */     RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
/*      */     
/*      */ 
/* 1075 */     double x1 = dataset.getXValue(series, item);
/* 1076 */     double y1 = dataset.getYValue(series, item);
/* 1077 */     double transX1 = domainAxis.valueToJava2D(x1, dataArea, xAxisLocation);
/* 1078 */     double transY1 = rangeAxis.valueToJava2D(y1, dataArea, yAxisLocation);
/*      */     
/* 1080 */     State s = (State)state;
/*      */     
/* 1082 */     if ((!Double.isNaN(transX1)) && (!Double.isNaN(transY1))) {
/* 1083 */       float x = (float)transX1;
/* 1084 */       float y = (float)transY1;
/* 1085 */       PlotOrientation orientation = plot.getOrientation();
/* 1086 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 1087 */         x = (float)transY1;
/* 1088 */         y = (float)transX1;
/*      */       }
/* 1090 */       if (s.isLastPointGood()) {
/* 1091 */         s.seriesPath.lineTo(x, y);
/*      */       }
/*      */       else {
/* 1094 */         s.seriesPath.moveTo(x, y);
/*      */       }
/* 1096 */       s.setLastPointGood(true);
/*      */     }
/*      */     else {
/* 1099 */       s.setLastPointGood(false);
/*      */     }
/*      */     
/* 1102 */     if (item == s.getLastItemIndex())
/*      */     {
/* 1104 */       drawFirstPassShape(g2, pass, series, item, s.seriesPath);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void drawSecondaryPass(Graphics2D g2, XYPlot plot, XYDataset dataset, int pass, int series, int item, ValueAxis domainAxis, Rectangle2D dataArea, ValueAxis rangeAxis, CrosshairState crosshairState, EntityCollection entities)
/*      */   {
/* 1136 */     Shape entityArea = null;
/*      */     
/*      */ 
/* 1139 */     double x1 = dataset.getXValue(series, item);
/* 1140 */     double y1 = dataset.getYValue(series, item);
/* 1141 */     if ((Double.isNaN(y1)) || (Double.isNaN(x1))) {
/* 1142 */       return;
/*      */     }
/*      */     
/* 1145 */     PlotOrientation orientation = plot.getOrientation();
/* 1146 */     RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
/* 1147 */     RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
/* 1148 */     double transX1 = domainAxis.valueToJava2D(x1, dataArea, xAxisLocation);
/* 1149 */     double transY1 = rangeAxis.valueToJava2D(y1, dataArea, yAxisLocation);
/*      */     
/* 1151 */     if (getItemShapeVisible(series, item)) {
/* 1152 */       Shape shape = getItemShape(series, item);
/* 1153 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 1154 */         shape = ShapeUtilities.createTranslatedShape(shape, transY1, transX1);
/*      */ 
/*      */       }
/* 1157 */       else if (orientation == PlotOrientation.VERTICAL) {
/* 1158 */         shape = ShapeUtilities.createTranslatedShape(shape, transX1, transY1);
/*      */       }
/*      */       
/* 1161 */       entityArea = shape;
/* 1162 */       if (shape.intersects(dataArea)) {
/* 1163 */         if (getItemShapeFilled(series, item)) {
/* 1164 */           if (this.useFillPaint) {
/* 1165 */             g2.setPaint(getItemFillPaint(series, item));
/*      */           }
/*      */           else {
/* 1168 */             g2.setPaint(getItemPaint(series, item));
/*      */           }
/* 1170 */           g2.fill(shape);
/*      */         }
/* 1172 */         if (this.drawOutlines) {
/* 1173 */           if (getUseOutlinePaint()) {
/* 1174 */             g2.setPaint(getItemOutlinePaint(series, item));
/*      */           }
/*      */           else {
/* 1177 */             g2.setPaint(getItemPaint(series, item));
/*      */           }
/* 1179 */           g2.setStroke(getItemOutlineStroke(series, item));
/* 1180 */           g2.draw(shape);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1185 */     double xx = transX1;
/* 1186 */     double yy = transY1;
/* 1187 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 1188 */       xx = transY1;
/* 1189 */       yy = transX1;
/*      */     }
/*      */     
/*      */ 
/* 1193 */     if (isItemLabelVisible(series, item)) {
/* 1194 */       drawItemLabel(g2, orientation, dataset, series, item, xx, yy, y1 < 0.0D);
/*      */     }
/*      */     
/*      */ 
/* 1198 */     int domainAxisIndex = plot.getDomainAxisIndex(domainAxis);
/* 1199 */     int rangeAxisIndex = plot.getRangeAxisIndex(rangeAxis);
/* 1200 */     updateCrosshairValues(crosshairState, x1, y1, domainAxisIndex, rangeAxisIndex, transX1, transY1, orientation);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1205 */     if ((entities != null) && (isPointInRect(dataArea, xx, yy))) {
/* 1206 */       addEntity(entities, entityArea, dataset, series, item, xx, yy);
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
/*      */   public LegendItem getLegendItem(int datasetIndex, int series)
/*      */   {
/* 1221 */     XYPlot plot = getPlot();
/* 1222 */     if (plot == null) {
/* 1223 */       return null;
/*      */     }
/*      */     
/* 1226 */     LegendItem result = null;
/* 1227 */     XYDataset dataset = plot.getDataset(datasetIndex);
/* 1228 */     if ((dataset != null) && 
/* 1229 */       (getItemVisible(series, 0))) {
/* 1230 */       String label = getLegendItemLabelGenerator().generateLabel(dataset, series);
/*      */       
/* 1232 */       String description = label;
/* 1233 */       String toolTipText = null;
/* 1234 */       if (getLegendItemToolTipGenerator() != null) {
/* 1235 */         toolTipText = getLegendItemToolTipGenerator().generateLabel(dataset, series);
/*      */       }
/*      */       
/* 1238 */       String urlText = null;
/* 1239 */       if (getLegendItemURLGenerator() != null) {
/* 1240 */         urlText = getLegendItemURLGenerator().generateLabel(dataset, series);
/*      */       }
/*      */       
/* 1243 */       boolean shapeIsVisible = getItemShapeVisible(series, 0);
/* 1244 */       Shape shape = lookupLegendShape(series);
/* 1245 */       boolean shapeIsFilled = getItemShapeFilled(series, 0);
/* 1246 */       Paint fillPaint = this.useFillPaint ? lookupSeriesFillPaint(series) : lookupSeriesPaint(series);
/*      */       
/*      */ 
/* 1249 */       boolean shapeOutlineVisible = this.drawOutlines;
/* 1250 */       Paint outlinePaint = this.useOutlinePaint ? lookupSeriesOutlinePaint(series) : lookupSeriesPaint(series);
/*      */       
/*      */ 
/* 1253 */       Stroke outlineStroke = lookupSeriesOutlineStroke(series);
/* 1254 */       boolean lineVisible = getItemLineVisible(series, 0);
/* 1255 */       Stroke lineStroke = lookupSeriesStroke(series);
/* 1256 */       Paint linePaint = lookupSeriesPaint(series);
/* 1257 */       result = new LegendItem(label, description, toolTipText, urlText, shapeIsVisible, shape, shapeIsFilled, fillPaint, shapeOutlineVisible, outlinePaint, outlineStroke, lineVisible, this.legendLine, lineStroke, linePaint);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1262 */       result.setLabelFont(lookupLegendTextFont(series));
/* 1263 */       Paint labelPaint = lookupLegendTextPaint(series);
/* 1264 */       if (labelPaint != null) {
/* 1265 */         result.setLabelPaint(labelPaint);
/*      */       }
/* 1267 */       result.setSeriesKey(dataset.getSeriesKey(series));
/* 1268 */       result.setSeriesIndex(series);
/* 1269 */       result.setDataset(dataset);
/* 1270 */       result.setDatasetIndex(datasetIndex);
/*      */     }
/*      */     
/*      */ 
/* 1274 */     return result;
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
/* 1286 */     XYLineAndShapeRenderer clone = (XYLineAndShapeRenderer)super.clone();
/* 1287 */     clone.seriesLinesVisible = ((BooleanList)this.seriesLinesVisible.clone());
/*      */     
/* 1289 */     if (this.legendLine != null) {
/* 1290 */       clone.legendLine = ShapeUtilities.clone(this.legendLine);
/*      */     }
/* 1292 */     clone.seriesShapesVisible = ((BooleanList)this.seriesShapesVisible.clone());
/*      */     
/* 1294 */     clone.seriesShapesFilled = ((BooleanList)this.seriesShapesFilled.clone());
/*      */     
/* 1296 */     return clone;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean equals(Object obj)
/*      */   {
/* 1307 */     if (obj == this) {
/* 1308 */       return true;
/*      */     }
/* 1310 */     if (!(obj instanceof XYLineAndShapeRenderer)) {
/* 1311 */       return false;
/*      */     }
/* 1313 */     if (!super.equals(obj)) {
/* 1314 */       return false;
/*      */     }
/* 1316 */     XYLineAndShapeRenderer that = (XYLineAndShapeRenderer)obj;
/* 1317 */     if (!ObjectUtilities.equal(this.linesVisible, that.linesVisible)) {
/* 1318 */       return false;
/*      */     }
/* 1320 */     if (!ObjectUtilities.equal(this.seriesLinesVisible, that.seriesLinesVisible))
/*      */     {
/*      */ 
/* 1323 */       return false;
/*      */     }
/* 1325 */     if (this.baseLinesVisible != that.baseLinesVisible) {
/* 1326 */       return false;
/*      */     }
/* 1328 */     if (!ShapeUtilities.equal(this.legendLine, that.legendLine)) {
/* 1329 */       return false;
/*      */     }
/* 1331 */     if (!ObjectUtilities.equal(this.shapesVisible, that.shapesVisible)) {
/* 1332 */       return false;
/*      */     }
/* 1334 */     if (!ObjectUtilities.equal(this.seriesShapesVisible, that.seriesShapesVisible))
/*      */     {
/*      */ 
/* 1337 */       return false;
/*      */     }
/* 1339 */     if (this.baseShapesVisible != that.baseShapesVisible) {
/* 1340 */       return false;
/*      */     }
/* 1342 */     if (!ObjectUtilities.equal(this.shapesFilled, that.shapesFilled)) {
/* 1343 */       return false;
/*      */     }
/* 1345 */     if (!ObjectUtilities.equal(this.seriesShapesFilled, that.seriesShapesFilled))
/*      */     {
/*      */ 
/* 1348 */       return false;
/*      */     }
/* 1350 */     if (this.baseShapesFilled != that.baseShapesFilled) {
/* 1351 */       return false;
/*      */     }
/* 1353 */     if (this.drawOutlines != that.drawOutlines) {
/* 1354 */       return false;
/*      */     }
/* 1356 */     if (this.useOutlinePaint != that.useOutlinePaint) {
/* 1357 */       return false;
/*      */     }
/* 1359 */     if (this.useFillPaint != that.useFillPaint) {
/* 1360 */       return false;
/*      */     }
/* 1362 */     if (this.drawSeriesLineAsPath != that.drawSeriesLineAsPath) {
/* 1363 */       return false;
/*      */     }
/* 1365 */     return true;
/*      */   }
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
/* 1378 */     stream.defaultReadObject();
/* 1379 */     this.legendLine = SerialUtilities.readShape(stream);
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
/* 1390 */     stream.defaultWriteObject();
/* 1391 */     SerialUtilities.writeShape(this.legendLine, stream);
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/xy/XYLineAndShapeRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */