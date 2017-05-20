/*     */ package uk.ac.shef.wit.simmetrics.similaritymetrics;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import uk.ac.shef.wit.simmetrics.tokenisers.InterfaceTokeniser;
/*     */ import uk.ac.shef.wit.simmetrics.tokenisers.TokeniserQGram3Extended;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class QGramsDistance
/*     */   extends AbstractStringMetric
/*     */   implements Serializable
/*     */ {
/*  62 */   private final float ESTIMATEDTIMINGCONST = 1.34E-4F;
/*     */   
/*     */ 
/*     */ 
/*     */   private final InterfaceTokeniser tokeniser;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public QGramsDistance()
/*     */   {
/*  73 */     this.tokeniser = new TokeniserQGram3Extended();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public QGramsDistance(InterfaceTokeniser tokeniserToUse)
/*     */   {
/*  82 */     this.tokeniser = tokeniserToUse;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getShortDescriptionString()
/*     */   {
/*  91 */     return "QGramsDistance";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getLongDescriptionString()
/*     */   {
/* 100 */     return "Implements the Q Grams Distance algorithm providing a similarity measure between two strings using the qGram approach check matching qGrams/possible matching qGrams";
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
/*     */   public String getSimilarityExplained(String string1, String string2)
/*     */   {
/* 113 */     return null;
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
/*     */   public float getSimilarityTimingEstimated(String string1, String string2)
/*     */   {
/* 127 */     float str1Length = string1.length();
/* 128 */     float str2Length = string2.length();
/* 129 */     return str1Length * str2Length * 1.34E-4F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float getSimilarity(String string1, String string2)
/*     */   {
/* 140 */     ArrayList<String> str1Tokens = this.tokeniser.tokenizeToArrayList(string1);
/* 141 */     ArrayList<String> str2Tokens = this.tokeniser.tokenizeToArrayList(string2);
/*     */     
/* 143 */     int maxQGramsMatching = str1Tokens.size() + str2Tokens.size();
/*     */     
/*     */ 
/* 146 */     if (maxQGramsMatching == 0) {
/* 147 */       return 0.0F;
/*     */     }
/* 149 */     return (maxQGramsMatching - getUnNormalisedSimilarity(string1, string2)) / maxQGramsMatching;
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
/*     */   public float getUnNormalisedSimilarity(String string1, String string2)
/*     */   {
/* 162 */     ArrayList<String> str1Tokens = this.tokeniser.tokenizeToArrayList(string1);
/* 163 */     ArrayList<String> str2Tokens = this.tokeniser.tokenizeToArrayList(string2);
/*     */     
/* 165 */     Set<String> allTokens = new HashSet();
/* 166 */     allTokens.addAll(str1Tokens);
/* 167 */     allTokens.addAll(str2Tokens);
/*     */     
/* 169 */     Iterator<String> allTokensIt = allTokens.iterator();
/* 170 */     int difference = 0;
/* 171 */     while (allTokensIt.hasNext()) {
/* 172 */       String token = (String)allTokensIt.next();
/* 173 */       int matchingQGrams1 = 0;
/* 174 */       for (String str1Token : str1Tokens) {
/* 175 */         if (str1Token.equals(token)) {
/* 176 */           matchingQGrams1++;
/*     */         }
/*     */       }
/* 179 */       int matchingQGrams2 = 0;
/* 180 */       for (String str2Token : str2Tokens) {
/* 181 */         if (str2Token.equals(token)) {
/* 182 */           matchingQGrams2++;
/*     */         }
/*     */       }
/* 185 */       if (matchingQGrams1 > matchingQGrams2) {
/* 186 */         difference += matchingQGrams1 - matchingQGrams2;
/*     */       } else {
/* 188 */         difference += matchingQGrams2 - matchingQGrams1;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 193 */     return difference;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/similaritymetrics/QGramsDistance.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */