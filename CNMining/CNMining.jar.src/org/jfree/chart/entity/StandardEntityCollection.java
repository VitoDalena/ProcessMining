/*     */ package org.jfree.chart.entity;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StandardEntityCollection
/*     */   implements EntityCollection, Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 5384773031184897047L;
/*     */   private List entities;
/*     */   
/*     */   public StandardEntityCollection()
/*     */   {
/*  77 */     this.entities = new ArrayList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getEntityCount()
/*     */   {
/*  86 */     return this.entities.size();
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
/*     */   public ChartEntity getEntity(int index)
/*     */   {
/*  99 */     return (ChartEntity)this.entities.get(index);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 106 */     this.entities.clear();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(ChartEntity entity)
/*     */   {
/* 115 */     if (entity == null) {
/* 116 */       throw new IllegalArgumentException("Null 'entity' argument.");
/*     */     }
/* 118 */     this.entities.add(entity);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addAll(EntityCollection collection)
/*     */   {
/* 128 */     this.entities.addAll(collection.getEntities());
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
/*     */   public ChartEntity getEntity(double x, double y)
/*     */   {
/* 141 */     int entityCount = this.entities.size();
/* 142 */     for (int i = entityCount - 1; i >= 0; i--) {
/* 143 */       ChartEntity entity = (ChartEntity)this.entities.get(i);
/* 144 */       if (entity.getArea().contains(x, y)) {
/* 145 */         return entity;
/*     */       }
/*     */     }
/* 148 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Collection getEntities()
/*     */   {
/* 157 */     return Collections.unmodifiableCollection(this.entities);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Iterator iterator()
/*     */   {
/* 166 */     return this.entities.iterator();
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
/* 177 */     if (obj == this) {
/* 178 */       return true;
/*     */     }
/* 180 */     if ((obj instanceof StandardEntityCollection)) {
/* 181 */       StandardEntityCollection that = (StandardEntityCollection)obj;
/* 182 */       return ObjectUtilities.equal(this.entities, that.entities);
/*     */     }
/* 184 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */     throws CloneNotSupportedException
/*     */   {
/* 195 */     StandardEntityCollection clone = (StandardEntityCollection)super.clone();
/*     */     
/* 197 */     clone.entities = new ArrayList(this.entities.size());
/* 198 */     for (int i = 0; i < this.entities.size(); i++) {
/* 199 */       ChartEntity entity = (ChartEntity)this.entities.get(i);
/* 200 */       clone.entities.add(entity.clone());
/*     */     }
/* 202 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/entity/StandardEntityCollection.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */