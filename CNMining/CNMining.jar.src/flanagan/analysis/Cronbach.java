/*      */ package flanagan.analysis;
/*      */ 
/*      */ import flanagan.io.Db;
/*      */ import flanagan.io.FileOutput;
/*      */ import flanagan.math.ArrayMaths;
/*      */ import flanagan.math.Fmath;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Cronbach
/*      */   extends Scores
/*      */ {
/*   56 */   private double rawAlpha = NaN.0D;
/*   57 */   private boolean rawAlphaCalculated = false;
/*   58 */   private double standardizedAlpha = NaN.0D;
/*   59 */   private boolean standardizedAlphaCalculated = false;
/*      */   
/*   61 */   private int deletedItemIndex = -1;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double rawAlpha()
/*      */   {
/*   72 */     if (!this.rawAlphaCalculated)
/*      */     {
/*   74 */       if (this.nItems == 1) {
/*   75 */         System.out.println("Method rawAlpha: only one item - alpha cannot be calculated - NaN returned");
/*   76 */         this.rawAlpha = NaN.0D;
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*   81 */         if (!this.dataPreprocessed) { preprocessData();
/*      */         }
/*      */         
/*   84 */         double rawAllResponsesTotalAllSquared = this.rawAllResponsesTotal * this.rawAllResponsesTotal;
/*      */         
/*      */ 
/*   87 */         double sumOfEachScoreSquared = 0.0D;
/*   88 */         for (int i = 0; i < this.nItems; i++) {
/*   89 */           for (int j = 0; j < this.nPersons; j++) { sumOfEachScoreSquared += this.scores1[j][i] * this.scores1[j][i];
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*   94 */         double reducedItemTotalsSquared = 0.0D;
/*   95 */         for (int i = 0; i < this.nItems; i++) { reducedItemTotalsSquared += this.rawItemTotals[i] * this.rawItemTotals[i] / this.nPersons;
/*      */         }
/*      */         
/*   98 */         double reducedPersonTotalsSquared = 0.0D;
/*   99 */         for (int i = 0; i < this.nPersons; i++) { reducedPersonTotalsSquared += this.rawPersonTotals[i] * this.rawPersonTotals[i] / this.nItems;
/*      */         }
/*      */         
/*  102 */         double sumOfSquaresWithinPersons = reducedPersonTotalsSquared - rawAllResponsesTotalAllSquared / this.nScores;
/*      */         
/*      */ 
/*  105 */         double sumOfSquareErrors = sumOfEachScoreSquared - reducedItemTotalsSquared - reducedPersonTotalsSquared + rawAllResponsesTotalAllSquared / this.nScores;
/*      */         
/*      */ 
/*      */ 
/*  109 */         int dfItems = this.nItems - 1;
/*      */         
/*  111 */         int dfPersons = this.nPersons - 1;
/*      */         
/*  113 */         int dfErrors = dfItems * dfPersons;
/*      */         
/*      */ 
/*  116 */         double reducedSquarePersons = sumOfSquaresWithinPersons / dfPersons;
/*  117 */         double reducedSquareErrors = sumOfSquareErrors / dfErrors;
/*      */         
/*      */ 
/*  120 */         this.rawAlpha = ((reducedSquarePersons - reducedSquareErrors) / reducedSquarePersons);
/*  121 */         this.rawAlphaCalculated = true;
/*      */       }
/*      */     }
/*  124 */     return this.rawAlpha;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public double standardizedAlpha()
/*      */   {
/*  132 */     if (!this.standardizedAlphaCalculated)
/*      */     {
/*  134 */       if (this.nItems == 1) {
/*  135 */         System.out.println("Method standardizedAlpha: only one item - alpha cannot be calculated - NaN returned");
/*  136 */         this.rawAlpha = NaN.0D;
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*  141 */         if (!this.dataPreprocessed) { preprocessData();
/*      */         }
/*      */         
/*  144 */         if (!this.covariancesCalculated) { covariancesAndCorrelationCoefficients();
/*      */         }
/*      */         
/*  147 */         this.standardizedAlpha = (this.nItems * this.rawMeanRhoWithoutTotals / (1.0D + (this.nItems - 1) * this.rawMeanRhoWithoutTotals));
/*  148 */         this.standardizedAlphaCalculated = true;
/*      */       }
/*      */     }
/*      */     
/*  152 */     return this.standardizedAlpha;
/*      */   }
/*      */   
/*      */   public double standardisedAlpha() {
/*  156 */     return standardizedAlpha();
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
/*      */   public void analysis()
/*      */   {
/*  169 */     this.outputFilename = "CronbachOutput";
/*  170 */     if (this.fileOption == 1) {
/*  171 */       this.outputFilename += ".txt";
/*      */     }
/*      */     else {
/*  174 */       this.outputFilename += ".xls";
/*      */     }
/*  176 */     String message1 = "Output file name for the analysis details:";
/*  177 */     String message2 = "\nEnter the required name (as a single word) and click OK ";
/*  178 */     String message3 = "\nor simply click OK for default value";
/*  179 */     String message = message1 + message2 + message3;
/*  180 */     String defaultName = this.outputFilename;
/*  181 */     this.outputFilename = Db.readLine(message, defaultName);
/*  182 */     analysis(this.outputFilename);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void analysis(String filename)
/*      */   {
/*  189 */     this.outputFilename = filename;
/*  190 */     String outputFilenameWithoutExtension = null;
/*  191 */     String extension = null;
/*  192 */     int pos = filename.indexOf('.');
/*  193 */     if (pos == -1) {
/*  194 */       outputFilenameWithoutExtension = filename;
/*  195 */       if (this.fileOption == 1) {
/*  196 */         this.outputFilename += ".txt";
/*      */       }
/*      */       else {
/*  199 */         this.outputFilename += ".xls";
/*      */       }
/*      */     }
/*      */     else {
/*  203 */       extension = filename.substring(pos).trim();
/*      */       
/*  205 */       outputFilenameWithoutExtension = filename.substring(0, pos).trim();
/*  206 */       if ((extension.equalsIgnoreCase(".xls")) && 
/*  207 */         (this.fileOption == 1)) {
/*  208 */         if (this.fileOptionSet) {
/*  209 */           String message1 = "Your entered output file type is .xls";
/*  210 */           String message2 = "\nbut you have chosen a .txt output";
/*  211 */           String message = message1 + message2;
/*  212 */           String headerComment = "Your output file name extension";
/*  213 */           String[] comments = { message, "replace it with .txt [text file]" };
/*  214 */           String[] boxTitles = { "Retain", ".txt" };
/*  215 */           int defaultBox = 1;
/*  216 */           int opt = Db.optionBox(headerComment, comments, boxTitles, defaultBox);
/*  217 */           if (opt == 2) this.outputFilename = (outputFilenameWithoutExtension + ".txt");
/*      */         }
/*      */         else {
/*  220 */           this.fileOption = 2;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*  225 */       if ((extension.equalsIgnoreCase(".txt")) && 
/*  226 */         (this.fileOption == 2)) {
/*  227 */         if (this.fileOptionSet) {
/*  228 */           String message1 = "Your entered output file type is .txt";
/*  229 */           String message2 = "\nbut you have chosen a .xls output";
/*  230 */           String message = message1 + message2;
/*  231 */           String headerComment = "Your output file name extension";
/*  232 */           String[] comments = { message, "replace it with .xls [Excel file]" };
/*  233 */           String[] boxTitles = { "Retain", ".xls" };
/*  234 */           int defaultBox = 1;
/*  235 */           int opt = Db.optionBox(headerComment, comments, boxTitles, defaultBox);
/*  236 */           if (opt == 2) this.outputFilename = (outputFilenameWithoutExtension + ".xls");
/*      */         }
/*      */         else {
/*  239 */           this.fileOption = 1;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*  244 */       if ((!extension.equalsIgnoreCase(".txt")) && (!extension.equalsIgnoreCase(".xls"))) {
/*  245 */         String message1 = "Your extension is " + extension;
/*  246 */         String message2 = "\n    Do you wish to retain it:";
/*  247 */         String message = message1 + message2;
/*  248 */         String headerComment = "Your output file name extension";
/*  249 */         String[] comments = { message, "replace it with .txt [text file]", "replace it with .xls [MS Excel file]" };
/*  250 */         String[] boxTitles = { "Retain", ".txt", ".xls" };
/*  251 */         int defaultBox = 1;
/*  252 */         int opt = Db.optionBox(headerComment, comments, boxTitles, defaultBox);
/*  253 */         switch (opt) {
/*  254 */         case 1:  this.fileOption = 1;
/*  255 */           break;
/*  256 */         case 2:  this.outputFilename = (outputFilenameWithoutExtension + ".txt");
/*  257 */           this.fileOption = 1;
/*  258 */           break;
/*  259 */         case 3:  this.outputFilename = (outputFilenameWithoutExtension + ".xls");
/*  260 */           this.fileOption = 2;
/*      */         }
/*      */         
/*      */       }
/*      */     }
/*      */     
/*  266 */     if (this.fileOption == 1) {
/*  267 */       analysisText();
/*      */     }
/*      */     else {
/*  270 */       analysisExcel();
/*      */     }
/*      */   }
/*      */   
/*      */   private void analysisExcel()
/*      */   {
/*  276 */     FileOutput fout = null;
/*  277 */     if (this.fileNumberingSet) {
/*  278 */       fout = new FileOutput(this.outputFilename, 'n');
/*      */     }
/*      */     else {
/*  281 */       fout = new FileOutput(this.outputFilename);
/*      */     }
/*      */     
/*      */ 
/*  285 */     if (!this.rawAlphaCalculated) rawAlpha();
/*  286 */     if (!this.standardizedAlphaCalculated) { standardizedAlpha();
/*      */     }
/*      */     
/*      */ 
/*  290 */     fout.println("CRONBACH'S ALPHA RELIABILITY ESTIMATOR");
/*  291 */     fout.println("Program: Cronbach - Analysis Output");
/*  292 */     for (int i = 0; i < this.titleLines; i++) fout.println(this.title[i]);
/*  293 */     Date d = new Date();
/*  294 */     String day = DateFormat.getDateInstance().format(d);
/*  295 */     String tim = DateFormat.getTimeInstance().format(d);
/*  296 */     fout.println("Program executed at " + tim + " on " + day);
/*  297 */     fout.println();
/*      */     
/*      */ 
/*  300 */     fout.println("RELIABILITY ESTIMATORS");
/*  301 */     fout.println("Cronbach's coefficient alpha");
/*  302 */     fout.printtab("Raw data                  ");
/*  303 */     fout.println(Fmath.truncate(this.rawAlpha, this.trunc));
/*  304 */     fout.printtab("Standardized data           ");
/*  305 */     fout.println(Fmath.truncate(this.standardizedAlpha, this.trunc));
/*  306 */     fout.println();
/*      */     
/*  308 */     fout.println("Average of the inter-item correlation coefficients, excluding item totals");
/*  309 */     fout.printtab("Raw data                  ");
/*  310 */     fout.println(Fmath.truncate(this.rawMeanRhoWithoutTotals, this.trunc));
/*  311 */     fout.printtab("Standardized data           ");
/*  312 */     fout.println(Fmath.truncate(this.standardizedMeanRhoWithoutTotals, this.trunc));
/*  313 */     fout.println();
/*      */     
/*  315 */     fout.println("Average of the inter-item correlation coefficients, including item totals");
/*  316 */     fout.printtab("Raw data                  ");
/*  317 */     fout.println(Fmath.truncate(this.rawMeanRhoWithTotals, this.trunc));
/*  318 */     fout.printtab("Standardized data           ");
/*  319 */     fout.println(Fmath.truncate(this.standardizedMeanRhoWithTotals, this.trunc));
/*  320 */     fout.println();
/*      */     
/*      */ 
/*  323 */     fout.println("'NO RESPONSE' DELETIONS AND REPLACEMENTS");
/*      */     
/*  325 */     boolean deletionFlag = false;
/*  326 */     if (this.nDeletedPersons != 0) {
/*  327 */       deletionFlag = true;
/*  328 */       fout.printtab("Number of persons deleted ");
/*  329 */       fout.println(this.nDeletedPersons);
/*  330 */       fout.printtab("Indices of deleted persons: ");
/*  331 */       for (int i = 0; i < this.nDeletedPersons; i++) fout.printtab(this.deletedPersonsIndices[i] + 1);
/*  332 */       fout.println();
/*      */     }
/*      */     else {
/*  335 */       fout.println("No persons were deleted ");
/*      */     }
/*      */     
/*      */ 
/*  339 */     if (this.nDeletedItems != 0) {
/*  340 */       deletionFlag = true;
/*  341 */       fout.printtab("Number of items deleted ");
/*  342 */       fout.println(this.nDeletedItems);
/*  343 */       fout.printtab("Names of deleted items: ");
/*  344 */       for (int i = 0; i < this.nDeletedItems; i++) fout.printtab(this.originalItemNames[this.deletedItemsIndices[i]]);
/*  345 */       fout.println();
/*      */     }
/*      */     else {
/*  348 */       fout.println("No items were deleted ");
/*      */     }
/*      */     
/*      */ 
/*  352 */     if (this.nReplacements != 0) {
/*  353 */       fout.printtab("Number of 'no responses' replaced ");
/*  354 */       fout.println(this.nReplacements);
/*  355 */       fout.printtab("Item name and person index of replacements: ");
/*  356 */       for (int i = 0; i < this.nReplacements; i++) fout.printtab(this.replacementIndices[i]);
/*  357 */       fout.printtab("Replacement option: ");
/*  358 */       fout.println(this.replacementOptionNames[(this.replacementOption - 1)]);
/*  359 */       fout.println();
/*      */ 
/*      */     }
/*  362 */     else if (deletionFlag) {
/*  363 */       fout.println("No 'no response' replacements, other than any above deletions, were made ");
/*      */     }
/*      */     else {
/*  366 */       fout.println("No 'no response' replacements were made ");
/*      */     }
/*      */     
/*  369 */     fout.println();
/*  370 */     fout.printtab("Number of items used         ");
/*  371 */     fout.println(this.nItems);
/*  372 */     fout.printtab("Number of persons used   ");
/*  373 */     fout.println(this.nPersons);
/*  374 */     fout.println();
/*      */     
/*      */ 
/*  377 */     fout.println("CORRELATION COEFFICIENTS");
/*  378 */     fout.println("Correlation coefficients between items  -  raw data");
/*  379 */     fout.printtab("    ");
/*  380 */     for (int i = 0; i <= this.nItems; i++) fout.printtab(this.itemNames[i]);
/*  381 */     fout.println();
/*  382 */     for (int i = 0; i <= this.nItems; i++) {
/*  383 */       fout.printtab(this.itemNames[i]);
/*  384 */       for (int j = 0; j <= this.nItems; j++) fout.printtab(Fmath.truncate(this.rawCorrelationCoefficients[i][j], this.trunc));
/*  385 */       fout.println();
/*      */     }
/*  387 */     fout.println();
/*      */     
/*  389 */     fout.print("Average inter-item correlation coefficient (excluding total)                    ");
/*  390 */     fout.println(Fmath.truncate(this.rawMeanRhoWithoutTotals, this.trunc));
/*  391 */     fout.print("Standard deviation of the inter-item correlation coefficient (excluding total)  ");
/*  392 */     fout.println(Fmath.truncate(this.rawStandardDeviationRhoWithoutTotals, this.trunc));
/*  393 */     fout.print("Average inter-item correlation coefficient (including total)                    ");
/*  394 */     fout.println(Fmath.truncate(this.rawMeanRhoWithTotals, this.trunc));
/*  395 */     fout.print("Standard deviation of the inter-item correlation coefficient (including total)  ");
/*  396 */     fout.println(Fmath.truncate(this.rawStandardDeviationRhoWithTotals, this.trunc));
/*  397 */     fout.println();
/*      */     
/*      */ 
/*  400 */     fout.println("Correlation coefficients between items  -  standardized data");
/*  401 */     fout.printtab("    ");
/*  402 */     for (int i = 0; i <= this.nItems; i++) fout.printtab(this.itemNames[i]);
/*  403 */     fout.println();
/*  404 */     for (int i = 0; i <= this.nItems; i++) {
/*  405 */       fout.printtab(this.itemNames[i]);
/*  406 */       for (int j = 0; j <= this.nItems; j++) fout.printtab(Fmath.truncate(this.standardizedCorrelationCoefficients[i][j], this.trunc));
/*  407 */       fout.println();
/*      */     }
/*  409 */     fout.println();
/*      */     
/*  411 */     fout.print("Average inter-item correlation coefficient (excluding total)                    ");
/*  412 */     fout.println(Fmath.truncate(this.standardizedMeanRhoWithoutTotals, this.trunc));
/*  413 */     fout.print("Standard deviation of the inter-item correlation coefficient (excluding total)  ");
/*  414 */     fout.println(Fmath.truncate(this.standardizedStandardDeviationRhoWithoutTotals, this.trunc));
/*  415 */     fout.print("Average inter-item correlation coefficient (including total)                    ");
/*  416 */     fout.println(Fmath.truncate(this.standardizedMeanRhoWithTotals, this.trunc));
/*  417 */     fout.print("Standard deviation of the inter-item correlation coefficient (including total)  ");
/*  418 */     fout.println(Fmath.truncate(this.standardizedStandardDeviationRhoWithTotals, this.trunc));
/*  419 */     fout.println();
/*      */     
/*      */ 
/*      */ 
/*  423 */     fout.println("ITEMS: MEANS, STANDARD DEVIATIONS, SKENESS AND KURTOSIS");
/*  424 */     fout.println("Raw data");
/*  425 */     fout.printtab("item ");
/*  426 */     fout.printtab("mean");
/*  427 */     fout.printtab("standard");
/*  428 */     fout.printtab("moment");
/*  429 */     fout.printtab("median");
/*  430 */     fout.printtab("quartile");
/*  431 */     fout.printtab("kurtosis");
/*  432 */     fout.println("dichotomous");
/*      */     
/*  434 */     fout.printtab("    ");
/*  435 */     fout.printtab("    ");
/*  436 */     fout.printtab("deviation");
/*  437 */     fout.printtab("skewness");
/*  438 */     fout.printtab("skewness");
/*  439 */     fout.printtab("skewness");
/*  440 */     fout.printtab("excess  ");
/*  441 */     fout.println("percentage");
/*      */     
/*  443 */     for (int i = 0; i < this.nItems; i++) {
/*  444 */       fout.printtab(this.itemNames[i]);
/*  445 */       fout.printtab(Fmath.truncate(this.rawItemMeans[i], this.trunc));
/*  446 */       fout.printtab(Fmath.truncate(this.rawItemStandardDeviations[i], this.trunc));
/*  447 */       fout.printtab(Fmath.truncate(this.rawItemMomentSkewness[i], this.trunc));
/*  448 */       fout.printtab(Fmath.truncate(this.rawItemMedianSkewness[i], this.trunc));
/*  449 */       fout.printtab(Fmath.truncate(this.rawItemQuartileSkewness[i], this.trunc));
/*  450 */       fout.printtab(Fmath.truncate(this.rawItemKurtosisExcess[i], this.trunc));
/*  451 */       fout.println(Fmath.truncate(this.dichotomousPercentage[i], 1));
/*      */     }
/*  453 */     fout.println();
/*      */     
/*  455 */     fout.println("ITEMS: MINIMA, MAXIMA, MEDIANS, RANGES AND TOTALS");
/*  456 */     fout.println("raw data");
/*  457 */     fout.printtab("item ");
/*  458 */     fout.printtab("minimum");
/*  459 */     fout.printtab("maximum");
/*  460 */     fout.printtab("median");
/*  461 */     fout.printtab("range");
/*  462 */     fout.printtab("total");
/*  463 */     fout.println("dichotomous");
/*      */     
/*  465 */     fout.printtab("    ");
/*  466 */     fout.printtab("    ");
/*  467 */     fout.printtab("     ");
/*  468 */     fout.printtab("     ");
/*  469 */     fout.printtab("     ");
/*  470 */     fout.printtab("     ");
/*  471 */     fout.println("percentage");
/*      */     
/*  473 */     for (int i = 0; i < this.nItems; i++) {
/*  474 */       fout.printtab(this.itemNames[i]);
/*  475 */       fout.printtab(Fmath.truncate(this.rawItemMinima[i], this.trunc));
/*  476 */       fout.printtab(Fmath.truncate(this.rawItemMaxima[i], this.trunc));
/*  477 */       fout.printtab(Fmath.truncate(this.rawItemMedians[i], this.trunc));
/*  478 */       fout.printtab(Fmath.truncate(this.rawItemRanges[i], this.trunc));
/*  479 */       fout.printtab(Fmath.truncate(this.rawItemTotals[i], this.trunc));
/*  480 */       fout.println(Fmath.truncate(this.dichotomousPercentage[i], 1));
/*      */     }
/*  482 */     fout.println();
/*      */     
/*  484 */     fout.printtab("item");
/*  485 */     fout.printtab("mean");
/*  486 */     fout.printtab("standard");
/*  487 */     fout.printtab("variance");
/*  488 */     fout.printtab("minimum");
/*  489 */     fout.printtab("maximum");
/*  490 */     fout.println("range");
/*  491 */     fout.printtab("statistic    ");
/*  492 */     fout.printtab("    ");
/*  493 */     fout.printtab("deviation");
/*  494 */     fout.printtab("     ");
/*  495 */     fout.printtab("     ");
/*  496 */     fout.printtab("     ");
/*  497 */     fout.printtab("     ");
/*  498 */     fout.println("     ");
/*      */     
/*  500 */     fout.printtab("item means");
/*  501 */     fout.printtab(Fmath.truncate(this.rawItemMeansMean, this.trunc));
/*  502 */     fout.printtab(Fmath.truncate(this.rawItemMeansSd, this.trunc));
/*  503 */     fout.printtab(Fmath.truncate(this.rawItemMeansVar, this.trunc));
/*  504 */     fout.printtab(Fmath.truncate(this.rawItemMeansMin, this.trunc));
/*  505 */     fout.printtab(Fmath.truncate(this.rawItemMeansMax, this.trunc));
/*  506 */     fout.println(Fmath.truncate(this.rawItemMeansRange, this.trunc));
/*      */     
/*  508 */     fout.printtab("item standard deviations");
/*  509 */     fout.printtab(Fmath.truncate(this.rawItemStandardDeviationsMean, this.trunc));
/*  510 */     fout.printtab(Fmath.truncate(this.rawItemStandardDeviationsSd, this.trunc));
/*  511 */     fout.printtab(Fmath.truncate(this.rawItemStandardDeviationsVar, this.trunc));
/*  512 */     fout.printtab(Fmath.truncate(this.rawItemStandardDeviationsMin, this.trunc));
/*  513 */     fout.printtab(Fmath.truncate(this.rawItemStandardDeviationsMax, this.trunc));
/*  514 */     fout.println(Fmath.truncate(this.rawItemStandardDeviationsRange, this.trunc));
/*      */     
/*  516 */     fout.printtab("item variances");
/*  517 */     fout.printtab(Fmath.truncate(this.rawItemVariancesMean, this.trunc));
/*  518 */     fout.printtab(Fmath.truncate(this.rawItemVariancesSd, this.trunc));
/*  519 */     fout.printtab(Fmath.truncate(this.rawItemVariancesVar, this.trunc));
/*  520 */     fout.printtab(Fmath.truncate(this.rawItemVariancesMin, this.trunc));
/*  521 */     fout.printtab(Fmath.truncate(this.rawItemVariancesMax, this.trunc));
/*  522 */     fout.println(Fmath.truncate(this.rawItemVariancesRange, this.trunc));
/*      */     
/*  524 */     fout.printtab("item mimima");
/*  525 */     fout.printtab(Fmath.truncate(this.rawItemMinimaMean, this.trunc));
/*  526 */     fout.printtab(Fmath.truncate(this.rawItemMinimaSd, this.trunc));
/*  527 */     fout.printtab(Fmath.truncate(this.rawItemMinimaVar, this.trunc));
/*  528 */     fout.printtab(Fmath.truncate(this.rawItemMinimaMin, this.trunc));
/*  529 */     fout.printtab(Fmath.truncate(this.rawItemMinimaMax, this.trunc));
/*  530 */     fout.println(Fmath.truncate(this.rawItemMinimaRange, this.trunc));
/*      */     
/*  532 */     fout.printtab("item maxima");
/*  533 */     fout.printtab(Fmath.truncate(this.rawItemMaximaMean, this.trunc));
/*  534 */     fout.printtab(Fmath.truncate(this.rawItemMaximaSd, this.trunc));
/*  535 */     fout.printtab(Fmath.truncate(this.rawItemMaximaVar, this.trunc));
/*  536 */     fout.printtab(Fmath.truncate(this.rawItemMaximaMin, this.trunc));
/*  537 */     fout.printtab(Fmath.truncate(this.rawItemMaximaMax, this.trunc));
/*  538 */     fout.println(Fmath.truncate(this.rawItemMaximaRange, this.trunc));
/*      */     
/*  540 */     fout.printtab("item medians");
/*  541 */     fout.printtab(Fmath.truncate(this.rawItemMediansMean, this.trunc));
/*  542 */     fout.printtab(Fmath.truncate(this.rawItemMediansSd, this.trunc));
/*  543 */     fout.printtab(Fmath.truncate(this.rawItemMediansVar, this.trunc));
/*  544 */     fout.printtab(Fmath.truncate(this.rawItemMediansMin, this.trunc));
/*  545 */     fout.printtab(Fmath.truncate(this.rawItemMediansMax, this.trunc));
/*  546 */     fout.println(Fmath.truncate(this.rawItemMediansRange, this.trunc));
/*      */     
/*  548 */     fout.printtab("item ranges");
/*  549 */     fout.printtab(Fmath.truncate(this.rawItemRangesMean, this.trunc));
/*  550 */     fout.printtab(Fmath.truncate(this.rawItemRangesSd, this.trunc));
/*  551 */     fout.printtab(Fmath.truncate(this.rawItemRangesVar, this.trunc));
/*  552 */     fout.printtab(Fmath.truncate(this.rawItemRangesMin, this.trunc));
/*  553 */     fout.printtab(Fmath.truncate(this.rawItemRangesMax, this.trunc));
/*  554 */     fout.println(Fmath.truncate(this.rawItemRangesRange, this.trunc));
/*      */     
/*  556 */     fout.printtab("item totals");
/*  557 */     fout.printtab(Fmath.truncate(this.rawItemTotalsMean, this.trunc));
/*  558 */     fout.printtab(Fmath.truncate(this.rawItemTotalsSd, this.trunc));
/*  559 */     fout.printtab(Fmath.truncate(this.rawItemTotalsVar, this.trunc));
/*  560 */     fout.printtab(Fmath.truncate(this.rawItemTotalsMin, this.trunc));
/*  561 */     fout.printtab(Fmath.truncate(this.rawItemTotalsMax, this.trunc));
/*  562 */     fout.println(Fmath.truncate(this.rawItemTotalsRange, this.trunc));
/*      */     
/*  564 */     fout.println();
/*      */     
/*  566 */     fout.println("Standardized data");
/*  567 */     fout.println("ITEMS: MEANS, STANDARD DEVIATIONS, SKENESS AND KURTOSIS");
/*  568 */     fout.printtab("item ");
/*  569 */     fout.printtab("mean");
/*  570 */     fout.printtab("standard");
/*  571 */     fout.printtab("moment");
/*  572 */     fout.printtab("median");
/*  573 */     fout.printtab("quartile");
/*  574 */     fout.println("kurtosis");
/*      */     
/*  576 */     fout.printtab("    ");
/*  577 */     fout.printtab("    ");
/*  578 */     fout.printtab("deviation");
/*  579 */     fout.printtab("skewness");
/*  580 */     fout.printtab("skewness");
/*  581 */     fout.printtab("skewness");
/*  582 */     fout.println("excess  ");
/*      */     
/*  584 */     for (int i = 0; i < this.nItems; i++) {
/*  585 */       fout.printtab(this.itemNames[i]);
/*  586 */       fout.printtab(Fmath.truncate(this.standardizedItemMeans[i], this.trunc));
/*  587 */       fout.printtab(Fmath.truncate(this.standardizedItemStandardDeviations[i], this.trunc));
/*  588 */       fout.printtab(Fmath.truncate(this.standardizedItemMomentSkewness[i], this.trunc));
/*  589 */       fout.printtab(Fmath.truncate(this.standardizedItemMedianSkewness[i], this.trunc));
/*  590 */       fout.printtab(Fmath.truncate(this.standardizedItemQuartileSkewness[i], this.trunc));
/*  591 */       fout.println(Fmath.truncate(this.standardizedItemKurtosisExcess[i], this.trunc));
/*      */     }
/*  593 */     fout.println();
/*      */     
/*  595 */     fout.println("ITEMS: MINIMA, MAXIMA, MEDIANS, RANGES AND TOTALS");
/*  596 */     fout.println("Standardized data");
/*  597 */     fout.printtab("item ");
/*  598 */     fout.printtab("minimum");
/*  599 */     fout.printtab("maximum");
/*  600 */     fout.printtab("median");
/*  601 */     fout.printtab("range");
/*  602 */     fout.println("total");
/*      */     
/*  604 */     fout.printtab("    ");
/*  605 */     fout.printtab("    ");
/*  606 */     fout.printtab("     ");
/*  607 */     fout.printtab("     ");
/*  608 */     fout.printtab("     ");
/*  609 */     fout.println("     ");
/*      */     
/*  611 */     for (int i = 0; i < this.nItems; i++) {
/*  612 */       fout.printtab(this.itemNames[i]);
/*  613 */       fout.printtab(Fmath.truncate(this.standardizedItemMinima[i], this.trunc));
/*  614 */       fout.printtab(Fmath.truncate(this.standardizedItemMaxima[i], this.trunc));
/*  615 */       fout.printtab(Fmath.truncate(this.standardizedItemMedians[i], this.trunc));
/*  616 */       fout.printtab(Fmath.truncate(this.standardizedItemRanges[i], this.trunc));
/*  617 */       fout.println(Fmath.truncate(this.standardizedItemTotals[i], this.trunc));
/*      */     }
/*  619 */     fout.println();
/*      */     
/*      */ 
/*      */ 
/*  623 */     fout.printtab("item");
/*  624 */     fout.printtab("mean");
/*  625 */     fout.printtab("standard");
/*  626 */     fout.printtab("variance");
/*  627 */     fout.printtab("minimum");
/*  628 */     fout.printtab("maximum");
/*  629 */     fout.println("range");
/*      */     
/*  631 */     fout.printtab("statistic    ");
/*  632 */     fout.printtab("    ");
/*  633 */     fout.printtab("deviation");
/*  634 */     fout.printtab("     ");
/*  635 */     fout.printtab("     ");
/*  636 */     fout.printtab("     ");
/*  637 */     fout.printtab("     ");
/*  638 */     fout.println("     ");
/*      */     
/*  640 */     fout.printtab("item means");
/*  641 */     fout.printtab(Fmath.truncate(this.standardizedItemMeansMean, this.trunc));
/*  642 */     fout.printtab(Fmath.truncate(this.standardizedItemMeansSd, this.trunc));
/*  643 */     fout.printtab(Fmath.truncate(this.standardizedItemMeansVar, this.trunc));
/*  644 */     fout.printtab(Fmath.truncate(this.standardizedItemMeansMin, this.trunc));
/*  645 */     fout.printtab(Fmath.truncate(this.standardizedItemMeansMax, this.trunc));
/*  646 */     fout.println(Fmath.truncate(this.standardizedItemMeansRange, this.trunc));
/*      */     
/*  648 */     fout.printtab("item standard deviations");
/*  649 */     fout.printtab(Fmath.truncate(this.standardizedItemStandardDeviationsMean, this.trunc));
/*  650 */     fout.printtab(Fmath.truncate(this.standardizedItemStandardDeviationsSd, this.trunc));
/*  651 */     fout.printtab(Fmath.truncate(this.standardizedItemStandardDeviationsVar, this.trunc));
/*  652 */     fout.printtab(Fmath.truncate(this.standardizedItemStandardDeviationsMin, this.trunc));
/*  653 */     fout.printtab(Fmath.truncate(this.standardizedItemStandardDeviationsMax, this.trunc));
/*  654 */     fout.println(Fmath.truncate(this.standardizedItemStandardDeviationsRange, this.trunc));
/*      */     
/*  656 */     fout.printtab("item variances");
/*  657 */     fout.printtab(Fmath.truncate(this.standardizedItemVariancesMean, this.trunc));
/*  658 */     fout.printtab(Fmath.truncate(this.standardizedItemVariancesSd, this.trunc));
/*  659 */     fout.printtab(Fmath.truncate(this.standardizedItemVariancesVar, this.trunc));
/*  660 */     fout.printtab(Fmath.truncate(this.standardizedItemVariancesMin, this.trunc));
/*  661 */     fout.printtab(Fmath.truncate(this.standardizedItemVariancesMax, this.trunc));
/*  662 */     fout.println(Fmath.truncate(this.standardizedItemVariancesRange, this.trunc));
/*      */     
/*  664 */     fout.printtab("item mimima");
/*  665 */     fout.printtab(Fmath.truncate(this.standardizedItemMinimaMean, this.trunc));
/*  666 */     fout.printtab(Fmath.truncate(this.standardizedItemMinimaSd, this.trunc));
/*  667 */     fout.printtab(Fmath.truncate(this.standardizedItemMinimaVar, this.trunc));
/*  668 */     fout.printtab(Fmath.truncate(this.standardizedItemMinimaMin, this.trunc));
/*  669 */     fout.printtab(Fmath.truncate(this.standardizedItemMinimaMax, this.trunc));
/*  670 */     fout.println(Fmath.truncate(this.standardizedItemMinimaRange, this.trunc));
/*      */     
/*  672 */     fout.printtab("item maxima");
/*  673 */     fout.printtab(Fmath.truncate(this.standardizedItemMaximaMean, this.trunc));
/*  674 */     fout.printtab(Fmath.truncate(this.standardizedItemMaximaSd, this.trunc));
/*  675 */     fout.printtab(Fmath.truncate(this.standardizedItemMaximaVar, this.trunc));
/*  676 */     fout.printtab(Fmath.truncate(this.standardizedItemMaximaMin, this.trunc));
/*  677 */     fout.printtab(Fmath.truncate(this.standardizedItemMaximaMax, this.trunc));
/*  678 */     fout.println(Fmath.truncate(this.standardizedItemMaximaRange, this.trunc));
/*      */     
/*  680 */     fout.print("item medians");
/*  681 */     fout.print(Fmath.truncate(this.rawItemMediansMean, this.trunc));
/*  682 */     fout.print(Fmath.truncate(this.rawItemMediansSd, this.trunc));
/*  683 */     fout.print(Fmath.truncate(this.rawItemMediansVar, this.trunc));
/*  684 */     fout.print(Fmath.truncate(this.rawItemMediansMin, this.trunc));
/*  685 */     fout.print(Fmath.truncate(this.rawItemMediansMax, this.trunc));
/*  686 */     fout.println(Fmath.truncate(this.rawItemMediansRange, this.trunc));
/*      */     
/*  688 */     fout.printtab("item ranges");
/*  689 */     fout.printtab(Fmath.truncate(this.standardizedItemRangesMean, this.trunc));
/*  690 */     fout.printtab(Fmath.truncate(this.standardizedItemRangesSd, this.trunc));
/*  691 */     fout.printtab(Fmath.truncate(this.standardizedItemRangesVar, this.trunc));
/*  692 */     fout.printtab(Fmath.truncate(this.standardizedItemRangesMin, this.trunc));
/*  693 */     fout.printtab(Fmath.truncate(this.standardizedItemRangesMax, this.trunc));
/*  694 */     fout.println(Fmath.truncate(this.standardizedItemRangesRange, this.trunc));
/*      */     
/*  696 */     fout.printtab("item totals");
/*  697 */     fout.printtab(Fmath.truncate(this.standardizedItemTotalsMean, this.trunc));
/*  698 */     fout.printtab(Fmath.truncate(this.standardizedItemTotalsSd, this.trunc));
/*  699 */     fout.printtab(Fmath.truncate(this.standardizedItemTotalsVar, this.trunc));
/*  700 */     fout.printtab(Fmath.truncate(this.standardizedItemTotalsMin, this.trunc));
/*  701 */     fout.printtab(Fmath.truncate(this.standardizedItemTotalsMax, this.trunc));
/*  702 */     fout.println(Fmath.truncate(this.standardizedItemTotalsRange, this.trunc));
/*      */     
/*  704 */     fout.println();
/*      */     
/*  706 */     fout.println("DELETION OF ITEMS");
/*      */     
/*  708 */     fout.printtab("                ");
/*  709 */     fout.printtab("Raw data        ");
/*  710 */     fout.printtab("                ");
/*  711 */     fout.printtab("                ");
/*  712 */     fout.printtab("                ");
/*  713 */     fout.println("Standardized data");
/*      */     
/*  715 */     fout.printtab("Deleted item");
/*  716 */     fout.printtab("Alpha       ");
/*  717 */     fout.printtab("Correlation ");
/*  718 */     fout.printtab("Average     ");
/*  719 */     fout.printtab("Average     ");
/*  720 */     fout.printtab("Alpha       ");
/*  721 */     fout.printtab("Correlation ");
/*  722 */     fout.printtab("Average     ");
/*  723 */     fout.println("Average     ");
/*      */     
/*      */ 
/*  726 */     fout.printtab("           ");
/*  727 */     fout.printtab("           ");
/*  728 */     fout.printtab("coefficient");
/*  729 */     fout.printtab("inter-item ");
/*  730 */     fout.printtab("inter-item ");
/*  731 */     fout.printtab("           ");
/*  732 */     fout.printtab("coefficient ");
/*  733 */     fout.printtab("inter-item ");
/*  734 */     fout.println("inter-item ");
/*      */     
/*      */ 
/*  737 */     fout.printtab("           ");
/*  738 */     fout.printtab("           ");
/*  739 */     fout.printtab("with total ");
/*  740 */     fout.printtab("correlation");
/*  741 */     fout.printtab("correlation");
/*  742 */     fout.printtab("           ");
/*  743 */     fout.printtab("with total ");
/*  744 */     fout.printtab("correlation");
/*  745 */     fout.println("correlation");
/*      */     
/*      */ 
/*  748 */     fout.printtab("           ");
/*  749 */     fout.printtab("           ");
/*  750 */     fout.printtab("           ");
/*  751 */     fout.printtab("coefficient");
/*  752 */     fout.printtab("coefficient");
/*  753 */     fout.printtab("           ");
/*  754 */     fout.printtab("           ");
/*  755 */     fout.printtab("coefficient");
/*  756 */     fout.println("coefficient");
/*      */     
/*      */ 
/*  759 */     fout.printtab("              ");
/*  760 */     fout.printtab("              ");
/*  761 */     fout.printtab("              ");
/*  762 */     fout.printtab("without totals");
/*  763 */     fout.printtab("with totals   ");
/*  764 */     fout.printtab("              ");
/*  765 */     fout.printtab("              ");
/*  766 */     fout.printtab("without totals");
/*  767 */     fout.println("with totals   ");
/*      */     
/*  769 */     double[] newRawAlpha = new double[this.nItems];
/*  770 */     double[] newStandardizedAlpha = new double[this.nItems];
/*  771 */     double[] newRawRho = new double[this.nItems];
/*  772 */     double[] newStandardizedRho = new double[this.nItems];
/*  773 */     for (int i = 0; i < this.nItems; i++) {
/*  774 */       int index = i + 1;
/*  775 */       double[][] newScore1 = deleteItem(index);
/*  776 */       Cronbach cr = new Cronbach();
/*  777 */       cr.enterScoresAsRowPerPerson(newScore1);
/*  778 */       double rawAlphaD = cr.rawAlpha();
/*  779 */       newRawAlpha[i] = rawAlphaD;
/*  780 */       double rawMeanRhoWithTotalsD = cr.rawAverageCorrelationCoefficientsWithTotals();
/*  781 */       double rawMeanRhoWithoutTotalsD = cr.rawAverageCorrelationCoefficients();
/*  782 */       double[] rawPersonTotalsD = cr.rawPersonTotals();
/*  783 */       double rawRhoAgainstTotalsD = Stat.corrCoeff(rawPersonTotalsD, this.scores0[i]);
/*  784 */       newRawRho[i] = rawRhoAgainstTotalsD;
/*      */       
/*  786 */       double standardizedAlphaD = cr.standardizedAlpha();
/*  787 */       newStandardizedAlpha[i] = standardizedAlphaD;
/*  788 */       double standardizedMeanRhoWithTotalsD = cr.standardizedAverageCorrelationCoefficientsWithTotals();
/*  789 */       double standardizedMeanRhoWithoutTotalsD = cr.standardizedAverageCorrelationCoefficients();
/*  790 */       double[] standardizedPersonTotalsD = cr.standardizedPersonTotals();
/*  791 */       double standardizedRhoAgainstTotalsD = Stat.corrCoeff(standardizedPersonTotalsD, this.scores0[i]);
/*  792 */       newStandardizedRho[i] = standardizedRhoAgainstTotalsD;
/*      */       
/*  794 */       fout.printtab(this.itemNames[i]);
/*  795 */       fout.printtab(Fmath.truncate(rawAlphaD, this.trunc));
/*  796 */       fout.printtab(Fmath.truncate(rawRhoAgainstTotalsD, this.trunc));
/*  797 */       fout.printtab(Fmath.truncate(rawMeanRhoWithoutTotalsD, this.trunc));
/*  798 */       fout.printtab(Fmath.truncate(rawMeanRhoWithTotalsD, this.trunc));
/*      */       
/*  800 */       fout.printtab(Fmath.truncate(standardizedAlphaD, this.trunc));
/*  801 */       fout.printtab(Fmath.truncate(standardizedRhoAgainstTotalsD, this.trunc));
/*  802 */       fout.printtab(Fmath.truncate(standardizedMeanRhoWithoutTotalsD, this.trunc));
/*  803 */       fout.println(Fmath.truncate(standardizedMeanRhoWithTotalsD, this.trunc));
/*      */     }
/*  805 */     fout.println();
/*      */     
/*  807 */     fout.printtab("No item deleted");
/*  808 */     fout.printtab(Fmath.truncate(this.rawAlpha, this.trunc));
/*  809 */     fout.printtab("   ");
/*  810 */     fout.printtab(Fmath.truncate(this.rawMeanRhoWithoutTotals, this.trunc));
/*  811 */     fout.printtab(Fmath.truncate(this.rawMeanRhoWithTotals, this.trunc));
/*      */     
/*  813 */     fout.printtab(Fmath.truncate(this.standardizedAlpha, this.trunc));
/*  814 */     fout.printtab("   ");
/*  815 */     fout.printtab(Fmath.truncate(this.standardizedMeanRhoWithoutTotals, this.trunc));
/*  816 */     fout.println(Fmath.truncate(this.standardizedMeanRhoWithTotals, this.trunc));
/*  817 */     fout.println();
/*      */     
/*      */ 
/*  820 */     deletedItemDataFile(newRawAlpha, newRawRho, newStandardizedAlpha, newStandardizedRho);
/*      */     
/*      */ 
/*  823 */     fout.println("INDIVIDUALS - raw data");
/*  824 */     fout.printtab("person ");
/*  825 */     fout.printtab("mean");
/*  826 */     fout.printtab("standard");
/*  827 */     fout.printtab("minimum");
/*  828 */     fout.printtab("maximum");
/*  829 */     fout.printtab("range");
/*  830 */     fout.printtab("total");
/*  831 */     fout.println("scores:");
/*      */     
/*  833 */     fout.printtab("    ");
/*  834 */     fout.printtab("    ");
/*  835 */     fout.printtab("deviation");
/*  836 */     fout.printtab("     ");
/*  837 */     fout.printtab("     ");
/*  838 */     fout.printtab("     ");
/*  839 */     fout.printtab("     ");
/*  840 */     for (int i = 0; i < this.nItems; i++) fout.printtab(this.itemNames[i]);
/*  841 */     fout.println();
/*      */     
/*  843 */     for (int i = 0; i < this.nPersons; i++) {
/*  844 */       fout.printtab(this.personIndices[i] + 1);
/*  845 */       fout.printtab(Fmath.truncate(this.rawPersonMeans[i], this.trunc));
/*  846 */       fout.printtab(Fmath.truncate(this.rawPersonStandardDeviations[i], this.trunc));
/*  847 */       fout.printtab(Fmath.truncate(this.rawPersonMinima[i], this.trunc));
/*  848 */       fout.printtab(Fmath.truncate(this.rawPersonMaxima[i], this.trunc));
/*  849 */       fout.printtab(Fmath.truncate(this.rawPersonRanges[i], this.trunc));
/*  850 */       fout.printtab(Fmath.truncate(this.rawPersonTotals[i], this.trunc));
/*  851 */       for (int j = 0; j < this.nItems; j++) fout.printtab(this.scores1[i][j]);
/*  852 */       fout.println();
/*      */     }
/*  854 */     fout.println();
/*      */     
/*  856 */     fout.println("INDIVIDUALS - standardized data");
/*  857 */     fout.printtab("person ");
/*  858 */     fout.printtab("mean");
/*  859 */     fout.printtab("standard");
/*  860 */     fout.printtab("minimum");
/*  861 */     fout.printtab("maximum");
/*  862 */     fout.printtab("range");
/*  863 */     fout.printtab("total");
/*  864 */     fout.println("scores:");
/*      */     
/*  866 */     fout.printtab("    ");
/*  867 */     fout.printtab("    ");
/*  868 */     fout.printtab("deviation");
/*  869 */     fout.printtab("     ");
/*  870 */     fout.printtab("     ");
/*  871 */     fout.printtab("     ");
/*  872 */     fout.printtab("     ");
/*  873 */     for (int i = 0; i < this.nItems; i++) fout.printtab(this.itemNames[i]);
/*  874 */     fout.println();
/*      */     
/*      */ 
/*  877 */     for (int i = 0; i < this.nPersons; i++) {
/*  878 */       fout.printtab(this.personIndices[i] + 1);
/*  879 */       fout.printtab(Fmath.truncate(this.standardizedPersonMeans[i], this.trunc));
/*  880 */       fout.printtab(Fmath.truncate(this.standardizedPersonStandardDeviations[i], this.trunc));
/*  881 */       fout.printtab(Fmath.truncate(this.standardizedPersonMinima[i], this.trunc));
/*  882 */       fout.printtab(Fmath.truncate(this.standardizedPersonMaxima[i], this.trunc));
/*  883 */       fout.printtab(Fmath.truncate(this.standardizedPersonRanges[i], this.trunc));
/*  884 */       fout.printtab(Fmath.truncate(this.standardizedPersonTotals[i], this.trunc));
/*  885 */       for (int j = 0; j < this.nItems; j++) fout.printtab(Fmath.truncate(this.standardizedScores1[i][j], this.trunc));
/*  886 */       fout.println();
/*      */     }
/*  888 */     fout.println();
/*      */     
/*  890 */     fout.println("ALL SCORES - raw data");
/*      */     
/*  892 */     fout.printtab("mean");
/*  893 */     fout.printtab("standard");
/*  894 */     fout.printtab("minimum");
/*  895 */     fout.printtab("maximum");
/*  896 */     fout.printtab("range");
/*  897 */     fout.println("overall");
/*  898 */     fout.printtab("    ");
/*  899 */     fout.printtab("deviation");
/*  900 */     fout.printtab("     ");
/*  901 */     fout.printtab("     ");
/*  902 */     fout.printtab("     ");
/*  903 */     fout.println("total");
/*      */     
/*  905 */     fout.printtab(Fmath.truncate(this.rawAllResponsesMean, this.trunc));
/*  906 */     fout.printtab(Fmath.truncate(this.rawAllResponsesStandardDeviation, this.trunc));
/*  907 */     fout.printtab(Fmath.truncate(this.rawAllResponsesMinimum, this.trunc));
/*  908 */     fout.printtab(Fmath.truncate(this.rawAllResponsesMaximum, this.trunc));
/*  909 */     fout.printtab(Fmath.truncate(this.rawAllResponsesRange, this.trunc));
/*  910 */     fout.println(Fmath.truncate(this.rawAllResponsesTotal, this.trunc));
/*  911 */     fout.println();
/*      */     
/*  913 */     fout.println("ALL SCORES - standardized data");
/*      */     
/*  915 */     fout.printtab("mean");
/*  916 */     fout.printtab("standard");
/*  917 */     fout.printtab("minimum");
/*  918 */     fout.printtab("maximum");
/*  919 */     fout.printtab("range");
/*  920 */     fout.println("overall");
/*  921 */     fout.printtab("    ");
/*  922 */     fout.printtab("deviation");
/*  923 */     fout.printtab("     ");
/*  924 */     fout.printtab("     ");
/*  925 */     fout.printtab("     ");
/*  926 */     fout.println("total");
/*      */     
/*  928 */     fout.printtab(Fmath.truncate(this.standardizedAllResponsesMean, this.trunc));
/*  929 */     fout.printtab(Fmath.truncate(this.standardizedAllResponsesStandardDeviation, this.trunc));
/*  930 */     fout.printtab(Fmath.truncate(this.standardizedAllResponsesMinimum, this.trunc));
/*  931 */     fout.printtab(Fmath.truncate(this.standardizedAllResponsesMaximum, this.trunc));
/*  932 */     fout.printtab(Fmath.truncate(this.standardizedAllResponsesRange, this.trunc));
/*  933 */     fout.println(Fmath.truncate(this.standardizedAllResponsesTotal, this.trunc));
/*  934 */     fout.println();
/*      */     
/*      */ 
/*  937 */     fout.close();
/*      */   }
/*      */   
/*      */ 
/*      */   private void analysisText()
/*      */   {
/*  943 */     FileOutput fout = null;
/*  944 */     if (this.fileNumberingSet) {
/*  945 */       fout = new FileOutput(this.outputFilename, 'n');
/*      */     }
/*      */     else {
/*  948 */       fout = new FileOutput(this.outputFilename);
/*      */     }
/*      */     
/*      */ 
/*  952 */     if (!this.rawAlphaCalculated) rawAlpha();
/*  953 */     if (!this.standardizedAlphaCalculated) { standardizedAlpha();
/*      */     }
/*      */     
/*  956 */     fout.println("CRONBACH'S ALPHA RELIABILITY ESTIMATOR");
/*  957 */     fout.println("Program: Cronbach - Analysis Output");
/*  958 */     for (int i = 0; i < this.titleLines; i++) fout.println(this.title[i]);
/*  959 */     Date d = new Date();
/*  960 */     String day = DateFormat.getDateInstance().format(d);
/*  961 */     String tim = DateFormat.getTimeInstance().format(d);
/*  962 */     fout.println("Program executed at " + tim + " on " + day);
/*  963 */     fout.println();
/*      */     
/*      */ 
/*  966 */     int field = 36;
/*  967 */     fout.println("RELIABILITY ESTIMATORS");
/*  968 */     fout.println("Cronbach's coefficient alpha");
/*  969 */     fout.print("Raw data ", field);
/*  970 */     fout.println(Fmath.truncate(this.rawAlpha, this.trunc));
/*  971 */     fout.print("Standardized data ", field);
/*  972 */     fout.println(Fmath.truncate(this.standardizedAlpha, this.trunc));
/*  973 */     fout.println();
/*      */     
/*  975 */     fout.println("Average of the inter-item correlation coefficients, excluding item totals");
/*  976 */     fout.print("Raw data ", field);
/*  977 */     fout.println(Fmath.truncate(this.rawMeanRhoWithoutTotals, this.trunc));
/*  978 */     fout.print("Standardized data ", field);
/*  979 */     fout.println(Fmath.truncate(this.standardizedMeanRhoWithoutTotals, this.trunc));
/*  980 */     fout.println();
/*      */     
/*  982 */     fout.println("Average of the inter-item correlation coefficients, including item totals");
/*  983 */     fout.print("Raw data ", field);
/*  984 */     fout.println(Fmath.truncate(this.rawMeanRhoWithTotals, this.trunc));
/*  985 */     fout.print("Standardized data ", field);
/*  986 */     fout.println(Fmath.truncate(this.standardizedMeanRhoWithTotals, this.trunc));
/*  987 */     fout.println();
/*      */     
/*      */ 
/*  990 */     fout.println("'NO RESPONSE' DELETIONS AND REPLACEMENTS");
/*      */     
/*  992 */     field = 34;
/*  993 */     int fieldInt = 6;
/*  994 */     boolean deletionFlag = false;
/*  995 */     if (this.nDeletedPersons != 0) {
/*  996 */       deletionFlag = true;
/*  997 */       fout.print("Number of persons deleted ", field);
/*  998 */       fout.println(this.nDeletedPersons);
/*  999 */       fout.print("Indices of deleted persons: ", field);
/* 1000 */       for (int i = 0; i < this.nDeletedPersons; i++) fout.print(this.deletedPersonsIndices[i] + 1, fieldInt);
/* 1001 */       fout.println();
/*      */     }
/*      */     else {
/* 1004 */       fout.println("No persons were deleted ");
/*      */     }
/*      */     
/*      */ 
/* 1008 */     if (this.nDeletedItems != 0) {
/* 1009 */       deletionFlag = true;
/* 1010 */       fout.print("Number of items deleted ", field);
/* 1011 */       fout.println(this.nDeletedItems);
/* 1012 */       fout.print("Names of deleted items: ", field);
/* 1013 */       for (int i = 0; i < this.nDeletedItems; i++) fout.print(this.originalItemNames[this.deletedItemsIndices[i]], fieldInt);
/* 1014 */       fout.println();
/*      */     }
/*      */     else {
/* 1017 */       fout.println("No items were deleted ");
/*      */     }
/*      */     
/*      */ 
/* 1021 */     if (this.nReplacements != 0) {
/* 1022 */       fout.printtab("Number of 'no responses' replaced ");
/* 1023 */       fout.println(this.nReplacements);
/* 1024 */       fout.print("Item name and person index of replacements: ", 50);
/* 1025 */       for (int i = 0; i < this.nReplacements; i++) fout.print(this.replacementIndices[i], fieldInt);
/* 1026 */       fout.print("Replacement option: ", field);
/* 1027 */       fout.println(this.replacementOptionNames[(this.replacementOption - 1)]);
/* 1028 */       fout.println();
/*      */ 
/*      */     }
/* 1031 */     else if (deletionFlag) {
/* 1032 */       fout.println("No 'no response' replacements, other than any above deletions, were made ");
/*      */     }
/*      */     else {
/* 1035 */       fout.println("No 'no response' replacements were made ");
/*      */     }
/*      */     
/* 1038 */     fout.println();
/* 1039 */     fout.print("Number of items used", 35);
/* 1040 */     fout.println(this.nItems);
/* 1041 */     fout.print("Number of persons used", 35);
/* 1042 */     fout.println(this.nPersons);
/* 1043 */     fout.println();
/*      */     
/*      */ 
/* 1046 */     int len = this.trunc + 8;
/* 1047 */     int fieldItemName = 0;
/* 1048 */     for (int i = 0; i <= this.nItems; i++) if (this.itemNames[i].length() > fieldItemName) fieldItemName = this.itemNames[i].length();
/* 1049 */     int fieldItemNumber = fieldItemName;
/* 1050 */     if (len > fieldItemNumber) fieldItemNumber = len;
/* 1051 */     fieldItemName++;
/* 1052 */     fieldItemNumber++;
/*      */     
/* 1054 */     fout.println("CORRELATION COEFFICIENTS");
/* 1055 */     fout.println("Correlation coefficients between items  -  raw data");
/* 1056 */     fout.print("    ", fieldItemName);
/*      */     
/* 1058 */     for (int i = 0; i <= this.nItems; i++) fout.print(this.itemNames[i], fieldItemNumber);
/* 1059 */     fout.println();
/* 1060 */     for (int i = 0; i <= this.nItems; i++) {
/* 1061 */       fout.print(this.itemNames[i], fieldItemName);
/* 1062 */       for (int j = 0; j <= this.nItems; j++) fout.print(Fmath.truncate(this.rawCorrelationCoefficients[i][j], this.trunc), fieldItemNumber);
/* 1063 */       fout.println();
/*      */     }
/* 1065 */     fout.println();
/*      */     
/* 1067 */     fout.print("Average inter-item correlation coefficient (excluding total) ", 80);
/* 1068 */     fout.println(Fmath.truncate(this.rawMeanRhoWithoutTotals, this.trunc));
/* 1069 */     fout.print("Standard deviation of the inter-item correlation coefficient (excluding total) ", 80);
/* 1070 */     fout.println(Fmath.truncate(this.rawStandardDeviationRhoWithoutTotals, this.trunc));
/* 1071 */     fout.print("Average inter-item correlation coefficient (including total) ", 80);
/* 1072 */     fout.println(Fmath.truncate(this.rawMeanRhoWithTotals, this.trunc));
/* 1073 */     fout.print("Standard deviation of the inter-item correlation coefficient (including total) ", 80);
/* 1074 */     fout.println(Fmath.truncate(this.rawStandardDeviationRhoWithTotals, this.trunc));
/*      */     
/* 1076 */     fout.println();
/*      */     
/*      */ 
/* 1079 */     fout.println("Correlation coefficients between items  -  standardized data");
/* 1080 */     fout.print("    ", fieldItemName);
/* 1081 */     for (int i = 0; i <= this.nItems; i++) fout.print(this.itemNames[i], fieldItemNumber);
/* 1082 */     fout.println();
/* 1083 */     for (int i = 0; i <= this.nItems; i++) {
/* 1084 */       fout.print(this.itemNames[i], fieldItemName);
/* 1085 */       for (int j = 0; j <= this.nItems; j++) fout.print(Fmath.truncate(this.standardizedCorrelationCoefficients[i][j], this.trunc), fieldItemNumber);
/* 1086 */       fout.println();
/*      */     }
/* 1088 */     fout.println();
/*      */     
/* 1090 */     fout.print("Average inter-item correlation coefficient (excluding total) ", 80);
/* 1091 */     fout.println(Fmath.truncate(this.standardizedMeanRhoWithoutTotals, this.trunc));
/* 1092 */     fout.print("Standard deviation of the inter-item correlation coefficient (excluding total) ", 80);
/* 1093 */     fout.println(Fmath.truncate(this.standardizedStandardDeviationRhoWithoutTotals, this.trunc));
/* 1094 */     fout.print("Average inter-item correlation coefficient (including total) ", 80);
/* 1095 */     fout.println(Fmath.truncate(this.standardizedMeanRhoWithTotals, this.trunc));
/* 1096 */     fout.print("Standard deviation of the inter-item correlation coefficient (including total) ", 80);
/* 1097 */     fout.println(Fmath.truncate(this.standardizedStandardDeviationRhoWithTotals, this.trunc));
/* 1098 */     fout.println();
/*      */     
/*      */ 
/* 1101 */     if (fieldItemNumber < 12) { fieldItemNumber = 12;
/*      */     }
/* 1103 */     fout.println("ITEMS: MEANS, STANDARD DEVIATIONS, SKENESS AND KURTOSIS");
/* 1104 */     fout.println("Raw data");
/* 1105 */     fout.print("item ", fieldItemName);
/* 1106 */     fout.print("mean", fieldItemNumber);
/* 1107 */     fout.print("standard", fieldItemNumber);
/* 1108 */     fout.print("moment", fieldItemNumber);
/* 1109 */     fout.print("median", fieldItemNumber);
/* 1110 */     fout.print("quartile", fieldItemNumber);
/* 1111 */     fout.print("kurtosis", fieldItemNumber);
/* 1112 */     fout.println("dichotomous");
/*      */     
/* 1114 */     fout.print("    ", fieldItemName);
/* 1115 */     fout.print("    ", fieldItemNumber);
/* 1116 */     fout.print("deviation", fieldItemNumber);
/* 1117 */     fout.print("skewness", fieldItemNumber);
/* 1118 */     fout.print("skewness", fieldItemNumber);
/* 1119 */     fout.print("skewness", fieldItemNumber);
/* 1120 */     fout.print("excess  ", fieldItemNumber);
/* 1121 */     fout.println("percentage");
/*      */     
/* 1123 */     for (int i = 0; i < this.nItems; i++) {
/* 1124 */       fout.print(this.itemNames[i], fieldItemName);
/* 1125 */       fout.print(Fmath.truncate(this.rawItemMeans[i], this.trunc), fieldItemNumber);
/* 1126 */       fout.print(Fmath.truncate(this.rawItemStandardDeviations[i], this.trunc), fieldItemNumber);
/* 1127 */       fout.print(Fmath.truncate(this.rawItemMomentSkewness[i], this.trunc), fieldItemNumber);
/* 1128 */       fout.print(Fmath.truncate(this.rawItemMedianSkewness[i], this.trunc), fieldItemNumber);
/* 1129 */       fout.print(Fmath.truncate(this.rawItemQuartileSkewness[i], this.trunc), fieldItemNumber);
/* 1130 */       fout.print(Fmath.truncate(this.rawItemKurtosisExcess[i], this.trunc), fieldItemNumber);
/* 1131 */       fout.println(Fmath.truncate(this.dichotomousPercentage[i], 1));
/*      */     }
/* 1133 */     fout.println();
/*      */     
/* 1135 */     fout.println("ITEMS: MINIMA, MAXIMA, MEDIANS, RANGES AND TOTALS");
/* 1136 */     fout.println("Raw data");
/* 1137 */     fout.print("item ", fieldItemName);
/* 1138 */     fout.print("minimum", fieldItemNumber);
/* 1139 */     fout.print("maximum", fieldItemNumber);
/* 1140 */     fout.print("median", fieldItemNumber);
/* 1141 */     fout.print("range", fieldItemNumber);
/* 1142 */     fout.print("total", fieldItemNumber);
/* 1143 */     fout.println("dichotomous");
/*      */     
/* 1145 */     fout.print("    ", fieldItemName);
/* 1146 */     fout.print("    ", fieldItemNumber);
/* 1147 */     fout.print("     ", fieldItemNumber);
/* 1148 */     fout.print("     ", fieldItemNumber);
/* 1149 */     fout.print("     ", fieldItemNumber);
/* 1150 */     fout.print("     ", fieldItemNumber);
/* 1151 */     fout.println("percentage");
/*      */     
/* 1153 */     for (int i = 0; i < this.nItems; i++) {
/* 1154 */       fout.print(this.itemNames[i], fieldItemName);
/* 1155 */       fout.print(Fmath.truncate(this.rawItemMinima[i], this.trunc), fieldItemNumber);
/* 1156 */       fout.print(Fmath.truncate(this.rawItemMaxima[i], this.trunc), fieldItemNumber);
/* 1157 */       fout.print(Fmath.truncate(this.rawItemMedians[i], this.trunc), fieldItemNumber);
/* 1158 */       fout.print(Fmath.truncate(this.rawItemRanges[i], this.trunc), fieldItemNumber);
/* 1159 */       fout.print(Fmath.truncate(this.rawItemTotals[i], this.trunc), fieldItemNumber);
/* 1160 */       fout.println(Fmath.truncate(this.dichotomousPercentage[i], 1));
/*      */     }
/* 1162 */     fout.println();
/*      */     
/* 1164 */     int fieldItemSName = 25;
/* 1165 */     fout.print("item", fieldItemSName);
/* 1166 */     fout.print("mean", fieldItemNumber);
/* 1167 */     fout.print("standard", fieldItemNumber);
/* 1168 */     fout.print("variance", fieldItemNumber);
/* 1169 */     fout.print("minimum", fieldItemNumber);
/* 1170 */     fout.print("maximum", fieldItemNumber);
/* 1171 */     fout.println("range");
/* 1172 */     fout.print("statistic    ", fieldItemSName);
/* 1173 */     fout.print("    ", fieldItemNumber);
/* 1174 */     fout.print("deviation", fieldItemNumber);
/* 1175 */     fout.print("     ", fieldItemNumber);
/* 1176 */     fout.print("     ", fieldItemNumber);
/* 1177 */     fout.print("     ", fieldItemNumber);
/* 1178 */     fout.print("     ", fieldItemNumber);
/* 1179 */     fout.println("     ");
/*      */     
/* 1181 */     fout.print("item means", fieldItemSName);
/* 1182 */     fout.print(Fmath.truncate(this.rawItemMeansMean, this.trunc), fieldItemNumber);
/* 1183 */     fout.print(Fmath.truncate(this.rawItemMeansSd, this.trunc), fieldItemNumber);
/* 1184 */     fout.print(Fmath.truncate(this.rawItemMeansVar, this.trunc), fieldItemNumber);
/* 1185 */     fout.print(Fmath.truncate(this.rawItemMeansMin, this.trunc), fieldItemNumber);
/* 1186 */     fout.print(Fmath.truncate(this.rawItemMeansMax, this.trunc), fieldItemNumber);
/* 1187 */     fout.println(Fmath.truncate(this.rawItemMeansRange, this.trunc));
/*      */     
/* 1189 */     fout.print("item standard deviations", fieldItemSName);
/* 1190 */     fout.print(Fmath.truncate(this.rawItemStandardDeviationsMean, this.trunc), fieldItemNumber);
/* 1191 */     fout.print(Fmath.truncate(this.rawItemStandardDeviationsSd, this.trunc), fieldItemNumber);
/* 1192 */     fout.print(Fmath.truncate(this.rawItemStandardDeviationsVar, this.trunc), fieldItemNumber);
/* 1193 */     fout.print(Fmath.truncate(this.rawItemStandardDeviationsMin, this.trunc), fieldItemNumber);
/* 1194 */     fout.print(Fmath.truncate(this.rawItemStandardDeviationsMax, this.trunc), fieldItemNumber);
/* 1195 */     fout.println(Fmath.truncate(this.rawItemStandardDeviationsRange, this.trunc));
/*      */     
/* 1197 */     fout.print("item variances", fieldItemSName);
/* 1198 */     fout.print(Fmath.truncate(this.rawItemVariancesMean, this.trunc), fieldItemNumber);
/* 1199 */     fout.print(Fmath.truncate(this.rawItemVariancesSd, this.trunc), fieldItemNumber);
/* 1200 */     fout.print(Fmath.truncate(this.rawItemVariancesVar, this.trunc), fieldItemNumber);
/* 1201 */     fout.print(Fmath.truncate(this.rawItemVariancesMin, this.trunc), fieldItemNumber);
/* 1202 */     fout.print(Fmath.truncate(this.rawItemVariancesMax, this.trunc), fieldItemNumber);
/* 1203 */     fout.println(Fmath.truncate(this.rawItemVariancesRange, this.trunc));
/*      */     
/* 1205 */     fout.print("item mimima", fieldItemSName);
/* 1206 */     fout.print(Fmath.truncate(this.rawItemMinimaMean, this.trunc), fieldItemNumber);
/* 1207 */     fout.print(Fmath.truncate(this.rawItemMinimaSd, this.trunc), fieldItemNumber);
/* 1208 */     fout.print(Fmath.truncate(this.rawItemMinimaVar, this.trunc), fieldItemNumber);
/* 1209 */     fout.print(Fmath.truncate(this.rawItemMinimaMin, this.trunc), fieldItemNumber);
/* 1210 */     fout.print(Fmath.truncate(this.rawItemMinimaMax, this.trunc), fieldItemNumber);
/* 1211 */     fout.println(Fmath.truncate(this.rawItemMinimaRange, this.trunc));
/*      */     
/* 1213 */     fout.print("item maxima", fieldItemSName);
/* 1214 */     fout.print(Fmath.truncate(this.rawItemMaximaMean, this.trunc), fieldItemNumber);
/* 1215 */     fout.print(Fmath.truncate(this.rawItemMaximaSd, this.trunc), fieldItemNumber);
/* 1216 */     fout.print(Fmath.truncate(this.rawItemMaximaVar, this.trunc), fieldItemNumber);
/* 1217 */     fout.print(Fmath.truncate(this.rawItemMaximaMin, this.trunc), fieldItemNumber);
/* 1218 */     fout.print(Fmath.truncate(this.rawItemMaximaMax, this.trunc), fieldItemNumber);
/* 1219 */     fout.println(Fmath.truncate(this.rawItemMaximaRange, this.trunc));
/*      */     
/* 1221 */     fout.print("item medians", fieldItemSName);
/* 1222 */     fout.print(Fmath.truncate(this.rawItemMediansMean, this.trunc), fieldItemNumber);
/* 1223 */     fout.print(Fmath.truncate(this.rawItemMediansSd, this.trunc), fieldItemNumber);
/* 1224 */     fout.print(Fmath.truncate(this.rawItemMediansVar, this.trunc), fieldItemNumber);
/* 1225 */     fout.print(Fmath.truncate(this.rawItemMediansMin, this.trunc), fieldItemNumber);
/* 1226 */     fout.print(Fmath.truncate(this.rawItemMediansMax, this.trunc), fieldItemNumber);
/* 1227 */     fout.println(Fmath.truncate(this.rawItemMediansRange, this.trunc));
/*      */     
/* 1229 */     fout.print("item ranges", fieldItemSName);
/* 1230 */     fout.print(Fmath.truncate(this.rawItemRangesMean, this.trunc), fieldItemNumber);
/* 1231 */     fout.print(Fmath.truncate(this.rawItemRangesSd, this.trunc), fieldItemNumber);
/* 1232 */     fout.print(Fmath.truncate(this.rawItemRangesVar, this.trunc), fieldItemNumber);
/* 1233 */     fout.print(Fmath.truncate(this.rawItemRangesMin, this.trunc), fieldItemNumber);
/* 1234 */     fout.print(Fmath.truncate(this.rawItemRangesMax, this.trunc), fieldItemNumber);
/* 1235 */     fout.println(Fmath.truncate(this.rawItemRangesRange, this.trunc));
/*      */     
/* 1237 */     fout.print("item totals", fieldItemSName);
/* 1238 */     fout.print(Fmath.truncate(this.rawItemTotalsMean, this.trunc), fieldItemNumber);
/* 1239 */     fout.print(Fmath.truncate(this.rawItemTotalsSd, this.trunc), fieldItemNumber);
/* 1240 */     fout.print(Fmath.truncate(this.rawItemTotalsVar, this.trunc), fieldItemNumber);
/* 1241 */     fout.print(Fmath.truncate(this.rawItemTotalsMin, this.trunc), fieldItemNumber);
/* 1242 */     fout.print(Fmath.truncate(this.rawItemTotalsMax, this.trunc), fieldItemNumber);
/* 1243 */     fout.println(Fmath.truncate(this.rawItemTotalsRange, this.trunc));
/*      */     
/* 1245 */     fout.println();
/*      */     
/* 1247 */     fout.println("standardized data");
/* 1248 */     fout.print("item ", fieldItemName);
/* 1249 */     fout.print("mean", fieldItemNumber);
/* 1250 */     fout.print("standard", fieldItemNumber);
/* 1251 */     fout.print("moment", fieldItemNumber);
/* 1252 */     fout.print("median", fieldItemNumber);
/* 1253 */     fout.print("quartile", fieldItemNumber);
/* 1254 */     fout.println("kurtosis");
/*      */     
/* 1256 */     fout.print("    ", fieldItemName);
/* 1257 */     fout.print("    ", fieldItemNumber);
/* 1258 */     fout.print("deviation", fieldItemNumber);
/* 1259 */     fout.print("skewness", fieldItemNumber);
/* 1260 */     fout.print("skewness", fieldItemNumber);
/* 1261 */     fout.print("skewness", fieldItemNumber);
/* 1262 */     fout.println("excess  ");
/*      */     
/* 1264 */     for (int i = 0; i < this.nItems; i++) {
/* 1265 */       fout.print(this.itemNames[i], fieldItemName);
/* 1266 */       fout.print(Fmath.truncate(this.standardizedItemMeans[i], this.trunc), fieldItemNumber);
/* 1267 */       fout.print(Fmath.truncate(this.standardizedItemStandardDeviations[i], this.trunc), fieldItemNumber);
/* 1268 */       fout.print(Fmath.truncate(this.standardizedItemMomentSkewness[i], this.trunc), fieldItemNumber);
/* 1269 */       fout.print(Fmath.truncate(this.standardizedItemMedianSkewness[i], this.trunc), fieldItemNumber);
/* 1270 */       fout.print(Fmath.truncate(this.standardizedItemQuartileSkewness[i], this.trunc), fieldItemNumber);
/* 1271 */       fout.println(Fmath.truncate(this.standardizedItemKurtosisExcess[i], this.trunc));
/*      */     }
/* 1273 */     fout.println();
/*      */     
/* 1275 */     fout.print("item ", fieldItemName);
/* 1276 */     fout.print("minimum", fieldItemNumber);
/* 1277 */     fout.print("maximum", fieldItemNumber);
/* 1278 */     fout.print("median", fieldItemNumber);
/* 1279 */     fout.print("range", fieldItemNumber);
/* 1280 */     fout.println("total");
/*      */     
/* 1282 */     fout.print("    ", fieldItemName);
/* 1283 */     fout.print("    ", fieldItemNumber);
/* 1284 */     fout.print("     ", fieldItemNumber);
/* 1285 */     fout.print("     ", fieldItemNumber);
/* 1286 */     fout.print("     ", fieldItemNumber);
/* 1287 */     fout.println("     ");
/*      */     
/* 1289 */     for (int i = 0; i < this.nItems; i++) {
/* 1290 */       fout.print(this.itemNames[i], fieldItemName);
/* 1291 */       fout.print(Fmath.truncate(this.standardizedItemMinima[i], this.trunc), fieldItemNumber);
/* 1292 */       fout.print(Fmath.truncate(this.standardizedItemMaxima[i], this.trunc), fieldItemNumber);
/* 1293 */       fout.print(Fmath.truncate(this.standardizedItemMedians[i], this.trunc), fieldItemNumber);
/* 1294 */       fout.print(Fmath.truncate(this.standardizedItemRanges[i], this.trunc), fieldItemNumber);
/* 1295 */       fout.println(Fmath.truncate(this.standardizedItemTotals[i], this.trunc));
/*      */     }
/* 1297 */     fout.println();
/*      */     
/*      */ 
/* 1300 */     fout.print("item", fieldItemSName);
/* 1301 */     fout.print("mean", fieldItemNumber);
/* 1302 */     fout.print("standard", fieldItemNumber);
/* 1303 */     fout.print("variance", fieldItemNumber);
/* 1304 */     fout.print("minimum", fieldItemNumber);
/* 1305 */     fout.print("maximum", fieldItemNumber);
/* 1306 */     fout.println("range");
/* 1307 */     fout.print("statistic    ", fieldItemSName);
/* 1308 */     fout.print("    ", fieldItemNumber);
/* 1309 */     fout.print("deviation", fieldItemNumber);
/* 1310 */     fout.print("     ", fieldItemNumber);
/* 1311 */     fout.print("     ", fieldItemNumber);
/* 1312 */     fout.print("     ", fieldItemNumber);
/* 1313 */     fout.print("     ", fieldItemNumber);
/* 1314 */     fout.println("     ");
/*      */     
/* 1316 */     fout.print("item means", fieldItemSName);
/* 1317 */     fout.print(Fmath.truncate(this.standardizedItemMeansMean, this.trunc), fieldItemNumber);
/* 1318 */     fout.print(Fmath.truncate(this.standardizedItemMeansSd, this.trunc), fieldItemNumber);
/* 1319 */     fout.print(Fmath.truncate(this.standardizedItemMeansVar, this.trunc), fieldItemNumber);
/* 1320 */     fout.print(Fmath.truncate(this.standardizedItemMeansMin, this.trunc), fieldItemNumber);
/* 1321 */     fout.print(Fmath.truncate(this.standardizedItemMeansMax, this.trunc), fieldItemNumber);
/* 1322 */     fout.println(Fmath.truncate(this.standardizedItemMeansRange, this.trunc));
/*      */     
/* 1324 */     fout.print("item standard deviations", fieldItemSName);
/* 1325 */     fout.print(Fmath.truncate(this.standardizedItemStandardDeviationsMean, this.trunc), fieldItemNumber);
/* 1326 */     fout.print(Fmath.truncate(this.standardizedItemStandardDeviationsSd, this.trunc), fieldItemNumber);
/* 1327 */     fout.print(Fmath.truncate(this.standardizedItemStandardDeviationsVar, this.trunc), fieldItemNumber);
/* 1328 */     fout.print(Fmath.truncate(this.standardizedItemStandardDeviationsMin, this.trunc), fieldItemNumber);
/* 1329 */     fout.print(Fmath.truncate(this.standardizedItemStandardDeviationsMax, this.trunc), fieldItemNumber);
/* 1330 */     fout.println(Fmath.truncate(this.standardizedItemStandardDeviationsRange, this.trunc));
/*      */     
/* 1332 */     fout.print("item variances", fieldItemSName);
/* 1333 */     fout.print(Fmath.truncate(this.standardizedItemVariancesMean, this.trunc), fieldItemNumber);
/* 1334 */     fout.print(Fmath.truncate(this.standardizedItemVariancesSd, this.trunc), fieldItemNumber);
/* 1335 */     fout.print(Fmath.truncate(this.standardizedItemVariancesVar, this.trunc), fieldItemNumber);
/* 1336 */     fout.print(Fmath.truncate(this.standardizedItemVariancesMin, this.trunc), fieldItemNumber);
/* 1337 */     fout.print(Fmath.truncate(this.standardizedItemVariancesMax, this.trunc), fieldItemNumber);
/* 1338 */     fout.println(Fmath.truncate(this.standardizedItemVariancesRange, this.trunc));
/*      */     
/* 1340 */     fout.print("item mimima", fieldItemSName);
/* 1341 */     fout.print(Fmath.truncate(this.standardizedItemMinimaMean, this.trunc), fieldItemNumber);
/* 1342 */     fout.print(Fmath.truncate(this.standardizedItemMinimaSd, this.trunc), fieldItemNumber);
/* 1343 */     fout.print(Fmath.truncate(this.standardizedItemMinimaVar, this.trunc), fieldItemNumber);
/* 1344 */     fout.print(Fmath.truncate(this.standardizedItemMinimaMin, this.trunc), fieldItemNumber);
/* 1345 */     fout.print(Fmath.truncate(this.standardizedItemMinimaMax, this.trunc), fieldItemNumber);
/* 1346 */     fout.println(Fmath.truncate(this.standardizedItemMinimaRange, this.trunc));
/*      */     
/* 1348 */     fout.print("item maxima", fieldItemSName);
/* 1349 */     fout.print(Fmath.truncate(this.standardizedItemMaximaMean, this.trunc), fieldItemNumber);
/* 1350 */     fout.print(Fmath.truncate(this.standardizedItemMaximaSd, this.trunc), fieldItemNumber);
/* 1351 */     fout.print(Fmath.truncate(this.standardizedItemMaximaVar, this.trunc), fieldItemNumber);
/* 1352 */     fout.print(Fmath.truncate(this.standardizedItemMaximaMin, this.trunc), fieldItemNumber);
/* 1353 */     fout.print(Fmath.truncate(this.standardizedItemMaximaMax, this.trunc), fieldItemNumber);
/* 1354 */     fout.println(Fmath.truncate(this.standardizedItemMaximaRange, this.trunc));
/*      */     
/* 1356 */     fout.print("item medians", fieldItemSName);
/* 1357 */     fout.print(Fmath.truncate(this.standardizedItemMediansMean, this.trunc), fieldItemNumber);
/* 1358 */     fout.print(Fmath.truncate(this.standardizedItemMediansSd, this.trunc), fieldItemNumber);
/* 1359 */     fout.print(Fmath.truncate(this.standardizedItemMediansVar, this.trunc), fieldItemNumber);
/* 1360 */     fout.print(Fmath.truncate(this.standardizedItemMediansMin, this.trunc), fieldItemNumber);
/* 1361 */     fout.print(Fmath.truncate(this.standardizedItemMediansMax, this.trunc), fieldItemNumber);
/* 1362 */     fout.println(Fmath.truncate(this.standardizedItemMediansRange, this.trunc));
/*      */     
/* 1364 */     fout.print("item ranges", fieldItemSName);
/* 1365 */     fout.print(Fmath.truncate(this.standardizedItemRangesMean, this.trunc), fieldItemNumber);
/* 1366 */     fout.print(Fmath.truncate(this.standardizedItemRangesSd, this.trunc), fieldItemNumber);
/* 1367 */     fout.print(Fmath.truncate(this.standardizedItemRangesVar, this.trunc), fieldItemNumber);
/* 1368 */     fout.print(Fmath.truncate(this.standardizedItemRangesMin, this.trunc), fieldItemNumber);
/* 1369 */     fout.print(Fmath.truncate(this.standardizedItemRangesMax, this.trunc), fieldItemNumber);
/* 1370 */     fout.println(Fmath.truncate(this.standardizedItemRangesRange, this.trunc));
/*      */     
/* 1372 */     fout.print("item totals", fieldItemSName);
/* 1373 */     fout.print(Fmath.truncate(this.standardizedItemTotalsMean, this.trunc), fieldItemNumber);
/* 1374 */     fout.print(Fmath.truncate(this.standardizedItemTotalsSd, this.trunc), fieldItemNumber);
/* 1375 */     fout.print(Fmath.truncate(this.standardizedItemTotalsVar, this.trunc), fieldItemNumber);
/* 1376 */     fout.print(Fmath.truncate(this.standardizedItemTotalsMin, this.trunc), fieldItemNumber);
/* 1377 */     fout.print(Fmath.truncate(this.standardizedItemTotalsMax, this.trunc), fieldItemNumber);
/* 1378 */     fout.println(Fmath.truncate(this.standardizedItemTotalsRange, this.trunc));
/*      */     
/* 1380 */     fout.println();
/*      */     
/*      */ 
/* 1383 */     fout.println("DELETION OF ITEMS");
/* 1384 */     int fieldDitem = 16;
/* 1385 */     if (fieldItemName > fieldDitem) { fieldDitem = fieldItemName;
/*      */     }
/* 1387 */     fout.print("   ", fieldDitem);
/* 1388 */     fout.print("Raw data", fieldItemNumber);
/* 1389 */     fout.print("   ", fieldItemNumber);
/* 1390 */     fout.print("   ", fieldItemNumber);
/* 1391 */     fout.print("   ", fieldItemNumber);
/* 1392 */     fout.println("Standardized data");
/*      */     
/* 1394 */     fout.print("Deleted item", fieldDitem);
/* 1395 */     fout.print("Alpha", fieldItemNumber);
/* 1396 */     fout.print("Correlation", fieldItemNumber);
/* 1397 */     fout.print("Average", fieldItemNumber);
/* 1398 */     fout.print("Average", fieldItemNumber);
/*      */     
/* 1400 */     fout.print("Alpha", fieldItemNumber);
/* 1401 */     fout.print("Correlation", fieldItemNumber);
/* 1402 */     fout.print("Average", fieldItemNumber);
/* 1403 */     fout.println("Average");
/*      */     
/*      */ 
/* 1406 */     fout.print("       ", fieldDitem);
/* 1407 */     fout.print("       ", fieldItemNumber);
/* 1408 */     fout.print("coefficient", fieldItemNumber);
/* 1409 */     fout.print("inter-item", fieldItemNumber);
/* 1410 */     fout.print("inter-item", fieldItemNumber);
/*      */     
/* 1412 */     fout.print("      ", fieldItemNumber);
/* 1413 */     fout.print("coefficient", fieldItemNumber);
/* 1414 */     fout.print("inter-item", fieldItemNumber);
/* 1415 */     fout.println("inter-item");
/*      */     
/*      */ 
/* 1418 */     fout.print("       ", fieldDitem);
/* 1419 */     fout.print("       ", fieldItemNumber);
/* 1420 */     fout.print("with total", fieldItemNumber);
/* 1421 */     fout.print("correlation", fieldItemNumber);
/* 1422 */     fout.print("correlation", fieldItemNumber);
/*      */     
/* 1424 */     fout.print("      ", fieldItemNumber);
/* 1425 */     fout.print("with total", fieldItemNumber);
/* 1426 */     fout.print("correlation", fieldItemNumber);
/* 1427 */     fout.println("correlation");
/*      */     
/*      */ 
/* 1430 */     fout.print("       ", fieldDitem);
/* 1431 */     fout.print("       ", fieldItemNumber);
/* 1432 */     fout.print("       ", fieldItemNumber);
/* 1433 */     fout.print("coefficient", fieldItemNumber);
/* 1434 */     fout.print("coefficient", fieldItemNumber);
/*      */     
/* 1436 */     fout.print("        ", fieldItemNumber);
/* 1437 */     fout.print("        ", fieldItemNumber);
/* 1438 */     fout.print("coefficient", fieldItemNumber);
/* 1439 */     fout.println("coefficient");
/*      */     
/*      */ 
/* 1442 */     fout.print("       ", fieldDitem);
/* 1443 */     fout.print("       ", fieldItemNumber);
/* 1444 */     fout.print("       ", fieldItemNumber);
/* 1445 */     fout.print("without totals", fieldItemNumber);
/* 1446 */     fout.print("with totals", fieldItemNumber);
/*      */     
/* 1448 */     fout.print("        ", fieldItemNumber);
/* 1449 */     fout.print("        ", fieldItemNumber);
/* 1450 */     fout.print("without totals", fieldItemNumber);
/* 1451 */     fout.println("with totals");
/*      */     
/* 1453 */     double[] newRawAlpha = new double[this.nItems];
/* 1454 */     double[] newStandardizedAlpha = new double[this.nItems];
/* 1455 */     double[] newRawRho = new double[this.nItems];
/* 1456 */     double[] newStandardizedRho = new double[this.nItems];
/* 1457 */     for (int i = 0; i < this.nItems; i++) {
/* 1458 */       int index = i + 1;
/* 1459 */       double[][] newScore1 = deleteItem(index);
/* 1460 */       Cronbach cr = new Cronbach();
/* 1461 */       cr.enterScoresAsRowPerPerson(newScore1);
/* 1462 */       double rawAlphaD = cr.rawAlpha();
/* 1463 */       newRawAlpha[i] = rawAlphaD;
/* 1464 */       double rawMeanRhoWithTotalsD = cr.rawAverageCorrelationCoefficientsWithTotals();
/* 1465 */       double[] rawPersonTotalsD = cr.rawPersonTotals();
/* 1466 */       double rawRhoAgainstTotalsD = Stat.corrCoeff(rawPersonTotalsD, this.scores0[i]);
/* 1467 */       double rawMeanRhoWithoutTotalsD = cr.rawAverageCorrelationCoefficients();
/* 1468 */       newRawRho[i] = rawRhoAgainstTotalsD;
/*      */       
/* 1470 */       double standardizedAlphaD = cr.standardizedAlpha();
/* 1471 */       newStandardizedAlpha[i] = standardizedAlphaD;
/* 1472 */       double standardizedMeanRhoWithTotalsD = cr.standardizedAverageCorrelationCoefficients();
/* 1473 */       double[] standardizedPersonTotalsD = cr.standardizedPersonTotals();
/* 1474 */       double standardizedRhoAgainstTotalsD = Stat.corrCoeff(standardizedPersonTotalsD, this.scores0[i]);
/* 1475 */       double standardizedMeanRhoWithoutTotalsD = cr.standardizedAverageCorrelationCoefficients();
/* 1476 */       newStandardizedRho[i] = standardizedRhoAgainstTotalsD;
/*      */       
/* 1478 */       fout.print(this.itemNames[i], fieldDitem);
/* 1479 */       fout.print(Fmath.truncate(rawAlphaD, this.trunc), fieldItemNumber);
/* 1480 */       fout.print(Fmath.truncate(rawRhoAgainstTotalsD, this.trunc), fieldItemNumber);
/* 1481 */       fout.print(Fmath.truncate(rawMeanRhoWithoutTotalsD, this.trunc), fieldItemNumber);
/* 1482 */       fout.print(Fmath.truncate(rawMeanRhoWithTotalsD, this.trunc), fieldItemNumber);
/*      */       
/* 1484 */       fout.print(Fmath.truncate(standardizedAlphaD, this.trunc), fieldItemNumber);
/* 1485 */       fout.print(Fmath.truncate(standardizedRhoAgainstTotalsD, this.trunc), fieldItemNumber);
/* 1486 */       fout.print(Fmath.truncate(standardizedMeanRhoWithoutTotalsD, this.trunc), fieldItemNumber);
/* 1487 */       fout.print(Fmath.truncate(standardizedMeanRhoWithTotalsD, this.trunc), fieldItemNumber);
/* 1488 */       fout.println();
/*      */     }
/* 1490 */     fout.println();
/*      */     
/* 1492 */     fout.print("No item deleted", fieldDitem);
/* 1493 */     fout.print(Fmath.truncate(this.rawAlpha, this.trunc), fieldItemNumber);
/* 1494 */     fout.print("   ", fieldItemNumber);
/* 1495 */     fout.print(Fmath.truncate(this.rawMeanRhoWithoutTotals, this.trunc), fieldItemNumber);
/* 1496 */     fout.print(Fmath.truncate(this.rawMeanRhoWithTotals, this.trunc), fieldItemNumber);
/*      */     
/* 1498 */     fout.print(Fmath.truncate(this.standardizedAlpha, this.trunc), fieldItemNumber);
/* 1499 */     fout.print("   ", fieldItemNumber);
/* 1500 */     fout.print(Fmath.truncate(this.standardizedMeanRhoWithoutTotals, this.trunc), fieldItemNumber);
/* 1501 */     fout.println(Fmath.truncate(this.standardizedMeanRhoWithTotals, this.trunc));
/* 1502 */     fout.println();
/*      */     
/*      */ 
/* 1505 */     deletedItemDataFile(newRawAlpha, newRawRho, newStandardizedAlpha, newStandardizedRho);
/*      */     
/* 1507 */     int fieldInd = 12;
/* 1508 */     fout.println("INDIVIDUALS - raw data");
/* 1509 */     fout.print("person", fieldInd);
/* 1510 */     fout.print("mean", fieldItemNumber);
/* 1511 */     fout.print("standard", fieldItemNumber);
/* 1512 */     fout.print("minimum", fieldItemNumber);
/* 1513 */     fout.print("maximum", fieldItemNumber);
/* 1514 */     fout.print("range", fieldItemNumber);
/* 1515 */     fout.println("total");
/*      */     
/* 1517 */     fout.print("    ", fieldInd);
/* 1518 */     fout.print("    ", fieldItemNumber);
/* 1519 */     fout.print("deviation", fieldItemNumber);
/* 1520 */     fout.print("     ", fieldItemNumber);
/* 1521 */     fout.print("     ", fieldItemNumber);
/* 1522 */     fout.print("     ", fieldItemNumber);
/* 1523 */     fout.println("     ");
/*      */     
/* 1525 */     int fieldScore = 0;
/* 1526 */     for (int i = 0; i < this.nItems; i++) {
/* 1527 */       for (int j = 0; j < this.nPersons; j++) {
/* 1528 */         int sl = Double.toString(this.scores0[i][j]).length();
/* 1529 */         if (sl > fieldScore) fieldScore = sl;
/*      */       }
/*      */     }
/* 1532 */     fieldScore++;
/* 1533 */     if (fieldScore < fieldItemName) fieldScore = fieldItemName;
/* 1534 */     for (int i = 0; i < this.nPersons; i++) {
/* 1535 */       fout.print(this.personIndices[i] + 1, fieldInd);
/* 1536 */       fout.print(Fmath.truncate(this.rawPersonMeans[i], this.trunc), fieldItemNumber);
/* 1537 */       fout.print(Fmath.truncate(this.rawPersonStandardDeviations[i], this.trunc), fieldItemNumber);
/* 1538 */       fout.print(Fmath.truncate(this.rawPersonMinima[i], this.trunc), fieldItemNumber);
/* 1539 */       fout.print(Fmath.truncate(this.rawPersonMaxima[i], this.trunc), fieldItemNumber);
/* 1540 */       fout.print(Fmath.truncate(this.rawPersonRanges[i], this.trunc), fieldItemNumber);
/* 1541 */       fout.println(Fmath.truncate(this.rawPersonTotals[i], this.trunc));
/*      */     }
/* 1543 */     fout.println();
/*      */     
/* 1545 */     fout.println("scores:");
/* 1546 */     fout.print("person ", fieldInd);
/* 1547 */     for (int i = 0; i < this.nItems; i++) fout.print(this.itemNames[i], fieldScore);
/* 1548 */     fout.println();
/*      */     
/* 1550 */     for (int i = 0; i < this.nPersons; i++) {
/* 1551 */       fout.print(this.personIndices[i] + 1, fieldInd);
/* 1552 */       for (int j = 0; j < this.nItems; j++) fout.print(this.scores1[i][j], fieldScore);
/* 1553 */       fout.println();
/*      */     }
/* 1555 */     fout.println();
/*      */     
/* 1557 */     fout.println("INDIVIDUALS - standardized data");
/* 1558 */     fout.print("person ", fieldInd);
/* 1559 */     fout.print("mean", fieldItemNumber);
/* 1560 */     fout.print("standard", fieldItemNumber);
/* 1561 */     fout.print("minimum", fieldItemNumber);
/* 1562 */     fout.print("maximum", fieldItemNumber);
/* 1563 */     fout.print("range", fieldItemNumber);
/* 1564 */     fout.println("total");
/*      */     
/*      */ 
/* 1567 */     fout.print("    ", fieldInd);
/* 1568 */     fout.print("    ", fieldItemNumber);
/* 1569 */     fout.print("deviation", fieldItemNumber);
/* 1570 */     fout.print("     ", fieldItemNumber);
/* 1571 */     fout.print("     ", fieldItemNumber);
/* 1572 */     fout.print("     ", fieldItemNumber);
/* 1573 */     fout.println("     ");
/*      */     
/* 1575 */     for (int i = 0; i < this.nPersons; i++) {
/* 1576 */       fout.print(this.personIndices[i] + 1, fieldInd);
/* 1577 */       fout.print(Fmath.truncate(this.standardizedPersonMeans[i], this.trunc), fieldItemNumber);
/* 1578 */       fout.print(Fmath.truncate(this.standardizedPersonStandardDeviations[i], this.trunc), fieldItemNumber);
/* 1579 */       fout.print(Fmath.truncate(this.standardizedPersonMinima[i], this.trunc), fieldItemNumber);
/* 1580 */       fout.print(Fmath.truncate(this.standardizedPersonMaxima[i], this.trunc), fieldItemNumber);
/* 1581 */       fout.print(Fmath.truncate(this.standardizedPersonRanges[i], this.trunc), fieldItemNumber);
/* 1582 */       fout.println(Fmath.truncate(this.standardizedPersonTotals[i], this.trunc));
/*      */     }
/* 1584 */     fout.println();
/*      */     
/* 1586 */     fout.println("scores:");
/* 1587 */     fout.print("person ", fieldInd);
/* 1588 */     for (int i = 0; i < this.nItems; i++) fout.print(this.itemNames[i], fieldItemNumber);
/* 1589 */     fout.println();
/*      */     
/* 1591 */     for (int i = 0; i < this.nPersons; i++) {
/* 1592 */       fout.print(this.personIndices[i] + 1, fieldInd);
/* 1593 */       for (int j = 0; j < this.nItems; j++) fout.print(Fmath.truncate(this.standardizedScores1[i][j], this.trunc), fieldItemNumber);
/* 1594 */       fout.println();
/*      */     }
/* 1596 */     fout.println();
/*      */     
/* 1598 */     fout.println("ALL SCORES - raw data");
/* 1599 */     fout.print("mean", fieldItemNumber);
/* 1600 */     fout.print("standard", fieldItemNumber);
/* 1601 */     fout.print("minimum", fieldItemNumber);
/* 1602 */     fout.print("maximum", fieldItemNumber);
/* 1603 */     fout.print("range", fieldItemNumber);
/* 1604 */     fout.println("overall");
/*      */     
/* 1606 */     fout.print("    ", fieldItemNumber);
/* 1607 */     fout.print("deviation", fieldItemNumber);
/* 1608 */     fout.print("     ", fieldItemNumber);
/* 1609 */     fout.print("     ", fieldItemNumber);
/* 1610 */     fout.print("     ", fieldItemNumber);
/* 1611 */     fout.println("total");
/*      */     
/* 1613 */     fout.print(Fmath.truncate(this.rawAllResponsesMean, this.trunc), fieldItemNumber);
/* 1614 */     fout.print(Fmath.truncate(this.rawAllResponsesStandardDeviation, this.trunc), fieldItemNumber);
/* 1615 */     fout.print(Fmath.truncate(this.rawAllResponsesMinimum, this.trunc), fieldItemNumber);
/* 1616 */     fout.print(Fmath.truncate(this.rawAllResponsesMaximum, this.trunc), fieldItemNumber);
/* 1617 */     fout.print(Fmath.truncate(this.rawAllResponsesRange, this.trunc), fieldItemNumber);
/* 1618 */     fout.println(Fmath.truncate(this.rawAllResponsesTotal, this.trunc));
/* 1619 */     fout.println();
/*      */     
/* 1621 */     fout.println("ALL SCORES - standardized data");
/* 1622 */     fout.print("mean", fieldItemNumber);
/* 1623 */     fout.print("standard", fieldItemNumber);
/* 1624 */     fout.print("minimum", fieldItemNumber);
/* 1625 */     fout.print("maximum", fieldItemNumber);
/* 1626 */     fout.print("range", fieldItemNumber);
/* 1627 */     fout.println("overall");
/*      */     
/* 1629 */     fout.print("    ", fieldItemNumber);
/* 1630 */     fout.print("deviation", fieldItemNumber);
/* 1631 */     fout.print("     ", fieldItemNumber);
/* 1632 */     fout.print("     ", fieldItemNumber);
/* 1633 */     fout.print("     ", fieldItemNumber);
/* 1634 */     fout.println("total");
/*      */     
/* 1636 */     fout.print(Fmath.truncate(this.standardizedAllResponsesMean, this.trunc), fieldItemNumber);
/* 1637 */     fout.print(Fmath.truncate(this.standardizedAllResponsesStandardDeviation, this.trunc), fieldItemNumber);
/* 1638 */     fout.print(Fmath.truncate(this.standardizedAllResponsesMinimum, this.trunc), fieldItemNumber);
/* 1639 */     fout.print(Fmath.truncate(this.standardizedAllResponsesMaximum, this.trunc), fieldItemNumber);
/* 1640 */     fout.print(Fmath.truncate(this.standardizedAllResponsesRange, this.trunc), fieldItemNumber);
/* 1641 */     fout.println(Fmath.truncate(this.standardizedAllResponsesTotal, this.trunc));
/* 1642 */     fout.println();
/*      */     
/*      */ 
/*      */ 
/* 1646 */     fout.close();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private void deletedItemDataFile(double[] newRawAlpha, double[] newRawRho, double[] newStandardizedAlpha, double[] newStandardizedRho)
/*      */   {
/* 1653 */     ArrayMaths am = new ArrayMaths(newRawAlpha);
/* 1654 */     int index1 = am.maximumIndex();
/* 1655 */     am = new ArrayMaths(newStandardizedAlpha);
/* 1656 */     int index2 = am.maximumIndex();
/* 1657 */     am = new ArrayMaths(newRawRho);
/* 1658 */     int index3 = am.minimumIndex();
/* 1659 */     am = new ArrayMaths(newStandardizedRho);
/* 1660 */     int index4 = am.minimumIndex();
/*      */     
/*      */ 
/*      */ 
/* 1664 */     this.deletedItemIndex = index3;
/* 1665 */     if ((index1 == index2) && (index1 == index3) && (index1 == index4)) {
/* 1666 */       this.deletedItemIndex = index1;
/*      */ 
/*      */     }
/* 1669 */     else if ((index1 == index2) && ((index1 == index3) || (index1 == index4))) {
/* 1670 */       this.deletedItemIndex = index1;
/*      */ 
/*      */     }
/* 1673 */     else if ((index4 == index3) && ((index4 == index1) || (index4 == index2))) {
/* 1674 */       this.deletedItemIndex = index4;
/*      */ 
/*      */     }
/* 1677 */     else if ((index1 == index2) && (index3 == index4)) {
/* 1678 */       this.deletedItemIndex = index3;
/*      */ 
/*      */     }
/* 1681 */     else if ((index1 == index3) && (index2 == index4)) {
/* 1682 */       this.deletedItemIndex = index1;
/*      */ 
/*      */     }
/* 1685 */     else if ((index1 != index2) && (index2 != index3) && (index3 != index4)) {
/* 1686 */       this.deletedItemIndex = index3;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1694 */     String deletedFilename = null;
/* 1695 */     if (this.inputFilename != null) {
/* 1696 */       deletedFilename = this.inputFilename;
/* 1697 */       int pos = deletedFilename.indexOf(".");
/* 1698 */       if (pos != -1) deletedFilename = deletedFilename.substring(0, pos);
/* 1699 */       deletedFilename = deletedFilename + "_" + this.itemNames[this.deletedItemIndex] + "_deleted";
/* 1700 */       deletedFilename = deletedFilename + ".txt";
/*      */     }
/*      */     else {
/* 1703 */       deletedFilename = "DeletedItemFile.txt";
/*      */     }
/*      */     
/* 1706 */     FileOutput dfout = new FileOutput(deletedFilename);
/* 1707 */     String newTitle = this.title[0] + " - Item " + this.itemNames[this.deletedItemIndex] + " deleted";
/* 1708 */     dfout.println(newTitle);
/* 1709 */     dfout.println(this.nItems - 1);
/* 1710 */     dfout.println(this.nPersons);
/* 1711 */     for (int i = 0; i < this.nItems; i++) {
/* 1712 */       if (i != this.deletedItemIndex) dfout.printtab(this.itemNames[i]);
/*      */     }
/* 1714 */     dfout.println();
/* 1715 */     if (this.originalDataType == 0) {
/* 1716 */       for (int i = 0; i < this.nItems; i++) {
/* 1717 */         if (i != this.deletedItemIndex) {
/* 1718 */           for (int j = 0; j < this.nPersons; j++) {
/* 1719 */             dfout.printtab(this.scores0[i][j]);
/*      */           }
/* 1721 */           dfout.println();
/*      */         }
/*      */         
/*      */       }
/*      */     } else {
/* 1726 */       for (int j = 0; j < this.nPersons; j++) {
/* 1727 */         for (int i = 0; i < this.nItems; i++) {
/* 1728 */           if (i != this.deletedItemIndex) {
/* 1729 */             dfout.printtab(this.scores1[j][i]);
/*      */           }
/*      */         }
/* 1732 */         dfout.println();
/*      */       }
/*      */     }
/* 1735 */     dfout.close();
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/analysis/Cronbach.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */