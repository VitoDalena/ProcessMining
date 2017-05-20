/*     */ package org.jfree.chart.renderer.xy;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.axis.CyclicNumberAxis;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.labels.XYToolTipGenerator;
/*     */ import org.jfree.chart.plot.CrosshairState;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.chart.urls.XYURLGenerator;
/*     */ import org.jfree.data.DomainOrder;
/*     */ import org.jfree.data.general.DatasetChangeListener;
/*     */ import org.jfree.data.general.DatasetGroup;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CyclicXYItemRenderer
/*     */   extends StandardXYItemRenderer
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 4035912243303764892L;
/*     */   
/*     */   public CyclicXYItemRenderer() {}
/*     */   
/*     */   public CyclicXYItemRenderer(int type)
/*     */   {
/*  95 */     super(type);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CyclicXYItemRenderer(int type, XYToolTipGenerator labelGenerator)
/*     */   {
/* 105 */     super(type, labelGenerator);
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
/*     */   public CyclicXYItemRenderer(int type, XYToolTipGenerator labelGenerator, XYURLGenerator urlGenerator)
/*     */   {
/* 118 */     super(type, labelGenerator, urlGenerator);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public void drawItem(Graphics2D g2, XYItemRendererState state, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState, int pass)
/*     */   {
/* 156 */     if ((!getPlotLines()) || ((!(domainAxis instanceof CyclicNumberAxis)) && (!(rangeAxis instanceof CyclicNumberAxis))) || (item <= 0))
/*     */     {
/* 158 */       super.drawItem(g2, state, dataArea, info, plot, domainAxis, rangeAxis, dataset, series, item, crosshairState, pass);
/*     */       
/* 160 */       return;
/*     */     }
/*     */     
/*     */ 
/* 164 */     double xn = dataset.getXValue(series, item - 1);
/* 165 */     double yn = dataset.getYValue(series, item - 1);
/*     */     
/* 167 */     if (Double.isNaN(yn)) {
/* 168 */       super.drawItem(g2, state, dataArea, info, plot, domainAxis, rangeAxis, dataset, series, item, crosshairState, pass);
/*     */       
/* 170 */       return;
/*     */     }
/* 172 */     double[] x = new double[2];
/* 173 */     double[] y = new double[2];
/* 174 */     x[0] = xn;
/* 175 */     y[0] = yn;
/*     */     
/*     */ 
/* 178 */     xn = dataset.getXValue(series, item);
/* 179 */     yn = dataset.getYValue(series, item);
/*     */     
/* 181 */     if (Double.isNaN(yn)) {
/* 182 */       return;
/*     */     }
/* 184 */     x[1] = xn;
/* 185 */     y[1] = yn;
/*     */     
/*     */ 
/* 188 */     double xcycleBound = NaN.0D;
/* 189 */     double ycycleBound = NaN.0D;
/* 190 */     boolean xBoundMapping = false;boolean yBoundMapping = false;
/* 191 */     CyclicNumberAxis cnax = null;CyclicNumberAxis cnay = null;
/*     */     
/* 193 */     if ((domainAxis instanceof CyclicNumberAxis)) {
/* 194 */       cnax = (CyclicNumberAxis)domainAxis;
/* 195 */       xcycleBound = cnax.getCycleBound();
/* 196 */       xBoundMapping = cnax.isBoundMappedToLastCycle();
/*     */       
/*     */ 
/*     */ 
/* 200 */       if ((x[0] != x[1]) && (((xcycleBound >= x[0]) && (xcycleBound <= x[1])) || ((xcycleBound >= x[1]) && (xcycleBound <= x[0]))))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/* 205 */         double[] nx = new double[3];
/* 206 */         double[] ny = new double[3];
/* 207 */         nx[0] = x[0];nx[2] = x[1];ny[0] = y[0];ny[2] = y[1];
/* 208 */         nx[1] = xcycleBound;
/* 209 */         ny[1] = ((y[1] - y[0]) * (xcycleBound - x[0]) / (x[1] - x[0]) + y[0]);
/*     */         
/* 211 */         x = nx;y = ny;
/*     */       }
/*     */     }
/*     */     
/* 215 */     if ((rangeAxis instanceof CyclicNumberAxis)) {
/* 216 */       cnay = (CyclicNumberAxis)rangeAxis;
/* 217 */       ycycleBound = cnay.getCycleBound();
/* 218 */       yBoundMapping = cnay.isBoundMappedToLastCycle();
/*     */       
/*     */ 
/* 221 */       if ((y[0] != y[1]) && (((ycycleBound >= y[0]) && (ycycleBound <= y[1])) || ((ycycleBound >= y[1]) && (ycycleBound <= y[0]))))
/*     */       {
/*     */ 
/* 224 */         double[] nx = new double[x.length + 1];
/* 225 */         double[] ny = new double[y.length + 1];
/* 226 */         nx[0] = x[0];nx[2] = x[1];ny[0] = y[0];ny[2] = y[1];
/* 227 */         ny[1] = ycycleBound;
/* 228 */         nx[1] = ((x[1] - x[0]) * (ycycleBound - y[0]) / (y[1] - y[0]) + x[0]);
/*     */         
/* 230 */         if (x.length == 3) {
/* 231 */           nx[3] = x[2];ny[3] = y[2];
/*     */         }
/* 233 */         x = nx;y = ny;
/*     */       }
/* 235 */       else if ((x.length == 3) && (y[1] != y[2]) && (((ycycleBound >= y[1]) && (ycycleBound <= y[2])) || ((ycycleBound >= y[2]) && (ycycleBound <= y[1]))))
/*     */       {
/*     */ 
/* 238 */         double[] nx = new double[4];
/* 239 */         double[] ny = new double[4];
/* 240 */         nx[0] = x[0];nx[1] = x[1];nx[3] = x[2];
/* 241 */         ny[0] = y[0];ny[1] = y[1];ny[3] = y[2];
/* 242 */         ny[2] = ycycleBound;
/* 243 */         nx[2] = ((x[2] - x[1]) * (ycycleBound - y[1]) / (y[2] - y[1]) + x[1]);
/*     */         
/* 245 */         x = nx;y = ny;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 250 */     if (x.length == 2) {
/* 251 */       super.drawItem(g2, state, dataArea, info, plot, domainAxis, rangeAxis, dataset, series, item, crosshairState, pass);
/*     */       
/* 253 */       return;
/*     */     }
/*     */     
/* 256 */     OverwriteDataSet newset = new OverwriteDataSet(x, y, dataset);
/*     */     
/* 258 */     if (cnax != null) {
/* 259 */       if (xcycleBound == x[0]) {
/* 260 */         cnax.setBoundMappedToLastCycle(x[1] <= xcycleBound);
/*     */       }
/* 262 */       if (xcycleBound == x[1]) {
/* 263 */         cnax.setBoundMappedToLastCycle(x[0] <= xcycleBound);
/*     */       }
/*     */     }
/* 266 */     if (cnay != null) {
/* 267 */       if (ycycleBound == y[0]) {
/* 268 */         cnay.setBoundMappedToLastCycle(y[1] <= ycycleBound);
/*     */       }
/* 270 */       if (ycycleBound == y[1]) {
/* 271 */         cnay.setBoundMappedToLastCycle(y[0] <= ycycleBound);
/*     */       }
/*     */     }
/* 274 */     super.drawItem(g2, state, dataArea, info, plot, domainAxis, rangeAxis, newset, series, 1, crosshairState, pass);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 279 */     if (cnax != null) {
/* 280 */       if (xcycleBound == x[1]) {
/* 281 */         cnax.setBoundMappedToLastCycle(x[2] <= xcycleBound);
/*     */       }
/* 283 */       if (xcycleBound == x[2]) {
/* 284 */         cnax.setBoundMappedToLastCycle(x[1] <= xcycleBound);
/*     */       }
/*     */     }
/* 287 */     if (cnay != null) {
/* 288 */       if (ycycleBound == y[1]) {
/* 289 */         cnay.setBoundMappedToLastCycle(y[2] <= ycycleBound);
/*     */       }
/* 291 */       if (ycycleBound == y[2]) {
/* 292 */         cnay.setBoundMappedToLastCycle(y[1] <= ycycleBound);
/*     */       }
/*     */     }
/* 295 */     super.drawItem(g2, state, dataArea, info, plot, domainAxis, rangeAxis, newset, series, 2, crosshairState, pass);
/*     */     
/*     */ 
/* 298 */     if (x.length == 4) {
/* 299 */       if (cnax != null) {
/* 300 */         if (xcycleBound == x[2]) {
/* 301 */           cnax.setBoundMappedToLastCycle(x[3] <= xcycleBound);
/*     */         }
/* 303 */         if (xcycleBound == x[3]) {
/* 304 */           cnax.setBoundMappedToLastCycle(x[2] <= xcycleBound);
/*     */         }
/*     */       }
/* 307 */       if (cnay != null) {
/* 308 */         if (ycycleBound == y[2]) {
/* 309 */           cnay.setBoundMappedToLastCycle(y[3] <= ycycleBound);
/*     */         }
/* 311 */         if (ycycleBound == y[3]) {
/* 312 */           cnay.setBoundMappedToLastCycle(y[2] <= ycycleBound);
/*     */         }
/*     */       }
/* 315 */       super.drawItem(g2, state, dataArea, info, plot, domainAxis, rangeAxis, newset, series, 3, crosshairState, pass);
/*     */     }
/*     */     
/*     */ 
/* 319 */     if (cnax != null) {
/* 320 */       cnax.setBoundMappedToLastCycle(xBoundMapping);
/*     */     }
/* 322 */     if (cnay != null) {
/* 323 */       cnay.setBoundMappedToLastCycle(yBoundMapping);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected static class OverwriteDataSet
/*     */     implements XYDataset
/*     */   {
/*     */     protected XYDataset delegateSet;
/*     */     
/*     */ 
/*     */ 
/*     */     Double[] x;
/*     */     
/*     */ 
/*     */ 
/*     */     Double[] y;
/*     */     
/*     */ 
/*     */ 
/*     */     public OverwriteDataSet(double[] x, double[] y, XYDataset delegateSet)
/*     */     {
/* 347 */       this.delegateSet = delegateSet;
/* 348 */       this.x = new Double[x.length];this.y = new Double[y.length];
/* 349 */       for (int i = 0; i < x.length; i++) {
/* 350 */         this.x[i] = new Double(x[i]);
/* 351 */         this.y[i] = new Double(y[i]);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public DomainOrder getDomainOrder()
/*     */     {
/* 361 */       return DomainOrder.NONE;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public int getItemCount(int series)
/*     */     {
/* 372 */       return this.x.length;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public Number getX(int series, int item)
/*     */     {
/* 384 */       return this.x[item];
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public double getXValue(int series, int item)
/*     */     {
/* 397 */       double result = NaN.0D;
/* 398 */       Number x = getX(series, item);
/* 399 */       if (x != null) {
/* 400 */         result = x.doubleValue();
/*     */       }
/* 402 */       return result;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public Number getY(int series, int item)
/*     */     {
/* 414 */       return this.y[item];
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public double getYValue(int series, int item)
/*     */     {
/* 427 */       double result = NaN.0D;
/* 428 */       Number y = getY(series, item);
/* 429 */       if (y != null) {
/* 430 */         result = y.doubleValue();
/*     */       }
/* 432 */       return result;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public int getSeriesCount()
/*     */     {
/* 441 */       return this.delegateSet.getSeriesCount();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public Comparable getSeriesKey(int series)
/*     */     {
/* 452 */       return this.delegateSet.getSeriesKey(series);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public int indexOf(Comparable seriesName)
/*     */     {
/* 463 */       return this.delegateSet.indexOf(seriesName);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void addChangeListener(DatasetChangeListener listener) {}
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void removeChangeListener(DatasetChangeListener listener) {}
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public DatasetGroup getGroup()
/*     */     {
/* 491 */       return this.delegateSet.getGroup();
/*     */     }
/*     */     
/*     */     public void setGroup(DatasetGroup group) {}
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/xy/CyclicXYItemRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */