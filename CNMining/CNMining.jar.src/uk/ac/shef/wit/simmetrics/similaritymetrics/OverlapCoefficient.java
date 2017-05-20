/*     */ package uk.ac.shef.wit.simmetrics.similaritymetrics;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
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
/*     */ public final class OverlapCoefficient
/*     */   extends AbstractStringMetric
/*     */   implements Serializable
/*     */ {
/*  64 */   private final float ESTIMATEDTIMINGCONST = 1.4E-4F;
/*     */   
/*     */ 
/*     */ 
/*     */   private final InterfaceTokeniser tokeniser;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public OverlapCoefficient()
/*     */   {
/*  75 */     this.tokeniser = new TokeniserWhitespace();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public OverlapCoefficient(InterfaceTokeniser tokeniserToUse)
/*     */   {
/*  84 */     this.tokeniser = tokeniserToUse;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getShortDescriptionString()
/*     */   {
/*  93 */     return "OverlapCoefficient";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getLongDescriptionString()
/*     */   {
/* 102 */     return "Implements the Overlap Coefficient algorithm providing a similarity measure between two string where it is determined to what degree a string is a subset of another";
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
/* 115 */     return null;
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
/* 129 */     float str1Tokens = this.tokeniser.tokenizeToArrayList(string1).size();
/* 130 */     float str2Tokens = this.tokeniser.tokenizeToArrayList(string2).size();
/* 131 */     return str1Tokens * str2Tokens * 1.4E-4F;
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
/*     */   public float getSimilarity(String string1, String string2)
/*     */   {
/* 144 */     ArrayList<String> str1Tokens = this.tokeniser.tokenizeToArrayList(string1);
/* 145 */     ArrayList<String> str2Tokens = this.tokeniser.tokenizeToArrayList(string2);
/*     */     
/* 147 */     Set<String> allTokens = new HashSet();
/* 148 */     allTokens.addAll(str1Tokens);
/* 149 */     int termsInString1 = allTokens.size();
/* 150 */     Set<String> secondStringTokens = new HashSet();
/* 151 */     secondStringTokens.addAll(str2Tokens);
/* 152 */     int termsInString2 = secondStringTokens.size();
/*     */     
/*     */ 
/* 155 */     allTokens.addAll(secondStringTokens);
/* 156 */     int commonTerms = termsInString1 + termsInString2 - allTokens.size();
/*     */     
/*     */ 
/* 159 */     return commonTerms / Math.min(termsInString1, termsInString2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float getUnNormalisedSimilarity(String string1, String string2)
/*     */   {
/* 170 */     return getSimilarity(string1, string2);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/similaritymetrics/OverlapCoefficient.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */