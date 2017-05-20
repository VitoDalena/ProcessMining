/*     */ package org.jfree.chart.event;
/*     */ 
/*     */ import java.util.EventObject;
/*     */ import org.jfree.chart.JFreeChart;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChartProgressEvent
/*     */   extends EventObject
/*     */ {
/*     */   public static final int DRAWING_STARTED = 1;
/*     */   public static final int DRAWING_FINISHED = 2;
/*     */   private int type;
/*     */   private int percent;
/*     */   private JFreeChart chart;
/*     */   
/*     */   public ChartProgressEvent(Object source, JFreeChart chart, int type, int percent)
/*     */   {
/*  76 */     super(source);
/*  77 */     this.chart = chart;
/*  78 */     this.type = type;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public JFreeChart getChart()
/*     */   {
/*  87 */     return this.chart;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setChart(JFreeChart chart)
/*     */   {
/*  96 */     this.chart = chart;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getType()
/*     */   {
/* 105 */     return this.type;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setType(int type)
/*     */   {
/* 114 */     this.type = type;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getPercent()
/*     */   {
/* 123 */     return this.percent;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPercent(int percent)
/*     */   {
/* 132 */     this.percent = percent;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/event/ChartProgressEvent.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */