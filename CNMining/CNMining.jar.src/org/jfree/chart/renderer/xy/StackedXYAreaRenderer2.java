/*     */ package org.jfree.chart.renderer.xy;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.ChartRenderingInfo;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.labels.XYToolTipGenerator;
/*     */ import org.jfree.chart.plot.CrosshairState;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.chart.urls.XYURLGenerator;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.xy.TableXYDataset;
/*     */ import org.jfree.data.xy.XYDataset;
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
/*     */ public class StackedXYAreaRenderer2
/*     */   extends XYAreaRenderer2
/*     */   implements Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 7752676509764539182L;
/*     */   private boolean roundXCoordinates;
/*     */   
/*     */   public StackedXYAreaRenderer2()
/*     */   {
/* 111 */     this(null, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StackedXYAreaRenderer2(XYToolTipGenerator labelGenerator, XYURLGenerator urlGenerator)
/*     */   {
/* 123 */     super(labelGenerator, urlGenerator);
/* 124 */     this.roundXCoordinates = true;
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
/*     */   public boolean getRoundXCoordinates()
/*     */   {
/* 138 */     return this.roundXCoordinates;
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
/*     */   public void setRoundXCoordinates(boolean round)
/*     */   {
/* 153 */     this.roundXCoordinates = round;
/* 154 */     fireChangeEvent();
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
/*     */   public Range findRangeBounds(XYDataset dataset)
/*     */   {
/* 167 */     if (dataset == null) {
/* 168 */       return null;
/*     */     }
/* 170 */     double min = Double.POSITIVE_INFINITY;
/* 171 */     double max = Double.NEGATIVE_INFINITY;
/* 172 */     TableXYDataset d = (TableXYDataset)dataset;
/* 173 */     int itemCount = d.getItemCount();
/* 174 */     for (int i = 0; i < itemCount; i++) {
/* 175 */       double[] stackValues = getStackValues((TableXYDataset)dataset, d.getSeriesCount(), i);
/*     */       
/* 177 */       min = Math.min(min, stackValues[0]);
/* 178 */       max = Math.max(max, stackValues[1]);
/*     */     }
/* 180 */     if (min == Double.POSITIVE_INFINITY) {
/* 181 */       return null;
/*     */     }
/* 183 */     return new Range(min, max);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getPassCount()
/*     */   {
/* 192 */     return 1;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void drawItem(Graphics2D g2, XYItemRendererState state, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState, int pass)
/*     */   {
/* 226 */     Shape entityArea = null;
/* 227 */     EntityCollection entities = null;
/* 228 */     if (info != null) {
/* 229 */       entities = info.getOwner().getEntityCollection();
/*     */     }
/*     */     
/* 232 */     TableXYDataset tdataset = (TableXYDataset)dataset;
/* 233 */     PlotOrientation orientation = plot.getOrientation();
/*     */     
/*     */ 
/* 236 */     double x1 = dataset.getXValue(series, item);
/* 237 */     double y1 = dataset.getYValue(series, item);
/* 238 */     if (Double.isNaN(y1)) {
/* 239 */       y1 = 0.0D;
/*     */     }
/* 241 */     double[] stack1 = getStackValues(tdataset, series, item);
/*     */     
/*     */ 
/*     */ 
/* 245 */     double x0 = dataset.getXValue(series, Math.max(item - 1, 0));
/* 246 */     double y0 = dataset.getYValue(series, Math.max(item - 1, 0));
/* 247 */     if (Double.isNaN(y0)) {
/* 248 */       y0 = 0.0D;
/*     */     }
/* 250 */     double[] stack0 = getStackValues(tdataset, series, Math.max(item - 1, 0));
/*     */     
/*     */ 
/* 253 */     int itemCount = dataset.getItemCount(series);
/* 254 */     double x2 = dataset.getXValue(series, Math.min(item + 1, itemCount - 1));
/*     */     
/* 256 */     double y2 = dataset.getYValue(series, Math.min(item + 1, itemCount - 1));
/*     */     
/* 258 */     if (Double.isNaN(y2)) {
/* 259 */       y2 = 0.0D;
/*     */     }
/* 261 */     double[] stack2 = getStackValues(tdataset, series, Math.min(item + 1, itemCount - 1));
/*     */     
/*     */ 
/* 264 */     double xleft = (x0 + x1) / 2.0D;
/* 265 */     double xright = (x1 + x2) / 2.0D;
/* 266 */     double[] stackLeft = averageStackValues(stack0, stack1);
/* 267 */     double[] stackRight = averageStackValues(stack1, stack2);
/* 268 */     double[] adjStackLeft = adjustedStackValues(stack0, stack1);
/* 269 */     double[] adjStackRight = adjustedStackValues(stack1, stack2);
/*     */     
/* 271 */     RectangleEdge edge0 = plot.getDomainAxisEdge();
/*     */     
/* 273 */     float transX1 = (float)domainAxis.valueToJava2D(x1, dataArea, edge0);
/* 274 */     float transXLeft = (float)domainAxis.valueToJava2D(xleft, dataArea, edge0);
/*     */     
/* 276 */     float transXRight = (float)domainAxis.valueToJava2D(xright, dataArea, edge0);
/*     */     
/*     */ 
/* 279 */     if (this.roundXCoordinates) {
/* 280 */       transX1 = Math.round(transX1);
/* 281 */       transXLeft = Math.round(transXLeft);
/* 282 */       transXRight = Math.round(transXRight);
/*     */     }
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
/* 303 */         if (orientation == PlotOrientation.VERTICAL) {
/* 304 */           left.moveTo(transX1, transY1);
/* 305 */           left.lineTo(transX1, transStack1);
/* 306 */           left.lineTo(transXLeft, transStackLeft);
/* 307 */           left.lineTo(transXLeft, transYLeft);
/*     */         }
/*     */         else {
/* 310 */           left.moveTo(transY1, transX1);
/* 311 */           left.lineTo(transStack1, transX1);
/* 312 */           left.lineTo(transStackLeft, transXLeft);
/* 313 */           left.lineTo(transYLeft, transXLeft);
/*     */         }
/* 315 */         left.closePath();
/*     */       }
/*     */       else {
/* 318 */         if (orientation == PlotOrientation.VERTICAL) {
/* 319 */           left.moveTo(transX1, transStack1);
/* 320 */           left.lineTo(transX1, transY1);
/* 321 */           left.lineTo(transXLeft, transStackLeft);
/*     */         }
/*     */         else {
/* 324 */           left.moveTo(transStack1, transX1);
/* 325 */           left.lineTo(transY1, transX1);
/* 326 */           left.lineTo(transStackLeft, transXLeft);
/*     */         }
/* 328 */         left.closePath();
/*     */       }
/*     */       
/* 331 */       float transStackRight = (float)rangeAxis.valueToJava2D(adjStackRight[1], dataArea, edge1);
/*     */       
/*     */ 
/* 334 */       if (y2 >= 0.0D) {
/* 335 */         double yright = (y1 + y2) / 2.0D + stackRight[1];
/* 336 */         float transYRight = (float)rangeAxis.valueToJava2D(yright, dataArea, edge1);
/*     */         
/* 338 */         if (orientation == PlotOrientation.VERTICAL) {
/* 339 */           right.moveTo(transX1, transStack1);
/* 340 */           right.lineTo(transX1, transY1);
/* 341 */           right.lineTo(transXRight, transYRight);
/* 342 */           right.lineTo(transXRight, transStackRight);
/*     */         }
/*     */         else {
/* 345 */           right.moveTo(transStack1, transX1);
/* 346 */           right.lineTo(transY1, transX1);
/* 347 */           right.lineTo(transYRight, transXRight);
/* 348 */           right.lineTo(transStackRight, transXRight);
/*     */         }
/* 350 */         right.closePath();
/*     */       }
/*     */       else {
/* 353 */         if (orientation == PlotOrientation.VERTICAL) {
/* 354 */           right.moveTo(transX1, transStack1);
/* 355 */           right.lineTo(transX1, transY1);
/* 356 */           right.lineTo(transXRight, transStackRight);
/*     */         }
/*     */         else {
/* 359 */           right.moveTo(transStack1, transX1);
/* 360 */           right.lineTo(transY1, transX1);
/* 361 */           right.lineTo(transStackRight, transXRight);
/*     */         }
/* 363 */         right.closePath();
/*     */       }
/*     */     }
/*     */     else {
/* 367 */       transY1 = (float)rangeAxis.valueToJava2D(y1 + stack1[0], dataArea, edge1);
/*     */       
/* 369 */       float transStack1 = (float)rangeAxis.valueToJava2D(stack1[0], dataArea, edge1);
/*     */       
/* 371 */       float transStackLeft = (float)rangeAxis.valueToJava2D(adjStackLeft[0], dataArea, edge1);
/*     */       
/*     */ 
/*     */ 
/* 375 */       if (y0 >= 0.0D) {
/* 376 */         if (orientation == PlotOrientation.VERTICAL) {
/* 377 */           left.moveTo(transX1, transStack1);
/* 378 */           left.lineTo(transX1, transY1);
/* 379 */           left.lineTo(transXLeft, transStackLeft);
/*     */         }
/*     */         else {
/* 382 */           left.moveTo(transStack1, transX1);
/* 383 */           left.lineTo(transY1, transX1);
/* 384 */           left.lineTo(transStackLeft, transXLeft);
/*     */         }
/* 386 */         left.clone();
/*     */       }
/*     */       else {
/* 389 */         double yleft = (y0 + y1) / 2.0D + stackLeft[0];
/* 390 */         float transYLeft = (float)rangeAxis.valueToJava2D(yleft, dataArea, edge1);
/*     */         
/* 392 */         if (orientation == PlotOrientation.VERTICAL) {
/* 393 */           left.moveTo(transX1, transY1);
/* 394 */           left.lineTo(transX1, transStack1);
/* 395 */           left.lineTo(transXLeft, transStackLeft);
/* 396 */           left.lineTo(transXLeft, transYLeft);
/*     */         }
/*     */         else {
/* 399 */           left.moveTo(transY1, transX1);
/* 400 */           left.lineTo(transStack1, transX1);
/* 401 */           left.lineTo(transStackLeft, transXLeft);
/* 402 */           left.lineTo(transYLeft, transXLeft);
/*     */         }
/* 404 */         left.closePath();
/*     */       }
/* 406 */       float transStackRight = (float)rangeAxis.valueToJava2D(adjStackRight[0], dataArea, edge1);
/*     */       
/*     */ 
/*     */ 
/* 410 */       if (y2 >= 0.0D) {
/* 411 */         if (orientation == PlotOrientation.VERTICAL) {
/* 412 */           right.moveTo(transX1, transStack1);
/* 413 */           right.lineTo(transX1, transY1);
/* 414 */           right.lineTo(transXRight, transStackRight);
/*     */         }
/*     */         else {
/* 417 */           right.moveTo(transStack1, transX1);
/* 418 */           right.lineTo(transY1, transX1);
/* 419 */           right.lineTo(transStackRight, transXRight);
/*     */         }
/* 421 */         right.closePath();
/*     */       }
/*     */       else {
/* 424 */         double yright = (y1 + y2) / 2.0D + stackRight[0];
/* 425 */         float transYRight = (float)rangeAxis.valueToJava2D(yright, dataArea, edge1);
/*     */         
/* 427 */         if (orientation == PlotOrientation.VERTICAL) {
/* 428 */           right.moveTo(transX1, transStack1);
/* 429 */           right.lineTo(transX1, transY1);
/* 430 */           right.lineTo(transXRight, transYRight);
/* 431 */           right.lineTo(transXRight, transStackRight);
/*     */         }
/*     */         else {
/* 434 */           right.moveTo(transStack1, transX1);
/* 435 */           right.lineTo(transY1, transX1);
/* 436 */           right.lineTo(transYRight, transXRight);
/* 437 */           right.lineTo(transStackRight, transXRight);
/*     */         }
/* 439 */         right.closePath();
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 444 */     Paint itemPaint = getItemPaint(series, item);
/* 445 */     if (pass == 0) {
/* 446 */       g2.setPaint(itemPaint);
/* 447 */       g2.fill(left);
/* 448 */       g2.fill(right);
/*     */     }
/*     */     
/*     */ 
/* 452 */     if (entities != null) {
/* 453 */       GeneralPath gp = new GeneralPath(left);
/* 454 */       gp.append(right, false);
/* 455 */       entityArea = gp;
/* 456 */       addEntity(entities, entityArea, dataset, series, item, transX1, transY1);
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
/*     */   private double[] getStackValues(TableXYDataset dataset, int series, int index)
/*     */   {
/* 477 */     double[] result = new double[2];
/* 478 */     for (int i = 0; i < series; i++) {
/* 479 */       double v = dataset.getYValue(i, index);
/* 480 */       if (!Double.isNaN(v)) {
/* 481 */         if (v >= 0.0D) {
/* 482 */           result[1] += v;
/*     */         }
/*     */         else {
/* 485 */           result[0] += v;
/*     */         }
/*     */       }
/*     */     }
/* 489 */     return result;
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
/* 502 */     double[] result = new double[2];
/* 503 */     result[0] = ((stack1[0] + stack2[0]) / 2.0D);
/* 504 */     result[1] = ((stack1[1] + stack2[1]) / 2.0D);
/* 505 */     return result;
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
/* 519 */     double[] result = new double[2];
/* 520 */     if ((stack1[0] == 0.0D) || (stack2[0] == 0.0D)) {
/* 521 */       result[0] = 0.0D;
/*     */     }
/*     */     else {
/* 524 */       result[0] = ((stack1[0] + stack2[0]) / 2.0D);
/*     */     }
/* 526 */     if ((stack1[1] == 0.0D) || (stack2[1] == 0.0D)) {
/* 527 */       result[1] = 0.0D;
/*     */     }
/*     */     else {
/* 530 */       result[1] = ((stack1[1] + stack2[1]) / 2.0D);
/*     */     }
/* 532 */     return result;
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
/* 543 */     if (obj == this) {
/* 544 */       return true;
/*     */     }
/* 546 */     if (!(obj instanceof StackedXYAreaRenderer2)) {
/* 547 */       return false;
/*     */     }
/* 549 */     StackedXYAreaRenderer2 that = (StackedXYAreaRenderer2)obj;
/* 550 */     if (this.roundXCoordinates != that.roundXCoordinates) {
/* 551 */       return false;
/*     */     }
/* 553 */     return super.equals(obj);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */     throws CloneNotSupportedException
/*     */   {
/* 564 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/xy/StackedXYAreaRenderer2.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */