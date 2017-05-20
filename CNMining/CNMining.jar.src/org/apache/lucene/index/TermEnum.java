/*    */ package org.apache.lucene.index;
/*    */ 
/*    */ import java.io.IOException;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class TermEnum
/*    */ {
/*    */   public abstract boolean next()
/*    */     throws IOException;
/*    */   
/*    */   public abstract Term term();
/*    */   
/*    */   public abstract int docFreq();
/*    */   
/*    */   public abstract void close()
/*    */     throws IOException;
/*    */   
/*    */   public boolean skipTo(Term target)
/*    */     throws IOException
/*    */   {
/*    */     do
/*    */     {
/* 56 */       if (!next())
/* 57 */         return false;
/* 58 */     } while (target.compareTo(term()) > 0);
/* 59 */     return true;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/index/TermEnum.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */