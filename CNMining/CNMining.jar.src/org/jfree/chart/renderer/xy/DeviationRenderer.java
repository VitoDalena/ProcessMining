/*     */ package org.jfree.chart.renderer.xy;
/*     */ 
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.Composite;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.jfree.chart.ChartRenderingInfo;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.plot.CrosshairState;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.xy.IntervalXYDataset;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DeviationRenderer
/*     */   extends XYLineAndShapeRenderer
/*     */ {
/*     */   private float alpha;
/*     */   
/*     */   public static class State
/*     */     extends XYLineAndShapeRenderer.State
/*     */   {
/*     */     public List upperCoordinates;
/*     */     public List lowerCoordinates;
/*     */     
/*     */     public State(PlotRenderingInfo info)
/*     */     {
/* 104 */       super();
/* 105 */       this.lowerCoordinates = new ArrayList();
/* 106 */       this.upperCoordinates = new ArrayList();
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
/*     */   public DeviationRenderer()
/*     */   {
/* 119 */     this(true, true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DeviationRenderer(boolean lines, boolean shapes)
/*     */   {
/* 129 */     super(lines, shapes);
/* 130 */     super.setDrawSeriesLineAsPath(true);
/* 131 */     this.alpha = 0.5F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float getAlpha()
/*     */   {
/* 142 */     return this.alpha;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAlpha(float alpha)
/*     */   {
/* 154 */     if ((alpha < 0.0F) || (alpha > 1.0F)) {
/* 155 */       throw new IllegalArgumentException("Requires 'alpha' in the range 0.0 to 1.0.");
/*     */     }
/*     */     
/* 158 */     this.alpha = alpha;
/* 159 */     fireChangeEvent();
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
/*     */   public void setDrawSeriesLineAsPath(boolean flag) {}
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
/* 182 */     return findRangeBounds(dataset, true);
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
/*     */   public XYItemRendererState initialise(Graphics2D g2, Rectangle2D dataArea, XYPlot plot, XYDataset dataset, PlotRenderingInfo info)
/*     */   {
/* 199 */     State state = new State(info);
/* 200 */     state.seriesPath = new GeneralPath();
/* 201 */     state.setProcessVisibleItemsOnly(false);
/* 202 */     return state;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getPassCount()
/*     */   {
/* 212 */     return 3;
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
/*     */   protected boolean isItemPass(int pass)
/*     */   {
/* 226 */     return pass == 2;
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
/*     */   protected boolean isLinePass(int pass)
/*     */   {
/* 240 */     return pass == 1;
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
/*     */ 
/*     */   public void drawItem(Graphics2D g2, XYItemRendererState state, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState, int pass)
/*     */   {
/* 275 */     if (!getItemVisible(series, item)) {
/* 276 */       return;
/*     */     }
/*     */     
/*     */ 
/* 280 */     if (pass == 0) {
/* 281 */       IntervalXYDataset intervalDataset = (IntervalXYDataset)dataset;
/* 282 */       State drState = (State)state;
/*     */       
/* 284 */       double x = intervalDataset.getXValue(series, item);
/* 285 */       double yLow = intervalDataset.getStartYValue(series, item);
/* 286 */       double yHigh = intervalDataset.getEndYValue(series, item);
/*     */       
/* 288 */       RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
/* 289 */       RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
/*     */       
/* 291 */       double xx = domainAxis.valueToJava2D(x, dataArea, xAxisLocation);
/* 292 */       double yyLow = rangeAxis.valueToJava2D(yLow, dataArea, yAxisLocation);
/*     */       
/* 294 */       double yyHigh = rangeAxis.valueToJava2D(yHigh, dataArea, yAxisLocation);
/*     */       
/*     */ 
/* 297 */       PlotOrientation orientation = plot.getOrientation();
/* 298 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 299 */         drState.lowerCoordinates.add(new double[] { yyLow, xx });
/* 300 */         drState.upperCoordinates.add(new double[] { yyHigh, xx });
/*     */       }
/* 302 */       else if (orientation == PlotOrientation.VERTICAL) {
/* 303 */         drState.lowerCoordinates.add(new double[] { xx, yyLow });
/* 304 */         drState.upperCoordinates.add(new double[] { xx, yyHigh });
/*     */       }
/*     */       
/* 307 */       if (item == dataset.getItemCount(series) - 1)
/*     */       {
/*     */ 
/* 310 */         Composite originalComposite = g2.getComposite();
/* 311 */         g2.setComposite(AlphaComposite.getInstance(3, this.alpha));
/*     */         
/* 313 */         g2.setPaint(getItemFillPaint(series, item));
/* 314 */         GeneralPath area = new GeneralPath();
/* 315 */         double[] coords = (double[])drState.lowerCoordinates.get(0);
/* 316 */         area.moveTo((float)coords[0], (float)coords[1]);
/* 317 */         for (int i = 1; i < drState.lowerCoordinates.size(); i++) {
/* 318 */           coords = (double[])drState.lowerCoordinates.get(i);
/* 319 */           area.lineTo((float)coords[0], (float)coords[1]);
/*     */         }
/* 321 */         int count = drState.upperCoordinates.size();
/* 322 */         coords = (double[])drState.upperCoordinates.get(count - 1);
/* 323 */         area.lineTo((float)coords[0], (float)coords[1]);
/* 324 */         for (int i = count - 2; i >= 0; i--) {
/* 325 */           coords = (double[])drState.upperCoordinates.get(i);
/* 326 */           area.lineTo((float)coords[0], (float)coords[1]);
/*     */         }
/* 328 */         area.closePath();
/* 329 */         g2.fill(area);
/* 330 */         g2.setComposite(originalComposite);
/*     */         
/* 332 */         drState.lowerCoordinates.clear();
/* 333 */         drState.upperCoordinates.clear();
/*     */       }
/*     */     }
/* 336 */     if (isLinePass(pass))
/*     */     {
/*     */ 
/*     */ 
/* 340 */       if (item == 0) {
/* 341 */         State s = (State)state;
/* 342 */         s.seriesPath.reset();
/* 343 */         s.setLastPointGood(false);
/*     */       }
/*     */       
/* 346 */       if (getItemLineVisible(series, item)) {
/* 347 */         drawPrimaryLineAsPath(state, g2, plot, dataset, pass, series, item, domainAxis, rangeAxis, dataArea);
/*     */ 
/*     */       }
/*     */       
/*     */ 
/*     */     }
/* 353 */     else if (isItemPass(pass))
/*     */     {
/*     */ 
/* 356 */       EntityCollection entities = null;
/* 357 */       if (info != null) {
/* 358 */         entities = info.getOwner().getEntityCollection();
/*     */       }
/*     */       
/* 361 */       drawSecondaryPass(g2, plot, dataset, pass, series, item, domainAxis, dataArea, rangeAxis, crosshairState, entities);
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
/*     */   public boolean equals(Object obj)
/*     */   {
/* 374 */     if (obj == this) {
/* 375 */       return true;
/*     */     }
/* 377 */     if (!(obj instanceof DeviationRenderer)) {
/* 378 */       return false;
/*     */     }
/* 380 */     DeviationRenderer that = (DeviationRenderer)obj;
/* 381 */     if (this.alpha != that.alpha) {
/* 382 */       return false;
/*     */     }
/* 384 */     return super.equals(obj);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/xy/DeviationRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */