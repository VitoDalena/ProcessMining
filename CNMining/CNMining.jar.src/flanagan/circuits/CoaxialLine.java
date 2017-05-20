/*     */ package flanagan.circuits;
/*     */ 
/*     */ import flanagan.complex.Complex;
/*     */ import flanagan.complex.ComplexMatrix;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CoaxialLine
/*     */   extends TransmissionLine
/*     */ {
/*  37 */   private double innerRadius = -1.0D;
/*  38 */   private double outerRadius = -1.0D;
/*  39 */   private boolean radiiSet = false;
/*     */   
/*  41 */   private double relativePermittivity = 1.0D;
/*  42 */   private double relativePermeability = 1.0D;
/*     */   
/*     */ 
/*     */ 
/*     */   public CoaxialLine()
/*     */   {
/*  48 */     this.title = "Coaxial Line";
/*     */   }
/*     */   
/*     */   public CoaxialLine(String title)
/*     */   {
/*  53 */     this.title = title;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setRadii(double innerRadius, double outerRadius)
/*     */   {
/*  61 */     if (innerRadius <= 0.0D) throw new IllegalArgumentException("The inner radius, " + innerRadius + ", must be greater than zero");
/*  62 */     if (outerRadius <= 0.0D) throw new IllegalArgumentException("The outer radius, " + outerRadius + ", must be greater than zero");
/*  63 */     if (innerRadius >= outerRadius) throw new IllegalArgumentException("The inner radius, " + innerRadius + ", must be less than the outer radius, " + outerRadius);
/*  64 */     this.innerRadius = innerRadius;
/*  65 */     this.outerRadius = outerRadius;
/*  66 */     this.radiiSet = true;
/*  67 */     calculateDistributedCapacitanceAndInductance();
/*     */   }
/*     */   
/*     */   public void setInnerRadius(double radius)
/*     */   {
/*  72 */     if (radius <= 0.0D) throw new IllegalArgumentException("The inner radius, " + radius + ", must be greater than zero");
/*  73 */     if ((this.outerRadius != -1.0D) && (this.outerRadius <= radius)) throw new IllegalArgumentException("The inner radius, " + radius + ", must be less than the outer radius, " + this.outerRadius);
/*  74 */     this.innerRadius = radius;
/*  75 */     if (this.outerRadius != -1.0D) this.radiiSet = true;
/*  76 */     if (this.radiiSet) calculateDistributedCapacitanceAndInductance();
/*     */   }
/*     */   
/*     */   public void setOuterRadius(double radius)
/*     */   {
/*  81 */     if (radius <= 0.0D) throw new IllegalArgumentException("The outer radius, " + radius + ", must be greater than zero");
/*  82 */     if ((this.innerRadius != -1.0D) && (this.innerRadius >= radius)) throw new IllegalArgumentException("The outer radius, " + radius + ", must be greater than the inner radius, " + this.innerRadius);
/*  83 */     this.outerRadius = radius;
/*  84 */     if (this.innerRadius != -1.0D) this.radiiSet = true;
/*  85 */     if (this.radiiSet) { calculateDistributedCapacitanceAndInductance();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setRelativePermittivity(double epsilonR)
/*     */   {
/*  91 */     this.relativePermittivity = epsilonR;
/*  92 */     if (this.radiiSet) { calculateDistributedCapacitanceAndInductance();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setRelativePermeability(double muR)
/*     */   {
/*  98 */     this.relativePermeability = muR;
/*  99 */     if (this.radiiSet) calculateDistributedCapacitanceAndInductance();
/*     */   }
/*     */   
/*     */   private void calculateDistributedCapacitanceAndInductance()
/*     */   {
/* 104 */     this.distributedCapacitance = Impedance.coaxialCapacitance(1.0D, this.innerRadius, this.outerRadius, this.relativePermittivity);
/* 105 */     this.distributedInductance = Impedance.coaxialInductance(1.0D, this.innerRadius, this.outerRadius, this.relativePermeability);
/*     */   }
/*     */   
/*     */ 
/*     */   public CoaxialLine copy()
/*     */   {
/* 111 */     if (this == null) {
/* 112 */       return null;
/*     */     }
/*     */     
/* 115 */     CoaxialLine tl = new CoaxialLine();
/*     */     
/* 117 */     tl.innerRadius = this.innerRadius;
/* 118 */     tl.outerRadius = this.outerRadius;
/* 119 */     tl.radiiSet = this.radiiSet;
/* 120 */     tl.relativePermittivity = this.relativePermittivity;
/* 121 */     tl.relativePermeability = this.relativePermeability;
/*     */     
/* 123 */     tl.title = this.title;
/* 124 */     tl.distributedResistance = this.distributedResistance;
/* 125 */     tl.distributedConductance = this.distributedConductance;
/* 126 */     tl.distributedCapacitance = this.distributedCapacitance;
/* 127 */     tl.distributedInductance = this.distributedInductance;
/*     */     
/* 129 */     tl.distributedImpedance = this.distributedImpedance.copy();
/* 130 */     tl.distributedAdmittance = this.distributedAdmittance.copy();
/* 131 */     tl.loadImpedance = this.loadImpedance.copy();
/*     */     
/* 133 */     tl.lineLength = this.lineLength;
/* 134 */     tl.segmentLength = this.segmentLength;
/* 135 */     tl.frequency = this.frequency;
/* 136 */     tl.segmentLength = this.segmentLength;
/* 137 */     tl.omega = this.omega;
/*     */     
/* 139 */     tl.inputVoltage = this.inputVoltage.copy();
/* 140 */     tl.inputCurrent = this.inputCurrent.copy();
/* 141 */     tl.outputVoltage = this.outputVoltage.copy();
/* 142 */     tl.outputCurrent = this.outputCurrent.copy();
/*     */     
/* 144 */     tl.idealWavelength = this.idealWavelength;
/* 145 */     tl.generalWavelength = this.generalWavelength;
/* 146 */     tl.lowLossWavelength = this.lowLossWavelength;
/*     */     
/* 148 */     tl.idealPhaseVelocity = this.idealPhaseVelocity;
/* 149 */     tl.generalPhaseVelocity = this.generalPhaseVelocity;
/* 150 */     tl.lowLossPhaseVelocity = this.lowLossPhaseVelocity;
/*     */     
/* 152 */     tl.idealGroupVelocity = this.idealGroupVelocity;
/* 153 */     tl.generalGroupVelocity = this.generalGroupVelocity;
/* 154 */     tl.lowLossGroupVelocity = this.lowLossGroupVelocity;
/* 155 */     tl.delta = this.delta;
/*     */     
/* 157 */     tl.idealAttenuationConstant = this.idealAttenuationConstant;
/* 158 */     tl.generalAttenuationConstant = this.generalAttenuationConstant;
/* 159 */     tl.lowLossAttenuationConstant = this.lowLossAttenuationConstant;
/*     */     
/* 161 */     tl.idealPhaseConstant = this.idealPhaseConstant;
/* 162 */     tl.generalPhaseConstant = this.generalPhaseConstant;
/* 163 */     tl.lowLossPhaseConstant = this.lowLossPhaseConstant;
/*     */     
/* 165 */     tl.idealPropagationConstant = this.idealPropagationConstant.copy();
/* 166 */     tl.loadImpedance = this.loadImpedance.copy();
/* 167 */     tl.loadImpedance = this.loadImpedance.copy();
/* 168 */     tl.loadImpedance = this.loadImpedance.copy();
/*     */     
/* 170 */     tl.generalPropagationConstant = this.generalPropagationConstant.copy();
/* 171 */     tl.lowLossPropagationConstant = this.lowLossPropagationConstant.copy();
/* 172 */     tl.idealCharacteristicImpedance = this.idealCharacteristicImpedance.copy();
/* 173 */     tl.idealRealCharacteristicImpedance = this.idealRealCharacteristicImpedance;
/*     */     
/* 175 */     tl.generalCharacteristicImpedance = this.generalCharacteristicImpedance.copy();
/* 176 */     tl.lowLossCharacteristicImpedance = this.lowLossCharacteristicImpedance.copy();
/* 177 */     tl.idealInputImpedance = this.idealInputImpedance.copy();
/* 178 */     tl.generalInputImpedance = this.generalInputImpedance.copy();
/* 179 */     tl.lowLossInputImpedance = this.lowLossInputImpedance.copy();
/*     */     
/* 181 */     tl.idealShortedLineImpedance = this.idealShortedLineImpedance.copy();
/* 182 */     tl.generalShortedLineImpedance = this.generalShortedLineImpedance.copy();
/* 183 */     tl.lowLossShortedLineImpedance = this.lowLossShortedLineImpedance.copy();
/*     */     
/* 185 */     tl.idealOpenLineImpedance = this.idealOpenLineImpedance.copy();
/* 186 */     tl.generalOpenLineImpedance = this.generalOpenLineImpedance.copy();
/* 187 */     tl.lowLossOpenLineImpedance = this.lowLossOpenLineImpedance.copy();
/*     */     
/* 189 */     tl.idealQuarterWaveLineImpedance = this.idealQuarterWaveLineImpedance.copy();
/* 190 */     tl.generalQuarterWaveLineImpedance = this.generalQuarterWaveLineImpedance.copy();
/* 191 */     tl.lowLossQuarterWaveLineImpedance = this.lowLossQuarterWaveLineImpedance.copy();
/*     */     
/* 193 */     tl.idealHalfWaveLineImpedance = this.idealHalfWaveLineImpedance.copy();
/* 194 */     tl.generalHalfWaveLineImpedance = this.generalHalfWaveLineImpedance.copy();
/* 195 */     tl.lowLossHalfWaveLineImpedance = this.lowLossHalfWaveLineImpedance.copy();
/*     */     
/* 197 */     tl.idealRefectionCoefficient = this.idealRefectionCoefficient.copy();
/* 198 */     tl.generalRefectionCoefficient = this.generalRefectionCoefficient.copy();
/* 199 */     tl.lowLossRefectionCoefficient = this.lowLossRefectionCoefficient.copy();
/*     */     
/* 201 */     tl.idealStandingWaveRatio = this.idealStandingWaveRatio;
/* 202 */     tl.generalStandingWaveRatio = this.generalStandingWaveRatio;
/* 203 */     tl.lowLossStandingWaveRatio = this.lowLossStandingWaveRatio;
/*     */     
/* 205 */     tl.idealABCDmatrix = this.idealABCDmatrix.copy();
/* 206 */     tl.generalABCDmatrix = this.generalABCDmatrix.copy();
/* 207 */     tl.lowLossABCDmatrix = this.lowLossABCDmatrix.copy();
/*     */     
/* 209 */     tl.numberOfPoints = this.numberOfPoints;
/*     */     
/* 211 */     return tl;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 219 */     Object ret = null;
/*     */     
/* 221 */     if (this != null)
/*     */     {
/* 223 */       CoaxialLine tl = new CoaxialLine();
/*     */       
/* 225 */       tl.innerRadius = this.innerRadius;
/* 226 */       tl.outerRadius = this.outerRadius;
/* 227 */       tl.radiiSet = this.radiiSet;
/* 228 */       tl.relativePermittivity = this.relativePermittivity;
/* 229 */       tl.relativePermeability = this.relativePermeability;
/*     */       
/* 231 */       tl.title = this.title;
/* 232 */       tl.distributedResistance = this.distributedResistance;
/* 233 */       tl.distributedConductance = this.distributedConductance;
/* 234 */       tl.distributedCapacitance = this.distributedCapacitance;
/* 235 */       tl.distributedInductance = this.distributedInductance;
/*     */       
/* 237 */       tl.distributedImpedance = this.distributedImpedance.copy();
/* 238 */       tl.distributedAdmittance = this.distributedAdmittance.copy();
/* 239 */       tl.loadImpedance = this.loadImpedance.copy();
/*     */       
/* 241 */       tl.lineLength = this.lineLength;
/* 242 */       tl.segmentLength = this.segmentLength;
/* 243 */       tl.frequency = this.frequency;
/* 244 */       tl.segmentLength = this.segmentLength;
/* 245 */       tl.omega = this.omega;
/*     */       
/* 247 */       tl.inputVoltage = this.inputVoltage.copy();
/* 248 */       tl.inputCurrent = this.inputCurrent.copy();
/* 249 */       tl.outputVoltage = this.outputVoltage.copy();
/* 250 */       tl.outputCurrent = this.outputCurrent.copy();
/*     */       
/* 252 */       tl.idealWavelength = this.idealWavelength;
/* 253 */       tl.generalWavelength = this.generalWavelength;
/* 254 */       tl.lowLossWavelength = this.lowLossWavelength;
/*     */       
/* 256 */       tl.idealPhaseVelocity = this.idealPhaseVelocity;
/* 257 */       tl.generalPhaseVelocity = this.generalPhaseVelocity;
/* 258 */       tl.lowLossPhaseVelocity = this.lowLossPhaseVelocity;
/*     */       
/* 260 */       tl.idealGroupVelocity = this.idealGroupVelocity;
/* 261 */       tl.generalGroupVelocity = this.generalGroupVelocity;
/* 262 */       tl.lowLossGroupVelocity = this.lowLossGroupVelocity;
/* 263 */       tl.delta = this.delta;
/*     */       
/* 265 */       tl.idealAttenuationConstant = this.idealAttenuationConstant;
/* 266 */       tl.generalAttenuationConstant = this.generalAttenuationConstant;
/* 267 */       tl.lowLossAttenuationConstant = this.lowLossAttenuationConstant;
/*     */       
/* 269 */       tl.idealPhaseConstant = this.idealPhaseConstant;
/* 270 */       tl.generalPhaseConstant = this.generalPhaseConstant;
/* 271 */       tl.lowLossPhaseConstant = this.lowLossPhaseConstant;
/*     */       
/* 273 */       tl.idealPropagationConstant = this.idealPropagationConstant.copy();
/* 274 */       tl.loadImpedance = this.loadImpedance.copy();
/* 275 */       tl.loadImpedance = this.loadImpedance.copy();
/* 276 */       tl.loadImpedance = this.loadImpedance.copy();
/*     */       
/* 278 */       tl.generalPropagationConstant = this.generalPropagationConstant.copy();
/* 279 */       tl.lowLossPropagationConstant = this.lowLossPropagationConstant.copy();
/* 280 */       tl.idealCharacteristicImpedance = this.idealCharacteristicImpedance.copy();
/* 281 */       tl.idealRealCharacteristicImpedance = this.idealRealCharacteristicImpedance;
/*     */       
/* 283 */       tl.generalCharacteristicImpedance = this.generalCharacteristicImpedance.copy();
/* 284 */       tl.lowLossCharacteristicImpedance = this.lowLossCharacteristicImpedance.copy();
/* 285 */       tl.idealInputImpedance = this.idealInputImpedance.copy();
/* 286 */       tl.generalInputImpedance = this.generalInputImpedance.copy();
/* 287 */       tl.lowLossInputImpedance = this.lowLossInputImpedance.copy();
/*     */       
/* 289 */       tl.idealShortedLineImpedance = this.idealShortedLineImpedance.copy();
/* 290 */       tl.generalShortedLineImpedance = this.generalShortedLineImpedance.copy();
/* 291 */       tl.lowLossShortedLineImpedance = this.lowLossShortedLineImpedance.copy();
/*     */       
/* 293 */       tl.idealOpenLineImpedance = this.idealOpenLineImpedance.copy();
/* 294 */       tl.generalOpenLineImpedance = this.generalOpenLineImpedance.copy();
/* 295 */       tl.lowLossOpenLineImpedance = this.lowLossOpenLineImpedance.copy();
/*     */       
/* 297 */       tl.idealQuarterWaveLineImpedance = this.idealQuarterWaveLineImpedance.copy();
/* 298 */       tl.generalQuarterWaveLineImpedance = this.generalQuarterWaveLineImpedance.copy();
/* 299 */       tl.lowLossQuarterWaveLineImpedance = this.lowLossQuarterWaveLineImpedance.copy();
/*     */       
/* 301 */       tl.idealHalfWaveLineImpedance = this.idealHalfWaveLineImpedance.copy();
/* 302 */       tl.generalHalfWaveLineImpedance = this.generalHalfWaveLineImpedance.copy();
/* 303 */       tl.lowLossHalfWaveLineImpedance = this.lowLossHalfWaveLineImpedance.copy();
/*     */       
/* 305 */       tl.idealRefectionCoefficient = this.idealRefectionCoefficient.copy();
/* 306 */       tl.generalRefectionCoefficient = this.generalRefectionCoefficient.copy();
/* 307 */       tl.lowLossRefectionCoefficient = this.lowLossRefectionCoefficient.copy();
/*     */       
/* 309 */       tl.idealStandingWaveRatio = this.idealStandingWaveRatio;
/* 310 */       tl.generalStandingWaveRatio = this.generalStandingWaveRatio;
/* 311 */       tl.lowLossStandingWaveRatio = this.lowLossStandingWaveRatio;
/*     */       
/* 313 */       tl.idealABCDmatrix = this.idealABCDmatrix.copy();
/* 314 */       tl.generalABCDmatrix = this.generalABCDmatrix.copy();
/* 315 */       tl.lowLossABCDmatrix = this.lowLossABCDmatrix.copy();
/*     */       
/* 317 */       tl.numberOfPoints = this.numberOfPoints;
/*     */       
/* 319 */       ret = tl;
/*     */     }
/* 321 */     return ret;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/circuits/CoaxialLine.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */