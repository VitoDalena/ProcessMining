/*     */ package org.deckfour.xes.extension.std;
/*     */ 
/*     */ import java.net.URI;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.deckfour.xes.extension.XExtension;
/*     */ import org.deckfour.xes.id.XID;
/*     */ import org.deckfour.xes.id.XIDFactory;
/*     */ import org.deckfour.xes.model.XAttribute;
/*     */ import org.deckfour.xes.model.XAttributeMap;
/*     */ import org.deckfour.xes.model.XEvent;
/*     */ import org.deckfour.xes.model.XTrace;
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
/*     */ public class XExtendedEvent
/*     */   implements XEvent
/*     */ {
/*     */   private XID id;
/*     */   protected XEvent original;
/*     */   
/*     */   public static XExtendedEvent wrap(XEvent event)
/*     */   {
/*  79 */     return new XExtendedEvent(event);
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
/*     */   public XExtendedEvent(XEvent original)
/*     */   {
/*  94 */     this.original = original;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/* 104 */     return XConceptExtension.instance().extractName(this.original);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 115 */     XConceptExtension.instance().assignName(this.original, name);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getInstance()
/*     */   {
/* 125 */     return XConceptExtension.instance().extractInstance(this.original);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setInstance(String instance)
/*     */   {
/* 136 */     XConceptExtension.instance().assignInstance(this.original, instance);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getTimestamp()
/*     */   {
/* 145 */     return XTimeExtension.instance().extractTimestamp(this.original);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTimestamp(Date timestamp)
/*     */   {
/* 155 */     XTimeExtension.instance().assignTimestamp(this.original, timestamp);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTimestamp(long timestamp)
/*     */   {
/* 165 */     XTimeExtension.instance().assignTimestamp(this.original, timestamp);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getResource()
/*     */   {
/* 175 */     return XOrganizationalExtension.instance().extractResource(this.original);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setResource(String resource)
/*     */   {
/* 186 */     XOrganizationalExtension.instance().assignResource(this.original, resource.trim());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getRole()
/*     */   {
/* 197 */     return XOrganizationalExtension.instance().extractRole(this.original);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setRole(String role)
/*     */   {
/* 207 */     XOrganizationalExtension.instance().assignRole(this.original, role.trim());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getGroup()
/*     */   {
/* 217 */     return XOrganizationalExtension.instance().extractGroup(this.original);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setGroup(String group)
/*     */   {
/* 227 */     XOrganizationalExtension.instance().assignGroup(this.original, group.trim());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getTransition()
/*     */   {
/* 238 */     return XLifecycleExtension.instance().extractTransition(this.original);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTransition(String transition)
/*     */   {
/* 249 */     XLifecycleExtension.instance().assignTransition(this.original, transition);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XLifecycleExtension.StandardModel getStandardTransition()
/*     */   {
/* 260 */     return XLifecycleExtension.instance().extractStandardTransition(this.original);
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
/*     */   public void setStandardTransition(XLifecycleExtension.StandardModel transition)
/*     */   {
/* 273 */     XLifecycleExtension.instance().assignStandardTransition(this.original, transition);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<String> getModelReferences()
/*     */   {
/* 284 */     return XSemanticExtension.instance().extractModelReferences(this.original);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setModelReferences(List<String> modelReferences)
/*     */   {
/* 295 */     XSemanticExtension.instance().assignModelReferences(this.original, modelReferences);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<URI> getModelReferenceURIs()
/*     */   {
/* 306 */     return XSemanticExtension.instance().extractModelReferenceURIs(this.original);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setModelReferenceURIs(List<URI> modelReferenceURIs)
/*     */   {
/* 318 */     XSemanticExtension.instance().assignModelReferenceUris(this.original, modelReferenceURIs);
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
/*     */   public XAttributeMap getAttributes()
/*     */   {
/* 331 */     return this.original.getAttributes();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Set<XExtension> getExtensions()
/*     */   {
/* 340 */     return this.original.getExtensions();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAttributes(XAttributeMap attributes)
/*     */   {
/* 351 */     this.original.setAttributes(attributes);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/*     */     try
/*     */     {
/* 360 */       XExtendedEvent clone = (XExtendedEvent)super.clone();
/* 361 */       clone.id = XIDFactory.instance().createId();
/* 362 */       clone.original = ((XEvent)this.original.clone());
/* 363 */       return clone;
/*     */     } catch (CloneNotSupportedException e) {
/* 365 */       e.printStackTrace(); }
/* 366 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 374 */     if ((o instanceof XExtendedEvent)) {
/* 375 */       return ((XExtendedEvent)o).id.equals(this.id);
/*     */     }
/* 377 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 385 */     return this.id.hashCode();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XID getID()
/*     */   {
/* 394 */     return this.id;
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
/*     */   public void accept(XVisitor visitor, XTrace trace)
/*     */   {
/* 407 */     visitor.visitEventPre(this, trace);
/*     */     
/*     */ 
/*     */ 
/* 411 */     for (XAttribute attribute : getAttributes().values()) {
/* 412 */       attribute.accept(visitor, this);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 417 */     visitor.visitEventPost(this, trace);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/extension/std/XExtendedEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */