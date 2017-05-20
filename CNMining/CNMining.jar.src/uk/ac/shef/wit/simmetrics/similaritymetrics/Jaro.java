/*     */ package uk.ac.shef.wit.simmetrics.similaritymetrics;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Jaro
/*     */   extends AbstractStringMetric
/*     */   implements Serializable
/*     */ {
/*  60 */   private final float ESTIMATEDTIMINGCONST = 4.12E-5F;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getShortDescriptionString()
/*     */   {
/*  74 */     return "Jaro";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getLongDescriptionString()
/*     */   {
/*  83 */     return "Implements the Jaro algorithm providing a similarity measure between two strings allowing character transpositions to a degree";
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
/*  96 */     return null;
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
/* 110 */     float str1Length = string1.length();
/* 111 */     float str2Length = string2.length();
/* 112 */     return str1Length * str2Length * 4.12E-5F;
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
/* 125 */     int halflen = Math.min(string1.length(), string2.length()) / 2 + 1;
/*     */     
/*     */ 
/* 128 */     StringBuffer common1 = getCommonCharacters(string1, string2, halflen);
/* 129 */     StringBuffer common2 = getCommonCharacters(string2, string1, halflen);
/*     */     
/*     */ 
/* 132 */     if ((common1.length() == 0) || (common2.length() == 0)) {
/* 133 */       return 0.0F;
/*     */     }
/*     */     
/*     */ 
/* 137 */     if (common1.length() != common2.length()) {
/* 138 */       return 0.0F;
/*     */     }
/*     */     
/*     */ 
/* 142 */     int transpositions = 0;
/* 143 */     for (int i = 0; i < common1.length(); i++) {
/* 144 */       if (common1.charAt(i) != common2.charAt(i))
/* 145 */         transpositions++;
/*     */     }
/* 147 */     transpositions = (int)(transpositions / 2.0F);
/*     */     
/*     */ 
/* 150 */     return (common1.length() / string1.length() + common2.length() / string2.length() + (common1.length() - transpositions) / common1.length()) / 3.0F;
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
/*     */   public float getUnNormalisedSimilarity(String string1, String string2)
/*     */   {
/* 164 */     return getSimilarity(string1, string2);
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
/*     */   private static StringBuffer getCommonCharacters(String string1, String string2, int distanceSep)
/*     */   {
/* 179 */     StringBuffer returnCommons = new StringBuffer();
/*     */     
/* 181 */     StringBuffer copy = new StringBuffer(string2);
/*     */     
/* 183 */     for (int i = 0; i < string1.length(); i++) {
/* 184 */       char ch = string1.charAt(i);
/*     */       
/* 186 */       boolean foundIt = false;
/*     */       
/* 188 */       for (int j = Math.max(0, i - distanceSep); (!foundIt) && (j < Math.min(i + distanceSep, string2.length())); j++)
/*     */       {
/* 190 */         if (copy.charAt(j) == ch) {
/* 191 */           foundIt = true;
/*     */           
/* 193 */           returnCommons.append(ch);
/*     */           
/* 195 */           copy.setCharAt(j, '\000');
/*     */         }
/*     */       }
/*     */     }
/* 199 */     return returnCommons;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/similaritymetrics/Jaro.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */