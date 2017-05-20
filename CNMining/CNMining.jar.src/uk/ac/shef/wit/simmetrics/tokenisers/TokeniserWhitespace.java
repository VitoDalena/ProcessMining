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
/*     */ public final class TokeniserWhitespace
/*     */   implements InterfaceTokeniser, Serializable
/*     */ {
/*  64 */   private InterfaceTermHandler stopWordHandler = new DummyStopTermHandler();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  69 */   private final String delimiters = "\r\n\t  ";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final String getShortDescriptionString()
/*     */   {
/*  77 */     return "TokeniserWhitespace";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final String getDelimiters()
/*     */   {
/*  86 */     return "\r\n\t  ";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public InterfaceTermHandler getStopWordHandler()
/*     */   {
/*  94 */     return this.stopWordHandler;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setStopWordHandler(InterfaceTermHandler stopWordHandler)
/*     */   {
/* 102 */     this.stopWordHandler = stopWordHandler;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final ArrayList<String> tokenizeToArrayList(String input)
/*     */   {
/* 112 */     ArrayList<String> returnVect = new ArrayList();
/* 113 */     int curPos = 0;
/* 114 */     while (curPos < input.length()) {
/* 115 */       char ch = input.charAt(curPos);
/* 116 */       if (Character.isWhitespace(ch)) {
/* 117 */         curPos++;
/*     */       }
/* 119 */       int nextGapPos = input.length();
/*     */       
/* 121 */       for (int i = 0; i < "\r\n\t  ".length(); i++) {
/* 122 */         int testPos = input.indexOf("\r\n\t  ".charAt(i), curPos);
/* 123 */         if ((testPos < nextGapPos) && (testPos != -1)) {
/* 124 */           nextGapPos = testPos;
/*     */         }
/*     */       }
/*     */       
/* 128 */       String term = input.substring(curPos, nextGapPos);
/* 129 */       if ((!this.stopWordHandler.isWord(term)) && (!term.trim().equals(""))) {
/* 130 */         returnVect.add(term);
/*     */       }
/* 132 */       curPos = nextGapPos;
/*     */     }
/*     */     
/* 135 */     return returnVect;
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


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/tokenisers/TokeniserWhitespace.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */