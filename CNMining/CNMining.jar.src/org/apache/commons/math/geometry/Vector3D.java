/*     */ package org.apache.commons.math.geometry;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Vector3D
/*     */   implements Serializable
/*     */ {
/*  33 */   public static final Vector3D plusI = new Vector3D(1.0D, 0.0D, 0.0D);
/*     */   
/*     */ 
/*  36 */   public static final Vector3D minusI = new Vector3D(-1.0D, 0.0D, 0.0D);
/*     */   
/*     */ 
/*  39 */   public static final Vector3D plusJ = new Vector3D(0.0D, 1.0D, 0.0D);
/*     */   
/*     */ 
/*  42 */   public static final Vector3D minusJ = new Vector3D(0.0D, -1.0D, 0.0D);
/*     */   
/*     */ 
/*  45 */   public static final Vector3D plusK = new Vector3D(0.0D, 0.0D, 1.0D);
/*     */   
/*     */ 
/*  48 */   public static final Vector3D minusK = new Vector3D(0.0D, 0.0D, -1.0D);
/*     */   
/*     */ 
/*  51 */   public static final Vector3D zero = new Vector3D(0.0D, 0.0D, 0.0D);
/*     */   private final double x;
/*     */   private final double y;
/*     */   private final double z;
/*     */   private static final long serialVersionUID = -5721105387745193385L;
/*     */   
/*  57 */   public Vector3D() { this.x = 0.0D;
/*  58 */     this.y = 0.0D;
/*  59 */     this.z = 0.0D;
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
/*     */   public Vector3D(double x, double y, double z)
/*     */   {
/*  72 */     this.x = x;
/*  73 */     this.y = y;
/*  74 */     this.z = z;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Vector3D(double alpha, double delta)
/*     */   {
/*  86 */     double cosDelta = Math.cos(delta);
/*  87 */     this.x = (Math.cos(alpha) * cosDelta);
/*  88 */     this.y = (Math.sin(alpha) * cosDelta);
/*  89 */     this.z = Math.sin(delta);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Vector3D(double a, Vector3D u)
/*     */   {
/*  99 */     this.x = (a * u.x);
/* 100 */     this.y = (a * u.y);
/* 101 */     this.z = (a * u.z);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Vector3D(double a1, Vector3D u1, double a2, Vector3D u2)
/*     */   {
/* 113 */     this.x = (a1 * u1.x + a2 * u2.x);
/* 114 */     this.y = (a1 * u1.y + a2 * u2.y);
/* 115 */     this.z = (a1 * u1.z + a2 * u2.z);
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
/*     */   public Vector3D(double a1, Vector3D u1, double a2, Vector3D u2, double a3, Vector3D u3)
/*     */   {
/* 130 */     this.x = (a1 * u1.x + a2 * u2.x + a3 * u3.x);
/* 131 */     this.y = (a1 * u1.y + a2 * u2.y + a3 * u3.y);
/* 132 */     this.z = (a1 * u1.z + a2 * u2.z + a3 * u3.z);
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
/*     */   public Vector3D(double a1, Vector3D u1, double a2, Vector3D u2, double a3, Vector3D u3, double a4, Vector3D u4)
/*     */   {
/* 149 */     this.x = (a1 * u1.x + a2 * u2.x + a3 * u3.x + a4 * u4.x);
/* 150 */     this.y = (a1 * u1.y + a2 * u2.y + a3 * u3.y + a4 * u4.y);
/* 151 */     this.z = (a1 * u1.z + a2 * u2.z + a3 * u3.z + a4 * u4.z);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getX()
/*     */   {
/* 159 */     return this.x;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getY()
/*     */   {
/* 167 */     return this.y;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getZ()
/*     */   {
/* 175 */     return this.z;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getNorm()
/*     */   {
/* 182 */     return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getAlpha()
/*     */   {
/* 190 */     return Math.atan2(this.y, this.x);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getDelta()
/*     */   {
/* 198 */     return Math.asin(this.z / getNorm());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Vector3D add(Vector3D v)
/*     */   {
/* 206 */     return new Vector3D(this.x + v.x, this.y + v.y, this.z + v.z);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Vector3D add(double factor, Vector3D v)
/*     */   {
/* 215 */     return new Vector3D(this.x + factor * v.x, this.y + factor * v.y, this.z + factor * v.z);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Vector3D subtract(Vector3D v)
/*     */   {
/* 223 */     return new Vector3D(this.x - v.x, this.y - v.y, this.z - v.z);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Vector3D subtract(double factor, Vector3D v)
/*     */   {
/* 232 */     return new Vector3D(this.x - factor * v.x, this.y - factor * v.y, this.z - factor * v.z);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Vector3D normalize()
/*     */   {
/* 240 */     double s = getNorm();
/* 241 */     if (s == 0.0D) {
/* 242 */       throw new ArithmeticException("cannot normalize a zero norm vector");
/*     */     }
/* 244 */     return scalarMultiply(1.0D / s);
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
/*     */   public Vector3D orthogonal()
/*     */   {
/* 264 */     double threshold = 0.6D * getNorm();
/* 265 */     if (threshold == 0.0D) {
/* 266 */       throw new ArithmeticException("null norm");
/*     */     }
/*     */     
/* 269 */     if ((this.x >= -threshold) && (this.x <= threshold)) {
/* 270 */       double inverse = 1.0D / Math.sqrt(this.y * this.y + this.z * this.z);
/* 271 */       return new Vector3D(0.0D, inverse * this.z, -inverse * this.y); }
/* 272 */     if ((this.y >= -threshold) && (this.y <= threshold)) {
/* 273 */       double inverse = 1.0D / Math.sqrt(this.x * this.x + this.z * this.z);
/* 274 */       return new Vector3D(-inverse * this.z, 0.0D, inverse * this.x);
/*     */     }
/* 276 */     double inverse = 1.0D / Math.sqrt(this.x * this.x + this.y * this.y);
/* 277 */     return new Vector3D(inverse * this.y, -inverse * this.x, 0.0D);
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
/*     */   public static double angle(Vector3D v1, Vector3D v2)
/*     */   {
/* 294 */     double normProduct = v1.getNorm() * v2.getNorm();
/* 295 */     if (normProduct == 0.0D) {
/* 296 */       throw new ArithmeticException("null norm");
/*     */     }
/*     */     
/* 299 */     double dot = dotProduct(v1, v2);
/* 300 */     double threshold = normProduct * 0.9999D;
/* 301 */     if ((dot < -threshold) || (dot > threshold))
/*     */     {
/* 303 */       Vector3D v3 = crossProduct(v1, v2);
/* 304 */       if (dot >= 0.0D) {
/* 305 */         return Math.asin(v3.getNorm() / normProduct);
/*     */       }
/* 307 */       return 3.141592653589793D - Math.asin(v3.getNorm() / normProduct);
/*     */     }
/*     */     
/*     */ 
/* 311 */     return Math.acos(dot / normProduct);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Vector3D negate()
/*     */   {
/* 319 */     return new Vector3D(-this.x, -this.y, -this.z);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Vector3D scalarMultiply(double a)
/*     */   {
/* 327 */     return new Vector3D(a * this.x, a * this.y, a * this.z);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double dotProduct(Vector3D v1, Vector3D v2)
/*     */   {
/* 336 */     return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Vector3D crossProduct(Vector3D v1, Vector3D v2)
/*     */   {
/* 345 */     return new Vector3D(v1.y * v2.z - v1.z * v2.y, v1.z * v2.x - v1.x * v2.z, v1.x * v2.y - v1.y * v2.x);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/geometry/Vector3D.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */