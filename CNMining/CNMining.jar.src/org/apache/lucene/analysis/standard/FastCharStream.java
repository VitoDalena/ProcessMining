/*     */ package org.apache.lucene.analysis.standard;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.io.Reader;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class FastCharStream
/*     */   implements CharStream
/*     */ {
/*  27 */   char[] buffer = null;
/*     */   
/*  29 */   int bufferLength = 0;
/*  30 */   int bufferPosition = 0;
/*     */   
/*  32 */   int tokenStart = 0;
/*  33 */   int bufferStart = 0;
/*     */   
/*     */   Reader input;
/*     */   
/*     */   public FastCharStream(Reader r)
/*     */   {
/*  39 */     this.input = r;
/*     */   }
/*     */   
/*     */   public final char readChar() throws IOException {
/*  43 */     if (this.bufferPosition >= this.bufferLength)
/*  44 */       refill();
/*  45 */     return this.buffer[(this.bufferPosition++)];
/*     */   }
/*     */   
/*     */   private final void refill() throws IOException {
/*  49 */     int newPosition = this.bufferLength - this.tokenStart;
/*     */     
/*  51 */     if (this.tokenStart == 0) {
/*  52 */       if (this.buffer == null) {
/*  53 */         this.buffer = new char['ࠀ'];
/*  54 */       } else if (this.bufferLength == this.buffer.length) {
/*  55 */         char[] newBuffer = new char[this.buffer.length * 2];
/*  56 */         System.arraycopy(this.buffer, 0, newBuffer, 0, this.bufferLength);
/*  57 */         this.buffer = newBuffer;
/*     */       }
/*     */     } else {
/*  60 */       System.arraycopy(this.buffer, this.tokenStart, this.buffer, 0, newPosition);
/*     */     }
/*     */     
/*  63 */     this.bufferLength = newPosition;
/*  64 */     this.bufferPosition = newPosition;
/*  65 */     this.bufferStart += this.tokenStart;
/*  66 */     this.tokenStart = 0;
/*     */     
/*  68 */     int charsRead = this.input.read(this.buffer, newPosition, this.buffer.length - newPosition);
/*     */     
/*  70 */     if (charsRead == -1) {
/*  71 */       throw new IOException("read past eof");
/*     */     }
/*  73 */     this.bufferLength += charsRead;
/*     */   }
/*     */   
/*     */   public final char BeginToken() throws IOException {
/*  77 */     this.tokenStart = this.bufferPosition;
/*  78 */     return readChar();
/*     */   }
/*     */   
/*     */   public final void backup(int amount) {
/*  82 */     this.bufferPosition -= amount;
/*     */   }
/*     */   
/*     */   public final String GetImage() {
/*  86 */     return new String(this.buffer, this.tokenStart, this.bufferPosition - this.tokenStart);
/*     */   }
/*     */   
/*     */   public final char[] GetSuffix(int len) {
/*  90 */     char[] value = new char[len];
/*  91 */     System.arraycopy(this.buffer, this.bufferPosition - len, value, 0, len);
/*  92 */     return value;
/*     */   }
/*     */   
/*     */   public final void Done() {
/*     */     try {
/*  97 */       this.input.close();
/*     */     } catch (IOException e) {
/*  99 */       System.err.println("Caught: " + e + "; ignoring.");
/*     */     }
/*     */   }
/*     */   
/*     */   public final int getColumn() {
/* 104 */     return this.bufferStart + this.bufferPosition;
/*     */   }
/*     */   
/* 107 */   public final int getLine() { return 1; }
/*     */   
/*     */   public final int getEndColumn() {
/* 110 */     return this.bufferStart + this.bufferPosition;
/*     */   }
/*     */   
/* 113 */   public final int getEndLine() { return 1; }
/*     */   
/*     */   public final int getBeginColumn() {
/* 116 */     return this.bufferStart + this.tokenStart;
/*     */   }
/*     */   
/* 119 */   public final int getBeginLine() { return 1; }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/analysis/standard/FastCharStream.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */