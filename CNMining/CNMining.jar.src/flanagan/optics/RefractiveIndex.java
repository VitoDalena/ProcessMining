/*     */ package flanagan.optics;
/*     */ 
/*     */ import flanagan.complex.Complex;
/*     */ import flanagan.interpolation.BiCubicSpline;
/*     */ import flanagan.interpolation.CubicSpline;
/*     */ import flanagan.physprop.Saline;
/*     */ import flanagan.physprop.Sucrose;
/*     */ import flanagan.physprop.Water;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RefractiveIndex
/*     */ {
/*  48 */   private static double imagPlusMinus = -1.0D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void setComlexImagAsPositive()
/*     */   {
/*  55 */     imagPlusMinus = 1.0D;
/*     */   }
/*     */   
/*     */   public static void setComlexImagAsNegative()
/*     */   {
/*  60 */     imagPlusMinus = -1.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Complex absToComplex(double riReal, double extCoeff, double concn, double wavl)
/*     */   {
/*  69 */     Complex ri = new Complex();
/*  70 */     ri.reset(riReal, extCoeff * concn * wavl / 12.566370614359172D);
/*  71 */     return ri;
/*     */   }
/*     */   
/*     */ 
/*     */   public static double imagToAbs(double riImag, double wavl)
/*     */   {
/*  77 */     return 4.0D * riImag * 3.141592653589793D / wavl;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Complex gold(double wavelength)
/*     */   {
/*  85 */     double[] wavl = { 1.87855E-7D, 1.91629E-7D, 1.9525E-7D, 1.99331E-7D, 2.03253E-7D, 2.07331E-7D, 2.11939E-7D, 2.16377E-7D, 2.214E-7D, 2.26248E-7D, 2.31314E-7D, 2.37063E-7D, 2.4263E-7D, 2.48964E-7D, 2.55111E-7D, 2.6157E-7D, 2.68946E-7D, 2.76134E-7D, 2.84367E-7D, 2.92415E-7D, 3.00932E-7D, 3.10737E-7D, 3.20372E-7D, 3.31508E-7D, 3.42497E-7D, 3.5424E-7D, 3.67905E-7D, 3.81489E-7D, 3.97385E-7D, 4.1328E-7D, 4.305E-7D, 4.50851E-7D, 4.71422E-7D, 4.95936E-7D, 5.20941E-7D, 5.48602E-7D, 5.82085E-7D, 6.16836E-7D, 6.5949E-7D, 7.04455E-7D, 7.56E-7D, 8.21086E-7D, 8.91972E-7D, 9.84E-7D, 1.08758E-6D, 1.21553E-6D, 1.39308E-6D, 1.61018E-6D, 1.93725E-6D };
/*     */     
/*  87 */     double[] rfInRe = { 1.28D, 1.32D, 1.34D, 1.33D, 1.33D, 1.3D, 1.3D, 1.3D, 1.3D, 1.31D, 1.3D, 1.32D, 1.32D, 1.33D, 1.33D, 1.35D, 1.38D, 1.43D, 1.47D, 1.49D, 1.53D, 1.53D, 1.54D, 1.48D, 1.48D, 1.5D, 1.48D, 1.46D, 1.47D, 1.46D, 1.45D, 1.38D, 1.31D, 1.04D, 0.62D, 0.43D, 0.29D, 0.21D, 0.14D, 0.13D, 0.14D, 0.16D, 0.17D, 0.22D, 0.27D, 0.35D, 0.43D, 0.56D, 0.92D };
/*     */     
/*  89 */     double[] rfInIm = { 1.188D, 1.203D, 1.226D, 1.251D, 1.277D, 1.304D, 1.35D, 1.387D, 1.427D, 1.46D, 1.497D, 1.536D, 1.577D, 1.631D, 1.688D, 1.749D, 1.803D, 1.847D, 1.869D, 1.878D, 1.889D, 1.893D, 1.898D, 1.883D, 1.871D, 1.866D, 1.895D, 1.933D, 1.952D, 1.958D, 1.948D, 1.914D, 1.849D, 1.833D, 2.081D, 2.455D, 2.863D, 3.272D, 3.697D, 4.103D, 4.542D, 5.083D, 5.663D, 6.35D, 7.15D, 8.145D, 9.519D, 11.21D, 13.78D };
/*     */     
/*  91 */     double[] derivRe = { 0.0D, -1.17631E15D, -3.60547E15D, 2.92961E15D, -4.45568E15D, 3.84052E15D, -9.56586E14D, -8.80015E13D, 1.17669E15D, -2.14766E15D, 2.49889E15D, -1.81842E15D, 1.06253E15D, -8.99034E14D, 1.01496E15D, -2.29767E14D, 7.62849E14D, -4.44179E14D, -5.30697E14D, 8.3214E14D, -1.17757E15D, 8.04127E14D, -1.40022E15D, 1.06549E15D, 7.02889E13D, -3.99E14D, 3.29142E13D, 2.65475E14D, -2.19619E14D, 1.38062E14D, -3.11414E14D, 1.90132E14D, -4.37649E14D, -4.12661E14D, 6.75965E14D, -4.75615E13D, 9.68919E13D, -1.02252E13D, 5.115E13D, -3.33214E11D, 5.09764E12D, -7.56272E12D, 1.02639E13D, -4.28914E12D, 3.57066E12D, -2.76676E12D, 1.04546E12D, 2.55831E12D, 0.0D };
/*     */     
/*  93 */     double[] derivIm = { 0.0D, 1.07557E15D, -4.54022E14D, 4.27299E14D, -5.01416E14D, 1.54402E15D, -9.99891E14D, 2.48281E14D, -4.98264E14D, 3.40549E14D, -2.67834E14D, 1.65108E14D, 2.31595E14D, 8.3984E13D, 1.49838E14D, -5.0561E14D, 3.84443E13D, -6.38396E14D, -1.55687E14D, 1.24515E14D, -2.15188E14D, 1.55368E14D, -3.38857E14D, 1.24305E14D, -1.79372E13D, 2.93519E14D, 4.26729E13D, -1.68238E14D, -1.7188E13D, -7.16957E13D, -4.22518E13D, -1.04677E14D, 2.39351E13D, 6.13432E14D, 8.33595E13D, -9.04645E13D, 2.22081E13D, -7.1846E13D, -1.13135E13D, -1.24725E13D, -3.07173E12D, 2.01135E12D, -1.58933E13D, 7.97278E12D, -1.02502E12D, -2.60397E11D, 3.57043E11D, 3.07012E11D, 0.0D };
/*  94 */     int n = wavl.length;
/*     */     
/*  96 */     Complex ri = new Complex();
/*     */     
/*  98 */     if ((wavelength >= wavl[0]) && (wavelength <= wavl[(n - 1)]))
/*     */     {
/* 100 */       double yRe = CubicSpline.interpolate(wavelength, wavl, rfInRe, derivRe);
/*     */       
/* 102 */       double yIm = CubicSpline.interpolate(wavelength, wavl, rfInIm, derivIm);
/* 103 */       ri.reset(yRe, imagPlusMinus * yIm);
/*     */     }
/*     */     else {
/* 106 */       throw new IllegalArgumentException("Wavelength is outside the limits (187.86nm - 1937.2nm) of the tabulated data"); }
/*     */     double yIm;
/* 108 */     double yRe; return ri;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Complex silver(double wavelength)
/*     */   {
/* 116 */     double[] wavl = { 1.87855E-7D, 1.91629E-7D, 1.9525E-7D, 1.99331E-7D, 2.03253E-7D, 2.07331E-7D, 2.11939E-7D, 2.16377E-7D, 2.214E-7D, 2.26248E-7D, 2.31314E-7D, 2.37063E-7D, 2.4263E-7D, 2.48964E-7D, 2.55111E-7D, 2.6157E-7D, 2.68946E-7D, 2.76134E-7D, 2.84367E-7D, 2.92415E-7D, 3.00932E-7D, 3.10737E-7D, 3.20372E-7D, 3.31508E-7D, 3.42497E-7D, 3.5424E-7D, 3.67905E-7D, 3.81489E-7D, 3.97385E-7D, 4.1328E-7D, 4.305E-7D, 4.50851E-7D, 4.71422E-7D, 4.95936E-7D, 5.20941E-7D, 5.48602E-7D, 5.82085E-7D, 6.16836E-7D, 6.5949E-7D, 7.04455E-7D, 7.56E-7D, 8.21086E-7D, 8.91972E-7D, 9.84E-7D, 1.08758E-6D, 1.21553E-6D, 1.39308E-6D, 1.61018E-6D, 1.93725E-6D };
/*     */     
/* 118 */     double[] rfInRe = { 1.07D, 1.1D, 1.12D, 1.14D, 1.15D, 1.18D, 1.2D, 1.22D, 1.25D, 1.26D, 1.28D, 1.28D, 1.3D, 1.31D, 1.33D, 1.35D, 1.38D, 1.41D, 1.41D, 1.39D, 1.34D, 1.13D, 0.81D, 0.17D, 0.14D, 0.1D, 0.07D, 0.05D, 0.05D, 0.05D, 0.04D, 0.04D, 0.05D, 0.05D, 0.05D, 0.06D, 0.05D, 0.06D, 0.05D, 0.04D, 0.03D, 0.04D, 0.04D, 0.04D, 0.04D, 0.09D, 0.13D, 0.15D, 0.24D };
/*     */     
/* 120 */     double[] rfInIm = { 1.212D, 1.232D, 1.255D, 1.277D, 1.296D, 1.312D, 1.325D, 1.336D, 1.342D, 1.344D, 1.357D, 1.367D, 1.378D, 1.389D, 1.393D, 1.387D, 1.372D, 1.331D, 1.264D, 1.161D, 0.964D, 0.616D, 0.392D, 0.829D, 1.142D, 1.419D, 1.657D, 1.864D, 2.07D, 2.275D, 2.462D, 2.657D, 2.869D, 3.093D, 3.324D, 3.586D, 3.858D, 4.152D, 4.483D, 4.838D, 5.242D, 5.727D, 6.312D, 6.992D, 7.795D, 8.828D, 10.1D, 11.85D, 14.08D };
/*     */     
/* 122 */     double[] derivRe = { 0.0D, -1.09443E15D, 4.50671E14D, -1.64535E15D, 2.64916E15D, -1.73921E15D, 2.84877E14D, 8.69272E14D, -1.77518E15D, 1.48932E15D, -1.89758E15D, 1.70681E15D, -1.10717E15D, 7.52799E14D, -2.81357E14D, 2.35816E14D, 1.51436E14D, -7.66855E14D, -3.01095E14D, 1.50003E14D, -2.68399E15D, 3.86776E14D, -6.17426E15D, 9.62736E15D, -2.62139E15D, 7.94178E14D, -1.68944E14D, 1.98255E14D, -3.52453E13D, -5.72818E13D, 5.05039E13D, 3.32047E13D, -4.0284E13D, 1.33103E12D, 3.42212E13D, -5.30981E13D, 4.73552E13D, -3.35549E13D, 9.74718E12D, -4.54863E12D, 1.1835E13D, -6.76495E12D, 2.08137E12D, -2.15834E12D, 6.30268E12D, -2.73772E12D, -7.13134E11D, 1.15139E12D, 0.0D };
/*     */     
/* 124 */     double[] derivIm = { 0.0D, 5.49257E14D, -4.99584E14D, -1.45243E13D, -2.5674E14D, -3.33755E14D, 5.01553E13D, -3.21087E14D, -3.68608E14D, 8.65942E14D, -4.85862E14D, 2.02151E14D, -6.51859E13D, -1.59369E14D, -3.45625E14D, 3.33754E13D, -7.21153E14D, -1.75627E14D, -4.86315E14D, -1.32704E15D, -1.65707E15D, -2.19007E14D, 1.01945E16D, -4.17064E15D, 5.88854E14D, -8.77761E14D, 4.82107E13D, -2.72544E14D, 1.09375E14D, -1.88393E14D, -8.63708E13D, 1.01638E14D, -1.07779E14D, 2.5244E13D, 2.97971E13D, -8.56009E13D, 4.64112E13D, -4.16531E13D, 1.48883E13D, -5.07977E11D, -1.77458E13D, 2.84057E13D, -2.4881E13D, 9.90519E12D, 5.74546E12D, -1.37589E13D, 1.24799E13D, -9.3404E12D, 0.0D };
/* 125 */     int n = wavl.length;
/*     */     
/* 127 */     Complex ri = new Complex();
/*     */     
/* 129 */     if ((wavelength >= wavl[0]) && (wavelength <= wavl[(n - 1)]))
/*     */     {
/* 131 */       double yRe = CubicSpline.interpolate(wavelength, wavl, rfInRe, derivRe);
/*     */       
/* 133 */       double yIm = CubicSpline.interpolate(wavelength, wavl, rfInIm, derivIm);
/* 134 */       ri.reset(yRe, imagPlusMinus * yIm);
/*     */     }
/*     */     else {
/* 137 */       throw new IllegalArgumentException("Wavelength is outside the limits (187.86nm - 1937.2nm) of the tabulated data"); }
/*     */     double yIm;
/* 139 */     double yRe; return ri;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double quartz(double wavelength)
/*     */   {
/* 147 */     double[] wavl = { 1.85E-7D, 2.14E-7D, 2.75E-7D, 3.61E-7D, 5.09E-7D, 5.89E-7D, 6.56E-7D };
/*     */     
/* 149 */     double[] rfInRe = { 1.57464D, 1.53386D, 1.49634D, 1.47503D, 1.4619D, 1.4583D, 1.4564D };
/*     */     
/* 151 */     double[] derivRe = { 0.0D, 2.58206E13D, 1.62375E12D, 1.75944E12D, -5.81947E10D, 3.55464E11D, 0.0D };
/*     */     
/* 153 */     double a1 = 0.444046D;double b1 = 9.677366E-15D;
/* 154 */     int n = wavl.length;
/*     */     
/*     */     double yRe;
/*     */     double yRe;
/* 158 */     if ((wavelength >= wavl[0]) && (wavelength <= wavl[(n - 1)]))
/*     */     {
/* 160 */       yRe = CubicSpline.interpolate(wavelength, wavl, rfInRe, derivRe);
/*     */     }
/*     */     else {
/* 163 */       System.out.println("Wavelength passed (" + wavelength * 1.0E7D + "nm) to RefractiveIndex.quartz() is outside");
/* 164 */       System.out.println("the experimental data limits (185.0 nm - 656.0 nm).   Extrapolation used");
/* 165 */       System.out.println("the Caunchy equation which may not be valid at the wavelength requested,");
/* 166 */       System.out.println(" especially if the wavelength is within an absorption band");
/* 167 */       yRe = 1.0D + a1 * (1.0D + b1 / Math.pow(wavelength, 2.0D));
/*     */     }
/* 169 */     return yRe;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double crownGlass(double wavelength)
/*     */   {
/* 177 */     double[] wavl = { 3.6502E-7D, 4.0466E-7D, 4.3584E-7D, 4.7999E-7D, 4.8613E-7D, 5.4607E-7D, 5.8756E-7D, 6.4385E-7D, 6.5628E-7D, 7.0652E-7D, 8.5211E-7D, 1.014E-6D };
/*     */     
/* 179 */     double[] rfInRe = { 1.83028D, 1.8169D, 1.80916D, 1.8009D, 1.79994D, 1.79227D, 1.78831D, 1.7841D, 1.7833D, 1.78048D, 1.7746D, 1.77018D };
/*     */     
/* 181 */     double[] derivRe = { 0.0D, 3.48108E12D, 1.37108E12D, 1.17265E12D, 9.68655E11D, 5.86009E11D, 4.3771E11D, 2.48861E11D, 3.01116E11D, 1.7006E11D, 8.74046E10D, 0.0D };
/*     */     
/* 183 */     double a1 = 0.762002D;double b1 = 1.18516E-14D;
/* 184 */     int n = wavl.length;
/*     */     
/*     */     double yRe;
/*     */     double yRe;
/* 188 */     if ((wavelength >= wavl[0]) && (wavelength <= wavl[(n - 1)]))
/*     */     {
/* 190 */       yRe = CubicSpline.interpolate(wavelength, wavl, rfInRe, derivRe);
/*     */     }
/*     */     else {
/* 193 */       System.out.println("Wavelength passed (" + wavelength * 1.0E7D + "nm) to RefractiveIndex.crownGlass() is outside");
/* 194 */       System.out.println("the experimental data limits (365.02 nm - 1014.0 nm).   Extrapolation used");
/* 195 */       System.out.println("the Caunchy equation which may not be valid at the wavelength requested,");
/* 196 */       System.out.println(" especially if the wavelength is within an absorption band");
/* 197 */       yRe = 1.0D + a1 * (1.0D + b1 / Math.pow(wavelength, 2.0D));
/*     */     }
/* 199 */     return yRe;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double floatGlass(double wavelength)
/*     */   {
/* 207 */     double[] wavl = { 5.435E-7D, 5.941E-7D, 6.04E-7D, 6.119E-7D, 6.328E-7D };
/*     */     
/* 209 */     double[] rfInRe = { 1.51958D, 1.51707D, 1.51671D, 1.5163D, 1.51553D };
/*     */     
/* 211 */     double[] derivRe = { 0.0D, 9.28695E11D, -3.3258E12D, 2.02454E12D, 0.0D };
/*     */     
/* 213 */     double a1 = 0.504167D;double b1 = 9.03525E-15D;
/* 214 */     int n = wavl.length;
/*     */     
/*     */     double yRe;
/*     */     double yRe;
/* 218 */     if ((wavelength >= wavl[0]) && (wavelength <= wavl[(n - 1)]))
/*     */     {
/* 220 */       yRe = CubicSpline.interpolate(wavelength, wavl, rfInRe, derivRe);
/*     */     }
/*     */     else {
/* 223 */       System.out.println("Wavelength passed (" + wavelength * 1.0E7D + "nm) to RefractiveIndex.floatGlass() is outside");
/* 224 */       System.out.println("the experimental data limits (543.5 nm - 632.8 nm).   Extrapolation used");
/* 225 */       System.out.println("the Caunchy equation which may not be valid at the wavelength requested,");
/* 226 */       System.out.println(" especially if the wavelength is within an absorption band");
/* 227 */       yRe = 1.0D + a1 * (1.0D + b1 / Math.pow(wavelength, 2.0D));
/*     */     }
/* 229 */     return yRe;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double microscopeSlideGlass(double wavelength)
/*     */   {
/* 237 */     double[] wavl = { 5.435E-7D, 5.941E-7D, 6.04E-7D, 6.119E-7D, 6.328E-7D };
/*     */     
/* 239 */     double[] rfInRe = { 1.51436D, 1.51184D, 1.51144D, 1.51111D, 1.51027D };
/*     */     
/* 241 */     double[] derivRe = { 0.0D, 5.00315E11D, -4.19006E11D, 2.22131E11D, 0.0D };
/*     */     
/* 243 */     double a1 = 0.498854D;double b1 = 9.18748E-15D;
/* 244 */     int n = wavl.length;
/*     */     
/*     */     double yRe;
/*     */     double yRe;
/* 248 */     if ((wavelength >= wavl[0]) && (wavelength <= wavl[(n - 1)]))
/*     */     {
/* 250 */       yRe = CubicSpline.interpolate(wavelength, wavl, rfInRe, derivRe);
/*     */     }
/*     */     else {
/* 253 */       System.out.println("Wavelength passed (" + wavelength * 1.0E7D + "nm) to RefractiveIndex.microSlideGlass() is outside");
/* 254 */       System.out.println("the experimental data limits (543.5 nm - 632.8 nm).   Extrapolation used");
/* 255 */       System.out.println("the Caunchy equation which may not be valid at the wavelength requested,");
/* 256 */       System.out.println(" especially if the wavelength is within an absorption band");
/* 257 */       yRe = 1.0D + a1 * (1.0D + b1 / Math.pow(wavelength, 2.0D));
/*     */     }
/* 259 */     return yRe;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double polymethacrylate(double wavelength)
/*     */   {
/* 267 */     double[] wavl = { 4.358E-7D, 5.461E-7D, 5.893E-7D };
/*     */     
/* 269 */     double[] rfInRe = { 1.502D, 1.494D, 1.492D };
/*     */     
/* 271 */     double[] derivRe = { 0.0D, 5.127E11D, 0.0D };
/*     */     
/* 273 */     double a1 = 0.498854D;double b1 = 9.18748E-15D;
/* 274 */     int n = wavl.length;
/*     */     
/*     */     double yRe;
/*     */     double yRe;
/* 278 */     if ((wavelength >= wavl[0]) && (wavelength <= wavl[(n - 1)]))
/*     */     {
/* 280 */       yRe = CubicSpline.interpolate(wavelength, wavl, rfInRe, derivRe);
/*     */     }
/*     */     else {
/* 283 */       System.out.println("Wavelength passed (" + wavelength * 1.0E7D + "nm) to RefrIndex.polymethacrylate() is outside");
/* 284 */       System.out.println("the experimental data limits (435.8 nm - 589.3 nm).   Extrapolation used");
/* 285 */       System.out.println("the Caunchy equation which may not be valid at the wavelength requested,");
/* 286 */       System.out.println(" especially if the wavelength is within an absorption band");
/* 287 */       yRe = 1.0D + a1 * (1.0D + b1 / Math.pow(wavelength, 2.0D));
/*     */     }
/* 289 */     return yRe;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double air(double wavelength)
/*     */   {
/* 297 */     double a1 = 2.879E-4D;double b1 = 5.67E-11D;
/*     */     
/* 299 */     wavelength *= 100.0D;
/* 300 */     if ((wavelength < 2.498E-5D) || (wavelength > 7.594E-5D)) {
/* 301 */       System.out.println("Wavelength passed (" + wavelength * 1.0E7D + "nm) to RefractiveIndex.air() is outside");
/* 302 */       System.out.println("the experimental data limits (249.8 nm - 759.4 nm).   Extrapolation using");
/* 303 */       System.out.println("the Caunchy equation may not be valid at the wavelength requested,");
/* 304 */       System.out.println(" especially if the wavelength is within an absorption band");
/*     */     }
/* 306 */     double ri = 1.0D + a1 * (1.0D + b1 / Math.pow(wavelength, 2.0D));
/* 307 */     return ri;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double water(double wavelength, double temperature)
/*     */   {
/* 315 */     double[] wavl = { 4.046E-7D, 5.8932E-7D, 7.0652E-7D };
/*     */     
/* 317 */     double[] temp = { 0.0D, 10.0D, 20.0D, 30.0D, 40.0D, 50.0D, 60.0D, 70.0D, 80.0D, 90.0D, 100.0D };
/*     */     
/* 319 */     double[] rfInRe1 = { 1.34359D, 1.34351D, 1.34287D, 1.3418D, 1.34039D, 1.33867D, 1.33669D, 1.33447D, 1.33204D, 1.32942D, 1.32663D };
/* 320 */     double[] rfInRe2 = { 1.33346D, 1.33341D, 1.33283D, 1.33184D, 1.33052D, 1.32892D, 1.32707D, 1.325D, 1.32274D, 1.32029D, 1.31766D };
/* 321 */     double[] rfInRe3 = { 1.33086D, 1.33073D, 1.33007D, 1.32903D, 1.32766D, 1.32603D, 1.32417D, 1.32209D, 1.31983D, 1.31739D, 1.31481D };
/*     */     
/* 323 */     double[] derivRe1 = { 0.0D, -7.46454E-6D, -3.74183E-6D, -3.36815E-6D, -3.18559E-6D, -2.4895E-6D, -2.4564E-6D, -2.08489E-6D, -1.80403E-6D, -2.09899E-6D, 0.0D };
/* 324 */     double[] derivRe2 = { 0.0D, -7.06563E-6D, -3.53749E-6D, -3.3844E-6D, -2.72489E-6D, -2.51602E-6D, -2.21102E-6D, -1.83991E-6D, -1.82936E-6D, -2.24266E-6D, 0.0D };
/* 325 */     double[] derivRe3 = { 0.0D, -7.19933E-6D, -3.00268E-6D, -3.58995E-6D, -2.43753E-6D, -2.25994E-6D, -2.32269E-6D, -1.64928E-6D, -1.88019E-6D, -1.62995E-6D, 0.0D };
/*     */     
/* 327 */     int n = wavl.length;
/* 328 */     int m = temp.length;
/* 329 */     double[] yt = new double[n];
/*     */     
/*     */ 
/* 332 */     if ((wavelength < wavl[0]) || (wavelength > wavl[(n - 1)])) {
/* 333 */       throw new IllegalArgumentException("Wavelength " + wavelength + " is out of experimental data bounds: " + wavl[0] + " to " + wavl[(n - 1)]);
/*     */     }
/* 335 */     if ((temperature < temp[0]) || (temperature > temp[(m - 1)])) {
/* 336 */       throw new IllegalArgumentException("Temperature " + temperature + " is out of experimental data bounds; " + temp[0] + " to " + temp[(m - 1)]);
/*     */     }
/*     */     
/*     */ 
/* 340 */     yt[0] = CubicSpline.interpolate(temperature, temp, rfInRe1, derivRe1);
/* 341 */     yt[1] = CubicSpline.interpolate(temperature, temp, rfInRe2, derivRe2);
/* 342 */     yt[2] = CubicSpline.interpolate(temperature, temp, rfInRe3, derivRe3);
/*     */     
/*     */ 
/* 345 */     CubicSpline cs = new CubicSpline(wavl, yt);
/* 346 */     cs.calcDeriv();
/* 347 */     double yRe = cs.interpolate(wavelength);
/*     */     
/* 349 */     return yRe;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double pva(double concn, double wavl, double temp)
/*     */   {
/* 361 */     double a1 = -0.998419D;double b1 = -1.87778E-17D;
/*     */     
/* 363 */     double rfwater = water(wavl, temp);
/* 364 */     double rfincr = 1.0D + a1 * (1.0D + b1 / Math.pow(wavl, 2.0D));
/* 365 */     double refind = rfwater + rfincr * concn / 10.0D;
/*     */     
/* 367 */     return refind;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static double saline(double concentration, double wavelength, double temperature)
/*     */   {
/* 374 */     double[] naclConcRi = { 0.0D, 1.0D, 2.0D, 3.0D, 4.0D, 5.0D, 6.0D, 7.0D, 8.0D, 9.0D, 10.1D, 11.1D, 12.1D, 13.1D, 14.1D, 15.1D, 16.2D, 17.2D, 18.2D, 19.2D, 20.2D, 21.3D, 22.3D, 23.3D, 24.4D, 25.4D, 26.4D, 27.5D, 28.5D, 29.5D, 30.6D, 31.6D, 32.7D, 33.7D, 34.8D, 35.8D, 36.9D, 37.9D, 39.0D, 40.0D, 41.1D, 42.1D, 43.2D, 44.2D, 45.3D, 46.4D, 47.4D, 48.5D, 49.6D, 50.6D, 51.7D, 53.8D, 56.0D, 58.1D, 60.3D, 62.5D, 64.6D, 66.8D, 69.0D, 71.2D, 73.4D, 75.6D, 77.8D, 80.0D, 82.2D, 84.5D, 86.7D, 88.9D, 91.2D, 93.4D, 95.7D, 98.0D, 100.2D, 102.5D, 104.8D, 107.1D, 112.8D, 118.6D, 124.4D, 130.3D, 136.2D, 142.1D, 148.1D, 154.1D, 160.2D, 166.3D, 178.6D, 191.1D, 203.7D, 216.6D, 229.6D, 247.2D, 256.1D, 269.6D, 283.3D, 297.2D, 311.3D };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 381 */     double[] rfIn = { 1.333D, 1.3332D, 1.3333D, 1.3335D, 1.3337D, 1.3339D, 1.334D, 1.3342D, 1.3344D, 1.3346D, 1.3347D, 1.3349D, 1.3351D, 1.3353D, 1.3354D, 1.3356D, 1.3358D, 1.336D, 1.3362D, 1.3363D, 1.3365D, 1.3367D, 1.3369D, 1.337D, 1.3372D, 1.3374D, 1.3376D, 1.3377D, 1.3379D, 1.3381D, 1.3383D, 1.3384D, 1.3386D, 1.3388D, 1.339D, 1.3391D, 1.3393D, 1.3395D, 1.3397D, 1.3398D, 1.34D, 1.3402D, 1.3404D, 1.3405D, 1.3407D, 1.3409D, 1.3411D, 1.3412D, 1.3414D, 1.3416D, 1.3418D, 1.3421D, 1.3425D, 1.3428D, 1.3432D, 1.3435D, 1.3439D, 1.3442D, 1.3446D, 1.3449D, 1.3453D, 1.3456D, 1.346D, 1.3463D, 1.3467D, 1.347D, 1.3474D, 1.3477D, 1.3481D, 1.3484D, 1.3488D, 1.3491D, 1.3495D, 1.3498D, 1.3502D, 1.3505D, 1.3514D, 1.3523D, 1.3532D, 1.3541D, 1.3549D, 1.3558D, 1.3567D, 1.3576D, 1.3585D, 1.3594D, 1.3612D, 1.363D, 1.3648D, 1.3666D, 1.3684D, 1.3702D, 1.3721D, 1.3739D, 1.3757D, 1.3776D, 1.3795D };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 391 */     double[] deriv = { 0.0D, -2.04904E-4D, 2.19616E-4D, -7.35613E-5D, 7.4629E-5D, -2.24955E-4D, 2.2519E-4D, -7.58054E-5D, 7.80315E-5D, -2.36321E-4D, 2.36336E-4D, -7.81129E-5D, 7.61157E-5D, -2.2635E-4D, 2.29284E-4D, -9.07847E-5D, 3.90193E-5D, 4.50731E-5D, -2.19312E-4D, 2.32174E-4D, -1.09384E-4D, 1.07407E-4D, -2.21696E-4D, 1.79377E-4D, -3.70715E-5D, 6.74765E-5D, -2.32834E-4D, 2.32621E-4D, -6.63432E-5D, 3.27522E-5D, -1.63915E-4D, 1.61508E-4D, -2.13737E-5D, 2.12013E-5D, -1.60693E-4D, 1.60681E-4D, -2.11441E-5D, 2.11468E-5D, -1.60694E-4D, 1.60744E-4D, -2.1382E-5D, 2.20772E-5D, -1.6403E-4D, 1.73733E-4D, -6.79449E-5D, 9.80467E-5D, -2.27966E-4D, 1.8624E-4D, -2.11277E-5D, -7.03714E-6D, -5.30975E-5D, 5.41898E-5D, -5.48913E-5D, 5.67056E-5D, -6.30137E-5D, 7.13824E-5D, -7.17063E-5D, 6.45891E-5D, -6.26832E-5D, 6.21769E-5D, -6.20574E-5D, 6.20859E-5D, -6.23194E-5D, 6.32246E-5D, -6.66119E-5D, 6.61361E-5D, -6.07803E-5D, 5.30183E-5D, -5.13701E-5D, 5.23148E-5D, -5.76185E-5D, 6.47375E-5D, -6.44613E-5D, 5.62732E-5D, -4.721E-5D, 1.91454E-5D, -5.78664E-6D, 1.31552E-6D, 5.24579E-7D, -6.04837E-6D, 6.43247E-6D, -2.44509E-6D, 8.31233E-7D, -8.79838E-7D, 2.54193E-7D, -1.36934E-7D, -3.01506E-7D, 2.07215E-7D, -1.07068E-6D, 2.48527E-6D, -9.3358E-6D, 1.82903E-5D, -1.54847E-5D, 3.70622E-6D, -3.10434E-7D, -1.30682E-7D, 0.0D };
/* 392 */     double[] wavlRockSalt = { 1.85E-7D, 5.89E-7D, 8.84E-7D, 1.179E-6D, 2.357E-6D, 3.536E-6D, 5.893E-6D, 8.84E-6D };
/* 393 */     double[] rfInRockSalt = { 1.893D, 1.544D, 1.534D, 1.53D, 1.526D, 1.523D, 1.516D, 1.502D };
/* 394 */     double[] derivRockSalt = { 0.0D, 3.74404E12D, -8.62356E11D, 1.19054E11D, -3.00122E10D, 5.3764E9D, -2.20178E9D, 0.0D };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 400 */     int nRi = 97;int nRockSalt = 8;
/*     */     
/* 402 */     double concen = concentration * 58.45D;
/*     */     
/*     */ 
/* 405 */     double refInWater = Water.refractIndex(wavelength, temperature);
/*     */     double refrInd;
/* 407 */     double refrInd; if (concentration == 0.0D) {
/* 408 */       refrInd = refInWater;
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/*     */ 
/* 414 */       if ((wavelength < 4.046E-7D) || (wavelength > 7.0652E-7D)) {
/* 415 */         throw new IllegalArgumentException("Wavelength outside the experimental data limits (404.6nm - 706.52nm)");
/*     */       }
/*     */       
/*     */ 
/* 419 */       if ((temperature < 0.0D) || (temperature > 100.0D)) {
/* 420 */         throw new IllegalArgumentException("Temperature " + temperature + " is outside the experimental data limits (0 C - 100 C)");
/*     */       }
/*     */       
/*     */ 
/* 424 */       if ((concen < naclConcRi[0]) || (concen > naclConcRi[(nRi - 1)])) {
/* 425 */         throw new IllegalArgumentException("Concentration" + concen + " is outside the experimental data limits");
/*     */       }
/*     */       
/*     */ 
/* 429 */       double refrIndNacl20 = CubicSpline.interpolate(concen, naclConcRi, rfIn, deriv);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 435 */       double densityNacl = Saline.density(concentration);
/*     */       
/*     */ 
/* 438 */       double densityWater20 = Water.density(20.0D);
/*     */       
/*     */ 
/* 441 */       double densityWater = Water.density(temperature);
/*     */       
/*     */ 
/* 444 */       double riWater20_589 = Water.refractIndex(5.893E-7D, 20.0D);
/*     */       
/*     */ 
/* 447 */       double molesWater = (densityNacl * 1000.0D - concen) / 18.02D;
/* 448 */       double molesNacl = concentration;
/* 449 */       double moleFractWater = molesWater / (molesWater + molesNacl);
/* 450 */       double moleFractNacl = molesNacl / (molesWater + molesNacl);
/*     */       
/*     */ 
/* 453 */       double prelor1 = 18.02D * moleFractWater + 58.45D * moleFractNacl;
/* 454 */       double n2 = refrIndNacl20 * refrIndNacl20;
/* 455 */       double refrIncr = (n2 - 1.0D) / (n2 + 2.0D) * prelor1 / densityNacl;
/*     */       
/* 457 */       n2 = riWater20_589 * riWater20_589;
/* 458 */       refrIncr -= (n2 - 1.0D) / (n2 + 2.0D) * (18.02D * moleFractWater) / densityWater20;
/*     */       
/*     */ 
/* 461 */       double reInRockSalt5893 = 1.516D;
/*     */       double reInRockSalt;
/*     */       double reInRockSalt;
/* 464 */       if ((wavelength >= wavlRockSalt[0]) && (wavelength <= wavlRockSalt[(nRockSalt - 1)])) {
/* 465 */         reInRockSalt = CubicSpline.interpolate(wavelength, wavlRockSalt, rfInRockSalt, derivRockSalt);
/*     */       }
/*     */       else {
/* 468 */         double a1 = 0.515533D;
/* 469 */         double b1 = 2.50204E-14D;
/* 470 */         reInRockSalt = 1.0D + a1 * (1.0D + b1 / Math.pow(wavelength, 2.0D));
/*     */       }
/*     */       
/*     */ 
/* 474 */       reInRockSalt *= reInRockSalt;
/* 475 */       reInRockSalt5893 *= reInRockSalt5893;
/* 476 */       refrIncr = refrIncr * ((reInRockSalt - 1.0D) / (reInRockSalt + 2.0D)) / ((reInRockSalt5893 - 1.0D) / (reInRockSalt5893 + 2.0D));
/*     */       
/*     */ 
/* 479 */       n2 = refInWater * refInWater;
/* 480 */       double lor1 = (n2 - 1.0D) / (n2 + 2.0D) * (18.02D * moleFractWater) / densityWater;
/* 481 */       lor1 += refrIncr;
/* 482 */       double lorDens = (18.02D * moleFractWater * densityWater20 / densityWater + 58.45D * moleFractNacl) * densityNacl / prelor1;
/* 483 */       lor1 = lor1 / prelor1 * lorDens;
/* 484 */       lor1 = (2.0D * lor1 + 1.0D) / (1.0D - lor1);
/* 485 */       refrInd = Math.sqrt(lor1);
/*     */     }
/* 487 */     return refrInd;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double sucrose(double concentration, double temperature)
/*     */   {
/* 497 */     double[] concnG = { 0.0D, 5.0D, 10.0D, 15.1D, 20.1D, 25.2D, 30.3D, 35.4D, 40.6D, 45.7D, 50.9D, 56.1D, 61.3D, 66.5D, 71.8D, 77.1D, 82.4D, 87.7D, 93.1D, 98.4D, 103.8D, 114.7D, 125.6D, 136.6D, 147.7D, 158.9D, 170.2D, 181.5D, 193.0D, 204.5D, 216.2D, 239.8D, 263.8D, 288.1D, 312.9D, 338.1D, 363.7D, 389.8D, 416.2D, 443.2D, 470.6D, 498.4D, 526.8D, 555.6D, 584.9D, 614.8D, 645.1D, 676.0D, 707.4D, 739.3D, 771.9D, 804.9D, 838.6D, 872.8D, 907.6D, 943.1D, 979.1D, 1015.7D, 1053.0D, 1090.9D, 1129.4D, 1168.5D, 1208.2D };
/* 498 */     double[] refInd = { 1.333D, 1.3337D, 1.3344D, 1.3351D, 1.3359D, 1.3366D, 1.3373D, 1.3381D, 1.3388D, 1.3395D, 1.3403D, 1.341D, 1.3418D, 1.3425D, 1.3433D, 1.344D, 1.3448D, 1.3455D, 1.3463D, 1.3471D, 1.3478D, 1.3494D, 1.3509D, 1.3525D, 1.3541D, 1.3557D, 1.3573D, 1.3589D, 1.3606D, 1.3622D, 1.3639D, 1.3672D, 1.3706D, 1.3741D, 1.3776D, 1.3812D, 1.3848D, 1.3885D, 1.3922D, 1.396D, 1.3999D, 1.4038D, 1.4078D, 1.4118D, 1.4159D, 1.4201D, 1.4243D, 1.4286D, 1.433D, 1.4374D, 1.4419D, 1.4465D, 1.4511D, 1.4558D, 1.4606D, 1.4654D, 1.4703D, 1.4753D, 1.4803D, 1.4854D, 1.4906D, 1.4958D, 1.501D };
/* 499 */     double[] deriv = { 0.0D, 8.87219E-7D, -3.54887E-6D, 9.95698E-6D, -9.31221E-6D, 3.62993E-7D, 7.86024E-6D, -8.73591E-6D, 1.22853E-6D, 7.05021E-6D, -9.99082E-6D, 1.07237E-5D, -1.07147E-5D, 9.94585E-6D, -1.0411E-5D, 1.03381E-5D, -9.58168E-6D, 6.62868E-6D, 9.93569E-7D, -7.60108E-6D, 5.46568E-6D, -3.1357E-6D, 2.02704E-6D, -6.87809E-7D, 2.17409E-8D, -9.4373E-8D, -3.16995E-7D, 1.36235E-6D, -1.83846E-6D, 1.45463E-6D, -7.98314E-7D, 2.76693E-7D, 1.46498E-7D, -2.71393E-7D, 2.2853E-7D, -2.28325E-7D, 1.58047E-7D, -1.40698E-7D, 3.72197E-8D, 1.21286E-7D, -1.69002E-7D, 1.09589E-7D, -1.50555E-7D, 8.24317E-8D, 3.46254E-8D, -1.10233E-7D, 3.66531E-8D, 6.86736E-8D, -1.23453E-7D, 9.23933E-9D, 1.0371E-7D, -1.74702E-7D, 7.44894E-8D, 3.92428E-8D, -1.41903E-7D, 6.38698E-8D, 3.62013E-8D, -1.24325E-7D, 4.47083E-8D, 2.66887E-8D, -7.19672E-8D, -5.86665E-8D, 0.0D };
/* 500 */     double refractsuc = 0.331335D;
/* 501 */     double[] weight = { 5.0D, 10.0D, 15.0D, 20.0D, 30.0D, 40.0D, 50.0D, 60.0D, 70.0D, 75.0D };
/* 502 */     double[][] cf = { { -0.25D, -0.27D, -0.31D, -0.31D, -0.34D, -0.35D, -0.36D, -0.37D, -0.36D, -0.36D }, { -0.21D, -0.23D, -0.26D, -0.27D, -0.29D, -0.31D, -0.31D, -0.32D, -0.31D, -0.29D }, { -0.16D, -0.18D, -0.2D, -0.2D, -0.22D, -0.23D, -0.23D, -0.23D, -0.2D, -0.17D }, { -0.11D, -0.12D, -0.14D, -0.14D, -0.15D, -0.16D, -0.16D, -0.15D, -0.12D, -0.09D }, { -0.06D, -0.07D, -0.08D, -0.08D, -0.08D, -0.09D, -0.09D, -0.08D, -0.07D, -0.05D }, { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D }, { 0.06D, 0.07D, 0.07D, 0.07D, 0.07D, 0.07D, 0.07D, 0.07D, 0.07D, 0.07D }, { 0.12D, 0.14D, 0.14D, 0.14D, 0.14D, 0.14D, 0.15D, 0.14D, 0.14D, 0.14D }, { 0.18D, 0.2D, 0.2D, 0.21D, 0.21D, 0.21D, 0.23D, 0.21D, 0.22D, 0.22D }, { 0.24D, 0.26D, 0.26D, 0.27D, 0.28D, 0.28D, 0.3D, 0.28D, 0.29D, 0.29D }, { 0.3D, 0.32D, 0.32D, 0.34D, 0.36D, 0.36D, 0.38D, 0.36D, 0.36D, 0.37D }, { 0.36D, 0.39D, 0.39D, 0.41D, 0.43D, 0.43D, 0.46D, 0.44D, 0.43D, 0.44D }, { 0.43D, 0.46D, 0.46D, 0.48D, 0.5D, 0.51D, 0.55D, 0.52D, 0.5D, 0.51D }, { 0.5D, 0.53D, 0.53D, 0.55D, 0.58D, 0.59D, 0.63D, 0.6D, 0.57D, 0.59D }, { 0.57D, 0.6D, 0.61D, 0.62D, 0.66D, 0.67D, 0.71D, 0.68D, 0.65D, 0.67D }, { 0.64D, 0.67D, 0.7D, 0.71D, 0.74D, 0.75D, 0.8D, 0.76D, 0.73D, 0.75D } };
/* 503 */     double[][] corrfac = new double[16][10];
/* 504 */     double[] tempw = { 15.0D, 16.0D, 17.0D, 18.0D, 19.0D, 20.0D, 21.0D, 22.0D, 23.0D, 24.0D, 25.0D, 26.0D, 27.0D, 28.0D, 29.0D, 30.0D };
/* 505 */     double[] corrfac5 = { -0.25D, -0.21D, -0.16D, -0.11D, -0.06D, 0.0D, 0.06D, 0.12D, 0.18D, 0.24D, 0.3D, 0.36D, 0.43D, 0.5D, 0.57D, 0.64D };
/* 506 */     double[] derivcor = { 0.0D, 0.0157677D, -0.00307078D, -0.00348457D, 0.017009D, -0.00455161D, 0.00119739D, -2.3797E-4D, -2.45514E-4D, 0.00122003D, -0.0046346D, 0.0173184D, -0.00463885D, 0.00123703D, -3.09256E-4D, 0.0D };
/*     */     
/* 508 */     double wavelength = 5.893E-7D;
/* 509 */     int n = 63;
/*     */     
/* 511 */     for (int i = 0; i < 16; i++)
/* 512 */       for (int j = 0; j < 10; j++) corrfac[i][j] = cf[i][j];
/*     */     double refind;
/*     */     double refind;
/* 515 */     if ((concentration >= concnG[0]) && (concentration <= concnG[(n - 1)])) {
/* 516 */       refind = CubicSpline.interpolate(concentration, concnG, refInd, deriv);
/*     */     }
/*     */     else {
/* 519 */       double refractwat = (refInd[0] * refInd[0] - 1.0D) / (refInd[0] * refInd[0] + 2.0D);
/* 520 */       double sucvol = concentration * Sucrose.specificVolume(concentration) * 1000.0D;
/* 521 */       double refracttot = (refractsuc * sucvol + refractwat * (1000.0D - sucvol)) / 1000.0D;
/* 522 */       refind = (1.0D + 2.0D * refracttot) / (1.0D - refracttot);
/*     */     }
/*     */     
/* 525 */     if (temperature != 20.0D) {
/* 526 */       double concw = Sucrose.gperlToWeightpercent(concentration, temperature);
/* 527 */       if (concw < 5.0D) {
/* 528 */         double refind2 = Water.refractIndex(wavelength, temperature);
/* 529 */         if (concentration == 0.0D) {
/* 530 */           refind = refind2;
/*     */         }
/*     */         else {
/* 533 */           double corrfactor = CubicSpline.interpolate(temperature, tempw, corrfac5, derivcor);
/* 534 */           concw += corrfactor;
/* 535 */           double concg = Sucrose.weightpercentToGperl(concw, temperature);
/* 536 */           double refind3 = CubicSpline.interpolate(concg, concnG, refInd, deriv);
/* 537 */           refind = refind2 + (refind3 - refind2) * concw / 5.0D;
/*     */         }
/*     */       }
/*     */       else {
/* 541 */         BiCubicSpline bcs = new BiCubicSpline(tempw, weight, corrfac);
/* 542 */         double corrfactor = bcs.interpolate(temperature, concw);
/* 543 */         concw += corrfactor;
/* 544 */         double concg = Sucrose.weightpercentToGperl(concw, temperature);
/* 545 */         refind = CubicSpline.interpolate(concg, concnG, refInd, deriv);
/*     */       }
/*     */     }
/* 548 */     return refind;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double lorenzLorentz(double na, double nb, double molwta, double molwtb, double molfracta, double densa, double densb, double densab)
/*     */   {
/* 558 */     double molmassa = molfracta * molwta;
/* 559 */     double molmassb = (1.0D - molfracta) * molwtb;
/* 560 */     double lla = na * na;
/* 561 */     lla = (lla - 1.0D) / (lla + 2.0D) * molmassa / densa;
/*     */     
/* 563 */     double llb = nb * nb;
/* 564 */     llb = (llb - 1.0D) / (llb + 2.0D) * molmassb / densb;
/*     */     
/* 566 */     double llab = lla + llb;
/* 567 */     double nab = llab * densab / (molmassa + molmassb);
/* 568 */     nab = (2.0D * nab + 1.0D) / (1.0D - nab);
/*     */     
/* 570 */     return Math.sqrt(nab);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double lorenzLorentz(double[] ni, double[] molwt, double[] molfract, double[] dens, double densmix)
/*     */   {
/* 579 */     double sum0 = 0.0D;double sum1 = 0.0D;
/* 580 */     int n = ni.length;
/*     */     
/* 582 */     if ((n != molwt.length) || (n != molfract.length) || (n != dens.length)) {
/* 583 */       throw new IllegalArgumentException("Array lengths differ");
/*     */     }
/* 585 */     for (int i = 0; i < n; i++) sum0 += molfract[i];
/* 586 */     if (Math.abs(1.0D - sum0) > 1.0E-5D) {
/* 587 */       throw new IllegalArgumentException("Mole fractions do not sum to unity");
/*     */     }
/*     */     
/* 590 */     sum0 = 0.0D;
/* 591 */     for (i = 0; i < n; i++) {
/* 592 */       double molmass = molfract[i] * molwt[i];
/* 593 */       double ll = ni[i] * ni[i];
/* 594 */       ll = (ll - 1.0D) / (ll + 2.0D) * molmass / dens[i];
/* 595 */       sum0 += ll;
/* 596 */       sum1 += molmass;
/*     */     }
/* 598 */     double nimix = sum0 * densmix / sum1;
/* 599 */     nimix = (2.0D * nimix + 1.0D) / (1.0D - nimix);
/*     */     
/* 601 */     return Math.sqrt(nimix);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/optics/RefractiveIndex.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */