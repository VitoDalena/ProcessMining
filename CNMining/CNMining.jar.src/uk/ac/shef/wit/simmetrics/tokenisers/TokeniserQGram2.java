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
/*     */ public final class TokeniserQGram2
/*     */   implements InterfaceTokeniser, Serializable
/*     */ {
/*  64 */   private InterfaceTermHandler stopWordHandler = new DummyStopTermHandler();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final String getShortDescriptionString()
/*     */   {
/*  72 */     return "TokeniserQGram2";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public InterfaceTermHandler getStopWordHandler()
/*     */   {
/*  80 */     return this.stopWordHandler;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setStopWordHandler(InterfaceTermHandler stopWordHandler)
/*     */   {
/*  88 */     this.stopWordHandler = stopWordHandler;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final String getDelimiters()
/*     */   {
/*  97 */     return "";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final ArrayList<String> tokenizeToArrayList(String input)
/*     */   {
/* 107 */     ArrayList<String> returnArrayList = new ArrayList();
/* 108 */     int curPos = 0;
/* 109 */     int length = input.length() - 1;
/* 110 */     while (curPos < length) {
/* 111 */       String term = input.substring(curPos, curPos + 2);
/* 112 */       if (!this.stopWordHandler.isWord(term)) {
/* 113 */         returnArrayList.add(term);
/*     */       }
/* 115 */       curPos++;
/*     */     }
/*     */     
/* 118 */     return returnArrayList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Set<String> tokenizeToSet(String input)
/*     */   {
/* 128 */     Set<String> returnSet = new HashSet();
/* 129 */     returnSet.addAll(tokenizeToArrayList(input));
/* 130 */     return returnSet;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/tokenisers/TokeniserQGram2.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */