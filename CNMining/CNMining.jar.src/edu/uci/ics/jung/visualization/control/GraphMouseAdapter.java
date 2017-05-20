/*    */ package edu.uci.ics.jung.visualization.control;
/*    */ 
/*    */ import java.awt.event.MouseAdapter;
/*    */ import java.awt.event.MouseEvent;
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
/*    */ public class GraphMouseAdapter
/*    */   extends MouseAdapter
/*    */ {
/*    */   protected int modifiers;
/*    */   
/*    */   public GraphMouseAdapter(int modifiers)
/*    */   {
/* 28 */     this.modifiers = modifiers;
/*    */   }
/*    */   
/*    */   public int getModifiers() {
/* 32 */     return this.modifiers;
/*    */   }
/*    */   
/*    */   public void setModifiers(int modifiers) {
/* 36 */     this.modifiers = modifiers;
/*    */   }
/*    */   
/*    */   protected boolean checkModifiers(MouseEvent e) {
/* 40 */     return e.getModifiers() == this.modifiers;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/control/GraphMouseAdapter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */