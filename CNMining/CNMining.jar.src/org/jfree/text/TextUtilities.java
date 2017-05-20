/*     */ package org.jfree.text;
/*     */ 
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.font.FontRenderContext;
/*     */ import java.awt.font.LineMetrics;
/*     */ import java.awt.font.TextLayout;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.text.BreakIterator;
/*     */ import org.jfree.base.AbstractBoot;
/*     */ import org.jfree.base.BaseBoot;
/*     */ import org.jfree.ui.TextAnchor;
/*     */ import org.jfree.util.Configuration;
/*     */ import org.jfree.util.Log;
/*     */ import org.jfree.util.LogContext;
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
/*     */ public class TextUtilities
/*     */ {
/*  86 */   protected static final LogContext logger = Log.createContext(TextUtilities.class);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private static boolean useDrawRotatedStringWorkaround;
/*     */   
/*     */ 
/*     */ 
/*     */   private static boolean useFontMetricsGetStringBounds;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   static
/*     */   {
/*     */     try
/*     */     {
/* 104 */       boolean isJava14 = ObjectUtilities.isJDK14();
/*     */       
/* 106 */       String configRotatedStringWorkaround = BaseBoot.getInstance().getGlobalConfig().getConfigProperty("org.jfree.text.UseDrawRotatedStringWorkaround", "auto");
/*     */       
/*     */ 
/* 109 */       if (configRotatedStringWorkaround.equals("auto")) {
/* 110 */         useDrawRotatedStringWorkaround = !isJava14;
/*     */       }
/*     */       else {
/* 113 */         useDrawRotatedStringWorkaround = configRotatedStringWorkaround.equals("true");
/*     */       }
/*     */       
/*     */ 
/* 117 */       String configFontMetricsStringBounds = BaseBoot.getInstance().getGlobalConfig().getConfigProperty("org.jfree.text.UseFontMetricsGetStringBounds", "auto");
/*     */       
/*     */ 
/* 120 */       if (configFontMetricsStringBounds.equals("auto")) {
/* 121 */         useFontMetricsGetStringBounds = isJava14 == true;
/*     */       }
/*     */       else {
/* 124 */         useFontMetricsGetStringBounds = configFontMetricsStringBounds.equals("true");
/*     */       }
/*     */       
/*     */ 
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 131 */       useDrawRotatedStringWorkaround = true;
/* 132 */       useFontMetricsGetStringBounds = true;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static TextBlock createTextBlock(String text, Font font, Paint paint)
/*     */   {
/* 154 */     if (text == null) {
/* 155 */       throw new IllegalArgumentException("Null 'text' argument.");
/*     */     }
/* 157 */     TextBlock result = new TextBlock();
/* 158 */     String input = text;
/* 159 */     boolean moreInputToProcess = text.length() > 0;
/* 160 */     int start = 0;
/* 161 */     while (moreInputToProcess) {
/* 162 */       int index = input.indexOf("\n");
/* 163 */       if (index > 0) {
/* 164 */         String line = input.substring(0, index);
/* 165 */         if (index < input.length() - 1) {
/* 166 */           result.addLine(line, font, paint);
/* 167 */           input = input.substring(index + 1);
/*     */         }
/*     */         else {
/* 170 */           moreInputToProcess = false;
/*     */         }
/*     */       }
/* 173 */       else if (index == 0) {
/* 174 */         if (index < input.length() - 1) {
/* 175 */           input = input.substring(index + 1);
/*     */         }
/*     */         else {
/* 178 */           moreInputToProcess = false;
/*     */         }
/*     */       }
/*     */       else {
/* 182 */         result.addLine(input, font, paint);
/* 183 */         moreInputToProcess = false;
/*     */       }
/*     */     }
/* 186 */     return result;
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
/*     */   public static TextBlock createTextBlock(String text, Font font, Paint paint, float maxWidth, TextMeasurer measurer)
/*     */   {
/* 206 */     return createTextBlock(text, font, paint, maxWidth, Integer.MAX_VALUE, measurer);
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
/*     */   public static TextBlock createTextBlock(String text, Font font, Paint paint, float maxWidth, int maxLines, TextMeasurer measurer)
/*     */   {
/* 228 */     TextBlock result = new TextBlock();
/* 229 */     BreakIterator iterator = BreakIterator.getLineInstance();
/* 230 */     iterator.setText(text);
/* 231 */     int current = 0;
/* 232 */     int lines = 0;
/* 233 */     int length = text.length();
/* 234 */     for (; (current < length) && (lines < maxLines); 
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 245 */         goto 103)
/*     */     {
/* 235 */       int next = nextLineBreak(text, current, maxWidth, iterator, measurer);
/*     */       
/* 237 */       if (next == -1) {
/* 238 */         result.addLine(text.substring(current), font, paint);
/* 239 */         return result;
/*     */       }
/* 241 */       result.addLine(text.substring(current, next), font, paint);
/* 242 */       lines++;
/* 243 */       current = next;
/* 244 */       if ((current < text.length()) && (text.charAt(current) == '\n')) {
/* 245 */         current++;
/*     */       }
/*     */     }
/* 248 */     if (current < length) {
/* 249 */       TextLine lastLine = result.getLastLine();
/* 250 */       TextFragment lastFragment = lastLine.getLastTextFragment();
/* 251 */       String oldStr = lastFragment.getText();
/* 252 */       String newStr = "...";
/* 253 */       if (oldStr.length() > 3) {
/* 254 */         newStr = oldStr.substring(0, oldStr.length() - 3) + "...";
/*     */       }
/*     */       
/* 257 */       lastLine.removeFragment(lastFragment);
/* 258 */       TextFragment newFragment = new TextFragment(newStr, lastFragment.getFont(), lastFragment.getPaint());
/*     */       
/* 260 */       lastLine.addFragment(newFragment);
/*     */     }
/* 262 */     return result;
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
/*     */   private static int nextLineBreak(String text, int start, float width, BreakIterator iterator, TextMeasurer measurer)
/*     */   {
/* 282 */     int current = start;
/*     */     
/* 284 */     float x = 0.0F;
/* 285 */     boolean firstWord = true;
/* 286 */     int newline = text.indexOf('\n', start);
/* 287 */     if (newline < 0)
/* 288 */       newline = Integer.MAX_VALUE;
/*     */     int end;
/* 290 */     while ((end = iterator.next()) != -1) {
/* 291 */       if (end > newline) {
/* 292 */         return newline;
/*     */       }
/* 294 */       x += measurer.getStringWidth(text, current, end);
/* 295 */       if (x > width) {
/* 296 */         if (firstWord) {
/* 297 */           while (measurer.getStringWidth(text, start, end) > width) {
/* 298 */             end--;
/* 299 */             if (end <= start) {
/* 300 */               return end;
/*     */             }
/*     */           }
/* 303 */           return end;
/*     */         }
/*     */         
/* 306 */         end = iterator.previous();
/* 307 */         return end;
/*     */       }
/*     */       
/*     */ 
/* 311 */       firstWord = false;
/* 312 */       current = end;
/*     */     }
/* 314 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Rectangle2D getTextBounds(String text, Graphics2D g2, FontMetrics fm)
/*     */   {
/*     */     Rectangle2D bounds;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 331 */     if (useFontMetricsGetStringBounds) {
/* 332 */       Rectangle2D bounds = fm.getStringBounds(text, g2);
/*     */       
/*     */ 
/*     */ 
/* 336 */       LineMetrics lm = fm.getFont().getLineMetrics(text, g2.getFontRenderContext());
/*     */       
/* 338 */       bounds.setRect(bounds.getX(), bounds.getY(), bounds.getWidth(), lm.getHeight());
/*     */     }
/*     */     else
/*     */     {
/* 342 */       double width = fm.stringWidth(text);
/* 343 */       double height = fm.getHeight();
/* 344 */       if (logger.isDebugEnabled()) {
/* 345 */         logger.debug("Height = " + height);
/*     */       }
/* 347 */       bounds = new Rectangle2D.Double(0.0D, -fm.getAscent(), width, height);
/*     */     }
/*     */     
/* 350 */     return bounds;
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
/*     */   public static Rectangle2D drawAlignedString(String text, Graphics2D g2, float x, float y, TextAnchor anchor)
/*     */   {
/* 369 */     Rectangle2D textBounds = new Rectangle2D.Double();
/* 370 */     float[] adjust = deriveTextBoundsAnchorOffsets(g2, text, anchor, textBounds);
/*     */     
/*     */ 
/* 373 */     textBounds.setRect(x + adjust[0], y + adjust[1] + adjust[2], textBounds.getWidth(), textBounds.getHeight());
/*     */     
/* 375 */     g2.drawString(text, x + adjust[0], y + adjust[1]);
/* 376 */     return textBounds;
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
/*     */   private static float[] deriveTextBoundsAnchorOffsets(Graphics2D g2, String text, TextAnchor anchor, Rectangle2D textBounds)
/*     */   {
/* 399 */     float[] result = new float[3];
/* 400 */     FontRenderContext frc = g2.getFontRenderContext();
/* 401 */     Font f = g2.getFont();
/* 402 */     FontMetrics fm = g2.getFontMetrics(f);
/* 403 */     Rectangle2D bounds = getTextBounds(text, g2, fm);
/* 404 */     LineMetrics metrics = f.getLineMetrics(text, frc);
/* 405 */     float ascent = metrics.getAscent();
/* 406 */     result[2] = (-ascent);
/* 407 */     float halfAscent = ascent / 2.0F;
/* 408 */     float descent = metrics.getDescent();
/* 409 */     float leading = metrics.getLeading();
/* 410 */     float xAdj = 0.0F;
/* 411 */     float yAdj = 0.0F;
/*     */     
/* 413 */     if ((anchor == TextAnchor.TOP_CENTER) || (anchor == TextAnchor.CENTER) || (anchor == TextAnchor.BOTTOM_CENTER) || (anchor == TextAnchor.BASELINE_CENTER) || (anchor == TextAnchor.HALF_ASCENT_CENTER))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 419 */       xAdj = (float)-bounds.getWidth() / 2.0F;
/*     */ 
/*     */     }
/* 422 */     else if ((anchor == TextAnchor.TOP_RIGHT) || (anchor == TextAnchor.CENTER_RIGHT) || (anchor == TextAnchor.BOTTOM_RIGHT) || (anchor == TextAnchor.BASELINE_RIGHT) || (anchor == TextAnchor.HALF_ASCENT_RIGHT))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 428 */       xAdj = (float)-bounds.getWidth();
/*     */     }
/*     */     
/*     */ 
/* 432 */     if ((anchor == TextAnchor.TOP_LEFT) || (anchor == TextAnchor.TOP_CENTER) || (anchor == TextAnchor.TOP_RIGHT))
/*     */     {
/*     */ 
/*     */ 
/* 436 */       yAdj = -descent - leading + (float)bounds.getHeight();
/*     */ 
/*     */     }
/* 439 */     else if ((anchor == TextAnchor.HALF_ASCENT_LEFT) || (anchor == TextAnchor.HALF_ASCENT_CENTER) || (anchor == TextAnchor.HALF_ASCENT_RIGHT))
/*     */     {
/*     */ 
/*     */ 
/* 443 */       yAdj = halfAscent;
/*     */ 
/*     */     }
/* 446 */     else if ((anchor == TextAnchor.CENTER_LEFT) || (anchor == TextAnchor.CENTER) || (anchor == TextAnchor.CENTER_RIGHT))
/*     */     {
/*     */ 
/*     */ 
/* 450 */       yAdj = -descent - leading + (float)(bounds.getHeight() / 2.0D);
/*     */ 
/*     */     }
/* 453 */     else if ((anchor == TextAnchor.BASELINE_LEFT) || (anchor == TextAnchor.BASELINE_CENTER) || (anchor == TextAnchor.BASELINE_RIGHT))
/*     */     {
/*     */ 
/*     */ 
/* 457 */       yAdj = 0.0F;
/*     */ 
/*     */     }
/* 460 */     else if ((anchor == TextAnchor.BOTTOM_LEFT) || (anchor == TextAnchor.BOTTOM_CENTER) || (anchor == TextAnchor.BOTTOM_RIGHT))
/*     */     {
/*     */ 
/*     */ 
/* 464 */       yAdj = -metrics.getDescent() - metrics.getLeading();
/*     */     }
/*     */     
/* 467 */     if (textBounds != null) {
/* 468 */       textBounds.setRect(bounds);
/*     */     }
/* 470 */     result[0] = xAdj;
/* 471 */     result[1] = yAdj;
/* 472 */     return result;
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
/*     */   public static void setUseDrawRotatedStringWorkaround(boolean use)
/*     */   {
/* 486 */     useDrawRotatedStringWorkaround = use;
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
/*     */   public static void drawRotatedString(String text, Graphics2D g2, double angle, float x, float y)
/*     */   {
/* 503 */     drawRotatedString(text, g2, x, y, angle, x, y);
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
/*     */   public static void drawRotatedString(String text, Graphics2D g2, float textX, float textY, double angle, float rotateX, float rotateY)
/*     */   {
/* 524 */     if ((text == null) || (text.equals(""))) {
/* 525 */       return;
/*     */     }
/*     */     
/* 528 */     AffineTransform saved = g2.getTransform();
/*     */     
/*     */ 
/* 531 */     AffineTransform rotate = AffineTransform.getRotateInstance(angle, rotateX, rotateY);
/*     */     
/* 533 */     g2.transform(rotate);
/*     */     
/* 535 */     if (useDrawRotatedStringWorkaround)
/*     */     {
/* 537 */       TextLayout tl = new TextLayout(text, g2.getFont(), g2.getFontRenderContext());
/*     */       
/* 539 */       tl.draw(g2, textX, textY);
/*     */     }
/*     */     else
/*     */     {
/* 543 */       g2.drawString(text, textX, textY);
/*     */     }
/* 545 */     g2.setTransform(saved);
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
/*     */   public static void drawRotatedString(String text, Graphics2D g2, float x, float y, TextAnchor textAnchor, double angle, float rotationX, float rotationY)
/*     */   {
/* 567 */     if ((text == null) || (text.equals(""))) {
/* 568 */       return;
/*     */     }
/* 570 */     float[] textAdj = deriveTextBoundsAnchorOffsets(g2, text, textAnchor);
/*     */     
/* 572 */     drawRotatedString(text, g2, x + textAdj[0], y + textAdj[1], angle, rotationX, rotationY);
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
/*     */   public static void drawRotatedString(String text, Graphics2D g2, float x, float y, TextAnchor textAnchor, double angle, TextAnchor rotationAnchor)
/*     */   {
/* 592 */     if ((text == null) || (text.equals(""))) {
/* 593 */       return;
/*     */     }
/* 595 */     float[] textAdj = deriveTextBoundsAnchorOffsets(g2, text, textAnchor);
/*     */     
/* 597 */     float[] rotateAdj = deriveRotationAnchorOffsets(g2, text, rotationAnchor);
/*     */     
/* 599 */     drawRotatedString(text, g2, x + textAdj[0], y + textAdj[1], angle, x + textAdj[0] + rotateAdj[0], y + textAdj[1] + rotateAdj[1]);
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
/*     */   public static Shape calculateRotatedStringBounds(String text, Graphics2D g2, float x, float y, TextAnchor textAnchor, double angle, TextAnchor rotationAnchor)
/*     */   {
/* 624 */     if ((text == null) || (text.equals(""))) {
/* 625 */       return null;
/*     */     }
/* 627 */     float[] textAdj = deriveTextBoundsAnchorOffsets(g2, text, textAnchor);
/*     */     
/* 629 */     if (logger.isDebugEnabled()) {
/* 630 */       logger.debug("TextBoundsAnchorOffsets = " + textAdj[0] + ", " + textAdj[1]);
/*     */     }
/*     */     
/* 633 */     float[] rotateAdj = deriveRotationAnchorOffsets(g2, text, rotationAnchor);
/*     */     
/* 635 */     if (logger.isDebugEnabled()) {
/* 636 */       logger.debug("RotationAnchorOffsets = " + rotateAdj[0] + ", " + rotateAdj[1]);
/*     */     }
/*     */     
/* 639 */     Shape result = calculateRotatedStringBounds(text, g2, x + textAdj[0], y + textAdj[1], angle, x + textAdj[0] + rotateAdj[0], y + textAdj[1] + rotateAdj[1]);
/*     */     
/*     */ 
/* 642 */     return result;
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
/*     */   private static float[] deriveTextBoundsAnchorOffsets(Graphics2D g2, String text, TextAnchor anchor)
/*     */   {
/* 662 */     float[] result = new float[2];
/* 663 */     FontRenderContext frc = g2.getFontRenderContext();
/* 664 */     Font f = g2.getFont();
/* 665 */     FontMetrics fm = g2.getFontMetrics(f);
/* 666 */     Rectangle2D bounds = getTextBounds(text, g2, fm);
/* 667 */     LineMetrics metrics = f.getLineMetrics(text, frc);
/* 668 */     float ascent = metrics.getAscent();
/* 669 */     float halfAscent = ascent / 2.0F;
/* 670 */     float descent = metrics.getDescent();
/* 671 */     float leading = metrics.getLeading();
/* 672 */     float xAdj = 0.0F;
/* 673 */     float yAdj = 0.0F;
/*     */     
/* 675 */     if ((anchor == TextAnchor.TOP_CENTER) || (anchor == TextAnchor.CENTER) || (anchor == TextAnchor.BOTTOM_CENTER) || (anchor == TextAnchor.BASELINE_CENTER) || (anchor == TextAnchor.HALF_ASCENT_CENTER))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 681 */       xAdj = (float)-bounds.getWidth() / 2.0F;
/*     */ 
/*     */     }
/* 684 */     else if ((anchor == TextAnchor.TOP_RIGHT) || (anchor == TextAnchor.CENTER_RIGHT) || (anchor == TextAnchor.BOTTOM_RIGHT) || (anchor == TextAnchor.BASELINE_RIGHT) || (anchor == TextAnchor.HALF_ASCENT_RIGHT))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 690 */       xAdj = (float)-bounds.getWidth();
/*     */     }
/*     */     
/*     */ 
/* 694 */     if ((anchor == TextAnchor.TOP_LEFT) || (anchor == TextAnchor.TOP_CENTER) || (anchor == TextAnchor.TOP_RIGHT))
/*     */     {
/*     */ 
/*     */ 
/* 698 */       yAdj = -descent - leading + (float)bounds.getHeight();
/*     */ 
/*     */     }
/* 701 */     else if ((anchor == TextAnchor.HALF_ASCENT_LEFT) || (anchor == TextAnchor.HALF_ASCENT_CENTER) || (anchor == TextAnchor.HALF_ASCENT_RIGHT))
/*     */     {
/*     */ 
/*     */ 
/* 705 */       yAdj = halfAscent;
/*     */ 
/*     */     }
/* 708 */     else if ((anchor == TextAnchor.CENTER_LEFT) || (anchor == TextAnchor.CENTER) || (anchor == TextAnchor.CENTER_RIGHT))
/*     */     {
/*     */ 
/*     */ 
/* 712 */       yAdj = -descent - leading + (float)(bounds.getHeight() / 2.0D);
/*     */ 
/*     */     }
/* 715 */     else if ((anchor == TextAnchor.BASELINE_LEFT) || (anchor == TextAnchor.BASELINE_CENTER) || (anchor == TextAnchor.BASELINE_RIGHT))
/*     */     {
/*     */ 
/*     */ 
/* 719 */       yAdj = 0.0F;
/*     */ 
/*     */     }
/* 722 */     else if ((anchor == TextAnchor.BOTTOM_LEFT) || (anchor == TextAnchor.BOTTOM_CENTER) || (anchor == TextAnchor.BOTTOM_RIGHT))
/*     */     {
/*     */ 
/*     */ 
/* 726 */       yAdj = -metrics.getDescent() - metrics.getLeading();
/*     */     }
/*     */     
/* 729 */     result[0] = xAdj;
/* 730 */     result[1] = yAdj;
/* 731 */     return result;
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
/*     */   private static float[] deriveRotationAnchorOffsets(Graphics2D g2, String text, TextAnchor anchor)
/*     */   {
/* 749 */     float[] result = new float[2];
/* 750 */     FontRenderContext frc = g2.getFontRenderContext();
/* 751 */     LineMetrics metrics = g2.getFont().getLineMetrics(text, frc);
/* 752 */     FontMetrics fm = g2.getFontMetrics();
/* 753 */     Rectangle2D bounds = getTextBounds(text, g2, fm);
/* 754 */     float ascent = metrics.getAscent();
/* 755 */     float halfAscent = ascent / 2.0F;
/* 756 */     float descent = metrics.getDescent();
/* 757 */     float leading = metrics.getLeading();
/* 758 */     float xAdj = 0.0F;
/* 759 */     float yAdj = 0.0F;
/*     */     
/* 761 */     if ((anchor == TextAnchor.TOP_LEFT) || (anchor == TextAnchor.CENTER_LEFT) || (anchor == TextAnchor.BOTTOM_LEFT) || (anchor == TextAnchor.BASELINE_LEFT) || (anchor == TextAnchor.HALF_ASCENT_LEFT))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 767 */       xAdj = 0.0F;
/*     */ 
/*     */     }
/* 770 */     else if ((anchor == TextAnchor.TOP_CENTER) || (anchor == TextAnchor.CENTER) || (anchor == TextAnchor.BOTTOM_CENTER) || (anchor == TextAnchor.BASELINE_CENTER) || (anchor == TextAnchor.HALF_ASCENT_CENTER))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 776 */       xAdj = (float)bounds.getWidth() / 2.0F;
/*     */ 
/*     */     }
/* 779 */     else if ((anchor == TextAnchor.TOP_RIGHT) || (anchor == TextAnchor.CENTER_RIGHT) || (anchor == TextAnchor.BOTTOM_RIGHT) || (anchor == TextAnchor.BASELINE_RIGHT) || (anchor == TextAnchor.HALF_ASCENT_RIGHT))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 785 */       xAdj = (float)bounds.getWidth();
/*     */     }
/*     */     
/*     */ 
/* 789 */     if ((anchor == TextAnchor.TOP_LEFT) || (anchor == TextAnchor.TOP_CENTER) || (anchor == TextAnchor.TOP_RIGHT))
/*     */     {
/*     */ 
/*     */ 
/* 793 */       yAdj = descent + leading - (float)bounds.getHeight();
/*     */ 
/*     */     }
/* 796 */     else if ((anchor == TextAnchor.CENTER_LEFT) || (anchor == TextAnchor.CENTER) || (anchor == TextAnchor.CENTER_RIGHT))
/*     */     {
/*     */ 
/*     */ 
/* 800 */       yAdj = descent + leading - (float)(bounds.getHeight() / 2.0D);
/*     */ 
/*     */     }
/* 803 */     else if ((anchor == TextAnchor.HALF_ASCENT_LEFT) || (anchor == TextAnchor.HALF_ASCENT_CENTER) || (anchor == TextAnchor.HALF_ASCENT_RIGHT))
/*     */     {
/*     */ 
/*     */ 
/* 807 */       yAdj = -halfAscent;
/*     */ 
/*     */     }
/* 810 */     else if ((anchor == TextAnchor.BASELINE_LEFT) || (anchor == TextAnchor.BASELINE_CENTER) || (anchor == TextAnchor.BASELINE_RIGHT))
/*     */     {
/*     */ 
/*     */ 
/* 814 */       yAdj = 0.0F;
/*     */ 
/*     */     }
/* 817 */     else if ((anchor == TextAnchor.BOTTOM_LEFT) || (anchor == TextAnchor.BOTTOM_CENTER) || (anchor == TextAnchor.BOTTOM_RIGHT))
/*     */     {
/*     */ 
/*     */ 
/* 821 */       yAdj = metrics.getDescent() + metrics.getLeading();
/*     */     }
/*     */     
/* 824 */     result[0] = xAdj;
/* 825 */     result[1] = yAdj;
/* 826 */     return result;
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
/*     */   public static Shape calculateRotatedStringBounds(String text, Graphics2D g2, float textX, float textY, double angle, float rotateX, float rotateY)
/*     */   {
/* 849 */     if ((text == null) || (text.equals(""))) {
/* 850 */       return null;
/*     */     }
/* 852 */     FontMetrics fm = g2.getFontMetrics();
/* 853 */     Rectangle2D bounds = getTextBounds(text, g2, fm);
/* 854 */     AffineTransform translate = AffineTransform.getTranslateInstance(textX, textY);
/*     */     
/* 856 */     Shape translatedBounds = translate.createTransformedShape(bounds);
/* 857 */     AffineTransform rotate = AffineTransform.getRotateInstance(angle, rotateX, rotateY);
/*     */     
/* 859 */     Shape result = rotate.createTransformedShape(translatedBounds);
/* 860 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean getUseFontMetricsGetStringBounds()
/*     */   {
/* 872 */     return useFontMetricsGetStringBounds;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void setUseFontMetricsGetStringBounds(boolean use)
/*     */   {
/* 883 */     useFontMetricsGetStringBounds = use;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean isUseDrawRotatedStringWorkaround()
/*     */   {
/* 893 */     return useDrawRotatedStringWorkaround;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/text/TextUtilities.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */