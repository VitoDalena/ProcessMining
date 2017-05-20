/*     */ package uk.ac.shef.wit.simmetrics.math;
/*     */ 
/*     */ import junit.framework.TestCase;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MathFuncsTest
/*     */   extends TestCase
/*     */ {
/*     */   protected void setUp() {}
/*     */   
/*     */   protected void tearDown() {}
/*     */   
/*     */   public void max3float()
/*     */   {
/*  75 */     assertEquals(Float.valueOf(0.3F), Float.valueOf(MathFuncs.max3(0.1F, 0.2F, 0.3F)));
/*  76 */     assertEquals(Float.valueOf(0.31F), Float.valueOf(MathFuncs.max3(0.31F, 0.2F, 0.3F)));
/*  77 */     assertEquals(Float.valueOf(0.5F), Float.valueOf(MathFuncs.max3(0.1F, 0.5F, 0.3F)));
/*  78 */     assertEquals(Float.valueOf(0.5F), Float.valueOf(MathFuncs.max3(-10.1F, 0.5F, -0.3F)));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void max3int()
/*     */   {
/*  85 */     assertEquals(5, MathFuncs.max3(-10, 5, 3));
/*  86 */     assertEquals(10, MathFuncs.max3(10, 5, 3));
/*  87 */     assertEquals(13, MathFuncs.max3(-10, 5, 13));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void min3int()
/*     */   {
/*  94 */     assertEquals(-10, MathFuncs.min3(-10, 5, 13));
/*  95 */     assertEquals(-13, MathFuncs.min3(-10, 5, -13));
/*  96 */     assertEquals(5, MathFuncs.min3(10, 5, 13));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void min3float()
/*     */   {
/* 103 */     assertEquals(Float.valueOf(5.45F), Float.valueOf(MathFuncs.min3(10.1F, 5.45F, 13.12F)));
/* 104 */     assertEquals(Float.valueOf(0.1F), Float.valueOf(MathFuncs.min3(0.1F, 5.45F, 13.12F)));
/* 105 */     assertEquals(Float.valueOf(-3.12F), Float.valueOf(MathFuncs.min3(10.1F, 5.45F, -3.12F)));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void max4float()
/*     */   {
/* 112 */     assertEquals(Float.valueOf(36.9F), Float.valueOf(MathFuncs.max4(10.1F, 5.45F, -3.12F, 36.9F)));
/* 113 */     assertEquals(Float.valueOf(10.1F), Float.valueOf(MathFuncs.max4(10.1F, 5.45F, -3.12F, 6.9F)));
/* 114 */     assertEquals(Float.valueOf(-3.12F), Float.valueOf(MathFuncs.max4(-10.1F, -5.45F, -3.12F, -36.9F)));
/* 115 */     assertEquals(Float.valueOf(25.4F), Float.valueOf(MathFuncs.max4(10.1F, 25.45F, -3.12F, 16.9F)));
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/math/MathFuncsTest.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */