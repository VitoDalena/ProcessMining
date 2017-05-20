/*    */ package org.processmining.plugins.bk;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DependencySet
/*    */   extends ArrayList<Dependency>
/*    */ {
/*    */   public DependencySet extractStrongDependencies(double threshold)
/*    */   {
/* 14 */     DependencySet s1 = new DependencySet();
/* 15 */     for (Dependency d : this) {
/* 16 */       if (d.weight >= threshold)
/* 17 */         s1.add(d);
/*    */     }
/* 19 */     return s1;
/*    */   }
/*    */   
/* 22 */   public boolean existsDependency(String from, String to) { return contains(new Dependency(from, to, 1.0D)); }
/*    */   
/*    */ 
/* 25 */   public void addDependency(String from, String to, double weight) { add(new Dependency(from, to, weight)); }
/*    */   
/*    */   public String toString() {
/* 28 */     String s = "";
/* 29 */     for (Dependency d : this) {
/* 30 */       s = s + d;
/* 31 */       s = s + "\n";
/*    */     }
/* 33 */     return s;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/processmining/plugins/bk/DependencySet.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */