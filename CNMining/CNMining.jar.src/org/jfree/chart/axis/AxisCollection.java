/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
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
/*     */ public class AxisCollection
/*     */ {
/*     */   private List axesAtTop;
/*     */   private List axesAtBottom;
/*     */   private List axesAtLeft;
/*     */   private List axesAtRight;
/*     */   
/*     */   public AxisCollection()
/*     */   {
/*  70 */     this.axesAtTop = new ArrayList();
/*  71 */     this.axesAtBottom = new ArrayList();
/*  72 */     this.axesAtLeft = new ArrayList();
/*  73 */     this.axesAtRight = new ArrayList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List getAxesAtTop()
/*     */   {
/*  83 */     return this.axesAtTop;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List getAxesAtBottom()
/*     */   {
/*  93 */     return this.axesAtBottom;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List getAxesAtLeft()
/*     */   {
/* 103 */     return this.axesAtLeft;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List getAxesAtRight()
/*     */   {
/* 113 */     return this.axesAtRight;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(Axis axis, RectangleEdge edge)
/*     */   {
/* 124 */     if (axis == null) {
/* 125 */       throw new IllegalArgumentException("Null 'axis' argument.");
/*     */     }
/* 127 */     if (edge == null) {
/* 128 */       throw new IllegalArgumentException("Null 'edge' argument.");
/*     */     }
/* 130 */     if (edge == RectangleEdge.TOP) {
/* 131 */       this.axesAtTop.add(axis);
/*     */     }
/* 133 */     else if (edge == RectangleEdge.BOTTOM) {
/* 134 */       this.axesAtBottom.add(axis);
/*     */     }
/* 136 */     else if (edge == RectangleEdge.LEFT) {
/* 137 */       this.axesAtLeft.add(axis);
/*     */     }
/* 139 */     else if (edge == RectangleEdge.RIGHT) {
/* 140 */       this.axesAtRight.add(axis);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/axis/AxisCollection.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */