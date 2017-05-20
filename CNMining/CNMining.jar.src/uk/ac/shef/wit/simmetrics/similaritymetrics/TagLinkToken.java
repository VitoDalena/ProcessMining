/*     */ package uk.ac.shef.wit.simmetrics.similaritymetrics;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class TagLinkToken
/*     */   extends AbstractStringMetric
/*     */   implements Serializable
/*     */ {
/*     */   private float matched;
/*     */   private float tr;
/*     */   private float tSize;
/*     */   private float uSize;
/*     */   private static final float DEF_TR = 0.3F;
/*     */   private String sA;
/*     */   private String sB;
/*     */   private String tokenT;
/*     */   private int largestIndex;
/*  45 */   private final float ESTIMATEDTIMINGCONST = 1.9796386E-4F;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TagLinkToken()
/*     */   {
/*  53 */     this(0.3F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private TagLinkToken(float tr)
/*     */   {
/*  63 */     this.tr = tr;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float getUnNormalisedSimilarity(String T, String U)
/*     */   {
/*  75 */     return getSimilarity(T, U);
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
/*     */   public float getSimilarity(String T, String U)
/*     */   {
/*  89 */     if (T.equals(U)) {
/*  90 */       this.matched = T.length();
/*  91 */       return 1.0F;
/*     */     }
/*  93 */     this.tSize = T.length();
/*  94 */     this.uSize = U.length();
/*     */     
/*  96 */     if (this.tSize < this.uSize) {
/*  97 */       String tmp1 = T;
/*  98 */       T = U;
/*  99 */       U = tmp1;
/* 100 */       float tmp2 = this.tSize;
/* 101 */       this.tSize = this.uSize;
/* 102 */       this.uSize = tmp2;
/* 103 */       this.tokenT = U;
/*     */     }
/* 105 */     this.tokenT = T;
/* 106 */     ArrayList<Candidates> candidateList = algorithm1(T, U);
/* 107 */     sortList(candidateList);
/* 108 */     float score = getScore(candidateList);
/* 109 */     score = (score / this.tSize + score / this.uSize) / 2.0F;
/* 110 */     return winkler(score, T, U);
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
/*     */   public String getSimilarityExplained(String T, String U)
/*     */   {
/* 123 */     StringBuffer buff = new StringBuffer();
/* 124 */     buff.append("\n****TagLinkToken****\n");
/* 125 */     buff.append("Ti=").append(T).append(", Uj=").append(U).append("\n");
/* 126 */     float score = 0.0F;
/* 127 */     if (T.equals(U)) {
/* 128 */       this.matched = T.length();
/* 129 */       buff.append("Sij=1.0");
/*     */     } else {
/* 131 */       this.tSize = T.length();
/* 132 */       this.uSize = U.length();
/*     */       
/* 134 */       if (this.tSize < this.uSize) {
/* 135 */         String tmp1 = T;
/* 136 */         T = U;
/* 137 */         U = tmp1;
/* 138 */         float tmp2 = this.tSize;
/* 139 */         this.tSize = this.uSize;
/* 140 */         this.uSize = tmp2;
/*     */       }
/* 142 */       ArrayList<Candidates> candidateList = algorithm1(T, U);
/* 143 */       sortList(candidateList);
/* 144 */       buff.append("Common characteres:\n");
/* 145 */       buff.append("Ti\tUj\tSij(Ti,Uj)\n");
/* 146 */       this.matched = 0.0F;
/* 147 */       HashMap<Integer, Object> tMap = new HashMap();
/* 148 */       HashMap<Integer, Object> uMap = new HashMap();
/* 149 */       for (Object aCandidateList : candidateList) {
/* 150 */         Candidates actualCandidates = (Candidates)aCandidateList;
/* 151 */         Integer tPos = Integer.valueOf(actualCandidates.getTPos());
/* 152 */         Integer uPos = Integer.valueOf(actualCandidates.getUPos());
/* 153 */         if ((!tMap.containsKey(tPos)) && (!uMap.containsKey(uPos)))
/*     */         {
/* 155 */           float actualScore = actualCandidates.getScore();
/* 156 */           score += actualScore;
/* 157 */           tMap.put(tPos, null);
/* 158 */           uMap.put(uPos, null);
/* 159 */           buff.append(T.charAt(tPos.intValue())).append("\t").append(U.charAt(uPos.intValue())).append("\t").append(round(actualScore)).append("\n");
/* 160 */           this.matched += 1.0F;
/*     */         }
/*     */       }
/* 163 */       score = (score / this.tSize + score / this.uSize) / 2.0F;
/* 164 */       System.out.println("score " + score);
/* 165 */       buff.append("Sij(T,U)=").append(round(winkler(score, T, U)));
/* 166 */       buff.append("\nMatched characters=").append(this.matched);
/*     */     }
/* 168 */     return buff.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private float getScore(ArrayList<Candidates> candidateList)
/*     */   {
/* 178 */     this.matched = 0.0F;
/* 179 */     this.largestIndex = -1;
/* 180 */     float scoreValue = 0.0F;
/* 181 */     HashMap<Integer, Object> tMap = new HashMap();
/* 182 */     HashMap<Integer, Object> uMap = new HashMap();
/* 183 */     for (Object aCandidateList : candidateList) {
/* 184 */       Candidates actualCandidates = (Candidates)aCandidateList;
/* 185 */       Integer actualTPos = Integer.valueOf(actualCandidates.getTPos());
/* 186 */       Integer actualUPos = Integer.valueOf(actualCandidates.getUPos());
/* 187 */       if ((!tMap.containsKey(actualTPos)) && (!uMap.containsKey(actualUPos)))
/*     */       {
/* 189 */         scoreValue += actualCandidates.getScore();
/* 190 */         tMap.put(actualTPos, null);
/* 191 */         uMap.put(actualUPos, null);
/* 192 */         if (this.largestIndex < actualTPos.intValue()) {
/* 193 */           this.largestIndex = actualTPos.intValue();
/*     */         }
/* 195 */         this.matched += 1.0F;
/*     */       }
/*     */     }
/* 198 */     return scoreValue;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private ArrayList<Candidates> algorithm1(String T, String U)
/*     */   {
/* 210 */     ArrayList<Candidates> candidateList = new ArrayList();
/* 211 */     int bound = (int)(1.0D / this.tr);
/* 212 */     for (int t = 0; t < T.length(); t++) {
/* 213 */       char chT = T.charAt(t);
/* 214 */       float lastTr = -1.0F;
/* 215 */       int u = Math.max(0, t - bound);int flag = 0;
/* 216 */       for (; (u < Math.min(t + bound + 1, U.length())) && (flag == 0); u++) {
/* 217 */         float tr2 = Math.abs(t - u);
/* 218 */         if ((lastTr >= 0.0D) && (lastTr < tr2)) {
/* 219 */           flag = 1;
/*     */         } else {
/* 221 */           char chU = U.charAt(u);
/* 222 */           float charScore = 0.0F;
/* 223 */           if (chT == chU) {
/* 224 */             charScore = 1.0F;
/*     */           }
/* 226 */           if (charScore > 0.0D)
/*     */           {
/*     */ 
/* 229 */             lastTr = tr2;
/*     */             
/* 231 */             charScore -= this.tr * tr2;
/* 232 */             if (charScore == 1.0D) {
/* 233 */               flag = 1;
/*     */             }
/* 235 */             candidateList.add(new Candidates(t, u, charScore));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 240 */     return candidateList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void sortList(ArrayList<Candidates> candidateList)
/*     */   {
/* 249 */     Collections.sort(candidateList, new Comparator() {
/*     */       public int compare(Object o1, Object o2) {
/* 251 */         float scoreT = ((Candidates)o1).getScore();
/* 252 */         float scoreU = ((Candidates)o2).getScore();
/* 253 */         if (scoreU > scoreT) {
/* 254 */           return 1;
/*     */         }
/* 256 */         if (scoreU < scoreT) {
/* 257 */           return -1;
/*     */         }
/* 259 */         return 0;
/*     */       }
/*     */     });
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
/*     */   private float winkler(float score, String T, String U)
/*     */   {
/* 274 */     score += getPrefix(T, U) * 0.1F * (1.0F - score);
/* 275 */     return score;
/*     */   }
/*     */   
/*     */   private int getPrefix(String T, String U) {
/* 279 */     int bound = Math.min(4, Math.min(T.length(), U.length()));
/*     */     
/* 281 */     for (int prefix = 0; prefix < bound; prefix++) {
/* 282 */       if (T.charAt(prefix) != U.charAt(prefix)) {
/*     */         break;
/*     */       }
/*     */     }
/* 286 */     return prefix;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float getMatched()
/*     */   {
/* 296 */     return this.matched;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float getTr()
/*     */   {
/* 305 */     return this.tr;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTreshold(float treshold)
/*     */   {
/* 315 */     this.tr = treshold;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getShortDescriptionString()
/*     */   {
/* 324 */     return "[TagLinkToken_Tr_" + this.tr + "]";
/*     */   }
/*     */   
/*     */   public boolean splitWord(float score) {
/* 328 */     boolean answer = true;
/* 329 */     if (score == 1.0D) {
/* 330 */       answer = false;
/*     */     } else {
/* 332 */       float matchedRate = this.matched / this.uSize;
/* 333 */       int cutUpper = (int)this.tSize - this.largestIndex;
/* 334 */       if ((this.largestIndex < 3) || (cutUpper < 3) || (matchedRate < 0.8D) || (score < 0.7D))
/*     */       {
/* 336 */         answer = false;
/*     */       } else {
/* 338 */         split();
/*     */       }
/*     */     }
/* 341 */     return answer;
/*     */   }
/*     */   
/*     */   private void split() {
/* 345 */     this.sA = "";
/* 346 */     this.sB = "";
/* 347 */     for (int cutIndex = 0; cutIndex < this.tSize; cutIndex++) {
/* 348 */       if (cutIndex <= this.largestIndex) {
/* 349 */         this.sA += this.tokenT.charAt(cutIndex);
/*     */       } else {
/* 351 */         this.sB += this.tokenT.charAt(cutIndex);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public String getSa() {
/* 357 */     return this.sA;
/*     */   }
/*     */   
/*     */   public String getSb() {
/* 361 */     return this.sB;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private float round(float number)
/*     */   {
/* 371 */     int round = (int)(number * 1000.0F);
/* 372 */     float rest = number * 1000.0F - round;
/* 373 */     if (rest >= 0.5D) {
/* 374 */       round++;
/*     */     }
/* 376 */     return round / 1000.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float getSimilarityTimingEstimated(String string1, String string2)
/*     */   {
/* 387 */     float str1Length = string1.length();
/* 388 */     float str2Length = string2.length();
/* 389 */     return str1Length * str2Length * 1.9796386E-4F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getLongDescriptionString()
/*     */   {
/* 398 */     return getShortDescriptionString();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/similaritymetrics/TagLinkToken.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */