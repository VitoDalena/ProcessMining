/*     */ package org.jfree.chart.util;
/*     */ 
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.FieldPosition;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.ParsePosition;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LogFormat
/*     */   extends NumberFormat
/*     */ {
/*     */   private double base;
/*     */   private double baseLog;
/*     */   private String baseLabel;
/*     */   private String powerLabel;
/*     */   private boolean showBase;
/*  80 */   private NumberFormat formatter = new DecimalFormat("0.0#");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public LogFormat()
/*     */   {
/*  88 */     this(10.0D, "10", true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public LogFormat(double base, String baseLabel, boolean showBase)
/*     */   {
/* 100 */     this(base, baseLabel, "^", showBase);
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
/*     */   public LogFormat(double base, String baseLabel, String powerLabel, boolean showBase)
/*     */   {
/* 116 */     if (baseLabel == null) {
/* 117 */       throw new IllegalArgumentException("Null 'baseLabel' argument.");
/*     */     }
/* 119 */     if (powerLabel == null) {
/* 120 */       throw new IllegalArgumentException("Null 'powerLabel' argument.");
/*     */     }
/* 122 */     this.base = base;
/* 123 */     this.baseLog = Math.log(this.base);
/* 124 */     this.baseLabel = baseLabel;
/* 125 */     this.showBase = showBase;
/* 126 */     this.powerLabel = powerLabel;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public NumberFormat getExponentFormat()
/*     */   {
/* 137 */     return (NumberFormat)this.formatter.clone();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setExponentFormat(NumberFormat format)
/*     */   {
/* 148 */     if (format == null) {
/* 149 */       throw new IllegalArgumentException("Null 'format' argument.");
/*     */     }
/* 151 */     this.formatter = format;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private double calculateLog(double value)
/*     */   {
/* 162 */     return Math.log(value) / this.baseLog;
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
/*     */   public StringBuffer format(double number, StringBuffer toAppendTo, FieldPosition pos)
/*     */   {
/* 176 */     StringBuffer result = new StringBuffer();
/* 177 */     if (this.showBase) {
/* 178 */       result.append(this.baseLabel);
/* 179 */       result.append(this.powerLabel);
/*     */     }
/* 181 */     result.append(this.formatter.format(calculateLog(number)));
/* 182 */     return result;
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
/*     */   public StringBuffer format(long number, StringBuffer toAppendTo, FieldPosition pos)
/*     */   {
/* 197 */     StringBuffer result = new StringBuffer();
/* 198 */     if (this.showBase) {
/* 199 */       result.append(this.baseLabel);
/* 200 */       result.append("^");
/*     */     }
/* 202 */     result.append(this.formatter.format(calculateLog(number)));
/* 203 */     return result;
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
/*     */   public Number parse(String source, ParsePosition parsePosition)
/*     */   {
/* 216 */     return null;
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
/* 227 */     if (obj == this) {
/* 228 */       return true;
/*     */     }
/* 230 */     if (!(obj instanceof LogFormat)) {
/* 231 */       return false;
/*     */     }
/* 233 */     LogFormat that = (LogFormat)obj;
/* 234 */     if (this.base != that.base) {
/* 235 */       return false;
/*     */     }
/* 237 */     if (!this.baseLabel.equals(that.baseLabel)) {
/* 238 */       return false;
/*     */     }
/* 240 */     if (this.baseLog != that.baseLog) {
/* 241 */       return false;
/*     */     }
/* 243 */     if (this.showBase != that.showBase) {
/* 244 */       return false;
/*     */     }
/* 246 */     if (!this.formatter.equals(that.formatter)) {
/* 247 */       return false;
/*     */     }
/* 249 */     return super.equals(obj);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 258 */     LogFormat clone = (LogFormat)super.clone();
/* 259 */     clone.formatter = ((NumberFormat)this.formatter.clone());
/* 260 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/util/LogFormat.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */