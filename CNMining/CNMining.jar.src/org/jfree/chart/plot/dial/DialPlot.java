/*     */ package org.jfree.chart.plot.dial;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jfree.chart.plot.Plot;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.PlotState;
/*     */ import org.jfree.data.general.DatasetChangeEvent;
/*     */ import org.jfree.data.general.ValueDataset;
/*     */ import org.jfree.util.ObjectList;
/*     */ import org.jfree.util.ObjectUtilities;
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
/*     */ public class DialPlot
/*     */   extends Plot
/*     */   implements DialLayerChangeListener
/*     */ {
/*     */   private DialLayer background;
/*     */   private DialLayer cap;
/*     */   private DialFrame dialFrame;
/*     */   private ObjectList datasets;
/*     */   private ObjectList scales;
/*     */   private ObjectList datasetToScaleMap;
/*     */   private List layers;
/*     */   private List pointers;
/*     */   private double viewX;
/*     */   private double viewY;
/*     */   private double viewW;
/*     */   private double viewH;
/*     */   
/*     */   public DialPlot()
/*     */   {
/* 142 */     this(null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DialPlot(ValueDataset dataset)
/*     */   {
/* 151 */     this.background = null;
/* 152 */     this.cap = null;
/* 153 */     this.dialFrame = new ArcDialFrame();
/* 154 */     this.datasets = new ObjectList();
/* 155 */     if (dataset != null) {
/* 156 */       setDataset(dataset);
/*     */     }
/* 158 */     this.scales = new ObjectList();
/* 159 */     this.datasetToScaleMap = new ObjectList();
/* 160 */     this.layers = new ArrayList();
/* 161 */     this.pointers = new ArrayList();
/* 162 */     this.viewX = 0.0D;
/* 163 */     this.viewY = 0.0D;
/* 164 */     this.viewW = 1.0D;
/* 165 */     this.viewH = 1.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DialLayer getBackground()
/*     */   {
/* 176 */     return this.background;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBackground(DialLayer background)
/*     */   {
/* 188 */     if (this.background != null) {
/* 189 */       this.background.removeChangeListener(this);
/*     */     }
/* 191 */     this.background = background;
/* 192 */     if (background != null) {
/* 193 */       background.addChangeListener(this);
/*     */     }
/* 195 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DialLayer getCap()
/*     */   {
/* 206 */     return this.cap;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCap(DialLayer cap)
/*     */   {
/* 218 */     if (this.cap != null) {
/* 219 */       this.cap.removeChangeListener(this);
/*     */     }
/* 221 */     this.cap = cap;
/* 222 */     if (cap != null) {
/* 223 */       cap.addChangeListener(this);
/*     */     }
/* 225 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DialFrame getDialFrame()
/*     */   {
/* 236 */     return this.dialFrame;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDialFrame(DialFrame frame)
/*     */   {
/* 248 */     if (frame == null) {
/* 249 */       throw new IllegalArgumentException("Null 'frame' argument.");
/*     */     }
/* 251 */     this.dialFrame.removeChangeListener(this);
/* 252 */     this.dialFrame = frame;
/* 253 */     frame.addChangeListener(this);
/* 254 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getViewX()
/*     */   {
/* 266 */     return this.viewX;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getViewY()
/*     */   {
/* 278 */     return this.viewY;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getViewWidth()
/*     */   {
/* 290 */     return this.viewW;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getViewHeight()
/*     */   {
/* 302 */     return this.viewH;
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
/*     */   public void setView(double x, double y, double w, double h)
/*     */   {
/* 320 */     this.viewX = x;
/* 321 */     this.viewY = y;
/* 322 */     this.viewW = w;
/* 323 */     this.viewH = h;
/* 324 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addLayer(DialLayer layer)
/*     */   {
/* 334 */     if (layer == null) {
/* 335 */       throw new IllegalArgumentException("Null 'layer' argument.");
/*     */     }
/* 337 */     this.layers.add(layer);
/* 338 */     layer.addChangeListener(this);
/* 339 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getLayerIndex(DialLayer layer)
/*     */   {
/* 350 */     if (layer == null) {
/* 351 */       throw new IllegalArgumentException("Null 'layer' argument.");
/*     */     }
/* 353 */     return this.layers.indexOf(layer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeLayer(int index)
/*     */   {
/* 363 */     DialLayer layer = (DialLayer)this.layers.get(index);
/* 364 */     if (layer != null) {
/* 365 */       layer.removeChangeListener(this);
/*     */     }
/* 367 */     this.layers.remove(index);
/* 368 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeLayer(DialLayer layer)
/*     */   {
/* 379 */     removeLayer(getLayerIndex(layer));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addPointer(DialPointer pointer)
/*     */   {
/* 389 */     if (pointer == null) {
/* 390 */       throw new IllegalArgumentException("Null 'pointer' argument.");
/*     */     }
/* 392 */     this.pointers.add(pointer);
/* 393 */     pointer.addChangeListener(this);
/* 394 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getPointerIndex(DialPointer pointer)
/*     */   {
/* 405 */     if (pointer == null) {
/* 406 */       throw new IllegalArgumentException("Null 'pointer' argument.");
/*     */     }
/* 408 */     return this.pointers.indexOf(pointer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removePointer(int index)
/*     */   {
/* 418 */     DialPointer pointer = (DialPointer)this.pointers.get(index);
/* 419 */     if (pointer != null) {
/* 420 */       pointer.removeChangeListener(this);
/*     */     }
/* 422 */     this.pointers.remove(index);
/* 423 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removePointer(DialPointer pointer)
/*     */   {
/* 434 */     removeLayer(getPointerIndex(pointer));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DialPointer getPointerForDataset(int datasetIndex)
/*     */   {
/* 446 */     DialPointer result = null;
/* 447 */     Iterator iterator = this.pointers.iterator();
/* 448 */     while (iterator.hasNext()) {
/* 449 */       DialPointer p = (DialPointer)iterator.next();
/* 450 */       if (p.getDatasetIndex() == datasetIndex) {
/* 451 */         return p;
/*     */       }
/*     */     }
/* 454 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ValueDataset getDataset()
/*     */   {
/* 463 */     return getDataset(0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ValueDataset getDataset(int index)
/*     */   {
/* 474 */     ValueDataset result = null;
/* 475 */     if (this.datasets.size() > index) {
/* 476 */       result = (ValueDataset)this.datasets.get(index);
/*     */     }
/* 478 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataset(ValueDataset dataset)
/*     */   {
/* 489 */     setDataset(0, dataset);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataset(int index, ValueDataset dataset)
/*     */   {
/* 500 */     ValueDataset existing = (ValueDataset)this.datasets.get(index);
/* 501 */     if (existing != null) {
/* 502 */       existing.removeChangeListener(this);
/*     */     }
/* 504 */     this.datasets.set(index, dataset);
/* 505 */     if (dataset != null) {
/* 506 */       dataset.addChangeListener(this);
/*     */     }
/*     */     
/*     */ 
/* 510 */     DatasetChangeEvent event = new DatasetChangeEvent(this, dataset);
/* 511 */     datasetChanged(event);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getDatasetCount()
/*     */   {
/* 521 */     return this.datasets.size();
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
/*     */   public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor, PlotState parentState, PlotRenderingInfo info)
/*     */   {
/* 539 */     Shape origClip = g2.getClip();
/* 540 */     g2.setClip(area);
/*     */     
/*     */ 
/* 543 */     Rectangle2D frame = viewToFrame(area);
/*     */     
/*     */ 
/* 546 */     if ((this.background != null) && (this.background.isVisible())) {
/* 547 */       if (this.background.isClippedToWindow()) {
/* 548 */         Shape savedClip = g2.getClip();
/* 549 */         g2.clip(this.dialFrame.getWindow(frame));
/* 550 */         this.background.draw(g2, this, frame, area);
/* 551 */         g2.setClip(savedClip);
/*     */       }
/*     */       else {
/* 554 */         this.background.draw(g2, this, frame, area);
/*     */       }
/*     */     }
/*     */     
/* 558 */     Iterator iterator = this.layers.iterator();
/* 559 */     while (iterator.hasNext()) {
/* 560 */       DialLayer current = (DialLayer)iterator.next();
/* 561 */       if (current.isVisible()) {
/* 562 */         if (current.isClippedToWindow()) {
/* 563 */           Shape savedClip = g2.getClip();
/* 564 */           g2.clip(this.dialFrame.getWindow(frame));
/* 565 */           current.draw(g2, this, frame, area);
/* 566 */           g2.setClip(savedClip);
/*     */         }
/*     */         else {
/* 569 */           current.draw(g2, this, frame, area);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 575 */     iterator = this.pointers.iterator();
/* 576 */     while (iterator.hasNext()) {
/* 577 */       DialPointer current = (DialPointer)iterator.next();
/* 578 */       if (current.isVisible()) {
/* 579 */         if (current.isClippedToWindow()) {
/* 580 */           Shape savedClip = g2.getClip();
/* 581 */           g2.clip(this.dialFrame.getWindow(frame));
/* 582 */           current.draw(g2, this, frame, area);
/* 583 */           g2.setClip(savedClip);
/*     */         }
/*     */         else {
/* 586 */           current.draw(g2, this, frame, area);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 592 */     if ((this.cap != null) && (this.cap.isVisible())) {
/* 593 */       if (this.cap.isClippedToWindow()) {
/* 594 */         Shape savedClip = g2.getClip();
/* 595 */         g2.clip(this.dialFrame.getWindow(frame));
/* 596 */         this.cap.draw(g2, this, frame, area);
/* 597 */         g2.setClip(savedClip);
/*     */       }
/*     */       else {
/* 600 */         this.cap.draw(g2, this, frame, area);
/*     */       }
/*     */     }
/*     */     
/* 604 */     if (this.dialFrame.isVisible()) {
/* 605 */       this.dialFrame.draw(g2, this, frame, area);
/*     */     }
/*     */     
/* 608 */     g2.setClip(origClip);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Rectangle2D viewToFrame(Rectangle2D view)
/*     */   {
/* 620 */     double width = view.getWidth() / this.viewW;
/* 621 */     double height = view.getHeight() / this.viewH;
/* 622 */     double x = view.getX() - width * this.viewX;
/* 623 */     double y = view.getY() - height * this.viewY;
/* 624 */     return new Rectangle2D.Double(x, y, width, height);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getValue(int datasetIndex)
/*     */   {
/* 635 */     double result = NaN.0D;
/* 636 */     ValueDataset dataset = getDataset(datasetIndex);
/* 637 */     if (dataset != null) {
/* 638 */       Number n = dataset.getValue();
/* 639 */       if (n != null) {
/* 640 */         result = n.doubleValue();
/*     */       }
/*     */     }
/* 643 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addScale(int index, DialScale scale)
/*     */   {
/* 654 */     if (scale == null) {
/* 655 */       throw new IllegalArgumentException("Null 'scale' argument.");
/*     */     }
/* 657 */     DialScale existing = (DialScale)this.scales.get(index);
/* 658 */     if (existing != null) {
/* 659 */       removeLayer(existing);
/*     */     }
/* 661 */     this.layers.add(scale);
/* 662 */     this.scales.set(index, scale);
/* 663 */     scale.addChangeListener(this);
/* 664 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DialScale getScale(int index)
/*     */   {
/* 675 */     DialScale result = null;
/* 676 */     if (this.scales.size() > index) {
/* 677 */       result = (DialScale)this.scales.get(index);
/*     */     }
/* 679 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mapDatasetToScale(int index, int scaleIndex)
/*     */   {
/* 689 */     this.datasetToScaleMap.set(index, new Integer(scaleIndex));
/* 690 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DialScale getScaleForDataset(int datasetIndex)
/*     */   {
/* 701 */     DialScale result = (DialScale)this.scales.get(0);
/* 702 */     Integer scaleIndex = (Integer)this.datasetToScaleMap.get(datasetIndex);
/* 703 */     if (scaleIndex != null) {
/* 704 */       result = getScale(scaleIndex.intValue());
/*     */     }
/* 706 */     return result;
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
/*     */   public static Rectangle2D rectangleByRadius(Rectangle2D rect, double radiusW, double radiusH)
/*     */   {
/* 720 */     if (rect == null) {
/* 721 */       throw new IllegalArgumentException("Null 'rect' argument.");
/*     */     }
/* 723 */     double x = rect.getCenterX();
/* 724 */     double y = rect.getCenterY();
/* 725 */     double w = rect.getWidth() * radiusW;
/* 726 */     double h = rect.getHeight() * radiusH;
/* 727 */     return new Rectangle2D.Double(x - w / 2.0D, y - h / 2.0D, w, h);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void dialLayerChanged(DialLayerChangeEvent event)
/*     */   {
/* 737 */     fireChangeEvent();
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
/*     */   public boolean equals(Object obj)
/*     */   {
/* 750 */     if (obj == this) {
/* 751 */       return true;
/*     */     }
/* 753 */     if (!(obj instanceof DialPlot)) {
/* 754 */       return false;
/*     */     }
/* 756 */     DialPlot that = (DialPlot)obj;
/* 757 */     if (!ObjectUtilities.equal(this.background, that.background)) {
/* 758 */       return false;
/*     */     }
/* 760 */     if (!ObjectUtilities.equal(this.cap, that.cap)) {
/* 761 */       return false;
/*     */     }
/* 763 */     if (!this.dialFrame.equals(that.dialFrame)) {
/* 764 */       return false;
/*     */     }
/* 766 */     if (this.viewX != that.viewX) {
/* 767 */       return false;
/*     */     }
/* 769 */     if (this.viewY != that.viewY) {
/* 770 */       return false;
/*     */     }
/* 772 */     if (this.viewW != that.viewW) {
/* 773 */       return false;
/*     */     }
/* 775 */     if (this.viewH != that.viewH) {
/* 776 */       return false;
/*     */     }
/* 778 */     if (!this.layers.equals(that.layers)) {
/* 779 */       return false;
/*     */     }
/* 781 */     if (!this.pointers.equals(that.pointers)) {
/* 782 */       return false;
/*     */     }
/* 784 */     return super.equals(obj);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 793 */     int result = 193;
/* 794 */     result = 37 * result + ObjectUtilities.hashCode(this.background);
/* 795 */     result = 37 * result + ObjectUtilities.hashCode(this.cap);
/* 796 */     result = 37 * result + this.dialFrame.hashCode();
/* 797 */     long temp = Double.doubleToLongBits(this.viewX);
/* 798 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/* 799 */     temp = Double.doubleToLongBits(this.viewY);
/* 800 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/* 801 */     temp = Double.doubleToLongBits(this.viewW);
/* 802 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/* 803 */     temp = Double.doubleToLongBits(this.viewH);
/* 804 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/* 805 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getPlotType()
/*     */   {
/* 814 */     return "DialPlot";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void writeObject(ObjectOutputStream stream)
/*     */     throws IOException
/*     */   {
/* 825 */     stream.defaultWriteObject();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void readObject(ObjectInputStream stream)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 838 */     stream.defaultReadObject();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/dial/DialPlot.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */