/*     */ package edu.uci.ics.jung.algorithms.layout;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.util.IterativeContext;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.geom.Point2D;
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
/*     */ public abstract class LayoutDecorator<V, E>
/*     */   implements Layout<V, E>, IterativeContext
/*     */ {
/*     */   protected Layout<V, E> delegate;
/*     */   
/*     */   public LayoutDecorator(Layout<V, E> delegate)
/*     */   {
/*  36 */     this.delegate = delegate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Layout<V, E> getDelegate()
/*     */   {
/*  43 */     return this.delegate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setDelegate(Layout<V, E> delegate)
/*     */   {
/*  50 */     this.delegate = delegate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void step()
/*     */   {
/*  57 */     if ((this.delegate instanceof IterativeContext)) {
/*  58 */       ((IterativeContext)this.delegate).step();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void initialize()
/*     */   {
/*  67 */     this.delegate.initialize();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setInitializer(Transformer<V, Point2D> initializer)
/*     */   {
/*  75 */     this.delegate.setInitializer(initializer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLocation(V v, Point2D location)
/*     */   {
/*  84 */     this.delegate.setLocation(v, location);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Dimension getSize()
/*     */   {
/*  91 */     return this.delegate.getSize();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Graph<V, E> getGraph()
/*     */   {
/*  98 */     return this.delegate.getGraph();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Point2D transform(V v)
/*     */   {
/* 105 */     return (Point2D)this.delegate.transform(v);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean done()
/*     */   {
/* 112 */     if ((this.delegate instanceof IterativeContext)) {
/* 113 */       return ((IterativeContext)this.delegate).done();
/*     */     }
/* 115 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void lock(V v, boolean state)
/*     */   {
/* 122 */     this.delegate.lock(v, state);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isLocked(V v)
/*     */   {
/* 129 */     return this.delegate.isLocked(v);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setSize(Dimension d)
/*     */   {
/* 136 */     this.delegate.setSize(d);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void reset()
/*     */   {
/* 143 */     this.delegate.reset();
/*     */   }
/*     */   
/*     */   public void setGraph(Graph<V, E> graph) {
/* 147 */     this.delegate.setGraph(graph);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/layout/LayoutDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */