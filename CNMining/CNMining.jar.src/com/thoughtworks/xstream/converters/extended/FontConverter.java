/*    */ package com.thoughtworks.xstream.converters.extended;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.Converter;
/*    */ import com.thoughtworks.xstream.converters.MarshallingContext;
/*    */ import com.thoughtworks.xstream.converters.UnmarshallingContext;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*    */ import java.awt.Font;
/*    */ import java.util.Map;
/*    */ import javax.swing.plaf.FontUIResource;
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
/*    */ public class FontConverter
/*    */   implements Converter
/*    */ {
/*    */   public boolean canConvert(Class type)
/*    */   {
/* 30 */     return (type.getName().equals("java.awt.Font")) || (type.getName().equals("javax.swing.plaf.FontUIResource"));
/*    */   }
/*    */   
/*    */   public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
/* 34 */     Font font = (Font)source;
/* 35 */     Map attributes = font.getAttributes();
/* 36 */     writer.startNode("attributes");
/* 37 */     context.convertAnother(attributes);
/* 38 */     writer.endNode();
/*    */   }
/*    */   
/*    */   public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
/* 42 */     reader.moveDown();
/* 43 */     Map attributes = (Map)context.convertAnother(null, Map.class);
/* 44 */     reader.moveUp();
/* 45 */     Font font = Font.getFont(attributes);
/* 46 */     if (context.getRequiredType() == FontUIResource.class) {
/* 47 */       return new FontUIResource(font);
/*    */     }
/* 49 */     return font;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/extended/FontConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */