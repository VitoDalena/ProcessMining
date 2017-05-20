/*     */ package org.deckfour.uitopia.ui.test.resources;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import org.deckfour.uitopia.api.model.Action;
/*     */ import org.deckfour.uitopia.api.model.ActionStatus;
/*     */ import org.deckfour.uitopia.api.model.ActionType;
/*     */ import org.deckfour.uitopia.api.model.Author;
/*     */ import org.deckfour.uitopia.api.model.Parameter;
/*     */ import org.deckfour.uitopia.api.model.Resource;
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
/*     */ public class TestAction
/*     */   implements Action
/*     */ {
/*     */   private ActionType type;
/*     */   private String name;
/*     */   private Author author;
/*     */   private List<Parameter> input;
/*     */   private List<Parameter> output;
/*     */   
/*     */   public TestAction(String name, ActionType type, Author author, List<Parameter> input, List<Parameter> output)
/*     */   {
/*  60 */     this.name = name;
/*  61 */     this.type = type;
/*  62 */     this.author = author;
/*  63 */     this.input = input;
/*  64 */     this.output = output;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getPackage()
/*     */   {
/*  73 */     return "";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Author getAuthor()
/*     */   {
/*  82 */     return this.author;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<Parameter> getInput()
/*     */   {
/*  91 */     return this.input;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/* 100 */     return this.name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<Parameter> getOutput()
/*     */   {
/* 109 */     return this.output;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ActionStatus getStatus(List<Collection<? extends Resource>> parameterValues)
/*     */   {
/* 119 */     ArrayList<Resource> resources = new ArrayList();
/* 120 */     for (Collection<? extends Resource> resCol : parameterValues) {
/* 121 */       resources.addAll(resCol);
/*     */     }
/* 123 */     int matches = 0;
/* 124 */     for (Parameter p : this.input) {
/* 125 */       for (int i = 0; i < resources.size(); i++) {
/* 126 */         Resource r = (Resource)resources.get(i);
/* 127 */         if (p.getType().equals(r.getType())) {
/* 128 */           resources.remove(i);
/* 129 */           matches++;
/* 130 */           break;
/*     */         }
/*     */       }
/*     */     }
/* 134 */     if (matches == 0)
/* 135 */       return ActionStatus.INVALID;
/* 136 */     if (matches >= this.input.size()) {
/* 137 */       return ActionStatus.EXECUTABLE;
/*     */     }
/* 139 */     return ActionStatus.INCOMPLETE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ActionType getType()
/*     */   {
/* 149 */     return this.type;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/test/resources/TestAction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */