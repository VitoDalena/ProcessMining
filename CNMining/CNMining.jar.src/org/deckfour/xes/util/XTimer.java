/*     */ package org.deckfour.xes.util;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XTimer
/*     */ {
/*     */   public static final long DAY_MILLIS = 86400000L;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final long HOUR_MILLIS = 3600000L;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final long MINUTE_MILLIS = 60000L;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final long SECOND_MILLIS = 1000L;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected long start;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected long stop;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XTimer()
/*     */   {
/*  86 */     this.start = System.currentTimeMillis();
/*  87 */     this.stop = this.start;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void start()
/*     */   {
/*  94 */     this.start = System.currentTimeMillis();
/*  95 */     this.stop = this.start;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void stop()
/*     */   {
/* 102 */     this.stop = System.currentTimeMillis();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getDuration()
/*     */   {
/* 112 */     if (this.start == this.stop) {
/* 113 */       return System.currentTimeMillis() - this.start;
/*     */     }
/* 115 */     return this.stop - this.start;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDurationString()
/*     */   {
/* 127 */     return formatDuration(getDuration());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String formatDuration(long millis)
/*     */   {
/* 138 */     StringBuilder sb = new StringBuilder();
/* 139 */     if (millis > 86400000L) {
/* 140 */       sb.append(millis / 86400000L);
/* 141 */       sb.append(" days, ");
/* 142 */       millis %= 86400000L;
/*     */     }
/* 144 */     if (millis > 3600000L) {
/* 145 */       sb.append(millis / 3600000L);
/* 146 */       sb.append(" hours, ");
/* 147 */       millis %= 3600000L;
/*     */     }
/* 149 */     if (millis > 60000L) {
/* 150 */       sb.append(millis / 60000L);
/* 151 */       sb.append(" minutes, ");
/* 152 */       millis %= 60000L;
/*     */     }
/* 154 */     if (millis > 1000L) {
/* 155 */       sb.append(millis / 1000L);
/* 156 */       sb.append(" seconds, ");
/* 157 */       millis %= 1000L;
/*     */     }
/* 159 */     sb.append(millis);
/* 160 */     sb.append(" milliseconds");
/* 161 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/util/XTimer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */