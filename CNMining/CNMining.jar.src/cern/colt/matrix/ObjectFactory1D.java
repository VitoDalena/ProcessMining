/*     */ package cern.colt.matrix;
/*     */ 
/*     */ import cern.colt.PersistentObject;
/*     */ import cern.colt.list.AbstractList;
/*     */ import cern.colt.list.ObjectArrayList;
/*     */ import cern.colt.matrix.impl.AbstractMatrix1D;
/*     */ import cern.colt.matrix.impl.DenseObjectMatrix1D;
/*     */ import cern.colt.matrix.impl.SparseObjectMatrix1D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ObjectFactory1D
/*     */   extends PersistentObject
/*     */ {
/*  36 */   public static final ObjectFactory1D dense = new ObjectFactory1D();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  41 */   public static final ObjectFactory1D sparse = new ObjectFactory1D();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectMatrix1D append(ObjectMatrix1D paramObjectMatrix1D1, ObjectMatrix1D paramObjectMatrix1D2)
/*     */   {
/*  52 */     ObjectMatrix1D localObjectMatrix1D = make(paramObjectMatrix1D1.size() + paramObjectMatrix1D2.size());
/*  53 */     localObjectMatrix1D.viewPart(0, paramObjectMatrix1D1.size()).assign(paramObjectMatrix1D1);
/*  54 */     localObjectMatrix1D.viewPart(paramObjectMatrix1D1.size(), paramObjectMatrix1D2.size()).assign(paramObjectMatrix1D2);
/*  55 */     return localObjectMatrix1D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ObjectMatrix1D make(ObjectMatrix1D[] paramArrayOfObjectMatrix1D)
/*     */   {
/*  62 */     if (paramArrayOfObjectMatrix1D.length == 0) { return make(0);
/*     */     }
/*  64 */     int i = 0;
/*  65 */     for (int j = 0; j < paramArrayOfObjectMatrix1D.length; j++) { i += paramArrayOfObjectMatrix1D[j].size();
/*     */     }
/*  67 */     ObjectMatrix1D localObjectMatrix1D = make(i);
/*  68 */     i = 0;
/*  69 */     for (int k = 0; k < paramArrayOfObjectMatrix1D.length; k++) {
/*  70 */       localObjectMatrix1D.viewPart(i, paramArrayOfObjectMatrix1D[k].size()).assign(paramArrayOfObjectMatrix1D[k]);
/*  71 */       i += paramArrayOfObjectMatrix1D[k].size();
/*     */     }
/*     */     
/*  74 */     return localObjectMatrix1D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectMatrix1D make(Object[] paramArrayOfObject)
/*     */   {
/*  83 */     if (this == sparse) return new SparseObjectMatrix1D(paramArrayOfObject);
/*  84 */     return new DenseObjectMatrix1D(paramArrayOfObject);
/*     */   }
/*     */   
/*     */ 
/*     */   public ObjectMatrix1D make(int paramInt)
/*     */   {
/*  90 */     if (this == sparse) return new SparseObjectMatrix1D(paramInt);
/*  91 */     return new DenseObjectMatrix1D(paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */   public ObjectMatrix1D make(int paramInt, Object paramObject)
/*     */   {
/*  97 */     return make(paramInt).assign(paramObject);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectMatrix1D make(ObjectArrayList paramObjectArrayList)
/*     */   {
/* 107 */     int i = paramObjectArrayList.size();
/* 108 */     ObjectMatrix1D localObjectMatrix1D = make(i);
/* 109 */     int j = i; do { localObjectMatrix1D.set(j, paramObjectArrayList.get(j));j--; } while (j >= 0);
/* 110 */     return localObjectMatrix1D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectMatrix1D repeat(ObjectMatrix1D paramObjectMatrix1D, int paramInt)
/*     */   {
/* 122 */     int i = paramObjectMatrix1D.size();
/* 123 */     ObjectMatrix1D localObjectMatrix1D = make(paramInt * i);
/* 124 */     int j = paramInt;
/* 125 */     do { localObjectMatrix1D.viewPart(i * j, i).assign(paramObjectMatrix1D);j--;
/* 124 */     } while (j >= 0);
/*     */     
/*     */ 
/* 127 */     return localObjectMatrix1D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectArrayList toList(ObjectMatrix1D paramObjectMatrix1D)
/*     */   {
/* 137 */     int i = paramObjectMatrix1D.size();
/* 138 */     ObjectArrayList localObjectArrayList = new ObjectArrayList(i);
/* 139 */     localObjectArrayList.setSize(i);
/* 140 */     int j = i; do { localObjectArrayList.set(j, paramObjectMatrix1D.get(j));j--; } while (j >= 0);
/* 141 */     return localObjectArrayList;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/ObjectFactory1D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */