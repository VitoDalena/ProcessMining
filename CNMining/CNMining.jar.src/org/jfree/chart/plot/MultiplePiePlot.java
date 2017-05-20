/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.Ellipse2D.Double;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.jfree.chart.ChartRenderingInfo;
/*     */ import org.jfree.chart.JFreeChart;
/*     */ import org.jfree.chart.LegendItem;
/*     */ import org.jfree.chart.LegendItemCollection;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.title.TextTitle;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.data.category.CategoryToPieDataset;
/*     */ import org.jfree.data.general.DatasetChangeEvent;
/*     */ import org.jfree.data.general.DatasetUtilities;
/*     */ import org.jfree.data.general.PieDataset;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.ui.RectangleInsets;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PaintUtilities;
/*     */ import org.jfree.util.ShapeUtilities;
/*     */ import org.jfree.util.TableOrder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MultiplePiePlot
/*     */   extends Plot
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -355377800470807389L;
/*     */   private JFreeChart pieChart;
/*     */   private CategoryDataset dataset;
/*     */   private TableOrder dataExtractOrder;
/* 115 */   private double limit = 0.0D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Comparable aggregatedItemsKey;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private transient Paint aggregatedItemsPaint;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private transient Map sectionPaints;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private transient Shape legendItemShape;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MultiplePiePlot()
/*     */   {
/* 149 */     this(null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MultiplePiePlot(CategoryDataset dataset)
/*     */   {
/* 159 */     setDataset(dataset);
/* 160 */     PiePlot piePlot = new PiePlot(null);
/* 161 */     piePlot.setIgnoreNullValues(true);
/* 162 */     this.pieChart = new JFreeChart(piePlot);
/* 163 */     this.pieChart.removeLegend();
/* 164 */     this.dataExtractOrder = TableOrder.BY_COLUMN;
/* 165 */     this.pieChart.setBackgroundPaint(null);
/* 166 */     TextTitle seriesTitle = new TextTitle("Series Title", new Font("SansSerif", 1, 12));
/*     */     
/* 168 */     seriesTitle.setPosition(RectangleEdge.BOTTOM);
/* 169 */     this.pieChart.setTitle(seriesTitle);
/* 170 */     this.aggregatedItemsKey = "Other";
/* 171 */     this.aggregatedItemsPaint = Color.lightGray;
/* 172 */     this.sectionPaints = new HashMap();
/* 173 */     this.legendItemShape = new Ellipse2D.Double(-4.0D, -4.0D, 8.0D, 8.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CategoryDataset getDataset()
/*     */   {
/* 182 */     return this.dataset;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataset(CategoryDataset dataset)
/*     */   {
/* 194 */     if (this.dataset != null) {
/* 195 */       this.dataset.removeChangeListener(this);
/*     */     }
/*     */     
/*     */ 
/* 199 */     this.dataset = dataset;
/* 200 */     if (dataset != null) {
/* 201 */       setDatasetGroup(dataset.getGroup());
/* 202 */       dataset.addChangeListener(this);
/*     */     }
/*     */     
/*     */ 
/* 206 */     datasetChanged(new DatasetChangeEvent(this, dataset));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public JFreeChart getPieChart()
/*     */   {
/* 219 */     return this.pieChart;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPieChart(JFreeChart pieChart)
/*     */   {
/* 231 */     if (pieChart == null) {
/* 232 */       throw new IllegalArgumentException("Null 'pieChart' argument.");
/*     */     }
/* 234 */     if (!(pieChart.getPlot() instanceof PiePlot)) {
/* 235 */       throw new IllegalArgumentException("The 'pieChart' argument must be a chart based on a PiePlot.");
/*     */     }
/*     */     
/* 238 */     this.pieChart = pieChart;
/* 239 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TableOrder getDataExtractOrder()
/*     */   {
/* 248 */     return this.dataExtractOrder;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataExtractOrder(TableOrder order)
/*     */   {
/* 258 */     if (order == null) {
/* 259 */       throw new IllegalArgumentException("Null 'order' argument");
/*     */     }
/* 261 */     this.dataExtractOrder = order;
/* 262 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getLimit()
/*     */   {
/* 272 */     return this.limit;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLimit(double limit)
/*     */   {
/* 282 */     this.limit = limit;
/* 283 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Comparable getAggregatedItemsKey()
/*     */   {
/* 295 */     return this.aggregatedItemsKey;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAggregatedItemsKey(Comparable key)
/*     */   {
/* 307 */     if (key == null) {
/* 308 */       throw new IllegalArgumentException("Null 'key' argument.");
/*     */     }
/* 310 */     this.aggregatedItemsKey = key;
/* 311 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getAggregatedItemsPaint()
/*     */   {
/* 323 */     return this.aggregatedItemsPaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAggregatedItemsPaint(Paint paint)
/*     */   {
/* 335 */     if (paint == null) {
/* 336 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 338 */     this.aggregatedItemsPaint = paint;
/* 339 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getPlotType()
/*     */   {
/* 348 */     return "Multiple Pie Plot";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Shape getLegendItemShape()
/*     */   {
/* 362 */     return this.legendItemShape;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLegendItemShape(Shape shape)
/*     */   {
/* 376 */     if (shape == null) {
/* 377 */       throw new IllegalArgumentException("Null 'shape' argument.");
/*     */     }
/* 379 */     this.legendItemShape = shape;
/* 380 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor, PlotState parentState, PlotRenderingInfo info)
/*     */   {
/* 401 */     RectangleInsets insets = getInsets();
/* 402 */     insets.trim(area);
/* 403 */     drawBackground(g2, area);
/* 404 */     drawOutline(g2, area);
/*     */     
/*     */ 
/* 407 */     if (DatasetUtilities.isEmptyOrNull(this.dataset)) {
/* 408 */       drawNoDataMessage(g2, area);
/* 409 */       return;
/*     */     }
/*     */     
/* 412 */     int pieCount = 0;
/* 413 */     if (this.dataExtractOrder == TableOrder.BY_ROW) {
/* 414 */       pieCount = this.dataset.getRowCount();
/*     */     }
/*     */     else {
/* 417 */       pieCount = this.dataset.getColumnCount();
/*     */     }
/*     */     
/*     */ 
/* 421 */     int displayCols = (int)Math.ceil(Math.sqrt(pieCount));
/* 422 */     int displayRows = (int)Math.ceil(pieCount / displayCols);
/*     */     
/*     */ 
/*     */ 
/* 426 */     if ((displayCols > displayRows) && (area.getWidth() < area.getHeight())) {
/* 427 */       int temp = displayCols;
/* 428 */       displayCols = displayRows;
/* 429 */       displayRows = temp;
/*     */     }
/*     */     
/* 432 */     prefetchSectionPaints();
/*     */     
/* 434 */     int x = (int)area.getX();
/* 435 */     int y = (int)area.getY();
/* 436 */     int width = (int)area.getWidth() / displayCols;
/* 437 */     int height = (int)area.getHeight() / displayRows;
/* 438 */     int row = 0;
/* 439 */     int column = 0;
/* 440 */     int diff = displayRows * displayCols - pieCount;
/* 441 */     int xoffset = 0;
/* 442 */     Rectangle rect = new Rectangle();
/*     */     
/* 444 */     for (int pieIndex = 0; pieIndex < pieCount; pieIndex++) {
/* 445 */       rect.setBounds(x + xoffset + width * column, y + height * row, width, height);
/*     */       
/*     */ 
/* 448 */       String title = null;
/* 449 */       if (this.dataExtractOrder == TableOrder.BY_ROW) {
/* 450 */         title = this.dataset.getRowKey(pieIndex).toString();
/*     */       }
/*     */       else {
/* 453 */         title = this.dataset.getColumnKey(pieIndex).toString();
/*     */       }
/* 455 */       this.pieChart.setTitle(title);
/*     */       
/* 457 */       PieDataset piedataset = null;
/* 458 */       PieDataset dd = new CategoryToPieDataset(this.dataset, this.dataExtractOrder, pieIndex);
/*     */       
/* 460 */       if (this.limit > 0.0D) {
/* 461 */         piedataset = DatasetUtilities.createConsolidatedPieDataset(dd, this.aggregatedItemsKey, this.limit);
/*     */       }
/*     */       else
/*     */       {
/* 465 */         piedataset = dd;
/*     */       }
/* 467 */       PiePlot piePlot = (PiePlot)this.pieChart.getPlot();
/* 468 */       piePlot.setDataset(piedataset);
/* 469 */       piePlot.setPieIndex(pieIndex);
/*     */       
/*     */ 
/* 472 */       for (int i = 0; i < piedataset.getItemCount(); i++) {
/* 473 */         Comparable key = piedataset.getKey(i);
/*     */         Paint p;
/* 475 */         Paint p; if (key.equals(this.aggregatedItemsKey)) {
/* 476 */           p = this.aggregatedItemsPaint;
/*     */         }
/*     */         else {
/* 479 */           p = (Paint)this.sectionPaints.get(key);
/*     */         }
/* 481 */         piePlot.setSectionPaint(key, p);
/*     */       }
/*     */       
/* 484 */       ChartRenderingInfo subinfo = null;
/* 485 */       if (info != null) {
/* 486 */         subinfo = new ChartRenderingInfo();
/*     */       }
/* 488 */       this.pieChart.draw(g2, rect, subinfo);
/* 489 */       if (info != null) {
/* 490 */         info.getOwner().getEntityCollection().addAll(subinfo.getEntityCollection());
/*     */         
/* 492 */         info.addSubplotInfo(subinfo.getPlotInfo());
/*     */       }
/*     */       
/* 495 */       column++;
/* 496 */       if (column == displayCols) {
/* 497 */         column = 0;
/* 498 */         row++;
/*     */         
/* 500 */         if ((row == displayRows - 1) && (diff != 0)) {
/* 501 */           xoffset = diff * width / 2;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void prefetchSectionPaints()
/*     */   {
/* 520 */     PiePlot piePlot = (PiePlot)getPieChart().getPlot();
/*     */     
/* 522 */     if (this.dataExtractOrder == TableOrder.BY_ROW)
/*     */     {
/* 524 */       for (int c = 0; c < this.dataset.getColumnCount(); c++) {
/* 525 */         Comparable key = this.dataset.getColumnKey(c);
/* 526 */         Paint p = piePlot.getSectionPaint(key);
/* 527 */         if (p == null) {
/* 528 */           p = (Paint)this.sectionPaints.get(key);
/* 529 */           if (p == null) {
/* 530 */             p = getDrawingSupplier().getNextPaint();
/*     */           }
/*     */         }
/* 533 */         this.sectionPaints.put(key, p);
/*     */       }
/*     */       
/*     */     }
/*     */     else {
/* 538 */       for (int r = 0; r < this.dataset.getRowCount(); r++) {
/* 539 */         Comparable key = this.dataset.getRowKey(r);
/* 540 */         Paint p = piePlot.getSectionPaint(key);
/* 541 */         if (p == null) {
/* 542 */           p = (Paint)this.sectionPaints.get(key);
/* 543 */           if (p == null) {
/* 544 */             p = getDrawingSupplier().getNextPaint();
/*     */           }
/*     */         }
/* 547 */         this.sectionPaints.put(key, p);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public LegendItemCollection getLegendItems()
/*     */   {
/* 560 */     LegendItemCollection result = new LegendItemCollection();
/* 561 */     if (this.dataset == null) {
/* 562 */       return result;
/*     */     }
/*     */     
/* 565 */     List keys = null;
/* 566 */     prefetchSectionPaints();
/* 567 */     if (this.dataExtractOrder == TableOrder.BY_ROW) {
/* 568 */       keys = this.dataset.getColumnKeys();
/*     */     }
/* 570 */     else if (this.dataExtractOrder == TableOrder.BY_COLUMN) {
/* 571 */       keys = this.dataset.getRowKeys();
/*     */     }
/*     */     
/* 574 */     if (keys != null) {
/* 575 */       int section = 0;
/* 576 */       Iterator iterator = keys.iterator();
/* 577 */       while (iterator.hasNext()) {
/* 578 */         Comparable key = (Comparable)iterator.next();
/* 579 */         String label = key.toString();
/* 580 */         String description = label;
/* 581 */         Paint paint = (Paint)this.sectionPaints.get(key);
/* 582 */         LegendItem item = new LegendItem(label, description, null, null, getLegendItemShape(), paint, Plot.DEFAULT_OUTLINE_STROKE, paint);
/*     */         
/*     */ 
/* 585 */         item.setDataset(getDataset());
/* 586 */         result.add(item);
/* 587 */         section++;
/*     */       }
/*     */     }
/* 590 */     if (this.limit > 0.0D) {
/* 591 */       result.add(new LegendItem(this.aggregatedItemsKey.toString(), this.aggregatedItemsKey.toString(), null, null, getLegendItemShape(), this.aggregatedItemsPaint, Plot.DEFAULT_OUTLINE_STROKE, this.aggregatedItemsPaint));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 596 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 609 */     if (obj == this) {
/* 610 */       return true;
/*     */     }
/* 612 */     if (!(obj instanceof MultiplePiePlot)) {
/* 613 */       return false;
/*     */     }
/* 615 */     MultiplePiePlot that = (MultiplePiePlot)obj;
/* 616 */     if (this.dataExtractOrder != that.dataExtractOrder) {
/* 617 */       return false;
/*     */     }
/* 619 */     if (this.limit != that.limit) {
/* 620 */       return false;
/*     */     }
/* 622 */     if (!this.aggregatedItemsKey.equals(that.aggregatedItemsKey)) {
/* 623 */       return false;
/*     */     }
/* 625 */     if (!PaintUtilities.equal(this.aggregatedItemsPaint, that.aggregatedItemsPaint))
/*     */     {
/* 627 */       return false;
/*     */     }
/* 629 */     if (!ObjectUtilities.equal(this.pieChart, that.pieChart)) {
/* 630 */       return false;
/*     */     }
/* 632 */     if (!ShapeUtilities.equal(this.legendItemShape, that.legendItemShape)) {
/* 633 */       return false;
/*     */     }
/* 635 */     if (!super.equals(obj)) {
/* 636 */       return false;
/*     */     }
/* 638 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */     throws CloneNotSupportedException
/*     */   {
/* 650 */     MultiplePiePlot clone = (MultiplePiePlot)super.clone();
/* 651 */     clone.pieChart = ((JFreeChart)this.pieChart.clone());
/* 652 */     clone.sectionPaints = new HashMap(this.sectionPaints);
/* 653 */     clone.legendItemShape = ShapeUtilities.clone(this.legendItemShape);
/* 654 */     return clone;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void writeObject(ObjectOutputStream stream)
/*     */     throws IOException
/*     */   {
/* 665 */     stream.defaultWriteObject();
/* 666 */     SerialUtilities.writePaint(this.aggregatedItemsPaint, stream);
/* 667 */     SerialUtilities.writeShape(this.legendItemShape, stream);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void readObject(ObjectInputStream stream)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 680 */     stream.defaultReadObject();
/* 681 */     this.aggregatedItemsPaint = SerialUtilities.readPaint(stream);
/* 682 */     this.legendItemShape = SerialUtilities.readShape(stream);
/* 683 */     this.sectionPaints = new HashMap();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/MultiplePiePlot.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */