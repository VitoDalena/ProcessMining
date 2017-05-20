/*     */ package flanagan.control;
/*     */ 
/*     */ import flanagan.complex.Complex;
/*     */ import flanagan.complex.ComplexPoly;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HighPassPassive
/*     */   extends BlackBox
/*     */ {
/*  49 */   private double resistance = 0.0D;
/*  50 */   private double capacitance = 0.0D;
/*  51 */   private double timeConstant = 0.0D;
/*  52 */   private boolean setR = false;
/*  53 */   private boolean setC = false;
/*     */   
/*     */ 
/*     */   public HighPassPassive()
/*     */   {
/*  58 */     super("Passive High Pass Filter");
/*  59 */     this.sZeros = Complex.oneDarray(1);
/*  60 */     this.sPoles = Complex.oneDarray(1);
/*  61 */     super.setSnumer(new ComplexPoly(0.0D, 1.0D));
/*  62 */     super.setSdenom(new ComplexPoly(1.0D, 1.0D));
/*  63 */     super.setZtransformMethod(1);
/*  64 */     super.addDeadTimeExtras();
/*  65 */     this.timeConstant = 1.0D;
/*     */   }
/*     */   
/*     */   public void setResistance(double res)
/*     */   {
/*  70 */     this.resistance = res;
/*  71 */     this.timeConstant = (res * this.capacitance);
/*  72 */     calcPolesZerosS();
/*  73 */     this.sNumer = ComplexPoly.rootsToPoly(this.sZeros);
/*  74 */     for (int i = 0; i <= this.sNumerDeg; i++) this.sNumer.resetCoeff(i, this.sNumer.coeffCopy(i).times(Math.pow(this.timeConstant, i)));
/*  75 */     this.sDenom = ComplexPoly.rootsToPoly(this.sPoles);
/*  76 */     super.addDeadTimeExtras();
/*  77 */     this.setR = true;
/*     */   }
/*     */   
/*     */   public void setCapacitance(double cap) {
/*  81 */     this.capacitance = cap;
/*  82 */     this.timeConstant = (cap * this.resistance);
/*  83 */     calcPolesZerosS();
/*  84 */     this.sNumer = ComplexPoly.rootsToPoly(this.sZeros);
/*  85 */     for (int i = 0; i <= this.sNumerDeg; i++) this.sNumer.resetCoeff(i, this.sNumer.coeffCopy(i).times(Math.pow(this.timeConstant, i)));
/*  86 */     this.sDenom = ComplexPoly.rootsToPoly(this.sPoles);
/*  87 */     super.addDeadTimeExtras();
/*  88 */     this.setC = true;
/*     */   }
/*     */   
/*     */   public void setTimeConstant(double tau) {
/*  92 */     this.timeConstant = tau;
/*  93 */     calcPolesZerosS();
/*  94 */     this.sNumer = ComplexPoly.rootsToPoly(this.sZeros);
/*  95 */     for (int i = 0; i <= this.sNumerDeg; i++) this.sNumer.resetCoeff(i, this.sNumer.coeffCopy(i).times(Math.pow(this.timeConstant, i)));
/*  96 */     this.sDenom = ComplexPoly.rootsToPoly(this.sPoles);
/*  97 */     super.addDeadTimeExtras();
/*     */   }
/*     */   
/*     */   public double getResistance() {
/* 101 */     if (this.setR) {
/* 102 */       return this.resistance;
/*     */     }
/*     */     
/* 105 */     System.out.println("Class; HighPassPassive, method: getResistance");
/* 106 */     System.out.println("No resistance has been entered; zero returned");
/* 107 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public double getCapacitance()
/*     */   {
/* 112 */     if (this.setC) {
/* 113 */       return this.capacitance;
/*     */     }
/*     */     
/* 116 */     System.out.println("Class; HighPassPassive, method: getCapacitance");
/* 117 */     System.out.println("No capacitance has been entered; zero returned");
/* 118 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public double getTimeConstant()
/*     */   {
/* 123 */     return this.timeConstant;
/*     */   }
/*     */   
/*     */   protected void calcPolesZerosS()
/*     */   {
/* 128 */     this.sZeros[0].setReal(0.0D);
/* 129 */     this.sPoles[0].setReal(-this.timeConstant);
/*     */   }
/*     */   
/*     */   public HighPassPassive copy()
/*     */   {
/* 134 */     if (this == null) {
/* 135 */       return null;
/*     */     }
/*     */     
/* 138 */     HighPassPassive bb = new HighPassPassive();
/*     */     
/* 140 */     bb.resistance = this.resistance;
/* 141 */     bb.capacitance = this.capacitance;
/* 142 */     bb.timeConstant = this.timeConstant;
/* 143 */     bb.setR = this.setR;
/* 144 */     bb.setC = this.setC;
/*     */     
/* 146 */     bb.sampLen = this.sampLen;
/* 147 */     bb.inputT = ((double[])this.inputT.clone());
/* 148 */     bb.outputT = ((double[])this.outputT.clone());
/* 149 */     bb.time = ((double[])this.time.clone());
/* 150 */     bb.forgetFactor = this.forgetFactor;
/* 151 */     bb.deltaT = this.deltaT;
/* 152 */     bb.sampFreq = this.sampFreq;
/* 153 */     bb.inputS = this.inputS.copy();
/* 154 */     bb.outputS = this.outputS.copy();
/* 155 */     bb.sValue = this.sValue.copy();
/* 156 */     bb.zValue = this.zValue.copy();
/* 157 */     bb.sNumer = this.sNumer.copy();
/* 158 */     bb.sDenom = this.sDenom.copy();
/* 159 */     bb.zNumer = this.zNumer.copy();
/* 160 */     bb.zDenom = this.zDenom.copy();
/* 161 */     bb.sPoles = Complex.copy(this.sPoles);
/* 162 */     bb.sZeros = Complex.copy(this.sZeros);
/* 163 */     bb.zPoles = Complex.copy(this.zPoles);
/* 164 */     bb.zZeros = Complex.copy(this.zZeros);
/* 165 */     bb.sNumerDeg = this.sNumerDeg;
/* 166 */     bb.sDenomDeg = this.sDenomDeg;
/* 167 */     bb.zNumerDeg = this.zNumerDeg;
/* 168 */     bb.zDenomDeg = this.zDenomDeg;
/* 169 */     bb.deadTime = this.deadTime;
/* 170 */     bb.orderPade = this.orderPade;
/* 171 */     bb.sNumerPade = this.sNumerPade.copy();
/* 172 */     bb.sDenomPade = this.sDenomPade.copy();
/* 173 */     bb.sPolesPade = Complex.copy(this.sPolesPade);
/* 174 */     bb.sZerosPade = Complex.copy(this.sZerosPade);
/* 175 */     bb.sNumerDegPade = this.sNumerDegPade;
/* 176 */     bb.sDenomDegPade = this.sDenomDegPade;
/* 177 */     bb.maptozero = this.maptozero;
/* 178 */     bb.padeAdded = this.padeAdded;
/* 179 */     bb.integrationSum = this.integrationSum;
/* 180 */     bb.integMethod = this.integMethod;
/* 181 */     bb.ztransMethod = this.ztransMethod;
/* 182 */     bb.name = this.name;
/* 183 */     bb.fixedName = this.fixedName;
/* 184 */     bb.nPlotPoints = this.nPlotPoints;
/*     */     
/* 186 */     return bb;
/*     */   }
/*     */   
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 192 */     Object ret = null;
/*     */     
/* 194 */     if (this != null) {
/* 195 */       HighPassPassive bb = new HighPassPassive();
/*     */       
/* 197 */       bb.resistance = this.resistance;
/* 198 */       bb.capacitance = this.capacitance;
/* 199 */       bb.timeConstant = this.timeConstant;
/* 200 */       bb.setR = this.setR;
/* 201 */       bb.setC = this.setC;
/*     */       
/* 203 */       bb.sampLen = this.sampLen;
/* 204 */       bb.inputT = ((double[])this.inputT.clone());
/* 205 */       bb.outputT = ((double[])this.outputT.clone());
/* 206 */       bb.time = ((double[])this.time.clone());
/* 207 */       bb.forgetFactor = this.forgetFactor;
/* 208 */       bb.deltaT = this.deltaT;
/* 209 */       bb.sampFreq = this.sampFreq;
/* 210 */       bb.inputS = this.inputS.copy();
/* 211 */       bb.outputS = this.outputS.copy();
/* 212 */       bb.sValue = this.sValue.copy();
/* 213 */       bb.zValue = this.zValue.copy();
/* 214 */       bb.sNumer = this.sNumer.copy();
/* 215 */       bb.sDenom = this.sDenom.copy();
/* 216 */       bb.zNumer = this.zNumer.copy();
/* 217 */       bb.zDenom = this.zDenom.copy();
/* 218 */       bb.sPoles = Complex.copy(this.sPoles);
/* 219 */       bb.sZeros = Complex.copy(this.sZeros);
/* 220 */       bb.zPoles = Complex.copy(this.zPoles);
/* 221 */       bb.zZeros = Complex.copy(this.zZeros);
/* 222 */       bb.sNumerDeg = this.sNumerDeg;
/* 223 */       bb.sDenomDeg = this.sDenomDeg;
/* 224 */       bb.zNumerDeg = this.zNumerDeg;
/* 225 */       bb.zDenomDeg = this.zDenomDeg;
/* 226 */       bb.deadTime = this.deadTime;
/* 227 */       bb.orderPade = this.orderPade;
/* 228 */       bb.sNumerPade = this.sNumerPade.copy();
/* 229 */       bb.sDenomPade = this.sDenomPade.copy();
/* 230 */       bb.sPolesPade = Complex.copy(this.sPolesPade);
/* 231 */       bb.sZerosPade = Complex.copy(this.sZerosPade);
/* 232 */       bb.sNumerDegPade = this.sNumerDegPade;
/* 233 */       bb.sDenomDegPade = this.sDenomDegPade;
/* 234 */       bb.maptozero = this.maptozero;
/* 235 */       bb.padeAdded = this.padeAdded;
/* 236 */       bb.integrationSum = this.integrationSum;
/* 237 */       bb.integMethod = this.integMethod;
/* 238 */       bb.ztransMethod = this.ztransMethod;
/* 239 */       bb.name = this.name;
/* 240 */       bb.fixedName = this.fixedName;
/* 241 */       bb.nPlotPoints = this.nPlotPoints;
/*     */       
/* 243 */       ret = bb;
/*     */     }
/* 245 */     return ret;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/control/HighPassPassive.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */