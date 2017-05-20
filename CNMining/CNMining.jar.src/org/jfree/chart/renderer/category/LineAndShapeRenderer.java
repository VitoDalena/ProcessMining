/*      */ package org.jfree.chart.renderer.category;
/*      */ 
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.geom.Line2D;
/*      */ import java.awt.geom.Line2D.Double;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.io.Serializable;
/*      */ import org.jfree.chart.LegendItem;
/*      */ import org.jfree.chart.axis.CategoryAxis;
/*      */ import org.jfree.chart.axis.ValueAxis;
/*      */ import org.jfree.chart.entity.EntityCollection;
/*      */ import org.jfree.chart.labels.CategorySeriesLabelGenerator;
/*      */ import org.jfree.chart.plot.CategoryPlot;
/*      */ import org.jfree.chart.plot.PlotOrientation;
/*      */ import org.jfree.data.category.CategoryDataset;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class LineAndShapeRenderer
/*      */   extends AbstractCategoryItemRenderer
/*      */   implements Cloneable, PublicCloneable, Serializable
/*      */ {
/*      */   private static final long serialVersionUID = -197749519869226398L;
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   private Boolean linesVisible;
/*      */   private BooleanList seriesLinesVisible;
/*      */   private boolean baseLinesVisible;
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
/*      */   private boolean useFillPaint;
/*      */   private boolean drawOutlines;
/*      */   private boolean useOutlinePaint;
/*      */   private boolean useSeriesOffset;
/*      */   private double itemMargin;
/*      */   
/*      */   public LineAndShapeRenderer()
/*      */   {
/*  220 */     this(true, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LineAndShapeRenderer(boolean lines, boolean shapes)
/*      */   {
/*  231 */     this.linesVisible = null;
/*  232 */     this.seriesLinesVisible = new BooleanList();
/*  233 */     this.baseLinesVisible = lines;
/*  234 */     this.shapesVisible = null;
/*  235 */     this.seriesShapesVisible = new BooleanList();
/*  236 */     this.baseShapesVisible = shapes;
/*  237 */     this.shapesFilled = null;
/*  238 */     this.seriesShapesFilled = new BooleanList();
/*  239 */     this.baseShapesFilled = true;
/*  240 */     this.useFillPaint = false;
/*  241 */     this.drawOutlines = true;
/*  242 */     this.useOutlinePaint = false;
/*  243 */     this.useSeriesOffset = false;
/*  244 */     this.itemMargin = 0.0D;
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
/*  259 */     Boolean flag = this.linesVisible;
/*  260 */     if (flag == null) {
/*  261 */       flag = getSeriesLinesVisible(series);
/*      */     }
/*  263 */     if (flag != null) {
/*  264 */       return flag.booleanValue();
/*      */     }
/*      */     
/*  267 */     return this.baseLinesVisible;
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
/*      */   public Boolean getLinesVisible()
/*      */   {
/*  284 */     return this.linesVisible;
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
/*      */   public void setLinesVisible(Boolean visible)
/*      */   {
/*  301 */     this.linesVisible = visible;
/*  302 */     fireChangeEvent();
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
/*  318 */     setLinesVisible(BooleanUtilities.valueOf(visible));
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
/*  332 */     return this.seriesLinesVisible.getBoolean(series);
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
/*  345 */     this.seriesLinesVisible.setBoolean(series, flag);
/*  346 */     fireChangeEvent();
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
/*  359 */     setSeriesLinesVisible(series, BooleanUtilities.valueOf(visible));
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
/*  370 */     return this.baseLinesVisible;
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
/*  382 */     this.baseLinesVisible = flag;
/*  383 */     fireChangeEvent();
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
/*      */   public boolean getItemShapeVisible(int series, int item)
/*      */   {
/*  398 */     Boolean flag = this.shapesVisible;
/*  399 */     if (flag == null) {
/*  400 */       flag = getSeriesShapesVisible(series);
/*      */     }
/*  402 */     if (flag != null) {
/*  403 */       return flag.booleanValue();
/*      */     }
/*      */     
/*  406 */     return this.baseShapesVisible;
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
/*      */   public Boolean getShapesVisible()
/*      */   {
/*  422 */     return this.shapesVisible;
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
/*      */   public void setShapesVisible(Boolean visible)
/*      */   {
/*  437 */     this.shapesVisible = visible;
/*  438 */     fireChangeEvent();
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
/*      */   public void setShapesVisible(boolean visible)
/*      */   {
/*  453 */     setShapesVisible(BooleanUtilities.valueOf(visible));
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
/*  467 */     return this.seriesShapesVisible.getBoolean(series);
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
/*  480 */     setSeriesShapesVisible(series, BooleanUtilities.valueOf(visible));
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
/*  493 */     this.seriesShapesVisible.setBoolean(series, flag);
/*  494 */     fireChangeEvent();
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
/*  505 */     return this.baseShapesVisible;
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
/*  517 */     this.baseShapesVisible = flag;
/*  518 */     fireChangeEvent();
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
/*  530 */     return this.drawOutlines;
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
/*  546 */     this.drawOutlines = flag;
/*  547 */     fireChangeEvent();
/*      */   }
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
/*  559 */     return this.useOutlinePaint;
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
/*      */   public void setUseOutlinePaint(boolean use)
/*      */   {
/*  572 */     this.useOutlinePaint = use;
/*  573 */     fireChangeEvent();
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
/*      */   public boolean getItemShapeFilled(int series, int item)
/*      */   {
/*  590 */     return getSeriesShapesFilled(series);
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
/*      */   public boolean getSeriesShapesFilled(int series)
/*      */   {
/*  604 */     if (this.shapesFilled != null) {
/*  605 */       return this.shapesFilled.booleanValue();
/*      */     }
/*      */     
/*      */ 
/*  609 */     Boolean flag = this.seriesShapesFilled.getBoolean(series);
/*  610 */     if (flag != null) {
/*  611 */       return flag.booleanValue();
/*      */     }
/*      */     
/*  614 */     return this.baseShapesFilled;
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
/*  631 */     return this.shapesFilled;
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
/*      */   public void setShapesFilled(boolean filled)
/*      */   {
/*  646 */     if (filled) {
/*  647 */       setShapesFilled(Boolean.TRUE);
/*      */     }
/*      */     else {
/*  650 */       setShapesFilled(Boolean.FALSE);
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public void setShapesFilled(Boolean filled)
/*      */   {
/*  666 */     this.shapesFilled = filled;
/*  667 */     fireChangeEvent();
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
/*      */   public void setSeriesShapesFilled(int series, Boolean filled)
/*      */   {
/*  680 */     this.seriesShapesFilled.setBoolean(series, filled);
/*  681 */     fireChangeEvent();
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
/*      */   public void setSeriesShapesFilled(int series, boolean filled)
/*      */   {
/*  695 */     setSeriesShapesFilled(series, BooleanUtilities.valueOf(filled));
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
/*  706 */     return this.baseShapesFilled;
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
/*  718 */     this.baseShapesFilled = flag;
/*  719 */     fireChangeEvent();
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
/*      */   public boolean getUseFillPaint()
/*      */   {
/*  732 */     return this.useFillPaint;
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
/*  745 */     this.useFillPaint = flag;
/*  746 */     fireChangeEvent();
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
/*      */   public boolean getUseSeriesOffset()
/*      */   {
/*  760 */     return this.useSeriesOffset;
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
/*      */   public void setUseSeriesOffset(boolean offset)
/*      */   {
/*  775 */     this.useSeriesOffset = offset;
/*  776 */     fireChangeEvent();
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
/*      */   public double getItemMargin()
/*      */   {
/*  793 */     return this.itemMargin;
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
/*      */   public void setItemMargin(double margin)
/*      */   {
/*  809 */     if ((margin < 0.0D) || (margin >= 1.0D)) {
/*  810 */       throw new IllegalArgumentException("Requires 0.0 <= margin < 1.0.");
/*      */     }
/*  812 */     this.itemMargin = margin;
/*  813 */     fireChangeEvent();
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
/*      */   public LegendItem getLegendItem(int datasetIndex, int series)
/*      */   {
/*  826 */     CategoryPlot cp = getPlot();
/*  827 */     if (cp == null) {
/*  828 */       return null;
/*      */     }
/*      */     
/*  831 */     if ((isSeriesVisible(series)) && (isSeriesVisibleInLegend(series))) {
/*  832 */       CategoryDataset dataset = cp.getDataset(datasetIndex);
/*  833 */       String label = getLegendItemLabelGenerator().generateLabel(dataset, series);
/*      */       
/*  835 */       String description = label;
/*  836 */       String toolTipText = null;
/*  837 */       if (getLegendItemToolTipGenerator() != null) {
/*  838 */         toolTipText = getLegendItemToolTipGenerator().generateLabel(dataset, series);
/*      */       }
/*      */       
/*  841 */       String urlText = null;
/*  842 */       if (getLegendItemURLGenerator() != null) {
/*  843 */         urlText = getLegendItemURLGenerator().generateLabel(dataset, series);
/*      */       }
/*      */       
/*  846 */       Shape shape = lookupLegendShape(series);
/*  847 */       Paint paint = lookupSeriesPaint(series);
/*  848 */       Paint fillPaint = this.useFillPaint ? getItemFillPaint(series, 0) : paint;
/*      */       
/*  850 */       boolean shapeOutlineVisible = this.drawOutlines;
/*  851 */       Paint outlinePaint = this.useOutlinePaint ? getItemOutlinePaint(series, 0) : paint;
/*      */       
/*  853 */       Stroke outlineStroke = lookupSeriesOutlineStroke(series);
/*  854 */       boolean lineVisible = getItemLineVisible(series, 0);
/*  855 */       boolean shapeVisible = getItemShapeVisible(series, 0);
/*  856 */       LegendItem result = new LegendItem(label, description, toolTipText, urlText, shapeVisible, shape, getItemShapeFilled(series, 0), fillPaint, shapeOutlineVisible, outlinePaint, outlineStroke, lineVisible, new Line2D.Double(-7.0D, 0.0D, 7.0D, 0.0D), getItemStroke(series, 0), getItemPaint(series, 0));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  861 */       result.setLabelFont(lookupLegendTextFont(series));
/*  862 */       Paint labelPaint = lookupLegendTextPaint(series);
/*  863 */       if (labelPaint != null) {
/*  864 */         result.setLabelPaint(labelPaint);
/*      */       }
/*  866 */       result.setDataset(dataset);
/*  867 */       result.setDatasetIndex(datasetIndex);
/*  868 */       result.setSeriesKey(dataset.getRowKey(series));
/*  869 */       result.setSeriesIndex(series);
/*  870 */       return result;
/*      */     }
/*  872 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getPassCount()
/*      */   {
/*  882 */     return 2;
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
/*      */   public void drawItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset dataset, int row, int column, int pass)
/*      */   {
/*  905 */     if (!getItemVisible(row, column)) {
/*  906 */       return;
/*      */     }
/*      */     
/*      */ 
/*  910 */     if ((!getItemLineVisible(row, column)) && (!getItemShapeVisible(row, column)))
/*      */     {
/*  912 */       return;
/*      */     }
/*      */     
/*      */ 
/*  916 */     Number v = dataset.getValue(row, column);
/*  917 */     if (v == null) {
/*  918 */       return;
/*      */     }
/*      */     
/*  921 */     int visibleRow = state.getVisibleSeriesIndex(row);
/*  922 */     if (visibleRow < 0) {
/*  923 */       return;
/*      */     }
/*  925 */     int visibleRowCount = state.getVisibleSeriesCount();
/*      */     
/*  927 */     PlotOrientation orientation = plot.getOrientation();
/*      */     
/*      */     double x1;
/*      */     double x1;
/*  931 */     if (this.useSeriesOffset) {
/*  932 */       x1 = domainAxis.getCategorySeriesMiddle(column, dataset.getColumnCount(), visibleRow, visibleRowCount, this.itemMargin, dataArea, plot.getDomainAxisEdge());
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*  937 */       x1 = domainAxis.getCategoryMiddle(column, getColumnCount(), dataArea, plot.getDomainAxisEdge());
/*      */     }
/*      */     
/*  940 */     double value = v.doubleValue();
/*  941 */     double y1 = rangeAxis.valueToJava2D(value, dataArea, plot.getRangeAxisEdge());
/*      */     
/*      */ 
/*  944 */     if ((pass == 0) && (getItemLineVisible(row, column)) && 
/*  945 */       (column != 0)) {
/*  946 */       Number previousValue = dataset.getValue(row, column - 1);
/*  947 */       if (previousValue != null)
/*      */       {
/*  949 */         double previous = previousValue.doubleValue();
/*      */         double x0;
/*  951 */         double x0; if (this.useSeriesOffset) {
/*  952 */           x0 = domainAxis.getCategorySeriesMiddle(column - 1, dataset.getColumnCount(), visibleRow, visibleRowCount, this.itemMargin, dataArea, plot.getDomainAxisEdge());
/*      */ 
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*      */ 
/*  959 */           x0 = domainAxis.getCategoryMiddle(column - 1, getColumnCount(), dataArea, plot.getDomainAxisEdge());
/*      */         }
/*      */         
/*      */ 
/*  963 */         double y0 = rangeAxis.valueToJava2D(previous, dataArea, plot.getRangeAxisEdge());
/*      */         
/*      */ 
/*  966 */         Line2D line = null;
/*  967 */         if (orientation == PlotOrientation.HORIZONTAL) {
/*  968 */           line = new Line2D.Double(y0, x0, y1, x1);
/*      */         }
/*  970 */         else if (orientation == PlotOrientation.VERTICAL) {
/*  971 */           line = new Line2D.Double(x0, y0, x1, y1);
/*      */         }
/*  973 */         g2.setPaint(getItemPaint(row, column));
/*  974 */         g2.setStroke(getItemStroke(row, column));
/*  975 */         g2.draw(line);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  980 */     if (pass == 1) {
/*  981 */       Shape shape = getItemShape(row, column);
/*  982 */       if (orientation == PlotOrientation.HORIZONTAL) {
/*  983 */         shape = ShapeUtilities.createTranslatedShape(shape, y1, x1);
/*      */       }
/*  985 */       else if (orientation == PlotOrientation.VERTICAL) {
/*  986 */         shape = ShapeUtilities.createTranslatedShape(shape, x1, y1);
/*      */       }
/*      */       
/*  989 */       if (getItemShapeVisible(row, column)) {
/*  990 */         if (getItemShapeFilled(row, column)) {
/*  991 */           if (this.useFillPaint) {
/*  992 */             g2.setPaint(getItemFillPaint(row, column));
/*      */           }
/*      */           else {
/*  995 */             g2.setPaint(getItemPaint(row, column));
/*      */           }
/*  997 */           g2.fill(shape);
/*      */         }
/*  999 */         if (this.drawOutlines) {
/* 1000 */           if (this.useOutlinePaint) {
/* 1001 */             g2.setPaint(getItemOutlinePaint(row, column));
/*      */           }
/*      */           else {
/* 1004 */             g2.setPaint(getItemPaint(row, column));
/*      */           }
/* 1006 */           g2.setStroke(getItemOutlineStroke(row, column));
/* 1007 */           g2.draw(shape);
/*      */         }
/*      */       }
/*      */       
/*      */ 
/* 1012 */       if (isItemLabelVisible(row, column)) {
/* 1013 */         if (orientation == PlotOrientation.HORIZONTAL) {
/* 1014 */           drawItemLabel(g2, orientation, dataset, row, column, y1, x1, value < 0.0D);
/*      */ 
/*      */         }
/* 1017 */         else if (orientation == PlotOrientation.VERTICAL) {
/* 1018 */           drawItemLabel(g2, orientation, dataset, row, column, x1, y1, value < 0.0D);
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1024 */       int datasetIndex = plot.indexOf(dataset);
/* 1025 */       updateCrosshairValues(state.getCrosshairState(), dataset.getRowKey(row), dataset.getColumnKey(column), value, datasetIndex, x1, y1, orientation);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1030 */       EntityCollection entities = state.getEntityCollection();
/* 1031 */       if (entities != null) {
/* 1032 */         addItemEntity(entities, dataset, row, column, shape);
/*      */       }
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
/* 1047 */     if (obj == this) {
/* 1048 */       return true;
/*      */     }
/* 1050 */     if (!(obj instanceof LineAndShapeRenderer)) {
/* 1051 */       return false;
/*      */     }
/*      */     
/* 1054 */     LineAndShapeRenderer that = (LineAndShapeRenderer)obj;
/* 1055 */     if (this.baseLinesVisible != that.baseLinesVisible) {
/* 1056 */       return false;
/*      */     }
/* 1058 */     if (!ObjectUtilities.equal(this.seriesLinesVisible, that.seriesLinesVisible))
/*      */     {
/* 1060 */       return false;
/*      */     }
/* 1062 */     if (!ObjectUtilities.equal(this.linesVisible, that.linesVisible)) {
/* 1063 */       return false;
/*      */     }
/* 1065 */     if (this.baseShapesVisible != that.baseShapesVisible) {
/* 1066 */       return false;
/*      */     }
/* 1068 */     if (!ObjectUtilities.equal(this.seriesShapesVisible, that.seriesShapesVisible))
/*      */     {
/* 1070 */       return false;
/*      */     }
/* 1072 */     if (!ObjectUtilities.equal(this.shapesVisible, that.shapesVisible)) {
/* 1073 */       return false;
/*      */     }
/* 1075 */     if (!ObjectUtilities.equal(this.shapesFilled, that.shapesFilled)) {
/* 1076 */       return false;
/*      */     }
/* 1078 */     if (!ObjectUtilities.equal(this.seriesShapesFilled, that.seriesShapesFilled))
/*      */     {
/* 1080 */       return false;
/*      */     }
/* 1082 */     if (this.baseShapesFilled != that.baseShapesFilled) {
/* 1083 */       return false;
/*      */     }
/* 1085 */     if (this.useOutlinePaint != that.useOutlinePaint) {
/* 1086 */       return false;
/*      */     }
/* 1088 */     if (this.useSeriesOffset != that.useSeriesOffset) {
/* 1089 */       return false;
/*      */     }
/* 1091 */     if (this.itemMargin != that.itemMargin) {
/* 1092 */       return false;
/*      */     }
/* 1094 */     return super.equals(obj);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object clone()
/*      */     throws CloneNotSupportedException
/*      */   {
/* 1105 */     LineAndShapeRenderer clone = (LineAndShapeRenderer)super.clone();
/* 1106 */     clone.seriesLinesVisible = ((BooleanList)this.seriesLinesVisible.clone());
/*      */     
/* 1108 */     clone.seriesShapesVisible = ((BooleanList)this.seriesShapesVisible.clone());
/*      */     
/* 1110 */     clone.seriesShapesFilled = ((BooleanList)this.seriesShapesFilled.clone());
/*      */     
/* 1112 */     return clone;
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/category/LineAndShapeRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */