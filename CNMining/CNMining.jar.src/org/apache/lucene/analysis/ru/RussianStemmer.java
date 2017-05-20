/*     */ package org.apache.lucene.analysis.ru;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class RussianStemmer
/*     */ {
/*     */   private char[] charset;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private int RV;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private int R1;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private int R2;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  33 */   private static char A = '\000';
/*  34 */   private static char B = '\001';
/*  35 */   private static char V = '\002';
/*  36 */   private static char G = '\003';
/*  37 */   private static char D = '\004';
/*  38 */   private static char E = '\005';
/*  39 */   private static char ZH = '\006';
/*  40 */   private static char Z = '\007';
/*  41 */   private static char I = '\b';
/*  42 */   private static char I_ = '\t';
/*  43 */   private static char K = '\n';
/*  44 */   private static char L = '\013';
/*  45 */   private static char M = '\f';
/*  46 */   private static char N = '\r';
/*  47 */   private static char O = '\016';
/*  48 */   private static char P = '\017';
/*  49 */   private static char R = '\020';
/*  50 */   private static char S = '\021';
/*  51 */   private static char T = '\022';
/*  52 */   private static char U = '\023';
/*  53 */   private static char F = '\024';
/*  54 */   private static char X = '\025';
/*  55 */   private static char TS = '\026';
/*  56 */   private static char CH = '\027';
/*  57 */   private static char SH = '\030';
/*  58 */   private static char SHCH = '\031';
/*  59 */   private static char HARD = '\032';
/*  60 */   private static char Y = '\033';
/*  61 */   private static char SOFT = '\034';
/*  62 */   private static char AE = '\035';
/*  63 */   private static char IU = '\036';
/*  64 */   private static char IA = '\037';
/*     */   
/*     */ 
/*  67 */   private static char[] vowels = { A, E, I, O, U, Y, AE, IU, IA };
/*     */   
/*  69 */   private static char[][] perfectiveGerundEndings1 = { { V }, { V, SH, I }, { V, SH, I, S, SOFT } };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  75 */   private static char[][] perfectiveGerund1Predessors = { { A }, { IA } };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  80 */   private static char[][] perfectiveGerundEndings2 = { { I, V }, { Y, V }, { I, V, SH, I }, { Y, V, SH, I }, { I, V, SH, I, S, SOFT }, { Y, V, SH, I, S, SOFT } };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  88 */   private static char[][] adjectiveEndings = { { E, E }, { I, E }, { Y, E }, { O, E }, { E, I_ }, { I, I_ }, { Y, I_ }, { O, I_ }, { E, M }, { I, M }, { Y, M }, { O, M }, { I, X }, { Y, X }, { U, IU }, { IU, IU }, { A, IA }, { IA, IA }, { O, IU }, { E, IU }, { I, M, I }, { Y, M, I }, { E, G, O }, { O, G, O }, { E, M, U }, { O, M, U } };
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
/* 117 */   private static char[][] participleEndings1 = { { SHCH }, { E, M }, { N, N }, { V, SH }, { IU, SHCH } };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 125 */   private static char[][] participleEndings2 = { { I, V, SH }, { Y, V, SH }, { U, IU, SHCH } };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 131 */   private static char[][] participle1Predessors = { { A }, { IA } };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 136 */   private static char[][] reflexiveEndings = { { S, IA }, { S, SOFT } };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 141 */   private static char[][] verbEndings1 = { { I_ }, { L }, { N }, { L, O }, { N, O }, { E, T }, { IU, T }, { L, A }, { N, A }, { L, I }, { E, M }, { N, Y }, { E, T, E }, { I_, T, E }, { T, SOFT }, { E, SH, SOFT }, { N, N, O } };
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
/* 161 */   private static char[][] verbEndings2 = { { IU }, { U, IU }, { E, N }, { E, I_ }, { IA, T }, { U, I_ }, { I, L }, { Y, L }, { I, M }, { Y, M }, { I, T }, { Y, T }, { I, L, A }, { Y, L, A }, { E, N, A }, { I, T, E }, { I, L, I }, { Y, L, I }, { I, L, O }, { Y, L, O }, { E, N, O }, { U, E, T }, { U, IU, T }, { E, N, Y }, { I, T, SOFT }, { Y, T, SOFT }, { I, SH, SOFT }, { E, I_, T, E }, { U, I_, T, E } };
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
/* 193 */   private static char[][] verb1Predessors = { { A }, { IA } };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 198 */   private static char[][] nounEndings = { { A }, { U }, { I_ }, { O }, { U }, { E }, { Y }, { I }, { SOFT }, { IA }, { E, V }, { O, V }, { I, E }, { SOFT, E }, { IA, X }, { I, IU }, { E, I }, { I, I }, { E, I_ }, { O, I_ }, { E, M }, { A, M }, { O, M }, { A, X }, { SOFT, IU }, { I, IA }, { SOFT, IA }, { I, I_ }, { IA, M }, { IA, M, I }, { A, M, I }, { I, E, I_ }, { I, IA, M }, { I, E, M }, { I, IA, X }, { I, IA, M, I } };
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
/* 237 */   private static char[][] superlativeEndings = { { E, I_, SH }, { E, I_, SH, E } };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 242 */   private static char[][] derivationalEndings = { { O, S, T }, { O, S, T, SOFT } };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RussianStemmer() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RussianStemmer(char[] charset)
/*     */   {
/* 261 */     this.charset = charset;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean adjectival(StringBuffer stemmingZone)
/*     */   {
/* 273 */     if (!findAndRemoveEnding(stemmingZone, adjectiveEndings)) {
/* 274 */       return false;
/*     */     }
/* 276 */     boolean r = (findAndRemoveEnding(stemmingZone, participleEndings1, participle1Predessors)) || (findAndRemoveEnding(stemmingZone, participleEndings2));
/*     */     
/*     */ 
/*     */ 
/* 280 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean derivational(StringBuffer stemmingZone)
/*     */   {
/* 290 */     int endingLength = findEnding(stemmingZone, derivationalEndings);
/* 291 */     if (endingLength == 0)
/*     */     {
/* 293 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 297 */     if (this.R2 - this.RV <= stemmingZone.length() - endingLength)
/*     */     {
/* 299 */       stemmingZone.setLength(stemmingZone.length() - endingLength);
/* 300 */       return true;
/*     */     }
/*     */     
/*     */ 
/* 304 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private int findEnding(StringBuffer stemmingZone, int startIndex, char[][] theEndingClass)
/*     */   {
/* 315 */     boolean match = false;
/* 316 */     for (int i = theEndingClass.length - 1; i >= 0; i--)
/*     */     {
/* 318 */       char[] theEnding = theEndingClass[i];
/*     */       
/* 320 */       if (startIndex < theEnding.length - 1)
/*     */       {
/* 322 */         match = false;
/*     */       }
/*     */       else {
/* 325 */         match = true;
/* 326 */         int stemmingIndex = startIndex;
/* 327 */         for (int j = theEnding.length - 1; j >= 0; j--)
/*     */         {
/* 329 */           if (stemmingZone.charAt(stemmingIndex--) != this.charset[theEnding[j]])
/*     */           {
/* 331 */             match = false;
/* 332 */             break;
/*     */           }
/*     */         }
/*     */         
/* 336 */         if (match)
/*     */         {
/* 338 */           return theEndingClass[i].length; }
/*     */       }
/*     */     }
/* 341 */     return 0;
/*     */   }
/*     */   
/*     */   private int findEnding(StringBuffer stemmingZone, char[][] theEndingClass)
/*     */   {
/* 346 */     return findEnding(stemmingZone, stemmingZone.length() - 1, theEndingClass);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean findAndRemoveEnding(StringBuffer stemmingZone, char[][] theEndingClass)
/*     */   {
/* 355 */     int endingLength = findEnding(stemmingZone, theEndingClass);
/* 356 */     if (endingLength == 0)
/*     */     {
/* 358 */       return false;
/*     */     }
/* 360 */     stemmingZone.setLength(stemmingZone.length() - endingLength);
/*     */     
/* 362 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean findAndRemoveEnding(StringBuffer stemmingZone, char[][] theEndingClass, char[][] thePredessors)
/*     */   {
/* 374 */     int endingLength = findEnding(stemmingZone, theEndingClass);
/* 375 */     if (endingLength == 0)
/*     */     {
/* 377 */       return false;
/*     */     }
/*     */     
/* 380 */     int predessorLength = findEnding(stemmingZone, stemmingZone.length() - endingLength - 1, thePredessors);
/*     */     
/*     */ 
/*     */ 
/* 384 */     if (predessorLength == 0) {
/* 385 */       return false;
/*     */     }
/* 387 */     stemmingZone.setLength(stemmingZone.length() - endingLength);
/*     */     
/* 389 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void markPositions(String word)
/*     */   {
/* 401 */     this.RV = 0;
/* 402 */     this.R1 = 0;
/* 403 */     this.R2 = 0;
/* 404 */     int i = 0;
/*     */     
/* 406 */     while ((word.length() > i) && (!isVowel(word.charAt(i))))
/*     */     {
/* 408 */       i++;
/*     */     }
/* 410 */     if (word.length() - 1 < ++i)
/* 411 */       return;
/* 412 */     this.RV = i;
/*     */     
/* 414 */     while ((word.length() > i) && (isVowel(word.charAt(i))))
/*     */     {
/* 416 */       i++;
/*     */     }
/* 418 */     if (word.length() - 1 < ++i)
/* 419 */       return;
/* 420 */     this.R1 = i;
/*     */     
/* 422 */     while ((word.length() > i) && (!isVowel(word.charAt(i))))
/*     */     {
/* 424 */       i++;
/*     */     }
/* 426 */     if (word.length() - 1 < ++i)
/* 427 */       return;
/* 428 */     while ((word.length() > i) && (isVowel(word.charAt(i))))
/*     */     {
/* 430 */       i++;
/*     */     }
/* 432 */     if (word.length() - 1 < ++i)
/* 433 */       return;
/* 434 */     this.R2 = i;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean isVowel(char letter)
/*     */   {
/* 445 */     for (int i = 0; i < vowels.length; i++)
/*     */     {
/* 447 */       if (letter == this.charset[vowels[i]])
/* 448 */         return true;
/*     */     }
/* 450 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean noun(StringBuffer stemmingZone)
/*     */   {
/* 460 */     return findAndRemoveEnding(stemmingZone, nounEndings);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean perfectiveGerund(StringBuffer stemmingZone)
/*     */   {
/* 470 */     return (findAndRemoveEnding(stemmingZone, perfectiveGerundEndings1, perfectiveGerund1Predessors)) || (findAndRemoveEnding(stemmingZone, perfectiveGerundEndings2));
/*     */   }
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
/*     */   private boolean reflexive(StringBuffer stemmingZone)
/*     */   {
/* 484 */     return findAndRemoveEnding(stemmingZone, reflexiveEndings);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean removeI(StringBuffer stemmingZone)
/*     */   {
/* 494 */     if ((stemmingZone.length() > 0) && (stemmingZone.charAt(stemmingZone.length() - 1) == this.charset[I]))
/*     */     {
/*     */ 
/* 497 */       stemmingZone.setLength(stemmingZone.length() - 1);
/* 498 */       return true;
/*     */     }
/*     */     
/*     */ 
/* 502 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean removeSoft(StringBuffer stemmingZone)
/*     */   {
/* 513 */     if ((stemmingZone.length() > 0) && (stemmingZone.charAt(stemmingZone.length() - 1) == this.charset[SOFT]))
/*     */     {
/*     */ 
/* 516 */       stemmingZone.setLength(stemmingZone.length() - 1);
/* 517 */       return true;
/*     */     }
/*     */     
/*     */ 
/* 521 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCharset(char[] newCharset)
/*     */   {
/* 532 */     this.charset = newCharset;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void setEndings()
/*     */   {
/* 541 */     vowels = new char[] { A, E, I, O, U, Y, AE, IU, IA };
/*     */     
/* 543 */     perfectiveGerundEndings1 = new char[][] { { V }, { V, SH, I }, { V, SH, I, S, SOFT } };
/*     */     
/*     */ 
/*     */ 
/* 547 */     perfectiveGerund1Predessors = new char[][] { { A }, { IA } };
/*     */     
/*     */ 
/* 550 */     perfectiveGerundEndings2 = new char[][] { { I, V }, { Y, V }, { I, V, SH, I }, { Y, V, SH, I }, { I, V, SH, I, S, SOFT }, { Y, V, SH, I, S, SOFT } };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 559 */     adjectiveEndings = new char[][] { { E, E }, { I, E }, { Y, E }, { O, E }, { E, I_ }, { I, I_ }, { Y, I_ }, { O, I_ }, { E, M }, { I, M }, { Y, M }, { O, M }, { I, X }, { Y, X }, { U, IU }, { IU, IU }, { A, IA }, { IA, IA }, { O, IU }, { E, IU }, { I, M, I }, { Y, M, I }, { E, G, O }, { O, G, O }, { E, M, U }, { O, M, U } };
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
/* 588 */     participleEndings1 = new char[][] { { SHCH }, { E, M }, { N, N }, { V, SH }, { IU, SHCH } };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 596 */     participleEndings2 = new char[][] { { I, V, SH }, { Y, V, SH }, { U, IU, SHCH } };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 602 */     participle1Predessors = new char[][] { { A }, { IA } };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 607 */     reflexiveEndings = new char[][] { { S, IA }, { S, SOFT } };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 612 */     verbEndings1 = new char[][] { { I_ }, { L }, { N }, { L, O }, { N, O }, { E, T }, { IU, T }, { L, A }, { N, A }, { L, I }, { E, M }, { N, Y }, { E, T, E }, { I_, T, E }, { T, SOFT }, { E, SH, SOFT }, { N, N, O } };
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
/* 632 */     verbEndings2 = new char[][] { { IU }, { U, IU }, { E, N }, { E, I_ }, { IA, T }, { U, I_ }, { I, L }, { Y, L }, { I, M }, { Y, M }, { I, T }, { Y, T }, { I, L, A }, { Y, L, A }, { E, N, A }, { I, T, E }, { I, L, I }, { Y, L, I }, { I, L, O }, { Y, L, O }, { E, N, O }, { U, E, T }, { U, IU, T }, { E, N, Y }, { I, T, SOFT }, { Y, T, SOFT }, { I, SH, SOFT }, { E, I_, T, E }, { U, I_, T, E } };
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
/* 664 */     verb1Predessors = new char[][] { { A }, { IA } };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 669 */     nounEndings = new char[][] { { A }, { IU }, { I_ }, { O }, { U }, { E }, { Y }, { I }, { SOFT }, { IA }, { E, V }, { O, V }, { I, E }, { SOFT, E }, { IA, X }, { I, IU }, { E, I }, { I, I }, { E, I_ }, { O, I_ }, { E, M }, { A, M }, { O, M }, { A, X }, { SOFT, IU }, { I, IA }, { SOFT, IA }, { I, I_ }, { IA, M }, { IA, M, I }, { A, M, I }, { I, E, I_ }, { I, IA, M }, { I, E, M }, { I, IA, X }, { I, IA, M, I } };
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
/* 708 */     superlativeEndings = new char[][] { { E, I_, SH }, { E, I_, SH, E } };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 713 */     derivationalEndings = new char[][] { { O, S, T }, { O, S, T, SOFT } };
/*     */   }
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
/*     */   public String stem(String input)
/*     */   {
/* 727 */     markPositions(input);
/* 728 */     if (this.RV == 0)
/* 729 */       return input;
/* 730 */     StringBuffer stemmingZone = new StringBuffer(input.substring(this.RV));
/*     */     
/*     */     boolean r;
/*     */     
/* 734 */     if (!perfectiveGerund(stemmingZone))
/*     */     {
/* 736 */       reflexive(stemmingZone);
/* 737 */       r = (adjectival(stemmingZone)) || (verb(stemmingZone)) || (noun(stemmingZone));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 743 */     removeI(stemmingZone);
/*     */     
/* 745 */     derivational(stemmingZone);
/*     */     
/* 747 */     superlative(stemmingZone);
/* 748 */     undoubleN(stemmingZone);
/* 749 */     removeSoft(stemmingZone);
/*     */     
/* 751 */     return input.substring(0, this.RV) + stemmingZone.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean superlative(StringBuffer stemmingZone)
/*     */   {
/* 761 */     return findAndRemoveEnding(stemmingZone, superlativeEndings);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean undoubleN(StringBuffer stemmingZone)
/*     */   {
/* 771 */     char[][] doubleN = { { N, N } };
/*     */     
/*     */ 
/* 774 */     if (findEnding(stemmingZone, doubleN) != 0)
/*     */     {
/* 776 */       stemmingZone.setLength(stemmingZone.length() - 1);
/* 777 */       return true;
/*     */     }
/*     */     
/*     */ 
/* 781 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean verb(StringBuffer stemmingZone)
/*     */   {
/* 792 */     return (findAndRemoveEnding(stemmingZone, verbEndings1, verb1Predessors)) || (findAndRemoveEnding(stemmingZone, verbEndings2));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String stem(String theWord, char[] charset)
/*     */   {
/* 804 */     RussianStemmer stemmer = new RussianStemmer();
/* 805 */     stemmer.setCharset(charset);
/* 806 */     return stemmer.stem(theWord);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/analysis/ru/RussianStemmer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */