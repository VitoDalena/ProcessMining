/*    */ package com.thoughtworks.xstream.converters.extended;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;
/*    */ import java.io.File;
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
/*    */ public class FileConverter
/*    */   extends AbstractSingleValueConverter
/*    */ {
/*    */   public boolean canConvert(Class type)
/*    */   {
/* 27 */     return type.equals(File.class);
/*    */   }
/*    */   
/*    */   public Object fromString(String str) {
/* 31 */     return new File(str);
/*    */   }
/*    */   
/*    */   public String toString(Object obj) {
/* 35 */     return ((File)obj).getPath();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/extended/FileConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */