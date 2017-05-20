/*     */ package com.thoughtworks.xstream.core.util;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PushbackInputStream;
/*     */ import java.io.Reader;
/*     */ import java.io.UnsupportedEncodingException;
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
/*     */ public final class XmlHeaderAwareReader
/*     */   extends Reader
/*     */ {
/*     */   private final InputStreamReader reader;
/*     */   private final double version;
/*     */   private static final String KEY_ENCODING = "encoding";
/*     */   private static final String KEY_VERSION = "version";
/*     */   private static final String XML_TOKEN = "?xml";
/*     */   private static final int STATE_BOM = 0;
/*     */   private static final int STATE_START = 1;
/*     */   private static final int STATE_AWAIT_XML_HEADER = 2;
/*     */   private static final int STATE_ATTR_NAME = 3;
/*     */   private static final int STATE_ATTR_VALUE = 4;
/*     */   
/*     */   public XmlHeaderAwareReader(InputStream in)
/*     */     throws UnsupportedEncodingException, IOException
/*     */   {
/*  58 */     PushbackInputStream[] pin = { (in instanceof PushbackInputStream) ? (PushbackInputStream)in : new PushbackInputStream(in, 64) };
/*     */     
/*     */ 
/*  61 */     Map header = getHeader(pin);
/*  62 */     this.version = Double.parseDouble((String)header.get("version"));
/*  63 */     this.reader = new InputStreamReader(pin[0], (String)header.get("encoding"));
/*     */   }
/*     */   
/*     */   private Map getHeader(PushbackInputStream[] in) throws IOException {
/*  67 */     Map header = new HashMap();
/*  68 */     header.put("encoding", "utf-8");
/*  69 */     header.put("version", "1.0");
/*     */     
/*  71 */     int state = 0;
/*  72 */     ByteArrayOutputStream out = new ByteArrayOutputStream(64);
/*  73 */     int i = 0;
/*  74 */     char ch = '\000';
/*  75 */     char valueEnd = '\000';
/*  76 */     StringBuffer name = new StringBuffer();
/*  77 */     StringBuffer value = new StringBuffer();
/*  78 */     boolean escape = false;
/*  79 */     while ((i != -1) && ((i = in[0].read()) != -1)) {
/*  80 */       out.write(i);
/*  81 */       ch = (char)i;
/*  82 */       switch (state) {
/*     */       case 0: 
/*  84 */         if (((ch == 'ï') && (out.size() == 1)) || ((ch == '»') && (out.size() == 2)) || ((ch == '¿') && (out.size() == 3)))
/*     */         {
/*     */ 
/*  87 */           if (ch == '¿') {
/*  88 */             out.reset();
/*  89 */             state = 1;
/*     */           }
/*     */         }
/*  92 */         else if (out.size() > 1) {
/*  93 */           i = -1;
/*     */         }
/*     */         else {
/*  96 */           state = 1;
/*     */         }
/*     */         break;
/*     */       case 1: 
/* 100 */         if (!Character.isWhitespace(ch)) {
/* 101 */           if (ch == '<') {
/* 102 */             state = 2;
/*     */           } else {
/* 104 */             i = -1;
/*     */           }
/*     */         }
/*     */         break;
/*     */       case 2: 
/* 109 */         if (!Character.isWhitespace(ch)) {
/* 110 */           name.append(Character.toLowerCase(ch));
/* 111 */           if (!"?xml".startsWith(name.substring(0))) {
/* 112 */             i = -1;
/*     */           }
/*     */         }
/* 115 */         else if (name.toString().equals("?xml")) {
/* 116 */           state = 3;
/* 117 */           name.setLength(0);
/*     */         } else {
/* 119 */           i = -1;
/*     */         }
/*     */         
/* 122 */         break;
/*     */       case 3: 
/* 124 */         if (!Character.isWhitespace(ch)) {
/* 125 */           if (ch == '=') {
/* 126 */             state = 4;
/*     */           } else {
/* 128 */             ch = Character.toLowerCase(ch);
/* 129 */             if (Character.isLetter(ch)) {
/* 130 */               name.append(ch);
/*     */             } else {
/* 132 */               i = -1;
/*     */             }
/*     */           }
/* 135 */         } else if (name.length() > 0) {
/* 136 */           i = -1;
/*     */         }
/*     */         break;
/*     */       case 4: 
/* 140 */         if (valueEnd == 0) {
/* 141 */           if ((ch == '"') || (ch == '\'')) {
/* 142 */             valueEnd = ch;
/*     */           } else {
/* 144 */             i = -1;
/*     */           }
/*     */         }
/* 147 */         else if ((ch == '\\') && (!escape)) {
/* 148 */           escape = true;
/*     */ 
/*     */         }
/* 151 */         else if ((ch == valueEnd) && (!escape)) {
/* 152 */           valueEnd = '\000';
/* 153 */           state = 3;
/* 154 */           header.put(name.toString(), value.toString());
/* 155 */           name.setLength(0);
/* 156 */           value.setLength(0);
/*     */         } else {
/* 158 */           escape = false;
/* 159 */           if (ch != '\n') {
/* 160 */             value.append(ch);
/*     */           } else {
/* 162 */             i = -1;
/*     */           }
/*     */         }
/*     */         
/*     */         break;
/*     */       }
/*     */       
/*     */     }
/* 170 */     byte[] pushbackData = out.toByteArray();
/* 171 */     for (i = pushbackData.length; i-- > 0;) {
/* 172 */       byte b = pushbackData[i];
/*     */       try {
/* 174 */         in[0].unread(b);
/*     */       } catch (IOException ex) {
/* 176 */         in[0] = new PushbackInputStream(in[0], ++i);
/*     */       }
/*     */     }
/* 179 */     return header;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getEncoding()
/*     */   {
/* 187 */     return this.reader.getEncoding();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getVersion()
/*     */   {
/* 195 */     return this.version;
/*     */   }
/*     */   
/*     */ 
/*     */   public void mark(int readAheadLimit)
/*     */     throws IOException
/*     */   {
/* 202 */     this.reader.mark(readAheadLimit);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean markSupported()
/*     */   {
/* 209 */     return this.reader.markSupported();
/*     */   }
/*     */   
/*     */ 
/*     */   public int read()
/*     */     throws IOException
/*     */   {
/* 216 */     return this.reader.read();
/*     */   }
/*     */   
/*     */ 
/*     */   public int read(char[] cbuf, int offset, int length)
/*     */     throws IOException
/*     */   {
/* 223 */     return this.reader.read(cbuf, offset, length);
/*     */   }
/*     */   
/*     */ 
/*     */   public int read(char[] cbuf)
/*     */     throws IOException
/*     */   {
/* 230 */     return this.reader.read(cbuf);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean ready()
/*     */     throws IOException
/*     */   {
/* 242 */     return this.reader.ready();
/*     */   }
/*     */   
/*     */ 
/*     */   public void reset()
/*     */     throws IOException
/*     */   {
/* 249 */     this.reader.reset();
/*     */   }
/*     */   
/*     */ 
/*     */   public long skip(long n)
/*     */     throws IOException
/*     */   {
/* 256 */     return this.reader.skip(n);
/*     */   }
/*     */   
/*     */ 
/*     */   public void close()
/*     */     throws IOException
/*     */   {
/* 263 */     this.reader.close();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 270 */     return this.reader.equals(obj);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 277 */     return this.reader.hashCode();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 284 */     return this.reader.toString();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/core/util/XmlHeaderAwareReader.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */