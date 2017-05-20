/*    */ package org.deckfour.uitopia.api.event;
/*    */ 
/*    */ import javax.swing.JComponent;
/*    */ import org.deckfour.uitopia.api.model.Resource;
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
/*    */ public abstract interface TaskListener
/*    */   extends MessageListener
/*    */ {
/*    */   public abstract void updateProgress(double paramDouble);
/*    */   
/*    */   public abstract InteractionResult showConfiguration(String paramString, JComponent paramJComponent);
/*    */   
/*    */   public abstract InteractionResult showWizard(String paramString, boolean paramBoolean1, boolean paramBoolean2, JComponent paramJComponent);
/*    */   
/*    */   public abstract void completed(Resource... paramVarArgs);
/*    */   
/*    */   public static enum InteractionResult
/*    */   {
/* 50 */     CANCEL,  NEXT,  PREV,  CONTINUE,  FINISHED;
/*    */     
/*    */     private InteractionResult() {}
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/api/event/TaskListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */