/*     */ package org.deckfour.xes.util;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XTokenHelper
/*     */ {
/*     */   public static String formatTokenString(List<String> tokens)
/*     */   {
/*  52 */     if (tokens.size() > 0) {
/*  53 */       StringBuffer sb = new StringBuffer();
/*  54 */       sb.append(formatToken((String)tokens.get(0)));
/*  55 */       for (int i = 1; i < tokens.size(); i++) {
/*  56 */         sb.append(' ');
/*  57 */         sb.append(formatToken((String)tokens.get(i)));
/*     */       }
/*  59 */       return sb.toString();
/*     */     }
/*  61 */     return "";
/*     */   }
/*     */   
/*     */   private static String formatToken(String token)
/*     */   {
/*  66 */     token = token.trim();
/*  67 */     if ((token.indexOf(' ') >= 0) || (token.indexOf('\t') >= 0))
/*     */     {
/*  69 */       StringBuffer sb = new StringBuffer();
/*  70 */       token = token.replaceAll("'", "\\'");
/*  71 */       sb.append('\'');
/*  72 */       sb.append(token);
/*  73 */       sb.append('\'');
/*  74 */       return sb.toString();
/*     */     }
/*  76 */     return token;
/*     */   }
/*     */   
/*     */   public static List<String> extractTokens(String tokenString)
/*     */   {
/*  81 */     List<String> tokens = new ArrayList();
/*  82 */     boolean isEscaped = false;
/*  83 */     boolean isQuoted = false;
/*  84 */     StringBuffer sb = new StringBuffer();
/*  85 */     for (char c : tokenString.toCharArray()) {
/*  86 */       if ((c == ' ') && (!isQuoted) && (!isEscaped))
/*     */       {
/*  88 */         String token = sb.toString().trim();
/*  89 */         if (token.length() > 0) {
/*  90 */           tokens.add(token);
/*     */         }
/*     */         
/*  93 */         sb = new StringBuffer();
/*  94 */       } else if (c == '\\') {
/*  95 */         isEscaped = true;
/*  96 */       } else if (c == '\'') {
/*  97 */         if (!isEscaped) {
/*  98 */           isQuoted = !isQuoted;
/*     */         } else {
/* 100 */           sb.append(c);
/*     */         }
/* 102 */         isEscaped = false;
/*     */       } else {
/* 104 */         sb.append(c);
/* 105 */         isEscaped = false;
/*     */       }
/*     */     }
/*     */     
/* 109 */     String token = sb.toString().trim();
/* 110 */     if (token.length() > 0) {
/* 111 */       tokens.add(token);
/*     */     }
/* 113 */     return Collections.unmodifiableList(tokens);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/util/XTokenHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */