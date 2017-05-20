/*     */ package edu.uci.ics.jung.visualization.control;
/*     */ 
/*     */ import edu.uci.ics.jung.visualization.VisualizationViewer;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseWheelEvent;
/*     */ import java.awt.event.MouseWheelListener;
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
/*     */ 
/*     */ public class ScalingGraphMousePlugin
/*     */   extends AbstractGraphMousePlugin
/*     */   implements MouseWheelListener
/*     */ {
/*  34 */   protected float in = 1.1F;
/*     */   
/*     */ 
/*     */ 
/*  38 */   protected float out = 0.9090909F;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  43 */   protected boolean zoomAtMouse = true;
/*     */   
/*     */ 
/*     */   protected ScalingControl scaler;
/*     */   
/*     */ 
/*     */   public ScalingGraphMousePlugin(ScalingControl scaler, int modifiers)
/*     */   {
/*  51 */     this(scaler, modifiers, 1.1F, 0.9090909F);
/*     */   }
/*     */   
/*     */   public ScalingGraphMousePlugin(ScalingControl scaler, int modifiers, float in, float out) {
/*  55 */     super(modifiers);
/*  56 */     this.scaler = scaler;
/*  57 */     this.in = in;
/*  58 */     this.out = out;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setZoomAtMouse(boolean zoomAtMouse)
/*     */   {
/*  64 */     this.zoomAtMouse = zoomAtMouse;
/*     */   }
/*     */   
/*     */   public boolean checkModifiers(MouseEvent e) {
/*  68 */     return (e.getModifiers() == this.modifiers) || ((e.getModifiers() & this.modifiers) != 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mouseWheelMoved(MouseWheelEvent e)
/*     */   {
/*  76 */     boolean accepted = checkModifiers(e);
/*  77 */     if (accepted == true) {
/*  78 */       VisualizationViewer vv = (VisualizationViewer)e.getSource();
/*  79 */       Point2D mouse = e.getPoint();
/*  80 */       Point2D center = vv.getCenter();
/*  81 */       int amount = e.getWheelRotation();
/*  82 */       if (this.zoomAtMouse) {
/*  83 */         if (amount > 0) {
/*  84 */           this.scaler.scale(vv, this.in, mouse);
/*  85 */         } else if (amount < 0) {
/*  86 */           this.scaler.scale(vv, this.out, mouse);
/*     */         }
/*     */       }
/*  89 */       else if (amount > 0) {
/*  90 */         this.scaler.scale(vv, this.in, center);
/*  91 */       } else if (amount < 0) {
/*  92 */         this.scaler.scale(vv, this.out, center);
/*     */       }
/*     */       
/*  95 */       e.consume();
/*  96 */       vv.repaint();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public float getIn()
/*     */   {
/* 103 */     return this.in;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setIn(float in)
/*     */   {
/* 109 */     this.in = in;
/*     */   }
/*     */   
/*     */ 
/*     */   public float getOut()
/*     */   {
/* 115 */     return this.out;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setOut(float out)
/*     */   {
/* 121 */     this.out = out;
/*     */   }
/*     */   
/*     */   public ScalingControl getScaler() {
/* 125 */     return this.scaler;
/*     */   }
/*     */   
/*     */   public void setScaler(ScalingControl scaler) {
/* 129 */     this.scaler = scaler;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/control/ScalingGraphMousePlugin.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */