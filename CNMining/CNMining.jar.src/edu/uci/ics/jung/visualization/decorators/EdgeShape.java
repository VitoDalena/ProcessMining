/*     */ package edu.uci.ics.jung.visualization.decorators;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.util.Context;
/*     */ import edu.uci.ics.jung.graph.util.EdgeIndexFunction;
/*     */ import edu.uci.ics.jung.graph.util.EdgeType;
/*     */ import edu.uci.ics.jung.graph.util.Pair;
/*     */ import edu.uci.ics.jung.visualization.util.ArrowFactory;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.CubicCurve2D;
/*     */ import java.awt.geom.CubicCurve2D.Float;
/*     */ import java.awt.geom.Ellipse2D;
/*     */ import java.awt.geom.Ellipse2D.Float;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Line2D.Float;
/*     */ import java.awt.geom.QuadCurve2D;
/*     */ import java.awt.geom.QuadCurve2D.Float;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Float;
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
/*     */ public class EdgeShape<V, E>
/*     */ {
/*  48 */   protected static Loop loop = new Loop();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  56 */   protected static SimpleLoop simpleLoop = new SimpleLoop();
/*     */   
/*     */ 
/*  59 */   protected static Box box = new Box();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static class Line<V, E>
/*     */     extends AbstractEdgeShapeTransformer<V, E>
/*     */   {
/*  70 */     private static Line2D instance = new Line2D.Float(0.0F, 0.0F, 1.0F, 0.0F);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public Shape transform(Context<Graph<V, E>, E> context)
/*     */     {
/*  78 */       Graph<V, E> graph = (Graph)context.graph;
/*  79 */       E e = context.element;
/*     */       
/*  81 */       Pair<V> endpoints = graph.getEndpoints(e);
/*  82 */       if (endpoints != null) {
/*  83 */         boolean isLoop = endpoints.getFirst().equals(endpoints.getSecond());
/*  84 */         if (isLoop) {
/*  85 */           return EdgeShape.loop.transform(context);
/*     */         }
/*     */       }
/*  88 */       return instance;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static class BentLine<V, E>
/*     */     extends AbstractEdgeShapeTransformer<V, E>
/*     */     implements EdgeShape.IndexedRendering<V, E>
/*     */   {
/* 102 */     private static GeneralPath instance = new GeneralPath();
/*     */     
/*     */     protected EdgeIndexFunction<V, E> parallelEdgeIndexFunction;
/*     */     
/*     */     public void setEdgeIndexFunction(EdgeIndexFunction<V, E> parallelEdgeIndexFunction)
/*     */     {
/* 108 */       this.parallelEdgeIndexFunction = parallelEdgeIndexFunction;
/* 109 */       EdgeShape.loop.setEdgeIndexFunction(parallelEdgeIndexFunction);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public EdgeIndexFunction<V, E> getEdgeIndexFunction()
/*     */     {
/* 118 */       return this.parallelEdgeIndexFunction;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public Shape transform(Context<Graph<V, E>, E> context)
/*     */     {
/* 130 */       Graph<V, E> graph = (Graph)context.graph;
/* 131 */       E e = context.element;
/* 132 */       Pair<V> endpoints = graph.getEndpoints(e);
/* 133 */       if (endpoints != null) {
/* 134 */         boolean isLoop = endpoints.getFirst().equals(endpoints.getSecond());
/* 135 */         if (isLoop) {
/* 136 */           return EdgeShape.loop.transform(context);
/*     */         }
/*     */       }
/*     */       
/* 140 */       int index = 1;
/* 141 */       if (this.parallelEdgeIndexFunction != null) {
/* 142 */         index = this.parallelEdgeIndexFunction.getIndex(graph, e);
/*     */       }
/* 144 */       float controlY = this.control_offset_increment + this.control_offset_increment * index;
/* 145 */       instance.reset();
/* 146 */       instance.moveTo(0.0F, 0.0F);
/* 147 */       instance.lineTo(0.5F, controlY);
/* 148 */       instance.lineTo(1.0F, 1.0F);
/* 149 */       return instance;
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
/*     */   public static class QuadCurve<V, E>
/*     */     extends AbstractEdgeShapeTransformer<V, E>
/*     */     implements EdgeShape.IndexedRendering<V, E>
/*     */   {
/* 164 */     private static QuadCurve2D instance = new QuadCurve2D.Float();
/*     */     
/*     */     protected EdgeIndexFunction<V, E> parallelEdgeIndexFunction;
/*     */     
/*     */     public void setEdgeIndexFunction(EdgeIndexFunction<V, E> parallelEdgeIndexFunction)
/*     */     {
/* 170 */       this.parallelEdgeIndexFunction = parallelEdgeIndexFunction;
/* 171 */       EdgeShape.loop.setEdgeIndexFunction(parallelEdgeIndexFunction);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public EdgeIndexFunction<V, E> getEdgeIndexFunction()
/*     */     {
/* 178 */       return this.parallelEdgeIndexFunction;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public Shape transform(Context<Graph<V, E>, E> context)
/*     */     {
/* 188 */       Graph<V, E> graph = (Graph)context.graph;
/* 189 */       E e = context.element;
/* 190 */       Pair<V> endpoints = graph.getEndpoints(e);
/* 191 */       if (endpoints != null) {
/* 192 */         boolean isLoop = endpoints.getFirst().equals(endpoints.getSecond());
/* 193 */         if (isLoop) {
/* 194 */           return EdgeShape.loop.transform(context);
/*     */         }
/*     */       }
/*     */       
/* 198 */       int index = 1;
/* 199 */       if (this.parallelEdgeIndexFunction != null) {
/* 200 */         index = this.parallelEdgeIndexFunction.getIndex(graph, e);
/*     */       }
/*     */       
/* 203 */       float controlY = this.control_offset_increment + this.control_offset_increment * index;
/*     */       
/* 205 */       instance.setCurve(0.0D, 0.0D, 0.5D, controlY, 1.0D, 0.0D);
/* 206 */       return instance;
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
/*     */   public static class CubicCurve<V, E>
/*     */     extends AbstractEdgeShapeTransformer<V, E>
/*     */     implements EdgeShape.IndexedRendering<V, E>
/*     */   {
/* 222 */     private static CubicCurve2D instance = new CubicCurve2D.Float();
/*     */     
/*     */     protected EdgeIndexFunction<V, E> parallelEdgeIndexFunction;
/*     */     
/*     */     public void setEdgeIndexFunction(EdgeIndexFunction<V, E> parallelEdgeIndexFunction)
/*     */     {
/* 228 */       this.parallelEdgeIndexFunction = parallelEdgeIndexFunction;
/* 229 */       EdgeShape.loop.setEdgeIndexFunction(parallelEdgeIndexFunction);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public EdgeIndexFunction<V, E> getEdgeIndexFunction()
/*     */     {
/* 236 */       return this.parallelEdgeIndexFunction;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public Shape transform(Context<Graph<V, E>, E> context)
/*     */     {
/* 246 */       Graph<V, E> graph = (Graph)context.graph;
/* 247 */       E e = context.element;
/* 248 */       Pair<V> endpoints = graph.getEndpoints(e);
/* 249 */       if (endpoints != null) {
/* 250 */         boolean isLoop = endpoints.getFirst().equals(endpoints.getSecond());
/* 251 */         if (isLoop) {
/* 252 */           return EdgeShape.loop.transform(context);
/*     */         }
/*     */       }
/*     */       
/* 256 */       int index = 1;
/* 257 */       if (this.parallelEdgeIndexFunction != null) {
/* 258 */         index = this.parallelEdgeIndexFunction.getIndex(graph, e);
/*     */       }
/*     */       
/* 261 */       float controlY = this.control_offset_increment + this.control_offset_increment * index;
/*     */       
/* 263 */       instance.setCurve(0.0D, 0.0D, 0.33000001311302185D, 2.0F * controlY, 0.6600000262260437D, -controlY, 1.0D, 0.0D);
/*     */       
/* 265 */       return instance;
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
/*     */   public static class SimpleLoop<V, E>
/*     */     extends AbstractEdgeShapeTransformer<V, E>
/*     */   {
/* 280 */     private static Ellipse2D instance = new Ellipse2D.Float(-0.5F, -0.5F, 1.0F, 1.0F);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public Shape transform(Context<Graph<V, E>, E> context)
/*     */     {
/* 287 */       return instance;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static class Loop<V, E>
/*     */     extends AbstractEdgeShapeTransformer<V, E>
/*     */     implements EdgeShape.IndexedRendering<V, E>
/*     */   {
/* 301 */     private static Ellipse2D instance = new Ellipse2D.Float();
/*     */     protected EdgeIndexFunction<V, E> parallelEdgeIndexFunction;
/*     */     
/*     */     public void setEdgeIndexFunction(EdgeIndexFunction<V, E> parallelEdgeIndexFunction)
/*     */     {
/* 306 */       this.parallelEdgeIndexFunction = parallelEdgeIndexFunction;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public EdgeIndexFunction<V, E> getEdgeIndexFunction()
/*     */     {
/* 314 */       return this.parallelEdgeIndexFunction;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public Shape transform(Context<Graph<V, E>, E> context)
/*     */     {
/* 323 */       Graph<V, E> graph = (Graph)context.graph;
/* 324 */       E e = context.element;
/* 325 */       int count = 1;
/* 326 */       if (this.parallelEdgeIndexFunction != null) {
/* 327 */         count = this.parallelEdgeIndexFunction.getIndex(graph, e);
/*     */       }
/*     */       
/* 330 */       float x = -0.5F;
/* 331 */       float y = -0.5F;
/* 332 */       float diam = 1.0F;
/* 333 */       diam += diam * count / 2.0F;
/* 334 */       x += x * count / 2.0F;
/* 335 */       y += y * count / 2.0F;
/* 336 */       instance.setFrame(x, y, diam, diam);
/* 337 */       return instance;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static class Wedge<V, E>
/*     */     extends AbstractEdgeShapeTransformer<V, E>
/*     */   {
/*     */     private static GeneralPath triangle;
/*     */     
/*     */     private static GeneralPath bowtie;
/*     */     
/*     */ 
/*     */     public Wedge(int width)
/*     */     {
/* 352 */       triangle = ArrowFactory.getWedgeArrow(width, 1.0F);
/* 353 */       triangle.transform(AffineTransform.getTranslateInstance(1.0D, 0.0D));
/* 354 */       bowtie = new GeneralPath(0);
/* 355 */       bowtie.moveTo(0.0F, width / 2);
/* 356 */       bowtie.lineTo(1.0F, -width / 2);
/* 357 */       bowtie.lineTo(1.0F, width / 2);
/* 358 */       bowtie.lineTo(0.0F, -width / 2);
/* 359 */       bowtie.closePath();
/*     */     }
/*     */     
/*     */     public Shape transform(Context<Graph<V, E>, E> context) {
/* 363 */       Graph<V, E> graph = (Graph)context.graph;
/* 364 */       E e = context.element;
/*     */       
/* 366 */       Pair<V> endpoints = graph.getEndpoints(e);
/* 367 */       if (endpoints != null) {
/* 368 */         boolean isLoop = endpoints.getFirst().equals(endpoints.getSecond());
/* 369 */         if (isLoop) {
/* 370 */           return EdgeShape.Loop.access$000();
/*     */         }
/*     */       }
/* 373 */       if (graph.getEdgeType(e) == EdgeType.DIRECTED) {
/* 374 */         return triangle;
/*     */       }
/* 376 */       return bowtie;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static class Box<V, E>
/*     */     extends AbstractEdgeShapeTransformer<V, E>
/*     */     implements EdgeShape.IndexedRendering<V, E>
/*     */   {
/* 390 */     private static Rectangle2D instance = new Rectangle2D.Float();
/*     */     protected EdgeIndexFunction<V, E> parallelEdgeIndexFunction;
/*     */     
/*     */     public void setEdgeIndexFunction(EdgeIndexFunction<V, E> parallelEdgeIndexFunction)
/*     */     {
/* 395 */       this.parallelEdgeIndexFunction = parallelEdgeIndexFunction;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public EdgeIndexFunction<V, E> getEdgeIndexFunction()
/*     */     {
/* 402 */       return this.parallelEdgeIndexFunction;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public Shape transform(Context<Graph<V, E>, E> context)
/*     */     {
/* 410 */       Graph<V, E> graph = (Graph)context.graph;
/* 411 */       E e = context.element;
/* 412 */       int count = 1;
/* 413 */       if (this.parallelEdgeIndexFunction != null) {
/* 414 */         count = this.parallelEdgeIndexFunction.getIndex(graph, e);
/*     */       }
/*     */       
/* 417 */       float x = -0.5F;
/* 418 */       float y = -0.5F;
/* 419 */       float diam = 1.0F;
/* 420 */       diam += diam * count / 2.0F;
/* 421 */       x += x * count / 2.0F;
/* 422 */       y += y * count / 2.0F;
/* 423 */       instance.setFrame(x, y, diam, diam);
/* 424 */       return instance;
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
/*     */   public static class Orthogonal<V, E>
/*     */     extends AbstractEdgeShapeTransformer<V, E>
/*     */     implements EdgeShape.IndexedRendering<V, E>
/*     */   {
/* 439 */     private static Line2D instance = new Line2D.Float(0.0F, 0.0F, 1.0F, 0.0F);
/*     */     
/*     */     protected EdgeIndexFunction<V, E> edgeIndexFunction;
/*     */     
/*     */     public void setEdgeIndexFunction(EdgeIndexFunction<V, E> edgeIndexFunction)
/*     */     {
/* 445 */       this.edgeIndexFunction = edgeIndexFunction;
/* 446 */       EdgeShape.box.setEdgeIndexFunction(edgeIndexFunction);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public EdgeIndexFunction<V, E> getEdgeIndexFunction()
/*     */     {
/* 453 */       return this.edgeIndexFunction;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public Shape transform(Context<Graph<V, E>, E> context)
/*     */     {
/* 463 */       Graph<V, E> graph = (Graph)context.graph;
/* 464 */       E e = context.element;
/* 465 */       Pair<V> endpoints = graph.getEndpoints(e);
/* 466 */       if (endpoints != null) {
/* 467 */         boolean isLoop = endpoints.getFirst().equals(endpoints.getSecond());
/* 468 */         if (isLoop) {
/* 469 */           return EdgeShape.box.transform(context);
/*     */         }
/*     */       }
/* 472 */       return instance;
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract interface IndexedRendering<V, E>
/*     */   {
/*     */     public abstract void setEdgeIndexFunction(EdgeIndexFunction<V, E> paramEdgeIndexFunction);
/*     */     
/*     */     public abstract EdgeIndexFunction<V, E> getEdgeIndexFunction();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/decorators/EdgeShape.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */