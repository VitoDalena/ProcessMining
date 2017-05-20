/*    */ package org.deckfour.uitopia.api.model;
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
/*    */ public enum ActionStatus
/*    */ {
/* 48 */   INVALID("invalid", "Action's parameters are not specified correctly."), 
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 54 */   INCOMPLETE("incomplete", "Action is missing parameters to be executed."), 
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 60 */   EXECUTABLE("executable", "Action is ready to be executed.");
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   private String name;
/*    */   
/*    */ 
/*    */ 
/*    */   private String description;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   private ActionStatus(String name, String description)
/*    */   {
/* 76 */     this.name = name;
/* 77 */     this.description = description;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public String getName()
/*    */   {
/* 84 */     return this.name;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public String getDescription()
/*    */   {
/* 91 */     return this.description;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public String toString()
/*    */   {
/* 98 */     return this.name;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/api/model/ActionStatus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */