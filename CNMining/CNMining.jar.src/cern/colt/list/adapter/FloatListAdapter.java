/*     */ package cern.colt.list.adapter;
/*     */ 
/*     */ import cern.colt.list.AbstractFloatList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FloatListAdapter
/*     */   extends java.util.AbstractList
/*     */   implements List
/*     */ {
/*     */   protected AbstractFloatList content;
/*     */   
/*     */   public FloatListAdapter(AbstractFloatList paramAbstractFloatList)
/*     */   {
/*  26 */     this.content = paramAbstractFloatList;
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
/*     */   public void add(int paramInt, Object paramObject)
/*     */   {
/*  45 */     this.content.beforeInsert(paramInt, value(paramObject));
/*  46 */     this.modCount += 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object get(int paramInt)
/*     */   {
/*  58 */     return object(this.content.get(paramInt));
/*     */   }
/*     */   
/*     */ 
/*     */   protected static Object object(float paramFloat)
/*     */   {
/*  64 */     return new Float(paramFloat);
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
/*     */   public Object remove(int paramInt)
/*     */   {
/*  79 */     Object localObject = get(paramInt);
/*  80 */     this.content.remove(paramInt);
/*  81 */     this.modCount += 1;
/*  82 */     return localObject;
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
/*     */   public Object set(int paramInt, Object paramObject)
/*     */   {
/* 102 */     Object localObject = get(paramInt);
/* 103 */     this.content.set(paramInt, value(paramObject));
/* 104 */     return localObject;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/* 112 */     return this.content.size();
/*     */   }
/*     */   
/*     */ 
/*     */   protected static float value(Object paramObject)
/*     */   {
/* 118 */     return ((Number)paramObject).floatValue();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/list/adapter/FloatListAdapter.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */