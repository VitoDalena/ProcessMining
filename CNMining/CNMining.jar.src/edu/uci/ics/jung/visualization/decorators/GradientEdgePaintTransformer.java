/*     */ package edu.uci.ics.jung.visualization.decorators;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.layout.Layout;
/*     */ import edu.uci.ics.jung.algorithms.util.SelfLoopEdgePredicate;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.util.Context;
/*     */ import edu.uci.ics.jung.graph.util.EdgeType;
/*     */ import edu.uci.ics.jung.graph.util.Pair;
/*     */ import edu.uci.ics.jung.visualization.Layer;
/*     */ import edu.uci.ics.jung.visualization.MultiLayerTransformer;
/*     */ import edu.uci.ics.jung.visualization.RenderContext;
/*     */ import edu.uci.ics.jung.visualization.VisualizationViewer;
/*     */ import edu.uci.ics.jung.visualization.transform.BidirectionalTransformer;
/*     */ import java.awt.Color;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Paint;
/*     */ import java.awt.geom.Point2D;
/*     */ import org.apache.commons.collections15.Predicate;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GradientEdgePaintTransformer<V, E>
/*     */   implements Transformer<E, Paint>
/*     */ {
/*     */   protected Color c1;
/*     */   protected Color c2;
/*     */   protected VisualizationViewer<V, E> vv;
/*     */   protected BidirectionalTransformer transformer;
/*  48 */   protected Predicate<Context<Graph<V, E>, E>> selfLoop = new SelfLoopEdgePredicate();
/*     */   
/*     */ 
/*     */   public GradientEdgePaintTransformer(Color c1, Color c2, VisualizationViewer<V, E> vv)
/*     */   {
/*  53 */     this.c1 = c1;
/*  54 */     this.c2 = c2;
/*  55 */     this.vv = vv;
/*  56 */     this.transformer = vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT);
/*     */   }
/*     */   
/*     */   public Paint transform(E e)
/*     */   {
/*  61 */     Layout<V, E> layout = this.vv.getGraphLayout();
/*  62 */     Pair<V> p = layout.getGraph().getEndpoints(e);
/*  63 */     V b = p.getFirst();
/*  64 */     V f = p.getSecond();
/*  65 */     Point2D pb = this.transformer.transform((Point2D)layout.transform(b));
/*  66 */     Point2D pf = this.transformer.transform((Point2D)layout.transform(f));
/*  67 */     float xB = (float)pb.getX();
/*  68 */     float yB = (float)pb.getY();
/*  69 */     float xF = (float)pf.getX();
/*  70 */     float yF = (float)pf.getY();
/*  71 */     if (layout.getGraph().getEdgeType(e) == EdgeType.UNDIRECTED) {
/*  72 */       xF = (xF + xB) / 2.0F;
/*  73 */       yF = (yF + yB) / 2.0F;
/*     */     }
/*  75 */     if (this.selfLoop.evaluate(Context.getInstance(layout.getGraph(), e))) {
/*  76 */       yF += 50.0F;
/*  77 */       xF += 50.0F;
/*     */     }
/*     */     
/*  80 */     return new GradientPaint(xB, yB, getColor1(e), xF, yF, getColor2(e), true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Color getColor1(E e)
/*     */   {
/*  90 */     return this.c1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Color getColor2(E e)
/*     */   {
/* 100 */     return this.c2;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/decorators/GradientEdgePaintTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */