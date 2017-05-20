/*     */ package org.jfree.data.time;
/*     */ 
/*     */ import java.io.Serializable;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SimpleTimePeriod
/*     */   implements TimePeriod, Comparable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 8684672361131829554L;
/*     */   private long start;
/*     */   private long end;
/*     */   
/*     */   public SimpleTimePeriod(long start, long end)
/*     */   {
/*  77 */     if (start > end) {
/*  78 */       throw new IllegalArgumentException("Requires start <= end.");
/*     */     }
/*  80 */     this.start = start;
/*  81 */     this.end = end;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SimpleTimePeriod(Date start, Date end)
/*     */   {
/*  91 */     this(start.getTime(), end.getTime());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getStart()
/*     */   {
/* 100 */     return new Date(this.start);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getStartMillis()
/*     */   {
/* 111 */     return this.start;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getEnd()
/*     */   {
/* 120 */     return new Date(this.end);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getEndMillis()
/*     */   {
/* 131 */     return this.end;
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
/*     */   public boolean equals(Object obj)
/*     */   {
/* 144 */     if (obj == this) {
/* 145 */       return true;
/*     */     }
/* 147 */     if (!(obj instanceof TimePeriod)) {
/* 148 */       return false;
/*     */     }
/* 150 */     TimePeriod that = (TimePeriod)obj;
/* 151 */     if (!getStart().equals(that.getStart())) {
/* 152 */       return false;
/*     */     }
/* 154 */     if (!getEnd().equals(that.getEnd())) {
/* 155 */       return false;
/*     */     }
/* 157 */     return true;
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
/*     */   public int compareTo(Object obj)
/*     */   {
/* 172 */     TimePeriod that = (TimePeriod)obj;
/* 173 */     long t0 = getStart().getTime();
/* 174 */     long t1 = getEnd().getTime();
/* 175 */     long m0 = t0 + (t1 - t0) / 2L;
/* 176 */     long t2 = that.getStart().getTime();
/* 177 */     long t3 = that.getEnd().getTime();
/* 178 */     long m1 = t2 + (t3 - t2) / 2L;
/* 179 */     if (m0 < m1) {
/* 180 */       return -1;
/*     */     }
/* 182 */     if (m0 > m1) {
/* 183 */       return 1;
/*     */     }
/*     */     
/* 186 */     if (t0 < t2) {
/* 187 */       return -1;
/*     */     }
/* 189 */     if (t0 > t2) {
/* 190 */       return 1;
/*     */     }
/*     */     
/* 193 */     if (t1 < t3) {
/* 194 */       return -1;
/*     */     }
/* 196 */     if (t1 > t3) {
/* 197 */       return 1;
/*     */     }
/*     */     
/* 200 */     return 0;
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
/*     */   public int hashCode()
/*     */   {
/* 216 */     int result = 17;
/* 217 */     result = 37 * result + (int)this.start;
/* 218 */     result = 37 * result + (int)this.end;
/* 219 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/time/SimpleTimePeriod.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */