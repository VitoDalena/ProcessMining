/*    */ package org.deckfour.uitopia.ui;
/*    */ 
/*    */ import org.deckfour.uitopia.api.hub.FrameworkHub;
/*    */ import org.deckfour.uitopia.ui.main.MainView;
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
/*    */ public class UITopiaController
/*    */ {
/*    */   private FrameworkHub<?, ?, ?, ?> hub;
/*    */   private MainView mainView;
/*    */   private UITopiaFrame frame;
/*    */   
/*    */   public UITopiaController(FrameworkHub<?, ?, ?, ?> hub)
/*    */   {
/* 49 */     this.hub = hub;
/* 50 */     this.mainView = new MainView(this);
/* 51 */     this.frame = new UITopiaFrame(this);
/*    */   }
/*    */   
/*    */   public FrameworkHub<?, ?, ?, ?> getFrameworkHub() {
/* 55 */     return this.hub;
/*    */   }
/*    */   
/*    */   public MainView getMainView() {
/* 59 */     return this.mainView;
/*    */   }
/*    */   
/*    */   public UITopiaFrame getFrame() {
/* 63 */     return this.frame;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/UITopiaController.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */