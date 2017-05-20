/*     */ package edu.uci.ics.jung.visualization.util;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Ellipse2D;
/*     */ import java.awt.geom.Ellipse2D.Float;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Float;
/*     */ import java.awt.geom.RoundRectangle2D;
/*     */ import java.awt.geom.RoundRectangle2D.Float;
/*     */ import org.apache.commons.collections15.Transformer;
/*     */ import org.apache.commons.collections15.functors.ConstantTransformer;
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
/*     */ public class VertexShapeFactory<V>
/*     */ {
/*     */   protected Transformer<V, Integer> vsf;
/*     */   protected Transformer<V, Float> varf;
/*     */   
/*     */   public VertexShapeFactory(Transformer<V, Integer> vsf, Transformer<V, Float> varf)
/*     */   {
/*  46 */     this.vsf = vsf;
/*  47 */     this.varf = varf;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public VertexShapeFactory()
/*     */   {
/*  57 */     this(new ConstantTransformer(Integer.valueOf(10)), new ConstantTransformer(Float.valueOf(1.0F)));
/*     */   }
/*     */   
/*     */ 
/*  61 */   private static final Rectangle2D theRectangle = new Rectangle2D.Float();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Rectangle2D getRectangle(V v)
/*     */   {
/*  69 */     float width = ((Integer)this.vsf.transform(v)).intValue();
/*  70 */     float height = width * ((Float)this.varf.transform(v)).floatValue();
/*  71 */     float h_offset = -(width / 2.0F);
/*  72 */     float v_offset = -(height / 2.0F);
/*  73 */     theRectangle.setFrame(h_offset, v_offset, width, height);
/*  74 */     return theRectangle;
/*     */   }
/*     */   
/*  77 */   private static final Ellipse2D theEllipse = new Ellipse2D.Float();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Ellipse2D getEllipse(V v)
/*     */   {
/*  85 */     theEllipse.setFrame(getRectangle(v));
/*  86 */     return theEllipse;
/*     */   }
/*     */   
/*  89 */   private static final RoundRectangle2D theRoundRectangle = new RoundRectangle2D.Float();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RoundRectangle2D getRoundRectangle(V v)
/*     */   {
/*  99 */     Rectangle2D frame = getRectangle(v);
/* 100 */     float arc_size = (float)Math.min(frame.getHeight(), frame.getWidth()) / 2.0F;
/* 101 */     theRoundRectangle.setRoundRect(frame.getX(), frame.getY(), frame.getWidth(), frame.getHeight(), arc_size, arc_size);
/*     */     
/* 103 */     return theRoundRectangle;
/*     */   }
/*     */   
/* 106 */   private static final GeneralPath thePolygon = new GeneralPath();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Shape getRegularPolygon(V v, int num_sides)
/*     */   {
/* 116 */     if (num_sides < 3)
/* 117 */       throw new IllegalArgumentException("Number of sides must be >= 3");
/* 118 */     Rectangle2D frame = getRectangle(v);
/* 119 */     float width = (float)frame.getWidth();
/* 120 */     float height = (float)frame.getHeight();
/*     */     
/*     */ 
/* 123 */     double angle = 0.0D;
/* 124 */     thePolygon.reset();
/* 125 */     thePolygon.moveTo(0.0F, 0.0F);
/* 126 */     thePolygon.lineTo(width, 0.0F);
/* 127 */     double theta = 6.283185307179586D / num_sides;
/* 128 */     for (int i = 2; i < num_sides; i++)
/*     */     {
/* 130 */       angle -= theta;
/* 131 */       float delta_x = (float)(width * Math.cos(angle));
/* 132 */       float delta_y = (float)(width * Math.sin(angle));
/* 133 */       Point2D prev = thePolygon.getCurrentPoint();
/* 134 */       thePolygon.lineTo((float)prev.getX() + delta_x, (float)prev.getY() + delta_y);
/*     */     }
/* 136 */     thePolygon.closePath();
/*     */     
/*     */ 
/* 139 */     Rectangle2D r = thePolygon.getBounds2D();
/* 140 */     double scale_x = width / r.getWidth();
/* 141 */     double scale_y = height / r.getHeight();
/* 142 */     float translationX = (float)(r.getMinX() + r.getWidth() / 2.0D);
/* 143 */     float translationY = (float)(r.getMinY() + r.getHeight() / 2.0D);
/*     */     
/* 145 */     AffineTransform at = AffineTransform.getScaleInstance(scale_x, scale_y);
/* 146 */     at.translate(-translationX, -translationY);
/*     */     
/* 148 */     Shape shape = at.createTransformedShape(thePolygon);
/* 149 */     return shape;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Shape getRegularStar(V v, int num_points)
/*     */   {
/* 161 */     if (num_points < 5)
/* 162 */       throw new IllegalArgumentException("Number of sides must be >= 5");
/* 163 */     Rectangle2D frame = getRectangle(v);
/* 164 */     float width = (float)frame.getWidth();
/* 165 */     float height = (float)frame.getHeight();
/*     */     
/*     */ 
/* 168 */     double theta = 6.283185307179586D / num_points;
/* 169 */     double angle = -theta / 2.0D;
/* 170 */     thePolygon.reset();
/* 171 */     thePolygon.moveTo(0.0F, 0.0F);
/* 172 */     float delta_x = width * (float)Math.cos(angle);
/* 173 */     float delta_y = width * (float)Math.sin(angle);
/* 174 */     Point2D prev = thePolygon.getCurrentPoint();
/* 175 */     thePolygon.lineTo((float)prev.getX() + delta_x, (float)prev.getY() + delta_y);
/* 176 */     for (int i = 1; i < num_points; i++)
/*     */     {
/* 178 */       angle += theta;
/* 179 */       delta_x = width * (float)Math.cos(angle);
/* 180 */       delta_y = width * (float)Math.sin(angle);
/* 181 */       prev = thePolygon.getCurrentPoint();
/* 182 */       thePolygon.lineTo((float)prev.getX() + delta_x, (float)prev.getY() + delta_y);
/* 183 */       angle -= theta * 2.0D;
/* 184 */       delta_x = width * (float)Math.cos(angle);
/* 185 */       delta_y = width * (float)Math.sin(angle);
/* 186 */       prev = thePolygon.getCurrentPoint();
/* 187 */       thePolygon.lineTo((float)prev.getX() + delta_x, (float)prev.getY() + delta_y);
/*     */     }
/* 189 */     thePolygon.closePath();
/*     */     
/*     */ 
/* 192 */     Rectangle2D r = thePolygon.getBounds2D();
/* 193 */     double scale_x = width / r.getWidth();
/* 194 */     double scale_y = height / r.getHeight();
/*     */     
/* 196 */     float translationX = (float)(r.getMinX() + r.getWidth() / 2.0D);
/* 197 */     float translationY = (float)(r.getMinY() + r.getHeight() / 2.0D);
/*     */     
/* 199 */     AffineTransform at = AffineTransform.getScaleInstance(scale_x, scale_y);
/* 200 */     at.translate(-translationX, -translationY);
/*     */     
/* 202 */     Shape shape = at.createTransformedShape(thePolygon);
/* 203 */     return shape;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/util/VertexShapeFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */