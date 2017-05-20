/*     */ package flanagan.physprop;
/*     */ 
/*     */ import flanagan.complex.Complex;
/*     */ import flanagan.optics.RefractiveIndex;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RefrIndex
/*     */ {
/*     */   public static Complex absToComplex(double riReal, double extCoeff, double concn, double wavl)
/*     */   {
/*  58 */     return RefractiveIndex.absToComplex(riReal, extCoeff, concn, wavl);
/*     */   }
/*     */   
/*     */ 
/*     */   public static double imagToAbs(double riImag, double wavl)
/*     */   {
/*  64 */     return RefractiveIndex.imagToAbs(riImag, wavl);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static Complex gold(double wavelength)
/*     */   {
/*  71 */     return RefractiveIndex.gold(wavelength);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static Complex silver(double wavelength)
/*     */   {
/*  78 */     return RefractiveIndex.silver(wavelength);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static double quartz(double wavelength)
/*     */   {
/*  85 */     return RefractiveIndex.quartz(wavelength);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static double crownGlass(double wavelength)
/*     */   {
/*  92 */     return RefractiveIndex.crownGlass(wavelength);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double floatGlass(double wavelength)
/*     */   {
/* 100 */     return RefractiveIndex.floatGlass(wavelength);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static double microSlideGlass(double wavelength)
/*     */   {
/* 107 */     return RefractiveIndex.microscopeSlideGlass(wavelength);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static double polymethacrylate(double wavelength)
/*     */   {
/* 114 */     return RefractiveIndex.polymethacrylate(wavelength);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static double air(double wavelength)
/*     */   {
/* 121 */     return RefractiveIndex.air(wavelength);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static double water(double wavelength, double temperature)
/*     */   {
/* 128 */     return RefractiveIndex.water(wavelength, temperature);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double pva(double concn, double wavl, double temp)
/*     */   {
/* 139 */     return RefractiveIndex.pva(concn, wavl, temp);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static double saline(double concentration, double wavelength, double temperature)
/*     */   {
/* 146 */     return RefractiveIndex.saline(concentration, wavelength, temperature);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double sucrose(double concentration, double temperature)
/*     */   {
/* 156 */     return RefractiveIndex.water(concentration, temperature);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double lorenzLorentz(double na, double nb, double molwta, double molwtb, double molfracta, double densa, double densb, double densab)
/*     */   {
/* 164 */     return RefractiveIndex.lorenzLorentz(na, nb, molwta, molwtb, molfracta, densa, densb, densab);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double lorenzLorentz(double[] ni, double[] molwt, double[] molfract, double[] dens, double densmix)
/*     */   {
/* 173 */     return RefractiveIndex.lorenzLorentz(ni, molwt, molfract, dens, densmix);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/physprop/RefrIndex.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */