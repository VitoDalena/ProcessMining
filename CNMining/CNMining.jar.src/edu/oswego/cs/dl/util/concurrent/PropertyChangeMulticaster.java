/*     */ package edu.oswego.cs.dl.util.concurrent;
/*     */ 
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
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
/*     */ public class PropertyChangeMulticaster
/*     */   implements Serializable
/*     */ {
/*  83 */   protected transient PropertyChangeListener[] listeners = new PropertyChangeListener[0];
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected final Object source;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected HashMap children;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected synchronized PropertyChangeMulticaster getChild(String paramString)
/*     */   {
/* 104 */     return this.children == null ? null : (PropertyChangeMulticaster)this.children.get(paramString);
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
/*     */   public PropertyChangeMulticaster(Object paramObject)
/*     */   {
/* 117 */     if (paramObject == null) {
/* 118 */       throw new NullPointerException();
/*     */     }
/*     */     
/* 121 */     this.source = paramObject;
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
/*     */   public synchronized void addPropertyChangeListener(PropertyChangeListener paramPropertyChangeListener)
/*     */   {
/* 136 */     if (paramPropertyChangeListener == null) { throw new NullPointerException();
/*     */     }
/* 138 */     int i = this.listeners.length;
/* 139 */     PropertyChangeListener[] arrayOfPropertyChangeListener = new PropertyChangeListener[i + 1];
/* 140 */     if (i > 0)
/* 141 */       System.arraycopy(this.listeners, 0, arrayOfPropertyChangeListener, 0, i);
/* 142 */     arrayOfPropertyChangeListener[i] = paramPropertyChangeListener;
/* 143 */     this.listeners = arrayOfPropertyChangeListener;
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
/*     */   public synchronized void addPropertyChangeListenerIfAbsent(PropertyChangeListener paramPropertyChangeListener)
/*     */   {
/* 160 */     if (paramPropertyChangeListener == null) { throw new NullPointerException();
/*     */     }
/*     */     
/* 163 */     int i = this.listeners.length;
/* 164 */     PropertyChangeListener[] arrayOfPropertyChangeListener = new PropertyChangeListener[i + 1];
/* 165 */     for (int j = 0; j < i; j++) {
/* 166 */       arrayOfPropertyChangeListener[j] = this.listeners[j];
/* 167 */       if (paramPropertyChangeListener.equals(this.listeners[j]))
/* 168 */         return;
/*     */     }
/* 170 */     arrayOfPropertyChangeListener[i] = paramPropertyChangeListener;
/* 171 */     this.listeners = arrayOfPropertyChangeListener;
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
/*     */   public synchronized void removePropertyChangeListener(PropertyChangeListener paramPropertyChangeListener)
/*     */   {
/* 189 */     int i = this.listeners.length - 1;
/* 190 */     if ((i < 0) || (paramPropertyChangeListener == null)) { return;
/*     */     }
/*     */     
/*     */ 
/* 194 */     PropertyChangeListener[] arrayOfPropertyChangeListener = new PropertyChangeListener[i];
/*     */     
/* 196 */     for (int j = 0; j < i; j++) {
/* 197 */       if (paramPropertyChangeListener.equals(this.listeners[j]))
/*     */       {
/* 199 */         for (int k = j + 1; k <= i; k++) arrayOfPropertyChangeListener[(k - 1)] = this.listeners[k];
/* 200 */         this.listeners = arrayOfPropertyChangeListener;
/* 201 */         return;
/*     */       }
/*     */       
/* 204 */       arrayOfPropertyChangeListener[j] = this.listeners[j];
/*     */     }
/*     */     
/*     */ 
/* 208 */     if (paramPropertyChangeListener.equals(this.listeners[i])) {
/* 209 */       this.listeners = arrayOfPropertyChangeListener;
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
/*     */ 
/*     */ 
/*     */   public void addPropertyChangeListener(String paramString, PropertyChangeListener paramPropertyChangeListener)
/*     */   {
/* 227 */     if (paramPropertyChangeListener == null) { throw new NullPointerException();
/*     */     }
/* 229 */     PropertyChangeMulticaster localPropertyChangeMulticaster1 = null;
/*     */     
/* 231 */     synchronized (this) {
/* 232 */       if (this.children == null) {
/* 233 */         this.children = new HashMap();
/*     */       } else {
/* 235 */         localPropertyChangeMulticaster1 = (PropertyChangeMulticaster)this.children.get(paramString);
/*     */       }
/* 237 */       if (localPropertyChangeMulticaster1 == null) {
/* 238 */         localPropertyChangeMulticaster1 = new PropertyChangeMulticaster(this.source);
/* 239 */         this.children.put(paramString, localPropertyChangeMulticaster1);
/*     */       }
/*     */     }
/*     */     
/* 243 */     localPropertyChangeMulticaster1.addPropertyChangeListener(paramPropertyChangeListener);
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
/*     */   public void addPropertyChangeListenerIfAbsent(String paramString, PropertyChangeListener paramPropertyChangeListener)
/*     */   {
/* 260 */     if (paramPropertyChangeListener == null) { throw new NullPointerException();
/*     */     }
/* 262 */     PropertyChangeMulticaster localPropertyChangeMulticaster1 = null;
/*     */     
/* 264 */     synchronized (this) {
/* 265 */       if (this.children == null) {
/* 266 */         this.children = new HashMap();
/*     */       } else {
/* 268 */         localPropertyChangeMulticaster1 = (PropertyChangeMulticaster)this.children.get(paramString);
/*     */       }
/* 270 */       if (localPropertyChangeMulticaster1 == null) {
/* 271 */         localPropertyChangeMulticaster1 = new PropertyChangeMulticaster(this.source);
/* 272 */         this.children.put(paramString, localPropertyChangeMulticaster1);
/*     */       }
/*     */     }
/*     */     
/* 276 */     localPropertyChangeMulticaster1.addPropertyChangeListenerIfAbsent(paramPropertyChangeListener);
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
/*     */   public void removePropertyChangeListener(String paramString, PropertyChangeListener paramPropertyChangeListener)
/*     */   {
/* 292 */     PropertyChangeMulticaster localPropertyChangeMulticaster = getChild(paramString);
/* 293 */     if (localPropertyChangeMulticaster != null) {
/* 294 */       localPropertyChangeMulticaster.removePropertyChangeListener(paramPropertyChangeListener);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void multicast(PropertyChangeEvent paramPropertyChangeEvent)
/*     */   {
/* 306 */     PropertyChangeMulticaster localPropertyChangeMulticaster1 = null;
/*     */     PropertyChangeListener[] arrayOfPropertyChangeListener;
/* 308 */     synchronized (this) {
/* 309 */       arrayOfPropertyChangeListener = this.listeners;
/*     */       
/* 311 */       if ((this.children != null) && (paramPropertyChangeEvent.getPropertyName() != null)) {
/* 312 */         localPropertyChangeMulticaster1 = (PropertyChangeMulticaster)this.children.get(paramPropertyChangeEvent.getPropertyName());
/*     */       }
/*     */     }
/* 315 */     for (int i = 0; i < arrayOfPropertyChangeListener.length; i++) {
/* 316 */       arrayOfPropertyChangeListener[i].propertyChange(paramPropertyChangeEvent);
/*     */     }
/* 318 */     if (localPropertyChangeMulticaster1 != null) {
/* 319 */       localPropertyChangeMulticaster1.multicast(paramPropertyChangeEvent);
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
/*     */ 
/*     */   public void firePropertyChange(String paramString, Object paramObject1, Object paramObject2)
/*     */   {
/* 336 */     if ((paramObject1 == null) || (paramObject2 == null) || (!paramObject1.equals(paramObject2))) {
/* 337 */       multicast(new PropertyChangeEvent(this.source, paramString, paramObject1, paramObject2));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void firePropertyChange(String paramString, int paramInt1, int paramInt2)
/*     */   {
/* 359 */     if (paramInt1 != paramInt2) {
/* 360 */       multicast(new PropertyChangeEvent(this.source, paramString, new Integer(paramInt1), new Integer(paramInt2)));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void firePropertyChange(String paramString, boolean paramBoolean1, boolean paramBoolean2)
/*     */   {
/* 382 */     if (paramBoolean1 != paramBoolean2) {
/* 383 */       multicast(new PropertyChangeEvent(this.source, paramString, new Boolean(paramBoolean1), new Boolean(paramBoolean2)));
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
/*     */   public void firePropertyChange(PropertyChangeEvent paramPropertyChangeEvent)
/*     */   {
/* 397 */     Object localObject1 = paramPropertyChangeEvent.getOldValue();
/* 398 */     Object localObject2 = paramPropertyChangeEvent.getNewValue();
/* 399 */     if ((localObject1 == null) || (localObject2 == null) || (!localObject1.equals(localObject2))) {
/* 400 */       multicast(paramPropertyChangeEvent);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean hasListeners(String paramString)
/*     */   {
/*     */     PropertyChangeMulticaster localPropertyChangeMulticaster1;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 415 */     synchronized (this) {
/* 416 */       if (this.listeners.length > 0) {
/* 417 */         boolean bool1 = true;return bool1; }
/* 418 */       if ((paramString == null) || (this.children == null)) {
/* 419 */         boolean bool2 = false;return bool2;
/*     */       }
/* 421 */       localPropertyChangeMulticaster1 = (PropertyChangeMulticaster)this.children.get(paramString);
/* 422 */       if (localPropertyChangeMulticaster1 == null) {
/* 423 */         boolean bool3 = false;return bool3;
/*     */       }
/*     */     }
/*     */     
/* 427 */     return localPropertyChangeMulticaster1.hasListeners(null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private synchronized void writeObject(ObjectOutputStream paramObjectOutputStream)
/*     */     throws IOException
/*     */   {
/* 439 */     paramObjectOutputStream.defaultWriteObject();
/*     */     
/* 441 */     for (int i = 0; i < this.listeners.length; i++) {
/* 442 */       PropertyChangeListener localPropertyChangeListener = this.listeners[i];
/* 443 */       if ((this.listeners[i] instanceof Serializable)) {
/* 444 */         paramObjectOutputStream.writeObject(this.listeners[i]);
/*     */       }
/*     */     }
/* 447 */     paramObjectOutputStream.writeObject(null);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream paramObjectInputStream) throws ClassNotFoundException, IOException
/*     */   {
/* 452 */     this.listeners = new PropertyChangeListener[0];
/* 453 */     paramObjectInputStream.defaultReadObject();
/*     */     
/*     */     Object localObject;
/* 456 */     while (null != (localObject = paramObjectInputStream.readObject())) {
/* 457 */       addPropertyChangeListener((PropertyChangeListener)localObject);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/PropertyChangeMulticaster.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */