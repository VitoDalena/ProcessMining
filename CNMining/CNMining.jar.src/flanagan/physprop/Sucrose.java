/*     */ package flanagan.physprop;
/*     */ 
/*     */ import flanagan.interpolation.BiCubicSpline;
/*     */ import flanagan.interpolation.CubicSpline;
/*     */ import flanagan.math.ArrayMaths;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Sucrose
/*     */ {
/*     */   public static final double MOLWEIGHT = 342.3D;
/*     */   
/*     */   public static double viscosity(double gPerL, double temperature)
/*     */   {
/*  54 */     if (temperature < 0.0D) throw new IllegalArgumentException("Temperature, " + temperature + ", out of range");
/*  55 */     if (gPerL < 0.0D) { throw new IllegalArgumentException("Concentration, " + gPerL + " g/l, out of range");
/*     */     }
/*  57 */     double[] visc20 = { 1.0D, 1.013D, 1.026D, 1.039D, 1.053D, 1.067D, 1.082D, 1.097D, 1.112D, 1.128D, 1.144D, 1.16D, 1.177D, 1.195D, 1.213D, 1.232D, 1.251D, 1.271D, 1.291D, 1.312D, 1.333D, 1.378D, 1.426D, 1.477D, 1.531D, 1.589D, 1.65D, 1.716D, 1.786D, 1.861D, 1.941D, 2.12D, 2.326D, 2.568D, 2.849D, 3.181D, 3.754D, 4.044D, 4.612D, 5.304D, 6.15D, 7.22D, 8.579D, 10.28D, 12.49D, 15.4D, 19.3D, 24.63D, 32.06D, 42.69D, 58.37D, 82.26D, 119.9D, 181.7D, 287.9D, 480.6D, 853.2D, 1628.0D };
/*  58 */     int viscLength = visc20.length;
/*  59 */     for (int i = 0; i < viscLength; i++) visc20[i] *= 1.005D;
/*  60 */     double[] concnWA = { 0.0D, 0.5D, 1.0D, 1.5D, 2.0D, 2.5D, 3.0D, 3.5D, 4.0D, 4.5D, 5.0D, 5.5D, 6.0D, 6.5D, 7.0D, 7.5D, 8.0D, 8.5D, 9.0D, 9.5D, 10.0D, 11.0D, 12.0D, 13.0D, 14.0D, 15.0D, 16.0D, 17.0D, 18.0D, 19.0D, 20.0D, 22.0D, 24.0D, 26.0D, 28.0D, 30.0D, 32.0D, 34.0D, 36.0D, 38.0D, 40.0D, 42.0D, 44.0D, 46.0D, 48.0D, 50.0D, 52.0D, 54.0D, 56.0D, 58.0D, 60.0D, 62.0D, 64.0D, 66.0D, 68.0D, 70.0D, 72.0D, 74.0D, 76.0D, 78.0D, 80.0D, 82.0D, 84.0D };
/*  61 */     double[] concnGA = { 0.0D, 5.0D, 10.0D, 15.1D, 20.1D, 25.2D, 30.3D, 35.4D, 40.6D, 45.7D, 50.9D, 56.1D, 61.3D, 66.5D, 71.8D, 77.1D, 82.4D, 87.7D, 93.1D, 98.4D, 103.8D, 114.7D, 125.6D, 136.6D, 147.7D, 158.9D, 170.2D, 181.5D, 193.0D, 204.5D, 216.2D, 239.8D, 263.8D, 288.1D, 312.9D, 338.1D, 363.7D, 389.8D, 416.2D, 443.2D, 470.6D, 498.4D, 526.8D, 555.6D, 584.9D, 614.8D, 645.1D, 676.0D, 707.4D, 739.3D, 771.9D, 804.9D, 838.6D, 872.8D, 907.6D, 943.1D, 979.1D, 1015.7D, 1053.0D, 1090.9D, 1129.4D, 1168.5D, 1208.2D };
/*  62 */     ArrayMaths amC = new ArrayMaths(concnGA);
/*  63 */     double[] zero = { 1.7921D, 1.5188D, 1.3077D, 1.1404D, 1.005D, 0.8937D, 0.8007D, 0.7225D, 0.656D, 0.5988D, 0.5454D, 0.5064D, 0.4688D, 0.4355D, 0.4061D, 0.3799D, 0.3565D, 0.3355D, 0.3165D, 0.2994D, 0.2838D };
/*  64 */     int length0 = zero.length;
/*     */     
/*  66 */     double[] twenty = { 3.804D, 3.154D, 2.652D, 2.267D, 1.96D, 1.704D, 1.504D, 1.331D, 1.193D, 1.07D, 0.97D, 0.884D, 0.808D, 0.742D, 0.685D, 0.635D, 0.59D, 0.55D };
/*  67 */     int length20 = twenty.length;
/*  68 */     double[] forty = { 14.77D, 11.56D, 9.794D, 7.468D, 6.2D, 5.187D, 4.382D, 3.762D, 3.249D, 2.847D, 2.497D, 2.219D, 1.982D, 1.778D, 1.608D, 1.462D, 1.334D, 1.221D, 1.123D, 1.037D, 0.96D };
/*  69 */     int length40 = forty.length;
/*  70 */     double[] sixty = { 238.0D, 156.0D, 109.8D, 74.6D, 56.5D, 43.86D, 33.78D, 26.52D, 21.28D, 17.18D, 14.01D, 11.67D, 9.83D, 8.34D, 7.15D, 6.2D, 5.4D, 4.73D, 4.15D, 3.72D, 3.34D };
/*  71 */     int length60 = sixty.length;
/*  72 */     double[] temp = new double[length0];
/*  73 */     temp[0] = 0.0D;
/*  74 */     for (int i = 1; i < length60; i++) temp[i] = (temp[(i - 1)] + 5.0D);
/*  75 */     ArrayMaths am0 = new ArrayMaths(zero);
/*  76 */     ArrayMaths am20 = new ArrayMaths(twenty);
/*  77 */     ArrayMaths am40 = new ArrayMaths(forty);
/*  78 */     ArrayMaths am60 = new ArrayMaths(sixty);
/*  79 */     ArrayMaths amT = new ArrayMaths(temp);
/*     */     
/*  81 */     double[] concn85 = { 0.0D, 216.2D, 470.6D, 771.9D };
/*  82 */     double[] concn100 = { 0.0D, 470.6D, 771.9D };
/*     */     
/*  84 */     double ret = NaN.0D;
/*     */     
/*  86 */     if (gPerL <= 771.9D) {
/*  87 */       if (temperature <= 85.0D)
/*     */       {
/*  89 */         int lengthT85 = length20;
/*  90 */         int lengthC85 = amC.indexOf(771.9D);
/*  91 */         double[] temp85 = amT.subarray_as_double(0, length20 - 1);
/*  92 */         double[] conc85 = amC.subarray_as_double(0, lengthC85);
/*     */         
/*  94 */         lengthC85++;
/*  95 */         double[][] array = new double[lengthT85][lengthC85];
/*  96 */         for (int i = 0; i < lengthT85; i++) {
/*  97 */           double[] lvisc = { Math.log(zero[i]), Math.log(twenty[i]), Math.log(forty[i]), Math.log(sixty[i]) };
/*  98 */           CubicSpline cs = new CubicSpline(concn85, lvisc);
/*  99 */           for (int j = 0; j < lengthC85; j++) {
/* 100 */             array[i][j] = cs.interpolate(conc85[j]);
/*     */           }
/*     */         }
/* 103 */         BiCubicSpline bcs = new BiCubicSpline(temp85, conc85, array);
/* 104 */         ret = Math.exp(bcs.interpolate(temperature, gPerL));
/*     */       }
/*     */       else {
/* 107 */         if (temperature > 100.0D) {
/* 108 */           throw new IllegalArgumentException("Temperature, " + temperature + ", out of range");
/*     */         }
/*     */         
/* 111 */         int lengthT100 = length60;
/* 112 */         int lengthC100 = amC.indexOf(771.9D);
/* 113 */         double[] temp100 = amT.subarray_as_double(0, lengthT100 - 1);
/* 114 */         double[] conc100 = amC.subarray_as_double(0, lengthC100);
/* 115 */         lengthC100++;
/*     */         
/* 117 */         double[][] array = new double[lengthT100][lengthC100];
/* 118 */         for (int i = 0; i < lengthT100; i++) {
/* 119 */           double[] lvisc = { Math.log(zero[i]), Math.log(forty[i]), Math.log(sixty[i]) };
/* 120 */           CubicSpline cs = new CubicSpline(concn100, lvisc);
/* 121 */           for (int j = 0; j < lengthC100; j++) {
/* 122 */             array[i][j] = cs.interpolate(conc100[j]);
/*     */           }
/*     */         }
/*     */         
/* 126 */         BiCubicSpline bcs = new BiCubicSpline(temp100, conc100, array);
/* 127 */         ret = Math.exp(bcs.interpolate(temperature, gPerL));
/*     */       }
/*     */       
/*     */ 
/*     */     }
/* 132 */     else if ((temperature == 20.0D) && (gPerL <= 1208.2D)) {
/* 133 */       double[] concM = amC.subarray_as_double(0, viscLength - 1);
/* 134 */       CubicSpline cs = new CubicSpline(concM, visc20);
/* 135 */       ret = cs.interpolate(gPerL);
/*     */     }
/*     */     else {
/* 138 */       throw new IllegalArgumentException("Concentration, " + gPerL + " g/l,  for this temperature, " + temperature + " C,is out of range");
/*     */     }
/*     */     
/*     */ 
/* 142 */     return ret;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double refractIndex(double concentration, double temperature)
/*     */   {
/* 154 */     return RefrIndex.sucrose(concentration, temperature);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double solubility(double temperature)
/*     */   {
/* 162 */     double[] tempc = { 0.0D, 5.0D, 10.0D, 15.0D, 20.0D, 25.0D, 30.0D, 35.0D, 40.0D, 45.0D, 50.0D, 55.0D, 60.0D, 65.0D, 70.0D, 75.0D, 80.0D, 85.0D, 90.0D, 95.0D, 100.0D };
/* 163 */     double[] solub = { 64.18D, 64.87D, 65.58D, 66.53D, 67.09D, 67.89D, 68.8D, 69.55D, 70.42D, 71.32D, 72.25D, 73.2D, 74.18D, 75.88D, 76.22D, 77.27D, 78.36D, 79.46D, 80.61D, 81.77D, 82.97D };
/* 164 */     double[] deriv = { 0.0D, -0.00489357D, 0.0243743D, -0.0350036D, 0.0220399D, 0.00444386D, -0.0134154D, 0.0108176D, -0.00105492D, 6.02123E-4D, 0.00584643D, -0.0191879D, 0.078105D, -0.120432D, 0.0772233D, -0.0180611D, 0.00462116D, 0.00197648D, -5.2706E-4D, 0.00253177D, 0.0D };
/*     */     
/*     */ 
/*     */ 
/* 168 */     int n = tempc.length;
/*     */     double solubility;
/* 170 */     if ((temperature >= tempc[0]) && (temperature <= tempc[(n - 1)])) {
/* 171 */       solubility = CubicSpline.interpolate(temperature, tempc, solub, deriv);
/*     */     }
/*     */     else {
/* 174 */       throw new IllegalArgumentException("The temperatue is outside the experimental data limits");
/*     */     }
/* 176 */     double solubility = weightpercentToGperl(solubility, temperature);
/*     */     
/* 178 */     return solubility;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double density(double concentration, double temperature)
/*     */   {
/* 188 */     double[] concnG = { 0.0D, 5.0D, 10.0D, 15.1D, 20.1D, 25.2D, 30.3D, 35.4D, 40.6D, 45.7D, 50.9D, 56.1D, 61.3D, 66.5D, 71.8D, 77.1D, 82.4D, 87.7D, 93.1D, 98.4D, 103.8D, 114.7D, 125.6D, 136.6D, 147.7D, 158.9D, 170.2D, 181.5D, 193.0D, 204.5D, 216.2D, 239.8D, 263.8D, 288.1D, 312.9D, 338.1D, 363.7D, 389.8D, 416.2D, 443.2D, 470.6D, 498.4D, 526.8D, 555.6D, 584.9D, 614.8D, 645.1D, 676.0D, 707.4D, 739.3D, 771.9D, 804.9D, 838.6D, 872.8D, 907.6D, 943.1D, 979.1D, 1015.7D, 1053.0D, 1090.9D, 1129.4D, 1168.5D, 1208.2D };
/* 189 */     double[] dens = { 998.2D, 1000.2D, 1002.1D, 1004.0D, 1006.0D, 1007.9D, 1009.9D, 1011.9D, 1013.9D, 1015.8D, 1017.8D, 1019.8D, 1021.8D, 1023.8D, 1025.9D, 1027.9D, 1029.9D, 1032.0D, 1034.0D, 1036.1D, 1038.1D, 1042.3D, 1046.5D, 1050.7D, 1054.9D, 1059.2D, 1063.5D, 1067.8D, 1072.2D, 1076.6D, 1081.0D, 1089.9D, 1099.0D, 1108.2D, 1117.5D, 1127.0D, 1136.6D, 1146.4D, 1156.2D, 1166.3D, 1176.5D, 1186.8D, 1197.2D, 1207.9D, 1218.6D, 1229.5D, 1240.6D, 1251.8D, 1263.2D, 1274.7D, 1286.4D, 1298.3D, 1310.3D, 1322.4D, 1334.8D, 1347.2D, 1359.9D, 1372.6D, 1385.5D, 1398.6D, 1411.7D, 1425.0D, 1438.3D };
/* 190 */     double[] deriv = { 0.0D, -0.00495764D, -0.00416945D, 0.0126089D, -0.0137458D, 0.00978735D, -0.00233552D, -4.45268E-4D, -0.00464715D, 0.00502912D, -0.00144255D, 7.41061E-4D, -0.0015217D, 0.00534573D, -0.00654364D, -5.311E-4D, 0.00866803D, -0.0127811D, 0.0134147D, -0.0118716D, 0.00515152D, -0.0012962D, 3.32699E-5D, -7.58744E-4D, 0.00112898D, -7.70477E-4D, 1.45246E-4D, 1.89494E-4D, 1.89925E-4D, -9.49193E-4D, 2.23635E-4D, 6.85836E-5D, 2.005E-5D, -2.87156E-4D, 2.46234E-4D, -2.22108E-4D, 1.74076E-4D, -3.61684E-4D, 2.96689E-4D, -1.83935E-4D, 4.13755E-5D, -3.62702E-4D, 4.85174E-4D, -4.59014E-4D, 4.51875E-5D, 1.42595E-4D, -2.5712E-4D, 1.25835E-4D, -1.32083E-4D, -8.03786E-5D, 1.51752E-4D, -2.12951E-4D, -1.10921E-4D, 2.4994E-4D, -4.47536E-4D, 3.3998E-4D, -3.2882E-4D, 2.20206E-5D, 5.04017E-5D, -2.53039E-4D, 1.15168E-4D, -2.24291E-4D, 0.0D };
/* 191 */     double[] concfit = { 0.0D, 200.0D, 400.0D, 600.0D, 800.0D, 1000.0D, 1200.0D };
/* 192 */     double[] coeff0 = { 9.98363E-4D, 9.26078E-4D, 8.64741E-4D, 8.12717E-4D, 7.66724E-4D, 7.27182E-4D, 6.92587E-4D };
/* 193 */     double[] deriv0 = { 0.0D, 3.53118E-10D, 2.29729E-10D, 1.24915E-10D, 1.75259E-10D, 1.41698E-10D, 0.0D };
/* 194 */     double[] coeff1 = { 2.42825E-7D, 2.60851E-7D, 2.61522E-7D, 1.52504E-7D, 2.35274E-7D, 2.1465E-7D, 1.91079E-7D };
/* 195 */     double[] deriv1 = { 0.0D, 1.11593E-12D, -7.06697E-12D, 1.06986E-11D, -6.95925E-12D, 1.6293E-12D, 0.0D };
/*     */     
/* 197 */     int n = concfit.length;
/* 198 */     double density; if ((concentration >= concfit[0]) && (concentration <= concfit[(n - 1)]) && (temperature >= 0.0D) && (temperature <= 50.0D)) {
/* 199 */       double coefficient0 = CubicSpline.interpolate(concentration, concfit, coeff0, deriv0);
/* 200 */       double coefficient1 = CubicSpline.interpolate(concentration, concfit, coeff1, deriv1);
/*     */       
/* 202 */       density = 1.0D / (coefficient0 + coefficient1 * temperature);
/*     */     }
/*     */     else {
/* 205 */       throw new IllegalArgumentException("Either Temperature or Concentration is outside the experimental data limits"); }
/*     */     double coefficient1;
/* 207 */     double coefficient0; double density; return density;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double specificVolume(double concentration)
/*     */   {
/* 218 */     double[] concnG = { 0.0D, 5.0D, 10.0D, 15.1D, 20.1D, 25.2D, 30.3D, 35.4D, 40.6D, 45.7D, 50.9D, 56.1D, 61.3D, 66.5D, 71.8D, 77.1D, 82.4D, 87.7D, 93.1D, 98.4D, 103.8D, 114.7D, 125.6D, 136.6D, 147.7D, 158.9D, 170.2D, 181.5D, 193.0D, 204.5D, 216.2D, 239.8D, 263.8D, 288.1D, 312.9D, 338.1D, 363.7D, 389.8D, 416.2D, 443.2D, 470.6D, 498.4D, 526.8D, 555.6D, 584.9D, 614.8D, 645.1D, 676.0D, 707.4D, 739.3D, 771.9D, 804.9D, 838.6D, 872.8D, 907.6D, 943.1D, 979.1D, 1015.7D, 1053.0D, 1090.9D, 1129.4D, 1168.5D, 1208.2D };
/* 219 */     double[] specv = { 6.21118E-4D, 6.21118E-4D, 6.21118E-4D, 6.17005E-4D, 6.18028E-4D, 6.16189E-4D, 6.14968E-4D, 6.1693E-4D, 6.14406E-4D, 6.15988E-4D, 6.1604E-4D, 6.16082E-4D, 6.16117E-4D, 6.16147E-4D, 6.16709E-4D, 6.15895E-4D, 6.16401E-4D, 6.16846E-4D, 6.16577E-4D, 6.16964E-4D, 6.16717E-4D, 6.16629E-4D, 6.17353E-4D, 6.1751E-4D, 6.17225E-4D, 6.17222E-4D, 6.17445E-4D, 6.18193E-4D, 6.18211E-4D, 6.18228E-4D, 6.18597E-4D, 6.18712E-4D, 6.19007E-4D, 6.19651E-4D, 6.19844E-4D, 6.20164E-4D, 6.20584E-4D, 6.20923E-4D, 6.21494E-4D, 6.21832E-4D, 6.22455E-4D, 6.22911E-4D, 6.2337E-4D, 6.23873E-4D, 6.24478E-4D, 6.24905E-4D, 6.2537E-4D, 6.25979E-4D, 6.26516E-4D, 6.27126E-4D, 6.27766E-4D, 6.28414E-4D, 6.28964E-4D, 6.29685E-4D, 6.30377E-4D, 6.3108E-4D, 6.31819E-4D, 6.32624E-4D, 6.33334E-4D, 6.34105E-4D, 6.35019E-4D, 6.3589E-4D, 6.36886E-4D };
/* 220 */     double[] deriv = { 0.0D, 9.54239E-8D, -3.81696E-7D, 4.69472E-7D, -2.94053E-7D, 3.9486E-8D, 2.78669E-7D, -4.19907E-7D, 3.86217E-7D, -1.95897E-7D, 5.08841E-8D, -9.858E-9D, -1.30053E-8D, 6.07699E-8D, -1.14515E-7D, 1.03377E-7D, -1.70413E-8D, -4.82412E-8D, 5.92625E-8D, -5.10779E-8D, 1.22998E-8D, 9.25257E-9D, -8.30337E-9D, -4.55102E-9D, 4.7569E-9D, -8.20925E-10D, 9.1751E-9D, -1.12103E-8D, 1.71621E-9D, 4.30015E-9D, -3.32497E-9D, 1.03549E-9D, 1.01682E-9D, -1.55614E-9D, 6.3652E-10D, 1.76082E-10D, -4.56375E-10D, 8.49619E-10D, -9.64276E-10D, 9.59006E-10D, -6.2015E-10D, 1.50421E-10D, -3.91772E-11D, 2.78811E-10D, -4.15371E-10D, 9.3832E-11D, 2.48046E-10D, -2.27533E-10D, 1.60667E-10D, -3.36646E-11D, 6.9795E-11D, -2.4342E-10D, 3.04857E-10D, -1.35319E-10D, 3.06608E-11D, -2.68355E-12D, 1.01252E-10D, -1.58595E-10D, 5.29862E-11D, 1.52913E-10D, -1.29603E-10D, 1.39208E-10D, 0.0D };
/* 221 */     double average = 6.21903E-4D;
/* 222 */     int n = concnG.length;
/*     */     double specvol;
/* 224 */     double specvol; if ((concentration >= concnG[0]) && (concentration <= concnG[(n - 1)])) {
/* 225 */       specvol = CubicSpline.interpolate(concentration, concnG, specv, deriv);
/*     */     }
/*     */     else {
/* 228 */       specvol = average;
/*     */     }
/* 230 */     return specvol;
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
/*     */   public static double diffCoeff(double concentration, double temperature)
/*     */   {
/* 243 */     double tempa = temperature - -273.15D;
/*     */     
/* 245 */     double viscosity = viscosity(concentration, temperature);
/* 246 */     double specvol = specificVolume(concentration);
/* 247 */     double vol = 342.3D * specvol / 6.0221419947E26D;
/* 248 */     double radius = Math.pow(3.0D * vol / 12.566370614359172D, 0.3333333333333333D);
/*     */     
/* 250 */     double f = 18.84955592153876D * viscosity * radius;
/*     */     
/* 252 */     double diffcoef = 1.380650324E-23D * tempa / f;
/*     */     
/* 254 */     return diffcoef;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static double moleFraction(double concentration, double temperature)
/*     */   {
/* 261 */     double weightSucrose = concentration * 1000.0D;
/* 262 */     double molesSucrose = weightSucrose / 342.3D;
/* 263 */     double totalWeight = density(concentration, temperature) * 1000.0D;
/* 264 */     double molesWater = (totalWeight - weightSucrose) / 18.02D;
/* 265 */     return molesSucrose / (molesWater + molesSucrose);
/*     */   }
/*     */   
/*     */   public static double molarToGperl(double molar)
/*     */   {
/* 270 */     return molar * 342.3D;
/*     */   }
/*     */   
/*     */ 
/*     */   public static double molarToWeightpercent(double molar, double temperature)
/*     */   {
/* 276 */     double weight = molarToGperl(molar);
/* 277 */     weight = gperlToWeightpercent(weight, temperature);
/* 278 */     return weight;
/*     */   }
/*     */   
/*     */   public static double gperlToMolar(double gperl)
/*     */   {
/* 283 */     return gperl / 342.3D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double gperlToWeightpercent(double concentration, double temperature)
/*     */   {
/* 292 */     double[] concnG = { 0.0D, 5.0D, 10.0D, 15.1D, 20.1D, 25.2D, 30.3D, 35.4D, 40.6D, 45.7D, 50.9D, 56.1D, 61.3D, 66.5D, 71.8D, 77.1D, 82.4D, 87.7D, 93.1D, 98.4D, 103.8D, 114.7D, 125.6D, 136.6D, 147.7D, 158.9D, 170.2D, 181.5D, 193.0D, 204.5D, 216.2D, 239.8D, 263.8D, 288.1D, 312.9D, 338.1D, 363.7D, 389.8D, 416.2D, 443.2D, 470.6D, 498.4D, 526.8D, 555.6D, 584.9D, 614.8D, 645.1D, 676.0D, 707.4D, 739.3D, 771.9D, 804.9D, 838.6D, 872.8D, 907.6D, 943.1D, 979.1D, 1015.7D, 1053.0D, 1090.9D, 1129.4D, 1168.5D, 1208.2D };
/* 293 */     double[] concnW = { 0.0D, 0.5D, 1.0D, 1.5D, 2.0D, 2.5D, 3.0D, 3.5D, 4.0D, 4.5D, 5.0D, 5.5D, 6.0D, 6.5D, 7.0D, 7.5D, 8.0D, 8.5D, 9.0D, 9.5D, 10.0D, 11.0D, 12.0D, 13.0D, 14.0D, 15.0D, 16.0D, 17.0D, 18.0D, 19.0D, 20.0D, 22.0D, 24.0D, 26.0D, 28.0D, 30.0D, 32.0D, 34.0D, 36.0D, 38.0D, 40.0D, 42.0D, 44.0D, 46.0D, 48.0D, 50.0D, 52.0D, 54.0D, 56.0D, 58.0D, 60.0D, 62.0D, 64.0D, 66.0D, 68.0D, 70.0D, 72.0D, 74.0D, 76.0D, 78.0D, 80.0D, 82.0D, 84.0D };
/* 294 */     double[] deriv = { 0.0D, 2.24325E-4D, -8.973E-4D, 0.00102728D, -8.82025E-4D, 1.7957E-4D, 1.63747E-4D, -8.34558E-4D, 9.70111E-4D, -8.49482E-4D, 2.38375E-4D, -1.04019E-4D, 1.77701E-4D, -6.06786E-4D, 1.76058E-4D, -9.74476E-5D, 2.13732E-4D, -7.5748E-4D, 8.50948E-4D, -6.86359E-4D, -5.6318E-5D, 4.08684E-5D, -1.07156E-4D, -6.87474E-5D, -6.27622E-5D, -1.12854E-4D, 9.20828E-5D, -2.55477E-4D, 1.19556E-4D, -2.22749E-4D, 3.59124E-6D, -8.44627E-5D, -2.16033E-5D, -8.47265E-5D, -4.4802E-5D, -4.36141E-5D, -7.34483E-5D, -1.02994E-5D, -8.43266E-5D, -3.04826E-5D, -3.26613E-5D, -6.69242E-5D, -2.42674E-5D, -4.13785E-5D, -5.47191E-5D, -1.76386E-5D, -5.07722E-5D, -3.04569E-5D, -2.61186E-5D, -5.41411E-5D, -7.97549E-6D, -5.00124E-5D, -1.83509E-5D, -3.00707E-5D, -3.65588E-5D, -1.72617E-5D, -2.57937E-5D, -2.9995E-5D, -2.0797E-5D, -2.23338E-5D, -1.90539E-5D, -2.47041E-5D, 0.0D };
/*     */     
/*     */ 
/* 297 */     double weight = concentration * 0.1D / (density(concentration, temperature) / 1000.0D);
/*     */     
/* 299 */     return weight;
/*     */   }
/*     */   
/*     */ 
/*     */   public static double weightpercentToMolar(double weight, double temperature)
/*     */   {
/* 305 */     double molar = weightpercentToGperl(weight, temperature);
/* 306 */     molar = gperlToMolar(molar);
/* 307 */     return molar;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double weightpercentToGperl(double concentration, double temperature)
/*     */   {
/* 317 */     double[] concnW = { 0.0D, 0.5D, 1.0D, 1.5D, 2.0D, 2.5D, 3.0D, 3.5D, 4.0D, 4.5D, 5.0D, 5.5D, 6.0D, 6.5D, 7.0D, 7.5D, 8.0D, 8.5D, 9.0D, 9.5D, 10.0D, 11.0D, 12.0D, 13.0D, 14.0D, 15.0D, 16.0D, 17.0D, 18.0D, 19.0D, 20.0D, 22.0D, 24.0D, 26.0D, 28.0D, 30.0D, 32.0D, 34.0D, 36.0D, 38.0D, 40.0D, 42.0D, 44.0D, 46.0D, 48.0D, 50.0D, 52.0D, 54.0D, 56.0D, 58.0D, 60.0D, 62.0D, 64.0D, 66.0D, 68.0D, 70.0D, 72.0D, 74.0D, 76.0D, 78.0D, 80.0D, 82.0D, 84.0D };
/* 318 */     double[] concnG = { 0.0D, 5.0D, 10.0D, 15.1D, 20.1D, 25.2D, 30.3D, 35.4D, 40.6D, 45.7D, 50.9D, 56.1D, 61.3D, 66.5D, 71.8D, 77.1D, 82.4D, 87.7D, 93.1D, 98.4D, 103.8D, 114.7D, 125.6D, 136.6D, 147.7D, 158.9D, 170.2D, 181.5D, 193.0D, 204.5D, 216.2D, 239.8D, 263.8D, 288.1D, 312.9D, 338.1D, 363.7D, 389.8D, 416.2D, 443.2D, 470.6D, 498.4D, 526.8D, 555.6D, 584.9D, 614.8D, 645.1D, 676.0D, 707.4D, 739.3D, 771.9D, 804.9D, 838.6D, 872.8D, 907.6D, 943.1D, 979.1D, 1015.7D, 1053.0D, 1090.9D, 1129.4D, 1168.5D, 1208.2D };
/* 319 */     double[] deriv = { 0.0D, -0.230536D, 0.922143D, -1.05804D, 0.909999D, -0.181959D, -0.182163D, 0.910611D, -1.06028D, 0.930517D, -0.261788D, 0.116633D, -0.204746D, 0.702351D, -0.204657D, 0.116276D, -0.260447D, 0.925512D, -1.0416D, 0.840888D, 0.078047D, -0.0545852D, 0.140294D, 0.0934103D, 0.0860649D, 0.16233D, -0.135385D, 0.37921D, -0.181457D, 0.346616D, -0.00500719D, 0.141714D, 0.0381529D, 0.155675D, 0.0891471D, 0.0877366D, 0.159906D, 0.0226375D, 0.199544D, 0.0791879D, 0.0837046D, 0.185994D, 0.0723209D, 0.124723D, 0.178788D, 0.0601258D, 0.180709D, 0.117039D, 0.101137D, 0.228414D, 0.0352078D, 0.230755D, 0.0917725D, 0.152155D, 0.199608D, 0.0994148D, 0.152733D, 0.189652D, 0.138658D, 0.155717D, 0.138476D, 0.190381D, 0.0D };
/* 320 */     double gperl = 0.0D;
/* 321 */     int n = concnW.length;int j = -1;int itermax = 1000;
/* 322 */     boolean test = true;
/*     */     
/* 324 */     if ((concentration < concnW[0]) || (concentration > concnW[(n - 1)])) {
/* 325 */       throw new IllegalArgumentException("concentration is outside the experimental data limits");
/*     */     }
/*     */     
/*     */ 
/* 329 */     double tol = concentration * 1.0E-4D;
/* 330 */     double g1 = 0.0D;
/* 331 */     double g2 = 1200.0D;
/* 332 */     double f1 = g1 - concentration * density(g1, temperature) / 100.0D;
/* 333 */     double f2 = g2 - concentration * density(g2, temperature) / 100.0D;
/* 334 */     if (((f1 > 0.0D) && (f2 > 0.0D)) || ((f1 < 0.0D) && (f2 < 0.0D))) {
/* 335 */       throw new IllegalArgumentException("Root must be bracketed in the bisection");
/*     */     }
/* 337 */     test = true;
/* 338 */     while (test) {
/* 339 */       if (f1 == 0.0D) {
/* 340 */         gperl = g1;
/* 341 */         test = false;
/*     */       }
/* 343 */       if (f2 == 0.0D) {
/* 344 */         gperl = g2;
/* 345 */         test = false;
/*     */       }
/* 347 */       if (test) {
/* 348 */         double g3 = 0.5D * (g1 + g2);
/* 349 */         double f3 = g3 - concentration * density(g3, temperature) / 100.0D;
/* 350 */         if (f3 == 0.0D) {
/* 351 */           gperl = g3;
/* 352 */           test = false;
/*     */         }
/*     */         else {
/* 355 */           if (f3 * f1 > 0.0D) {
/* 356 */             g1 = g3;
/*     */           }
/*     */           else {
/* 359 */             g2 = g3;
/*     */           }
/* 361 */           f1 = g1 - concentration * density(g1, temperature) / 100.0D;
/* 362 */           f2 = g2 - concentration * density(g2, temperature) / 100.0D;
/* 363 */           if (Math.abs(g1 - g2) < tol) {
/* 364 */             gperl = 0.5D * (g1 + g2);
/* 365 */             test = false;
/*     */           }
/*     */         }
/* 368 */         j++;
/* 369 */         if (j > itermax) {
/* 370 */           throw new IllegalArgumentException("number of iteractions in bisection exceeded");
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 375 */     return gperl;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/physprop/Sucrose.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */