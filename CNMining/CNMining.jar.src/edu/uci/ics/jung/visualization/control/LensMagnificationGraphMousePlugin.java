/*    */ package edu.uci.ics.jung.visualization.control;
/*    */ 
/*    */ import edu.uci.ics.jung.visualization.Layer;
/*    */ import edu.uci.ics.jung.visualization.MultiLayerTransformer;
/*    */ import edu.uci.ics.jung.visualization.RenderContext;
/*    */ import edu.uci.ics.jung.visualization.VisualizationViewer;
/*    */ import edu.uci.ics.jung.visualization.transform.LensTransformer;
/*    */ import edu.uci.ics.jung.visualization.transform.MutableTransformer;
/*    */ import java.awt.event.MouseEvent;
/*    */ import java.awt.event.MouseWheelEvent;
/*    */ import java.awt.event.MouseWheelListener;
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
/*    */ public class LensMagnificationGraphMousePlugin
/*    */   extends AbstractGraphMousePlugin
/*    */   implements MouseWheelListener
/*    */ {
/* 32 */   protected float floor = 1.0F;
/*    */   
/* 34 */   protected float ceiling = 5.0F;
/*    */   
/* 36 */   protected float delta = 0.2F;
/*    */   
/*    */ 
/*    */ 
/*    */   public LensMagnificationGraphMousePlugin()
/*    */   {
/* 42 */     this(2);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public LensMagnificationGraphMousePlugin(float floor, float ceiling, float delta)
/*    */   {
/* 50 */     this(2, floor, ceiling, delta);
/*    */   }
/*    */   
/*    */ 
/* 54 */   public LensMagnificationGraphMousePlugin(int modifiers) { this(modifiers, 1.0F, 4.0F, 0.2F); }
/*    */   
/*    */   public LensMagnificationGraphMousePlugin(int modifiers, float floor, float ceiling, float delta) {
/* 57 */     super(modifiers);
/* 58 */     this.floor = floor;
/* 59 */     this.ceiling = ceiling;
/* 60 */     this.delta = delta;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean checkModifiers(MouseEvent e)
/*    */   {
/* 66 */     return (e.getModifiers() & this.modifiers) != 0;
/*    */   }
/*    */   
/*    */   private void changeMagnification(MutableTransformer transformer, float delta) {
/* 70 */     if ((transformer instanceof LensTransformer)) {
/* 71 */       LensTransformer ht = (LensTransformer)transformer;
/* 72 */       float magnification = ht.getMagnification() + delta;
/* 73 */       magnification = Math.max(this.floor, magnification);
/* 74 */       magnification = Math.min(magnification, this.ceiling);
/* 75 */       ht.setMagnification(magnification);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void mouseWheelMoved(MouseWheelEvent e)
/*    */   {
/* 83 */     boolean accepted = checkModifiers(e);
/* 84 */     float delta = this.delta;
/* 85 */     if (accepted == true) {
/* 86 */       VisualizationViewer vv = (VisualizationViewer)e.getSource();
/* 87 */       MutableTransformer modelTransformer = vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT);
/* 88 */       MutableTransformer viewTransformer = vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW);
/* 89 */       int amount = e.getWheelRotation();
/* 90 */       if (amount < 0) {
/* 91 */         delta = -delta;
/*    */       }
/* 93 */       changeMagnification(modelTransformer, delta);
/* 94 */       changeMagnification(viewTransformer, delta);
/* 95 */       vv.repaint();
/* 96 */       e.consume();
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/control/LensMagnificationGraphMousePlugin.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */