/*     */ package org.processmining.plugins.cnmining;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.LinkedList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Constraint
/*     */ {
/*  14 */   private final LinkedList<String> head_list = new LinkedList();
/*  15 */   private final LinkedList<String> body_list = new LinkedList();
/*     */   
/*     */ 
/*     */   private boolean positive_constraint;
/*     */   
/*     */ 
/*  21 */   private boolean pathConstraint = false;
/*     */   
/*     */ 
/*     */ 
/*     */   public Constraint() {}
/*     */   
/*     */ 
/*     */   public Constraint(boolean type, boolean pathConstraint)
/*     */   {
/*  30 */     this.positive_constraint = type;
/*  31 */     this.pathConstraint = pathConstraint;
/*     */   }
/*     */   
/*     */   public void addBody(String body)
/*     */   {
/*  36 */     this.body_list.add(body);
/*     */   }
/*     */   
/*     */   public void addHead(String head) {
/*  40 */     this.head_list.add(head);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj)
/*     */   {
/*  45 */     if (this == obj)
/*  46 */       return true;
/*  47 */     if (obj == null)
/*  48 */       return false;
/*  49 */     if (getClass() != obj.getClass())
/*  50 */       return false;
/*  51 */     Constraint other = (Constraint)obj;
/*  52 */     Collections.sort(this.body_list);
/*  53 */     Collections.sort(other.body_list);
/*  54 */     if (!this.body_list.equals(other.body_list))
/*  55 */       return false;
/*  56 */     if (this.head_list == null) {
/*  57 */       if (other.head_list != null)
/*  58 */         return false;
/*  59 */     } else if (!this.head_list.equals(other.head_list))
/*  60 */       return false;
/*  61 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public LinkedList<String> getBodyList()
/*     */   {
/*  68 */     return this.body_list;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public LinkedList<String> getHeadList()
/*     */   {
/*  75 */     return this.head_list;
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/*  80 */     int prime = 31;
/*  81 */     int result = 1;
/*  82 */     result = 31 * result + (this.head_list == null ? 0 : this.head_list.hashCode());
/*  83 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isPathConstraint()
/*     */   {
/*  90 */     return this.pathConstraint;
/*     */   }
/*     */   
/*     */   public boolean isPositiveConstraint() {
/*  94 */     return this.positive_constraint;
/*     */   }
/*     */   
/*     */   public void setConstraintType(boolean flag) {
/*  98 */     this.positive_constraint = flag;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPathConstraint(boolean pathConstraint)
/*     */   {
/* 110 */     this.pathConstraint = pathConstraint;
/*     */   }
/*     */   
/*     */   public void setType(boolean type) {
/* 114 */     this.positive_constraint = type;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 125 */     String result = "[CONSTRAINT]= ";
/*     */     
/* 127 */     for (String body : this.body_list) {
/* 128 */       result = result + body + " ; ";
/*     */     }
/* 130 */     result = result.substring(0, result.length() - 2) + "=> " + this.head_list;
/* 131 */     result = result + (this.pathConstraint ? " PATH " : " EDGE ");
/*     */     
/* 133 */     result = result + (this.positive_constraint ? "POSITIVO" : "NEGATO");
/*     */     
/* 135 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/Dipendenze/CNMining.jar!/org/processmining/plugins/core/Constraint.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */