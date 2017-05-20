/*    */ package org.deckfour.uitopia.ui.test.resources;
/*    */ 
/*    */ import org.deckfour.uitopia.api.model.Author;
/*    */ import org.deckfour.uitopia.api.model.Resource;
/*    */ import org.deckfour.uitopia.api.model.ResourceType;
/*    */ import org.deckfour.uitopia.api.model.View;
/*    */ import org.deckfour.uitopia.api.model.ViewType;
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
/*    */ public class TestViewType
/*    */   implements ViewType
/*    */ {
/*    */   private String name;
/*    */   private ResourceType type;
/*    */   private Author author;
/*    */   
/*    */   public TestViewType(String name, ResourceType type, Author author)
/*    */   {
/* 52 */     this.name = name;
/* 53 */     this.type = type;
/* 54 */     this.author = author;
/*    */   }
/*    */   
/*    */ 
/*    */   public View createView(Resource resource)
/*    */     throws IllegalArgumentException
/*    */   {
/* 61 */     String name = this.name + " for " + resource.getName();
/* 62 */     TestView view = new TestView(name, this, resource);
/* 63 */     return view;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public Author getAuthor()
/*    */   {
/* 70 */     return this.author;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public String getTypeName()
/*    */   {
/* 77 */     return this.name;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public ResourceType getViewableType()
/*    */   {
/* 84 */     return this.type;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 88 */     return getTypeName();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/test/resources/TestViewType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */