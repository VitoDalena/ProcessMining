/*     */ package org.deckfour.uitopia.ui.views;
/*     */ 
/*     */ import com.fluxicon.slickerbox.factory.SlickerDecorator;
/*     */ import com.fluxicon.slickerbox.factory.SlickerFactory;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.ComponentOrientation;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.ComponentListener;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.Box;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollBar;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JSlider;
/*     */ import javax.swing.JViewport;
/*     */ import javax.swing.event.ChangeEvent;
/*     */ import javax.swing.event.ChangeListener;
/*     */ import org.deckfour.uitopia.api.hub.ViewManager;
/*     */ import org.deckfour.uitopia.api.model.View;
/*     */ import org.deckfour.uitopia.ui.components.TiledPanel;
/*     */ import org.deckfour.uitopia.ui.components.ViewHeaderBar;
/*     */ import org.deckfour.uitopia.ui.util.ImageLoader;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ViewGrid
/*     */   extends JPanel
/*     */ {
/*     */   private static final long serialVersionUID = -6850127447334279630L;
/*  70 */   private static final Color COLOR_SCROLL_BG = new Color(0, 0, 0, 0);
/*  71 */   private static final Color COLOR_SCROLL_ACTIVE = new Color(200, 200, 200, 220);
/*     */   
/*  73 */   private static final Color COLOR_SCROLL_PASSIVE = new Color(200, 200, 200, 120);
/*     */   
/*     */   private ViewManager manager;
/*     */   
/*     */   private ViewsView parent;
/*     */   
/*     */   private List<ViewItem> items;
/*     */   private TiledPanel contents;
/*     */   private JPanel grid;
/*     */   private JSlider sizeSlider;
/*     */   
/*     */   public ViewGrid(ViewManager manager, ViewsView parent)
/*     */   {
/*  86 */     this.manager = manager;
/*  87 */     this.parent = parent;
/*  88 */     setupUI();
/*  89 */     updateItems();
/*     */   }
/*     */   
/*     */   private void setupUI() {
/*  93 */     setLayout(new BorderLayout());
/*  94 */     setOpaque(true);
/*  95 */     setBorder(BorderFactory.createEmptyBorder());
/*  96 */     add(new ViewHeaderBar("Views"), "North");
/*  97 */     this.contents = new TiledPanel(ImageLoader.load("tile_corkboard.jpg"));
/*  98 */     this.contents.setLayout(new BorderLayout());
/*  99 */     add(this.contents, "Center");
/* 100 */     this.grid = new JPanel();
/* 101 */     this.contents.addComponentListener(new ComponentListener()
/*     */     {
/*     */       public void componentHidden(ComponentEvent e) {}
/*     */       
/*     */       public void componentMoved(ComponentEvent e) {}
/*     */       
/*     */       public void componentResized(ComponentEvent e)
/*     */       {
/* 109 */         ViewGrid.this.grid.doLayout();
/* 110 */         ViewGrid.this.grid.revalidate();
/*     */       }
/*     */       
/*     */ 
/*     */       public void componentShown(ComponentEvent e) {}
/* 115 */     });
/* 116 */     this.grid.setOpaque(false);
/* 117 */     this.grid.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
/* 118 */     this.grid.setLayout(new GridLayoutManager(5, 5));
/* 119 */     JScrollPane scrollPane = new JScrollPane(this.grid);
/* 120 */     scrollPane.setOpaque(false);
/* 121 */     scrollPane.getViewport().setOpaque(false);
/* 122 */     scrollPane.setBorder(BorderFactory.createEmptyBorder());
/* 123 */     scrollPane.setViewportBorder(BorderFactory.createEmptyBorder());
/* 124 */     scrollPane.setHorizontalScrollBarPolicy(31);
/*     */     
/* 126 */     scrollPane.setVerticalScrollBarPolicy(20);
/*     */     
/* 128 */     SlickerDecorator.instance().decorate(scrollPane.getVerticalScrollBar(), COLOR_SCROLL_BG, COLOR_SCROLL_ACTIVE, COLOR_SCROLL_PASSIVE);
/*     */     
/* 130 */     scrollPane.getVerticalScrollBar().setOpaque(false);
/* 131 */     this.contents.add(scrollPane, "Center");
/* 132 */     JPanel bottom = new JPanel();
/* 133 */     bottom.setBackground(new Color(120, 120, 120));
/* 134 */     bottom.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
/* 135 */     bottom.setLayout(new BoxLayout(bottom, 0));
/* 136 */     this.sizeSlider = SlickerFactory.instance().createSlider(0);
/* 137 */     this.sizeSlider.setMinimum(80);
/* 138 */     this.sizeSlider.setMaximum(500);
/* 139 */     this.sizeSlider.setValue(250);
/* 140 */     this.sizeSlider.addChangeListener(new ChangeListener() {
/*     */       public void stateChanged(ChangeEvent e) {
/* 142 */         ViewGrid.this.handleResize();
/*     */       }
/* 144 */     });
/* 145 */     Dimension dim = new Dimension(200, 20);
/* 146 */     this.sizeSlider.setMinimumSize(dim);
/* 147 */     this.sizeSlider.setMaximumSize(dim);
/* 148 */     this.sizeSlider.setPreferredSize(dim);
/* 149 */     this.sizeSlider.setToolTipText("Zoom in/out");
/* 150 */     bottom.add(Box.createHorizontalGlue());
/* 151 */     bottom.add(this.sizeSlider);
/* 152 */     add(bottom, "South");
/* 153 */     updateItems();
/*     */   }
/*     */   
/*     */   private void handleResize() {
/* 157 */     int width = this.sizeSlider.getValue();
/* 158 */     int height = width;
/* 159 */     for (ViewItem item : this.items) {
/* 160 */       item.updateSize(width, height);
/*     */     }
/* 162 */     this.grid.revalidate();
/* 163 */     this.grid.repaint();
/*     */   }
/*     */   
/*     */   public void updateItems() {
/* 167 */     this.grid.removeAll();
/* 168 */     List<View> views = this.manager.getViews();
/* 169 */     this.items = new ArrayList(views.size());
/* 170 */     for (View view : views) {
/* 171 */       ViewItem item = new ViewItem(this.parent, view);
/* 172 */       this.items.add(item);
/* 173 */       this.grid.add(item);
/*     */     }
/* 175 */     handleResize();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/views/ViewGrid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */