/*     */ package flanagan.control;
/*     */ 
/*     */ import flanagan.complex.Complex;
/*     */ import flanagan.complex.ComplexPoly;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Compensator
/*     */   extends BlackBox
/*     */ {
/*  47 */   private double kConst = 1.0D;
/*  48 */   private double aConst = 1.0D;
/*  49 */   private double bConst = 1.0D;
/*     */   
/*     */   public Compensator()
/*     */   {
/*  53 */     super("Compensator");
/*  54 */     this.sZeros = Complex.oneDarray(1);
/*  55 */     this.sPoles = Complex.oneDarray(1);
/*  56 */     super.setSnumer(new ComplexPoly(1.0D, 1.0D));
/*  57 */     super.setSdenom(new ComplexPoly(1.0D, 1.0D));
/*  58 */     super.setZtransformMethod(1);
/*  59 */     super.addDeadTimeExtras();
/*     */   }
/*     */   
/*     */ 
/*     */   public Compensator(double kk, double aa, double bb)
/*     */   {
/*  65 */     super("Compensator");
/*  66 */     this.aConst = aa;
/*  67 */     this.bConst = bb;
/*  68 */     this.kConst = kk;
/*  69 */     this.sZeros = Complex.oneDarray(1);
/*  70 */     this.sPoles = Complex.oneDarray(1);
/*  71 */     super.setSnumer(new ComplexPoly(this.aConst * this.kConst, this.kConst));
/*  72 */     super.setSdenom(new ComplexPoly(this.bConst, 1.0D));
/*  73 */     super.setZtransformMethod(1);
/*  74 */     super.addDeadTimeExtras();
/*     */   }
/*     */   
/*     */   public void setCoeff(double kk, double aa, double bb) {
/*  78 */     this.aConst = aa;
/*  79 */     this.bConst = bb;
/*  80 */     this.kConst = kk;
/*  81 */     Complex[] num = Complex.oneDarray(2);
/*  82 */     num[0].reset(this.aConst * this.kConst, 0.0D);
/*  83 */     num[1].reset(this.kConst, 0.0D);
/*  84 */     this.sNumer.resetPoly(num);
/*  85 */     Complex[] den = Complex.oneDarray(2);
/*  86 */     den[0].reset(this.bConst, 0.0D);
/*  87 */     den[1].reset(1.0D, 0.0D);
/*  88 */     this.sDenom.resetPoly(den);
/*  89 */     calcPolesZerosS();
/*  90 */     super.addDeadTimeExtras();
/*     */   }
/*     */   
/*     */   public void setK(double kk) {
/*  94 */     this.kConst = kk;
/*  95 */     Complex co = new Complex(this.aConst * this.kConst, 0.0D);
/*  96 */     this.sNumer.resetCoeff(0, co);
/*  97 */     co = new Complex(this.kConst, 0.0D);
/*  98 */     this.sNumer.resetCoeff(1, co);
/*  99 */     calcPolesZerosS();
/* 100 */     super.addDeadTimeExtras();
/*     */   }
/*     */   
/*     */   public void setA(double aa) {
/* 104 */     this.aConst = aa;
/* 105 */     Complex co = new Complex(this.aConst * this.kConst, 0.0D);
/* 106 */     this.sNumer.resetCoeff(0, co);
/* 107 */     calcPolesZerosS();
/* 108 */     super.addDeadTimeExtras();
/*     */   }
/*     */   
/*     */   public void setB(double bb) {
/* 112 */     this.bConst = bb;
/* 113 */     Complex co = new Complex(this.bConst, 0.0D);
/* 114 */     this.sDenom.resetCoeff(0, co);
/* 115 */     calcPolesZerosS();
/* 116 */     super.addDeadTimeExtras();
/*     */   }
/*     */   
/*     */   public double getA() {
/* 120 */     return this.aConst;
/*     */   }
/*     */   
/*     */   public double getB() {
/* 124 */     return this.bConst;
/*     */   }
/*     */   
/*     */   public double getK() {
/* 128 */     return this.kConst;
/*     */   }
/*     */   
/*     */   public void calcPolesZerosS()
/*     */   {
/* 133 */     this.sZeros[0].setReal(-this.aConst);
/* 134 */     this.sPoles[0].setReal(-this.bConst);
/*     */   }
/*     */   
/*     */   public Compensator copy()
/*     */   {
/* 139 */     if (this == null) {
/* 140 */       return null;
/*     */     }
/*     */     
/* 143 */     Compensator bb = new Compensator();
/*     */     
/* 145 */     bb.kConst = this.kConst;
/* 146 */     bb.aConst = this.aConst;
/* 147 */     bb.bConst = this.bConst;
/*     */     
/* 149 */     bb.sampLen = this.sampLen;
/* 150 */     bb.inputT = ((double[])this.inputT.clone());
/* 151 */     bb.outputT = ((double[])this.outputT.clone());
/* 152 */     bb.time = ((double[])this.time.clone());
/* 153 */     bb.forgetFactor = this.forgetFactor;
/* 154 */     bb.deltaT = this.deltaT;
/* 155 */     bb.sampFreq = this.sampFreq;
/* 156 */     bb.inputS = this.inputS.copy();
/* 157 */     bb.outputS = this.outputS.copy();
/* 158 */     bb.sValue = this.sValue.copy();
/* 159 */     bb.zValue = this.zValue.copy();
/* 160 */     bb.sNumer = this.sNumer.copy();
/* 161 */     bb.sDenom = this.sDenom.copy();
/* 162 */     bb.zNumer = this.zNumer.copy();
/* 163 */     bb.zDenom = this.zDenom.copy();
/* 164 */     bb.sPoles = Complex.copy(this.sPoles);
/* 165 */     bb.sZeros = Complex.copy(this.sZeros);
/* 166 */     bb.zPoles = Complex.copy(this.zPoles);
/* 167 */     bb.zZeros = Complex.copy(this.zZeros);
/* 168 */     bb.sNumerDeg = this.sNumerDeg;
/* 169 */     bb.sDenomDeg = this.sDenomDeg;
/* 170 */     bb.zNumerDeg = this.zNumerDeg;
/* 171 */     bb.zDenomDeg = this.zDenomDeg;
/* 172 */     bb.deadTime = this.deadTime;
/* 173 */     bb.orderPade = this.orderPade;
/* 174 */     bb.sNumerPade = this.sNumerPade.copy();
/* 175 */     bb.sDenomPade = this.sDenomPade.copy();
/* 176 */     bb.sPolesPade = Complex.copy(this.sPolesPade);
/* 177 */     bb.sZerosPade = Complex.copy(this.sZerosPade);
/* 178 */     bb.sNumerDegPade = this.sNumerDegPade;
/* 179 */     bb.sDenomDegPade = this.sDenomDegPade;
/* 180 */     bb.maptozero = this.maptozero;
/* 181 */     bb.padeAdded = this.padeAdded;
/* 182 */     bb.integrationSum = this.integrationSum;
/* 183 */     bb.integMethod = this.integMethod;
/* 184 */     bb.ztransMethod = this.ztransMethod;
/* 185 */     bb.name = this.name;
/* 186 */     bb.fixedName = this.fixedName;
/* 187 */     bb.nPlotPoints = this.nPlotPoints;
/*     */     
/* 189 */     return bb;
/*     */   }
/*     */   
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 195 */     Object ret = null;
/*     */     
/* 197 */     if (this != null) {
/* 198 */       Compensator bb = new Compensator();
/*     */       
/* 200 */       bb.kConst = this.kConst;
/* 201 */       bb.aConst = this.aConst;
/* 202 */       bb.bConst = this.bConst;
/*     */       
/* 204 */       bb.sampLen = this.sampLen;
/* 205 */       bb.inputT = ((double[])this.inputT.clone());
/* 206 */       bb.outputT = ((double[])this.outputT.clone());
/* 207 */       bb.time = ((double[])this.time.clone());
/* 208 */       bb.forgetFactor = this.forgetFactor;
/* 209 */       bb.deltaT = this.deltaT;
/* 210 */       bb.sampFreq = this.sampFreq;
/* 211 */       bb.inputS = this.inputS.copy();
/* 212 */       bb.outputS = this.outputS.copy();
/* 213 */       bb.sValue = this.sValue.copy();
/* 214 */       bb.zValue = this.zValue.copy();
/* 215 */       bb.sNumer = this.sNumer.copy();
/* 216 */       bb.sDenom = this.sDenom.copy();
/* 217 */       bb.zNumer = this.zNumer.copy();
/* 218 */       bb.zDenom = this.zDenom.copy();
/* 219 */       bb.sPoles = Complex.copy(this.sPoles);
/* 220 */       bb.sZeros = Complex.copy(this.sZeros);
/* 221 */       bb.zPoles = Complex.copy(this.zPoles);
/* 222 */       bb.zZeros = Complex.copy(this.zZeros);
/* 223 */       bb.sNumerDeg = this.sNumerDeg;
/* 224 */       bb.sDenomDeg = this.sDenomDeg;
/* 225 */       bb.zNumerDeg = this.zNumerDeg;
/* 226 */       bb.zDenomDeg = this.zDenomDeg;
/* 227 */       bb.deadTime = this.deadTime;
/* 228 */       bb.orderPade = this.orderPade;
/* 229 */       bb.sNumerPade = this.sNumerPade.copy();
/* 230 */       bb.sDenomPade = this.sDenomPade.copy();
/* 231 */       bb.sPolesPade = Complex.copy(this.sPolesPade);
/* 232 */       bb.sZerosPade = Complex.copy(this.sZerosPade);
/* 233 */       bb.sNumerDegPade = this.sNumerDegPade;
/* 234 */       bb.sDenomDegPade = this.sDenomDegPade;
/* 235 */       bb.maptozero = this.maptozero;
/* 236 */       bb.padeAdded = this.padeAdded;
/* 237 */       bb.integrationSum = this.integrationSum;
/* 238 */       bb.integMethod = this.integMethod;
/* 239 */       bb.ztransMethod = this.ztransMethod;
/* 240 */       bb.name = this.name;
/* 241 */       bb.fixedName = this.fixedName;
/* 242 */       bb.nPlotPoints = this.nPlotPoints;
/*     */       
/* 244 */       ret = bb;
/*     */     }
/* 246 */     return ret;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/control/Compensator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */