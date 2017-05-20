/*     */ package org.jfree.text;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.font.LineMetrics;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.Size2D;
/*     */ import org.jfree.ui.TextAnchor;
/*     */ import org.jfree.util.Log;
/*     */ import org.jfree.util.LogContext;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TextFragment
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 4465945952903143262L;
/*  82 */   public static final Font DEFAULT_FONT = new Font("Serif", 0, 12);
/*     */   
/*     */ 
/*  85 */   public static final Paint DEFAULT_PAINT = Color.black;
/*     */   
/*     */ 
/*     */ 
/*     */   private String text;
/*     */   
/*     */ 
/*     */ 
/*     */   private Font font;
/*     */   
/*     */ 
/*     */ 
/*     */   private transient Paint paint;
/*     */   
/*     */ 
/*     */   private float baselineOffset;
/*     */   
/*     */ 
/* 103 */   protected static final LogContext logger = Log.createContext(TextFragment.class);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TextFragment(String text)
/*     */   {
/* 112 */     this(text, DEFAULT_FONT, DEFAULT_PAINT);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TextFragment(String text, Font font)
/*     */   {
/* 122 */     this(text, font, DEFAULT_PAINT);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TextFragment(String text, Font font, Paint paint)
/*     */   {
/* 133 */     this(text, font, paint, 0.0F);
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
/*     */   public TextFragment(String text, Font font, Paint paint, float baselineOffset)
/*     */   {
/* 146 */     if (text == null) {
/* 147 */       throw new IllegalArgumentException("Null 'text' argument.");
/*     */     }
/* 149 */     if (font == null) {
/* 150 */       throw new IllegalArgumentException("Null 'font' argument.");
/*     */     }
/* 152 */     if (paint == null) {
/* 153 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 155 */     this.text = text;
/* 156 */     this.font = font;
/* 157 */     this.paint = paint;
/* 158 */     this.baselineOffset = baselineOffset;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getText()
/*     */   {
/* 167 */     return this.text;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Font getFont()
/*     */   {
/* 176 */     return this.font;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getPaint()
/*     */   {
/* 185 */     return this.paint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float getBaselineOffset()
/*     */   {
/* 194 */     return this.baselineOffset;
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
/*     */   public void draw(Graphics2D g2, float anchorX, float anchorY, TextAnchor anchor, float rotateX, float rotateY, double angle)
/*     */   {
/* 214 */     g2.setFont(this.font);
/* 215 */     g2.setPaint(this.paint);
/* 216 */     TextUtilities.drawRotatedString(this.text, g2, anchorX, anchorY + this.baselineOffset, anchor, angle, rotateX, rotateY);
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
/*     */   public Size2D calculateDimensions(Graphics2D g2)
/*     */   {
/* 229 */     FontMetrics fm = g2.getFontMetrics(this.font);
/* 230 */     Rectangle2D bounds = TextUtilities.getTextBounds(this.text, g2, fm);
/*     */     
/* 232 */     Size2D result = new Size2D(bounds.getWidth(), bounds.getHeight());
/* 233 */     return result;
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
/*     */   public float calculateBaselineOffset(Graphics2D g2, TextAnchor anchor)
/*     */   {
/* 247 */     float result = 0.0F;
/* 248 */     FontMetrics fm = g2.getFontMetrics(this.font);
/* 249 */     LineMetrics lm = fm.getLineMetrics("ABCxyz", g2);
/* 250 */     if ((anchor == TextAnchor.TOP_LEFT) || (anchor == TextAnchor.TOP_CENTER) || (anchor == TextAnchor.TOP_RIGHT))
/*     */     {
/* 252 */       result = lm.getAscent();
/*     */     }
/* 254 */     else if ((anchor == TextAnchor.BOTTOM_LEFT) || (anchor == TextAnchor.BOTTOM_CENTER) || (anchor == TextAnchor.BOTTOM_RIGHT))
/*     */     {
/*     */ 
/* 257 */       result = -lm.getDescent() - lm.getLeading();
/*     */     }
/* 259 */     return result;
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
/* 270 */     if (obj == null) {
/* 271 */       return false;
/*     */     }
/* 273 */     if (obj == this) {
/* 274 */       return true;
/*     */     }
/* 276 */     if ((obj instanceof TextFragment)) {
/* 277 */       TextFragment tf = (TextFragment)obj;
/* 278 */       if (!this.text.equals(tf.text)) {
/* 279 */         return false;
/*     */       }
/* 281 */       if (!this.font.equals(tf.font)) {
/* 282 */         return false;
/*     */       }
/* 284 */       if (!this.paint.equals(tf.paint)) {
/* 285 */         return false;
/*     */       }
/* 287 */       return true;
/*     */     }
/* 289 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 299 */     int result = this.text != null ? this.text.hashCode() : 0;
/* 300 */     result = 29 * result + (this.font != null ? this.font.hashCode() : 0);
/* 301 */     result = 29 * result + (this.paint != null ? this.paint.hashCode() : 0);
/* 302 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void writeObject(ObjectOutputStream stream)
/*     */     throws IOException
/*     */   {
/* 314 */     stream.defaultWriteObject();
/* 315 */     SerialUtilities.writePaint(this.paint, stream);
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
/* 328 */     stream.defaultReadObject();
/* 329 */     this.paint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/text/TextFragment.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */