/*     */ package com.thoughtworks.xstream.io.xml;
/*     */ 
/*     */ import com.thoughtworks.xstream.converters.reflection.ObjectAccessException;
/*     */ import com.thoughtworks.xstream.io.naming.NameCoder;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.Map;
/*     */ import java.util.WeakHashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XmlFriendlyNameCoder
/*     */   implements NameCoder, Cloneable
/*     */ {
/*     */   private final String dollarReplacement;
/*     */   private final String escapeCharReplacement;
/*     */   private transient Map escapeCache;
/*     */   private transient Map unescapeCache;
/*     */   
/*     */   public XmlFriendlyNameCoder()
/*     */   {
/*  56 */     this("_-", "__");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XmlFriendlyNameCoder(String dollarReplacement, String escapeCharReplacement)
/*     */   {
/*  68 */     this.dollarReplacement = dollarReplacement;
/*  69 */     this.escapeCharReplacement = escapeCharReplacement;
/*  70 */     readResolve();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String decodeAttribute(String attributeName)
/*     */   {
/*  77 */     return decodeName(attributeName);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String decodeNode(String elementName)
/*     */   {
/*  84 */     return decodeName(elementName);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String encodeAttribute(String name)
/*     */   {
/*  91 */     return encodeName(name);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String encodeNode(String name)
/*     */   {
/*  98 */     return encodeName(name);
/*     */   }
/*     */   
/*     */   private String encodeName(String name) {
/* 102 */     WeakReference ref = (WeakReference)this.escapeCache.get(name);
/* 103 */     String s = (String)(ref == null ? null : ref.get());
/*     */     
/* 105 */     if (s == null) {
/* 106 */       int length = name.length();
/*     */       
/*     */ 
/* 109 */       for (int i = 0; 
/*     */           
/* 111 */           i < length; i++) {
/* 112 */         char c = name.charAt(i);
/* 113 */         if ((c == '$') || (c == '_')) {
/*     */           break;
/*     */         }
/*     */       }
/*     */       
/* 118 */       if (i == length) {
/* 119 */         return name;
/*     */       }
/*     */       
/*     */ 
/* 123 */       StringBuffer result = new StringBuffer(length + 8);
/*     */       
/*     */ 
/* 126 */       if (i > 0) {
/* 127 */         result.append(name.substring(0, i));
/*     */       }
/* 130 */       for (; 
/* 130 */           i < length; i++) {
/* 131 */         char c = name.charAt(i);
/* 132 */         if (c == '$') {
/* 133 */           result.append(this.dollarReplacement);
/* 134 */         } else if (c == '_') {
/* 135 */           result.append(this.escapeCharReplacement);
/*     */         } else {
/* 137 */           result.append(c);
/*     */         }
/*     */       }
/* 140 */       s = result.toString();
/* 141 */       this.escapeCache.put(name, new WeakReference(s));
/*     */     }
/* 143 */     return s;
/*     */   }
/*     */   
/*     */   private String decodeName(String name) {
/* 147 */     WeakReference ref = (WeakReference)this.unescapeCache.get(name);
/* 148 */     String s = (String)(ref == null ? null : ref.get());
/*     */     
/* 150 */     if (s == null) {
/* 151 */       char dollarReplacementFirstChar = this.dollarReplacement.charAt(0);
/* 152 */       char escapeReplacementFirstChar = this.escapeCharReplacement.charAt(0);
/* 153 */       int length = name.length();
/*     */       
/*     */ 
/* 156 */       for (int i = 0; 
/*     */           
/* 158 */           i < length; i++) {
/* 159 */         char c = name.charAt(i);
/*     */         
/* 161 */         if ((c == dollarReplacementFirstChar) || (c == escapeReplacementFirstChar)) {
/*     */           break;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 167 */       if (i == length) {
/* 168 */         return name;
/*     */       }
/*     */       
/*     */ 
/* 172 */       StringBuffer result = new StringBuffer(length + 8);
/*     */       
/*     */ 
/* 175 */       if (i > 0) {
/* 176 */         result.append(name.substring(0, i));
/*     */       }
/* 179 */       for (; 
/* 179 */           i < length; i++) {
/* 180 */         char c = name.charAt(i);
/* 181 */         if ((c == dollarReplacementFirstChar) && (name.startsWith(this.dollarReplacement, i))) {
/* 182 */           i += this.dollarReplacement.length() - 1;
/* 183 */           result.append('$');
/* 184 */         } else if ((c == escapeReplacementFirstChar) && (name.startsWith(this.escapeCharReplacement, i)))
/*     */         {
/* 186 */           i += this.escapeCharReplacement.length() - 1;
/* 187 */           result.append('_');
/*     */         } else {
/* 189 */           result.append(c);
/*     */         }
/*     */       }
/*     */       
/* 193 */       s = result.toString();
/* 194 */       this.unescapeCache.put(name, new WeakReference(s));
/*     */     }
/* 196 */     return s;
/*     */   }
/*     */   
/*     */   public Object clone() {
/*     */     try {
/* 201 */       XmlFriendlyNameCoder coder = (XmlFriendlyNameCoder)super.clone();
/* 202 */       coder.readResolve();
/* 203 */       return coder;
/*     */     }
/*     */     catch (CloneNotSupportedException e) {
/* 206 */       throw new ObjectAccessException("Cannot clone XmlFriendlyNameCoder", e);
/*     */     }
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 211 */     this.escapeCache = new WeakHashMap();
/* 212 */     this.unescapeCache = new WeakHashMap();
/* 213 */     return this;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/XmlFriendlyNameCoder.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */