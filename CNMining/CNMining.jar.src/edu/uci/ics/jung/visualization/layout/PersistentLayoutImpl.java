/*     */ package edu.uci.ics.jung.visualization.layout;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.layout.Layout;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.visualization.util.Caching;
/*     */ import edu.uci.ics.jung.visualization.util.ChangeEventSupport;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections15.Factory;
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
/*     */ public class PersistentLayoutImpl<V, E>
/*     */   extends ObservableCachingLayout<V, E>
/*     */   implements PersistentLayout<V, E>, ChangeEventSupport, Caching
/*     */ {
/*     */   protected Map<V, PersistentLayout.Point> map;
/*     */   protected Set<V> dontmove;
/*     */   protected boolean locked;
/*     */   
/*     */   public PersistentLayoutImpl(Layout<V, E> layout)
/*     */   {
/*  67 */     super(layout);
/*  68 */     this.map = LazyMap.decorate(new HashMap(), new RandomPointFactory(getSize()));
/*     */     
/*  70 */     this.dontmove = new HashSet();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void initializeLocations()
/*     */   {
/*  79 */     for (V v : getGraph().getVertices()) {
/*  80 */       Point2D coord = (Point2D)this.delegate.transform(v);
/*  81 */       if (!this.dontmove.contains(v)) {
/*  82 */         initializeLocation(v, coord, getSize());
/*     */       }
/*     */     }
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
/*     */ 
/*     */   protected void initializeLocation(V v, Point2D coord, Dimension d)
/*     */   {
/*  98 */     PersistentLayout.Point point = (PersistentLayout.Point)this.map.get(v);
/*  99 */     coord.setLocation(point.x, point.y);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void persist(String fileName)
/*     */     throws IOException
/*     */   {
/* 109 */     for (V v : getGraph().getVertices()) {
/* 110 */       PersistentLayout.Point p = new PersistentLayout.Point(transform(v));
/* 111 */       this.map.put(v, p);
/*     */     }
/* 113 */     ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
/*     */     
/* 115 */     oos.writeObject(this.map);
/* 116 */     oos.close();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void restore(String fileName)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 128 */     ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
/*     */     
/* 130 */     this.map = ((Map)ois.readObject());
/* 131 */     ois.close();
/* 132 */     initializeLocations();
/* 133 */     this.locked = true;
/* 134 */     fireStateChanged();
/*     */   }
/*     */   
/*     */   public void lock(boolean locked) {
/* 138 */     this.locked = locked;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean done()
/*     */   {
/* 147 */     return (super.done()) || (this.locked);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void lock(V v, boolean state)
/*     */   {
/* 156 */     this.dontmove.add(v);
/* 157 */     this.delegate.lock(v, state);
/*     */   }
/*     */   
/*     */   public static class RandomPointFactory
/*     */     implements Factory<PersistentLayout.Point>, Serializable
/*     */   {
/*     */     Dimension d;
/*     */     
/* 165 */     public RandomPointFactory(Dimension d) { this.d = d; }
/*     */     
/*     */     public PersistentLayout.Point create() {
/* 168 */       double x = Math.random() * this.d.width;
/* 169 */       double y = Math.random() * this.d.height;
/* 170 */       return new PersistentLayout.Point(x, y);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/layout/PersistentLayoutImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */