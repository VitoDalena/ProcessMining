/*    */ package org.deckfour.xes.model;
/*    */ 
/*    */ import java.util.Date;
/*    */ import org.deckfour.xes.model.impl.XsDateTimeFormat;
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
/*    */ public abstract interface XAttributeTimestamp
/*    */   extends XAttribute
/*    */ {
/* 56 */   public static final XsDateTimeFormat FORMATTER = new XsDateTimeFormat();
/*    */   
/*    */   public abstract void setValue(Date paramDate);
/*    */   
/*    */   public abstract void setValueMillis(long paramLong);
/*    */   
/*    */   public abstract Date getValue();
/*    */   
/*    */   public abstract long getValueMillis();
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/model/XAttributeTimestamp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */