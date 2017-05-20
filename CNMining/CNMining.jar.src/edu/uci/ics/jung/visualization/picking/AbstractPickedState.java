/*    */ package edu.uci.ics.jung.visualization.picking;
/*    */ 
/*    */ import java.awt.event.ItemEvent;
/*    */ import java.awt.event.ItemListener;
/*    */ import javax.swing.event.EventListenerList;
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
/*    */ public abstract class AbstractPickedState<T>
/*    */   implements PickedState<T>
/*    */ {
/* 25 */   protected EventListenerList listenerList = new EventListenerList();
/*    */   
/*    */   public void addItemListener(ItemListener l) {
/* 28 */     this.listenerList.add(ItemListener.class, l);
/*    */   }
/*    */   
/*    */   public void removeItemListener(ItemListener l)
/*    */   {
/* 33 */     this.listenerList.remove(ItemListener.class, l);
/*    */   }
/*    */   
/*    */   protected void fireItemStateChanged(ItemEvent e) {
/* 37 */     Object[] listeners = this.listenerList.getListenerList();
/* 38 */     for (int i = listeners.length - 2; i >= 0; i -= 2) {
/* 39 */       if (listeners[i] == ItemListener.class) {
/* 40 */         ((ItemListener)listeners[(i + 1)]).itemStateChanged(e);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/picking/AbstractPickedState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */