/*     */ package edu.uci.ics.jung.visualization.renderers;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.layout.Layout;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.util.Context;
/*     */ import edu.uci.ics.jung.graph.util.EdgeType;
/*     */ import edu.uci.ics.jung.graph.util.Pair;
/*     */ import edu.uci.ics.jung.visualization.Layer;
/*     */ import edu.uci.ics.jung.visualization.MultiLayerTransformer;
/*     */ import edu.uci.ics.jung.visualization.RenderContext;
/*     */ import edu.uci.ics.jung.visualization.transform.LensTransformer;
/*     */ import edu.uci.ics.jung.visualization.transform.MutableTransformer;
/*     */ import edu.uci.ics.jung.visualization.transform.shape.TransformingGraphics;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Line2D.Double;
/*     */ import java.awt.geom.Line2D.Float;
/*     */ import java.awt.geom.PathIterator;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Point2D.Float;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.RectangularShape;
/*     */ import java.io.PrintStream;
/*     */ import javax.swing.JComponent;
/*     */ import org.apache.commons.collections15.Predicate;
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
/*     */ public class ReshapingEdgeRenderer<V, E>
/*     */   extends BasicEdgeRenderer<V, E>
/*     */   implements Renderer.Edge<V, E>
/*     */ {
/*     */   protected void drawSimpleEdge(RenderContext<V, E> rc, Layout<V, E> layout, E e)
/*     */   {
/*  59 */     TransformingGraphics g = (TransformingGraphics)rc.getGraphicsContext();
/*  60 */     Graph<V, E> graph = layout.getGraph();
/*  61 */     Pair<V> endpoints = graph.getEndpoints(e);
/*  62 */     V v1 = endpoints.getFirst();
/*  63 */     V v2 = endpoints.getSecond();
/*  64 */     Point2D p1 = (Point2D)layout.transform(v1);
/*  65 */     Point2D p2 = (Point2D)layout.transform(v2);
/*  66 */     p1 = rc.getMultiLayerTransformer().transform(Layer.LAYOUT, p1);
/*  67 */     p2 = rc.getMultiLayerTransformer().transform(Layer.LAYOUT, p2);
/*  68 */     float x1 = (float)p1.getX();
/*  69 */     float y1 = (float)p1.getY();
/*  70 */     float x2 = (float)p2.getX();
/*  71 */     float y2 = (float)p2.getY();
/*     */     
/*  73 */     float flatness = 0.0F;
/*  74 */     MutableTransformer transformer = rc.getMultiLayerTransformer().getTransformer(Layer.VIEW);
/*  75 */     if ((transformer instanceof LensTransformer)) {
/*  76 */       LensTransformer ht = (LensTransformer)transformer;
/*  77 */       RectangularShape lensShape = ht.getLensShape();
/*  78 */       if ((lensShape.contains(x1, y1)) || (lensShape.contains(x2, y2))) {
/*  79 */         flatness = 0.05F;
/*     */       }
/*     */     }
/*     */     
/*  83 */     boolean isLoop = v1.equals(v2);
/*  84 */     Shape s2 = (Shape)rc.getVertexShapeTransformer().transform(v2);
/*  85 */     Shape edgeShape = (Shape)rc.getEdgeShapeTransformer().transform(Context.getInstance(graph, e));
/*     */     
/*  87 */     boolean edgeHit = true;
/*  88 */     boolean arrowHit = true;
/*  89 */     Rectangle deviceRectangle = null;
/*  90 */     JComponent vv = rc.getScreenDevice();
/*  91 */     if (vv != null) {
/*  92 */       Dimension d = vv.getSize();
/*  93 */       deviceRectangle = new Rectangle(0, 0, d.width, d.height);
/*     */     }
/*     */     
/*  96 */     AffineTransform xform = AffineTransform.getTranslateInstance(x1, y1);
/*     */     
/*  98 */     if (isLoop)
/*     */     {
/*     */ 
/*     */ 
/* 102 */       Rectangle2D s2Bounds = s2.getBounds2D();
/* 103 */       xform.scale(s2Bounds.getWidth(), s2Bounds.getHeight());
/* 104 */       xform.translate(0.0D, -edgeShape.getBounds2D().getWidth() / 2.0D);
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 109 */       float dx = x2 - x1;
/* 110 */       float dy = y2 - y1;
/* 111 */       float thetaRadians = (float)Math.atan2(dy, dx);
/* 112 */       xform.rotate(thetaRadians);
/* 113 */       float dist = (float)Math.sqrt(dx * dx + dy * dy);
/* 114 */       xform.scale(dist, 1.0D);
/*     */     }
/*     */     
/* 117 */     edgeShape = xform.createTransformedShape(edgeShape);
/*     */     
/* 119 */     MutableTransformer vt = rc.getMultiLayerTransformer().getTransformer(Layer.VIEW);
/* 120 */     if ((vt instanceof LensTransformer)) {
/* 121 */       vt = ((LensTransformer)vt).getDelegate();
/*     */     }
/* 123 */     edgeHit = vt.transform(edgeShape).intersects(deviceRectangle);
/*     */     
/* 125 */     if (edgeHit == true)
/*     */     {
/* 127 */       Paint oldPaint = g.getPaint();
/*     */       
/*     */ 
/*     */ 
/* 131 */       Paint fill_paint = (Paint)rc.getEdgeFillPaintTransformer().transform(e);
/* 132 */       if (fill_paint != null)
/*     */       {
/* 134 */         g.setPaint(fill_paint);
/* 135 */         g.fill(edgeShape, flatness);
/*     */       }
/* 137 */       Paint draw_paint = (Paint)rc.getEdgeDrawPaintTransformer().transform(e);
/* 138 */       if (draw_paint != null)
/*     */       {
/* 140 */         g.setPaint(draw_paint);
/* 141 */         g.draw(edgeShape, flatness);
/*     */       }
/*     */       
/* 144 */       float scalex = (float)g.getTransform().getScaleX();
/* 145 */       float scaley = (float)g.getTransform().getScaleY();
/*     */       
/* 147 */       if ((scalex < 0.3D) || (scaley < 0.3D)) { return;
/*     */       }
/* 149 */       if (rc.getEdgeArrowPredicate().evaluate(Context.getInstance(graph, e)))
/*     */       {
/* 151 */         Shape destVertexShape = (Shape)rc.getVertexShapeTransformer().transform(graph.getEndpoints(e).getSecond());
/*     */         
/*     */ 
/* 154 */         AffineTransform xf = AffineTransform.getTranslateInstance(x2, y2);
/* 155 */         destVertexShape = xf.createTransformedShape(destVertexShape);
/*     */         
/* 157 */         arrowHit = rc.getMultiLayerTransformer().getTransformer(Layer.VIEW).transform(destVertexShape).intersects(deviceRectangle);
/* 158 */         if (arrowHit)
/*     */         {
/* 160 */           AffineTransform at = getArrowTransform(rc, new GeneralPath(edgeShape), destVertexShape);
/*     */           
/* 162 */           if (at == null) return;
/* 163 */           Shape arrow = (Shape)rc.getEdgeArrowTransformer().transform(Context.getInstance(graph, e));
/* 164 */           arrow = at.createTransformedShape(arrow);
/* 165 */           g.setPaint((Paint)rc.getArrowFillPaintTransformer().transform(e));
/* 166 */           g.fill(arrow);
/* 167 */           g.setPaint((Paint)rc.getArrowDrawPaintTransformer().transform(e));
/* 168 */           g.draw(arrow);
/*     */         }
/* 170 */         if (graph.getEdgeType(e) == EdgeType.UNDIRECTED) {
/* 171 */           Shape vertexShape = (Shape)rc.getVertexShapeTransformer().transform(graph.getEndpoints(e).getFirst());
/*     */           
/* 173 */           xf = AffineTransform.getTranslateInstance(x1, y1);
/* 174 */           vertexShape = xf.createTransformedShape(vertexShape);
/*     */           
/* 176 */           arrowHit = rc.getMultiLayerTransformer().getTransformer(Layer.VIEW).transform(vertexShape).intersects(deviceRectangle);
/*     */           
/* 178 */           if (arrowHit) {
/* 179 */             AffineTransform at = getReverseArrowTransform(rc, new GeneralPath(edgeShape), vertexShape, !isLoop);
/* 180 */             if (at == null) return;
/* 181 */             Shape arrow = (Shape)rc.getEdgeArrowTransformer().transform(Context.getInstance(graph, e));
/* 182 */             arrow = at.createTransformedShape(arrow);
/* 183 */             g.setPaint((Paint)rc.getArrowFillPaintTransformer().transform(e));
/* 184 */             g.fill(arrow);
/* 185 */             g.setPaint((Paint)rc.getArrowDrawPaintTransformer().transform(e));
/* 186 */             g.draw(arrow);
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 191 */       if (draw_paint == null) {
/* 192 */         g.setPaint(oldPaint);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 200 */       g.setPaint(oldPaint);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public AffineTransform getArrowTransform(RenderContext<V, E> rc, GeneralPath edgeShape, Shape vertexShape)
/*     */   {
/* 209 */     float[] seg = new float[6];
/* 210 */     Point2D p1 = null;
/* 211 */     Point2D p2 = null;
/* 212 */     AffineTransform at = new AffineTransform();
/*     */     
/*     */ 
/* 215 */     for (PathIterator i = edgeShape.getPathIterator(null, 1.0D); !i.isDone(); i.next()) {
/* 216 */       int ret = i.currentSegment(seg);
/* 217 */       if (ret == 0) {
/* 218 */         p2 = new Point2D.Float(seg[0], seg[1]);
/* 219 */       } else if (ret == 1) {
/* 220 */         p1 = p2;
/* 221 */         p2 = new Point2D.Float(seg[0], seg[1]);
/* 222 */         if (vertexShape.contains(p2)) {
/* 223 */           at = getArrowTransform(rc, new Line2D.Float(p1, p2), vertexShape);
/* 224 */           break;
/*     */         }
/*     */       }
/*     */     }
/* 228 */     return at;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public AffineTransform getReverseArrowTransform(RenderContext<V, E> rc, GeneralPath edgeShape, Shape vertexShape)
/*     */   {
/* 236 */     return getReverseArrowTransform(rc, edgeShape, vertexShape, true);
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
/* 252 */     float[] seg = new float[6];
/* 253 */     Point2D p1 = null;
/* 254 */     Point2D p2 = null;
/*     */     
/* 256 */     AffineTransform at = new AffineTransform();
/* 257 */     for (PathIterator i = edgeShape.getPathIterator(null, 1.0D); !i.isDone(); i.next()) {
/* 258 */       int ret = i.currentSegment(seg);
/* 259 */       if (ret == 0) {
/* 260 */         p2 = new Point2D.Float(seg[0], seg[1]);
/* 261 */       } else if (ret == 1) {
/* 262 */         p1 = p2;
/* 263 */         p2 = new Point2D.Float(seg[0], seg[1]);
/* 264 */         if ((!passedGo) && (vertexShape.contains(p2))) {
/* 265 */           passedGo = true;
/* 266 */         } else if ((passedGo == true) && (!vertexShape.contains(p2)))
/*     */         {
/* 268 */           at = getReverseArrowTransform(rc, new Line2D.Float(p1, p2), vertexShape);
/* 269 */           break;
/*     */         }
/*     */       }
/*     */     }
/* 273 */     return at;
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
/* 286 */     float dx = (float)(edgeShape.getX1() - edgeShape.getX2());
/* 287 */     float dy = (float)(edgeShape.getY1() - edgeShape.getY2());
/*     */     
/*     */ 
/* 290 */     while (dx * dx + dy * dy > rc.getArrowPlacementTolerance()) {
/*     */       try {
/* 292 */         edgeShape = getLastOutsideSegment(edgeShape, vertexShape);
/*     */       } catch (IllegalArgumentException e) {
/* 294 */         System.err.println(e.toString());
/* 295 */         return null;
/*     */       }
/* 297 */       dx = (float)(edgeShape.getX1() - edgeShape.getX2());
/* 298 */       dy = (float)(edgeShape.getY1() - edgeShape.getY2());
/*     */     }
/* 300 */     double atheta = Math.atan2(dx, dy) + 1.5707963267948966D;
/* 301 */     AffineTransform at = AffineTransform.getTranslateInstance(edgeShape.getX1(), edgeShape.getY1());
/*     */     
/* 303 */     at.rotate(-atheta);
/* 304 */     return at;
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
/* 316 */     float dx = (float)(edgeShape.getX1() - edgeShape.getX2());
/* 317 */     float dy = (float)(edgeShape.getY1() - edgeShape.getY2());
/*     */     
/*     */ 
/* 320 */     while (dx * dx + dy * dy > rc.getArrowPlacementTolerance()) {
/*     */       try {
/* 322 */         edgeShape = getFirstOutsideSegment(edgeShape, vertexShape);
/*     */       } catch (IllegalArgumentException e) {
/* 324 */         System.err.println(e.toString());
/* 325 */         return null;
/*     */       }
/* 327 */       dx = (float)(edgeShape.getX1() - edgeShape.getX2());
/* 328 */       dy = (float)(edgeShape.getY1() - edgeShape.getY2());
/*     */     }
/*     */     
/* 331 */     double atheta = Math.atan2(dx, dy) - 1.5707963267948966D;
/* 332 */     AffineTransform at = AffineTransform.getTranslateInstance(edgeShape.getX1(), edgeShape.getY1());
/* 333 */     at.rotate(-atheta);
/* 334 */     return at;
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
/* 346 */     if (!shape.contains(line.getP2())) {
/* 347 */       String errorString = "line end point: " + line.getP2() + " is not contained in shape: " + shape.getBounds2D();
/*     */       
/* 349 */       throw new IllegalArgumentException(errorString);
/*     */     }
/*     */     
/* 352 */     Line2D left = new Line2D.Double();
/* 353 */     Line2D right = new Line2D.Double();
/*     */     
/*     */     do
/*     */     {
/* 357 */       subdivide(line, left, right);
/* 358 */       line = right;
/* 359 */     } while (!shape.contains(line.getP1()));
/*     */     
/*     */ 
/* 362 */     return left;
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
/* 375 */     if (!shape.contains(line.getP1())) {
/* 376 */       String errorString = "line start point: " + line.getP1() + " is not contained in shape: " + shape.getBounds2D();
/*     */       
/* 378 */       throw new IllegalArgumentException(errorString);
/*     */     }
/* 380 */     Line2D left = new Line2D.Float();
/* 381 */     Line2D right = new Line2D.Float();
/*     */     
/*     */     do
/*     */     {
/* 385 */       subdivide(line, left, right);
/* 386 */       line = left;
/* 387 */     } while (!shape.contains(line.getP2()));
/*     */     
/*     */ 
/* 390 */     return right;
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
/* 403 */     double x1 = src.getX1();
/* 404 */     double y1 = src.getY1();
/* 405 */     double x2 = src.getX2();
/* 406 */     double y2 = src.getY2();
/*     */     
/* 408 */     double mx = x1 + (x2 - x1) / 2.0D;
/* 409 */     double my = y1 + (y2 - y1) / 2.0D;
/* 410 */     if (left != null) {
/* 411 */       left.setLine(x1, y1, mx, my);
/*     */     }
/* 413 */     if (right != null) {
/* 414 */       right.setLine(mx, my, x2, y2);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/renderers/ReshapingEdgeRenderer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */