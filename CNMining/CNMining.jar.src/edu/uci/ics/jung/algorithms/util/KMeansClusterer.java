/*     */ package edu.uci.ics.jung.algorithms.util;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
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
/*     */ public class KMeansClusterer<T>
/*     */ {
/*     */   protected int max_iterations;
/*     */   protected double convergence_threshold;
/*     */   protected Random rand;
/*     */   
/*     */   public KMeansClusterer(int max_iterations, double convergence_threshold)
/*     */   {
/*  53 */     this.max_iterations = max_iterations;
/*  54 */     this.convergence_threshold = convergence_threshold;
/*  55 */     this.rand = new Random();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public KMeansClusterer()
/*     */   {
/*  64 */     this(100, 0.001D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMaxIterations()
/*     */   {
/*  72 */     return this.max_iterations;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMaxIterations(int max_iterations)
/*     */   {
/*  80 */     if (max_iterations < 0) {
/*  81 */       throw new IllegalArgumentException("max iterations must be >= 0");
/*     */     }
/*  83 */     this.max_iterations = max_iterations;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getConvergenceThreshold()
/*     */   {
/*  91 */     return this.convergence_threshold;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setConvergenceThreshold(double convergence_threshold)
/*     */   {
/* 100 */     if (convergence_threshold <= 0.0D) {
/* 101 */       throw new IllegalArgumentException("convergence threshold must be > 0");
/*     */     }
/*     */     
/* 104 */     this.convergence_threshold = convergence_threshold;
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
/*     */   public Collection<Map<T, double[]>> cluster(Map<T, double[]> object_locations, int num_clusters)
/*     */   {
/* 119 */     if ((object_locations == null) || (object_locations.isEmpty())) {
/* 120 */       throw new IllegalArgumentException("'objects' must be non-empty");
/*     */     }
/* 122 */     if ((num_clusters < 2) || (num_clusters > object_locations.size())) {
/* 123 */       throw new IllegalArgumentException("number of clusters must be >= 2 and <= number of objects (" + object_locations.size() + ")");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 128 */     Set<double[]> centroids = new HashSet();
/*     */     
/* 130 */     Object[] obj_array = object_locations.keySet().toArray();
/* 131 */     Set<T> tried = new HashSet();
/*     */     
/*     */ 
/* 134 */     while ((centroids.size() < num_clusters) && (tried.size() < object_locations.size()))
/*     */     {
/* 136 */       T o = obj_array[((int)(this.rand.nextDouble() * obj_array.length))];
/* 137 */       tried.add(o);
/* 138 */       double[] mean_value = (double[])object_locations.get(o);
/* 139 */       boolean duplicate = false;
/* 140 */       for (double[] cur : centroids)
/*     */       {
/* 142 */         if (Arrays.equals(mean_value, cur))
/* 143 */           duplicate = true;
/*     */       }
/* 145 */       if (!duplicate) {
/* 146 */         centroids.add(mean_value);
/*     */       }
/*     */     }
/* 149 */     if (tried.size() >= object_locations.size()) {
/* 150 */       throw new NotEnoughClustersException();
/*     */     }
/*     */     
/* 153 */     Map<double[], Map<T, double[]>> clusterMap = assignToClusters(object_locations, centroids);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 159 */     int iterations = 0;
/* 160 */     double max_movement = Double.POSITIVE_INFINITY;
/* 161 */     while ((iterations++ < this.max_iterations) && (max_movement > this.convergence_threshold))
/*     */     {
/* 163 */       max_movement = 0.0D;
/* 164 */       Set<double[]> new_centroids = new HashSet();
/*     */       
/* 166 */       for (Map.Entry<double[], Map<T, double[]>> entry : clusterMap.entrySet())
/*     */       {
/* 168 */         double[] centroid = (double[])entry.getKey();
/* 169 */         Map<T, double[]> elements = (Map)entry.getValue();
/* 170 */         ArrayList<double[]> locations = new ArrayList(elements.values());
/*     */         
/* 172 */         double[] mean = DiscreteDistribution.mean(locations);
/* 173 */         max_movement = Math.max(max_movement, Math.sqrt(DiscreteDistribution.squaredError(centroid, mean)));
/*     */         
/* 175 */         new_centroids.add(mean);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 181 */       clusterMap = assignToClusters(object_locations, new_centroids);
/*     */     }
/* 183 */     return clusterMap.values();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Map<double[], Map<T, double[]>> assignToClusters(Map<T, double[]> object_locations, Set<double[]> centroids)
/*     */   {
/* 195 */     Map<double[], Map<T, double[]>> clusterMap = new HashMap();
/* 196 */     for (double[] centroid : centroids) {
/* 197 */       clusterMap.put(centroid, new HashMap());
/*     */     }
/* 199 */     for (Map.Entry<T, double[]> object_location : object_locations.entrySet())
/*     */     {
/* 201 */       T object = object_location.getKey();
/* 202 */       double[] location = (double[])object_location.getValue();
/*     */       
/*     */ 
/* 205 */       Iterator<double[]> c_iter = centroids.iterator();
/* 206 */       double[] closest = (double[])c_iter.next();
/* 207 */       double distance = DiscreteDistribution.squaredError(location, closest);
/*     */       
/* 209 */       while (c_iter.hasNext())
/*     */       {
/* 211 */         double[] centroid = (double[])c_iter.next();
/* 212 */         double dist_cur = DiscreteDistribution.squaredError(location, centroid);
/* 213 */         if (dist_cur < distance)
/*     */         {
/* 215 */           distance = dist_cur;
/* 216 */           closest = centroid;
/*     */         }
/*     */       }
/* 219 */       ((Map)clusterMap.get(closest)).put(object, location);
/*     */     }
/*     */     
/* 222 */     return clusterMap;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSeed(int random_seed)
/*     */   {
/* 231 */     this.rand = new Random(random_seed);
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
/*     */   public static class NotEnoughClustersException
/*     */     extends RuntimeException
/*     */   {
/*     */     public String getMessage()
/*     */     {
/* 249 */       return "Not enough distinct points in the input data set to form the requested number of clusters";
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/util/KMeansClusterer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */