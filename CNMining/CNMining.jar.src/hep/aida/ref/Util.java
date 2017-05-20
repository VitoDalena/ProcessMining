/*     */ package hep.aida.ref;
/*     */ 
/*     */ import hep.aida.IAxis;
/*     */ import hep.aida.IHistogram1D;
/*     */ import hep.aida.IHistogram2D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class Util
/*     */ {
/*     */   public int maxBin(IHistogram1D paramIHistogram1D)
/*     */   {
/*  19 */     int i = -1;
/*  20 */     double d1 = Double.MIN_VALUE;
/*  21 */     int j = paramIHistogram1D.xAxis().bins();
/*  22 */     do { double d2 = paramIHistogram1D.binHeight(j);
/*  23 */       if (d2 > d1) {
/*  24 */         d1 = d2;
/*  25 */         i = j;
/*     */       }
/*  21 */       j--; } while (j >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  28 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int maxBinX(IHistogram2D paramIHistogram2D)
/*     */   {
/*  35 */     double d1 = Double.MIN_VALUE;
/*  36 */     int i = -1;
/*  37 */     int j = -1;
/*  38 */     int k = paramIHistogram2D.xAxis().bins();
/*  39 */     do { int m = paramIHistogram2D.yAxis().bins();
/*  40 */       do { double d2 = paramIHistogram2D.binHeight(k, m);
/*  41 */         if (d2 > d1) {
/*  42 */           d1 = d2;
/*  43 */           i = k;
/*  44 */           j = m;
/*     */         }
/*  39 */         m--; } while (m >= 0);
/*  38 */       k--; } while (k >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  48 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int maxBinY(IHistogram2D paramIHistogram2D)
/*     */   {
/*  55 */     double d1 = Double.MIN_VALUE;
/*  56 */     int i = -1;
/*  57 */     int j = -1;
/*  58 */     int k = paramIHistogram2D.xAxis().bins();
/*  59 */     do { int m = paramIHistogram2D.yAxis().bins();
/*  60 */       do { double d2 = paramIHistogram2D.binHeight(k, m);
/*  61 */         if (d2 > d1) {
/*  62 */           d1 = d2;
/*  63 */           i = k;
/*  64 */           j = m;
/*     */         }
/*  59 */         m--; } while (m >= 0);
/*  58 */       k--; } while (k >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  68 */     return j;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int minBin(IHistogram1D paramIHistogram1D)
/*     */   {
/*  75 */     int i = -1;
/*  76 */     double d1 = Double.MAX_VALUE;
/*  77 */     int j = paramIHistogram1D.xAxis().bins();
/*  78 */     do { double d2 = paramIHistogram1D.binHeight(j);
/*  79 */       if (d2 < d1) {
/*  80 */         d1 = d2;
/*  81 */         i = j;
/*     */       }
/*  77 */       j--; } while (j >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  84 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int minBinX(IHistogram2D paramIHistogram2D)
/*     */   {
/*  91 */     double d1 = Double.MAX_VALUE;
/*  92 */     int i = -1;
/*  93 */     int j = -1;
/*  94 */     int k = paramIHistogram2D.xAxis().bins();
/*  95 */     do { int m = paramIHistogram2D.yAxis().bins();
/*  96 */       do { double d2 = paramIHistogram2D.binHeight(k, m);
/*  97 */         if (d2 < d1) {
/*  98 */           d1 = d2;
/*  99 */           i = k;
/* 100 */           j = m;
/*     */         }
/*  95 */         m--; } while (m >= 0);
/*  94 */       k--; } while (k >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 104 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int minBinY(IHistogram2D paramIHistogram2D)
/*     */   {
/* 111 */     double d1 = Double.MAX_VALUE;
/* 112 */     int i = -1;
/* 113 */     int j = -1;
/* 114 */     int k = paramIHistogram2D.xAxis().bins();
/* 115 */     do { int m = paramIHistogram2D.yAxis().bins();
/* 116 */       do { double d2 = paramIHistogram2D.binHeight(k, m);
/* 117 */         if (d2 < d1) {
/* 118 */           d1 = d2;
/* 119 */           i = k;
/* 120 */           j = m;
/*     */         }
/* 115 */         m--; } while (m >= 0);
/* 114 */       k--; } while (k >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 124 */     return j;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/hep/aida/ref/Util.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */