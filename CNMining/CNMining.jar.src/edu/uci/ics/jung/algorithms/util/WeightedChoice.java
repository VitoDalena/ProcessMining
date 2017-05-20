/*     */ package edu.uci.ics.jung.algorithms.util;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Queue;
/*     */ import java.util.Random;
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
/*     */ public class WeightedChoice<T>
/*     */ {
/*     */   private List<WeightedChoice<T>.ItemPair> item_pairs;
/*     */   private Random random;
/*     */   public static final double DEFAULT_THRESHOLD = 1.0E-11D;
/*     */   
/*     */   public WeightedChoice(Map<T, ? extends Number> item_weights)
/*     */   {
/*  49 */     this(item_weights, new Random(), 1.0E-11D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public WeightedChoice(Map<T, ? extends Number> item_weights, double threshold)
/*     */   {
/*  57 */     this(item_weights, new Random(), threshold);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public WeightedChoice(Map<T, ? extends Number> item_weights, Random random)
/*     */   {
/*  65 */     this(item_weights, random, 1.0E-11D);
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
/*     */   public WeightedChoice(Map<T, ? extends Number> item_weights, Random random, double threshold)
/*     */   {
/*  82 */     if (item_weights.isEmpty()) {
/*  83 */       throw new IllegalArgumentException("Item weights must be non-empty");
/*     */     }
/*  85 */     int item_count = item_weights.size();
/*  86 */     this.item_pairs = new ArrayList(item_count);
/*     */     
/*  88 */     double sum = 0.0D;
/*  89 */     for (Map.Entry<T, ? extends Number> entry : item_weights.entrySet())
/*     */     {
/*  91 */       double value = ((Number)entry.getValue()).doubleValue();
/*  92 */       if (value <= 0.0D)
/*  93 */         throw new IllegalArgumentException("Weights must be > 0");
/*  94 */       sum += value;
/*     */     }
/*  96 */     double mean_weight = sum / item_weights.size();
/*     */     
/*  98 */     Queue<WeightedChoice<T>.ItemPair> light_weights = new LinkedList();
/*  99 */     Queue<WeightedChoice<T>.ItemPair> heavy_weights = new LinkedList();
/* 100 */     for (Map.Entry<T, ? extends Number> entry : item_weights.entrySet())
/*     */     {
/* 102 */       double value = ((Number)entry.getValue()).doubleValue() / sum;
/* 103 */       enqueueItem(entry.getKey(), value, mean_weight, light_weights, heavy_weights);
/*     */     }
/*     */     
/*     */ 
/* 107 */     while ((!heavy_weights.isEmpty()) || (!light_weights.isEmpty()))
/*     */     {
/* 109 */       WeightedChoice<T>.ItemPair heavy_item = (ItemPair)heavy_weights.poll();
/* 110 */       WeightedChoice<T>.ItemPair light_item = (ItemPair)light_weights.poll();
/* 111 */       double light_weight = 0.0D;
/* 112 */       T light = null;
/* 113 */       T heavy = null;
/* 114 */       if (light_item != null)
/*     */       {
/* 116 */         light_weight = light_item.weight;
/* 117 */         light = light_item.light;
/*     */       }
/* 119 */       if (heavy_item != null)
/*     */       {
/* 121 */         heavy = heavy_item.heavy;
/*     */         
/*     */ 
/*     */ 
/* 125 */         double new_weight = heavy_item.weight - (mean_weight - light_weight);
/* 126 */         if (new_weight > threshold)
/* 127 */           enqueueItem(heavy, new_weight, mean_weight, light_weights, heavy_weights);
/*     */       }
/* 129 */       light_weight *= item_count;
/*     */       
/* 131 */       this.item_pairs.add(new ItemPair(light, heavy, light_weight, null));
/*     */     }
/*     */     
/* 134 */     this.random = random;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void enqueueItem(T key, double value, double threshold, Queue<WeightedChoice<T>.ItemPair> light_weights, Queue<WeightedChoice<T>.ItemPair> heavy_weights)
/*     */   {
/* 145 */     if (value < threshold) {
/* 146 */       light_weights.offer(new ItemPair(key, null, value, null));
/*     */     } else {
/* 148 */       heavy_weights.offer(new ItemPair(null, key, value, null));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setRandomSeed(long seed)
/*     */   {
/* 156 */     this.random.setSeed(seed);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public T nextItem()
/*     */   {
/* 165 */     WeightedChoice<T>.ItemPair item_pair = (ItemPair)this.item_pairs.get(this.random.nextInt(this.item_pairs.size()));
/* 166 */     if (this.random.nextDouble() < item_pair.weight)
/* 167 */       return (T)item_pair.light;
/* 168 */     return (T)item_pair.heavy;
/*     */   }
/*     */   
/*     */ 
/*     */   private class ItemPair
/*     */   {
/*     */     T light;
/*     */     
/*     */     T heavy;
/*     */     
/*     */     double weight;
/*     */     
/*     */     private ItemPair(T light, double heavy)
/*     */     {
/* 182 */       this.light = light;
/* 183 */       this.heavy = heavy;
/* 184 */       this.weight = weight;
/*     */     }
/*     */     
/*     */ 
/*     */     public String toString()
/*     */     {
/* 190 */       return String.format("[L:%s, H:%s, %.3f]", new Object[] { this.light, this.heavy, Double.valueOf(this.weight) });
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/util/WeightedChoice.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */