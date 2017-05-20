/*     */ package org.jfree.chart;
/*     */ 
/*     */ import java.awt.Stroke;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ import org.jfree.io.SerialUtilities;
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
/*     */ public class StrokeMap
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   static final long serialVersionUID = -8148916785963525169L;
/*     */   private transient Map store;
/*     */   
/*     */   public StrokeMap()
/*     */   {
/*  78 */     this.store = new TreeMap();
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
/*     */   public Stroke getStroke(Comparable key)
/*     */   {
/*  93 */     if (key == null) {
/*  94 */       throw new IllegalArgumentException("Null 'key' argument.");
/*     */     }
/*  96 */     return (Stroke)this.store.get(key);
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
/* 109 */     return this.store.containsKey(key);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void put(Comparable key, Stroke stroke)
/*     */   {
/* 120 */     if (key == null) {
/* 121 */       throw new IllegalArgumentException("Null 'key' argument.");
/*     */     }
/* 123 */     this.store.put(key, stroke);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 130 */     this.store.clear();
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
/* 141 */     if (obj == this) {
/* 142 */       return true;
/*     */     }
/* 144 */     if (!(obj instanceof StrokeMap)) {
/* 145 */       return false;
/*     */     }
/* 147 */     StrokeMap that = (StrokeMap)obj;
/* 148 */     if (this.store.size() != that.store.size()) {
/* 149 */       return false;
/*     */     }
/* 151 */     Set keys = this.store.keySet();
/* 152 */     Iterator iterator = keys.iterator();
/* 153 */     while (iterator.hasNext()) {
/* 154 */       Comparable key = (Comparable)iterator.next();
/* 155 */       Stroke s1 = getStroke(key);
/* 156 */       Stroke s2 = that.getStroke(key);
/* 157 */       if (!ObjectUtilities.equal(s1, s2)) {
/* 158 */         return false;
/*     */       }
/*     */     }
/* 161 */     return true;
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
/* 174 */     return super.clone();
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
/* 185 */     stream.defaultWriteObject();
/* 186 */     stream.writeInt(this.store.size());
/* 187 */     Set keys = this.store.keySet();
/* 188 */     Iterator iterator = keys.iterator();
/* 189 */     while (iterator.hasNext()) {
/* 190 */       Comparable key = (Comparable)iterator.next();
/* 191 */       stream.writeObject(key);
/* 192 */       Stroke stroke = getStroke(key);
/* 193 */       SerialUtilities.writeStroke(stroke, stream);
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
/* 207 */     stream.defaultReadObject();
/* 208 */     this.store = new TreeMap();
/* 209 */     int keyCount = stream.readInt();
/* 210 */     for (int i = 0; i < keyCount; i++) {
/* 211 */       Comparable key = (Comparable)stream.readObject();
/* 212 */       Stroke stroke = SerialUtilities.readStroke(stream);
/* 213 */       this.store.put(key, stroke);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/StrokeMap.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */