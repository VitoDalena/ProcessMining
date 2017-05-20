/*     */ package org.apache.commons.compress.archivers.zip;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.zip.ZipException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExtraFieldUtils
/*     */ {
/*     */   private static final int WORD = 4;
/*  41 */   private static final Map implementations = new HashMap();
/*  42 */   static { register(AsiExtraField.class);
/*  43 */     register(JarMarker.class);
/*  44 */     register(UnicodePathExtraField.class);
/*  45 */     register(UnicodeCommentExtraField.class);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void register(Class c)
/*     */   {
/*     */     try
/*     */     {
/*  57 */       ZipExtraField ze = (ZipExtraField)c.newInstance();
/*  58 */       implementations.put(ze.getHeaderId(), c);
/*     */     } catch (ClassCastException cc) {
/*  60 */       throw new RuntimeException(c + " doesn't implement ZipExtraField");
/*     */     } catch (InstantiationException ie) {
/*  62 */       throw new RuntimeException(c + " is not a concrete class");
/*     */     } catch (IllegalAccessException ie) {
/*  64 */       throw new RuntimeException(c + "'s no-arg constructor is not public");
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
/*     */   public static ZipExtraField createExtraField(ZipShort headerId)
/*     */     throws InstantiationException, IllegalAccessException
/*     */   {
/*  78 */     Class c = (Class)implementations.get(headerId);
/*  79 */     if (c != null) {
/*  80 */       return (ZipExtraField)c.newInstance();
/*     */     }
/*  82 */     UnrecognizedExtraField u = new UnrecognizedExtraField();
/*  83 */     u.setHeaderId(headerId);
/*  84 */     return u;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ZipExtraField[] parse(byte[] data)
/*     */     throws ZipException
/*     */   {
/*  95 */     return parse(data, true);
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
/*     */   public static ZipExtraField[] parse(byte[] data, boolean local)
/*     */     throws ZipException
/*     */   {
/* 109 */     List v = new ArrayList();
/* 110 */     int start = 0;
/* 111 */     while (start <= data.length - 4) {
/* 112 */       ZipShort headerId = new ZipShort(data, start);
/* 113 */       int length = new ZipShort(data, start + 2).getValue();
/* 114 */       if (start + 4 + length > data.length) {
/* 115 */         throw new ZipException("data starting at " + start + " is in unknown format");
/*     */       }
/*     */       try
/*     */       {
/* 119 */         ZipExtraField ze = createExtraField(headerId);
/* 120 */         if (local) {
/* 121 */           ze.parseFromLocalFileData(data, start + 4, length);
/*     */         } else {
/* 123 */           ze.parseFromCentralDirectoryData(data, start + 4, length);
/*     */         }
/*     */         
/* 126 */         v.add(ze);
/*     */       } catch (InstantiationException ie) {
/* 128 */         throw new ZipException(ie.getMessage());
/*     */       } catch (IllegalAccessException iae) {
/* 130 */         throw new ZipException(iae.getMessage());
/*     */       }
/* 132 */       start += length + 4;
/*     */     }
/*     */     
/* 135 */     ZipExtraField[] result = new ZipExtraField[v.size()];
/* 136 */     return (ZipExtraField[])v.toArray(result);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static byte[] mergeLocalFileDataData(ZipExtraField[] data)
/*     */   {
/* 145 */     int sum = 4 * data.length;
/* 146 */     for (int i = 0; i < data.length; i++) {
/* 147 */       sum += data[i].getLocalFileDataLength().getValue();
/*     */     }
/* 149 */     byte[] result = new byte[sum];
/* 150 */     int start = 0;
/* 151 */     for (int i = 0; i < data.length; i++) {
/* 152 */       System.arraycopy(data[i].getHeaderId().getBytes(), 0, result, start, 2);
/*     */       
/* 154 */       System.arraycopy(data[i].getLocalFileDataLength().getBytes(), 0, result, start + 2, 2);
/*     */       
/* 156 */       byte[] local = data[i].getLocalFileDataData();
/* 157 */       System.arraycopy(local, 0, result, start + 4, local.length);
/* 158 */       start += local.length + 4;
/*     */     }
/* 160 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static byte[] mergeCentralDirectoryData(ZipExtraField[] data)
/*     */   {
/* 169 */     int sum = 4 * data.length;
/* 170 */     for (int i = 0; i < data.length; i++) {
/* 171 */       sum += data[i].getCentralDirectoryLength().getValue();
/*     */     }
/* 173 */     byte[] result = new byte[sum];
/* 174 */     int start = 0;
/* 175 */     for (int i = 0; i < data.length; i++) {
/* 176 */       System.arraycopy(data[i].getHeaderId().getBytes(), 0, result, start, 2);
/*     */       
/* 178 */       System.arraycopy(data[i].getCentralDirectoryLength().getBytes(), 0, result, start + 2, 2);
/*     */       
/* 180 */       byte[] local = data[i].getCentralDirectoryData();
/* 181 */       System.arraycopy(local, 0, result, start + 4, local.length);
/* 182 */       start += local.length + 4;
/*     */     }
/* 184 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/compress/archivers/zip/ExtraFieldUtils.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       0.7.1
 */