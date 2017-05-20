/*     */ package org.deckfour.uitopia.ui.test.resources;
/*     */ 
/*     */ import java.awt.FileDialog;
/*     */ import java.awt.Frame;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import org.deckfour.uitopia.api.event.UpdateListener;
/*     */ import org.deckfour.uitopia.api.hub.ResourceManager;
/*     */ import org.deckfour.uitopia.api.model.Resource;
/*     */ import org.deckfour.uitopia.api.model.ResourceFilter;
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
/*     */ public class TestResourceManager
/*     */   implements ResourceManager<TestResource>
/*     */ {
/*  58 */   private static Random rnd = new Random();
/*     */   
/*  60 */   private List<ResourceType> types = new ArrayList();
/*  61 */   private List<TestResource> resources = new ArrayList();
/*  62 */   private Map<TestResource, List<TestResource>> childrenMap = new HashMap();
/*  63 */   private Map<TestResource, List<TestResource>> parentsMap = new HashMap();
/*  64 */   private List<UpdateListener> listeners = new ArrayList();
/*     */   
/*     */   public TestResourceManager(int numResources) {
/*  67 */     this.resources.addAll(TestResourceFactory.generateResources(numResources));
/*     */     
/*  69 */     this.types.add(TestResourceFactory.TYPE_LOG);
/*  70 */     this.types.add(TestResourceFactory.TYPE_MODEL);
/*  71 */     for (TestResource res : this.resources) {
/*  72 */       this.childrenMap.put(res, getResourcesSubset(rnd.nextInt(5)));
/*  73 */       this.parentsMap.put(res, getResourcesSubset(rnd.nextInt(5)));
/*     */     }
/*     */   }
/*     */   
/*     */   private List<TestResource> getResourcesSubset(int size) {
/*  78 */     HashSet<TestResource> subset = new HashSet();
/*  79 */     for (int i = 0; i < size; i++) {
/*  80 */       subset.add(this.resources.get(rnd.nextInt(this.resources.size())));
/*     */     }
/*  82 */     return new ArrayList(subset);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean exportResource(Resource resource)
/*     */     throws IOException
/*     */   {
/*  93 */     FileDialog dialog = new FileDialog((Frame)null, "Save " + resource.getName() + "...", 1);
/*     */     
/*  95 */     dialog.setVisible(true);
/*  96 */     System.out.println("exported " + resource.getName() + ".");
/*  97 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<TestResource> getAllResources(ResourceFilter filter)
/*     */   {
/* 108 */     List<TestResource> filtered = new ArrayList();
/* 109 */     for (TestResource r : this.resources) {
/* 110 */       if (filter.accept(r)) {
/* 111 */         filtered.add(r);
/*     */       }
/*     */     }
/* 114 */     return filtered;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<TestResource> getAllResources()
/*     */   {
/* 123 */     return this.resources;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<ResourceType> getAllSupportedResourceTypes()
/*     */   {
/* 134 */     return this.types;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<TestResource> getChildrenOf(Resource parent, ResourceFilter filter)
/*     */   {
/* 147 */     return (List)this.childrenMap.get(parent);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<TestResource> getChildrenOf(Resource parent)
/*     */   {
/* 158 */     return (List)this.childrenMap.get(parent);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<TestResource> getFavoriteResources(ResourceFilter filter)
/*     */   {
/* 169 */     return getFavoriteResources();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<TestResource> getFavoriteResources()
/*     */   {
/* 178 */     ArrayList<TestResource> filtered = new ArrayList();
/* 179 */     for (TestResource res : this.resources) {
/* 180 */       if (res.isFavorite()) {
/* 181 */         filtered.add(res);
/*     */       }
/*     */     }
/* 184 */     return filtered;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<TestResource> getImportedResources(ResourceFilter filter)
/*     */   {
/* 195 */     return getImportedResources();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<TestResource> getImportedResources()
/*     */   {
/* 204 */     ArrayList<TestResource> filtered = new ArrayList();
/* 205 */     for (TestResource res : this.resources) {
/* 206 */       if (res.getSourceAction() == null) {
/* 207 */         filtered.add(res);
/*     */       }
/*     */     }
/* 210 */     return filtered;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<TestResource> getParentsOf(Resource child, ResourceFilter filter)
/*     */   {
/* 222 */     return getParentsOf(child);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<TestResource> getParentsOf(Resource child)
/*     */   {
/* 233 */     return (List)this.parentsMap.get(child);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<ResourceType> getResourceTypes(List<? extends Resource> resources)
/*     */   {
/* 245 */     return this.types;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean importResource()
/*     */     throws IOException
/*     */   {
/* 254 */     FileDialog dialog = new FileDialog((Frame)null, "Load resource...", 0);
/*     */     
/* 256 */     dialog.setVisible(true);
/* 257 */     addResource(TestResourceFactory.generateResource());
/* 258 */     System.out.println("imported resource.");
/* 259 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addListener(UpdateListener listener)
/*     */   {
/* 269 */     this.listeners.add(listener);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Collection<UpdateListener> getListeners()
/*     */   {
/* 278 */     return this.listeners;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeAllListeners()
/*     */   {
/* 287 */     this.listeners.clear();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeListener(UpdateListener listener)
/*     */   {
/* 298 */     this.listeners.clear();
/*     */   }
/*     */   
/*     */   public void addResource(TestResource res) {
/* 302 */     this.resources.add(res);
/* 303 */     notifyListeners();
/*     */   }
/*     */   
/*     */   private void notifyListeners() {
/* 307 */     for (UpdateListener listener : this.listeners) {
/* 308 */       listener.updated();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/test/resources/TestResourceManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */