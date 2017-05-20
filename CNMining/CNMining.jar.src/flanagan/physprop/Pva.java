/*     */ package flanagan.physprop;
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
/*     */ public class Pva
/*     */ {
/*     */   public static double viscosity(double concn, double molwt, double temp)
/*     */   {
/*  52 */     double intVisc30 = 4.53E-4D * Math.pow(molwt, 0.64D);
/*  53 */     double intVisc20 = intVisc30 * 1.07D;
/*  54 */     double intViscT = intVisc20 * Math.pow(1.07D, -(temp - 20.0D) / 10.0D);
/*  55 */     double concndlpg = concn / 10.0D;
/*  56 */     double spViscT = concndlpg * (intViscT + 0.201D * concndlpg * Math.pow(intViscT, 2.28D));
/*  57 */     double waterViscT = Water.viscosity(temp);
/*  58 */     double viscosity = (spViscT + 1.0D) * waterViscT;
/*     */     
/*  60 */     return viscosity;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double density(double concn, double molwt)
/*     */   {
/*  70 */     concn /= 10.0D;
/*  71 */     double density = 1000.0D * (0.99565D + (0.00248D - 1.09D / molwt) * concn + (6.4E-5D - 0.39D / molwt) * concn * concn);
/*     */     
/*  73 */     return density;
/*     */   }
/*     */   
/*     */ 
/*     */   public static double specificVolume()
/*     */   {
/*  79 */     return 7.65E-4D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double diffCoeff(double concn, double molwt, double temp)
/*     */   {
/*  89 */     double tempa = temp - -273.15D;
/*     */     
/*  91 */     double viscosity = viscosity(concn, molwt, temp);
/*  92 */     double specvol = specificVolume();
/*  93 */     double vol = molwt * specvol / 6.0221419947E26D;
/*  94 */     double radius = Math.pow(3.0D * vol / 12.566370614359172D, 0.3333333333333333D);
/*     */     
/*  96 */     double f = 18.84955592153876D * viscosity * radius;
/*  97 */     double diffcoef = 1.380650324E-23D * tempa / f;
/*     */     
/*  99 */     return diffcoef;
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
/*     */   public static double refractIndex(double concn, double wavl, double temp)
/*     */   {
/* 112 */     double a1 = -0.998419D;double b1 = -1.87778E-17D;
/*     */     
/* 114 */     double rfwater = Water.refractIndex(wavl, temp);
/* 115 */     double rfincr = 1.0D + a1 * (1.0D + b1 / Math.pow(wavl, 2.0D));
/* 116 */     double refind = rfwater + rfincr * concn / 10.0D;
/*     */     
/* 118 */     return refind;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/physprop/Pva.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */