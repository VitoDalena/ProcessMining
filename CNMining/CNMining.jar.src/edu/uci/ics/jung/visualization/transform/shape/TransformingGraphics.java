/*     */ package edu.uci.ics.jung.visualization.transform.shape;
/*     */ 
/*     */ import edu.uci.ics.jung.visualization.transform.BidirectionalTransformer;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.awt.image.ImageObserver;
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
/*     */ public class TransformingGraphics
/*     */   extends GraphicsDecorator
/*     */ {
/*     */   protected BidirectionalTransformer transformer;
/*     */   
/*     */   public TransformingGraphics(BidirectionalTransformer transformer)
/*     */   {
/*  43 */     this(transformer, null);
/*     */   }
/*     */   
/*     */   public TransformingGraphics(BidirectionalTransformer transformer, Graphics2D delegate) {
/*  47 */     super(delegate);
/*  48 */     this.transformer = transformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public BidirectionalTransformer getTransformer()
/*     */   {
/*  55 */     return this.transformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setTransformer(BidirectionalTransformer transformer)
/*     */   {
/*  62 */     this.transformer = transformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void draw(Shape s)
/*     */   {
/*  69 */     Shape shape = ((ShapeTransformer)this.transformer).transform(s);
/*  70 */     this.delegate.draw(shape);
/*     */   }
/*     */   
/*     */   public void draw(Shape s, float flatness) {
/*  74 */     Shape shape = null;
/*  75 */     if ((this.transformer instanceof ShapeFlatnessTransformer)) {
/*  76 */       shape = ((ShapeFlatnessTransformer)this.transformer).transform(s, flatness);
/*     */     } else {
/*  78 */       shape = ((ShapeTransformer)this.transformer).transform(s);
/*     */     }
/*  80 */     this.delegate.draw(shape);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void fill(Shape s)
/*     */   {
/*  88 */     Shape shape = ((ShapeTransformer)this.transformer).transform(s);
/*  89 */     this.delegate.fill(shape);
/*     */   }
/*     */   
/*     */   public void fill(Shape s, float flatness) {
/*  93 */     Shape shape = null;
/*  94 */     if ((this.transformer instanceof ShapeFlatnessTransformer)) {
/*  95 */       shape = ((ShapeFlatnessTransformer)this.transformer).transform(s, flatness);
/*     */     } else {
/*  97 */       shape = ((ShapeTransformer)this.transformer).transform(s);
/*     */     }
/*  99 */     this.delegate.fill(shape);
/*     */   }
/*     */   
/*     */   public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
/* 103 */     Image image = null;
/* 104 */     if ((this.transformer instanceof ShapeFlatnessTransformer)) {
/* 105 */       Rectangle2D r = new Rectangle2D.Double(x, y, img.getWidth(observer), img.getHeight(observer));
/* 106 */       Rectangle2D s = ((ShapeTransformer)this.transformer).transform(r).getBounds2D();
/* 107 */       image = img.getScaledInstance((int)s.getWidth(), (int)s.getHeight(), 4);
/* 108 */       x = (int)s.getMinX();
/* 109 */       y = (int)s.getMinY();
/*     */     } else {
/* 111 */       image = img;
/*     */     }
/* 113 */     return this.delegate.drawImage(image, x, y, observer);
/*     */   }
/*     */   
/*     */   public boolean drawImage(Image img, AffineTransform at, ImageObserver observer) {
/* 117 */     Image image = null;
/* 118 */     int x = (int)at.getTranslateX();
/* 119 */     int y = (int)at.getTranslateY();
/* 120 */     if ((this.transformer instanceof ShapeFlatnessTransformer)) {
/* 121 */       Rectangle2D r = new Rectangle2D.Double(x, y, img.getWidth(observer), img.getHeight(observer));
/* 122 */       Rectangle2D s = ((ShapeTransformer)this.transformer).transform(r).getBounds2D();
/* 123 */       image = img.getScaledInstance((int)s.getWidth(), (int)s.getHeight(), 4);
/* 124 */       x = (int)s.getMinX();
/* 125 */       y = (int)s.getMinY();
/* 126 */       at.setToTranslation(s.getMinX(), s.getMinY());
/*     */     } else {
/* 128 */       image = img;
/*     */     }
/* 130 */     return this.delegate.drawImage(image, at, observer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean hit(Rectangle rect, Shape s, boolean onStroke)
/*     */   {
/* 138 */     Shape shape = ((ShapeTransformer)this.transformer).transform(s);
/* 139 */     return this.delegate.hit(rect, shape, onStroke);
/*     */   }
/*     */   
/*     */   public Graphics create() {
/* 143 */     return this.delegate.create();
/*     */   }
/*     */   
/*     */   public void dispose() {
/* 147 */     this.delegate.dispose();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/transform/shape/TransformingGraphics.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */