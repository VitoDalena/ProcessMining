/*     */ package org.jfree.chart.title;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.LegendItem;
/*     */ import org.jfree.chart.LegendItemCollection;
/*     */ import org.jfree.chart.LegendItemSource;
/*     */ import org.jfree.chart.block.Arrangement;
/*     */ import org.jfree.chart.block.Block;
/*     */ import org.jfree.chart.block.BlockContainer;
/*     */ import org.jfree.chart.block.BlockFrame;
/*     */ import org.jfree.chart.block.BlockResult;
/*     */ import org.jfree.chart.block.BorderArrangement;
/*     */ import org.jfree.chart.block.CenterArrangement;
/*     */ import org.jfree.chart.block.ColumnArrangement;
/*     */ import org.jfree.chart.block.EntityBlockParams;
/*     */ import org.jfree.chart.block.FlowArrangement;
/*     */ import org.jfree.chart.block.LabelBlock;
/*     */ import org.jfree.chart.block.RectangleConstraint;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.entity.StandardEntityCollection;
/*     */ import org.jfree.chart.entity.TitleEntity;
/*     */ import org.jfree.chart.event.TitleChangeEvent;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.RectangleAnchor;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.ui.RectangleInsets;
/*     */ import org.jfree.ui.Size2D;
/*     */ import org.jfree.util.PaintUtilities;
/*     */ import org.jfree.util.PublicCloneable;
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
/*     */ public class LegendTitle
/*     */   extends Title
/*     */   implements Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 2644010518533854633L;
/* 114 */   public static final Font DEFAULT_ITEM_FONT = new Font("SansSerif", 0, 12);
/*     */   
/*     */ 
/*     */ 
/* 118 */   public static final Paint DEFAULT_ITEM_PAINT = Color.black;
/*     */   
/*     */ 
/*     */ 
/*     */   private LegendItemSource[] sources;
/*     */   
/*     */ 
/*     */ 
/*     */   private transient Paint backgroundPaint;
/*     */   
/*     */ 
/*     */ 
/*     */   private RectangleEdge legendItemGraphicEdge;
/*     */   
/*     */ 
/*     */ 
/*     */   private RectangleAnchor legendItemGraphicAnchor;
/*     */   
/*     */ 
/*     */ 
/*     */   private RectangleAnchor legendItemGraphicLocation;
/*     */   
/*     */ 
/*     */ 
/*     */   private RectangleInsets legendItemGraphicPadding;
/*     */   
/*     */ 
/*     */ 
/*     */   private Font itemFont;
/*     */   
/*     */ 
/*     */ 
/*     */   private transient Paint itemPaint;
/*     */   
/*     */ 
/*     */ 
/*     */   private RectangleInsets itemLabelPadding;
/*     */   
/*     */ 
/*     */ 
/*     */   private BlockContainer items;
/*     */   
/*     */ 
/*     */ 
/*     */   private Arrangement hLayout;
/*     */   
/*     */ 
/*     */ 
/*     */   private Arrangement vLayout;
/*     */   
/*     */ 
/*     */ 
/*     */   private BlockContainer wrapper;
/*     */   
/*     */ 
/*     */ 
/*     */   public LegendTitle(LegendItemSource source)
/*     */   {
/* 176 */     this(source, new FlowArrangement(), new ColumnArrangement());
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
/*     */   public LegendTitle(LegendItemSource source, Arrangement hLayout, Arrangement vLayout)
/*     */   {
/* 190 */     this.sources = new LegendItemSource[] { source };
/* 191 */     this.items = new BlockContainer(hLayout);
/* 192 */     this.hLayout = hLayout;
/* 193 */     this.vLayout = vLayout;
/* 194 */     this.backgroundPaint = null;
/* 195 */     this.legendItemGraphicEdge = RectangleEdge.LEFT;
/* 196 */     this.legendItemGraphicAnchor = RectangleAnchor.CENTER;
/* 197 */     this.legendItemGraphicLocation = RectangleAnchor.CENTER;
/* 198 */     this.legendItemGraphicPadding = new RectangleInsets(2.0D, 2.0D, 2.0D, 2.0D);
/* 199 */     this.itemFont = DEFAULT_ITEM_FONT;
/* 200 */     this.itemPaint = DEFAULT_ITEM_PAINT;
/* 201 */     this.itemLabelPadding = new RectangleInsets(2.0D, 2.0D, 2.0D, 2.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public LegendItemSource[] getSources()
/*     */   {
/* 210 */     return this.sources;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSources(LegendItemSource[] sources)
/*     */   {
/* 220 */     if (sources == null) {
/* 221 */       throw new IllegalArgumentException("Null 'sources' argument.");
/*     */     }
/* 223 */     this.sources = sources;
/* 224 */     notifyListeners(new TitleChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getBackgroundPaint()
/*     */   {
/* 233 */     return this.backgroundPaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBackgroundPaint(Paint paint)
/*     */   {
/* 243 */     this.backgroundPaint = paint;
/* 244 */     notifyListeners(new TitleChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RectangleEdge getLegendItemGraphicEdge()
/*     */   {
/* 253 */     return this.legendItemGraphicEdge;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLegendItemGraphicEdge(RectangleEdge edge)
/*     */   {
/* 262 */     if (edge == null) {
/* 263 */       throw new IllegalArgumentException("Null 'edge' argument.");
/*     */     }
/* 265 */     this.legendItemGraphicEdge = edge;
/* 266 */     notifyListeners(new TitleChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RectangleAnchor getLegendItemGraphicAnchor()
/*     */   {
/* 275 */     return this.legendItemGraphicAnchor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLegendItemGraphicAnchor(RectangleAnchor anchor)
/*     */   {
/* 284 */     if (anchor == null) {
/* 285 */       throw new IllegalArgumentException("Null 'anchor' point.");
/*     */     }
/* 287 */     this.legendItemGraphicAnchor = anchor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RectangleAnchor getLegendItemGraphicLocation()
/*     */   {
/* 296 */     return this.legendItemGraphicLocation;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLegendItemGraphicLocation(RectangleAnchor anchor)
/*     */   {
/* 305 */     this.legendItemGraphicLocation = anchor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RectangleInsets getLegendItemGraphicPadding()
/*     */   {
/* 314 */     return this.legendItemGraphicPadding;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLegendItemGraphicPadding(RectangleInsets padding)
/*     */   {
/* 324 */     if (padding == null) {
/* 325 */       throw new IllegalArgumentException("Null 'padding' argument.");
/*     */     }
/* 327 */     this.legendItemGraphicPadding = padding;
/* 328 */     notifyListeners(new TitleChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Font getItemFont()
/*     */   {
/* 337 */     return this.itemFont;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setItemFont(Font font)
/*     */   {
/* 347 */     if (font == null) {
/* 348 */       throw new IllegalArgumentException("Null 'font' argument.");
/*     */     }
/* 350 */     this.itemFont = font;
/* 351 */     notifyListeners(new TitleChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getItemPaint()
/*     */   {
/* 360 */     return this.itemPaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setItemPaint(Paint paint)
/*     */   {
/* 369 */     if (paint == null) {
/* 370 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 372 */     this.itemPaint = paint;
/* 373 */     notifyListeners(new TitleChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RectangleInsets getItemLabelPadding()
/*     */   {
/* 382 */     return this.itemLabelPadding;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setItemLabelPadding(RectangleInsets padding)
/*     */   {
/* 391 */     if (padding == null) {
/* 392 */       throw new IllegalArgumentException("Null 'padding' argument.");
/*     */     }
/* 394 */     this.itemLabelPadding = padding;
/* 395 */     notifyListeners(new TitleChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void fetchLegendItems()
/*     */   {
/* 402 */     this.items.clear();
/* 403 */     RectangleEdge p = getPosition();
/* 404 */     if (RectangleEdge.isTopOrBottom(p)) {
/* 405 */       this.items.setArrangement(this.hLayout);
/*     */     }
/*     */     else {
/* 408 */       this.items.setArrangement(this.vLayout);
/*     */     }
/* 410 */     for (int s = 0; s < this.sources.length; s++) {
/* 411 */       LegendItemCollection legendItems = this.sources[s].getLegendItems();
/* 412 */       if (legendItems != null) {
/* 413 */         for (int i = 0; i < legendItems.getItemCount(); i++) {
/* 414 */           LegendItem item = legendItems.get(i);
/* 415 */           Block block = createLegendItemBlock(item);
/* 416 */           this.items.add(block);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Block createLegendItemBlock(LegendItem item)
/*     */   {
/* 430 */     BlockContainer result = null;
/* 431 */     LegendGraphic lg = new LegendGraphic(item.getShape(), item.getFillPaint());
/*     */     
/* 433 */     lg.setFillPaintTransformer(item.getFillPaintTransformer());
/* 434 */     lg.setShapeFilled(item.isShapeFilled());
/* 435 */     lg.setLine(item.getLine());
/* 436 */     lg.setLineStroke(item.getLineStroke());
/* 437 */     lg.setLinePaint(item.getLinePaint());
/* 438 */     lg.setLineVisible(item.isLineVisible());
/* 439 */     lg.setShapeVisible(item.isShapeVisible());
/* 440 */     lg.setShapeOutlineVisible(item.isShapeOutlineVisible());
/* 441 */     lg.setOutlinePaint(item.getOutlinePaint());
/* 442 */     lg.setOutlineStroke(item.getOutlineStroke());
/* 443 */     lg.setPadding(this.legendItemGraphicPadding);
/*     */     
/* 445 */     LegendItemBlockContainer legendItem = new LegendItemBlockContainer(new BorderArrangement(), item.getDataset(), item.getSeriesKey());
/*     */     
/*     */ 
/* 448 */     lg.setShapeAnchor(getLegendItemGraphicAnchor());
/* 449 */     lg.setShapeLocation(getLegendItemGraphicLocation());
/* 450 */     legendItem.add(lg, this.legendItemGraphicEdge);
/* 451 */     Font textFont = item.getLabelFont();
/* 452 */     if (textFont == null) {
/* 453 */       textFont = this.itemFont;
/*     */     }
/* 455 */     Paint textPaint = item.getLabelPaint();
/* 456 */     if (textPaint == null) {
/* 457 */       textPaint = this.itemPaint;
/*     */     }
/* 459 */     LabelBlock labelBlock = new LabelBlock(item.getLabel(), textFont, textPaint);
/*     */     
/* 461 */     labelBlock.setPadding(this.itemLabelPadding);
/* 462 */     legendItem.add(labelBlock);
/* 463 */     legendItem.setToolTipText(item.getToolTipText());
/* 464 */     legendItem.setURLText(item.getURLText());
/*     */     
/* 466 */     result = new BlockContainer(new CenterArrangement());
/* 467 */     result.add(legendItem);
/*     */     
/* 469 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BlockContainer getItemContainer()
/*     */   {
/* 478 */     return this.items;
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
/*     */   public Size2D arrange(Graphics2D g2, RectangleConstraint constraint)
/*     */   {
/* 491 */     Size2D result = new Size2D();
/* 492 */     fetchLegendItems();
/* 493 */     if (this.items.isEmpty()) {
/* 494 */       return result;
/*     */     }
/* 496 */     BlockContainer container = this.wrapper;
/* 497 */     if (container == null) {
/* 498 */       container = this.items;
/*     */     }
/* 500 */     RectangleConstraint c = toContentConstraint(constraint);
/* 501 */     Size2D size = container.arrange(g2, c);
/* 502 */     result.height = calculateTotalHeight(size.height);
/* 503 */     result.width = calculateTotalWidth(size.width);
/* 504 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void draw(Graphics2D g2, Rectangle2D area)
/*     */   {
/* 515 */     draw(g2, area, null);
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
/*     */   public Object draw(Graphics2D g2, Rectangle2D area, Object params)
/*     */   {
/* 529 */     Rectangle2D target = (Rectangle2D)area.clone();
/* 530 */     Rectangle2D hotspot = (Rectangle2D)area.clone();
/* 531 */     StandardEntityCollection sec = null;
/* 532 */     if (((params instanceof EntityBlockParams)) && (((EntityBlockParams)params).getGenerateEntities()))
/*     */     {
/* 534 */       sec = new StandardEntityCollection();
/* 535 */       sec.add(new TitleEntity(hotspot, this));
/*     */     }
/* 537 */     target = trimMargin(target);
/* 538 */     if (this.backgroundPaint != null) {
/* 539 */       g2.setPaint(this.backgroundPaint);
/* 540 */       g2.fill(target);
/*     */     }
/* 542 */     BlockFrame border = getFrame();
/* 543 */     border.draw(g2, target);
/* 544 */     border.getInsets().trim(target);
/* 545 */     BlockContainer container = this.wrapper;
/* 546 */     if (container == null) {
/* 547 */       container = this.items;
/*     */     }
/* 549 */     target = trimPadding(target);
/* 550 */     Object val = container.draw(g2, target, params);
/* 551 */     if ((val instanceof BlockResult)) {
/* 552 */       EntityCollection ec = ((BlockResult)val).getEntityCollection();
/* 553 */       if ((ec != null) && (sec != null)) {
/* 554 */         sec.addAll(ec);
/* 555 */         ((BlockResult)val).setEntityCollection(sec);
/*     */       }
/*     */     }
/* 558 */     return val;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BlockContainer getWrapper()
/*     */   {
/* 569 */     return this.wrapper;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setWrapper(BlockContainer wrapper)
/*     */   {
/* 578 */     this.wrapper = wrapper;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 589 */     if (obj == this) {
/* 590 */       return true;
/*     */     }
/* 592 */     if (!(obj instanceof LegendTitle)) {
/* 593 */       return false;
/*     */     }
/* 595 */     if (!super.equals(obj)) {
/* 596 */       return false;
/*     */     }
/* 598 */     LegendTitle that = (LegendTitle)obj;
/* 599 */     if (!PaintUtilities.equal(this.backgroundPaint, that.backgroundPaint)) {
/* 600 */       return false;
/*     */     }
/* 602 */     if (this.legendItemGraphicEdge != that.legendItemGraphicEdge) {
/* 603 */       return false;
/*     */     }
/* 605 */     if (this.legendItemGraphicAnchor != that.legendItemGraphicAnchor) {
/* 606 */       return false;
/*     */     }
/* 608 */     if (this.legendItemGraphicLocation != that.legendItemGraphicLocation) {
/* 609 */       return false;
/*     */     }
/* 611 */     if (!this.itemFont.equals(that.itemFont)) {
/* 612 */       return false;
/*     */     }
/* 614 */     if (!this.itemPaint.equals(that.itemPaint)) {
/* 615 */       return false;
/*     */     }
/* 617 */     if (!this.hLayout.equals(that.hLayout)) {
/* 618 */       return false;
/*     */     }
/* 620 */     if (!this.vLayout.equals(that.vLayout)) {
/* 621 */       return false;
/*     */     }
/* 623 */     return true;
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
/* 634 */     stream.defaultWriteObject();
/* 635 */     SerialUtilities.writePaint(this.backgroundPaint, stream);
/* 636 */     SerialUtilities.writePaint(this.itemPaint, stream);
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
/* 649 */     stream.defaultReadObject();
/* 650 */     this.backgroundPaint = SerialUtilities.readPaint(stream);
/* 651 */     this.itemPaint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/title/LegendTitle.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */