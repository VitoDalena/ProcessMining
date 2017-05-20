/*     */ package uk.ac.shef.wit.simmetrics.similaritymetrics.costfunctions;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class SubCost5_3_Minus3
/*     */   extends AbstractSubstitutionCost
/*     */   implements Serializable
/*     */ {
/*     */   private static final int CHAR_EXACT_MATCH_SCORE = 5;
/*     */   private static final int CHAR_APPROX_MATCH_SCORE = 3;
/*     */   private static final int CHAR_MISMATCH_MATCH_SCORE = -3;
/*  84 */   private static final Set[] approx = new Set[7];
/*  85 */   static { approx[0] = new HashSet();
/*  86 */     approx[0].add(Character.valueOf('d'));
/*  87 */     approx[0].add(Character.valueOf('t'));
/*  88 */     approx[1] = new HashSet();
/*  89 */     approx[1].add(Character.valueOf('g'));
/*  90 */     approx[1].add(Character.valueOf('j'));
/*  91 */     approx[2] = new HashSet();
/*  92 */     approx[2].add(Character.valueOf('l'));
/*  93 */     approx[2].add(Character.valueOf('r'));
/*  94 */     approx[3] = new HashSet();
/*  95 */     approx[3].add(Character.valueOf('m'));
/*  96 */     approx[3].add(Character.valueOf('n'));
/*  97 */     approx[4] = new HashSet();
/*  98 */     approx[4].add(Character.valueOf('b'));
/*  99 */     approx[4].add(Character.valueOf('p'));
/* 100 */     approx[4].add(Character.valueOf('v'));
/* 101 */     approx[5] = new HashSet();
/* 102 */     approx[5].add(Character.valueOf('a'));
/* 103 */     approx[5].add(Character.valueOf('e'));
/* 104 */     approx[5].add(Character.valueOf('i'));
/* 105 */     approx[5].add(Character.valueOf('o'));
/* 106 */     approx[5].add(Character.valueOf('u'));
/* 107 */     approx[6] = new HashSet();
/* 108 */     approx[6].add(Character.valueOf(','));
/* 109 */     approx[6].add(Character.valueOf('.'));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final String getShortDescriptionString()
/*     */   {
/* 118 */     return "SubCost5_3_Minus3";
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
/*     */   public final float getCost(String str1, int string1Index, String str2, int string2Index)
/*     */   {
/* 135 */     if ((str1.length() <= string1Index) || (string1Index < 0)) {
/* 136 */       return -3.0F;
/*     */     }
/* 138 */     if ((str2.length() <= string2Index) || (string2Index < 0)) {
/* 139 */       return -3.0F;
/*     */     }
/*     */     
/* 142 */     if (str1.charAt(string1Index) == str2.charAt(string2Index)) {
/* 143 */       return 5.0F;
/*     */     }
/*     */     
/* 146 */     Character si = Character.valueOf(Character.toLowerCase(str1.charAt(string1Index)));
/* 147 */     Character ti = Character.valueOf(Character.toLowerCase(str2.charAt(string2Index)));
/* 148 */     for (Set aApprox : approx) {
/* 149 */       if ((aApprox.contains(si)) && (aApprox.contains(ti)))
/* 150 */         return 3.0F;
/*     */     }
/* 152 */     return -3.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final float getMaxCost()
/*     */   {
/* 162 */     return 5.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final float getMinCost()
/*     */   {
/* 171 */     return -3.0F;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/similaritymetrics/costfunctions/SubCost5_3_Minus3.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */