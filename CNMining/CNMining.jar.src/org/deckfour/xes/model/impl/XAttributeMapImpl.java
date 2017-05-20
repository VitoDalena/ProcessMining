/*    */ package org.deckfour.xes.model.impl;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.deckfour.xes.model.XAttribute;
/*    */ import org.deckfour.xes.model.XAttributeMap;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class XAttributeMapImpl
/*    */   extends HashMap<String, XAttribute>
/*    */   implements XAttributeMap
/*    */ {
/*    */   private static final long serialVersionUID = 2701256420845748051L;
/*    */   
/*    */   public XAttributeMapImpl()
/*    */   {
/* 65 */     this(0);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public XAttributeMapImpl(int size)
/*    */   {
/* 74 */     super(size);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public XAttributeMapImpl(Map<String, XAttribute> template)
/*    */   {
/* 84 */     super(template.size());
/* 85 */     for (String key : template.keySet()) {
/* 86 */       put(key, template.get(key));
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public Object clone()
/*    */   {
/* 94 */     XAttributeMapImpl clone = (XAttributeMapImpl)super.clone();
/* 95 */     clone.clear();
/* 96 */     for (String key : keySet()) {
/* 97 */       clone.put(key, (XAttribute)((XAttribute)get(key)).clone());
/*    */     }
/* 99 */     return clone;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/model/impl/XAttributeMapImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */