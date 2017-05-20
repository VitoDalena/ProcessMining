/*     */ package org.jfree.chart;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
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
/*     */ public class LegendItemCollection
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1365215565589815953L;
/*     */   private List items;
/*     */   
/*     */   public LegendItemCollection()
/*     */   {
/*  69 */     this.items = new ArrayList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(LegendItem item)
/*     */   {
/*  78 */     this.items.add(item);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addAll(LegendItemCollection collection)
/*     */   {
/*  87 */     this.items.addAll(collection.items);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public LegendItem get(int index)
/*     */   {
/*  98 */     return (LegendItem)this.items.get(index);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getItemCount()
/*     */   {
/* 107 */     return this.items.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Iterator iterator()
/*     */   {
/* 116 */     return this.items.iterator();
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
/* 127 */     if (obj == this) {
/* 128 */       return true;
/*     */     }
/* 130 */     if (!(obj instanceof LegendItemCollection)) {
/* 131 */       return false;
/*     */     }
/* 133 */     LegendItemCollection that = (LegendItemCollection)obj;
/* 134 */     if (!this.items.equals(that.items)) {
/* 135 */       return false;
/*     */     }
/* 137 */     return true;
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
/* 149 */     LegendItemCollection clone = (LegendItemCollection)super.clone();
/* 150 */     clone.items = ((List)ObjectUtilities.deepClone(this.items));
/* 151 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/LegendItemCollection.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */