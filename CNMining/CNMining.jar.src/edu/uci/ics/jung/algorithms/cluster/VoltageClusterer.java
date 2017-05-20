/*     */ package edu.uci.ics.jung.algorithms.cluster;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.scoring.VoltageScorer;
/*     */ import edu.uci.ics.jung.algorithms.util.DiscreteDistribution;
/*     */ import edu.uci.ics.jung.algorithms.util.KMeansClusterer;
/*     */ import edu.uci.ics.jung.algorithms.util.KMeansClusterer.NotEnoughClustersException;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
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
/*     */ public class VoltageClusterer<V, E>
/*     */ {
/*     */   protected int num_candidates;
/*     */   protected KMeansClusterer<V> kmc;
/*     */   protected Random rand;
/*     */   protected Graph<V, E> g;
/*     */   
/*     */   public VoltageClusterer(Graph<V, E> g, int num_candidates)
/*     */   {
/*  90 */     if (num_candidates < 1) {
/*  91 */       throw new IllegalArgumentException("must generate >=1 candidates");
/*     */     }
/*  93 */     this.num_candidates = num_candidates;
/*  94 */     this.kmc = new KMeansClusterer();
/*  95 */     this.rand = new Random();
/*  96 */     this.g = g;
/*     */   }
/*     */   
/*     */   protected void setRandomSeed(int random_seed)
/*     */   {
/* 101 */     this.rand = new Random(random_seed);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Collection<Set<V>> getCommunity(V v)
/*     */   {
/* 110 */     return cluster_internal(v, 2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Collection<Set<V>> cluster(int num_clusters)
/*     */   {
/* 120 */     return cluster_internal(null, num_clusters);
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
/*     */   protected Collection<Set<V>> cluster_internal(V origin, int num_clusters)
/*     */   {
/* 135 */     ArrayList<V> v_array = new ArrayList(this.g.getVertices());
/*     */     
/* 137 */     LinkedList<Set<V>> candidates = new LinkedList();
/*     */     
/* 139 */     for (int j = 0; j < this.num_candidates; j++) {
/*     */       V source;
/*     */       V source;
/* 142 */       if (origin == null) {
/* 143 */         source = v_array.get((int)(this.rand.nextDouble() * v_array.size()));
/*     */       } else
/* 145 */         source = origin;
/* 146 */       V target = null;
/*     */       do
/*     */       {
/* 149 */         target = v_array.get((int)(this.rand.nextDouble() * v_array.size()));
/*     */       }
/* 151 */       while (source == target);
/* 152 */       VoltageScorer<V, E> vs = new VoltageScorer(this.g, source, target);
/* 153 */       vs.evaluate();
/*     */       
/* 155 */       Map<V, double[]> voltage_ranks = new HashMap();
/* 156 */       for (V v : this.g.getVertices()) {
/* 157 */         voltage_ranks.put(v, new double[] { ((Double)vs.getVertexScore(v)).doubleValue() });
/*     */       }
/*     */       
/* 160 */       addTwoCandidateClusters(candidates, voltage_ranks);
/*     */     }
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
/* 172 */     Collection<Set<V>> clusters = new LinkedList();
/* 173 */     Set<V> remaining = new HashSet(this.g.getVertices());
/*     */     
/* 175 */     List<V> seed_candidates = getSeedCandidates(candidates);
/* 176 */     int seed_index = 0;
/*     */     
/* 178 */     for (int j = 0; j < num_clusters - 1; j++)
/*     */     {
/* 180 */       if (remaining.isEmpty())
/*     */         break;
/*     */       V seed;
/*     */       V seed;
/* 184 */       if ((seed_index == 0) && (origin != null)) {
/* 185 */         seed = origin;
/*     */       } else {
/*     */         do {
/* 188 */           seed = seed_candidates.get(seed_index++);
/* 189 */         } while (!remaining.contains(seed));
/*     */       }
/*     */       
/* 192 */       Map<V, double[]> occur_counts = getObjectCounts(candidates, seed);
/* 193 */       if (occur_counts.size() < 2) {
/*     */         break;
/*     */       }
/*     */       
/*     */       try
/*     */       {
/* 199 */         Collection<Map<V, double[]>> high_low = this.kmc.cluster(occur_counts, 2);
/*     */         
/* 201 */         Iterator<Map<V, double[]>> h_iter = high_low.iterator();
/* 202 */         Map<V, double[]> cluster1 = (Map)h_iter.next();
/* 203 */         Map<V, double[]> cluster2 = (Map)h_iter.next();
/* 204 */         double[] centroid1 = DiscreteDistribution.mean(cluster1.values());
/* 205 */         double[] centroid2 = DiscreteDistribution.mean(cluster2.values());
/*     */         Set<V> new_cluster;
/* 207 */         Set<V> new_cluster; if (centroid1[0] >= centroid2[0]) {
/* 208 */           new_cluster = cluster1.keySet();
/*     */         } else {
/* 210 */           new_cluster = cluster2.keySet();
/*     */         }
/*     */         
/* 213 */         for (Set<V> cluster : candidates)
/* 214 */           cluster.removeAll(new_cluster);
/* 215 */         clusters.add(new_cluster);
/* 216 */         remaining.removeAll(new_cluster);
/*     */       }
/*     */       catch (KMeansClusterer.NotEnoughClustersException nece)
/*     */       {
/*     */         break;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 226 */     if (!remaining.isEmpty()) {
/* 227 */       clusters.add(remaining);
/*     */     }
/* 229 */     return clusters;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void addTwoCandidateClusters(LinkedList<Set<V>> candidates, Map<V, double[]> voltage_ranks)
/*     */   {
/*     */     try
/*     */     {
/* 243 */       List<Map<V, double[]>> clusters = new ArrayList(this.kmc.cluster(voltage_ranks, 3));
/* 244 */       boolean b01 = ((Map)clusters.get(0)).size() > ((Map)clusters.get(1)).size();
/* 245 */       boolean b02 = ((Map)clusters.get(0)).size() > ((Map)clusters.get(2)).size();
/* 246 */       boolean b12 = ((Map)clusters.get(1)).size() > ((Map)clusters.get(2)).size();
/* 247 */       if ((b01) && (b02))
/*     */       {
/* 249 */         candidates.add(((Map)clusters.get(1)).keySet());
/* 250 */         candidates.add(((Map)clusters.get(2)).keySet());
/*     */       }
/* 252 */       else if ((!b01) && (b12))
/*     */       {
/* 254 */         candidates.add(((Map)clusters.get(0)).keySet());
/* 255 */         candidates.add(((Map)clusters.get(2)).keySet());
/*     */       }
/* 257 */       else if ((!b02) && (!b12))
/*     */       {
/* 259 */         candidates.add(((Map)clusters.get(0)).keySet());
/* 260 */         candidates.add(((Map)clusters.get(1)).keySet());
/*     */       }
/*     */     }
/*     */     catch (KMeansClusterer.NotEnoughClustersException e) {}
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
/*     */   protected void addOneCandidateCluster(LinkedList<Set<V>> candidates, Map<V, double[]> voltage_ranks)
/*     */   {
/*     */     try
/*     */     {
/* 282 */       List<Map<V, double[]>> clusters = new ArrayList(this.kmc.cluster(voltage_ranks, 2));
/* 283 */       if (((Map)clusters.get(0)).size() < ((Map)clusters.get(1)).size()) {
/* 284 */         candidates.add(((Map)clusters.get(0)).keySet());
/*     */       } else {
/* 286 */         candidates.add(((Map)clusters.get(1)).keySet());
/*     */       }
/*     */     }
/*     */     catch (KMeansClusterer.NotEnoughClustersException e) {}
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
/*     */   protected List<V> getSeedCandidates(Collection<Set<V>> candidates)
/*     */   {
/* 302 */     Map<V, double[]> occur_counts = getObjectCounts(candidates, null);
/*     */     
/* 304 */     ArrayList<V> occurrences = new ArrayList(occur_counts.keySet());
/* 305 */     Collections.sort(occurrences, new MapValueArrayComparator(occur_counts));
/*     */     
/* 307 */     System.out.println("occurrences: ");
/* 308 */     for (int i = 0; i < occurrences.size(); i++) {
/* 309 */       System.out.println(((double[])occur_counts.get(occurrences.get(i)))[0]);
/*     */     }
/* 311 */     return occurrences;
/*     */   }
/*     */   
/*     */   protected Map<V, double[]> getObjectCounts(Collection<Set<V>> candidates, V seed)
/*     */   {
/* 316 */     Map<V, double[]> occur_counts = new HashMap();
/* 317 */     for (V v : this.g.getVertices()) {
/* 318 */       occur_counts.put(v, new double[] { 0.0D });
/*     */     }
/* 320 */     for (Set<V> candidate : candidates)
/*     */     {
/* 322 */       if (seed == null)
/* 323 */         System.out.println(candidate.size());
/* 324 */       if ((seed == null) || (candidate.contains(seed)))
/*     */       {
/* 326 */         for (V element : candidate)
/*     */         {
/* 328 */           double[] count = (double[])occur_counts.get(element);
/* 329 */           count[0] += 1.0D;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 334 */     if (seed == null)
/*     */     {
/* 336 */       System.out.println("occur_counts size: " + occur_counts.size());
/* 337 */       for (V v : occur_counts.keySet()) {
/* 338 */         System.out.println(((double[])occur_counts.get(v))[0]);
/*     */       }
/*     */     }
/* 341 */     return occur_counts;
/*     */   }
/*     */   
/*     */   protected class MapValueArrayComparator implements Comparator<V>
/*     */   {
/*     */     private Map<V, double[]> map;
/*     */     
/*     */     protected MapValueArrayComparator()
/*     */     {
/* 350 */       this.map = map;
/*     */     }
/*     */     
/*     */     public int compare(V o1, V o2)
/*     */     {
/* 355 */       double[] count0 = (double[])this.map.get(o1);
/* 356 */       double[] count1 = (double[])this.map.get(o2);
/* 357 */       if (count0[0] < count1[0])
/* 358 */         return 1;
/* 359 */       if (count0[0] > count1[0])
/* 360 */         return -1;
/* 361 */       return 0;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/cluster/VoltageClusterer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */