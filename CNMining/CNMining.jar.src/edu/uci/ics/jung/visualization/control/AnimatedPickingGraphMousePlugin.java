/*     */ package edu.uci.ics.jung.visualization.control;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.layout.GraphElementAccessor;
/*     */ import edu.uci.ics.jung.algorithms.layout.Layout;
/*     */ import edu.uci.ics.jung.visualization.Layer;
/*     */ import edu.uci.ics.jung.visualization.MultiLayerTransformer;
/*     */ import edu.uci.ics.jung.visualization.RenderContext;
/*     */ import edu.uci.ics.jung.visualization.VisualizationViewer;
/*     */ import edu.uci.ics.jung.visualization.picking.PickedState;
/*     */ import edu.uci.ics.jung.visualization.transform.MutableTransformer;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import java.awt.geom.Point2D;
/*     */ import javax.swing.JComponent;
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
/*     */ public class AnimatedPickingGraphMousePlugin<V, E>
/*     */   extends AbstractGraphMousePlugin
/*     */   implements MouseListener, MouseMotionListener
/*     */ {
/*     */   protected V vertex;
/*     */   
/*     */   public AnimatedPickingGraphMousePlugin()
/*     */   {
/*  51 */     this(18);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public AnimatedPickingGraphMousePlugin(int selectionModifiers)
/*     */   {
/*  59 */     super(selectionModifiers);
/*  60 */     this.cursor = Cursor.getPredefinedCursor(12);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mousePressed(MouseEvent e)
/*     */   {
/*  69 */     if (e.getModifiers() == this.modifiers) {
/*  70 */       VisualizationViewer<V, E> vv = (VisualizationViewer)e.getSource();
/*  71 */       GraphElementAccessor<V, E> pickSupport = vv.getPickSupport();
/*  72 */       PickedState<V> pickedVertexState = vv.getPickedVertexState();
/*  73 */       Layout<V, E> layout = vv.getGraphLayout();
/*  74 */       if ((pickSupport != null) && (pickedVertexState != null))
/*     */       {
/*  76 */         Point2D p = e.getPoint();
/*  77 */         this.vertex = pickSupport.getVertex(layout, p.getX(), p.getY());
/*  78 */         if ((this.vertex != null) && 
/*  79 */           (!pickedVertexState.isPicked(this.vertex))) {
/*  80 */           pickedVertexState.clear();
/*  81 */           pickedVertexState.pick(this.vertex, true);
/*     */         }
/*     */       }
/*     */       
/*  85 */       e.consume();
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
/*     */   public void mouseReleased(MouseEvent e)
/*     */   {
/*  99 */     if (e.getModifiers() == this.modifiers) {
/* 100 */       final VisualizationViewer<V, E> vv = (VisualizationViewer)e.getSource();
/* 101 */       if (this.vertex != null) {
/* 102 */         Layout<V, E> layout = vv.getGraphLayout();
/* 103 */         Point2D q = (Point2D)layout.transform(this.vertex);
/* 104 */         Point2D lvc = vv.getRenderContext().getMultiLayerTransformer().inverseTransform(vv.getCenter());
/* 105 */         final double dx = (lvc.getX() - q.getX()) / 10.0D;
/* 106 */         double dy = (lvc.getY() - q.getY()) / 10.0D;
/*     */         
/* 108 */         Runnable animator = new Runnable()
/*     */         {
/*     */           public void run() {
/* 111 */             for (int i = 0; i < 10; i++) {
/* 112 */               vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT).translate(dx, this.val$dy);
/*     */               try {
/* 114 */                 Thread.sleep(100L);
/*     */               }
/*     */               catch (InterruptedException ex) {}
/*     */             }
/*     */           }
/* 119 */         };
/* 120 */         Thread thread = new Thread(animator);
/* 121 */         thread.start();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void mouseClicked(MouseEvent e) {}
/*     */   
/*     */ 
/*     */   public void mouseEntered(MouseEvent e)
/*     */   {
/* 133 */     JComponent c = (JComponent)e.getSource();
/* 134 */     c.setCursor(this.cursor);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void mouseExited(MouseEvent e)
/*     */   {
/* 141 */     JComponent c = (JComponent)e.getSource();
/* 142 */     c.setCursor(Cursor.getPredefinedCursor(0));
/*     */   }
/*     */   
/*     */   public void mouseMoved(MouseEvent e) {}
/*     */   
/*     */   public void mouseDragged(MouseEvent arg0) {}
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/control/AnimatedPickingGraphMousePlugin.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */