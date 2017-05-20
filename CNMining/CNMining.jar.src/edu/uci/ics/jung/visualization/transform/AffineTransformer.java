/*     */ package edu.uci.ics.jung.visualization.transform;
/*     */ 
/*     */ import edu.uci.ics.jung.visualization.transform.shape.ShapeTransformer;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.NoninvertibleTransformException;
/*     */ import java.awt.geom.PathIterator;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Point2D.Float;
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
/*     */ public class AffineTransformer
/*     */   implements BidirectionalTransformer, ShapeTransformer
/*     */ {
/*     */   protected AffineTransform inverse;
/*  38 */   protected AffineTransform transform = new AffineTransform();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AffineTransformer() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public AffineTransformer(AffineTransform transform)
/*     */   {
/*  51 */     if (transform != null) {
/*  52 */       this.transform = transform;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public AffineTransform getTransform()
/*     */   {
/*  59 */     return this.transform;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setTransform(AffineTransform transform)
/*     */   {
/*  65 */     this.transform = transform;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Point2D inverseTransform(Point2D p)
/*     */   {
/*  75 */     return getInverse().transform(p, null);
/*     */   }
/*     */   
/*     */   public AffineTransform getInverse() {
/*  79 */     if (this.inverse == null) {
/*     */       try {
/*  81 */         this.inverse = this.transform.createInverse();
/*     */       } catch (NoninvertibleTransformException e) {
/*  83 */         e.printStackTrace();
/*     */       }
/*     */     }
/*  86 */     return this.inverse;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getScaleX()
/*     */   {
/*  93 */     return this.transform.getScaleX();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getScaleY()
/*     */   {
/* 100 */     return this.transform.getScaleY();
/*     */   }
/*     */   
/*     */   public double getScale() {
/* 104 */     return Math.sqrt(this.transform.getDeterminant());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getShearX()
/*     */   {
/* 111 */     return this.transform.getShearX();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getShearY()
/*     */   {
/* 118 */     return this.transform.getShearY();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getTranslateX()
/*     */   {
/* 125 */     return this.transform.getTranslateX();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getTranslateY()
/*     */   {
/* 132 */     return this.transform.getTranslateY();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Point2D transform(Point2D p)
/*     */   {
/* 143 */     if (p == null) return null;
/* 144 */     return this.transform.transform(p, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Shape transform(Shape shape)
/*     */   {
/* 153 */     GeneralPath newPath = new GeneralPath();
/* 154 */     float[] coords = new float[6];
/* 155 */     PathIterator iterator = shape.getPathIterator(null);
/* 156 */     for (; !iterator.isDone(); 
/* 157 */         iterator.next()) {
/* 158 */       int type = iterator.currentSegment(coords);
/* 159 */       Point2D p; Point2D q; switch (type) {
/*     */       case 0: 
/* 161 */         p = transform(new Point2D.Float(coords[0], coords[1]));
/* 162 */         newPath.moveTo((float)p.getX(), (float)p.getY());
/* 163 */         break;
/*     */       
/*     */       case 1: 
/* 166 */         p = transform(new Point2D.Float(coords[0], coords[1]));
/* 167 */         newPath.lineTo((float)p.getX(), (float)p.getY());
/* 168 */         break;
/*     */       
/*     */       case 2: 
/* 171 */         p = transform(new Point2D.Float(coords[0], coords[1]));
/* 172 */         q = transform(new Point2D.Float(coords[2], coords[3]));
/* 173 */         newPath.quadTo((float)p.getX(), (float)p.getY(), (float)q.getX(), (float)q.getY());
/* 174 */         break;
/*     */       
/*     */       case 3: 
/* 177 */         p = transform(new Point2D.Float(coords[0], coords[1]));
/* 178 */         q = transform(new Point2D.Float(coords[2], coords[3]));
/* 179 */         Point2D r = transform(new Point2D.Float(coords[4], coords[5]));
/* 180 */         newPath.curveTo((float)p.getX(), (float)p.getY(), (float)q.getX(), (float)q.getY(), (float)r.getX(), (float)r.getY());
/*     */         
/*     */ 
/* 183 */         break;
/*     */       
/*     */       case 4: 
/* 186 */         newPath.closePath();
/*     */       }
/*     */       
/*     */     }
/*     */     
/* 191 */     return newPath;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Shape inverseTransform(Shape shape)
/*     */   {
/* 200 */     GeneralPath newPath = new GeneralPath();
/* 201 */     float[] coords = new float[6];
/* 202 */     PathIterator iterator = shape.getPathIterator(null);
/* 203 */     for (; !iterator.isDone(); 
/* 204 */         iterator.next()) {
/* 205 */       int type = iterator.currentSegment(coords);
/* 206 */       Point2D p; Point2D q; switch (type) {
/*     */       case 0: 
/* 208 */         p = inverseTransform(new Point2D.Float(coords[0], coords[1]));
/* 209 */         newPath.moveTo((float)p.getX(), (float)p.getY());
/* 210 */         break;
/*     */       
/*     */       case 1: 
/* 213 */         p = inverseTransform(new Point2D.Float(coords[0], coords[1]));
/* 214 */         newPath.lineTo((float)p.getX(), (float)p.getY());
/* 215 */         break;
/*     */       
/*     */       case 2: 
/* 218 */         p = inverseTransform(new Point2D.Float(coords[0], coords[1]));
/* 219 */         q = inverseTransform(new Point2D.Float(coords[2], coords[3]));
/* 220 */         newPath.quadTo((float)p.getX(), (float)p.getY(), (float)q.getX(), (float)q.getY());
/* 221 */         break;
/*     */       
/*     */       case 3: 
/* 224 */         p = inverseTransform(new Point2D.Float(coords[0], coords[1]));
/* 225 */         q = inverseTransform(new Point2D.Float(coords[2], coords[3]));
/* 226 */         Point2D r = inverseTransform(new Point2D.Float(coords[4], coords[5]));
/* 227 */         newPath.curveTo((float)p.getX(), (float)p.getY(), (float)q.getX(), (float)q.getY(), (float)r.getX(), (float)r.getY());
/*     */         
/*     */ 
/* 230 */         break;
/*     */       
/*     */       case 4: 
/* 233 */         newPath.closePath();
/*     */       }
/*     */       
/*     */     }
/*     */     
/* 238 */     return newPath;
/*     */   }
/*     */   
/*     */   public double getRotation() {
/* 242 */     double[] unitVector = { 0.0D, 0.0D, 1.0D, 0.0D };
/* 243 */     double[] result = new double[4];
/*     */     
/* 245 */     this.transform.transform(unitVector, 0, result, 0, 2);
/*     */     
/* 247 */     double dy = Math.abs(result[3] - result[1]);
/* 248 */     double length = Point2D.distance(result[0], result[1], result[2], result[3]);
/* 249 */     double rotation = Math.asin(dy / length);
/*     */     
/* 251 */     if (result[3] - result[1] > 0.0D) {
/* 252 */       if (result[2] - result[0] < 0.0D) {
/* 253 */         rotation = 3.141592653589793D - rotation;
/*     */       }
/*     */     }
/* 256 */     else if (result[2] - result[0] > 0.0D) {
/* 257 */       rotation = 6.283185307179586D - rotation;
/*     */     } else {
/* 259 */       rotation += 3.141592653589793D;
/*     */     }
/*     */     
/*     */ 
/* 263 */     return rotation;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 268 */     return "Transformer using " + this.transform;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/transform/AffineTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */