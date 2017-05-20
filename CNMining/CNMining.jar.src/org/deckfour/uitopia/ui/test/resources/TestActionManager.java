/*     */ package org.deckfour.uitopia.ui.test.resources;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import org.deckfour.uitopia.api.hub.ActionManager;
/*     */ import org.deckfour.uitopia.api.model.ActionType;
/*     */ import org.deckfour.uitopia.api.model.Parameter;
/*     */ import org.deckfour.uitopia.api.model.ResourceType;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TestActionManager
/*     */   implements ActionManager<TestAction>
/*     */ {
/*  50 */   private static final Random rnd = new Random();
/*     */   private List<TestAction> actions;
/*     */   private List<ResourceType> types;
/*     */   
/*     */   public TestActionManager(List<ResourceType> types, int size)
/*     */   {
/*  56 */     this.types = types;
/*  57 */     this.actions = new ArrayList();
/*  58 */     for (int i = 0; i < size; i++) {
/*  59 */       addAction(generateAction("Action " + i));
/*     */     }
/*     */   }
/*     */   
/*     */   public void addAction(TestAction action) {
/*  64 */     this.actions.add(action);
/*     */   }
/*     */   
/*     */   private TestAction generateAction(String name) {
/*  68 */     Random rnd = new Random();
/*  69 */     ActionType type = rnd.nextBoolean() ? ActionType.HEADLESS : ActionType.INTERACTIVE;
/*  70 */     return new TestAction(name, type, TestAuthor.DEFAULT_AUTHOR, generateParameterList(1 + rnd.nextInt(4)), generateParameterList(1 + rnd.nextInt(4)));
/*     */   }
/*     */   
/*     */   private List<Parameter> generateParameterList(int size) {
/*  74 */     ArrayList<Parameter> parameters = new ArrayList();
/*  75 */     for (int i = 0; i < size; i++) {
/*  76 */       int min = rnd.nextInt(1);
/*  77 */       int max = rnd.nextInt(min + 3);
/*  78 */       TestParameter param = new TestParameter("Parameter " + i, (ResourceType)this.types.get(rnd.nextInt(this.types.size())), min, max);
/*     */       
/*     */ 
/*     */ 
/*  82 */       parameters.add(param);
/*     */     }
/*  84 */     return parameters;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<TestAction> getActions(List<ResourceType> parameters, List<ResourceType> results, ActionType type)
/*     */   {
/*  92 */     List<TestAction> filtered = new ArrayList();
/*  93 */     for (TestAction action : this.actions) {
/*  94 */       if (action.getType().equals(type)) {
/*  95 */         filtered.add(action);
/*     */       }
/*     */     }
/*  98 */     return filtered;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<TestAction> getActions(List<ResourceType> parameters, List<ResourceType> results)
/*     */   {
/* 106 */     return this.actions;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public List<TestAction> getActions()
/*     */   {
/* 113 */     return this.actions;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/test/resources/TestActionManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */