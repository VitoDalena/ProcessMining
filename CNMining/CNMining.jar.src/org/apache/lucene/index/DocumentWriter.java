/*     */ package org.apache.lucene.index;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
/*     */ import java.io.StringReader;
/*     */ import java.util.Arrays;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import org.apache.lucene.analysis.Analyzer;
/*     */ import org.apache.lucene.analysis.Token;
/*     */ import org.apache.lucene.analysis.TokenStream;
/*     */ import org.apache.lucene.document.Document;
/*     */ import org.apache.lucene.document.Field;
/*     */ import org.apache.lucene.search.Similarity;
/*     */ import org.apache.lucene.store.Directory;
/*     */ import org.apache.lucene.store.OutputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class DocumentWriter
/*     */ {
/*     */   private Analyzer analyzer;
/*     */   private Directory directory;
/*     */   private Similarity similarity;
/*     */   private FieldInfos fieldInfos;
/*     */   private int maxFieldLength;
/*     */   
/*     */   DocumentWriter(Directory directory, Analyzer analyzer, Similarity similarity, int maxFieldLength)
/*     */   {
/*  51 */     this.directory = directory;
/*  52 */     this.analyzer = analyzer;
/*  53 */     this.similarity = similarity;
/*  54 */     this.maxFieldLength = maxFieldLength;
/*     */   }
/*     */   
/*     */   final void addDocument(String segment, Document doc)
/*     */     throws IOException
/*     */   {
/*  60 */     this.fieldInfos = new FieldInfos();
/*  61 */     this.fieldInfos.add(doc);
/*  62 */     this.fieldInfos.write(this.directory, segment + ".fnm");
/*     */     
/*     */ 
/*  65 */     FieldsWriter fieldsWriter = new FieldsWriter(this.directory, segment, this.fieldInfos);
/*     */     try
/*     */     {
/*  68 */       fieldsWriter.addDocument(doc);
/*     */     } finally {
/*  70 */       fieldsWriter.close();
/*     */     }
/*     */     
/*     */ 
/*  74 */     this.postingTable.clear();
/*  75 */     this.fieldLengths = new int[this.fieldInfos.size()];
/*  76 */     this.fieldPositions = new int[this.fieldInfos.size()];
/*     */     
/*  78 */     this.fieldBoosts = new float[this.fieldInfos.size()];
/*  79 */     Arrays.fill(this.fieldBoosts, doc.getBoost());
/*     */     
/*  81 */     invertDocument(doc);
/*     */     
/*     */ 
/*  84 */     Posting[] postings = sortPostingTable();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 100 */     writePostings(postings, segment);
/*     */     
/*     */ 
/* 103 */     writeNorms(doc, segment);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 109 */   private final Hashtable postingTable = new Hashtable();
/*     */   private int[] fieldLengths;
/*     */   private int[] fieldPositions;
/*     */   private float[] fieldBoosts;
/*     */   
/*     */   private final void invertDocument(Document doc)
/*     */     throws IOException
/*     */   {
/* 117 */     Enumeration fields = doc.fields();
/* 118 */     while (fields.hasMoreElements()) {
/* 119 */       Field field = (Field)fields.nextElement();
/* 120 */       String fieldName = field.name();
/* 121 */       int fieldNumber = this.fieldInfos.fieldNumber(fieldName);
/*     */       
/* 123 */       int length = this.fieldLengths[fieldNumber];
/* 124 */       int position = this.fieldPositions[fieldNumber];
/*     */       
/* 126 */       if (field.isIndexed()) {
/* 127 */         if (!field.isTokenized()) {
/* 128 */           addPosition(fieldName, field.stringValue(), position++);
/* 129 */           length++;
/*     */         } else { Reader reader;
/*     */           Reader reader;
/* 132 */           if (field.readerValue() != null) {
/* 133 */             reader = field.readerValue();
/* 134 */           } else if (field.stringValue() != null) {
/* 135 */             reader = new StringReader(field.stringValue());
/*     */           } else {
/* 137 */             throw new IllegalArgumentException("field must have either String or Reader value");
/*     */           }
/*     */           
/*     */ 
/* 141 */           TokenStream stream = this.analyzer.tokenStream(fieldName, reader);
/*     */           try {
/* 143 */             for (Token t = stream.next(); t != null; t = stream.next()) {
/* 144 */               position += t.getPositionIncrement() - 1;
/* 145 */               addPosition(fieldName, t.termText(), position++);
/* 146 */               length++; if (length > this.maxFieldLength)
/*     */                 break;
/*     */             }
/* 149 */           } finally { stream.close();
/*     */           }
/*     */         }
/*     */         
/* 153 */         this.fieldLengths[fieldNumber] = length;
/* 154 */         this.fieldPositions[fieldNumber] = position;
/* 155 */         this.fieldBoosts[fieldNumber] *= field.getBoost();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/* 160 */   private final Term termBuffer = new Term("", "");
/*     */   
/*     */   private final void addPosition(String field, String text, int position) {
/* 163 */     this.termBuffer.set(field, text);
/* 164 */     Posting ti = (Posting)this.postingTable.get(this.termBuffer);
/* 165 */     if (ti != null) {
/* 166 */       int freq = ti.freq;
/* 167 */       if (ti.positions.length == freq) {
/* 168 */         int[] newPositions = new int[freq * 2];
/* 169 */         int[] positions = ti.positions;
/* 170 */         for (int i = 0; i < freq; i++)
/* 171 */           newPositions[i] = positions[i];
/* 172 */         ti.positions = newPositions;
/*     */       }
/* 174 */       ti.positions[freq] = position;
/* 175 */       ti.freq = (freq + 1);
/*     */     } else {
/* 177 */       Term term = new Term(field, text, false);
/* 178 */       this.postingTable.put(term, new Posting(term, position));
/*     */     }
/*     */   }
/*     */   
/*     */   private final Posting[] sortPostingTable()
/*     */   {
/* 184 */     Posting[] array = new Posting[this.postingTable.size()];
/* 185 */     Enumeration postings = this.postingTable.elements();
/* 186 */     for (int i = 0; postings.hasMoreElements(); i++) {
/* 187 */       array[i] = ((Posting)postings.nextElement());
/*     */     }
/*     */     
/* 190 */     quickSort(array, 0, array.length - 1);
/*     */     
/* 192 */     return array;
/*     */   }
/*     */   
/*     */   private static final void quickSort(Posting[] postings, int lo, int hi) {
/* 196 */     if (lo >= hi) {
/* 197 */       return;
/*     */     }
/* 199 */     int mid = (lo + hi) / 2;
/*     */     
/* 201 */     if (postings[lo].term.compareTo(postings[mid].term) > 0) {
/* 202 */       Posting tmp = postings[lo];
/* 203 */       postings[lo] = postings[mid];
/* 204 */       postings[mid] = tmp;
/*     */     }
/*     */     
/* 207 */     if (postings[mid].term.compareTo(postings[hi].term) > 0) {
/* 208 */       Posting tmp = postings[mid];
/* 209 */       postings[mid] = postings[hi];
/* 210 */       postings[hi] = tmp;
/*     */       
/* 212 */       if (postings[lo].term.compareTo(postings[mid].term) > 0) {
/* 213 */         Posting tmp2 = postings[lo];
/* 214 */         postings[lo] = postings[mid];
/* 215 */         postings[mid] = tmp2;
/*     */       }
/*     */     }
/*     */     
/* 219 */     int left = lo + 1;
/* 220 */     int right = hi - 1;
/*     */     
/* 222 */     if (left >= right) {
/* 223 */       return;
/*     */     }
/* 225 */     Term partition = postings[mid].term;
/*     */     for (;;)
/*     */     {
/* 228 */       if (postings[right].term.compareTo(partition) > 0) {
/* 229 */         right--;
/*     */       } else {
/* 231 */         while ((left < right) && (postings[left].term.compareTo(partition) <= 0)) {
/* 232 */           left++;
/*     */         }
/* 234 */         if (left >= right) break;
/* 235 */         Posting tmp = postings[left];
/* 236 */         postings[left] = postings[right];
/* 237 */         postings[right] = tmp;
/* 238 */         right--;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 244 */     quickSort(postings, lo, left);
/* 245 */     quickSort(postings, left + 1, hi);
/*     */   }
/*     */   
/*     */   private final void writePostings(Posting[] postings, String segment) throws IOException
/*     */   {
/* 250 */     OutputStream freq = null;OutputStream prox = null;
/* 251 */     TermInfosWriter tis = null;
/* 252 */     TermVectorsWriter termVectorWriter = null;
/*     */     try
/*     */     {
/* 255 */       freq = this.directory.createFile(segment + ".frq");
/* 256 */       prox = this.directory.createFile(segment + ".prx");
/* 257 */       tis = new TermInfosWriter(this.directory, segment, this.fieldInfos);
/* 258 */       TermInfo ti = new TermInfo();
/* 259 */       String currentField = null;
/*     */       
/* 261 */       for (int i = 0; i < postings.length; i++) {
/* 262 */         Posting posting = postings[i];
/*     */         
/*     */ 
/* 265 */         ti.set(1, freq.getFilePointer(), prox.getFilePointer(), -1);
/* 266 */         tis.add(posting.term, ti);
/*     */         
/*     */ 
/* 269 */         int postingFreq = posting.freq;
/* 270 */         if (postingFreq == 1) {
/* 271 */           freq.writeVInt(1);
/*     */         } else {
/* 273 */           freq.writeVInt(0);
/* 274 */           freq.writeVInt(postingFreq);
/*     */         }
/*     */         
/* 277 */         int lastPosition = 0;
/* 278 */         int[] positions = posting.positions;
/* 279 */         for (int j = 0; j < postingFreq; j++) {
/* 280 */           int position = positions[j];
/* 281 */           prox.writeVInt(position - lastPosition);
/* 282 */           lastPosition = position;
/*     */         }
/*     */         
/* 285 */         String termField = posting.term.field();
/* 286 */         if (currentField != termField)
/*     */         {
/* 288 */           currentField = termField;
/* 289 */           FieldInfo fi = this.fieldInfos.fieldInfo(currentField);
/* 290 */           if (fi.storeTermVector) {
/* 291 */             if (termVectorWriter == null) {
/* 292 */               termVectorWriter = new TermVectorsWriter(this.directory, segment, this.fieldInfos);
/*     */               
/* 294 */               termVectorWriter.openDocument();
/*     */             }
/* 296 */             termVectorWriter.openField(currentField);
/* 297 */           } else if (termVectorWriter != null) {
/* 298 */             termVectorWriter.closeField();
/*     */           }
/*     */         }
/* 301 */         if ((termVectorWriter != null) && (termVectorWriter.isFieldOpen())) {
/* 302 */           termVectorWriter.addTerm(posting.term.text(), postingFreq);
/*     */         }
/*     */       }
/* 305 */       if (termVectorWriter != null) {
/* 306 */         termVectorWriter.closeDocument();
/*     */       }
/*     */     }
/*     */     finally {
/* 310 */       IOException keep = null;
/* 311 */       if (freq != null) try { freq.close(); } catch (IOException e) { if (keep == null) keep = e; }
/* 312 */       if (prox != null) try { prox.close(); } catch (IOException e) { if (keep == null) keep = e; }
/* 313 */       if (tis != null) try { tis.close(); } catch (IOException e) { if (keep == null) keep = e; }
/* 314 */       if (termVectorWriter != null) try { termVectorWriter.close(); } catch (IOException e) { if (keep == null) keep = e; }
/* 315 */       if (keep != null) throw ((IOException)keep.fillInStackTrace());
/*     */     }
/*     */   }
/*     */   
/*     */   private final void writeNorms(Document doc, String segment) throws IOException {
/* 320 */     for (int n = 0; n < this.fieldInfos.size(); n++) {
/* 321 */       FieldInfo fi = this.fieldInfos.fieldInfo(n);
/* 322 */       if (fi.isIndexed) {
/* 323 */         float norm = this.fieldBoosts[n] * this.similarity.lengthNorm(fi.name, this.fieldLengths[n]);
/* 324 */         OutputStream norms = this.directory.createFile(segment + ".f" + n);
/*     */         try {
/* 326 */           norms.writeByte(Similarity.encodeNorm(norm));
/*     */         } finally {
/* 328 */           norms.close();
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/index/DocumentWriter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */