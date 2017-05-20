/*     */ package org.apache.lucene.search;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Locale;
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
/*     */ public class SortField
/*     */   implements Serializable
/*     */ {
/*     */   public static final int SCORE = 0;
/*     */   public static final int DOC = 1;
/*     */   public static final int AUTO = 2;
/*     */   public static final int STRING = 3;
/*     */   public static final int INT = 4;
/*     */   public static final int FLOAT = 5;
/*     */   public static final int CUSTOM = 9;
/*  72 */   public static final SortField FIELD_SCORE = new SortField(null, 0);
/*     */   
/*     */ 
/*  75 */   public static final SortField FIELD_DOC = new SortField(null, 1);
/*     */   
/*     */   private String field;
/*     */   
/*  79 */   private int type = 2;
/*     */   private Locale locale;
/*  81 */   boolean reverse = false;
/*     */   
/*     */ 
/*     */   private SortComparatorSource factory;
/*     */   
/*     */ 
/*     */   public SortField(String field)
/*     */   {
/*  89 */     this.field = field.intern();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SortField(String field, boolean reverse)
/*     */   {
/*  98 */     this.field = field.intern();
/*  99 */     this.reverse = reverse;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SortField(String field, int type)
/*     */   {
/* 109 */     this.field = (field != null ? field.intern() : field);
/* 110 */     this.type = type;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SortField(String field, int type, boolean reverse)
/*     */   {
/* 121 */     this.field = (field != null ? field.intern() : field);
/* 122 */     this.type = type;
/* 123 */     this.reverse = reverse;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SortField(String field, Locale locale)
/*     */   {
/* 132 */     this.field = field.intern();
/* 133 */     this.type = 3;
/* 134 */     this.locale = locale;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SortField(String field, Locale locale, boolean reverse)
/*     */   {
/* 143 */     this.field = field.intern();
/* 144 */     this.type = 3;
/* 145 */     this.locale = locale;
/* 146 */     this.reverse = reverse;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public SortField(String field, SortComparatorSource comparator)
/*     */   {
/* 154 */     this.field = (field != null ? field.intern() : field);
/* 155 */     this.type = 9;
/* 156 */     this.factory = comparator;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SortField(String field, SortComparatorSource comparator, boolean reverse)
/*     */   {
/* 165 */     this.field = (field != null ? field.intern() : field);
/* 166 */     this.type = 9;
/* 167 */     this.reverse = reverse;
/* 168 */     this.factory = comparator;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getField()
/*     */   {
/* 176 */     return this.field;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getType()
/*     */   {
/* 183 */     return this.type;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Locale getLocale()
/*     */   {
/* 191 */     return this.locale;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean getReverse()
/*     */   {
/* 198 */     return this.reverse;
/*     */   }
/*     */   
/*     */   public SortComparatorSource getFactory() {
/* 202 */     return this.factory;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 206 */     StringBuffer buffer = new StringBuffer();
/* 207 */     switch (this.type) {
/* 208 */     case 0:  buffer.append("<score>");
/* 209 */       break;
/*     */     case 1: 
/* 211 */       buffer.append("<doc>");
/* 212 */       break;
/*     */     case 9: 
/* 214 */       buffer.append("<custom:\"" + this.field + "\": " + this.factory + ">");
/*     */       
/* 216 */       break;
/*     */     default: 
/* 218 */       buffer.append("\"" + this.field + "\"");
/*     */     }
/*     */     
/*     */     
/* 222 */     if (this.locale != null) buffer.append("(" + this.locale + ")");
/* 223 */     if (this.reverse) { buffer.append('!');
/*     */     }
/* 225 */     return buffer.toString();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/SortField.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */