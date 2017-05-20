/*     */ package org.jfree.text;
/*     */ 
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jfree.ui.Size2D;
/*     */ import org.jfree.ui.TextAnchor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TextLine
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 7100085690160465444L;
/*     */   private List fragments;
/*     */   
/*     */   public TextLine()
/*     */   {
/*  80 */     this.fragments = new ArrayList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TextLine(String text)
/*     */   {
/*  89 */     this(text, TextFragment.DEFAULT_FONT);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TextLine(String text, Font font)
/*     */   {
/*  99 */     this.fragments = new ArrayList();
/* 100 */     TextFragment fragment = new TextFragment(text, font);
/* 101 */     this.fragments.add(fragment);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TextLine(String text, Font font, Paint paint)
/*     */   {
/* 112 */     if (text == null) {
/* 113 */       throw new IllegalArgumentException("Null 'text' argument.");
/*     */     }
/* 115 */     if (font == null) {
/* 116 */       throw new IllegalArgumentException("Null 'font' argument.");
/*     */     }
/* 118 */     if (paint == null) {
/* 119 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 121 */     this.fragments = new ArrayList();
/* 122 */     TextFragment fragment = new TextFragment(text, font, paint);
/* 123 */     this.fragments.add(fragment);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addFragment(TextFragment fragment)
/*     */   {
/* 132 */     this.fragments.add(fragment);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeFragment(TextFragment fragment)
/*     */   {
/* 141 */     this.fragments.remove(fragment);
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
/*     */   public void draw(Graphics2D g2, float anchorX, float anchorY, TextAnchor anchor, float rotateX, float rotateY, double angle)
/*     */   {
/* 162 */     float x = anchorX;
/* 163 */     float yOffset = calculateBaselineOffset(g2, anchor);
/* 164 */     Iterator iterator = this.fragments.iterator();
/* 165 */     while (iterator.hasNext()) {
/* 166 */       TextFragment fragment = (TextFragment)iterator.next();
/* 167 */       Size2D d = fragment.calculateDimensions(g2);
/* 168 */       fragment.draw(g2, x, anchorY + yOffset, TextAnchor.BASELINE_LEFT, rotateX, rotateY, angle);
/*     */       
/*     */ 
/*     */ 
/* 172 */       x += (float)d.getWidth();
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
/*     */   public Size2D calculateDimensions(Graphics2D g2)
/*     */   {
/* 185 */     double width = 0.0D;
/* 186 */     double height = 0.0D;
/* 187 */     Iterator iterator = this.fragments.iterator();
/* 188 */     while (iterator.hasNext()) {
/* 189 */       TextFragment fragment = (TextFragment)iterator.next();
/* 190 */       Size2D dimension = fragment.calculateDimensions(g2);
/* 191 */       width += dimension.getWidth();
/* 192 */       height = Math.max(height, dimension.getHeight());
/*     */     }
/* 194 */     return new Size2D(width, height);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TextFragment getFirstTextFragment()
/*     */   {
/* 203 */     TextFragment result = null;
/* 204 */     if (this.fragments.size() > 0) {
/* 205 */       result = (TextFragment)this.fragments.get(0);
/*     */     }
/* 207 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TextFragment getLastTextFragment()
/*     */   {
/* 216 */     TextFragment result = null;
/* 217 */     if (this.fragments.size() > 0) {
/* 218 */       result = (TextFragment)this.fragments.get(this.fragments.size() - 1);
/*     */     }
/*     */     
/* 221 */     return result;
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
/*     */   private float calculateBaselineOffset(Graphics2D g2, TextAnchor anchor)
/*     */   {
/* 235 */     float result = 0.0F;
/* 236 */     Iterator iterator = this.fragments.iterator();
/* 237 */     while (iterator.hasNext()) {
/* 238 */       TextFragment fragment = (TextFragment)iterator.next();
/* 239 */       result = Math.max(result, fragment.calculateBaselineOffset(g2, anchor));
/*     */     }
/*     */     
/* 242 */     return result;
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
/* 253 */     if (obj == null) {
/* 254 */       return false;
/*     */     }
/* 256 */     if (obj == this) {
/* 257 */       return true;
/*     */     }
/* 259 */     if ((obj instanceof TextLine)) {
/* 260 */       TextLine line = (TextLine)obj;
/* 261 */       return this.fragments.equals(line.fragments);
/*     */     }
/* 263 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 272 */     return this.fragments != null ? this.fragments.hashCode() : 0;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/text/TextLine.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */