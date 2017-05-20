/*     */ package org.jfree.chart.title;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import org.jfree.chart.block.RectangleConstraint;
/*     */ import org.jfree.chart.event.TitleChangeEvent;
/*     */ import org.jfree.ui.HorizontalAlignment;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.ui.RectangleInsets;
/*     */ import org.jfree.ui.Size2D;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ImageTitle
/*     */   extends Title
/*     */ {
/*     */   private Image image;
/*     */   
/*     */   public ImageTitle(Image image)
/*     */   {
/* 100 */     this(image, image.getHeight(null), image.getWidth(null), Title.DEFAULT_POSITION, Title.DEFAULT_HORIZONTAL_ALIGNMENT, Title.DEFAULT_VERTICAL_ALIGNMENT, Title.DEFAULT_PADDING);
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
/*     */   public ImageTitle(Image image, RectangleEdge position, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment)
/*     */   {
/* 117 */     this(image, image.getHeight(null), image.getWidth(null), position, horizontalAlignment, verticalAlignment, Title.DEFAULT_PADDING);
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
/*     */   public ImageTitle(Image image, int height, int width, RectangleEdge position, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment, RectangleInsets padding)
/*     */   {
/* 141 */     super(position, horizontalAlignment, verticalAlignment, padding);
/* 142 */     if (image == null) {
/* 143 */       throw new NullPointerException("Null 'image' argument.");
/*     */     }
/* 145 */     this.image = image;
/* 146 */     setHeight(height);
/* 147 */     setWidth(width);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Image getImage()
/*     */   {
/* 157 */     return this.image;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setImage(Image image)
/*     */   {
/* 167 */     if (image == null) {
/* 168 */       throw new NullPointerException("Null 'image' argument.");
/*     */     }
/* 170 */     this.image = image;
/* 171 */     notifyListeners(new TitleChangeEvent(this));
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
/* 184 */     Size2D s = new Size2D(this.image.getWidth(null), this.image.getHeight(null));
/*     */     
/* 186 */     return new Size2D(calculateTotalWidth(s.getWidth()), calculateTotalHeight(s.getHeight()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void draw(Graphics2D g2, Rectangle2D area)
/*     */   {
/* 198 */     RectangleEdge position = getPosition();
/* 199 */     if ((position == RectangleEdge.TOP) || (position == RectangleEdge.BOTTOM)) {
/* 200 */       drawHorizontal(g2, area);
/*     */     }
/* 202 */     else if ((position == RectangleEdge.LEFT) || (position == RectangleEdge.RIGHT))
/*     */     {
/* 204 */       drawVertical(g2, area);
/*     */     }
/*     */     else {
/* 207 */       throw new RuntimeException("Invalid title position.");
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
/*     */ 
/*     */   protected Size2D drawHorizontal(Graphics2D g2, Rectangle2D chartArea)
/*     */   {
/* 223 */     double startY = 0.0D;
/* 224 */     double topSpace = 0.0D;
/* 225 */     double bottomSpace = 0.0D;
/* 226 */     double leftSpace = 0.0D;
/* 227 */     double rightSpace = 0.0D;
/*     */     
/* 229 */     double w = getWidth();
/* 230 */     double h = getHeight();
/* 231 */     RectangleInsets padding = getPadding();
/* 232 */     topSpace = padding.calculateTopOutset(h);
/* 233 */     bottomSpace = padding.calculateBottomOutset(h);
/* 234 */     leftSpace = padding.calculateLeftOutset(w);
/* 235 */     rightSpace = padding.calculateRightOutset(w);
/*     */     
/* 237 */     if (getPosition() == RectangleEdge.TOP) {
/* 238 */       startY = chartArea.getY() + topSpace;
/*     */     }
/*     */     else {
/* 241 */       startY = chartArea.getY() + chartArea.getHeight() - bottomSpace - h;
/*     */     }
/*     */     
/*     */ 
/* 245 */     HorizontalAlignment horizontalAlignment = getHorizontalAlignment();
/* 246 */     double startX = 0.0D;
/* 247 */     if (horizontalAlignment == HorizontalAlignment.CENTER) {
/* 248 */       startX = chartArea.getX() + leftSpace + chartArea.getWidth() / 2.0D - w / 2.0D;
/*     */ 
/*     */     }
/* 251 */     else if (horizontalAlignment == HorizontalAlignment.LEFT) {
/* 252 */       startX = chartArea.getX() + leftSpace;
/*     */     }
/* 254 */     else if (horizontalAlignment == HorizontalAlignment.RIGHT) {
/* 255 */       startX = chartArea.getX() + chartArea.getWidth() - rightSpace - w;
/*     */     }
/* 257 */     g2.drawImage(this.image, (int)startX, (int)startY, (int)w, (int)h, null);
/*     */     
/*     */ 
/* 260 */     return new Size2D(chartArea.getWidth() + leftSpace + rightSpace, h + topSpace + bottomSpace);
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
/*     */   protected Size2D drawVertical(Graphics2D g2, Rectangle2D chartArea)
/*     */   {
/* 277 */     double startX = 0.0D;
/* 278 */     double topSpace = 0.0D;
/* 279 */     double bottomSpace = 0.0D;
/* 280 */     double leftSpace = 0.0D;
/* 281 */     double rightSpace = 0.0D;
/*     */     
/* 283 */     double w = getWidth();
/* 284 */     double h = getHeight();
/*     */     
/* 286 */     RectangleInsets padding = getPadding();
/* 287 */     if (padding != null) {
/* 288 */       topSpace = padding.calculateTopOutset(h);
/* 289 */       bottomSpace = padding.calculateBottomOutset(h);
/* 290 */       leftSpace = padding.calculateLeftOutset(w);
/* 291 */       rightSpace = padding.calculateRightOutset(w);
/*     */     }
/*     */     
/* 294 */     if (getPosition() == RectangleEdge.LEFT) {
/* 295 */       startX = chartArea.getX() + leftSpace;
/*     */     }
/*     */     else {
/* 298 */       startX = chartArea.getMaxX() - rightSpace - w;
/*     */     }
/*     */     
/*     */ 
/* 302 */     VerticalAlignment alignment = getVerticalAlignment();
/* 303 */     double startY = 0.0D;
/* 304 */     if (alignment == VerticalAlignment.CENTER) {
/* 305 */       startY = chartArea.getMinY() + topSpace + chartArea.getHeight() / 2.0D - h / 2.0D;
/*     */ 
/*     */     }
/* 308 */     else if (alignment == VerticalAlignment.TOP) {
/* 309 */       startY = chartArea.getMinY() + topSpace;
/*     */     }
/* 311 */     else if (alignment == VerticalAlignment.BOTTOM) {
/* 312 */       startY = chartArea.getMaxY() - bottomSpace - h;
/*     */     }
/*     */     
/* 315 */     g2.drawImage(this.image, (int)startX, (int)startY, (int)w, (int)h, null);
/*     */     
/*     */ 
/* 318 */     return new Size2D(chartArea.getWidth() + leftSpace + rightSpace, h + topSpace + bottomSpace);
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
/*     */   public Object draw(Graphics2D g2, Rectangle2D area, Object params)
/*     */   {
/* 333 */     draw(g2, area);
/* 334 */     return null;
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
/*     */   public boolean equals(Object obj)
/*     */   {
/* 352 */     if (obj == this) {
/* 353 */       return true;
/*     */     }
/* 355 */     if (!(obj instanceof ImageTitle)) {
/* 356 */       return false;
/*     */     }
/* 358 */     ImageTitle that = (ImageTitle)obj;
/* 359 */     if (!ObjectUtilities.equal(this.image, that.image)) {
/* 360 */       return false;
/*     */     }
/* 362 */     return super.equals(obj);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/title/ImageTitle.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */