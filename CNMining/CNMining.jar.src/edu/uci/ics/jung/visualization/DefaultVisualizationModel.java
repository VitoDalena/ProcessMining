/*     */ package edu.uci.ics.jung.visualization;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.layout.Layout;
/*     */ import edu.uci.ics.jung.algorithms.layout.util.Relaxer;
/*     */ import edu.uci.ics.jung.algorithms.layout.util.VisRunner;
/*     */ import edu.uci.ics.jung.algorithms.util.IterativeContext;
/*     */ import edu.uci.ics.jung.visualization.layout.ObservableCachingLayout;
/*     */ import edu.uci.ics.jung.visualization.util.ChangeEventSupport;
/*     */ import edu.uci.ics.jung.visualization.util.DefaultChangeEventSupport;
/*     */ import java.awt.Dimension;
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
/*     */ public class DefaultVisualizationModel<V, E>
/*     */   implements VisualizationModel<V, E>, ChangeEventSupport
/*     */ {
/*  35 */   ChangeEventSupport changeSupport = new DefaultChangeEventSupport(this);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Relaxer relaxer;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Layout<V, E> layout;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected ChangeListener changeListener;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DefaultVisualizationModel(Layout<V, E> layout)
/*     */   {
/*  58 */     this(layout, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DefaultVisualizationModel(Layout<V, E> layout, Dimension d)
/*     */   {
/*  67 */     if (this.changeListener == null) {
/*  68 */       this.changeListener = new ChangeListener() {
/*     */         public void stateChanged(ChangeEvent e) {
/*  70 */           DefaultVisualizationModel.this.fireStateChanged();
/*     */         }
/*     */       };
/*     */     }
/*  74 */     setGraphLayout(layout, d);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setGraphLayout(Layout<V, E> layout, Dimension viewSize)
/*     */   {
/*  84 */     if ((this.layout != null) && ((this.layout instanceof ChangeEventSupport))) {
/*  85 */       ((ChangeEventSupport)this.layout).removeChangeListener(this.changeListener);
/*     */     }
/*     */     
/*  88 */     if ((layout instanceof ChangeEventSupport)) {
/*  89 */       this.layout = layout;
/*     */     } else {
/*  91 */       this.layout = new ObservableCachingLayout(layout);
/*     */     }
/*     */     
/*  94 */     ((ChangeEventSupport)this.layout).addChangeListener(this.changeListener);
/*     */     
/*  96 */     if (viewSize == null) {
/*  97 */       viewSize = new Dimension(600, 600);
/*     */     }
/*  99 */     Dimension layoutSize = layout.getSize();
/*     */     
/*     */ 
/* 102 */     if (layoutSize == null) {
/* 103 */       layout.setSize(viewSize);
/*     */     }
/* 105 */     if (this.relaxer != null) {
/* 106 */       this.relaxer.stop();
/* 107 */       this.relaxer = null;
/*     */     }
/* 109 */     if ((layout instanceof IterativeContext)) {
/* 110 */       layout.initialize();
/* 111 */       if (this.relaxer == null) {
/* 112 */         this.relaxer = new VisRunner((IterativeContext)this.layout);
/* 113 */         this.relaxer.prerelax();
/* 114 */         this.relaxer.relax();
/*     */       }
/*     */     }
/* 117 */     fireStateChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setGraphLayout(Layout<V, E> layout)
/*     */   {
/* 125 */     setGraphLayout(layout, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Layout<V, E> getGraphLayout()
/*     */   {
/* 132 */     return this.layout;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Relaxer getRelaxer()
/*     */   {
/* 139 */     return this.relaxer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setRelaxer(VisRunner relaxer)
/*     */   {
/* 146 */     this.relaxer = relaxer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addChangeListener(ChangeListener l)
/*     */   {
/* 154 */     this.changeSupport.addChangeListener(l);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeChangeListener(ChangeListener l)
/*     */   {
/* 162 */     this.changeSupport.removeChangeListener(l);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ChangeListener[] getChangeListeners()
/*     */   {
/* 173 */     return this.changeSupport.getChangeListeners();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void fireStateChanged()
/*     */   {
/* 185 */     this.changeSupport.fireStateChanged();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/DefaultVisualizationModel.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */