/*     */ package org.deckfour.xes.classification;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import org.deckfour.xes.model.XAttribute;
/*     */ import org.deckfour.xes.model.XAttributeMap;
/*     */ import org.deckfour.xes.model.XEvent;
/*     */ import org.deckfour.xes.model.XLog;
/*     */ import org.deckfour.xes.model.XVisitor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XEventAttributeClassifier
/*     */   implements XEventClassifier, Comparable<XEventAttributeClassifier>
/*     */ {
/*     */   protected String[] keys;
/*     */   protected String name;
/*     */   
/*     */   public XEventAttributeClassifier(String name, String... keys)
/*     */   {
/*  77 */     this.name = name;
/*  78 */     this.keys = keys;
/*  79 */     Arrays.sort(keys);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getClassIdentity(XEvent event)
/*     */   {
/*  90 */     StringBuilder sb = new StringBuilder();
/*  91 */     for (int i = 0; i < this.keys.length; i++) {
/*  92 */       XAttribute attribute = (XAttribute)event.getAttributes().get(this.keys[i]);
/*     */       
/*     */ 
/*     */ 
/*  96 */       if (attribute != null) {
/*  97 */         sb.append(attribute.toString().trim());
/*     */       }
/*  99 */       if (i < this.keys.length - 1) {
/* 100 */         sb.append("+");
/*     */       }
/*     */     }
/* 103 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 113 */     this.name = name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String name()
/*     */   {
/* 122 */     return this.name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean sameEventClass(XEvent eventA, XEvent eventB)
/*     */   {
/* 133 */     return getClassIdentity(eventA).equals(getClassIdentity(eventB));
/*     */   }
/*     */   
/*     */   public String toString() {
/* 137 */     return name();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String[] getDefiningAttributeKeys()
/*     */   {
/* 148 */     return this.keys;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int compareTo(XEventAttributeClassifier o)
/*     */   {
/* 157 */     if (!o.name.equals(this.name)) {
/* 158 */       return this.name.compareTo(o.name);
/*     */     }
/* 160 */     if (this.keys.length != o.keys.length) {
/* 161 */       return this.keys.length - o.keys.length;
/*     */     }
/* 163 */     for (int i = 0; i < this.keys.length; i++) {
/* 164 */       if (!this.keys[i].equals(o.keys[i])) {
/* 165 */         return this.keys[i].compareTo(o.keys[i]);
/*     */       }
/*     */     }
/*     */     
/* 169 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 173 */     if (!(o instanceof XEventAttributeClassifier)) {
/* 174 */       return false;
/*     */     }
/* 176 */     return compareTo((XEventAttributeClassifier)o) == 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void accept(XVisitor visitor, XLog log)
/*     */   {
/* 183 */     visitor.visitClassifierPre(this, log);
/*     */     
/*     */ 
/*     */ 
/* 187 */     visitor.visitClassifierPost(this, log);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/classification/XEventAttributeClassifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */