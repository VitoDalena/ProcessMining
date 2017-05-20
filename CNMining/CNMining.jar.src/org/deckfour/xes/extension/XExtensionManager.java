/*     */ package org.deckfour.xes.extension;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.net.URI;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import org.deckfour.xes.extension.std.XConceptExtension;
/*     */ import org.deckfour.xes.extension.std.XLifecycleExtension;
/*     */ import org.deckfour.xes.extension.std.XOrganizationalExtension;
/*     */ import org.deckfour.xes.extension.std.XSemanticExtension;
/*     */ import org.deckfour.xes.extension.std.XTimeExtension;
/*     */ import org.deckfour.xes.logging.XLogging;
/*     */ import org.deckfour.xes.logging.XLogging.Importance;
/*     */ import org.deckfour.xes.util.XRuntimeUtils;
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
/*     */ public class XExtensionManager
/*     */ {
/*     */   public static final long MAX_CACHE_MILLIS = -1702967296L;
/*  87 */   private static XExtensionManager singleton = new XExtensionManager();
/*     */   
/*     */   private HashMap<URI, XExtension> extensionMap;
/*     */   
/*     */   private ArrayList<XExtension> extensionList;
/*     */   
/*     */   public static XExtensionManager instance()
/*     */   {
/*  95 */     return singleton;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private XExtensionManager()
/*     */   {
/* 112 */     this.extensionMap = new HashMap();
/* 113 */     this.extensionList = new ArrayList();
/* 114 */     registerStandardExtensions();
/* 115 */     loadExtensionCache();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void register(XExtension extension)
/*     */   {
/* 125 */     this.extensionMap.put(extension.getUri(), extension);
/*     */     
/* 127 */     int i = this.extensionList.indexOf(extension);
/* 128 */     if (i < 0) {
/* 129 */       this.extensionList.add(extension);
/*     */     } else {
/* 131 */       this.extensionList.remove(i);
/* 132 */       this.extensionList.add(i, extension);
/*     */     }
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
/*     */ 
/*     */   public XExtension getByUri(URI uri)
/*     */   {
/* 147 */     XExtension extension = (XExtension)this.extensionMap.get(uri);
/* 148 */     if (extension == null) {
/*     */       try {
/* 150 */         extension = XExtensionParser.instance().parse(uri);
/* 151 */         register(extension);
/* 152 */         XLogging.log("Imported XES extension '" + extension.getUri() + "' from remote source", XLogging.Importance.DEBUG);
/*     */ 
/*     */       }
/*     */       catch (IOException e) {}catch (Exception e)
/*     */       {
/*     */ 
/* 158 */         e.printStackTrace();
/* 159 */         return null;
/*     */       }
/* 161 */       cacheExtension(uri);
/*     */     }
/* 163 */     return extension;
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
/*     */   public XExtension getByName(String name)
/*     */   {
/* 176 */     for (XExtension ext : this.extensionList) {
/* 177 */       if (ext.getName().equals(name)) {
/* 178 */         return ext;
/*     */       }
/*     */     }
/* 181 */     return null;
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
/*     */   public XExtension getByPrefix(String prefix)
/*     */   {
/* 194 */     for (XExtension ext : this.extensionList) {
/* 195 */       if (ext.getPrefix().equals(prefix)) {
/* 196 */         return ext;
/*     */       }
/*     */     }
/* 199 */     return null;
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
/*     */   public XExtension getByIndex(int index)
/*     */   {
/* 212 */     if ((index < 0) || (index >= this.extensionList.size())) {
/* 213 */       return null;
/*     */     }
/* 215 */     return (XExtension)this.extensionList.get(index);
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
/*     */   public int getIndex(XExtension extension)
/*     */   {
/* 228 */     for (int i = 0; i < this.extensionList.size(); i++) {
/* 229 */       if (((XExtension)this.extensionList.get(i)).equals(extension)) {
/* 230 */         return i;
/*     */       }
/*     */     }
/* 233 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void registerStandardExtensions()
/*     */   {
/* 241 */     register(XConceptExtension.instance());
/* 242 */     register(XTimeExtension.instance());
/* 243 */     register(XLifecycleExtension.instance());
/* 244 */     register(XOrganizationalExtension.instance());
/* 245 */     register(XSemanticExtension.instance());
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
/*     */   protected void cacheExtension(URI uri)
/*     */   {
/* 258 */     String uriStr = uri.toString().toLowerCase();
/* 259 */     if (uriStr.endsWith("/")) {
/* 260 */       uriStr = uriStr.substring(0, uriStr.length() - 1);
/*     */     }
/* 262 */     String fileName = uriStr.substring(uriStr.lastIndexOf('/'));
/* 263 */     if (!fileName.endsWith(".xesext")) {
/* 264 */       fileName = fileName + ".xesext";
/*     */     }
/* 266 */     File cacheFile = new File(XRuntimeUtils.getExtensionCacheFolder().getAbsolutePath() + File.separator + fileName);
/*     */     
/*     */     try
/*     */     {
/* 270 */       byte[] buffer = new byte['Ѐ'];
/* 271 */       BufferedInputStream bis = new BufferedInputStream(uri.toURL().openStream());
/*     */       
/* 273 */       cacheFile.createNewFile();
/* 274 */       BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(cacheFile));
/*     */       
/* 276 */       int read = bis.read(buffer);
/* 277 */       while (read >= 0) {
/* 278 */         bos.write(buffer, 0, read);
/* 279 */         read = bis.read(buffer);
/*     */       }
/* 281 */       bis.close();
/* 282 */       bos.flush();
/* 283 */       bos.close();
/* 284 */       XLogging.log("Cached XES extension '" + uri + "'", XLogging.Importance.DEBUG);
/*     */     }
/*     */     catch (IOException e) {
/* 287 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void loadExtensionCache()
/*     */   {
/* 297 */     long minModified = System.currentTimeMillis() - -1702967296L;
/* 298 */     File extFolder = XRuntimeUtils.getExtensionCacheFolder();
/*     */     
/* 300 */     File[] extFiles = extFolder.listFiles();
/* 301 */     if (extFiles == null)
/*     */     {
/*     */ 
/* 304 */       XLogging.log("Extension caching disabled (Could not access cache directory)!", XLogging.Importance.WARNING);
/*     */       
/*     */ 
/* 307 */       return;
/*     */     }
/* 309 */     for (File extFile : extFiles) {
/* 310 */       if (extFile.getName().toLowerCase().endsWith(".xesext"))
/*     */       {
/*     */ 
/*     */ 
/* 314 */         if (extFile.lastModified() < minModified)
/*     */         {
/* 316 */           if (!extFile.delete()) {
/* 317 */             extFile.deleteOnExit();
/*     */           }
/*     */         }
/*     */         else {
/*     */           try {
/* 322 */             XExtension extension = XExtensionParser.instance().parse(extFile);
/* 323 */             if (!this.extensionMap.containsKey(extension.getUri())) {
/* 324 */               this.extensionMap.put(extension.getUri(), extension);
/* 325 */               this.extensionList.add(extension);
/* 326 */               XLogging.log("Loaded XES extension '" + extension.getUri() + "' from cache", XLogging.Importance.DEBUG);
/*     */ 
/*     */             }
/*     */             else
/*     */             {
/* 331 */               XLogging.log("Skipping cached XES extension '" + extension.getUri() + "' (already defined)", XLogging.Importance.DEBUG);
/*     */             }
/*     */             
/*     */           }
/*     */           catch (Exception e)
/*     */           {
/* 337 */             e.printStackTrace();
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/extension/XExtensionManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */