/*    */ package org.deckfour.xes.xstream;
/*    */ 
/*    */ import com.thoughtworks.xstream.XStream;
/*    */ import com.thoughtworks.xstream.converters.MarshallingContext;
/*    */ import com.thoughtworks.xstream.converters.UnmarshallingContext;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*    */ import java.net.URI;
/*    */ import org.deckfour.xes.extension.XExtension;
/*    */ import org.deckfour.xes.extension.XExtensionManager;
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
/*    */ 
/*    */ 
/*    */ public class XExtensionConverter
/*    */   extends XConverter
/*    */ {
/*    */   public void marshal(Object obj, HierarchicalStreamWriter writer, MarshallingContext context)
/*    */   {
/* 68 */     XExtension extension = (XExtension)obj;
/* 69 */     writer.addAttribute("uri", extension.getUri().toString());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context)
/*    */   {
/* 77 */     URI uri = URI.create(reader.getAttribute("uri"));
/* 78 */     return XExtensionManager.instance().getByUri(uri);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean canConvert(Class c)
/*    */   {
/* 86 */     return XExtension.class.isAssignableFrom(c);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void registerAliases(XStream stream)
/*    */   {
/* 94 */     stream.aliasType("XExtension", XExtension.class);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/xstream/XExtensionConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */