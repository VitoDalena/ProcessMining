/*     */ package cern.colt.matrix.doublealgo;
/*     */ 
/*     */ import cern.colt.PersistentObject;
/*     */ import cern.colt.matrix.DoubleMatrix1D;
/*     */ import cern.colt.matrix.DoubleMatrix2D;
/*     */ import cern.jet.math.Functions;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ /**
/*     */  * @deprecated
/*     */  */
/*     */ public class Transform
/*     */   extends PersistentObject
/*     */ {
/*  60 */   public static final Transform transform = new Transform();
/*     */   
/*  62 */   private static final Functions F = Functions.functions;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DoubleMatrix1D abs(DoubleMatrix1D paramDoubleMatrix1D)
/*     */   {
/*  74 */     return paramDoubleMatrix1D.assign(Functions.abs);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DoubleMatrix2D abs(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/*  82 */     return paramDoubleMatrix2D.assign(Functions.abs);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DoubleMatrix1D div(DoubleMatrix1D paramDoubleMatrix1D, double paramDouble)
/*     */   {
/*  91 */     return paramDoubleMatrix1D.assign(Functions.div(paramDouble));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DoubleMatrix1D div(DoubleMatrix1D paramDoubleMatrix1D1, DoubleMatrix1D paramDoubleMatrix1D2)
/*     */   {
/* 100 */     return paramDoubleMatrix1D1.assign(paramDoubleMatrix1D2, Functions.div);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DoubleMatrix2D div(DoubleMatrix2D paramDoubleMatrix2D, double paramDouble)
/*     */   {
/* 109 */     return paramDoubleMatrix2D.assign(Functions.div(paramDouble));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DoubleMatrix2D div(DoubleMatrix2D paramDoubleMatrix2D1, DoubleMatrix2D paramDoubleMatrix2D2)
/*     */   {
/* 118 */     return paramDoubleMatrix2D1.assign(paramDoubleMatrix2D2, Functions.div);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DoubleMatrix2D equals(DoubleMatrix2D paramDoubleMatrix2D, double paramDouble)
/*     */   {
/* 127 */     return paramDoubleMatrix2D.assign(Functions.equals(paramDouble));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DoubleMatrix2D equals(DoubleMatrix2D paramDoubleMatrix2D1, DoubleMatrix2D paramDoubleMatrix2D2)
/*     */   {
/* 136 */     return paramDoubleMatrix2D1.assign(paramDoubleMatrix2D2, Functions.equals);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DoubleMatrix2D greater(DoubleMatrix2D paramDoubleMatrix2D, double paramDouble)
/*     */   {
/* 145 */     return paramDoubleMatrix2D.assign(Functions.greater(paramDouble));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DoubleMatrix2D greater(DoubleMatrix2D paramDoubleMatrix2D1, DoubleMatrix2D paramDoubleMatrix2D2)
/*     */   {
/* 154 */     return paramDoubleMatrix2D1.assign(paramDoubleMatrix2D2, Functions.greater);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DoubleMatrix2D less(DoubleMatrix2D paramDoubleMatrix2D, double paramDouble)
/*     */   {
/* 163 */     return paramDoubleMatrix2D.assign(Functions.less(paramDouble));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DoubleMatrix2D less(DoubleMatrix2D paramDoubleMatrix2D1, DoubleMatrix2D paramDoubleMatrix2D2)
/*     */   {
/* 172 */     return paramDoubleMatrix2D1.assign(paramDoubleMatrix2D2, Functions.less);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DoubleMatrix1D minus(DoubleMatrix1D paramDoubleMatrix1D, double paramDouble)
/*     */   {
/* 181 */     return paramDoubleMatrix1D.assign(Functions.minus(paramDouble));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DoubleMatrix1D minus(DoubleMatrix1D paramDoubleMatrix1D1, DoubleMatrix1D paramDoubleMatrix1D2)
/*     */   {
/* 190 */     return paramDoubleMatrix1D1.assign(paramDoubleMatrix1D2, Functions.minus);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DoubleMatrix2D minus(DoubleMatrix2D paramDoubleMatrix2D, double paramDouble)
/*     */   {
/* 199 */     return paramDoubleMatrix2D.assign(Functions.minus(paramDouble));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DoubleMatrix2D minus(DoubleMatrix2D paramDoubleMatrix2D1, DoubleMatrix2D paramDoubleMatrix2D2)
/*     */   {
/* 208 */     return paramDoubleMatrix2D1.assign(paramDoubleMatrix2D2, Functions.minus);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DoubleMatrix1D minusMult(DoubleMatrix1D paramDoubleMatrix1D1, DoubleMatrix1D paramDoubleMatrix1D2, double paramDouble)
/*     */   {
/* 218 */     return paramDoubleMatrix1D1.assign(paramDoubleMatrix1D2, Functions.minusMult(paramDouble));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DoubleMatrix2D minusMult(DoubleMatrix2D paramDoubleMatrix2D1, DoubleMatrix2D paramDoubleMatrix2D2, double paramDouble)
/*     */   {
/* 228 */     return paramDoubleMatrix2D1.assign(paramDoubleMatrix2D2, Functions.minusMult(paramDouble));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DoubleMatrix1D mult(DoubleMatrix1D paramDoubleMatrix1D, double paramDouble)
/*     */   {
/* 237 */     return paramDoubleMatrix1D.assign(Functions.mult(paramDouble));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DoubleMatrix1D mult(DoubleMatrix1D paramDoubleMatrix1D1, DoubleMatrix1D paramDoubleMatrix1D2)
/*     */   {
/* 246 */     return paramDoubleMatrix1D1.assign(paramDoubleMatrix1D2, Functions.mult);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DoubleMatrix2D mult(DoubleMatrix2D paramDoubleMatrix2D, double paramDouble)
/*     */   {
/* 255 */     return paramDoubleMatrix2D.assign(Functions.mult(paramDouble));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DoubleMatrix2D mult(DoubleMatrix2D paramDoubleMatrix2D1, DoubleMatrix2D paramDoubleMatrix2D2)
/*     */   {
/* 264 */     return paramDoubleMatrix2D1.assign(paramDoubleMatrix2D2, Functions.mult);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static DoubleMatrix1D negate(DoubleMatrix1D paramDoubleMatrix1D)
/*     */   {
/* 271 */     return paramDoubleMatrix1D.assign(Functions.mult(-1.0D));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static DoubleMatrix2D negate(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/* 278 */     return paramDoubleMatrix2D.assign(Functions.mult(-1.0D));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DoubleMatrix1D plus(DoubleMatrix1D paramDoubleMatrix1D, double paramDouble)
/*     */   {
/* 287 */     return paramDoubleMatrix1D.assign(Functions.plus(paramDouble));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DoubleMatrix1D plus(DoubleMatrix1D paramDoubleMatrix1D1, DoubleMatrix1D paramDoubleMatrix1D2)
/*     */   {
/* 296 */     return paramDoubleMatrix1D1.assign(paramDoubleMatrix1D2, Functions.plus);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DoubleMatrix2D plus(DoubleMatrix2D paramDoubleMatrix2D, double paramDouble)
/*     */   {
/* 305 */     return paramDoubleMatrix2D.assign(Functions.plus(paramDouble));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DoubleMatrix2D plus(DoubleMatrix2D paramDoubleMatrix2D1, DoubleMatrix2D paramDoubleMatrix2D2)
/*     */   {
/* 314 */     return paramDoubleMatrix2D1.assign(paramDoubleMatrix2D2, Functions.plus);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DoubleMatrix1D plusMult(DoubleMatrix1D paramDoubleMatrix1D1, DoubleMatrix1D paramDoubleMatrix1D2, double paramDouble)
/*     */   {
/* 324 */     return paramDoubleMatrix1D1.assign(paramDoubleMatrix1D2, Functions.plusMult(paramDouble));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DoubleMatrix2D plusMult(DoubleMatrix2D paramDoubleMatrix2D1, DoubleMatrix2D paramDoubleMatrix2D2, double paramDouble)
/*     */   {
/* 334 */     return paramDoubleMatrix2D1.assign(paramDoubleMatrix2D2, Functions.plusMult(paramDouble));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DoubleMatrix1D pow(DoubleMatrix1D paramDoubleMatrix1D, double paramDouble)
/*     */   {
/* 343 */     return paramDoubleMatrix1D.assign(Functions.pow(paramDouble));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DoubleMatrix1D pow(DoubleMatrix1D paramDoubleMatrix1D1, DoubleMatrix1D paramDoubleMatrix1D2)
/*     */   {
/* 352 */     return paramDoubleMatrix1D1.assign(paramDoubleMatrix1D2, Functions.pow);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DoubleMatrix2D pow(DoubleMatrix2D paramDoubleMatrix2D, double paramDouble)
/*     */   {
/* 361 */     return paramDoubleMatrix2D.assign(Functions.pow(paramDouble));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DoubleMatrix2D pow(DoubleMatrix2D paramDoubleMatrix2D1, DoubleMatrix2D paramDoubleMatrix2D2)
/*     */   {
/* 370 */     return paramDoubleMatrix2D1.assign(paramDoubleMatrix2D2, Functions.pow);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/doublealgo/Transform.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */