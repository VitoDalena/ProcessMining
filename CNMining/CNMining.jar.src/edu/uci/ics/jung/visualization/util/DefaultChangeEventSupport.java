/*    */ package edu.uci.ics.jung.visualization.util;
/*    */ 
/*    */ import javax.swing.event.ChangeEvent;
/*    */ import javax.swing.event.ChangeListener;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DefaultChangeEventSupport
/*    */   implements ChangeEventSupport
/*    */ {
/*    */   Object eventSource;
/* 30 */   protected EventListenerList listenerList = new EventListenerList();
/*    */   
/*    */ 
/*    */ 
/*    */   protected transient ChangeEvent changeEvent;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public DefaultChangeEventSupport(Object eventSource)
/*    */   {
/* 41 */     this.eventSource = eventSource;
/*    */   }
/*    */   
/*    */   public void addChangeListener(ChangeListener l) {
/* 45 */     this.listenerList.add(ChangeListener.class, l);
/*    */   }
/*    */   
/*    */   public void removeChangeListener(ChangeListener l) {
/* 49 */     this.listenerList.remove(ChangeListener.class, l);
/*    */   }
/*    */   
/*    */   public ChangeListener[] getChangeListeners() {
/* 53 */     return (ChangeListener[])this.listenerList.getListeners(ChangeListener.class);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void fireStateChanged()
/*    */   {
/* 66 */     Object[] listeners = this.listenerList.getListenerList();
/*    */     
/*    */ 
/* 69 */     for (int i = listeners.length - 2; i >= 0; i -= 2) {
/* 70 */       if (listeners[i] == ChangeListener.class)
/*    */       {
/* 72 */         if (this.changeEvent == null)
/* 73 */           this.changeEvent = new ChangeEvent(this.eventSource);
/* 74 */         ((ChangeListener)listeners[(i + 1)]).stateChanged(this.changeEvent);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/util/DefaultChangeEventSupport.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */