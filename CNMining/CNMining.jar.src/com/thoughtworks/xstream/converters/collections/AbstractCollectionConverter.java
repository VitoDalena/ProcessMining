/*    */ package com.thoughtworks.xstream.converters.collections;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.ConversionException;
/*    */ import com.thoughtworks.xstream.converters.Converter;
/*    */ import com.thoughtworks.xstream.converters.MarshallingContext;
/*    */ import com.thoughtworks.xstream.converters.UnmarshallingContext;
/*    */ import com.thoughtworks.xstream.core.util.HierarchicalStreams;
/*    */ import com.thoughtworks.xstream.io.ExtendedHierarchicalStreamWriterHelper;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*    */ import com.thoughtworks.xstream.mapper.Mapper;
/*    */ import com.thoughtworks.xstream.mapper.Mapper.Null;
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
/*    */ public abstract class AbstractCollectionConverter
/*    */   implements Converter
/*    */ {
/*    */   private final Mapper mapper;
/*    */   
/*    */   public abstract boolean canConvert(Class paramClass);
/*    */   
/*    */   public AbstractCollectionConverter(Mapper mapper)
/*    */   {
/* 41 */     this.mapper = mapper;
/*    */   }
/*    */   
/*    */   protected Mapper mapper() {
/* 45 */     return this.mapper;
/*    */   }
/*    */   
/*    */ 
/*    */   public abstract void marshal(Object paramObject, HierarchicalStreamWriter paramHierarchicalStreamWriter, MarshallingContext paramMarshallingContext);
/*    */   
/*    */ 
/*    */   public abstract Object unmarshal(HierarchicalStreamReader paramHierarchicalStreamReader, UnmarshallingContext paramUnmarshallingContext);
/*    */   
/*    */   protected void writeItem(Object item, MarshallingContext context, HierarchicalStreamWriter writer)
/*    */   {
/* 56 */     if (item == null)
/*    */     {
/* 58 */       String name = mapper().serializedClass(null);
/* 59 */       ExtendedHierarchicalStreamWriterHelper.startNode(writer, name, Mapper.Null.class);
/* 60 */       writer.endNode();
/*    */     } else {
/* 62 */       String name = mapper().serializedClass(item.getClass());
/* 63 */       ExtendedHierarchicalStreamWriterHelper.startNode(writer, name, item.getClass());
/* 64 */       context.convertAnother(item);
/* 65 */       writer.endNode();
/*    */     }
/*    */   }
/*    */   
/*    */   protected Object readItem(HierarchicalStreamReader reader, UnmarshallingContext context, Object current) {
/* 70 */     Class type = HierarchicalStreams.readClassType(reader, mapper());
/* 71 */     return context.convertAnother(current, type);
/*    */   }
/*    */   
/*    */   protected Object createCollection(Class type) {
/* 75 */     Class defaultType = mapper().defaultImplementationOf(type);
/*    */     try {
/* 77 */       return defaultType.newInstance();
/*    */     } catch (InstantiationException e) {
/* 79 */       throw new ConversionException("Cannot instantiate " + defaultType.getName(), e);
/*    */     } catch (IllegalAccessException e) {
/* 81 */       throw new ConversionException("Cannot instantiate " + defaultType.getName(), e);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/collections/AbstractCollectionConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */