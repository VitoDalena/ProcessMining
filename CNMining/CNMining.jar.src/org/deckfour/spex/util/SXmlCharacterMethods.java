/*     */ package org.deckfour.spex.util;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SXmlCharacterMethods
/*     */ {
/*     */   public static String convertCharsToXml(String input)
/*     */   {
/*  56 */     StringBuffer result = new StringBuffer();
/*  57 */     for (int i = 0; i < input.length(); i++) {
/*  58 */       switch (input.charAt(i)) {
/*  59 */       case '<':  result.append("&lt;"); break;
/*  60 */       case '>':  result.append("&gt;"); break;
/*  61 */       case '"':  result.append("&quot;"); break;
/*  62 */       case '\'':  result.append("&apos;"); break;
/*  63 */       case '&':  result.append("&amp;"); break;
/*  64 */       default:  result.append(input.charAt(i));
/*     */       }
/*     */     }
/*  67 */     return result.toString().trim();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String convertCharsFromXml(String input)
/*     */   {
/*  77 */     StringBuffer result = new StringBuffer();
/*  78 */     for (int i = 0; i < input.length();) {
/*  79 */       if (input.charAt(i) == '&') {
/*  80 */         if (input.substring(i, i + 3).equals("&lt;")) {
/*  81 */           result.append('<');
/*  82 */           i += 4;
/*  83 */         } else if (input.substring(i, i + 3).equals("&gt;")) {
/*  84 */           result.append('>');
/*  85 */           i += 4;
/*  86 */         } else if (input.substring(i, i + 4).equals("&amp;")) {
/*  87 */           result.append('&');
/*  88 */           i += 5;
/*  89 */         } else if (input.substring(i, i + 5).equals("&quot;")) {
/*  90 */           result.append('"');
/*  91 */           i += 6;
/*  92 */         } else if (input.substring(i, i + 5).equals("&apos;")) {
/*  93 */           result.append('\'');
/*  94 */           i += 6;
/*     */         } else {
/*  96 */           result.append(input.charAt(i));
/*  97 */           i++;
/*     */         }
/*     */       } else {
/* 100 */         result.append(input.charAt(i));
/* 101 */         i++;
/*     */       }
/*     */     }
/* 104 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/spex/util/SXmlCharacterMethods.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */