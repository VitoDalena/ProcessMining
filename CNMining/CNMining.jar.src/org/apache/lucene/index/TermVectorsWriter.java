/*     */ package org.apache.lucene.index;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Vector;
/*     */ import org.apache.lucene.store.Directory;
/*     */ import org.apache.lucene.store.OutputStream;
/*     */ import org.apache.lucene.util.StringHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class TermVectorsWriter
/*     */ {
/*  42 */   private OutputStream tvx = null; private OutputStream tvd = null; private OutputStream tvf = null;
/*  43 */   private Vector fields = null;
/*  44 */   private Vector terms = null;
/*     */   
/*     */ 
/*  47 */   private TVField currentField = null;
/*  48 */   private long currentDocPointer = -1L;
/*     */   
/*     */   public static final int FORMAT_VERSION = 1;
/*     */   
/*     */   public static final int FORMAT_SIZE = 4;
/*     */   public static final String TVX_EXTENSION = ".tvx";
/*     */   public static final String TVD_EXTENSION = ".tvd";
/*     */   public static final String TVF_EXTENSION = ".tvf";
/*     */   private FieldInfos fieldInfos;
/*     */   
/*     */   public TermVectorsWriter(Directory directory, String segment, FieldInfos fieldInfos)
/*     */     throws IOException
/*     */   {
/*  61 */     this.tvx = directory.createFile(segment + ".tvx");
/*  62 */     this.tvx.writeInt(1);
/*  63 */     this.tvd = directory.createFile(segment + ".tvd");
/*  64 */     this.tvd.writeInt(1);
/*  65 */     this.tvf = directory.createFile(segment + ".tvf");
/*  66 */     this.tvf.writeInt(1);
/*     */     
/*  68 */     this.fieldInfos = fieldInfos;
/*  69 */     this.fields = new Vector(fieldInfos.size());
/*  70 */     this.terms = new Vector();
/*     */   }
/*     */   
/*     */   public final void openDocument()
/*     */     throws IOException
/*     */   {
/*  76 */     closeDocument();
/*     */     
/*  78 */     this.currentDocPointer = this.tvd.getFilePointer();
/*     */   }
/*     */   
/*     */   public final void closeDocument()
/*     */     throws IOException
/*     */   {
/*  84 */     if (isDocumentOpen()) {
/*  85 */       closeField();
/*  86 */       writeDoc();
/*  87 */       this.fields.clear();
/*  88 */       this.currentDocPointer = -1L;
/*     */     }
/*     */   }
/*     */   
/*     */   public final boolean isDocumentOpen()
/*     */   {
/*  94 */     return this.currentDocPointer != -1L;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final void openField(String field)
/*     */     throws IOException
/*     */   {
/* 105 */     if (!isDocumentOpen()) { throw new IllegalStateException("Cannot open field when no document is open.");
/*     */     }
/* 107 */     closeField();
/* 108 */     this.currentField = new TVField(this.fieldInfos.fieldNumber(field));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public final void closeField()
/*     */     throws IOException
/*     */   {
/* 116 */     if (isFieldOpen())
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 122 */       writeField();
/* 123 */       this.fields.add(this.currentField);
/* 124 */       this.terms.clear();
/* 125 */       this.currentField = null;
/*     */     }
/*     */   }
/*     */   
/*     */   public final boolean isFieldOpen()
/*     */   {
/* 131 */     return this.currentField != null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final void addTerm(String termText, int freq)
/*     */   {
/* 141 */     if (!isDocumentOpen()) throw new IllegalStateException("Cannot add terms when document is not open");
/* 142 */     if (!isFieldOpen()) { throw new IllegalStateException("Cannot add terms when field is not open");
/*     */     }
/* 144 */     addTermInternal(termText, freq);
/*     */   }
/*     */   
/*     */   private final void addTermInternal(String termText, int freq) {
/* 148 */     this.currentField.length += freq;
/* 149 */     TVTerm term = new TVTerm(null);
/* 150 */     term.termText = termText;
/* 151 */     term.freq = freq;
/* 152 */     this.terms.add(term);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public final void addVectors(TermFreqVector[] vectors)
/*     */     throws IOException
/*     */   {
/* 160 */     if (!isDocumentOpen()) throw new IllegalStateException("Cannot add term vectors when document is not open");
/* 161 */     if (isFieldOpen()) { throw new IllegalStateException("Cannot add term vectors when field is open");
/*     */     }
/* 163 */     for (int i = 0; i < vectors.length; i++) {
/* 164 */       addTermFreqVector(vectors[i]);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final void addTermFreqVector(TermFreqVector vector)
/*     */     throws IOException
/*     */   {
/* 176 */     if (!isDocumentOpen()) throw new IllegalStateException("Cannot add term vector when document is not open");
/* 177 */     if (isFieldOpen()) throw new IllegalStateException("Cannot add term vector when field is open");
/* 178 */     addTermFreqVectorInternal(vector);
/*     */   }
/*     */   
/*     */   private final void addTermFreqVectorInternal(TermFreqVector vector) throws IOException
/*     */   {
/* 183 */     openField(vector.getField());
/* 184 */     for (int i = 0; i < vector.size(); i++) {
/* 185 */       addTermInternal(vector.getTerms()[i], vector.getTermFrequencies()[i]);
/*     */     }
/* 187 */     closeField();
/*     */   }
/*     */   
/*     */ 
/*     */   final void close()
/*     */     throws IOException
/*     */   {
/*     */     try
/*     */     {
/* 196 */       closeDocument();
/*     */     }
/*     */     finally
/*     */     {
/* 200 */       IOException keep = null;
/* 201 */       if (this.tvx != null)
/*     */         try {
/* 203 */           this.tvx.close();
/*     */         } catch (IOException e) {
/* 205 */           if (keep == null) keep = e;
/*     */         }
/* 207 */       if (this.tvd != null)
/*     */         try {
/* 209 */           this.tvd.close();
/*     */         } catch (IOException e) {
/* 211 */           if (keep == null) keep = e;
/*     */         }
/* 213 */       if (this.tvf != null)
/*     */         try {
/* 215 */           this.tvf.close();
/*     */         } catch (IOException e) {
/* 217 */           if (keep == null) keep = e;
/*     */         }
/* 219 */       if (keep != null) { throw ((IOException)keep.fillInStackTrace());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void writeField()
/*     */     throws IOException
/*     */   {
/* 227 */     this.currentField.tvfPointer = this.tvf.getFilePointer();
/*     */     
/*     */     int size;
/*     */     
/* 231 */     this.tvf.writeVInt(size = this.terms.size());
/* 232 */     this.tvf.writeVInt(this.currentField.length - size);
/* 233 */     String lastTermText = "";
/*     */     
/* 235 */     for (int i = 0; i < size; i++) {
/* 236 */       TVTerm term = (TVTerm)this.terms.elementAt(i);
/*     */       
/* 238 */       int start = StringHelper.stringDifference(lastTermText, term.termText);
/* 239 */       int length = term.termText.length() - start;
/* 240 */       this.tvf.writeVInt(start);
/* 241 */       this.tvf.writeVInt(length);
/* 242 */       this.tvf.writeChars(term.termText, start, length);
/* 243 */       this.tvf.writeVInt(term.freq);
/* 244 */       lastTermText = term.termText;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private void writeDoc()
/*     */     throws IOException
/*     */   {
/* 252 */     if (isFieldOpen()) { throw new IllegalStateException("Field is still open while writing document");
/*     */     }
/*     */     
/* 255 */     this.tvx.writeLong(this.currentDocPointer);
/*     */     
/*     */ 
/*     */     int size;
/*     */     
/*     */ 
/* 261 */     this.tvd.writeVInt(size = this.fields.size());
/*     */     
/*     */ 
/* 264 */     int lastFieldNumber = 0;
/* 265 */     for (int i = 0; i < size; i++) {
/* 266 */       TVField field = (TVField)this.fields.elementAt(i);
/* 267 */       this.tvd.writeVInt(field.number - lastFieldNumber);
/*     */       
/* 269 */       lastFieldNumber = field.number;
/*     */     }
/*     */     
/*     */ 
/* 273 */     long lastFieldPointer = 0L;
/* 274 */     for (int i = 0; i < size; i++) {
/* 275 */       TVField field = (TVField)this.fields.elementAt(i);
/* 276 */       this.tvd.writeVLong(field.tvfPointer - lastFieldPointer);
/*     */       
/* 278 */       lastFieldPointer = field.tvfPointer;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private static class TVField
/*     */   {
/*     */     int number;
/* 286 */     long tvfPointer = 0L;
/* 287 */     int length = 0;
/*     */     
/*     */ 
/* 290 */     TVField(int number) { this.number = number; } }
/*     */   
/*     */   private static class TVTerm { private TVTerm() {}
/*     */     String termText;
/* 294 */     TVTerm(TermVectorsWriter.1 x0) { this(); }
/*     */     
/* 296 */     int freq = 0;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/index/TermVectorsWriter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */