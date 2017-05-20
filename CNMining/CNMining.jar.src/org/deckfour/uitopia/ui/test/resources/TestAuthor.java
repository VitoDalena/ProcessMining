/*    */ package org.deckfour.uitopia.ui.test.resources;
/*    */ 
/*    */ import java.net.URI;
/*    */ import org.deckfour.uitopia.api.model.Author;
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
/*    */ public class TestAuthor
/*    */   implements Author
/*    */ {
/* 45 */   public static final TestAuthor DEFAULT_AUTHOR = new TestAuthor("John W. Doe", "john.doe@meme.com", "Meme Industries", URI.create("http://www.meme.com/"));
/*    */   private String name;
/*    */   private String email;
/*    */   private String affiliation;
/*    */   private URI website;
/*    */   
/*    */   public TestAuthor(String name, String email, String affiliation, URI website)
/*    */   {
/* 53 */     this.name = name;
/* 54 */     this.email = email;
/* 55 */     this.affiliation = affiliation;
/* 56 */     this.website = website;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public String getAffiliation()
/*    */   {
/* 63 */     return this.affiliation;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public String getEmail()
/*    */   {
/* 70 */     return this.email;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public String getName()
/*    */   {
/* 77 */     return this.name;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public URI getWebsite()
/*    */   {
/* 84 */     return this.website;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/test/resources/TestAuthor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */