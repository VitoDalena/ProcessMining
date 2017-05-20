/*     */ package org.apache.lucene.index;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import org.apache.lucene.store.Directory;
/*     */ import org.apache.lucene.store.InputStream;
/*     */ 
/*     */ 
/*     */ class TermVectorsReader
/*     */ {
/*     */   private FieldInfos fieldInfos;
/*     */   private InputStream tvx;
/*     */   private InputStream tvd;
/*     */   private InputStream tvf;
/*     */   private int size;
/*     */   
/*     */   TermVectorsReader(Directory d, String segment, FieldInfos fieldInfos)
/*     */     throws IOException
/*     */   {
/*  20 */     if (d.fileExists(segment + ".tvx")) {
/*  21 */       this.tvx = d.openFile(segment + ".tvx");
/*  22 */       checkValidFormat(this.tvx);
/*  23 */       this.tvd = d.openFile(segment + ".tvd");
/*  24 */       checkValidFormat(this.tvd);
/*  25 */       this.tvf = d.openFile(segment + ".tvf");
/*  26 */       checkValidFormat(this.tvf);
/*  27 */       this.size = ((int)this.tvx.length() / 8);
/*     */     }
/*     */     
/*  30 */     this.fieldInfos = fieldInfos;
/*     */   }
/*     */   
/*     */   private void checkValidFormat(InputStream in) throws IOException
/*     */   {
/*  35 */     int format = in.readInt();
/*  36 */     if (format > 1)
/*     */     {
/*  38 */       throw new IOException("Incompatible format version: " + format + " expected " + 1 + " or less");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   synchronized void close()
/*     */     throws IOException
/*     */   {
/*  47 */     if (this.tvx != null) this.tvx.close();
/*  48 */     if (this.tvd != null) this.tvd.close();
/*  49 */     if (this.tvf != null) { this.tvf.close();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   int size()
/*     */   {
/*  57 */     return this.size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   synchronized TermFreqVector get(int docNum, String field)
/*     */   {
/*  68 */     int fieldNumber = this.fieldInfos.fieldNumber(field);
/*  69 */     TermFreqVector result = null;
/*  70 */     if (this.tvx != null)
/*     */     {
/*     */       try
/*     */       {
/*     */ 
/*  75 */         this.tvx.seek(docNum * 8L + 4L);
/*     */         
/*  77 */         long position = this.tvx.readLong();
/*     */         
/*  79 */         this.tvd.seek(position);
/*  80 */         int fieldCount = this.tvd.readVInt();
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*  85 */         int number = 0;
/*  86 */         int found = -1;
/*  87 */         for (int i = 0; i < fieldCount; i++) {
/*  88 */           number += this.tvd.readVInt();
/*  89 */           if (number == fieldNumber) { found = i;
/*     */           }
/*     */         }
/*     */         
/*  93 */         if (found != -1)
/*     */         {
/*  95 */           position = 0L;
/*  96 */           for (int i = 0; i <= found; i++)
/*     */           {
/*  98 */             position += this.tvd.readVLong();
/*     */           }
/* 100 */           result = readTermVector(field, position);
/*     */ 
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */       }
/*     */       catch (Exception e) {}
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 112 */       System.out.println("No tvx file");
/*     */     }
/* 114 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */   synchronized TermFreqVector[] get(int docNum)
/*     */   {
/* 120 */     TermFreqVector[] result = null;
/*     */     
/* 122 */     if (this.tvx != null) {
/*     */       try
/*     */       {
/* 125 */         this.tvx.seek(docNum * 8L + 4L);
/* 126 */         long position = this.tvx.readLong();
/*     */         
/* 128 */         this.tvd.seek(position);
/* 129 */         int fieldCount = this.tvd.readVInt();
/*     */         
/*     */ 
/* 132 */         if (fieldCount != 0) {
/* 133 */           int number = 0;
/* 134 */           String[] fields = new String[fieldCount];
/*     */           
/* 136 */           for (int i = 0; i < fieldCount; i++) {
/* 137 */             number += this.tvd.readVInt();
/* 138 */             fields[i] = this.fieldInfos.fieldName(number);
/*     */           }
/*     */           
/*     */ 
/* 142 */           position = 0L;
/* 143 */           long[] tvfPointers = new long[fieldCount];
/* 144 */           for (int i = 0; i < fieldCount; i++) {
/* 145 */             position += this.tvd.readVLong();
/* 146 */             tvfPointers[i] = position;
/*     */           }
/*     */           
/* 149 */           result = readTermVectors(fields, tvfPointers);
/*     */         }
/*     */       } catch (IOException e) {
/* 152 */         e.printStackTrace();
/*     */       }
/*     */       
/*     */     }
/*     */     else {
/* 157 */       System.out.println("No tvx file");
/*     */     }
/* 159 */     return result;
/*     */   }
/*     */   
/*     */   private SegmentTermVector[] readTermVectors(String[] fields, long[] tvfPointers)
/*     */     throws IOException
/*     */   {
/* 165 */     SegmentTermVector[] res = new SegmentTermVector[fields.length];
/* 166 */     for (int i = 0; i < fields.length; i++) {
/* 167 */       res[i] = readTermVector(fields[i], tvfPointers[i]);
/*     */     }
/* 169 */     return res;
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
/*     */   private SegmentTermVector readTermVector(String field, long tvfPointer)
/*     */     throws IOException
/*     */   {
/* 184 */     this.tvf.seek(tvfPointer);
/*     */     
/* 186 */     int numTerms = this.tvf.readVInt();
/*     */     
/*     */ 
/* 189 */     if (numTerms == 0) { return new SegmentTermVector(field, null, null);
/*     */     }
/* 191 */     int length = numTerms + this.tvf.readVInt();
/*     */     
/* 193 */     String[] terms = new String[numTerms];
/*     */     
/* 195 */     int[] termFreqs = new int[numTerms];
/*     */     
/* 197 */     int start = 0;
/* 198 */     int deltaLength = 0;
/* 199 */     int totalLength = 0;
/* 200 */     char[] buffer = new char[0];
/* 201 */     String previousString = "";
/* 202 */     for (int i = 0; i < numTerms; i++) {
/* 203 */       start = this.tvf.readVInt();
/* 204 */       deltaLength = this.tvf.readVInt();
/* 205 */       totalLength = start + deltaLength;
/* 206 */       if (buffer.length < totalLength)
/*     */       {
/* 208 */         buffer = new char[totalLength];
/* 209 */         for (int j = 0; j < previousString.length(); j++)
/* 210 */           buffer[j] = previousString.charAt(j);
/*     */       }
/* 212 */       this.tvf.readChars(buffer, start, deltaLength);
/* 213 */       terms[i] = new String(buffer, 0, totalLength);
/* 214 */       previousString = terms[i];
/* 215 */       termFreqs[i] = this.tvf.readVInt();
/*     */     }
/* 217 */     SegmentTermVector tv = new SegmentTermVector(field, terms, termFreqs);
/* 218 */     return tv;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/index/TermVectorsReader.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */