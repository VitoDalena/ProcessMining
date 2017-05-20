/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Arc2D;
/*     */ import java.awt.geom.Arc2D.Double;
/*     */ import java.awt.geom.Ellipse2D;
/*     */ import java.awt.geom.Ellipse2D.Double;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.io.Serializable;
/*     */ import java.util.ResourceBundle;
/*     */ import org.jfree.chart.LegendItemCollection;
/*     */ import org.jfree.chart.event.RendererChangeEvent;
/*     */ import org.jfree.chart.event.RendererChangeListener;
/*     */ import org.jfree.chart.renderer.WaferMapRenderer;
/*     */ import org.jfree.chart.util.ResourceBundleWrapper;
/*     */ import org.jfree.data.general.DatasetChangeEvent;
/*     */ import org.jfree.data.general.WaferMapDataset;
/*     */ import org.jfree.ui.RectangleInsets;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WaferMapPlot
/*     */   extends Plot
/*     */   implements RendererChangeListener, Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 4668320403707308155L;
/*  83 */   public static final Stroke DEFAULT_GRIDLINE_STROKE = new BasicStroke(0.5F, 0, 2, 0.0F, new float[] { 2.0F, 2.0F }, 0.0F);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  91 */   public static final Paint DEFAULT_GRIDLINE_PAINT = Color.lightGray;
/*     */   
/*     */ 
/*     */   public static final boolean DEFAULT_CROSSHAIR_VISIBLE = false;
/*     */   
/*     */ 
/*  97 */   public static final Stroke DEFAULT_CROSSHAIR_STROKE = DEFAULT_GRIDLINE_STROKE;
/*     */   
/*     */ 
/*     */ 
/* 101 */   public static final Paint DEFAULT_CROSSHAIR_PAINT = Color.blue;
/*     */   
/*     */ 
/* 104 */   protected static ResourceBundle localizationResources = ResourceBundleWrapper.getBundle("org.jfree.chart.plot.LocalizationBundle");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private PlotOrientation orientation;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private WaferMapDataset dataset;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private WaferMapRenderer renderer;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public WaferMapPlot()
/*     */   {
/* 127 */     this(null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public WaferMapPlot(WaferMapDataset dataset)
/*     */   {
/* 136 */     this(dataset, null);
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
/*     */   public WaferMapPlot(WaferMapDataset dataset, WaferMapRenderer renderer)
/*     */   {
/* 149 */     this.orientation = PlotOrientation.VERTICAL;
/*     */     
/* 151 */     this.dataset = dataset;
/* 152 */     if (dataset != null) {
/* 153 */       dataset.addChangeListener(this);
/*     */     }
/*     */     
/* 156 */     this.renderer = renderer;
/* 157 */     if (renderer != null) {
/* 158 */       renderer.setPlot(this);
/* 159 */       renderer.addChangeListener(this);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getPlotType()
/*     */   {
/* 170 */     return "WMAP_Plot";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public WaferMapDataset getDataset()
/*     */   {
/* 179 */     return this.dataset;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataset(WaferMapDataset dataset)
/*     */   {
/* 191 */     if (this.dataset != null) {
/* 192 */       this.dataset.removeChangeListener(this);
/*     */     }
/*     */     
/*     */ 
/* 196 */     this.dataset = dataset;
/* 197 */     if (dataset != null) {
/* 198 */       setDatasetGroup(dataset.getGroup());
/* 199 */       dataset.addChangeListener(this);
/*     */     }
/*     */     
/*     */ 
/* 203 */     datasetChanged(new DatasetChangeEvent(this, dataset));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setRenderer(WaferMapRenderer renderer)
/*     */   {
/* 214 */     if (this.renderer != null) {
/* 215 */       this.renderer.removeChangeListener(this);
/*     */     }
/* 217 */     this.renderer = renderer;
/* 218 */     if (renderer != null) {
/* 219 */       renderer.setPlot(this);
/*     */     }
/* 221 */     fireChangeEvent();
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
/*     */   public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor, PlotState state, PlotRenderingInfo info)
/*     */   {
/* 238 */     boolean b1 = area.getWidth() <= 10.0D;
/* 239 */     boolean b2 = area.getHeight() <= 10.0D;
/* 240 */     if ((b1) || (b2)) {
/* 241 */       return;
/*     */     }
/*     */     
/*     */ 
/* 245 */     if (info != null) {
/* 246 */       info.setPlotArea(area);
/*     */     }
/*     */     
/*     */ 
/* 250 */     RectangleInsets insets = getInsets();
/* 251 */     insets.trim(area);
/*     */     
/* 253 */     drawChipGrid(g2, area);
/* 254 */     drawWaferEdge(g2, area);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void drawChipGrid(Graphics2D g2, Rectangle2D plotArea)
/*     */   {
/* 266 */     Shape savedClip = g2.getClip();
/* 267 */     g2.setClip(getWaferEdge(plotArea));
/* 268 */     Rectangle2D chip = new Rectangle2D.Double();
/* 269 */     int xchips = 35;
/* 270 */     int ychips = 20;
/* 271 */     double space = 1.0D;
/* 272 */     if (this.dataset != null) {
/* 273 */       xchips = this.dataset.getMaxChipX() + 2;
/* 274 */       ychips = this.dataset.getMaxChipY() + 2;
/* 275 */       space = this.dataset.getChipSpace();
/*     */     }
/* 277 */     double startX = plotArea.getX();
/* 278 */     double startY = plotArea.getY();
/* 279 */     double chipWidth = 1.0D;
/* 280 */     double chipHeight = 1.0D;
/* 281 */     if (plotArea.getWidth() != plotArea.getHeight()) {
/* 282 */       double major = 0.0D;
/* 283 */       double minor = 0.0D;
/* 284 */       if (plotArea.getWidth() > plotArea.getHeight()) {
/* 285 */         major = plotArea.getWidth();
/* 286 */         minor = plotArea.getHeight();
/*     */       }
/*     */       else {
/* 289 */         major = plotArea.getHeight();
/* 290 */         minor = plotArea.getWidth();
/*     */       }
/*     */       
/* 293 */       if (plotArea.getWidth() == minor) {
/* 294 */         startY += (major - minor) / 2.0D;
/* 295 */         chipWidth = (plotArea.getWidth() - (space * xchips - 1.0D)) / xchips;
/*     */         
/* 297 */         chipHeight = (plotArea.getWidth() - (space * ychips - 1.0D)) / ychips;
/*     */       }
/*     */       else
/*     */       {
/* 301 */         startX += (major - minor) / 2.0D;
/* 302 */         chipWidth = (plotArea.getHeight() - (space * xchips - 1.0D)) / xchips;
/*     */         
/* 304 */         chipHeight = (plotArea.getHeight() - (space * ychips - 1.0D)) / ychips;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 309 */     for (int x = 1; x <= xchips; x++) {
/* 310 */       double upperLeftX = startX - chipWidth + chipWidth * x + space * (x - 1);
/*     */       
/* 312 */       for (int y = 1; y <= ychips; y++) {
/* 313 */         double upperLeftY = startY - chipHeight + chipHeight * y + space * (y - 1);
/*     */         
/* 315 */         chip.setFrame(upperLeftX, upperLeftY, chipWidth, chipHeight);
/* 316 */         g2.setColor(Color.white);
/* 317 */         if (this.dataset.getChipValue(x - 1, ychips - y - 1) != null) {
/* 318 */           g2.setPaint(this.renderer.getChipColor(this.dataset.getChipValue(x - 1, ychips - y - 1)));
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 324 */         g2.fill(chip);
/* 325 */         g2.setColor(Color.lightGray);
/* 326 */         g2.draw(chip);
/*     */       }
/*     */     }
/* 329 */     g2.setClip(savedClip);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Ellipse2D getWaferEdge(Rectangle2D plotArea)
/*     */   {
/* 340 */     Ellipse2D edge = new Ellipse2D.Double();
/* 341 */     double diameter = plotArea.getWidth();
/* 342 */     double upperLeftX = plotArea.getX();
/* 343 */     double upperLeftY = plotArea.getY();
/*     */     
/* 345 */     if (plotArea.getWidth() != plotArea.getHeight()) {
/* 346 */       double major = 0.0D;
/* 347 */       double minor = 0.0D;
/* 348 */       if (plotArea.getWidth() > plotArea.getHeight()) {
/* 349 */         major = plotArea.getWidth();
/* 350 */         minor = plotArea.getHeight();
/*     */       }
/*     */       else {
/* 353 */         major = plotArea.getHeight();
/* 354 */         minor = plotArea.getWidth();
/*     */       }
/*     */       
/* 357 */       diameter = minor;
/*     */       
/* 359 */       if (plotArea.getWidth() == minor) {
/* 360 */         upperLeftY = plotArea.getY() + (major - minor) / 2.0D;
/*     */       }
/*     */       else {
/* 363 */         upperLeftX = plotArea.getX() + (major - minor) / 2.0D;
/*     */       }
/*     */     }
/* 366 */     edge.setFrame(upperLeftX, upperLeftY, diameter, diameter);
/* 367 */     return edge;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void drawWaferEdge(Graphics2D g2, Rectangle2D plotArea)
/*     */   {
/* 378 */     Ellipse2D waferEdge = getWaferEdge(plotArea);
/* 379 */     g2.setColor(Color.black);
/* 380 */     g2.draw(waferEdge);
/*     */     
/*     */ 
/*     */ 
/* 384 */     Arc2D notch = null;
/* 385 */     Rectangle2D waferFrame = waferEdge.getFrame();
/* 386 */     double notchDiameter = waferFrame.getWidth() * 0.04D;
/* 387 */     if (this.orientation == PlotOrientation.HORIZONTAL) {
/* 388 */       Rectangle2D notchFrame = new Rectangle2D.Double(waferFrame.getX() + waferFrame.getWidth() - notchDiameter / 2.0D, waferFrame.getY() + waferFrame.getHeight() / 2.0D - notchDiameter / 2.0D, notchDiameter, notchDiameter);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 395 */       notch = new Arc2D.Double(notchFrame, 90.0D, 180.0D, 0);
/*     */     }
/*     */     else {
/* 398 */       Rectangle2D notchFrame = new Rectangle2D.Double(waferFrame.getX() + waferFrame.getWidth() / 2.0D - notchDiameter / 2.0D, waferFrame.getY() + waferFrame.getHeight() - notchDiameter / 2.0D, notchDiameter, notchDiameter);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 405 */       notch = new Arc2D.Double(notchFrame, 0.0D, 180.0D, 0);
/*     */     }
/* 407 */     g2.setColor(Color.white);
/* 408 */     g2.fill(notch);
/* 409 */     g2.setColor(Color.black);
/* 410 */     g2.draw(notch);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public LegendItemCollection getLegendItems()
/*     */   {
/* 420 */     return this.renderer.getLegendCollection();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void rendererChanged(RendererChangeEvent event)
/*     */   {
/* 429 */     fireChangeEvent();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/WaferMapPlot.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */