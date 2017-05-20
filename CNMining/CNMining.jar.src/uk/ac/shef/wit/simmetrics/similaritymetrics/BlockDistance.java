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
/*     */ public final class BlockDistance
/*     */   extends AbstractStringMetric
/*     */   implements Serializable
/*     */ {
/*  64 */   private final float ESTIMATEDTIMINGCONST = 6.445714E-5F;
/*     */   
/*     */ 
/*     */ 
/*     */   private final InterfaceTokeniser tokeniser;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public BlockDistance()
/*     */   {
/*  75 */     this.tokeniser = new TokeniserWhitespace();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BlockDistance(InterfaceTokeniser tokeniserToUse)
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
/*  93 */     return "BlockDistance";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getLongDescriptionString()
/*     */   {
/* 102 */     return "Implements the Block distance algorithm whereby vector space block distance is used to determine a similarity";
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
/* 131 */     return ((str1Tokens + str2Tokens) * str1Tokens + (str1Tokens + str2Tokens) * str2Tokens) * 6.445714E-5F;
/*     */   }
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
/* 143 */     ArrayList<String> str1Tokens = this.tokeniser.tokenizeToArrayList(string1);
/* 144 */     ArrayList<String> str2Tokens = this.tokeniser.tokenizeToArrayList(string2);
/*     */     
/* 146 */     float totalPossible = str1Tokens.size() + str2Tokens.size();
/*     */     
/* 148 */     float totalDistance = getUnNormalisedSimilarity(string1, string2);
/* 149 */     return (totalPossible - totalDistance) / totalPossible;
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
/* 160 */     ArrayList<String> str1Tokens = this.tokeniser.tokenizeToArrayList(string1);
/* 161 */     ArrayList<String> str2Tokens = this.tokeniser.tokenizeToArrayList(string2);
/*     */     
/* 163 */     Set<Object> allTokens = new HashSet();
/* 164 */     allTokens.addAll(str1Tokens);
/* 165 */     allTokens.addAll(str2Tokens);
/*     */     
/* 167 */     int totalDistance = 0;
/* 168 */     for (Object allToken : allTokens) {
/* 169 */       String token = (String)allToken;
/* 170 */       int countInString1 = 0;
/* 171 */       int countInString2 = 0;
/* 172 */       for (Object str1Token : str1Tokens) {
/* 173 */         String sToken = (String)str1Token;
/* 174 */         if (sToken.equals(token)) {
/* 175 */           countInString1++;
/*     */         }
/*     */       }
/* 178 */       for (Object str2Token : str2Tokens) {
/* 179 */         String sToken = (String)str2Token;
/* 180 */         if (sToken.equals(token)) {
/* 181 */           countInString2++;
/*     */         }
/*     */       }
/* 184 */       if (countInString1 > countInString2) {
/* 185 */         totalDistance += countInString1 - countInString2;
/*     */       } else {
/* 187 */         totalDistance += countInString2 - countInString1;
/*     */       }
/*     */     }
/* 190 */     return totalDistance;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/similaritymetrics/BlockDistance.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */