/*     */ package org.jfree.chart.panel;
/*     */ 
/*     */ import javax.swing.event.EventListenerList;
/*     */ import org.jfree.chart.event.OverlayChangeEvent;
/*     */ import org.jfree.chart.event.OverlayChangeListener;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AbstractOverlay
/*     */ {
/*     */   private transient EventListenerList changeListeners;
/*     */   
/*     */   public AbstractOverlay()
/*     */   {
/*  63 */     this.changeListeners = new EventListenerList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addChangeListener(OverlayChangeListener listener)
/*     */   {
/*  74 */     if (listener == null) {
/*  75 */       throw new IllegalArgumentException("Null 'listener' argument.");
/*     */     }
/*  77 */     this.changeListeners.add(OverlayChangeListener.class, listener);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeChangeListener(OverlayChangeListener listener)
/*     */   {
/*  88 */     if (listener == null) {
/*  89 */       throw new IllegalArgumentException("Null 'listener' argument.");
/*     */     }
/*  91 */     this.changeListeners.remove(OverlayChangeListener.class, listener);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void fireOverlayChanged()
/*     */   {
/* 100 */     OverlayChangeEvent event = new OverlayChangeEvent(this);
/* 101 */     notifyListeners(event);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void notifyListeners(OverlayChangeEvent event)
/*     */   {
/* 111 */     Object[] listeners = this.changeListeners.getListenerList();
/* 112 */     for (int i = listeners.length - 2; i >= 0; i -= 2) {
/* 113 */       if (listeners[i] == OverlayChangeListener.class) {
/* 114 */         ((OverlayChangeListener)listeners[(i + 1)]).overlayChanged(event);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/panel/AbstractOverlay.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */