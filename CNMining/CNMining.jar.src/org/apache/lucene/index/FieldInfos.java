/*     */ package org.apache.lucene.index;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import org.apache.lucene.document.Document;
/*     */ import org.apache.lucene.document.Field;
/*     */ import org.apache.lucene.store.Directory;
/*     */ import org.apache.lucene.store.InputStream;
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
/*     */ final class FieldInfos
/*     */ {
/*  36 */   private ArrayList byNumber = new ArrayList();
/*  37 */   private HashMap byName = new HashMap();
/*     */   
/*     */   FieldInfos() {
/*  40 */     add("", false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   FieldInfos(Directory d, String name)
/*     */     throws IOException
/*     */   {
/*  53 */     InputStream input = d.openFile(name);
/*     */     try {
/*  55 */       read(input);
/*     */     } finally {
/*  57 */       input.close();
/*     */     }
/*     */   }
/*     */   
/*     */   public void add(Document doc)
/*     */   {
/*  63 */     Enumeration fields = doc.fields();
/*  64 */     while (fields.hasMoreElements()) {
/*  65 */       Field field = (Field)fields.nextElement();
/*  66 */       add(field.name(), field.isIndexed(), field.isTermVectorStored());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addIndexed(Collection names, boolean storeTermVectors)
/*     */   {
/*  75 */     Iterator i = names.iterator();
/*  76 */     int j = 0;
/*  77 */     while (i.hasNext()) {
/*  78 */       add((String)i.next(), true, storeTermVectors);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(Collection names, boolean isIndexed)
/*     */   {
/*  90 */     Iterator i = names.iterator();
/*  91 */     int j = 0;
/*  92 */     while (i.hasNext()) {
/*  93 */       add((String)i.next(), isIndexed);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(String name, boolean isIndexed)
/*     */   {
/* 104 */     add(name, isIndexed, false);
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
/*     */   public void add(String name, boolean isIndexed, boolean storeTermVector)
/*     */   {
/* 117 */     FieldInfo fi = fieldInfo(name);
/* 118 */     if (fi == null) {
/* 119 */       addInternal(name, isIndexed, storeTermVector);
/*     */     } else {
/* 121 */       if (fi.isIndexed != isIndexed) {
/* 122 */         fi.isIndexed = true;
/*     */       }
/* 124 */       if (fi.storeTermVector != storeTermVector) {
/* 125 */         fi.storeTermVector = true;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void addInternal(String name, boolean isIndexed, boolean storeTermVector)
/*     */   {
/* 132 */     FieldInfo fi = new FieldInfo(name, isIndexed, this.byNumber.size(), storeTermVector);
/*     */     
/* 134 */     this.byNumber.add(fi);
/* 135 */     this.byName.put(name, fi);
/*     */   }
/*     */   
/*     */   public int fieldNumber(String fieldName) {
/* 139 */     FieldInfo fi = fieldInfo(fieldName);
/* 140 */     if (fi != null) {
/* 141 */       return fi.number;
/*     */     }
/* 143 */     return -1;
/*     */   }
/*     */   
/*     */   public FieldInfo fieldInfo(String fieldName) {
/* 147 */     return (FieldInfo)this.byName.get(fieldName);
/*     */   }
/*     */   
/*     */   public String fieldName(int fieldNumber) {
/* 151 */     return fieldInfo(fieldNumber).name;
/*     */   }
/*     */   
/*     */   public FieldInfo fieldInfo(int fieldNumber) {
/* 155 */     return (FieldInfo)this.byNumber.get(fieldNumber);
/*     */   }
/*     */   
/*     */   public int size() {
/* 159 */     return this.byNumber.size();
/*     */   }
/*     */   
/*     */   public boolean hasVectors() {
/* 163 */     boolean hasVectors = false;
/* 164 */     for (int i = 0; i < size(); i++) {
/* 165 */       if (fieldInfo(i).storeTermVector)
/* 166 */         hasVectors = true;
/*     */     }
/* 168 */     return hasVectors;
/*     */   }
/*     */   
/*     */   public void write(Directory d, String name) throws IOException {
/* 172 */     OutputStream output = d.createFile(name);
/*     */     try {
/* 174 */       write(output);
/*     */     } finally {
/* 176 */       output.close();
/*     */     }
/*     */   }
/*     */   
/*     */   public void write(OutputStream output) throws IOException {
/* 181 */     output.writeVInt(size());
/* 182 */     for (int i = 0; i < size(); i++) {
/* 183 */       FieldInfo fi = fieldInfo(i);
/* 184 */       byte bits = 0;
/* 185 */       if (fi.isIndexed) bits = (byte)(bits | 0x1);
/* 186 */       if (fi.storeTermVector) bits = (byte)(bits | 0x2);
/* 187 */       output.writeString(fi.name);
/*     */       
/*     */ 
/* 190 */       output.writeByte(bits);
/*     */     }
/*     */   }
/*     */   
/*     */   private void read(InputStream input) throws IOException {
/* 195 */     int size = input.readVInt();
/* 196 */     for (int i = 0; i < size; i++) {
/* 197 */       String name = input.readString().intern();
/* 198 */       byte bits = input.readByte();
/* 199 */       boolean isIndexed = (bits & 0x1) != 0;
/* 200 */       boolean storeTermVector = (bits & 0x2) != 0;
/* 201 */       addInternal(name, isIndexed, storeTermVector);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/index/FieldInfos.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */