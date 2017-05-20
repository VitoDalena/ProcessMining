/*    */ package edu.uci.ics.jung.visualization.control;
/*    */ 
/*    */ import java.awt.Cursor;
/*    */ import java.awt.Point;
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
/*    */ public abstract class AbstractGraphMousePlugin
/*    */   implements GraphMousePlugin
/*    */ {
/*    */   protected int modifiers;
/*    */   protected Point down;
/*    */   protected Cursor cursor;
/*    */   
/*    */   public AbstractGraphMousePlugin(int modifiers)
/*    */   {
/* 45 */     this.modifiers = modifiers;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public int getModifiers()
/*    */   {
/* 52 */     return this.modifiers;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void setModifiers(int modifiers)
/*    */   {
/* 59 */     this.modifiers = modifiers;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean checkModifiers(MouseEvent e)
/*    */   {
/* 68 */     return e.getModifiers() == this.modifiers;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public Cursor getCursor()
/*    */   {
/* 75 */     return this.cursor;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void setCursor(Cursor cursor)
/*    */   {
/* 82 */     this.cursor = cursor;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/control/AbstractGraphMousePlugin.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */