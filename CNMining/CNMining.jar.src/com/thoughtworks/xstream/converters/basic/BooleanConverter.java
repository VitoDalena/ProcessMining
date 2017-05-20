/*    */ package com.thoughtworks.xstream.converters.basic;
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
/*    */ public class BooleanConverter
/*    */   extends AbstractSingleValueConverter
/*    */ {
/* 24 */   public static final BooleanConverter TRUE_FALSE = new BooleanConverter("true", "false", false);
/*    */   
/* 26 */   public static final BooleanConverter YES_NO = new BooleanConverter("yes", "no", false);
/*    */   
/* 28 */   public static final BooleanConverter BINARY = new BooleanConverter("1", "0", true);
/*    */   private final String positive;
/*    */   private final String negative;
/*    */   private final boolean caseSensitive;
/*    */   
/*    */   public BooleanConverter(String positive, String negative, boolean caseSensitive)
/*    */   {
/* 35 */     this.positive = positive;
/* 36 */     this.negative = negative;
/* 37 */     this.caseSensitive = caseSensitive;
/*    */   }
/*    */   
/*    */   public BooleanConverter() {
/* 41 */     this("true", "false", false);
/*    */   }
/*    */   
/*    */   public boolean shouldConvert(Class type, Object value) {
/* 45 */     return true;
/*    */   }
/*    */   
/*    */   public boolean canConvert(Class type) {
/* 49 */     return (type.equals(Boolean.TYPE)) || (type.equals(Boolean.class));
/*    */   }
/*    */   
/*    */   public Object fromString(String str) {
/* 53 */     if (this.caseSensitive) {
/* 54 */       return this.positive.equals(str) ? Boolean.TRUE : Boolean.FALSE;
/*    */     }
/* 56 */     return this.positive.equalsIgnoreCase(str) ? Boolean.TRUE : Boolean.FALSE;
/*    */   }
/*    */   
/*    */   public String toString(Object obj)
/*    */   {
/* 61 */     Boolean value = (Boolean)obj;
/* 62 */     return value.booleanValue() ? this.positive : obj == null ? null : this.negative;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/basic/BooleanConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */