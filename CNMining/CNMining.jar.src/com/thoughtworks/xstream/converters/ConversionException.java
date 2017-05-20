/*     */ package com.thoughtworks.xstream.converters;
/*     */ 
/*     */ import com.thoughtworks.xstream.XStreamException;
/*     */ import com.thoughtworks.xstream.core.util.OrderRetainingMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
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
/*     */ public class ConversionException
/*     */   extends XStreamException
/*     */   implements ErrorWriter
/*     */ {
/*     */   private static final String SEPARATOR = "\n-------------------------------";
/*  35 */   private Map stuff = new OrderRetainingMap();
/*     */   
/*     */   public ConversionException(String msg, Throwable cause) {
/*  38 */     super(msg, cause);
/*  39 */     if (msg != null) {
/*  40 */       add("message", msg);
/*     */     }
/*  42 */     if (cause != null) {
/*  43 */       add("cause-exception", cause.getClass().getName());
/*  44 */       add("cause-message", (cause instanceof ConversionException) ? ((ConversionException)cause).getShortMessage() : cause.getMessage());
/*     */     }
/*     */   }
/*     */   
/*     */   public ConversionException(String msg) {
/*  49 */     super(msg);
/*     */   }
/*     */   
/*     */   public ConversionException(Throwable cause) {
/*  53 */     this(cause.getMessage(), cause);
/*     */   }
/*     */   
/*     */   public String get(String errorKey) {
/*  57 */     return (String)this.stuff.get(errorKey);
/*     */   }
/*     */   
/*     */   public void add(String name, String information) {
/*  61 */     String key = name;
/*  62 */     int i = 0;
/*  63 */     while (this.stuff.containsKey(key)) {
/*  64 */       String value = (String)this.stuff.get(key);
/*  65 */       if (information.equals(value))
/*  66 */         return;
/*  67 */       key = name + "[" + ++i + "]";
/*     */     }
/*  69 */     this.stuff.put(key, information);
/*     */   }
/*     */   
/*     */   public void set(String name, String information) {
/*  73 */     String key = name;
/*  74 */     int i = 0;
/*  75 */     this.stuff.put(key, information);
/*  76 */     while (this.stuff.containsKey(key)) {
/*  77 */       if (i != 0) {
/*  78 */         this.stuff.remove(key);
/*     */       }
/*  80 */       key = name + "[" + ++i + "]";
/*     */     }
/*     */   }
/*     */   
/*     */   public Iterator keys() {
/*  85 */     return this.stuff.keySet().iterator();
/*     */   }
/*     */   
/*     */   public String getMessage() {
/*  89 */     StringBuffer result = new StringBuffer();
/*  90 */     if (super.getMessage() != null) {
/*  91 */       result.append(super.getMessage());
/*     */     }
/*  93 */     if (!result.toString().endsWith("\n-------------------------------")) {
/*  94 */       result.append("\n---- Debugging information ----");
/*     */     }
/*  96 */     for (Iterator iterator = keys(); iterator.hasNext();) {
/*  97 */       String k = (String)iterator.next();
/*  98 */       String v = get(k);
/*  99 */       result.append('\n').append(k);
/* 100 */       result.append("                    ".substring(Math.min(20, k.length())));
/* 101 */       result.append(": ").append(v);
/*     */     }
/* 103 */     result.append("\n-------------------------------");
/* 104 */     return result.toString();
/*     */   }
/*     */   
/*     */   public String getShortMessage() {
/* 108 */     return super.getMessage();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/ConversionException.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */