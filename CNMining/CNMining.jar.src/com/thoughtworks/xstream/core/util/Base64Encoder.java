/*    */ package com.thoughtworks.xstream.core.util;
/*    */ 
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.Reader;
/*    */ import java.io.StringReader;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Base64Encoder
/*    */ {
/* 51 */   private static final char[] SIXTY_FOUR_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
/* 52 */   private static final int[] REVERSE_MAPPING = new int[123];
/*    */   
/*    */   static {
/* 55 */     for (int i = 0; i < SIXTY_FOUR_CHARS.length; i++) REVERSE_MAPPING[SIXTY_FOUR_CHARS[i]] = (i + 1);
/*    */   }
/*    */   
/*    */   public String encode(byte[] input) {
/* 59 */     StringBuffer result = new StringBuffer();
/* 60 */     int outputCharCount = 0;
/* 61 */     for (int i = 0; i < input.length; i += 3) {
/* 62 */       int remaining = Math.min(3, input.length - i);
/* 63 */       int oneBigNumber = (input[i] & 0xFF) << 16 | (remaining <= 1 ? 0 : input[(i + 1)] & 0xFF) << 8 | (remaining <= 2 ? 0 : input[(i + 2)] & 0xFF);
/* 64 */       for (int j = 0; j < 4; j++) result.append(remaining + 1 > j ? SIXTY_FOUR_CHARS[(0x3F & oneBigNumber >> 6 * (3 - j))] : '=');
/* 65 */       outputCharCount += 4; if (outputCharCount % 76 == 0) result.append('\n');
/*    */     }
/* 67 */     return result.toString();
/*    */   }
/*    */   
/*    */   public byte[] decode(String input) {
/*    */     try {
/* 72 */       ByteArrayOutputStream out = new ByteArrayOutputStream();
/* 73 */       StringReader in = new StringReader(input);
/* 74 */       for (int i = 0; i < input.length(); i += 4) {
/* 75 */         int[] a = { mapCharToInt(in), mapCharToInt(in), mapCharToInt(in), mapCharToInt(in) };
/* 76 */         int oneBigNumber = (a[0] & 0x3F) << 18 | (a[1] & 0x3F) << 12 | (a[2] & 0x3F) << 6 | a[3] & 0x3F;
/* 77 */         for (int j = 0; j < 3; j++) if (a[(j + 1)] >= 0) out.write(0xFF & oneBigNumber >> 8 * (2 - j));
/*    */       }
/* 79 */       return out.toByteArray();
/*    */     } catch (IOException e) {
/* 81 */       throw new Error(e + ": " + e.getMessage());
/*    */     }
/*    */   }
/*    */   
/*    */   private int mapCharToInt(Reader input) throws IOException {
/*    */     int c;
/* 87 */     while ((c = input.read()) != -1) {
/* 88 */       int result = REVERSE_MAPPING[c];
/* 89 */       if (result != 0) return result - 1;
/* 90 */       if (c == 61) return -1;
/*    */     }
/* 92 */     return -1;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/core/util/Base64Encoder.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */