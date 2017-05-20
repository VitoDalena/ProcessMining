/*    */ package org.deckfour.uitopia.ui.test.resources;
/*    */ 
/*    */ import org.deckfour.uitopia.api.model.Parameter;
/*    */ import org.deckfour.uitopia.api.model.ResourceType;
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
/*    */ public class TestParameter
/*    */   implements Parameter
/*    */ {
/*    */   private int min;
/*    */   private int max;
/*    */   private String name;
/*    */   private ResourceType type;
/*    */   
/*    */   public TestParameter(String name, ResourceType type, int min, int max)
/*    */   {
/* 50 */     this.name = name;
/* 51 */     this.type = type;
/* 52 */     this.min = min;
/* 53 */     this.max = max;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public int getMaxCardinality()
/*    */   {
/* 60 */     return this.max;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public int getMinCardinality()
/*    */   {
/* 67 */     return this.min;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public String getName()
/*    */   {
/* 74 */     return this.name;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public ResourceType getType()
/*    */   {
/* 81 */     return this.type;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/test/resources/TestParameter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */