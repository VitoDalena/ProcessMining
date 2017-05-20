/*     */ package org.jfree.chart;
/*     */ 
/*     */ import java.awt.event.MouseWheelEvent;
/*     */ import java.awt.event.MouseWheelListener;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.plot.Plot;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.Zoomable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class MouseWheelHandler
/*     */   implements MouseWheelListener, Serializable
/*     */ {
/*     */   private ChartPanel chartPanel;
/*     */   double zoomFactor;
/*     */   
/*     */   public MouseWheelHandler(ChartPanel chartPanel)
/*     */   {
/*  74 */     this.chartPanel = chartPanel;
/*  75 */     this.zoomFactor = 0.1D;
/*  76 */     this.chartPanel.addMouseWheelListener(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getZoomFactor()
/*     */   {
/*  88 */     return this.zoomFactor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setZoomFactor(double zoomFactor)
/*     */   {
/*  99 */     this.zoomFactor = zoomFactor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mouseWheelMoved(MouseWheelEvent e)
/*     */   {
/* 108 */     JFreeChart chart = this.chartPanel.getChart();
/* 109 */     if (chart == null) {
/* 110 */       return;
/*     */     }
/* 112 */     Plot plot = chart.getPlot();
/* 113 */     if ((plot instanceof Zoomable)) {
/* 114 */       Zoomable zoomable = (Zoomable)plot;
/* 115 */       handleZoomable(zoomable, e);
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
/*     */   private void handleZoomable(Zoomable zoomable, MouseWheelEvent e)
/*     */   {
/* 129 */     Plot plot = (Plot)zoomable;
/* 130 */     ChartRenderingInfo info = this.chartPanel.getChartRenderingInfo();
/* 131 */     PlotRenderingInfo pinfo = info.getPlotInfo();
/* 132 */     Point2D p = this.chartPanel.translateScreenToJava2D(e.getPoint());
/* 133 */     if (!pinfo.getDataArea().contains(p)) {
/* 134 */       return;
/*     */     }
/* 136 */     int clicks = e.getWheelRotation();
/* 137 */     int direction = 0;
/* 138 */     if (clicks < 0) {
/* 139 */       direction = -1;
/*     */     }
/* 141 */     else if (clicks > 0) {
/* 142 */       direction = 1;
/*     */     }
/*     */     
/* 145 */     boolean old = plot.isNotify();
/*     */     
/*     */ 
/* 148 */     plot.setNotify(false);
/* 149 */     double increment = 1.0D + this.zoomFactor;
/* 150 */     if (direction > 0) {
/* 151 */       zoomable.zoomDomainAxes(increment, pinfo, p, true);
/* 152 */       zoomable.zoomRangeAxes(increment, pinfo, p, true);
/*     */     }
/* 154 */     else if (direction < 0) {
/* 155 */       zoomable.zoomDomainAxes(1.0D / increment, pinfo, p, true);
/* 156 */       zoomable.zoomRangeAxes(1.0D / increment, pinfo, p, true);
/*     */     }
/*     */     
/* 159 */     plot.setNotify(old);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/MouseWheelHandler.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */