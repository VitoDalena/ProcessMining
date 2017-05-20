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
/*     */ public class DelayLine
/*     */   extends BlackBox
/*     */ {
/*     */   public DelayLine(double delayTime, int orderPade)
/*     */   {
/*  51 */     super("DeadTime");
/*  52 */     super.setDeadTime(delayTime, orderPade);
/*     */   }
/*     */   
/*     */ 
/*     */   public DelayLine(double delayTime)
/*     */   {
/*  58 */     super("DeadTime");
/*  59 */     this.fixedName = "DeadTime";
/*     */   }
/*     */   
/*     */   public void setDelayTime(double delayTime)
/*     */   {
/*  64 */     super.setDeadTime(delayTime);
/*     */   }
/*     */   
/*     */   public void setDelayTime(double delayTime, int orderPade)
/*     */   {
/*  69 */     super.setDeadTime(delayTime, orderPade);
/*     */   }
/*     */   
/*     */   public double getDelayTime()
/*     */   {
/*  74 */     return this.deadTime;
/*     */   }
/*     */   
/*     */   public DelayLine copy()
/*     */   {
/*  79 */     if (this == null) {
/*  80 */       return null;
/*     */     }
/*     */     
/*  83 */     DelayLine bb = new DelayLine(this.deadTime);
/*     */     
/*  85 */     bb.sampLen = this.sampLen;
/*  86 */     bb.inputT = ((double[])this.inputT.clone());
/*  87 */     bb.outputT = ((double[])this.outputT.clone());
/*  88 */     bb.time = ((double[])this.time.clone());
/*  89 */     bb.forgetFactor = this.forgetFactor;
/*  90 */     bb.deltaT = this.deltaT;
/*  91 */     bb.sampFreq = this.sampFreq;
/*  92 */     bb.inputS = this.inputS.copy();
/*  93 */     bb.outputS = this.outputS.copy();
/*  94 */     bb.sValue = this.sValue.copy();
/*  95 */     bb.zValue = this.zValue.copy();
/*  96 */     bb.sNumer = this.sNumer.copy();
/*  97 */     bb.sDenom = this.sDenom.copy();
/*  98 */     bb.zNumer = this.zNumer.copy();
/*  99 */     bb.zDenom = this.zDenom.copy();
/* 100 */     bb.sPoles = Complex.copy(this.sPoles);
/* 101 */     bb.sZeros = Complex.copy(this.sZeros);
/* 102 */     bb.zPoles = Complex.copy(this.zPoles);
/* 103 */     bb.zZeros = Complex.copy(this.zZeros);
/* 104 */     bb.sNumerDeg = this.sNumerDeg;
/* 105 */     bb.sDenomDeg = this.sDenomDeg;
/* 106 */     bb.zNumerDeg = this.zNumerDeg;
/* 107 */     bb.zDenomDeg = this.zDenomDeg;
/* 108 */     bb.deadTime = this.deadTime;
/* 109 */     bb.orderPade = this.orderPade;
/* 110 */     bb.sNumerPade = this.sNumerPade.copy();
/* 111 */     bb.sDenomPade = this.sDenomPade.copy();
/* 112 */     bb.sPolesPade = Complex.copy(this.sPolesPade);
/* 113 */     bb.sZerosPade = Complex.copy(this.sZerosPade);
/* 114 */     bb.sNumerDegPade = this.sNumerDegPade;
/* 115 */     bb.sDenomDegPade = this.sDenomDegPade;
/* 116 */     bb.maptozero = this.maptozero;
/* 117 */     bb.padeAdded = this.padeAdded;
/* 118 */     bb.integrationSum = this.integrationSum;
/* 119 */     bb.integMethod = this.integMethod;
/* 120 */     bb.ztransMethod = this.ztransMethod;
/* 121 */     bb.name = this.name;
/* 122 */     bb.fixedName = this.fixedName;
/* 123 */     bb.nPlotPoints = this.nPlotPoints;
/*     */     
/* 125 */     return bb;
/*     */   }
/*     */   
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 131 */     Object ret = null;
/*     */     
/* 133 */     if (this != null) {
/* 134 */       DelayLine bb = new DelayLine(this.deadTime);
/*     */       
/* 136 */       bb.sampLen = this.sampLen;
/* 137 */       bb.inputT = ((double[])this.inputT.clone());
/* 138 */       bb.outputT = ((double[])this.outputT.clone());
/* 139 */       bb.time = ((double[])this.time.clone());
/* 140 */       bb.forgetFactor = this.forgetFactor;
/* 141 */       bb.deltaT = this.deltaT;
/* 142 */       bb.sampFreq = this.sampFreq;
/* 143 */       bb.inputS = this.inputS.copy();
/* 144 */       bb.outputS = this.outputS.copy();
/* 145 */       bb.sValue = this.sValue.copy();
/* 146 */       bb.zValue = this.zValue.copy();
/* 147 */       bb.sNumer = this.sNumer.copy();
/* 148 */       bb.sDenom = this.sDenom.copy();
/* 149 */       bb.zNumer = this.zNumer.copy();
/* 150 */       bb.zDenom = this.zDenom.copy();
/* 151 */       bb.sPoles = Complex.copy(this.sPoles);
/* 152 */       bb.sZeros = Complex.copy(this.sZeros);
/* 153 */       bb.zPoles = Complex.copy(this.zPoles);
/* 154 */       bb.zZeros = Complex.copy(this.zZeros);
/* 155 */       bb.sNumerDeg = this.sNumerDeg;
/* 156 */       bb.sDenomDeg = this.sDenomDeg;
/* 157 */       bb.zNumerDeg = this.zNumerDeg;
/* 158 */       bb.zDenomDeg = this.zDenomDeg;
/* 159 */       bb.deadTime = this.deadTime;
/* 160 */       bb.orderPade = this.orderPade;
/* 161 */       bb.sNumerPade = this.sNumerPade.copy();
/* 162 */       bb.sDenomPade = this.sDenomPade.copy();
/* 163 */       bb.sPolesPade = Complex.copy(this.sPolesPade);
/* 164 */       bb.sZerosPade = Complex.copy(this.sZerosPade);
/* 165 */       bb.sNumerDegPade = this.sNumerDegPade;
/* 166 */       bb.sDenomDegPade = this.sDenomDegPade;
/* 167 */       bb.maptozero = this.maptozero;
/* 168 */       bb.padeAdded = this.padeAdded;
/* 169 */       bb.integrationSum = this.integrationSum;
/* 170 */       bb.integMethod = this.integMethod;
/* 171 */       bb.ztransMethod = this.ztransMethod;
/* 172 */       bb.name = this.name;
/* 173 */       bb.fixedName = this.fixedName;
/* 174 */       bb.nPlotPoints = this.nPlotPoints;
/*     */       
/* 176 */       ret = bb;
/*     */     }
/* 178 */     return ret;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/control/DelayLine.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */