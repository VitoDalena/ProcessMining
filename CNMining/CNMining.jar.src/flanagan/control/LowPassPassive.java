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
/*     */ public class LowPassPassive
/*     */   extends BlackBox
/*     */ {
/*  49 */   private double resistance = 0.0D;
/*  50 */   private double capacitance = 0.0D;
/*  51 */   private double timeConstant = 0.0D;
/*  52 */   private boolean setR = false;
/*  53 */   private boolean setC = false;
/*     */   
/*     */ 
/*     */   public LowPassPassive()
/*     */   {
/*  58 */     super("Passive Low Pass Filter");
/*  59 */     this.sPoles = Complex.oneDarray(1);
/*  60 */     super.setSnumer(new ComplexPoly(1.0D));
/*  61 */     super.setSdenom(new ComplexPoly(1.0D, 1.0D));
/*  62 */     super.setZtransformMethod(1);
/*  63 */     super.addDeadTimeExtras();
/*  64 */     this.timeConstant = 1.0D;
/*     */   }
/*     */   
/*     */   public void setResistance(double res) {
/*  68 */     this.resistance = res;
/*  69 */     this.timeConstant = (res * this.capacitance);
/*  70 */     calcPolesZerosS();
/*  71 */     this.sDenom = ComplexPoly.rootsToPoly(this.sPoles);
/*  72 */     super.addDeadTimeExtras();
/*  73 */     this.setR = true;
/*     */   }
/*     */   
/*     */   public void setCapacitance(double cap) {
/*  77 */     this.capacitance = cap;
/*  78 */     this.timeConstant = (cap * this.resistance);
/*  79 */     calcPolesZerosS();
/*  80 */     this.sDenom = ComplexPoly.rootsToPoly(this.sPoles);
/*  81 */     super.addDeadTimeExtras();
/*  82 */     this.setC = true;
/*     */   }
/*     */   
/*     */   public void setTimeConstant(double tau) {
/*  86 */     this.timeConstant = tau;
/*  87 */     calcPolesZerosS();
/*  88 */     this.sDenom = ComplexPoly.rootsToPoly(this.sPoles);
/*  89 */     super.addDeadTimeExtras();
/*     */   }
/*     */   
/*     */   public double getResistance() {
/*  93 */     if (this.setR) {
/*  94 */       return this.resistance;
/*     */     }
/*     */     
/*  97 */     System.out.println("Class; LowPassPassive, method: getResistance");
/*  98 */     System.out.println("No resistance has been entered; zero returned");
/*  99 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public double getCapacitance()
/*     */   {
/* 104 */     if (this.setC) {
/* 105 */       return this.capacitance;
/*     */     }
/*     */     
/* 108 */     System.out.println("Class; LowPassPassive, method: getCapacitance");
/* 109 */     System.out.println("No capacitance has been entered; zero returned");
/* 110 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public double getTimeConstant()
/*     */   {
/* 115 */     return this.timeConstant;
/*     */   }
/*     */   
/*     */   protected void calcPolesZerosS()
/*     */   {
/* 120 */     this.sPoles[0].setReal(-this.timeConstant);
/*     */   }
/*     */   
/*     */   public LowPassPassive copy()
/*     */   {
/* 125 */     if (this == null) {
/* 126 */       return null;
/*     */     }
/*     */     
/* 129 */     LowPassPassive bb = new LowPassPassive();
/*     */     
/* 131 */     bb.resistance = this.resistance;
/* 132 */     bb.capacitance = this.capacitance;
/* 133 */     bb.timeConstant = this.timeConstant;
/* 134 */     bb.setR = this.setR;
/* 135 */     bb.setC = this.setC;
/*     */     
/* 137 */     bb.sampLen = this.sampLen;
/* 138 */     bb.inputT = ((double[])this.inputT.clone());
/* 139 */     bb.outputT = ((double[])this.outputT.clone());
/* 140 */     bb.time = ((double[])this.time.clone());
/* 141 */     bb.forgetFactor = this.forgetFactor;
/* 142 */     bb.deltaT = this.deltaT;
/* 143 */     bb.sampFreq = this.sampFreq;
/* 144 */     bb.inputS = this.inputS.copy();
/* 145 */     bb.outputS = this.outputS.copy();
/* 146 */     bb.sValue = this.sValue.copy();
/* 147 */     bb.zValue = this.zValue.copy();
/* 148 */     bb.sNumer = this.sNumer.copy();
/* 149 */     bb.sDenom = this.sDenom.copy();
/* 150 */     bb.zNumer = this.zNumer.copy();
/* 151 */     bb.zDenom = this.zDenom.copy();
/* 152 */     bb.sPoles = Complex.copy(this.sPoles);
/* 153 */     bb.sZeros = Complex.copy(this.sZeros);
/* 154 */     bb.zPoles = Complex.copy(this.zPoles);
/* 155 */     bb.zZeros = Complex.copy(this.zZeros);
/* 156 */     bb.sNumerDeg = this.sNumerDeg;
/* 157 */     bb.sDenomDeg = this.sDenomDeg;
/* 158 */     bb.zNumerDeg = this.zNumerDeg;
/* 159 */     bb.zDenomDeg = this.zDenomDeg;
/* 160 */     bb.deadTime = this.deadTime;
/* 161 */     bb.orderPade = this.orderPade;
/* 162 */     bb.sNumerPade = this.sNumerPade.copy();
/* 163 */     bb.sDenomPade = this.sDenomPade.copy();
/* 164 */     bb.sPolesPade = Complex.copy(this.sPolesPade);
/* 165 */     bb.sZerosPade = Complex.copy(this.sZerosPade);
/* 166 */     bb.sNumerDegPade = this.sNumerDegPade;
/* 167 */     bb.sDenomDegPade = this.sDenomDegPade;
/* 168 */     bb.maptozero = this.maptozero;
/* 169 */     bb.padeAdded = this.padeAdded;
/* 170 */     bb.integrationSum = this.integrationSum;
/* 171 */     bb.integMethod = this.integMethod;
/* 172 */     bb.ztransMethod = this.ztransMethod;
/* 173 */     bb.name = this.name;
/* 174 */     bb.fixedName = this.fixedName;
/* 175 */     bb.nPlotPoints = this.nPlotPoints;
/*     */     
/* 177 */     return bb;
/*     */   }
/*     */   
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 183 */     Object ret = null;
/*     */     
/* 185 */     if (this != null) {
/* 186 */       LowPassPassive bb = new LowPassPassive();
/*     */       
/* 188 */       bb.resistance = this.resistance;
/* 189 */       bb.capacitance = this.capacitance;
/* 190 */       bb.timeConstant = this.timeConstant;
/* 191 */       bb.setR = this.setR;
/* 192 */       bb.setC = this.setC;
/*     */       
/* 194 */       bb.sampLen = this.sampLen;
/* 195 */       bb.inputT = ((double[])this.inputT.clone());
/* 196 */       bb.outputT = ((double[])this.outputT.clone());
/* 197 */       bb.time = ((double[])this.time.clone());
/* 198 */       bb.forgetFactor = this.forgetFactor;
/* 199 */       bb.deltaT = this.deltaT;
/* 200 */       bb.sampFreq = this.sampFreq;
/* 201 */       bb.inputS = this.inputS.copy();
/* 202 */       bb.outputS = this.outputS.copy();
/* 203 */       bb.sValue = this.sValue.copy();
/* 204 */       bb.zValue = this.zValue.copy();
/* 205 */       bb.sNumer = this.sNumer.copy();
/* 206 */       bb.sDenom = this.sDenom.copy();
/* 207 */       bb.zNumer = this.zNumer.copy();
/* 208 */       bb.zDenom = this.zDenom.copy();
/* 209 */       bb.sPoles = Complex.copy(this.sPoles);
/* 210 */       bb.sZeros = Complex.copy(this.sZeros);
/* 211 */       bb.zPoles = Complex.copy(this.zPoles);
/* 212 */       bb.zZeros = Complex.copy(this.zZeros);
/* 213 */       bb.sNumerDeg = this.sNumerDeg;
/* 214 */       bb.sDenomDeg = this.sDenomDeg;
/* 215 */       bb.zNumerDeg = this.zNumerDeg;
/* 216 */       bb.zDenomDeg = this.zDenomDeg;
/* 217 */       bb.deadTime = this.deadTime;
/* 218 */       bb.orderPade = this.orderPade;
/* 219 */       bb.sNumerPade = this.sNumerPade.copy();
/* 220 */       bb.sDenomPade = this.sDenomPade.copy();
/* 221 */       bb.sPolesPade = Complex.copy(this.sPolesPade);
/* 222 */       bb.sZerosPade = Complex.copy(this.sZerosPade);
/* 223 */       bb.sNumerDegPade = this.sNumerDegPade;
/* 224 */       bb.sDenomDegPade = this.sDenomDegPade;
/* 225 */       bb.maptozero = this.maptozero;
/* 226 */       bb.padeAdded = this.padeAdded;
/* 227 */       bb.integrationSum = this.integrationSum;
/* 228 */       bb.integMethod = this.integMethod;
/* 229 */       bb.ztransMethod = this.ztransMethod;
/* 230 */       bb.name = this.name;
/* 231 */       bb.fixedName = this.fixedName;
/* 232 */       bb.nPlotPoints = this.nPlotPoints;
/*     */       
/* 234 */       ret = bb;
/*     */     }
/* 236 */     return ret;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/control/LowPassPassive.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */