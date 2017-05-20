/*     */ package org.jfree.chart.renderer.category;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.axis.CategoryAxis;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.data.DataUtilities;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.data.general.DatasetUtilities;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.util.PublicCloneable;
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
/*     */ public class StackedAreaRenderer
/*     */   extends AreaRenderer
/*     */   implements Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -3595635038460823663L;
/*     */   private boolean renderAsPercentages;
/*     */   
/*     */   public StackedAreaRenderer()
/*     */   {
/* 112 */     this(false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StackedAreaRenderer(boolean renderAsPercentages)
/*     */   {
/* 123 */     this.renderAsPercentages = renderAsPercentages;
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
/*     */   public boolean getRenderAsPercentages()
/*     */   {
/* 136 */     return this.renderAsPercentages;
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
/*     */   public void setRenderAsPercentages(boolean asPercentages)
/*     */   {
/* 149 */     this.renderAsPercentages = asPercentages;
/* 150 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getPassCount()
/*     */   {
/* 161 */     return 2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Range findRangeBounds(CategoryDataset dataset)
/*     */   {
/* 173 */     if (dataset == null) {
/* 174 */       return null;
/*     */     }
/* 176 */     if (this.renderAsPercentages) {
/* 177 */       return new Range(0.0D, 1.0D);
/*     */     }
/*     */     
/* 180 */     return DatasetUtilities.findStackedRangeBounds(dataset);
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
/*     */ 
/*     */ 
/*     */   public void drawItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset dataset, int row, int column, int pass)
/*     */   {
/* 209 */     if (!isSeriesVisible(row)) {
/* 210 */       return;
/*     */     }
/*     */     
/*     */ 
/* 214 */     Shape entityArea = null;
/* 215 */     EntityCollection entities = state.getEntityCollection();
/*     */     
/* 217 */     double y1 = 0.0D;
/* 218 */     Number n = dataset.getValue(row, column);
/* 219 */     if (n != null) {
/* 220 */       y1 = n.doubleValue();
/* 221 */       if (this.renderAsPercentages) {
/* 222 */         double total = DataUtilities.calculateColumnTotal(dataset, column, state.getVisibleSeriesArray());
/*     */         
/* 224 */         y1 /= total;
/*     */       }
/*     */     }
/* 227 */     double[] stack1 = getStackValues(dataset, row, column, state.getVisibleSeriesArray());
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 234 */     double xx1 = domainAxis.getCategoryMiddle(column, getColumnCount(), dataArea, plot.getDomainAxisEdge());
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 240 */     double y0 = 0.0D;
/* 241 */     n = dataset.getValue(row, Math.max(column - 1, 0));
/* 242 */     if (n != null) {
/* 243 */       y0 = n.doubleValue();
/* 244 */       if (this.renderAsPercentages) {
/* 245 */         double total = DataUtilities.calculateColumnTotal(dataset, Math.max(column - 1, 0), state.getVisibleSeriesArray());
/*     */         
/* 247 */         y0 /= total;
/*     */       }
/*     */     }
/* 250 */     double[] stack0 = getStackValues(dataset, row, Math.max(column - 1, 0), state.getVisibleSeriesArray());
/*     */     
/*     */ 
/*     */ 
/* 254 */     double xx0 = domainAxis.getCategoryStart(column, getColumnCount(), dataArea, plot.getDomainAxisEdge());
/*     */     
/*     */ 
/* 257 */     int itemCount = dataset.getColumnCount();
/* 258 */     double y2 = 0.0D;
/* 259 */     n = dataset.getValue(row, Math.min(column + 1, itemCount - 1));
/* 260 */     if (n != null) {
/* 261 */       y2 = n.doubleValue();
/* 262 */       if (this.renderAsPercentages) {
/* 263 */         double total = DataUtilities.calculateColumnTotal(dataset, Math.min(column + 1, itemCount - 1), state.getVisibleSeriesArray());
/*     */         
/*     */ 
/* 266 */         y2 /= total;
/*     */       }
/*     */     }
/* 269 */     double[] stack2 = getStackValues(dataset, row, Math.min(column + 1, itemCount - 1), state.getVisibleSeriesArray());
/*     */     
/*     */ 
/* 272 */     double xx2 = domainAxis.getCategoryEnd(column, getColumnCount(), dataArea, plot.getDomainAxisEdge());
/*     */     
/*     */ 
/*     */ 
/* 276 */     double xxLeft = xx0;
/* 277 */     double xxRight = xx2;
/*     */     
/* 279 */     double[] stackLeft = averageStackValues(stack0, stack1);
/* 280 */     double[] stackRight = averageStackValues(stack1, stack2);
/* 281 */     double[] adjStackLeft = adjustedStackValues(stack0, stack1);
/* 282 */     double[] adjStackRight = adjustedStackValues(stack1, stack2);
/*     */     
/*     */ 
/*     */ 
/* 286 */     RectangleEdge edge1 = plot.getRangeAxisEdge();
/*     */     
/* 288 */     GeneralPath left = new GeneralPath();
/* 289 */     GeneralPath right = new GeneralPath();
/* 290 */     float transY1; if (y1 >= 0.0D) {
/* 291 */       float transY1 = (float)rangeAxis.valueToJava2D(y1 + stack1[1], dataArea, edge1);
/*     */       
/* 293 */       float transStack1 = (float)rangeAxis.valueToJava2D(stack1[1], dataArea, edge1);
/*     */       
/* 295 */       float transStackLeft = (float)rangeAxis.valueToJava2D(adjStackLeft[1], dataArea, edge1);
/*     */       
/*     */ 
/*     */ 
/* 299 */       if (y0 >= 0.0D) {
/* 300 */         double yleft = (y0 + y1) / 2.0D + stackLeft[1];
/* 301 */         float transYLeft = (float)rangeAxis.valueToJava2D(yleft, dataArea, edge1);
/*     */         
/* 303 */         left.moveTo((float)xx1, transY1);
/* 304 */         left.lineTo((float)xx1, transStack1);
/* 305 */         left.lineTo((float)xxLeft, transStackLeft);
/* 306 */         left.lineTo((float)xxLeft, transYLeft);
/* 307 */         left.closePath();
/*     */       }
/*     */       else {
/* 310 */         left.moveTo((float)xx1, transStack1);
/* 311 */         left.lineTo((float)xx1, transY1);
/* 312 */         left.lineTo((float)xxLeft, transStackLeft);
/* 313 */         left.closePath();
/*     */       }
/*     */       
/* 316 */       float transStackRight = (float)rangeAxis.valueToJava2D(adjStackRight[1], dataArea, edge1);
/*     */       
/*     */ 
/* 319 */       if (y2 >= 0.0D) {
/* 320 */         double yright = (y1 + y2) / 2.0D + stackRight[1];
/* 321 */         float transYRight = (float)rangeAxis.valueToJava2D(yright, dataArea, edge1);
/*     */         
/* 323 */         right.moveTo((float)xx1, transStack1);
/* 324 */         right.lineTo((float)xx1, transY1);
/* 325 */         right.lineTo((float)xxRight, transYRight);
/* 326 */         right.lineTo((float)xxRight, transStackRight);
/* 327 */         right.closePath();
/*     */       }
/*     */       else {
/* 330 */         right.moveTo((float)xx1, transStack1);
/* 331 */         right.lineTo((float)xx1, transY1);
/* 332 */         right.lineTo((float)xxRight, transStackRight);
/* 333 */         right.closePath();
/*     */       }
/*     */     }
/*     */     else {
/* 337 */       transY1 = (float)rangeAxis.valueToJava2D(y1 + stack1[0], dataArea, edge1);
/*     */       
/* 339 */       float transStack1 = (float)rangeAxis.valueToJava2D(stack1[0], dataArea, edge1);
/*     */       
/* 341 */       float transStackLeft = (float)rangeAxis.valueToJava2D(adjStackLeft[0], dataArea, edge1);
/*     */       
/*     */ 
/*     */ 
/* 345 */       if (y0 >= 0.0D) {
/* 346 */         left.moveTo((float)xx1, transStack1);
/* 347 */         left.lineTo((float)xx1, transY1);
/* 348 */         left.lineTo((float)xxLeft, transStackLeft);
/* 349 */         left.clone();
/*     */       }
/*     */       else {
/* 352 */         double yleft = (y0 + y1) / 2.0D + stackLeft[0];
/* 353 */         float transYLeft = (float)rangeAxis.valueToJava2D(yleft, dataArea, edge1);
/*     */         
/* 355 */         left.moveTo((float)xx1, transY1);
/* 356 */         left.lineTo((float)xx1, transStack1);
/* 357 */         left.lineTo((float)xxLeft, transStackLeft);
/* 358 */         left.lineTo((float)xxLeft, transYLeft);
/* 359 */         left.closePath();
/*     */       }
/* 361 */       float transStackRight = (float)rangeAxis.valueToJava2D(adjStackRight[0], dataArea, edge1);
/*     */       
/*     */ 
/*     */ 
/* 365 */       if (y2 >= 0.0D) {
/* 366 */         right.moveTo((float)xx1, transStack1);
/* 367 */         right.lineTo((float)xx1, transY1);
/* 368 */         right.lineTo((float)xxRight, transStackRight);
/* 369 */         right.closePath();
/*     */       }
/*     */       else {
/* 372 */         double yright = (y1 + y2) / 2.0D + stackRight[0];
/* 373 */         float transYRight = (float)rangeAxis.valueToJava2D(yright, dataArea, edge1);
/*     */         
/* 375 */         right.moveTo((float)xx1, transStack1);
/* 376 */         right.lineTo((float)xx1, transY1);
/* 377 */         right.lineTo((float)xxRight, transYRight);
/* 378 */         right.lineTo((float)xxRight, transStackRight);
/* 379 */         right.closePath();
/*     */       }
/*     */     }
/*     */     
/* 383 */     if (pass == 0) {
/* 384 */       Paint itemPaint = getItemPaint(row, column);
/* 385 */       g2.setPaint(itemPaint);
/* 386 */       g2.fill(left);
/* 387 */       g2.fill(right);
/*     */       
/*     */ 
/* 390 */       if (entities != null) {
/* 391 */         GeneralPath gp = new GeneralPath(left);
/* 392 */         gp.append(right, false);
/* 393 */         entityArea = gp;
/* 394 */         addItemEntity(entities, dataset, row, column, entityArea);
/*     */       }
/*     */     }
/* 397 */     else if (pass == 1) {
/* 398 */       drawItemLabel(g2, plot.getOrientation(), dataset, row, column, xx1, transY1, y1 < 0.0D);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected double[] getStackValues(CategoryDataset dataset, int series, int index, int[] validRows)
/*     */   {
/* 419 */     double[] result = new double[2];
/* 420 */     double total = 0.0D;
/* 421 */     if (this.renderAsPercentages) {
/* 422 */       total = DataUtilities.calculateColumnTotal(dataset, index, validRows);
/*     */     }
/*     */     
/* 425 */     for (int i = 0; i < series; i++) {
/* 426 */       if (isSeriesVisible(i)) {
/* 427 */         double v = 0.0D;
/* 428 */         Number n = dataset.getValue(i, index);
/* 429 */         if (n != null) {
/* 430 */           v = n.doubleValue();
/* 431 */           if (this.renderAsPercentages) {
/* 432 */             v /= total;
/*     */           }
/*     */         }
/* 435 */         if (!Double.isNaN(v)) {
/* 436 */           if (v >= 0.0D) {
/* 437 */             result[1] += v;
/*     */           }
/*     */           else {
/* 440 */             result[0] += v;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 445 */     return result;
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
/*     */   private double[] averageStackValues(double[] stack1, double[] stack2)
/*     */   {
/* 458 */     double[] result = new double[2];
/* 459 */     result[0] = ((stack1[0] + stack2[0]) / 2.0D);
/* 460 */     result[1] = ((stack1[1] + stack2[1]) / 2.0D);
/* 461 */     return result;
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
/*     */   private double[] adjustedStackValues(double[] stack1, double[] stack2)
/*     */   {
/* 475 */     double[] result = new double[2];
/* 476 */     if ((stack1[0] == 0.0D) || (stack2[0] == 0.0D)) {
/* 477 */       result[0] = 0.0D;
/*     */     }
/*     */     else {
/* 480 */       result[0] = ((stack1[0] + stack2[0]) / 2.0D);
/*     */     }
/* 482 */     if ((stack1[1] == 0.0D) || (stack2[1] == 0.0D)) {
/* 483 */       result[1] = 0.0D;
/*     */     }
/*     */     else {
/* 486 */       result[1] = ((stack1[1] + stack2[1]) / 2.0D);
/*     */     }
/* 488 */     return result;
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
/* 499 */     if (obj == this) {
/* 500 */       return true;
/*     */     }
/* 502 */     if (!(obj instanceof StackedAreaRenderer)) {
/* 503 */       return false;
/*     */     }
/* 505 */     StackedAreaRenderer that = (StackedAreaRenderer)obj;
/* 506 */     if (this.renderAsPercentages != that.renderAsPercentages) {
/* 507 */       return false;
/*     */     }
/* 509 */     return super.equals(obj);
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
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   protected double getPreviousHeight(CategoryDataset dataset, int series, int category)
/*     */   {
/* 530 */     double result = 0.0D;
/*     */     
/* 532 */     double total = 0.0D;
/* 533 */     if (this.renderAsPercentages) {
/* 534 */       total = DataUtilities.calculateColumnTotal(dataset, category);
/*     */     }
/* 536 */     for (int i = 0; i < series; i++) {
/* 537 */       Number n = dataset.getValue(i, category);
/* 538 */       if (n != null) {
/* 539 */         double v = n.doubleValue();
/* 540 */         if (this.renderAsPercentages) {
/* 541 */           v /= total;
/*     */         }
/* 543 */         result += v;
/*     */       }
/*     */     }
/* 546 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/category/StackedAreaRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */