/*     */ package com.thoughtworks.xstream.io.json;
/*     */ 
/*     */ import com.thoughtworks.xstream.core.util.QuickWriter;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*     */ import java.io.Writer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JsonWriter
/*     */   extends AbstractJsonWriter
/*     */ {
/*     */   protected final QuickWriter writer;
/*     */   protected final Format format;
/*     */   private int depth;
/*     */   private boolean newLineProposed;
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public JsonWriter(Writer writer, char[] lineIndenter, String newLine)
/*     */   {
/*  40 */     this(writer, 0, new Format(lineIndenter, newLine.toCharArray(), Format.SPACE_AFTER_LABEL | Format.COMPACT_EMPTY_ELEMENT));
/*     */   }
/*     */   
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public JsonWriter(Writer writer, char[] lineIndenter)
/*     */   {
/*  49 */     this(writer, 0, new Format(lineIndenter, new char[] { '\n' }, Format.SPACE_AFTER_LABEL | Format.COMPACT_EMPTY_ELEMENT));
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public JsonWriter(Writer writer, String lineIndenter, String newLine)
/*     */   {
/*  57 */     this(writer, 0, new Format(lineIndenter.toCharArray(), newLine.toCharArray(), Format.SPACE_AFTER_LABEL | Format.COMPACT_EMPTY_ELEMENT));
/*     */   }
/*     */   
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public JsonWriter(Writer writer, String lineIndenter)
/*     */   {
/*  66 */     this(writer, 0, new Format(lineIndenter.toCharArray(), new char[] { '\n' }, Format.SPACE_AFTER_LABEL | Format.COMPACT_EMPTY_ELEMENT));
/*     */   }
/*     */   
/*     */ 
/*     */   public JsonWriter(Writer writer)
/*     */   {
/*  72 */     this(writer, 0, new Format(new char[] { ' ', ' ' }, new char[] { '\n' }, Format.SPACE_AFTER_LABEL | Format.COMPACT_EMPTY_ELEMENT));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public JsonWriter(Writer writer, char[] lineIndenter, String newLine, int mode)
/*     */   {
/*  82 */     this(writer, mode, new Format(lineIndenter, newLine.toCharArray(), Format.SPACE_AFTER_LABEL | Format.COMPACT_EMPTY_ELEMENT));
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
/*     */   public JsonWriter(Writer writer, int mode)
/*     */   {
/*  96 */     this(writer, mode, new Format(new char[] { ' ', ' ' }, new char[] { '\n' }, Format.SPACE_AFTER_LABEL | Format.COMPACT_EMPTY_ELEMENT));
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
/*     */   public JsonWriter(Writer writer, Format format)
/*     */   {
/* 110 */     this(writer, 0, format);
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
/*     */   public JsonWriter(Writer writer, int mode, Format format)
/*     */   {
/* 133 */     this(writer, mode, format, 1024);
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
/*     */   public JsonWriter(Writer writer, int mode, Format format, int bufferSize)
/*     */   {
/* 147 */     super(mode);
/* 148 */     this.writer = new QuickWriter(writer, bufferSize);
/* 149 */     this.format = format;
/* 150 */     this.depth = ((mode & 0x1) == 0 ? -1 : 0);
/*     */   }
/*     */   
/*     */   public void flush() {
/* 154 */     this.writer.flush();
/*     */   }
/*     */   
/*     */   public void close() {
/* 158 */     this.writer.close();
/*     */   }
/*     */   
/*     */   public HierarchicalStreamWriter underlyingWriter() {
/* 162 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void startObject(String name)
/*     */   {
/* 169 */     if (this.newLineProposed) {
/* 170 */       writeNewLine();
/*     */     }
/* 172 */     this.writer.write('{');
/* 173 */     startNewLine();
/* 174 */     if (name != null) {
/* 175 */       addLabel(name);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void addLabel(String name)
/*     */   {
/* 183 */     if (this.newLineProposed) {
/* 184 */       writeNewLine();
/*     */     }
/* 186 */     this.writer.write('"');
/* 187 */     writeText(name);
/* 188 */     this.writer.write("\":");
/* 189 */     if ((this.format.mode() & Format.SPACE_AFTER_LABEL) != 0) {
/* 190 */       this.writer.write(' ');
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void addValue(String value, AbstractJsonWriter.Type type)
/*     */   {
/* 198 */     if (this.newLineProposed) {
/* 199 */       writeNewLine();
/*     */     }
/* 201 */     if (type == AbstractJsonWriter.Type.STRING) {
/* 202 */       this.writer.write('"');
/*     */     }
/* 204 */     writeText(value);
/* 205 */     if (type == AbstractJsonWriter.Type.STRING) {
/* 206 */       this.writer.write('"');
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void startArray()
/*     */   {
/* 214 */     if (this.newLineProposed) {
/* 215 */       writeNewLine();
/*     */     }
/* 217 */     this.writer.write("[");
/* 218 */     startNewLine();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void nextElement()
/*     */   {
/* 225 */     this.writer.write(",");
/* 226 */     writeNewLine();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void endArray()
/*     */   {
/* 233 */     endNewLine();
/* 234 */     this.writer.write("]");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void endObject()
/*     */   {
/* 241 */     endNewLine();
/* 242 */     this.writer.write("}");
/*     */   }
/*     */   
/*     */   private void startNewLine() {
/* 246 */     if (++this.depth > 0) {
/* 247 */       this.newLineProposed = true;
/*     */     }
/*     */   }
/*     */   
/*     */   private void endNewLine() {
/* 252 */     if (this.depth-- > 0) {
/* 253 */       if (((this.format.mode() & Format.COMPACT_EMPTY_ELEMENT) != 0) && (this.newLineProposed)) {
/* 254 */         this.newLineProposed = false;
/*     */       } else {
/* 256 */         writeNewLine();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void writeNewLine() {
/* 262 */     int depth = this.depth;
/* 263 */     this.writer.write(this.format.getNewLine());
/* 264 */     while (depth-- > 0) {
/* 265 */       this.writer.write(this.format.getLineIndenter());
/*     */     }
/* 267 */     this.newLineProposed = false;
/*     */   }
/*     */   
/*     */   private void writeText(String text) {
/* 271 */     int length = text.length();
/* 272 */     for (int i = 0; i < length; i++) {
/* 273 */       char c = text.charAt(i);
/* 274 */       switch (c) {
/*     */       case '"': 
/* 276 */         this.writer.write("\\\"");
/* 277 */         break;
/*     */       case '\\': 
/* 279 */         this.writer.write("\\\\");
/* 280 */         break;
/*     */       default: 
/* 282 */         if (c > '\037') {
/* 283 */           this.writer.write(c);
/*     */         } else {
/* 285 */           this.writer.write("\\u");
/* 286 */           String hex = "000" + Integer.toHexString(c);
/* 287 */           this.writer.write(hex.substring(hex.length() - 4));
/*     */         }
/*     */         
/*     */ 
/*     */         break;
/*     */       }
/*     */       
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static class Format
/*     */   {
/* 301 */     public static int SPACE_AFTER_LABEL = 1;
/* 302 */     public static int COMPACT_EMPTY_ELEMENT = 2;
/*     */     
/*     */ 
/*     */     private char[] lineIndenter;
/*     */     
/*     */ 
/*     */     private char[] newLine;
/*     */     
/*     */ 
/*     */     private final int mode;
/*     */     
/*     */ 
/*     */ 
/*     */     public Format(char[] lineIndenter, char[] newLine, int mode)
/*     */     {
/* 317 */       this.lineIndenter = lineIndenter;
/* 318 */       this.newLine = newLine;
/* 319 */       this.mode = mode;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public char[] getLineIndenter()
/*     */     {
/* 329 */       return this.lineIndenter;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public char[] getNewLine()
/*     */     {
/* 339 */       return this.newLine;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public int mode()
/*     */     {
/* 349 */       return this.mode;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/json/JsonWriter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */