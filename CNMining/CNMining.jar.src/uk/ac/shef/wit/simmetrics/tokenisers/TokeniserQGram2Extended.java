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
/*     */ public final class TokeniserQGram2Extended
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
/*  83 */     return "TokeniserQGram2Extended";
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
/* 102 */     ArrayList<String> returnArrayList = new ArrayList();
/* 103 */     StringBuffer adjustedString = new StringBuffer();
/* 104 */     adjustedString.append('#');
/* 105 */     adjustedString.append(input);
/* 106 */     adjustedString.append('#');
/* 107 */     int curPos = 0;
/* 108 */     int length = adjustedString.length() - 1;
/* 109 */     while (curPos < length) {
/* 110 */       String term = adjustedString.substring(curPos, curPos + 2);
/* 111 */       if (!this.stopWordHandler.isWord(term)) {
/* 112 */         returnArrayList.add(term);
/*     */       }
/* 114 */       curPos++;
/*     */     }
/*     */     
/* 117 */     return returnArrayList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public InterfaceTermHandler getStopWordHandler()
/*     */   {
/* 125 */     return this.stopWordHandler;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setStopWordHandler(InterfaceTermHandler stopWordHandler)
/*     */   {
/* 133 */     this.stopWordHandler = stopWordHandler;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Set<String> tokenizeToSet(String input)
/*     */   {
/* 143 */     Set<String> returnSet = new HashSet();
/* 144 */     returnSet.addAll(tokenizeToArrayList(input));
/* 145 */     return returnSet;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/tokenisers/TokeniserQGram2Extended.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */