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
/*     */ public class ParallelPlateLine
/*     */   extends TransmissionLine
/*     */ {
/*  37 */   private double plateWidth = -1.0D;
/*  38 */   private double plateSeparation = -1.0D;
/*  39 */   private boolean distancesSet = false;
/*     */   
/*  41 */   private double relativePermittivity = 1.0D;
/*  42 */   private double relativePermeability = 1.0D;
/*     */   
/*     */ 
/*     */ 
/*     */   public ParallelPlateLine()
/*     */   {
/*  48 */     this.title = "Parallel Plate Line";
/*     */   }
/*     */   
/*     */   public ParallelPlateLine(String title)
/*     */   {
/*  53 */     this.title = title;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setPlateWidth(double width)
/*     */   {
/*  59 */     if (width <= 0.0D) throw new IllegalArgumentException("The plate width, " + width + ", must be greater than zero");
/*  60 */     this.plateWidth = width;
/*  61 */     if (this.plateSeparation != -1.0D) this.distancesSet = true;
/*  62 */     if (this.distancesSet) { calculateDistributedCapacitanceAndInductance();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setPlateSeparation(double separation)
/*     */   {
/*  68 */     if (separation <= 0.0D) throw new IllegalArgumentException("The plate separation, " + separation + ", must be greater than zero");
/*  69 */     this.plateSeparation = separation;
/*  70 */     if (this.plateWidth != -1.0D) this.distancesSet = true;
/*  71 */     if (this.distancesSet) { calculateDistributedCapacitanceAndInductance();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setRelativePermittivity(double epsilonR)
/*     */   {
/*  77 */     this.relativePermittivity = epsilonR;
/*  78 */     if (this.distancesSet) { calculateDistributedCapacitanceAndInductance();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setRelativePermeability(double muR)
/*     */   {
/*  84 */     this.relativePermeability = muR;
/*  85 */     if (this.distancesSet) calculateDistributedCapacitanceAndInductance();
/*     */   }
/*     */   
/*     */   private void calculateDistributedCapacitanceAndInductance()
/*     */   {
/*  90 */     this.distributedCapacitance = Impedance.parallelPlateCapacitance(1.0D, this.plateWidth, this.plateSeparation, this.relativePermittivity);
/*  91 */     this.distributedInductance = Impedance.parallelPlateInductance(1.0D, this.plateWidth, this.plateSeparation, this.relativePermeability);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ParallelPlateLine copy()
/*     */   {
/*  98 */     if (this == null) {
/*  99 */       return null;
/*     */     }
/*     */     
/* 102 */     ParallelPlateLine tl = new ParallelPlateLine();
/*     */     
/* 104 */     tl.plateWidth = this.plateWidth;
/* 105 */     tl.plateSeparation = this.plateSeparation;
/* 106 */     tl.distancesSet = this.distancesSet;
/* 107 */     tl.relativePermittivity = this.relativePermittivity;
/* 108 */     tl.relativePermeability = this.relativePermeability;
/*     */     
/* 110 */     tl.title = this.title;
/* 111 */     tl.distributedResistance = this.distributedResistance;
/* 112 */     tl.distributedConductance = this.distributedConductance;
/* 113 */     tl.distributedCapacitance = this.distributedCapacitance;
/* 114 */     tl.distributedInductance = this.distributedInductance;
/*     */     
/* 116 */     tl.distributedImpedance = this.distributedImpedance.copy();
/* 117 */     tl.distributedAdmittance = this.distributedAdmittance.copy();
/* 118 */     tl.loadImpedance = this.loadImpedance.copy();
/*     */     
/* 120 */     tl.lineLength = this.lineLength;
/* 121 */     tl.segmentLength = this.segmentLength;
/* 122 */     tl.frequency = this.frequency;
/* 123 */     tl.segmentLength = this.segmentLength;
/* 124 */     tl.omega = this.omega;
/*     */     
/* 126 */     tl.inputVoltage = this.inputVoltage.copy();
/* 127 */     tl.inputCurrent = this.inputCurrent.copy();
/* 128 */     tl.outputVoltage = this.outputVoltage.copy();
/* 129 */     tl.outputCurrent = this.outputCurrent.copy();
/*     */     
/* 131 */     tl.idealWavelength = this.idealWavelength;
/* 132 */     tl.generalWavelength = this.generalWavelength;
/* 133 */     tl.lowLossWavelength = this.lowLossWavelength;
/*     */     
/* 135 */     tl.idealPhaseVelocity = this.idealPhaseVelocity;
/* 136 */     tl.generalPhaseVelocity = this.generalPhaseVelocity;
/* 137 */     tl.lowLossPhaseVelocity = this.lowLossPhaseVelocity;
/*     */     
/* 139 */     tl.idealGroupVelocity = this.idealGroupVelocity;
/* 140 */     tl.generalGroupVelocity = this.generalGroupVelocity;
/* 141 */     tl.lowLossGroupVelocity = this.lowLossGroupVelocity;
/* 142 */     tl.delta = this.delta;
/*     */     
/* 144 */     tl.idealAttenuationConstant = this.idealAttenuationConstant;
/* 145 */     tl.generalAttenuationConstant = this.generalAttenuationConstant;
/* 146 */     tl.lowLossAttenuationConstant = this.lowLossAttenuationConstant;
/*     */     
/* 148 */     tl.idealPhaseConstant = this.idealPhaseConstant;
/* 149 */     tl.generalPhaseConstant = this.generalPhaseConstant;
/* 150 */     tl.lowLossPhaseConstant = this.lowLossPhaseConstant;
/*     */     
/* 152 */     tl.idealPropagationConstant = this.idealPropagationConstant.copy();
/* 153 */     tl.loadImpedance = this.loadImpedance.copy();
/* 154 */     tl.loadImpedance = this.loadImpedance.copy();
/* 155 */     tl.loadImpedance = this.loadImpedance.copy();
/*     */     
/* 157 */     tl.generalPropagationConstant = this.generalPropagationConstant.copy();
/* 158 */     tl.lowLossPropagationConstant = this.lowLossPropagationConstant.copy();
/* 159 */     tl.idealCharacteristicImpedance = this.idealCharacteristicImpedance.copy();
/* 160 */     tl.idealRealCharacteristicImpedance = this.idealRealCharacteristicImpedance;
/*     */     
/* 162 */     tl.generalCharacteristicImpedance = this.generalCharacteristicImpedance.copy();
/* 163 */     tl.lowLossCharacteristicImpedance = this.lowLossCharacteristicImpedance.copy();
/* 164 */     tl.idealInputImpedance = this.idealInputImpedance.copy();
/* 165 */     tl.generalInputImpedance = this.generalInputImpedance.copy();
/* 166 */     tl.lowLossInputImpedance = this.lowLossInputImpedance.copy();
/*     */     
/* 168 */     tl.idealShortedLineImpedance = this.idealShortedLineImpedance.copy();
/* 169 */     tl.generalShortedLineImpedance = this.generalShortedLineImpedance.copy();
/* 170 */     tl.lowLossShortedLineImpedance = this.lowLossShortedLineImpedance.copy();
/*     */     
/* 172 */     tl.idealOpenLineImpedance = this.idealOpenLineImpedance.copy();
/* 173 */     tl.generalOpenLineImpedance = this.generalOpenLineImpedance.copy();
/* 174 */     tl.lowLossOpenLineImpedance = this.lowLossOpenLineImpedance.copy();
/*     */     
/* 176 */     tl.idealQuarterWaveLineImpedance = this.idealQuarterWaveLineImpedance.copy();
/* 177 */     tl.generalQuarterWaveLineImpedance = this.generalQuarterWaveLineImpedance.copy();
/* 178 */     tl.lowLossQuarterWaveLineImpedance = this.lowLossQuarterWaveLineImpedance.copy();
/*     */     
/* 180 */     tl.idealHalfWaveLineImpedance = this.idealHalfWaveLineImpedance.copy();
/* 181 */     tl.generalHalfWaveLineImpedance = this.generalHalfWaveLineImpedance.copy();
/* 182 */     tl.lowLossHalfWaveLineImpedance = this.lowLossHalfWaveLineImpedance.copy();
/*     */     
/* 184 */     tl.idealRefectionCoefficient = this.idealRefectionCoefficient.copy();
/* 185 */     tl.generalRefectionCoefficient = this.generalRefectionCoefficient.copy();
/* 186 */     tl.lowLossRefectionCoefficient = this.lowLossRefectionCoefficient.copy();
/*     */     
/* 188 */     tl.idealStandingWaveRatio = this.idealStandingWaveRatio;
/* 189 */     tl.generalStandingWaveRatio = this.generalStandingWaveRatio;
/* 190 */     tl.lowLossStandingWaveRatio = this.lowLossStandingWaveRatio;
/*     */     
/* 192 */     tl.idealABCDmatrix = this.idealABCDmatrix.copy();
/* 193 */     tl.generalABCDmatrix = this.generalABCDmatrix.copy();
/* 194 */     tl.lowLossABCDmatrix = this.lowLossABCDmatrix.copy();
/*     */     
/* 196 */     tl.numberOfPoints = this.numberOfPoints;
/*     */     
/* 198 */     return tl;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 206 */     Object ret = null;
/*     */     
/* 208 */     if (this != null)
/*     */     {
/* 210 */       ParallelPlateLine tl = new ParallelPlateLine();
/*     */       
/* 212 */       tl.plateWidth = this.plateWidth;
/* 213 */       tl.plateSeparation = this.plateSeparation;
/* 214 */       tl.distancesSet = this.distancesSet;
/* 215 */       tl.relativePermittivity = this.relativePermittivity;
/* 216 */       tl.relativePermeability = this.relativePermeability;
/*     */       
/* 218 */       tl.title = this.title;
/* 219 */       tl.distributedResistance = this.distributedResistance;
/* 220 */       tl.distributedConductance = this.distributedConductance;
/* 221 */       tl.distributedCapacitance = this.distributedCapacitance;
/* 222 */       tl.distributedInductance = this.distributedInductance;
/*     */       
/* 224 */       tl.distributedImpedance = this.distributedImpedance.copy();
/* 225 */       tl.distributedAdmittance = this.distributedAdmittance.copy();
/* 226 */       tl.loadImpedance = this.loadImpedance.copy();
/*     */       
/* 228 */       tl.lineLength = this.lineLength;
/* 229 */       tl.segmentLength = this.segmentLength;
/* 230 */       tl.frequency = this.frequency;
/* 231 */       tl.segmentLength = this.segmentLength;
/* 232 */       tl.omega = this.omega;
/*     */       
/* 234 */       tl.inputVoltage = this.inputVoltage.copy();
/* 235 */       tl.inputCurrent = this.inputCurrent.copy();
/* 236 */       tl.outputVoltage = this.outputVoltage.copy();
/* 237 */       tl.outputCurrent = this.outputCurrent.copy();
/*     */       
/* 239 */       tl.idealWavelength = this.idealWavelength;
/* 240 */       tl.generalWavelength = this.generalWavelength;
/* 241 */       tl.lowLossWavelength = this.lowLossWavelength;
/*     */       
/* 243 */       tl.idealPhaseVelocity = this.idealPhaseVelocity;
/* 244 */       tl.generalPhaseVelocity = this.generalPhaseVelocity;
/* 245 */       tl.lowLossPhaseVelocity = this.lowLossPhaseVelocity;
/*     */       
/* 247 */       tl.idealGroupVelocity = this.idealGroupVelocity;
/* 248 */       tl.generalGroupVelocity = this.generalGroupVelocity;
/* 249 */       tl.lowLossGroupVelocity = this.lowLossGroupVelocity;
/* 250 */       tl.delta = this.delta;
/*     */       
/* 252 */       tl.idealAttenuationConstant = this.idealAttenuationConstant;
/* 253 */       tl.generalAttenuationConstant = this.generalAttenuationConstant;
/* 254 */       tl.lowLossAttenuationConstant = this.lowLossAttenuationConstant;
/*     */       
/* 256 */       tl.idealPhaseConstant = this.idealPhaseConstant;
/* 257 */       tl.generalPhaseConstant = this.generalPhaseConstant;
/* 258 */       tl.lowLossPhaseConstant = this.lowLossPhaseConstant;
/*     */       
/* 260 */       tl.idealPropagationConstant = this.idealPropagationConstant.copy();
/* 261 */       tl.loadImpedance = this.loadImpedance.copy();
/* 262 */       tl.loadImpedance = this.loadImpedance.copy();
/* 263 */       tl.loadImpedance = this.loadImpedance.copy();
/*     */       
/* 265 */       tl.generalPropagationConstant = this.generalPropagationConstant.copy();
/* 266 */       tl.lowLossPropagationConstant = this.lowLossPropagationConstant.copy();
/* 267 */       tl.idealCharacteristicImpedance = this.idealCharacteristicImpedance.copy();
/* 268 */       tl.idealRealCharacteristicImpedance = this.idealRealCharacteristicImpedance;
/*     */       
/* 270 */       tl.generalCharacteristicImpedance = this.generalCharacteristicImpedance.copy();
/* 271 */       tl.lowLossCharacteristicImpedance = this.lowLossCharacteristicImpedance.copy();
/* 272 */       tl.idealInputImpedance = this.idealInputImpedance.copy();
/* 273 */       tl.generalInputImpedance = this.generalInputImpedance.copy();
/* 274 */       tl.lowLossInputImpedance = this.lowLossInputImpedance.copy();
/*     */       
/* 276 */       tl.idealShortedLineImpedance = this.idealShortedLineImpedance.copy();
/* 277 */       tl.generalShortedLineImpedance = this.generalShortedLineImpedance.copy();
/* 278 */       tl.lowLossShortedLineImpedance = this.lowLossShortedLineImpedance.copy();
/*     */       
/* 280 */       tl.idealOpenLineImpedance = this.idealOpenLineImpedance.copy();
/* 281 */       tl.generalOpenLineImpedance = this.generalOpenLineImpedance.copy();
/* 282 */       tl.lowLossOpenLineImpedance = this.lowLossOpenLineImpedance.copy();
/*     */       
/* 284 */       tl.idealQuarterWaveLineImpedance = this.idealQuarterWaveLineImpedance.copy();
/* 285 */       tl.generalQuarterWaveLineImpedance = this.generalQuarterWaveLineImpedance.copy();
/* 286 */       tl.lowLossQuarterWaveLineImpedance = this.lowLossQuarterWaveLineImpedance.copy();
/*     */       
/* 288 */       tl.idealHalfWaveLineImpedance = this.idealHalfWaveLineImpedance.copy();
/* 289 */       tl.generalHalfWaveLineImpedance = this.generalHalfWaveLineImpedance.copy();
/* 290 */       tl.lowLossHalfWaveLineImpedance = this.lowLossHalfWaveLineImpedance.copy();
/*     */       
/* 292 */       tl.idealRefectionCoefficient = this.idealRefectionCoefficient.copy();
/* 293 */       tl.generalRefectionCoefficient = this.generalRefectionCoefficient.copy();
/* 294 */       tl.lowLossRefectionCoefficient = this.lowLossRefectionCoefficient.copy();
/*     */       
/* 296 */       tl.idealStandingWaveRatio = this.idealStandingWaveRatio;
/* 297 */       tl.generalStandingWaveRatio = this.generalStandingWaveRatio;
/* 298 */       tl.lowLossStandingWaveRatio = this.lowLossStandingWaveRatio;
/*     */       
/* 300 */       tl.idealABCDmatrix = this.idealABCDmatrix.copy();
/* 301 */       tl.generalABCDmatrix = this.generalABCDmatrix.copy();
/* 302 */       tl.lowLossABCDmatrix = this.lowLossABCDmatrix.copy();
/*     */       
/* 304 */       tl.numberOfPoints = this.numberOfPoints;
/*     */       
/* 306 */       ret = tl;
/*     */     }
/* 308 */     return ret;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/circuits/ParallelPlateLine.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */