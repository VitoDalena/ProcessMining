/*     */ package edu.uci.ics.jung.visualization.control;
/*     */ 
/*     */ import edu.uci.ics.jung.visualization.Layer;
/*     */ import edu.uci.ics.jung.visualization.MultiLayerTransformer;
/*     */ import edu.uci.ics.jung.visualization.RenderContext;
/*     */ import edu.uci.ics.jung.visualization.VisualizationModel;
/*     */ import edu.uci.ics.jung.visualization.VisualizationServer.Paintable;
/*     */ import edu.uci.ics.jung.visualization.VisualizationViewer;
/*     */ import edu.uci.ics.jung.visualization.transform.MutableAffineTransformer;
/*     */ import edu.uci.ics.jung.visualization.transform.MutableTransformer;
/*     */ import edu.uci.ics.jung.visualization.transform.shape.ShapeTransformer;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
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
/*     */ public class SatelliteVisualizationViewer<V, E>
/*     */   extends VisualizationViewer<V, E>
/*     */ {
/*     */   protected VisualizationViewer<V, E> master;
/*     */   
/*     */   public SatelliteVisualizationViewer(VisualizationViewer<V, E> master)
/*     */   {
/*  52 */     this(master, master.getModel());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SatelliteVisualizationViewer(VisualizationViewer<V, E> master, Dimension preferredSize)
/*     */   {
/*  62 */     this(master, master.getModel(), preferredSize);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected SatelliteVisualizationViewer(VisualizationViewer<V, E> master, VisualizationModel<V, E> model)
/*     */   {
/*  72 */     this(master, model, new Dimension(300, 300));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected SatelliteVisualizationViewer(VisualizationViewer<V, E> master, VisualizationModel<V, E> model, Dimension preferredSize)
/*     */   {
/*  84 */     super(model, preferredSize);
/*  85 */     this.master = master;
/*     */     
/*     */ 
/*  88 */     ModalGraphMouse gm = new ModalSatelliteGraphMouse();
/*  89 */     setGraphMouse(gm);
/*     */     
/*     */ 
/*  92 */     addPreRenderPaintable(new ViewLens(this, master));
/*     */     
/*     */ 
/*     */ 
/*  96 */     AffineTransform modelLayoutTransform = new AffineTransform(master.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT).getTransform());
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 101 */     getRenderContext().getMultiLayerTransformer().setTransformer(Layer.LAYOUT, new MutableAffineTransformer(modelLayoutTransform));
/*     */     
/*     */ 
/* 104 */     master.addChangeListener(this);
/*     */     
/*     */ 
/* 107 */     setPickedVertexState(master.getPickedVertexState());
/* 108 */     setPickedEdgeState(master.getPickedEdgeState());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public VisualizationViewer<V, E> getMaster()
/*     */   {
/* 115 */     return this.master;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   static class ViewLens<V, E>
/*     */     implements VisualizationServer.Paintable
/*     */   {
/*     */     VisualizationViewer<V, E> master;
/*     */     
/*     */ 
/*     */     VisualizationViewer<V, E> vv;
/*     */     
/*     */ 
/*     */ 
/*     */     public ViewLens(VisualizationViewer<V, E> vv, VisualizationViewer<V, E> master)
/*     */     {
/* 132 */       this.vv = vv;
/* 133 */       this.master = master;
/*     */     }
/*     */     
/* 136 */     public void paint(Graphics g) { ShapeTransformer masterViewTransformer = this.master.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW);
/*     */       
/* 138 */       ShapeTransformer masterLayoutTransformer = this.master.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT);
/* 139 */       ShapeTransformer vvLayoutTransformer = this.vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT);
/*     */       
/* 141 */       Shape lens = this.master.getBounds();
/* 142 */       lens = masterViewTransformer.inverseTransform(lens);
/* 143 */       lens = masterLayoutTransformer.inverseTransform(lens);
/* 144 */       lens = vvLayoutTransformer.transform(lens);
/* 145 */       Graphics2D g2d = (Graphics2D)g;
/* 146 */       Color old = g.getColor();
/* 147 */       Color lensColor = this.master.getBackground();
/* 148 */       this.vv.setBackground(lensColor.darker());
/* 149 */       g.setColor(lensColor);
/* 150 */       g2d.fill(lens);
/* 151 */       g.setColor(Color.gray);
/* 152 */       g2d.draw(lens);
/* 153 */       g.setColor(old);
/*     */     }
/*     */     
/*     */     public boolean useTransform() {
/* 157 */       return true;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/control/SatelliteVisualizationViewer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */