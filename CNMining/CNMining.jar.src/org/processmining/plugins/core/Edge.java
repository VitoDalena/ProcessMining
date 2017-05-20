/*    */ package org.processmining.plugins.core;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Edge
/*    */ {
/*    */   private Node x;
/*    */   
/*    */ 
/*    */   private Node y;
/*    */   
/*    */ 
/*    */   private boolean flag;
/*    */   
/*    */ 
/*    */ 
/*    */   public Edge(Node x, Node y)
/*    */   {
/* 19 */     this.x = x;
/* 20 */     this.y = y;
/*    */   }
/*    */   
/*    */   public Edge(Node x, Node y, boolean flag)
/*    */   {
/* 25 */     this.x = x;
/* 26 */     this.y = y;
/* 27 */     this.flag = flag;
/*    */   }
/*    */   
/*    */   public boolean equals(Object obj)
/*    */   {
/* 32 */     if (this == obj)
/* 33 */       return true;
/* 34 */     if (obj == null)
/* 35 */       return false;
/* 36 */     if (getClass() != obj.getClass())
/* 37 */       return false;
/* 38 */     Edge other = (Edge)obj;
/* 39 */     if (this.x == null) {
/* 40 */       if (other.x != null)
/* 41 */         return false;
/* 42 */     } else if (!this.x.equals(other.x))
/* 43 */       return false;
/* 44 */     if (this.y == null) {
/* 45 */       if (other.y != null)
/* 46 */         return false;
/* 47 */     } else if (!this.y.equals(other.y))
/* 48 */       return false;
/* 49 */     return true;
/*    */   }
/*    */   
/*    */   public Node getX() {
/* 53 */     return this.x;
/*    */   }
/*    */   
/*    */   public Node getY() {
/* 57 */     return this.y;
/*    */   }
/*    */   
/*    */   public int hashCode()
/*    */   {
/* 62 */     int prime = 31;
/* 63 */     int result = 1;
/* 64 */     result = 31 * result + (this.flag ? 1231 : 1237);
/* 65 */     result = 31 * result + (this.x == null ? 0 : this.x.hashCode());
/* 66 */     result = 31 * result + (this.y == null ? 0 : this.y.hashCode());
/* 67 */     return result;
/*    */   }
/*    */   
/*    */   public boolean isFlag() {
/* 71 */     return this.flag;
/*    */   }
/*    */   
/*    */   public void setFlag(boolean flag) {
/* 75 */     this.flag = flag;
/*    */   }
/*    */   
/*    */   public void setX(Node x) {
/* 79 */     this.x = x;
/*    */   }
/*    */   
/*    */   public void setY(Node y) {
/* 83 */     this.y = y;
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 88 */     return "Edge [x=" + this.x + ", y=" + this.y + ", flag=" + this.flag + "]";
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/processmining/plugins/core/Edge.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */