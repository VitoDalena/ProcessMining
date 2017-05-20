/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import org.jfree.ui.TextAnchor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ValueTick
/*     */   extends Tick
/*     */ {
/*     */   private double value;
/*     */   private TickType tickType;
/*     */   
/*     */   public ValueTick(double value, String label, TextAnchor textAnchor, TextAnchor rotationAnchor, double angle)
/*     */   {
/*  75 */     this(TickType.MAJOR, value, label, textAnchor, rotationAnchor, angle);
/*  76 */     this.value = value;
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
/*     */   public ValueTick(TickType tickType, double value, String label, TextAnchor textAnchor, TextAnchor rotationAnchor, double angle)
/*     */   {
/*  97 */     super(label, textAnchor, rotationAnchor, angle);
/*  98 */     this.value = value;
/*  99 */     this.tickType = tickType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getValue()
/*     */   {
/* 108 */     return this.value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TickType getTickType()
/*     */   {
/* 119 */     return this.tickType;
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
/* 130 */     if (obj == this) {
/* 131 */       return true;
/*     */     }
/* 133 */     if (!(obj instanceof ValueTick)) {
/* 134 */       return false;
/*     */     }
/* 136 */     ValueTick that = (ValueTick)obj;
/* 137 */     if (this.value != that.value) {
/* 138 */       return false;
/*     */     }
/* 140 */     if (!this.tickType.equals(that.tickType)) {
/* 141 */       return false;
/*     */     }
/* 143 */     return super.equals(obj);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/axis/ValueTick.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */