/*     */ package uk.ac.shef.wit.simmetrics.wordhandlers;
/*     */ 
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
/*     */ public final class GenericStopTermHandler
/*     */   implements InterfaceTermHandler
/*     */ {
/*  58 */   private final Set<String> wordSet = new HashSet();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addWord(String termToAdd)
/*     */   {
/*  65 */     this.wordSet.add(termToAdd);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final String getShortDescriptionString()
/*     */   {
/*  74 */     return "GenericStopTermHandler";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeWord(String termToRemove)
/*     */   {
/*  82 */     this.wordSet.remove(termToRemove);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getNumberOfWords()
/*     */   {
/*  90 */     return this.wordSet.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isWord(String termToTest)
/*     */   {
/*  99 */     return this.wordSet.contains(termToTest);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public StringBuffer getWordsAsBuffer()
/*     */   {
/* 107 */     StringBuffer outputBuffer = new StringBuffer();
/* 108 */     outputBuffer.append(this.wordSet.toArray().toString());
/* 109 */     return outputBuffer;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/wordhandlers/GenericStopTermHandler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */