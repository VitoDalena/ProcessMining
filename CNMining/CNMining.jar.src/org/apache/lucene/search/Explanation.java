/*     */ package org.apache.lucene.search;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Explanation
/*     */   implements Serializable
/*     */ {
/*     */   private float value;
/*     */   private String description;
/*     */   private ArrayList details;
/*     */   
/*     */   public Explanation() {}
/*     */   
/*     */   public Explanation(float value, String description)
/*     */   {
/*  30 */     this.value = value;
/*  31 */     this.description = description;
/*     */   }
/*     */   
/*     */ 
/*  35 */   public float getValue() { return this.value; }
/*     */   
/*  37 */   public void setValue(float value) { this.value = value; }
/*     */   
/*     */   public String getDescription() {
/*  40 */     return this.description;
/*     */   }
/*     */   
/*  43 */   public void setDescription(String description) { this.description = description; }
/*     */   
/*     */ 
/*     */   public Explanation[] getDetails()
/*     */   {
/*  48 */     if (this.details == null)
/*  49 */       return null;
/*  50 */     return (Explanation[])this.details.toArray(new Explanation[0]);
/*     */   }
/*     */   
/*     */   public void addDetail(Explanation detail)
/*     */   {
/*  55 */     if (this.details == null)
/*  56 */       this.details = new ArrayList();
/*  57 */     this.details.add(detail);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*  62 */   public String toString() { return toString(0); }
/*     */   
/*     */   private String toString(int depth) {
/*  65 */     StringBuffer buffer = new StringBuffer();
/*  66 */     for (int i = 0; i < depth; i++) {
/*  67 */       buffer.append("  ");
/*     */     }
/*  69 */     buffer.append(getValue());
/*  70 */     buffer.append(" = ");
/*  71 */     buffer.append(getDescription());
/*  72 */     buffer.append("\n");
/*     */     
/*  74 */     Explanation[] details = getDetails();
/*  75 */     if (details != null) {
/*  76 */       for (int i = 0; i < details.length; i++) {
/*  77 */         buffer.append(details[i].toString(depth + 1));
/*     */       }
/*     */     }
/*     */     
/*  81 */     return buffer.toString();
/*     */   }
/*     */   
/*     */ 
/*     */   public String toHtml()
/*     */   {
/*  87 */     StringBuffer buffer = new StringBuffer();
/*  88 */     buffer.append("<ul>\n");
/*     */     
/*  90 */     buffer.append("<li>");
/*  91 */     buffer.append(getValue());
/*  92 */     buffer.append(" = ");
/*  93 */     buffer.append(getDescription());
/*  94 */     buffer.append("</li>\n");
/*     */     
/*  96 */     Explanation[] details = getDetails();
/*  97 */     if (details != null) {
/*  98 */       for (int i = 0; i < details.length; i++) {
/*  99 */         buffer.append(details[i].toHtml());
/*     */       }
/*     */     }
/*     */     
/* 103 */     buffer.append("</ul>\n");
/*     */     
/* 105 */     return buffer.toString();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/Explanation.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */