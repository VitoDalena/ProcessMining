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
/*     */ public class NumberTick
/*     */   extends ValueTick
/*     */ {
/*     */   private Number number;
/*     */   
/*     */   public NumberTick(Number number, String label, TextAnchor textAnchor, TextAnchor rotationAnchor, double angle)
/*     */   {
/*  68 */     super(number.doubleValue(), label, textAnchor, rotationAnchor, angle);
/*  69 */     this.number = number;
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
/*     */   public NumberTick(TickType tickType, double value, String label, TextAnchor textAnchor, TextAnchor rotationAnchor, double angle)
/*     */   {
/*  90 */     super(tickType, value, label, textAnchor, rotationAnchor, angle);
/*  91 */     this.number = new Double(value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getNumber()
/*     */   {
/* 101 */     return this.number;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/axis/NumberTick.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */