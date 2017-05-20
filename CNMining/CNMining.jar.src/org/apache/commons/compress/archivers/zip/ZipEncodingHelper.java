/*     */ package org.apache.commons.compress.archivers.zip;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.charset.Charset;
/*     */ import java.nio.charset.UnsupportedCharsetException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ abstract class ZipEncodingHelper
/*     */ {
/*     */   private static class SimpleEncodingHolder
/*     */   {
/*     */     private final char[] highChars;
/*     */     private Simple8BitZipEncoding encoding;
/*     */     
/*     */     SimpleEncodingHolder(char[] highChars)
/*     */     {
/*  50 */       this.highChars = highChars;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public synchronized Simple8BitZipEncoding getEncoding()
/*     */     {
/*  58 */       if (this.encoding == null) {
/*  59 */         this.encoding = new Simple8BitZipEncoding(this.highChars);
/*     */       }
/*  61 */       return this.encoding;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  68 */   private static final Map simpleEncodings = new HashMap();
/*     */   
/*  70 */   static { char[] cp437_high_chars = { 'Ç', 'ü', 'é', 'â', 'ä', 'à', 'å', 'ç', 'ê', 'ë', 'è', 'ï', 'î', 'ì', 'Ä', 'Å', 'É', 'æ', 'Æ', 'ô', 'ö', 'ò', 'û', 'ù', 'ÿ', 'Ö', 'Ü', '¢', '£', '¥', '₧', 'ƒ', 'á', 'í', 'ó', 'ú', 'ñ', 'Ñ', 'ª', 'º', '¿', '⌐', '¬', '½', '¼', '¡', '«', '»', '░', '▒', '▓', '│', '┤', '╡', '╢', '╖', '╕', '╣', '║', '╗', '╝', '╜', '╛', '┐', '└', '┴', '┬', '├', '─', '┼', '╞', '╟', '╚', '╔', '╩', '╦', '╠', '═', '╬', '╧', '╨', '╤', '╥', '╙', '╘', '╒', '╓', '╫', '╪', '┘', '┌', '█', '▄', '▌', '▐', '▀', 'α', 'ß', 'Γ', 'π', 'Σ', 'σ', 'µ', 'τ', 'Φ', 'Θ', 'Ω', 'δ', '∞', 'φ', 'ε', '∩', '≡', '±', '≥', '≤', '⌠', '⌡', '÷', '≈', '°', '∙', '·', '√', 'ⁿ', '²', '■', ' ' };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  94 */     SimpleEncodingHolder cp437 = new SimpleEncodingHolder(cp437_high_chars);
/*     */     
/*  96 */     simpleEncodings.put("CP437", cp437);
/*  97 */     simpleEncodings.put("Cp437", cp437);
/*  98 */     simpleEncodings.put("cp437", cp437);
/*  99 */     simpleEncodings.put("IBM437", cp437);
/* 100 */     simpleEncodings.put("ibm437", cp437);
/*     */     
/* 102 */     char[] cp850_high_chars = { 'Ç', 'ü', 'é', 'â', 'ä', 'à', 'å', 'ç', 'ê', 'ë', 'è', 'ï', 'î', 'ì', 'Ä', 'Å', 'É', 'æ', 'Æ', 'ô', 'ö', 'ò', 'û', 'ù', 'ÿ', 'Ö', 'Ü', 'ø', '£', 'Ø', '×', 'ƒ', 'á', 'í', 'ó', 'ú', 'ñ', 'Ñ', 'ª', 'º', '¿', '®', '¬', '½', '¼', '¡', '«', '»', '░', '▒', '▓', '│', '┤', 'Á', 'Â', 'À', '©', '╣', '║', '╗', '╝', '¢', '¥', '┐', '└', '┴', '┬', '├', '─', '┼', 'ã', 'Ã', '╚', '╔', '╩', '╦', '╠', '═', '╬', '¤', 'ð', 'Ð', 'Ê', 'Ë', 'È', 'ı', 'Í', 'Î', 'Ï', '┘', '┌', '█', '▄', '¦', 'Ì', '▀', 'Ó', 'ß', 'Ô', 'Ò', 'õ', 'Õ', 'µ', 'þ', 'Þ', 'Ú', 'Û', 'Ù', 'ý', 'Ý', '¯', '´', '­', '±', '‗', '¾', '¶', '§', '÷', '¸', '°', '¨', '·', '¹', '³', '²', '■', ' ' };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 126 */     SimpleEncodingHolder cp850 = new SimpleEncodingHolder(cp850_high_chars);
/*     */     
/* 128 */     simpleEncodings.put("CP850", cp850);
/* 129 */     simpleEncodings.put("Cp850", cp850);
/* 130 */     simpleEncodings.put("cp850", cp850);
/* 131 */     simpleEncodings.put("IBM850", cp850);
/* 132 */     simpleEncodings.put("ibm850", cp850);
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
/*     */   static ByteBuffer growBuffer(ByteBuffer b, int newCapacity)
/*     */   {
/* 148 */     b.limit(b.position());
/* 149 */     b.rewind();
/*     */     
/* 151 */     int c2 = b.capacity() * 2;
/* 152 */     ByteBuffer on = ByteBuffer.allocate(c2 < newCapacity ? newCapacity : c2);
/*     */     
/* 154 */     on.put(b);
/* 155 */     return on;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 163 */   private static final byte[] HEX_DIGITS = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70 };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   static final String UTF8 = "UTF8";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   static void appendSurrogate(ByteBuffer bb, char c)
/*     */   {
/* 178 */     bb.put((byte)37);
/* 179 */     bb.put((byte)85);
/*     */     
/* 181 */     bb.put(HEX_DIGITS[(c >> '\f' & 0xF)]);
/* 182 */     bb.put(HEX_DIGITS[(c >> '\b' & 0xF)]);
/* 183 */     bb.put(HEX_DIGITS[(c >> '\004' & 0xF)]);
/* 184 */     bb.put(HEX_DIGITS[(c & 0xF)]);
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
/* 196 */   static final ZipEncoding UTF8_ZIP_ENCODING = new FallbackZipEncoding("UTF8");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   static ZipEncoding getZipEncoding(String name)
/*     */   {
/* 208 */     if (isUTF8(name)) {
/* 209 */       return UTF8_ZIP_ENCODING;
/*     */     }
/*     */     
/* 212 */     if (name == null) {
/* 213 */       return new FallbackZipEncoding();
/*     */     }
/*     */     
/* 216 */     SimpleEncodingHolder h = (SimpleEncodingHolder)simpleEncodings.get(name);
/*     */     
/*     */ 
/* 219 */     if (h != null) {
/* 220 */       return h.getEncoding();
/*     */     }
/*     */     
/*     */     try
/*     */     {
/* 225 */       Charset cs = Charset.forName(name);
/* 226 */       return new NioZipEncoding(cs);
/*     */     }
/*     */     catch (UnsupportedCharsetException e) {}
/* 229 */     return new FallbackZipEncoding(name);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   static boolean isUTF8(String encoding)
/*     */   {
/* 238 */     if (encoding == null)
/*     */     {
/* 240 */       encoding = System.getProperty("file.encoding");
/*     */     }
/* 242 */     return ("UTF8".equalsIgnoreCase(encoding)) || ("utf-8".equalsIgnoreCase(encoding));
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/compress/archivers/zip/ZipEncodingHelper.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       0.7.1
 */