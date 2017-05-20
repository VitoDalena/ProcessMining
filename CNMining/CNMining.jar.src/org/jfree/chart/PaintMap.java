/*     */ package org.jfree.chart;
/*     */ 
/*     */ import java.awt.Paint;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.util.PaintUtilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PaintMap
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   static final long serialVersionUID = -4639833772123069274L;
/*     */   private transient Map store;
/*     */   
/*     */   public PaintMap()
/*     */   {
/*  80 */     this.store = new HashMap();
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
/*     */   public Paint getPaint(Comparable key)
/*     */   {
/*  95 */     if (key == null) {
/*  96 */       throw new IllegalArgumentException("Null 'key' argument.");
/*     */     }
/*  98 */     return (Paint)this.store.get(key);
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
/*     */   public boolean containsKey(Comparable key)
/*     */   {
/* 111 */     return this.store.containsKey(key);
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
/*     */   public void put(Comparable key, Paint paint)
/*     */   {
/* 125 */     if (key == null) {
/* 126 */       throw new IllegalArgumentException("Null 'key' argument.");
/*     */     }
/* 128 */     this.store.put(key, paint);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 135 */     this.store.clear();
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
/* 146 */     if (obj == this) {
/* 147 */       return true;
/*     */     }
/* 149 */     if (!(obj instanceof PaintMap)) {
/* 150 */       return false;
/*     */     }
/* 152 */     PaintMap that = (PaintMap)obj;
/* 153 */     if (this.store.size() != that.store.size()) {
/* 154 */       return false;
/*     */     }
/* 156 */     Set keys = this.store.keySet();
/* 157 */     Iterator iterator = keys.iterator();
/* 158 */     while (iterator.hasNext()) {
/* 159 */       Comparable key = (Comparable)iterator.next();
/* 160 */       Paint p1 = getPaint(key);
/* 161 */       Paint p2 = that.getPaint(key);
/* 162 */       if (!PaintUtilities.equal(p1, p2)) {
/* 163 */         return false;
/*     */       }
/*     */     }
/* 166 */     return true;
/*     */   }
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
/* 179 */     return super.clone();
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
/* 190 */     stream.defaultWriteObject();
/* 191 */     stream.writeInt(this.store.size());
/* 192 */     Set keys = this.store.keySet();
/* 193 */     Iterator iterator = keys.iterator();
/* 194 */     while (iterator.hasNext()) {
/* 195 */       Comparable key = (Comparable)iterator.next();
/* 196 */       stream.writeObject(key);
/* 197 */       Paint paint = getPaint(key);
/* 198 */       SerialUtilities.writePaint(paint, stream);
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
/*     */   private void readObject(ObjectInputStream stream)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 212 */     stream.defaultReadObject();
/* 213 */     this.store = new HashMap();
/* 214 */     int keyCount = stream.readInt();
/* 215 */     for (int i = 0; i < keyCount; i++) {
/* 216 */       Comparable key = (Comparable)stream.readObject();
/* 217 */       Paint paint = SerialUtilities.readPaint(stream);
/* 218 */       this.store.put(key, paint);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/PaintMap.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */