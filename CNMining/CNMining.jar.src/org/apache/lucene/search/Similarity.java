/*     */ package org.apache.lucene.search;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.apache.lucene.index.Term;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Similarity
/*     */ {
/*  66 */   private static Similarity defaultImpl = new DefaultSimilarity();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void setDefault(Similarity similarity)
/*     */   {
/*  75 */     defaultImpl = similarity;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Similarity getDefault()
/*     */   {
/*  87 */     return defaultImpl;
/*     */   }
/*     */   
/*     */ 
/*  91 */   private static final float[] NORM_TABLE = new float['Ā'];
/*     */   
/*     */   static {
/*  94 */     for (int i = 0; i < 256; i++) {
/*  95 */       NORM_TABLE[i] = byteToFloat((byte)i);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static float decodeNorm(byte b)
/*     */   {
/* 102 */     return NORM_TABLE[(b & 0xFF)];
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static byte encodeNorm(float f)
/*     */   {
/* 153 */     return floatToByte(f);
/*     */   }
/*     */   
/*     */   private static float byteToFloat(byte b) {
/* 157 */     if (b == 0)
/* 158 */       return 0.0F;
/* 159 */     int mantissa = b & 0x7;
/* 160 */     int exponent = b >> 3 & 0x1F;
/* 161 */     int bits = exponent + 48 << 24 | mantissa << 21;
/* 162 */     return Float.intBitsToFloat(bits);
/*     */   }
/*     */   
/*     */   private static byte floatToByte(float f) {
/* 166 */     if (f < 0.0F) {
/* 167 */       f = 0.0F;
/*     */     }
/* 169 */     if (f == 0.0F) {
/* 170 */       return 0;
/*     */     }
/* 172 */     int bits = Float.floatToIntBits(f);
/* 173 */     int mantissa = (bits & 0xFFFFFF) >> 21;
/* 174 */     int exponent = (bits >> 24 & 0x7F) - 63 + 15;
/*     */     
/* 176 */     if (exponent > 31) {
/* 177 */       exponent = 31;
/* 178 */       mantissa = 7;
/*     */     }
/*     */     
/* 181 */     if (exponent < 0) {
/* 182 */       exponent = 0;
/* 183 */       mantissa = 1;
/*     */     }
/*     */     
/* 186 */     return (byte)(exponent << 3 | mantissa);
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
/*     */   public float tf(int freq)
/*     */   {
/* 206 */     return tf(freq);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float idf(Term term, Searcher searcher)
/*     */     throws IOException
/*     */   {
/* 255 */     return idf(searcher.docFreq(term), searcher.maxDoc());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float idf(Collection terms, Searcher searcher)
/*     */     throws IOException
/*     */   {
/* 268 */     float idf = 0.0F;
/* 269 */     Iterator i = terms.iterator();
/* 270 */     while (i.hasNext()) {
/* 271 */       idf += idf((Term)i.next(), searcher);
/*     */     }
/* 273 */     return idf;
/*     */   }
/*     */   
/*     */   public abstract float lengthNorm(String paramString, int paramInt);
/*     */   
/*     */   public abstract float queryNorm(float paramFloat);
/*     */   
/*     */   public abstract float sloppyFreq(int paramInt);
/*     */   
/*     */   public abstract float tf(float paramFloat);
/*     */   
/*     */   public abstract float idf(int paramInt1, int paramInt2);
/*     */   
/*     */   public abstract float coord(int paramInt1, int paramInt2);
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/Similarity.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */