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
/*     */ public class TranslatingGraphMousePlugin
/*     */   extends AbstractGraphMousePlugin
/*     */   implements MouseListener, MouseMotionListener
/*     */ {
/*     */   public TranslatingGraphMousePlugin()
/*     */   {
/*  39 */     this(16);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public TranslatingGraphMousePlugin(int modifiers)
/*     */   {
/*  47 */     super(modifiers);
/*  48 */     this.cursor = Cursor.getPredefinedCursor(13);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mousePressed(MouseEvent e)
/*     */   {
/*  58 */     VisualizationViewer vv = (VisualizationViewer)e.getSource();
/*  59 */     boolean accepted = checkModifiers(e);
/*  60 */     this.down = e.getPoint();
/*  61 */     if (accepted) {
/*  62 */       vv.setCursor(this.cursor);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mouseReleased(MouseEvent e)
/*     */   {
/*  71 */     VisualizationViewer vv = (VisualizationViewer)e.getSource();
/*  72 */     this.down = null;
/*  73 */     vv.setCursor(Cursor.getPredefinedCursor(0));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mouseDragged(MouseEvent e)
/*     */   {
/*  82 */     VisualizationViewer vv = (VisualizationViewer)e.getSource();
/*  83 */     boolean accepted = checkModifiers(e);
/*  84 */     if (accepted) {
/*  85 */       MutableTransformer modelTransformer = vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT);
/*  86 */       vv.setCursor(this.cursor);
/*     */       try {
/*  88 */         Point2D q = vv.getRenderContext().getMultiLayerTransformer().inverseTransform(this.down);
/*  89 */         Point2D p = vv.getRenderContext().getMultiLayerTransformer().inverseTransform(e.getPoint());
/*  90 */         float dx = (float)(p.getX() - q.getX());
/*  91 */         float dy = (float)(p.getY() - q.getY());
/*     */         
/*  93 */         modelTransformer.translate(dx, dy);
/*  94 */         this.down.x = e.getX();
/*  95 */         this.down.y = e.getY();
/*     */       } catch (RuntimeException ex) {
/*  97 */         System.err.println("down = " + this.down + ", e = " + e);
/*  98 */         throw ex;
/*     */       }
/*     */       
/* 101 */       e.consume();
/* 102 */       vv.repaint();
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


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/control/TranslatingGraphMousePlugin.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */