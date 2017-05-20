/*     */ package edu.uci.ics.jung.algorithms.layout;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ public class CircleLayout<V, E>
/*     */   extends AbstractLayout<V, E>
/*     */ {
/*     */   private double radius;
/*     */   private List<V> vertex_ordered_list;
/*  41 */   Map<V, CircleVertexData> circleVertexDataMap = LazyMap.decorate(new HashMap(), new Factory()
/*     */   {
/*     */     public CircleLayout.CircleVertexData create()
/*     */     {
/*  45 */       return new CircleLayout.CircleVertexData();
/*     */     }
/*  41 */   });
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CircleLayout(Graph<V, E> g)
/*     */   {
/*  52 */     super(g);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getRadius()
/*     */   {
/*  59 */     return this.radius;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setRadius(double radius)
/*     */   {
/*  67 */     this.radius = radius;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setVertexOrder(Comparator<V> comparator)
/*     */   {
/*  76 */     this.vertex_ordered_list = new ArrayList(getGraph().getVertexCount());
/*  77 */     Collections.sort(this.vertex_ordered_list, comparator);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setVertexOrder(List<V> vertex_list)
/*     */   {
/*  86 */     if (!vertex_list.containsAll(getGraph().getVertices())) {
/*  87 */       throw new IllegalArgumentException("Supplied list must include all vertices of the graph");
/*     */     }
/*  89 */     this.vertex_ordered_list = vertex_list;
/*     */   }
/*     */   
/*     */   public void reset() {
/*  93 */     initialize();
/*     */   }
/*     */   
/*     */   public void initialize()
/*     */   {
/*  98 */     Dimension d = getSize();
/*     */     double height;
/* 100 */     double width; int i; if (d != null)
/*     */     {
/* 102 */       if (this.vertex_ordered_list == null) {
/* 103 */         setVertexOrder(new ArrayList(getGraph().getVertices()));
/*     */       }
/* 105 */       height = d.getHeight();
/* 106 */       width = d.getWidth();
/*     */       
/* 108 */       if (this.radius <= 0.0D) {
/* 109 */         this.radius = (0.45D * (height < width ? height : width));
/*     */       }
/*     */       
/* 112 */       i = 0;
/* 113 */       for (V v : this.vertex_ordered_list)
/*     */       {
/* 115 */         Point2D coord = transform(v);
/*     */         
/* 117 */         double angle = 6.283185307179586D * i / this.vertex_ordered_list.size();
/*     */         
/* 119 */         coord.setLocation(Math.cos(angle) * this.radius + width / 2.0D, Math.sin(angle) * this.radius + height / 2.0D);
/*     */         
/*     */ 
/* 122 */         CircleVertexData data = getCircleData(v);
/* 123 */         data.setAngle(angle);
/* 124 */         i++;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected CircleVertexData getCircleData(V v) {
/* 130 */     return (CircleVertexData)this.circleVertexDataMap.get(v);
/*     */   }
/*     */   
/*     */   protected static class CircleVertexData {
/*     */     private double angle;
/*     */     
/*     */     protected double getAngle() {
/* 137 */       return this.angle;
/*     */     }
/*     */     
/*     */     protected void setAngle(double angle) {
/* 141 */       this.angle = angle;
/*     */     }
/*     */     
/*     */     public String toString()
/*     */     {
/* 146 */       return "CircleVertexData: angle=" + this.angle;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/layout/CircleLayout.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */