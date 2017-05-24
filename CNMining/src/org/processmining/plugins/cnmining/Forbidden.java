/*    */ package org.processmining.plugins.cnmining;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Forbidden
/*    */ {
/*    */   private String b;
/*    */   
/*    */ 
/*    */ 
/*    */   private String a;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public Forbidden(String b, String a)
/*    */   {
/* 20 */     this.b = b;
/* 21 */     this.a = a;
/*    */   }
/*    */   
/*    */   public boolean equals(Object obj)
/*    */   {
/* 26 */     if (this == obj)
/* 27 */       return true;
/* 28 */     if (obj == null)
/* 29 */       return false;
/* 30 */     if (getClass() != obj.getClass())
/* 31 */       return false;
/* 32 */     Forbidden other = (Forbidden)obj;
/* 33 */     if (this.a == null) {
/* 34 */       if (other.a != null)
/* 35 */         return false;
/* 36 */     } else if (!this.a.equals(other.a))
/* 37 */       return false;
/* 38 */     if (this.b == null) {
/* 39 */       if (other.b != null)
/* 40 */         return false;
/* 41 */     } else if (!this.b.equals(other.b))
/* 42 */       return false;
/* 43 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public String getA()
/*    */   {
/* 50 */     return this.a;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public String getB()
/*    */   {
/* 57 */     return this.b;
/*    */   }
/*    */   
/*    */   public int hashCode()
/*    */   {
/* 62 */     int prime = 31;
/* 63 */     int result = 1;
/* 64 */     result = 31 * result + (this.a == null ? 0 : this.a.hashCode());
/* 65 */     result = 31 * result + (this.b == null ? 0 : this.b.hashCode());
/* 66 */     return result;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void setA(String a)
/*    */   {
/* 73 */     this.a = a;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void setB(String b)
/*    */   {
/* 80 */     this.b = b;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public String toString()
/*    */   {
/* 89 */     return "[Forbidden] " + this.b + " => " + this.a;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/Dipendenze/CNMining.jar!/org/processmining/plugins/core/Forbidden.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */