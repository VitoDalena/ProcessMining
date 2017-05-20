/*     */ package flanagan.control;
/*     */ 
/*     */ import flanagan.complex.Complex;
/*     */ import flanagan.complex.ComplexPoly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ClosedLoop
/*     */   extends BlackBox
/*     */ {
/*  44 */   private OpenLoop forwardPath = new OpenLoop();
/*  45 */   private OpenLoop closedPath = new OpenLoop();
/*     */   
/*  47 */   private ArrayList<BlackBox> feedbackPath = new ArrayList();
/*  48 */   private int nFeedbackBoxes = 0;
/*     */   
/*  50 */   private boolean checkPath = false;
/*  51 */   private boolean checkNoMix = true;
/*  52 */   private boolean checkConsolidate = false;
/*     */   
/*     */   public ClosedLoop()
/*     */   {
/*  56 */     super("Closed Loop");
/*     */   }
/*     */   
/*     */   public void addBoxToForwardPath(BlackBox box)
/*     */   {
/*  61 */     this.forwardPath.addBoxToPath(box);
/*     */   }
/*     */   
/*     */   public void addBoxToFeedbackPath(BlackBox box)
/*     */   {
/*  66 */     this.feedbackPath.add(box);
/*  67 */     this.nFeedbackBoxes += 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void consolidate()
/*     */   {
/*  75 */     this.closedPath = this.forwardPath.copy();
/*  76 */     for (int i = 0; i < this.nFeedbackBoxes; i++) {
/*  77 */       this.closedPath.addBoxToPath((BlackBox)this.feedbackPath.get(i));
/*     */     }
/*     */     
/*     */ 
/*  81 */     this.forwardPath.consolidate();
/*     */     
/*     */ 
/*  84 */     this.closedPath.consolidate();
/*     */     
/*     */ 
/*  87 */     ComplexPoly fpNumer = this.forwardPath.getSnumer();
/*  88 */     ComplexPoly fpDenom = this.forwardPath.getSdenom();
/*  89 */     ComplexPoly cpNumer = this.closedPath.getSnumer();
/*  90 */     ComplexPoly cpDenom = this.closedPath.getSdenom();
/*  91 */     if (fpDenom.isEqual(cpDenom)) {
/*  92 */       this.sNumer = fpNumer.copy();
/*  93 */       this.sDenom = cpNumer.plus(fpDenom).copy();
/*     */     }
/*     */     else {
/*  96 */       this.sNumer = fpNumer.times(cpDenom);
/*  97 */       this.sDenom = cpNumer.plus(cpDenom.times(fpDenom));
/*     */     }
/*  99 */     this.checkConsolidate = true;
/*     */   }
/*     */   
/*     */   public int getNumberOfBoxesInForwardPath()
/*     */   {
/* 104 */     if (!this.checkConsolidate) consolidate();
/* 105 */     return this.forwardPath.getNumberOfBoxes();
/*     */   }
/*     */   
/*     */   public int getNumberOfBoxesInClosedLoop()
/*     */   {
/* 110 */     if (!this.checkConsolidate) consolidate();
/* 111 */     return this.closedPath.getNumberOfBoxes();
/*     */   }
/*     */   
/*     */   public ArrayList<Object> getForwardPathSegmentsArrayList()
/*     */   {
/* 116 */     if (!this.checkConsolidate) consolidate();
/* 117 */     return this.forwardPath.getSegmentsArrayList();
/*     */   }
/*     */   
/*     */   public Vector<Object> getForwardPathSegmentsVector()
/*     */   {
/* 122 */     if (!this.checkConsolidate) consolidate();
/* 123 */     return this.forwardPath.getSegmentsVector();
/*     */   }
/*     */   
/*     */ 
/*     */   public ArrayList<Object> getClosedLoopSegmentsArrayList()
/*     */   {
/* 129 */     if (!this.checkConsolidate) consolidate();
/* 130 */     return this.closedPath.getSegmentsArrayList();
/*     */   }
/*     */   
/*     */   public Vector<Object> getClosedLoopSegmentsVector()
/*     */   {
/* 135 */     if (!this.checkConsolidate) consolidate();
/* 136 */     return this.closedPath.getSegmentsVector();
/*     */   }
/*     */   
/*     */   public int getNumberOfSegmentsInForwardPath()
/*     */   {
/* 141 */     if (!this.checkConsolidate) consolidate();
/* 142 */     return this.forwardPath.getNumberOfSegments();
/*     */   }
/*     */   
/*     */   public int getNumberOfSegmentsInClosedLoop()
/*     */   {
/* 147 */     if (!this.checkConsolidate) consolidate();
/* 148 */     return this.closedPath.getNumberOfSegments();
/*     */   }
/*     */   
/*     */   public String getNamesOfBoxesInForwardPath()
/*     */   {
/* 153 */     if (!this.checkConsolidate) consolidate();
/* 154 */     return this.forwardPath.getNamesOfBoxes();
/*     */   }
/*     */   
/*     */   public String getNamesOfBoxesInClosedLoop()
/*     */   {
/* 159 */     if (!this.checkConsolidate) consolidate();
/* 160 */     return this.closedPath.getNamesOfBoxes();
/*     */   }
/*     */   
/*     */   public void removeAllBoxes()
/*     */   {
/* 165 */     this.forwardPath.removeAllBoxes();
/* 166 */     this.closedPath.removeAllBoxes();
/*     */   }
/*     */   
/*     */   public ClosedLoop copy()
/*     */   {
/* 171 */     if (this == null) {
/* 172 */       return null;
/*     */     }
/*     */     
/* 175 */     ClosedLoop bb = new ClosedLoop();
/*     */     
/* 177 */     bb.nFeedbackBoxes = this.nFeedbackBoxes;
/* 178 */     bb.checkPath = this.checkPath;
/* 179 */     bb.checkNoMix = this.checkNoMix;
/* 180 */     bb.checkConsolidate = this.checkConsolidate;
/* 181 */     bb.forwardPath = this.forwardPath.copy();
/* 182 */     bb.closedPath = this.closedPath.copy();
/* 183 */     if (this.feedbackPath.size() == 0) {
/* 184 */       bb.feedbackPath = new ArrayList();
/*     */     }
/*     */     else {
/* 187 */       for (int i = 0; i < this.feedbackPath.size(); i++) { bb.feedbackPath.add(((BlackBox)this.feedbackPath.get(i)).copy());
/*     */       }
/*     */     }
/* 190 */     bb.sampLen = this.sampLen;
/* 191 */     bb.inputT = ((double[])this.inputT.clone());
/* 192 */     bb.outputT = ((double[])this.outputT.clone());
/* 193 */     bb.time = ((double[])this.time.clone());
/* 194 */     bb.forgetFactor = this.forgetFactor;
/* 195 */     bb.deltaT = this.deltaT;
/* 196 */     bb.sampFreq = this.sampFreq;
/* 197 */     bb.inputS = this.inputS.copy();
/* 198 */     bb.outputS = this.outputS.copy();
/* 199 */     bb.sValue = this.sValue.copy();
/* 200 */     bb.zValue = this.zValue.copy();
/* 201 */     bb.sNumer = this.sNumer.copy();
/* 202 */     bb.sDenom = this.sDenom.copy();
/* 203 */     bb.zNumer = this.zNumer.copy();
/* 204 */     bb.zDenom = this.zDenom.copy();
/* 205 */     bb.sPoles = Complex.copy(this.sPoles);
/* 206 */     bb.sZeros = Complex.copy(this.sZeros);
/* 207 */     bb.zPoles = Complex.copy(this.zPoles);
/* 208 */     bb.zZeros = Complex.copy(this.zZeros);
/* 209 */     bb.sNumerDeg = this.sNumerDeg;
/* 210 */     bb.sDenomDeg = this.sDenomDeg;
/* 211 */     bb.zNumerDeg = this.zNumerDeg;
/* 212 */     bb.zDenomDeg = this.zDenomDeg;
/* 213 */     bb.deadTime = this.deadTime;
/* 214 */     bb.orderPade = this.orderPade;
/* 215 */     bb.sNumerPade = this.sNumerPade.copy();
/* 216 */     bb.sDenomPade = this.sDenomPade.copy();
/* 217 */     bb.sPolesPade = Complex.copy(this.sPolesPade);
/* 218 */     bb.sZerosPade = Complex.copy(this.sZerosPade);
/* 219 */     bb.sNumerDegPade = this.sNumerDegPade;
/* 220 */     bb.sDenomDegPade = this.sDenomDegPade;
/* 221 */     bb.maptozero = this.maptozero;
/* 222 */     bb.padeAdded = this.padeAdded;
/* 223 */     bb.integrationSum = this.integrationSum;
/* 224 */     bb.integMethod = this.integMethod;
/* 225 */     bb.ztransMethod = this.ztransMethod;
/* 226 */     bb.name = this.name;
/* 227 */     bb.fixedName = this.fixedName;
/* 228 */     bb.nPlotPoints = this.nPlotPoints;
/*     */     
/* 230 */     return bb;
/*     */   }
/*     */   
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 236 */     Object ret = null;
/*     */     
/* 238 */     if (this != null) {
/* 239 */       ClosedLoop bb = new ClosedLoop();
/*     */       
/* 241 */       bb.nFeedbackBoxes = this.nFeedbackBoxes;
/* 242 */       bb.checkPath = this.checkPath;
/* 243 */       bb.checkNoMix = this.checkNoMix;
/* 244 */       bb.checkConsolidate = this.checkConsolidate;
/* 245 */       bb.forwardPath = this.forwardPath.copy();
/* 246 */       bb.closedPath = this.closedPath.copy();
/* 247 */       if (this.feedbackPath.size() == 0) {
/* 248 */         bb.feedbackPath = new ArrayList();
/*     */       }
/*     */       else {
/* 251 */         for (int i = 0; i < this.feedbackPath.size(); i++) { bb.feedbackPath.add(((BlackBox)this.feedbackPath.get(i)).copy());
/*     */         }
/*     */       }
/* 254 */       bb.sampLen = this.sampLen;
/* 255 */       bb.inputT = ((double[])this.inputT.clone());
/* 256 */       bb.outputT = ((double[])this.outputT.clone());
/* 257 */       bb.time = ((double[])this.time.clone());
/* 258 */       bb.forgetFactor = this.forgetFactor;
/* 259 */       bb.deltaT = this.deltaT;
/* 260 */       bb.sampFreq = this.sampFreq;
/* 261 */       bb.inputS = this.inputS.copy();
/* 262 */       bb.outputS = this.outputS.copy();
/* 263 */       bb.sValue = this.sValue.copy();
/* 264 */       bb.zValue = this.zValue.copy();
/* 265 */       bb.sNumer = this.sNumer.copy();
/* 266 */       bb.sDenom = this.sDenom.copy();
/* 267 */       bb.zNumer = this.zNumer.copy();
/* 268 */       bb.zDenom = this.zDenom.copy();
/* 269 */       bb.sPoles = Complex.copy(this.sPoles);
/* 270 */       bb.sZeros = Complex.copy(this.sZeros);
/* 271 */       bb.zPoles = Complex.copy(this.zPoles);
/* 272 */       bb.zZeros = Complex.copy(this.zZeros);
/* 273 */       bb.sNumerDeg = this.sNumerDeg;
/* 274 */       bb.sDenomDeg = this.sDenomDeg;
/* 275 */       bb.zNumerDeg = this.zNumerDeg;
/* 276 */       bb.zDenomDeg = this.zDenomDeg;
/* 277 */       bb.deadTime = this.deadTime;
/* 278 */       bb.orderPade = this.orderPade;
/* 279 */       bb.sNumerPade = this.sNumerPade.copy();
/* 280 */       bb.sDenomPade = this.sDenomPade.copy();
/* 281 */       bb.sPolesPade = Complex.copy(this.sPolesPade);
/* 282 */       bb.sZerosPade = Complex.copy(this.sZerosPade);
/* 283 */       bb.sNumerDegPade = this.sNumerDegPade;
/* 284 */       bb.sDenomDegPade = this.sDenomDegPade;
/* 285 */       bb.maptozero = this.maptozero;
/* 286 */       bb.padeAdded = this.padeAdded;
/* 287 */       bb.integrationSum = this.integrationSum;
/* 288 */       bb.integMethod = this.integMethod;
/* 289 */       bb.ztransMethod = this.ztransMethod;
/* 290 */       bb.name = this.name;
/* 291 */       bb.fixedName = this.fixedName;
/* 292 */       bb.nPlotPoints = this.nPlotPoints;
/*     */       
/* 294 */       ret = bb;
/*     */     }
/* 296 */     return ret;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/control/ClosedLoop.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */