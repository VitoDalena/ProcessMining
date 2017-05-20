/*     */ package edu.uci.ics.jung.visualization.renderers;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.layout.Layout;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.util.Context;
/*     */ import edu.uci.ics.jung.visualization.Layer;
/*     */ import edu.uci.ics.jung.visualization.MultiLayerTransformer;
/*     */ import edu.uci.ics.jung.visualization.RenderContext;
/*     */ import edu.uci.ics.jung.visualization.picking.PickedState;
/*     */ import edu.uci.ics.jung.visualization.transform.BidirectionalTransformer;
/*     */ import edu.uci.ics.jung.visualization.transform.shape.GraphicsDecorator;
/*     */ import edu.uci.ics.jung.visualization.transform.shape.ShapeTransformer;
/*     */ import edu.uci.ics.jung.visualization.transform.shape.TransformingGraphics;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.Point;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import javax.swing.JComponent;
/*     */ import org.apache.commons.collections15.Predicate;
/*     */ import org.apache.commons.collections15.Transformer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BasicVertexLabelRenderer<V, E>
/*     */   implements Renderer.VertexLabel<V, E>
/*     */ {
/*  33 */   protected Renderer.VertexLabel.Position position = Renderer.VertexLabel.Position.SE;
/*  34 */   private Renderer.VertexLabel.Positioner positioner = new OutsidePositioner();
/*     */   
/*     */ 
/*     */   public BasicVertexLabelRenderer() {}
/*     */   
/*     */   public BasicVertexLabelRenderer(Renderer.VertexLabel.Position position)
/*     */   {
/*  41 */     this.position = position;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Renderer.VertexLabel.Position getPosition()
/*     */   {
/*  48 */     return this.position;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setPosition(Renderer.VertexLabel.Position position)
/*     */   {
/*  55 */     this.position = position;
/*     */   }
/*     */   
/*     */   public Component prepareRenderer(RenderContext<V, E> rc, VertexLabelRenderer graphLabelRenderer, Object value, boolean isSelected, V vertex)
/*     */   {
/*  60 */     return rc.getVertexLabelRenderer().getVertexLabelRendererComponent(rc.getScreenDevice(), value, (Font)rc.getVertexFontTransformer().transform(vertex), isSelected, vertex);
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
/*     */   public void labelVertex(RenderContext<V, E> rc, Layout<V, E> layout, V v, String label)
/*     */   {
/*  73 */     Graph<V, E> graph = layout.getGraph();
/*  74 */     if (!rc.getVertexIncludePredicate().evaluate(Context.getInstance(graph, v))) {
/*  75 */       return;
/*     */     }
/*  77 */     Point2D pt = (Point2D)layout.transform(v);
/*  78 */     pt = rc.getMultiLayerTransformer().transform(Layer.LAYOUT, pt);
/*     */     
/*  80 */     float x = (float)pt.getX();
/*  81 */     float y = (float)pt.getY();
/*     */     
/*  83 */     Component component = prepareRenderer(rc, rc.getVertexLabelRenderer(), label, rc.getPickedVertexState().isPicked(v), v);
/*     */     
/*  85 */     GraphicsDecorator g = rc.getGraphicsContext();
/*  86 */     Dimension d = component.getPreferredSize();
/*  87 */     AffineTransform xform = AffineTransform.getTranslateInstance(x, y);
/*     */     
/*  89 */     Shape shape = (Shape)rc.getVertexShapeTransformer().transform(v);
/*  90 */     shape = xform.createTransformedShape(shape);
/*  91 */     if ((rc.getGraphicsContext() instanceof TransformingGraphics)) {
/*  92 */       BidirectionalTransformer transformer = ((TransformingGraphics)rc.getGraphicsContext()).getTransformer();
/*  93 */       if ((transformer instanceof ShapeTransformer)) {
/*  94 */         ShapeTransformer shapeTransformer = (ShapeTransformer)transformer;
/*  95 */         shape = shapeTransformer.transform(shape);
/*     */       }
/*     */     }
/*  98 */     Rectangle2D bounds = shape.getBounds2D();
/*     */     
/* 100 */     Point p = null;
/* 101 */     if (this.position == Renderer.VertexLabel.Position.AUTO) {
/* 102 */       Dimension vvd = rc.getScreenDevice().getSize();
/* 103 */       if ((vvd.width == 0) || (vvd.height == 0)) {
/* 104 */         vvd = rc.getScreenDevice().getPreferredSize();
/*     */       }
/* 106 */       p = getAnchorPoint(bounds, d, this.positioner.getPosition(x, y, vvd));
/*     */     } else {
/* 108 */       p = getAnchorPoint(bounds, d, this.position);
/*     */     }
/* 110 */     g.draw(component, rc.getRendererPane(), p.x, p.y, d.width, d.height, true);
/*     */   }
/*     */   
/*     */ 
/*     */   protected Point getAnchorPoint(Rectangle2D vertexBounds, Dimension labelSize, Renderer.VertexLabel.Position position)
/*     */   {
/* 116 */     int offset = 5;
/* 117 */     double x; double y; switch (position)
/*     */     {
/*     */     case N: 
/* 120 */       x = vertexBounds.getCenterX() - labelSize.width / 2;
/* 121 */       y = vertexBounds.getMinY() - offset - labelSize.height;
/* 122 */       return new Point((int)x, (int)y);
/*     */     
/*     */     case NE: 
/* 125 */       x = vertexBounds.getMaxX() + offset;
/* 126 */       y = vertexBounds.getMinY() - offset - labelSize.height;
/* 127 */       return new Point((int)x, (int)y);
/*     */     
/*     */     case E: 
/* 130 */       x = vertexBounds.getMaxX() + offset;
/* 131 */       y = vertexBounds.getCenterY() - labelSize.height / 2;
/* 132 */       return new Point((int)x, (int)y);
/*     */     
/*     */     case SE: 
/* 135 */       x = vertexBounds.getMaxX() + offset;
/* 136 */       y = vertexBounds.getMaxY() + offset;
/* 137 */       return new Point((int)x, (int)y);
/*     */     
/*     */     case S: 
/* 140 */       x = vertexBounds.getCenterX() - labelSize.width / 2;
/* 141 */       y = vertexBounds.getMaxY() + offset;
/* 142 */       return new Point((int)x, (int)y);
/*     */     
/*     */     case SW: 
/* 145 */       x = vertexBounds.getMinX() - offset - labelSize.width;
/* 146 */       y = vertexBounds.getMaxY() + offset;
/* 147 */       return new Point((int)x, (int)y);
/*     */     
/*     */     case W: 
/* 150 */       x = vertexBounds.getMinX() - offset - labelSize.width;
/* 151 */       y = vertexBounds.getCenterY() - labelSize.height / 2;
/* 152 */       return new Point((int)x, (int)y);
/*     */     
/*     */     case NW: 
/* 155 */       x = vertexBounds.getMinX() - offset - labelSize.width;
/* 156 */       y = vertexBounds.getMinY() - offset - labelSize.height;
/* 157 */       return new Point((int)x, (int)y);
/*     */     
/*     */     case CNTR: 
/* 160 */       x = vertexBounds.getCenterX() - labelSize.width / 2;
/* 161 */       y = vertexBounds.getCenterY() - labelSize.height / 2;
/* 162 */       return new Point((int)x, (int)y);
/*     */     }
/*     */     
/* 165 */     return new Point();
/*     */   }
/*     */   
/*     */   public static class InsidePositioner implements Renderer.VertexLabel.Positioner
/*     */   {
/*     */     public Renderer.VertexLabel.Position getPosition(float x, float y, Dimension d) {
/* 171 */       int cx = d.width / 2;
/* 172 */       int cy = d.height / 2;
/* 173 */       if ((x > cx) && (y > cy)) return Renderer.VertexLabel.Position.NW;
/* 174 */       if ((x > cx) && (y < cy)) return Renderer.VertexLabel.Position.SW;
/* 175 */       if ((x < cx) && (y > cy)) return Renderer.VertexLabel.Position.NE;
/* 176 */       return Renderer.VertexLabel.Position.SE;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class OutsidePositioner implements Renderer.VertexLabel.Positioner {
/* 181 */     public Renderer.VertexLabel.Position getPosition(float x, float y, Dimension d) { int cx = d.width / 2;
/* 182 */       int cy = d.height / 2;
/* 183 */       if ((x > cx) && (y > cy)) return Renderer.VertexLabel.Position.SE;
/* 184 */       if ((x > cx) && (y < cy)) return Renderer.VertexLabel.Position.NE;
/* 185 */       if ((x < cx) && (y > cy)) return Renderer.VertexLabel.Position.SW;
/* 186 */       return Renderer.VertexLabel.Position.NW;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public Renderer.VertexLabel.Positioner getPositioner()
/*     */   {
/* 193 */     return this.positioner;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setPositioner(Renderer.VertexLabel.Positioner positioner)
/*     */   {
/* 200 */     this.positioner = positioner;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/renderers/BasicVertexLabelRenderer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */