/*      */ package org.apache.commons.math.geometry;
/*      */ 
/*      */ import java.io.Serializable;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Rotation
/*      */   implements Serializable
/*      */ {
/*      */   private final double q0;
/*      */   private final double q1;
/*      */   private final double q2;
/*      */   private final double q3;
/*      */   private static final long serialVersionUID = 8225864499430109352L;
/*      */   
/*      */   public Rotation()
/*      */   {
/*   94 */     this.q0 = 1.0D;
/*   95 */     this.q1 = 0.0D;
/*   96 */     this.q2 = 0.0D;
/*   97 */     this.q3 = 0.0D;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Rotation(double q0, double q1, double q2, double q3, boolean needsNormalization)
/*      */   {
/*  117 */     if (needsNormalization)
/*      */     {
/*  119 */       double inv = 1.0D / Math.sqrt(q0 * q0 + q1 * q1 + q2 * q2 + q3 * q3);
/*  120 */       q0 *= inv;
/*  121 */       q1 *= inv;
/*  122 */       q2 *= inv;
/*  123 */       q3 *= inv;
/*      */     }
/*      */     
/*  126 */     this.q0 = q0;
/*  127 */     this.q1 = q1;
/*  128 */     this.q2 = q2;
/*  129 */     this.q3 = q3;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Rotation(Vector3D axis, double angle)
/*      */   {
/*  146 */     double norm = axis.getNorm();
/*  147 */     if (norm == 0.0D) {
/*  148 */       throw new ArithmeticException("zero norm for rotation axis");
/*      */     }
/*      */     
/*  151 */     double halfAngle = -0.5D * angle;
/*  152 */     double coeff = Math.sin(halfAngle) / norm;
/*      */     
/*  154 */     this.q0 = Math.cos(halfAngle);
/*  155 */     this.q1 = (coeff * axis.getX());
/*  156 */     this.q2 = (coeff * axis.getY());
/*  157 */     this.q3 = (coeff * axis.getZ());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Rotation(double[][] m, double threshold)
/*      */     throws NotARotationMatrixException
/*      */   {
/*  195 */     if ((m.length != 3) || (m[0].length != 3) || (m[1].length != 3) || (m[2].length != 3))
/*      */     {
/*  197 */       throw new NotARotationMatrixException("a {0}x{1} matrix cannot be a rotation matrix", new Object[] { Integer.toString(m.length), Integer.toString(m[0].length) });
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  206 */     double[][] ort = orthogonalizeMatrix(m, threshold);
/*      */     
/*      */ 
/*  209 */     double det = ort[0][0] * (ort[1][1] * ort[2][2] - ort[2][1] * ort[1][2]) - ort[1][0] * (ort[0][1] * ort[2][2] - ort[2][1] * ort[0][2]) + ort[2][0] * (ort[0][1] * ort[1][2] - ort[1][1] * ort[0][2]);
/*      */     
/*      */ 
/*  212 */     if (det < 0.0D) {
/*  213 */       throw new NotARotationMatrixException("the closest orthogonal matrix has a negative determinant {0}", new Object[] { Double.toString(det) });
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  231 */     double s = ort[0][0] + ort[1][1] + ort[2][2];
/*  232 */     if (s > -0.19D)
/*      */     {
/*  234 */       this.q0 = (0.5D * Math.sqrt(s + 1.0D));
/*  235 */       double inv = 0.25D / this.q0;
/*  236 */       this.q1 = (inv * (ort[1][2] - ort[2][1]));
/*  237 */       this.q2 = (inv * (ort[2][0] - ort[0][2]));
/*  238 */       this.q3 = (inv * (ort[0][1] - ort[1][0]));
/*      */     } else {
/*  240 */       s = ort[0][0] - ort[1][1] - ort[2][2];
/*  241 */       if (s > -0.19D)
/*      */       {
/*  243 */         this.q1 = (0.5D * Math.sqrt(s + 1.0D));
/*  244 */         double inv = 0.25D / this.q1;
/*  245 */         this.q0 = (inv * (ort[1][2] - ort[2][1]));
/*  246 */         this.q2 = (inv * (ort[0][1] + ort[1][0]));
/*  247 */         this.q3 = (inv * (ort[0][2] + ort[2][0]));
/*      */       } else {
/*  249 */         s = ort[1][1] - ort[0][0] - ort[2][2];
/*  250 */         if (s > -0.19D)
/*      */         {
/*  252 */           this.q2 = (0.5D * Math.sqrt(s + 1.0D));
/*  253 */           double inv = 0.25D / this.q2;
/*  254 */           this.q0 = (inv * (ort[2][0] - ort[0][2]));
/*  255 */           this.q1 = (inv * (ort[0][1] + ort[1][0]));
/*  256 */           this.q3 = (inv * (ort[2][1] + ort[1][2]));
/*      */         }
/*      */         else {
/*  259 */           s = ort[2][2] - ort[0][0] - ort[1][1];
/*  260 */           this.q3 = (0.5D * Math.sqrt(s + 1.0D));
/*  261 */           double inv = 0.25D / this.q3;
/*  262 */           this.q0 = (inv * (ort[0][1] - ort[1][0]));
/*  263 */           this.q1 = (inv * (ort[0][2] + ort[2][0]));
/*  264 */           this.q2 = (inv * (ort[2][1] + ort[1][2]));
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Rotation(Vector3D u1, Vector3D u2, Vector3D v1, Vector3D v2)
/*      */   {
/*  292 */     double u1u1 = Vector3D.dotProduct(u1, u1);
/*  293 */     double u2u2 = Vector3D.dotProduct(u2, u2);
/*  294 */     double v1v1 = Vector3D.dotProduct(v1, v1);
/*  295 */     double v2v2 = Vector3D.dotProduct(v2, v2);
/*  296 */     if ((u1u1 == 0.0D) || (u2u2 == 0.0D) || (v1v1 == 0.0D) || (v2v2 == 0.0D)) {
/*  297 */       throw new IllegalArgumentException("zero norm for rotation defining vector");
/*      */     }
/*      */     
/*  300 */     double u1x = u1.getX();
/*  301 */     double u1y = u1.getY();
/*  302 */     double u1z = u1.getZ();
/*      */     
/*  304 */     double u2x = u2.getX();
/*  305 */     double u2y = u2.getY();
/*  306 */     double u2z = u2.getZ();
/*      */     
/*      */ 
/*  309 */     double coeff = Math.sqrt(u1u1 / v1v1);
/*  310 */     double v1x = coeff * v1.getX();
/*  311 */     double v1y = coeff * v1.getY();
/*  312 */     double v1z = coeff * v1.getZ();
/*  313 */     v1 = new Vector3D(v1x, v1y, v1z);
/*      */     
/*      */ 
/*  316 */     double u1u2 = Vector3D.dotProduct(u1, u2);
/*  317 */     double v1v2 = Vector3D.dotProduct(v1, v2);
/*  318 */     double coeffU = u1u2 / u1u1;
/*  319 */     double coeffV = v1v2 / u1u1;
/*  320 */     double beta = Math.sqrt((u2u2 - u1u2 * coeffU) / (v2v2 - v1v2 * coeffV));
/*  321 */     double alpha = coeffU - beta * coeffV;
/*  322 */     double v2x = alpha * v1x + beta * v2.getX();
/*  323 */     double v2y = alpha * v1y + beta * v2.getY();
/*  324 */     double v2z = alpha * v1z + beta * v2.getZ();
/*  325 */     v2 = new Vector3D(v2x, v2y, v2z);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  330 */     Vector3D uRef = u1;
/*  331 */     Vector3D vRef = v1;
/*  332 */     double dx1 = v1x - u1.getX();
/*  333 */     double dy1 = v1y - u1.getY();
/*  334 */     double dz1 = v1z - u1.getZ();
/*  335 */     double dx2 = v2x - u2.getX();
/*  336 */     double dy2 = v2y - u2.getY();
/*  337 */     double dz2 = v2z - u2.getZ();
/*  338 */     Vector3D k = new Vector3D(dy1 * dz2 - dz1 * dy2, dz1 * dx2 - dx1 * dz2, dx1 * dy2 - dy1 * dx2);
/*      */     
/*      */ 
/*  341 */     double c = k.getX() * (u1y * u2z - u1z * u2y) + k.getY() * (u1z * u2x - u1x * u2z) + k.getZ() * (u1x * u2y - u1y * u2x);
/*      */     
/*      */ 
/*      */ 
/*  345 */     if (c == 0.0D)
/*      */     {
/*      */ 
/*  348 */       Vector3D u3 = Vector3D.crossProduct(u1, u2);
/*  349 */       Vector3D v3 = Vector3D.crossProduct(v1, v2);
/*  350 */       double u3x = u3.getX();
/*  351 */       double u3y = u3.getY();
/*  352 */       double u3z = u3.getZ();
/*  353 */       double v3x = v3.getX();
/*  354 */       double v3y = v3.getY();
/*  355 */       double v3z = v3.getZ();
/*      */       
/*  357 */       double dx3 = v3x - u3x;
/*  358 */       double dy3 = v3y - u3y;
/*  359 */       double dz3 = v3z - u3z;
/*  360 */       k = new Vector3D(dy1 * dz3 - dz1 * dy3, dz1 * dx3 - dx1 * dz3, dx1 * dy3 - dy1 * dx3);
/*      */       
/*      */ 
/*  363 */       c = k.getX() * (u1y * u3z - u1z * u3y) + k.getY() * (u1z * u3x - u1x * u3z) + k.getZ() * (u1x * u3y - u1y * u3x);
/*      */       
/*      */ 
/*      */ 
/*  367 */       if (c == 0.0D)
/*      */       {
/*      */ 
/*  370 */         k = new Vector3D(dy2 * dz3 - dz2 * dy3, dz2 * dx3 - dx2 * dz3, dx2 * dy3 - dy2 * dx3);
/*      */         
/*      */ 
/*  373 */         c = k.getX() * (u2y * u3z - u2z * u3y) + k.getY() * (u2z * u3x - u2x * u3z) + k.getZ() * (u2x * u3y - u2y * u3x);
/*      */         
/*      */ 
/*      */ 
/*  377 */         if (c == 0.0D)
/*      */         {
/*      */ 
/*  380 */           this.q0 = 1.0D;
/*  381 */           this.q1 = 0.0D;
/*  382 */           this.q2 = 0.0D;
/*  383 */           this.q3 = 0.0D;
/*  384 */           return;
/*      */         }
/*      */         
/*      */ 
/*  388 */         uRef = u2;
/*  389 */         vRef = v2;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  396 */     c = Math.sqrt(c);
/*  397 */     double inv = 1.0D / (c + c);
/*  398 */     this.q1 = (inv * k.getX());
/*  399 */     this.q2 = (inv * k.getY());
/*  400 */     this.q3 = (inv * k.getZ());
/*      */     
/*      */ 
/*  403 */     k = new Vector3D(uRef.getY() * this.q3 - uRef.getZ() * this.q2, uRef.getZ() * this.q1 - uRef.getX() * this.q3, uRef.getX() * this.q2 - uRef.getY() * this.q1);
/*      */     
/*      */ 
/*  406 */     c = Vector3D.dotProduct(k, k);
/*  407 */     this.q0 = (Vector3D.dotProduct(vRef, k) / (c + c));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Rotation(Vector3D u, Vector3D v)
/*      */   {
/*  426 */     double normProduct = u.getNorm() * v.getNorm();
/*  427 */     if (normProduct == 0.0D) {
/*  428 */       throw new IllegalArgumentException("zero norm for rotation defining vector");
/*      */     }
/*      */     
/*  431 */     double dot = Vector3D.dotProduct(u, v);
/*      */     
/*  433 */     if (dot < -0.999999999999998D * normProduct)
/*      */     {
/*      */ 
/*  436 */       Vector3D w = u.orthogonal();
/*  437 */       this.q0 = 0.0D;
/*  438 */       this.q1 = (-w.getX());
/*  439 */       this.q2 = (-w.getY());
/*  440 */       this.q3 = (-w.getZ());
/*      */     }
/*      */     else
/*      */     {
/*  444 */       this.q0 = Math.sqrt(0.5D * (1.0D + dot / normProduct));
/*  445 */       double coeff = 1.0D / (2.0D * this.q0 * normProduct);
/*  446 */       this.q1 = (coeff * (v.getY() * u.getZ() - v.getZ() * u.getY()));
/*  447 */       this.q2 = (coeff * (v.getZ() * u.getX() - v.getX() * u.getZ()));
/*  448 */       this.q3 = (coeff * (v.getX() * u.getY() - v.getY() * u.getX()));
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Rotation(RotationOrder order, double alpha1, double alpha2, double alpha3)
/*      */   {
/*  474 */     Rotation r1 = new Rotation(order.getA1(), alpha1);
/*  475 */     Rotation r2 = new Rotation(order.getA2(), alpha2);
/*  476 */     Rotation r3 = new Rotation(order.getA3(), alpha3);
/*  477 */     Rotation composed = r1.applyTo(r2.applyTo(r3));
/*  478 */     this.q0 = composed.q0;
/*  479 */     this.q1 = composed.q1;
/*  480 */     this.q2 = composed.q2;
/*  481 */     this.q3 = composed.q3;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Rotation revert()
/*      */   {
/*  492 */     return new Rotation(-this.q0, this.q1, this.q2, this.q3, false);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public double getQ0()
/*      */   {
/*  499 */     return this.q0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public double getQ1()
/*      */   {
/*  506 */     return this.q1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public double getQ2()
/*      */   {
/*  513 */     return this.q2;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public double getQ3()
/*      */   {
/*  520 */     return this.q3;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Vector3D getAxis()
/*      */   {
/*  527 */     double squaredSine = this.q1 * this.q1 + this.q2 * this.q2 + this.q3 * this.q3;
/*  528 */     if (squaredSine == 0.0D)
/*  529 */       return new Vector3D(1.0D, 0.0D, 0.0D);
/*  530 */     if (this.q0 < 0.0D) {
/*  531 */       double inverse = 1.0D / Math.sqrt(squaredSine);
/*  532 */       return new Vector3D(this.q1 * inverse, this.q2 * inverse, this.q3 * inverse);
/*      */     }
/*  534 */     double inverse = -1.0D / Math.sqrt(squaredSine);
/*  535 */     return new Vector3D(this.q1 * inverse, this.q2 * inverse, this.q3 * inverse);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public double getAngle()
/*      */   {
/*  542 */     if ((this.q0 < -0.1D) || (this.q0 > 0.1D))
/*  543 */       return 2.0D * Math.asin(Math.sqrt(this.q1 * this.q1 + this.q2 * this.q2 + this.q3 * this.q3));
/*  544 */     if (this.q0 < 0.0D) {
/*  545 */       return 2.0D * Math.acos(-this.q0);
/*      */     }
/*  547 */     return 2.0D * Math.acos(this.q0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double[] getAngles(RotationOrder order)
/*      */     throws CardanEulerSingularityException
/*      */   {
/*  588 */     if (order == RotationOrder.XYZ)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  595 */       Vector3D v1 = applyTo(Vector3D.plusK);
/*  596 */       Vector3D v2 = applyInverseTo(Vector3D.plusI);
/*  597 */       if ((v2.getZ() < -0.9999999999D) || (v2.getZ() > 0.9999999999D)) {
/*  598 */         throw new CardanEulerSingularityException(true);
/*      */       }
/*  600 */       return new double[] { Math.atan2(-v1.getY(), v1.getZ()), Math.asin(v2.getZ()), Math.atan2(-v2.getY(), v2.getX()) };
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  606 */     if (order == RotationOrder.XZY)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  613 */       Vector3D v1 = applyTo(Vector3D.plusJ);
/*  614 */       Vector3D v2 = applyInverseTo(Vector3D.plusI);
/*  615 */       if ((v2.getY() < -0.9999999999D) || (v2.getY() > 0.9999999999D)) {
/*  616 */         throw new CardanEulerSingularityException(true);
/*      */       }
/*  618 */       return new double[] { Math.atan2(v1.getZ(), v1.getY()), -Math.asin(v2.getY()), Math.atan2(v2.getZ(), v2.getX()) };
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  624 */     if (order == RotationOrder.YXZ)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  631 */       Vector3D v1 = applyTo(Vector3D.plusK);
/*  632 */       Vector3D v2 = applyInverseTo(Vector3D.plusJ);
/*  633 */       if ((v2.getZ() < -0.9999999999D) || (v2.getZ() > 0.9999999999D)) {
/*  634 */         throw new CardanEulerSingularityException(true);
/*      */       }
/*  636 */       return new double[] { Math.atan2(v1.getX(), v1.getZ()), -Math.asin(v2.getZ()), Math.atan2(v2.getX(), v2.getY()) };
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  642 */     if (order == RotationOrder.YZX)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  649 */       Vector3D v1 = applyTo(Vector3D.plusI);
/*  650 */       Vector3D v2 = applyInverseTo(Vector3D.plusJ);
/*  651 */       if ((v2.getX() < -0.9999999999D) || (v2.getX() > 0.9999999999D)) {
/*  652 */         throw new CardanEulerSingularityException(true);
/*      */       }
/*  654 */       return new double[] { Math.atan2(-v1.getZ(), v1.getX()), Math.asin(v2.getX()), Math.atan2(-v2.getZ(), v2.getY()) };
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  660 */     if (order == RotationOrder.ZXY)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  667 */       Vector3D v1 = applyTo(Vector3D.plusJ);
/*  668 */       Vector3D v2 = applyInverseTo(Vector3D.plusK);
/*  669 */       if ((v2.getY() < -0.9999999999D) || (v2.getY() > 0.9999999999D)) {
/*  670 */         throw new CardanEulerSingularityException(true);
/*      */       }
/*  672 */       return new double[] { Math.atan2(-v1.getX(), v1.getY()), Math.asin(v2.getY()), Math.atan2(-v2.getX(), v2.getZ()) };
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  678 */     if (order == RotationOrder.ZYX)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  685 */       Vector3D v1 = applyTo(Vector3D.plusI);
/*  686 */       Vector3D v2 = applyInverseTo(Vector3D.plusK);
/*  687 */       if ((v2.getX() < -0.9999999999D) || (v2.getX() > 0.9999999999D)) {
/*  688 */         throw new CardanEulerSingularityException(true);
/*      */       }
/*  690 */       return new double[] { Math.atan2(v1.getY(), v1.getX()), -Math.asin(v2.getX()), Math.atan2(v2.getY(), v2.getZ()) };
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  696 */     if (order == RotationOrder.XYX)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  703 */       Vector3D v1 = applyTo(Vector3D.plusI);
/*  704 */       Vector3D v2 = applyInverseTo(Vector3D.plusI);
/*  705 */       if ((v2.getX() < -0.9999999999D) || (v2.getX() > 0.9999999999D)) {
/*  706 */         throw new CardanEulerSingularityException(false);
/*      */       }
/*  708 */       return new double[] { Math.atan2(v1.getY(), -v1.getZ()), Math.acos(v2.getX()), Math.atan2(v2.getY(), v2.getZ()) };
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  714 */     if (order == RotationOrder.XZX)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  721 */       Vector3D v1 = applyTo(Vector3D.plusI);
/*  722 */       Vector3D v2 = applyInverseTo(Vector3D.plusI);
/*  723 */       if ((v2.getX() < -0.9999999999D) || (v2.getX() > 0.9999999999D)) {
/*  724 */         throw new CardanEulerSingularityException(false);
/*      */       }
/*  726 */       return new double[] { Math.atan2(v1.getZ(), v1.getY()), Math.acos(v2.getX()), Math.atan2(v2.getZ(), -v2.getY()) };
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  732 */     if (order == RotationOrder.YXY)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  739 */       Vector3D v1 = applyTo(Vector3D.plusJ);
/*  740 */       Vector3D v2 = applyInverseTo(Vector3D.plusJ);
/*  741 */       if ((v2.getY() < -0.9999999999D) || (v2.getY() > 0.9999999999D)) {
/*  742 */         throw new CardanEulerSingularityException(false);
/*      */       }
/*  744 */       return new double[] { Math.atan2(v1.getX(), v1.getZ()), Math.acos(v2.getY()), Math.atan2(v2.getX(), -v2.getZ()) };
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  750 */     if (order == RotationOrder.YZY)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  757 */       Vector3D v1 = applyTo(Vector3D.plusJ);
/*  758 */       Vector3D v2 = applyInverseTo(Vector3D.plusJ);
/*  759 */       if ((v2.getY() < -0.9999999999D) || (v2.getY() > 0.9999999999D)) {
/*  760 */         throw new CardanEulerSingularityException(false);
/*      */       }
/*  762 */       return new double[] { Math.atan2(v1.getZ(), -v1.getX()), Math.acos(v2.getY()), Math.atan2(v2.getZ(), v2.getX()) };
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  768 */     if (order == RotationOrder.ZXZ)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  775 */       Vector3D v1 = applyTo(Vector3D.plusK);
/*  776 */       Vector3D v2 = applyInverseTo(Vector3D.plusK);
/*  777 */       if ((v2.getZ() < -0.9999999999D) || (v2.getZ() > 0.9999999999D)) {
/*  778 */         throw new CardanEulerSingularityException(false);
/*      */       }
/*  780 */       return new double[] { Math.atan2(v1.getX(), -v1.getY()), Math.acos(v2.getZ()), Math.atan2(v2.getX(), v2.getY()) };
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  793 */     Vector3D v1 = applyTo(Vector3D.plusK);
/*  794 */     Vector3D v2 = applyInverseTo(Vector3D.plusK);
/*  795 */     if ((v2.getZ() < -0.9999999999D) || (v2.getZ() > 0.9999999999D)) {
/*  796 */       throw new CardanEulerSingularityException(false);
/*      */     }
/*  798 */     return new double[] { Math.atan2(v1.getY(), v1.getX()), Math.acos(v2.getZ()), Math.atan2(v2.getY(), -v2.getX()) };
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double[][] getMatrix()
/*      */   {
/*  814 */     double q0q0 = this.q0 * this.q0;
/*  815 */     double q0q1 = this.q0 * this.q1;
/*  816 */     double q0q2 = this.q0 * this.q2;
/*  817 */     double q0q3 = this.q0 * this.q3;
/*  818 */     double q1q1 = this.q1 * this.q1;
/*  819 */     double q1q2 = this.q1 * this.q2;
/*  820 */     double q1q3 = this.q1 * this.q3;
/*  821 */     double q2q2 = this.q2 * this.q2;
/*  822 */     double q2q3 = this.q2 * this.q3;
/*  823 */     double q3q3 = this.q3 * this.q3;
/*      */     
/*      */ 
/*  826 */     double[][] m = new double[3][];
/*  827 */     m[0] = new double[3];
/*  828 */     m[1] = new double[3];
/*  829 */     m[2] = new double[3];
/*      */     
/*  831 */     m[0][0] = (2.0D * (q0q0 + q1q1) - 1.0D);
/*  832 */     m[1][0] = (2.0D * (q1q2 - q0q3));
/*  833 */     m[2][0] = (2.0D * (q1q3 + q0q2));
/*      */     
/*  835 */     m[0][1] = (2.0D * (q1q2 + q0q3));
/*  836 */     m[1][1] = (2.0D * (q0q0 + q2q2) - 1.0D);
/*  837 */     m[2][1] = (2.0D * (q2q3 - q0q1));
/*      */     
/*  839 */     m[0][2] = (2.0D * (q1q3 - q0q2));
/*  840 */     m[1][2] = (2.0D * (q2q3 + q0q1));
/*  841 */     m[2][2] = (2.0D * (q0q0 + q3q3) - 1.0D);
/*      */     
/*  843 */     return m;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Vector3D applyTo(Vector3D u)
/*      */   {
/*  853 */     double x = u.getX();
/*  854 */     double y = u.getY();
/*  855 */     double z = u.getZ();
/*      */     
/*  857 */     double s = this.q1 * x + this.q2 * y + this.q3 * z;
/*      */     
/*  859 */     return new Vector3D(2.0D * (this.q0 * (x * this.q0 - (this.q2 * z - this.q3 * y)) + s * this.q1) - x, 2.0D * (this.q0 * (y * this.q0 - (this.q3 * x - this.q1 * z)) + s * this.q2) - y, 2.0D * (this.q0 * (z * this.q0 - (this.q1 * y - this.q2 * x)) + s * this.q3) - z);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Vector3D applyInverseTo(Vector3D u)
/*      */   {
/*  871 */     double x = u.getX();
/*  872 */     double y = u.getY();
/*  873 */     double z = u.getZ();
/*      */     
/*  875 */     double s = this.q1 * x + this.q2 * y + this.q3 * z;
/*  876 */     double m0 = -this.q0;
/*      */     
/*  878 */     return new Vector3D(2.0D * (m0 * (x * m0 - (this.q2 * z - this.q3 * y)) + s * this.q1) - x, 2.0D * (m0 * (y * m0 - (this.q3 * x - this.q1 * z)) + s * this.q2) - y, 2.0D * (m0 * (z * m0 - (this.q1 * y - this.q2 * x)) + s * this.q3) - z);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Rotation applyTo(Rotation r)
/*      */   {
/*  894 */     return new Rotation(r.q0 * this.q0 - (r.q1 * this.q1 + r.q2 * this.q2 + r.q3 * this.q3), r.q1 * this.q0 + r.q0 * this.q1 + (r.q2 * this.q3 - r.q3 * this.q2), r.q2 * this.q0 + r.q0 * this.q2 + (r.q3 * this.q1 - r.q1 * this.q3), r.q3 * this.q0 + r.q0 * this.q3 + (r.q1 * this.q2 - r.q2 * this.q1), false);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Rotation applyInverseTo(Rotation r)
/*      */   {
/*  913 */     return new Rotation(-r.q0 * this.q0 - (r.q1 * this.q1 + r.q2 * this.q2 + r.q3 * this.q3), -r.q1 * this.q0 + r.q0 * this.q1 + (r.q2 * this.q3 - r.q3 * this.q2), -r.q2 * this.q0 + r.q0 * this.q2 + (r.q3 * this.q1 - r.q1 * this.q3), -r.q3 * this.q0 + r.q0 * this.q3 + (r.q1 * this.q2 - r.q2 * this.q1), false);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private double[][] orthogonalizeMatrix(double[][] m, double threshold)
/*      */     throws NotARotationMatrixException
/*      */   {
/*  932 */     double[] m0 = m[0];
/*  933 */     double[] m1 = m[1];
/*  934 */     double[] m2 = m[2];
/*  935 */     double x00 = m0[0];
/*  936 */     double x01 = m0[1];
/*  937 */     double x02 = m0[2];
/*  938 */     double x10 = m1[0];
/*  939 */     double x11 = m1[1];
/*  940 */     double x12 = m1[2];
/*  941 */     double x20 = m2[0];
/*  942 */     double x21 = m2[1];
/*  943 */     double x22 = m2[2];
/*  944 */     double fn = 0.0D;
/*      */     
/*      */ 
/*  947 */     double[][] o = new double[3][3];
/*  948 */     double[] o0 = o[0];
/*  949 */     double[] o1 = o[1];
/*  950 */     double[] o2 = o[2];
/*      */     
/*      */ 
/*  953 */     int i = 0;
/*  954 */     for (;;) { i++; if (i >= 11) {
/*      */         break;
/*      */       }
/*  957 */       double mx00 = m0[0] * x00 + m1[0] * x10 + m2[0] * x20;
/*  958 */       double mx10 = m0[1] * x00 + m1[1] * x10 + m2[1] * x20;
/*  959 */       double mx20 = m0[2] * x00 + m1[2] * x10 + m2[2] * x20;
/*  960 */       double mx01 = m0[0] * x01 + m1[0] * x11 + m2[0] * x21;
/*  961 */       double mx11 = m0[1] * x01 + m1[1] * x11 + m2[1] * x21;
/*  962 */       double mx21 = m0[2] * x01 + m1[2] * x11 + m2[2] * x21;
/*  963 */       double mx02 = m0[0] * x02 + m1[0] * x12 + m2[0] * x22;
/*  964 */       double mx12 = m0[1] * x02 + m1[1] * x12 + m2[1] * x22;
/*  965 */       double mx22 = m0[2] * x02 + m1[2] * x12 + m2[2] * x22;
/*      */       
/*      */ 
/*  968 */       o0[0] = (x00 - 0.5D * (x00 * mx00 + x01 * mx10 + x02 * mx20 - m0[0]));
/*  969 */       o0[1] = (x01 - 0.5D * (x00 * mx01 + x01 * mx11 + x02 * mx21 - m0[1]));
/*  970 */       o0[2] = (x02 - 0.5D * (x00 * mx02 + x01 * mx12 + x02 * mx22 - m0[2]));
/*  971 */       o1[0] = (x10 - 0.5D * (x10 * mx00 + x11 * mx10 + x12 * mx20 - m1[0]));
/*  972 */       o1[1] = (x11 - 0.5D * (x10 * mx01 + x11 * mx11 + x12 * mx21 - m1[1]));
/*  973 */       o1[2] = (x12 - 0.5D * (x10 * mx02 + x11 * mx12 + x12 * mx22 - m1[2]));
/*  974 */       o2[0] = (x20 - 0.5D * (x20 * mx00 + x21 * mx10 + x22 * mx20 - m2[0]));
/*  975 */       o2[1] = (x21 - 0.5D * (x20 * mx01 + x21 * mx11 + x22 * mx21 - m2[1]));
/*  976 */       o2[2] = (x22 - 0.5D * (x20 * mx02 + x21 * mx12 + x22 * mx22 - m2[2]));
/*      */       
/*      */ 
/*  979 */       double corr00 = o0[0] - m0[0];
/*  980 */       double corr01 = o0[1] - m0[1];
/*  981 */       double corr02 = o0[2] - m0[2];
/*  982 */       double corr10 = o1[0] - m1[0];
/*  983 */       double corr11 = o1[1] - m1[1];
/*  984 */       double corr12 = o1[2] - m1[2];
/*  985 */       double corr20 = o2[0] - m2[0];
/*  986 */       double corr21 = o2[1] - m2[1];
/*  987 */       double corr22 = o2[2] - m2[2];
/*      */       
/*      */ 
/*  990 */       double fn1 = corr00 * corr00 + corr01 * corr01 + corr02 * corr02 + corr10 * corr10 + corr11 * corr11 + corr12 * corr12 + corr20 * corr20 + corr21 * corr21 + corr22 * corr22;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  995 */       if (Math.abs(fn1 - fn) <= threshold) {
/*  996 */         return o;
/*      */       }
/*      */       
/*  999 */       x00 = o0[0];
/* 1000 */       x01 = o0[1];
/* 1001 */       x02 = o0[2];
/* 1002 */       x10 = o1[0];
/* 1003 */       x11 = o1[1];
/* 1004 */       x12 = o1[2];
/* 1005 */       x20 = o2[0];
/* 1006 */       x21 = o2[1];
/* 1007 */       x22 = o2[2];
/* 1008 */       fn = fn1;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1013 */     throw new NotARotationMatrixException("unable to orthogonalize matrix in {0} iterations", new Object[] { Integer.toString(i - 1) });
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/geometry/Rotation.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */