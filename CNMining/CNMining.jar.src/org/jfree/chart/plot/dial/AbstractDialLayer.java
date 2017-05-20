/*     */ package org.jfree.chart.plot.dial;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.util.Arrays;
/*     */ import java.util.EventListener;
/*     */ import java.util.List;
/*     */ import javax.swing.event.EventListenerList;
/*     */ import org.jfree.chart.HashUtilities;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractDialLayer
/*     */   implements DialLayer
/*     */ {
/*     */   private boolean visible;
/*     */   private transient EventListenerList listenerList;
/*     */   
/*     */   protected AbstractDialLayer()
/*     */   {
/*  73 */     this.visible = true;
/*  74 */     this.listenerList = new EventListenerList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isVisible()
/*     */   {
/*  86 */     return this.visible;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setVisible(boolean visible)
/*     */   {
/*  99 */     this.visible = visible;
/* 100 */     notifyListeners(new DialLayerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 111 */     if (obj == this) {
/* 112 */       return true;
/*     */     }
/* 114 */     if (!(obj instanceof AbstractDialLayer)) {
/* 115 */       return false;
/*     */     }
/* 117 */     AbstractDialLayer that = (AbstractDialLayer)obj;
/* 118 */     return this.visible == that.visible;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 127 */     int result = 23;
/* 128 */     result = HashUtilities.hashCode(result, this.visible);
/* 129 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */     throws CloneNotSupportedException
/*     */   {
/* 141 */     AbstractDialLayer clone = (AbstractDialLayer)super.clone();
/*     */     
/* 143 */     clone.listenerList = new EventListenerList();
/* 144 */     return clone;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addChangeListener(DialLayerChangeListener listener)
/*     */   {
/* 155 */     this.listenerList.add(DialLayerChangeListener.class, listener);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeChangeListener(DialLayerChangeListener listener)
/*     */   {
/* 166 */     this.listenerList.remove(DialLayerChangeListener.class, listener);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean hasListener(EventListener listener)
/*     */   {
/* 179 */     List list = Arrays.asList(this.listenerList.getListenerList());
/* 180 */     return list.contains(listener);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void notifyListeners(DialLayerChangeEvent event)
/*     */   {
/* 190 */     Object[] listeners = this.listenerList.getListenerList();
/* 191 */     for (int i = listeners.length - 2; i >= 0; i -= 2) {
/* 192 */       if (listeners[i] == DialLayerChangeListener.class) {
/* 193 */         ((DialLayerChangeListener)listeners[(i + 1)]).dialLayerChanged(event);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void readObject(ObjectInputStream stream)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 209 */     stream.defaultReadObject();
/* 210 */     this.listenerList = new EventListenerList();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/dial/AbstractDialLayer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */