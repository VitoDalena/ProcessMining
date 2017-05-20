/*     */ package edu.uci.ics.jung.visualization.control;
/*     */ 
/*     */ import edu.uci.ics.jung.visualization.Layer;
/*     */ import edu.uci.ics.jung.visualization.MultiLayerTransformer;
/*     */ import edu.uci.ics.jung.visualization.RenderContext;
/*     */ import edu.uci.ics.jung.visualization.VisualizationViewer;
/*     */ import edu.uci.ics.jung.visualization.transform.LensTransformer;
/*     */ import edu.uci.ics.jung.visualization.transform.MutableTransformer;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import java.awt.geom.Point2D;
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
/*     */ public class LensTranslatingGraphMousePlugin
/*     */   extends TranslatingGraphMousePlugin
/*     */   implements MouseListener, MouseMotionListener
/*     */ {
/*     */   protected boolean dragOnLens;
/*     */   protected boolean dragOnEdge;
/*     */   protected double edgeOffset;
/*     */   
/*     */   public LensTranslatingGraphMousePlugin()
/*     */   {
/*  45 */     this(16);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public LensTranslatingGraphMousePlugin(int modifiers)
/*     */   {
/*  53 */     super(modifiers);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mousePressed(MouseEvent e)
/*     */   {
/*  63 */     VisualizationViewer vv = (VisualizationViewer)e.getSource();
/*  64 */     MutableTransformer vt = vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW);
/*  65 */     if ((vt instanceof LensTransformer)) {
/*  66 */       vt = ((LensTransformer)vt).getDelegate();
/*     */     }
/*  68 */     Point2D p = vt.inverseTransform(e.getPoint());
/*  69 */     boolean accepted = checkModifiers(e);
/*  70 */     if (accepted) {
/*  71 */       vv.setCursor(Cursor.getPredefinedCursor(13));
/*  72 */       testViewCenter(vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT), p);
/*  73 */       testViewCenter(vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW), p);
/*  74 */       vv.repaint();
/*     */     }
/*  76 */     super.mousePressed(e);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void setViewCenter(MutableTransformer transformer, Point2D point)
/*     */   {
/*  85 */     if ((transformer instanceof LensTransformer)) {
/*  86 */       LensTransformer ht = (LensTransformer)transformer;
/*     */       
/*  88 */       ht.setViewCenter(point);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void setViewRadius(MutableTransformer transformer, Point2D point)
/*     */   {
/*  98 */     if ((transformer instanceof LensTransformer)) {
/*  99 */       LensTransformer ht = (LensTransformer)transformer;
/*     */       
/* 101 */       double distanceFromCenter = ht.getDistanceFromCenter(point);
/* 102 */       ht.setViewRadius(distanceFromCenter + this.edgeOffset);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void testViewCenter(MutableTransformer transformer, Point2D point)
/*     */   {
/* 112 */     if ((transformer instanceof LensTransformer)) {
/* 113 */       LensTransformer ht = (LensTransformer)transformer;
/*     */       
/* 115 */       double distanceFromCenter = ht.getDistanceFromCenter(point);
/* 116 */       if (distanceFromCenter < 10.0D) {
/* 117 */         ht.setViewCenter(point);
/* 118 */         this.dragOnLens = true;
/* 119 */       } else if (Math.abs(distanceFromCenter - ht.getViewRadius()) < 10.0D) {
/* 120 */         this.edgeOffset = (ht.getViewRadius() - distanceFromCenter);
/* 121 */         ht.setViewRadius(distanceFromCenter + this.edgeOffset);
/* 122 */         this.dragOnEdge = true;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mouseReleased(MouseEvent e)
/*     */   {
/* 132 */     super.mouseReleased(e);
/* 133 */     this.dragOnLens = false;
/* 134 */     this.dragOnEdge = false;
/* 135 */     this.edgeOffset = 0.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mouseDragged(MouseEvent e)
/*     */   {
/* 144 */     VisualizationViewer vv = (VisualizationViewer)e.getSource();
/* 145 */     MutableTransformer vt = vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW);
/* 146 */     if ((vt instanceof LensTransformer)) {
/* 147 */       vt = ((LensTransformer)vt).getDelegate();
/*     */     }
/* 149 */     Point2D p = vt.inverseTransform(e.getPoint());
/* 150 */     boolean accepted = checkModifiers(e);
/*     */     
/* 152 */     if (accepted) {
/* 153 */       MutableTransformer modelTransformer = vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT);
/* 154 */       vv.setCursor(Cursor.getPredefinedCursor(13));
/* 155 */       if (this.dragOnLens) {
/* 156 */         setViewCenter(modelTransformer, p);
/* 157 */         setViewCenter(vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW), p);
/* 158 */         e.consume();
/* 159 */         vv.repaint();
/*     */       }
/* 161 */       else if (this.dragOnEdge)
/*     */       {
/* 163 */         setViewRadius(modelTransformer, p);
/* 164 */         setViewRadius(vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW), p);
/* 165 */         e.consume();
/* 166 */         vv.repaint();
/*     */       }
/*     */       else
/*     */       {
/* 170 */         MutableTransformer mt = vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT);
/* 171 */         Point2D iq = vt.inverseTransform(this.down);
/* 172 */         iq = mt.inverseTransform(iq);
/* 173 */         Point2D ip = vt.inverseTransform(e.getPoint());
/* 174 */         ip = mt.inverseTransform(ip);
/* 175 */         float dx = (float)(ip.getX() - iq.getX());
/* 176 */         float dy = (float)(ip.getY() - iq.getY());
/*     */         
/* 178 */         modelTransformer.translate(dx, dy);
/* 179 */         this.down.x = e.getX();
/* 180 */         this.down.y = e.getY();
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/control/LensTranslatingGraphMousePlugin.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */