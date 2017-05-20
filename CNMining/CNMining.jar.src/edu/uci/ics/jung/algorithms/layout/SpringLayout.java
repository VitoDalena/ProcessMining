/*     */ package edu.uci.ics.jung.algorithms.layout;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.layout.util.RandomLocationTransformer;
/*     */ import edu.uci.ics.jung.algorithms.util.IterativeContext;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.util.Pair;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.event.ComponentAdapter;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections15.Factory;
/*     */ import org.apache.commons.collections15.Transformer;
/*     */ import org.apache.commons.collections15.functors.ConstantTransformer;
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
/*     */ public class SpringLayout<V, E>
/*     */   extends AbstractLayout<V, E>
/*     */   implements IterativeContext
/*     */ {
/*  39 */   protected double stretch = 0.7D;
/*     */   protected Transformer<E, Integer> lengthFunction;
/*  41 */   protected int repulsion_range_sq = 10000;
/*  42 */   protected double force_multiplier = 0.3333333333333333D;
/*     */   
/*  44 */   protected Map<V, SpringVertexData> springVertexData = LazyMap.decorate(new HashMap(), new Factory()
/*     */   {
/*     */     public SpringLayout.SpringVertexData create()
/*     */     {
/*  48 */       return new SpringLayout.SpringVertexData();
/*     */     }
/*  44 */   });
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
/*     */   public SpringLayout(Graph<V, E> g)
/*     */   {
/*  58 */     this(g, new ConstantTransformer(Integer.valueOf(30)));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SpringLayout(Graph<V, E> g, Transformer<E, Integer> length_function)
/*     */   {
/*  69 */     super(g);
/*  70 */     this.lengthFunction = length_function;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getStretch()
/*     */   {
/*  78 */     return this.stretch;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSize(Dimension size)
/*     */   {
/*  86 */     if (!this.initialized)
/*  87 */       setInitializer(new RandomLocationTransformer(size));
/*  88 */     super.setSize(size);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setStretch(double stretch)
/*     */   {
/* 105 */     this.stretch = stretch;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getRepulsionRange()
/*     */   {
/* 113 */     return (int)Math.sqrt(this.repulsion_range_sq);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setRepulsionRange(int range)
/*     */   {
/* 123 */     this.repulsion_range_sq = (range * range);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getForceMultiplier()
/*     */   {
/* 131 */     return this.force_multiplier;
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
/*     */   public void setForceMultiplier(double force)
/*     */   {
/* 145 */     this.force_multiplier = force;
/*     */   }
/*     */   
/*     */ 
/*     */   public void initialize() {}
/*     */   
/*     */ 
/*     */   public void step()
/*     */   {
/*     */     try
/*     */     {
/* 156 */       for (V v : getGraph().getVertices()) {
/* 157 */         SpringVertexData svd = (SpringVertexData)this.springVertexData.get(v);
/* 158 */         if (svd != null)
/*     */         {
/*     */ 
/* 161 */           svd.dx /= 4.0D;
/* 162 */           svd.dy /= 4.0D;
/* 163 */           svd.edgedx = (svd.edgedy = 0.0D);
/* 164 */           svd.repulsiondx = (svd.repulsiondy = 0.0D);
/*     */         }
/*     */       }
/* 167 */     } catch (ConcurrentModificationException cme) { step();
/*     */     }
/*     */     
/* 170 */     relaxEdges();
/* 171 */     calculateRepulsion();
/* 172 */     moveNodes();
/*     */   }
/*     */   
/*     */   protected void relaxEdges() {
/*     */     try {
/* 177 */       for (E e : getGraph().getEdges()) {
/* 178 */         Pair<V> endpoints = getGraph().getEndpoints(e);
/* 179 */         V v1 = endpoints.getFirst();
/* 180 */         V v2 = endpoints.getSecond();
/*     */         
/* 182 */         Point2D p1 = transform(v1);
/* 183 */         Point2D p2 = transform(v2);
/* 184 */         if ((p1 != null) && (p2 != null)) {
/* 185 */           double vx = p1.getX() - p2.getX();
/* 186 */           double vy = p1.getY() - p2.getY();
/* 187 */           double len = Math.sqrt(vx * vx + vy * vy);
/*     */           
/* 189 */           double desiredLen = ((Integer)this.lengthFunction.transform(e)).intValue();
/*     */           
/*     */ 
/* 192 */           len = len == 0.0D ? 1.0E-4D : len;
/*     */           
/* 194 */           double f = this.force_multiplier * (desiredLen - len) / len;
/*     */           
/* 196 */           f *= Math.pow(this.stretch, getGraph().degree(v1) + getGraph().degree(v2) - 2);
/*     */           
/*     */ 
/*     */ 
/* 200 */           double dx = f * vx;
/* 201 */           double dy = f * vy;
/*     */           
/* 203 */           SpringVertexData v1D = (SpringVertexData)this.springVertexData.get(v1);
/* 204 */           SpringVertexData v2D = (SpringVertexData)this.springVertexData.get(v2);
/*     */           
/* 206 */           v1D.edgedx += dx;
/* 207 */           v1D.edgedy += dy;
/* 208 */           v2D.edgedx += -dx;
/* 209 */           v2D.edgedy += -dy;
/*     */         }
/*     */       }
/* 212 */     } catch (ConcurrentModificationException cme) { relaxEdges();
/*     */     }
/*     */   }
/*     */   
/*     */   protected void calculateRepulsion() {
/*     */     try {
/* 218 */       for (V v : getGraph().getVertices())
/* 219 */         if (!isLocked(v))
/*     */         {
/* 221 */           SpringVertexData svd = (SpringVertexData)this.springVertexData.get(v);
/* 222 */           if (svd != null) {
/* 223 */             double dx = 0.0D;double dy = 0.0D;
/*     */             
/* 225 */             for (V v2 : getGraph().getVertices())
/* 226 */               if (v != v2) {
/* 227 */                 Point2D p = transform(v);
/* 228 */                 Point2D p2 = transform(v2);
/* 229 */                 if ((p != null) && (p2 != null)) {
/* 230 */                   double vx = p.getX() - p2.getX();
/* 231 */                   double vy = p.getY() - p2.getY();
/* 232 */                   double distanceSq = p.distanceSq(p2);
/* 233 */                   if (distanceSq == 0.0D) {
/* 234 */                     dx += Math.random();
/* 235 */                     dy += Math.random();
/* 236 */                   } else if (distanceSq < this.repulsion_range_sq) {
/* 237 */                     double factor = 1.0D;
/* 238 */                     dx += factor * vx / distanceSq;
/* 239 */                     dy += factor * vy / distanceSq;
/*     */                   }
/*     */                 } }
/* 242 */             double dlen = dx * dx + dy * dy;
/* 243 */             if (dlen > 0.0D) {
/* 244 */               dlen = Math.sqrt(dlen) / 2.0D;
/* 245 */               svd.repulsiondx += dx / dlen;
/* 246 */               svd.repulsiondy += dy / dlen;
/*     */             }
/*     */           }
/*     */         }
/* 250 */     } catch (ConcurrentModificationException cme) { calculateRepulsion();
/*     */     }
/*     */   }
/*     */   
/*     */   protected void moveNodes()
/*     */   {
/* 256 */     synchronized (getSize()) {
/*     */       try {
/* 258 */         for (V v : getGraph().getVertices()) {
/* 259 */           if (!isLocked(v)) {
/* 260 */             SpringVertexData vd = (SpringVertexData)this.springVertexData.get(v);
/* 261 */             if (vd != null) {
/* 262 */               Point2D xyd = transform(v);
/*     */               
/* 264 */               vd.dx += vd.repulsiondx + vd.edgedx;
/* 265 */               vd.dy += vd.repulsiondy + vd.edgedy;
/*     */               
/*     */ 
/* 268 */               xyd.setLocation(xyd.getX() + Math.max(-5.0D, Math.min(5.0D, vd.dx)), xyd.getY() + Math.max(-5.0D, Math.min(5.0D, vd.dy)));
/*     */               
/*     */ 
/* 271 */               Dimension d = getSize();
/* 272 */               int width = d.width;
/* 273 */               int height = d.height;
/*     */               
/* 275 */               if (xyd.getX() < 0.0D) {
/* 276 */                 xyd.setLocation(0.0D, xyd.getY());
/* 277 */               } else if (xyd.getX() > width) {
/* 278 */                 xyd.setLocation(width, xyd.getY());
/*     */               }
/* 280 */               if (xyd.getY() < 0.0D) {
/* 281 */                 xyd.setLocation(xyd.getX(), 0.0D);
/* 282 */               } else if (xyd.getY() > height)
/* 283 */                 xyd.setLocation(xyd.getX(), height);
/*     */             }
/*     */           }
/*     */         }
/*     */       } catch (ConcurrentModificationException cme) {
/* 288 */         moveNodes();
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
/*     */   public class SpringDimensionChecker
/*     */     extends ComponentAdapter
/*     */   {
/*     */     public SpringDimensionChecker() {}
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void componentResized(ComponentEvent e)
/*     */     {
/* 313 */       SpringLayout.this.setSize(e.getComponent().getSize());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isIncremental()
/*     */   {
/* 321 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean done()
/*     */   {
/* 328 */     return false;
/*     */   }
/*     */   
/*     */   public void reset() {}
/*     */   
/*     */   protected static class SpringVertexData
/*     */   {
/*     */     protected double edgedx;
/*     */     protected double edgedy;
/*     */     protected double repulsiondx;
/*     */     protected double repulsiondy;
/*     */     protected double dx;
/*     */     protected double dy;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/layout/SpringLayout.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */