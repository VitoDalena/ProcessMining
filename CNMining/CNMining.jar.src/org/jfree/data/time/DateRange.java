/*     */ package org.jfree.data.time;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.DateFormat;
/*     */ import java.util.Date;
/*     */ import org.jfree.data.Range;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DateRange
/*     */   extends Range
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -4705682568375418157L;
/*     */   private long lowerDate;
/*     */   private long upperDate;
/*     */   
/*     */   public DateRange()
/*     */   {
/*  72 */     this(new Date(0L), new Date(1L));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateRange(Date lower, Date upper)
/*     */   {
/*  82 */     super(lower.getTime(), upper.getTime());
/*  83 */     this.lowerDate = lower.getTime();
/*  84 */     this.upperDate = upper.getTime();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateRange(double lower, double upper)
/*     */   {
/*  95 */     super(lower, upper);
/*  96 */     this.lowerDate = (lower);
/*  97 */     this.upperDate = (upper);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateRange(Range other)
/*     */   {
/* 109 */     this(other.getLowerBound(), other.getUpperBound());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getLowerDate()
/*     */   {
/* 120 */     return new Date(this.lowerDate);
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
/*     */   public long getLowerMillis()
/*     */   {
/* 133 */     return this.lowerDate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getUpperDate()
/*     */   {
/* 144 */     return new Date(this.upperDate);
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
/*     */   public long getUpperMillis()
/*     */   {
/* 157 */     return this.upperDate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 166 */     DateFormat df = DateFormat.getDateTimeInstance();
/* 167 */     return "[" + df.format(getLowerDate()) + " --> " + df.format(getUpperDate()) + "]";
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/time/DateRange.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */