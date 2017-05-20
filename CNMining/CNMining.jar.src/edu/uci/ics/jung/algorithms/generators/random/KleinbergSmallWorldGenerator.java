/*     */ package edu.uci.ics.jung.algorithms.generators.random;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.generators.Lattice2DGenerator;
/*     */ import edu.uci.ics.jung.algorithms.util.WeightedChoice;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Point2D.Double;
/*     */ import java.awt.geom.Point2D.Float;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.EnumMap;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import org.apache.commons.collections15.Factory;
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
/*     */ public class KleinbergSmallWorldGenerator<V, E>
/*     */   extends Lattice2DGenerator<V, E>
/*     */ {
/*     */   private double clustering_exponent;
/*     */   private Random random;
/*     */   
/*     */   private static enum Direction
/*     */   {
/*  42 */     UP,  DOWN,  LEFT,  RIGHT;
/*  43 */     private Direction() {} } private static Map<Direction, Point2D> steps = new EnumMap(Direction.class);
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
/*     */   public KleinbergSmallWorldGenerator(Factory<Graph<V, E>> graph_factory, Factory<V> vertex_factory, Factory<E> edge_factory, int latticeSize, double clusteringExponent)
/*     */   {
/*  58 */     this(graph_factory, vertex_factory, edge_factory, latticeSize, latticeSize, clusteringExponent);
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
/*     */   public KleinbergSmallWorldGenerator(Factory<Graph<V, E>> graph_factory, Factory<V> vertex_factory, Factory<E> edge_factory, int row_count, int col_count, double clusteringExponent)
/*     */   {
/*  72 */     super(graph_factory, vertex_factory, edge_factory, row_count, col_count, true);
/*  73 */     this.clustering_exponent = clusteringExponent;
/*  74 */     initialize();
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
/*     */   public KleinbergSmallWorldGenerator(Factory<Graph<V, E>> graph_factory, Factory<V> vertex_factory, Factory<E> edge_factory, int row_count, int col_count, double clusteringExponent, boolean isToroidal)
/*     */   {
/*  90 */     super(graph_factory, vertex_factory, edge_factory, row_count, col_count, isToroidal);
/*  91 */     this.clustering_exponent = clusteringExponent;
/*  92 */     initialize();
/*     */   }
/*     */   
/*     */   private void initialize()
/*     */   {
/*  97 */     this.random = new Random();
/*  98 */     steps.put(Direction.UP, new Point2D.Float(0.0F, -1.0F));
/*  99 */     steps.put(Direction.DOWN, new Point2D.Float(0.0F, 1.0F));
/* 100 */     steps.put(Direction.LEFT, new Point2D.Float(-1.0F, 0.0F));
/* 101 */     steps.put(Direction.RIGHT, new Point2D.Float(1.0F, 0.0F));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setRandom(Random random)
/*     */   {
/* 110 */     this.random = random;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setRandomSeed(long seed)
/*     */   {
/* 119 */     this.random.setSeed(seed);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Graph<V, E> create()
/*     */   {
/* 130 */     Graph<V, E> graph = super.create();
/*     */     
/*     */ 
/* 133 */     int max_distance = this.is_toroidal ? (int)(Math.ceil((this.row_count - 1) / 2) + Math.ceil((this.col_count - 1) / 2)) : this.row_count - 1 + (this.col_count - 1);
/*     */     
/*     */ 
/*     */ 
/* 137 */     Map<Direction, Boolean> direction_ok = new EnumMap(Direction.class);
/*     */     
/*     */ 
/*     */ 
/* 141 */     Map<Integer, Double> distance_weights = new HashMap();
/* 142 */     for (int i = 2; i <= max_distance; i++)
/* 143 */       distance_weights.put(Integer.valueOf(i), Double.valueOf(Math.pow(i, -this.clustering_exponent)));
/* 144 */     WeightedChoice<Integer> weighted_choice = new WeightedChoice(distance_weights);
/*     */     
/*     */ 
/* 147 */     for (int i = 0; i < graph.getVertexCount(); i++)
/*     */     {
/* 149 */       int row = getRow(i);
/* 150 */       int col = getCol(i);
/*     */       
/*     */ 
/* 153 */       int max_distance_i = this.is_toroidal ? max_distance : Math.max(row, this.row_count - 1 - row) + Math.max(col, this.col_count - 1 - col);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 162 */       int distance_i = Integer.MAX_VALUE;
/*     */       do
/*     */       {
/* 165 */         distance_i = ((Integer)weighted_choice.nextItem()).intValue();
/* 166 */       } while (distance_i < max_distance_i);
/*     */       
/*     */ 
/* 169 */       Point2D location = new Point2D.Float(row, col);
/* 170 */       Point2D offset = new Point2D.Float(0.0F, 0.0F);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 176 */       if (this.is_toroidal) {
/* 177 */         for (Direction direction : Direction.values()) {
/* 178 */           direction_ok.put(direction, Boolean.valueOf(true));
/*     */         }
/*     */       } else {
/* 181 */         boolean UL_OK = manhattanDistance(location, new Point2D.Float(0.0F, 0.0F)) >= distance_i;
/* 182 */         boolean UR_OK = manhattanDistance(location, new Point2D.Float(this.col_count - 1, 0.0F)) >= distance_i;
/* 183 */         boolean LL_OK = manhattanDistance(location, new Point2D.Float(0.0F, this.row_count - 1)) >= distance_i;
/* 184 */         boolean LR_OK = manhattanDistance(location, new Point2D.Float(this.col_count - 1, this.row_count - 1)) >= distance_i;
/* 185 */         direction_ok.put(Direction.UP, Boolean.valueOf((UL_OK) || (UR_OK)));
/* 186 */         direction_ok.put(Direction.DOWN, Boolean.valueOf((LL_OK) || (LR_OK)));
/* 187 */         direction_ok.put(Direction.LEFT, Boolean.valueOf((UL_OK) || (LL_OK)));
/* 188 */         direction_ok.put(Direction.RIGHT, Boolean.valueOf((UR_OK) || (LR_OK)));
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 193 */       for (int d = 1; d <= distance_i; d++) {
/* 194 */         assert (walkAway(location, offset, direction_ok) == true);
/*     */       }
/*     */       
/* 197 */       int j = getIndex((int)(location.getX() + offset.getX() % this.col_count), (int)(location.getY() + offset.getY() % this.row_count));
/*     */       
/*     */ 
/* 200 */       V source = getVertex(i);
/* 201 */       V target = getVertex(j);
/*     */       
/* 203 */       graph.addEdge(this.edge_factory.create(), source, target);
/*     */     }
/*     */     
/* 206 */     return graph;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean walkAway(Point2D current, Point2D offset, Map<Direction, Boolean> direction_ok)
/*     */   {
/* 216 */     List<Point2D> walk_candidates = new ArrayList(4);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 221 */     if ((offset.getX() >= 0.0D) && (((Boolean)direction_ok.get(Direction.RIGHT)).booleanValue()))
/* 222 */       addIfLegal(walk_candidates, current, sum(offset, (Point2D)steps.get(Direction.RIGHT)));
/* 223 */     if ((offset.getX() <= 0.0D) && (((Boolean)direction_ok.get(Direction.LEFT)).booleanValue()))
/* 224 */       addIfLegal(walk_candidates, current, sum(offset, (Point2D)steps.get(Direction.LEFT)));
/* 225 */     if ((offset.getY() >= 0.0D) && (((Boolean)direction_ok.get(Direction.DOWN)).booleanValue()))
/* 226 */       addIfLegal(walk_candidates, current, sum(offset, (Point2D)steps.get(Direction.DOWN)));
/* 227 */     if ((offset.getY() <= 0.0D) && (((Boolean)direction_ok.get(Direction.UP)).booleanValue())) {
/* 228 */       addIfLegal(walk_candidates, current, sum(offset, (Point2D)steps.get(Direction.UP)));
/*     */     }
/* 230 */     if (walk_candidates.isEmpty()) {
/* 231 */       return false;
/*     */     }
/* 233 */     Point2D step = (Point2D)walk_candidates.get(this.random.nextInt(walk_candidates.size()));
/* 234 */     offset.setLocation(sum(offset, step));
/* 235 */     current.setLocation(sum(current, step));
/* 236 */     return true;
/*     */   }
/*     */   
/*     */   private void addIfLegal(Collection<Point2D> locations, Point2D location, Point2D step)
/*     */   {
/* 241 */     Point2D new_location = sum(location, step);
/* 242 */     if ((this.is_toroidal) || ((new_location.getX() >= 0.0D) && (new_location.getX() < this.col_count) && (new_location.getY() >= 0.0D) && (new_location.getY() < this.row_count)))
/*     */     {
/* 244 */       locations.add(step);
/*     */     }
/*     */   }
/*     */   
/*     */   private Point2D sum(Point2D p1, Point2D p2) {
/* 249 */     return new Point2D.Double(p1.getX() + p2.getX(), p1.getY() + p2.getY());
/*     */   }
/*     */   
/*     */   private int manhattanDistance(Point2D p1, Point2D p2)
/*     */   {
/* 254 */     return (int)(Math.abs(p1.getX() - p2.getX()) + Math.abs(p1.getY() - p2.getY()));
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/generators/random/KleinbergSmallWorldGenerator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */