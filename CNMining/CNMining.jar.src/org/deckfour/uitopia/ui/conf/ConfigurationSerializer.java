/*    */ package org.deckfour.uitopia.ui.conf;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import java.util.TreeSet;
/*    */ import org.deckfour.spex.SXDocument;
/*    */ import org.deckfour.spex.SXTag;
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
/*    */ public class ConfigurationSerializer
/*    */ {
/*    */   public static void serialize(ConfigurationSet set, File file)
/*    */     throws IOException
/*    */   {
/* 52 */     OutputStream os = new FileOutputStream(file);
/* 53 */     serialize(set, os);
/*    */   }
/*    */   
/*    */   public static void serialize(ConfigurationSet set, OutputStream out) throws IOException {
/* 57 */     SXDocument doc = new SXDocument(out);
/* 58 */     doc.addComment("UITopia configuration file.");
/* 59 */     doc.addComment("(c) 2009 by Christian W. Guenther (christian@deckfour.org)");
/* 60 */     doc.addComment("WARNING: Do not manually edit this file, unless you know what you are doing!");
/* 61 */     SXTag root = doc.addNode("configuration");
/* 62 */     root.addAttribute("timestamp", Long.toString(System.currentTimeMillis()));
/* 63 */     serialize(set, root);
/* 64 */     doc.close();
/*    */   }
/*    */   
/*    */   private static void serialize(ConfigurationSet set, SXTag parent) throws IOException {
/* 68 */     SXTag tag = parent.addChildNode("set");
/* 69 */     tag.addAttribute("name", set.name());
/* 70 */     TreeSet<String> keys = new TreeSet(set.keySet());
/* 71 */     for (String key : keys) {
/* 72 */       String value = (String)set.get(key);
/* 73 */       if (value != null) {
/* 74 */         SXTag attribute = tag.addChildNode("attribute");
/* 75 */         attribute.addAttribute("key", key);
/* 76 */         attribute.addAttribute("value", value);
/*    */       }
/*    */     }
/* 79 */     for (ConfigurationSet child : set.getChildren()) {
/* 80 */       serialize(child, tag);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/conf/ConfigurationSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */