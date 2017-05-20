/*     */ package edu.uci.ics.jung.visualization.control;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.layout.GraphElementAccessor;
/*     */ import edu.uci.ics.jung.algorithms.layout.Layout;
/*     */ import edu.uci.ics.jung.graph.DirectedGraph;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.UndirectedGraph;
/*     */ import edu.uci.ics.jung.graph.util.EdgeType;
/*     */ import edu.uci.ics.jung.visualization.MultiLayerTransformer;
/*     */ import edu.uci.ics.jung.visualization.RenderContext;
/*     */ import edu.uci.ics.jung.visualization.VisualizationViewer;
/*     */ import edu.uci.ics.jung.visualization.picking.PickedState;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.util.Set;
/*     */ import javax.swing.AbstractAction;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JPopupMenu;
/*     */ import org.apache.commons.collections15.Factory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EditingPopupGraphMousePlugin<V, E>
/*     */   extends AbstractPopupGraphMousePlugin
/*     */ {
/*     */   protected Factory<V> vertexFactory;
/*     */   protected Factory<E> edgeFactory;
/*  34 */   protected JPopupMenu popup = new JPopupMenu();
/*     */   
/*     */   public EditingPopupGraphMousePlugin(Factory<V> vertexFactory, Factory<E> edgeFactory) {
/*  37 */     this.vertexFactory = vertexFactory;
/*  38 */     this.edgeFactory = edgeFactory;
/*     */   }
/*     */   
/*     */   protected void handlePopup(MouseEvent e)
/*     */   {
/*  43 */     final VisualizationViewer<V, E> vv = (VisualizationViewer)e.getSource();
/*     */     
/*  45 */     final Layout<V, E> layout = vv.getGraphLayout();
/*  46 */     final Graph<V, E> graph = layout.getGraph();
/*  47 */     final Point2D p = e.getPoint();
/*  48 */     Point2D ivp = p;
/*  49 */     GraphElementAccessor<V, E> pickSupport = vv.getPickSupport();
/*  50 */     if (pickSupport != null)
/*     */     {
/*  52 */       final V vertex = pickSupport.getVertex(layout, ivp.getX(), ivp.getY());
/*  53 */       final E edge = pickSupport.getEdge(layout, ivp.getX(), ivp.getY());
/*  54 */       final PickedState<V> pickedVertexState = vv.getPickedVertexState();
/*  55 */       final PickedState<E> pickedEdgeState = vv.getPickedEdgeState();
/*     */       
/*  57 */       if (vertex != null) {
/*  58 */         Set<V> picked = pickedVertexState.getPicked();
/*  59 */         JMenu undirectedMenu; if (picked.size() > 0) { JMenu directedMenu;
/*  60 */           if (!(graph instanceof UndirectedGraph)) {
/*  61 */             directedMenu = new JMenu("Create Directed Edge");
/*  62 */             this.popup.add(directedMenu);
/*  63 */             for (final V other : picked) {
/*  64 */               directedMenu.add(new AbstractAction("[" + other + "," + vertex + "]") {
/*     */                 public void actionPerformed(ActionEvent e) {
/*  66 */                   graph.addEdge(EditingPopupGraphMousePlugin.this.edgeFactory.create(), other, vertex, EdgeType.DIRECTED);
/*     */                   
/*  68 */                   vv.repaint();
/*     */                 }
/*     */               });
/*     */             }
/*     */           }
/*  73 */           if (!(graph instanceof DirectedGraph)) {
/*  74 */             undirectedMenu = new JMenu("Create Undirected Edge");
/*  75 */             this.popup.add(undirectedMenu);
/*  76 */             for (final V other : picked) {
/*  77 */               undirectedMenu.add(new AbstractAction("[" + other + "," + vertex + "]") {
/*     */                 public void actionPerformed(ActionEvent e) {
/*  79 */                   graph.addEdge(EditingPopupGraphMousePlugin.this.edgeFactory.create(), other, vertex);
/*     */                   
/*  81 */                   vv.repaint();
/*     */                 }
/*     */               });
/*     */             }
/*     */           }
/*     */         }
/*  87 */         this.popup.add(new AbstractAction("Delete Vertex") {
/*     */           public void actionPerformed(ActionEvent e) {
/*  89 */             pickedVertexState.pick(vertex, false);
/*  90 */             graph.removeVertex(vertex);
/*  91 */             vv.repaint();
/*     */           }
/*     */         }); } else if (edge != null) {
/*  94 */         this.popup.add(new AbstractAction("Delete Edge") {
/*     */           public void actionPerformed(ActionEvent e) {
/*  96 */             pickedEdgeState.pick(edge, false);
/*  97 */             graph.removeEdge(edge);
/*  98 */             vv.repaint();
/*     */           }
/*     */         });
/* 101 */       } else { this.popup.add(new AbstractAction("Create Vertex") {
/*     */           public void actionPerformed(ActionEvent e) {
/* 103 */             V newVertex = EditingPopupGraphMousePlugin.this.vertexFactory.create();
/* 104 */             graph.addVertex(newVertex);
/* 105 */             layout.setLocation(newVertex, vv.getRenderContext().getMultiLayerTransformer().inverseTransform(p));
/* 106 */             vv.repaint();
/*     */           }
/*     */         });
/*     */       }
/* 110 */       if (this.popup.getComponentCount() > 0) {
/* 111 */         this.popup.show(vv, e.getX(), e.getY());
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/control/EditingPopupGraphMousePlugin.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */