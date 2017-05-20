/*     */ package org.jfree.chart;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.event.ActionEvent;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JPopupMenu;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.plot.Plot;
/*     */ import org.jfree.chart.plot.PolarPlot;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PolarChartPanel
/*     */   extends ChartPanel
/*     */ {
/*     */   private static final String POLAR_ZOOM_IN_ACTION_COMMAND = "Polar Zoom In";
/*     */   private static final String POLAR_ZOOM_OUT_ACTION_COMMAND = "Polar Zoom Out";
/*     */   private static final String POLAR_AUTO_RANGE_ACTION_COMMAND = "Polar Auto Range";
/*     */   
/*     */   public PolarChartPanel(JFreeChart chart)
/*     */   {
/*  99 */     this(chart, true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PolarChartPanel(JFreeChart chart, boolean useBuffer)
/*     */   {
/* 109 */     super(chart, useBuffer);
/* 110 */     checkChart(chart);
/* 111 */     setMinimumDrawWidth(200);
/* 112 */     setMinimumDrawHeight(200);
/* 113 */     setMaximumDrawWidth(2000);
/* 114 */     setMaximumDrawHeight(2000);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setChart(JFreeChart chart)
/*     */   {
/* 126 */     checkChart(chart);
/* 127 */     super.setChart(chart);
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
/*     */   protected JPopupMenu createPopupMenu(boolean properties, boolean save, boolean print, boolean zoom)
/*     */   {
/* 145 */     JPopupMenu result = super.createPopupMenu(properties, save, print, zoom);
/* 146 */     int zoomInIndex = getPopupMenuItem(result, "Zoom In");
/* 147 */     int zoomOutIndex = getPopupMenuItem(result, "Zoom Out");
/* 148 */     int autoIndex = getPopupMenuItem(result, "Auto Range");
/* 149 */     if (zoom) {
/* 150 */       JMenuItem zoomIn = new JMenuItem("Zoom In");
/* 151 */       zoomIn.setActionCommand("Polar Zoom In");
/* 152 */       zoomIn.addActionListener(this);
/*     */       
/* 154 */       JMenuItem zoomOut = new JMenuItem("Zoom Out");
/* 155 */       zoomOut.setActionCommand("Polar Zoom Out");
/* 156 */       zoomOut.addActionListener(this);
/*     */       
/* 158 */       JMenuItem auto = new JMenuItem("Auto Range");
/* 159 */       auto.setActionCommand("Polar Auto Range");
/* 160 */       auto.addActionListener(this);
/*     */       
/* 162 */       if (zoomInIndex != -1) {
/* 163 */         result.remove(zoomInIndex);
/*     */       }
/*     */       else {
/* 166 */         zoomInIndex = result.getComponentCount() - 1;
/*     */       }
/* 168 */       result.add(zoomIn, zoomInIndex);
/* 169 */       if (zoomOutIndex != -1) {
/* 170 */         result.remove(zoomOutIndex);
/*     */       }
/*     */       else {
/* 173 */         zoomOutIndex = zoomInIndex + 1;
/*     */       }
/* 175 */       result.add(zoomOut, zoomOutIndex);
/* 176 */       if (autoIndex != -1) {
/* 177 */         result.remove(autoIndex);
/*     */       }
/*     */       else {
/* 180 */         autoIndex = zoomOutIndex + 1;
/*     */       }
/* 182 */       result.add(auto, autoIndex);
/*     */     }
/* 184 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void actionPerformed(ActionEvent event)
/*     */   {
/* 193 */     String command = event.getActionCommand();
/*     */     
/* 195 */     if (command.equals("Polar Zoom In")) {
/* 196 */       PolarPlot plot = (PolarPlot)getChart().getPlot();
/* 197 */       plot.zoom(0.5D);
/*     */     }
/* 199 */     else if (command.equals("Polar Zoom Out")) {
/* 200 */       PolarPlot plot = (PolarPlot)getChart().getPlot();
/* 201 */       plot.zoom(2.0D);
/*     */     }
/* 203 */     else if (command.equals("Polar Auto Range")) {
/* 204 */       PolarPlot plot = (PolarPlot)getChart().getPlot();
/* 205 */       plot.getAxis().setAutoRange(true);
/*     */     }
/*     */     else {
/* 208 */       super.actionPerformed(event);
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
/*     */   private void checkChart(JFreeChart chart)
/*     */   {
/* 226 */     Plot plot = chart.getPlot();
/* 227 */     if (!(plot instanceof PolarPlot)) {
/* 228 */       throw new IllegalArgumentException("plot is not a PolarPlot");
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
/*     */   private int getPopupMenuItem(JPopupMenu menu, String text)
/*     */   {
/* 241 */     int index = -1;
/* 242 */     for (int i = 0; (index == -1) && (i < menu.getComponentCount()); i++) {
/* 243 */       Component comp = menu.getComponent(i);
/* 244 */       if ((comp instanceof JMenuItem)) {
/* 245 */         JMenuItem item = (JMenuItem)comp;
/* 246 */         if (text.equals(item.getText())) {
/* 247 */           index = i;
/*     */         }
/*     */       }
/*     */     }
/* 251 */     return index;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/PolarChartPanel.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */