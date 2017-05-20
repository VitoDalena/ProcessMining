/*     */ package edu.uci.ics.jung.visualization.control;
/*     */ 
/*     */ import edu.uci.ics.jung.visualization.Layer;
/*     */ import edu.uci.ics.jung.visualization.MultiLayerTransformer;
/*     */ import edu.uci.ics.jung.visualization.RenderContext;
/*     */ import edu.uci.ics.jung.visualization.VisualizationViewer;
/*     */ import edu.uci.ics.jung.visualization.transform.MutableTransformer;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.io.PrintStream;
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
/*     */ public class ViewTranslatingGraphMousePlugin
/*     */   extends AbstractGraphMousePlugin
/*     */   implements MouseListener, MouseMotionListener
/*     */ {
/*     */   public ViewTranslatingGraphMousePlugin()
/*     */   {
/*  40 */     this(16);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ViewTranslatingGraphMousePlugin(int modifiers)
/*     */   {
/*  48 */     super(modifiers);
/*  49 */     this.cursor = Cursor.getPredefinedCursor(13);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mousePressed(MouseEvent e)
/*     */   {
/*  59 */     VisualizationViewer<?, ?> vv = (VisualizationViewer)e.getSource();
/*  60 */     boolean accepted = checkModifiers(e);
/*  61 */     this.down = e.getPoint();
/*  62 */     if (accepted) {
/*  63 */       vv.setCursor(this.cursor);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mouseReleased(MouseEvent e)
/*     */   {
/*  72 */     VisualizationViewer<?, ?> vv = (VisualizationViewer)e.getSource();
/*  73 */     this.down = null;
/*  74 */     vv.setCursor(Cursor.getPredefinedCursor(0));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mouseDragged(MouseEvent e)
/*     */   {
/*  83 */     VisualizationViewer<?, ?> vv = (VisualizationViewer)e.getSource();
/*  84 */     boolean accepted = checkModifiers(e);
/*  85 */     if (accepted) {
/*  86 */       MutableTransformer viewTransformer = vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW);
/*  87 */       vv.setCursor(this.cursor);
/*     */       try {
/*  89 */         Point2D q = viewTransformer.inverseTransform(this.down);
/*  90 */         Point2D p = viewTransformer.inverseTransform(e.getPoint());
/*  91 */         float dx = (float)(p.getX() - q.getX());
/*  92 */         float dy = (float)(p.getY() - q.getY());
/*     */         
/*  94 */         viewTransformer.translate(dx, dy);
/*  95 */         this.down.x = e.getX();
/*  96 */         this.down.y = e.getY();
/*     */       } catch (RuntimeException ex) {
/*  98 */         System.err.println("down = " + this.down + ", e = " + e);
/*  99 */         throw ex;
/*     */       }
/*     */       
/* 102 */       e.consume();
/*     */     }
/*     */   }
/*     */   
/*     */   public void mouseClicked(MouseEvent e) {}
/*     */   
/*     */   public void mouseEntered(MouseEvent e) {}
/*     */   
/*     */   public void mouseExited(MouseEvent e) {}
/*     */   
/*     */   public void mouseMoved(MouseEvent e) {}
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/control/ViewTranslatingGraphMousePlugin.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */