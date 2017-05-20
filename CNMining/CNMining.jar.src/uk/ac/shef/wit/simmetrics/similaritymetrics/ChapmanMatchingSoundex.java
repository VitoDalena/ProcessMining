/*     */ package uk.ac.shef.wit.simmetrics.similaritymetrics;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import uk.ac.shef.wit.simmetrics.tokenisers.InterfaceTokeniser;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ChapmanMatchingSoundex
/*     */   extends MongeElkan
/*     */   implements Serializable
/*     */ {
/*  60 */   private final float ESTIMATEDTIMINGCONST = 0.026571428F;
/*     */   
/*     */ 
/*     */ 
/*     */   public ChapmanMatchingSoundex()
/*     */   {
/*  66 */     super(new Soundex());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ChapmanMatchingSoundex(InterfaceTokeniser tokeniserToUse)
/*     */   {
/*  75 */     super(tokeniserToUse, new Soundex());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getShortDescriptionString()
/*     */   {
/*  84 */     return "ChapmanMatchingSoundex";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getLongDescriptionString()
/*     */   {
/*  93 */     return "Implements the Chapman Matching Soundex algorithm whereby terms are matched and tested against the standard soundex algorithm - this is intended to provide a better rating for lists of proper names.";
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
/* 106 */     return null;
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
/* 120 */     float str1Tokens = this.tokeniser.tokenizeToArrayList(string1).size();
/* 121 */     float str2Tokens = this.tokeniser.tokenizeToArrayList(string2).size();
/* 122 */     return (this.tokeniser.tokenizeToArrayList(string1).size() + this.tokeniser.tokenizeToArrayList(string2).size()) * ((str1Tokens + str2Tokens) * 0.026571428F);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/similaritymetrics/ChapmanMatchingSoundex.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */