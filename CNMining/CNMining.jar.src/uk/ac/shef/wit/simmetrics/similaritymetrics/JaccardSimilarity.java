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
/*     */ public final class JaccardSimilarity
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
/*     */   public JaccardSimilarity()
/*     */   {
/*  75 */     this.tokeniser = new TokeniserWhitespace();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public JaccardSimilarity(InterfaceTokeniser tokeniserToUse)
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
/*  93 */     return "JaccardSimilarity";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getLongDescriptionString()
/*     */   {
/* 102 */     return "Implements the Jaccard Similarity algorithm providing a similarity measure between two strings";
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
/* 152 */     ArrayList<String> str1Tokens = this.tokeniser.tokenizeToArrayList(string1);
/* 153 */     ArrayList<String> str2Tokens = this.tokeniser.tokenizeToArrayList(string2);
/*     */     
/* 155 */     Set<String> allTokens = new HashSet();
/* 156 */     allTokens.addAll(str1Tokens);
/* 157 */     int termsInString1 = allTokens.size();
/* 158 */     Set<String> secondStringTokens = new HashSet();
/* 159 */     secondStringTokens.addAll(str2Tokens);
/* 160 */     int termsInString2 = secondStringTokens.size();
/*     */     
/*     */ 
/* 163 */     allTokens.addAll(secondStringTokens);
/* 164 */     int commonTerms = termsInString1 + termsInString2 - allTokens.size();
/*     */     
/*     */ 
/* 167 */     return commonTerms / allTokens.size();
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
/* 178 */     return getSimilarity(string1, string2);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/similaritymetrics/JaccardSimilarity.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */