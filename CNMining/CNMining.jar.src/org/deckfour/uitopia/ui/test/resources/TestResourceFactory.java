/*    */ package org.deckfour.uitopia.ui.test.resources;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import org.deckfour.uitopia.api.model.ResourceType;
/*    */ import org.deckfour.uitopia.ui.util.ImageLoader;
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
/*    */ public class TestResourceFactory
/*    */ {
/* 49 */   public static ResourceType TYPE_LOG = new TestResourceType("Event log", ImageLoader.load("resourcetype_log_30x35.png"));
/* 50 */   public static ResourceType TYPE_MODEL = new TestResourceType("Petri net", ImageLoader.load("resourcetype_model_30x35.png"));
/* 51 */   private static Random random = new Random();
/* 52 */   private static int counter = 1;
/* 53 */   private static long creation = System.currentTimeMillis();
/*    */   
/*    */   public static List<TestResource> generateResources(int number) {
/* 56 */     ArrayList<TestResource> list = new ArrayList();
/* 57 */     for (int i = 0; i < number; i++) {
/* 58 */       list.add(generateResource());
/*    */     }
/* 60 */     return list;
/*    */   }
/*    */   
/*    */   public static synchronized TestResource generateResource() {
/* 64 */     ResourceType type = random.nextBoolean() ? TYPE_LOG : TYPE_MODEL;
/* 65 */     String name = type.getTypeName() + " " + counter;
/* 66 */     counter += 1;
/* 67 */     Date creationDate = new Date(creation);
/* 68 */     creation -= random.nextInt(60000);
/* 69 */     TestResource res = new TestResource(name, creationDate, type, null);
/* 70 */     if (random.nextInt(10) > 7) {
/* 71 */       res.setFavorite(true);
/*    */     }
/* 73 */     return res;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/test/resources/TestResourceFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */