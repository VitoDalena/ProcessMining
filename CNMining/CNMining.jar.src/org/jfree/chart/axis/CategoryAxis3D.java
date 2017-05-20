/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.Effect3D;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.renderer.category.CategoryItemRenderer;
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
/*     */ public class CategoryAxis3D
/*     */   extends CategoryAxis
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 4114732251353700972L;
/*     */   
/*     */   public CategoryAxis3D()
/*     */   {
/*  82 */     this(null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CategoryAxis3D(String label)
/*     */   {
/*  91 */     super(label);
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
/* 118 */     if (!isVisible()) {
/* 119 */       return new AxisState(cursor);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 125 */     CategoryPlot plot = (CategoryPlot)getPlot();
/*     */     
/* 127 */     Rectangle2D adjustedDataArea = new Rectangle2D.Double();
/* 128 */     if ((plot.getRenderer() instanceof Effect3D)) {
/* 129 */       Effect3D e3D = (Effect3D)plot.getRenderer();
/* 130 */       double adjustedX = dataArea.getMinX();
/* 131 */       double adjustedY = dataArea.getMinY();
/* 132 */       double adjustedW = dataArea.getWidth() - e3D.getXOffset();
/* 133 */       double adjustedH = dataArea.getHeight() - e3D.getYOffset();
/*     */       
/* 135 */       if ((edge == RectangleEdge.LEFT) || (edge == RectangleEdge.BOTTOM)) {
/* 136 */         adjustedY += e3D.getYOffset();
/*     */       }
/* 138 */       else if ((edge == RectangleEdge.RIGHT) || (edge == RectangleEdge.TOP)) {
/* 139 */         adjustedX += e3D.getXOffset();
/*     */       }
/* 141 */       adjustedDataArea.setRect(adjustedX, adjustedY, adjustedW, adjustedH);
/*     */     }
/*     */     else
/*     */     {
/* 145 */       adjustedDataArea.setRect(dataArea);
/*     */     }
/*     */     
/* 148 */     if (isAxisLineVisible()) {
/* 149 */       drawAxisLine(g2, cursor, adjustedDataArea, edge);
/*     */     }
/*     */     
/* 152 */     AxisState state = new AxisState(cursor);
/* 153 */     if (isTickMarksVisible()) {
/* 154 */       drawTickMarks(g2, cursor, adjustedDataArea, edge, state);
/*     */     }
/* 156 */     state = drawCategoryLabels(g2, plotArea, adjustedDataArea, edge, state, plotState);
/*     */     
/* 158 */     state = drawLabel(getLabel(), g2, plotArea, dataArea, edge, state);
/*     */     
/* 160 */     return state;
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
/*     */   public double getCategoryJava2DCoordinate(CategoryAnchor anchor, int category, int categoryCount, Rectangle2D area, RectangleEdge edge)
/*     */   {
/* 181 */     double result = 0.0D;
/* 182 */     Rectangle2D adjustedArea = area;
/* 183 */     CategoryPlot plot = (CategoryPlot)getPlot();
/* 184 */     CategoryItemRenderer renderer = plot.getRenderer();
/* 185 */     if ((renderer instanceof Effect3D)) {
/* 186 */       Effect3D e3D = (Effect3D)renderer;
/* 187 */       double adjustedX = area.getMinX();
/* 188 */       double adjustedY = area.getMinY();
/* 189 */       double adjustedW = area.getWidth() - e3D.getXOffset();
/* 190 */       double adjustedH = area.getHeight() - e3D.getYOffset();
/*     */       
/* 192 */       if ((edge == RectangleEdge.LEFT) || (edge == RectangleEdge.BOTTOM)) {
/* 193 */         adjustedY += e3D.getYOffset();
/*     */       }
/* 195 */       else if ((edge == RectangleEdge.RIGHT) || (edge == RectangleEdge.TOP)) {
/* 196 */         adjustedX += e3D.getXOffset();
/*     */       }
/* 198 */       adjustedArea = new Rectangle2D.Double(adjustedX, adjustedY, adjustedW, adjustedH);
/*     */     }
/*     */     
/*     */ 
/* 202 */     if (anchor == CategoryAnchor.START) {
/* 203 */       result = getCategoryStart(category, categoryCount, adjustedArea, edge);
/*     */ 
/*     */     }
/* 206 */     else if (anchor == CategoryAnchor.MIDDLE) {
/* 207 */       result = getCategoryMiddle(category, categoryCount, adjustedArea, edge);
/*     */ 
/*     */     }
/* 210 */     else if (anchor == CategoryAnchor.END) {
/* 211 */       result = getCategoryEnd(category, categoryCount, adjustedArea, edge);
/*     */     }
/*     */     
/* 214 */     return result;
/*     */   }
/*     */   
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
/* 227 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/axis/CategoryAxis3D.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */