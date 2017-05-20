/*    */ package org.apache.lucene.analysis.standard;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.apache.lucene.analysis.Token;
/*    */ import org.apache.lucene.analysis.TokenFilter;
/*    */ import org.apache.lucene.analysis.TokenStream;
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
/*    */ 
/*    */ 
/*    */ public final class StandardFilter
/*    */   extends TokenFilter
/*    */   implements StandardTokenizerConstants
/*    */ {
/*    */   public StandardFilter(TokenStream in)
/*    */   {
/* 29 */     super(in);
/*    */   }
/*    */   
/* 32 */   private static final String APOSTROPHE_TYPE = StandardTokenizerConstants.tokenImage[2];
/* 33 */   private static final String ACRONYM_TYPE = StandardTokenizerConstants.tokenImage[3];
/*    */   
/*    */ 
/*    */ 
/*    */   public final Token next()
/*    */     throws IOException
/*    */   {
/* 40 */     Token t = this.input.next();
/*    */     
/* 42 */     if (t == null) {
/* 43 */       return null;
/*    */     }
/* 45 */     String text = t.termText();
/* 46 */     String type = t.type();
/*    */     
/* 48 */     if ((type == APOSTROPHE_TYPE) && ((text.endsWith("'s")) || (text.endsWith("'S"))))
/*    */     {
/* 50 */       return new Token(text.substring(0, text.length() - 2), t.startOffset(), t.endOffset(), type);
/*    */     }
/*    */     
/*    */ 
/* 54 */     if (type == ACRONYM_TYPE) {
/* 55 */       StringBuffer trimmed = new StringBuffer();
/* 56 */       for (int i = 0; i < text.length(); i++) {
/* 57 */         char c = text.charAt(i);
/* 58 */         if (c != '.')
/* 59 */           trimmed.append(c);
/*    */       }
/* 61 */       return new Token(trimmed.toString(), t.startOffset(), t.endOffset(), type);
/*    */     }
/*    */     
/*    */ 
/* 65 */     return t;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/analysis/standard/StandardFilter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */