/*     */ package org.deckfour.xes.extension;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.net.URI;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import org.deckfour.xes.model.XAttribute;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XExtension
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -677323212952951508L;
/*     */   protected String name;
/*     */   protected String prefix;
/*     */   protected URI uri;
/*     */   protected HashSet<XAttribute> allAttributes;
/*     */   protected HashSet<XAttribute> logAttributes;
/*     */   protected HashSet<XAttribute> traceAttributes;
/*     */   protected HashSet<XAttribute> eventAttributes;
/*     */   protected HashSet<XAttribute> metaAttributes;
/*     */   
/*     */   protected XExtension(String name, String prefix, URI uri)
/*     */   {
/* 133 */     this.name = name;
/* 134 */     this.prefix = prefix;
/* 135 */     this.uri = uri;
/* 136 */     this.allAttributes = null;
/* 137 */     this.logAttributes = new HashSet();
/* 138 */     this.traceAttributes = new HashSet();
/* 139 */     this.eventAttributes = new HashSet();
/* 140 */     this.metaAttributes = new HashSet();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/* 147 */     return this.name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public URI getUri()
/*     */   {
/* 157 */     return this.uri;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getPrefix()
/*     */   {
/* 166 */     return this.prefix;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Collection<XAttribute> getDefinedAttributes()
/*     */   {
/* 174 */     if (this.allAttributes == null)
/*     */     {
/* 176 */       this.allAttributes = new HashSet();
/* 177 */       this.allAttributes.addAll(getLogAttributes());
/* 178 */       this.allAttributes.addAll(getTraceAttributes());
/* 179 */       this.allAttributes.addAll(getEventAttributes());
/* 180 */       this.allAttributes.addAll(getEventAttributes());
/*     */     }
/* 182 */     return this.allAttributes;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Collection<XAttribute> getLogAttributes()
/*     */   {
/* 190 */     return this.logAttributes;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Collection<XAttribute> getTraceAttributes()
/*     */   {
/* 198 */     return this.traceAttributes;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Collection<XAttribute> getEventAttributes()
/*     */   {
/* 206 */     return this.eventAttributes;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Collection<XAttribute> getMetaAttributes()
/*     */   {
/* 214 */     return this.metaAttributes;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 224 */     if ((obj instanceof XExtension)) {
/* 225 */       return this.uri.equals(((XExtension)obj).uri);
/*     */     }
/* 227 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 238 */     return this.uri.hashCode();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 248 */     return this.name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void accept(XVisitor visitor, XLog log)
/*     */   {
/* 258 */     visitor.visitExtensionPre(this, log);
/*     */     
/*     */ 
/*     */ 
/* 262 */     visitor.visitExtensionPost(this, log);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/extension/XExtension.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */