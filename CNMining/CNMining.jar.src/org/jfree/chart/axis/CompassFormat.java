/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.text.FieldPosition;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.ParsePosition;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CompassFormat
/*     */   extends NumberFormat
/*     */ {
/*     */   private static final String N = "N";
/*     */   private static final String E = "E";
/*     */   private static final String S = "S";
/*     */   private static final String W = "W";
/*  65 */   public static final String[] DIRECTIONS = { "N", "NNE", "NE", "ENE", "E", "ESE", "SE", "SSE", "S", "SSW", "SW", "WSW", "W", "WNW", "NW", "NNW", "N" };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDirectionCode(double direction)
/*     */   {
/*  86 */     direction %= 360.0D;
/*  87 */     if (direction < 0.0D) {
/*  88 */       direction += 360.0D;
/*     */     }
/*  90 */     int index = ((int)Math.floor(direction / 11.25D) + 1) / 2;
/*  91 */     return DIRECTIONS[index];
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
/*     */   public StringBuffer format(double number, StringBuffer toAppendTo, FieldPosition pos)
/*     */   {
/* 106 */     return toAppendTo.append(getDirectionCode(number));
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
/*     */   public StringBuffer format(long number, StringBuffer toAppendTo, FieldPosition pos)
/*     */   {
/* 120 */     return toAppendTo.append(getDirectionCode(number));
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
/*     */   public Number parse(String source, ParsePosition parsePosition)
/*     */   {
/* 133 */     return null;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/axis/CompassFormat.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */