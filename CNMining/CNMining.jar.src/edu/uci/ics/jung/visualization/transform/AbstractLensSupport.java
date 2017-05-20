/*     */ package edu.uci.ics.jung.visualization.transform;
/*     */ 
/*     */ import edu.uci.ics.jung.visualization.VisualizationServer.Paintable;
/*     */ import edu.uci.ics.jung.visualization.VisualizationViewer;
/*     */ import edu.uci.ics.jung.visualization.VisualizationViewer.GraphMouse;
/*     */ import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.geom.RectangularShape;
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
/*     */ public abstract class AbstractLensSupport<V, E>
/*     */   implements LensSupport
/*     */ {
/*     */   protected VisualizationViewer<V, E> vv;
/*     */   protected VisualizationViewer.GraphMouse graphMouse;
/*     */   protected LensTransformer lensTransformer;
/*     */   protected ModalGraphMouse lensGraphMouse;
/*     */   protected Lens lens;
/*     */   protected LensControls lensControls;
/*     */   protected String defaultToolTipText;
/*     */   protected static final String instructions = "<html><center>Mouse-Drag the Lens center to move it<p>Mouse-Drag the Lens edge to resize it<p>Ctrl+MouseWheel to change magnification</center></html>";
/*     */   
/*     */   public AbstractLensSupport(VisualizationViewer<V, E> vv, ModalGraphMouse lensGraphMouse)
/*     */   {
/*  53 */     this.vv = vv;
/*  54 */     this.graphMouse = vv.getGraphMouse();
/*  55 */     this.defaultToolTipText = vv.getToolTipText();
/*  56 */     this.lensGraphMouse = lensGraphMouse;
/*     */   }
/*     */   
/*     */   public void activate(boolean state) {
/*  60 */     if (state) activate(); else
/*  61 */       deactivate();
/*     */   }
/*     */   
/*     */   public LensTransformer getLensTransformer() {
/*  65 */     return this.lensTransformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ModalGraphMouse getGraphMouse()
/*     */   {
/*  72 */     return this.lensGraphMouse;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static class Lens
/*     */     implements VisualizationServer.Paintable
/*     */   {
/*     */     LensTransformer lensTransformer;
/*     */     
/*     */     RectangularShape lensShape;
/*     */     
/*  84 */     Paint paint = Color.decode("0xdddddd");
/*     */     
/*     */     public Lens(LensTransformer lensTransformer) {
/*  87 */       this.lensTransformer = lensTransformer;
/*  88 */       this.lensShape = lensTransformer.getLensShape();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public Paint getPaint()
/*     */     {
/*  95 */       return this.paint;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public void setPaint(Paint paint)
/*     */     {
/* 102 */       this.paint = paint;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void paint(Graphics g)
/*     */     {
/* 111 */       Graphics2D g2d = (Graphics2D)g;
/* 112 */       g2d.setPaint(this.paint);
/* 113 */       g2d.fill(this.lensShape);
/*     */     }
/*     */     
/*     */     public boolean useTransform() {
/* 117 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static class LensControls
/*     */     implements VisualizationServer.Paintable
/*     */   {
/*     */     LensTransformer lensTransformer;
/*     */     
/*     */     RectangularShape lensShape;
/*     */     
/* 130 */     Paint paint = Color.gray;
/*     */     
/*     */     public LensControls(LensTransformer lensTransformer) {
/* 133 */       this.lensTransformer = lensTransformer;
/* 134 */       this.lensShape = lensTransformer.getLensShape();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public Paint getPaint()
/*     */     {
/* 141 */       return this.paint;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public void setPaint(Paint paint)
/*     */     {
/* 148 */       this.paint = paint;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void paint(Graphics g)
/*     */     {
/* 157 */       Graphics2D g2d = (Graphics2D)g;
/* 158 */       g2d.setPaint(this.paint);
/* 159 */       g2d.draw(this.lensShape);
/* 160 */       int centerX = (int)Math.round(this.lensShape.getCenterX());
/* 161 */       int centerY = (int)Math.round(this.lensShape.getCenterY());
/* 162 */       g.drawOval(centerX - 10, centerY - 10, 20, 20);
/*     */     }
/*     */     
/*     */     public boolean useTransform() {
/* 166 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Lens getLens()
/*     */   {
/* 174 */     return this.lens;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setLens(Lens lens)
/*     */   {
/* 181 */     this.lens = lens;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public LensControls getLensControls()
/*     */   {
/* 188 */     return this.lensControls;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setLensControls(LensControls lensControls)
/*     */   {
/* 195 */     this.lensControls = lensControls;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/transform/AbstractLensSupport.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */