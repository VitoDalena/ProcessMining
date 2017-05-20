/*     */ package org.jfree.data.time;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FixedMillisecond
/*     */   extends RegularTimePeriod
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 7867521484545646931L;
/*     */   private long time;
/*     */   
/*     */   public FixedMillisecond()
/*     */   {
/*  74 */     this(new Date());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FixedMillisecond(long millisecond)
/*     */   {
/*  83 */     this(new Date(millisecond));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FixedMillisecond(Date time)
/*     */   {
/*  92 */     this.time = time.getTime();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getTime()
/*     */   {
/* 101 */     return new Date(this.time);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void peg(Calendar calendar) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RegularTimePeriod previous()
/*     */   {
/* 121 */     RegularTimePeriod result = null;
/* 122 */     long t = this.time;
/* 123 */     if (t != Long.MIN_VALUE) {
/* 124 */       result = new FixedMillisecond(t - 1L);
/*     */     }
/* 126 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RegularTimePeriod next()
/*     */   {
/* 135 */     RegularTimePeriod result = null;
/* 136 */     long t = this.time;
/* 137 */     if (t != Long.MAX_VALUE) {
/* 138 */       result = new FixedMillisecond(t + 1L);
/*     */     }
/* 140 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 151 */     if ((object instanceof FixedMillisecond)) {
/* 152 */       FixedMillisecond m = (FixedMillisecond)object;
/* 153 */       return this.time == m.getFirstMillisecond();
/*     */     }
/*     */     
/* 156 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 167 */     return (int)this.time;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int compareTo(Object o1)
/*     */   {
/*     */     int result;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     int result;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 186 */     if ((o1 instanceof FixedMillisecond)) {
/* 187 */       FixedMillisecond t1 = (FixedMillisecond)o1;
/* 188 */       long difference = this.time - t1.time;
/* 189 */       int result; if (difference > 0L) {
/* 190 */         result = 1;
/*     */       } else {
/*     */         int result;
/* 193 */         if (difference < 0L) {
/* 194 */           result = -1;
/*     */         }
/*     */         else {
/* 197 */           result = 0;
/*     */         }
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*     */       int result;
/* 204 */       if ((o1 instanceof RegularTimePeriod))
/*     */       {
/* 206 */         result = 0;
/*     */ 
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/*     */ 
/* 213 */         result = 1;
/*     */       }
/*     */     }
/* 216 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getFirstMillisecond()
/*     */   {
/* 226 */     return this.time;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getFirstMillisecond(Calendar calendar)
/*     */   {
/* 238 */     return this.time;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getLastMillisecond()
/*     */   {
/* 247 */     return this.time;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getLastMillisecond(Calendar calendar)
/*     */   {
/* 258 */     return this.time;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getMiddleMillisecond()
/*     */   {
/* 267 */     return this.time;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getMiddleMillisecond(Calendar calendar)
/*     */   {
/* 278 */     return this.time;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getSerialIndex()
/*     */   {
/* 287 */     return this.time;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/time/FixedMillisecond.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */