/*     */ package org.deckfour.xes.extension.std;
/*     */ 
/*     */ import java.net.URI;
/*     */ import java.util.Date;
/*     */ import java.util.HashSet;
/*     */ import org.deckfour.xes.extension.XExtension;
/*     */ import org.deckfour.xes.factory.XFactory;
/*     */ import org.deckfour.xes.factory.XFactoryRegistry;
/*     */ import org.deckfour.xes.info.XGlobalAttributeNameMap;
/*     */ import org.deckfour.xes.model.XAttribute;
/*     */ import org.deckfour.xes.model.XAttributeMap;
/*     */ import org.deckfour.xes.model.XAttributeTimestamp;
/*     */ import org.deckfour.xes.model.XEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XTimeExtension
/*     */   extends XExtension
/*     */ {
/*     */   private static final long serialVersionUID = -3632061569016038500L;
/*  69 */   public static final URI EXTENSION_URI = URI.create("http://www.xes-standard.org/time.xesext");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final String KEY_TIMESTAMP = "time:timestamp";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static XAttributeTimestamp ATTR_TIMESTAMP;
/*     */   
/*     */ 
/*     */ 
/*  83 */   private static XTimeExtension singleton = new XTimeExtension();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static XTimeExtension instance()
/*     */   {
/*  91 */     return singleton;
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/*  95 */     return singleton;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private XTimeExtension()
/*     */   {
/* 102 */     super("Time", "time", EXTENSION_URI);
/* 103 */     XFactory factory = (XFactory)XFactoryRegistry.instance().currentDefault();
/* 104 */     ATTR_TIMESTAMP = factory.createAttributeTimestamp("time:timestamp", 0L, this);
/*     */     
/* 106 */     this.eventAttributes.add((XAttribute)ATTR_TIMESTAMP.clone());
/*     */     
/* 108 */     XGlobalAttributeNameMap.instance().registerMapping("EN", "time:timestamp", "Timestamp");
/*     */     
/*     */ 
/* 111 */     XGlobalAttributeNameMap.instance().registerMapping("DE", "time:timestamp", "Zeitstempel");
/*     */     
/*     */ 
/* 114 */     XGlobalAttributeNameMap.instance().registerMapping("FR", "time:timestamp", "Horodateur");
/*     */     
/*     */ 
/* 117 */     XGlobalAttributeNameMap.instance().registerMapping("ES", "time:timestamp", "Timestamp");
/*     */     
/*     */ 
/* 120 */     XGlobalAttributeNameMap.instance().registerMapping("PT", "time:timestamp", "Timestamp");
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
/*     */   public Date extractTimestamp(XEvent event)
/*     */   {
/* 134 */     XAttributeTimestamp timestampAttribute = (XAttributeTimestamp)event.getAttributes().get("time:timestamp");
/*     */     
/* 136 */     if (timestampAttribute == null) {
/* 137 */       return null;
/*     */     }
/* 139 */     return timestampAttribute.getValue();
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
/*     */   public void assignTimestamp(XEvent event, Date timestamp)
/*     */   {
/* 152 */     assignTimestamp(event, timestamp.getTime());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void assignTimestamp(XEvent event, long time)
/*     */   {
/* 164 */     XAttributeTimestamp attr = (XAttributeTimestamp)ATTR_TIMESTAMP.clone();
/* 165 */     attr.setValueMillis(time);
/* 166 */     event.getAttributes().put("time:timestamp", attr);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/extension/std/XTimeExtension.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */