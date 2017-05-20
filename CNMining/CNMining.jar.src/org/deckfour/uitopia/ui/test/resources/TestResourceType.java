/*    */ package org.deckfour.uitopia.ui.test.resources;
/*    */ 
/*    */ import java.awt.Image;
/*    */ import org.deckfour.uitopia.api.model.Author;
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
/*    */ 
/*    */ public class TestResourceType
/*    */   implements ResourceType
/*    */ {
/*    */   private final String name;
/*    */   private final Image icon;
/*    */   
/*    */   public TestResourceType(String name, Image icon)
/*    */   {
/* 50 */     this.name = name;
/* 51 */     this.icon = icon;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public Author getTypeAuthor()
/*    */   {
/* 58 */     return TestAuthor.DEFAULT_AUTHOR;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public Class<?> getTypeClass()
/*    */   {
/* 66 */     return null;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public Image getTypeIcon()
/*    */   {
/* 73 */     return this.icon;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public String getTypeName()
/*    */   {
/* 80 */     return this.name;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean isAssignableFrom(ResourceType type)
/*    */   {
/* 88 */     return false;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/test/resources/TestResourceType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */