/*    */ package org.processmining.contexts.uitopia.model;
/*    */ 
/*    */ import java.net.URI;
/*    */ import java.net.URL;
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
/*    */ class ResourceTypeInfo
/*    */   implements Author
/*    */ {
/*    */   public String affiliation;
/*    */   public String email;
/*    */   public String author;
/*    */   public String website;
/*    */   public String icon;
/*    */   public String typename;
/*    */   
/*    */   public ResourceTypeInfo(String typename, String affiliation, String email, String author, String website, String icon)
/*    */   {
/* 50 */     this.typename = typename;
/* 51 */     this.affiliation = affiliation;
/* 52 */     this.email = email;
/* 53 */     this.author = author;
/* 54 */     this.website = website;
/* 55 */     this.icon = icon;
/*    */   }
/*    */   
/*    */   public String getTypeName() {
/* 59 */     return this.typename;
/*    */   }
/*    */   
/*    */   public String getAffiliation() {
/* 63 */     return this.affiliation;
/*    */   }
/*    */   
/*    */   public String getEmail() {
/* 67 */     return this.email;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 71 */     return this.author;
/*    */   }
/*    */   
/*    */   public URI getWebsite() {
/* 75 */     URI uri = null;
/*    */     try {
/* 77 */       uri = new URL(this.website).toURI();
/*    */     }
/*    */     catch (Exception e2) {}
/* 80 */     return uri;
/*    */   }
/*    */   
/*    */   public String getIcon() {
/* 84 */     return this.icon;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/processmining/contexts/uitopia/model/ResourceTypeInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */