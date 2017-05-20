/*    */ package com.thoughtworks.xstream.converters.extended;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.Converter;
/*    */ import com.thoughtworks.xstream.converters.MarshallingContext;
/*    */ import com.thoughtworks.xstream.converters.UnmarshallingContext;
/*    */ import com.thoughtworks.xstream.io.ExtendedHierarchicalStreamWriterHelper;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*    */ import java.awt.Color;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
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
/*    */ public class ColorConverter
/*    */   implements Converter
/*    */ {
/*    */   public boolean canConvert(Class type)
/*    */   {
/* 36 */     return type.getName().equals("java.awt.Color");
/*    */   }
/*    */   
/*    */   public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
/* 40 */     Color color = (Color)source;
/* 41 */     write("red", color.getRed(), writer);
/* 42 */     write("green", color.getGreen(), writer);
/* 43 */     write("blue", color.getBlue(), writer);
/* 44 */     write("alpha", color.getAlpha(), writer);
/*    */   }
/*    */   
/*    */   public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
/* 48 */     Map elements = new HashMap();
/* 49 */     while (reader.hasMoreChildren()) {
/* 50 */       reader.moveDown();
/* 51 */       elements.put(reader.getNodeName(), Integer.valueOf(reader.getValue()));
/* 52 */       reader.moveUp();
/*    */     }
/* 54 */     return new Color(((Integer)elements.get("red")).intValue(), ((Integer)elements.get("green")).intValue(), ((Integer)elements.get("blue")).intValue(), ((Integer)elements.get("alpha")).intValue());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   private void write(String fieldName, int value, HierarchicalStreamWriter writer)
/*    */   {
/* 61 */     ExtendedHierarchicalStreamWriterHelper.startNode(writer, fieldName, Integer.TYPE);
/* 62 */     writer.setValue(String.valueOf(value));
/* 63 */     writer.endNode();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/extended/ColorConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */