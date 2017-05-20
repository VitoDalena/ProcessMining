/*     */ package org.apache.lucene.document;
/*     */ 
/*     */ import java.io.Reader;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Field
/*     */   implements Serializable
/*     */ {
/*  34 */   private String name = "body";
/*  35 */   private String stringValue = null;
/*  36 */   private boolean storeTermVector = false;
/*  37 */   private Reader readerValue = null;
/*  38 */   private boolean isStored = false;
/*  39 */   private boolean isIndexed = true;
/*  40 */   private boolean isTokenized = true;
/*     */   
/*  42 */   private float boost = 1.0F;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBoost(float boost)
/*     */   {
/*  61 */     this.boost = boost;
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
/*     */   public float getBoost()
/*     */   {
/*  76 */     return this.boost;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static final Field Keyword(String name, String value)
/*     */   {
/*  83 */     return new Field(name, value, true, true, false);
/*     */   }
/*     */   
/*     */ 
/*     */   public static final Field UnIndexed(String name, String value)
/*     */   {
/*  89 */     return new Field(name, value, true, false, false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static final Field Text(String name, String value)
/*     */   {
/*  96 */     return Text(name, value, false);
/*     */   }
/*     */   
/*     */ 
/*     */   public static final Field Keyword(String name, Date value)
/*     */   {
/* 102 */     return new Field(name, DateField.dateToString(value), true, true, false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static final Field Text(String name, String value, boolean storeTermVector)
/*     */   {
/* 109 */     return new Field(name, value, true, true, true, storeTermVector);
/*     */   }
/*     */   
/*     */ 
/*     */   public static final Field UnStored(String name, String value)
/*     */   {
/* 115 */     return UnStored(name, value, false);
/*     */   }
/*     */   
/*     */ 
/*     */   public static final Field UnStored(String name, String value, boolean storeTermVector)
/*     */   {
/* 121 */     return new Field(name, value, false, true, true, storeTermVector);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static final Field Text(String name, Reader value)
/*     */   {
/* 128 */     return Text(name, value, false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static final Field Text(String name, Reader value, boolean storeTermVector)
/*     */   {
/* 135 */     Field f = new Field(name, value);
/* 136 */     f.storeTermVector = storeTermVector;
/* 137 */     return f;
/*     */   }
/*     */   
/*     */   public String name()
/*     */   {
/* 142 */     return this.name;
/*     */   }
/*     */   
/*     */   public String stringValue() {
/* 146 */     return this.stringValue;
/*     */   }
/*     */   
/* 149 */   public Reader readerValue() { return this.readerValue; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Field(String name, String string, boolean store, boolean index, boolean token)
/*     */   {
/* 157 */     this(name, string, store, index, token, false);
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
/*     */   public Field(String name, String string, boolean store, boolean index, boolean token, boolean storeTermVector)
/*     */   {
/* 171 */     if (name == null)
/* 172 */       throw new IllegalArgumentException("name cannot be null");
/* 173 */     if (string == null)
/* 174 */       throw new IllegalArgumentException("value cannot be null");
/* 175 */     if ((!index) && (storeTermVector)) {
/* 176 */       throw new IllegalArgumentException("cannot store a term vector for fields that are not indexed.");
/*     */     }
/* 178 */     this.name = name.intern();
/* 179 */     this.stringValue = string;
/* 180 */     this.isStored = store;
/* 181 */     this.isIndexed = index;
/* 182 */     this.isTokenized = token;
/* 183 */     this.storeTermVector = storeTermVector;
/*     */   }
/*     */   
/*     */   Field(String name, Reader reader) {
/* 187 */     if (name == null)
/* 188 */       throw new IllegalArgumentException("name cannot be null");
/* 189 */     if (reader == null) {
/* 190 */       throw new IllegalArgumentException("value cannot be null");
/*     */     }
/* 192 */     this.name = name.intern();
/* 193 */     this.readerValue = reader;
/*     */   }
/*     */   
/*     */ 
/*     */   public final boolean isStored()
/*     */   {
/* 199 */     return this.isStored;
/*     */   }
/*     */   
/*     */   public final boolean isIndexed() {
/* 203 */     return this.isIndexed;
/*     */   }
/*     */   
/*     */   public final boolean isTokenized()
/*     */   {
/* 208 */     return this.isTokenized;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final boolean isTermVectorStored()
/*     */   {
/* 218 */     return this.storeTermVector;
/*     */   }
/*     */   
/*     */   public final String toString() {
/* 222 */     if ((this.isStored) && (this.isIndexed) && (!this.isTokenized))
/* 223 */       return "Keyword<" + this.name + ":" + this.stringValue + ">";
/* 224 */     if ((this.isStored) && (!this.isIndexed) && (!this.isTokenized))
/* 225 */       return "Unindexed<" + this.name + ":" + this.stringValue + ">";
/* 226 */     if ((this.isStored) && (this.isIndexed) && (this.isTokenized) && (this.stringValue != null))
/* 227 */       return "Text<" + this.name + ":" + this.stringValue + ">";
/* 228 */     if ((!this.isStored) && (this.isIndexed) && (this.isTokenized) && (this.readerValue != null))
/* 229 */       return "Text<" + this.name + ":" + this.readerValue + ">";
/* 230 */     if ((!this.isStored) && (this.isIndexed) && (this.isTokenized))
/*     */     {
/* 232 */       return "UnStored<" + this.name + ">";
/*     */     }
/*     */     
/*     */ 
/* 236 */     return super.toString();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/document/Field.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */