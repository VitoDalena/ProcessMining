/*     */ package org.deckfour.spex;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import org.deckfour.spex.util.SXmlCharacterMethods;
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
/*     */ public class SXTag
/*     */   extends SXNode
/*     */ {
/*  66 */   protected String name = null;
/*     */   
/*     */ 
/*     */ 
/*  70 */   protected SXNode lastChildNode = null;
/*     */   
/*     */ 
/*     */ 
/*  74 */   protected boolean isOpen = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SXTag(String aName, Writer aWriter, int aTabLevel, String aTabString)
/*     */     throws IOException
/*     */   {
/*  87 */     super(aWriter, aTabLevel, aTabString);
/*     */     
/*  89 */     this.name = aName.trim();
/*  90 */     this.lastChildNode = null;
/*     */     
/*  92 */     synchronized (this) {
/*  93 */       indentLine();
/*  94 */       this.writer.write("<" + this.name);
/*  95 */       this.isOpen = true;
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
/*     */ 
/*     */ 
/*     */   public synchronized void addAttribute(String aName, String aValue)
/*     */     throws IOException
/*     */   {
/* 113 */     if (!this.isOpen) {
/* 114 */       throw new IOException("Attempted to add attribute '" + aName + "' to already closed tag '" + this.name + "'!");
/*     */     }
/*     */     
/* 117 */     if ((aName == null) || (aValue == null) || (aName.trim().length() == 0) || (aValue.trim().length() == 0))
/*     */     {
/*     */ 
/*     */ 
/* 121 */       return;
/*     */     }
/*     */     
/* 124 */     if (this.lastChildNode == null)
/*     */     {
/* 126 */       aName = aName.trim();
/* 127 */       aValue = SXmlCharacterMethods.convertCharsToXml(aValue.trim());
/* 128 */       this.writer.write(" " + aName + "=\"" + aValue + "\"");
/*     */     }
/*     */     else {
/* 131 */       throw new IOException("No attributes can be added to a node after the first child has been added! ('" + this.name + "')");
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
/*     */   public synchronized SXTag addChildNode(String tagName)
/*     */     throws IOException
/*     */   {
/* 146 */     if (!this.isOpen) {
/* 147 */       throw new IOException("Attempted to add child node '" + tagName + "' to already closed tag '" + this.name + "'!");
/*     */     }
/* 149 */     if (this.lastChildNode == null)
/*     */     {
/* 151 */       this.writer.write(">");
/*     */     } else {
/* 153 */       this.lastChildNode.close();
/*     */     }
/*     */     
/* 156 */     this.writer.write("\n");
/*     */     
/* 158 */     SXTag childNode = new SXTag(tagName, this.writer, this.tabLevel + 1, this.tabString);
/* 159 */     this.lastChildNode = childNode;
/* 160 */     return childNode;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void addTextNode(String text)
/*     */     throws IOException
/*     */   {
/* 171 */     if ((text == null) || (text.trim().length() == 0)) {
/* 172 */       return;
/*     */     }
/*     */     
/* 175 */     if (!this.isOpen) {
/* 176 */       throw new IOException("Attempted to add child text node to already closed tag '" + this.name + "'!");
/*     */     }
/* 178 */     if (this.lastChildNode == null)
/*     */     {
/* 180 */       this.writer.write(">");
/*     */     }
/*     */     else
/*     */     {
/* 184 */       this.lastChildNode.close();
/* 185 */       this.writer.write("\n");
/* 186 */       indentLine();
/*     */     }
/* 188 */     SXTextNode textNode = new SXTextNode(text, this.writer, this.tabLevel, this.tabString);
/* 189 */     this.lastChildNode = textNode;
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
/*     */   public synchronized void addComment(String comment)
/*     */     throws IOException
/*     */   {
/* 206 */     if (!this.isOpen) {
/* 207 */       throw new IOException("Attempted to add comment child node to already closed tag '" + this.name + "'!");
/*     */     }
/* 209 */     if (this.lastChildNode == null)
/*     */     {
/* 211 */       this.writer.write(">");
/*     */     } else {
/* 213 */       this.lastChildNode.close();
/*     */     }
/*     */     
/* 216 */     this.writer.write("\n");
/* 217 */     SXCommentNode commentNode = new SXCommentNode(comment, this.writer, this.tabLevel + 1, this.tabString);
/* 218 */     this.lastChildNode = commentNode;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void close()
/*     */     throws IOException
/*     */   {
/* 228 */     if (!this.isOpen) {
/* 229 */       throw new IOException("Attempted to close already closed tag '" + this.name + "'!");
/*     */     }
/*     */     
/* 232 */     if (this.lastChildNode != null)
/*     */     {
/* 234 */       this.lastChildNode.close();
/*     */       
/* 236 */       if (!(this.lastChildNode instanceof SXTextNode)) {
/* 237 */         this.writer.write("\n");
/* 238 */         indentLine();
/*     */       }
/*     */       
/* 241 */       this.writer.write("</" + this.name + ">");
/* 242 */       this.lastChildNode = null;
/*     */     }
/*     */     else {
/* 245 */       this.writer.write("/>");
/*     */     }
/* 247 */     this.isOpen = false;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/spex/SXTag.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */