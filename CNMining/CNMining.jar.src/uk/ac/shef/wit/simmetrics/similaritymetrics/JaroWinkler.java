/*     */ package uk.ac.shef.wit.simmetrics.similaritymetrics;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import uk.ac.shef.wit.simmetrics.math.MathFuncs;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class JaroWinkler
/*     */   extends AbstractStringMetric
/*     */   implements Serializable
/*     */ {
/*  61 */   private final float ESTIMATEDTIMINGCONST = 4.342E-5F;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private final AbstractStringMetric internalStringMetric;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final int MINPREFIXTESTLENGTH = 6;
/*     */   
/*     */ 
/*     */ 
/*     */   private static final float PREFIXADUSTMENTSCALE = 0.1F;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public JaroWinkler()
/*     */   {
/*  82 */     this.internalStringMetric = new Jaro();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getShortDescriptionString()
/*     */   {
/*  91 */     return "JaroWinkler";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getLongDescriptionString()
/*     */   {
/* 100 */     return "Implements the Jaro-Winkler algorithm providing a similarity measure between two strings allowing character transpositions to a degree adjusting the weighting for common prefixes";
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
/* 129 */     return str1Length * str2Length * 4.342E-5F;
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
/* 141 */     float dist = this.internalStringMetric.getSimilarity(string1, string2);
/*     */     
/*     */ 
/* 144 */     int prefixLength = getPrefixLength(string1, string2);
/* 145 */     return dist + prefixLength * 0.1F * (1.0F - dist);
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
/* 156 */     return getSimilarity(string1, string2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static int getPrefixLength(String string1, String string2)
/*     */   {
/* 167 */     int n = MathFuncs.min3(6, string1.length(), string2.length());
/*     */     
/* 169 */     for (int i = 0; i < n; i++)
/*     */     {
/* 171 */       if (string1.charAt(i) != string2.charAt(i))
/*     */       {
/* 173 */         return i;
/*     */       }
/*     */     }
/* 176 */     return n;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/similaritymetrics/JaroWinkler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */