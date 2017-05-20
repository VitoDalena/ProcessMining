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
/*     */ public final class MatchingCoefficient
/*     */   extends AbstractStringMetric
/*     */   implements Serializable
/*     */ {
/*  62 */   private final float ESTIMATEDTIMINGCONST = 2.0E-4F;
/*     */   
/*     */ 
/*     */ 
/*     */   private final InterfaceTokeniser tokeniser;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public MatchingCoefficient()
/*     */   {
/*  73 */     this.tokeniser = new TokeniserWhitespace();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MatchingCoefficient(InterfaceTokeniser tokeniserToUse)
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
/*  91 */     return "MatchingCoefficient";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getLongDescriptionString()
/*     */   {
/* 100 */     return "Implements the Matching Coefficient algorithm providing a similarity measure between two strings";
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
/* 127 */     float str1Tokens = this.tokeniser.tokenizeToArrayList(string1).size();
/* 128 */     float str2Tokens = this.tokeniser.tokenizeToArrayList(string2).size();
/* 129 */     return str2Tokens * str1Tokens * 2.0E-4F;
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
/* 143 */     int totalPossible = Math.max(str1Tokens.size(), str2Tokens.size());
/* 144 */     return getUnNormalisedSimilarity(string1, string2) / totalPossible;
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
/* 155 */     ArrayList<String> str1Tokens = this.tokeniser.tokenizeToArrayList(string1);
/* 156 */     ArrayList<String> str2Tokens = this.tokeniser.tokenizeToArrayList(string2);
/*     */     
/* 158 */     int totalFound = 0;
/* 159 */     for (Object str1Token : str1Tokens) {
/* 160 */       String sToken = (String)str1Token;
/* 161 */       boolean found = false;
/* 162 */       for (Object str2Token : str2Tokens) {
/* 163 */         String tToken = (String)str2Token;
/* 164 */         if (sToken.equals(tToken)) {
/* 165 */           found = true;
/*     */         }
/*     */       }
/* 168 */       if (found) {
/* 169 */         totalFound++;
/*     */       }
/*     */     }
/* 172 */     return totalFound;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/similaritymetrics/MatchingCoefficient.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */