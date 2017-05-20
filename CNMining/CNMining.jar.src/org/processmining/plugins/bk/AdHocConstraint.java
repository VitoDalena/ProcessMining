/*    */ package org.processmining.plugins.bk;
/*    */ 
/*    */ import java.util.TreeSet;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AdHocConstraint
/*    */   implements Comparable
/*    */ {
/*    */   private Integer head;
/*    */   private TreeSet<Integer> body;
/* 16 */   private boolean hasSuperSet = false;
/* 17 */   private boolean hasSubSet = false;
/*    */   
/*    */   AdHocConstraint(Integer head, TreeSet<Integer> body) {
/* 20 */     this.head = head;
/* 21 */     this.body = body;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 25 */     String out = this.head;
/* 26 */     for (Object x : this.body) {
/* 27 */       out = out + " " + ((Integer)x).intValue();
/*    */     }
/* 29 */     return out;
/*    */   }
/*    */   
/*    */   public String toString(String[] taskLabels) {
/* 33 */     String out = taskLabels[this.head.intValue()];
/* 34 */     for (Object x : this.body) {
/* 35 */       out = out + " " + taskLabels[((Integer)x).intValue()];
/*    */     }
/* 37 */     return out;
/*    */   }
/*    */   
/*    */   public Integer getHead() {
/* 41 */     return this.head;
/*    */   }
/*    */   
/*    */   public TreeSet<Integer> getBody() {
/* 45 */     return this.body;
/*    */   }
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
/*    */   public int compareTo(Object o)
/*    */   {
/* 72 */     AdHocConstraint c = (AdHocConstraint)o;
/* 73 */     if (this.head.compareTo(c.getHead()) != 1)
/* 74 */       return this.head.compareTo(c.getHead());
/* 75 */     return this.body.toString().compareTo(c.getBody().toString());
/*    */   }
/*    */   
/*    */   public boolean equals(Object o) {
/* 79 */     AdHocConstraint c = (AdHocConstraint)o;
/* 80 */     return (this.head.equals(c.getHead())) && (this.body.equals(c.getBody()));
/*    */   }
/*    */   
/*    */   public void setHasSuperSet(boolean hasSuperSet) {
/* 84 */     this.hasSuperSet = hasSuperSet;
/*    */   }
/*    */   
/*    */   public boolean hasSuperSet() {
/* 88 */     return this.hasSuperSet;
/*    */   }
/*    */   
/*    */   public void setHasSubSet(boolean hasSubSet) {
/* 92 */     this.hasSubSet = hasSubSet;
/*    */   }
/*    */   
/*    */   public boolean hasSubSet() {
/* 96 */     return this.hasSubSet;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/processmining/plugins/bk/AdHocConstraint.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */