/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import org.jfree.chart.Effect3D;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.Plot;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NumberAxis3D
/*     */   extends NumberAxis
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -1790205852569123512L;
/*     */   
/*     */   public NumberAxis3D()
/*     */   {
/*  97 */     this(null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public NumberAxis3D(String label)
/*     */   {
/* 106 */     super(label);
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
/*     */   public AxisState draw(Graphics2D g2, double cursor, Rectangle2D plotArea, Rectangle2D dataArea, RectangleEdge edge, PlotRenderingInfo plotState)
/*     */   {
/* 129 */     if (!isVisible()) {
/* 130 */       AxisState state = new AxisState(cursor);
/*     */       
/*     */ 
/* 133 */       List ticks = refreshTicks(g2, state, dataArea, edge);
/* 134 */       state.setTicks(ticks);
/* 135 */       return state;
/*     */     }
/*     */     
/*     */ 
/* 139 */     double xOffset = 0.0D;
/* 140 */     double yOffset = 0.0D;
/* 141 */     Plot plot = getPlot();
/* 142 */     if ((plot instanceof CategoryPlot)) {
/* 143 */       CategoryPlot cp = (CategoryPlot)plot;
/* 144 */       CategoryItemRenderer r = cp.getRenderer();
/* 145 */       if ((r instanceof Effect3D)) {
/* 146 */         Effect3D e3D = (Effect3D)r;
/* 147 */         xOffset = e3D.getXOffset();
/* 148 */         yOffset = e3D.getYOffset();
/*     */       }
/*     */     }
/*     */     
/* 152 */     double adjustedX = dataArea.getMinX();
/* 153 */     double adjustedY = dataArea.getMinY();
/* 154 */     double adjustedW = dataArea.getWidth() - xOffset;
/* 155 */     double adjustedH = dataArea.getHeight() - yOffset;
/*     */     
/* 157 */     if ((edge == RectangleEdge.LEFT) || (edge == RectangleEdge.BOTTOM)) {
/* 158 */       adjustedY += yOffset;
/*     */     }
/* 160 */     else if ((edge == RectangleEdge.RIGHT) || (edge == RectangleEdge.TOP)) {
/* 161 */       adjustedX += xOffset;
/*     */     }
/* 163 */     Rectangle2D adjustedDataArea = new Rectangle2D.Double(adjustedX, adjustedY, adjustedW, adjustedH);
/*     */     
/*     */ 
/*     */ 
/* 167 */     AxisState info = drawTickMarksAndLabels(g2, cursor, plotArea, adjustedDataArea, edge);
/*     */     
/*     */ 
/*     */ 
/* 171 */     info = drawLabel(getLabel(), g2, plotArea, dataArea, edge, info);
/*     */     
/* 173 */     return info;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/axis/NumberAxis3D.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */