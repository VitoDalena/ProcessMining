/*     */ package org.apache.commons.compress.archivers.zip;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.nio.ByteBuffer;
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
/*     */ class Simple8BitZipEncoding
/*     */   implements ZipEncoding
/*     */ {
/*     */   private final char[] highChars;
/*     */   private final List reverseMapping;
/*     */   
/*     */   private static final class Simple8BitChar
/*     */     implements Comparable
/*     */   {
/*     */     public final char unicode;
/*     */     public final byte code;
/*     */     
/*     */     Simple8BitChar(byte code, char unicode)
/*     */     {
/*  58 */       this.code = code;
/*  59 */       this.unicode = unicode;
/*     */     }
/*     */     
/*     */     public int compareTo(Object o) {
/*  63 */       Simple8BitChar a = (Simple8BitChar)o;
/*     */       
/*  65 */       return this.unicode - a.unicode;
/*     */     }
/*     */     
/*     */     public String toString() {
/*  69 */       return "0x" + Integer.toHexString(0xFFFF & this.unicode) + "->0x" + Integer.toHexString(0xFF & this.code);
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
/*     */ 
/*     */   public Simple8BitZipEncoding(char[] highChars)
/*     */   {
/*  92 */     this.highChars = ((char[])highChars.clone());
/*  93 */     List temp = new ArrayList(this.highChars.length);
/*     */     
/*  95 */     byte code = Byte.MAX_VALUE;
/*     */     
/*  97 */     for (int i = 0; i < this.highChars.length; i++) {
/*  98 */       code = (byte)(code + 1);temp.add(new Simple8BitChar(code, this.highChars[i]));
/*     */     }
/*     */     
/* 101 */     Collections.sort(temp);
/* 102 */     this.reverseMapping = Collections.unmodifiableList(temp);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public char decodeByte(byte b)
/*     */   {
/* 113 */     if (b >= 0) {
/* 114 */       return (char)b;
/*     */     }
/*     */     
/*     */ 
/* 118 */     return this.highChars[(128 + b)];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean canEncodeChar(char c)
/*     */   {
/* 127 */     if ((c >= 0) && (c < '')) {
/* 128 */       return true;
/*     */     }
/*     */     
/* 131 */     Simple8BitChar r = encodeHighChar(c);
/* 132 */     return r != null;
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
/*     */   public boolean pushEncodedChar(ByteBuffer bb, char c)
/*     */   {
/* 146 */     if ((c >= 0) && (c < '')) {
/* 147 */       bb.put((byte)c);
/* 148 */       return true;
/*     */     }
/*     */     
/* 151 */     Simple8BitChar r = encodeHighChar(c);
/* 152 */     if (r == null) {
/* 153 */       return false;
/*     */     }
/* 155 */     bb.put(r.code);
/* 156 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Simple8BitChar encodeHighChar(char c)
/*     */   {
/* 168 */     int i0 = 0;
/* 169 */     int i1 = this.reverseMapping.size();
/*     */     
/* 171 */     while (i1 > i0)
/*     */     {
/* 173 */       int i = i0 + (i1 - i0) / 2;
/*     */       
/* 175 */       Simple8BitChar m = (Simple8BitChar)this.reverseMapping.get(i);
/*     */       
/* 177 */       if (m.unicode == c) {
/* 178 */         return m;
/*     */       }
/*     */       
/* 181 */       if (m.unicode < c) {
/* 182 */         i0 = i + 1;
/*     */       } else {
/* 184 */         i1 = i;
/*     */       }
/*     */     }
/*     */     
/* 188 */     if (i0 >= this.reverseMapping.size()) {
/* 189 */       return null;
/*     */     }
/*     */     
/* 192 */     Simple8BitChar r = (Simple8BitChar)this.reverseMapping.get(i0);
/*     */     
/* 194 */     if (r.unicode != c) {
/* 195 */       return null;
/*     */     }
/*     */     
/* 198 */     return r;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean canEncode(String name)
/*     */   {
/* 207 */     for (int i = 0; i < name.length(); i++)
/*     */     {
/* 209 */       char c = name.charAt(i);
/*     */       
/* 211 */       if (!canEncodeChar(c)) {
/* 212 */         return false;
/*     */       }
/*     */     }
/*     */     
/* 216 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ByteBuffer encode(String name)
/*     */   {
/* 224 */     ByteBuffer out = ByteBuffer.allocate(name.length() + 6 + (name.length() + 1) / 2);
/*     */     
/*     */ 
/* 227 */     for (int i = 0; i < name.length(); i++)
/*     */     {
/* 229 */       char c = name.charAt(i);
/*     */       
/* 231 */       if (out.remaining() < 6) {
/* 232 */         out = ZipEncodingHelper.growBuffer(out, out.position() + 6);
/*     */       }
/*     */       
/* 235 */       if (!pushEncodedChar(out, c))
/*     */       {
/* 237 */         ZipEncodingHelper.appendSurrogate(out, c);
/*     */       }
/*     */     }
/*     */     
/* 241 */     out.limit(out.position());
/* 242 */     out.rewind();
/* 243 */     return out;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String decode(byte[] data)
/*     */     throws IOException
/*     */   {
/* 251 */     char[] ret = new char[data.length];
/*     */     
/* 253 */     for (int i = 0; i < data.length; i++) {
/* 254 */       ret[i] = decodeByte(data[i]);
/*     */     }
/*     */     
/* 257 */     return new String(ret);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/compress/archivers/zip/Simple8BitZipEncoding.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       0.7.1
 */