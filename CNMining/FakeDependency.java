/*    */ package org.processmining.plugins.core;
/*    */ 
/*    */ import java.util.LinkedList;
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
/*    */ public class FakeDependency
/*    */ {
/*    */   private final int id_attivita_x;
/*    */   private final int id_attivita_y;
/*    */   private LinkedList<Node> nodi_del_path;
/*    */   
/*    */   public FakeDependency(int attivita_x, int attivita_y)
/*    */   {
/* 23 */     this.id_attivita_x = attivita_x;
/* 24 */     this.id_attivita_y = attivita_y;
/*    */   }
/*    */   
/*    */   public FakeDependency(int attivita_x, int attivita_y, LinkedList<Node> lista_nodi)
/*    */   {
/* 29 */     this.id_attivita_x = attivita_x;
/* 30 */     this.id_attivita_y = attivita_y;
/*    */     
/* 32 */     this.nodi_del_path = lista_nodi;
/*    */   }
/*    */   
/*    */   public boolean equals(Object obj)
/*    */   {
/* 37 */     if (this == obj)
/* 38 */       return true;
/* 39 */     if (obj == null)
/* 40 */       return false;
/* 41 */     if (getClass() != obj.getClass())
/* 42 */       return false;
/* 43 */     FakeDependency other = (FakeDependency)obj;
/* 44 */     if (this.id_attivita_x != other.id_attivita_x)
/* 45 */       return false;
/* 46 */     if (this.id_attivita_y != other.id_attivita_y)
/* 47 */       return false;
/* 48 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public int getAttivita_x()
/*    */   {
/* 55 */     return this.id_attivita_x;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public int getAttivita_y()
/*    */   {
/* 64 */     return this.id_attivita_y;
/*    */   }
/*    */   
/*    */   public LinkedList<Node> getNodi_del_path() {
/* 68 */     return this.nodi_del_path;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public int hashCode()
/*    */   {
/* 78 */     int prime = 31;
/* 79 */     int result = 1;
/* 80 */     result = 31 * result + this.id_attivita_x;
/* 81 */     result = 31 * result + this.id_attivita_y;
/* 82 */     return result;
/*    */   }
/*    */   
/*    */   public void setNodi_del_path(LinkedList<Node> nodi_del_path) {
/* 86 */     this.nodi_del_path = nodi_del_path;
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 91 */     return 
/* 92 */       "Attivita_Parallela [id_attivita_x=" + this.id_attivita_x + ", id_attivita_y=" + this.id_attivita_y + "]";
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/processmining/plugins/core/FakeDependency.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */