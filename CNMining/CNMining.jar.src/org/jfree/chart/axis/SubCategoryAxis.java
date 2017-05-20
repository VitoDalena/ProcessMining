/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jfree.chart.event.AxisChangeEvent;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.Plot;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.text.TextUtilities;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.ui.TextAnchor;
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
/*     */ public class SubCategoryAxis
/*     */   extends CategoryAxis
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -1279463299793228344L;
/*     */   private List subCategories;
/*  89 */   private Font subLabelFont = new Font("SansSerif", 0, 10);
/*     */   
/*     */ 
/*  92 */   private transient Paint subLabelPaint = Color.black;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SubCategoryAxis(String label)
/*     */   {
/* 100 */     super(label);
/* 101 */     this.subCategories = new ArrayList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addSubCategory(Comparable subCategory)
/*     */   {
/* 111 */     if (subCategory == null) {
/* 112 */       throw new IllegalArgumentException("Null 'subcategory' axis.");
/*     */     }
/* 114 */     this.subCategories.add(subCategory);
/* 115 */     notifyListeners(new AxisChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Font getSubLabelFont()
/*     */   {
/* 126 */     return this.subLabelFont;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSubLabelFont(Font font)
/*     */   {
/* 138 */     if (font == null) {
/* 139 */       throw new IllegalArgumentException("Null 'font' argument.");
/*     */     }
/* 141 */     this.subLabelFont = font;
/* 142 */     notifyListeners(new AxisChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getSubLabelPaint()
/*     */   {
/* 153 */     return this.subLabelPaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSubLabelPaint(Paint paint)
/*     */   {
/* 165 */     if (paint == null) {
/* 166 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 168 */     this.subLabelPaint = paint;
/* 169 */     notifyListeners(new AxisChangeEvent(this));
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
/*     */   public AxisSpace reserveSpace(Graphics2D g2, Plot plot, Rectangle2D plotArea, RectangleEdge edge, AxisSpace space)
/*     */   {
/* 188 */     if (space == null) {
/* 189 */       space = new AxisSpace();
/*     */     }
/*     */     
/*     */ 
/* 193 */     if (!isVisible()) {
/* 194 */       return space;
/*     */     }
/*     */     
/* 197 */     space = super.reserveSpace(g2, plot, plotArea, edge, space);
/* 198 */     double maxdim = getMaxDim(g2, edge);
/* 199 */     if (RectangleEdge.isTopOrBottom(edge)) {
/* 200 */       space.add(maxdim, edge);
/*     */     }
/* 202 */     else if (RectangleEdge.isLeftOrRight(edge)) {
/* 203 */       space.add(maxdim, edge);
/*     */     }
/* 205 */     return space;
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
/*     */   private double getMaxDim(Graphics2D g2, RectangleEdge edge)
/*     */   {
/* 218 */     double result = 0.0D;
/* 219 */     g2.setFont(this.subLabelFont);
/* 220 */     FontMetrics fm = g2.getFontMetrics();
/* 221 */     Iterator iterator = this.subCategories.iterator();
/* 222 */     while (iterator.hasNext()) {
/* 223 */       Comparable subcategory = (Comparable)iterator.next();
/* 224 */       String label = subcategory.toString();
/* 225 */       Rectangle2D bounds = TextUtilities.getTextBounds(label, g2, fm);
/* 226 */       double dim = 0.0D;
/* 227 */       if (RectangleEdge.isLeftOrRight(edge)) {
/* 228 */         dim = bounds.getWidth();
/*     */       }
/*     */       else {
/* 231 */         dim = bounds.getHeight();
/*     */       }
/* 233 */       result = Math.max(result, dim);
/*     */     }
/* 235 */     return result;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AxisState draw(Graphics2D g2, double cursor, Rectangle2D plotArea, Rectangle2D dataArea, RectangleEdge edge, PlotRenderingInfo plotState)
/*     */   {
/* 262 */     if (!isVisible()) {
/* 263 */       return new AxisState(cursor);
/*     */     }
/*     */     
/* 266 */     if (isAxisLineVisible()) {
/* 267 */       drawAxisLine(g2, cursor, dataArea, edge);
/*     */     }
/*     */     
/*     */ 
/* 271 */     AxisState state = new AxisState(cursor);
/* 272 */     state = drawSubCategoryLabels(g2, plotArea, dataArea, edge, state, plotState);
/*     */     
/* 274 */     state = drawCategoryLabels(g2, plotArea, dataArea, edge, state, plotState);
/*     */     
/* 276 */     state = drawLabel(getLabel(), g2, plotArea, dataArea, edge, state);
/*     */     
/* 278 */     return state;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected AxisState drawSubCategoryLabels(Graphics2D g2, Rectangle2D plotArea, Rectangle2D dataArea, RectangleEdge edge, AxisState state, PlotRenderingInfo plotState)
/*     */   {
/* 303 */     if (state == null) {
/* 304 */       throw new IllegalArgumentException("Null 'state' argument.");
/*     */     }
/*     */     
/* 307 */     g2.setFont(this.subLabelFont);
/* 308 */     g2.setPaint(this.subLabelPaint);
/* 309 */     CategoryPlot plot = (CategoryPlot)getPlot();
/* 310 */     int categoryCount = 0;
/* 311 */     CategoryDataset dataset = plot.getDataset();
/* 312 */     if (dataset != null) {
/* 313 */       categoryCount = dataset.getColumnCount();
/*     */     }
/*     */     
/* 316 */     double maxdim = getMaxDim(g2, edge);
/* 317 */     for (int categoryIndex = 0; categoryIndex < categoryCount; 
/* 318 */         categoryIndex++)
/*     */     {
/* 320 */       double x0 = 0.0D;
/* 321 */       double x1 = 0.0D;
/* 322 */       double y0 = 0.0D;
/* 323 */       double y1 = 0.0D;
/* 324 */       if (edge == RectangleEdge.TOP) {
/* 325 */         x0 = getCategoryStart(categoryIndex, categoryCount, dataArea, edge);
/*     */         
/* 327 */         x1 = getCategoryEnd(categoryIndex, categoryCount, dataArea, edge);
/*     */         
/* 329 */         y1 = state.getCursor();
/* 330 */         y0 = y1 - maxdim;
/*     */       }
/* 332 */       else if (edge == RectangleEdge.BOTTOM) {
/* 333 */         x0 = getCategoryStart(categoryIndex, categoryCount, dataArea, edge);
/*     */         
/* 335 */         x1 = getCategoryEnd(categoryIndex, categoryCount, dataArea, edge);
/*     */         
/* 337 */         y0 = state.getCursor();
/* 338 */         y1 = y0 + maxdim;
/*     */       }
/* 340 */       else if (edge == RectangleEdge.LEFT) {
/* 341 */         y0 = getCategoryStart(categoryIndex, categoryCount, dataArea, edge);
/*     */         
/* 343 */         y1 = getCategoryEnd(categoryIndex, categoryCount, dataArea, edge);
/*     */         
/* 345 */         x1 = state.getCursor();
/* 346 */         x0 = x1 - maxdim;
/*     */       }
/* 348 */       else if (edge == RectangleEdge.RIGHT) {
/* 349 */         y0 = getCategoryStart(categoryIndex, categoryCount, dataArea, edge);
/*     */         
/* 351 */         y1 = getCategoryEnd(categoryIndex, categoryCount, dataArea, edge);
/*     */         
/* 353 */         x0 = state.getCursor();
/* 354 */         x1 = x0 + maxdim;
/*     */       }
/* 356 */       Rectangle2D area = new Rectangle2D.Double(x0, y0, x1 - x0, y1 - y0);
/*     */       
/* 358 */       int subCategoryCount = this.subCategories.size();
/* 359 */       float width = (float)((x1 - x0) / subCategoryCount);
/* 360 */       float height = (float)((y1 - y0) / subCategoryCount);
/* 361 */       float xx = 0.0F;
/* 362 */       float yy = 0.0F;
/* 363 */       for (int i = 0; i < subCategoryCount; i++) {
/* 364 */         if (RectangleEdge.isTopOrBottom(edge)) {
/* 365 */           xx = (float)(x0 + (i + 0.5D) * width);
/* 366 */           yy = (float)area.getCenterY();
/*     */         }
/*     */         else {
/* 369 */           xx = (float)area.getCenterX();
/* 370 */           yy = (float)(y0 + (i + 0.5D) * height);
/*     */         }
/* 372 */         String label = this.subCategories.get(i).toString();
/* 373 */         TextUtilities.drawRotatedString(label, g2, xx, yy, TextAnchor.CENTER, 0.0D, TextAnchor.CENTER);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 378 */     if (edge.equals(RectangleEdge.TOP)) {
/* 379 */       double h = maxdim;
/* 380 */       state.cursorUp(h);
/*     */     }
/* 382 */     else if (edge.equals(RectangleEdge.BOTTOM)) {
/* 383 */       double h = maxdim;
/* 384 */       state.cursorDown(h);
/*     */     }
/* 386 */     else if (edge == RectangleEdge.LEFT) {
/* 387 */       double w = maxdim;
/* 388 */       state.cursorLeft(w);
/*     */     }
/* 390 */     else if (edge == RectangleEdge.RIGHT) {
/* 391 */       double w = maxdim;
/* 392 */       state.cursorRight(w);
/*     */     }
/* 394 */     return state;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 405 */     if (obj == this) {
/* 406 */       return true;
/*     */     }
/* 408 */     if (((obj instanceof SubCategoryAxis)) && (super.equals(obj))) {
/* 409 */       SubCategoryAxis axis = (SubCategoryAxis)obj;
/* 410 */       if (!this.subCategories.equals(axis.subCategories)) {
/* 411 */         return false;
/*     */       }
/* 413 */       if (!this.subLabelFont.equals(axis.subLabelFont)) {
/* 414 */         return false;
/*     */       }
/* 416 */       if (!this.subLabelPaint.equals(axis.subLabelPaint)) {
/* 417 */         return false;
/*     */       }
/* 419 */       return true;
/*     */     }
/* 421 */     return false;
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
/* 432 */     stream.defaultWriteObject();
/* 433 */     SerialUtilities.writePaint(this.subLabelPaint, stream);
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
/* 446 */     stream.defaultReadObject();
/* 447 */     this.subLabelPaint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/axis/SubCategoryAxis.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */