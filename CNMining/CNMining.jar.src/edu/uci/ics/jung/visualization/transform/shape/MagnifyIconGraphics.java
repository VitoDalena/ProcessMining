/*    */ package edu.uci.ics.jung.visualization.transform.shape;
/*    */ 
/*    */ import edu.uci.ics.jung.visualization.transform.BidirectionalTransformer;
/*    */ import java.awt.Component;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.Shape;
/*    */ import java.awt.geom.AffineTransform;
/*    */ import java.awt.geom.Area;
/*    */ import java.awt.geom.Rectangle2D;
/*    */ import java.awt.geom.Rectangle2D.Double;
/*    */ import javax.swing.Icon;
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
/*    */ public class MagnifyIconGraphics
/*    */   extends TransformingFlatnessGraphics
/*    */ {
/*    */   public MagnifyIconGraphics(BidirectionalTransformer transformer)
/*    */   {
/* 35 */     this(transformer, null);
/*    */   }
/*    */   
/*    */   public MagnifyIconGraphics(BidirectionalTransformer transformer, Graphics2D delegate) {
/* 39 */     super(transformer, delegate);
/*    */   }
/*    */   
/*    */   public void draw(Icon icon, Component c, Shape clip, int x, int y)
/*    */   {
/* 44 */     if ((this.transformer instanceof MagnifyShapeTransformer)) {
/* 45 */       MagnifyShapeTransformer mst = (MagnifyShapeTransformer)this.transformer;
/* 46 */       int w = icon.getIconWidth();
/* 47 */       int h = icon.getIconHeight();
/* 48 */       Rectangle2D r = new Rectangle2D.Double(x - w / 2, y - h / 2, w, h);
/* 49 */       Shape lens = mst.getLensShape();
/* 50 */       if (lens.intersects(r))
/*    */       {
/* 52 */         Rectangle2D s = mst.magnify(r).getBounds2D();
/* 53 */         if (lens.intersects(s)) {
/* 54 */           clip = mst.transform(clip);
/* 55 */           double sx = s.getWidth() / r.getWidth();
/* 56 */           double sy = s.getHeight() / r.getHeight();
/*    */           
/* 58 */           AffineTransform old = this.delegate.getTransform();
/* 59 */           AffineTransform xform = new AffineTransform(old);
/* 60 */           xform.translate(s.getMinX(), s.getMinY());
/* 61 */           xform.scale(sx, sy);
/* 62 */           xform.translate(-s.getMinX(), -s.getMinY());
/* 63 */           Shape oldClip = this.delegate.getClip();
/* 64 */           this.delegate.clip(clip);
/* 65 */           this.delegate.setTransform(xform);
/* 66 */           icon.paintIcon(c, this.delegate, (int)s.getMinX(), (int)s.getMinY());
/* 67 */           this.delegate.setTransform(old);
/* 68 */           this.delegate.setClip(oldClip);
/*    */         }
/*    */         else
/*    */         {
/* 72 */           Shape oldClip = this.delegate.getClip();
/* 73 */           Area viewBounds = new Area(oldClip);
/* 74 */           viewBounds.subtract(new Area(lens));
/* 75 */           this.delegate.setClip(viewBounds);
/* 76 */           icon.paintIcon(c, this.delegate, (int)r.getMinX(), (int)r.getMinY());
/* 77 */           this.delegate.setClip(oldClip);
/*    */         }
/*    */       }
/*    */       else {
/* 81 */         icon.paintIcon(c, this.delegate, (int)r.getMinX(), (int)r.getMinY());
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/transform/shape/MagnifyIconGraphics.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */