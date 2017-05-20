/*    */ package org.processmining.plugins.core;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import java.util.TreeSet;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CustomTreeSet
/*    */ {
/*    */   private TreeSet<TreeSet<String>> supercluster;
/*    */   
/*    */   public CustomTreeSet()
/*    */   {
/* 18 */     this.supercluster = new TreeSet(new Comparator()
/*    */     {
/*    */       public int compare(TreeSet<String> o1, TreeSet<String> o2)
/*    */       {
/* 22 */         if ((o1.containsAll(o2)) && (o2.containsAll(o1)))
/*    */         {
/* 24 */           return 0; }
/* 25 */         if (o1.containsAll(o2)) {
/* 26 */           return 1;
/*    */         }
/* 28 */         return -1;
/*    */       }
/*    */     });
/*    */   }
/*    */   
/*    */   public void add(TreeSet<String> localcluster)
/*    */   {
/* 35 */     boolean aggiungi = true;
/*    */     
/* 37 */     for (TreeSet<String> cluster : this.supercluster)
/*    */     {
/* 39 */       if ((cluster.containsAll(localcluster)) && (localcluster.containsAll(cluster))) {
/* 40 */         aggiungi = false;
/* 41 */         break;
/*    */       }
/*    */     }
/*    */     
/* 45 */     if (aggiungi)
/* 46 */       this.supercluster.add(localcluster);
/*    */   }
/*    */   
/*    */   public TreeSet<TreeSet<String>> getSupercluster() {
/* 50 */     return this.supercluster;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/processmining/plugins/core/CustomTreeSet.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */