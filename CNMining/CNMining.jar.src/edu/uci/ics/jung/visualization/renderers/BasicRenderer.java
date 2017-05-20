/*     */ package edu.uci.ics.jung.visualization.renderers;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.layout.Layout;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.visualization.RenderContext;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import javax.swing.JComponent;
/*     */ import org.apache.commons.collections15.Transformer;
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
/*     */ public class BasicRenderer<V, E>
/*     */   implements Renderer<V, E>
/*     */ {
/*  27 */   Renderer.Vertex<V, E> vertexRenderer = new BasicVertexRenderer();
/*  28 */   Renderer.VertexLabel<V, E> vertexLabelRenderer = new BasicVertexLabelRenderer();
/*  29 */   Renderer.Edge<V, E> edgeRenderer = new BasicEdgeRenderer();
/*     */   
/*  31 */   Renderer.EdgeLabel<V, E> edgeLabelRenderer = new BasicEdgeLabelRenderer();
/*     */   
/*     */   public void render(RenderContext<V, E> renderContext, Layout<V, E> layout)
/*     */   {
/*     */     try
/*     */     {
/*  37 */       for (E e : layout.getGraph().getEdges())
/*     */       {
/*  39 */         renderEdge(renderContext, layout, e);
/*     */         
/*     */ 
/*     */ 
/*  43 */         renderEdgeLabel(renderContext, layout, e);
/*     */       }
/*     */       
/*     */     }
/*     */     catch (ConcurrentModificationException cme)
/*     */     {
/*  49 */       renderContext.getScreenDevice().repaint();
/*     */     }
/*     */     
/*     */     try
/*     */     {
/*  54 */       for (V v : layout.getGraph().getVertices())
/*     */       {
/*  56 */         renderVertex(renderContext, layout, v);
/*     */         
/*     */ 
/*     */ 
/*  60 */         renderVertexLabel(renderContext, layout, v);
/*     */       }
/*     */       
/*     */     }
/*     */     catch (ConcurrentModificationException cme)
/*     */     {
/*  66 */       renderContext.getScreenDevice().repaint();
/*     */     }
/*     */   }
/*     */   
/*     */   public void renderVertex(RenderContext<V, E> rc, Layout<V, E> layout, V v) {
/*  71 */     this.vertexRenderer.paintVertex(rc, layout, v);
/*     */   }
/*     */   
/*     */   public void renderVertexLabel(RenderContext<V, E> rc, Layout<V, E> layout, V v) {
/*  75 */     this.vertexLabelRenderer.labelVertex(rc, layout, v, (String)rc.getVertexLabelTransformer().transform(v));
/*     */   }
/*     */   
/*     */   public void renderEdge(RenderContext<V, E> rc, Layout<V, E> layout, E e) {
/*  79 */     this.edgeRenderer.paintEdge(rc, layout, e);
/*     */   }
/*     */   
/*     */   public void renderEdgeLabel(RenderContext<V, E> rc, Layout<V, E> layout, E e) {
/*  83 */     this.edgeLabelRenderer.labelEdge(rc, layout, e, (String)rc.getEdgeLabelTransformer().transform(e));
/*     */   }
/*     */   
/*     */   public void setVertexRenderer(Renderer.Vertex<V, E> r) {
/*  87 */     this.vertexRenderer = r;
/*     */   }
/*     */   
/*     */   public void setEdgeRenderer(Renderer.Edge<V, E> r) {
/*  91 */     this.edgeRenderer = r;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Renderer.EdgeLabel<V, E> getEdgeLabelRenderer()
/*     */   {
/*  98 */     return this.edgeLabelRenderer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setEdgeLabelRenderer(Renderer.EdgeLabel<V, E> edgeLabelRenderer)
/*     */   {
/* 105 */     this.edgeLabelRenderer = edgeLabelRenderer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Renderer.VertexLabel<V, E> getVertexLabelRenderer()
/*     */   {
/* 112 */     return this.vertexLabelRenderer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setVertexLabelRenderer(Renderer.VertexLabel<V, E> vertexLabelRenderer)
/*     */   {
/* 120 */     this.vertexLabelRenderer = vertexLabelRenderer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Renderer.Edge<V, E> getEdgeRenderer()
/*     */   {
/* 127 */     return this.edgeRenderer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Renderer.Vertex<V, E> getVertexRenderer()
/*     */   {
/* 134 */     return this.vertexRenderer;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/renderers/BasicRenderer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */