/*     */ package org.apache.lucene.search;
/*     */ 
/*     */ import java.io.Serializable;
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
/*     */ public class Sort
/*     */   implements Serializable
/*     */ {
/* 106 */   public static final Sort RELEVANCE = new Sort();
/*     */   
/*     */ 
/* 109 */   public static final Sort INDEXORDER = new Sort(SortField.FIELD_DOC);
/*     */   
/*     */ 
/*     */ 
/*     */   SortField[] fields;
/*     */   
/*     */ 
/*     */ 
/*     */   public Sort()
/*     */   {
/* 119 */     this(new SortField[] { SortField.FIELD_SCORE, SortField.FIELD_DOC });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Sort(String field)
/*     */   {
/* 129 */     setSort(field, false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Sort(String field, boolean reverse)
/*     */   {
/* 139 */     setSort(field, reverse);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Sort(String[] fields)
/*     */   {
/* 149 */     setSort(fields);
/*     */   }
/*     */   
/*     */ 
/*     */   public Sort(SortField field)
/*     */   {
/* 155 */     setSort(field);
/*     */   }
/*     */   
/*     */ 
/*     */   public Sort(SortField[] fields)
/*     */   {
/* 161 */     setSort(fields);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public final void setSort(String field)
/*     */   {
/* 168 */     setSort(field, false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setSort(String field, boolean reverse)
/*     */   {
/* 175 */     SortField[] nfields = { new SortField(field, 2, reverse), SortField.FIELD_DOC };
/*     */     
/*     */ 
/*     */ 
/* 179 */     this.fields = nfields;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setSort(String[] fieldnames)
/*     */   {
/* 185 */     int n = fieldnames.length;
/* 186 */     SortField[] nfields = new SortField[n];
/* 187 */     for (int i = 0; i < n; i++) {
/* 188 */       nfields[i] = new SortField(fieldnames[i], 2);
/*     */     }
/* 190 */     this.fields = nfields;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setSort(SortField field)
/*     */   {
/* 196 */     this.fields = new SortField[] { field };
/*     */   }
/*     */   
/*     */ 
/*     */   public void setSort(SortField[] fields)
/*     */   {
/* 202 */     this.fields = fields;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 206 */     StringBuffer buffer = new StringBuffer();
/*     */     
/* 208 */     for (int i = 0; i < this.fields.length; i++) {
/* 209 */       buffer.append(this.fields[i].toString());
/* 210 */       if (i + 1 < this.fields.length) {
/* 211 */         buffer.append(',');
/*     */       }
/*     */     }
/* 214 */     return buffer.toString();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/Sort.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */