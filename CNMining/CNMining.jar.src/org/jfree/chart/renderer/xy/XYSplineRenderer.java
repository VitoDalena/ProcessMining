/*     */ package org.jfree.chart.renderer.xy;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.Vector;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
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
/*     */ public class XYSplineRenderer
/*     */   extends XYLineAndShapeRenderer
/*     */ {
/*     */   private Vector points;
/*     */   private int precision;
/*     */   
/*     */   public XYSplineRenderer()
/*     */   {
/*  88 */     this(5);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XYSplineRenderer(int precision)
/*     */   {
/*  98 */     if (precision <= 0) {
/*  99 */       throw new IllegalArgumentException("Requires precision > 0.");
/*     */     }
/* 101 */     this.precision = precision;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getPrecision()
/*     */   {
/* 112 */     return this.precision;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPrecision(int p)
/*     */   {
/* 124 */     if (p <= 0) {
/* 125 */       throw new IllegalArgumentException("Requires p > 0.");
/*     */     }
/* 127 */     this.precision = p;
/* 128 */     fireChangeEvent();
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
/*     */   public XYItemRendererState initialise(Graphics2D g2, Rectangle2D dataArea, XYPlot plot, XYDataset data, PlotRenderingInfo info)
/*     */   {
/* 150 */     XYLineAndShapeRenderer.State state = (XYLineAndShapeRenderer.State)super.initialise(g2, dataArea, plot, data, info);
/* 151 */     state.setProcessVisibleItemsOnly(false);
/* 152 */     this.points = new Vector();
/* 153 */     setDrawSeriesLineAsPath(true);
/* 154 */     return state;
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
/*     */   protected void drawPrimaryLineAsPath(XYItemRendererState state, Graphics2D g2, XYPlot plot, XYDataset dataset, int pass, int series, int item, ValueAxis domainAxis, ValueAxis rangeAxis, Rectangle2D dataArea)
/*     */   {
/* 180 */     RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
/* 181 */     RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
/*     */     
/*     */ 
/* 184 */     double x1 = dataset.getXValue(series, item);
/* 185 */     double y1 = dataset.getYValue(series, item);
/* 186 */     double transX1 = domainAxis.valueToJava2D(x1, dataArea, xAxisLocation);
/* 187 */     double transY1 = rangeAxis.valueToJava2D(y1, dataArea, yAxisLocation);
/*     */     
/*     */ 
/* 190 */     if ((!Double.isNaN(transX1)) && (!Double.isNaN(transY1))) {
/* 191 */       ControlPoint p = new ControlPoint(plot.getOrientation() == PlotOrientation.HORIZONTAL ? (float)transY1 : (float)transX1, plot.getOrientation() == PlotOrientation.HORIZONTAL ? (float)transX1 : (float)transY1);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 196 */       if (!this.points.contains(p)) {
/* 197 */         this.points.add(p);
/*     */       }
/*     */     }
/* 200 */     if (item == dataset.getItemCount(series) - 1) {
/* 201 */       XYLineAndShapeRenderer.State s = (XYLineAndShapeRenderer.State)state;
/*     */       
/* 203 */       if (this.points.size() > 1)
/*     */       {
/* 205 */         ControlPoint cp0 = (ControlPoint)this.points.get(0);
/* 206 */         s.seriesPath.moveTo(cp0.x, cp0.y);
/* 207 */         if (this.points.size() == 2)
/*     */         {
/*     */ 
/* 210 */           ControlPoint cp1 = (ControlPoint)this.points.get(1);
/* 211 */           s.seriesPath.lineTo(cp1.x, cp1.y);
/*     */         }
/*     */         else
/*     */         {
/* 215 */           int np = this.points.size();
/* 216 */           float[] d = new float[np];
/* 217 */           float[] x = new float[np];
/*     */           
/*     */ 
/* 220 */           float oldy = 0.0F;
/* 221 */           float oldt = 0.0F;
/*     */           
/* 223 */           float[] a = new float[np];
/*     */           
/*     */ 
/* 226 */           float[] h = new float[np];
/*     */           
/* 228 */           for (int i = 0; i < np; i++) {
/* 229 */             ControlPoint cpi = (ControlPoint)this.points.get(i);
/* 230 */             x[i] = cpi.x;
/* 231 */             d[i] = cpi.y;
/*     */           }
/*     */           
/* 234 */           for (int i = 1; i <= np - 1; i++) {
/* 235 */             x[i] -= x[(i - 1)];
/*     */           }
/* 237 */           float[] sub = new float[np - 1];
/* 238 */           float[] diag = new float[np - 1];
/* 239 */           float[] sup = new float[np - 1];
/*     */           
/* 241 */           for (int i = 1; i <= np - 2; i++) {
/* 242 */             diag[i] = ((h[i] + h[(i + 1)]) / 3.0F);
/* 243 */             sup[i] = (h[(i + 1)] / 6.0F);
/* 244 */             h[i] /= 6.0F;
/* 245 */             a[i] = ((d[(i + 1)] - d[i]) / h[(i + 1)] - (d[i] - d[(i - 1)]) / h[i]);
/*     */           }
/*     */           
/* 248 */           solveTridiag(sub, diag, sup, a, np - 2);
/*     */           
/*     */ 
/*     */ 
/* 252 */           oldt = x[0];
/* 253 */           oldy = d[0];
/* 254 */           s.seriesPath.moveTo(oldt, oldy);
/* 255 */           for (int i = 1; i <= np - 1; i++)
/*     */           {
/* 257 */             for (int j = 1; j <= this.precision; j++) {
/* 258 */               float t1 = h[i] * j / this.precision;
/* 259 */               float t2 = h[i] - t1;
/* 260 */               float y = ((-a[(i - 1)] / 6.0F * (t2 + h[i]) * t1 + d[(i - 1)]) * t2 + (-a[i] / 6.0F * (t1 + h[i]) * t2 + d[i]) * t1) / h[i];
/*     */               
/*     */ 
/* 263 */               float t = x[(i - 1)] + t1;
/* 264 */               s.seriesPath.lineTo(t, y);
/* 265 */               oldt = t;
/* 266 */               oldy = y;
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 271 */         drawFirstPassShape(g2, pass, series, item, s.seriesPath);
/*     */       }
/*     */       
/*     */ 
/* 275 */       this.points = new Vector();
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
/*     */   private void solveTridiag(float[] sub, float[] diag, float[] sup, float[] b, int n)
/*     */   {
/* 291 */     for (int i = 2; i <= n; i++) {
/* 292 */       sub[i] /= diag[(i - 1)];
/* 293 */       diag[i] -= sub[i] * sup[(i - 1)];
/* 294 */       b[i] -= sub[i] * b[(i - 1)];
/*     */     }
/* 296 */     b[n] /= diag[n];
/* 297 */     for (i = n - 1; i >= 1; i--) {
/* 298 */       b[i] = ((b[i] - sup[i] * b[(i + 1)]) / diag[i]);
/*     */     }
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
/* 310 */     if (obj == this) {
/* 311 */       return true;
/*     */     }
/* 313 */     if (!(obj instanceof XYSplineRenderer)) {
/* 314 */       return false;
/*     */     }
/* 316 */     XYSplineRenderer that = (XYSplineRenderer)obj;
/* 317 */     if (this.precision != that.precision) {
/* 318 */       return false;
/*     */     }
/* 320 */     return super.equals(obj);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   class ControlPoint
/*     */   {
/*     */     public float x;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public float y;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public ControlPoint(float x, float y)
/*     */     {
/* 341 */       this.x = x;
/* 342 */       this.y = y;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public boolean equals(Object obj)
/*     */     {
/* 353 */       if (obj == this) {
/* 354 */         return true;
/*     */       }
/* 356 */       if (!(obj instanceof ControlPoint)) {
/* 357 */         return false;
/*     */       }
/* 359 */       ControlPoint that = (ControlPoint)obj;
/* 360 */       if (this.x != that.x) {
/* 361 */         return false;
/*     */       }
/*     */       
/* 364 */       return true;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/xy/XYSplineRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */