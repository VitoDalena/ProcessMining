/*     */ package org.jfree.chart.util;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HexNumberFormat
/*     */   extends NumberFormat
/*     */ {
/*     */   public static final int BYTE = 2;
/*     */   public static final int WORD = 4;
/*     */   public static final int DWORD = 8;
/*     */   public static final int QWORD = 16;
/*  68 */   private int m_numDigits = 8;
/*     */   
/*     */ 
/*     */ 
/*     */   public HexNumberFormat()
/*     */   {
/*  74 */     this(8);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public HexNumberFormat(int digits)
/*     */   {
/*  84 */     this.m_numDigits = digits;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final int getNumberOfDigits()
/*     */   {
/*  93 */     return this.m_numDigits;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setNumberOfDigits(int digits)
/*     */   {
/* 102 */     this.m_numDigits = digits;
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
/* 117 */     return format(number, toAppendTo, pos);
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
/*     */   public StringBuffer format(long number, StringBuffer toAppendTo, FieldPosition pos)
/*     */   {
/* 132 */     String l_hex = Long.toHexString(number).toUpperCase();
/*     */     
/* 134 */     int l_pad = this.m_numDigits - l_hex.length();
/* 135 */     l_pad = 0 < l_pad ? l_pad : 0;
/*     */     
/* 137 */     StringBuffer l_extended = new StringBuffer("0x");
/* 138 */     for (int i = 0; i < l_pad; i++) {
/* 139 */       l_extended.append(0);
/*     */     }
/* 141 */     l_extended.append(l_hex);
/*     */     
/* 143 */     return l_extended;
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
/* 156 */     return null;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/util/HexNumberFormat.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */