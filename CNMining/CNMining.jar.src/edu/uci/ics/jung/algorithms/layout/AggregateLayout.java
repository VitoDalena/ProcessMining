/*     */ package edu.uci.ics.jung.algorithms.layout;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.util.IterativeContext;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
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
/*     */ 
/*     */ public class AggregateLayout<V, E>
/*     */   implements Layout<V, E>, IterativeContext
/*     */ {
/*     */   protected Layout<V, E> delegate;
/*  38 */   protected Map<Layout<V, E>, Point2D> layouts = new HashMap();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public AggregateLayout(Layout<V, E> delegate)
/*     */   {
/*  45 */     this.delegate = delegate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Layout<V, E> getDelegate()
/*     */   {
/*  52 */     return this.delegate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setDelegate(Layout<V, E> delegate)
/*     */   {
/*  59 */     this.delegate = delegate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void put(Layout<V, E> layout, Point2D center)
/*     */   {
/*  69 */     this.layouts.put(layout, center);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Point2D get(Layout<V, E> layout)
/*     */   {
/*  78 */     return (Point2D)this.layouts.get(layout);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void remove(Layout<V, E> layout)
/*     */   {
/*  85 */     this.layouts.remove(layout);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void removeAll()
/*     */   {
/*  92 */     this.layouts.clear();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Graph<V, E> getGraph()
/*     */   {
/* 101 */     return this.delegate.getGraph();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Dimension getSize()
/*     */   {
/* 110 */     return this.delegate.getSize();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void initialize()
/*     */   {
/* 118 */     this.delegate.initialize();
/* 119 */     for (Layout<V, E> layout : this.layouts.keySet()) {
/* 120 */       layout.initialize();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isLocked(V v)
/*     */   {
/* 132 */     boolean locked = false;
/* 133 */     for (Layout<V, E> layout : this.layouts.keySet()) {
/* 134 */       locked |= layout.isLocked(v);
/*     */     }
/* 136 */     locked |= this.delegate.isLocked(v);
/* 137 */     return locked;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void lock(V v, boolean state)
/*     */   {
/* 148 */     for (Layout<V, E> layout : this.layouts.keySet()) {
/* 149 */       if (layout.getGraph().getVertices().contains(v)) {
/* 150 */         layout.lock(v, state);
/*     */       }
/*     */     }
/* 153 */     this.delegate.lock(v, state);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void reset()
/*     */   {
/* 161 */     for (Layout<V, E> layout : this.layouts.keySet()) {
/* 162 */       layout.reset();
/*     */     }
/* 164 */     this.delegate.reset();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setGraph(Graph<V, E> graph)
/*     */   {
/* 172 */     this.delegate.setGraph(graph);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setInitializer(Transformer<V, Point2D> initializer)
/*     */   {
/* 180 */     this.delegate.setInitializer(initializer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLocation(V v, Point2D location)
/*     */   {
/* 189 */     boolean wasInSublayout = false;
/* 190 */     for (Layout<V, E> layout : this.layouts.keySet()) {
/* 191 */       if (layout.getGraph().getVertices().contains(v)) {
/* 192 */         Point2D center = (Point2D)this.layouts.get(layout);
/*     */         
/*     */ 
/* 195 */         Dimension d = layout.getSize();
/*     */         
/* 197 */         AffineTransform at = AffineTransform.getTranslateInstance(-center.getX() + d.width / 2, -center.getY() + d.height / 2);
/*     */         
/* 199 */         Point2D localLocation = at.transform(location, null);
/* 200 */         layout.setLocation(v, localLocation);
/* 201 */         wasInSublayout = true;
/*     */       }
/*     */     }
/* 204 */     if ((!wasInSublayout) && (getGraph().getVertices().contains(v))) {
/* 205 */       this.delegate.setLocation(v, location);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSize(Dimension d)
/*     */   {
/* 214 */     this.delegate.setSize(d);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Map<Layout<V, E>, Point2D> getLayouts()
/*     */   {
/* 221 */     return this.layouts;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Point2D transform(V v)
/*     */   {
/* 232 */     boolean wasInSublayout = false;
/* 233 */     for (Layout<V, E> layout : this.layouts.keySet()) {
/* 234 */       if (layout.getGraph().getVertices().contains(v)) {
/* 235 */         wasInSublayout = true;
/* 236 */         Point2D center = (Point2D)this.layouts.get(layout);
/*     */         
/*     */ 
/* 239 */         Dimension d = layout.getSize();
/* 240 */         AffineTransform at = AffineTransform.getTranslateInstance(center.getX() - d.width / 2, center.getY() - d.height / 2);
/*     */         
/*     */ 
/* 243 */         return at.transform((Point2D)layout.transform(v), null);
/*     */       }
/*     */     }
/* 246 */     if (!wasInSublayout) {
/* 247 */       return (Point2D)this.delegate.transform(v);
/*     */     }
/* 249 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean done()
/*     */   {
/* 258 */     boolean done = true;
/* 259 */     for (Layout<V, E> layout : this.layouts.keySet()) {
/* 260 */       if ((layout instanceof IterativeContext)) {
/* 261 */         done &= ((IterativeContext)layout).done();
/*     */       }
/*     */     }
/* 264 */     if ((this.delegate instanceof IterativeContext)) {
/* 265 */       done &= ((IterativeContext)this.delegate).done();
/*     */     }
/* 267 */     return done;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void step()
/*     */   {
/* 275 */     for (Layout<V, E> layout : this.layouts.keySet()) {
/* 276 */       if ((layout instanceof IterativeContext)) {
/* 277 */         IterativeContext context = (IterativeContext)layout;
/* 278 */         if (!context.done()) {
/* 279 */           context.step();
/*     */         }
/*     */       }
/*     */     }
/* 283 */     if ((this.delegate instanceof IterativeContext)) {
/* 284 */       IterativeContext context = (IterativeContext)this.delegate;
/* 285 */       if (!context.done()) {
/* 286 */         context.step();
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/layout/AggregateLayout.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */