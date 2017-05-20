/*     */ package org.deckfour.xes.model.buffered;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Set;
/*     */ import org.deckfour.xes.extension.XExtension;
/*     */ import org.deckfour.xes.model.XAttribute;
/*     */ import org.deckfour.xes.model.XAttributeMap;
/*     */ import org.deckfour.xes.model.XEvent;
/*     */ import org.deckfour.xes.model.XLog;
/*     */ import org.deckfour.xes.model.XTrace;
/*     */ import org.deckfour.xes.model.XVisitor;
/*     */ import org.deckfour.xes.util.XAttributeUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XTraceBufferedImpl
/*     */   implements XTrace
/*     */ {
/*     */   public static final String ID_PREFIX = "TRACE";
/*     */   private XAttributeMap attributes;
/*     */   private XAttributeMapSerializer attributeMapSerializer;
/*     */   private XFastEventList events;
/*     */   
/*     */   public XTraceBufferedImpl(XAttributeMap attributeMap, XAttributeMapSerializer attributeMapSerializer)
/*     */   {
/*  96 */     this.attributeMapSerializer = attributeMapSerializer;
/*  97 */     this.attributes = attributeMap;
/*     */     try {
/*  99 */       this.events = new XFastEventList(attributeMapSerializer);
/*     */     }
/*     */     catch (IOException e) {
/* 102 */       e.printStackTrace();
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
/*     */   public boolean equals(Object o)
/*     */   {
/* 117 */     if (o == this) {
/* 118 */       return true;
/*     */     }
/* 120 */     if (!(o instanceof XTrace)) {
/* 121 */       return false;
/*     */     }
/* 123 */     XTrace other = (XTrace)o;
/*     */     
/*     */ 
/* 126 */     if (size() != other.size()) {
/* 127 */       return false;
/*     */     }
/* 129 */     ListIterator<XEvent> i1 = listIterator();
/* 130 */     ListIterator<XEvent> i2 = other.listIterator();
/* 131 */     while ((i1.hasNext()) && (i2.hasNext())) {
/* 132 */       XEvent e1 = (XEvent)i1.next();
/* 133 */       XEvent e2 = (XEvent)i2.next();
/* 134 */       if (e1 == null ? e2 != null : !e1.equals(e2)) {
/* 135 */         return false;
/*     */       }
/*     */     }
/*     */     
/* 139 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XAttributeMap getAttributes()
/*     */   {
/* 148 */     return this.attributes;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Set<XExtension> getExtensions()
/*     */   {
/* 157 */     return XAttributeUtils.extractExtensions(this.attributes);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAttributes(XAttributeMap attributes)
/*     */   {
/* 166 */     this.attributes = attributes;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean add(XEvent event)
/*     */   {
/*     */     try
/*     */     {
/* 176 */       this.events.append(event);
/*     */     } catch (IOException e) {
/* 178 */       e.printStackTrace();
/* 179 */       return false;
/*     */     }
/* 181 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(int index, XEvent event)
/*     */   {
/*     */     try
/*     */     {
/* 191 */       this.events.insert(event, index);
/*     */     } catch (IOException e) {
/* 193 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean addAll(Collection<? extends XEvent> c)
/*     */   {
/* 203 */     return addAll(this.events.size(), c);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean addAll(int index, Collection<? extends XEvent> c)
/*     */   {
/* 212 */     for (XEvent event : c) {
/*     */       try {
/* 214 */         this.events.insert(event, index);
/*     */       } catch (IOException e) {
/* 216 */         e.printStackTrace();
/*     */       }
/* 218 */       index++;
/*     */     }
/* 220 */     return c.size() > 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/*     */     try
/*     */     {
/* 230 */       this.events.cleanup();
/* 231 */       this.events = new XFastEventList(this.attributeMapSerializer);
/*     */     } catch (IOException e) {
/* 233 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean contains(Object o)
/*     */   {
/* 243 */     for (int i = 0; i < this.events.size(); i++) {
/*     */       try {
/* 245 */         if (this.events.get(i).equals(o)) {
/* 246 */           return true;
/*     */         }
/*     */       } catch (IOException e) {
/* 249 */         e.printStackTrace();
/*     */       }
/*     */     }
/* 252 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean containsAll(Collection<?> c)
/*     */   {
/* 261 */     for (Object e : c) {
/* 262 */       if (!contains(e)) {
/* 263 */         return false;
/*     */       }
/*     */     }
/* 266 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public XEvent get(int index)
/*     */   {
/*     */     try
/*     */     {
/* 276 */       return this.events.get(index);
/*     */     }
/*     */     catch (IOException e) {
/* 279 */       e.printStackTrace(); }
/* 280 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int indexOf(Object o)
/*     */   {
/*     */     try
/*     */     {
/* 291 */       for (int i = 0; i < this.events.size(); i++) {
/* 292 */         if (this.events.get(i).equals(o)) {
/* 293 */           return i;
/*     */         }
/*     */       }
/*     */     } catch (IOException e) {
/* 297 */       e.printStackTrace();
/*     */     }
/* 299 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/* 308 */     return this.events.size() == 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Iterator<XEvent> iterator()
/*     */   {
/* 317 */     return new XTraceIterator(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int lastIndexOf(Object o)
/*     */   {
/* 326 */     int index = -1;
/*     */     try {
/* 328 */       for (int i = 0; i < this.events.size(); i++) {
/* 329 */         if (this.events.get(i).equals(o)) {
/* 330 */           index = i;
/*     */         }
/*     */       }
/*     */     } catch (IOException e) {
/* 334 */       e.printStackTrace();
/*     */     }
/* 336 */     return index;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ListIterator<XEvent> listIterator()
/*     */   {
/* 345 */     return new XTraceIterator(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ListIterator<XEvent> listIterator(int index)
/*     */   {
/* 354 */     return new XTraceIterator(this, index);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean remove(Object o)
/*     */   {
/* 363 */     int index = indexOf(o);
/* 364 */     if (index >= 0) {
/*     */       try {
/* 366 */         this.events.remove(index);
/*     */       } catch (IOException e) {
/* 368 */         e.printStackTrace();
/* 369 */         return false;
/*     */       }
/* 371 */       return true;
/*     */     }
/* 373 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XEvent remove(int index)
/*     */   {
/*     */     try
/*     */     {
/* 384 */       return this.events.remove(index);
/*     */     }
/*     */     catch (IOException e) {
/* 387 */       e.printStackTrace(); }
/* 388 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean removeAll(Collection<?> c)
/*     */   {
/* 398 */     boolean modified = false;
/* 399 */     for (Object o : c) {
/* 400 */       modified |= remove(o);
/*     */     }
/* 402 */     return modified;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean retainAll(Collection<?> c)
/*     */   {
/* 411 */     boolean modified = false;
/* 412 */     for (int i = 0; i < this.events.size(); i++) {
/*     */       try {
/* 414 */         if (!c.contains(this.events.get(i))) {
/* 415 */           this.events.remove(i);
/* 416 */           modified = true;
/* 417 */           i--;
/*     */         }
/*     */       } catch (IOException e) {
/* 420 */         e.printStackTrace();
/*     */       }
/*     */     }
/* 423 */     return modified;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public XEvent set(int index, XEvent event)
/*     */   {
/*     */     try
/*     */     {
/* 433 */       return this.events.replace(event, index);
/*     */     }
/*     */     catch (IOException e) {
/* 436 */       e.printStackTrace(); }
/* 437 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/* 447 */     return this.events.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<XEvent> subList(int fromIndex, int toIndex)
/*     */   {
/* 456 */     XTraceBufferedImpl sublist = (XTraceBufferedImpl)clone();
/* 457 */     clear();
/* 458 */     for (int i = fromIndex; i < toIndex; i++) {
/*     */       try {
/* 460 */         sublist.add(this.events.get(i));
/*     */       } catch (IOException e) {
/* 462 */         e.printStackTrace();
/*     */       }
/*     */     }
/* 465 */     return sublist;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object[] toArray()
/*     */   {
/* 474 */     XEvent[] array = new XEvent[this.events.size()];
/* 475 */     for (int i = 0; i < this.events.size(); i++) {
/*     */       try {
/* 477 */         array[i] = this.events.get(i);
/*     */       } catch (IOException e) {
/* 479 */         e.printStackTrace();
/*     */       }
/*     */     }
/* 482 */     return array;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public <T> T[] toArray(T[] a)
/*     */   {
/* 492 */     if (a.length < this.events.size()) {
/* 493 */       a = (Object[])Array.newInstance(a.getClass().getComponentType(), this.events.size());
/*     */     }
/*     */     
/* 496 */     for (int i = 0; i < this.events.size(); i++) {
/*     */       try {
/* 498 */         a[i] = this.events.get(i);
/*     */       } catch (IOException e) {
/* 500 */         e.printStackTrace();
/*     */       }
/*     */     }
/* 503 */     return a;
/*     */   }
/*     */   
/*     */ 
/*     */   public Object clone()
/*     */   {
/*     */     XTraceBufferedImpl clone;
/*     */     try
/*     */     {
/* 512 */       clone = (XTraceBufferedImpl)super.clone();
/*     */     } catch (CloneNotSupportedException e) {
/* 514 */       e.printStackTrace();
/* 515 */       return null;
/*     */     }
/* 517 */     clone.attributes = ((XAttributeMap)this.attributes.clone());
/* 518 */     clone.events = ((XFastEventList)this.events.clone());
/* 519 */     return clone;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean consolidate()
/*     */   {
/*     */     try
/*     */     {
/* 529 */       return this.events.consolidate();
/*     */     } catch (IOException e) {
/* 531 */       e.printStackTrace(); }
/* 532 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int insertOrdered(XEvent event)
/*     */   {
/*     */     try
/*     */     {
/* 546 */       return this.events.insertOrdered(event);
/*     */     } catch (IOException e) {
/* 548 */       e.printStackTrace(); }
/* 549 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void finalize()
/*     */     throws Throwable
/*     */   {
/* 560 */     super.finalize();
/* 561 */     this.events.cleanup();
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
/*     */   public void accept(XVisitor visitor, XLog log)
/*     */   {
/* 574 */     visitor.visitTracePre(this, log);
/*     */     
/*     */ 
/*     */ 
/* 578 */     for (XAttribute attribute : this.attributes.values()) {
/* 579 */       attribute.accept(visitor, this);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 584 */     for (XEvent event : this) {
/* 585 */       event.accept(visitor, this);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 590 */     visitor.visitTracePost(this, log);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/model/buffered/XTraceBufferedImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */