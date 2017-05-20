/*    */ package com.thoughtworks.xstream.converters.extended;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.reflection.ReflectionConverter;
/*    */ import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;
/*    */ import com.thoughtworks.xstream.mapper.Mapper;
/*    */ import javax.swing.LookAndFeel;
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
/*    */ public class LookAndFeelConverter
/*    */   extends ReflectionConverter
/*    */ {
/*    */   public LookAndFeelConverter(Mapper mapper, ReflectionProvider reflectionProvider)
/*    */   {
/* 40 */     super(mapper, reflectionProvider);
/*    */   }
/*    */   
/*    */   public boolean canConvert(Class type) {
/* 44 */     return LookAndFeel.class.isAssignableFrom(type);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/extended/LookAndFeelConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */