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
/*     */ public class MongeElkan
/*     */   extends AbstractStringMetric
/*     */   implements Serializable
/*     */ {
/*  62 */   private final float ESTIMATEDTIMINGCONST = 0.0344F;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   final InterfaceTokeniser tokeniser;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private final AbstractStringMetric internalStringMetric;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getSimilarityExplained(String string1, String string2)
/*     */   {
/*  84 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public MongeElkan()
/*     */   {
/*  91 */     this.tokeniser = new TokeniserWhitespace();
/*  92 */     this.internalStringMetric = new SmithWatermanGotoh();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MongeElkan(InterfaceTokeniser tokeniserToUse)
/*     */   {
/* 101 */     this.tokeniser = tokeniserToUse;
/* 102 */     this.internalStringMetric = new SmithWatermanGotoh();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MongeElkan(InterfaceTokeniser tokeniserToUse, AbstractStringMetric metricToUse)
/*     */   {
/* 112 */     this.tokeniser = tokeniserToUse;
/* 113 */     this.internalStringMetric = metricToUse;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MongeElkan(AbstractStringMetric metricToUse)
/*     */   {
/* 122 */     this.tokeniser = new TokeniserWhitespace();
/* 123 */     this.internalStringMetric = metricToUse;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getShortDescriptionString()
/*     */   {
/* 132 */     return "MongeElkan";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getLongDescriptionString()
/*     */   {
/* 141 */     return "Implements the Monge Elkan algorithm providing an matching style similarity measure between two strings";
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
/* 155 */     float str1Tokens = this.tokeniser.tokenizeToArrayList(string1).size();
/* 156 */     float str2Tokens = this.tokeniser.tokenizeToArrayList(string2).size();
/* 157 */     return ((str1Tokens + str2Tokens) * str1Tokens + (str1Tokens + str2Tokens) * str2Tokens) * 0.0344F;
/*     */   }
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
/* 169 */     ArrayList<String> str1Tokens = this.tokeniser.tokenizeToArrayList(string1);
/* 170 */     ArrayList<String> str2Tokens = this.tokeniser.tokenizeToArrayList(string2);
/*     */     
/* 172 */     float sumMatches = 0.0F;
/*     */     
/* 174 */     for (Object str1Token : str1Tokens) {
/* 175 */       float maxFound = 0.0F;
/* 176 */       for (Object str2Token : str2Tokens) {
/* 177 */         float found = this.internalStringMetric.getSimilarity((String)str1Token, (String)str2Token);
/* 178 */         if (found > maxFound) {
/* 179 */           maxFound = found;
/*     */         }
/*     */       }
/* 182 */       sumMatches += maxFound;
/*     */     }
/* 184 */     return sumMatches / str1Tokens.size();
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
/* 196 */     return getSimilarity(string1, string2);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/similaritymetrics/MongeElkan.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */