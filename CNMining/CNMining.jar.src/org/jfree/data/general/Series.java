/*     */ package org.jfree.data.general;
/*     */ 
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.beans.PropertyChangeSupport;
/*     */ import java.io.Serializable;
/*     */ import javax.swing.event.EventListenerList;
/*     */ import org.jfree.util.ObjectUtilities;
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
/*     */ public abstract class Series
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -6906561437538683581L;
/*     */   private Comparable key;
/*     */   private String description;
/*     */   private EventListenerList listeners;
/*     */   private PropertyChangeSupport propertyChangeSupport;
/*     */   private boolean notify;
/*     */   
/*     */   protected Series(Comparable key)
/*     */   {
/* 101 */     this(key, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Series(Comparable key, String description)
/*     */   {
/* 111 */     if (key == null) {
/* 112 */       throw new IllegalArgumentException("Null 'key' argument.");
/*     */     }
/* 114 */     this.key = key;
/* 115 */     this.description = description;
/* 116 */     this.listeners = new EventListenerList();
/* 117 */     this.propertyChangeSupport = new PropertyChangeSupport(this);
/* 118 */     this.notify = true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Comparable getKey()
/*     */   {
/* 129 */     return this.key;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setKey(Comparable key)
/*     */   {
/* 141 */     if (key == null) {
/* 142 */       throw new IllegalArgumentException("Null 'key' argument.");
/*     */     }
/* 144 */     Comparable old = this.key;
/* 145 */     this.key = key;
/* 146 */     this.propertyChangeSupport.firePropertyChange("Key", old, key);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDescription()
/*     */   {
/* 157 */     return this.description;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDescription(String description)
/*     */   {
/* 169 */     String old = this.description;
/* 170 */     this.description = description;
/* 171 */     this.propertyChangeSupport.firePropertyChange("Description", old, description);
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
/*     */   public boolean getNotify()
/*     */   {
/* 184 */     return this.notify;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setNotify(boolean notify)
/*     */   {
/* 196 */     if (this.notify != notify) {
/* 197 */       this.notify = notify;
/* 198 */       fireSeriesChanged();
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
/*     */   public boolean isEmpty()
/*     */   {
/* 211 */     return getItemCount() == 0;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract int getItemCount();
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
/*     */   public Object clone()
/*     */     throws CloneNotSupportedException
/*     */   {
/* 240 */     Series clone = (Series)super.clone();
/* 241 */     clone.listeners = new EventListenerList();
/* 242 */     clone.propertyChangeSupport = new PropertyChangeSupport(clone);
/* 243 */     return clone;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 255 */     if (obj == this) {
/* 256 */       return true;
/*     */     }
/* 258 */     if (!(obj instanceof Series)) {
/* 259 */       return false;
/*     */     }
/* 261 */     Series that = (Series)obj;
/* 262 */     if (!getKey().equals(that.getKey())) {
/* 263 */       return false;
/*     */     }
/* 265 */     if (!ObjectUtilities.equal(getDescription(), that.getDescription())) {
/* 266 */       return false;
/*     */     }
/* 268 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 278 */     int result = this.key.hashCode();
/* 279 */     result = 29 * result + (this.description != null ? this.description.hashCode() : 0);
/*     */     
/* 281 */     return result;
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
/*     */   public void addChangeListener(SeriesChangeListener listener)
/*     */   {
/* 294 */     this.listeners.add(SeriesChangeListener.class, listener);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeChangeListener(SeriesChangeListener listener)
/*     */   {
/* 304 */     this.listeners.remove(SeriesChangeListener.class, listener);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void fireSeriesChanged()
/*     */   {
/* 312 */     if (this.notify) {
/* 313 */       notifyListeners(new SeriesChangeEvent(this));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void notifyListeners(SeriesChangeEvent event)
/*     */   {
/* 325 */     Object[] listenerList = this.listeners.getListenerList();
/* 326 */     for (int i = listenerList.length - 2; i >= 0; i -= 2) {
/* 327 */       if (listenerList[i] == SeriesChangeListener.class) {
/* 328 */         ((SeriesChangeListener)listenerList[(i + 1)]).seriesChanged(event);
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
/*     */   public void addPropertyChangeListener(PropertyChangeListener listener)
/*     */   {
/* 341 */     this.propertyChangeSupport.addPropertyChangeListener(listener);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removePropertyChangeListener(PropertyChangeListener listener)
/*     */   {
/* 350 */     this.propertyChangeSupport.removePropertyChangeListener(listener);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void firePropertyChange(String property, Object oldValue, Object newValue)
/*     */   {
/* 362 */     this.propertyChangeSupport.firePropertyChange(property, oldValue, newValue);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/general/Series.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */