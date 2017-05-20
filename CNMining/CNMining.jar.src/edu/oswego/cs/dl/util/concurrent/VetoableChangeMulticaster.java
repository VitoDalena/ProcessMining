/*     */ package edu.oswego.cs.dl.util.concurrent;
/*     */ 
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyVetoException;
/*     */ import java.beans.VetoableChangeListener;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.EventObject;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VetoableChangeMulticaster
/*     */   implements Serializable
/*     */ {
/* 119 */   protected transient VetoableChangeListener[] listeners = new VetoableChangeListener[0];
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
/*     */   protected synchronized VetoableChangeMulticaster getChild(String paramString)
/*     */   {
/* 140 */     return this.children == null ? null : (VetoableChangeMulticaster)this.children.get(paramString);
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
/*     */   public VetoableChangeMulticaster(Object paramObject)
/*     */   {
/* 153 */     if (paramObject == null) {
/* 154 */       throw new NullPointerException();
/*     */     }
/*     */     
/* 157 */     this.source = paramObject;
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
/*     */   public synchronized void addVetoableChangeListener(VetoableChangeListener paramVetoableChangeListener)
/*     */   {
/* 171 */     if (paramVetoableChangeListener == null) { throw new NullPointerException();
/*     */     }
/* 173 */     int i = this.listeners.length;
/* 174 */     VetoableChangeListener[] arrayOfVetoableChangeListener = new VetoableChangeListener[i + 1];
/* 175 */     if (i > 0)
/* 176 */       System.arraycopy(this.listeners, 0, arrayOfVetoableChangeListener, 0, i);
/* 177 */     arrayOfVetoableChangeListener[i] = paramVetoableChangeListener;
/* 178 */     this.listeners = arrayOfVetoableChangeListener;
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
/*     */   public synchronized void addVetoableChangeListenerIfAbsent(VetoableChangeListener paramVetoableChangeListener)
/*     */   {
/* 194 */     if (paramVetoableChangeListener == null) { throw new NullPointerException();
/*     */     }
/*     */     
/* 197 */     int i = this.listeners.length;
/* 198 */     VetoableChangeListener[] arrayOfVetoableChangeListener = new VetoableChangeListener[i + 1];
/* 199 */     for (int j = 0; j < i; j++) {
/* 200 */       arrayOfVetoableChangeListener[j] = this.listeners[j];
/* 201 */       if (paramVetoableChangeListener.equals(this.listeners[j]))
/* 202 */         return;
/*     */     }
/* 204 */     arrayOfVetoableChangeListener[i] = paramVetoableChangeListener;
/* 205 */     this.listeners = arrayOfVetoableChangeListener;
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
/*     */   public synchronized void removeVetoableChangeListener(VetoableChangeListener paramVetoableChangeListener)
/*     */   {
/* 223 */     int i = this.listeners.length - 1;
/* 224 */     if ((i < 0) || (paramVetoableChangeListener == null)) { return;
/*     */     }
/*     */     
/*     */ 
/* 228 */     VetoableChangeListener[] arrayOfVetoableChangeListener = new VetoableChangeListener[i];
/*     */     
/* 230 */     for (int j = 0; j < i; j++) {
/* 231 */       if (paramVetoableChangeListener.equals(this.listeners[j]))
/*     */       {
/* 233 */         for (int k = j + 1; k <= i; k++) arrayOfVetoableChangeListener[(k - 1)] = this.listeners[k];
/* 234 */         this.listeners = arrayOfVetoableChangeListener;
/* 235 */         return;
/*     */       }
/*     */       
/* 238 */       arrayOfVetoableChangeListener[j] = this.listeners[j];
/*     */     }
/*     */     
/*     */ 
/* 242 */     if (paramVetoableChangeListener.equals(this.listeners[i])) {
/* 243 */       this.listeners = arrayOfVetoableChangeListener;
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
/*     */   public void addVetoableChangeListener(String paramString, VetoableChangeListener paramVetoableChangeListener)
/*     */   {
/* 262 */     if (paramVetoableChangeListener == null) { throw new NullPointerException();
/*     */     }
/* 264 */     VetoableChangeMulticaster localVetoableChangeMulticaster1 = null;
/*     */     
/* 266 */     synchronized (this) {
/* 267 */       if (this.children == null) {
/* 268 */         this.children = new HashMap();
/*     */       } else {
/* 270 */         localVetoableChangeMulticaster1 = (VetoableChangeMulticaster)this.children.get(paramString);
/*     */       }
/* 272 */       if (localVetoableChangeMulticaster1 == null) {
/* 273 */         localVetoableChangeMulticaster1 = new VetoableChangeMulticaster(this.source);
/* 274 */         this.children.put(paramString, localVetoableChangeMulticaster1);
/*     */       }
/*     */     }
/*     */     
/* 278 */     localVetoableChangeMulticaster1.addVetoableChangeListener(paramVetoableChangeListener);
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
/*     */   public void addVetoableChangeListenerIfAbsent(String paramString, VetoableChangeListener paramVetoableChangeListener)
/*     */   {
/* 295 */     if (paramVetoableChangeListener == null) { throw new NullPointerException();
/*     */     }
/* 297 */     VetoableChangeMulticaster localVetoableChangeMulticaster1 = null;
/*     */     
/* 299 */     synchronized (this) {
/* 300 */       if (this.children == null) {
/* 301 */         this.children = new HashMap();
/*     */       } else {
/* 303 */         localVetoableChangeMulticaster1 = (VetoableChangeMulticaster)this.children.get(paramString);
/*     */       }
/* 305 */       if (localVetoableChangeMulticaster1 == null) {
/* 306 */         localVetoableChangeMulticaster1 = new VetoableChangeMulticaster(this.source);
/* 307 */         this.children.put(paramString, localVetoableChangeMulticaster1);
/*     */       }
/*     */     }
/*     */     
/* 311 */     localVetoableChangeMulticaster1.addVetoableChangeListenerIfAbsent(paramVetoableChangeListener);
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
/*     */   public void removeVetoableChangeListener(String paramString, VetoableChangeListener paramVetoableChangeListener)
/*     */   {
/* 328 */     VetoableChangeMulticaster localVetoableChangeMulticaster = getChild(paramString);
/* 329 */     if (localVetoableChangeMulticaster != null) {
/* 330 */       localVetoableChangeMulticaster.removeVetoableChangeListener(paramVetoableChangeListener);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void multicast(PropertyChangeEvent paramPropertyChangeEvent)
/*     */     throws PropertyVetoException
/*     */   {
/* 342 */     VetoableChangeMulticaster localVetoableChangeMulticaster1 = null;
/*     */     VetoableChangeListener[] arrayOfVetoableChangeListener;
/* 344 */     synchronized (this) {
/* 345 */       arrayOfVetoableChangeListener = this.listeners;
/*     */       
/* 347 */       if ((this.children != null) && (paramPropertyChangeEvent.getPropertyName() != null)) {
/* 348 */         localVetoableChangeMulticaster1 = (VetoableChangeMulticaster)this.children.get(paramPropertyChangeEvent.getPropertyName());
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 353 */     int i = 0;
/*     */     try
/*     */     {
/* 356 */       for (i = 0; i < arrayOfVetoableChangeListener.length; i++) {
/* 357 */         arrayOfVetoableChangeListener[i].vetoableChange(paramPropertyChangeEvent);
/*     */       }
/* 359 */       if (localVetoableChangeMulticaster1 != null) {
/* 360 */         localVetoableChangeMulticaster1.multicast(paramPropertyChangeEvent);
/*     */       }
/*     */       
/*     */ 
/*     */     }
/*     */     catch (PropertyVetoException localPropertyVetoException1)
/*     */     {
/* 367 */       PropertyChangeEvent localPropertyChangeEvent = new PropertyChangeEvent(paramPropertyChangeEvent.getSource(), paramPropertyChangeEvent.getPropertyName(), paramPropertyChangeEvent.getNewValue(), paramPropertyChangeEvent.getOldValue());
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 373 */       int j = i < arrayOfVetoableChangeListener.length ? i : arrayOfVetoableChangeListener.length - 1;
/*     */       
/* 375 */       for (int k = 0; k <= j; k++) {
/*     */         try {
/* 377 */           arrayOfVetoableChangeListener[k].vetoableChange(localPropertyChangeEvent);
/*     */         }
/*     */         catch (PropertyVetoException localPropertyVetoException2) {}
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 385 */       throw localPropertyVetoException1;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void fireVetoableChange(String paramString, Object paramObject1, Object paramObject2)
/*     */     throws PropertyVetoException
/*     */   {
/* 413 */     if ((paramObject1 == null) || (paramObject2 == null) || (!paramObject1.equals(paramObject2))) {
/* 414 */       multicast(new PropertyChangeEvent(this.source, paramString, paramObject1, paramObject2));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void fireVetoableChange(String paramString, int paramInt1, int paramInt2)
/*     */     throws PropertyVetoException
/*     */   {
/* 447 */     if (paramInt1 != paramInt2) {
/* 448 */       multicast(new PropertyChangeEvent(this.source, paramString, new Integer(paramInt1), new Integer(paramInt2)));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void fireVetoableChange(String paramString, boolean paramBoolean1, boolean paramBoolean2)
/*     */     throws PropertyVetoException
/*     */   {
/* 481 */     if (paramBoolean1 != paramBoolean2) {
/* 482 */       multicast(new PropertyChangeEvent(this.source, paramString, new Boolean(paramBoolean1), new Boolean(paramBoolean2)));
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public void fireVetoableChange(PropertyChangeEvent paramPropertyChangeEvent)
/*     */     throws PropertyVetoException
/*     */   {
/* 508 */     Object localObject1 = paramPropertyChangeEvent.getOldValue();
/* 509 */     Object localObject2 = paramPropertyChangeEvent.getNewValue();
/* 510 */     if ((localObject1 == null) || (localObject2 == null) || (!localObject1.equals(localObject2))) {
/* 511 */       multicast(paramPropertyChangeEvent);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean hasListeners(String paramString)
/*     */   {
/*     */     VetoableChangeMulticaster localVetoableChangeMulticaster1;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 526 */     synchronized (this) {
/* 527 */       if (this.listeners.length > 0) {
/* 528 */         boolean bool1 = true;return bool1; }
/* 529 */       if ((paramString == null) || (this.children == null)) {
/* 530 */         boolean bool2 = false;return bool2;
/*     */       }
/* 532 */       localVetoableChangeMulticaster1 = (VetoableChangeMulticaster)this.children.get(paramString);
/* 533 */       if (localVetoableChangeMulticaster1 == null) {
/* 534 */         boolean bool3 = false;return bool3;
/*     */       }
/*     */     }
/*     */     
/* 538 */     return localVetoableChangeMulticaster1.hasListeners(null);
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
/* 550 */     paramObjectOutputStream.defaultWriteObject();
/*     */     
/* 552 */     for (int i = 0; i < this.listeners.length; i++) {
/* 553 */       VetoableChangeListener localVetoableChangeListener = this.listeners[i];
/* 554 */       if ((this.listeners[i] instanceof Serializable)) {
/* 555 */         paramObjectOutputStream.writeObject(this.listeners[i]);
/*     */       }
/*     */     }
/* 558 */     paramObjectOutputStream.writeObject(null);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream paramObjectInputStream) throws ClassNotFoundException, IOException
/*     */   {
/* 563 */     this.listeners = new VetoableChangeListener[0];
/* 564 */     paramObjectInputStream.defaultReadObject();
/*     */     
/*     */     Object localObject;
/* 567 */     while (null != (localObject = paramObjectInputStream.readObject())) {
/* 568 */       addVetoableChangeListener((VetoableChangeListener)localObject);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/VetoableChangeMulticaster.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */