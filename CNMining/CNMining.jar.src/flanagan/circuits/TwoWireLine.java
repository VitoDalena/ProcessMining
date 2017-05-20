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
/*     */ 
/*     */ public class TwoWireLine
/*     */   extends TransmissionLine
/*     */ {
/*  38 */   private double wireRadius = -1.0D;
/*  39 */   private double wireSeparation = -1.0D;
/*  40 */   private boolean distancesSet = false;
/*     */   
/*  42 */   private double relativePermittivity = 1.0D;
/*  43 */   private double relativePermeability = 1.0D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setWireRadius(double radius)
/*     */   {
/*  53 */     if (radius <= 0.0D) throw new IllegalArgumentException("The wire radius, " + radius + ", must be greater than zero");
/*  54 */     if ((this.wireSeparation != -1.0D) && (this.wireSeparation <= 2.0D * radius)) throw new IllegalArgumentException("The wire separation distance, " + this.wireSeparation + ", must be greater than the sum of the two wire radii, " + 2.0D * radius);
/*  55 */     this.wireRadius = radius;
/*  56 */     if (this.wireSeparation != -1.0D) this.distancesSet = true;
/*  57 */     if (this.distancesSet) { calculateDistributedCapacitanceAndInductance();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setWireSeparation(double separation)
/*     */   {
/*  63 */     if (separation <= 0.0D) throw new IllegalArgumentException("The wire separation, " + separation + ", must be greater than zero");
/*  64 */     if ((this.wireRadius != -1.0D) && (separation <= 2.0D * this.wireRadius)) throw new IllegalArgumentException("The wire separation distance, " + separation + ", must be greater than the sum of the two wire radii, " + 2.0D * this.wireRadius);
/*  65 */     this.wireSeparation = separation;
/*  66 */     if (this.wireRadius != -1.0D) this.distancesSet = true;
/*  67 */     if (this.distancesSet) { calculateDistributedCapacitanceAndInductance();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setRelativePermittivity(double epsilonR)
/*     */   {
/*  73 */     this.relativePermittivity = epsilonR;
/*  74 */     if (this.distancesSet) { calculateDistributedCapacitanceAndInductance();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setRelativePermeability(double muR)
/*     */   {
/*  80 */     this.relativePermeability = muR;
/*  81 */     if (this.distancesSet) calculateDistributedCapacitanceAndInductance();
/*     */   }
/*     */   
/*     */   private void calculateDistributedCapacitanceAndInductance()
/*     */   {
/*  86 */     this.distributedCapacitance = Impedance.parallelWiresCapacitance(1.0D, this.wireRadius, this.wireSeparation, this.relativePermittivity);
/*  87 */     this.distributedInductance = Impedance.parallelWiresInductance(1.0D, this.wireRadius, this.wireSeparation, this.relativePermeability);
/*     */   }
/*     */   
/*     */ 
/*     */   public TwoWireLine copy()
/*     */   {
/*  93 */     if (this == null) {
/*  94 */       return null;
/*     */     }
/*     */     
/*  97 */     TwoWireLine tl = new TwoWireLine();
/*     */     
/*  99 */     tl.wireRadius = this.wireRadius;
/* 100 */     tl.wireSeparation = this.wireSeparation;
/* 101 */     tl.distancesSet = this.distancesSet;
/* 102 */     tl.relativePermittivity = this.relativePermittivity;
/* 103 */     tl.relativePermeability = this.relativePermeability;
/*     */     
/* 105 */     tl.title = this.title;
/* 106 */     tl.distributedResistance = this.distributedResistance;
/* 107 */     tl.distributedConductance = this.distributedConductance;
/* 108 */     tl.distributedCapacitance = this.distributedCapacitance;
/* 109 */     tl.distributedInductance = this.distributedInductance;
/*     */     
/* 111 */     tl.distributedImpedance = this.distributedImpedance.copy();
/* 112 */     tl.distributedAdmittance = this.distributedAdmittance.copy();
/* 113 */     tl.loadImpedance = this.loadImpedance.copy();
/*     */     
/* 115 */     tl.lineLength = this.lineLength;
/* 116 */     tl.segmentLength = this.segmentLength;
/* 117 */     tl.frequency = this.frequency;
/* 118 */     tl.segmentLength = this.segmentLength;
/* 119 */     tl.omega = this.omega;
/*     */     
/* 121 */     tl.inputVoltage = this.inputVoltage.copy();
/* 122 */     tl.inputCurrent = this.inputCurrent.copy();
/* 123 */     tl.outputVoltage = this.outputVoltage.copy();
/* 124 */     tl.outputCurrent = this.outputCurrent.copy();
/*     */     
/* 126 */     tl.idealWavelength = this.idealWavelength;
/* 127 */     tl.generalWavelength = this.generalWavelength;
/* 128 */     tl.lowLossWavelength = this.lowLossWavelength;
/*     */     
/* 130 */     tl.idealPhaseVelocity = this.idealPhaseVelocity;
/* 131 */     tl.generalPhaseVelocity = this.generalPhaseVelocity;
/* 132 */     tl.lowLossPhaseVelocity = this.lowLossPhaseVelocity;
/*     */     
/* 134 */     tl.idealGroupVelocity = this.idealGroupVelocity;
/* 135 */     tl.generalGroupVelocity = this.generalGroupVelocity;
/* 136 */     tl.lowLossGroupVelocity = this.lowLossGroupVelocity;
/* 137 */     tl.delta = this.delta;
/*     */     
/* 139 */     tl.idealAttenuationConstant = this.idealAttenuationConstant;
/* 140 */     tl.generalAttenuationConstant = this.generalAttenuationConstant;
/* 141 */     tl.lowLossAttenuationConstant = this.lowLossAttenuationConstant;
/*     */     
/* 143 */     tl.idealPhaseConstant = this.idealPhaseConstant;
/* 144 */     tl.generalPhaseConstant = this.generalPhaseConstant;
/* 145 */     tl.lowLossPhaseConstant = this.lowLossPhaseConstant;
/*     */     
/* 147 */     tl.idealPropagationConstant = this.idealPropagationConstant.copy();
/* 148 */     tl.loadImpedance = this.loadImpedance.copy();
/* 149 */     tl.loadImpedance = this.loadImpedance.copy();
/* 150 */     tl.loadImpedance = this.loadImpedance.copy();
/*     */     
/* 152 */     tl.generalPropagationConstant = this.generalPropagationConstant.copy();
/* 153 */     tl.lowLossPropagationConstant = this.lowLossPropagationConstant.copy();
/* 154 */     tl.idealCharacteristicImpedance = this.idealCharacteristicImpedance.copy();
/* 155 */     tl.idealRealCharacteristicImpedance = this.idealRealCharacteristicImpedance;
/*     */     
/* 157 */     tl.generalCharacteristicImpedance = this.generalCharacteristicImpedance.copy();
/* 158 */     tl.lowLossCharacteristicImpedance = this.lowLossCharacteristicImpedance.copy();
/* 159 */     tl.idealInputImpedance = this.idealInputImpedance.copy();
/* 160 */     tl.generalInputImpedance = this.generalInputImpedance.copy();
/* 161 */     tl.lowLossInputImpedance = this.lowLossInputImpedance.copy();
/*     */     
/* 163 */     tl.idealShortedLineImpedance = this.idealShortedLineImpedance.copy();
/* 164 */     tl.generalShortedLineImpedance = this.generalShortedLineImpedance.copy();
/* 165 */     tl.lowLossShortedLineImpedance = this.lowLossShortedLineImpedance.copy();
/*     */     
/* 167 */     tl.idealOpenLineImpedance = this.idealOpenLineImpedance.copy();
/* 168 */     tl.generalOpenLineImpedance = this.generalOpenLineImpedance.copy();
/* 169 */     tl.lowLossOpenLineImpedance = this.lowLossOpenLineImpedance.copy();
/*     */     
/* 171 */     tl.idealQuarterWaveLineImpedance = this.idealQuarterWaveLineImpedance.copy();
/* 172 */     tl.generalQuarterWaveLineImpedance = this.generalQuarterWaveLineImpedance.copy();
/* 173 */     tl.lowLossQuarterWaveLineImpedance = this.lowLossQuarterWaveLineImpedance.copy();
/*     */     
/* 175 */     tl.idealHalfWaveLineImpedance = this.idealHalfWaveLineImpedance.copy();
/* 176 */     tl.generalHalfWaveLineImpedance = this.generalHalfWaveLineImpedance.copy();
/* 177 */     tl.lowLossHalfWaveLineImpedance = this.lowLossHalfWaveLineImpedance.copy();
/*     */     
/* 179 */     tl.idealRefectionCoefficient = this.idealRefectionCoefficient.copy();
/* 180 */     tl.generalRefectionCoefficient = this.generalRefectionCoefficient.copy();
/* 181 */     tl.lowLossRefectionCoefficient = this.lowLossRefectionCoefficient.copy();
/*     */     
/* 183 */     tl.idealStandingWaveRatio = this.idealStandingWaveRatio;
/* 184 */     tl.generalStandingWaveRatio = this.generalStandingWaveRatio;
/* 185 */     tl.lowLossStandingWaveRatio = this.lowLossStandingWaveRatio;
/*     */     
/* 187 */     tl.idealABCDmatrix = this.idealABCDmatrix.copy();
/* 188 */     tl.generalABCDmatrix = this.generalABCDmatrix.copy();
/* 189 */     tl.lowLossABCDmatrix = this.lowLossABCDmatrix.copy();
/*     */     
/* 191 */     tl.numberOfPoints = this.numberOfPoints;
/*     */     
/* 193 */     return tl;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 201 */     Object ret = null;
/*     */     
/* 203 */     if (this != null)
/*     */     {
/* 205 */       TwoWireLine tl = new TwoWireLine();
/*     */       
/* 207 */       tl.wireRadius = this.wireRadius;
/* 208 */       tl.wireSeparation = this.wireSeparation;
/* 209 */       tl.distancesSet = this.distancesSet;
/* 210 */       tl.relativePermittivity = this.relativePermittivity;
/* 211 */       tl.relativePermeability = this.relativePermeability;
/*     */       
/* 213 */       tl.title = this.title;
/* 214 */       tl.distributedResistance = this.distributedResistance;
/* 215 */       tl.distributedConductance = this.distributedConductance;
/* 216 */       tl.distributedCapacitance = this.distributedCapacitance;
/* 217 */       tl.distributedInductance = this.distributedInductance;
/*     */       
/* 219 */       tl.distributedImpedance = this.distributedImpedance.copy();
/* 220 */       tl.distributedAdmittance = this.distributedAdmittance.copy();
/* 221 */       tl.loadImpedance = this.loadImpedance.copy();
/*     */       
/* 223 */       tl.lineLength = this.lineLength;
/* 224 */       tl.segmentLength = this.segmentLength;
/* 225 */       tl.frequency = this.frequency;
/* 226 */       tl.segmentLength = this.segmentLength;
/* 227 */       tl.omega = this.omega;
/*     */       
/* 229 */       tl.inputVoltage = this.inputVoltage.copy();
/* 230 */       tl.inputCurrent = this.inputCurrent.copy();
/* 231 */       tl.outputVoltage = this.outputVoltage.copy();
/* 232 */       tl.outputCurrent = this.outputCurrent.copy();
/*     */       
/* 234 */       tl.idealWavelength = this.idealWavelength;
/* 235 */       tl.generalWavelength = this.generalWavelength;
/* 236 */       tl.lowLossWavelength = this.lowLossWavelength;
/*     */       
/* 238 */       tl.idealPhaseVelocity = this.idealPhaseVelocity;
/* 239 */       tl.generalPhaseVelocity = this.generalPhaseVelocity;
/* 240 */       tl.lowLossPhaseVelocity = this.lowLossPhaseVelocity;
/*     */       
/* 242 */       tl.idealGroupVelocity = this.idealGroupVelocity;
/* 243 */       tl.generalGroupVelocity = this.generalGroupVelocity;
/* 244 */       tl.lowLossGroupVelocity = this.lowLossGroupVelocity;
/* 245 */       tl.delta = this.delta;
/*     */       
/* 247 */       tl.idealAttenuationConstant = this.idealAttenuationConstant;
/* 248 */       tl.generalAttenuationConstant = this.generalAttenuationConstant;
/* 249 */       tl.lowLossAttenuationConstant = this.lowLossAttenuationConstant;
/*     */       
/* 251 */       tl.idealPhaseConstant = this.idealPhaseConstant;
/* 252 */       tl.generalPhaseConstant = this.generalPhaseConstant;
/* 253 */       tl.lowLossPhaseConstant = this.lowLossPhaseConstant;
/*     */       
/* 255 */       tl.idealPropagationConstant = this.idealPropagationConstant.copy();
/* 256 */       tl.loadImpedance = this.loadImpedance.copy();
/* 257 */       tl.loadImpedance = this.loadImpedance.copy();
/* 258 */       tl.loadImpedance = this.loadImpedance.copy();
/*     */       
/* 260 */       tl.generalPropagationConstant = this.generalPropagationConstant.copy();
/* 261 */       tl.lowLossPropagationConstant = this.lowLossPropagationConstant.copy();
/* 262 */       tl.idealCharacteristicImpedance = this.idealCharacteristicImpedance.copy();
/* 263 */       tl.idealRealCharacteristicImpedance = this.idealRealCharacteristicImpedance;
/*     */       
/* 265 */       tl.generalCharacteristicImpedance = this.generalCharacteristicImpedance.copy();
/* 266 */       tl.lowLossCharacteristicImpedance = this.lowLossCharacteristicImpedance.copy();
/* 267 */       tl.idealInputImpedance = this.idealInputImpedance.copy();
/* 268 */       tl.generalInputImpedance = this.generalInputImpedance.copy();
/* 269 */       tl.lowLossInputImpedance = this.lowLossInputImpedance.copy();
/*     */       
/* 271 */       tl.idealShortedLineImpedance = this.idealShortedLineImpedance.copy();
/* 272 */       tl.generalShortedLineImpedance = this.generalShortedLineImpedance.copy();
/* 273 */       tl.lowLossShortedLineImpedance = this.lowLossShortedLineImpedance.copy();
/*     */       
/* 275 */       tl.idealOpenLineImpedance = this.idealOpenLineImpedance.copy();
/* 276 */       tl.generalOpenLineImpedance = this.generalOpenLineImpedance.copy();
/* 277 */       tl.lowLossOpenLineImpedance = this.lowLossOpenLineImpedance.copy();
/*     */       
/* 279 */       tl.idealQuarterWaveLineImpedance = this.idealQuarterWaveLineImpedance.copy();
/* 280 */       tl.generalQuarterWaveLineImpedance = this.generalQuarterWaveLineImpedance.copy();
/* 281 */       tl.lowLossQuarterWaveLineImpedance = this.lowLossQuarterWaveLineImpedance.copy();
/*     */       
/* 283 */       tl.idealHalfWaveLineImpedance = this.idealHalfWaveLineImpedance.copy();
/* 284 */       tl.generalHalfWaveLineImpedance = this.generalHalfWaveLineImpedance.copy();
/* 285 */       tl.lowLossHalfWaveLineImpedance = this.lowLossHalfWaveLineImpedance.copy();
/*     */       
/* 287 */       tl.idealRefectionCoefficient = this.idealRefectionCoefficient.copy();
/* 288 */       tl.generalRefectionCoefficient = this.generalRefectionCoefficient.copy();
/* 289 */       tl.lowLossRefectionCoefficient = this.lowLossRefectionCoefficient.copy();
/*     */       
/* 291 */       tl.idealStandingWaveRatio = this.idealStandingWaveRatio;
/* 292 */       tl.generalStandingWaveRatio = this.generalStandingWaveRatio;
/* 293 */       tl.lowLossStandingWaveRatio = this.lowLossStandingWaveRatio;
/*     */       
/* 295 */       tl.idealABCDmatrix = this.idealABCDmatrix.copy();
/* 296 */       tl.generalABCDmatrix = this.generalABCDmatrix.copy();
/* 297 */       tl.lowLossABCDmatrix = this.lowLossABCDmatrix.copy();
/*     */       
/* 299 */       tl.numberOfPoints = this.numberOfPoints;
/*     */       
/* 301 */       ret = tl;
/*     */     }
/* 303 */     return ret;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/circuits/TwoWireLine.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */