/*     */ package flanagan.io;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import javax.swing.filechooser.FileFilter;
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
/*     */ 
/*     */ 
/*     */ public class FileTypeFilter
/*     */   extends FileFilter
/*     */ {
/*  76 */   private static String TYPE_UNKNOWN = "Type Unknown";
/*  77 */   private static String HIDDEN_FILE = "Hidden File";
/*     */   
/*  79 */   private Hashtable<String, FileTypeFilter> filters = null;
/*  80 */   private String description = null;
/*  81 */   private String fullDescription = null;
/*  82 */   private boolean useExtensionsInDescription = true;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FileTypeFilter()
/*     */   {
/*  92 */     this.filters = new Hashtable();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FileTypeFilter(String extension)
/*     */   {
/* 102 */     this(extension, null);
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
/*     */   public FileTypeFilter(String extension, String description)
/*     */   {
/* 115 */     this();
/* 116 */     if (extension != null) addExtension(extension);
/* 117 */     if (description != null) { setDescription(description);
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
/*     */   public FileTypeFilter(String[] filters)
/*     */   {
/* 130 */     this(filters, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FileTypeFilter(String[] filters, String description)
/*     */   {
/* 142 */     this();
/* 143 */     for (int i = 0; i < filters.length; i++)
/*     */     {
/* 145 */       addExtension(filters[i]);
/*     */     }
/* 147 */     if (description != null) { setDescription(description);
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
/*     */   public boolean accept(File f)
/*     */   {
/* 160 */     if (f != null) {
/* 161 */       if (f.isDirectory()) {
/* 162 */         return true;
/*     */       }
/* 164 */       String extension = getExtension(f);
/* 165 */       if ((extension != null) && (this.filters.get(getExtension(f)) != null)) {
/* 166 */         return true;
/*     */       }
/*     */     }
/* 169 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getExtension(File f)
/*     */   {
/* 179 */     if (f != null) {
/* 180 */       String filename = f.getName();
/* 181 */       int i = filename.lastIndexOf('.');
/* 182 */       if ((i > 0) && (i < filename.length() - 1)) {
/* 183 */         return filename.substring(i + 1).toLowerCase();
/*     */       }
/*     */     }
/* 186 */     return null;
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
/*     */   public void addExtension(String extension)
/*     */   {
/* 202 */     if (this.filters == null) {
/* 203 */       this.filters = new Hashtable(5);
/*     */     }
/* 205 */     this.filters.put(extension.toLowerCase(), this);
/* 206 */     this.fullDescription = null;
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
/*     */   public String getDescription()
/*     */   {
/* 220 */     if (this.fullDescription == null) {
/* 221 */       if ((this.description == null) || (isExtensionListInDescription())) {
/* 222 */         this.fullDescription = (this.description + " (");
/*     */         
/* 224 */         Enumeration extensions = this.filters.keys();
/* 225 */         if (extensions != null) {
/* 226 */           this.fullDescription = (this.fullDescription + "." + (String)extensions.nextElement());
/* 227 */           while (extensions.hasMoreElements()) {
/* 228 */             this.fullDescription = (this.fullDescription + ", ." + (String)extensions.nextElement());
/*     */           }
/*     */         }
/* 231 */         this.fullDescription += ")";
/*     */       } else {
/* 233 */         this.fullDescription = this.description;
/*     */       }
/*     */     }
/* 236 */     return this.fullDescription;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDescription(String description)
/*     */   {
/* 248 */     this.description = description;
/* 249 */     this.fullDescription = null;
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
/*     */   public void setExtensionListInDescription(boolean b)
/*     */   {
/* 264 */     this.useExtensionsInDescription = b;
/* 265 */     this.fullDescription = null;
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
/*     */   public boolean isExtensionListInDescription()
/*     */   {
/* 280 */     return this.useExtensionsInDescription;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/io/FileTypeFilter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */