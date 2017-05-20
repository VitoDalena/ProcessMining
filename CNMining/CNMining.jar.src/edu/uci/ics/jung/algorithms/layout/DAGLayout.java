/*     */ package edu.uci.ics.jung.algorithms.layout;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.util.Pair;
/*     */ import java.awt.Dimension;
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
/*     */ public class DAGLayout<V, E>
/*     */   extends SpringLayout<V, E>
/*     */ {
/*  50 */   private Map<V, Number> minLevels = new HashMap();
/*     */   
/*     */   static int graphHeight;
/*     */   static int numRoots;
/*  54 */   final double SPACEFACTOR = 1.3D;
/*     */   
/*  56 */   final double LEVELATTRACTIONRATE = 0.8D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  66 */   final double MSV_THRESHOLD = 10.0D;
/*     */   double meanSquareVel;
/*  68 */   boolean stoppingIncrements = false;
/*     */   int incrementsLeft;
/*  70 */   final int COOL_DOWN_INCREMENTS = 200;
/*     */   
/*     */ 
/*     */ 
/*     */   public DAGLayout(Graph<V, E> g)
/*     */   {
/*  76 */     super(g);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setRoot(Graph<V, E> g)
/*     */   {
/*  85 */     numRoots = 0;
/*  86 */     for (V v : g.getVertices()) {
/*  87 */       Collection<V> successors = getGraph().getSuccessors(v);
/*  88 */       if (successors.size() == 0) {
/*  89 */         setRoot(v);
/*  90 */         numRoots += 1;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setRoot(V v)
/*     */   {
/*  99 */     this.minLevels.put(v, new Integer(0));
/*     */     
/* 101 */     propagateMinimumLevel(v);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void propagateMinimumLevel(V v)
/*     */   {
/* 112 */     int level = ((Number)this.minLevels.get(v)).intValue();
/* 113 */     for (V child : getGraph().getPredecessors(v))
/*     */     {
/* 115 */       Number o = (Number)this.minLevels.get(child);
/* 116 */       int oldLevel; int oldLevel; if (o != null) {
/* 117 */         oldLevel = o.intValue();
/*     */       } else
/* 119 */         oldLevel = 0;
/* 120 */       int newLevel = Math.max(oldLevel, level + 1);
/* 121 */       this.minLevels.put(child, new Integer(newLevel));
/*     */       
/* 123 */       if (newLevel > graphHeight)
/* 124 */         graphHeight = newLevel;
/* 125 */       propagateMinimumLevel(child);
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
/*     */ 
/*     */   private void initializeLocation(V v, Point2D coord, Dimension d)
/*     */   {
/* 141 */     int level = ((Number)this.minLevels.get(v)).intValue();
/* 142 */     int minY = (int)(level * d.getHeight() / (graphHeight * 1.3D));
/* 143 */     double x = Math.random() * d.getWidth();
/* 144 */     double y = Math.random() * (d.getHeight() - minY) + minY;
/* 145 */     coord.setLocation(x, y);
/*     */   }
/*     */   
/*     */   public void setSize(Dimension size)
/*     */   {
/* 150 */     super.setSize(size);
/* 151 */     for (V v : getGraph().getVertices()) {
/* 152 */       initializeLocation(v, transform(v), getSize());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void initialize()
/*     */   {
/* 161 */     super.initialize();
/* 162 */     setRoot(getGraph());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void moveNodes()
/*     */   {
/* 173 */     double oldMSV = this.meanSquareVel;
/* 174 */     this.meanSquareVel = 0.0D;
/*     */     
/* 176 */     synchronized (getSize())
/*     */     {
/* 178 */       for (V v : getGraph().getVertices()) {
/* 179 */         if (!isLocked(v))
/*     */         {
/* 181 */           SpringLayout.SpringVertexData vd = (SpringLayout.SpringVertexData)this.springVertexData.get(v);
/* 182 */           Point2D xyd = transform(v);
/*     */           
/* 184 */           int width = getSize().width;
/* 185 */           int height = getSize().height;
/*     */           
/*     */ 
/* 188 */           int level = ((Number)this.minLevels.get(v)).intValue();
/*     */           
/* 190 */           int minY = (int)(level * height / (graphHeight * 1.3D));
/* 191 */           int maxY = level == 0 ? (int)(height / (graphHeight * 1.3D * 2.0D)) : height;
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 197 */           vd.dx += 2.0D * vd.repulsiondx + vd.edgedx;
/* 198 */           vd.dy += vd.repulsiondy + vd.edgedy;
/*     */           
/*     */ 
/*     */ 
/* 202 */           double delta = xyd.getY() - minY;
/* 203 */           vd.dy -= delta * 0.8D;
/* 204 */           if (level == 0) {
/* 205 */             vd.dy -= delta * 0.8D;
/*     */           }
/*     */           
/*     */ 
/* 209 */           this.meanSquareVel += vd.dx * vd.dx + vd.dy * vd.dy;
/*     */           
/*     */ 
/* 212 */           xyd.setLocation(xyd.getX() + Math.max(-5.0D, Math.min(5.0D, vd.dx)), xyd.getY() + Math.max(-5.0D, Math.min(5.0D, vd.dy)));
/*     */           
/* 214 */           if (xyd.getX() < 0.0D) {
/* 215 */             xyd.setLocation(0.0D, xyd.getY());
/* 216 */           } else if (xyd.getX() > width) {
/* 217 */             xyd.setLocation(width, xyd.getY());
/*     */           }
/*     */           
/*     */ 
/* 221 */           if (xyd.getY() < minY) {
/* 222 */             xyd.setLocation(xyd.getX(), minY);
/*     */           }
/* 224 */           else if (xyd.getY() > maxY) {
/* 225 */             xyd.setLocation(xyd.getX(), maxY);
/*     */           }
/*     */           
/*     */ 
/*     */ 
/* 230 */           if ((numRoots == 1) && (level == 0)) {
/* 231 */             xyd.setLocation(width / 2, xyd.getY());
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 236 */     if ((!this.stoppingIncrements) && (Math.abs(this.meanSquareVel - oldMSV) < 10.0D))
/*     */     {
/* 238 */       this.stoppingIncrements = true;
/* 239 */       this.incrementsLeft = 200;
/* 240 */     } else if ((this.stoppingIncrements) && (Math.abs(this.meanSquareVel - oldMSV) <= 10.0D))
/*     */     {
/*     */ 
/* 243 */       this.incrementsLeft -= 1;
/* 244 */       if (this.incrementsLeft <= 0) {
/* 245 */         this.incrementsLeft = 0;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean done()
/*     */   {
/* 254 */     if ((this.stoppingIncrements) && (this.incrementsLeft == 0)) {
/* 255 */       return true;
/*     */     }
/* 257 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLocation(V picked, double x, double y)
/*     */   {
/* 266 */     Point2D coord = transform(picked);
/* 267 */     coord.setLocation(x, y);
/* 268 */     this.stoppingIncrements = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLocation(V picked, Point2D p)
/*     */   {
/* 277 */     Point2D coord = transform(picked);
/* 278 */     coord.setLocation(p);
/* 279 */     this.stoppingIncrements = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void relaxEdges()
/*     */   {
/* 289 */     for (E e : getGraph().getEdges()) {
/* 290 */       Pair<V> endpoints = getGraph().getEndpoints(e);
/* 291 */       V v1 = endpoints.getFirst();
/* 292 */       V v2 = endpoints.getSecond();
/*     */       
/* 294 */       Point2D p1 = transform(v1);
/* 295 */       Point2D p2 = transform(v2);
/* 296 */       double vx = p1.getX() - p2.getX();
/* 297 */       double vy = p1.getY() - p2.getY();
/* 298 */       double len = Math.sqrt(vx * vx + vy * vy);
/*     */       
/*     */ 
/* 301 */       int level1 = ((Number)this.minLevels.get(v1)).intValue();
/*     */       
/* 303 */       int level2 = ((Number)this.minLevels.get(v2)).intValue();
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 308 */       double desiredLen = ((Integer)this.lengthFunction.transform(e)).intValue();
/*     */       
/*     */ 
/* 311 */       len = len == 0.0D ? 1.0E-4D : len;
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 318 */       double f = this.force_multiplier * (desiredLen - len) / len;
/*     */       
/* 320 */       f *= Math.pow(this.stretch / 100.0D, getGraph().degree(v1) + getGraph().degree(v2) - 2);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 325 */       if (level1 != level2) {
/* 326 */         f /= Math.pow(Math.abs(level2 - level1), 1.5D);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 332 */       double dx = f * vx;
/* 333 */       double dy = f * vy;
/*     */       
/* 335 */       SpringLayout.SpringVertexData v1D = (SpringLayout.SpringVertexData)this.springVertexData.get(v1);
/* 336 */       SpringLayout.SpringVertexData v2D = (SpringLayout.SpringVertexData)this.springVertexData.get(v2);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 341 */       v1D.edgedx += dx;
/* 342 */       v1D.edgedy += dy;
/* 343 */       v2D.edgedx += -dx;
/* 344 */       v2D.edgedy += -dy;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/layout/DAGLayout.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */