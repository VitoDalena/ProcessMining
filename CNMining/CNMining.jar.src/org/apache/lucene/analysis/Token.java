/*     */ package org.apache.lucene.analysis;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Token
/*     */ {
/*     */   String termText;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   int startOffset;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   int endOffset;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  37 */   String type = "word";
/*     */   
/*  39 */   private int positionIncrement = 1;
/*     */   
/*     */ 
/*     */   public Token(String text, int start, int end)
/*     */   {
/*  44 */     this.termText = text;
/*  45 */     this.startOffset = start;
/*  46 */     this.endOffset = end;
/*     */   }
/*     */   
/*     */   public Token(String text, int start, int end, String typ)
/*     */   {
/*  51 */     this.termText = text;
/*  52 */     this.startOffset = start;
/*  53 */     this.endOffset = end;
/*  54 */     this.type = typ;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPositionIncrement(int positionIncrement)
/*     */   {
/*  83 */     if (positionIncrement < 0) {
/*  84 */       throw new IllegalArgumentException("Increment must be zero or greater: " + positionIncrement);
/*     */     }
/*  86 */     this.positionIncrement = positionIncrement;
/*     */   }
/*     */   
/*     */ 
/*     */   public int getPositionIncrement()
/*     */   {
/*  92 */     return this.positionIncrement;
/*     */   }
/*     */   
/*  95 */   public final String termText() { return this.termText; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final int startOffset()
/*     */   {
/* 103 */     return this.startOffset;
/*     */   }
/*     */   
/*     */   public final int endOffset() {
/* 107 */     return this.endOffset;
/*     */   }
/*     */   
/* 110 */   public final String type() { return this.type; }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/analysis/Token.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */