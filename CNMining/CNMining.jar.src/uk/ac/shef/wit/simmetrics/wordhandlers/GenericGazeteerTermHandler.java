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
/*     */ 
/*     */ public final class GenericGazeteerTermHandler
/*     */   implements InterfaceTermHandler
/*     */ {
/*  59 */   private final Set<String> wordSet = new HashSet();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addWord(String termToAdd)
/*     */   {
/*  66 */     this.wordSet.add(termToAdd);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final String getShortDescriptionString()
/*     */   {
/*  75 */     return "GenericGazeteerTermHandler";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeWord(String termToRemove)
/*     */   {
/*  83 */     this.wordSet.remove(termToRemove);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getNumberOfWords()
/*     */   {
/*  91 */     return this.wordSet.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isWord(String termToTest)
/*     */   {
/* 100 */     return this.wordSet.contains(termToTest);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public StringBuffer getWordsAsBuffer()
/*     */   {
/* 108 */     StringBuffer outputBuffer = new StringBuffer();
/* 109 */     outputBuffer.append(this.wordSet.toArray().toString());
/* 110 */     return outputBuffer;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/wordhandlers/GenericGazeteerTermHandler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */