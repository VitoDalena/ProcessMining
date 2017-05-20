/*    */ package edu.uci.ics.jung.visualization.renderers;
/*    */ 
/*    */ import edu.uci.ics.jung.algorithms.layout.Layout;
/*    */ 
/*    */ public abstract interface Renderer<V, E>
/*    */ {
/*    */   public abstract void render(edu.uci.ics.jung.visualization.RenderContext<V, E> paramRenderContext, Layout<V, E> paramLayout);
/*    */   
/*    */   public abstract void renderVertex(edu.uci.ics.jung.visualization.RenderContext<V, E> paramRenderContext, Layout<V, E> paramLayout, V paramV);
/*    */   
/*    */   public abstract void renderVertexLabel(edu.uci.ics.jung.visualization.RenderContext<V, E> paramRenderContext, Layout<V, E> paramLayout, V paramV);
/*    */   
/*    */   public abstract void renderEdge(edu.uci.ics.jung.visualization.RenderContext<V, E> paramRenderContext, Layout<V, E> paramLayout, E paramE);
/*    */   
/*    */   public abstract void renderEdgeLabel(edu.uci.ics.jung.visualization.RenderContext<V, E> paramRenderContext, Layout<V, E> paramLayout, E paramE);
/*    */   
/*    */   public abstract void setVertexRenderer(Vertex<V, E> paramVertex);
/*    */   
/*    */   public abstract void setEdgeRenderer(Edge<V, E> paramEdge);
/*    */   
/*    */   public abstract void setVertexLabelRenderer(VertexLabel<V, E> paramVertexLabel);
/*    */   
/*    */   public abstract void setEdgeLabelRenderer(EdgeLabel<V, E> paramEdgeLabel);
/*    */   
/*    */   public abstract VertexLabel<V, E> getVertexLabelRenderer();
/*    */   
/*    */   public abstract Vertex<V, E> getVertexRenderer();
/*    */   
/*    */   public abstract Edge<V, E> getEdgeRenderer();
/*    */   
/*    */   public abstract EdgeLabel<V, E> getEdgeLabelRenderer();
/*    */   
/*    */   public static abstract interface EdgeLabel<V, E>
/*    */   {
/*    */     public abstract void labelEdge(edu.uci.ics.jung.visualization.RenderContext<V, E> paramRenderContext, Layout<V, E> paramLayout, E paramE, String paramString);
/*    */     
/*    */     public static class NOOP
/*    */       implements Renderer.EdgeLabel
/*    */     {
/*    */       public void labelEdge(edu.uci.ics.jung.visualization.RenderContext rc, Layout layout, Object e, String label) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public static abstract interface VertexLabel<V, E>
/*    */   {
/*    */     public abstract void labelVertex(edu.uci.ics.jung.visualization.RenderContext<V, E> paramRenderContext, Layout<V, E> paramLayout, V paramV, String paramString);
/*    */     
/*    */     public abstract Position getPosition();
/*    */     
/*    */     public abstract void setPosition(Position paramPosition);
/*    */     
/*    */     public abstract void setPositioner(Positioner paramPositioner);
/*    */     
/*    */     public abstract Positioner getPositioner();
/*    */     
/*    */     public static class NOOP implements Renderer.VertexLabel
/*    */     {
/*    */       public void labelVertex(edu.uci.ics.jung.visualization.RenderContext rc, Layout layout, Object v, String label) {}
/*    */       
/* 60 */       public Renderer.VertexLabel.Position getPosition() { return Renderer.VertexLabel.Position.CNTR; }
/*    */       
/*    */       public void setPosition(Renderer.VertexLabel.Position position) {}
/* 63 */       public Renderer.VertexLabel.Positioner getPositioner() { new Renderer.VertexLabel.Positioner()
/*    */         {
/* 65 */           public Renderer.VertexLabel.Position getPosition(float x, float y, java.awt.Dimension d) { return Renderer.VertexLabel.Position.CNTR; }
/*    */         }; }
/*    */       
/*    */       public void setPositioner(Renderer.VertexLabel.Positioner positioner) {}
/*    */     }
/*    */     
/* 71 */     public static enum Position { N,  NE,  E,  SE,  S,  SW,  W,  NW,  CNTR,  AUTO;
/*    */       
/*    */       private Position() {}
/*    */     }
/*    */     
/*    */     public static abstract interface Positioner
/*    */     {
/*    */       public abstract Renderer.VertexLabel.Position getPosition(float paramFloat1, float paramFloat2, java.awt.Dimension paramDimension);
/*    */     }
/*    */   }
/*    */   
/*    */   public static abstract interface Edge<V, E>
/*    */   {
/*    */     public abstract void paintEdge(edu.uci.ics.jung.visualization.RenderContext<V, E> paramRenderContext, Layout<V, E> paramLayout, E paramE);
/*    */     
/*    */     public static class NOOP
/*    */       implements Renderer.Edge
/*    */     {
/*    */       public void paintEdge(edu.uci.ics.jung.visualization.RenderContext rc, Layout layout, Object e) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public static abstract interface Vertex<V, E>
/*    */   {
/*    */     public abstract void paintVertex(edu.uci.ics.jung.visualization.RenderContext<V, E> paramRenderContext, Layout<V, E> paramLayout, V paramV);
/*    */     
/*    */     public static class NOOP
/*    */       implements Renderer.Vertex
/*    */     {
/*    */       public void paintVertex(edu.uci.ics.jung.visualization.RenderContext rc, Layout layout, Object v) {}
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/renderers/Renderer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */