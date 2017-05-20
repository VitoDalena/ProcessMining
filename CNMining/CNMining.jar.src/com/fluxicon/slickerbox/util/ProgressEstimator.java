/*     */ package com.fluxicon.slickerbox.util;
/*     */ 
/*     */ import java.util.Random;
/*     */ import javax.swing.JProgressBar;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ProgressEstimator
/*     */ {
/*     */   private static final long SECOND = 1000L;
/*     */   private static final long MINUTE = 60000L;
/*     */   private static final long HOUR = 3600000L;
/*     */   private static final long DAY = 86400000L;
/*  59 */   private static final String[] indeterminate = {
/*  60 */     "hold on there...", 
/*  61 */     "almost there...", 
/*  62 */     "just a second..." };
/*     */   
/*  64 */   private static long lastIndeterminateTime = 0L;
/*  65 */   private static int lastIndeterminate = 0;
/*  66 */   private static Random random = new Random();
/*     */   
/*  68 */   private long refTime = System.currentTimeMillis();
/*  69 */   private double refProgress = 0.5D;
/*     */   private long[] lastTargetTimes;
/*     */   
/*     */   public ProgressEstimator() {
/*  73 */     this(null);
/*     */   }
/*     */   
/*     */   public ProgressEstimator(JProgressBar progressBar) {
/*  77 */     if (progressBar != null) {
/*  78 */       initialize(progressBar);
/*     */     }
/*     */   }
/*     */   
/*     */   public void initialize(JProgressBar progressBar) {
/*  83 */     this.refTime = System.currentTimeMillis();
/*  84 */     this.refProgress = progressBar.getPercentComplete();
/*  85 */     this.lastTargetTimes = new long[3];
/*     */   }
/*     */   
/*     */   public String getIndeterminate() {
/*  89 */     long now = System.currentTimeMillis();
/*  90 */     if (now - lastIndeterminateTime >= 5000L)
/*     */     {
/*  92 */       lastIndeterminateTime = now;
/*  93 */       lastIndeterminate = random.nextInt(indeterminate.length);
/*     */     }
/*  95 */     return indeterminate[lastIndeterminate];
/*     */   }
/*     */   
/*     */   public String getEstimation(JProgressBar progressBar) {
/*  99 */     return getEstimation(progressBar.getPercentComplete());
/*     */   }
/*     */   
/*     */   public String getEstimation(double percentComplete) {
/* 103 */     return getEstimationString(estimateMillisRemainingCorrected(percentComplete));
/*     */   }
/*     */   
/*     */   private long estimateMillisRemainingCorrected(double percentComplete) {
/* 107 */     long now = System.currentTimeMillis();
/* 108 */     long currentTarget = now + estimateMillisRemaining(percentComplete);
/* 109 */     long nextTarget = currentTarget * 10L;
/* 110 */     double divisor = 10.0D;
/* 111 */     if (this.lastTargetTimes[0] > 0L) {
/* 112 */       nextTarget += this.lastTargetTimes[0] * 5L;
/* 113 */       divisor += 5.0D;
/*     */     }
/* 115 */     if (this.lastTargetTimes[1] > 0L) {
/* 116 */       nextTarget += this.lastTargetTimes[1] * 2L;
/* 117 */       divisor += 2.0D;
/*     */     }
/* 119 */     if (this.lastTargetTimes[2] > 0L) {
/* 120 */       nextTarget += this.lastTargetTimes[2];
/* 121 */       divisor += 1.0D;
/*     */     }
/* 123 */     nextTarget = (nextTarget / divisor);
/* 124 */     if (this.lastTargetTimes[2] > 0L) {
/* 125 */       long increase = nextTarget - this.lastTargetTimes[2];
/* 126 */       if (increase > 0L) {
/* 127 */         nextTarget += increase;
/*     */       }
/*     */     }
/* 130 */     if (nextTarget < currentTarget) {
/* 131 */       nextTarget = currentTarget;
/*     */     }
/* 133 */     this.lastTargetTimes[2] = this.lastTargetTimes[1];
/* 134 */     this.lastTargetTimes[1] = this.lastTargetTimes[0];
/* 135 */     this.lastTargetTimes[0] = nextTarget;
/* 136 */     long correctedDuration = nextTarget - now;
/* 137 */     return correctedDuration;
/*     */   }
/*     */   
/*     */   public long estimateMillisRemaining(double percentComplete) {
/* 141 */     double progressToDo = 1.0D - percentComplete;
/* 142 */     if (progressToDo == 0.0D)
/* 143 */       return 0L;
/* 144 */     if (progressToDo == 1.0D) {
/* 145 */       return -1L;
/*     */     }
/* 147 */     long runTime = System.currentTimeMillis() - this.refTime;
/* 148 */     double intermediateProgress = percentComplete - this.refProgress;
/* 149 */     long estimation = (runTime / intermediateProgress * progressToDo);
/* 150 */     return estimation;
/*     */   }
/*     */   
/*     */   private String getEstimationString(long millisRemaining)
/*     */   {
/* 155 */     long now = System.currentTimeMillis();
/* 156 */     if (now - this.refTime < 1000L) {
/* 157 */       return "";
/*     */     }
/* 159 */     if (millisRemaining < 0L)
/* 160 */       return "unknown duration";
/* 161 */     if (millisRemaining == 0L) {
/* 162 */       return "finishing...";
/*     */     }
/* 164 */     int days = (int)(millisRemaining / 86400000L);
/* 165 */     millisRemaining %= 86400000L;
/* 166 */     int hours = (int)(millisRemaining / 3600000L);
/* 167 */     millisRemaining %= 3600000L;
/* 168 */     int minutes = (int)(millisRemaining / 60000L);
/* 169 */     millisRemaining %= 60000L;
/* 170 */     int seconds = (int)(millisRemaining / 1000L);
/*     */     
/* 172 */     if (days > 0)
/* 173 */       return "about " + days + " days remaining";
/* 174 */     if (hours > 0)
/* 175 */       return "about " + hours + " hours remaining";
/* 176 */     if (minutes > 1)
/* 177 */       return "about " + (minutes + 1) + " minutes remaining";
/* 178 */     if (minutes == 1) {
/* 179 */       if (seconds > 15) {
/* 180 */         return "about two minutes remaining";
/*     */       }
/* 182 */       return "about a minute remaining";
/*     */     }
/* 184 */     if (seconds > 45)
/* 185 */       return "less than a minute remaining";
/* 186 */     if (seconds > 0) {
/* 187 */       int remainingTens = (seconds / 5 + 1) * 5;
/* 188 */       return "less than " + remainingTens + " seconds remaining";
/*     */     }
/* 190 */     return "finishing...";
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/util/ProgressEstimator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */