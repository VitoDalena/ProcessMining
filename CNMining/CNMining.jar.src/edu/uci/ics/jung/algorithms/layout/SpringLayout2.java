/*     */ package edu.uci.ics.jung.algorithms.layout;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Point2D.Double;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.Map;
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
/*     */ public class SpringLayout2<V, E>
/*     */   extends SpringLayout<V, E>
/*     */ {
/*     */   protected int currentIteration;
/*     */   protected int averageCounter;
/*  33 */   protected int loopCountMax = 4;
/*     */   
/*     */   protected boolean done;
/*  36 */   protected Point2D averageDelta = new Point2D.Double();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SpringLayout2(Graph<V, E> g)
/*     */   {
/*  45 */     super(g);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SpringLayout2(Graph<V, E> g, Transformer<E, Integer> length_function)
/*     */   {
/*  56 */     super(g, length_function);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void step()
/*     */   {
/*  64 */     super.step();
/*  65 */     this.currentIteration += 1;
/*  66 */     testAverageDeltas();
/*     */   }
/*     */   
/*     */   private void testAverageDeltas() {
/*  70 */     double dx = this.averageDelta.getX();
/*  71 */     double dy = this.averageDelta.getY();
/*  72 */     if ((Math.abs(dx) < 0.001D) && (Math.abs(dy) < 0.001D)) {
/*  73 */       this.done = true;
/*  74 */       System.err.println("done, dx=" + dx + ", dy=" + dy);
/*     */     }
/*  76 */     if (this.currentIteration > this.loopCountMax) {
/*  77 */       this.averageDelta.setLocation(0.0D, 0.0D);
/*  78 */       this.averageCounter = 0;
/*  79 */       this.currentIteration = 0;
/*     */     }
/*     */   }
/*     */   
/*     */   protected void moveNodes()
/*     */   {
/*  85 */     synchronized (getSize()) {
/*     */       try {
/*  87 */         for (V v : getGraph().getVertices()) {
/*  88 */           if (!isLocked(v)) {
/*  89 */             SpringLayout.SpringVertexData vd = (SpringLayout.SpringVertexData)this.springVertexData.get(v);
/*  90 */             if (vd != null) {
/*  91 */               Point2D xyd = transform(v);
/*     */               
/*  93 */               vd.dx += vd.repulsiondx + vd.edgedx;
/*  94 */               vd.dy += vd.repulsiondy + vd.edgedy;
/*     */               
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 100 */               this.averageDelta.setLocation((this.averageDelta.getX() * this.averageCounter + vd.dx) / (this.averageCounter + 1), (this.averageDelta.getY() * this.averageCounter + vd.dy) / (this.averageCounter + 1));
/*     */               
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 106 */               this.averageCounter += 1;
/*     */               
/*     */ 
/* 109 */               xyd.setLocation(xyd.getX() + Math.max(-5.0D, Math.min(5.0D, vd.dx)), xyd.getY() + Math.max(-5.0D, Math.min(5.0D, vd.dy)));
/*     */               
/*     */ 
/* 112 */               Dimension d = getSize();
/* 113 */               int width = d.width;
/* 114 */               int height = d.height;
/*     */               
/* 116 */               if (xyd.getX() < 0.0D) {
/* 117 */                 xyd.setLocation(0.0D, xyd.getY());
/* 118 */               } else if (xyd.getX() > width) {
/* 119 */                 xyd.setLocation(width, xyd.getY());
/*     */               }
/* 121 */               if (xyd.getY() < 0.0D) {
/* 122 */                 xyd.setLocation(xyd.getX(), 0.0D);
/* 123 */               } else if (xyd.getY() > height)
/* 124 */                 xyd.setLocation(xyd.getX(), height);
/*     */             }
/*     */           }
/*     */         }
/*     */       } catch (ConcurrentModificationException cme) {
/* 129 */         moveNodes();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean done()
/*     */   {
/* 136 */     return this.done;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/layout/SpringLayout2.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */