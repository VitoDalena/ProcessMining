/*     */ package edu.cornell.lassp.houle.RngPack;
/*     */ 
/*     */ import cern.colt.PersistentObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class RandomElement
/*     */   extends PersistentObject
/*     */   implements Cloneable
/*     */ {
/*     */   double BMoutput;
/*  36 */   boolean BMoutputAvailable = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int choose(int paramInt)
/*     */   {
/*  45 */     return choose(1, paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int choose(int paramInt1, int paramInt2)
/*     */   {
/*  55 */     return (int)(paramInt1 + ((1L + paramInt2 - paramInt1) * raw()));
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
/*     */   public double gaussian()
/*     */   {
/*  69 */     if (this.BMoutputAvailable)
/*     */     {
/*  71 */       this.BMoutputAvailable = false;
/*  72 */       return this.BMoutput;
/*     */     }
/*     */     double d1;
/*     */     double d2;
/*     */     double d3;
/*  77 */     do { d1 = 2.0D * raw() - 1.0D;
/*  78 */       d2 = 2.0D * raw() - 1.0D;
/*  79 */       d3 = d1 * d1 + d2 * d2;
/*  80 */     } while (d3 >= 1.0D);
/*     */     
/*  82 */     double d4 = Math.sqrt(-2.0D * Math.log(d3) / d3);
/*  83 */     this.BMoutput = (d1 * d4);
/*  84 */     this.BMoutputAvailable = true;
/*  85 */     return d2 * d4;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double gaussian(double paramDouble)
/*     */   {
/* 119 */     return gaussian() * paramDouble;
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
/*     */ 
/*     */   public double powlaw(double paramDouble1, double paramDouble2)
/*     */   {
/* 136 */     return paramDouble2 * Math.pow(raw(), 1.0D / (paramDouble1 + 1.0D));
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
/*     */   public abstract double raw();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void raw(double[] paramArrayOfDouble)
/*     */   {
/* 160 */     raw(paramArrayOfDouble, paramArrayOfDouble.length);
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
/*     */   public void raw(double[] paramArrayOfDouble, int paramInt)
/*     */   {
/* 176 */     for (int i = 0; i < paramInt; i++) {
/* 177 */       paramArrayOfDouble[i] = raw();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double uniform(double paramDouble1, double paramDouble2)
/*     */   {
/* 186 */     return paramDouble1 + (paramDouble2 - paramDouble1) * raw();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/cornell/lassp/houle/RngPack/RandomElement.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */