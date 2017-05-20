/*     */ package org.apache.commons.math.geometry;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class RotationOrder
/*     */ {
/*     */   private RotationOrder(String name, Vector3D a1, Vector3D a2, Vector3D a3)
/*     */   {
/*  44 */     this.name = name;
/*  45 */     this.a1 = a1;
/*  46 */     this.a2 = a2;
/*  47 */     this.a3 = a3;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/*  54 */     return this.name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Vector3D getA1()
/*     */   {
/*  61 */     return this.a1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Vector3D getA2()
/*     */   {
/*  68 */     return this.a2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Vector3D getA3()
/*     */   {
/*  75 */     return this.a3;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  82 */   public static final RotationOrder XYZ = new RotationOrder("XYZ", Vector3D.plusI, Vector3D.plusJ, Vector3D.plusK);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  89 */   public static final RotationOrder XZY = new RotationOrder("XZY", Vector3D.plusI, Vector3D.plusK, Vector3D.plusJ);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  96 */   public static final RotationOrder YXZ = new RotationOrder("YXZ", Vector3D.plusJ, Vector3D.plusI, Vector3D.plusK);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 103 */   public static final RotationOrder YZX = new RotationOrder("YZX", Vector3D.plusJ, Vector3D.plusK, Vector3D.plusI);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 110 */   public static final RotationOrder ZXY = new RotationOrder("ZXY", Vector3D.plusK, Vector3D.plusI, Vector3D.plusJ);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 117 */   public static final RotationOrder ZYX = new RotationOrder("ZYX", Vector3D.plusK, Vector3D.plusJ, Vector3D.plusI);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 124 */   public static final RotationOrder XYX = new RotationOrder("XYX", Vector3D.plusI, Vector3D.plusJ, Vector3D.plusI);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 131 */   public static final RotationOrder XZX = new RotationOrder("XZX", Vector3D.plusI, Vector3D.plusK, Vector3D.plusI);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 138 */   public static final RotationOrder YXY = new RotationOrder("YXY", Vector3D.plusJ, Vector3D.plusI, Vector3D.plusJ);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 145 */   public static final RotationOrder YZY = new RotationOrder("YZY", Vector3D.plusJ, Vector3D.plusK, Vector3D.plusJ);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 152 */   public static final RotationOrder ZXZ = new RotationOrder("ZXZ", Vector3D.plusK, Vector3D.plusI, Vector3D.plusK);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 159 */   public static final RotationOrder ZYZ = new RotationOrder("ZYZ", Vector3D.plusK, Vector3D.plusJ, Vector3D.plusK);
/*     */   private final String name;
/*     */   private final Vector3D a1;
/*     */   private final Vector3D a2;
/*     */   private final Vector3D a3;
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/geometry/RotationOrder.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */