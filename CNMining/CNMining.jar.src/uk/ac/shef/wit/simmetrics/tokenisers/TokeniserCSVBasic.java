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
/*     */ public final class TokeniserCSVBasic
/*     */   implements InterfaceTokeniser, Serializable
/*     */ {
/*  65 */   private InterfaceTermHandler stopWordHandler = new DummyStopTermHandler();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  70 */   private final String delimiters = ",";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final String getShortDescriptionString()
/*     */   {
/*  78 */     return "TokeniserCSVBasic";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final String getDelimiters()
/*     */   {
/*  87 */     return ",";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public InterfaceTermHandler getStopWordHandler()
/*     */   {
/*  95 */     return this.stopWordHandler;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setStopWordHandler(InterfaceTermHandler stopWordHandler)
/*     */   {
/* 103 */     this.stopWordHandler = stopWordHandler;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final ArrayList<String> tokenizeToArrayList(String input)
/*     */   {
/* 113 */     ArrayList<String> returnArrayList = new ArrayList();
/* 114 */     int curPos = 0;
/* 115 */     while (curPos < input.length()) {
/* 116 */       char ch = input.charAt(curPos);
/* 117 */       if (Character.isWhitespace(ch)) {
/* 118 */         curPos++;
/*     */       }
/* 120 */       int nextGapPos = input.length();
/*     */       
/* 122 */       for (int i = 0; i < ",".length(); i++) {
/* 123 */         int testPos = input.indexOf(",".charAt(i), curPos);
/* 124 */         if ((testPos < nextGapPos) && (testPos != -1)) {
/* 125 */           nextGapPos = testPos;
/*     */         }
/*     */       }
/*     */       
/* 129 */       String term = input.substring(curPos, nextGapPos);
/* 130 */       if ((!this.stopWordHandler.isWord(term)) && (!term.equals(" "))) {
/* 131 */         returnArrayList.add(term);
/*     */       }
/* 133 */       curPos = nextGapPos;
/*     */     }
/*     */     
/* 136 */     return returnArrayList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Set<String> tokenizeToSet(String input)
/*     */   {
/* 146 */     Set<String> returnSet = new HashSet();
/* 147 */     returnSet.addAll(tokenizeToArrayList(input));
/* 148 */     return returnSet;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/tokenisers/TokeniserCSVBasic.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */