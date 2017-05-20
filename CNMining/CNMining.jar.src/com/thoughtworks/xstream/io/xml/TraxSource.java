/*     */ package com.thoughtworks.xstream.io.xml;
/*     */ 
/*     */ import com.thoughtworks.xstream.XStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.xml.transform.sax.SAXSource;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.XMLFilter;
/*     */ import org.xml.sax.XMLReader;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TraxSource
/*     */   extends SAXSource
/*     */ {
/*     */   public static final String XSTREAM_FEATURE = "http://com.thoughtworks.xstream/XStreamSource/feature";
/*  69 */   private XMLReader xmlReader = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  74 */   private XStream xstream = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  79 */   private List source = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TraxSource()
/*     */   {
/*  89 */     super(new InputSource());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TraxSource(Object source)
/*     */   {
/* 100 */     super(new InputSource());
/*     */     
/* 102 */     setSource(source);
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
/*     */   public TraxSource(Object source, XStream xstream)
/*     */   {
/* 117 */     super(new InputSource());
/*     */     
/* 119 */     setSource(source);
/* 120 */     setXStream(xstream);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TraxSource(List source)
/*     */   {
/* 132 */     super(new InputSource());
/*     */     
/* 134 */     setSourceAsList(source);
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
/*     */   public TraxSource(List source, XStream xstream)
/*     */   {
/* 149 */     super(new InputSource());
/*     */     
/* 151 */     setSourceAsList(source);
/* 152 */     setXStream(xstream);
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
/*     */   public void setInputSource(InputSource inputSource)
/*     */   {
/* 169 */     throw new UnsupportedOperationException();
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
/*     */   public void setXMLReader(XMLReader reader)
/*     */   {
/* 185 */     createXMLReader(reader);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XMLReader getXMLReader()
/*     */   {
/* 196 */     if (this.xmlReader == null) {
/* 197 */       createXMLReader(null);
/*     */     }
/* 199 */     return this.xmlReader;
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
/*     */   public void setXStream(XStream xstream)
/*     */   {
/* 213 */     if (xstream == null) {
/* 214 */       throw new IllegalArgumentException("xstream");
/*     */     }
/* 216 */     this.xstream = xstream;
/*     */     
/* 218 */     configureXMLReader();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSource(Object obj)
/*     */   {
/* 228 */     if (obj == null) {
/* 229 */       throw new IllegalArgumentException("obj");
/*     */     }
/* 231 */     List list = new ArrayList(1);
/* 232 */     list.add(obj);
/*     */     
/* 234 */     setSourceAsList(list);
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
/*     */   public void setSourceAsList(List list)
/*     */   {
/* 250 */     if ((list == null) || (list.isEmpty())) {
/* 251 */       throw new IllegalArgumentException("list");
/*     */     }
/* 253 */     this.source = list;
/*     */     
/* 255 */     configureXMLReader();
/*     */   }
/*     */   
/*     */   private void createXMLReader(XMLReader filterChain) {
/* 259 */     if (filterChain == null) {
/* 260 */       this.xmlReader = new SaxWriter();
/*     */     }
/* 262 */     else if ((filterChain instanceof XMLFilter))
/*     */     {
/* 264 */       XMLFilter filter = (XMLFilter)filterChain;
/* 265 */       while ((filter.getParent() instanceof XMLFilter)) {
/* 266 */         filter = (XMLFilter)filter.getParent();
/*     */       }
/* 268 */       if (!(filter.getParent() instanceof SaxWriter)) {
/* 269 */         filter.setParent(new SaxWriter());
/*     */       }
/*     */       
/*     */ 
/* 273 */       this.xmlReader = filterChain;
/*     */     } else {
/* 275 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/* 278 */     configureXMLReader();
/*     */   }
/*     */   
/*     */   private void configureXMLReader() {
/* 282 */     if (this.xmlReader != null) {
/*     */       try {
/* 284 */         if (this.xstream != null) {
/* 285 */           this.xmlReader.setProperty("http://com.thoughtworks.xstream/sax/property/configured-xstream", this.xstream);
/*     */         }
/*     */         
/* 288 */         if (this.source != null) {
/* 289 */           this.xmlReader.setProperty("http://com.thoughtworks.xstream/sax/property/source-object-list", this.source);
/*     */         }
/*     */       }
/*     */       catch (SAXException e) {
/* 293 */         throw new IllegalArgumentException(e.getMessage());
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/TraxSource.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */