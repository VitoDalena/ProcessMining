/*     */ package cern.colt;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Timer
/*     */   extends PersistentObject
/*     */ {
/*     */   private long baseTime;
/*     */   private long elapsedTime;
/*     */   private static final long UNIT = 1000L;
/*     */   
/*     */   public Timer()
/*     */   {
/*  25 */     reset();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Timer display()
/*     */   {
/*  32 */     System.out.println(this);
/*  33 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */   public float elapsedTime()
/*     */   {
/*  39 */     return seconds();
/*     */   }
/*     */   
/*     */ 
/*     */   public long millis()
/*     */   {
/*  45 */     long l = this.elapsedTime;
/*  46 */     if (this.baseTime != 0L) {
/*  47 */       l += System.currentTimeMillis() - this.baseTime;
/*     */     }
/*  49 */     return l;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Timer minus(Timer paramTimer)
/*     */   {
/*  58 */     Timer localTimer = new Timer();
/*  59 */     localTimer.elapsedTime = (millis() - paramTimer.millis());
/*  60 */     return localTimer;
/*     */   }
/*     */   
/*     */ 
/*     */   public float minutes()
/*     */   {
/*  66 */     return seconds() / 60.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Timer plus(Timer paramTimer)
/*     */   {
/*  75 */     Timer localTimer = new Timer();
/*  76 */     localTimer.elapsedTime = (millis() + paramTimer.millis());
/*  77 */     return localTimer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Timer reset()
/*     */   {
/*  84 */     this.elapsedTime = 0L;
/*  85 */     this.baseTime = 0L;
/*  86 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */   public float seconds()
/*     */   {
/*  92 */     return (float)millis() / 1000.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Timer start()
/*     */   {
/*  99 */     this.baseTime = System.currentTimeMillis();
/* 100 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Timer stop()
/*     */   {
/* 107 */     if (this.baseTime != 0L) {
/* 108 */       this.elapsedTime += System.currentTimeMillis() - this.baseTime;
/*     */     }
/* 110 */     this.baseTime = 0L;
/* 111 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void test(int paramInt)
/*     */   {
/* 118 */     Timer localTimer = new Timer().start();
/* 119 */     int i = 0;
/* 120 */     for (int j = 0; j < paramInt; j++) {
/* 121 */       i++;
/*     */     }
/* 123 */     localTimer.stop();
/* 124 */     localTimer.display();
/* 125 */     System.out.println("I finished the test using " + localTimer);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 130 */     i = 0;
/* 131 */     for (int k = 0; k < paramInt; k++) {
/* 132 */       i++;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 138 */     localTimer.start();
/* 139 */     i = 0;
/* 140 */     for (int m = 0; m < paramInt; m++) {
/* 141 */       i++;
/*     */     }
/* 143 */     localTimer.stop().display();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 148 */     localTimer.reset();
/* 149 */     localTimer.start();
/* 150 */     i = 0;
/* 151 */     for (int n = 0; n < paramInt; n++) {
/* 152 */       i++;
/*     */     }
/* 154 */     localTimer.stop().display();
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 160 */     return "Time=" + Float.toString(elapsedTime()) + " secs";
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/Timer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */