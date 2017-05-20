/*    */ package org.apache.lucene.analysis;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.Reader;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class CharTokenizer
/*    */   extends Tokenizer
/*    */ {
/*    */   public CharTokenizer(Reader input)
/*    */   {
/* 24 */     super(input);
/*    */   }
/*    */   
/* 27 */   private int offset = 0; private int bufferIndex = 0; private int dataLen = 0;
/*    */   
/*    */ 
/* 30 */   private final char[] buffer = new char['ÿ'];
/* 31 */   private final char[] ioBuffer = new char['Ѐ'];
/*    */   
/*    */   private static final int MAX_WORD_LEN = 255;
/*    */   
/*    */   private static final int IO_BUFFER_SIZE = 1024;
/*    */   
/*    */ 
/*    */   protected abstract boolean isTokenChar(char paramChar);
/*    */   
/*    */ 
/*    */   protected char normalize(char c)
/*    */   {
/* 43 */     return c;
/*    */   }
/*    */   
/*    */   public final Token next() throws IOException
/*    */   {
/* 48 */     int length = 0;
/* 49 */     int start = this.offset;
/*    */     
/*    */     for (;;)
/*    */     {
/* 53 */       this.offset += 1;
/* 54 */       if (this.bufferIndex >= this.dataLen) {
/* 55 */         this.dataLen = this.input.read(this.ioBuffer);
/* 56 */         this.bufferIndex = 0;
/*    */       }
/*    */       
/* 59 */       if (this.dataLen == -1) {
/* 60 */         if (length <= 0)
/*    */         {
/*    */ 
/* 63 */           return null; }
/*    */       } else {
/* 65 */         char c = this.ioBuffer[(this.bufferIndex++)];
/*    */         
/* 67 */         if (isTokenChar(c))
/*    */         {
/* 69 */           if (length == 0) {
/* 70 */             start = this.offset - 1;
/*    */           }
/* 72 */           this.buffer[(length++)] = normalize(c);
/*    */           
/* 74 */           if (length == 255)
/*    */             break;
/*    */         } else {
/* 77 */           if (length > 0)
/*    */             break;
/*    */         }
/*    */       }
/*    */     }
/* 82 */     return new Token(new String(this.buffer, 0, length), start, start + length);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/analysis/CharTokenizer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */