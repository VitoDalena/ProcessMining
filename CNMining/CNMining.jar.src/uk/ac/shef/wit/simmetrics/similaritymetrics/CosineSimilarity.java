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
/*     */ public final class CosineSimilarity
/*     */   extends AbstractStringMetric
/*     */   implements Serializable
/*     */ {
/*  64 */   private final float ESTIMATEDTIMINGCONST = 3.8337143E-7F;
/*     */   
/*     */ 
/*     */ 
/*     */   private final InterfaceTokeniser tokeniser;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public CosineSimilarity()
/*     */   {
/*  75 */     this.tokeniser = new TokeniserWhitespace();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CosineSimilarity(InterfaceTokeniser tokeniserToUse)
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
/*  93 */     return "CosineSimilarity";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getLongDescriptionString()
/*     */   {
/* 102 */     return "Implements the Cosine Similarity algorithm providing a similarity measure between two strings from the angular divergence within term based vector space";
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
/* 129 */     float str1Length = string1.length();
/* 130 */     float str2Length = string2.length();
/* 131 */     return (str1Length + str2Length) * ((str1Length + str2Length) * 3.8337143E-7F);
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
/* 142 */     ArrayList<String> str1Tokens = this.tokeniser.tokenizeToArrayList(string1);
/* 143 */     ArrayList<String> str2Tokens = this.tokeniser.tokenizeToArrayList(string2);
/*     */     
/* 145 */     Set<String> allTokens = new HashSet();
/* 146 */     allTokens.addAll(str1Tokens);
/* 147 */     int termsInString1 = allTokens.size();
/* 148 */     Set<String> secondStringTokens = new HashSet();
/* 149 */     secondStringTokens.addAll(str2Tokens);
/* 150 */     int termsInString2 = secondStringTokens.size();
/*     */     
/*     */ 
/* 153 */     allTokens.addAll(secondStringTokens);
/* 154 */     int commonTerms = termsInString1 + termsInString2 - allTokens.size();
/*     */     
/*     */ 
/* 157 */     return commonTerms / (float)(Math.pow(termsInString1, 0.5D) * Math.pow(termsInString2, 0.5D));
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
/* 168 */     return getSimilarity(string1, string2);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/similaritymetrics/CosineSimilarity.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */