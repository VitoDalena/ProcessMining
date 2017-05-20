/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.NumberFormat;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NumberTickUnit
/*     */   extends TickUnit
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 3849459506627654442L;
/*     */   private NumberFormat formatter;
/*     */   
/*     */   public NumberTickUnit(double size)
/*     */   {
/*  71 */     this(size, NumberFormat.getNumberInstance());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public NumberTickUnit(double size, NumberFormat formatter)
/*     */   {
/*  82 */     super(size);
/*  83 */     if (formatter == null) {
/*  84 */       throw new IllegalArgumentException("Null 'formatter' argument.");
/*     */     }
/*  86 */     this.formatter = formatter;
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
/*     */   public NumberTickUnit(double size, NumberFormat formatter, int minorTickCount)
/*     */   {
/* 101 */     super(size, minorTickCount);
/* 102 */     if (formatter == null) {
/* 103 */       throw new IllegalArgumentException("Null 'formatter' argument.");
/*     */     }
/* 105 */     this.formatter = formatter;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String valueToString(double value)
/*     */   {
/* 116 */     return this.formatter.format(value);
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
/* 127 */     if (obj == this) {
/* 128 */       return true;
/*     */     }
/* 130 */     if (!(obj instanceof NumberTickUnit)) {
/* 131 */       return false;
/*     */     }
/* 133 */     if (!super.equals(obj)) {
/* 134 */       return false;
/*     */     }
/* 136 */     NumberTickUnit that = (NumberTickUnit)obj;
/* 137 */     if (!this.formatter.equals(that.formatter)) {
/* 138 */       return false;
/*     */     }
/* 140 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 149 */     return "[size=" + valueToString(getSize()) + "]";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 158 */     int result = super.hashCode();
/* 159 */     result = 29 * result + (this.formatter != null ? this.formatter.hashCode() : 0);
/*     */     
/* 161 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/axis/NumberTickUnit.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */