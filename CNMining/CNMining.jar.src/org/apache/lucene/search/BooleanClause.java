/*    */ package org.apache.lucene.search;
/*    */ 
/*    */ import java.io.Serializable;
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
/*    */ public class BooleanClause
/*    */   implements Serializable
/*    */ {
/*    */   public Query query;
/* 25 */   public boolean required = false;
/*    */   
/*    */ 
/* 28 */   public boolean prohibited = false;
/*    */   
/*    */ 
/*    */   public BooleanClause(Query q, boolean r, boolean p)
/*    */   {
/* 33 */     this.query = q;
/* 34 */     this.required = r;
/* 35 */     this.prohibited = p;
/*    */   }
/*    */   
/*    */   public boolean equals(Object o)
/*    */   {
/* 40 */     if (!(o instanceof BooleanClause))
/* 41 */       return false;
/* 42 */     BooleanClause other = (BooleanClause)o;
/* 43 */     return (this.query.equals(other.query)) && (this.required == other.required) && (this.prohibited == other.prohibited);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public int hashCode()
/*    */   {
/* 50 */     return this.query.hashCode() ^ (this.required ? 1 : 0) ^ (this.prohibited ? 2 : 0);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/BooleanClause.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */