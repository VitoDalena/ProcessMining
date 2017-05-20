/*    */ package org.apache.lucene.index;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.ObjectInputStream;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class Term
/*    */   implements Comparable, Serializable
/*    */ {
/*    */   String field;
/*    */   String text;
/*    */   
/* 33 */   public Term(String fld, String txt) { this(fld, txt, true); }
/*    */   
/*    */   Term(String fld, String txt, boolean intern) {
/* 36 */     this.field = (intern ? fld.intern() : fld);
/* 37 */     this.text = txt;
/*    */   }
/*    */   
/*    */   public final String field()
/*    */   {
/* 42 */     return this.field;
/*    */   }
/*    */   
/*    */   public final String text()
/*    */   {
/* 47 */     return this.text;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object o)
/*    */   {
/* 52 */     if (o == null)
/* 53 */       return false;
/* 54 */     Term other = (Term)o;
/* 55 */     return (this.field == other.field) && (this.text.equals(other.text));
/*    */   }
/*    */   
/*    */   public final int hashCode()
/*    */   {
/* 60 */     return this.field.hashCode() + this.text.hashCode();
/*    */   }
/*    */   
/*    */   public int compareTo(Object other) {
/* 64 */     return compareTo((Term)other);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public final int compareTo(Term other)
/*    */   {
/* 73 */     if (this.field == other.field) {
/* 74 */       return this.text.compareTo(other.text);
/*    */     }
/* 76 */     return this.field.compareTo(other.field);
/*    */   }
/*    */   
/*    */   final void set(String fld, String txt)
/*    */   {
/* 81 */     this.field = fld;
/* 82 */     this.text = txt;
/*    */   }
/*    */   
/* 85 */   public final String toString() { return this.field + ":" + this.text; }
/*    */   
/*    */   private void readObject(ObjectInputStream in)
/*    */     throws IOException, ClassNotFoundException
/*    */   {
/* 90 */     in.defaultReadObject();
/* 91 */     this.field = this.field.intern();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/index/Term.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */