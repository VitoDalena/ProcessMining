/*     */ package com.thoughtworks.xstream.io.xml.xppdom;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.xmlpull.v1.XmlPullParser;
/*     */ import org.xmlpull.v1.XmlPullParserException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XppDom
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String name;
/*     */   private String value;
/*     */   private Map attributes;
/*     */   private List childList;
/*     */   private transient Map childMap;
/*     */   private XppDom parent;
/*     */   
/*     */   public XppDom(String name)
/*     */   {
/*  44 */     this.name = name;
/*  45 */     this.childList = new ArrayList();
/*  46 */     this.childMap = new HashMap();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/*  54 */     return this.name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getValue()
/*     */   {
/*  62 */     return this.value;
/*     */   }
/*     */   
/*     */   public void setValue(String value) {
/*  66 */     this.value = value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String[] getAttributeNames()
/*     */   {
/*  74 */     if (null == this.attributes) {
/*  75 */       return new String[0];
/*     */     }
/*  77 */     return (String[])this.attributes.keySet().toArray(new String[0]);
/*     */   }
/*     */   
/*     */   public String getAttribute(String name)
/*     */   {
/*  82 */     return null != this.attributes ? (String)this.attributes.get(name) : null;
/*     */   }
/*     */   
/*     */   public void setAttribute(String name, String value) {
/*  86 */     if (null == this.attributes) {
/*  87 */       this.attributes = new HashMap();
/*     */     }
/*     */     
/*  90 */     this.attributes.put(name, value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public XppDom getChild(int i)
/*     */   {
/*  98 */     return (XppDom)this.childList.get(i);
/*     */   }
/*     */   
/*     */   public XppDom getChild(String name) {
/* 102 */     return (XppDom)this.childMap.get(name);
/*     */   }
/*     */   
/*     */   public void addChild(XppDom xpp3Dom) {
/* 106 */     xpp3Dom.setParent(this);
/* 107 */     this.childList.add(xpp3Dom);
/* 108 */     this.childMap.put(xpp3Dom.getName(), xpp3Dom);
/*     */   }
/*     */   
/*     */   public XppDom[] getChildren() {
/* 112 */     if (null == this.childList) {
/* 113 */       return new XppDom[0];
/*     */     }
/* 115 */     return (XppDom[])this.childList.toArray(new XppDom[0]);
/*     */   }
/*     */   
/*     */   public XppDom[] getChildren(String name)
/*     */   {
/* 120 */     if (null == this.childList) {
/* 121 */       return new XppDom[0];
/*     */     }
/* 123 */     ArrayList children = new ArrayList();
/* 124 */     int size = this.childList.size();
/*     */     
/* 126 */     for (int i = 0; i < size; i++) {
/* 127 */       XppDom configuration = (XppDom)this.childList.get(i);
/* 128 */       if (name.equals(configuration.getName())) {
/* 129 */         children.add(configuration);
/*     */       }
/*     */     }
/*     */     
/* 133 */     return (XppDom[])children.toArray(new XppDom[0]);
/*     */   }
/*     */   
/*     */   public int getChildCount()
/*     */   {
/* 138 */     if (null == this.childList) {
/* 139 */       return 0;
/*     */     }
/*     */     
/* 142 */     return this.childList.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public XppDom getParent()
/*     */   {
/* 150 */     return this.parent;
/*     */   }
/*     */   
/*     */   public void setParent(XppDom parent) {
/* 154 */     this.parent = parent;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   Object readResolve()
/*     */   {
/* 162 */     this.childMap = new HashMap();
/* 163 */     for (Iterator iter = this.childList.iterator(); iter.hasNext();) {
/* 164 */       XppDom element = (XppDom)iter.next();
/* 165 */       this.childMap.put(element.getName(), element);
/*     */     }
/* 167 */     return this;
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
/*     */ 
/*     */ 
/*     */   public static XppDom build(XmlPullParser parser)
/*     */     throws XmlPullParserException, IOException
/*     */   {
/* 185 */     List elements = new ArrayList();
/* 186 */     List values = new ArrayList();
/* 187 */     XppDom node = null;
/*     */     
/* 189 */     int eventType = parser.getEventType();
/* 190 */     while (eventType != 1) {
/* 191 */       if (eventType == 2) {
/* 192 */         String rawName = parser.getName();
/*     */         
/*     */ 
/* 195 */         XppDom child = new Xpp3Dom(rawName);
/*     */         
/* 197 */         int depth = elements.size();
/* 198 */         if (depth > 0) {
/* 199 */           XppDom parent = (XppDom)elements.get(depth - 1);
/* 200 */           parent.addChild(child);
/*     */         }
/*     */         
/* 203 */         elements.add(child);
/* 204 */         values.add(new StringBuffer());
/*     */         
/* 206 */         int attributesSize = parser.getAttributeCount();
/* 207 */         for (int i = 0; i < attributesSize; i++) {
/* 208 */           String name = parser.getAttributeName(i);
/* 209 */           String value = parser.getAttributeValue(i);
/* 210 */           child.setAttribute(name, value);
/*     */         }
/* 212 */       } else if (eventType == 4) {
/* 213 */         int depth = values.size() - 1;
/* 214 */         StringBuffer valueBuffer = (StringBuffer)values.get(depth);
/* 215 */         valueBuffer.append(parser.getText());
/* 216 */       } else if (eventType == 3) {
/* 217 */         int depth = elements.size() - 1;
/* 218 */         XppDom finalNode = (XppDom)elements.remove(depth);
/* 219 */         String accumulatedValue = values.remove(depth).toString();
/*     */         String finishedValue;
/*     */         String finishedValue;
/* 222 */         if (0 == accumulatedValue.length()) {
/* 223 */           finishedValue = null;
/*     */         } else {
/* 225 */           finishedValue = accumulatedValue;
/*     */         }
/*     */         
/* 228 */         finalNode.setValue(finishedValue);
/* 229 */         if (0 == depth) {
/* 230 */           node = finalNode;
/*     */         }
/*     */       }
/*     */       
/* 234 */       eventType = parser.next();
/*     */     }
/*     */     
/* 237 */     return node;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/xppdom/XppDom.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */