/*      */ package flanagan.analysis;
/*      */ 
/*      */ import flanagan.io.Db;
/*      */ import flanagan.io.FileChooser;
/*      */ import flanagan.io.FileInput;
/*      */ import flanagan.math.ArrayMaths;
/*      */ import flanagan.math.Fmath;
/*      */ import flanagan.math.Matrix;
/*      */ import flanagan.plot.PlotGraph;
/*      */ import java.io.PrintStream;
/*      */ import java.text.DateFormat;
/*      */ import java.util.Date;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Scores
/*      */ {
/*   53 */   protected String[] title = null;
/*   54 */   protected int titleLines = 0;
/*      */   
/*   56 */   protected String inputFilename = null;
/*   57 */   protected String outputFilename = null;
/*   58 */   protected int fileOption = 1;
/*      */   
/*   60 */   protected boolean fileOptionSet = false;
/*   61 */   protected String[] fileExtensions = { ".txt", ".xls" };
/*   62 */   protected boolean fileNumberingSet = false;
/*      */   
/*   64 */   protected int trunc = 6;
/*      */   
/*   66 */   protected int originalDataType = -1;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   74 */   protected int originalDataOrder = -1;
/*      */   
/*      */ 
/*   77 */   protected Object originalData = null;
/*      */   
/*   79 */   protected double[][] scores0 = (double[][])null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*   84 */   protected double[][] originalScores0 = (double[][])null;
/*   85 */   protected double[][] standardizedScores0 = (double[][])null;
/*      */   
/*   87 */   protected double[][] scores1 = (double[][])null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*   92 */   protected double[][] originalScores1 = (double[][])null;
/*   93 */   protected double[][] standardizedScores1 = (double[][])null;
/*      */   
/*   95 */   protected boolean dataEntered = false;
/*   96 */   protected boolean dataPreprocessed = false;
/*      */   
/*   98 */   protected int nItems = 0;
/*   99 */   protected int originalNitems = 0;
/*  100 */   protected String[] itemNames = null;
/*  101 */   protected String[] originalItemNames = null;
/*  102 */   protected boolean itemNamesSet = false;
/*      */   
/*  104 */   protected int nPersons = 0;
/*  105 */   protected int originalNpersons = 0;
/*      */   
/*  107 */   protected int nScores = 0;
/*  108 */   protected int originalNscores = 0;
/*      */   
/*  110 */   protected String otherFalse = null;
/*  111 */   protected String otherTrue = null;
/*      */   
/*  113 */   protected boolean otherDichotomousDataSet = false;
/*  114 */   protected boolean[] dichotomous = null;
/*  115 */   protected double[] dichotomousPercentage = null;
/*  116 */   protected boolean dichotomousOverall = false;
/*  117 */   protected boolean dichotomousCheckDone = false;
/*      */   
/*  119 */   protected boolean letterToNumeralSet = false;
/*      */   
/*  121 */   protected boolean ignoreNoResponseRequests = false;
/*      */   
/*  123 */   protected double itemDeletionPercentage = 100.0D;
/*  124 */   protected boolean itemDeletionPercentageSet = false;
/*      */   
/*  126 */   protected double personDeletionPercentage = 0.0D;
/*  127 */   protected boolean personDeletionPercentageSet = false;
/*      */   
/*  129 */   protected int replacementOption = 3;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  136 */   protected String[] replacementOptionNames = { "score replaced by zero", "score replaced by person's mean", "score replaced by item mean", "score replaced by overall mean", "user supplied score for each 'no response'" };
/*      */   
/*  138 */   protected boolean replacementOptionSet = false;
/*  139 */   protected boolean allNoResponseOptionsSet = false;
/*  140 */   protected boolean noResponseHandlingSet = false;
/*      */   
/*  142 */   protected int nNaN = 0;
/*  143 */   protected boolean[] deletedItems = null;
/*  144 */   protected int nDeletedItems = 0;
/*  145 */   protected int[] deletedItemsIndices = null;
/*  146 */   protected int[] itemIndices = null;
/*      */   
/*  148 */   protected boolean[] deletedPersons = null;
/*      */   
/*  150 */   protected int nDeletedPersons = 0;
/*  151 */   protected int[] deletedPersonsIndices = null;
/*  152 */   protected int[] personIndices = null;
/*      */   
/*  154 */   protected int nReplacements = 0;
/*  155 */   protected String[] replacementIndices = null;
/*      */   
/*  157 */   protected double[] rawItemMeans = null;
/*  158 */   protected double rawItemMeansMean = NaN.0D;
/*  159 */   protected double rawItemMeansSd = NaN.0D;
/*  160 */   protected double rawItemMeansVar = NaN.0D;
/*  161 */   protected double rawItemMeansMin = NaN.0D;
/*  162 */   protected double rawItemMeansMax = NaN.0D;
/*  163 */   protected double rawItemMeansRange = NaN.0D;
/*      */   
/*  165 */   protected double[] rawItemStandardDeviations = null;
/*  166 */   protected double rawItemStandardDeviationsMean = NaN.0D;
/*  167 */   protected double rawItemStandardDeviationsSd = NaN.0D;
/*  168 */   protected double rawItemStandardDeviationsVar = NaN.0D;
/*  169 */   protected double rawItemStandardDeviationsMin = NaN.0D;
/*  170 */   protected double rawItemStandardDeviationsMax = NaN.0D;
/*  171 */   protected double rawItemStandardDeviationsRange = NaN.0D;
/*      */   
/*  173 */   protected double[] rawItemVariances = null;
/*  174 */   protected double rawItemVariancesMean = NaN.0D;
/*  175 */   protected double rawItemVariancesSd = NaN.0D;
/*  176 */   protected double rawItemVariancesVar = NaN.0D;
/*  177 */   protected double rawItemVariancesMin = NaN.0D;
/*  178 */   protected double rawItemVariancesMax = NaN.0D;
/*  179 */   protected double rawItemVariancesRange = NaN.0D;
/*      */   
/*  181 */   protected double[] rawItemMinima = null;
/*  182 */   protected double rawItemMinimaMean = NaN.0D;
/*  183 */   protected double rawItemMinimaSd = NaN.0D;
/*  184 */   protected double rawItemMinimaVar = NaN.0D;
/*  185 */   protected double rawItemMinimaMin = NaN.0D;
/*  186 */   protected double rawItemMinimaMax = NaN.0D;
/*  187 */   protected double rawItemMinimaRange = NaN.0D;
/*      */   
/*  189 */   protected double[] rawItemMaxima = null;
/*  190 */   protected double rawItemMaximaMean = NaN.0D;
/*  191 */   protected double rawItemMaximaSd = NaN.0D;
/*  192 */   protected double rawItemMaximaVar = NaN.0D;
/*  193 */   protected double rawItemMaximaMin = NaN.0D;
/*  194 */   protected double rawItemMaximaMax = NaN.0D;
/*  195 */   protected double rawItemMaximaRange = NaN.0D;
/*      */   
/*  197 */   protected double[] rawItemRanges = null;
/*  198 */   protected double rawItemRangesMean = NaN.0D;
/*  199 */   protected double rawItemRangesSd = NaN.0D;
/*  200 */   protected double rawItemRangesVar = NaN.0D;
/*  201 */   protected double rawItemRangesMin = NaN.0D;
/*  202 */   protected double rawItemRangesMax = NaN.0D;
/*  203 */   protected double rawItemRangesRange = NaN.0D;
/*      */   
/*  205 */   protected double[] rawItemTotals = null;
/*  206 */   protected double rawItemTotalsMean = NaN.0D;
/*  207 */   protected double rawItemTotalsSd = NaN.0D;
/*  208 */   protected double rawItemTotalsVar = NaN.0D;
/*  209 */   protected double rawItemTotalsMin = NaN.0D;
/*  210 */   protected double rawItemTotalsMax = NaN.0D;
/*  211 */   protected double rawItemTotalsRange = NaN.0D;
/*      */   
/*  213 */   protected double[] rawItemMedians = null;
/*  214 */   protected double rawItemMediansMean = NaN.0D;
/*  215 */   protected double rawItemMediansSd = NaN.0D;
/*  216 */   protected double rawItemMediansVar = NaN.0D;
/*  217 */   protected double rawItemMediansMin = NaN.0D;
/*  218 */   protected double rawItemMediansMax = NaN.0D;
/*  219 */   protected double rawItemMediansRange = NaN.0D;
/*      */   
/*  221 */   protected double[] rawItemMomentSkewness = null;
/*  222 */   protected double[] rawItemMedianSkewness = null;
/*  223 */   protected double[] rawItemQuartileSkewness = null;
/*  224 */   protected double[] rawItemKurtosisExcess = null;
/*      */   
/*  226 */   protected double[] rawPersonMeans = null;
/*  227 */   protected double[] rawPersonStandardDeviations = null;
/*  228 */   protected double[] rawPersonVariances = null;
/*  229 */   protected double[] rawPersonMinima = null;
/*  230 */   protected double[] rawPersonMaxima = null;
/*  231 */   protected double[] rawPersonRanges = null;
/*  232 */   protected double[] rawPersonTotals = null;
/*      */   
/*  234 */   protected double rawAllResponsesMean = NaN.0D;
/*  235 */   protected double rawAllResponsesStandardDeviation = NaN.0D;
/*  236 */   protected double rawAllResponsesVariance = NaN.0D;
/*  237 */   protected double rawAllResponsesMinimum = NaN.0D;
/*  238 */   protected double rawAllResponsesMaximum = NaN.0D;
/*  239 */   protected double rawAllResponsesRange = NaN.0D;
/*  240 */   protected double rawAllResponsesTotal = NaN.0D;
/*      */   
/*  242 */   protected double[][] rawCovariances = (double[][])null;
/*  243 */   protected double[][] rawCorrelationCoefficients = (double[][])null;
/*  244 */   protected double[] rawRhosWithTotal = null;
/*  245 */   protected double rawMeanRhoWithTotals = NaN.0D;
/*  246 */   protected double rawStandardDeviationRhoWithTotals = NaN.0D;
/*  247 */   protected double rawMeanRhoWithoutTotals = NaN.0D;
/*  248 */   protected double rawStandardDeviationRhoWithoutTotals = NaN.0D;
/*      */   
/*  250 */   protected double[] standardizedItemMeans = null;
/*  251 */   protected double standardizedItemMeansMean = NaN.0D;
/*  252 */   protected double standardizedItemMeansSd = NaN.0D;
/*  253 */   protected double standardizedItemMeansVar = NaN.0D;
/*  254 */   protected double standardizedItemMeansMin = NaN.0D;
/*  255 */   protected double standardizedItemMeansMax = NaN.0D;
/*  256 */   protected double standardizedItemMeansRange = NaN.0D;
/*      */   
/*  258 */   protected double[] standardizedItemStandardDeviations = null;
/*  259 */   protected double standardizedItemStandardDeviationsMean = NaN.0D;
/*  260 */   protected double standardizedItemStandardDeviationsSd = NaN.0D;
/*  261 */   protected double standardizedItemStandardDeviationsVar = NaN.0D;
/*  262 */   protected double standardizedItemStandardDeviationsMin = NaN.0D;
/*  263 */   protected double standardizedItemStandardDeviationsMax = NaN.0D;
/*  264 */   protected double standardizedItemStandardDeviationsRange = NaN.0D;
/*      */   
/*  266 */   protected double[] standardizedItemVariances = null;
/*  267 */   protected double standardizedItemVariancesMean = NaN.0D;
/*  268 */   protected double standardizedItemVariancesSd = NaN.0D;
/*  269 */   protected double standardizedItemVariancesVar = NaN.0D;
/*  270 */   protected double standardizedItemVariancesMin = NaN.0D;
/*  271 */   protected double standardizedItemVariancesMax = NaN.0D;
/*  272 */   protected double standardizedItemVariancesRange = NaN.0D;
/*      */   
/*  274 */   protected double[] standardizedItemMinima = null;
/*  275 */   protected double standardizedItemMinimaMean = NaN.0D;
/*  276 */   protected double standardizedItemMinimaSd = NaN.0D;
/*  277 */   protected double standardizedItemMinimaVar = NaN.0D;
/*  278 */   protected double standardizedItemMinimaMin = NaN.0D;
/*  279 */   protected double standardizedItemMinimaMax = NaN.0D;
/*  280 */   protected double standardizedItemMinimaRange = NaN.0D;
/*      */   
/*  282 */   protected double[] standardizedItemMaxima = null;
/*  283 */   protected double standardizedItemMaximaMean = NaN.0D;
/*  284 */   protected double standardizedItemMaximaSd = NaN.0D;
/*  285 */   protected double standardizedItemMaximaVar = NaN.0D;
/*  286 */   protected double standardizedItemMaximaMin = NaN.0D;
/*  287 */   protected double standardizedItemMaximaMax = NaN.0D;
/*  288 */   protected double standardizedItemMaximaRange = NaN.0D;
/*      */   
/*  290 */   protected double[] standardizedItemRanges = null;
/*  291 */   protected double standardizedItemRangesMean = NaN.0D;
/*  292 */   protected double standardizedItemRangesSd = NaN.0D;
/*  293 */   protected double standardizedItemRangesVar = NaN.0D;
/*  294 */   protected double standardizedItemRangesMin = NaN.0D;
/*  295 */   protected double standardizedItemRangesMax = NaN.0D;
/*  296 */   protected double standardizedItemRangesRange = NaN.0D;
/*      */   
/*  298 */   protected double[] standardizedItemTotals = null;
/*  299 */   protected double standardizedItemTotalsMean = NaN.0D;
/*  300 */   protected double standardizedItemTotalsSd = NaN.0D;
/*  301 */   protected double standardizedItemTotalsVar = NaN.0D;
/*  302 */   protected double standardizedItemTotalsMin = NaN.0D;
/*  303 */   protected double standardizedItemTotalsMax = NaN.0D;
/*  304 */   protected double standardizedItemTotalsRange = NaN.0D;
/*      */   
/*  306 */   protected double[] standardizedItemMedians = null;
/*  307 */   protected double standardizedItemMediansMean = NaN.0D;
/*  308 */   protected double standardizedItemMediansSd = NaN.0D;
/*  309 */   protected double standardizedItemMediansVar = NaN.0D;
/*  310 */   protected double standardizedItemMediansMin = NaN.0D;
/*  311 */   protected double standardizedItemMediansMax = NaN.0D;
/*  312 */   protected double standardizedItemMediansRange = NaN.0D;
/*      */   
/*  314 */   protected double[] standardizedItemMomentSkewness = null;
/*  315 */   protected double[] standardizedItemMedianSkewness = null;
/*  316 */   protected double[] standardizedItemQuartileSkewness = null;
/*  317 */   protected double[] standardizedItemKurtosisExcess = null;
/*      */   
/*  319 */   protected double[] standardizedPersonMeans = null;
/*  320 */   protected double[] standardizedPersonStandardDeviations = null;
/*  321 */   protected double[] standardizedPersonVariances = null;
/*  322 */   protected double[] standardizedPersonMinima = null;
/*  323 */   protected double[] standardizedPersonMaxima = null;
/*  324 */   protected double[] standardizedPersonRanges = null;
/*  325 */   protected double[] standardizedPersonTotals = null;
/*      */   
/*  327 */   protected double standardizedAllResponsesMean = NaN.0D;
/*  328 */   protected double standardizedAllResponsesStandardDeviation = NaN.0D;
/*  329 */   protected double standardizedAllResponsesVariance = NaN.0D;
/*  330 */   protected double standardizedAllResponsesMinimum = NaN.0D;
/*  331 */   protected double standardizedAllResponsesMaximum = NaN.0D;
/*  332 */   protected double standardizedAllResponsesRange = NaN.0D;
/*  333 */   protected double standardizedAllResponsesTotal = NaN.0D;
/*      */   
/*  335 */   protected double[][] standardizedCovariances = (double[][])null;
/*  336 */   protected double[][] standardizedCorrelationCoefficients = (double[][])null;
/*  337 */   protected double[] standardizedRhosWithTotal = null;
/*  338 */   protected double standardizedMeanRhoWithTotals = NaN.0D;
/*  339 */   protected double standardizedStandardDeviationRhoWithTotals = NaN.0D;
/*  340 */   protected double standardizedMeanRhoWithoutTotals = NaN.0D;
/*  341 */   protected double standardizedStandardDeviationRhoWithoutTotals = NaN.0D;
/*      */   
/*  343 */   protected boolean variancesCalculated = false;
/*  344 */   protected boolean covariancesCalculated = false;
/*      */   
/*  346 */   protected boolean nFactorOption = false;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void enterTitle(String title)
/*      */   {
/*  357 */     if (this.title == null) {
/*  358 */       this.title = new String[2];
/*  359 */       this.title[0] = ("Title: " + title);
/*  360 */       Date d = new Date();
/*  361 */       String day = DateFormat.getDateInstance().format(d);
/*  362 */       String tim = DateFormat.getTimeInstance().format(d);
/*  363 */       this.title[1] = ("Program execution initiated at " + tim + " on " + day);
/*      */     }
/*      */     else {
/*  366 */       this.title[0] = title;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setPersonDeletionPercentage(double perCent)
/*      */   {
/*  373 */     this.personDeletionPercentage = perCent;
/*  374 */     this.personDeletionPercentageSet = true;
/*  375 */     if ((this.itemDeletionPercentageSet) && (this.replacementOptionSet)) {
/*  376 */       this.allNoResponseOptionsSet = true;
/*  377 */       if (this.dataEntered) {
/*  378 */         preprocessData();
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void setItemDeletionPercentage(double perCent)
/*      */   {
/*  385 */     this.itemDeletionPercentage = perCent;
/*  386 */     this.itemDeletionPercentageSet = true;
/*  387 */     if ((this.personDeletionPercentageSet) && (this.replacementOptionSet)) {
/*  388 */       this.allNoResponseOptionsSet = true;
/*  389 */       if (this.dataEntered) {
/*  390 */         preprocessData();
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMissingDataOption(int option)
/*      */   {
/*  404 */     if ((option < 1) || (option > 5)) throw new IllegalArgumentException("The missing response option entered is " + option + "; the option must be 1, 2, 3, 4 or 5");
/*  405 */     this.replacementOption = option;
/*  406 */     this.replacementOptionSet = true;
/*  407 */     if ((this.personDeletionPercentageSet) && (this.itemDeletionPercentageSet)) {
/*  408 */       this.allNoResponseOptionsSet = true;
/*  409 */       if (this.dataEntered) {
/*  410 */         preprocessData();
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void ignoreMissingDataOptionRequests()
/*      */   {
/*  417 */     this.ignoreNoResponseRequests = true;
/*  418 */     this.allNoResponseOptionsSet = true;
/*  419 */     this.itemDeletionPercentageSet = true;
/*  420 */     this.personDeletionPercentageSet = true;
/*  421 */     this.allNoResponseOptionsSet = true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void noResponseHandling()
/*      */   {
/*  429 */     if ((this.nNaN > 0) && (!this.noResponseHandlingSet))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*  434 */       this.nDeletedPersons = 0;
/*      */       
/*  436 */       for (int j = 0; j < this.nPersons; j++) {
/*  437 */         int nIndNaN = 0;
/*  438 */         this.deletedPersons[j] = false;
/*  439 */         for (int i = 0; i < this.nItems; i++) {
/*  440 */           if (Double.isNaN(this.scores0[i][j])) {
/*  441 */             nIndNaN++;
/*      */           }
/*      */         }
/*  444 */         if (nIndNaN == this.nItems) {
/*  445 */           this.deletedPersons[j] = true;
/*      */         }
/*      */       }
/*      */       
/*  449 */       for (int i = 0; i < this.nPersons; i++) {
/*  450 */         if (this.deletedPersons[i] == 0) {
/*  451 */           int deletedSum = 0;
/*  452 */           for (int j = 0; j < this.nItems; j++) {
/*  453 */             if (Double.isNaN(this.scores0[j][i])) {
/*  454 */               deletedSum++;
/*  455 */               double pc = deletedSum * 100.0D / this.nItems;
/*  456 */               if (pc > this.personDeletionPercentage) {
/*  457 */                 this.deletedPersons[i] = true;
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */       
/*  464 */       for (int i = 0; i < this.nPersons; i++) if (this.deletedPersons[i] != 0) this.nDeletedPersons += 1;
/*  465 */       if (this.nDeletedPersons > 0) {
/*  466 */         int counter = 0;
/*  467 */         this.deletedPersonsIndices = new int[this.nDeletedPersons];
/*  468 */         for (int j = 0; j < this.nPersons; j++) {
/*  469 */           if (this.deletedPersons[j] != 0) {
/*  470 */             this.deletedPersonsIndices[counter] = j;
/*  471 */             counter++;
/*      */           }
/*      */         }
/*  474 */         double[][] scoreTemp = new double[this.nItems][this.nPersons - this.nDeletedPersons];
/*  475 */         this.personIndices = new int[this.nPersons - this.nDeletedPersons];
/*  476 */         counter = 0;
/*  477 */         for (int i = 0; i < this.nPersons; i++) {
/*  478 */           if (this.deletedPersons[i] == 0) {
/*  479 */             for (int j = 0; j < this.nItems; j++) {
/*  480 */               scoreTemp[j][counter] = this.scores0[j][i];
/*      */             }
/*  482 */             this.personIndices[counter] = i;
/*  483 */             counter++;
/*      */           }
/*      */         }
/*  486 */         this.nPersons -= this.nDeletedPersons;
/*  487 */         this.nScores = (this.nPersons * this.nItems);
/*  488 */         this.scores0 = scoreTemp;
/*      */       }
/*  490 */       if (this.nDeletedPersons == 0) {
/*  491 */         this.personIndices = new int[this.nPersons];
/*  492 */         for (int i = 0; i < this.nPersons; i++) { this.personIndices[i] = i;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  498 */       this.deletedItems = new boolean[this.nItems];
/*  499 */       this.nDeletedItems = 0;
/*  500 */       for (int i = 0; i < this.nItems; i++) {
/*  501 */         int nItemNaN = 0;
/*  502 */         this.deletedItems[i] = false;
/*  503 */         for (int j = 0; j < this.nPersons; j++) {
/*  504 */           if (Double.isNaN(this.scores0[i][j])) nItemNaN++;
/*      */         }
/*  506 */         if (nItemNaN == this.nPersons) {
/*  507 */           this.deletedItems[i] = true;
/*      */         }
/*      */       }
/*      */       
/*  511 */       for (int i = 0; i < this.nItems; i++) {
/*  512 */         this.deletedItems[i] = false;
/*  513 */         int deletedSum = 0;
/*  514 */         for (int j = 0; j < this.nPersons; j++) {
/*  515 */           if (Double.isNaN(this.scores0[i][j])) {
/*  516 */             deletedSum++;
/*  517 */             double pc = deletedSum * 100.0D / this.nPersons;
/*  518 */             if (pc > this.itemDeletionPercentage) {
/*  519 */               this.deletedItems[i] = true;
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*  524 */       for (int i = 0; i < this.nItems; i++) if (this.deletedItems[i] != 0) { this.nDeletedItems += 1;
/*      */         }
/*  526 */       if (this.nDeletedItems > 0)
/*      */       {
/*  528 */         int counter = 0;
/*  529 */         this.deletedItemsIndices = new int[this.nDeletedItems];
/*  530 */         for (int i = 0; i < this.nItems; i++) {
/*  531 */           if (this.deletedItems[i] != 0) {
/*  532 */             this.deletedItemsIndices[counter] = i;
/*  533 */             counter++;
/*      */           }
/*      */         }
/*      */         
/*  537 */         if (this.nItems - this.nDeletedItems <= 1) throw new IllegalArgumentException("You have deleted " + this.nDeletedItems + " items leaving " + (this.nItems - this.nDeletedItems) + " items and hence no possibility calculation of alpha");
/*  538 */         double[][] scoreTemp = new double[this.nItems - this.nDeletedItems][this.nPersons];
/*  539 */         String[] nameTemp = new String[this.nItems - this.nDeletedItems];
/*  540 */         this.itemIndices = new int[this.nItems - this.nDeletedItems];
/*  541 */         counter = 0;
/*  542 */         for (int i = 0; i < this.nItems; i++) {
/*  543 */           if (this.deletedItems[i] == 0) {
/*  544 */             nameTemp[counter] = this.itemNames[i];
/*  545 */             for (int j = 0; j < this.nPersons; j++) {
/*  546 */               scoreTemp[counter][j] = this.scores0[i][j];
/*      */             }
/*  548 */             this.itemIndices[counter] = i;
/*  549 */             counter++;
/*      */           }
/*      */         }
/*  552 */         this.nItems -= this.nDeletedItems;
/*  553 */         this.nScores = (this.nPersons * this.nItems);
/*  554 */         this.scores0 = scoreTemp;
/*  555 */         this.itemNames = nameTemp;
/*      */       }
/*  557 */       if (this.nDeletedItems == 0) {
/*  558 */         this.itemIndices = new int[this.nItems];
/*  559 */         for (int i = 0; i < this.nItems; i++) {
/*  560 */           this.itemIndices[i] = i;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  566 */       int newNaNn = 0;
/*  567 */       for (int i = 0; i < this.nPersons; i++) {
/*  568 */         for (int j = 0; j < this.nItems; j++) {
/*  569 */           if (!Double.isNaN(this.scores0[j][i])) {
/*  570 */             newNaNn++;
/*      */           }
/*      */         }
/*      */       }
/*      */       
/*  575 */       if (newNaNn > 0)
/*      */       {
/*  577 */         double[] tItemMeans = new double[this.nItems];
/*  578 */         double tTotalMean = 0.0D;
/*  579 */         int counter2 = 0;
/*  580 */         for (int i = 0; i < this.nItems; i++) {
/*  581 */           tItemMeans[i] = 0.0D;
/*  582 */           int counter = 0;
/*  583 */           for (int j = 0; j < this.nPersons; j++) {
/*  584 */             if (!Double.isNaN(this.scores0[i][j])) {
/*  585 */               tItemMeans[i] += this.scores0[i][j];
/*  586 */               counter++;
/*  587 */               tTotalMean += this.scores0[i][j];
/*  588 */               counter2++;
/*      */             }
/*      */           }
/*  591 */           tItemMeans[i] /= counter;
/*  592 */           tTotalMean /= counter2;
/*      */         }
/*      */         
/*      */ 
/*  596 */         double[] tIndivMeans = new double[this.nPersons];
/*  597 */         for (int i = 0; i < this.nPersons; i++) {
/*  598 */           tIndivMeans[i] = 0.0D;
/*  599 */           int counter = 0;
/*  600 */           for (int j = 0; j < this.nItems; j++) {
/*  601 */             if (!Double.isNaN(this.scores0[j][i])) {
/*  602 */               tIndivMeans[i] += this.scores0[j][i];
/*  603 */               counter++;
/*      */             }
/*      */           }
/*  606 */           tIndivMeans[i] /= counter;
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*  612 */         this.replacementIndices = new String[newNaNn];
/*  613 */         int rcounter = 0;
/*  614 */         switch (this.replacementOption) {
/*      */         case 1: 
/*  616 */           for (int i = 0; i < this.nItems; i++) {
/*  617 */             for (int j = 0; j < this.nPersons; j++) {
/*  618 */               if (Double.isNaN(this.scores0[i][j])) {
/*  619 */                 this.scores0[i][j] = 0.0D;
/*  620 */                 this.replacementIndices[rcounter] = (this.itemNames[i] + ", " + (j + 1) + ";");
/*  621 */                 rcounter++;
/*      */               }
/*      */             }
/*      */           }
/*  625 */           break;
/*      */         case 2: 
/*  627 */           for (int i = 0; i < this.nItems; i++) {
/*  628 */             for (int j = 0; j < this.nPersons; j++) {
/*  629 */               if (Double.isNaN(this.scores0[i][j])) {
/*  630 */                 this.scores0[i][j] = tIndivMeans[i];
/*  631 */                 this.replacementIndices[rcounter] = (this.itemNames[i] + ", " + (j + 1) + ";");
/*  632 */                 rcounter++;
/*      */               }
/*      */             }
/*      */           }
/*  636 */           break;
/*      */         case 3: 
/*  638 */           for (int i = 0; i < this.nItems; i++) {
/*  639 */             for (int j = 0; j < this.nPersons; j++) {
/*  640 */               if (Double.isNaN(this.scores0[i][j])) {
/*  641 */                 this.scores0[i][j] = tItemMeans[i];
/*  642 */                 this.replacementIndices[rcounter] = (this.itemNames[i] + ", " + (j + 1) + ";");
/*  643 */                 rcounter++;
/*      */               }
/*      */             }
/*      */           }
/*  647 */           break;
/*      */         case 4: 
/*  649 */           for (int i = 0; i < this.nItems; i++) {
/*  650 */             for (int j = 0; j < this.nPersons; j++) {
/*  651 */               if (Double.isNaN(this.scores0[i][j])) {
/*  652 */                 this.scores0[i][j] = tTotalMean;
/*  653 */                 this.replacementIndices[rcounter] = (this.itemNames[i] + ", " + (j + 1) + ";");
/*  654 */                 rcounter++;
/*      */               }
/*      */             }
/*      */           }
/*  658 */           break;
/*      */         case 5: 
/*  660 */           for (int i = 0; i < this.nItems; i++) {
/*  661 */             for (int j = 0; j < this.nPersons; j++) {
/*  662 */               if (Double.isNaN(this.scores0[i][j])) {
/*  663 */                 String message1 = "Missing response:";
/*  664 */                 String message2 = "\nItem index = " + i + ",    item mean = " + Fmath.truncate(tItemMeans[i], 4);
/*  665 */                 String message3 = "\nPerson index = " + j + ",    person's responses mean = " + Fmath.truncate(tIndivMeans[j], 4);
/*  666 */                 String message4 = "\nTotal mean = " + Fmath.truncate(tTotalMean, 4);
/*  667 */                 String message5 = "\nEnter the replacement value";
/*  668 */                 String message = message1 + message2 + message3 + message4 + message5;
/*  669 */                 this.scores0[i][j] = Db.readDouble(message);
/*  670 */                 this.replacementIndices[rcounter] = (this.itemNames[i] + ", " + (j + 1) + ";");
/*  671 */                 rcounter++;
/*      */               }
/*      */             }
/*      */           }
/*  675 */           break;
/*  676 */         default:  throw new IllegalArgumentException("!! It should not be possible to have an option choice (replacementOption) = " + this.replacementOption);
/*      */         }
/*      */       }
/*      */     }
/*  680 */     this.noResponseHandlingSet = true;
/*      */   }
/*      */   
/*      */ 
/*      */   protected void noResponseRequests()
/*      */   {
/*  686 */     if (!this.allNoResponseOptionsSet) {
/*  687 */       if (!this.ignoreNoResponseRequests)
/*      */       {
/*      */ 
/*  690 */         if ((this.personDeletionPercentage != 0.0D) && 
/*  691 */           (!this.itemDeletionPercentageSet)) {
/*  692 */           String message0 = "There are missing responses in this data set";
/*  693 */           String message1 = "\nYou have not set the percentage of no responses at which you will delete an item";
/*  694 */           String message2 = "\n(0% = item deleted if a single 'no response' present in the item)";
/*  695 */           String message3 = "\n(100% = item never deleted)";
/*  696 */           String message4 = "\nEnter the required value and click OK ";
/*  697 */           String message5 = "\nor simply click OK for default value";
/*  698 */           String message6 = message0 + message1 + message2 + message3 + message4 + message5;
/*  699 */           this.itemDeletionPercentage = Db.readDouble(message6, this.itemDeletionPercentage);
/*      */         }
/*      */         
/*  702 */         this.itemDeletionPercentageSet = true;
/*      */         
/*  704 */         if (this.itemDeletionPercentage != 0.0D)
/*      */         {
/*  706 */           if (!this.personDeletionPercentageSet) {
/*  707 */             String message0 = "There are missing responses in this data set";
/*  708 */             String message1 = "\nYou have not set the percentage of no responses at which you will delete a person";
/*  709 */             String message2 = "\n(0% = person deleted if gives a single 'no response')";
/*  710 */             String message3 = "\n(100% = person never deleted)";
/*  711 */             String message4 = "\nEnter the required value and click OK ";
/*  712 */             String message5 = "\nor simply click OK for default value";
/*  713 */             String message6 = message0 + message1 + message2 + message3 + message4 + message5;
/*  714 */             this.personDeletionPercentage = Db.readDouble(message6, this.personDeletionPercentage);
/*      */           }
/*      */         }
/*  717 */         this.personDeletionPercentageSet = true;
/*      */         
/*  719 */         if ((this.itemDeletionPercentage != 0.0D) && (this.personDeletionPercentage != 0.0D))
/*      */         {
/*  721 */           if (!this.replacementOptionSet) {
/*  722 */             String message0 = "There are missing responses in this data set";
/*  723 */             String message1 = "\nYou have not set the option flag for replacing a missing score";
/*  724 */             String message2 = "\n  option = 1 - score replaced by zero";
/*  725 */             String message3 = "\n  option = 2 - score replaced by person's mean";
/*  726 */             String message4 = "\n  option = 3 - score replaced by item mean (default option)";
/*  727 */             String message5 = "\n  option = 4 - score replaced by overall mean";
/*  728 */             String message6 = "\n  option = 5 - user supplied score for each 'no response'";
/*  729 */             String message7 = "\nEnter the required value and click OK ";
/*  730 */             String message8 = "\nor simply click OK for default value";
/*  731 */             String message9 = message0 + message1 + message2 + message3 + message4 + message5 + message6 + message7 + message8;
/*  732 */             this.replacementOption = Db.readInt(message9, this.replacementOption);
/*      */           }
/*      */         }
/*  735 */         this.replacementOptionSet = true;
/*      */       }
/*  737 */       this.allNoResponseOptionsSet = true;
/*      */     }
/*      */   }
/*      */   
/*      */   public void setDenominatorToN()
/*      */   {
/*  743 */     this.nFactorOption = true;
/*      */   }
/*      */   
/*      */   public void setDenominatorToNminusOne()
/*      */   {
/*  748 */     this.nFactorOption = false;
/*      */   }
/*      */   
/*      */   public int[] deletedPersonsIndices()
/*      */   {
/*  753 */     if (!this.dataPreprocessed) preprocessData();
/*  754 */     if (this.nDeletedPersons == 0) {
/*  755 */       System.out.println("Method - deletedPersonsIndices: there are no deleted persons; null returned");
/*  756 */       return null;
/*      */     }
/*      */     
/*  759 */     ArrayMaths am1 = new ArrayMaths(this.deletedPersonsIndices);
/*  760 */     ArrayMaths am2 = am1.plus(1);
/*  761 */     return am2.array_as_int();
/*      */   }
/*      */   
/*      */ 
/*      */   public int numberOfDeletedPersons()
/*      */   {
/*  767 */     if (!this.dataPreprocessed) preprocessData();
/*  768 */     return this.nDeletedPersons;
/*      */   }
/*      */   
/*      */   public int[] deletedItemsIndices()
/*      */   {
/*  773 */     if (!this.dataPreprocessed) preprocessData();
/*  774 */     if (this.nDeletedItems == 0) {
/*  775 */       System.out.println("Method - deletedItemsIndices: there are no deleted items; null returned");
/*  776 */       return null;
/*      */     }
/*      */     
/*  779 */     ArrayMaths am1 = new ArrayMaths(this.deletedItemsIndices);
/*  780 */     ArrayMaths am2 = am1.plus(1);
/*  781 */     return am2.array_as_int();
/*      */   }
/*      */   
/*      */ 
/*      */   public String[] deletedItemsNames()
/*      */   {
/*  787 */     if (!this.dataPreprocessed) preprocessData();
/*  788 */     if (this.nDeletedItems == 0) {
/*  789 */       System.out.println("Method - deletedItemsIndices: there are no deleted items; null returned");
/*  790 */       return null;
/*      */     }
/*      */     
/*  793 */     String[] nam = new String[this.nDeletedItems];
/*  794 */     for (int i = 0; i < this.nDeletedItems; i++) {
/*  795 */       nam[i] = this.originalItemNames[this.deletedItemsIndices[i]];
/*      */     }
/*  797 */     return nam;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int numberOfDeletedItems()
/*      */   {
/*  804 */     if (!this.dataPreprocessed) preprocessData();
/*  805 */     return this.nDeletedItems;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void readScoresAsRowPerItem()
/*      */   {
/*  820 */     int lineNumber = 1;
/*  821 */     FileChooser fin = new FileChooser("C:\\Java6\\Scores");
/*      */     
/*      */ 
/*  824 */     this.inputFilename = fin.selectFile();
/*  825 */     if (fin.eol()) { lineNumber++;
/*      */     }
/*      */     
/*  828 */     this.title = new String[3];
/*  829 */     this.titleLines = 3;
/*  830 */     this.title[0] = fin.readLine();
/*  831 */     this.title[1] = ("Data read from file: " + this.inputFilename);
/*  832 */     Date d = new Date();
/*  833 */     String day = DateFormat.getDateInstance().format(d);
/*  834 */     String tim = DateFormat.getTimeInstance().format(d);
/*  835 */     this.title[2] = ("Program execution initiated at " + tim + " on " + day);
/*      */     
/*      */ 
/*  838 */     this.nItems = fin.readInt();
/*  839 */     if (fin.eol()) { lineNumber++;
/*      */     }
/*      */     
/*  842 */     this.nPersons = fin.readInt();
/*  843 */     if (fin.eol()) lineNumber++;
/*  844 */     this.nScores = (this.nItems * this.nPersons);
/*      */     
/*      */ 
/*  847 */     this.itemNames = new String[this.nItems + 1];
/*  848 */     for (int i = 0; i < this.nItems; i++) {
/*  849 */       this.itemNames[i] = fin.readWord();
/*  850 */       if (fin.eol()) lineNumber++;
/*      */     }
/*  852 */     this.itemNames[this.nItems] = "total";
/*  853 */     this.originalItemNames = this.itemNames;
/*  854 */     this.itemNamesSet = true;
/*      */     
/*      */ 
/*  857 */     String[][] scores = new String[this.nItems][this.nPersons];
/*  858 */     for (int i = 0; i < this.nItems; i++) {
/*  859 */       int wordsPerLine = 1;
/*  860 */       for (int j = 0; j < this.nPersons; j++) {
/*  861 */         scores[i][j] = fin.readWord();
/*  862 */         if (fin.eol()) {
/*  863 */           if (wordsPerLine != this.nPersons) throw new IllegalArgumentException("Line " + lineNumber + ": the number of scores in this row, " + wordsPerLine + ", does not equal the total number of persons, " + this.nPersons);
/*  864 */           lineNumber++;
/*      */         }
/*      */         else {
/*  867 */           wordsPerLine++;
/*      */         }
/*      */       }
/*      */     }
/*  871 */     fin.close();
/*      */     
/*      */ 
/*  874 */     this.originalData = scores;
/*  875 */     this.originalDataType = 1;
/*  876 */     this.originalDataOrder = 0;
/*  877 */     this.dataEntered = true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void readScoresAsRowPerItem(String filename)
/*      */   {
/*  891 */     int lineNumber = 1;
/*  892 */     this.inputFilename = filename;
/*  893 */     FileInput fin = new FileInput(filename);
/*  894 */     if (fin.eol()) { lineNumber++;
/*      */     }
/*      */     
/*  897 */     this.title = new String[3];
/*  898 */     this.titleLines = 3;
/*  899 */     this.title[0] = fin.readLine();
/*  900 */     this.title[1] = ("Data read from file: " + filename);
/*  901 */     Date d = new Date();
/*  902 */     String day = DateFormat.getDateInstance().format(d);
/*  903 */     String tim = DateFormat.getTimeInstance().format(d);
/*  904 */     this.title[2] = ("Program execution initiated at " + tim + " on " + day);
/*      */     
/*      */ 
/*  907 */     this.nItems = fin.readInt();
/*  908 */     if (fin.eol()) { lineNumber++;
/*      */     }
/*      */     
/*  911 */     this.nPersons = fin.readInt();
/*  912 */     if (fin.eol()) lineNumber++;
/*  913 */     this.nScores = (this.nItems * this.nPersons);
/*      */     
/*      */ 
/*  916 */     this.itemNames = new String[this.nItems + 1];
/*  917 */     for (int i = 0; i < this.nItems; i++) {
/*  918 */       this.itemNames[i] = fin.readWord();
/*  919 */       if (fin.eol()) { lineNumber++;
/*      */       }
/*      */     }
/*  922 */     this.itemNames[this.nItems] = "total";
/*  923 */     this.originalItemNames = this.itemNames;
/*  924 */     this.itemNamesSet = true;
/*      */     
/*      */ 
/*  927 */     String[][] scores = new String[this.nItems][this.nPersons];
/*  928 */     for (int i = 0; i < this.nPersons; i++) {
/*  929 */       int wordsPerLine = 1;
/*  930 */       for (int j = 0; j < this.nItems; j++) {
/*  931 */         scores[j][i] = fin.readWord();
/*  932 */         if (fin.eol()) {
/*  933 */           if (wordsPerLine != this.nItems) throw new IllegalArgumentException("Line " + lineNumber + ": the number of scores in this row, " + wordsPerLine + ", does not equal the total number of items, " + this.nItems);
/*  934 */           lineNumber++;
/*      */         }
/*      */         else {
/*  937 */           wordsPerLine++;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  942 */     fin.close();
/*      */     
/*      */ 
/*  945 */     this.originalData = scores;
/*  946 */     this.originalDataType = 1;
/*  947 */     this.originalDataOrder = 0;
/*  948 */     this.dataEntered = true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void enterScoresAsRowPerItem(String[][] scores)
/*      */   {
/*  960 */     this.nItems = scores.length;
/*  961 */     this.nPersons = scores[0].length;
/*  962 */     this.nScores = (this.nItems * this.nPersons);
/*      */     
/*      */ 
/*  965 */     if (this.title == null) {
/*  966 */       this.title = new String[2];
/*  967 */       this.title[0] = "Untitled Scores Analysis";
/*  968 */       Date d = new Date();
/*  969 */       String day = DateFormat.getDateInstance().format(d);
/*  970 */       String tim = DateFormat.getTimeInstance().format(d);
/*  971 */       this.title[1] = ("Program execution initiated at " + tim + " on " + day);
/*      */     }
/*      */     
/*      */ 
/*  975 */     this.originalData = scores;
/*  976 */     this.originalDataType = 1;
/*  977 */     this.originalDataOrder = 0;
/*  978 */     this.dataEntered = true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void enterScoresAsRowPerItem(double[][] scores)
/*      */   {
/*  990 */     this.nItems = scores.length;
/*  991 */     this.nPersons = scores[0].length;
/*  992 */     this.nScores = (this.nItems * this.nPersons);
/*      */     
/*      */ 
/*  995 */     if (this.title == null) {
/*  996 */       this.title = new String[2];
/*  997 */       this.title[0] = "Untitled";
/*  998 */       Date d = new Date();
/*  999 */       String day = DateFormat.getDateInstance().format(d);
/* 1000 */       String tim = DateFormat.getTimeInstance().format(d);
/* 1001 */       this.title[1] = ("Program execution initiated at " + tim + " on " + day);
/*      */     }
/*      */     
/*      */ 
/* 1005 */     this.originalData = scores;
/* 1006 */     this.originalDataType = 2;
/* 1007 */     this.originalDataOrder = 0;
/* 1008 */     this.dataEntered = true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void enterScoresAsRowPerItem(Matrix scores)
/*      */   {
/* 1018 */     double[][] scoresdd = scores.getArrayCopy();
/*      */     
/*      */ 
/* 1021 */     this.nItems = scoresdd.length;
/* 1022 */     this.nPersons = scoresdd[0].length;
/* 1023 */     this.nScores = (this.nItems * this.nPersons);
/*      */     
/*      */ 
/* 1026 */     if (this.title == null) {
/* 1027 */       this.title = new String[2];
/* 1028 */       this.title[0] = "Untitled Scores Analysis";
/* 1029 */       Date d = new Date();
/* 1030 */       String day = DateFormat.getDateInstance().format(d);
/* 1031 */       String tim = DateFormat.getTimeInstance().format(d);
/* 1032 */       this.title[1] = ("Program execution initiated at " + tim + " on " + day);
/*      */     }
/*      */     
/*      */ 
/* 1036 */     this.originalData = scores;
/* 1037 */     this.originalDataType = 3;
/* 1038 */     this.originalDataOrder = 0;
/* 1039 */     this.dataEntered = true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void enterScoresAsRowPerItem(float[][] scores)
/*      */   {
/* 1052 */     this.nItems = scores.length;
/* 1053 */     this.nPersons = scores[0].length;
/* 1054 */     this.nScores = (this.nItems * this.nPersons);
/*      */     
/*      */ 
/* 1057 */     if (this.title == null) {
/* 1058 */       this.title = new String[2];
/* 1059 */       this.title[0] = "Untitled Scores Analysis";
/* 1060 */       Date d = new Date();
/* 1061 */       String day = DateFormat.getDateInstance().format(d);
/* 1062 */       String tim = DateFormat.getTimeInstance().format(d);
/* 1063 */       this.title[1] = ("Program execution initiated at " + tim + " on " + day);
/*      */     }
/*      */     
/*      */ 
/* 1067 */     this.originalData = scores;
/* 1068 */     this.originalDataType = 4;
/* 1069 */     this.originalDataOrder = 0;
/* 1070 */     this.dataEntered = true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void enterScoresAsRowPerItem(int[][] scores)
/*      */   {
/* 1082 */     this.nItems = scores.length;
/* 1083 */     this.nPersons = scores[0].length;
/* 1084 */     this.nScores = (this.nItems * this.nPersons);
/*      */     
/*      */ 
/* 1087 */     if (this.title == null) {
/* 1088 */       this.title = new String[2];
/* 1089 */       this.title[0] = "Untitled Scores Analysis";
/* 1090 */       Date d = new Date();
/* 1091 */       String day = DateFormat.getDateInstance().format(d);
/* 1092 */       String tim = DateFormat.getTimeInstance().format(d);
/* 1093 */       this.title[1] = ("Program execution initiated at " + tim + " on " + day);
/*      */     }
/*      */     
/*      */ 
/* 1097 */     this.originalData = scores;
/* 1098 */     this.originalDataType = 5;
/* 1099 */     this.originalDataOrder = 0;
/* 1100 */     this.dataEntered = true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void enterScoresAsRowPerItem(char[][] scores)
/*      */   {
/* 1112 */     this.nItems = scores.length;
/* 1113 */     this.nPersons = scores[0].length;
/* 1114 */     this.nScores = (this.nItems * this.nPersons);
/*      */     
/*      */ 
/* 1117 */     if (this.title == null) {
/* 1118 */       this.title = new String[2];
/* 1119 */       this.title[0] = "Untitled Scores Analysis";
/* 1120 */       Date d = new Date();
/* 1121 */       String day = DateFormat.getDateInstance().format(d);
/* 1122 */       String tim = DateFormat.getTimeInstance().format(d);
/* 1123 */       this.title[1] = ("Program execution initiated at " + tim + " on " + day);
/*      */     }
/*      */     
/*      */ 
/* 1127 */     this.originalData = scores;
/* 1128 */     this.originalDataType = 6;
/* 1129 */     this.originalDataOrder = 0;
/* 1130 */     this.dataEntered = true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void enterScoresAsRowPerItem(boolean[][] scores)
/*      */   {
/* 1142 */     this.nItems = scores.length;
/* 1143 */     this.nPersons = scores[0].length;
/* 1144 */     this.nScores = (this.nItems * this.nPersons);
/* 1145 */     this.dichotomous = new boolean[this.nItems];
/* 1146 */     this.dichotomousPercentage = new double[this.nItems];
/* 1147 */     for (int i = 0; i < this.nItems; i++) {
/* 1148 */       this.dichotomous[i] = true;
/* 1149 */       this.dichotomousPercentage[i] = 100.0D;
/*      */     }
/* 1151 */     this.dichotomousOverall = true;
/* 1152 */     this.dichotomousCheckDone = true;
/*      */     
/*      */ 
/* 1155 */     if (this.title == null) {
/* 1156 */       this.title = new String[2];
/* 1157 */       this.title[0] = "Untitled Scores Analysis";
/* 1158 */       Date d = new Date();
/* 1159 */       String day = DateFormat.getDateInstance().format(d);
/* 1160 */       String tim = DateFormat.getTimeInstance().format(d);
/* 1161 */       this.title[1] = ("Program execution initiated at " + tim + " on " + day);
/*      */     }
/*      */     
/*      */ 
/* 1165 */     this.originalData = scores;
/* 1166 */     this.originalDataType = 7;
/* 1167 */     this.originalDataOrder = 0;
/* 1168 */     this.dataEntered = true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void readScoresAsRowPerPerson()
/*      */   {
/* 1182 */     int lineNumber = 1;
/* 1183 */     FileChooser fin = new FileChooser("C:\\Java6\\Scores");
/*      */     
/*      */ 
/* 1186 */     this.inputFilename = fin.selectFile();
/* 1187 */     if (fin.eol()) { lineNumber++;
/*      */     }
/*      */     
/* 1190 */     this.title = new String[3];
/* 1191 */     this.titleLines = 3;
/* 1192 */     this.title[0] = ("Title: " + fin.readLine());
/* 1193 */     this.title[1] = ("Data read from file: " + this.inputFilename);
/* 1194 */     Date d = new Date();
/* 1195 */     String day = DateFormat.getDateInstance().format(d);
/* 1196 */     String tim = DateFormat.getTimeInstance().format(d);
/* 1197 */     this.title[2] = ("Program execution initiated at " + tim + " on " + day);
/*      */     
/*      */ 
/* 1200 */     this.nItems = fin.readInt();
/* 1201 */     if (fin.eol()) { lineNumber++;
/*      */     }
/*      */     
/* 1204 */     this.nPersons = fin.readInt();
/* 1205 */     if (fin.eol()) lineNumber++;
/* 1206 */     this.nScores = (this.nItems * this.nPersons);
/*      */     
/*      */ 
/* 1209 */     this.itemNames = new String[this.nItems + 1];
/* 1210 */     for (int i = 0; i < this.nItems; i++) {
/* 1211 */       this.itemNames[i] = fin.readWord();
/* 1212 */       if (fin.eol()) lineNumber++;
/*      */     }
/* 1214 */     this.itemNames[this.nItems] = "total";
/* 1215 */     this.originalItemNames = this.itemNames;
/* 1216 */     this.itemNamesSet = true;
/*      */     
/*      */ 
/* 1219 */     String[][] scores = new String[this.nPersons][this.nItems];
/* 1220 */     for (int i = 0; i < this.nPersons; i++) {
/* 1221 */       int wordsPerLine = 1;
/* 1222 */       for (int j = 0; j < this.nItems; j++) {
/* 1223 */         scores[i][j] = fin.readWord();
/* 1224 */         if (fin.eol()) {
/* 1225 */           if (wordsPerLine != this.nItems) throw new IllegalArgumentException("Line " + lineNumber + ": the number of scores in this row, " + wordsPerLine + ", does not equal the total number of items, " + this.nItems);
/* 1226 */           lineNumber++;
/*      */         }
/*      */         else {
/* 1229 */           wordsPerLine++;
/*      */         }
/*      */       }
/*      */     }
/* 1233 */     fin.close();
/*      */     
/*      */ 
/* 1236 */     this.originalData = scores;
/* 1237 */     this.originalDataType = 1;
/* 1238 */     this.originalDataOrder = 1;
/* 1239 */     this.dataEntered = true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void readScoresAsRowPerPerson(String filename)
/*      */   {
/* 1253 */     int lineNumber = 1;
/* 1254 */     this.inputFilename = filename;
/* 1255 */     FileInput fin = new FileInput(filename);
/* 1256 */     if (fin.eol()) { lineNumber++;
/*      */     }
/*      */     
/* 1259 */     this.title = new String[3];
/* 1260 */     this.titleLines = 3;
/* 1261 */     this.title[0] = ("Title: " + fin.readLine());
/* 1262 */     this.title[1] = ("Data read from file: " + filename);
/* 1263 */     Date d = new Date();
/* 1264 */     String day = DateFormat.getDateInstance().format(d);
/* 1265 */     String tim = DateFormat.getTimeInstance().format(d);
/* 1266 */     this.title[2] = ("Program execution initiated at " + tim + " on " + day);
/*      */     
/*      */ 
/* 1269 */     this.nItems = fin.readInt();
/* 1270 */     if (fin.eol()) { lineNumber++;
/*      */     }
/*      */     
/* 1273 */     this.nPersons = fin.readInt();
/* 1274 */     if (fin.eol()) lineNumber++;
/* 1275 */     this.nScores = (this.nItems * this.nPersons);
/*      */     
/*      */ 
/* 1278 */     this.itemNames = new String[this.nItems + 1];
/* 1279 */     for (int i = 0; i < this.nItems; i++) {
/* 1280 */       this.itemNames[i] = fin.readWord();
/* 1281 */       if (fin.eol()) lineNumber++;
/*      */     }
/* 1283 */     this.itemNames[this.nItems] = "total";
/* 1284 */     this.originalItemNames = this.itemNames;
/* 1285 */     this.itemNamesSet = true;
/*      */     
/*      */ 
/* 1288 */     String[][] scores = new String[this.nPersons][this.nItems];
/* 1289 */     for (int i = 0; i < this.nPersons; i++) {
/* 1290 */       int wordsPerLine = 1;
/* 1291 */       for (int j = 0; j < this.nItems; j++) {
/* 1292 */         scores[i][j] = fin.readWord();
/* 1293 */         if (fin.eol()) {
/* 1294 */           if (wordsPerLine != this.nItems) throw new IllegalArgumentException("Line " + lineNumber + ": the number of scores in this row, " + wordsPerLine + ", does not equal the total number of items, " + this.nItems);
/* 1295 */           lineNumber++;
/*      */         }
/*      */         else {
/* 1298 */           wordsPerLine++;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1303 */     fin.close();
/*      */     
/*      */ 
/* 1306 */     this.originalData = scores;
/* 1307 */     this.originalDataType = 1;
/* 1308 */     this.originalDataOrder = 1;
/* 1309 */     this.dataEntered = true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void enterScoresAsRowPerIperson(String[][] scores)
/*      */   {
/* 1322 */     this.nPersons = scores.length;
/* 1323 */     this.nItems = scores[0].length;
/* 1324 */     this.nScores = (this.nItems * this.nPersons);
/*      */     
/*      */ 
/* 1327 */     if (this.title == null) {
/* 1328 */       this.title = new String[2];
/* 1329 */       this.title[0] = "Untitled Scores Analysis";
/* 1330 */       Date d = new Date();
/* 1331 */       String day = DateFormat.getDateInstance().format(d);
/* 1332 */       String tim = DateFormat.getTimeInstance().format(d);
/* 1333 */       this.title[1] = ("Program execution initiated at " + tim + " on " + day);
/*      */     }
/*      */     
/*      */ 
/* 1337 */     this.originalData = scores;
/* 1338 */     this.originalDataType = 2;
/* 1339 */     this.originalDataOrder = 1;
/* 1340 */     this.dataEntered = true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void enterScoresAsRowPerPerson(double[][] scores)
/*      */   {
/* 1352 */     this.nPersons = scores.length;
/* 1353 */     this.nItems = scores[0].length;
/* 1354 */     this.nScores = (this.nItems * this.nPersons);
/*      */     
/*      */ 
/* 1357 */     if (this.title == null) {
/* 1358 */       this.title = new String[2];
/* 1359 */       this.title[0] = "Untitled Scores Analysis";
/* 1360 */       Date d = new Date();
/* 1361 */       String day = DateFormat.getDateInstance().format(d);
/* 1362 */       String tim = DateFormat.getTimeInstance().format(d);
/* 1363 */       this.title[1] = ("Program execution initiated at " + tim + " on " + day);
/*      */     }
/*      */     
/*      */ 
/* 1367 */     this.originalData = scores;
/* 1368 */     this.originalDataType = 2;
/* 1369 */     this.originalDataOrder = 1;
/* 1370 */     this.dataEntered = true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void enterScoresAsRowPerPerson(Matrix scores)
/*      */   {
/* 1380 */     double[][] scoresdd = scores.getArrayCopy();
/*      */     
/*      */ 
/* 1383 */     this.nPersons = scoresdd.length;
/* 1384 */     this.nItems = scoresdd[0].length;
/* 1385 */     this.nScores = (this.nItems * this.nPersons);
/*      */     
/*      */ 
/* 1388 */     if (this.title == null) {
/* 1389 */       this.title = new String[2];
/* 1390 */       this.title[0] = "Untitled Scores Analysis";
/* 1391 */       Date d = new Date();
/* 1392 */       String day = DateFormat.getDateInstance().format(d);
/* 1393 */       String tim = DateFormat.getTimeInstance().format(d);
/* 1394 */       this.title[1] = ("Program execution initiated at " + tim + " on " + day);
/*      */     }
/*      */     
/*      */ 
/* 1398 */     this.originalData = scores;
/* 1399 */     this.originalDataType = 3;
/* 1400 */     this.originalDataOrder = 1;
/* 1401 */     this.dataEntered = true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void enterScoresAsRowPerPerson(float[][] scores)
/*      */   {
/* 1412 */     this.nPersons = scores.length;
/* 1413 */     this.nItems = scores[0].length;
/* 1414 */     this.nScores = (this.nItems * this.nPersons);
/*      */     
/*      */ 
/* 1417 */     if (this.title == null) {
/* 1418 */       this.title = new String[2];
/* 1419 */       this.title[0] = "Untitled Scores Analysis";
/* 1420 */       Date d = new Date();
/* 1421 */       String day = DateFormat.getDateInstance().format(d);
/* 1422 */       String tim = DateFormat.getTimeInstance().format(d);
/* 1423 */       this.title[1] = ("Program execution initiated at " + tim + " on " + day);
/*      */     }
/*      */     
/*      */ 
/* 1427 */     this.originalData = scores;
/* 1428 */     this.originalDataType = 4;
/* 1429 */     this.originalDataOrder = 1;
/* 1430 */     this.dataEntered = true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void enterScoresAsRowPerPerson(int[][] scores)
/*      */   {
/* 1442 */     this.nPersons = scores.length;
/* 1443 */     this.nItems = scores[0].length;
/* 1444 */     this.nScores = (this.nItems * this.nPersons);
/*      */     
/*      */ 
/* 1447 */     if (this.title == null) {
/* 1448 */       this.title = new String[2];
/* 1449 */       this.title[0] = "Untitled Scores Analysis";
/* 1450 */       Date d = new Date();
/* 1451 */       String day = DateFormat.getDateInstance().format(d);
/* 1452 */       String tim = DateFormat.getTimeInstance().format(d);
/* 1453 */       this.title[1] = ("Program execution initiated at " + tim + " on " + day);
/*      */     }
/*      */     
/*      */ 
/* 1457 */     this.originalData = scores;
/* 1458 */     this.originalDataType = 5;
/* 1459 */     this.originalDataOrder = 1;
/* 1460 */     this.dataEntered = true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void enterScoresAsRowPerPerson(char[][] scores)
/*      */   {
/* 1473 */     this.nPersons = scores.length;
/* 1474 */     this.nItems = scores[0].length;
/* 1475 */     this.nScores = (this.nItems * this.nPersons);
/*      */     
/*      */ 
/* 1478 */     if (this.title == null) {
/* 1479 */       this.title = new String[2];
/* 1480 */       this.title[0] = "Untitled Scores Analysis";
/* 1481 */       Date d = new Date();
/* 1482 */       String day = DateFormat.getDateInstance().format(d);
/* 1483 */       String tim = DateFormat.getTimeInstance().format(d);
/* 1484 */       this.title[1] = ("Program execution initiated at " + tim + " on " + day);
/*      */     }
/*      */     
/*      */ 
/* 1488 */     this.originalData = scores;
/* 1489 */     this.originalDataType = 6;
/* 1490 */     this.originalDataOrder = 1;
/* 1491 */     this.dataEntered = true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void enterScoresAsRowPerPerson(boolean[][] scores)
/*      */   {
/* 1503 */     this.nPersons = scores.length;
/* 1504 */     this.nItems = scores[0].length;
/* 1505 */     this.nScores = (this.nItems * this.nPersons);
/* 1506 */     this.dichotomous = new boolean[this.nItems];
/* 1507 */     this.dichotomousPercentage = new double[this.nItems];
/* 1508 */     for (int i = 0; i < this.nItems; i++) {
/* 1509 */       this.dichotomous[i] = true;
/* 1510 */       this.dichotomousPercentage[i] = 100.0D;
/*      */     }
/* 1512 */     this.dichotomousOverall = true;
/* 1513 */     this.dichotomousCheckDone = true;
/*      */     
/*      */ 
/* 1516 */     if (this.title == null) {
/* 1517 */       this.title = new String[2];
/* 1518 */       this.title[0] = "Untitled Scores Analysis";
/* 1519 */       Date d = new Date();
/* 1520 */       String day = DateFormat.getDateInstance().format(d);
/* 1521 */       String tim = DateFormat.getTimeInstance().format(d);
/* 1522 */       this.title[1] = ("Program execution initiated at " + tim + " on " + day);
/*      */     }
/*      */     
/*      */ 
/* 1526 */     this.originalData = scores;
/* 1527 */     this.originalDataType = 7;
/* 1528 */     this.originalDataOrder = 1;
/* 1529 */     this.dataEntered = true;
/*      */   }
/*      */   
/*      */ 
/*      */   public void enterItemNames(String[] itemNames)
/*      */   {
/* 1535 */     int len = itemNames.length;
/* 1536 */     this.itemNames = new String[len + 1];
/* 1537 */     for (int i = 0; i < len; i++) this.itemNames[i] = itemNames[i];
/* 1538 */     this.itemNames[len] = "total";
/* 1539 */     this.itemNamesSet = true;
/*      */   }
/*      */   
/*      */ 
/*      */   public void letterToNumeral()
/*      */   {
/* 1545 */     this.letterToNumeralSet = true;
/*      */   }
/*      */   
/*      */ 
/*      */   public void otherDichotomousData(String falseSign, String trueSign)
/*      */   {
/* 1551 */     this.otherFalse = falseSign;
/* 1552 */     this.otherTrue = trueSign;
/* 1553 */     this.otherDichotomousDataSet = true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected double[][] transpose0to1(double[][] scores00)
/*      */   {
/* 1560 */     int n0 = scores00.length;
/* 1561 */     int n1 = scores00[0].length;
/* 1562 */     double[][] scores11 = new double[n1][n0];
/* 1563 */     for (int i = 0; i < n0; i++) {
/* 1564 */       for (int j = 0; j < n1; j++) {
/* 1565 */         scores11[j][i] = scores00[i][j];
/*      */       }
/*      */     }
/* 1568 */     return scores11;
/*      */   }
/*      */   
/*      */   protected String[][] transpose0to1(String[][] scores00)
/*      */   {
/* 1573 */     int n0 = scores00.length;
/* 1574 */     int n1 = scores00[0].length;
/* 1575 */     String[][] scores11 = new String[n1][n0];
/* 1576 */     for (int i = 0; i < n0; i++) {
/* 1577 */       for (int j = 0; j < n1; j++) {
/* 1578 */         scores11[j][i] = scores00[i][j];
/*      */       }
/*      */     }
/* 1581 */     return scores11;
/*      */   }
/*      */   
/*      */   protected double[][] transpose1to0(double[][] scores11)
/*      */   {
/* 1586 */     int n0 = scores11.length;
/* 1587 */     int n1 = scores11[0].length;
/* 1588 */     double[][] scores00 = new double[n1][n0];
/* 1589 */     for (int i = 0; i < n0; i++) {
/* 1590 */       for (int j = 0; j < n1; j++) {
/* 1591 */         scores00[j][i] = scores11[i][j];
/*      */       }
/*      */     }
/* 1594 */     return scores00;
/*      */   }
/*      */   
/*      */   protected String[][] transpose1to0(String[][] scores11)
/*      */   {
/* 1599 */     int n0 = scores11.length;
/* 1600 */     int n1 = scores11[0].length;
/* 1601 */     String[][] scores00 = new String[n1][n0];
/* 1602 */     for (int i = 0; i < n0; i++) {
/* 1603 */       for (int j = 0; j < n1; j++) {
/* 1604 */         scores00[j][i] = scores11[i][j];
/*      */       }
/*      */     }
/* 1607 */     return scores00;
/*      */   }
/*      */   
/*      */   protected boolean[][] transpose1to0(boolean[][] scores11)
/*      */   {
/* 1612 */     int n0 = scores11.length;
/* 1613 */     int n1 = scores11[0].length;
/* 1614 */     boolean[][] scores00 = new boolean[n1][n0];
/* 1615 */     for (int i = 0; i < n0; i++) {
/* 1616 */       for (int j = 0; j < n1; j++) {
/* 1617 */         scores00[j][i] = scores11[i][j];
/*      */       }
/*      */     }
/* 1620 */     return scores00;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void checkLengths(String[][] scores)
/*      */   {
/* 1628 */     int n0 = scores.length;
/* 1629 */     int n1 = scores[0].length;
/* 1630 */     for (int i = 1; i < n0; i++) {
/* 1631 */       if (scores[i].length != n1) {
/* 1632 */         throw new IllegalArgumentException("The length of each item and of each person's responses must be identical (missing responses must be included - see documentation web page)");
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   protected void checkLengths(double[][] scores)
/*      */   {
/* 1639 */     int n0 = scores.length;
/* 1640 */     int n1 = scores[0].length;
/* 1641 */     for (int i = 1; i < n0; i++) {
/* 1642 */       if (scores[i].length != n1) {
/* 1643 */         throw new IllegalArgumentException("The length of each item and of each person's responses must be identical (missing responses must be included - see documentation web page)");
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   protected void checkLengths(char[][] scores)
/*      */   {
/* 1650 */     int n0 = scores.length;
/* 1651 */     int n1 = scores[0].length;
/* 1652 */     for (int i = 1; i < n0; i++) {
/* 1653 */       if (scores[i].length != n1) {
/* 1654 */         throw new IllegalArgumentException("The length of each item and of each person's responses must be identical (missing responses must be included - see documentation web page)");
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   protected void checkLengths(float[][] scores)
/*      */   {
/* 1661 */     int n0 = scores.length;
/* 1662 */     int n1 = scores[0].length;
/* 1663 */     for (int i = 1; i < n0; i++) {
/* 1664 */       if (scores[i].length != n1) {
/* 1665 */         throw new IllegalArgumentException("The length of each item and of each person's responses must be identical (missing responses must be included - see documentation web page)");
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   protected void checkLengths(int[][] scores)
/*      */   {
/* 1672 */     int n0 = scores.length;
/* 1673 */     int n1 = scores[0].length;
/* 1674 */     for (int i = 1; i < n0; i++) {
/* 1675 */       if (scores[i].length != n1) {
/* 1676 */         throw new IllegalArgumentException("The length of each item and of each person's responses must be identical (missing responses must be included - see documentation web page)");
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   protected void checkLengths(boolean[][] scores)
/*      */   {
/* 1683 */     int n0 = scores.length;
/* 1684 */     int n1 = scores[0].length;
/* 1685 */     for (int i = 1; i < n0; i++) {
/* 1686 */       if (scores[i].length != n1) {
/* 1687 */         throw new IllegalArgumentException("The length of each item and of each person's responses must be identical (missing responses must be included - see documentation web page)");
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected void trimScores(String[][] scores)
/*      */   {
/* 1696 */     int n = scores.length;
/* 1697 */     int m = scores[0].length;
/* 1698 */     for (int i = 0; i < n; i++) {
/* 1699 */       for (int j = 0; j < m; j++) {
/* 1700 */         scores[i][j].trim();
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void preprocessData()
/*      */   {
/* 1714 */     if (!this.dataPreprocessed) {
/* 1715 */       if (!this.dataEntered) { throw new IllegalArgumentException("No data has been entered");
/*      */       }
/*      */       
/* 1718 */       this.scores0 = new double[this.nItems][this.nPersons];
/* 1719 */       this.originalScores0 = new double[this.nItems][this.nPersons];
/* 1720 */       this.scores1 = new double[this.nPersons][this.nItems];
/* 1721 */       this.originalScores1 = new double[this.nPersons][this.nItems];
/* 1722 */       this.deletedPersons = new boolean[this.nPersons];
/* 1723 */       this.deletedItems = new boolean[this.nItems];
/* 1724 */       this.personIndices = new int[this.nPersons];
/* 1725 */       for (int i = 0; i < this.nPersons; i++) this.personIndices[i] = i;
/* 1726 */       this.itemIndices = new int[this.nItems];
/* 1727 */       for (int i = 0; i < this.nItems; i++) { this.itemIndices[i] = i;
/*      */       }
/*      */       
/*      */ 
/* 1731 */       this.nNaN = 0;
/* 1732 */       this.nDeletedPersons = 0;
/* 1733 */       this.nDeletedItems = 0;
/*      */       
/*      */ 
/* 1736 */       if (this.itemNamesSet) {
/* 1737 */         if (this.nItems + 1 != this.itemNames.length) throw new IllegalArgumentException("The number of item names, " + this.itemNames.length + ", does not equal the number of items, " + this.nItems);
/*      */       }
/*      */       else {
/* 1740 */         this.itemNames = new String[this.nItems + 1];
/* 1741 */         for (int i = 0; i < this.nItems; i++) this.itemNames[i] = ("item" + i);
/* 1742 */         this.itemNames[this.nItems] = "total";
/*      */       }
/*      */       
/*      */ 
/* 1746 */       String[][] holdingArrayS = (String[][])null;
/* 1747 */       double[][] holdingArrayD = (double[][])null;
/* 1748 */       boolean[][] holdingArrayB = (boolean[][])null;
/* 1749 */       int m = 0;
/* 1750 */       int n = 0;
/* 1751 */       switch (this.originalDataType) {
/* 1752 */       case 1:  holdingArrayS = (String[][])this.originalData;
/* 1753 */         checkLengths(holdingArrayS);
/*      */         
/* 1755 */         if (this.originalDataOrder == 1) {
/* 1756 */           holdingArrayS = transpose1to0(holdingArrayS);
/*      */         }
/* 1758 */         trimScores(holdingArrayS);
/* 1759 */         break;
/* 1760 */       case 2:  holdingArrayD = (double[][])this.originalData;
/* 1761 */         checkLengths(holdingArrayD);
/*      */         
/* 1763 */         if (this.originalDataOrder == 1)
/* 1764 */           holdingArrayD = transpose1to0(holdingArrayD);
/* 1765 */         break;
/*      */       case 3: 
/* 1767 */         holdingArrayD = ((Matrix)this.originalData).getArrayCopy();
/* 1768 */         checkLengths(holdingArrayD);
/*      */         
/* 1770 */         if (this.originalDataOrder == 1)
/* 1771 */           holdingArrayD = transpose1to0(holdingArrayD);
/* 1772 */         break;
/*      */       case 4: 
/* 1774 */         float[][] holdingArrayF = (float[][])this.originalData;
/* 1775 */         checkLengths(holdingArrayF);
/*      */         
/* 1777 */         m = holdingArrayF.length;
/* 1778 */         n = holdingArrayF[0].length;
/* 1779 */         for (int i = 0; i < m; i++) {
/* 1780 */           for (int j = 0; j < n; j++) {
/* 1781 */             holdingArrayD[i][j] = new Float(holdingArrayF[i][j]).doubleValue();
/*      */           }
/*      */         }
/*      */         
/* 1785 */         if (this.originalDataOrder == 1)
/* 1786 */           holdingArrayD = transpose1to0(holdingArrayD);
/* 1787 */         break;
/*      */       case 5: 
/* 1789 */         int[][] holdingArrayI = (int[][])this.originalData;
/* 1790 */         checkLengths(holdingArrayI);
/*      */         
/* 1792 */         m = holdingArrayI.length;
/* 1793 */         n = holdingArrayI[0].length;
/* 1794 */         for (int i = 0; i < m; i++) {
/* 1795 */           for (int j = 0; j < n; j++) {
/* 1796 */             holdingArrayD[i][j] = new Integer(holdingArrayI[i][j]).doubleValue();
/*      */           }
/*      */         }
/*      */         
/* 1800 */         if (this.originalDataOrder == 1)
/* 1801 */           holdingArrayD = transpose1to0(holdingArrayD);
/* 1802 */         break;
/*      */       case 6: 
/* 1804 */         char[][] holdingArrayC = (char[][])this.originalData;
/* 1805 */         checkLengths(holdingArrayC);
/*      */         
/* 1807 */         m = holdingArrayC.length;
/* 1808 */         n = holdingArrayC[0].length;
/* 1809 */         holdingArrayS = new String[m][n];
/* 1810 */         for (int i = 0; i < m; i++) {
/* 1811 */           for (int j = 0; j < n; j++) {
/* 1812 */             holdingArrayS[i][j] = Character.toString(holdingArrayC[i][j]);
/*      */           }
/*      */         }
/*      */         
/* 1816 */         if (this.originalDataOrder == 1) {
/* 1817 */           holdingArrayS = transpose1to0(holdingArrayS);
/*      */         }
/* 1819 */         trimScores(holdingArrayS);
/* 1820 */         break;
/* 1821 */       case 7:  holdingArrayB = (boolean[][])this.originalData;
/* 1822 */         checkLengths(holdingArrayB);
/*      */         
/* 1824 */         if (this.originalDataOrder == 1) {
/* 1825 */           holdingArrayB = transpose1to0(holdingArrayB);
/*      */         }
/*      */         
/*      */         break;
/*      */       }
/*      */       
/*      */       
/* 1832 */       switch (this.originalDataType) {
/*      */       case 1: case 6: 
/* 1834 */         for (int i = 0; i < this.nItems; i++) {
/* 1835 */           for (int j = 0; j < this.nPersons; j++) {
/* 1836 */             boolean elementSet = false;
/* 1837 */             if (this.otherDichotomousDataSet) {
/* 1838 */               if (holdingArrayS[i][j].equalsIgnoreCase(this.otherTrue)) {
/* 1839 */                 this.scores0[i][j] = 1.0D;
/* 1840 */                 elementSet = true;
/*      */ 
/*      */               }
/* 1843 */               else if (holdingArrayS[i][j].equalsIgnoreCase(this.otherFalse)) {
/* 1844 */                 this.scores0[i][j] = 0.0D;
/* 1845 */                 elementSet = true;
/*      */               }
/*      */               else {
/* 1848 */                 this.scores0[i][j] = NaN.0D;
/* 1849 */                 elementSet = true;
/*      */               }
/*      */             }
/*      */             
/* 1853 */             if ((!elementSet) && 
/* 1854 */               (this.letterToNumeralSet)) {
/* 1855 */               char elem = holdingArrayS[i][j].charAt(0);
/* 1856 */               if ((elem > '@') && (elem < '[') && (holdingArrayS[i][j].length() == 1)) {
/* 1857 */                 this.scores0[i][j] = (elem - '?');
/* 1858 */                 elementSet = true;
/*      */ 
/*      */               }
/* 1861 */               else if ((elem > '`') && (elem < '{') && (holdingArrayS[i][j].length() == 1)) {
/* 1862 */                 this.scores0[i][j] = (elem - '`');
/* 1863 */                 elementSet = true;
/*      */               }
/*      */               else {
/* 1866 */                 this.scores0[i][j] = NaN.0D;
/* 1867 */                 elementSet = true;
/*      */               }
/*      */             }
/*      */             
/*      */ 
/* 1872 */             if (!elementSet) {
/* 1873 */               if ((holdingArrayS[i][j].equalsIgnoreCase("yes")) || (holdingArrayS[i][j].equalsIgnoreCase("y")) || (holdingArrayS[i][j].equalsIgnoreCase("true"))) {
/* 1874 */                 this.scores0[i][j] = 1.0D;
/* 1875 */                 elementSet = true;
/*      */ 
/*      */               }
/* 1878 */               else if ((holdingArrayS[i][j].equalsIgnoreCase("no")) || (holdingArrayS[i][j].equalsIgnoreCase("n")) || (holdingArrayS[i][j].equalsIgnoreCase("false"))) {
/* 1879 */                 this.scores0[i][j] = 0.0D;
/* 1880 */                 elementSet = true;
/*      */               }
/*      */             }
/*      */             
/* 1884 */             if (!elementSet) {
/*      */               try {
/* 1886 */                 this.scores0[i][j] = Double.valueOf(holdingArrayS[i][j]).doubleValue();
/*      */               }
/*      */               catch (Exception e) {
/* 1889 */                 this.scores0[i][j] = NaN.0D;
/* 1890 */                 this.nNaN += 1;
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/* 1895 */         break;
/*      */       case 2: case 3: 
/*      */       case 4: 
/*      */       case 5: 
/* 1899 */         for (int i = 0; i < this.nItems; i++) {
/* 1900 */           for (int j = 0; j < this.nPersons; j++) {
/*      */             try {
/* 1902 */               this.scores0[i][j] = Double.valueOf(holdingArrayD[i][j]).doubleValue();
/*      */             }
/*      */             catch (Exception e) {
/* 1905 */               this.scores0[i][j] = NaN.0D;
/* 1906 */               this.nNaN += 1;
/*      */             }
/*      */           }
/*      */         }
/* 1910 */         break;
/* 1911 */       case 7:  for (int i = 0; i < this.nItems; i++) {
/* 1912 */           for (int j = 0; j < this.nPersons; j++) {
/* 1913 */             if (holdingArrayB[i][j] != 0) {
/* 1914 */               this.scores0[i][j] = 1.0D;
/*      */             }
/*      */             else {
/* 1917 */               this.scores0[i][j] = 0.0D;
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */       
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1927 */       this.originalScores0 = ((double[][])this.scores0.clone());
/* 1928 */       this.originalScores1 = transpose0to1(this.scores0);
/* 1929 */       this.originalNitems = this.nItems;
/* 1930 */       this.originalNpersons = this.nPersons;
/* 1931 */       this.originalNscores = (this.originalNitems * this.originalNpersons);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1936 */       if (this.nNaN > 0) { noResponseHandling();
/*      */       }
/*      */       
/* 1939 */       this.scores1 = new double[this.nPersons][this.nItems];
/* 1940 */       for (int i = 0; i < this.nItems; i++) {
/* 1941 */         for (int j = 0; j < this.nPersons; j++) {
/* 1942 */           this.scores1[j][i] = this.scores0[i][j];
/*      */         }
/*      */       }
/*      */       
/*      */ 
/* 1947 */       checkWhetherRawItemsDichotomous();
/*      */       
/*      */ 
/* 1950 */       this.standardizedScores0 = new double[this.nItems][this.nPersons];
/* 1951 */       this.standardizedScores1 = new double[this.nPersons][this.nItems];
/* 1952 */       for (int i = 0; i < this.nItems; i++) {
/* 1953 */         Stat st = new Stat(this.scores0[i]);
/* 1954 */         this.standardizedScores0[i] = st.standardize();
/*      */       }
/* 1956 */       this.standardizedScores1 = transpose0to1(this.standardizedScores0);
/*      */       
/*      */ 
/* 1959 */       meansAndVariances();
/*      */       
/*      */ 
/* 1962 */       covariancesAndCorrelationCoefficients();
/*      */       
/* 1964 */       this.dataPreprocessed = true;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Object originalResponses()
/*      */   {
/* 1972 */     return originalScores();
/*      */   }
/*      */   
/*      */   public Object originalScores() {
/* 1976 */     if (!this.dataEntered) throw new IllegalArgumentException("No data has been entered");
/* 1977 */     return this.originalData;
/*      */   }
/*      */   
/*      */   public double[][] originalResponsesAsRowPerPerson()
/*      */   {
/* 1982 */     return originalScoresAsRowPerPerson();
/*      */   }
/*      */   
/*      */   public double[][] originalScoresAsRowPerPerson() {
/* 1986 */     if (!this.dataPreprocessed) preprocessData();
/* 1987 */     return this.originalScores1;
/*      */   }
/*      */   
/*      */   public double[][] originalResponsesAsRowPerItem()
/*      */   {
/* 1992 */     return originalScoresAsRowPerItem();
/*      */   }
/*      */   
/*      */   public double[][] originalScoresAsRowPerItem() {
/* 1996 */     if (!this.dataPreprocessed) preprocessData();
/* 1997 */     return this.originalScores0;
/*      */   }
/*      */   
/*      */   public double[][] usedresponsesAsRowPerPerson()
/*      */   {
/* 2002 */     return usedScoresAsRowPerPerson();
/*      */   }
/*      */   
/*      */   public double[][] usedScoresAsRowPerPerson() {
/* 2006 */     if (!this.dataPreprocessed) preprocessData();
/* 2007 */     return this.scores1;
/*      */   }
/*      */   
/*      */   public double[][] usedScoresAsRowPerItem()
/*      */   {
/* 2012 */     if (!this.dataPreprocessed) preprocessData();
/* 2013 */     return this.scores0;
/*      */   }
/*      */   
/*      */   public double[][] standardizedScoresAsRowPerPerson()
/*      */   {
/* 2018 */     if (!this.dataPreprocessed) preprocessData();
/* 2019 */     return this.standardizedScores1;
/*      */   }
/*      */   
/*      */   public double[][] standardisedScoresAsRowPerPerson() {
/* 2023 */     if (!this.dataPreprocessed) preprocessData();
/* 2024 */     return this.standardizedScores1;
/*      */   }
/*      */   
/*      */   public double[][] standardizedScoresAsRowPerItem()
/*      */   {
/* 2029 */     if (!this.dataPreprocessed) preprocessData();
/* 2030 */     return this.standardizedScores0;
/*      */   }
/*      */   
/*      */   public double[][] standardisedScoresAsRowPerItem() {
/* 2034 */     if (!this.dataPreprocessed) preprocessData();
/* 2035 */     return this.standardizedScores0;
/*      */   }
/*      */   
/*      */   public int originalNumberOfItems()
/*      */   {
/* 2040 */     if (!this.dataPreprocessed) preprocessData();
/* 2041 */     return this.originalNitems;
/*      */   }
/*      */   
/*      */   public int originalNumberOfPersons()
/*      */   {
/* 2046 */     if (!this.dataPreprocessed) preprocessData();
/* 2047 */     return this.originalNpersons;
/*      */   }
/*      */   
/*      */   public int usedNumberOfItems()
/*      */   {
/* 2052 */     if (!this.dataPreprocessed) preprocessData();
/* 2053 */     return this.nItems;
/*      */   }
/*      */   
/*      */   public int usedNumberOfPersons()
/*      */   {
/* 2058 */     if (!this.dataPreprocessed) preprocessData();
/* 2059 */     return this.nPersons;
/*      */   }
/*      */   
/*      */ 
/*      */   public int originalTotalNumberOfScores()
/*      */   {
/* 2065 */     if (!this.dataPreprocessed) preprocessData();
/* 2066 */     return this.originalNscores;
/*      */   }
/*      */   
/*      */   public int usedTotalNumberOfScores()
/*      */   {
/* 2071 */     if (!this.dataPreprocessed) preprocessData();
/* 2072 */     return this.nScores;
/*      */   }
/*      */   
/*      */   public int numberOfDeletedScores()
/*      */   {
/* 2077 */     if (!this.dataPreprocessed) preprocessData();
/* 2078 */     return this.originalNscores - this.nScores;
/*      */   }
/*      */   
/*      */   public int numberOfReplacedScores()
/*      */   {
/* 2083 */     if (!this.dataPreprocessed) preprocessData();
/* 2084 */     return this.nReplacements;
/*      */   }
/*      */   
/*      */   public String[] indicesOfReplacedScores()
/*      */   {
/* 2089 */     if (!this.dataPreprocessed) preprocessData();
/* 2090 */     return this.replacementIndices;
/*      */   }
/*      */   
/*      */   public String[] itemNames()
/*      */   {
/* 2095 */     if (!this.dataEntered) throw new IllegalArgumentException("no data has been entered");
/* 2096 */     String[] ret = new String[this.nItems];
/* 2097 */     for (int i = 0; i < this.nItems; i++) ret[i] = this.itemNames[i];
/* 2098 */     return ret;
/*      */   }
/*      */   
/*      */   public String[] originalItemNames() {
/* 2102 */     if (!this.dataEntered) throw new IllegalArgumentException("no data has been entered");
/* 2103 */     String[] ret = new String[this.originalNitems];
/* 2104 */     for (int i = 0; i < this.originalNitems; i++) ret[i] = this.originalItemNames[i];
/* 2105 */     return ret;
/*      */   }
/*      */   
/*      */ 
/*      */   public int itemIndex(String itemName)
/*      */   {
/* 2111 */     if (!this.dataEntered) throw new IllegalArgumentException("no data has been entered");
/* 2112 */     int index = -1;
/* 2113 */     int jj = 0;
/* 2114 */     boolean test = true;
/* 2115 */     while (test) {
/* 2116 */       if (itemName.trim().equalsIgnoreCase(this.itemNames[jj].trim())) {
/* 2117 */         index = jj;
/* 2118 */         test = false;
/*      */       }
/*      */       else {
/* 2121 */         jj++;
/* 2122 */         if (jj > this.nItems) throw new IllegalArgumentException("Item name, " + itemName + ", is not present in the list of entered item names");
/*      */       }
/*      */     }
/* 2125 */     return index + 1;
/*      */   }
/*      */   
/*      */   public String itemName(int index)
/*      */   {
/* 2130 */     if (!this.dataEntered) throw new IllegalArgumentException("no data has been entered");
/* 2131 */     return this.itemNames[(index - 1)];
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void meansAndVariances()
/*      */   {
/* 2141 */     this.rawItemMeans = new double[this.nItems];
/* 2142 */     this.rawItemMedians = new double[this.nItems];
/* 2143 */     this.rawItemStandardDeviations = new double[this.nItems];
/* 2144 */     this.rawItemVariances = new double[this.nItems];
/* 2145 */     this.rawItemMinima = new double[this.nItems];
/* 2146 */     this.rawItemMaxima = new double[this.nItems];
/* 2147 */     this.rawItemRanges = new double[this.nItems];
/* 2148 */     this.rawItemTotals = new double[this.nItems];
/* 2149 */     this.rawItemMomentSkewness = new double[this.nItems];
/* 2150 */     this.rawItemMedianSkewness = new double[this.nItems];
/* 2151 */     this.rawItemQuartileSkewness = new double[this.nItems];
/* 2152 */     this.rawItemKurtosisExcess = new double[this.nItems];
/*      */     
/* 2154 */     for (int i = 0; i < this.nItems; i++) {
/* 2155 */       Stat am0 = new Stat(this.scores0[i]);
/* 2156 */       if (this.nFactorOption) {
/* 2157 */         am0.setDenominatorToN();
/*      */       }
/*      */       else {
/* 2160 */         am0.setDenominatorToNminusOne();
/*      */       }
/*      */       
/* 2163 */       this.rawItemMeans[i] = am0.mean_as_double();
/* 2164 */       this.rawItemVariances[i] = am0.variance_as_double();
/* 2165 */       this.rawItemStandardDeviations[i] = Math.sqrt(this.rawItemVariances[i]);
/* 2166 */       this.rawItemMinima[i] = am0.minimum_as_double();
/* 2167 */       this.rawItemMaxima[i] = am0.maximum_as_double();
/* 2168 */       this.rawItemRanges[i] = (this.rawItemMaxima[i] - this.rawItemMinima[i]);
/* 2169 */       this.rawItemTotals[i] = am0.sum_as_double();
/* 2170 */       Stat ams0 = am0.sort();
/* 2171 */       this.rawItemMedians[i] = ams0.median_as_double();
/* 2172 */       this.rawItemMomentSkewness[i] = am0.momentSkewness_as_double();
/* 2173 */       this.rawItemMedianSkewness[i] = am0.medianSkewness_as_double();
/* 2174 */       this.rawItemQuartileSkewness[i] = am0.quartileSkewness_as_double();
/* 2175 */       this.rawItemKurtosisExcess[i] = am0.kurtosisExcess_as_double();
/*      */     }
/*      */     
/* 2178 */     Stat st = new Stat(this.rawItemMeans);
/* 2179 */     if (this.nFactorOption) {
/* 2180 */       st.setDenominatorToN();
/*      */     }
/*      */     else {
/* 2183 */       st.setDenominatorToNminusOne();
/*      */     }
/* 2185 */     this.rawItemMeansMean = st.mean_as_double();
/* 2186 */     this.rawItemMeansVar = st.variance_as_double();
/* 2187 */     this.rawItemMeansSd = Math.sqrt(this.rawItemMeansVar);
/* 2188 */     this.rawItemMeansMin = st.minimum_as_double();
/* 2189 */     this.rawItemMeansMax = st.maximum_as_double();
/* 2190 */     this.rawItemMeansRange = (this.rawItemMeansMax - this.rawItemMeansMin);
/*      */     
/* 2192 */     st = new Stat(this.rawItemStandardDeviations);
/* 2193 */     if (this.nFactorOption) {
/* 2194 */       st.setDenominatorToN();
/*      */     }
/*      */     else {
/* 2197 */       st.setDenominatorToNminusOne();
/*      */     }
/* 2199 */     this.rawItemStandardDeviationsMean = st.mean_as_double();
/* 2200 */     this.rawItemStandardDeviationsVar = st.variance_as_double();
/* 2201 */     this.rawItemStandardDeviationsSd = Math.sqrt(this.rawItemStandardDeviationsVar);
/* 2202 */     this.rawItemStandardDeviationsMin = st.minimum_as_double();
/* 2203 */     this.rawItemStandardDeviationsMax = st.maximum_as_double();
/* 2204 */     this.rawItemStandardDeviationsRange = (this.rawItemStandardDeviationsMax - this.rawItemStandardDeviationsMin);
/*      */     
/* 2206 */     st = new Stat(this.rawItemVariances);
/* 2207 */     if (this.nFactorOption) {
/* 2208 */       st.setDenominatorToN();
/*      */     }
/*      */     else {
/* 2211 */       st.setDenominatorToNminusOne();
/*      */     }
/* 2213 */     this.rawItemVariancesMean = st.mean_as_double();
/* 2214 */     this.rawItemVariancesVar = st.variance_as_double();
/* 2215 */     this.rawItemVariancesSd = Math.sqrt(this.rawItemVariancesVar);
/* 2216 */     this.rawItemVariancesMin = st.minimum_as_double();
/* 2217 */     this.rawItemVariancesMax = st.maximum_as_double();
/* 2218 */     this.rawItemVariancesRange = (this.rawItemVariancesMax - this.rawItemVariancesMin);
/*      */     
/* 2220 */     st = new Stat(this.rawItemMinima);
/* 2221 */     if (this.nFactorOption) {
/* 2222 */       st.setDenominatorToN();
/*      */     }
/*      */     else {
/* 2225 */       st.setDenominatorToNminusOne();
/*      */     }
/* 2227 */     this.rawItemMinimaMean = st.mean_as_double();
/* 2228 */     this.rawItemMinimaVar = st.variance_as_double();
/* 2229 */     this.rawItemMinimaSd = Math.sqrt(this.rawItemMinimaVar);
/* 2230 */     this.rawItemMinimaMin = st.minimum_as_double();
/* 2231 */     this.rawItemMinimaMax = st.maximum_as_double();
/* 2232 */     this.rawItemMinimaRange = (this.rawItemMinimaMax - this.rawItemMinimaMin);
/*      */     
/* 2234 */     st = new Stat(this.rawItemMaxima);
/* 2235 */     if (this.nFactorOption) {
/* 2236 */       st.setDenominatorToN();
/*      */     }
/*      */     else {
/* 2239 */       st.setDenominatorToNminusOne();
/*      */     }
/* 2241 */     this.rawItemMaximaMean = st.mean_as_double();
/* 2242 */     this.rawItemMaximaVar = st.variance_as_double();
/* 2243 */     this.rawItemMaximaSd = Math.sqrt(this.rawItemMaximaVar);
/* 2244 */     this.rawItemMaximaMin = st.minimum_as_double();
/* 2245 */     this.rawItemMaximaMax = st.maximum_as_double();
/* 2246 */     this.rawItemMaximaRange = (this.rawItemMaximaMax - this.rawItemMaximaMin);
/*      */     
/* 2248 */     st = new Stat(this.rawItemRanges);
/* 2249 */     if (this.nFactorOption) {
/* 2250 */       st.setDenominatorToN();
/*      */     }
/*      */     else {
/* 2253 */       st.setDenominatorToNminusOne();
/*      */     }
/* 2255 */     this.rawItemRangesMean = st.mean_as_double();
/* 2256 */     this.rawItemRangesVar = st.variance_as_double();
/* 2257 */     this.rawItemRangesSd = Math.sqrt(this.rawItemRangesVar);
/* 2258 */     this.rawItemRangesMin = st.minimum_as_double();
/* 2259 */     this.rawItemRangesMax = st.maximum_as_double();
/* 2260 */     this.rawItemRangesRange = (this.rawItemRangesMax - this.rawItemRangesMin);
/*      */     
/* 2262 */     st = new Stat(this.rawItemTotals);
/* 2263 */     if (this.nFactorOption) {
/* 2264 */       st.setDenominatorToN();
/*      */     }
/*      */     else {
/* 2267 */       st.setDenominatorToNminusOne();
/*      */     }
/* 2269 */     this.rawItemTotalsMean = st.mean_as_double();
/* 2270 */     this.rawItemTotalsVar = st.variance_as_double();
/* 2271 */     this.rawItemTotalsSd = Math.sqrt(this.rawItemTotalsVar);
/* 2272 */     this.rawItemTotalsMin = st.minimum_as_double();
/* 2273 */     this.rawItemTotalsMax = st.maximum_as_double();
/* 2274 */     this.rawItemTotalsRange = (this.rawItemTotalsMax - this.rawItemTotalsMin);
/*      */     
/* 2276 */     st = new Stat(this.rawItemMedians);
/* 2277 */     if (this.nFactorOption) {
/* 2278 */       st.setDenominatorToN();
/*      */     }
/*      */     else {
/* 2281 */       st.setDenominatorToNminusOne();
/*      */     }
/* 2283 */     this.rawItemMediansMean = st.mean_as_double();
/* 2284 */     this.rawItemMediansVar = st.variance_as_double();
/* 2285 */     this.rawItemMediansSd = Math.sqrt(this.rawItemMediansVar);
/* 2286 */     this.rawItemMediansMin = st.minimum_as_double();
/* 2287 */     this.rawItemMediansMax = st.maximum_as_double();
/* 2288 */     this.rawItemMediansRange = (this.rawItemMediansMax - this.rawItemMediansMin);
/*      */     
/* 2290 */     this.standardizedItemMeans = new double[this.nItems];
/* 2291 */     this.standardizedItemMedians = new double[this.nItems];
/* 2292 */     this.standardizedItemStandardDeviations = new double[this.nItems];
/* 2293 */     this.standardizedItemVariances = new double[this.nItems];
/* 2294 */     this.standardizedItemMinima = new double[this.nItems];
/* 2295 */     this.standardizedItemMaxima = new double[this.nItems];
/* 2296 */     this.standardizedItemRanges = new double[this.nItems];
/* 2297 */     this.standardizedItemTotals = new double[this.nItems];
/* 2298 */     this.standardizedItemMomentSkewness = new double[this.nItems];
/* 2299 */     this.standardizedItemMedianSkewness = new double[this.nItems];
/* 2300 */     this.standardizedItemQuartileSkewness = new double[this.nItems];
/* 2301 */     this.standardizedItemKurtosisExcess = new double[this.nItems];
/*      */     
/* 2303 */     for (int i = 0; i < this.nItems; i++) {
/* 2304 */       Stat ams0 = new Stat(this.standardizedScores0[i]);
/* 2305 */       if (this.nFactorOption) {
/* 2306 */         ams0.setDenominatorToN();
/*      */       }
/*      */       else {
/* 2309 */         ams0.setDenominatorToNminusOne();
/*      */       }
/* 2311 */       this.standardizedItemMeans[i] = 0.0D;
/* 2312 */       this.standardizedItemVariances[i] = 1.0D;
/* 2313 */       this.standardizedItemStandardDeviations[i] = 1.0D;
/* 2314 */       this.standardizedItemMinima[i] = ams0.minimum_as_double();
/* 2315 */       this.standardizedItemMaxima[i] = ams0.maximum_as_double();
/* 2316 */       this.standardizedItemRanges[i] = (this.standardizedItemMaxima[i] - this.standardizedItemMinima[i]);
/* 2317 */       this.standardizedItemTotals[i] = 0.0D;
/* 2318 */       Stat amss0 = ams0.sort();
/* 2319 */       this.standardizedItemMedians[i] = amss0.median_as_double();
/* 2320 */       this.standardizedItemMomentSkewness[i] = ams0.momentSkewness_as_double();
/* 2321 */       this.standardizedItemMedianSkewness[i] = ams0.medianSkewness_as_double();
/* 2322 */       this.standardizedItemQuartileSkewness[i] = ams0.quartileSkewness_as_double();
/* 2323 */       this.standardizedItemKurtosisExcess[i] = ams0.kurtosisExcess_as_double();
/*      */     }
/*      */     
/*      */ 
/* 2327 */     st = new Stat(this.standardizedItemMeans);
/* 2328 */     if (this.nFactorOption) {
/* 2329 */       st.setDenominatorToN();
/*      */     }
/*      */     else {
/* 2332 */       st.setDenominatorToNminusOne();
/*      */     }
/* 2334 */     this.standardizedItemMeansMean = st.mean_as_double();
/* 2335 */     this.standardizedItemMeansVar = st.variance_as_double();
/* 2336 */     this.standardizedItemMeansSd = Math.sqrt(this.standardizedItemMeansVar);
/* 2337 */     this.standardizedItemMeansMin = st.minimum_as_double();
/* 2338 */     this.standardizedItemMeansMax = st.maximum_as_double();
/* 2339 */     this.standardizedItemMeansRange = (this.standardizedItemMeansMax - this.standardizedItemMeansMin);
/*      */     
/* 2341 */     st = new Stat(this.standardizedItemStandardDeviations);
/* 2342 */     if (this.nFactorOption) {
/* 2343 */       st.setDenominatorToN();
/*      */     }
/*      */     else {
/* 2346 */       st.setDenominatorToNminusOne();
/*      */     }
/* 2348 */     this.standardizedItemStandardDeviationsMean = st.mean_as_double();
/* 2349 */     this.standardizedItemStandardDeviationsVar = st.variance_as_double();
/* 2350 */     this.standardizedItemStandardDeviationsSd = Math.sqrt(this.standardizedItemStandardDeviationsVar);
/* 2351 */     this.standardizedItemStandardDeviationsMin = st.minimum_as_double();
/* 2352 */     this.standardizedItemStandardDeviationsMax = st.maximum_as_double();
/* 2353 */     this.standardizedItemStandardDeviationsRange = (this.standardizedItemStandardDeviationsMax - this.standardizedItemStandardDeviationsMin);
/*      */     
/* 2355 */     st = new Stat(this.standardizedItemVariances);
/* 2356 */     if (this.nFactorOption) {
/* 2357 */       st.setDenominatorToN();
/*      */     }
/*      */     else {
/* 2360 */       st.setDenominatorToNminusOne();
/*      */     }
/* 2362 */     this.standardizedItemVariancesMean = st.mean_as_double();
/* 2363 */     this.standardizedItemVariancesVar = st.variance_as_double();
/* 2364 */     this.standardizedItemVariancesSd = Math.sqrt(this.standardizedItemVariancesVar);
/* 2365 */     this.standardizedItemVariancesMin = st.minimum_as_double();
/* 2366 */     this.standardizedItemVariancesMax = st.maximum_as_double();
/* 2367 */     this.standardizedItemVariancesRange = (this.standardizedItemVariancesMax - this.standardizedItemVariancesMin);
/*      */     
/* 2369 */     st = new Stat(this.standardizedItemMinima);
/* 2370 */     if (this.nFactorOption) {
/* 2371 */       st.setDenominatorToN();
/*      */     }
/*      */     else {
/* 2374 */       st.setDenominatorToNminusOne();
/*      */     }
/* 2376 */     this.standardizedItemMinimaMean = st.mean_as_double();
/* 2377 */     this.standardizedItemMinimaVar = st.variance_as_double();
/* 2378 */     this.standardizedItemMinimaSd = Math.sqrt(this.standardizedItemMinimaVar);
/* 2379 */     this.standardizedItemMinimaMin = st.minimum_as_double();
/* 2380 */     this.standardizedItemMinimaMax = st.maximum_as_double();
/* 2381 */     this.standardizedItemMinimaRange = (this.standardizedItemMinimaMax - this.standardizedItemMinimaMin);
/*      */     
/* 2383 */     st = new Stat(this.standardizedItemMaxima);
/* 2384 */     if (this.nFactorOption) {
/* 2385 */       st.setDenominatorToN();
/*      */     }
/*      */     else {
/* 2388 */       st.setDenominatorToNminusOne();
/*      */     }
/* 2390 */     this.standardizedItemMaximaMean = st.mean_as_double();
/* 2391 */     this.standardizedItemMaximaVar = st.variance_as_double();
/* 2392 */     this.standardizedItemMaximaSd = Math.sqrt(this.standardizedItemMaximaVar);
/* 2393 */     this.standardizedItemMaximaMin = st.minimum_as_double();
/* 2394 */     this.standardizedItemMaximaMax = st.maximum_as_double();
/* 2395 */     this.standardizedItemMaximaRange = (this.standardizedItemMaximaMax - this.standardizedItemMaximaMin);
/*      */     
/* 2397 */     st = new Stat(this.standardizedItemRanges);
/* 2398 */     if (this.nFactorOption) {
/* 2399 */       st.setDenominatorToN();
/*      */     }
/*      */     else {
/* 2402 */       st.setDenominatorToNminusOne();
/*      */     }
/* 2404 */     this.standardizedItemRangesMean = st.mean_as_double();
/* 2405 */     this.standardizedItemRangesVar = st.variance_as_double();
/* 2406 */     this.standardizedItemRangesSd = Math.sqrt(this.standardizedItemRangesVar);
/* 2407 */     this.standardizedItemRangesMin = st.minimum_as_double();
/* 2408 */     this.standardizedItemRangesMax = st.maximum_as_double();
/* 2409 */     this.standardizedItemRangesRange = (this.standardizedItemRangesMax - this.standardizedItemRangesMin);
/*      */     
/* 2411 */     this.standardizedItemTotalsMean = 0.0D;
/* 2412 */     this.standardizedItemTotalsVar = 0.0D;
/* 2413 */     this.standardizedItemTotalsSd = 0.0D;
/* 2414 */     this.standardizedItemTotalsMin = 0.0D;
/* 2415 */     this.standardizedItemTotalsMax = 0.0D;
/* 2416 */     this.standardizedItemTotalsRange = 0.0D;
/*      */     
/* 2418 */     st = new Stat(this.standardizedItemMedians);
/* 2419 */     if (this.nFactorOption) {
/* 2420 */       st.setDenominatorToN();
/*      */     }
/*      */     else {
/* 2423 */       st.setDenominatorToNminusOne();
/*      */     }
/* 2425 */     this.standardizedItemMediansMean = st.mean_as_double();
/* 2426 */     this.standardizedItemMediansVar = st.variance_as_double();
/* 2427 */     this.standardizedItemMediansSd = Math.sqrt(this.standardizedItemMediansVar);
/* 2428 */     this.standardizedItemMediansMin = st.minimum_as_double();
/* 2429 */     this.standardizedItemMediansMax = st.maximum_as_double();
/* 2430 */     this.standardizedItemMediansRange = (this.standardizedItemMediansMax - this.standardizedItemMediansMin);
/*      */     
/*      */ 
/* 2433 */     this.rawPersonMeans = new double[this.nPersons];
/* 2434 */     this.rawPersonStandardDeviations = new double[this.nPersons];
/* 2435 */     this.rawPersonVariances = new double[this.nPersons];
/* 2436 */     this.rawPersonMinima = new double[this.nPersons];
/* 2437 */     this.rawPersonMaxima = new double[this.nPersons];
/* 2438 */     this.rawPersonRanges = new double[this.nPersons];
/* 2439 */     this.rawPersonTotals = new double[this.nPersons];
/* 2440 */     Stat[] am1 = new Stat[this.nPersons];
/* 2441 */     for (int i = 0; i < this.nPersons; i++) {
/* 2442 */       am1[i] = new Stat(this.scores1[i]);
/* 2443 */       if (this.nFactorOption) {
/* 2444 */         am1[i].setDenominatorToN();
/*      */       }
/*      */       else {
/* 2447 */         am1[i].setDenominatorToNminusOne();
/*      */       }
/* 2449 */       this.rawPersonMeans[i] = am1[i].mean_as_double();
/* 2450 */       this.rawPersonVariances[i] = am1[i].variance_as_double();
/* 2451 */       this.rawPersonStandardDeviations[i] = Math.sqrt(this.rawPersonVariances[i]);
/* 2452 */       this.rawPersonMinima[i] = am1[i].minimum_as_double();
/* 2453 */       this.rawPersonMaxima[i] = am1[i].maximum_as_double();
/* 2454 */       this.rawPersonRanges[i] = (this.rawPersonMaxima[i] - this.rawPersonMinima[i]);
/* 2455 */       this.rawPersonTotals[i] = am1[i].sum_as_double();
/*      */     }
/*      */     
/* 2458 */     this.standardizedPersonMeans = new double[this.nPersons];
/* 2459 */     this.standardizedPersonStandardDeviations = new double[this.nPersons];
/* 2460 */     this.standardizedPersonVariances = new double[this.nPersons];
/* 2461 */     this.standardizedPersonMinima = new double[this.nPersons];
/* 2462 */     this.standardizedPersonMaxima = new double[this.nPersons];
/* 2463 */     this.standardizedPersonRanges = new double[this.nPersons];
/* 2464 */     this.standardizedPersonTotals = new double[this.nPersons];
/* 2465 */     Stat[] ams1 = new Stat[this.nPersons];
/* 2466 */     for (int i = 0; i < this.nPersons; i++) {
/* 2467 */       ams1[i] = new Stat(this.standardizedScores1[i]);
/* 2468 */       if (this.nFactorOption) {
/* 2469 */         ams1[i].setDenominatorToN();
/*      */       }
/*      */       else {
/* 2472 */         ams1[i].setDenominatorToNminusOne();
/*      */       }
/* 2474 */       this.standardizedPersonMeans[i] = ams1[i].mean_as_double();
/* 2475 */       this.standardizedPersonVariances[i] = ams1[i].variance_as_double();
/* 2476 */       this.standardizedPersonStandardDeviations[i] = Math.sqrt(this.standardizedPersonVariances[i]);
/* 2477 */       this.standardizedPersonMinima[i] = ams1[i].minimum_as_double();
/* 2478 */       this.standardizedPersonMaxima[i] = ams1[i].maximum_as_double();
/* 2479 */       this.standardizedPersonRanges[i] = (this.standardizedPersonMaxima[i] - this.standardizedPersonMinima[i]);
/* 2480 */       this.standardizedPersonTotals[i] = ams1[i].sum_as_double();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 2485 */     ArrayMaths am = new ArrayMaths(this.scores0[0]);
/* 2486 */     for (int i = 1; i < this.nItems; i++) {
/* 2487 */       am = am.concatenate(this.scores0[i]);
/*      */     }
/* 2489 */     Stat ams = am.toStat();
/* 2490 */     if (this.nFactorOption) {
/* 2491 */       ams.setDenominatorToN();
/*      */     }
/*      */     else {
/* 2494 */       ams.setDenominatorToNminusOne();
/*      */     }
/* 2496 */     this.rawAllResponsesMean = ams.mean_as_double();
/* 2497 */     this.rawAllResponsesVariance = ams.variance_as_double();
/* 2498 */     this.rawAllResponsesStandardDeviation = Math.sqrt(this.rawAllResponsesVariance);
/* 2499 */     this.rawAllResponsesMinimum = ams.minimum_as_double();
/* 2500 */     this.rawAllResponsesMaximum = ams.maximum_as_double();
/* 2501 */     this.rawAllResponsesRange = (this.rawAllResponsesMaximum - this.rawAllResponsesMinimum);
/* 2502 */     this.rawAllResponsesTotal = ams.sum_as_double();
/*      */     
/* 2504 */     ArrayMaths amm = new ArrayMaths(this.standardizedScores0[0]);
/* 2505 */     for (int i = 1; i < this.nItems; i++) {
/* 2506 */       amm = amm.concatenate(this.standardizedScores0[i]);
/*      */     }
/* 2508 */     Stat amss = amm.toStat();
/* 2509 */     if (this.nFactorOption) {
/* 2510 */       amss.setDenominatorToN();
/*      */     }
/*      */     else {
/* 2513 */       amss.setDenominatorToNminusOne();
/*      */     }
/* 2515 */     this.standardizedAllResponsesMean = amss.mean_as_double();
/* 2516 */     this.standardizedAllResponsesVariance = amss.variance_as_double();
/* 2517 */     this.standardizedAllResponsesStandardDeviation = Math.sqrt(this.standardizedAllResponsesVariance);
/* 2518 */     this.standardizedAllResponsesMinimum = amss.minimum_as_double();
/* 2519 */     this.standardizedAllResponsesMaximum = amss.maximum_as_double();
/* 2520 */     this.standardizedAllResponsesRange = (this.standardizedAllResponsesMaximum - this.standardizedAllResponsesMinimum);
/* 2521 */     this.standardizedAllResponsesTotal = 0.0D;
/*      */     
/* 2523 */     this.variancesCalculated = true;
/*      */   }
/*      */   
/*      */   public double[] rawItemMeans()
/*      */   {
/* 2528 */     if (!this.dataPreprocessed) preprocessData();
/* 2529 */     if (!this.variancesCalculated) meansAndVariances();
/* 2530 */     return this.rawItemMeans;
/*      */   }
/*      */   
/*      */   public double[] standardizedItemMeans()
/*      */   {
/* 2535 */     if (!this.dataPreprocessed) preprocessData();
/* 2536 */     if (!this.variancesCalculated) meansAndVariances();
/* 2537 */     return this.standardizedItemMeans;
/*      */   }
/*      */   
/*      */   public double[] standardisedItemMeans() {
/* 2541 */     return standardizedItemMeans();
/*      */   }
/*      */   
/*      */   public double rawItemMean(String itemName)
/*      */   {
/* 2546 */     if (!this.dataPreprocessed) preprocessData();
/* 2547 */     int index = itemIndex(itemName);
/* 2548 */     if (!this.variancesCalculated) meansAndVariances();
/* 2549 */     return this.rawItemMeans[(index - 1)];
/*      */   }
/*      */   
/*      */   public double rawItemMean(int index)
/*      */   {
/* 2554 */     if (!this.dataPreprocessed) preprocessData();
/* 2555 */     if ((index < 1) || (index > this.nItems)) throw new IllegalArgumentException("The item index, " + index + ", must lie between 1 and the number of items," + this.nItems + ", inclusive");
/* 2556 */     if (!this.variancesCalculated) meansAndVariances();
/* 2557 */     return this.rawItemMeans[(index - 1)];
/*      */   }
/*      */   
/*      */   public double rawMeanOfItemMeans()
/*      */   {
/* 2562 */     return this.rawItemMeansMean;
/*      */   }
/*      */   
/*      */   public double rawStandardDeviationOfItemMeans()
/*      */   {
/* 2567 */     return this.rawItemMeansSd;
/*      */   }
/*      */   
/*      */ 
/*      */   public double rawVarianceOfItemMeans()
/*      */   {
/* 2573 */     return this.rawItemMeansVar;
/*      */   }
/*      */   
/*      */   public double rawMaximumOfItemMeans()
/*      */   {
/* 2578 */     return this.rawItemMeansMax;
/*      */   }
/*      */   
/*      */   public double rawMinimumOfItemMeans()
/*      */   {
/* 2583 */     return this.rawItemMeansMin;
/*      */   }
/*      */   
/*      */   public double rawRangeOfItemMeans()
/*      */   {
/* 2588 */     return this.rawItemMeansRange;
/*      */   }
/*      */   
/*      */   public double standardizedItemMean(String itemName)
/*      */   {
/* 2593 */     if (!this.dataPreprocessed) preprocessData();
/* 2594 */     int index = itemIndex(itemName);
/* 2595 */     if (!this.variancesCalculated) meansAndVariances();
/* 2596 */     return this.standardizedItemMeans[(index - 1)];
/*      */   }
/*      */   
/*      */   public double standardisedItemMean(String itemName) {
/* 2600 */     return standardizedItemMean(itemName);
/*      */   }
/*      */   
/*      */   public double standardizedItemMean(int index)
/*      */   {
/* 2605 */     if (!this.dataPreprocessed) preprocessData();
/* 2606 */     if ((index < 1) || (index > this.nItems)) throw new IllegalArgumentException("The item index, " + index + ", must lie between 1 and the number of items," + this.nItems + ", inclusive");
/* 2607 */     if (!this.variancesCalculated) meansAndVariances();
/* 2608 */     return this.standardizedItemMeans[(index - 1)];
/*      */   }
/*      */   
/*      */   public double standardisedItemMean(int index) {
/* 2612 */     return standardizedItemMean(index);
/*      */   }
/*      */   
/*      */   public double standardizedMeanOfItemMeans()
/*      */   {
/* 2617 */     return this.standardizedItemMeansMean;
/*      */   }
/*      */   
/*      */   public double standardisedMeanOfItemMeans() {
/* 2621 */     return this.standardizedItemMeansMean;
/*      */   }
/*      */   
/*      */   public double standardizedStanadarDeviationOfItemMeans()
/*      */   {
/* 2626 */     return this.standardizedItemMeansSd;
/*      */   }
/*      */   
/*      */   public double standardisedStanadarDeviationOfItemMeans() {
/* 2630 */     return this.standardizedItemMeansSd;
/*      */   }
/*      */   
/*      */   public double standardizedVarianceOfItemMeans()
/*      */   {
/* 2635 */     return this.standardizedItemMeansVar;
/*      */   }
/*      */   
/*      */   public double standardizedMaximumOfItemMeans()
/*      */   {
/* 2640 */     return this.standardizedItemMeansMax;
/*      */   }
/*      */   
/*      */   public double standardisedVarianceOfItemMeans() {
/* 2644 */     return this.standardizedItemMeansVar;
/*      */   }
/*      */   
/*      */   public double standardizedMinimumOfItemMeans()
/*      */   {
/* 2649 */     return this.standardizedItemMeansMin;
/*      */   }
/*      */   
/*      */   public double standardisedMinimumOfItemMeans() {
/* 2653 */     return this.standardizedItemMeansMin;
/*      */   }
/*      */   
/*      */   public double standardizedRangeOfItemMeans()
/*      */   {
/* 2658 */     return this.standardizedItemMeansRange;
/*      */   }
/*      */   
/*      */   public double standardisedRangeOfItemMeans() {
/* 2662 */     return this.standardizedItemMeansRange;
/*      */   }
/*      */   
/*      */   public double[] rawItemStandardDeviations()
/*      */   {
/* 2667 */     if (!this.dataPreprocessed) preprocessData();
/* 2668 */     if (!this.variancesCalculated) meansAndVariances();
/* 2669 */     return this.rawItemStandardDeviations;
/*      */   }
/*      */   
/*      */   public double rawItemStandardDeviation(String itemName)
/*      */   {
/* 2674 */     if (!this.dataPreprocessed) preprocessData();
/* 2675 */     int index = itemIndex(itemName);
/* 2676 */     if (!this.variancesCalculated) meansAndVariances();
/* 2677 */     return this.rawItemStandardDeviations[(index - 1)];
/*      */   }
/*      */   
/*      */   public double rawItemStandardDeviation(int index)
/*      */   {
/* 2682 */     if (!this.dataPreprocessed) preprocessData();
/* 2683 */     if ((index < 1) || (index > this.nItems)) throw new IllegalArgumentException("The item index, " + index + ", must lie between 1 and the number of items," + this.nItems + ", inclusive");
/* 2684 */     if (!this.variancesCalculated) meansAndVariances();
/* 2685 */     return this.rawItemStandardDeviations[(index - 1)];
/*      */   }
/*      */   
/*      */   public double rawMeanOfItemStandardDeviations()
/*      */   {
/* 2690 */     return this.rawItemStandardDeviationsMean;
/*      */   }
/*      */   
/*      */   public double rawStanadarDeviationOfItemStandardDeviations()
/*      */   {
/* 2695 */     return this.rawItemStandardDeviationsSd;
/*      */   }
/*      */   
/*      */   public double rawVarianceOfItemStandardDeviations()
/*      */   {
/* 2700 */     return this.rawItemStandardDeviationsVar;
/*      */   }
/*      */   
/*      */   public double rawMaximumOfItemStandardDeviations()
/*      */   {
/* 2705 */     return this.rawItemStandardDeviationsMax;
/*      */   }
/*      */   
/*      */   public double rawMinimumOfItemStandardDeviations()
/*      */   {
/* 2710 */     return this.rawItemStandardDeviationsMin;
/*      */   }
/*      */   
/*      */   public double rawRangeOfItemStandardDeviations()
/*      */   {
/* 2715 */     return this.rawItemStandardDeviationsRange;
/*      */   }
/*      */   
/*      */   public double[] standardizedItemStandardDeviations()
/*      */   {
/* 2720 */     if (!this.dataPreprocessed) preprocessData();
/* 2721 */     if (!this.variancesCalculated) meansAndVariances();
/* 2722 */     return this.standardizedItemStandardDeviations;
/*      */   }
/*      */   
/*      */   public double[] standardisedItemStandardDeviations() {
/* 2726 */     return standardizedItemStandardDeviations();
/*      */   }
/*      */   
/*      */   public double standardizedItemStandardDeviation(String itemName)
/*      */   {
/* 2731 */     if (!this.dataPreprocessed) preprocessData();
/* 2732 */     int index = itemIndex(itemName);
/* 2733 */     if (!this.variancesCalculated) meansAndVariances();
/* 2734 */     return this.standardizedItemStandardDeviations[(index - 1)];
/*      */   }
/*      */   
/*      */   public double standardisedItemStandardDeviation(String itemName) {
/* 2738 */     return standardizedItemStandardDeviation(itemName);
/*      */   }
/*      */   
/*      */   public double standardizedItemStandardDeviation(int index)
/*      */   {
/* 2743 */     if (!this.dataPreprocessed) preprocessData();
/* 2744 */     if ((index < 1) || (index > this.nItems)) throw new IllegalArgumentException("The item index, " + index + ", must lie between 1 and the number of items," + this.nItems + ", inclusive");
/* 2745 */     if (!this.variancesCalculated) meansAndVariances();
/* 2746 */     return this.standardizedItemStandardDeviations[(index - 1)];
/*      */   }
/*      */   
/*      */   public double standardisedItemStandardDeviation(int index) {
/* 2750 */     return standardizedItemStandardDeviation(index);
/*      */   }
/*      */   
/*      */   public double standardizedMeanOfItemStandardDeviations()
/*      */   {
/* 2755 */     return this.standardizedItemStandardDeviationsMean;
/*      */   }
/*      */   
/*      */   public double standardisedMeanOfItemStandardDeviations() {
/* 2759 */     return this.standardizedItemStandardDeviationsMean;
/*      */   }
/*      */   
/*      */   public double standardizedStanadarDeviationOfItemStandardDeviations()
/*      */   {
/* 2764 */     return this.standardizedItemStandardDeviationsSd;
/*      */   }
/*      */   
/*      */   public double standardisedStanadarDeviationOfItemStandardDeviations() {
/* 2768 */     return this.standardizedItemStandardDeviationsSd;
/*      */   }
/*      */   
/*      */   public double standardizedVarianceOfItemStandardDeviations()
/*      */   {
/* 2773 */     return this.standardizedItemStandardDeviationsVar;
/*      */   }
/*      */   
/*      */   public double standardisedVarianceOfItemStandardDeviations() {
/* 2777 */     return this.standardizedItemStandardDeviationsVar;
/*      */   }
/*      */   
/*      */   public double standardizedMaximumOfItemStandardDeviations()
/*      */   {
/* 2782 */     return this.standardizedItemStandardDeviationsMax;
/*      */   }
/*      */   
/*      */   public double standardisedMaximumOfItemStandardDeviations() {
/* 2786 */     return this.standardizedItemStandardDeviationsMax;
/*      */   }
/*      */   
/*      */   public double standardizedMinimumOfItemStandardDeviations()
/*      */   {
/* 2791 */     return this.standardizedItemStandardDeviationsMin;
/*      */   }
/*      */   
/*      */   public double standardisedMinimumOfItemStandardDeviations() {
/* 2795 */     return this.standardizedItemStandardDeviationsMin;
/*      */   }
/*      */   
/*      */   public double standardizedRangeOfItemStandardDeviations()
/*      */   {
/* 2800 */     return this.standardizedItemStandardDeviationsRange;
/*      */   }
/*      */   
/*      */   public double standardisedRangeOfItemStandardDeviations() {
/* 2804 */     return this.standardizedItemStandardDeviationsRange;
/*      */   }
/*      */   
/*      */   public double[] rawItemVariances()
/*      */   {
/* 2809 */     if (!this.dataPreprocessed) preprocessData();
/* 2810 */     if (!this.variancesCalculated) meansAndVariances();
/* 2811 */     return this.rawItemVariances;
/*      */   }
/*      */   
/*      */   public double[] standardizedItemVariances()
/*      */   {
/* 2816 */     if (!this.dataPreprocessed) preprocessData();
/* 2817 */     if (!this.variancesCalculated) meansAndVariances();
/* 2818 */     return this.standardizedItemVariances;
/*      */   }
/*      */   
/*      */   public double[] standardisedItemVariances()
/*      */   {
/* 2823 */     return standardizedItemVariances();
/*      */   }
/*      */   
/*      */   public double rawItemVariance(String itemName)
/*      */   {
/* 2828 */     if (!this.dataPreprocessed) preprocessData();
/* 2829 */     int index = itemIndex(itemName);
/* 2830 */     if (!this.variancesCalculated) meansAndVariances();
/* 2831 */     return this.rawItemVariances[(index - 1)];
/*      */   }
/*      */   
/*      */   public double rawItemVariance(int index)
/*      */   {
/* 2836 */     if (!this.dataPreprocessed) preprocessData();
/* 2837 */     if ((index < 1) || (index > this.nItems)) throw new IllegalArgumentException("The item index, " + index + ", must lie between 1 and the number of items," + this.nItems + ", inclusive");
/* 2838 */     if (!this.variancesCalculated) meansAndVariances();
/* 2839 */     return this.rawItemVariances[(index - 1)];
/*      */   }
/*      */   
/*      */   public double standardizedItemVariance(String itemName)
/*      */   {
/* 2844 */     if (!this.dataPreprocessed) preprocessData();
/* 2845 */     int index = itemIndex(itemName);
/* 2846 */     if (!this.variancesCalculated) meansAndVariances();
/* 2847 */     return this.standardizedItemVariances[(index - 1)];
/*      */   }
/*      */   
/*      */   public double standardisedItemVariance(String itemName) {
/* 2851 */     return standardizedItemVariance(itemName);
/*      */   }
/*      */   
/*      */   public double standardizedItemVariance(int index)
/*      */   {
/* 2856 */     if (!this.dataPreprocessed) preprocessData();
/* 2857 */     if ((index < 1) || (index > this.nItems)) throw new IllegalArgumentException("The item index, " + index + ", must lie between 1 and the number of items," + this.nItems + ", inclusive");
/* 2858 */     if (!this.variancesCalculated) meansAndVariances();
/* 2859 */     return this.standardizedItemVariances[(index - 1)];
/*      */   }
/*      */   
/*      */   public double standardisedItemVariance(int index) {
/* 2863 */     return standardizedItemVariance(index);
/*      */   }
/*      */   
/*      */   public double rawMeanOfItemVariances()
/*      */   {
/* 2868 */     return this.rawItemVariancesMean;
/*      */   }
/*      */   
/*      */   public double rawStanadarDeviationOfItemVariances()
/*      */   {
/* 2873 */     return this.rawItemVariancesSd;
/*      */   }
/*      */   
/*      */   public double rawVarianceOfItemVariances()
/*      */   {
/* 2878 */     return this.rawItemVariancesVar;
/*      */   }
/*      */   
/*      */   public double rawMaximumOfItemVariances()
/*      */   {
/* 2883 */     return this.rawItemVariancesMax;
/*      */   }
/*      */   
/*      */   public double rawMinimumOfItemVariances()
/*      */   {
/* 2888 */     return this.rawItemVariancesMin;
/*      */   }
/*      */   
/*      */   public double rawRangeOfItemVariances()
/*      */   {
/* 2893 */     return this.rawItemVariancesRange;
/*      */   }
/*      */   
/*      */   public double standardizedMeanOfItemVariances()
/*      */   {
/* 2898 */     return this.standardizedItemVariancesMean;
/*      */   }
/*      */   
/*      */   public double standardisedMeanOfItemVariances() {
/* 2902 */     return this.standardizedItemVariancesMean;
/*      */   }
/*      */   
/*      */   public double standardizedStanadarDeviationOfItemVariances()
/*      */   {
/* 2907 */     return this.standardizedItemVariancesSd;
/*      */   }
/*      */   
/*      */   public double standardisedStanadarDeviationOfItemVariances() {
/* 2911 */     return this.standardizedItemVariancesSd;
/*      */   }
/*      */   
/*      */   public double standardizedVarianceOfItemVariances()
/*      */   {
/* 2916 */     return this.standardizedItemVariancesVar;
/*      */   }
/*      */   
/*      */   public double standardisedVarianceOfItemVariances() {
/* 2920 */     return this.standardizedItemVariancesVar;
/*      */   }
/*      */   
/*      */   public double standardizedMaximumOfItemVariances()
/*      */   {
/* 2925 */     return this.standardizedItemVariancesMax;
/*      */   }
/*      */   
/*      */   public double standardisedMaximumOfItemVariances() {
/* 2929 */     return this.standardizedItemVariancesMax;
/*      */   }
/*      */   
/*      */   public double standardizedMinimumOfItemVariances()
/*      */   {
/* 2934 */     return this.standardizedItemVariancesMin;
/*      */   }
/*      */   
/*      */   public double standardisedMinimumOfItemVariances() {
/* 2938 */     return this.standardizedItemVariancesMin;
/*      */   }
/*      */   
/*      */   public double standardizedRangeOfItemVariances()
/*      */   {
/* 2943 */     return this.standardizedItemVariancesRange;
/*      */   }
/*      */   
/*      */   public double standardisedRangeOfItemVariances() {
/* 2947 */     return this.standardizedItemVariancesRange;
/*      */   }
/*      */   
/*      */   public double[] rawItemMinima()
/*      */   {
/* 2952 */     if (!this.dataPreprocessed) preprocessData();
/* 2953 */     if (!this.variancesCalculated) meansAndVariances();
/* 2954 */     return this.rawItemMinima;
/*      */   }
/*      */   
/*      */   public double[] standardizedItemMinima()
/*      */   {
/* 2959 */     if (!this.dataPreprocessed) preprocessData();
/* 2960 */     if (!this.variancesCalculated) meansAndVariances();
/* 2961 */     return this.standardizedItemMinima;
/*      */   }
/*      */   
/*      */   public double[] standardisedItemMinima() {
/* 2965 */     return standardizedItemMinima();
/*      */   }
/*      */   
/*      */   public double rawItemMinimum(int index)
/*      */   {
/* 2970 */     if (!this.dataPreprocessed) preprocessData();
/* 2971 */     if ((index < 1) || (index > this.nItems)) throw new IllegalArgumentException("The item index, " + index + ", must lie between 1 and the number of items," + this.nItems + ", inclusive");
/* 2972 */     if (!this.variancesCalculated) meansAndVariances();
/* 2973 */     return this.rawItemMinima[(index - 1)];
/*      */   }
/*      */   
/*      */   public double standardizedItemMinimum(int index)
/*      */   {
/* 2978 */     if (!this.dataPreprocessed) preprocessData();
/* 2979 */     if ((index < 1) || (index > this.nItems)) throw new IllegalArgumentException("The item index, " + index + ", must lie between 1 and the number of items," + this.nItems + ", inclusive");
/* 2980 */     if (!this.variancesCalculated) meansAndVariances();
/* 2981 */     return this.standardizedItemMinima[(index - 1)];
/*      */   }
/*      */   
/*      */   public double standardisedItemMinimum(int index) {
/* 2985 */     return standardizedItemMinimum(index);
/*      */   }
/*      */   
/*      */   public double rawMeanOfItemMinima()
/*      */   {
/* 2990 */     return this.rawItemMinimaMean;
/*      */   }
/*      */   
/*      */   public double rawStanadarDeviationOfItemMinima()
/*      */   {
/* 2995 */     return this.rawItemMinimaSd;
/*      */   }
/*      */   
/*      */   public double rawVarianceOfItemMinima()
/*      */   {
/* 3000 */     return this.rawItemMinimaVar;
/*      */   }
/*      */   
/*      */   public double rawMaximumOfItemMinima()
/*      */   {
/* 3005 */     return this.rawItemMinimaMax;
/*      */   }
/*      */   
/*      */   public double rawMinimumOfItemMinima()
/*      */   {
/* 3010 */     return this.rawItemMinimaMin;
/*      */   }
/*      */   
/*      */   public double rawRangeOfItemMinima()
/*      */   {
/* 3015 */     return this.rawItemMinimaRange;
/*      */   }
/*      */   
/*      */   public double standardizedMeanOfItemMinima()
/*      */   {
/* 3020 */     return this.standardizedItemMinimaMean;
/*      */   }
/*      */   
/*      */   public double standardisedMeanOfItemMinima() {
/* 3024 */     return this.standardizedItemMinimaMean;
/*      */   }
/*      */   
/*      */   public double standardizedStanadarDeviationOfItemMinima()
/*      */   {
/* 3029 */     return this.standardizedItemMinimaSd;
/*      */   }
/*      */   
/*      */   public double standardisedStanadarDeviationOfItemMinima() {
/* 3033 */     return this.standardizedItemMinimaSd;
/*      */   }
/*      */   
/*      */   public double standardizedVarianceOfItemMinima()
/*      */   {
/* 3038 */     return this.standardizedItemMinimaVar;
/*      */   }
/*      */   
/*      */   public double standardisedVarianceOfItemMinima() {
/* 3042 */     return this.standardizedItemMinimaVar;
/*      */   }
/*      */   
/*      */   public double standardizedMaximumOfItemMinima()
/*      */   {
/* 3047 */     return this.standardizedItemMinimaMax;
/*      */   }
/*      */   
/*      */   public double standardisedMaximumOfItemMinima() {
/* 3051 */     return this.standardizedItemMinimaMax;
/*      */   }
/*      */   
/*      */   public double standardizedMinimumOfItemMinima()
/*      */   {
/* 3056 */     return this.standardizedItemMinimaMin;
/*      */   }
/*      */   
/*      */   public double standardisedMinimumOfItemMinima() {
/* 3060 */     return this.standardizedItemMinimaMin;
/*      */   }
/*      */   
/*      */   public double standardizedRangeOfItemMinima()
/*      */   {
/* 3065 */     return this.standardizedItemMinimaRange;
/*      */   }
/*      */   
/*      */   public double standardisedRangeOfItemMinima() {
/* 3069 */     return this.standardizedItemMinimaRange;
/*      */   }
/*      */   
/*      */   public double[] rawItemMaxima()
/*      */   {
/* 3074 */     if (!this.dataPreprocessed) preprocessData();
/* 3075 */     if (!this.variancesCalculated) meansAndVariances();
/* 3076 */     return this.rawItemMaxima;
/*      */   }
/*      */   
/*      */   public double[] standardizedItemMaxima()
/*      */   {
/* 3081 */     if (!this.dataPreprocessed) preprocessData();
/* 3082 */     if (!this.variancesCalculated) meansAndVariances();
/* 3083 */     return this.standardizedItemMaxima;
/*      */   }
/*      */   
/*      */   public double[] standardisedItemMaxima() {
/* 3087 */     return standardizedItemMaxima();
/*      */   }
/*      */   
/*      */   public double rawItemMaximum(int index)
/*      */   {
/* 3092 */     if (!this.dataPreprocessed) preprocessData();
/* 3093 */     if ((index < 1) || (index > this.nItems)) throw new IllegalArgumentException("The item index, " + index + ", must lie between 1 and the number of items," + this.nItems + ", inclusive");
/* 3094 */     if (!this.variancesCalculated) meansAndVariances();
/* 3095 */     return this.rawItemMaxima[(index - 1)];
/*      */   }
/*      */   
/*      */   public double standardizedItemMaximum(int index)
/*      */   {
/* 3100 */     if (!this.dataPreprocessed) preprocessData();
/* 3101 */     if ((index < 1) || (index > this.nItems)) throw new IllegalArgumentException("The item index, " + index + ", must lie between 1 and the number of items," + this.nItems + ", inclusive");
/* 3102 */     if (!this.variancesCalculated) meansAndVariances();
/* 3103 */     return this.standardizedItemMaxima[(index - 1)];
/*      */   }
/*      */   
/*      */   public double standardisedItemMaximum(int index) {
/* 3107 */     return standardizedItemMaximum(index);
/*      */   }
/*      */   
/*      */   public double rawMeanOfItemMaxima()
/*      */   {
/* 3112 */     return this.rawItemMaximaMean;
/*      */   }
/*      */   
/*      */   public double rawStanadarDeviationOfItemMaxima()
/*      */   {
/* 3117 */     return this.rawItemMaximaSd;
/*      */   }
/*      */   
/*      */   public double rawVarianceOfItemMaxima()
/*      */   {
/* 3122 */     return this.rawItemMaximaVar;
/*      */   }
/*      */   
/*      */   public double rawMaximumOfItemMaxima()
/*      */   {
/* 3127 */     return this.rawItemMaximaMax;
/*      */   }
/*      */   
/*      */   public double rawMinimumOfItemMaxima()
/*      */   {
/* 3132 */     return this.rawItemMaximaMin;
/*      */   }
/*      */   
/*      */   public double rawRangeOfItemMaxima()
/*      */   {
/* 3137 */     return this.rawItemMaximaRange;
/*      */   }
/*      */   
/*      */   public double standardizedMeanOfItemMaxima()
/*      */   {
/* 3142 */     return this.standardizedItemMaximaMean;
/*      */   }
/*      */   
/*      */   public double standardisedMeanOfItemMaxima() {
/* 3146 */     return this.standardizedItemMaximaMean;
/*      */   }
/*      */   
/*      */   public double standardizedStanadarDeviationOfItemMaxima()
/*      */   {
/* 3151 */     return this.standardizedItemMaximaSd;
/*      */   }
/*      */   
/*      */   public double standardisedStanadarDeviationOfItemMaxima() {
/* 3155 */     return this.standardizedItemMaximaSd;
/*      */   }
/*      */   
/*      */   public double standardizedVarianceOfItemMaxima()
/*      */   {
/* 3160 */     return this.standardizedItemMaximaVar;
/*      */   }
/*      */   
/*      */   public double standardisedVarianceOfItemMaxima() {
/* 3164 */     return this.standardizedItemMaximaVar;
/*      */   }
/*      */   
/*      */   public double standardizedMaximumOfItemMaxima()
/*      */   {
/* 3169 */     return this.standardizedItemMaximaMax;
/*      */   }
/*      */   
/*      */   public double standardisedMaximumOfItemMaxima() {
/* 3173 */     return this.standardizedItemMaximaMax;
/*      */   }
/*      */   
/*      */   public double standardizedMinimumOfItemMaxima()
/*      */   {
/* 3178 */     return this.standardizedItemMaximaMin;
/*      */   }
/*      */   
/*      */   public double standardisedMinimumOfItemMaxima() {
/* 3182 */     return this.standardizedItemMaximaMin;
/*      */   }
/*      */   
/*      */   public double standardizedRangeOfItemMaxima()
/*      */   {
/* 3187 */     return this.standardizedItemMaximaRange;
/*      */   }
/*      */   
/*      */   public double standardisedRangeOfItemMaxima() {
/* 3191 */     return this.standardizedItemMaximaRange;
/*      */   }
/*      */   
/*      */   public double[] rawItemRanges()
/*      */   {
/* 3196 */     if (!this.dataPreprocessed) preprocessData();
/* 3197 */     if (!this.variancesCalculated) meansAndVariances();
/* 3198 */     return this.rawItemRanges;
/*      */   }
/*      */   
/*      */   public double[] standardizedItemRanges()
/*      */   {
/* 3203 */     if (!this.dataPreprocessed) preprocessData();
/* 3204 */     if (!this.variancesCalculated) meansAndVariances();
/* 3205 */     return this.standardizedItemRanges;
/*      */   }
/*      */   
/*      */   public double[] standardisedItemRanges() {
/* 3209 */     return standardizedItemRanges();
/*      */   }
/*      */   
/*      */   public double rawItemRange(int index)
/*      */   {
/* 3214 */     if (!this.dataPreprocessed) preprocessData();
/* 3215 */     if ((index < 1) || (index > this.nItems)) throw new IllegalArgumentException("The item index, " + index + ", must lie between 1 and the number of items," + this.nItems + ", inclusive");
/* 3216 */     if (!this.variancesCalculated) meansAndVariances();
/* 3217 */     return this.rawItemRanges[(index - 1)];
/*      */   }
/*      */   
/*      */   public double standardizedItemRange(int index)
/*      */   {
/* 3222 */     if (!this.dataPreprocessed) preprocessData();
/* 3223 */     if ((index < 1) || (index > this.nItems)) throw new IllegalArgumentException("The item index, " + index + ", must lie between 1 and the number of items," + this.nItems + ", inclusive");
/* 3224 */     if (!this.variancesCalculated) meansAndVariances();
/* 3225 */     return this.standardizedItemRanges[(index - 1)];
/*      */   }
/*      */   
/*      */   public double standardisedItemRange(int index) {
/* 3229 */     return standardizedItemRange(index);
/*      */   }
/*      */   
/*      */   public double rawMeanOfItemRanges()
/*      */   {
/* 3234 */     return this.rawItemRangesMean;
/*      */   }
/*      */   
/*      */   public double rawStanadarDeviationOfItemRanges()
/*      */   {
/* 3239 */     return this.rawItemRangesSd;
/*      */   }
/*      */   
/*      */   public double rawVarianceOfItemRanges()
/*      */   {
/* 3244 */     return this.rawItemRangesVar;
/*      */   }
/*      */   
/*      */   public double rawMaximumOfItemRanges()
/*      */   {
/* 3249 */     return this.rawItemRangesMax;
/*      */   }
/*      */   
/*      */   public double rawMinimumOfItemRanges()
/*      */   {
/* 3254 */     return this.rawItemRangesMin;
/*      */   }
/*      */   
/*      */   public double rawRangeOfItemRanges()
/*      */   {
/* 3259 */     return this.rawItemRangesRange;
/*      */   }
/*      */   
/*      */   public double standardizedMeanOfItemRanges()
/*      */   {
/* 3264 */     return this.standardizedItemRangesMean;
/*      */   }
/*      */   
/*      */   public double standardisedMeanOfItemRanges() {
/* 3268 */     return this.standardizedItemRangesMean;
/*      */   }
/*      */   
/*      */   public double standardizedStanadarDeviationOfItemRanges()
/*      */   {
/* 3273 */     return this.standardizedItemRangesSd;
/*      */   }
/*      */   
/*      */   public double standardisedStanadarDeviationOfItemRanges() {
/* 3277 */     return this.standardizedItemRangesSd;
/*      */   }
/*      */   
/*      */   public double standardizedVarianceOfItemRanges()
/*      */   {
/* 3282 */     return this.standardizedItemRangesVar;
/*      */   }
/*      */   
/*      */   public double standardisedVarianceOfItemRanges() {
/* 3286 */     return this.standardizedItemRangesVar;
/*      */   }
/*      */   
/*      */   public double standardizedMaximumOfItemRanges()
/*      */   {
/* 3291 */     return this.standardizedItemRangesMax;
/*      */   }
/*      */   
/*      */   public double standardisedMaximumOfItemRanges() {
/* 3295 */     return this.standardizedItemRangesMax;
/*      */   }
/*      */   
/*      */   public double standardizedMinimumOfItemRanges()
/*      */   {
/* 3300 */     return this.standardizedItemRangesMin;
/*      */   }
/*      */   
/*      */   public double standardisedMinimumOfItemRanges() {
/* 3304 */     return this.standardizedItemRangesMin;
/*      */   }
/*      */   
/*      */   public double standardizedRangeOfItemRanges()
/*      */   {
/* 3309 */     return this.standardizedItemRangesRange;
/*      */   }
/*      */   
/*      */   public double standardisedRangeOfItemRanges() {
/* 3313 */     return this.standardizedItemRangesRange;
/*      */   }
/*      */   
/*      */   public double[] rawItemTotals()
/*      */   {
/* 3318 */     if (!this.dataPreprocessed) preprocessData();
/* 3319 */     if (!this.variancesCalculated) meansAndVariances();
/* 3320 */     return this.rawItemTotals;
/*      */   }
/*      */   
/*      */   public double[] standardizedItemTotals()
/*      */   {
/* 3325 */     if (!this.dataPreprocessed) preprocessData();
/* 3326 */     if (!this.variancesCalculated) meansAndVariances();
/* 3327 */     return this.standardizedItemTotals;
/*      */   }
/*      */   
/*      */   public double[] standardisedItemTotals() {
/* 3331 */     return standardizedItemTotals();
/*      */   }
/*      */   
/*      */   public double rawItemTotal(String itemName)
/*      */   {
/* 3336 */     if (!this.dataPreprocessed) preprocessData();
/* 3337 */     int index = itemIndex(itemName);
/* 3338 */     if (!this.variancesCalculated) meansAndVariances();
/* 3339 */     return this.rawItemTotals[(index - 1)];
/*      */   }
/*      */   
/*      */   public double rawItemTotal(int index)
/*      */   {
/* 3344 */     if (!this.dataPreprocessed) preprocessData();
/* 3345 */     if ((index < 1) || (index > this.nItems)) throw new IllegalArgumentException("The item index, " + index + ", must lie between 1 and the number of items," + this.nItems + ", inclusive");
/* 3346 */     if (!this.variancesCalculated) meansAndVariances();
/* 3347 */     return this.rawItemTotals[(index - 1)];
/*      */   }
/*      */   
/*      */   public double standardizedItemTotal(String itemName)
/*      */   {
/* 3352 */     if (!this.dataPreprocessed) preprocessData();
/* 3353 */     int index = itemIndex(itemName);
/* 3354 */     if (!this.variancesCalculated) meansAndVariances();
/* 3355 */     return this.standardizedItemTotals[(index - 1)];
/*      */   }
/*      */   
/*      */   public double standardisedItemTotal(String itemName) {
/* 3359 */     return standardizedItemTotal(itemName);
/*      */   }
/*      */   
/*      */   public double standardizedItemTotal(int index)
/*      */   {
/* 3364 */     if (!this.dataPreprocessed) preprocessData();
/* 3365 */     if ((index < 1) || (index > this.nItems)) throw new IllegalArgumentException("The item index, " + index + ", must lie between 1 and the number of items," + this.nItems + ", inclusive");
/* 3366 */     if (!this.variancesCalculated) meansAndVariances();
/* 3367 */     return this.standardizedItemTotals[(index - 1)];
/*      */   }
/*      */   
/*      */   public double standardisedItemTotal(int index) {
/* 3371 */     return standardizedItemTotal(index);
/*      */   }
/*      */   
/*      */   public double rawMeanOfItemTotals()
/*      */   {
/* 3376 */     return this.rawItemTotalsMean;
/*      */   }
/*      */   
/*      */   public double rawStanadarDeviationOfItemTotals()
/*      */   {
/* 3381 */     return this.rawItemTotalsSd;
/*      */   }
/*      */   
/*      */   public double rawVarianceOfItemTotals()
/*      */   {
/* 3386 */     return this.rawItemTotalsVar;
/*      */   }
/*      */   
/*      */   public double rawMaximumOfItemTotals()
/*      */   {
/* 3391 */     return this.rawItemTotalsMax;
/*      */   }
/*      */   
/*      */   public double rawMinimumOfItemTotals()
/*      */   {
/* 3396 */     return this.rawItemTotalsMin;
/*      */   }
/*      */   
/*      */   public double rawRangeOfItemTotals()
/*      */   {
/* 3401 */     return this.rawItemTotalsRange;
/*      */   }
/*      */   
/*      */   public double standardizedMeanOfItemTotals()
/*      */   {
/* 3406 */     return this.standardizedItemTotalsMean;
/*      */   }
/*      */   
/*      */   public double standardisedMeanOfItemTotals() {
/* 3410 */     return this.standardizedItemTotalsMean;
/*      */   }
/*      */   
/*      */   public double standardizedStanadarDeviationOfItemTotals()
/*      */   {
/* 3415 */     return this.standardizedItemTotalsSd;
/*      */   }
/*      */   
/*      */   public double standardisedStanadarDeviationOfItemTotals() {
/* 3419 */     return this.standardizedItemTotalsSd;
/*      */   }
/*      */   
/*      */   public double standardizedVarianceOfItemTotals()
/*      */   {
/* 3424 */     return this.standardizedItemTotalsVar;
/*      */   }
/*      */   
/*      */   public double standardisedVarianceOfItemTotals() {
/* 3428 */     return this.standardizedItemTotalsVar;
/*      */   }
/*      */   
/*      */   public double standardizedMaximumOfItemTotals()
/*      */   {
/* 3433 */     return this.standardizedItemTotalsMax;
/*      */   }
/*      */   
/*      */   public double standardisedMaximumOfItemTotals() {
/* 3437 */     return this.standardizedItemTotalsMax;
/*      */   }
/*      */   
/*      */   public double standardizedMinimumOfItemTotals()
/*      */   {
/* 3442 */     return this.standardizedItemTotalsMin;
/*      */   }
/*      */   
/*      */   public double standardisedMinimumOfItemTotals() {
/* 3446 */     return this.standardizedItemTotalsMin;
/*      */   }
/*      */   
/*      */   public double standardizedRangeOfItemTotals()
/*      */   {
/* 3451 */     return this.standardizedItemTotalsRange;
/*      */   }
/*      */   
/*      */   public double standardisedRangeOfItemTotals() {
/* 3455 */     return this.standardizedItemTotalsRange;
/*      */   }
/*      */   
/*      */   public double[] rawPersonMeans()
/*      */   {
/* 3460 */     if (!this.dataPreprocessed) preprocessData();
/* 3461 */     if (!this.variancesCalculated) meansAndVariances();
/* 3462 */     return this.rawPersonMeans;
/*      */   }
/*      */   
/*      */   public double[] standardizedPersonMeans()
/*      */   {
/* 3467 */     if (!this.dataPreprocessed) preprocessData();
/* 3468 */     if (!this.variancesCalculated) meansAndVariances();
/* 3469 */     return this.standardizedPersonMeans;
/*      */   }
/*      */   
/*      */   public double[] standardisedPersonMeans() {
/* 3473 */     return standardizedPersonMeans();
/*      */   }
/*      */   
/*      */   public double rawPersonMean(int index)
/*      */   {
/* 3478 */     if (!this.dataPreprocessed) preprocessData();
/* 3479 */     if ((index < 1) || (index > this.nPersons)) throw new IllegalArgumentException("The person index, " + index + ", must lie between 1 and the number of persons," + this.nPersons + ", inclusive");
/* 3480 */     if (!this.variancesCalculated) meansAndVariances();
/* 3481 */     return this.rawPersonMeans[(index - 1)];
/*      */   }
/*      */   
/*      */   public double standardizedPersonMean(int index)
/*      */   {
/* 3486 */     if (!this.dataPreprocessed) preprocessData();
/* 3487 */     if ((index < 1) || (index > this.nPersons)) throw new IllegalArgumentException("The person index, " + index + ", must lie between 1 and the number of persons," + this.nPersons + ", inclusive");
/* 3488 */     if (!this.variancesCalculated) meansAndVariances();
/* 3489 */     return this.standardizedPersonMeans[(index - 1)];
/*      */   }
/*      */   
/*      */   public double standardisedPersonMean(int index) {
/* 3493 */     return standardizedPersonMean(index);
/*      */   }
/*      */   
/*      */   public double[] rawPersonStandardDeviations()
/*      */   {
/* 3498 */     if (!this.dataPreprocessed) preprocessData();
/* 3499 */     if (!this.variancesCalculated) meansAndVariances();
/* 3500 */     return this.rawPersonStandardDeviations;
/*      */   }
/*      */   
/*      */   public double[] standardizedPersonStandardDeviations()
/*      */   {
/* 3505 */     if (!this.dataPreprocessed) preprocessData();
/* 3506 */     if (!this.variancesCalculated) meansAndVariances();
/* 3507 */     return this.standardizedPersonStandardDeviations;
/*      */   }
/*      */   
/*      */   public double[] standardisedPersonStandardDeviations() {
/* 3511 */     return standardizedPersonStandardDeviations();
/*      */   }
/*      */   
/*      */   public double rawPersonStandardDeviation(int index)
/*      */   {
/* 3516 */     if (!this.dataPreprocessed) preprocessData();
/* 3517 */     if ((index < 1) || (index > this.nPersons)) throw new IllegalArgumentException("The person index, " + index + ", must lie between 1 and the number of persons," + this.nPersons + ", inclusive");
/* 3518 */     if (!this.variancesCalculated) meansAndVariances();
/* 3519 */     return this.rawPersonStandardDeviations[(index - 1)];
/*      */   }
/*      */   
/*      */   public double standardizedPersonStandardDeviation(int index)
/*      */   {
/* 3524 */     if (!this.dataPreprocessed) preprocessData();
/* 3525 */     if ((index < 1) || (index > this.nPersons)) throw new IllegalArgumentException("The person index, " + index + ", must lie between 1 and the number of persons," + this.nPersons + ", inclusive");
/* 3526 */     if (!this.variancesCalculated) meansAndVariances();
/* 3527 */     return this.standardizedPersonStandardDeviations[(index - 1)];
/*      */   }
/*      */   
/*      */   public double standardisedPersonStandardDeviation(int index) {
/* 3531 */     return standardizedPersonStandardDeviation(index);
/*      */   }
/*      */   
/*      */   public double[] rawPersonVariances()
/*      */   {
/* 3536 */     if (!this.dataPreprocessed) preprocessData();
/* 3537 */     if (!this.variancesCalculated) meansAndVariances();
/* 3538 */     return this.rawPersonVariances;
/*      */   }
/*      */   
/*      */   public double[] standardizedPersonVariances()
/*      */   {
/* 3543 */     if (!this.dataPreprocessed) preprocessData();
/* 3544 */     if (!this.variancesCalculated) meansAndVariances();
/* 3545 */     return this.standardizedPersonVariances;
/*      */   }
/*      */   
/*      */   public double[] standardisedPersonVariances() {
/* 3549 */     return standardizedPersonVariances();
/*      */   }
/*      */   
/*      */   public double rawPersonVariance(int index)
/*      */   {
/* 3554 */     if (!this.dataPreprocessed) preprocessData();
/* 3555 */     if ((index < 1) || (index > this.nPersons)) throw new IllegalArgumentException("The person index, " + index + ", must lie between 1 and the number of persons," + this.nPersons + ", inclusive");
/* 3556 */     if (!this.variancesCalculated) meansAndVariances();
/* 3557 */     return this.rawPersonVariances[(index - 1)];
/*      */   }
/*      */   
/*      */   public double standardizedPersonVariance(int index)
/*      */   {
/* 3562 */     if (!this.dataPreprocessed) preprocessData();
/* 3563 */     if ((index < 1) || (index > this.nPersons)) throw new IllegalArgumentException("The person index, " + index + ", must lie between 1 and the number of persons," + this.nPersons + ", inclusive");
/* 3564 */     if (!this.variancesCalculated) meansAndVariances();
/* 3565 */     return this.standardizedPersonVariances[(index - 1)];
/*      */   }
/*      */   
/*      */   public double standardisedPersonVariance(int index) {
/* 3569 */     return standardizedPersonVariance(index);
/*      */   }
/*      */   
/*      */   public double[] rawPersonMinima()
/*      */   {
/* 3574 */     if (!this.dataPreprocessed) preprocessData();
/* 3575 */     if (!this.variancesCalculated) meansAndVariances();
/* 3576 */     return this.rawPersonMinima;
/*      */   }
/*      */   
/*      */   public double[] standardizedPersonMinima()
/*      */   {
/* 3581 */     if (!this.dataPreprocessed) preprocessData();
/* 3582 */     if (!this.variancesCalculated) meansAndVariances();
/* 3583 */     return this.standardizedPersonMinima;
/*      */   }
/*      */   
/*      */   public double[] standardisedPersonMinima() {
/* 3587 */     return standardisedPersonMinima();
/*      */   }
/*      */   
/*      */   public double rawPersonMinimum(int index)
/*      */   {
/* 3592 */     if (!this.dataPreprocessed) preprocessData();
/* 3593 */     if ((index < 1) || (index > this.nItems)) throw new IllegalArgumentException("The person index, " + index + ", must lie between 1 and the number of persons," + this.nPersons + ", inclusive");
/* 3594 */     if (!this.variancesCalculated) meansAndVariances();
/* 3595 */     return this.rawPersonMinima[(index - 1)];
/*      */   }
/*      */   
/*      */   public double standardizedPersonMinimum(int index)
/*      */   {
/* 3600 */     if (!this.dataPreprocessed) preprocessData();
/* 3601 */     if ((index < 1) || (index > this.nPersons)) throw new IllegalArgumentException("The person index, " + index + ", must lie between 1 and the number of persons," + this.nPersons + ", inclusive");
/* 3602 */     if (!this.variancesCalculated) meansAndVariances();
/* 3603 */     return this.standardizedPersonMinima[(index - 1)];
/*      */   }
/*      */   
/*      */   public double standardisedPersonMinimum(int index) {
/* 3607 */     return standardizedPersonMinimum(index);
/*      */   }
/*      */   
/*      */   public double[] rawPersonMaxima()
/*      */   {
/* 3612 */     if (!this.dataPreprocessed) preprocessData();
/* 3613 */     if (!this.variancesCalculated) meansAndVariances();
/* 3614 */     return this.rawPersonMaxima;
/*      */   }
/*      */   
/*      */   public double[] standardizedPersonMaxima()
/*      */   {
/* 3619 */     if (!this.dataPreprocessed) preprocessData();
/* 3620 */     if (!this.variancesCalculated) meansAndVariances();
/* 3621 */     return this.standardizedPersonMaxima;
/*      */   }
/*      */   
/*      */   public double[] standardisedPersonMaxima() {
/* 3625 */     return standardizedPersonMaxima();
/*      */   }
/*      */   
/*      */   public double rawPersonMaximum(int index)
/*      */   {
/* 3630 */     if (!this.dataPreprocessed) preprocessData();
/* 3631 */     if ((index < 1) || (index > this.nItems)) throw new IllegalArgumentException("The person index, " + index + ", must lie between 1 and the number of persons," + this.nPersons + ", inclusive");
/* 3632 */     if (!this.variancesCalculated) meansAndVariances();
/* 3633 */     return this.rawPersonMaxima[(index - 1)];
/*      */   }
/*      */   
/*      */   public double standardizedPersonMaximum(int index)
/*      */   {
/* 3638 */     if (!this.dataPreprocessed) preprocessData();
/* 3639 */     if ((index < 1) || (index > this.nPersons)) throw new IllegalArgumentException("The person index, " + index + ", must lie between 1 and the number of persons," + this.nPersons + ", inclusive");
/* 3640 */     if (!this.variancesCalculated) meansAndVariances();
/* 3641 */     return this.standardizedPersonMaxima[(index - 1)];
/*      */   }
/*      */   
/*      */   public double standardisedPersonMaximum(int index) {
/* 3645 */     return standardizedPersonMaximum(index);
/*      */   }
/*      */   
/*      */   public double[] rawPersonRanges()
/*      */   {
/* 3650 */     if (!this.dataPreprocessed) preprocessData();
/* 3651 */     if (!this.variancesCalculated) meansAndVariances();
/* 3652 */     return this.rawPersonRanges;
/*      */   }
/*      */   
/*      */   public double[] standardizedPersonRanges()
/*      */   {
/* 3657 */     if (!this.dataPreprocessed) preprocessData();
/* 3658 */     if (!this.variancesCalculated) meansAndVariances();
/* 3659 */     return this.standardizedPersonRanges;
/*      */   }
/*      */   
/*      */   public double[] standardisedPersonRanges() {
/* 3663 */     return standardizedPersonRanges();
/*      */   }
/*      */   
/*      */   public double rawPersonRange(int index)
/*      */   {
/* 3668 */     if (!this.dataPreprocessed) preprocessData();
/* 3669 */     if ((index < 1) || (index > this.nItems)) throw new IllegalArgumentException("The person index, " + index + ", must lie between 1 and the number of persons," + this.nPersons + ", inclusive");
/* 3670 */     if (!this.variancesCalculated) meansAndVariances();
/* 3671 */     return this.rawPersonRanges[(index - 1)];
/*      */   }
/*      */   
/*      */   public double standardizedPersonRange(int index)
/*      */   {
/* 3676 */     if (!this.dataPreprocessed) preprocessData();
/* 3677 */     if ((index < 1) || (index > this.nPersons)) throw new IllegalArgumentException("The person index, " + index + ", must lie between 1 and the number of persons," + this.nPersons + ", inclusive");
/* 3678 */     if (!this.variancesCalculated) meansAndVariances();
/* 3679 */     return this.standardizedPersonRanges[(index - 1)];
/*      */   }
/*      */   
/*      */   public double standardisedPersonRange(int index) {
/* 3683 */     return standardizedPersonRange(index);
/*      */   }
/*      */   
/*      */   public double[] rawItemMedians()
/*      */   {
/* 3688 */     if (!this.dataPreprocessed) preprocessData();
/* 3689 */     if (!this.variancesCalculated) meansAndVariances();
/* 3690 */     return this.rawItemMedians;
/*      */   }
/*      */   
/*      */   public double[] standardizedItemMedians()
/*      */   {
/* 3695 */     if (!this.dataPreprocessed) preprocessData();
/* 3696 */     if (!this.variancesCalculated) meansAndVariances();
/* 3697 */     return this.standardizedItemMedians;
/*      */   }
/*      */   
/*      */   public double[] standardisedItemMedians() {
/* 3701 */     return standardizedItemMedians();
/*      */   }
/*      */   
/*      */   public double rawItemMedian(String itemName)
/*      */   {
/* 3706 */     if (!this.dataPreprocessed) preprocessData();
/* 3707 */     int index = itemIndex(itemName);
/* 3708 */     if (!this.variancesCalculated) meansAndVariances();
/* 3709 */     return this.rawItemMedians[(index - 1)];
/*      */   }
/*      */   
/*      */   public double rawItemMedian(int index)
/*      */   {
/* 3714 */     if (!this.dataPreprocessed) preprocessData();
/* 3715 */     if ((index < 1) || (index > this.nItems)) throw new IllegalArgumentException("The item index, " + index + ", must lie between 1 and the number of items," + this.nItems + ", inclusive");
/* 3716 */     if (!this.variancesCalculated) meansAndVariances();
/* 3717 */     return this.rawItemMedians[(index - 1)];
/*      */   }
/*      */   
/*      */   public double standardizedItemMedian(String itemName)
/*      */   {
/* 3722 */     if (!this.dataPreprocessed) preprocessData();
/* 3723 */     int index = itemIndex(itemName);
/* 3724 */     if (!this.variancesCalculated) meansAndVariances();
/* 3725 */     return this.standardizedItemMedians[(index - 1)];
/*      */   }
/*      */   
/*      */   public double standardisedItemMedian(String itemName) {
/* 3729 */     return standardizedItemMedian(itemName);
/*      */   }
/*      */   
/*      */   public double standardizedItemMedian(int index)
/*      */   {
/* 3734 */     if (!this.dataPreprocessed) preprocessData();
/* 3735 */     if ((index < 1) || (index > this.nItems)) throw new IllegalArgumentException("The item index, " + index + ", must lie between 1 and the number of items," + this.nItems + ", inclusive");
/* 3736 */     if (!this.variancesCalculated) meansAndVariances();
/* 3737 */     return this.standardizedItemMedians[(index - 1)];
/*      */   }
/*      */   
/*      */   public double standardisedItemMedian(int index) {
/* 3741 */     return standardizedItemMedian(index);
/*      */   }
/*      */   
/*      */   public double rawMeanOfItemMedians()
/*      */   {
/* 3746 */     return this.rawItemMediansMean;
/*      */   }
/*      */   
/*      */   public double rawStanadarDeviationOfItemMedians()
/*      */   {
/* 3751 */     return this.rawItemMediansSd;
/*      */   }
/*      */   
/*      */   public double rawVarianceOfItemMedians()
/*      */   {
/* 3756 */     return this.rawItemMediansVar;
/*      */   }
/*      */   
/*      */   public double rawMaximumOfItemMedians()
/*      */   {
/* 3761 */     return this.rawItemMediansMax;
/*      */   }
/*      */   
/*      */   public double rawMinimumOfItemMedians()
/*      */   {
/* 3766 */     return this.rawItemMediansMin;
/*      */   }
/*      */   
/*      */   public double rawRangeOfItemMedians()
/*      */   {
/* 3771 */     return this.rawItemMediansRange;
/*      */   }
/*      */   
/*      */   public double standardizedMeanOfItemMedians()
/*      */   {
/* 3776 */     return this.standardizedItemMediansMean;
/*      */   }
/*      */   
/*      */   public double standardisedMeanOfItemMedians() {
/* 3780 */     return this.standardizedItemMediansMean;
/*      */   }
/*      */   
/*      */   public double standardizedStanadarDeviationOfItemMedians()
/*      */   {
/* 3785 */     return this.standardizedItemMediansSd;
/*      */   }
/*      */   
/*      */   public double standardisedStanadarDeviationOfItemMedians() {
/* 3789 */     return this.standardizedItemMediansSd;
/*      */   }
/*      */   
/*      */   public double standardizedVarianceOfItemMedians()
/*      */   {
/* 3794 */     return this.standardizedItemMediansVar;
/*      */   }
/*      */   
/*      */   public double standardisedVarianceOfItemMedians() {
/* 3798 */     return this.standardizedItemMediansVar;
/*      */   }
/*      */   
/*      */   public double standardizedMaximumOfItemMedians()
/*      */   {
/* 3803 */     return this.standardizedItemMediansMax;
/*      */   }
/*      */   
/*      */   public double standardisedMaximumOfItemMedians() {
/* 3807 */     return this.standardizedItemMediansMax;
/*      */   }
/*      */   
/*      */   public double standardizedMinimumOfItemMedians()
/*      */   {
/* 3812 */     return this.standardizedItemMediansMin;
/*      */   }
/*      */   
/*      */   public double standardisedMinimumOfItemMedians() {
/* 3816 */     return this.standardizedItemMediansMin;
/*      */   }
/*      */   
/*      */   public double standardizedRangeOfItemMedians()
/*      */   {
/* 3821 */     return this.standardizedItemMediansRange;
/*      */   }
/*      */   
/*      */   public double standardisedRangeOfItemMedians() {
/* 3825 */     return this.standardizedItemMediansRange;
/*      */   }
/*      */   
/*      */   public double[] rawPersonTotals()
/*      */   {
/* 3830 */     if (!this.dataPreprocessed) preprocessData();
/* 3831 */     if (!this.variancesCalculated) meansAndVariances();
/* 3832 */     return this.rawPersonTotals;
/*      */   }
/*      */   
/*      */   public double[] standardizedPersonTotals()
/*      */   {
/* 3837 */     if (!this.dataPreprocessed) preprocessData();
/* 3838 */     if (!this.variancesCalculated) meansAndVariances();
/* 3839 */     return this.standardizedPersonTotals;
/*      */   }
/*      */   
/*      */   public double[] standardisedPersonTotals() {
/* 3843 */     return standardizedPersonTotals();
/*      */   }
/*      */   
/*      */   public double rawPersonTotal(int index)
/*      */   {
/* 3848 */     if (!this.dataPreprocessed) preprocessData();
/* 3849 */     if ((index < 1) || (index > this.nItems)) throw new IllegalArgumentException("The person index, " + index + ", must lie between 1 and the number of persons," + this.nPersons + ", inclusive");
/* 3850 */     if (!this.variancesCalculated) meansAndVariances();
/* 3851 */     return this.rawPersonTotals[(index - 1)];
/*      */   }
/*      */   
/*      */   public double standardizedPersonTotal(int index)
/*      */   {
/* 3856 */     if (!this.dataPreprocessed) preprocessData();
/* 3857 */     if ((index < 1) || (index > this.nPersons)) throw new IllegalArgumentException("The person index, " + index + ", must lie between 1 and the number of persons," + this.nPersons + ", inclusive");
/* 3858 */     if (!this.variancesCalculated) meansAndVariances();
/* 3859 */     return this.standardizedPersonTotals[(index - 1)];
/*      */   }
/*      */   
/*      */   public double standardisedPersonTotal(int index) {
/* 3863 */     return standardizedPersonTotal(index);
/*      */   }
/*      */   
/*      */   public double rawAllResponsesMean()
/*      */   {
/* 3868 */     if (!this.dataPreprocessed) preprocessData();
/* 3869 */     if (!this.variancesCalculated) meansAndVariances();
/* 3870 */     return this.rawAllResponsesMean;
/*      */   }
/*      */   
/*      */   public double standardizedAllResponsesMean()
/*      */   {
/* 3875 */     if (!this.dataPreprocessed) preprocessData();
/* 3876 */     if (!this.variancesCalculated) meansAndVariances();
/* 3877 */     return this.standardizedAllResponsesMean;
/*      */   }
/*      */   
/*      */   public double standardisedTotalMean() {
/* 3881 */     return standardizedAllResponsesMean();
/*      */   }
/*      */   
/*      */   public double rawAllResponsesStandardDeviation()
/*      */   {
/* 3886 */     if (!this.dataPreprocessed) preprocessData();
/* 3887 */     if (!this.variancesCalculated) meansAndVariances();
/* 3888 */     return this.rawAllResponsesStandardDeviation;
/*      */   }
/*      */   
/*      */   public double standardizedAllResponsesStandardDeviation()
/*      */   {
/* 3893 */     if (!this.dataPreprocessed) preprocessData();
/* 3894 */     if (!this.variancesCalculated) meansAndVariances();
/* 3895 */     return this.standardizedAllResponsesStandardDeviation;
/*      */   }
/*      */   
/*      */   public double standardisedTotalStandardDeviation() {
/* 3899 */     return standardizedAllResponsesStandardDeviation();
/*      */   }
/*      */   
/*      */   public double rawAllResponsesVariance()
/*      */   {
/* 3904 */     if (!this.dataPreprocessed) preprocessData();
/* 3905 */     if (!this.variancesCalculated) meansAndVariances();
/* 3906 */     return this.rawAllResponsesVariance;
/*      */   }
/*      */   
/*      */   public double standardizedAllResponsesVariance()
/*      */   {
/* 3911 */     if (!this.dataPreprocessed) preprocessData();
/* 3912 */     if (!this.variancesCalculated) meansAndVariances();
/* 3913 */     return this.standardizedAllResponsesVariance;
/*      */   }
/*      */   
/*      */   public double standardisedTotalVariance() {
/* 3917 */     return standardizedAllResponsesVariance();
/*      */   }
/*      */   
/*      */   public double rawAllResponsesMinimum()
/*      */   {
/* 3922 */     if (!this.dataPreprocessed) preprocessData();
/* 3923 */     if (!this.variancesCalculated) meansAndVariances();
/* 3924 */     return this.rawAllResponsesMinimum;
/*      */   }
/*      */   
/*      */   public double standardizedAllResponsesMinimum()
/*      */   {
/* 3929 */     if (!this.dataPreprocessed) preprocessData();
/* 3930 */     if (!this.variancesCalculated) meansAndVariances();
/* 3931 */     return this.standardizedAllResponsesMinimum;
/*      */   }
/*      */   
/*      */   public double standardisedTotalMinimum() {
/* 3935 */     return standardizedAllResponsesMinimum();
/*      */   }
/*      */   
/*      */   public double rawAllResponsesMaximum()
/*      */   {
/* 3940 */     if (!this.dataPreprocessed) preprocessData();
/* 3941 */     if (!this.variancesCalculated) meansAndVariances();
/* 3942 */     return this.rawAllResponsesMaximum;
/*      */   }
/*      */   
/*      */   public double standardizedAllResponsesMaximum()
/*      */   {
/* 3947 */     if (!this.dataPreprocessed) preprocessData();
/* 3948 */     if (!this.variancesCalculated) meansAndVariances();
/* 3949 */     return this.standardizedAllResponsesMaximum;
/*      */   }
/*      */   
/*      */   public double standardisedTotalMaximum() {
/* 3953 */     return standardizedAllResponsesMaximum();
/*      */   }
/*      */   
/*      */   public double rawAllResponsesRange()
/*      */   {
/* 3958 */     if (!this.dataPreprocessed) preprocessData();
/* 3959 */     if (!this.variancesCalculated) meansAndVariances();
/* 3960 */     return this.rawAllResponsesRange;
/*      */   }
/*      */   
/*      */   public double standardizedAllResponsesRange()
/*      */   {
/* 3965 */     if (!this.dataPreprocessed) preprocessData();
/* 3966 */     if (!this.variancesCalculated) meansAndVariances();
/* 3967 */     return this.standardizedAllResponsesRange;
/*      */   }
/*      */   
/*      */   public double standardisedTotalRange() {
/* 3971 */     return standardizedAllResponsesRange();
/*      */   }
/*      */   
/*      */   public double rawAllResponsesTotal()
/*      */   {
/* 3976 */     if (!this.dataPreprocessed) preprocessData();
/* 3977 */     if (!this.variancesCalculated) meansAndVariances();
/* 3978 */     return this.rawAllResponsesTotal;
/*      */   }
/*      */   
/*      */   public double standardizedAllResponsesTotal()
/*      */   {
/* 3983 */     if (!this.dataPreprocessed) preprocessData();
/* 3984 */     if (!this.variancesCalculated) meansAndVariances();
/* 3985 */     return this.standardizedAllResponsesTotal;
/*      */   }
/*      */   
/*      */   public double standardisedTotalTotal() {
/* 3989 */     return standardizedAllResponsesTotal();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected void covariancesAndCorrelationCoefficients()
/*      */   {
/* 3996 */     this.rawCovariances = new double[this.nItems + 1][this.nItems + 1];
/*      */     
/* 3998 */     for (int i = 0; i < this.nItems; i++) {
/* 3999 */       for (int j = i; j < this.nItems; j++) {
/* 4000 */         this.rawCovariances[i][j] = Stat.covariance(this.scores0[i], this.scores0[j]);
/* 4001 */         if (i != j) { this.rawCovariances[j][i] = this.rawCovariances[i][j];
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 4006 */     for (int i = 0; i < this.nItems; i++) {
/* 4007 */       this.rawCovariances[i][this.nItems] = Stat.covariance(this.scores0[i], this.rawPersonTotals);
/* 4008 */       this.rawCovariances[this.nItems][i] = this.rawCovariances[i][this.nItems];
/*      */     }
/* 4010 */     this.rawCovariances[this.nItems][this.nItems] = Stat.covariance(this.rawPersonTotals, this.rawPersonTotals);
/*      */     
/*      */ 
/* 4013 */     this.standardizedCovariances = new double[this.nItems + 1][this.nItems + 1];
/* 4014 */     for (int i = 0; i < this.nItems; i++) {
/* 4015 */       for (int j = i; j < this.nItems; j++) {
/* 4016 */         this.standardizedCovariances[i][j] = Stat.covariance(this.scores0[i], this.scores0[j]);
/* 4017 */         if (i != j) { this.standardizedCovariances[j][i] = this.standardizedCovariances[i][j];
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 4022 */     for (int i = 0; i < this.nItems; i++) {
/* 4023 */       this.standardizedCovariances[i][this.nItems] = Stat.covariance(this.scores0[i], this.standardizedPersonTotals);
/* 4024 */       this.standardizedCovariances[this.nItems][i] = this.standardizedCovariances[i][this.nItems];
/*      */     }
/* 4026 */     this.standardizedCovariances[this.nItems][this.nItems] = Stat.covariance(this.standardizedPersonTotals, this.standardizedPersonTotals);
/*      */     
/*      */ 
/*      */ 
/* 4030 */     this.rawCorrelationCoefficients = new double[this.nItems + 1][this.nItems + 1];
/*      */     
/*      */ 
/* 4033 */     for (int i = 0; i < this.nItems; i++) {
/* 4034 */       this.rawCorrelationCoefficients[i][i] = 1.0D;
/* 4035 */       for (int j = i + 1; j < this.nItems; j++) {
/* 4036 */         this.rawCorrelationCoefficients[i][j] = (this.rawCovariances[i][j] / Math.sqrt(this.rawCovariances[i][i] * this.rawCovariances[j][j]));
/* 4037 */         this.rawCorrelationCoefficients[j][i] = this.rawCorrelationCoefficients[i][j];
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 4042 */     for (int i = 0; i < this.nItems; i++) {
/* 4043 */       this.rawCorrelationCoefficients[i][this.nItems] = (this.rawCovariances[i][this.nItems] / Math.sqrt(this.rawCovariances[i][i] * this.rawCovariances[this.nItems][this.nItems]));
/* 4044 */       this.rawCorrelationCoefficients[this.nItems][i] = this.rawCorrelationCoefficients[i][this.nItems];
/*      */     }
/* 4046 */     this.rawCorrelationCoefficients[this.nItems][this.nItems] = 1.0D;
/*      */     
/*      */ 
/* 4049 */     double[] rhoArray = new double[this.nItems * (this.nItems - 1) / 2];
/* 4050 */     int kk = 0;
/* 4051 */     for (int i = 0; i < this.nItems; i++) {
/* 4052 */       for (int j = i + 1; j < this.nItems; j++) {
/* 4053 */         rhoArray[kk] = this.rawCorrelationCoefficients[i][j];
/* 4054 */         kk++;
/*      */       }
/*      */     }
/* 4057 */     Stat st = new Stat(rhoArray);
/* 4058 */     if (this.nFactorOption) {
/* 4059 */       st.setDenominatorToN();
/*      */     }
/*      */     else {
/* 4062 */       st.setDenominatorToNminusOne();
/*      */     }
/* 4064 */     this.rawMeanRhoWithoutTotals = st.mean_as_double();
/* 4065 */     this.rawStandardDeviationRhoWithoutTotals = st.standardDeviation_as_double();
/*      */     
/* 4067 */     rhoArray = new double[this.nItems * (this.nItems + 1) / 2];
/* 4068 */     kk = 0;
/* 4069 */     for (int i = 0; i <= this.nItems; i++) {
/* 4070 */       for (int j = i + 1; j <= this.nItems; j++) {
/* 4071 */         rhoArray[kk] = this.rawCorrelationCoefficients[i][j];
/* 4072 */         kk++;
/*      */       }
/*      */     }
/* 4075 */     st = new Stat(rhoArray);
/* 4076 */     if (this.nFactorOption) {
/* 4077 */       st.setDenominatorToN();
/*      */     }
/*      */     else {
/* 4080 */       st.setDenominatorToNminusOne();
/*      */     }
/* 4082 */     this.rawMeanRhoWithTotals = st.mean_as_double();
/* 4083 */     this.rawStandardDeviationRhoWithTotals = st.standardDeviation_as_double();
/*      */     
/*      */ 
/* 4086 */     this.standardizedCorrelationCoefficients = new double[this.nItems + 1][this.nItems + 1];
/*      */     
/* 4088 */     for (int i = 0; i < this.nItems; i++) {
/* 4089 */       this.standardizedCorrelationCoefficients[i][i] = 1.0D;
/* 4090 */       for (int j = i + 1; j < this.nItems; j++) {
/* 4091 */         this.standardizedCorrelationCoefficients[i][j] = (this.standardizedCovariances[i][j] / Math.sqrt(this.standardizedCovariances[i][i] * this.standardizedCovariances[j][j]));
/* 4092 */         this.standardizedCorrelationCoefficients[j][i] = this.standardizedCorrelationCoefficients[i][j];
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 4097 */     for (int i = 0; i < this.nItems; i++) {
/* 4098 */       this.standardizedCorrelationCoefficients[i][this.nItems] = (this.standardizedCovariances[i][this.nItems] / Math.sqrt(this.standardizedCovariances[i][i] * this.standardizedCovariances[this.nItems][this.nItems]));
/* 4099 */       this.standardizedCorrelationCoefficients[this.nItems][i] = this.standardizedCorrelationCoefficients[i][this.nItems];
/*      */     }
/* 4101 */     this.standardizedCorrelationCoefficients[this.nItems][this.nItems] = 1.0D;
/*      */     
/*      */ 
/* 4104 */     rhoArray = new double[this.nItems * (this.nItems - 1) / 2];
/* 4105 */     kk = 0;
/* 4106 */     for (int i = 0; i < this.nItems; i++) {
/* 4107 */       for (int j = i + 1; j < this.nItems; j++) {
/* 4108 */         rhoArray[kk] = this.standardizedCorrelationCoefficients[i][j];
/* 4109 */         kk++;
/*      */       }
/*      */     }
/* 4112 */     st = new Stat(rhoArray);
/* 4113 */     if (this.nFactorOption) {
/* 4114 */       st.setDenominatorToN();
/*      */     }
/*      */     else {
/* 4117 */       st.setDenominatorToNminusOne();
/*      */     }
/* 4119 */     this.standardizedMeanRhoWithoutTotals = st.mean_as_double();
/* 4120 */     this.standardizedStandardDeviationRhoWithoutTotals = st.standardDeviation_as_double();
/*      */     
/* 4122 */     rhoArray = new double[this.nItems * (this.nItems + 1) / 2];
/* 4123 */     kk = 0;
/* 4124 */     for (int i = 0; i <= this.nItems; i++) {
/* 4125 */       for (int j = i + 1; j <= this.nItems; j++) {
/* 4126 */         rhoArray[kk] = this.standardizedCorrelationCoefficients[i][j];
/* 4127 */         kk++;
/*      */       }
/*      */     }
/* 4130 */     st = new Stat(rhoArray);
/* 4131 */     if (this.nFactorOption) {
/* 4132 */       st.setDenominatorToN();
/*      */     }
/*      */     else {
/* 4135 */       st.setDenominatorToNminusOne();
/*      */     }
/* 4137 */     this.standardizedMeanRhoWithTotals = st.mean_as_double();
/* 4138 */     this.standardizedStandardDeviationRhoWithTotals = st.standardDeviation_as_double();
/*      */     
/* 4140 */     this.covariancesCalculated = true;
/*      */   }
/*      */   
/*      */   public double[][] rawCovariances()
/*      */   {
/* 4145 */     if (!this.dataPreprocessed) preprocessData();
/* 4146 */     if (!this.covariancesCalculated) covariancesAndCorrelationCoefficients();
/* 4147 */     return this.rawCovariances;
/*      */   }
/*      */   
/*      */   public double[][] standardizedCovariances()
/*      */   {
/* 4152 */     if (!this.dataPreprocessed) preprocessData();
/* 4153 */     if (!this.covariancesCalculated) covariancesAndCorrelationCoefficients();
/* 4154 */     return this.standardizedCovariances;
/*      */   }
/*      */   
/*      */   public double[][] standardisedCovariances() {
/* 4158 */     return standardizedCovariances();
/*      */   }
/*      */   
/*      */   public double rawCovariance(String itemName1, String itemName2)
/*      */   {
/* 4163 */     if (!this.dataPreprocessed) preprocessData();
/* 4164 */     if (!this.covariancesCalculated) covariancesAndCorrelationCoefficients();
/* 4165 */     int index1 = itemIndex(itemName1);
/* 4166 */     int index2 = itemIndex(itemName2);
/* 4167 */     return this.rawCovariances[(index1 - 1)][(index2 - 1)];
/*      */   }
/*      */   
/*      */   public double rawCovariance(int index1, int index2)
/*      */   {
/* 4172 */     if (!this.dataPreprocessed) preprocessData();
/* 4173 */     if ((index1 < 1) || (index1 > this.nItems)) throw new IllegalArgumentException("The first item index, " + index1 + ", must lie between 1 and the number of items plus one (for totals)," + (this.nItems + 1) + ", inclusive");
/* 4174 */     if ((index2 < 1) || (index2 > this.nItems)) throw new IllegalArgumentException("The second item index, " + index2 + ", must lie between 1 and the number of items plus one (for totals)," + (this.nItems + 1) + ", inclusive");
/* 4175 */     if (!this.covariancesCalculated) covariancesAndCorrelationCoefficients();
/* 4176 */     return this.rawCovariances[(index1 - 1)][(index2 - 1)];
/*      */   }
/*      */   
/*      */   public double rawCovariance(String itemName)
/*      */   {
/* 4181 */     if (!this.dataPreprocessed) preprocessData();
/* 4182 */     if (!this.covariancesCalculated) covariancesAndCorrelationCoefficients();
/* 4183 */     int index = itemIndex(itemName);
/* 4184 */     return this.rawCovariances[(index - 1)][this.nItems];
/*      */   }
/*      */   
/*      */   public double rawCovariance(int index)
/*      */   {
/* 4189 */     if (!this.dataPreprocessed) preprocessData();
/* 4190 */     if ((index < 1) || (index > this.nItems)) throw new IllegalArgumentException("The first item index, " + index + ", must lie between 1 and the number of items plus one (for totals)," + (this.nItems + 1) + ", inclusive");
/* 4191 */     if (!this.covariancesCalculated) covariancesAndCorrelationCoefficients();
/* 4192 */     return this.rawCovariances[(index - 1)][this.nItems];
/*      */   }
/*      */   
/*      */   public double standardizedCovariance(String itemName1, String itemName2)
/*      */   {
/* 4197 */     if (!this.dataPreprocessed) preprocessData();
/* 4198 */     if (!this.covariancesCalculated) covariancesAndCorrelationCoefficients();
/* 4199 */     int index1 = itemIndex(itemName1);
/* 4200 */     int index2 = itemIndex(itemName2);
/* 4201 */     return this.standardizedCovariances[(index1 + 1)][(index2 + 1)];
/*      */   }
/*      */   
/*      */   public double standardisedCovariance(String itemName1, String itemName2) {
/* 4205 */     return standardizedCovariance(itemName1, itemName2);
/*      */   }
/*      */   
/*      */   public double standardizedCovariance(int index1, int index2)
/*      */   {
/* 4210 */     if (!this.dataPreprocessed) preprocessData();
/* 4211 */     if ((index1 < 1) || (index1 > this.nItems)) throw new IllegalArgumentException("The first item index, " + index1 + ", must lie between 1 and the number of items plus one (for totals)," + (this.nItems + 1) + ", inclusive");
/* 4212 */     if ((index2 < 1) || (index2 > this.nItems)) throw new IllegalArgumentException("The second item index, " + index2 + ", must lie between 1 and the number of items plus one (for totals)," + (this.nItems + 1) + ", inclusive");
/* 4213 */     if (!this.covariancesCalculated) covariancesAndCorrelationCoefficients();
/* 4214 */     return this.standardizedCovariances[(index1 + 1)][(index2 + 1)];
/*      */   }
/*      */   
/*      */   public double standardisedCovariance(int index1, int index2) {
/* 4218 */     return standardizedCovariance(index1, index2);
/*      */   }
/*      */   
/*      */   public double standardizedCovariance(String itemName)
/*      */   {
/* 4223 */     if (!this.dataPreprocessed) preprocessData();
/* 4224 */     if (!this.covariancesCalculated) covariancesAndCorrelationCoefficients();
/* 4225 */     int index = itemIndex(itemName);
/* 4226 */     return this.standardizedCovariances[(index + 1)][this.nItems];
/*      */   }
/*      */   
/*      */   public double standardisedCovariance(String itemName) {
/* 4230 */     return standardizedCovariance(itemName);
/*      */   }
/*      */   
/*      */   public double standardizedCovariance(int index)
/*      */   {
/* 4235 */     if (!this.dataPreprocessed) preprocessData();
/* 4236 */     if ((index < 1) || (index > this.nItems)) throw new IllegalArgumentException("The first item index, " + index + ", must lie between 1 and the number of items plus one (for totals)," + (this.nItems + 1) + ", inclusive");
/* 4237 */     if (!this.covariancesCalculated) covariancesAndCorrelationCoefficients();
/* 4238 */     return this.standardizedCovariances[(index + 1)][this.nItems];
/*      */   }
/*      */   
/*      */   public double standardisedCovariance(int index) {
/* 4242 */     return standardizedCovariance(index);
/*      */   }
/*      */   
/*      */   public double rawAverageCorrelationCoefficients()
/*      */   {
/* 4247 */     if (!this.dataPreprocessed) preprocessData();
/* 4248 */     if (!this.covariancesCalculated) covariancesAndCorrelationCoefficients();
/* 4249 */     return this.rawMeanRhoWithoutTotals;
/*      */   }
/*      */   
/*      */   public double rawStandardDeviationCorrelationCoefficients()
/*      */   {
/* 4254 */     if (!this.dataPreprocessed) preprocessData();
/* 4255 */     if (!this.covariancesCalculated) covariancesAndCorrelationCoefficients();
/* 4256 */     return this.rawStandardDeviationRhoWithoutTotals;
/*      */   }
/*      */   
/*      */   public double standardizedAverageCorrelationCoefficients()
/*      */   {
/* 4261 */     if (!this.dataPreprocessed) preprocessData();
/* 4262 */     if (!this.covariancesCalculated) covariancesAndCorrelationCoefficients();
/* 4263 */     return this.standardizedMeanRhoWithoutTotals;
/*      */   }
/*      */   
/*      */   public double standardisedAverageCorrelationCoefficients() {
/* 4267 */     return standardizedAverageCorrelationCoefficients();
/*      */   }
/*      */   
/*      */   public double standardizedStandardDeviationCorrelationCoefficients()
/*      */   {
/* 4272 */     if (!this.dataPreprocessed) preprocessData();
/* 4273 */     if (!this.covariancesCalculated) covariancesAndCorrelationCoefficients();
/* 4274 */     return this.standardizedStandardDeviationRhoWithoutTotals;
/*      */   }
/*      */   
/*      */   public double standardisedStandardDeviationCorrelationCoefficients() {
/* 4278 */     return standardizedStandardDeviationCorrelationCoefficients();
/*      */   }
/*      */   
/*      */   public double rawAverageCorrelationCoefficientsWithTotals()
/*      */   {
/* 4283 */     if (!this.dataPreprocessed) preprocessData();
/* 4284 */     if (!this.covariancesCalculated) covariancesAndCorrelationCoefficients();
/* 4285 */     return this.rawMeanRhoWithTotals;
/*      */   }
/*      */   
/*      */   public double rawStandardDeviationCorrelationCoefficientsWithTotals()
/*      */   {
/* 4290 */     if (!this.dataPreprocessed) preprocessData();
/* 4291 */     if (!this.covariancesCalculated) covariancesAndCorrelationCoefficients();
/* 4292 */     return this.rawStandardDeviationRhoWithTotals;
/*      */   }
/*      */   
/*      */   public double standardizedAverageCorrelationCoefficientsWithTotals()
/*      */   {
/* 4297 */     if (!this.dataPreprocessed) preprocessData();
/* 4298 */     if (!this.covariancesCalculated) covariancesAndCorrelationCoefficients();
/* 4299 */     return this.standardizedMeanRhoWithTotals;
/*      */   }
/*      */   
/*      */   public double standardisedAverageCorrelationCoefficientsWithTotals() {
/* 4303 */     return standardizedAverageCorrelationCoefficientsWithTotals();
/*      */   }
/*      */   
/*      */   public double standardizedStandardDeviationCorrelationCoefficientsWithTotals()
/*      */   {
/* 4308 */     if (!this.dataPreprocessed) preprocessData();
/* 4309 */     if (!this.covariancesCalculated) covariancesAndCorrelationCoefficients();
/* 4310 */     return this.standardizedStandardDeviationRhoWithTotals;
/*      */   }
/*      */   
/*      */   public double standardisedStandardDeviationCorrelationCoefficientsWithTotals() {
/* 4314 */     return standardizedStandardDeviationCorrelationCoefficientsWithTotals();
/*      */   }
/*      */   
/*      */   public double[][] rawCorrelationCoefficients()
/*      */   {
/* 4319 */     if (!this.dataPreprocessed) preprocessData();
/* 4320 */     if (!this.covariancesCalculated) covariancesAndCorrelationCoefficients();
/* 4321 */     return this.rawCorrelationCoefficients;
/*      */   }
/*      */   
/*      */ 
/*      */   public double[][] standardizedCorrelationCoefficients()
/*      */   {
/* 4327 */     if (!this.dataPreprocessed) preprocessData();
/* 4328 */     if (!this.covariancesCalculated) covariancesAndCorrelationCoefficients();
/* 4329 */     return this.standardizedCorrelationCoefficients;
/*      */   }
/*      */   
/*      */   public double[][] standardisedCorrelationCoefficients() {
/* 4333 */     return standardizedCorrelationCoefficients();
/*      */   }
/*      */   
/*      */   public double rawCorrelationCoefficient(String itemName1, String itemName2)
/*      */   {
/* 4338 */     if (!this.dataPreprocessed) preprocessData();
/* 4339 */     if (!this.covariancesCalculated) covariancesAndCorrelationCoefficients();
/* 4340 */     int index1 = itemIndex(itemName1);
/* 4341 */     int index2 = itemIndex(itemName2);
/* 4342 */     return this.rawCorrelationCoefficients[(index1 - 1)][(index2 - 1)];
/*      */   }
/*      */   
/*      */   public double rawCorrelationCoefficient(int index1, int index2)
/*      */   {
/* 4347 */     if (!this.dataPreprocessed) preprocessData();
/* 4348 */     if ((index1 < 1) || (index1 > this.nItems)) throw new IllegalArgumentException("The first item index, " + index1 + ", must lie between 1 and the number of items plus one (for totals)," + (this.nItems + 1) + ", inclusive");
/* 4349 */     if ((index2 < 1) || (index2 > this.nItems)) throw new IllegalArgumentException("The second item index, " + index2 + ", must lie between 1 and the number of items plus one (for totals)," + (this.nItems + 1) + ", inclusive");
/* 4350 */     if (!this.covariancesCalculated) covariancesAndCorrelationCoefficients();
/* 4351 */     return this.rawCorrelationCoefficients[(index1 - 1)][(index2 - 1)];
/*      */   }
/*      */   
/*      */   public double rawCorrelationCoefficient(String itemName)
/*      */   {
/* 4356 */     if (!this.dataPreprocessed) preprocessData();
/* 4357 */     if (!this.covariancesCalculated) covariancesAndCorrelationCoefficients();
/* 4358 */     int index = itemIndex(itemName);
/* 4359 */     return this.rawCorrelationCoefficients[(index - 1)][this.nItems];
/*      */   }
/*      */   
/*      */   public double rawCorrelationCoefficient(int index)
/*      */   {
/* 4364 */     if (!this.dataPreprocessed) preprocessData();
/* 4365 */     if ((index < 1) || (index > this.nItems)) throw new IllegalArgumentException("The first item index, " + index + ", must lie between 1 and the number of items plus one (for totals)," + (this.nItems + 1) + ", inclusive");
/* 4366 */     if (!this.covariancesCalculated) covariancesAndCorrelationCoefficients();
/* 4367 */     return this.rawCorrelationCoefficients[(index - 1)][this.nItems];
/*      */   }
/*      */   
/*      */   public double standardizedCorrelationCoefficient(String itemName1, String itemName2)
/*      */   {
/* 4372 */     if (!this.dataPreprocessed) preprocessData();
/* 4373 */     if (!this.covariancesCalculated) covariancesAndCorrelationCoefficients();
/* 4374 */     int index1 = itemIndex(itemName1);
/* 4375 */     int index2 = itemIndex(itemName2);
/* 4376 */     return this.standardizedCorrelationCoefficients[(index1 + 1)][(index2 + 1)];
/*      */   }
/*      */   
/*      */   public double standardisedCorrelationCoefficient(String itemName1, String itemName2) {
/* 4380 */     return standardizedCorrelationCoefficient(itemName1, itemName2);
/*      */   }
/*      */   
/*      */   public double standardizedCorrelationCoefficient(int index1, int index2)
/*      */   {
/* 4385 */     if (!this.dataPreprocessed) preprocessData();
/* 4386 */     if ((index1 < 1) || (index1 > this.nItems)) throw new IllegalArgumentException("The first item index, " + index1 + ", must lie between 1 and the number of items plus one (for totals)," + (this.nItems + 1) + ", inclusive");
/* 4387 */     if ((index2 < 1) || (index2 > this.nItems)) throw new IllegalArgumentException("The second item index, " + index2 + ", must lie between 1 and the number of items plus one (for totals)," + (this.nItems + 1) + ", inclusive");
/* 4388 */     if (!this.covariancesCalculated) covariancesAndCorrelationCoefficients();
/* 4389 */     return this.standardizedCorrelationCoefficients[(index1 + 1)][(index2 + 1)];
/*      */   }
/*      */   
/*      */   public double standardisedCorrelationCoefficient(int index1, int index2) {
/* 4393 */     return standardizedCorrelationCoefficient(index1, index2);
/*      */   }
/*      */   
/*      */   public double standardizedCorrelationCoefficient(String itemName)
/*      */   {
/* 4398 */     if (!this.dataPreprocessed) preprocessData();
/* 4399 */     if (!this.covariancesCalculated) covariancesAndCorrelationCoefficients();
/* 4400 */     int index = itemIndex(itemName);
/* 4401 */     return this.standardizedCorrelationCoefficients[(index + 1)][this.nItems];
/*      */   }
/*      */   
/*      */   public double standardisedCorrelationCoefficient(String itemName) {
/* 4405 */     return standardizedCorrelationCoefficient(itemName);
/*      */   }
/*      */   
/*      */   public double standardizedCorrelationCoefficient(int index)
/*      */   {
/* 4410 */     if (!this.dataPreprocessed) preprocessData();
/* 4411 */     if ((index < 1) || (index > this.nItems)) throw new IllegalArgumentException("The first item index, " + index + ", must lie between 1 and the number of items plus one (for totals)," + (this.nItems + 1) + ", inclusive");
/* 4412 */     if (!this.covariancesCalculated) covariancesAndCorrelationCoefficients();
/* 4413 */     return this.standardizedCorrelationCoefficients[(index + 1)][this.nItems];
/*      */   }
/*      */   
/*      */   public double standardisedCorrelationCoefficient(int index) {
/* 4417 */     return standardizedCorrelationCoefficient(index);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected double[] checkWhetherRawItemsDichotomous()
/*      */   {
/* 4424 */     if (!this.dichotomousCheckDone) {
/* 4425 */       this.dichotomous = new boolean[this.nItems];
/* 4426 */       this.dichotomousPercentage = new double[this.nItems];
/* 4427 */       int nDich = 0;
/* 4428 */       for (int k = 0; k < this.nItems; k++) {
/* 4429 */         this.dichotomousPercentage[k] = checkWhetherDichotomous(this.scores0[k]);
/* 4430 */         if (this.dichotomousPercentage[k] == 100.0D) {
/* 4431 */           this.dichotomous[k] = true;
/* 4432 */           nDich++;
/*      */         }
/*      */       }
/* 4435 */       if (nDich == this.nItems) { this.dichotomousOverall = true;
/*      */       }
/* 4437 */       this.dichotomousCheckDone = false;
/*      */     }
/* 4439 */     return this.dichotomousPercentage;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected double checkWhetherDichotomous(double[] array)
/*      */   {
/* 4446 */     int n = array.length;
/* 4447 */     double[] responseMatching = new double[n];
/* 4448 */     boolean[] matchingCheck = new boolean[n];
/*      */     
/* 4450 */     for (int i = 0; i < n; i++) {
/* 4451 */       responseMatching[i] = 0.0D;
/* 4452 */       matchingCheck[i] = false;
/*      */     }
/*      */     
/* 4455 */     for (int i = 0; i < n; i++) {
/* 4456 */       responseMatching[i] = 0.0D;
/* 4457 */       for (int j = 0; j < n; j++) {
/* 4458 */         if ((array[i] == array[j]) && (matchingCheck[j] == 0)) {
/* 4459 */           responseMatching[i] += 1.0D;
/* 4460 */           matchingCheck[j] = true;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 4465 */     ArrayMaths am0 = new ArrayMaths(responseMatching);
/* 4466 */     ArrayMaths am1 = am0.sort();
/* 4467 */     double[] sorted = am1.array();
/* 4468 */     double max = (sorted[(n - 1)] + sorted[(n - 2)]) * 100.0D / n;
/* 4469 */     return max;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double[][] deleteItem(String name)
/*      */   {
/* 4479 */     int index = itemIndex(name);
/* 4480 */     return deleteItem(index);
/*      */   }
/*      */   
/*      */   public double[][] deleteItem(int index)
/*      */   {
/* 4485 */     index--;
/* 4486 */     int jj = 0;
/*      */     
/* 4488 */     double[][] array1 = new double[this.nItems - 1][this.nPersons];
/* 4489 */     for (int i = 0; i < this.nItems; i++) {
/* 4490 */       if (i != index) {
/* 4491 */         array1[jj] = this.scores0[i];
/* 4492 */         jj++;
/*      */       }
/*      */     }
/* 4495 */     return transpose0to1(array1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void rawItemItemPlot(String itemName1, String itemName2)
/*      */   {
/* 4502 */     int index1 = itemIndex(itemName1);
/* 4503 */     int index2 = itemIndex(itemName2);
/* 4504 */     rawItemItemPlot(index1, index2);
/*      */   }
/*      */   
/*      */ 
/*      */   public void rawItemItemPlot(int itemIndex1, int itemIndex2)
/*      */   {
/* 4510 */     itemIndex1--;
/* 4511 */     itemIndex2--;
/*      */     
/* 4513 */     PlotGraph pg = new PlotGraph(this.scores0[itemIndex1], this.scores0[itemIndex2]);
/* 4514 */     String graphTitle = "Scores: plot of responses to the item, " + this.itemNames[itemIndex1] + ", against those to the item, " + this.itemNames[itemIndex2];
/* 4515 */     pg.setGraphTitle(graphTitle);
/* 4516 */     pg.setXaxisLegend("Responses to the item, " + this.itemNames[itemIndex1]);
/* 4517 */     pg.setYaxisLegend("Responses to the item, " + this.itemNames[itemIndex2]);
/* 4518 */     pg.setLine(0);
/* 4519 */     pg.setPoint(4);
/* 4520 */     pg.plot();
/*      */   }
/*      */   
/*      */   public void rawItemMeansPlot(String itemName)
/*      */   {
/* 4525 */     int index = itemIndex(itemName);
/* 4526 */     rawItemMeansPlot(index);
/*      */   }
/*      */   
/*      */   public void rawItemMeansPlot(int itemIndex)
/*      */   {
/* 4531 */     itemIndex--;
/*      */     
/* 4533 */     PlotGraph pg = new PlotGraph(this.rawPersonMeans, this.scores0[itemIndex]);
/* 4534 */     String graphTitle = "Scores: plot of responses to the item, " + this.itemNames[itemIndex] + ", against the means of the responses to all items";
/* 4535 */     pg.setGraphTitle(graphTitle);
/* 4536 */     pg.setXaxisLegend("Mean of the responses to all the items, ");
/* 4537 */     pg.setYaxisLegend("Responses to the item, " + this.itemNames[itemIndex]);
/* 4538 */     pg.setLine(0);
/* 4539 */     pg.setPoint(4);
/* 4540 */     pg.plot();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void standardizedItemItemPlot(String itemName1, String itemName2)
/*      */   {
/* 4547 */     int index1 = itemIndex(itemName1);
/* 4548 */     int index2 = itemIndex(itemName2);
/* 4549 */     standardizedItemItemPlot(index1, index2);
/*      */   }
/*      */   
/*      */   public void standardisedItemItemPlot(String itemName1, String itemName2) {
/* 4553 */     standardizedItemItemPlot(itemName1, itemName2);
/*      */   }
/*      */   
/*      */   public void standardizedItemItemPlot(int itemIndex1, int itemIndex2)
/*      */   {
/* 4558 */     itemIndex1--;
/* 4559 */     itemIndex2--;
/*      */     
/* 4561 */     PlotGraph pg = new PlotGraph(this.standardizedScores0[itemIndex1], this.standardizedScores0[itemIndex2]);
/* 4562 */     String graphTitle = "Scores: plot of responses to the item, " + this.itemNames[itemIndex1] + ", against those to the item, " + this.itemNames[itemIndex2];
/* 4563 */     pg.setGraphTitle(graphTitle);
/* 4564 */     pg.setXaxisLegend("Responses to the item, " + this.itemNames[itemIndex1]);
/* 4565 */     pg.setYaxisLegend("Responses to the item, " + this.itemNames[itemIndex2]);
/* 4566 */     pg.setLine(0);
/* 4567 */     pg.setPoint(4);
/* 4568 */     pg.plot();
/*      */   }
/*      */   
/*      */   public void standardisedItemItemPlot(int itemIndex1, int itemIndex2) {
/* 4572 */     standardizedItemItemPlot(itemIndex1, itemIndex2);
/*      */   }
/*      */   
/*      */ 
/*      */   public void standardizedItemMeansPlot(String itemName)
/*      */   {
/* 4578 */     int index = itemIndex(itemName);
/* 4579 */     standardizedItemMeansPlot(index);
/*      */   }
/*      */   
/*      */   public void standardisedItemMeansPlot(String itemName) {
/* 4583 */     standardizedItemMeansPlot(itemName);
/*      */   }
/*      */   
/*      */ 
/*      */   public void standardizedItemMeansPlot(int itemIndex)
/*      */   {
/* 4589 */     itemIndex--;
/*      */     
/* 4591 */     PlotGraph pg = new PlotGraph(this.standardizedPersonMeans, this.standardizedScores0[itemIndex]);
/* 4592 */     String graphTitle = "Scores: plot of responses to the item, " + this.itemNames[itemIndex] + ", against the means of the responses to all items";
/* 4593 */     pg.setGraphTitle(graphTitle);
/* 4594 */     pg.setXaxisLegend("Mean of the responses to all the items, ");
/* 4595 */     pg.setYaxisLegend("Responses to the item, " + this.itemNames[itemIndex]);
/* 4596 */     pg.setLine(0);
/* 4597 */     pg.setPoint(4);
/* 4598 */     pg.plot();
/*      */   }
/*      */   
/*      */   public void standardisedItemMeansPlot(int itemIndex) {
/* 4602 */     standardizedItemMeansPlot(itemIndex);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void numberOfDecimalPlaces(int trunc)
/*      */   {
/* 4610 */     this.trunc = trunc;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setOutputFileType(int option)
/*      */   {
/* 4617 */     this.fileOption = option;
/* 4618 */     this.fileOptionSet = true;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setFileNumbering()
/*      */   {
/* 4624 */     this.fileNumberingSet = true;
/*      */   }
/*      */   
/*      */ 
/*      */   public void removeFileNumbering()
/*      */   {
/* 4630 */     this.fileNumberingSet = false;
/*      */   }
/*      */   
/*      */ 
/*      */   public PCA toPCA()
/*      */   {
/* 4636 */     PCA pca = new PCA();
/*      */     
/* 4638 */     pca.title = this.title;
/* 4639 */     pca.titleLines = this.titleLines;
/* 4640 */     pca.inputFilename = this.inputFilename;
/* 4641 */     pca.outputFilename = this.outputFilename;
/* 4642 */     pca.fileOption = this.fileOption;
/* 4643 */     pca.fileOptionSet = this.fileOptionSet;
/* 4644 */     pca.fileExtensions = this.fileExtensions;
/* 4645 */     pca.fileNumberingSet = this.fileNumberingSet;
/*      */     
/* 4647 */     pca.originalDataType = this.originalDataType;
/* 4648 */     pca.originalDataOrder = this.originalDataOrder;
/* 4649 */     pca.originalData = this.originalData;
/* 4650 */     pca.scores0 = ((double[][])this.scores0.clone());
/* 4651 */     pca.originalScores0 = ((double[][])this.originalScores0.clone());
/* 4652 */     pca.standardizedScores0 = ((double[][])this.standardizedScores0.clone());
/* 4653 */     pca.scores1 = ((double[][])this.scores1.clone());
/* 4654 */     pca.originalScores1 = ((double[][])this.originalScores1.clone());
/* 4655 */     pca.standardizedScores1 = ((double[][])this.standardizedScores1.clone());
/* 4656 */     pca.dataEntered = this.dataEntered;
/* 4657 */     pca.nItems = this.nItems;
/* 4658 */     pca.originalNitems = this.originalNitems;
/* 4659 */     pca.itemNames = ((String[])this.itemNames.clone());
/* 4660 */     pca.originalItemNames = ((String[])this.originalItemNames.clone());
/* 4661 */     pca.itemNamesSet = this.itemNamesSet;
/* 4662 */     pca.nPersons = this.nPersons;
/* 4663 */     pca.originalNpersons = this.originalNpersons;
/* 4664 */     pca.nScores = this.nScores;
/* 4665 */     pca.originalNscores = this.originalNscores;
/* 4666 */     pca.otherFalse = this.otherFalse;
/* 4667 */     pca.otherTrue = this.otherTrue;
/* 4668 */     pca.otherDichotomousDataSet = this.otherDichotomousDataSet;
/* 4669 */     pca.dichotomous = ((boolean[])this.dichotomous.clone());
/* 4670 */     pca.dichotomousPercentage = ((double[])this.dichotomousPercentage.clone());
/* 4671 */     pca.dichotomousOverall = this.dichotomousOverall;
/* 4672 */     pca.dichotomousCheckDone = this.dichotomousCheckDone;
/* 4673 */     pca.letterToNumeralSet = this.letterToNumeralSet;
/* 4674 */     pca.ignoreNoResponseRequests = this.ignoreNoResponseRequests;
/* 4675 */     pca.itemDeletionPercentage = this.itemDeletionPercentage;
/* 4676 */     pca.itemDeletionPercentageSet = this.itemDeletionPercentageSet;
/* 4677 */     pca.personDeletionPercentage = this.personDeletionPercentage;
/* 4678 */     pca.personDeletionPercentageSet = this.personDeletionPercentageSet;
/* 4679 */     pca.replacementOption = this.replacementOption;
/* 4680 */     pca.replacementOptionNames = ((String[])this.replacementOptionNames.clone());
/* 4681 */     pca.replacementOptionSet = this.replacementOptionSet;
/* 4682 */     pca.allNoResponseOptionsSet = this.allNoResponseOptionsSet;
/*      */     
/* 4684 */     pca.noResponseHandlingSet = this.noResponseHandlingSet;
/* 4685 */     pca.nNaN = this.nNaN;
/* 4686 */     pca.deletedItems = ((boolean[])this.deletedItems.clone());
/* 4687 */     pca.nDeletedItems = this.nDeletedItems;
/* 4688 */     pca.deletedItemsIndices = ((int[])this.deletedItemsIndices.clone());
/* 4689 */     pca.itemIndices = ((int[])this.itemIndices.clone());
/* 4690 */     pca.deletedPersons = ((boolean[])this.deletedPersons.clone());
/* 4691 */     pca.nDeletedPersons = this.nDeletedPersons;
/* 4692 */     pca.deletedPersonsIndices = ((int[])this.deletedPersonsIndices.clone());
/* 4693 */     pca.personIndices = ((int[])this.personIndices.clone());
/* 4694 */     pca.nReplacements = this.nReplacements;
/* 4695 */     pca.replacementIndices = ((String[])this.replacementIndices.clone());
/*      */     
/* 4697 */     pca.nFactorOption = this.nFactorOption;
/* 4698 */     if (this.dataEntered) {
/* 4699 */       pca.dataPreprocessed = false;
/* 4700 */       pca.preprocessData();
/*      */     }
/*      */     
/* 4703 */     return pca;
/*      */   }
/*      */   
/*      */   public Cronbach toCronbach()
/*      */   {
/* 4708 */     Cronbach cr = new Cronbach();
/*      */     
/* 4710 */     cr.title = this.title;
/* 4711 */     cr.titleLines = this.titleLines;
/* 4712 */     cr.inputFilename = this.inputFilename;
/* 4713 */     cr.outputFilename = this.outputFilename;
/* 4714 */     cr.fileOption = this.fileOption;
/* 4715 */     cr.fileOptionSet = this.fileOptionSet;
/* 4716 */     cr.fileExtensions = this.fileExtensions;
/* 4717 */     cr.fileNumberingSet = this.fileNumberingSet;
/*      */     
/* 4719 */     cr.originalDataType = this.originalDataType;
/* 4720 */     cr.originalDataOrder = this.originalDataOrder;
/* 4721 */     cr.originalData = this.originalData;
/* 4722 */     cr.scores0 = ((double[][])this.scores0.clone());
/* 4723 */     cr.originalScores0 = ((double[][])this.originalScores0.clone());
/* 4724 */     cr.standardizedScores0 = ((double[][])this.standardizedScores0.clone());
/* 4725 */     cr.scores1 = ((double[][])this.scores1.clone());
/* 4726 */     cr.originalScores1 = ((double[][])this.originalScores1.clone());
/* 4727 */     cr.standardizedScores1 = ((double[][])this.standardizedScores1.clone());
/* 4728 */     cr.dataEntered = this.dataEntered;
/* 4729 */     cr.nItems = this.nItems;
/* 4730 */     cr.originalNitems = this.originalNitems;
/* 4731 */     cr.itemNames = ((String[])this.itemNames.clone());
/* 4732 */     cr.originalItemNames = ((String[])this.originalItemNames.clone());
/* 4733 */     cr.itemNamesSet = this.itemNamesSet;
/* 4734 */     cr.nPersons = this.nPersons;
/* 4735 */     cr.originalNpersons = this.originalNpersons;
/* 4736 */     cr.nScores = this.nScores;
/* 4737 */     cr.originalNscores = this.originalNscores;
/* 4738 */     cr.otherFalse = this.otherFalse;
/* 4739 */     cr.otherTrue = this.otherTrue;
/* 4740 */     cr.otherDichotomousDataSet = this.otherDichotomousDataSet;
/* 4741 */     cr.dichotomous = ((boolean[])this.dichotomous.clone());
/* 4742 */     cr.dichotomousPercentage = ((double[])this.dichotomousPercentage.clone());
/* 4743 */     cr.dichotomousOverall = this.dichotomousOverall;
/* 4744 */     cr.dichotomousCheckDone = this.dichotomousCheckDone;
/* 4745 */     cr.letterToNumeralSet = this.letterToNumeralSet;
/* 4746 */     cr.ignoreNoResponseRequests = this.ignoreNoResponseRequests;
/* 4747 */     cr.itemDeletionPercentage = this.itemDeletionPercentage;
/* 4748 */     cr.itemDeletionPercentageSet = this.itemDeletionPercentageSet;
/* 4749 */     cr.personDeletionPercentage = this.personDeletionPercentage;
/* 4750 */     cr.personDeletionPercentageSet = this.personDeletionPercentageSet;
/* 4751 */     cr.replacementOption = this.replacementOption;
/* 4752 */     cr.replacementOptionNames = ((String[])this.replacementOptionNames.clone());
/* 4753 */     cr.replacementOptionSet = this.replacementOptionSet;
/* 4754 */     cr.allNoResponseOptionsSet = this.allNoResponseOptionsSet;
/*      */     
/* 4756 */     cr.noResponseHandlingSet = this.noResponseHandlingSet;
/* 4757 */     cr.nNaN = this.nNaN;
/* 4758 */     cr.deletedItems = ((boolean[])this.deletedItems.clone());
/* 4759 */     cr.nDeletedItems = this.nDeletedItems;
/* 4760 */     cr.deletedItemsIndices = ((int[])this.deletedItemsIndices.clone());
/* 4761 */     cr.itemIndices = ((int[])this.itemIndices.clone());
/* 4762 */     cr.deletedPersons = ((boolean[])this.deletedPersons.clone());
/* 4763 */     cr.nDeletedPersons = this.nDeletedPersons;
/* 4764 */     cr.deletedPersonsIndices = ((int[])this.deletedPersonsIndices.clone());
/* 4765 */     cr.personIndices = ((int[])this.personIndices.clone());
/* 4766 */     cr.nReplacements = this.nReplacements;
/* 4767 */     cr.replacementIndices = ((String[])this.replacementIndices.clone());
/*      */     
/* 4769 */     cr.nFactorOption = this.nFactorOption;
/* 4770 */     if (this.dataEntered) {
/* 4771 */       cr.dataPreprocessed = false;
/* 4772 */       cr.preprocessData();
/*      */     }
/*      */     
/* 4775 */     return cr;
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/analysis/Scores.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */