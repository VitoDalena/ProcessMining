/*    */ package org.deckfour.xes.model.buffered;
/*    */ 
/*    */ import org.deckfour.xes.nikefs2.NikeFS2StorageProvider;
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
/*    */ public class XAttributeMapBufferedImpl
/*    */   extends XAbstractAttributeMapBufferedImpl
/*    */ {
/*    */   public XAttributeMapBufferedImpl()
/*    */   {
/* 59 */     super(new XAttributeMapSerializerImpl());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public XAttributeMapBufferedImpl(NikeFS2StorageProvider provider)
/*    */   {
/* 68 */     super(provider, new XAttributeMapSerializerImpl());
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/model/buffered/XAttributeMapBufferedImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */