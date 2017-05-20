/*     */ package org.apache.lucene.document;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Document
/*     */   implements Serializable
/*     */ {
/*  43 */   List fields = new Vector();
/*  44 */   private float boost = 1.0F;
/*     */   
/*     */ 
/*     */ 
/*     */ 
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
/*  60 */     this.boost = boost;
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
/*  75 */     return this.boost;
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
/*     */   public final void add(Field field)
/*     */   {
/*  89 */     this.fields.add(field);
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
/*     */   public final void removeField(String name)
/*     */   {
/* 103 */     Iterator it = this.fields.iterator();
/* 104 */     while (it.hasNext()) {
/* 105 */       Field field = (Field)it.next();
/* 106 */       if (field.name().equals(name)) {
/* 107 */         it.remove();
/* 108 */         return;
/*     */       }
/*     */     }
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
/*     */   public final void removeFields(String name)
/*     */   {
/* 123 */     Iterator it = this.fields.iterator();
/* 124 */     while (it.hasNext()) {
/* 125 */       Field field = (Field)it.next();
/* 126 */       if (field.name().equals(name)) {
/* 127 */         it.remove();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final Field getField(String name)
/*     */   {
/* 137 */     for (int i = 0; i < this.fields.size(); i++) {
/* 138 */       Field field = (Field)this.fields.get(i);
/* 139 */       if (field.name().equals(name))
/* 140 */         return field;
/*     */     }
/* 142 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final String get(String name)
/*     */   {
/* 150 */     Field field = getField(name);
/* 151 */     if (field != null) {
/* 152 */       return field.stringValue();
/*     */     }
/* 154 */     return null;
/*     */   }
/*     */   
/*     */   public final Enumeration fields()
/*     */   {
/* 159 */     return ((Vector)this.fields).elements();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final Field[] getFields(String name)
/*     */   {
/* 170 */     List result = new ArrayList();
/* 171 */     for (int i = 0; i < this.fields.size(); i++) {
/* 172 */       Field field = (Field)this.fields.get(i);
/* 173 */       if (field.name().equals(name)) {
/* 174 */         result.add(field);
/*     */       }
/*     */     }
/*     */     
/* 178 */     if (result.size() == 0) {
/* 179 */       return null;
/*     */     }
/* 181 */     return (Field[])result.toArray(new Field[result.size()]);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final String[] getValues(String name)
/*     */   {
/* 192 */     Field[] namedFields = getFields(name);
/* 193 */     if (namedFields == null)
/* 194 */       return null;
/* 195 */     String[] values = new String[namedFields.length];
/* 196 */     for (int i = 0; i < namedFields.length; i++) {
/* 197 */       values[i] = namedFields[i].stringValue();
/*     */     }
/* 199 */     return values;
/*     */   }
/*     */   
/*     */   public final String toString()
/*     */   {
/* 204 */     StringBuffer buffer = new StringBuffer();
/* 205 */     buffer.append("Document<");
/* 206 */     for (int i = 0; i < this.fields.size(); i++) {
/* 207 */       Field field = (Field)this.fields.get(i);
/* 208 */       buffer.append(field.toString());
/* 209 */       if (i != this.fields.size() - 1)
/* 210 */         buffer.append(" ");
/*     */     }
/* 212 */     buffer.append(">");
/* 213 */     return buffer.toString();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/document/Document.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */