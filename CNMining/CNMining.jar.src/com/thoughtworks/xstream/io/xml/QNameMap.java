/*    */ package com.thoughtworks.xstream.io.xml;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import javax.xml.namespace.QName;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class QNameMap
/*    */ {
/*    */   private Map qnameToJava;
/*    */   private Map javaToQName;
/* 32 */   private String defaultPrefix = "";
/* 33 */   private String defaultNamespace = "";
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public String getJavaClassName(QName qname)
/*    */   {
/* 41 */     if (this.qnameToJava != null) {
/* 42 */       String answer = (String)this.qnameToJava.get(qname);
/* 43 */       if (answer != null) {
/* 44 */         return answer;
/*    */       }
/*    */     }
/* 47 */     return qname.getLocalPart();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public QName getQName(String javaClassName)
/*    */   {
/* 56 */     if (this.javaToQName != null) {
/* 57 */       QName answer = (QName)this.javaToQName.get(javaClassName);
/* 58 */       if (answer != null) {
/* 59 */         return answer;
/*    */       }
/*    */     }
/* 62 */     return new QName(this.defaultNamespace, javaClassName, this.defaultPrefix);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public synchronized void registerMapping(QName qname, String javaClassName)
/*    */   {
/* 69 */     if (this.javaToQName == null) {
/* 70 */       this.javaToQName = Collections.synchronizedMap(new HashMap());
/*    */     }
/* 72 */     if (this.qnameToJava == null) {
/* 73 */       this.qnameToJava = Collections.synchronizedMap(new HashMap());
/*    */     }
/* 75 */     this.javaToQName.put(javaClassName, qname);
/* 76 */     this.qnameToJava.put(qname, javaClassName);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public synchronized void registerMapping(QName qname, Class type)
/*    */   {
/* 83 */     registerMapping(qname, type.getName());
/*    */   }
/*    */   
/*    */   public String getDefaultNamespace() {
/* 87 */     return this.defaultNamespace;
/*    */   }
/*    */   
/*    */   public void setDefaultNamespace(String defaultNamespace) {
/* 91 */     this.defaultNamespace = defaultNamespace;
/*    */   }
/*    */   
/*    */   public String getDefaultPrefix() {
/* 95 */     return this.defaultPrefix;
/*    */   }
/*    */   
/*    */   public void setDefaultPrefix(String defaultPrefix) {
/* 99 */     this.defaultPrefix = defaultPrefix;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/QNameMap.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */