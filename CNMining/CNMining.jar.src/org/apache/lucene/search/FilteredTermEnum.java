/*    */ package org.apache.lucene.search;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.apache.lucene.index.Term;
/*    */ import org.apache.lucene.index.TermEnum;
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
/*    */ public abstract class FilteredTermEnum
/*    */   extends TermEnum
/*    */ {
/* 28 */   private Term currentTerm = null;
/* 29 */   private TermEnum actualEnum = null;
/*    */   
/*    */   public FilteredTermEnum()
/*    */     throws IOException
/*    */   {}
/*    */   
/*    */   protected abstract boolean termCompare(Term paramTerm);
/*    */   
/*    */   protected abstract float difference();
/*    */   
/*    */   protected abstract boolean endEnum();
/*    */   
/*    */   protected void setEnum(TermEnum actualEnum) throws IOException
/*    */   {
/* 43 */     this.actualEnum = actualEnum;
/*    */     
/* 45 */     Term term = actualEnum.term();
/* 46 */     if ((term != null) && (termCompare(term)))
/* 47 */       this.currentTerm = term; else {
/* 48 */       next();
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public int docFreq()
/*    */   {
/* 56 */     if (this.actualEnum == null) return -1;
/* 57 */     return this.actualEnum.docFreq();
/*    */   }
/*    */   
/*    */   public boolean next() throws IOException
/*    */   {
/* 62 */     if (this.actualEnum == null) return false;
/* 63 */     this.currentTerm = null;
/* 64 */     while (this.currentTerm == null) {
/* 65 */       if (endEnum()) return false;
/* 66 */       if (this.actualEnum.next()) {
/* 67 */         Term term = this.actualEnum.term();
/* 68 */         if (termCompare(term)) {
/* 69 */           this.currentTerm = term;
/* 70 */           return true;
/*    */         }
/*    */       } else {
/* 73 */         return false;
/*    */       } }
/* 75 */     this.currentTerm = null;
/* 76 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */   public Term term()
/*    */   {
/* 82 */     return this.currentTerm;
/*    */   }
/*    */   
/*    */   public void close() throws IOException
/*    */   {
/* 87 */     this.actualEnum.close();
/* 88 */     this.currentTerm = null;
/* 89 */     this.actualEnum = null;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/FilteredTermEnum.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */