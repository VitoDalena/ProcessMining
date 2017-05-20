/*     */ package org.jfree.chart.annotations;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Paint;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.HashUtilities;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.TextAnchor;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PaintUtilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TextAnnotation
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 7008912287533127432L;
/*  78 */   public static final Font DEFAULT_FONT = new Font("SansSerif", 0, 10);
/*     */   
/*     */ 
/*     */ 
/*  82 */   public static final Paint DEFAULT_PAINT = Color.black;
/*     */   
/*     */ 
/*  85 */   public static final TextAnchor DEFAULT_TEXT_ANCHOR = TextAnchor.CENTER;
/*     */   
/*     */ 
/*  88 */   public static final TextAnchor DEFAULT_ROTATION_ANCHOR = TextAnchor.CENTER;
/*     */   
/*     */ 
/*     */ 
/*     */   public static final double DEFAULT_ROTATION_ANGLE = 0.0D;
/*     */   
/*     */ 
/*     */ 
/*     */   private String text;
/*     */   
/*     */ 
/*     */   private Font font;
/*     */   
/*     */ 
/*     */   private transient Paint paint;
/*     */   
/*     */ 
/*     */   private TextAnchor textAnchor;
/*     */   
/*     */ 
/*     */   private TextAnchor rotationAnchor;
/*     */   
/*     */ 
/*     */   private double rotationAngle;
/*     */   
/*     */ 
/*     */ 
/*     */   protected TextAnnotation(String text)
/*     */   {
/* 117 */     if (text == null) {
/* 118 */       throw new IllegalArgumentException("Null 'text' argument.");
/*     */     }
/* 120 */     this.text = text;
/* 121 */     this.font = DEFAULT_FONT;
/* 122 */     this.paint = DEFAULT_PAINT;
/* 123 */     this.textAnchor = DEFAULT_TEXT_ANCHOR;
/* 124 */     this.rotationAnchor = DEFAULT_ROTATION_ANCHOR;
/* 125 */     this.rotationAngle = 0.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getText()
/*     */   {
/* 136 */     return this.text;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setText(String text)
/*     */   {
/* 147 */     if (text == null) {
/* 148 */       throw new IllegalArgumentException("Null 'text' argument.");
/*     */     }
/* 150 */     this.text = text;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Font getFont()
/*     */   {
/* 161 */     return this.font;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setFont(Font font)
/*     */   {
/* 172 */     if (font == null) {
/* 173 */       throw new IllegalArgumentException("Null 'font' argument.");
/*     */     }
/* 175 */     this.font = font;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getPaint()
/*     */   {
/* 186 */     return this.paint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPaint(Paint paint)
/*     */   {
/* 197 */     if (paint == null) {
/* 198 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 200 */     this.paint = paint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TextAnchor getTextAnchor()
/*     */   {
/* 211 */     return this.textAnchor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTextAnchor(TextAnchor anchor)
/*     */   {
/* 223 */     if (anchor == null) {
/* 224 */       throw new IllegalArgumentException("Null 'anchor' argument.");
/*     */     }
/* 226 */     this.textAnchor = anchor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TextAnchor getRotationAnchor()
/*     */   {
/* 237 */     return this.rotationAnchor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setRotationAnchor(TextAnchor anchor)
/*     */   {
/* 248 */     this.rotationAnchor = anchor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getRotationAngle()
/*     */   {
/* 259 */     return this.rotationAngle;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setRotationAngle(double angle)
/*     */   {
/* 270 */     this.rotationAngle = angle;
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
/* 281 */     if (obj == this) {
/* 282 */       return true;
/*     */     }
/*     */     
/* 285 */     if (!(obj instanceof TextAnnotation)) {
/* 286 */       return false;
/*     */     }
/* 288 */     TextAnnotation that = (TextAnnotation)obj;
/* 289 */     if (!ObjectUtilities.equal(this.text, that.getText())) {
/* 290 */       return false;
/*     */     }
/* 292 */     if (!ObjectUtilities.equal(this.font, that.getFont())) {
/* 293 */       return false;
/*     */     }
/* 295 */     if (!PaintUtilities.equal(this.paint, that.getPaint())) {
/* 296 */       return false;
/*     */     }
/* 298 */     if (!ObjectUtilities.equal(this.textAnchor, that.getTextAnchor())) {
/* 299 */       return false;
/*     */     }
/* 301 */     if (!ObjectUtilities.equal(this.rotationAnchor, that.getRotationAnchor()))
/*     */     {
/* 303 */       return false;
/*     */     }
/* 305 */     if (this.rotationAngle != that.getRotationAngle()) {
/* 306 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 310 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 320 */     int result = 193;
/* 321 */     result = 37 * result + this.font.hashCode();
/* 322 */     result = 37 * result + HashUtilities.hashCodeForPaint(this.paint);
/* 323 */     result = 37 * result + this.rotationAnchor.hashCode();
/* 324 */     long temp = Double.doubleToLongBits(this.rotationAngle);
/* 325 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/* 326 */     result = 37 * result + this.text.hashCode();
/* 327 */     result = 37 * result + this.textAnchor.hashCode();
/* 328 */     return result;
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
/* 339 */     stream.defaultWriteObject();
/* 340 */     SerialUtilities.writePaint(this.paint, stream);
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
/* 353 */     stream.defaultReadObject();
/* 354 */     this.paint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/annotations/TextAnnotation.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */