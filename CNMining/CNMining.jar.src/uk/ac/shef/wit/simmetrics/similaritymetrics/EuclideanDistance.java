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
/*     */ public final class EuclideanDistance
/*     */   extends AbstractStringMetric
/*     */   implements Serializable
/*     */ {
/*  64 */   private final float ESTIMATEDTIMINGCONST = 7.4457144E-5F;
/*     */   
/*     */ 
/*     */ 
/*     */   private final InterfaceTokeniser tokeniser;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public EuclideanDistance()
/*     */   {
/*  75 */     this.tokeniser = new TokeniserWhitespace();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public EuclideanDistance(InterfaceTokeniser tokeniserToUse)
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
/*  93 */     return "EuclideanDistance";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getLongDescriptionString()
/*     */   {
/* 102 */     return "Implements the Euclidean Distancey algorithm providing a similarity measure between two stringsusing the vector space of combined terms as the dimensions";
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
/* 131 */     return ((str1Tokens + str2Tokens) * str1Tokens + (str1Tokens + str2Tokens) * str2Tokens) * 7.4457144E-5F;
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
/*     */   public float getSimilarity(String string1, String string2)
/*     */   {
/* 145 */     ArrayList<String> str1Tokens = this.tokeniser.tokenizeToArrayList(string1);
/* 146 */     ArrayList<String> str2Tokens = this.tokeniser.tokenizeToArrayList(string2);
/* 147 */     float totalPossible = (float)Math.sqrt(str1Tokens.size() * str1Tokens.size() + str2Tokens.size() * str2Tokens.size());
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
/* 163 */     Set<String> allTokens = new HashSet();
/* 164 */     allTokens.addAll(str1Tokens);
/* 165 */     allTokens.addAll(str2Tokens);
/*     */     
/* 167 */     float totalDistance = 0.0F;
/* 168 */     for (String token : allTokens) {
/* 169 */       int countInString1 = 0;
/* 170 */       int countInString2 = 0;
/* 171 */       for (String sToken : str1Tokens) {
/* 172 */         if (sToken.equals(token)) {
/* 173 */           countInString1++;
/*     */         }
/*     */       }
/* 176 */       for (String sToken : str2Tokens) {
/* 177 */         if (sToken.equals(token)) {
/* 178 */           countInString2++;
/*     */         }
/*     */       }
/*     */       
/* 182 */       totalDistance += (countInString1 - countInString2) * (countInString1 - countInString2);
/*     */     }
/*     */     
/* 185 */     totalDistance = (float)Math.sqrt(totalDistance);
/* 186 */     return totalDistance;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float getEuclidDistance(String string1, String string2)
/*     */   {
/* 197 */     ArrayList<String> str1Tokens = this.tokeniser.tokenizeToArrayList(string1);
/* 198 */     ArrayList<String> str2Tokens = this.tokeniser.tokenizeToArrayList(string2);
/*     */     
/* 200 */     Set<String> allTokens = new HashSet();
/* 201 */     allTokens.addAll(str1Tokens);
/* 202 */     allTokens.addAll(str2Tokens);
/*     */     
/* 204 */     float totalDistance = 0.0F;
/* 205 */     for (String token : allTokens) {
/* 206 */       int countInString1 = 0;
/* 207 */       int countInString2 = 0;
/* 208 */       for (String sToken : str1Tokens) {
/* 209 */         if (sToken.equals(token)) {
/* 210 */           countInString1++;
/*     */         }
/*     */       }
/* 213 */       for (String sToken : str2Tokens) {
/* 214 */         if (sToken.equals(token)) {
/* 215 */           countInString2++;
/*     */         }
/*     */       }
/*     */       
/* 219 */       totalDistance += (countInString1 - countInString2) * (countInString1 - countInString2);
/*     */     }
/*     */     
/* 222 */     return (float)Math.sqrt(totalDistance);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/similaritymetrics/EuclideanDistance.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */