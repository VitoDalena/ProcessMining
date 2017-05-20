/*     */ package uk.ac.shef.wit.simmetrics.tokenisers;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import uk.ac.shef.wit.simmetrics.wordhandlers.DummyStopTermHandler;
/*     */ import uk.ac.shef.wit.simmetrics.wordhandlers.InterfaceTermHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class TokeniserQGram3Extended
/*     */   implements InterfaceTokeniser, Serializable
/*     */ {
/*  65 */   private InterfaceTermHandler stopWordHandler = new DummyStopTermHandler();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  70 */   private final char QGRAMSTARTPADDING = '#';
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  75 */   private final char QGRAMENDPADDING = '#';
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final String getShortDescriptionString()
/*     */   {
/*  83 */     return "TokeniserQGram3Extended";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final String getDelimiters()
/*     */   {
/*  92 */     return "";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final ArrayList<String> tokenizeToArrayList(String input)
/*     */   {
/* 102 */     ArrayList<String> returnVect = new ArrayList();
/* 103 */     StringBuffer adjustedString = new StringBuffer();
/* 104 */     adjustedString.append('#');
/* 105 */     adjustedString.append('#');
/* 106 */     adjustedString.append(input);
/* 107 */     adjustedString.append('#');
/* 108 */     adjustedString.append('#');
/* 109 */     int curPos = 0;
/* 110 */     int length = adjustedString.length() - 2;
/* 111 */     while (curPos < length) {
/* 112 */       String term = adjustedString.substring(curPos, curPos + 3);
/* 113 */       if (!this.stopWordHandler.isWord(term)) {
/* 114 */         returnVect.add(term);
/*     */       }
/* 116 */       curPos++;
/*     */     }
/*     */     
/* 119 */     return returnVect;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public InterfaceTermHandler getStopWordHandler()
/*     */   {
/* 127 */     return this.stopWordHandler;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setStopWordHandler(InterfaceTermHandler stopWordHandler)
/*     */   {
/* 135 */     this.stopWordHandler = stopWordHandler;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Set<String> tokenizeToSet(String input)
/*     */   {
/* 145 */     Set<String> returnSet = new HashSet();
/* 146 */     returnSet.addAll(tokenizeToArrayList(input));
/* 147 */     return returnSet;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/tokenisers/TokeniserQGram3Extended.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */