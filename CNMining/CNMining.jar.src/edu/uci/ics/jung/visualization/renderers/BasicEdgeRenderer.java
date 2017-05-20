/*     */ package edu.uci.ics.jung.visualization.renderers;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.layout.Layout;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.util.Context;
/*     */ import edu.uci.ics.jung.graph.util.EdgeIndexFunction;
/*     */ import edu.uci.ics.jung.graph.util.EdgeType;
/*     */ import edu.uci.ics.jung.graph.util.Pair;
/*     */ import edu.uci.ics.jung.visualization.Layer;
/*     */ import edu.uci.ics.jung.visualization.MultiLayerTransformer;
/*     */ import edu.uci.ics.jung.visualization.RenderContext;
/*     */ import edu.uci.ics.jung.visualization.decorators.EdgeShape.IndexedRendering;
/*     */ import edu.uci.ics.jung.visualization.decorators.EdgeShape.Orthogonal;
/*     */ import edu.uci.ics.jung.visualization.transform.LensTransformer;
/*     */ import edu.uci.ics.jung.visualization.transform.MutableTransformer;
/*     */ import edu.uci.ics.jung.visualization.transform.shape.GraphicsDecorator;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Line2D.Double;
/*     */ import java.awt.geom.Line2D.Float;
/*     */ import java.awt.geom.PathIterator;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Point2D.Float;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.PrintStream;
/*     */ import javax.swing.JComponent;
/*     */ import org.apache.commons.collections15.Predicate;
/*     */ import org.apache.commons.collections15.Transformer;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BasicEdgeRenderer<V, E>
/*     */   implements Renderer.Edge<V, E>
/*     */ {
/*     */   public void paintEdge(RenderContext<V, E> rc, Layout<V, E> layout, E e)
/*     */   {
/*  43 */     GraphicsDecorator g2d = rc.getGraphicsContext();
/*  44 */     Graph<V, E> graph = layout.getGraph();
/*  45 */     if (!rc.getEdgeIncludePredicate().evaluate(Context.getInstance(graph, e))) {
/*  46 */       return;
/*     */     }
/*     */     
/*  49 */     Pair<V> endpoints = graph.getEndpoints(e);
/*  50 */     V v1 = endpoints.getFirst();
/*  51 */     V v2 = endpoints.getSecond();
/*  52 */     if ((!rc.getVertexIncludePredicate().evaluate(Context.getInstance(graph, v1))) || (!rc.getVertexIncludePredicate().evaluate(Context.getInstance(graph, v2))))
/*     */     {
/*  54 */       return;
/*     */     }
/*  56 */     Stroke new_stroke = (Stroke)rc.getEdgeStrokeTransformer().transform(e);
/*  57 */     Stroke old_stroke = g2d.getStroke();
/*  58 */     if (new_stroke != null) {
/*  59 */       g2d.setStroke(new_stroke);
/*     */     }
/*  61 */     drawSimpleEdge(rc, layout, e);
/*     */     
/*     */ 
/*  64 */     if (new_stroke != null) {
/*  65 */       g2d.setStroke(old_stroke);
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
/*     */   protected void drawSimpleEdge(RenderContext<V, E> rc, Layout<V, E> layout, E e)
/*     */   {
/*  79 */     GraphicsDecorator g = rc.getGraphicsContext();
/*  80 */     Graph<V, E> graph = layout.getGraph();
/*  81 */     Pair<V> endpoints = graph.getEndpoints(e);
/*  82 */     V v1 = endpoints.getFirst();
/*  83 */     V v2 = endpoints.getSecond();
/*     */     
/*  85 */     Point2D p1 = (Point2D)layout.transform(v1);
/*  86 */     Point2D p2 = (Point2D)layout.transform(v2);
/*  87 */     p1 = rc.getMultiLayerTransformer().transform(Layer.LAYOUT, p1);
/*  88 */     p2 = rc.getMultiLayerTransformer().transform(Layer.LAYOUT, p2);
/*  89 */     float x1 = (float)p1.getX();
/*  90 */     float y1 = (float)p1.getY();
/*  91 */     float x2 = (float)p2.getX();
/*  92 */     float y2 = (float)p2.getY();
/*     */     
/*  94 */     boolean isLoop = v1.equals(v2);
/*  95 */     Shape s2 = (Shape)rc.getVertexShapeTransformer().transform(v2);
/*  96 */     Shape edgeShape = (Shape)rc.getEdgeShapeTransformer().transform(Context.getInstance(graph, e));
/*     */     
/*  98 */     boolean edgeHit = true;
/*  99 */     boolean arrowHit = true;
/* 100 */     Rectangle deviceRectangle = null;
/* 101 */     JComponent vv = rc.getScreenDevice();
/* 102 */     if (vv != null) {
/* 103 */       Dimension d = vv.getSize();
/* 104 */       deviceRectangle = new Rectangle(0, 0, d.width, d.height);
/*     */     }
/*     */     
/* 107 */     AffineTransform xform = AffineTransform.getTranslateInstance(x1, y1);
/*     */     
/* 109 */     if (isLoop)
/*     */     {
/*     */ 
/*     */ 
/* 113 */       Rectangle2D s2Bounds = s2.getBounds2D();
/* 114 */       xform.scale(s2Bounds.getWidth(), s2Bounds.getHeight());
/* 115 */       xform.translate(0.0D, -edgeShape.getBounds2D().getWidth() / 2.0D);
/* 116 */     } else if ((rc.getEdgeShapeTransformer() instanceof EdgeShape.Orthogonal)) {
/* 117 */       float dx = x2 - x1;
/* 118 */       float dy = y2 - y1;
/* 119 */       int index = 0;
/* 120 */       if ((rc.getEdgeShapeTransformer() instanceof EdgeShape.IndexedRendering)) {
/* 121 */         EdgeIndexFunction<V, E> peif = ((EdgeShape.IndexedRendering)rc.getEdgeShapeTransformer()).getEdgeIndexFunction();
/*     */         
/* 123 */         index = peif.getIndex(graph, e);
/* 124 */         index *= 20;
/*     */       }
/* 126 */       GeneralPath gp = new GeneralPath();
/* 127 */       gp.moveTo(0.0F, 0.0F);
/* 128 */       if (x1 > x2) {
/* 129 */         if (y1 > y2) {
/* 130 */           gp.lineTo(0.0F, index);
/* 131 */           gp.lineTo(dx - index, index);
/* 132 */           gp.lineTo(dx - index, dy);
/* 133 */           gp.lineTo(dx, dy);
/*     */         } else {
/* 135 */           gp.lineTo(0.0F, -index);
/* 136 */           gp.lineTo(dx - index, -index);
/* 137 */           gp.lineTo(dx - index, dy);
/* 138 */           gp.lineTo(dx, dy);
/*     */         }
/*     */         
/*     */       }
/* 142 */       else if (y1 > y2) {
/* 143 */         gp.lineTo(0.0F, index);
/* 144 */         gp.lineTo(dx + index, index);
/* 145 */         gp.lineTo(dx + index, dy);
/* 146 */         gp.lineTo(dx, dy);
/*     */       }
/*     */       else {
/* 149 */         gp.lineTo(0.0F, -index);
/* 150 */         gp.lineTo(dx + index, -index);
/* 151 */         gp.lineTo(dx + index, dy);
/* 152 */         gp.lineTo(dx, dy);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 158 */       edgeShape = gp;
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/*     */ 
/* 164 */       float dx = x2 - x1;
/* 165 */       float dy = y2 - y1;
/* 166 */       float thetaRadians = (float)Math.atan2(dy, dx);
/* 167 */       xform.rotate(thetaRadians);
/* 168 */       float dist = (float)Math.sqrt(dx * dx + dy * dy);
/* 169 */       xform.scale(dist, 1.0D);
/*     */     }
/*     */     
/* 172 */     edgeShape = xform.createTransformedShape(edgeShape);
/*     */     
/* 174 */     MutableTransformer vt = rc.getMultiLayerTransformer().getTransformer(Layer.VIEW);
/* 175 */     if ((vt instanceof LensTransformer)) {
/* 176 */       vt = ((LensTransformer)vt).getDelegate();
/*     */     }
/* 178 */     edgeHit = vt.transform(edgeShape).intersects(deviceRectangle);
/*     */     
/* 180 */     if (edgeHit == true)
/*     */     {
/* 182 */       Paint oldPaint = g.getPaint();
/*     */       
/*     */ 
/*     */ 
/* 186 */       Paint fill_paint = (Paint)rc.getEdgeFillPaintTransformer().transform(e);
/* 187 */       if (fill_paint != null)
/*     */       {
/* 189 */         g.setPaint(fill_paint);
/* 190 */         g.fill(edgeShape);
/*     */       }
/* 192 */       Paint draw_paint = (Paint)rc.getEdgeDrawPaintTransformer().transform(e);
/* 193 */       if (draw_paint != null)
/*     */       {
/* 195 */         g.setPaint(draw_paint);
/* 196 */         g.draw(edgeShape);
/*     */       }
/*     */       
/* 199 */       float scalex = (float)g.getTransform().getScaleX();
/* 200 */       float scaley = (float)g.getTransform().getScaleY();
/*     */       
/* 202 */       if ((scalex < 0.3D) || (scaley < 0.3D)) { return;
/*     */       }
/* 204 */       if (rc.getEdgeArrowPredicate().evaluate(Context.getInstance(graph, e)))
/*     */       {
/* 206 */         Stroke new_stroke = (Stroke)rc.getEdgeArrowStrokeTransformer().transform(e);
/* 207 */         Stroke old_stroke = g.getStroke();
/* 208 */         if (new_stroke != null) {
/* 209 */           g.setStroke(new_stroke);
/*     */         }
/*     */         
/* 212 */         Shape destVertexShape = (Shape)rc.getVertexShapeTransformer().transform(graph.getEndpoints(e).getSecond());
/*     */         
/*     */ 
/* 215 */         AffineTransform xf = AffineTransform.getTranslateInstance(x2, y2);
/* 216 */         destVertexShape = xf.createTransformedShape(destVertexShape);
/*     */         
/* 218 */         arrowHit = rc.getMultiLayerTransformer().getTransformer(Layer.VIEW).transform(destVertexShape).intersects(deviceRectangle);
/* 219 */         if (arrowHit)
/*     */         {
/* 221 */           AffineTransform at = getArrowTransform(rc, new GeneralPath(edgeShape), destVertexShape);
/*     */           
/* 223 */           if (at == null) return;
/* 224 */           Shape arrow = (Shape)rc.getEdgeArrowTransformer().transform(Context.getInstance(graph, e));
/* 225 */           arrow = at.createTransformedShape(arrow);
/* 226 */           g.setPaint((Paint)rc.getArrowFillPaintTransformer().transform(e));
/* 227 */           g.fill(arrow);
/* 228 */           g.setPaint((Paint)rc.getArrowDrawPaintTransformer().transform(e));
/* 229 */           g.draw(arrow);
/*     */         }
/* 231 */         if (graph.getEdgeType(e) == EdgeType.UNDIRECTED) {
/* 232 */           Shape vertexShape = (Shape)rc.getVertexShapeTransformer().transform(graph.getEndpoints(e).getFirst());
/*     */           
/* 234 */           xf = AffineTransform.getTranslateInstance(x1, y1);
/* 235 */           vertexShape = xf.createTransformedShape(vertexShape);
/*     */           
/* 237 */           arrowHit = rc.getMultiLayerTransformer().getTransformer(Layer.VIEW).transform(vertexShape).intersects(deviceRectangle);
/*     */           
/* 239 */           if (arrowHit) {
/* 240 */             AffineTransform at = getReverseArrowTransform(rc, new GeneralPath(edgeShape), vertexShape, !isLoop);
/* 241 */             if (at == null) return;
/* 242 */             Shape arrow = (Shape)rc.getEdgeArrowTransformer().transform(Context.getInstance(graph, e));
/* 243 */             arrow = at.createTransformedShape(arrow);
/* 244 */             g.setPaint((Paint)rc.getArrowFillPaintTransformer().transform(e));
/* 245 */             g.fill(arrow);
/* 246 */             g.setPaint((Paint)rc.getArrowDrawPaintTransformer().transform(e));
/* 247 */             g.draw(arrow);
/*     */           }
/*     */         }
/*     */         
/* 251 */         if (new_stroke != null) {
/* 252 */           g.setStroke(old_stroke);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 257 */       g.setPaint(oldPaint);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public AffineTransform getArrowTransform(RenderContext<V, E> rc, GeneralPath edgeShape, Shape vertexShape)
/*     */   {
/* 266 */     float[] seg = new float[6];
/* 267 */     Point2D p1 = null;
/* 268 */     Point2D p2 = null;
/* 269 */     AffineTransform at = new AffineTransform();
/*     */     
/*     */ 
/* 272 */     for (PathIterator i = edgeShape.getPathIterator(null, 1.0D); !i.isDone(); i.next()) {
/* 273 */       int ret = i.currentSegment(seg);
/* 274 */       if (ret == 0) {
/* 275 */         p2 = new Point2D.Float(seg[0], seg[1]);
/* 276 */       } else if (ret == 1) {
/* 277 */         p1 = p2;
/* 278 */         p2 = new Point2D.Float(seg[0], seg[1]);
/* 279 */         if (vertexShape.contains(p2)) {
/* 280 */           at = getArrowTransform(rc, new Line2D.Float(p1, p2), vertexShape);
/* 281 */           break;
/*     */         }
/*     */       }
/*     */     }
/* 285 */     return at;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public AffineTransform getReverseArrowTransform(RenderContext<V, E> rc, GeneralPath edgeShape, Shape vertexShape)
/*     */   {
/* 293 */     return getReverseArrowTransform(rc, edgeShape, vertexShape, true);
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
/*     */   public AffineTransform getReverseArrowTransform(RenderContext<V, E> rc, GeneralPath edgeShape, Shape vertexShape, boolean passedGo)
/*     */   {
/* 309 */     float[] seg = new float[6];
/* 310 */     Point2D p1 = null;
/* 311 */     Point2D p2 = null;
/*     */     
/* 313 */     AffineTransform at = new AffineTransform();
/* 314 */     for (PathIterator i = edgeShape.getPathIterator(null, 1.0D); !i.isDone(); i.next()) {
/* 315 */       int ret = i.currentSegment(seg);
/* 316 */       if (ret == 0) {
/* 317 */         p2 = new Point2D.Float(seg[0], seg[1]);
/* 318 */       } else if (ret == 1) {
/* 319 */         p1 = p2;
/* 320 */         p2 = new Point2D.Float(seg[0], seg[1]);
/* 321 */         if ((!passedGo) && (vertexShape.contains(p2))) {
/* 322 */           passedGo = true;
/* 323 */         } else if ((passedGo == true) && (!vertexShape.contains(p2)))
/*     */         {
/* 325 */           at = getReverseArrowTransform(rc, new Line2D.Float(p1, p2), vertexShape);
/* 326 */           break;
/*     */         }
/*     */       }
/*     */     }
/* 330 */     return at;
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
/*     */   public AffineTransform getArrowTransform(RenderContext<V, E> rc, Line2D edgeShape, Shape vertexShape)
/*     */   {
/* 343 */     float dx = (float)(edgeShape.getX1() - edgeShape.getX2());
/* 344 */     float dy = (float)(edgeShape.getY1() - edgeShape.getY2());
/*     */     
/*     */ 
/* 347 */     while (dx * dx + dy * dy > rc.getArrowPlacementTolerance()) {
/*     */       try {
/* 349 */         edgeShape = getLastOutsideSegment(edgeShape, vertexShape);
/*     */       } catch (IllegalArgumentException e) {
/* 351 */         System.err.println(e.toString());
/* 352 */         return null;
/*     */       }
/* 354 */       dx = (float)(edgeShape.getX1() - edgeShape.getX2());
/* 355 */       dy = (float)(edgeShape.getY1() - edgeShape.getY2());
/*     */     }
/* 357 */     double atheta = Math.atan2(dx, dy) + 1.5707963267948966D;
/* 358 */     AffineTransform at = AffineTransform.getTranslateInstance(edgeShape.getX1(), edgeShape.getY1());
/*     */     
/* 360 */     at.rotate(-atheta);
/* 361 */     return at;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected AffineTransform getReverseArrowTransform(RenderContext<V, E> rc, Line2D edgeShape, Shape vertexShape)
/*     */   {
/* 373 */     float dx = (float)(edgeShape.getX1() - edgeShape.getX2());
/* 374 */     float dy = (float)(edgeShape.getY1() - edgeShape.getY2());
/*     */     
/*     */ 
/* 377 */     while (dx * dx + dy * dy > rc.getArrowPlacementTolerance()) {
/*     */       try {
/* 379 */         edgeShape = getFirstOutsideSegment(edgeShape, vertexShape);
/*     */       } catch (IllegalArgumentException e) {
/* 381 */         System.err.println(e.toString());
/* 382 */         return null;
/*     */       }
/* 384 */       dx = (float)(edgeShape.getX1() - edgeShape.getX2());
/* 385 */       dy = (float)(edgeShape.getY1() - edgeShape.getY2());
/*     */     }
/*     */     
/* 388 */     double atheta = Math.atan2(dx, dy) - 1.5707963267948966D;
/* 389 */     AffineTransform at = AffineTransform.getTranslateInstance(edgeShape.getX1(), edgeShape.getY1());
/* 390 */     at.rotate(-atheta);
/* 391 */     return at;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Line2D getLastOutsideSegment(Line2D line, Shape shape)
/*     */   {
/* 403 */     if (!shape.contains(line.getP2())) {
/* 404 */       String errorString = "line end point: " + line.getP2() + " is not contained in shape: " + shape.getBounds2D();
/*     */       
/* 406 */       throw new IllegalArgumentException(errorString);
/*     */     }
/*     */     
/* 409 */     Line2D left = new Line2D.Double();
/* 410 */     Line2D right = new Line2D.Double();
/*     */     
/*     */     do
/*     */     {
/* 414 */       subdivide(line, left, right);
/* 415 */       line = right;
/* 416 */     } while (!shape.contains(line.getP1()));
/*     */     
/*     */ 
/* 419 */     return left;
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
/*     */   protected Line2D getFirstOutsideSegment(Line2D line, Shape shape)
/*     */   {
/* 432 */     if (!shape.contains(line.getP1())) {
/* 433 */       String errorString = "line start point: " + line.getP1() + " is not contained in shape: " + shape.getBounds2D();
/*     */       
/* 435 */       throw new IllegalArgumentException(errorString);
/*     */     }
/* 437 */     Line2D left = new Line2D.Float();
/* 438 */     Line2D right = new Line2D.Float();
/*     */     
/*     */     do
/*     */     {
/* 442 */       subdivide(line, left, right);
/* 443 */       line = left;
/* 444 */     } while (!shape.contains(line.getP2()));
/*     */     
/*     */ 
/* 447 */     return right;
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
/*     */   protected void subdivide(Line2D src, Line2D left, Line2D right)
/*     */   {
/* 460 */     double x1 = src.getX1();
/* 461 */     double y1 = src.getY1();
/* 462 */     double x2 = src.getX2();
/* 463 */     double y2 = src.getY2();
/*     */     
/* 465 */     double mx = x1 + (x2 - x1) / 2.0D;
/* 466 */     double my = y1 + (y2 - y1) / 2.0D;
/* 467 */     if (left != null) {
/* 468 */       left.setLine(x1, y1, mx, my);
/*     */     }
/* 470 */     if (right != null) {
/* 471 */       right.setLine(mx, my, x2, y2);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/renderers/BasicEdgeRenderer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */