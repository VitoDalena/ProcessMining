/*     */ package edu.uci.ics.jung.algorithms.layout;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.Forest;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.util.TreeUtils;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Point;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Point2D.Double;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections15.Transformer;
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
/*     */ public class TreeLayout<V, E>
/*     */   implements Layout<V, E>
/*     */ {
/*  36 */   protected Dimension size = new Dimension(600, 600);
/*     */   protected Forest<V, E> graph;
/*  38 */   protected Map<V, Integer> basePositions = new HashMap();
/*     */   
/*  40 */   protected Map<V, Point2D> locations = LazyMap.decorate(new HashMap(), new Transformer()
/*     */   {
/*     */     public Point2D transform(V arg0)
/*     */     {
/*  44 */       return new Point2D.Double();
/*     */     }
/*  40 */   });
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  47 */   protected transient Set<V> alreadyDone = new HashSet();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  52 */   public static int DEFAULT_DISTX = 50;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  57 */   public static int DEFAULT_DISTY = 50;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  62 */   protected int distX = 50;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  67 */   protected int distY = 50;
/*     */   
/*  69 */   protected transient Point m_currentPoint = new Point();
/*     */   
/*     */ 
/*     */ 
/*     */   public TreeLayout(Forest<V, E> g)
/*     */   {
/*  75 */     this(g, DEFAULT_DISTX, DEFAULT_DISTY);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public TreeLayout(Forest<V, E> g, int distx)
/*     */   {
/*  83 */     this(g, distx, DEFAULT_DISTY);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public TreeLayout(Forest<V, E> g, int distx, int disty)
/*     */   {
/*  90 */     if (g == null)
/*  91 */       throw new IllegalArgumentException("Graph must be non-null");
/*  92 */     if ((distx < 1) || (disty < 1))
/*  93 */       throw new IllegalArgumentException("X and Y distances must each be positive");
/*  94 */     this.graph = g;
/*  95 */     this.distX = distx;
/*  96 */     this.distY = disty;
/*  97 */     buildTree();
/*     */   }
/*     */   
/*     */   protected void buildTree() {
/* 101 */     this.m_currentPoint = new Point(0, 20);
/* 102 */     Collection<V> roots = TreeUtils.getRoots(this.graph);
/* 103 */     if ((roots.size() > 0) && (this.graph != null)) {
/* 104 */       calculateDimensionX(roots);
/* 105 */       for (V v : roots) {
/* 106 */         calculateDimensionX(v);
/* 107 */         this.m_currentPoint.x += ((Integer)this.basePositions.get(v)).intValue() / 2 + 50;
/* 108 */         buildTree(v, this.m_currentPoint.x);
/*     */       }
/*     */     }
/* 111 */     int width = 0;
/* 112 */     for (V v : roots) {
/* 113 */       width += ((Integer)this.basePositions.get(v)).intValue();
/*     */     }
/*     */   }
/*     */   
/*     */   protected void buildTree(V v, int x)
/*     */   {
/* 119 */     if (!this.alreadyDone.contains(v)) {
/* 120 */       this.alreadyDone.add(v);
/*     */       
/*     */ 
/* 123 */       this.m_currentPoint.y += this.distY;
/* 124 */       this.m_currentPoint.x = x;
/*     */       
/* 126 */       setCurrentPositionFor(v);
/*     */       
/* 128 */       int sizeXofCurrent = ((Integer)this.basePositions.get(v)).intValue();
/*     */       
/* 130 */       int lastX = x - sizeXofCurrent / 2;
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 135 */       for (V element : this.graph.getSuccessors(v)) {
/* 136 */         int sizeXofChild = ((Integer)this.basePositions.get(element)).intValue();
/* 137 */         int startXofChild = lastX + sizeXofChild / 2;
/* 138 */         buildTree(element, startXofChild);
/* 139 */         lastX = lastX + sizeXofChild + this.distX;
/*     */       }
/* 141 */       this.m_currentPoint.y -= this.distY;
/*     */     }
/*     */   }
/*     */   
/*     */   private int calculateDimensionX(V v)
/*     */   {
/* 147 */     int size = 0;
/* 148 */     int childrenNum = this.graph.getSuccessors(v).size();
/*     */     
/* 150 */     if (childrenNum != 0) {
/* 151 */       for (V element : this.graph.getSuccessors(v)) {
/* 152 */         size += calculateDimensionX(element) + this.distX;
/*     */       }
/*     */     }
/* 155 */     size = Math.max(0, size - this.distX);
/* 156 */     this.basePositions.put(v, Integer.valueOf(size));
/*     */     
/* 158 */     return size;
/*     */   }
/*     */   
/*     */   private int calculateDimensionX(Collection<V> roots)
/*     */   {
/* 163 */     int size = 0;
/* 164 */     for (V v : roots) {
/* 165 */       int childrenNum = this.graph.getSuccessors(v).size();
/*     */       
/* 167 */       if (childrenNum != 0) {
/* 168 */         for (V element : this.graph.getSuccessors(v)) {
/* 169 */           size += calculateDimensionX(element) + this.distX;
/*     */         }
/*     */       }
/* 172 */       size = Math.max(0, size - this.distX);
/* 173 */       this.basePositions.put(v, Integer.valueOf(size));
/*     */     }
/*     */     
/* 176 */     return size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSize(Dimension size)
/*     */   {
/* 185 */     throw new UnsupportedOperationException("Size of TreeLayout is set by vertex spacing in constructor");
/*     */   }
/*     */   
/*     */   protected void setCurrentPositionFor(V vertex)
/*     */   {
/* 190 */     int x = this.m_currentPoint.x;
/* 191 */     int y = this.m_currentPoint.y;
/* 192 */     if (x < 0) { this.size.width -= x;
/*     */     }
/* 194 */     if (x > this.size.width - this.distX) {
/* 195 */       this.size.width = (x + this.distX);
/*     */     }
/* 197 */     if (y < 0) this.size.height -= y;
/* 198 */     if (y > this.size.height - this.distY)
/* 199 */       this.size.height = (y + this.distY);
/* 200 */     ((Point2D)this.locations.get(vertex)).setLocation(this.m_currentPoint);
/*     */   }
/*     */   
/*     */   public Graph<V, E> getGraph()
/*     */   {
/* 205 */     return this.graph;
/*     */   }
/*     */   
/*     */   public Dimension getSize() {
/* 209 */     return this.size;
/*     */   }
/*     */   
/*     */ 
/*     */   public void initialize() {}
/*     */   
/*     */   public boolean isLocked(V v)
/*     */   {
/* 217 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void lock(V v, boolean state) {}
/*     */   
/*     */   public void reset() {}
/*     */   
/*     */   public void setGraph(Graph<V, E> graph)
/*     */   {
/* 227 */     if ((graph instanceof Forest)) {
/* 228 */       this.graph = ((Forest)graph);
/* 229 */       buildTree();
/*     */     } else {
/* 231 */       throw new IllegalArgumentException("graph must be a Forest");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setInitializer(Transformer<V, Point2D> initializer) {}
/*     */   
/*     */ 
/*     */   public Point2D getCenter()
/*     */   {
/* 242 */     return new Point2D.Double(this.size.getWidth() / 2.0D, this.size.getHeight() / 2.0D);
/*     */   }
/*     */   
/*     */   public void setLocation(V v, Point2D location) {
/* 246 */     ((Point2D)this.locations.get(v)).setLocation(location);
/*     */   }
/*     */   
/*     */   public Point2D transform(V v) {
/* 250 */     return (Point2D)this.locations.get(v);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/layout/TreeLayout.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */