/*     */ package org.jfree.data.general;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InvalidObjectException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectInputValidation;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Arrays;
/*     */ import java.util.EventListener;
/*     */ import java.util.List;
/*     */ import javax.swing.event.EventListenerList;
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
/*     */ public abstract class AbstractDataset
/*     */   implements Dataset, Cloneable, Serializable, ObjectInputValidation
/*     */ {
/*     */   private static final long serialVersionUID = 1918768939869230744L;
/*     */   private DatasetGroup group;
/*     */   private transient EventListenerList listenerList;
/*     */   
/*     */   protected AbstractDataset()
/*     */   {
/*  93 */     this.group = new DatasetGroup();
/*  94 */     this.listenerList = new EventListenerList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DatasetGroup getGroup()
/*     */   {
/* 105 */     return this.group;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setGroup(DatasetGroup group)
/*     */   {
/* 116 */     if (group == null) {
/* 117 */       throw new IllegalArgumentException("Null 'group' argument.");
/*     */     }
/* 119 */     this.group = group;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addChangeListener(DatasetChangeListener listener)
/*     */   {
/* 130 */     this.listenerList.add(DatasetChangeListener.class, listener);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeChangeListener(DatasetChangeListener listener)
/*     */   {
/* 142 */     this.listenerList.remove(DatasetChangeListener.class, listener);
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
/*     */   public boolean hasListener(EventListener listener)
/*     */   {
/* 158 */     List list = Arrays.asList(this.listenerList.getListenerList());
/* 159 */     return list.contains(listener);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void fireDatasetChanged()
/*     */   {
/* 168 */     notifyListeners(new DatasetChangeEvent(this, this));
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
/*     */   protected void notifyListeners(DatasetChangeEvent event)
/*     */   {
/* 182 */     Object[] listeners = this.listenerList.getListenerList();
/* 183 */     for (int i = listeners.length - 2; i >= 0; i -= 2) {
/* 184 */       if (listeners[i] == DatasetChangeListener.class) {
/* 185 */         ((DatasetChangeListener)listeners[(i + 1)]).datasetChanged(event);
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
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */     throws CloneNotSupportedException
/*     */   {
/* 203 */     AbstractDataset clone = (AbstractDataset)super.clone();
/* 204 */     clone.listenerList = new EventListenerList();
/* 205 */     return clone;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void writeObject(ObjectOutputStream stream)
/*     */     throws IOException
/*     */   {
/* 216 */     stream.defaultWriteObject();
/*     */   }
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
/* 229 */     stream.defaultReadObject();
/* 230 */     this.listenerList = new EventListenerList();
/* 231 */     stream.registerValidation(this, 10);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void validateObject()
/*     */     throws InvalidObjectException
/*     */   {
/* 253 */     fireDatasetChanged();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/general/AbstractDataset.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */