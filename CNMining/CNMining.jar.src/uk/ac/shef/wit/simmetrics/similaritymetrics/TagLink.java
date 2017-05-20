/*     */ package uk.ac.shef.wit.simmetrics.similaritymetrics;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import uk.ac.shef.wit.simmetrics.tokenisers.InterfaceTokeniser;
/*     */ import uk.ac.shef.wit.simmetrics.tokenisers.TokeniserWhitespace;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class TagLink
/*     */   extends AbstractStringMetric
/*     */   implements Serializable
/*     */ {
/*     */   private HashMap<String, Float> idfMap;
/*     */   private AbstractStringMetric characterBasedStringMetric;
/*  89 */   private static final AbstractStringMetric DEFAULT_METRIC = new TagLinkToken();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private final InterfaceTokeniser tokeniser;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  99 */   private final float ESTIMATEDTIMINGCONST = 6.186371E-4F;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public TagLink()
/*     */   {
/* 106 */     this(DEFAULT_METRIC);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 114 */     System.out.println("WARNING - this metric is not recomended for fast processing it has been added \n by a third party into the library and from the source is an extremely \n unoptermised process the library author does not recomend its usage \n if you do take the time to perfect this code I will gladly update its\n source - thanks Sam");
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
/*     */   public TagLink(AbstractStringMetric characterBasedStringMetric)
/*     */   {
/* 127 */     this.characterBasedStringMetric = characterBasedStringMetric;
/* 128 */     this.tokeniser = new TokeniserWhitespace();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 136 */     System.out.println("WARNING - this metric is not recomended for fast processing it has been added \n by a third party into the library and from the source is an extremely \n unoptermised process the library author does not recomend its usage \n if you do take the time to perfect this code I will gladly update its\n source - thanks Sam");
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
/*     */ 
/*     */   public TagLink(String[] dataSetArray)
/*     */   {
/* 151 */     this(dataSetArray, DEFAULT_METRIC);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 159 */     System.out.println("WARNING - this metric is not recomended for fast processing it has been added \n by a third party into the library and from the source is an extremely \n unoptermised process the library author does not recomend its usage \n if you do take the time to perfect this code I will gladly update its\n source - thanks Sam");
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
/*     */ 
/*     */ 
/*     */   public TagLink(String[] dataSetArray, AbstractStringMetric characterBasedStringMetric)
/*     */   {
/* 175 */     this.characterBasedStringMetric = characterBasedStringMetric;
/* 176 */     this.tokeniser = new TokeniserWhitespace();
/* 177 */     this.idfMap = getIDFMap(dataSetArray);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 185 */     System.out.println("WARNING - this metric is not recomended for fast processing it has been added \n by a third party into the library and from the source is an extremely \n unoptermised process the library author does not recomend its usage \n if you do take the time to perfect this code I will gladly update its\n source - thanks Sam");
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
/*     */ 
/*     */ 
/*     */   private float getMinStringSize(String[] tTokens, String[] uTokens)
/*     */   {
/* 201 */     float tSize = 0.0F;float uSize = 0.0F;
/* 202 */     for (String tToken : tTokens) {
/* 203 */       tSize += tToken.length();
/*     */     }
/* 205 */     for (String uToken : uTokens) {
/* 206 */       uSize += uToken.length();
/*     */     }
/* 208 */     return Math.min(tSize, uSize);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float getSimilarityTimingEstimated(String string1, String string2)
/*     */   {
/* 220 */     float str1Length = string1.length();
/* 221 */     float str2Length = string2.length();
/* 222 */     return str1Length * str2Length * 6.186371E-4F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float getUnNormalisedSimilarity(String T, String U)
/*     */   {
/* 234 */     return getSimilarity(T, U);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float getSimilarity(String T, String U)
/*     */   {
/* 253 */     System.out.println("WARNING - this metric is not recomended for fast processing it has been added \n by a third party into the library and from the source is an extremely \n unoptermised process the library author does not recomend its usage \n if you do take the time to perfect this code I will gladly update its\n source - thanks Sam");
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 259 */     if (T.equals(U)) {
/* 260 */       return 1.0F;
/*     */     }
/* 262 */     ArrayList<String> tArrayList = this.tokeniser.tokenizeToArrayList(T);
/* 263 */     ArrayList<String> uArrayList = this.tokeniser.tokenizeToArrayList(U);
/* 264 */     String[] tTokens = (String[])tArrayList.toArray(new String[tArrayList.size()]);
/* 265 */     String[] uTokens = (String[])uArrayList.toArray(new String[uArrayList.size()]);
/* 266 */     float[] tIdfArray = getIDFArray(tTokens);
/* 267 */     float[] uIdfArray = getIDFArray(uTokens);
/* 268 */     return algorithm1(tTokens, uTokens, tIdfArray, uIdfArray);
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
/*     */ 
/*     */ 
/*     */   private float algorithm1(String[] tTokens, String[] uTokens, float[] tIdfArray, float[] uIdfArray)
/*     */   {
/* 284 */     ArrayList<Candidates> candidateList = obtainCandidateList(tTokens, uTokens, tIdfArray, uIdfArray);
/*     */     
/* 286 */     sortCandidateList(candidateList);
/* 287 */     float scoreValue = 0.0F;
/* 288 */     HashMap<Integer, Object> tMap = new HashMap();
/* 289 */     HashMap<Integer, Object> uMap = new HashMap();
/* 290 */     for (Object aCandidateList : candidateList) {
/* 291 */       Candidates actualCandidates = (Candidates)aCandidateList;
/* 292 */       Integer tPos = Integer.valueOf(actualCandidates.getTPos());
/* 293 */       Integer uPos = Integer.valueOf(actualCandidates.getUPos());
/* 294 */       if ((!tMap.containsKey(tPos)) && (!uMap.containsKey(uPos)))
/*     */       {
/* 296 */         scoreValue += actualCandidates.getScore();
/* 297 */         tMap.put(tPos, null);
/* 298 */         uMap.put(uPos, null);
/*     */       }
/*     */     }
/* 301 */     return scoreValue;
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
/*     */   public String getSimilarityExplained(String T, String U)
/*     */   {
/* 314 */     StringBuffer buff = new StringBuffer();
/* 315 */     buff.append("\n\t*****TagLink String Distance*****");
/* 316 */     if (T.equals(U)) {
/* 317 */       buff.append("\nS(T,U)=1.0\n");
/*     */     } else {
/* 319 */       ArrayList<String> tArrayList = this.tokeniser.tokenizeToArrayList(T);
/* 320 */       ArrayList<String> uArrayList = this.tokeniser.tokenizeToArrayList(U);
/* 321 */       String[] tTokens = (String[])tArrayList.toArray(new String[tArrayList.size()]);
/* 322 */       String[] uTokens = (String[])uArrayList.toArray(new String[uArrayList.size()]);
/* 323 */       buff.append("\nT={");
/* 324 */       for (String tToken : tTokens) {
/* 325 */         buff.append(tToken).append(", ");
/*     */       }
/* 327 */       buff.append("}\n");
/*     */       
/* 329 */       buff.append("U={");
/* 330 */       for (String uToken : uTokens) {
/* 331 */         buff.append(uToken).append(", ");
/*     */       }
/* 333 */       buff.append("}\n");
/*     */       
/* 335 */       float minStringSize = getMinStringSize(tTokens, uTokens);
/* 336 */       buff.append("min(|T|,|U|)=").append(minStringSize).append("\n");
/*     */       
/* 338 */       buff.append("\nIDF weights:\n");
/* 339 */       buff.append("Ti\tai(Ti)\n");
/* 340 */       float[] tIdfArray = getIDFArray(tTokens);
/* 341 */       float[] uIdfArray = getIDFArray(uTokens);
/* 342 */       for (int i = 0; i < tIdfArray.length; i++) {
/* 343 */         buff.append(tTokens[i]).append("\t").append(round(tIdfArray[i])).append("\n");
/*     */       }
/* 345 */       buff.append("\nUj\taj(Uj)\n");
/* 346 */       for (int i = 0; i < uIdfArray.length; i++) {
/* 347 */         buff.append(uTokens[i]).append("\t").append(round(uIdfArray[i])).append("\n");
/*     */       }
/* 349 */       buff.append("\nScores:\n");
/* 350 */       buff.append("Ti\tUj\tSij(Ti,Uj)\tIDFij(Ti,Uj)\tMRij(Ti,Uj)\tSij\n");
/* 351 */       ArrayList<Candidates> candidateList = new ArrayList();
/* 352 */       for (int t = 0; t < tTokens.length; t++) {
/* 353 */         int lastTr = -1;
/* 354 */         int u = 0; for (int flag = 0; (u < uTokens.length) && (flag == 0); u++) {
/* 355 */           int tr = Math.abs(t - u);
/* 356 */           if ((lastTr >= 0) && (lastTr < tr)) {
/* 357 */             flag = 1;
/*     */           } else {
/* 359 */             String tTok = tTokens[t];String uTok = uTokens[u];
/* 360 */             float innerScore = this.characterBasedStringMetric.getSimilarity(tTok, uTok);
/*     */             
/* 362 */             if (innerScore >= 0.0D) { float MR;
/*     */               float MR;
/* 364 */               if (innerScore == 1.0D) {
/* 365 */                 MR = tTokens[t].length();
/*     */               } else {
/* 367 */                 MR = ((TagLinkToken)this.characterBasedStringMetric).getMatched();
/*     */               }
/* 369 */               MR /= minStringSize;
/* 370 */               float IDF = tIdfArray[t] * uIdfArray[u];
/* 371 */               float weight = (IDF + MR) / 2.0F;
/* 372 */               if (innerScore == 1.0F) {
/* 373 */                 lastTr = tr;
/*     */               }
/* 375 */               buff.append(tTok).append("\t").append(uTok).append("\t").append(round(innerScore)).append("\t").append(round(IDF)).append("\t").append(round(MR)).append("\t").append(round(innerScore * weight)).append("\n");
/* 376 */               candidateList.add(new Candidates(t, u, innerScore * weight));
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 381 */       sortCandidateList(candidateList);
/*     */       
/*     */ 
/* 384 */       buff.append("\nCommon tokens (Algorithm 1):\n");
/* 385 */       buff.append("Ti\tUj\tSij*Xij\n");
/* 386 */       float score = 0.0F;
/* 387 */       HashMap<Integer, Object> tMap = new HashMap();
/* 388 */       HashMap<Integer, Object> uMap = new HashMap();
/* 389 */       for (Object aCandidateList : candidateList) {
/* 390 */         Candidates actualCandidates = (Candidates)aCandidateList;
/* 391 */         Integer tPos = Integer.valueOf(actualCandidates.getTPos());
/* 392 */         Integer uPos = Integer.valueOf(actualCandidates.getUPos());
/* 393 */         if ((!tMap.containsKey(tPos)) && (!uMap.containsKey(uPos)))
/*     */         {
/* 395 */           float tokenScore = actualCandidates.getScore();
/* 396 */           score += tokenScore;
/* 397 */           tMap.put(tPos, null);
/* 398 */           uMap.put(uPos, null);
/* 399 */           buff.append(tTokens[tPos.intValue()]).append("\t").append(uTokens[uPos.intValue()]).append("\t").append(round(tokenScore)).append("\n");
/*     */         }
/*     */       }
/* 402 */       buff.append("\nS(T,U)=").append(round(score)).append("\n");
/*     */     }
/* 404 */     return buff.toString();
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
/*     */ 
/*     */ 
/*     */   private ArrayList<Candidates> obtainCandidateList(String[] tTokens, String[] uTokens, float[] tIdfArray, float[] uIdfArray)
/*     */   {
/* 420 */     ArrayList<Candidates> candidateList = new ArrayList();
/* 421 */     float minStringSize = getMinStringSize(tTokens, uTokens);
/* 422 */     for (int t = 0; t < tTokens.length; t++) {
/* 423 */       int lastTr = -1;
/* 424 */       int u = 0; for (int flag = 0; (u < uTokens.length) && (flag == 0); u++) {
/* 425 */         int tr = Math.abs(t - u);
/* 426 */         if ((lastTr >= 0) && (lastTr < tr)) {
/* 427 */           flag = 1;
/*     */         } else {
/* 429 */           String tTok = tTokens[t];String uTok = uTokens[u];
/* 430 */           float innerScore = this.characterBasedStringMetric.getSimilarity(tTok, uTok);
/*     */           
/* 432 */           if (innerScore >= 0.0F) { float matched;
/*     */             float matched;
/* 434 */             if (innerScore == 1.0F) {
/* 435 */               matched = tTokens[t].length();
/*     */             } else {
/* 437 */               matched = ((TagLinkToken)this.characterBasedStringMetric).getMatched();
/*     */             }
/* 439 */             float weightMatched = matched / minStringSize;
/* 440 */             float weightTFIDF = tIdfArray[t] * uIdfArray[u];
/* 441 */             float weight = (weightTFIDF + weightMatched) / 2.0F;
/* 442 */             if (innerScore == 1.0F) {
/* 443 */               lastTr = tr;
/*     */             }
/* 445 */             candidateList.add(new Candidates(t, u, innerScore * weight));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 450 */     return candidateList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void sortCandidateList(ArrayList<Candidates> list)
/*     */   {
/* 459 */     Collections.sort(list, new Comparator()
/*     */     {
/*     */       public int compare(Object o1, Object o2) {
/* 462 */         float scoreT = ((Candidates)o1).getScore();
/* 463 */         float scoreU = ((Candidates)o2).getScore();
/* 464 */         if (scoreU > scoreT) {
/* 465 */           return 1;
/*     */         }
/* 467 */         if (scoreU < scoreT) {
/* 468 */           return -1;
/*     */         }
/* 470 */         return 0;
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private float[] getIDFArray(String[] tokenArray)
/*     */   {
/* 483 */     int tokenArrayLength = tokenArray.length;
/* 484 */     float[] IDFArray = new float[tokenArrayLength];
/* 485 */     if (this.idfMap == null) {
/* 486 */       float cosineWeight = 1.0F / (float)Math.sqrt(tokenArrayLength);
/* 487 */       for (int i = 0; i < tokenArrayLength; i++) {
/* 488 */         IDFArray[i] = cosineWeight;
/*     */       }
/*     */     } else {
/* 491 */       float sq = 0.0F;
/* 492 */       for (int i = 0; i < tokenArrayLength; i++) {
/* 493 */         String actualToken = tokenArray[i];
/* 494 */         float idfWeight = 0.0F;
/*     */         try {
/* 496 */           idfWeight = ((Float)this.idfMap.get(actualToken)).floatValue();
/*     */         }
/*     */         catch (Exception e)
/*     */         {
/* 500 */           e.printStackTrace();
/*     */         }
/* 502 */         IDFArray[i] = idfWeight;
/* 503 */         sq += idfWeight * idfWeight;
/*     */       }
/* 505 */       sq = (float)Math.sqrt(sq);
/* 506 */       for (int i = 0; i < tokenArrayLength; i++) {
/* 507 */         IDFArray[i] /= sq;
/*     */       }
/*     */     }
/* 510 */     return IDFArray;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getLongDescriptionString()
/*     */   {
/* 519 */     return getShortDescriptionString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getShortDescriptionString()
/*     */   {
/* 528 */     if (this.idfMap == null) {
/* 529 */       return "[TagLink_[" + this.characterBasedStringMetric.toString() + "]";
/*     */     }
/*     */     
/* 532 */     return "[TagLink_IDF_[" + this.characterBasedStringMetric.toString() + "]";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private HashMap<String, Float> getIDFMap(String[] dataSetArray)
/*     */   {
/* 544 */     float N = dataSetArray.length;
/* 545 */     HashMap<String, Float> idfMap = new HashMap();
/* 546 */     for (int row = 0; row < N; row++) {
/* 547 */       HashMap<String, Object> rowMap = new HashMap();
/* 548 */       HashMap<String, Float> freqMap = new HashMap();
/* 549 */       String actualRow = dataSetArray[row];
/* 550 */       ArrayList<String> tokenArrayList = this.tokeniser.tokenizeToArrayList(actualRow);
/* 551 */       String[] rowArray = (String[])tokenArrayList.toArray(new String[tokenArrayList.size()]);
/* 552 */       for (String actualToken : rowArray) {
/* 553 */         rowMap.put(actualToken, null);
/*     */         
/* 555 */         float actualFrequency = getFrequency(actualToken, freqMap) + 1.0F;
/*     */         
/* 557 */         freqMap.put(actualToken, Float.valueOf(actualFrequency));
/*     */       }
/* 559 */       Collection<String> entries = rowMap.keySet();
/* 560 */       for (String actualToken : entries) {
/* 561 */         float actualFrequency = getFrequency(actualToken, idfMap) + 1.0F;
/*     */         
/* 563 */         idfMap.put(actualToken, Float.valueOf(actualFrequency));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 568 */     Collection<Map.Entry<String, Float>> entries = idfMap.entrySet();
/*     */     
/* 570 */     for (Map.Entry<String, Float> entry : entries) {
/* 571 */       Map.Entry<String, Float> ent = entry;
/* 572 */       String key = (String)ent.getKey();
/* 573 */       float frequency = ((Float)ent.getValue()).floatValue();
/* 574 */       float idf = (float)Math.log(N / frequency + 1.0F);
/* 575 */       idfMap.put(key, Float.valueOf(idf));
/*     */     }
/* 577 */     return idfMap;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private float getFrequency(String word, Map<String, Float> map)
/*     */   {
/* 588 */     Float frequency = (Float)map.get(word);
/* 589 */     if (frequency == null) {
/* 590 */       return 0.0F;
/*     */     }
/* 592 */     return frequency.floatValue();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private float round(float number)
/*     */   {
/* 602 */     int round = (int)(number * 1000.0F);
/* 603 */     float rest = number * 1000.0F - round;
/* 604 */     if (rest >= 0.5F) {
/* 605 */       round++;
/*     */     }
/* 607 */     return round / 1000.0F;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/similaritymetrics/TagLink.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */