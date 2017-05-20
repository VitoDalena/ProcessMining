/*     */ package org.jfree.chart.title;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import javax.swing.event.EventListenerList;
/*     */ import org.jfree.chart.block.AbstractBlock;
/*     */ import org.jfree.chart.block.Block;
/*     */ import org.jfree.chart.event.TitleChangeEvent;
/*     */ import org.jfree.chart.event.TitleChangeListener;
/*     */ import org.jfree.ui.HorizontalAlignment;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.ui.RectangleInsets;
/*     */ import org.jfree.ui.VerticalAlignment;
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
/*     */ public abstract class Title
/*     */   extends AbstractBlock
/*     */   implements Block, Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -6675162505277817221L;
/*  96 */   public static final RectangleEdge DEFAULT_POSITION = RectangleEdge.TOP;
/*     */   
/*     */ 
/*     */ 
/* 100 */   public static final HorizontalAlignment DEFAULT_HORIZONTAL_ALIGNMENT = HorizontalAlignment.CENTER;
/*     */   
/*     */ 
/*     */ 
/* 104 */   public static final VerticalAlignment DEFAULT_VERTICAL_ALIGNMENT = VerticalAlignment.CENTER;
/*     */   
/*     */ 
/* 107 */   public static final RectangleInsets DEFAULT_PADDING = new RectangleInsets(1.0D, 1.0D, 1.0D, 1.0D);
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean visible;
/*     */   
/*     */ 
/*     */ 
/*     */   private RectangleEdge position;
/*     */   
/*     */ 
/*     */ 
/*     */   private HorizontalAlignment horizontalAlignment;
/*     */   
/*     */ 
/*     */ 
/*     */   private VerticalAlignment verticalAlignment;
/*     */   
/*     */ 
/*     */ 
/*     */   private transient EventListenerList listenerList;
/*     */   
/*     */ 
/*     */ 
/*     */   private boolean notify;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Title()
/*     */   {
/* 138 */     this(DEFAULT_POSITION, DEFAULT_HORIZONTAL_ALIGNMENT, DEFAULT_VERTICAL_ALIGNMENT, DEFAULT_PADDING);
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
/*     */   protected Title(RectangleEdge position, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment)
/*     */   {
/* 157 */     this(position, horizontalAlignment, verticalAlignment, DEFAULT_PADDING);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Title(RectangleEdge position, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment, RectangleInsets padding)
/*     */   {
/* 182 */     if (position == null) {
/* 183 */       throw new IllegalArgumentException("Null 'position' argument.");
/*     */     }
/* 185 */     if (horizontalAlignment == null) {
/* 186 */       throw new IllegalArgumentException("Null 'horizontalAlignment' argument.");
/*     */     }
/*     */     
/*     */ 
/* 190 */     if (verticalAlignment == null) {
/* 191 */       throw new IllegalArgumentException("Null 'verticalAlignment' argument.");
/*     */     }
/*     */     
/* 194 */     if (padding == null) {
/* 195 */       throw new IllegalArgumentException("Null 'spacer' argument.");
/*     */     }
/*     */     
/* 198 */     this.visible = true;
/* 199 */     this.position = position;
/* 200 */     this.horizontalAlignment = horizontalAlignment;
/* 201 */     this.verticalAlignment = verticalAlignment;
/* 202 */     setPadding(padding);
/* 203 */     this.listenerList = new EventListenerList();
/* 204 */     this.notify = true;
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
/*     */   public boolean isVisible()
/*     */   {
/* 219 */     return this.visible;
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
/*     */   public void setVisible(boolean visible)
/*     */   {
/* 233 */     this.visible = visible;
/* 234 */     notifyListeners(new TitleChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RectangleEdge getPosition()
/*     */   {
/* 243 */     return this.position;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPosition(RectangleEdge position)
/*     */   {
/* 253 */     if (position == null) {
/* 254 */       throw new IllegalArgumentException("Null 'position' argument.");
/*     */     }
/* 256 */     if (this.position != position) {
/* 257 */       this.position = position;
/* 258 */       notifyListeners(new TitleChangeEvent(this));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public HorizontalAlignment getHorizontalAlignment()
/*     */   {
/* 268 */     return this.horizontalAlignment;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setHorizontalAlignment(HorizontalAlignment alignment)
/*     */   {
/* 279 */     if (alignment == null) {
/* 280 */       throw new IllegalArgumentException("Null 'alignment' argument.");
/*     */     }
/* 282 */     if (this.horizontalAlignment != alignment) {
/* 283 */       this.horizontalAlignment = alignment;
/* 284 */       notifyListeners(new TitleChangeEvent(this));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public VerticalAlignment getVerticalAlignment()
/*     */   {
/* 294 */     return this.verticalAlignment;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setVerticalAlignment(VerticalAlignment alignment)
/*     */   {
/* 305 */     if (alignment == null) {
/* 306 */       throw new IllegalArgumentException("Null 'alignment' argument.");
/*     */     }
/* 308 */     if (this.verticalAlignment != alignment) {
/* 309 */       this.verticalAlignment = alignment;
/* 310 */       notifyListeners(new TitleChangeEvent(this));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean getNotify()
/*     */   {
/* 321 */     return this.notify;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setNotify(boolean flag)
/*     */   {
/* 332 */     this.notify = flag;
/* 333 */     if (flag) {
/* 334 */       notifyListeners(new TitleChangeEvent(this));
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
/*     */ 
/*     */   public abstract void draw(Graphics2D paramGraphics2D, Rectangle2D paramRectangle2D);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */     throws CloneNotSupportedException
/*     */   {
/* 361 */     Title duplicate = (Title)super.clone();
/* 362 */     duplicate.listenerList = new EventListenerList();
/*     */     
/* 364 */     return duplicate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addChangeListener(TitleChangeListener listener)
/*     */   {
/* 373 */     this.listenerList.add(TitleChangeListener.class, listener);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeChangeListener(TitleChangeListener listener)
/*     */   {
/* 382 */     this.listenerList.remove(TitleChangeListener.class, listener);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void notifyListeners(TitleChangeEvent event)
/*     */   {
/* 393 */     if (this.notify) {
/* 394 */       Object[] listeners = this.listenerList.getListenerList();
/* 395 */       for (int i = listeners.length - 2; i >= 0; i -= 2) {
/* 396 */         if (listeners[i] == TitleChangeListener.class) {
/* 397 */           ((TitleChangeListener)listeners[(i + 1)]).titleChanged(event);
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
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 412 */     if (obj == this) {
/* 413 */       return true;
/*     */     }
/* 415 */     if (!(obj instanceof Title)) {
/* 416 */       return false;
/*     */     }
/* 418 */     Title that = (Title)obj;
/* 419 */     if (this.visible != that.visible) {
/* 420 */       return false;
/*     */     }
/* 422 */     if (this.position != that.position) {
/* 423 */       return false;
/*     */     }
/* 425 */     if (this.horizontalAlignment != that.horizontalAlignment) {
/* 426 */       return false;
/*     */     }
/* 428 */     if (this.verticalAlignment != that.verticalAlignment) {
/* 429 */       return false;
/*     */     }
/* 431 */     if (this.notify != that.notify) {
/* 432 */       return false;
/*     */     }
/* 434 */     return super.equals(obj);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 443 */     int result = 193;
/* 444 */     result = 37 * result + ObjectUtilities.hashCode(this.position);
/* 445 */     result = 37 * result + ObjectUtilities.hashCode(this.horizontalAlignment);
/*     */     
/* 447 */     result = 37 * result + ObjectUtilities.hashCode(this.verticalAlignment);
/* 448 */     return result;
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
/* 459 */     stream.defaultWriteObject();
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
/* 472 */     stream.defaultReadObject();
/* 473 */     this.listenerList = new EventListenerList();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/title/Title.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */