/*    */ package org.deckfour.uitopia.api.ui;
/*    */ 
/*    */ import org.deckfour.uitopia.api.hub.FrameworkHub;
/*    */ import org.deckfour.uitopia.ui.UITopiaController;
/*    */ import org.deckfour.uitopia.ui.UITopiaFrame;
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
/*    */ public class UITopiaBootstrap
/*    */ {
/*    */   public static UITopiaController bootstrap(FrameworkHub<?, ?, ?, ?> framework)
/*    */   {
/* 45 */     UITopiaController controller = new UITopiaController(framework);
/* 46 */     controller.getFrame().setVisible(true);
/* 47 */     return controller;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/api/ui/UITopiaBootstrap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */