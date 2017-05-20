/*     */ package edu.uci.ics.jung.visualization.control;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.layout.GraphElementAccessor;
/*     */ import edu.uci.ics.jung.algorithms.layout.Layout;
/*     */ import edu.uci.ics.jung.visualization.Layer;
/*     */ import edu.uci.ics.jung.visualization.MultiLayerTransformer;
/*     */ import edu.uci.ics.jung.visualization.RenderContext;
/*     */ import edu.uci.ics.jung.visualization.VisualizationViewer;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.util.Map;
/*     */ import javax.swing.JOptionPane;
/*     */ import org.apache.commons.collections15.Transformer;
/*     */ import org.apache.commons.collections15.functors.MapTransformer;
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
/*     */ public class LabelEditingGraphMousePlugin<V, E>
/*     */   extends AbstractGraphMousePlugin
/*     */   implements MouseListener
/*     */ {
/*     */   protected V vertex;
/*     */   protected E edge;
/*     */   
/*     */   public LabelEditingGraphMousePlugin()
/*     */   {
/*  53 */     this(16);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public LabelEditingGraphMousePlugin(int selectionModifiers)
/*     */   {
/*  62 */     super(selectionModifiers);
/*  63 */     this.cursor = Cursor.getPredefinedCursor(12);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mouseClicked(MouseEvent e)
/*     */   {
/*  85 */     if ((e.getModifiers() == this.modifiers) && (e.getClickCount() == 2)) {
/*  86 */       VisualizationViewer<V, E> vv = (VisualizationViewer)e.getSource();
/*  87 */       GraphElementAccessor<V, E> pickSupport = vv.getPickSupport();
/*  88 */       if (pickSupport != null) {
/*  89 */         Transformer<V, String> vs = vv.getRenderContext().getVertexLabelTransformer();
/*  90 */         if ((vs instanceof MapTransformer)) {
/*  91 */           Map<V, String> map = ((MapTransformer)vs).getMap();
/*  92 */           Layout<V, E> layout = vv.getGraphLayout();
/*     */           
/*  94 */           Point2D p = e.getPoint();
/*     */           
/*  96 */           V vertex = pickSupport.getVertex(layout, p.getX(), p.getY());
/*  97 */           if (vertex != null) {
/*  98 */             String newLabel = (String)vs.transform(vertex);
/*  99 */             newLabel = JOptionPane.showInputDialog("New Vertex Label for " + vertex);
/* 100 */             if (newLabel != null) {
/* 101 */               map.put(vertex, newLabel);
/* 102 */               vv.repaint();
/*     */             }
/* 104 */             return;
/*     */           }
/*     */         }
/* 107 */         Transformer<E, String> es = vv.getRenderContext().getEdgeLabelTransformer();
/* 108 */         if ((es instanceof MapTransformer)) {
/* 109 */           Map<E, String> map = ((MapTransformer)es).getMap();
/* 110 */           Layout<V, E> layout = vv.getGraphLayout();
/*     */           
/* 112 */           Point2D p = e.getPoint();
/*     */           
/* 114 */           Point2D ip = vv.getRenderContext().getMultiLayerTransformer().inverseTransform(Layer.VIEW, p);
/* 115 */           E edge = pickSupport.getEdge(layout, ip.getX(), ip.getY());
/* 116 */           if (edge != null) {
/* 117 */             String newLabel = JOptionPane.showInputDialog("New Edge Label for " + edge);
/* 118 */             if (newLabel != null) {
/* 119 */               map.put(edge, newLabel);
/* 120 */               vv.repaint();
/*     */             }
/* 122 */             return;
/*     */           }
/*     */         }
/*     */       }
/* 126 */       e.consume();
/*     */     }
/*     */   }
/*     */   
/*     */   public void mouseReleased(MouseEvent e) {}
/*     */   
/*     */   public void mousePressed(MouseEvent e) {}
/*     */   
/*     */   public void mouseEntered(MouseEvent e) {}
/*     */   
/*     */   public void mouseExited(MouseEvent e) {}
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/control/LabelEditingGraphMousePlugin.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */