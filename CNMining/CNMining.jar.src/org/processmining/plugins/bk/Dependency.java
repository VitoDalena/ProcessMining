/*    */ package org.processmining.plugins.bk;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Dependency
/*    */   implements Comparable
/*    */ {
/* 11 */   public String from = null;
/* 12 */   public String to = null;
/* 13 */   public double weight = 1.0D;
/*    */   
/*    */   public String getFrom() {
/* 16 */     return this.from;
/*    */   }
/*    */   
/* 19 */   public String getTo() { return this.to; }
/*    */   
/*    */   public double getWeight() {
/* 22 */     return this.weight;
/*    */   }
/*    */   
/*    */   public Dependency(String from, String to, double weight) {
/* 26 */     this.from = from;
/* 27 */     this.to = to;
/* 28 */     this.weight = 1.0D;
/*    */   }
/*    */   
/*    */   public int compareTo(Object d1) {
/* 32 */     if ((this.from.equals(((Dependency)d1).from)) && (this.to.equals(((Dependency)d1).to)))
/* 33 */       return 0;
/* 34 */     if ((this.from.compareTo(((Dependency)d1).from) < 0) || ((this.from.equals(((Dependency)d1).from)) && (this.to.compareTo(((Dependency)d1).to) < 0))) {
/* 35 */       return -1;
/*    */     }
/* 37 */     return 1;
/*    */   }
/*    */   
/*    */   public boolean equals(Object o) {
/* 41 */     if (o.getClass() != getClass()) {
/* 42 */       return false;
/*    */     }
/* 44 */     return compareTo((Dependency)o) == 0;
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 49 */     return "(" + this.from + "," + this.to + "," + this.weight + ")";
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/processmining/plugins/bk/Dependency.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */