/*     */ package uk.ac.shef.wit.simmetrics.similaritymetrics;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
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
/*     */ public final class ChapmanOrderedNameCompoundSimilarity
/*     */   extends AbstractStringMetric
/*     */   implements Serializable
/*     */ {
/*  63 */   private final float ESTIMATEDTIMINGCONST = 0.026571428F;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   final InterfaceTokeniser tokeniser;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  73 */   private final AbstractStringMetric internalStringMetric1 = new Soundex();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  78 */   private final AbstractStringMetric internalStringMetric2 = new SmithWaterman();
/*     */   
/*     */ 
/*     */ 
/*     */   public ChapmanOrderedNameCompoundSimilarity()
/*     */   {
/*  84 */     this.tokeniser = new TokeniserWhitespace();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ChapmanOrderedNameCompoundSimilarity(InterfaceTokeniser tokeniserToUse)
/*     */   {
/*  93 */     this.tokeniser = tokeniserToUse;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getShortDescriptionString()
/*     */   {
/* 102 */     return "ChapmanOrderedNameCompoundSimilarity";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getLongDescriptionString()
/*     */   {
/* 111 */     return "Implements the Chapman Ordered Name Compound Similarity algorithm whereby terms are matched and tested against the standard soundex algorithm - this is intended to provide a better rating for lists of proper names.";
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
/* 124 */     return null;
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
/* 138 */     float str1Tokens = this.tokeniser.tokenizeToArrayList(string1).size();
/* 139 */     float str2Tokens = this.tokeniser.tokenizeToArrayList(string2).size();
/* 140 */     return (this.tokeniser.tokenizeToArrayList(string1).size() + this.tokeniser.tokenizeToArrayList(string2).size()) * ((str1Tokens + str2Tokens) * 0.026571428F);
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
/*     */   public final float getSimilarity(String string1, String string2)
/*     */   {
/* 153 */     ArrayList str1Tokens = this.tokeniser.tokenizeToArrayList(string1);
/* 154 */     ArrayList str2Tokens = this.tokeniser.tokenizeToArrayList(string2);
/* 155 */     int str1TokenNum = str1Tokens.size();
/* 156 */     int str2TokenNum = str2Tokens.size();
/* 157 */     int minTokens = Math.min(str1TokenNum, str2TokenNum);
/*     */     
/* 159 */     float SKEW_AMMOUNT = 1.0F;
/*     */     
/* 161 */     float sumMatches = 0.0F;
/* 162 */     for (int i = 1; i <= minTokens; i++) {
/* 163 */       float strWeightingAdjustment = 1.0F / minTokens + (minTokens - i + 0.5F - minTokens / 2.0F) / minTokens * SKEW_AMMOUNT * (1.0F / minTokens);
/* 164 */       String sToken = (String)str1Tokens.get(str1TokenNum - i);
/* 165 */       String tToken = (String)str2Tokens.get(str2TokenNum - i);
/*     */       
/* 167 */       float found1 = this.internalStringMetric1.getSimilarity(sToken, tToken);
/* 168 */       float found2 = this.internalStringMetric2.getSimilarity(sToken, tToken);
/* 169 */       sumMatches += 0.5F * (found1 + found2) * strWeightingAdjustment;
/*     */     }
/* 171 */     return sumMatches;
/*     */   }
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
/* 183 */     return getSimilarity(string1, string2);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/similaritymetrics/ChapmanOrderedNameCompoundSimilarity.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */