/*    */ package cern.colt.matrix;
/*    */ 
/*    */ import cern.colt.PersistentObject;
/*    */ import cern.colt.matrix.impl.DenseObjectMatrix3D;
/*    */ import cern.colt.matrix.impl.SparseObjectMatrix3D;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ObjectFactory3D
/*    */   extends PersistentObject
/*    */ {
/* 36 */   public static final ObjectFactory3D dense = new ObjectFactory3D();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/* 41 */   public static final ObjectFactory3D sparse = new ObjectFactory3D();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public ObjectMatrix3D make(Object[][][] paramArrayOfObject)
/*    */   {
/* 59 */     if (this == sparse) return new SparseObjectMatrix3D(paramArrayOfObject);
/* 60 */     return new DenseObjectMatrix3D(paramArrayOfObject);
/*    */   }
/*    */   
/*    */ 
/*    */   public ObjectMatrix3D make(int paramInt1, int paramInt2, int paramInt3)
/*    */   {
/* 66 */     if (this == sparse) return new SparseObjectMatrix3D(paramInt1, paramInt2, paramInt3);
/* 67 */     return new DenseObjectMatrix3D(paramInt1, paramInt2, paramInt3);
/*    */   }
/*    */   
/*    */ 
/*    */   public ObjectMatrix3D make(int paramInt1, int paramInt2, int paramInt3, Object paramObject)
/*    */   {
/* 73 */     return make(paramInt1, paramInt2, paramInt3).assign(paramObject);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/ObjectFactory3D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */