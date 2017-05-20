/*     */ package com.thoughtworks.xstream.io.xml;
/*     */ 
/*     */ import com.thoughtworks.xstream.core.util.FastStack;
/*     */ import com.thoughtworks.xstream.core.util.QuickWriter;
/*     */ import com.thoughtworks.xstream.io.StreamException;
/*     */ import com.thoughtworks.xstream.io.naming.NameCoder;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PrettyPrintWriter
/*     */   extends AbstractXmlWriter
/*     */ {
/*  49 */   public static int XML_QUIRKS = -1;
/*  50 */   public static int XML_1_0 = 0;
/*  51 */   public static int XML_1_1 = 1;
/*     */   
/*     */   private final QuickWriter writer;
/*  54 */   private final FastStack elementStack = new FastStack(16);
/*     */   
/*     */   private final char[] lineIndenter;
/*     */   
/*     */   private final int mode;
/*     */   private boolean tagInProgress;
/*     */   protected int depth;
/*     */   private boolean readyForNewLine;
/*     */   private boolean tagIsEmpty;
/*     */   private String newLine;
/*  64 */   private static final char[] NULL = "&#x0;".toCharArray();
/*  65 */   private static final char[] AMP = "&amp;".toCharArray();
/*  66 */   private static final char[] LT = "&lt;".toCharArray();
/*  67 */   private static final char[] GT = "&gt;".toCharArray();
/*  68 */   private static final char[] CR = "&#xd;".toCharArray();
/*  69 */   private static final char[] QUOT = "&quot;".toCharArray();
/*  70 */   private static final char[] APOS = "&apos;".toCharArray();
/*  71 */   private static final char[] CLOSE = "</".toCharArray();
/*     */   
/*     */ 
/*     */   private PrettyPrintWriter(Writer writer, int mode, char[] lineIndenter, NameCoder nameCoder, String newLine)
/*     */   {
/*  76 */     super(nameCoder);
/*  77 */     this.writer = new QuickWriter(writer);
/*  78 */     this.lineIndenter = lineIndenter;
/*  79 */     this.newLine = newLine;
/*  80 */     this.mode = mode;
/*  81 */     if ((mode < XML_QUIRKS) || (mode > XML_1_1)) {
/*  82 */       throw new IllegalArgumentException("Not a valid XML mode");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public PrettyPrintWriter(Writer writer, char[] lineIndenter, String newLine, XmlFriendlyReplacer replacer)
/*     */   {
/*  92 */     this(writer, XML_QUIRKS, lineIndenter, replacer, newLine);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public PrettyPrintWriter(Writer writer, int mode, char[] lineIndenter, NameCoder nameCoder)
/*     */   {
/* 100 */     this(writer, mode, lineIndenter, nameCoder, "\n");
/*     */   }
/*     */   
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public PrettyPrintWriter(Writer writer, int mode, char[] lineIndenter, XmlFriendlyReplacer replacer)
/*     */   {
/* 109 */     this(writer, mode, lineIndenter, replacer, "\n");
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public PrettyPrintWriter(Writer writer, char[] lineIndenter, String newLine) {
/* 116 */     this(writer, lineIndenter, newLine, new XmlFriendlyReplacer());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public PrettyPrintWriter(Writer writer, int mode, char[] lineIndenter)
/*     */   {
/* 123 */     this(writer, mode, lineIndenter, new XmlFriendlyNameCoder());
/*     */   }
/*     */   
/*     */   public PrettyPrintWriter(Writer writer, char[] lineIndenter) {
/* 127 */     this(writer, XML_QUIRKS, lineIndenter);
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public PrettyPrintWriter(Writer writer, String lineIndenter, String newLine) {
/* 134 */     this(writer, lineIndenter.toCharArray(), newLine);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public PrettyPrintWriter(Writer writer, int mode, String lineIndenter)
/*     */   {
/* 141 */     this(writer, mode, lineIndenter.toCharArray());
/*     */   }
/*     */   
/*     */   public PrettyPrintWriter(Writer writer, String lineIndenter) {
/* 145 */     this(writer, lineIndenter.toCharArray());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public PrettyPrintWriter(Writer writer, int mode, NameCoder nameCoder)
/*     */   {
/* 152 */     this(writer, mode, new char[] { ' ', ' ' }, nameCoder);
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public PrettyPrintWriter(Writer writer, int mode, XmlFriendlyReplacer replacer)
/*     */   {
/* 160 */     this(writer, mode, new char[] { ' ', ' ' }, replacer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public PrettyPrintWriter(Writer writer, NameCoder nameCoder)
/*     */   {
/* 167 */     this(writer, XML_QUIRKS, new char[] { ' ', ' ' }, nameCoder, "\n");
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public PrettyPrintWriter(Writer writer, XmlFriendlyReplacer replacer) {
/* 174 */     this(writer, new char[] { ' ', ' ' }, "\n", replacer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public PrettyPrintWriter(Writer writer, int mode)
/*     */   {
/* 181 */     this(writer, mode, new char[] { ' ', ' ' });
/*     */   }
/*     */   
/*     */   public PrettyPrintWriter(Writer writer) {
/* 185 */     this(writer, new char[] { ' ', ' ' });
/*     */   }
/*     */   
/*     */   public void startNode(String name) {
/* 189 */     String escapedName = encodeNode(name);
/* 190 */     this.tagIsEmpty = false;
/* 191 */     finishTag();
/* 192 */     this.writer.write('<');
/* 193 */     this.writer.write(escapedName);
/* 194 */     this.elementStack.push(escapedName);
/* 195 */     this.tagInProgress = true;
/* 196 */     this.depth += 1;
/* 197 */     this.readyForNewLine = true;
/* 198 */     this.tagIsEmpty = true;
/*     */   }
/*     */   
/*     */   public void startNode(String name, Class clazz) {
/* 202 */     startNode(name);
/*     */   }
/*     */   
/*     */   public void setValue(String text) {
/* 206 */     this.readyForNewLine = false;
/* 207 */     this.tagIsEmpty = false;
/* 208 */     finishTag();
/*     */     
/* 210 */     writeText(this.writer, text);
/*     */   }
/*     */   
/*     */   public void addAttribute(String key, String value) {
/* 214 */     this.writer.write(' ');
/* 215 */     this.writer.write(encodeAttribute(key));
/* 216 */     this.writer.write('=');
/* 217 */     this.writer.write('"');
/* 218 */     writeAttributeValue(this.writer, value);
/* 219 */     this.writer.write('"');
/*     */   }
/*     */   
/*     */   protected void writeAttributeValue(QuickWriter writer, String text) {
/* 223 */     writeText(text);
/*     */   }
/*     */   
/*     */   protected void writeText(QuickWriter writer, String text) {
/* 227 */     writeText(text);
/*     */   }
/*     */   
/*     */   private void writeText(String text) {
/* 231 */     int length = text.length();
/* 232 */     for (int i = 0; i < length; i++) {
/* 233 */       char c = text.charAt(i);
/* 234 */       switch (c) {
/*     */       case '\000': 
/* 236 */         if (this.mode == XML_QUIRKS) {
/* 237 */           this.writer.write(NULL);
/*     */         } else {
/* 239 */           throw new StreamException("Invalid character 0x0 in XML stream");
/*     */         }
/*     */         break;
/*     */       case '&': 
/* 243 */         this.writer.write(AMP);
/* 244 */         break;
/*     */       case '<': 
/* 246 */         this.writer.write(LT);
/* 247 */         break;
/*     */       case '>': 
/* 249 */         this.writer.write(GT);
/* 250 */         break;
/*     */       case '"': 
/* 252 */         this.writer.write(QUOT);
/* 253 */         break;
/*     */       case '\'': 
/* 255 */         this.writer.write(APOS);
/* 256 */         break;
/*     */       case '\r': 
/* 258 */         this.writer.write(CR);
/* 259 */         break;
/*     */       case '\t': 
/*     */       case '\n': 
/* 262 */         this.writer.write(c);
/* 263 */         break;
/*     */       default: 
/* 265 */         if ((Character.isDefined(c)) && (!Character.isISOControl(c))) {
/* 266 */           if ((this.mode != XML_QUIRKS) && 
/* 267 */             (c > 55295) && (c < 57344)) {
/* 268 */             throw new StreamException("Invalid character 0x" + Integer.toHexString(c) + " in XML stream");
/*     */           }
/*     */           
/*     */ 
/*     */ 
/* 273 */           this.writer.write(c);
/*     */         } else {
/* 275 */           if ((this.mode == XML_1_0) && (
/* 276 */             (c < '\t') || (c == '\013') || (c == '\f') || (c == '\016') || (c == '\017')))
/*     */           {
/*     */ 
/*     */ 
/*     */ 
/* 281 */             throw new StreamException("Invalid character 0x" + Integer.toHexString(c) + " in XML 1.0 stream");
/*     */           }
/*     */           
/*     */ 
/*     */ 
/* 286 */           if ((this.mode != XML_QUIRKS) && (
/* 287 */             (c == 65534) || (c == 65535))) {
/* 288 */             throw new StreamException("Invalid character 0x" + Integer.toHexString(c) + " in XML stream");
/*     */           }
/*     */           
/*     */ 
/*     */ 
/* 293 */           this.writer.write("&#x");
/* 294 */           this.writer.write(Integer.toHexString(c));
/* 295 */           this.writer.write(';');
/*     */         }
/*     */         break; }
/*     */     }
/*     */   }
/*     */   
/*     */   public void endNode() {
/* 302 */     this.depth -= 1;
/* 303 */     if (this.tagIsEmpty) {
/* 304 */       this.writer.write('/');
/* 305 */       this.readyForNewLine = false;
/* 306 */       finishTag();
/* 307 */       this.elementStack.popSilently();
/*     */     } else {
/* 309 */       finishTag();
/* 310 */       this.writer.write(CLOSE);
/* 311 */       this.writer.write((String)this.elementStack.pop());
/* 312 */       this.writer.write('>');
/*     */     }
/* 314 */     this.readyForNewLine = true;
/* 315 */     if (this.depth == 0) {
/* 316 */       this.writer.flush();
/*     */     }
/*     */   }
/*     */   
/*     */   private void finishTag() {
/* 321 */     if (this.tagInProgress) {
/* 322 */       this.writer.write('>');
/*     */     }
/* 324 */     this.tagInProgress = false;
/* 325 */     if (this.readyForNewLine) {
/* 326 */       endOfLine();
/*     */     }
/* 328 */     this.readyForNewLine = false;
/* 329 */     this.tagIsEmpty = false;
/*     */   }
/*     */   
/*     */   protected void endOfLine() {
/* 333 */     this.writer.write(getNewLine());
/* 334 */     for (int i = 0; i < this.depth; i++) {
/* 335 */       this.writer.write(this.lineIndenter);
/*     */     }
/*     */   }
/*     */   
/*     */   public void flush() {
/* 340 */     this.writer.flush();
/*     */   }
/*     */   
/*     */   public void close() {
/* 344 */     this.writer.close();
/*     */   }
/*     */   
/*     */   protected String getNewLine() {
/* 348 */     return this.newLine;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/PrettyPrintWriter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */