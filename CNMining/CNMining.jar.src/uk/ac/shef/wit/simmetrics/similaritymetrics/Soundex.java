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
/*     */ public final class Soundex
/*     */   extends AbstractStringMetric
/*     */   implements Serializable
/*     */ {
/*  60 */   private final float ESTIMATEDTIMINGCONST = 5.2E-4F;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private final AbstractStringMetric internalStringMetric;
/*     */   
/*     */ 
/*     */ 
/*     */   private static final int SOUNDEXLENGTH = 6;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Soundex()
/*     */   {
/*  76 */     this.internalStringMetric = new JaroWinkler();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Soundex(AbstractStringMetric internalComparisonMetric)
/*     */   {
/*  85 */     this.internalStringMetric = internalComparisonMetric;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getShortDescriptionString()
/*     */   {
/*  94 */     return "Soundex";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getLongDescriptionString()
/*     */   {
/* 103 */     return "Implements the Soundex algorithm providing a similarity measure between two soundex codes";
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
/* 116 */     return null;
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
/* 130 */     float str1Length = string1.length();
/* 131 */     float str2Length = string2.length();
/* 132 */     String testString = "abcdefghijklmnopq";
/* 133 */     return (str1Length + str2Length) * 5.2E-4F + this.internalStringMetric.getSimilarityTimingEstimated("abcdefghijklmnopq".substring(0, 6), "abcdefghijklmnopq".substring(0, 6));
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
/* 144 */     String soundex1 = calcSoundEx(string1, 6);
/* 145 */     String soundex2 = calcSoundEx(string2, 6);
/*     */     
/* 147 */     return this.internalStringMetric.getSimilarity(soundex1, soundex2);
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
/* 159 */     return this.internalStringMetric.getUnNormalisedSimilarity(string1, string2);
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
/*     */   private static String calcSoundEx(String wordString, int soundExLen)
/*     */   {
/* 178 */     if (soundExLen > 10) {
/* 179 */       soundExLen = 10;
/*     */     }
/* 181 */     if (soundExLen < 4) {
/* 182 */       soundExLen = 4;
/*     */     }
/*     */     
/*     */ 
/* 186 */     if (wordString.length() == 0) {
/* 187 */       return "";
/*     */     }
/*     */     
/*     */ 
/* 191 */     wordString = wordString.toUpperCase();
/*     */     
/*     */ 
/*     */ 
/* 195 */     String wordStr = wordString;
/* 196 */     wordStr = wordStr.replaceAll("[^A-Z]", " ");
/* 197 */     wordStr = wordStr.replaceAll("\\s+", "");
/*     */     
/*     */ 
/* 200 */     if (wordStr.length() == 0) {
/* 201 */       return "";
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 207 */     char firstLetter = wordStr.charAt(0);
/*     */     
/*     */ 
/* 210 */     if (wordStr.length() > 25) {
/* 211 */       wordStr = "-" + wordStr.substring(1, 24);
/*     */     } else {
/* 213 */       wordStr = "-" + wordStr.substring(1);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 224 */     wordStr = wordStr.replaceAll("[AEIOUWH]", "0");
/* 225 */     wordStr = wordStr.replaceAll("[BPFV]", "1");
/* 226 */     wordStr = wordStr.replaceAll("[CSKGJQXZ]", "2");
/* 227 */     wordStr = wordStr.replaceAll("[DT]", "3");
/* 228 */     wordStr = wordStr.replaceAll("[L]", "4");
/* 229 */     wordStr = wordStr.replaceAll("[MN]", "5");
/* 230 */     wordStr = wordStr.replaceAll("[R]", "6");
/*     */     
/*     */ 
/* 233 */     int wsLen = wordStr.length();
/* 234 */     char lastChar = '-';
/* 235 */     String tmpStr = "-";
/* 236 */     for (int i = 1; i < wsLen; i++) {
/* 237 */       char curChar = wordStr.charAt(i);
/* 238 */       if (curChar != lastChar) {
/* 239 */         tmpStr = tmpStr + curChar;
/* 240 */         lastChar = curChar;
/*     */       }
/*     */     }
/* 243 */     wordStr = tmpStr;
/* 244 */     wordStr = wordStr.substring(1);
/* 245 */     wordStr = wordStr.replaceAll("0", "");
/* 246 */     wordStr = wordStr + "000000000000000000";
/* 247 */     wordStr = firstLetter + "-" + wordStr;
/* 248 */     wordStr = wordStr.substring(0, soundExLen);
/* 249 */     return wordStr;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/similaritymetrics/Soundex.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */