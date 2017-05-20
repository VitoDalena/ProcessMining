/*    */ package com.thoughtworks.xstream.converters.extended;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;
/*    */ import javax.xml.datatype.DatatypeConfigurationException;
/*    */ import javax.xml.datatype.DatatypeFactory;
/*    */ import javax.xml.datatype.Duration;
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
/*    */ public class DurationConverter
/*    */   extends AbstractSingleValueConverter
/*    */ {
/*    */   private final DatatypeFactory factory;
/*    */   
/*    */   public DurationConverter()
/*    */     throws DatatypeConfigurationException
/*    */   {
/* 33 */     this(DatatypeFactory.newInstance());
/*    */   }
/*    */   
/*    */   public DurationConverter(DatatypeFactory factory) {
/* 37 */     this.factory = factory;
/*    */   }
/*    */   
/*    */   public boolean canConvert(Class c) {
/* 41 */     return (this.factory != null) && (Duration.class.isAssignableFrom(c));
/*    */   }
/*    */   
/*    */   public Object fromString(String s) {
/* 45 */     return this.factory.newDuration(s);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/extended/DurationConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */