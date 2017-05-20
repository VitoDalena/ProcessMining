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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ZeroOrderHold
/*     */   extends BlackBox
/*     */ {
/*     */   public ZeroOrderHold(double deltaT, int orderPade)
/*     */   {
/*  55 */     super("ZeroOrderHold");
/*  56 */     this.sPoles = Complex.oneDarray(1);
/*  57 */     super.setDeltaT(deltaT);
/*  58 */     super.setPadeOrder(orderPade);
/*  59 */     setNumDen(deltaT);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ZeroOrderHold(double deltaT)
/*     */   {
/*  66 */     super("ZeroOrderHold");
/*  67 */     this.sPoles = Complex.oneDarray(1);
/*  68 */     super.setDeltaT(deltaT);
/*  69 */     setNumDen(deltaT);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setNumDen(double deltaT)
/*     */   {
/*  76 */     this.sDenom = new ComplexPoly(0.0D, 1.0D);
/*  77 */     this.sPoles[0].reset(0.0D, 0.0D);
/*     */     
/*     */ 
/*  80 */     this.sNumer = new ComplexPoly(1.0D);
/*  81 */     this.deadTime = deltaT;
/*  82 */     super.pade();
/*  83 */     this.deadTime = 0.0D;
/*     */     
/*     */ 
/*  86 */     this.sNumerPade = this.sNumerPade.plus(this.sDenomPade);
/*  87 */     this.sZerosPade = this.sNumerPade.roots();
/*     */   }
/*     */   
/*     */   public ZeroOrderHold copy()
/*     */   {
/*  92 */     if (this == null) {
/*  93 */       return null;
/*     */     }
/*     */     
/*  96 */     ZeroOrderHold bb = new ZeroOrderHold(this.deltaT, this.orderPade);
/*     */     
/*  98 */     bb.sampLen = this.sampLen;
/*  99 */     bb.inputT = ((double[])this.inputT.clone());
/* 100 */     bb.outputT = ((double[])this.outputT.clone());
/* 101 */     bb.time = ((double[])this.time.clone());
/* 102 */     bb.forgetFactor = this.forgetFactor;
/* 103 */     bb.deltaT = this.deltaT;
/* 104 */     bb.sampFreq = this.sampFreq;
/* 105 */     bb.inputS = this.inputS.copy();
/* 106 */     bb.outputS = this.outputS.copy();
/* 107 */     bb.sValue = this.sValue.copy();
/* 108 */     bb.zValue = this.zValue.copy();
/* 109 */     bb.sNumer = this.sNumer.copy();
/* 110 */     bb.sDenom = this.sDenom.copy();
/* 111 */     bb.zNumer = this.zNumer.copy();
/* 112 */     bb.zDenom = this.zDenom.copy();
/* 113 */     bb.sPoles = Complex.copy(this.sPoles);
/* 114 */     bb.sZeros = Complex.copy(this.sZeros);
/* 115 */     bb.zPoles = Complex.copy(this.zPoles);
/* 116 */     bb.zZeros = Complex.copy(this.zZeros);
/* 117 */     bb.sNumerDeg = this.sNumerDeg;
/* 118 */     bb.sDenomDeg = this.sDenomDeg;
/* 119 */     bb.zNumerDeg = this.zNumerDeg;
/* 120 */     bb.zDenomDeg = this.zDenomDeg;
/* 121 */     bb.deadTime = this.deadTime;
/* 122 */     bb.orderPade = this.orderPade;
/* 123 */     bb.sNumerPade = this.sNumerPade.copy();
/* 124 */     bb.sDenomPade = this.sDenomPade.copy();
/* 125 */     bb.sPolesPade = Complex.copy(this.sPolesPade);
/* 126 */     bb.sZerosPade = Complex.copy(this.sZerosPade);
/* 127 */     bb.sNumerDegPade = this.sNumerDegPade;
/* 128 */     bb.sDenomDegPade = this.sDenomDegPade;
/* 129 */     bb.maptozero = this.maptozero;
/* 130 */     bb.padeAdded = this.padeAdded;
/* 131 */     bb.integrationSum = this.integrationSum;
/* 132 */     bb.integMethod = this.integMethod;
/* 133 */     bb.ztransMethod = this.ztransMethod;
/* 134 */     bb.name = this.name;
/* 135 */     bb.fixedName = this.fixedName;
/* 136 */     bb.nPlotPoints = this.nPlotPoints;
/*     */     
/* 138 */     return bb;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 145 */     Object ret = null;
/*     */     
/* 147 */     if (this != null) {
/* 148 */       ZeroOrderHold bb = new ZeroOrderHold(this.deltaT, this.orderPade);
/*     */       
/* 150 */       bb.sampLen = this.sampLen;
/* 151 */       bb.inputT = ((double[])this.inputT.clone());
/* 152 */       bb.outputT = ((double[])this.outputT.clone());
/* 153 */       bb.time = ((double[])this.time.clone());
/* 154 */       bb.forgetFactor = this.forgetFactor;
/* 155 */       bb.deltaT = this.deltaT;
/* 156 */       bb.sampFreq = this.sampFreq;
/* 157 */       bb.inputS = this.inputS.copy();
/* 158 */       bb.outputS = this.outputS.copy();
/* 159 */       bb.sValue = this.sValue.copy();
/* 160 */       bb.zValue = this.zValue.copy();
/* 161 */       bb.sNumer = this.sNumer.copy();
/* 162 */       bb.sDenom = this.sDenom.copy();
/* 163 */       bb.zNumer = this.zNumer.copy();
/* 164 */       bb.zDenom = this.zDenom.copy();
/* 165 */       bb.sPoles = Complex.copy(this.sPoles);
/* 166 */       bb.sZeros = Complex.copy(this.sZeros);
/* 167 */       bb.zPoles = Complex.copy(this.zPoles);
/* 168 */       bb.zZeros = Complex.copy(this.zZeros);
/* 169 */       bb.sNumerDeg = this.sNumerDeg;
/* 170 */       bb.sDenomDeg = this.sDenomDeg;
/* 171 */       bb.zNumerDeg = this.zNumerDeg;
/* 172 */       bb.zDenomDeg = this.zDenomDeg;
/* 173 */       bb.deadTime = this.deadTime;
/* 174 */       bb.orderPade = this.orderPade;
/* 175 */       bb.sNumerPade = this.sNumerPade.copy();
/* 176 */       bb.sDenomPade = this.sDenomPade.copy();
/* 177 */       bb.sPolesPade = Complex.copy(this.sPolesPade);
/* 178 */       bb.sZerosPade = Complex.copy(this.sZerosPade);
/* 179 */       bb.sNumerDegPade = this.sNumerDegPade;
/* 180 */       bb.sDenomDegPade = this.sDenomDegPade;
/* 181 */       bb.maptozero = this.maptozero;
/* 182 */       bb.padeAdded = this.padeAdded;
/* 183 */       bb.integrationSum = this.integrationSum;
/* 184 */       bb.integMethod = this.integMethod;
/* 185 */       bb.ztransMethod = this.ztransMethod;
/* 186 */       bb.name = this.name;
/* 187 */       bb.fixedName = this.fixedName;
/* 188 */       bb.nPlotPoints = this.nPlotPoints;
/*     */       
/* 190 */       ret = bb;
/*     */     }
/* 192 */     return ret;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/control/ZeroOrderHold.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */