/*      */ package org.apache.commons.collections15;
/*      */ 
/*      */ import java.io.File;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.InputStreamReader;
/*      */ import java.io.LineNumberReader;
/*      */ import java.io.OutputStream;
/*      */ import java.io.PrintStream;
/*      */ import java.io.PrintWriter;
/*      */ import java.io.Reader;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Properties;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.Vector;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ExtendedProperties
/*      */   extends Hashtable
/*      */ {
/*      */   private ExtendedProperties defaults;
/*      */   protected String file;
/*      */   protected String basePath;
/*  148 */   protected String fileSeparator = System.getProperty("file.separator");
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*  153 */   protected boolean isInitialized = false;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  159 */   protected static String include = "include";
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  167 */   protected ArrayList<String> keysAsListed = new ArrayList();
/*      */   
/*      */ 
/*      */ 
/*      */   protected static final String START_TOKEN = "${";
/*      */   
/*      */ 
/*      */ 
/*      */   protected static final String END_TOKEN = "}";
/*      */   
/*      */ 
/*      */ 
/*      */   protected String interpolate(String base)
/*      */   {
/*  181 */     return interpolateHelper(base, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected String interpolateHelper(String base, List priorVariables)
/*      */   {
/*  199 */     if (base == null) {
/*  200 */       return null;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  205 */     if (priorVariables == null) {
/*  206 */       priorVariables = new ArrayList();
/*  207 */       priorVariables.add(base);
/*      */     }
/*      */     
/*  210 */     int begin = -1;
/*  211 */     int end = -1;
/*  212 */     int prec = 0 - "}".length();
/*  213 */     String variable = null;
/*  214 */     StringBuffer result = new StringBuffer();
/*      */     
/*      */ 
/*  217 */     while (((begin = base.indexOf("${", prec + "}".length())) > -1) && ((end = base.indexOf("}", begin)) > -1)) {
/*  218 */       result.append(base.substring(prec + "}".length(), begin));
/*  219 */       variable = base.substring(begin + "${".length(), end);
/*      */       
/*      */ 
/*  222 */       if (priorVariables.contains(variable)) {
/*  223 */         String initialBase = priorVariables.remove(0).toString();
/*  224 */         priorVariables.add(variable);
/*  225 */         StringBuffer priorVariableSb = new StringBuffer();
/*      */         
/*      */ 
/*      */ 
/*  229 */         for (Iterator it = priorVariables.iterator(); it.hasNext();) {
/*  230 */           priorVariableSb.append(it.next());
/*  231 */           if (it.hasNext()) {
/*  232 */             priorVariableSb.append("->");
/*      */           }
/*      */         }
/*      */         
/*  236 */         throw new IllegalStateException("infinite loop in property interpolation of " + initialBase + ": " + priorVariableSb.toString());
/*      */       }
/*      */       
/*      */ 
/*  240 */       priorVariables.add(variable);
/*      */       
/*      */ 
/*      */ 
/*  244 */       Object value = getProperty(variable);
/*  245 */       if (value != null) {
/*  246 */         result.append(interpolateHelper(value.toString(), priorVariables));
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  252 */         priorVariables.remove(priorVariables.size() - 1);
/*  253 */       } else if ((this.defaults != null) && (this.defaults.getString(variable, null) != null)) {
/*  254 */         result.append(this.defaults.getString(variable));
/*      */       }
/*      */       else {
/*  257 */         result.append("${").append(variable).append("}");
/*      */       }
/*  259 */       prec = end;
/*      */     }
/*  261 */     result.append(base.substring(prec + "}".length(), base.length()));
/*      */     
/*  263 */     return result.toString();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private static String escape(String s)
/*      */   {
/*  270 */     StringBuffer buf = new StringBuffer(s);
/*  271 */     for (int i = 0; i < buf.length(); i++) {
/*  272 */       char c = buf.charAt(i);
/*  273 */       if ((c == ',') || (c == '\\')) {
/*  274 */         buf.insert(i, '\\');
/*  275 */         i++;
/*      */       }
/*      */     }
/*  278 */     return buf.toString();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private static String unescape(String s)
/*      */   {
/*  285 */     StringBuffer buf = new StringBuffer(s);
/*  286 */     for (int i = 0; i < buf.length() - 1; i++) {
/*  287 */       char c1 = buf.charAt(i);
/*  288 */       char c2 = buf.charAt(i + 1);
/*  289 */       if ((c1 == '\\') && (c2 == '\\')) {
/*  290 */         buf.deleteCharAt(i);
/*      */       }
/*      */     }
/*  293 */     return buf.toString();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static int countPreceding(String line, int index, char ch)
/*      */   {
/*  302 */     for (int i = index - 1; i >= 0; i--) {
/*  303 */       if (line.charAt(i) != ch) {
/*      */         break;
/*      */       }
/*      */     }
/*  307 */     return index - 1 - i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private static boolean endsWithSlash(String line)
/*      */   {
/*  314 */     if (!line.endsWith("\\")) {
/*  315 */       return false;
/*      */     }
/*  317 */     return countPreceding(line, line.length() - 1, '\\') % 2 == 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public ExtendedProperties() {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   static class PropertiesReader
/*      */     extends LineNumberReader
/*      */   {
/*      */     public PropertiesReader(Reader reader)
/*      */     {
/*  333 */       super();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public String readProperty()
/*      */       throws IOException
/*      */     {
/*  343 */       StringBuffer buffer = new StringBuffer();
/*      */       try
/*      */       {
/*      */         for (;;) {
/*  347 */           String line = readLine().trim();
/*  348 */           if ((line.length() != 0) && (line.charAt(0) != '#')) {
/*  349 */             if (ExtendedProperties.endsWithSlash(line)) {
/*  350 */               line = line.substring(0, line.length() - 1);
/*  351 */               buffer.append(line);
/*      */             } else {
/*  353 */               buffer.append(line);
/*  354 */               break;
/*      */             }
/*      */           }
/*      */         }
/*      */       } catch (NullPointerException ex) {
/*  359 */         return null;
/*      */       }
/*      */       
/*  362 */       return buffer.toString();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static class PropertiesTokenizer
/*      */     extends StringTokenizer
/*      */   {
/*      */     static final String DELIMITER = ",";
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public PropertiesTokenizer(String string)
/*      */     {
/*  383 */       super(",");
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public boolean hasMoreTokens()
/*      */     {
/*  392 */       return super.hasMoreTokens();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public String nextToken()
/*      */     {
/*  401 */       StringBuffer buffer = new StringBuffer();
/*      */       
/*  403 */       while (hasMoreTokens()) {
/*  404 */         String token = super.nextToken();
/*  405 */         if (ExtendedProperties.endsWithSlash(token)) {
/*  406 */           buffer.append(token.substring(0, token.length() - 1));
/*  407 */           buffer.append(",");
/*      */         } else {
/*  409 */           buffer.append(token);
/*  410 */           break;
/*      */         }
/*      */       }
/*      */       
/*  414 */       return buffer.toString().trim();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ExtendedProperties(String file)
/*      */     throws IOException
/*      */   {
/*  432 */     this(file, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ExtendedProperties(String file, String defaultFile)
/*      */     throws IOException
/*      */   {
/*  443 */     this.file = file;
/*      */     
/*  445 */     this.basePath = new File(file).getAbsolutePath();
/*  446 */     this.basePath = this.basePath.substring(0, this.basePath.lastIndexOf(this.fileSeparator) + 1);
/*      */     
/*  448 */     FileInputStream in = null;
/*      */     try {
/*  450 */       in = new FileInputStream(file);
/*  451 */       load(in);
/*      */       try
/*      */       {
/*  454 */         if (in != null) {
/*  455 */           in.close();
/*      */         }
/*      */       }
/*      */       catch (IOException ex) {}
/*      */       
/*      */ 
/*  461 */       if (defaultFile == null) {
/*      */         return;
/*      */       }
/*      */     }
/*      */     finally
/*      */     {
/*      */       try
/*      */       {
/*  454 */         if (in != null) {
/*  455 */           in.close();
/*      */         }
/*      */       }
/*      */       catch (IOException ex) {}
/*      */     }
/*      */     
/*      */ 
/*  462 */     this.defaults = new ExtendedProperties(defaultFile);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isInitialized()
/*      */   {
/*  471 */     return this.isInitialized;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getInclude()
/*      */   {
/*  481 */     return include;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setInclude(String inc)
/*      */   {
/*  491 */     include = inc;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void load(InputStream input)
/*      */     throws IOException
/*      */   {
/*  501 */     load(input, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized void load(InputStream input, String enc)
/*      */     throws IOException
/*      */   {
/*  513 */     PropertiesReader reader = null;
/*  514 */     if (enc != null) {
/*      */       try {
/*  516 */         reader = new PropertiesReader(new InputStreamReader(input, enc));
/*      */       }
/*      */       catch (UnsupportedEncodingException ex) {}
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  523 */     if (reader == null) {
/*      */       try {
/*  525 */         reader = new PropertiesReader(new InputStreamReader(input, "8859_1"));
/*      */ 
/*      */       }
/*      */       catch (UnsupportedEncodingException ex)
/*      */       {
/*  530 */         reader = new PropertiesReader(new InputStreamReader(input));
/*      */       }
/*      */     }
/*      */     try
/*      */     {
/*      */       for (;;) {
/*  536 */         String line = reader.readProperty();
/*  537 */         int equalSign = line.indexOf('=');
/*      */         
/*  539 */         if (equalSign > 0) {
/*  540 */           String key = line.substring(0, equalSign).trim();
/*  541 */           String value = line.substring(equalSign + 1).trim();
/*      */           
/*      */ 
/*  544 */           if (!"".equals(value))
/*      */           {
/*      */ 
/*      */ 
/*  548 */             if ((getInclude() != null) && (key.equalsIgnoreCase(getInclude())))
/*      */             {
/*  550 */               File file = null;
/*      */               
/*  552 */               if (value.startsWith(this.fileSeparator))
/*      */               {
/*  554 */                 file = new File(value);
/*      */ 
/*      */               }
/*      */               else
/*      */               {
/*      */ 
/*  560 */                 if (value.startsWith("." + this.fileSeparator)) {
/*  561 */                   value = value.substring(2);
/*      */                 }
/*      */                 
/*  564 */                 file = new File(this.basePath + value);
/*      */               }
/*      */               
/*  567 */               if ((file != null) && (file.exists()) && (file.canRead())) {
/*  568 */                 load(new FileInputStream(file));
/*      */               }
/*      */             } else {
/*  571 */               addProperty(key, value);
/*      */             }
/*      */             
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     catch (NullPointerException ex) {}finally
/*      */     {
/*  580 */       this.isInitialized = true;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object getProperty(String key)
/*      */   {
/*  593 */     Object obj = get(key);
/*      */     
/*  595 */     if (obj == null)
/*      */     {
/*      */ 
/*  598 */       if (this.defaults != null) {
/*  599 */         obj = this.defaults.get(key);
/*      */       }
/*      */     }
/*      */     
/*  603 */     return obj;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addProperty(String key, Object value)
/*      */   {
/*  626 */     if ((value instanceof String)) {
/*  627 */       String str = (String)value;
/*  628 */       if (str.indexOf(",") > 0)
/*      */       {
/*  630 */         PropertiesTokenizer tokenizer = new PropertiesTokenizer(str);
/*  631 */         while (tokenizer.hasMoreTokens()) {
/*  632 */           String token = tokenizer.nextToken();
/*  633 */           addPropertyInternal(key, unescape(token));
/*      */         }
/*      */       }
/*      */       else {
/*  637 */         addPropertyInternal(key, unescape(str));
/*      */       }
/*      */     } else {
/*  640 */       addPropertyInternal(key, value);
/*      */     }
/*      */     
/*      */ 
/*  644 */     this.isInitialized = true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void addPropertyDirect(String key, Object value)
/*      */   {
/*  656 */     if (!containsKey(key)) {
/*  657 */       this.keysAsListed.add(key);
/*      */     }
/*  659 */     put(key, value);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void addPropertyInternal(String key, Object value)
/*      */   {
/*  674 */     Object current = get(key);
/*      */     
/*  676 */     if ((current instanceof String))
/*      */     {
/*  678 */       Vector v = new Vector(2);
/*  679 */       v.addElement(current);
/*  680 */       v.addElement(value);
/*  681 */       put(key, v);
/*      */     }
/*  683 */     else if ((current instanceof Vector))
/*      */     {
/*  685 */       ((Vector)current).addElement(value);
/*      */     }
/*      */     else
/*      */     {
/*  689 */       if (!containsKey(key)) {
/*  690 */         this.keysAsListed.add(key);
/*      */       }
/*  692 */       put(key, value);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setProperty(String key, Object value)
/*      */   {
/*  705 */     clearProperty(key);
/*  706 */     addProperty(key, value);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized void save(OutputStream output, String header)
/*      */     throws IOException
/*      */   {
/*  719 */     if (output == null) {
/*  720 */       return;
/*      */     }
/*  722 */     PrintWriter theWrtr = new PrintWriter(output);
/*  723 */     if (header != null) {
/*  724 */       theWrtr.println(header);
/*      */     }
/*      */     
/*  727 */     Enumeration theKeys = keys();
/*  728 */     while (theKeys.hasMoreElements()) {
/*  729 */       String key = (String)theKeys.nextElement();
/*  730 */       Object value = get(key);
/*  731 */       if (value != null) {
/*  732 */         if ((value instanceof String)) {
/*  733 */           StringBuffer currentOutput = new StringBuffer();
/*  734 */           currentOutput.append(key);
/*  735 */           currentOutput.append("=");
/*  736 */           currentOutput.append(escape((String)value));
/*  737 */           theWrtr.println(currentOutput.toString());
/*      */         }
/*  739 */         else if ((value instanceof Vector)) {
/*  740 */           Vector values = (Vector)value;
/*  741 */           Enumeration valuesEnum = values.elements();
/*  742 */           while (valuesEnum.hasMoreElements()) {
/*  743 */             String currentElement = (String)valuesEnum.nextElement();
/*  744 */             StringBuffer currentOutput = new StringBuffer();
/*  745 */             currentOutput.append(key);
/*  746 */             currentOutput.append("=");
/*  747 */             currentOutput.append(escape(currentElement));
/*  748 */             theWrtr.println(currentOutput.toString());
/*      */           }
/*      */         }
/*      */       }
/*  752 */       theWrtr.println();
/*  753 */       theWrtr.flush();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void combine(ExtendedProperties props)
/*      */   {
/*  765 */     for (Iterator it = props.getKeys(); it.hasNext();) {
/*  766 */       String key = (String)it.next();
/*  767 */       setProperty(key, props.get(key));
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void clearProperty(String key)
/*      */   {
/*  777 */     if (containsKey(key))
/*      */     {
/*      */ 
/*  780 */       for (int i = 0; i < this.keysAsListed.size(); i++) {
/*  781 */         if (((String)this.keysAsListed.get(i)).equals(key)) {
/*  782 */           this.keysAsListed.remove(i);
/*  783 */           break;
/*      */         }
/*      */       }
/*  786 */       remove(key);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Iterator getKeys()
/*      */   {
/*  797 */     return this.keysAsListed.iterator();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Iterator getKeys(String prefix)
/*      */   {
/*  808 */     Iterator keys = getKeys();
/*  809 */     ArrayList matchingKeys = new ArrayList();
/*      */     
/*  811 */     while (keys.hasNext()) {
/*  812 */       Object key = keys.next();
/*      */       
/*  814 */       if (((key instanceof String)) && (((String)key).startsWith(prefix))) {
/*  815 */         matchingKeys.add(key);
/*      */       }
/*      */     }
/*  818 */     return matchingKeys.iterator();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ExtendedProperties subset(String prefix)
/*      */   {
/*  830 */     ExtendedProperties c = new ExtendedProperties();
/*  831 */     Iterator keys = getKeys();
/*  832 */     boolean validSubset = false;
/*      */     
/*  834 */     while (keys.hasNext()) {
/*  835 */       Object key = keys.next();
/*      */       
/*  837 */       if (((key instanceof String)) && (((String)key).startsWith(prefix))) {
/*  838 */         if (!validSubset) {
/*  839 */           validSubset = true;
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  848 */         String newKey = null;
/*  849 */         if (((String)key).length() == prefix.length()) {
/*  850 */           newKey = prefix;
/*      */         } else {
/*  852 */           newKey = ((String)key).substring(prefix.length() + 1);
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  860 */         c.addPropertyDirect(newKey, get(key));
/*      */       }
/*      */     }
/*      */     
/*  864 */     if (validSubset) {
/*  865 */       return c;
/*      */     }
/*  867 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void display()
/*      */   {
/*  875 */     Iterator i = getKeys();
/*      */     
/*  877 */     while (i.hasNext()) {
/*  878 */       String key = (String)i.next();
/*  879 */       Object value = get(key);
/*  880 */       System.out.println(key + " => " + value);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getString(String key)
/*      */   {
/*  893 */     return getString(key, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getString(String key, String defaultValue)
/*      */   {
/*  907 */     Object value = get(key);
/*      */     
/*  909 */     if ((value instanceof String)) {
/*  910 */       return interpolate((String)value);
/*      */     }
/*  912 */     if (value == null) {
/*  913 */       if (this.defaults != null) {
/*  914 */         return interpolate(this.defaults.getString(key, defaultValue));
/*      */       }
/*  916 */       return interpolate(defaultValue);
/*      */     }
/*  918 */     if ((value instanceof Vector)) {
/*  919 */       return interpolate((String)((Vector)value).get(0));
/*      */     }
/*  921 */     throw new ClassCastException('\'' + key + "' doesn't map to a String object");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Properties getProperties(String key)
/*      */   {
/*  937 */     return getProperties(key, new Properties());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Properties getProperties(String key, Properties defaults)
/*      */   {
/*  955 */     String[] tokens = getStringArray(key);
/*      */     
/*      */ 
/*  958 */     Properties props = new Properties(defaults);
/*  959 */     for (int i = 0; i < tokens.length; i++) {
/*  960 */       String token = tokens[i];
/*  961 */       int equalSign = token.indexOf('=');
/*  962 */       if (equalSign > 0) {
/*  963 */         String pkey = token.substring(0, equalSign).trim();
/*  964 */         String pvalue = token.substring(equalSign + 1).trim();
/*  965 */         props.put(pkey, pvalue);
/*      */       } else {
/*  967 */         throw new IllegalArgumentException('\'' + token + "' does not contain " + "an equals sign");
/*      */       }
/*      */     }
/*  970 */     return props;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String[] getStringArray(String key)
/*      */   {
/*  983 */     Object value = get(key);
/*      */     
/*      */ 
/*      */ 
/*  987 */     if ((value instanceof String)) {
/*  988 */       Vector vector = new Vector(1);
/*  989 */       vector.addElement(value);
/*      */     } else { Vector vector;
/*  991 */       if ((value instanceof Vector)) {
/*  992 */         vector = (Vector)value;
/*      */       } else {
/*  994 */         if (value == null) {
/*  995 */           if (this.defaults != null) {
/*  996 */             return this.defaults.getStringArray(key);
/*      */           }
/*  998 */           return new String[0];
/*      */         }
/*      */         
/* 1001 */         throw new ClassCastException('\'' + key + "' doesn't map to a String/Vector object");
/*      */       } }
/*      */     Vector vector;
/* 1004 */     String[] tokens = new String[vector.size()];
/* 1005 */     for (int i = 0; i < tokens.length; i++) {
/* 1006 */       tokens[i] = ((String)vector.elementAt(i));
/*      */     }
/*      */     
/* 1009 */     return tokens;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Vector getVector(String key)
/*      */   {
/* 1022 */     return getVector(key, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Vector getVector(String key, Vector defaultValue)
/*      */   {
/* 1036 */     Object value = get(key);
/*      */     
/* 1038 */     if ((value instanceof Vector)) {
/* 1039 */       return (Vector)value;
/*      */     }
/* 1041 */     if ((value instanceof String)) {
/* 1042 */       Vector v = new Vector(1);
/* 1043 */       v.addElement(value);
/* 1044 */       put(key, v);
/* 1045 */       return v;
/*      */     }
/* 1047 */     if (value == null) {
/* 1048 */       if (this.defaults != null) {
/* 1049 */         return this.defaults.getVector(key, defaultValue);
/*      */       }
/* 1051 */       return defaultValue == null ? new Vector() : defaultValue;
/*      */     }
/*      */     
/* 1054 */     throw new ClassCastException('\'' + key + "' doesn't map to a Vector object");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getBoolean(String key)
/*      */   {
/* 1069 */     Boolean b = getBoolean(key, null);
/* 1070 */     if (b != null) {
/* 1071 */       return b.booleanValue();
/*      */     }
/* 1073 */     throw new NoSuchElementException('\'' + key + "' doesn't map to an existing object");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getBoolean(String key, boolean defaultValue)
/*      */   {
/* 1087 */     return getBoolean(key, new Boolean(defaultValue)).booleanValue();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Boolean getBoolean(String key, Boolean defaultValue)
/*      */   {
/* 1102 */     Object value = get(key);
/*      */     
/* 1104 */     if ((value instanceof Boolean)) {
/* 1105 */       return (Boolean)value;
/*      */     }
/* 1107 */     if ((value instanceof String)) {
/* 1108 */       String s = testBoolean((String)value);
/* 1109 */       Boolean b = new Boolean(s);
/* 1110 */       put(key, b);
/* 1111 */       return b;
/*      */     }
/* 1113 */     if (value == null) {
/* 1114 */       if (this.defaults != null) {
/* 1115 */         return this.defaults.getBoolean(key, defaultValue);
/*      */       }
/* 1117 */       return defaultValue;
/*      */     }
/*      */     
/* 1120 */     throw new ClassCastException('\'' + key + "' doesn't map to a Boolean object");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String testBoolean(String value)
/*      */   {
/* 1137 */     String s = value.toLowerCase();
/*      */     
/* 1139 */     if ((s.equals("true")) || (s.equals("on")) || (s.equals("yes")))
/* 1140 */       return "true";
/* 1141 */     if ((s.equals("false")) || (s.equals("off")) || (s.equals("no"))) {
/* 1142 */       return "false";
/*      */     }
/* 1144 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte getByte(String key)
/*      */   {
/* 1161 */     Byte b = getByte(key, null);
/* 1162 */     if (b != null) {
/* 1163 */       return b.byteValue();
/*      */     }
/* 1165 */     throw new NoSuchElementException('\'' + key + " doesn't map to an existing object");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte getByte(String key, byte defaultValue)
/*      */   {
/* 1181 */     return getByte(key, new Byte(defaultValue)).byteValue();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Byte getByte(String key, Byte defaultValue)
/*      */   {
/* 1197 */     Object value = get(key);
/*      */     
/* 1199 */     if ((value instanceof Byte)) {
/* 1200 */       return (Byte)value;
/*      */     }
/* 1202 */     if ((value instanceof String)) {
/* 1203 */       Byte b = new Byte((String)value);
/* 1204 */       put(key, b);
/* 1205 */       return b;
/*      */     }
/* 1207 */     if (value == null) {
/* 1208 */       if (this.defaults != null) {
/* 1209 */         return this.defaults.getByte(key, defaultValue);
/*      */       }
/* 1211 */       return defaultValue;
/*      */     }
/*      */     
/* 1214 */     throw new ClassCastException('\'' + key + "' doesn't map to a Byte object");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public short getShort(String key)
/*      */   {
/* 1231 */     Short s = getShort(key, null);
/* 1232 */     if (s != null) {
/* 1233 */       return s.shortValue();
/*      */     }
/* 1235 */     throw new NoSuchElementException('\'' + key + "' doesn't map to an existing object");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public short getShort(String key, short defaultValue)
/*      */   {
/* 1251 */     return getShort(key, new Short(defaultValue)).shortValue();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Short getShort(String key, Short defaultValue)
/*      */   {
/* 1267 */     Object value = get(key);
/*      */     
/* 1269 */     if ((value instanceof Short)) {
/* 1270 */       return (Short)value;
/*      */     }
/* 1272 */     if ((value instanceof String)) {
/* 1273 */       Short s = new Short((String)value);
/* 1274 */       put(key, s);
/* 1275 */       return s;
/*      */     }
/* 1277 */     if (value == null) {
/* 1278 */       if (this.defaults != null) {
/* 1279 */         return this.defaults.getShort(key, defaultValue);
/*      */       }
/* 1281 */       return defaultValue;
/*      */     }
/*      */     
/* 1284 */     throw new ClassCastException('\'' + key + "' doesn't map to a Short object");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getInt(String name)
/*      */   {
/* 1296 */     return getInteger(name);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getInt(String name, int def)
/*      */   {
/* 1308 */     return getInteger(name, def);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getInteger(String key)
/*      */   {
/* 1324 */     Integer i = getInteger(key, null);
/* 1325 */     if (i != null) {
/* 1326 */       return i.intValue();
/*      */     }
/* 1328 */     throw new NoSuchElementException('\'' + key + "' doesn't map to an existing object");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getInteger(String key, int defaultValue)
/*      */   {
/* 1344 */     Integer i = getInteger(key, null);
/*      */     
/* 1346 */     if (i == null) {
/* 1347 */       return defaultValue;
/*      */     }
/* 1349 */     return i.intValue();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Integer getInteger(String key, Integer defaultValue)
/*      */   {
/* 1365 */     Object value = get(key);
/*      */     
/* 1367 */     if ((value instanceof Integer)) {
/* 1368 */       return (Integer)value;
/*      */     }
/* 1370 */     if ((value instanceof String)) {
/* 1371 */       Integer i = new Integer((String)value);
/* 1372 */       put(key, i);
/* 1373 */       return i;
/*      */     }
/* 1375 */     if (value == null) {
/* 1376 */       if (this.defaults != null) {
/* 1377 */         return this.defaults.getInteger(key, defaultValue);
/*      */       }
/* 1379 */       return defaultValue;
/*      */     }
/*      */     
/* 1382 */     throw new ClassCastException('\'' + key + "' doesn't map to a Integer object");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public long getLong(String key)
/*      */   {
/* 1399 */     Long l = getLong(key, null);
/* 1400 */     if (l != null) {
/* 1401 */       return l.longValue();
/*      */     }
/* 1403 */     throw new NoSuchElementException('\'' + key + "' doesn't map to an existing object");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public long getLong(String key, long defaultValue)
/*      */   {
/* 1419 */     return getLong(key, new Long(defaultValue)).longValue();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Long getLong(String key, Long defaultValue)
/*      */   {
/* 1435 */     Object value = get(key);
/*      */     
/* 1437 */     if ((value instanceof Long)) {
/* 1438 */       return (Long)value;
/*      */     }
/* 1440 */     if ((value instanceof String)) {
/* 1441 */       Long l = new Long((String)value);
/* 1442 */       put(key, l);
/* 1443 */       return l;
/*      */     }
/* 1445 */     if (value == null) {
/* 1446 */       if (this.defaults != null) {
/* 1447 */         return this.defaults.getLong(key, defaultValue);
/*      */       }
/* 1449 */       return defaultValue;
/*      */     }
/*      */     
/* 1452 */     throw new ClassCastException('\'' + key + "' doesn't map to a Long object");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public float getFloat(String key)
/*      */   {
/* 1469 */     Float f = getFloat(key, null);
/* 1470 */     if (f != null) {
/* 1471 */       return f.floatValue();
/*      */     }
/* 1473 */     throw new NoSuchElementException('\'' + key + "' doesn't map to an existing object");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public float getFloat(String key, float defaultValue)
/*      */   {
/* 1489 */     return getFloat(key, new Float(defaultValue)).floatValue();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Float getFloat(String key, Float defaultValue)
/*      */   {
/* 1505 */     Object value = get(key);
/*      */     
/* 1507 */     if ((value instanceof Float)) {
/* 1508 */       return (Float)value;
/*      */     }
/* 1510 */     if ((value instanceof String)) {
/* 1511 */       Float f = new Float((String)value);
/* 1512 */       put(key, f);
/* 1513 */       return f;
/*      */     }
/* 1515 */     if (value == null) {
/* 1516 */       if (this.defaults != null) {
/* 1517 */         return this.defaults.getFloat(key, defaultValue);
/*      */       }
/* 1519 */       return defaultValue;
/*      */     }
/*      */     
/* 1522 */     throw new ClassCastException('\'' + key + "' doesn't map to a Float object");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getDouble(String key)
/*      */   {
/* 1539 */     Double d = getDouble(key, null);
/* 1540 */     if (d != null) {
/* 1541 */       return d.doubleValue();
/*      */     }
/* 1543 */     throw new NoSuchElementException('\'' + key + "' doesn't map to an existing object");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getDouble(String key, double defaultValue)
/*      */   {
/* 1559 */     return getDouble(key, new Double(defaultValue)).doubleValue();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Double getDouble(String key, Double defaultValue)
/*      */   {
/* 1575 */     Object value = get(key);
/*      */     
/* 1577 */     if ((value instanceof Double)) {
/* 1578 */       return (Double)value;
/*      */     }
/* 1580 */     if ((value instanceof String)) {
/* 1581 */       Double d = new Double((String)value);
/* 1582 */       put(key, d);
/* 1583 */       return d;
/*      */     }
/* 1585 */     if (value == null) {
/* 1586 */       if (this.defaults != null) {
/* 1587 */         return this.defaults.getDouble(key, defaultValue);
/*      */       }
/* 1589 */       return defaultValue;
/*      */     }
/*      */     
/* 1592 */     throw new ClassCastException('\'' + key + "' doesn't map to a Double object");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static ExtendedProperties convertProperties(Properties props)
/*      */   {
/* 1603 */     ExtendedProperties c = new ExtendedProperties();
/*      */     
/* 1605 */     for (Enumeration e = props.keys(); e.hasMoreElements();) {
/* 1606 */       String s = (String)e.nextElement();
/* 1607 */       c.setProperty(s, props.getProperty(s));
/*      */     }
/*      */     
/* 1610 */     return c;
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/ExtendedProperties.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */