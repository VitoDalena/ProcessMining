/*    */ package com.thoughtworks.xstream.converters.extended;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.Converter;
/*    */ import com.thoughtworks.xstream.converters.MarshallingContext;
/*    */ import com.thoughtworks.xstream.converters.UnmarshallingContext;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*    */ import java.util.regex.Pattern;
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
/*    */ public class RegexPatternConverter
/*    */   implements Converter
/*    */ {
/*    */   private Converter defaultConverter;
/*    */   
/*    */   public RegexPatternConverter(Converter defaultConverter)
/*    */   {
/* 30 */     this.defaultConverter = defaultConverter;
/*    */   }
/*    */   
/*    */   public boolean canConvert(Class type) {
/* 34 */     return type.equals(Pattern.class);
/*    */   }
/*    */   
/*    */   public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
/* 38 */     this.defaultConverter.marshal(source, writer, context);
/*    */   }
/*    */   
/*    */   public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
/* 42 */     Pattern notCompiled = (Pattern)this.defaultConverter.unmarshal(reader, context);
/* 43 */     return Pattern.compile(notCompiled.pattern(), notCompiled.flags());
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/extended/RegexPatternConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */