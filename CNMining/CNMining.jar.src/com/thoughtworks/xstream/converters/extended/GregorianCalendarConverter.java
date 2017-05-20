/*    */ package com.thoughtworks.xstream.converters.extended;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.Converter;
/*    */ import com.thoughtworks.xstream.converters.MarshallingContext;
/*    */ import com.thoughtworks.xstream.converters.UnmarshallingContext;
/*    */ import com.thoughtworks.xstream.io.ExtendedHierarchicalStreamWriterHelper;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*    */ import java.util.Date;
/*    */ import java.util.GregorianCalendar;
/*    */ import java.util.TimeZone;
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
/*    */ public class GregorianCalendarConverter
/*    */   implements Converter
/*    */ {
/*    */   public boolean canConvert(Class type)
/*    */   {
/* 35 */     return type.equals(GregorianCalendar.class);
/*    */   }
/*    */   
/*    */   public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
/* 39 */     GregorianCalendar calendar = (GregorianCalendar)source;
/* 40 */     ExtendedHierarchicalStreamWriterHelper.startNode(writer, "time", Long.TYPE);
/* 41 */     long timeInMillis = calendar.getTime().getTime();
/* 42 */     writer.setValue(String.valueOf(timeInMillis));
/* 43 */     writer.endNode();
/* 44 */     ExtendedHierarchicalStreamWriterHelper.startNode(writer, "timezone", String.class);
/* 45 */     writer.setValue(calendar.getTimeZone().getID());
/* 46 */     writer.endNode();
/*    */   }
/*    */   
/*    */   public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
/* 50 */     reader.moveDown();
/* 51 */     long timeInMillis = Long.parseLong(reader.getValue());
/* 52 */     reader.moveUp();
/*    */     String timeZone;
/* 54 */     if (reader.hasMoreChildren()) {
/* 55 */       reader.moveDown();
/* 56 */       String timeZone = reader.getValue();
/* 57 */       reader.moveUp();
/*    */     } else {
/* 59 */       timeZone = TimeZone.getDefault().getID();
/*    */     }
/*    */     
/* 62 */     GregorianCalendar result = new GregorianCalendar();
/* 63 */     result.setTimeZone(TimeZone.getTimeZone(timeZone));
/* 64 */     result.setTime(new Date(timeInMillis));
/*    */     
/* 66 */     return result;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/extended/GregorianCalendarConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */