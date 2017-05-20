/*     */ package hep.aida.bin;
/*     */ 
/*     */ import cern.colt.matrix.impl.Former;
/*     */ import cern.colt.matrix.impl.FormerFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BinFunctions1D
/*     */ {
/*  18 */   public static final BinFunctions1D functions = new BinFunctions1D();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  23 */   public static final BinFunction1D max = new BinFunction1D() {
/*  24 */     public final double apply(DynamicBin1D paramAnonymousDynamicBin1D) { return paramAnonymousDynamicBin1D.max(); }
/*  25 */     public final String name() { return "Max"; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  31 */   public static final BinFunction1D mean = new BinFunction1D() {
/*  32 */     public final double apply(DynamicBin1D paramAnonymousDynamicBin1D) { return paramAnonymousDynamicBin1D.mean(); }
/*  33 */     public final String name() { return "Mean"; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  39 */   public static final BinFunction1D median = new BinFunction1D() {
/*  40 */     public final double apply(DynamicBin1D paramAnonymousDynamicBin1D) { return paramAnonymousDynamicBin1D.median(); }
/*  41 */     public final String name() { return "Median"; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  47 */   public static final BinFunction1D min = new BinFunction1D() {
/*  48 */     public final double apply(DynamicBin1D paramAnonymousDynamicBin1D) { return paramAnonymousDynamicBin1D.min(); }
/*  49 */     public final String name() { return "Min"; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  55 */   public static final BinFunction1D rms = new BinFunction1D() {
/*  56 */     public final double apply(DynamicBin1D paramAnonymousDynamicBin1D) { return paramAnonymousDynamicBin1D.rms(); }
/*  57 */     public final String name() { return "RMS"; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  63 */   public static final BinFunction1D size = new BinFunction1D() {
/*  64 */     public final double apply(DynamicBin1D paramAnonymousDynamicBin1D) { return paramAnonymousDynamicBin1D.size(); }
/*  65 */     public final String name() { return "Size"; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  71 */   public static final BinFunction1D stdDev = new BinFunction1D() {
/*  72 */     public final double apply(DynamicBin1D paramAnonymousDynamicBin1D) { return paramAnonymousDynamicBin1D.standardDeviation(); }
/*  73 */     public final String name() { return "StdDev"; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  79 */   public static final BinFunction1D sum = new BinFunction1D() {
/*  80 */     public final double apply(DynamicBin1D paramAnonymousDynamicBin1D) { return paramAnonymousDynamicBin1D.sum(); }
/*  81 */     public final String name() { return "Sum"; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*  86 */   public static final BinFunction1D sumLog = new BinFunction1D() {
/*  87 */     public final double apply(DynamicBin1D paramAnonymousDynamicBin1D) { return paramAnonymousDynamicBin1D.sumOfLogarithms(); }
/*  88 */     public final String name() { return "SumLog"; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*  93 */   public static final BinFunction1D geometricMean = new BinFunction1D() {
/*  94 */     public final double apply(DynamicBin1D paramAnonymousDynamicBin1D) { return paramAnonymousDynamicBin1D.geometricMean(); }
/*  95 */     public final String name() { return "GeomMean"; }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static BinFunction1D quantile(double paramDouble)
/*     */   {
/* 108 */     new BinFunction1D() {
/* 109 */       public final double apply(DynamicBin1D paramAnonymousDynamicBin1D) { return paramAnonymousDynamicBin1D.quantile(this.val$percentage); }
/* 110 */       public final String name() { return new FormerFactory().create("%1.2G").form(this.val$percentage * 100.0D) + "% Q."; }
/*     */     };
/*     */   }
/*     */   
/*     */   private final double val$percentage;
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/hep/aida/bin/BinFunctions1D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */