/*     */ package edu.uci.ics.jung.visualization.annotations;
/*     */ 
/*     */ import edu.uci.ics.jung.visualization.MultiLayerTransformer;
/*     */ import edu.uci.ics.jung.visualization.RenderContext;
/*     */ import edu.uci.ics.jung.visualization.VisualizationServer.Paintable;
/*     */ import edu.uci.ics.jung.visualization.VisualizationViewer;
/*     */ import edu.uci.ics.jung.visualization.control.AbstractGraphMousePlugin;
/*     */ import java.awt.Color;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Shape;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D.Float;
/*     */ import java.awt.geom.RectangularShape;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JOptionPane;
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
/*     */ public class AnnotatingGraphMousePlugin<V, E>
/*     */   extends AbstractGraphMousePlugin
/*     */   implements MouseListener, MouseMotionListener
/*     */ {
/*     */   protected int additionalModifiers;
/*  53 */   protected RectangularShape rectangularShape = new Rectangle2D.Float();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected VisualizationServer.Paintable lensPaintable;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected AnnotationManager annotationManager;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  68 */   protected Color annotationColor = Color.cyan;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  73 */   protected Annotation.Layer layer = Annotation.Layer.LOWER;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean fill;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected MultiLayerTransformer basicTransformer;
/*     */   
/*     */ 
/*     */ 
/*     */   protected RenderContext<V, E> rc;
/*     */   
/*     */ 
/*     */ 
/*  91 */   protected boolean added = false;
/*     */   
/*     */ 
/*     */ 
/*     */   public AnnotatingGraphMousePlugin(RenderContext<V, E> rc)
/*     */   {
/*  97 */     this(rc, 16, 17);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AnnotatingGraphMousePlugin(RenderContext<V, E> rc, int selectionModifiers, int additionalModifiers)
/*     */   {
/* 108 */     super(selectionModifiers);
/* 109 */     this.rc = rc;
/* 110 */     this.basicTransformer = rc.getMultiLayerTransformer();
/* 111 */     this.additionalModifiers = additionalModifiers;
/* 112 */     this.lensPaintable = new LensPaintable();
/*     */     
/* 114 */     this.annotationManager = new AnnotationManager(rc);
/* 115 */     this.cursor = Cursor.getPredefinedCursor(12);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Color getAnnotationColor()
/*     */   {
/* 122 */     return this.annotationColor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setAnnotationColor(Color lensColor)
/*     */   {
/* 129 */     this.annotationColor = lensColor;
/*     */   }
/*     */   
/*     */ 
/*     */   class LensPaintable
/*     */     implements VisualizationServer.Paintable
/*     */   {
/*     */     LensPaintable() {}
/*     */     
/*     */     public void paint(Graphics g)
/*     */     {
/* 140 */       Color oldColor = g.getColor();
/* 141 */       g.setColor(AnnotatingGraphMousePlugin.this.annotationColor);
/* 142 */       ((Graphics2D)g).draw(AnnotatingGraphMousePlugin.this.rectangularShape);
/* 143 */       g.setColor(oldColor);
/*     */     }
/*     */     
/*     */     public boolean useTransform() {
/* 147 */       return false;
/*     */     }
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
/*     */   public void mousePressed(MouseEvent e)
/*     */   {
/* 161 */     VisualizationViewer<V, E> vv = (VisualizationViewer)e.getSource();
/* 162 */     this.down = e.getPoint();
/*     */     
/* 164 */     if (!this.added) {
/* 165 */       vv.addPreRenderPaintable(this.annotationManager.getLowerAnnotationPaintable());
/* 166 */       vv.addPostRenderPaintable(this.annotationManager.getUpperAnnotationPaintable());
/* 167 */       this.added = true;
/*     */     }
/*     */     
/*     */ 
/* 171 */     if (e.isPopupTrigger()) {
/* 172 */       String annotationString = JOptionPane.showInputDialog(vv, "Annotation:");
/* 173 */       if ((annotationString != null) && (annotationString.length() > 0)) {
/* 174 */         Point2D p = vv.getRenderContext().getMultiLayerTransformer().inverseTransform(this.down);
/* 175 */         Annotation<String> annotation = new Annotation(annotationString, this.layer, this.annotationColor, this.fill, p);
/*     */         
/* 177 */         this.annotationManager.add(this.layer, annotation);
/*     */       }
/* 179 */     } else if (e.getModifiers() == this.additionalModifiers) {
/* 180 */       Annotation annotation = this.annotationManager.getAnnotation(this.down);
/* 181 */       this.annotationManager.remove(annotation);
/* 182 */     } else if (e.getModifiers() == this.modifiers) {
/* 183 */       this.rectangularShape.setFrameFromDiagonal(this.down, this.down);
/* 184 */       vv.addPostRenderPaintable(this.lensPaintable);
/*     */     }
/* 186 */     vv.repaint();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mouseReleased(MouseEvent e)
/*     */   {
/* 196 */     VisualizationViewer<V, E> vv = (VisualizationViewer)e.getSource();
/* 197 */     if (e.isPopupTrigger()) {
/* 198 */       String annotationString = JOptionPane.showInputDialog(vv, "Annotation:");
/* 199 */       if ((annotationString != null) && (annotationString.length() > 0)) {
/* 200 */         Point2D p = vv.getRenderContext().getMultiLayerTransformer().inverseTransform(this.down);
/* 201 */         Annotation<String> annotation = new Annotation(annotationString, this.layer, this.annotationColor, this.fill, p);
/*     */         
/* 203 */         this.annotationManager.add(this.layer, annotation);
/*     */       }
/* 205 */     } else if ((e.getModifiers() == this.modifiers) && 
/* 206 */       (this.down != null)) {
/* 207 */       Point2D out = e.getPoint();
/* 208 */       RectangularShape arect = (RectangularShape)this.rectangularShape.clone();
/* 209 */       arect.setFrameFromDiagonal(this.down, out);
/* 210 */       Shape s = vv.getRenderContext().getMultiLayerTransformer().inverseTransform(arect);
/* 211 */       Annotation<Shape> annotation = new Annotation(s, this.layer, this.annotationColor, this.fill, out);
/*     */       
/* 213 */       this.annotationManager.add(this.layer, annotation);
/*     */     }
/*     */     
/* 216 */     this.down = null;
/* 217 */     vv.removePostRenderPaintable(this.lensPaintable);
/* 218 */     vv.repaint();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mouseDragged(MouseEvent e)
/*     */   {
/* 229 */     VisualizationViewer<V, E> vv = (VisualizationViewer)e.getSource();
/*     */     
/* 231 */     Point2D out = e.getPoint();
/* 232 */     if (e.getModifiers() == this.additionalModifiers) {
/* 233 */       this.rectangularShape.setFrameFromDiagonal(this.down, out);
/*     */     }
/* 235 */     else if (e.getModifiers() == this.modifiers) {
/* 236 */       this.rectangularShape.setFrameFromDiagonal(this.down, out);
/*     */     }
/*     */     
/* 239 */     this.rectangularShape.setFrameFromDiagonal(this.down, out);
/* 240 */     vv.repaint();
/*     */   }
/*     */   
/*     */   public void mouseClicked(MouseEvent e) {}
/*     */   
/*     */   public void mouseEntered(MouseEvent e)
/*     */   {
/* 247 */     JComponent c = (JComponent)e.getSource();
/* 248 */     c.setCursor(this.cursor);
/*     */   }
/*     */   
/*     */   public void mouseExited(MouseEvent e) {
/* 252 */     JComponent c = (JComponent)e.getSource();
/* 253 */     c.setCursor(Cursor.getPredefinedCursor(0));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void mouseMoved(MouseEvent e) {}
/*     */   
/*     */ 
/*     */   public RectangularShape getRectangularShape()
/*     */   {
/* 263 */     return this.rectangularShape;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setRectangularShape(RectangularShape rect)
/*     */   {
/* 270 */     this.rectangularShape = rect;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Annotation.Layer getLayer()
/*     */   {
/* 277 */     return this.layer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setLayer(Annotation.Layer layer)
/*     */   {
/* 284 */     this.layer = layer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isFill()
/*     */   {
/* 291 */     return this.fill;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setFill(boolean fill)
/*     */   {
/* 298 */     this.fill = fill;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/annotations/AnnotatingGraphMousePlugin.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */