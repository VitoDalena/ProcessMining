/*     */ package edu.uci.ics.jung.visualization;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.layout.Layout;
/*     */ import edu.uci.ics.jung.visualization.transform.BidirectionalTransformer;
/*     */ import edu.uci.ics.jung.visualization.transform.MutableTransformer;
/*     */ import edu.uci.ics.jung.visualization.transform.shape.Intersector;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.AdjustmentEvent;
/*     */ import java.awt.event.AdjustmentListener;
/*     */ import java.awt.event.ComponentAdapter;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Line2D.Double;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Point2D.Double;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.Set;
/*     */ import javax.swing.BoundedRangeModel;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollBar;
/*     */ import javax.swing.event.ChangeEvent;
/*     */ import javax.swing.event.ChangeListener;
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
/*     */ public class GraphZoomScrollPane
/*     */   extends JPanel
/*     */ {
/*     */   protected VisualizationViewer vv;
/*     */   protected JScrollBar horizontalScrollBar;
/*     */   protected JScrollBar verticalScrollBar;
/*     */   protected JComponent corner;
/*  64 */   protected boolean scrollBarsMayControlAdjusting = true;
/*     */   
/*     */ 
/*     */   protected JPanel south;
/*     */   
/*     */ 
/*     */ 
/*     */   public GraphZoomScrollPane(VisualizationViewer vv)
/*     */   {
/*  73 */     super(new BorderLayout());
/*  74 */     this.vv = vv;
/*  75 */     addComponentListener(new ResizeListener());
/*  76 */     Dimension d = vv.getGraphLayout().getSize();
/*  77 */     this.verticalScrollBar = new JScrollBar(1, 0, d.height, 0, d.height);
/*  78 */     this.horizontalScrollBar = new JScrollBar(0, 0, d.width, 0, d.width);
/*  79 */     this.verticalScrollBar.addAdjustmentListener(new VerticalAdjustmentListenerImpl());
/*  80 */     this.horizontalScrollBar.addAdjustmentListener(new HorizontalAdjustmentListenerImpl());
/*  81 */     this.verticalScrollBar.setUnitIncrement(20);
/*  82 */     this.horizontalScrollBar.setUnitIncrement(20);
/*     */     
/*     */ 
/*  85 */     vv.addChangeListener(new ChangeListener()
/*     */     {
/*     */       public void stateChanged(ChangeEvent evt) {
/*  88 */         VisualizationViewer vv = (VisualizationViewer)evt.getSource();
/*     */         
/*  90 */         GraphZoomScrollPane.this.setScrollBars(vv);
/*     */       }
/*  92 */     });
/*  93 */     add(vv);
/*  94 */     add(this.verticalScrollBar, "East");
/*  95 */     this.south = new JPanel(new BorderLayout());
/*  96 */     this.south.add(this.horizontalScrollBar);
/*  97 */     setCorner(new JPanel());
/*  98 */     add(this.south, "South");
/*     */   }
/*     */   
/*     */   class HorizontalAdjustmentListenerImpl
/*     */     implements AdjustmentListener
/*     */   {
/*     */     HorizontalAdjustmentListenerImpl() {}
/*     */     
/* 106 */     int previous = 0;
/*     */     
/* 108 */     public void adjustmentValueChanged(AdjustmentEvent e) { int hval = e.getValue();
/* 109 */       float dh = this.previous - hval;
/* 110 */       this.previous = hval;
/* 111 */       if ((dh != 0.0F) && (GraphZoomScrollPane.this.scrollBarsMayControlAdjusting))
/*     */       {
/* 113 */         float layoutScale = (float)GraphZoomScrollPane.this.vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT).getScale();
/* 114 */         dh *= layoutScale;
/* 115 */         AffineTransform at = AffineTransform.getTranslateInstance(dh, 0.0D);
/* 116 */         GraphZoomScrollPane.this.vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT).preConcatenate(at);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   class VerticalAdjustmentListenerImpl
/*     */     implements AdjustmentListener
/*     */   {
/*     */     VerticalAdjustmentListenerImpl() {}
/*     */     
/* 126 */     int previous = 0;
/*     */     
/* 128 */     public void adjustmentValueChanged(AdjustmentEvent e) { JScrollBar sb = (JScrollBar)e.getSource();
/* 129 */       BoundedRangeModel m = sb.getModel();
/* 130 */       int vval = m.getValue();
/* 131 */       float dv = this.previous - vval;
/* 132 */       this.previous = vval;
/* 133 */       if ((dv != 0.0F) && (GraphZoomScrollPane.this.scrollBarsMayControlAdjusting))
/*     */       {
/*     */ 
/* 136 */         float layoutScale = (float)GraphZoomScrollPane.this.vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT).getScale();
/* 137 */         dv *= layoutScale;
/* 138 */         AffineTransform at = AffineTransform.getTranslateInstance(0.0D, dv);
/* 139 */         GraphZoomScrollPane.this.vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT).preConcatenate(at);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void setScrollBars(VisualizationViewer vv)
/*     */   {
/* 151 */     Dimension d = vv.getGraphLayout().getSize();
/* 152 */     Rectangle2D vvBounds = vv.getBounds();
/*     */     
/*     */ 
/* 155 */     Rectangle layoutRectangle = new Rectangle(0, 0, d.width, d.height);
/*     */     
/*     */ 
/*     */ 
/* 159 */     BidirectionalTransformer viewTransformer = vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW);
/* 160 */     BidirectionalTransformer layoutTransformer = vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT);
/*     */     
/* 162 */     Point2D h0 = new Point2D.Double(vvBounds.getMinX(), vvBounds.getCenterY());
/* 163 */     Point2D h1 = new Point2D.Double(vvBounds.getMaxX(), vvBounds.getCenterY());
/* 164 */     Point2D v0 = new Point2D.Double(vvBounds.getCenterX(), vvBounds.getMinY());
/* 165 */     Point2D v1 = new Point2D.Double(vvBounds.getCenterX(), vvBounds.getMaxY());
/*     */     
/* 167 */     h0 = viewTransformer.inverseTransform(h0);
/* 168 */     h0 = layoutTransformer.inverseTransform(h0);
/* 169 */     h1 = viewTransformer.inverseTransform(h1);
/* 170 */     h1 = layoutTransformer.inverseTransform(h1);
/* 171 */     v0 = viewTransformer.inverseTransform(v0);
/* 172 */     v0 = layoutTransformer.inverseTransform(v0);
/* 173 */     v1 = viewTransformer.inverseTransform(v1);
/* 174 */     v1 = layoutTransformer.inverseTransform(v1);
/*     */     
/* 176 */     this.scrollBarsMayControlAdjusting = false;
/* 177 */     setScrollBarValues(layoutRectangle, h0, h1, v0, v1);
/* 178 */     this.scrollBarsMayControlAdjusting = true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void setScrollBarValues(Rectangle rectangle, Point2D h0, Point2D h1, Point2D v0, Point2D v1)
/*     */   {
/* 185 */     boolean containsH0 = rectangle.contains(h0);
/* 186 */     boolean containsH1 = rectangle.contains(h1);
/* 187 */     boolean containsV0 = rectangle.contains(v0);
/* 188 */     boolean containsV1 = rectangle.contains(v1);
/*     */     
/*     */ 
/*     */ 
/* 192 */     Intersector intersector = new Intersector(rectangle, new Line2D.Double(h0, h1));
/*     */     
/* 194 */     int min = 0;
/*     */     
/* 196 */     int val = 0;
/*     */     
/*     */ 
/* 199 */     Set points = intersector.getPoints();
/* 200 */     Point2D first = null;
/* 201 */     Point2D second = null;
/*     */     
/* 203 */     Point2D[] pointArray = (Point2D[])points.toArray(new Point2D[points.size()]);
/* 204 */     if (pointArray.length > 1) {
/* 205 */       first = pointArray[0];
/* 206 */       second = pointArray[1];
/* 207 */     } else if (pointArray.length > 0) {
/* 208 */       first = second = pointArray[0];
/*     */     }
/*     */     
/* 211 */     if ((first != null) && (second != null))
/*     */     {
/* 213 */       if ((h0.getX() - h1.getX()) * (first.getX() - second.getX()) < 0.0D)
/*     */       {
/* 215 */         Point2D temp = first;
/* 216 */         first = second;
/* 217 */         second = temp; }
/*     */       int ext;
/*     */       int ext;
/* 220 */       int max; if ((containsH0) && (containsH1)) {
/* 221 */         int max = (int)first.distance(second);
/* 222 */         val = (int)first.distance(h0);
/* 223 */         ext = (int)h0.distance(h1);
/*     */       } else { int ext;
/* 225 */         if (containsH0) {
/* 226 */           int max = (int)first.distance(second);
/* 227 */           val = (int)first.distance(h0);
/* 228 */           ext = (int)h0.distance(second);
/*     */         } else { int ext;
/* 230 */           if (containsH1) {
/* 231 */             int max = (int)first.distance(second);
/* 232 */             val = 0;
/* 233 */             ext = (int)first.distance(h1);
/*     */           }
/*     */           else {
/* 236 */             max = ext = rectangle.width;
/* 237 */             val = min;
/*     */           } } }
/* 239 */       this.horizontalScrollBar.setValues(val, ext + 1, min, max);
/*     */     }
/*     */     
/*     */ 
/* 243 */     min = val = 0;
/*     */     
/* 245 */     intersector.intersectLine(new Line2D.Double(v0, v1));
/* 246 */     points = intersector.getPoints();
/*     */     
/* 248 */     pointArray = (Point2D[])points.toArray(new Point2D[points.size()]);
/* 249 */     if (pointArray.length > 1) {
/* 250 */       first = pointArray[0];
/* 251 */       second = pointArray[1];
/* 252 */     } else if (pointArray.length > 0) {
/* 253 */       first = second = pointArray[0];
/*     */     }
/*     */     
/* 256 */     if ((first != null) && (second != null))
/*     */     {
/*     */ 
/* 259 */       if ((v0.getY() - v1.getY()) * (first.getY() - second.getY()) < 0.0D)
/*     */       {
/* 261 */         Point2D temp = first;
/* 262 */         first = second;
/* 263 */         second = temp; }
/*     */       int ext;
/*     */       int ext;
/* 266 */       int max; if ((containsV0) && (containsV1)) {
/* 267 */         int max = (int)first.distance(second);
/* 268 */         val = (int)first.distance(v0);
/* 269 */         ext = (int)v0.distance(v1);
/*     */       } else { int ext;
/* 271 */         if (containsV0) {
/* 272 */           int max = (int)first.distance(second);
/* 273 */           val = (int)first.distance(v0);
/* 274 */           ext = (int)v0.distance(second);
/*     */         } else { int ext;
/* 276 */           if (containsV1) {
/* 277 */             int max = (int)first.distance(second);
/* 278 */             val = 0;
/* 279 */             ext = (int)first.distance(v1);
/*     */           }
/*     */           else {
/* 282 */             max = ext = rectangle.height;
/* 283 */             val = min;
/*     */           } } }
/* 285 */       this.verticalScrollBar.setValues(val, ext + 1, min, max);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected class ResizeListener
/*     */     extends ComponentAdapter
/*     */   {
/*     */     protected ResizeListener() {}
/*     */     
/*     */     public void componentHidden(ComponentEvent e) {}
/*     */     
/*     */     public void componentResized(ComponentEvent e)
/*     */     {
/* 299 */       GraphZoomScrollPane.this.setScrollBars(GraphZoomScrollPane.this.vv);
/*     */     }
/*     */     
/*     */ 
/*     */     public void componentShown(ComponentEvent e) {}
/*     */   }
/*     */   
/*     */ 
/*     */   public JComponent getCorner()
/*     */   {
/* 309 */     return this.corner;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setCorner(JComponent corner)
/*     */   {
/* 316 */     this.corner = corner;
/* 317 */     corner.setPreferredSize(new Dimension(this.verticalScrollBar.getPreferredSize().width, this.horizontalScrollBar.getPreferredSize().height));
/*     */     
/* 319 */     this.south.add(this.corner, "East");
/*     */   }
/*     */   
/*     */   public JScrollBar getHorizontalScrollBar() {
/* 323 */     return this.horizontalScrollBar;
/*     */   }
/*     */   
/*     */   public JScrollBar getVerticalScrollBar() {
/* 327 */     return this.verticalScrollBar;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/GraphZoomScrollPane.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */