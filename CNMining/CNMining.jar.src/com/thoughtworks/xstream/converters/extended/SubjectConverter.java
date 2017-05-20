/*     */ package com.thoughtworks.xstream.converters.extended;
/*     */ 
/*     */ import com.thoughtworks.xstream.converters.MarshallingContext;
/*     */ import com.thoughtworks.xstream.converters.UnmarshallingContext;
/*     */ import com.thoughtworks.xstream.converters.collections.AbstractCollectionConverter;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*     */ import com.thoughtworks.xstream.mapper.Mapper;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import javax.security.auth.Subject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SubjectConverter
/*     */   extends AbstractCollectionConverter
/*     */ {
/*     */   public SubjectConverter(Mapper mapper)
/*     */   {
/*  38 */     super(mapper);
/*     */   }
/*     */   
/*     */   public boolean canConvert(Class type) {
/*  42 */     return type.equals(Subject.class);
/*     */   }
/*     */   
/*     */   public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
/*  46 */     Subject subject = (Subject)source;
/*  47 */     marshalPrincipals(subject.getPrincipals(), writer, context);
/*  48 */     marshalPublicCredentials(subject.getPublicCredentials(), writer, context);
/*  49 */     marshalPrivateCredentials(subject.getPrivateCredentials(), writer, context);
/*  50 */     marshalReadOnly(subject.isReadOnly(), writer);
/*     */   }
/*     */   
/*     */   protected void marshalPrincipals(Set principals, HierarchicalStreamWriter writer, MarshallingContext context) {
/*  54 */     writer.startNode("principals");
/*  55 */     for (Iterator iter = principals.iterator(); iter.hasNext();) {
/*  56 */       Object principal = iter.next();
/*  57 */       writeItem(principal, context, writer);
/*     */     }
/*  59 */     writer.endNode();
/*     */   }
/*     */   
/*     */ 
/*     */   protected void marshalPublicCredentials(Set pubCredentials, HierarchicalStreamWriter writer, MarshallingContext context) {}
/*     */   
/*     */   protected void marshalPrivateCredentials(Set privCredentials, HierarchicalStreamWriter writer, MarshallingContext context) {}
/*     */   
/*     */   protected void marshalReadOnly(boolean readOnly, HierarchicalStreamWriter writer)
/*     */   {
/*  69 */     writer.startNode("readOnly");
/*  70 */     writer.setValue(String.valueOf(readOnly));
/*  71 */     writer.endNode();
/*     */   }
/*     */   
/*     */   public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
/*  75 */     Set principals = unmarshalPrincipals(reader, context);
/*  76 */     Set publicCredentials = unmarshalPublicCredentials(reader, context);
/*  77 */     Set privateCredentials = unmarshalPrivateCredentials(reader, context);
/*  78 */     boolean readOnly = unmarshalReadOnly(reader);
/*  79 */     return new Subject(readOnly, principals, publicCredentials, privateCredentials);
/*     */   }
/*     */   
/*     */   protected Set unmarshalPrincipals(HierarchicalStreamReader reader, UnmarshallingContext context) {
/*  83 */     return populateSet(reader, context);
/*     */   }
/*     */   
/*     */   protected Set unmarshalPublicCredentials(HierarchicalStreamReader reader, UnmarshallingContext context) {
/*  87 */     return Collections.EMPTY_SET;
/*     */   }
/*     */   
/*     */   protected Set unmarshalPrivateCredentials(HierarchicalStreamReader reader, UnmarshallingContext context) {
/*  91 */     return Collections.EMPTY_SET;
/*     */   }
/*     */   
/*     */   protected boolean unmarshalReadOnly(HierarchicalStreamReader reader) {
/*  95 */     reader.moveDown();
/*  96 */     boolean readOnly = Boolean.getBoolean(reader.getValue());
/*  97 */     reader.moveUp();
/*  98 */     return readOnly;
/*     */   }
/*     */   
/*     */   protected Set populateSet(HierarchicalStreamReader reader, UnmarshallingContext context) {
/* 102 */     Set set = new HashSet();
/* 103 */     reader.moveDown();
/* 104 */     while (reader.hasMoreChildren()) {
/* 105 */       reader.moveDown();
/* 106 */       Object elementl = readItem(reader, context, set);
/* 107 */       reader.moveUp();
/* 108 */       set.add(elementl);
/*     */     }
/* 110 */     reader.moveUp();
/* 111 */     return set;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/extended/SubjectConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */