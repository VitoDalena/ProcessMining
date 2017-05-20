/*     */ package org.jfree.data.time;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TimePeriodValue
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 3390443360845711275L;
/*     */   private TimePeriod period;
/*     */   private Number value;
/*     */   
/*     */   public TimePeriodValue(TimePeriod period, Number value)
/*     */   {
/*  71 */     if (period == null) {
/*  72 */       throw new IllegalArgumentException("Null 'period' argument.");
/*     */     }
/*  74 */     this.period = period;
/*  75 */     this.value = value;
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
/*     */   public TimePeriodValue(TimePeriod period, double value)
/*     */   {
/*  88 */     this(period, new Double(value));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TimePeriod getPeriod()
/*     */   {
/*  97 */     return this.period;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getValue()
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
/*     */   public void setValue(Number value)
/*     */   {
/* 119 */     this.value = value;
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
/* 130 */     if (this == obj) {
/* 131 */       return true;
/*     */     }
/* 133 */     if (!(obj instanceof TimePeriodValue)) {
/* 134 */       return false;
/*     */     }
/*     */     
/* 137 */     TimePeriodValue timePeriodValue = (TimePeriodValue)obj;
/*     */     
/* 139 */     if (this.period != null ? !this.period.equals(timePeriodValue.period) : timePeriodValue.period != null)
/*     */     {
/* 141 */       return false;
/*     */     }
/* 143 */     if (this.value != null ? !this.value.equals(timePeriodValue.value) : timePeriodValue.value != null)
/*     */     {
/* 145 */       return false;
/*     */     }
/*     */     
/* 148 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 158 */     int result = this.period != null ? this.period.hashCode() : 0;
/* 159 */     result = 29 * result + (this.value != null ? this.value.hashCode() : 0);
/* 160 */     return result;
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
/*     */   {
/* 172 */     Object clone = null;
/*     */     try {
/* 174 */       clone = super.clone();
/*     */     }
/*     */     catch (CloneNotSupportedException e) {
/* 177 */       e.printStackTrace();
/*     */     }
/* 179 */     return clone;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 189 */     return "TimePeriodValue[" + getPeriod() + "," + getValue() + "]";
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/time/TimePeriodValue.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */