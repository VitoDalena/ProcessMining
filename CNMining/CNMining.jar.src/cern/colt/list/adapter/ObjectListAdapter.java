/*     */ package cern.colt.list.adapter;
/*     */ 
/*     */ import cern.colt.list.ObjectArrayList;
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
/*     */ public class ObjectListAdapter
/*     */   extends java.util.AbstractList
/*     */   implements List
/*     */ {
/*     */   protected ObjectArrayList content;
/*     */   
/*     */   public ObjectListAdapter(ObjectArrayList paramObjectArrayList)
/*     */   {
/*  22 */     this.content = paramObjectArrayList;
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
/*  41 */     this.content.beforeInsert(paramInt, paramObject);
/*  42 */     this.modCount += 1;
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
/*  54 */     return this.content.get(paramInt);
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
/*  69 */     Object localObject = get(paramInt);
/*  70 */     this.content.remove(paramInt);
/*  71 */     this.modCount += 1;
/*  72 */     return localObject;
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
/*  92 */     Object localObject = get(paramInt);
/*  93 */     this.content.set(paramInt, paramObject);
/*  94 */     return localObject;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/* 102 */     return this.content.size();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/list/adapter/ObjectListAdapter.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */