/*     */ package cern.jet.stat.quantile;
/*     */ 
/*     */ import cern.colt.PersistentObject;
/*     */ import cern.colt.list.AbstractFloatList;
/*     */ import cern.colt.list.FloatArrayList;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EquiDepthHistogram
/*     */   extends PersistentObject
/*     */ {
/*     */   protected float[] binBoundaries;
/*     */   
/*     */   public EquiDepthHistogram(float[] paramArrayOfFloat)
/*     */   {
/*  36 */     this.binBoundaries = this.binBoundaries;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int binOfElement(float paramFloat)
/*     */   {
/*  46 */     int i = Arrays.binarySearch(this.binBoundaries, paramFloat);
/*  47 */     if (i >= 0)
/*     */     {
/*  49 */       if (i == this.binBoundaries.length - 1) i--;
/*     */     }
/*     */     else
/*     */     {
/*  53 */       i++;
/*  54 */       if ((i == 0) || (i == this.binBoundaries.length)) {
/*  55 */         throw new IllegalArgumentException("Element=" + paramFloat + " not contained in any bin.");
/*     */       }
/*  57 */       i--;
/*     */     }
/*  59 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */   public int bins()
/*     */   {
/*  65 */     return this.binBoundaries.length - 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public float endOfBin(int paramInt)
/*     */   {
/*  72 */     return this.binBoundaries[(paramInt + 1)];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double percentFromTo(float paramFloat1, float paramFloat2)
/*     */   {
/*  81 */     return phi(paramFloat2) - phi(paramFloat1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double phi(float paramFloat)
/*     */   {
/*  91 */     int i = this.binBoundaries.length;
/*  92 */     if (paramFloat <= this.binBoundaries[0]) return 0.0D;
/*  93 */     if (paramFloat >= this.binBoundaries[(i - 1)]) { return 1.0D;
/*     */     }
/*  95 */     double d1 = 1.0D / (i - 1);
/*  96 */     int j = new FloatArrayList(this.binBoundaries).binarySearch(paramFloat);
/*  97 */     if (j >= 0) {
/*  98 */       return d1 * j;
/*     */     }
/*     */     
/*     */ 
/* 102 */     int k = -j - 1;
/* 103 */     double d2 = this.binBoundaries[(k - 1)];
/* 104 */     double d3 = this.binBoundaries[k] - d2;
/* 105 */     double d4 = (paramFloat - d2) / d3;
/* 106 */     return d1 * (d4 + (k - 1));
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public int size()
/*     */   {
/* 114 */     return this.binBoundaries.length;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public float startOfBin(int paramInt)
/*     */   {
/* 121 */     return this.binBoundaries[paramInt];
/*     */   }
/*     */   
/*     */ 
/*     */   public static void test(float paramFloat)
/*     */   {
/* 127 */     float[] arrayOfFloat = { 50.0F, 100.0F, 200.0F, 300.0F, 1400.0F, 1500.0F, 1600.0F, 1700.0F, 1800.0F, 1900.0F, 2000.0F };
/*     */     
/* 129 */     EquiDepthHistogram localEquiDepthHistogram = new EquiDepthHistogram(arrayOfFloat);
/* 130 */     System.out.println("elem=" + paramFloat + ", phi=" + localEquiDepthHistogram.phi(paramFloat));
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/stat/quantile/EquiDepthHistogram.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */