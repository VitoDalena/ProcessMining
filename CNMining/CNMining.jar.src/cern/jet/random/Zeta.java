/*     */ package cern.jet.random;
/*     */ 
/*     */ import edu.cornell.lassp.houle.RngPack.RandomElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Zeta
/*     */   extends AbstractDiscreteDistribution
/*     */ {
/*  41 */   protected double ro_prev = -1.0D; protected double pk_prev = -1.0D;
/*  42 */   protected double maxlongint = 9.223372036854776E18D;
/*     */   
/*     */ 
/*  45 */   protected static Zeta shared = new Zeta(1.0D, 1.0D, AbstractDistribution.makeDefaultGenerator());
/*     */   protected double ro;
/*     */   protected double pk;
/*     */   
/*     */   public Zeta(double paramDouble1, double paramDouble2, RandomElement paramRandomElement) {
/*  50 */     setRandomGenerator(paramRandomElement);
/*  51 */     setState(paramDouble1, paramDouble2);
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
/*     */   protected double c;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected double d;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected long generateZeta(double paramDouble1, double paramDouble2, RandomElement paramRandomElement)
/*     */   {
/*  98 */     if ((paramDouble1 != this.ro_prev) || (paramDouble2 != this.pk_prev)) {
/*  99 */       this.ro_prev = paramDouble1;
/* 100 */       this.pk_prev = paramDouble2;
/* 101 */       if (paramDouble1 < paramDouble2) {
/* 102 */         this.c = (paramDouble2 - 0.5D);
/* 103 */         this.d = 0.0D;
/*     */       }
/*     */       else {
/* 106 */         this.c = (paramDouble1 - 0.5D);
/* 107 */         this.d = ((1.0D + paramDouble1) * Math.log((1.0D + paramDouble2) / (1.0D + paramDouble1))); } }
/*     */     double d4;
/*     */     long l;
/*     */     double d3;
/*     */     do { double d2;
/* 112 */       do { double d1 = paramRandomElement.raw();
/* 113 */         d2 = paramRandomElement.raw();
/* 114 */         d4 = (this.c + 0.5D) * Math.exp(-Math.log(d1) / paramDouble1) - this.c;
/* 115 */       } while ((d4 <= 0.5D) || (d4 >= this.maxlongint));
/*     */       
/* 117 */       l = (int)(d4 + 0.5D);
/* 118 */       d3 = -Math.log(d2);
/* 119 */     } while (d3 < (1.0D + paramDouble1) * Math.log((l + paramDouble2) / (d4 + this.c)) - this.d);
/*     */     
/* 121 */     return l;
/*     */   }
/*     */   
/*     */ 
/*     */   public int nextInt()
/*     */   {
/* 127 */     return (int)generateZeta(this.ro, this.pk, this.randomGenerator);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setState(double paramDouble1, double paramDouble2)
/*     */   {
/* 133 */     this.ro = paramDouble1;
/* 134 */     this.pk = paramDouble2;
/*     */   }
/*     */   
/*     */ 
/*     */   public static int staticNextInt(double paramDouble1, double paramDouble2)
/*     */   {
/* 140 */     synchronized (shared) {
/* 141 */       shared.setState(paramDouble1, paramDouble2);
/* 142 */       int i = shared.nextInt();return i;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 149 */     return getClass().getName() + "(" + this.ro + "," + this.pk + ")";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private static void xstaticSetRandomGenerator(RandomElement paramRandomElement)
/*     */   {
/* 156 */     synchronized (shared) {
/* 157 */       shared.setRandomGenerator(paramRandomElement);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/random/Zeta.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */