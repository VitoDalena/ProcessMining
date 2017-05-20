/*     */ package edu.uci.ics.jung.visualization.layout;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.layout.Layout;
/*     */ import edu.uci.ics.jung.algorithms.layout.LayoutDecorator;
/*     */ import edu.uci.ics.jung.algorithms.util.IterativeContext;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.visualization.util.Caching;
/*     */ import edu.uci.ics.jung.visualization.util.ChangeEventSupport;
/*     */ import edu.uci.ics.jung.visualization.util.DefaultChangeEventSupport;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.swing.event.ChangeListener;
/*     */ import org.apache.commons.collections15.Transformer;
/*     */ import org.apache.commons.collections15.functors.ChainedTransformer;
/*     */ import org.apache.commons.collections15.functors.CloneTransformer;
/*     */ import org.apache.commons.collections15.map.LazyMap;
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
/*     */ public class ObservableCachingLayout<V, E>
/*     */   extends LayoutDecorator<V, E>
/*     */   implements ChangeEventSupport, Caching
/*     */ {
/*  43 */   protected ChangeEventSupport changeSupport = new DefaultChangeEventSupport(this);
/*     */   
/*     */   protected Map<V, Point2D> locationMap;
/*     */   
/*     */   public ObservableCachingLayout(Layout<V, E> delegate)
/*     */   {
/*  49 */     super(delegate);
/*  50 */     this.locationMap = LazyMap.decorate(new HashMap(), new ChainedTransformer(new Transformer[] { delegate, CloneTransformer.getInstance() }));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void step()
/*     */   {
/*  59 */     super.step();
/*  60 */     fireStateChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void initialize()
/*     */   {
/*  69 */     super.initialize();
/*  70 */     fireStateChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean done()
/*     */   {
/*  78 */     if ((this.delegate instanceof IterativeContext)) {
/*  79 */       return ((IterativeContext)this.delegate).done();
/*     */     }
/*  81 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLocation(V v, Point2D location)
/*     */   {
/*  92 */     super.setLocation(v, location);
/*  93 */     fireStateChanged();
/*     */   }
/*     */   
/*     */   public void addChangeListener(ChangeListener l) {
/*  97 */     this.changeSupport.addChangeListener(l);
/*     */   }
/*     */   
/*     */   public void removeChangeListener(ChangeListener l) {
/* 101 */     this.changeSupport.removeChangeListener(l);
/*     */   }
/*     */   
/*     */   public ChangeListener[] getChangeListeners() {
/* 105 */     return this.changeSupport.getChangeListeners();
/*     */   }
/*     */   
/*     */   public void fireStateChanged() {
/* 109 */     this.changeSupport.fireStateChanged();
/*     */   }
/*     */   
/*     */   public void setGraph(Graph<V, E> graph)
/*     */   {
/* 114 */     this.delegate.setGraph(graph);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 118 */     this.locationMap.clear();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void init() {}
/*     */   
/*     */ 
/*     */ 
/*     */   public Point2D transform(V v)
/*     */   {
/* 129 */     return (Point2D)this.locationMap.get(v);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/layout/ObservableCachingLayout.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */