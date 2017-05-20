/*     */ package org.apache.commons.math;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.text.MessageFormat;
/*     */ import java.util.Locale;
/*     */ import java.util.MissingResourceException;
/*     */ import java.util.ResourceBundle;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MathException
/*     */   extends Exception
/*     */ {
/*     */   private static final long serialVersionUID = -8602234299177097102L;
/*     */   private static final boolean JDK_SUPPORTS_NESTED;
/*     */   
/*     */   static
/*     */   {
/*  47 */     boolean flag = false;
/*     */     try {
/*  49 */       Throwable.class.getDeclaredMethod("getCause", new Class[0]);
/*  50 */       flag = true;
/*     */     } catch (NoSuchMethodException ex) {
/*  52 */       flag = false;
/*     */     }
/*  54 */     JDK_SUPPORTS_NESTED = flag;
/*     */   }
/*     */   
/*     */ 
/*  58 */   private static ResourceBundle cachedResources = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private final String pattern;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private final Object[] arguments;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private final Throwable rootCause;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static String translate(String s, Locale locale)
/*     */   {
/*     */     try
/*     */     {
/*  84 */       if ((cachedResources == null) || (!cachedResources.getLocale().equals(locale)))
/*     */       {
/*  86 */         cachedResources = ResourceBundle.getBundle("org.apache.commons.math.MessagesResources", locale);
/*     */       }
/*     */       
/*     */ 
/*  90 */       if (cachedResources.getLocale().getLanguage().equals(locale.getLanguage()))
/*     */       {
/*  92 */         return cachedResources.getString(s);
/*     */       }
/*     */     }
/*     */     catch (MissingResourceException mre) {}
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 101 */     return s;
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
/*     */   private static String buildMessage(String pattern, Object[] arguments, Locale locale)
/*     */   {
/* 114 */     MessageFormat mf = new MessageFormat(translate(pattern, locale));
/* 115 */     mf.setLocale(locale);
/* 116 */     return mf.format(arguments);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MathException()
/*     */   {
/* 125 */     this.pattern = null;
/* 126 */     this.arguments = new Object[0];
/* 127 */     this.rootCause = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public MathException(String msg)
/*     */   {
/* 138 */     super(msg);
/* 139 */     this.pattern = msg;
/* 140 */     this.arguments = new Object[0];
/* 141 */     this.rootCause = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MathException(String pattern, Object[] arguments)
/*     */   {
/* 152 */     super(buildMessage(pattern, arguments, Locale.US));
/* 153 */     this.pattern = pattern;
/* 154 */     this.arguments = ((Object[])arguments.clone());
/* 155 */     this.rootCause = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MathException(Throwable rootCause)
/*     */   {
/* 166 */     super(rootCause == null ? null : rootCause.getMessage());
/* 167 */     this.pattern = getMessage();
/* 168 */     this.arguments = new Object[0];
/* 169 */     this.rootCause = rootCause;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public MathException(String msg, Throwable rootCause)
/*     */   {
/* 182 */     super(msg);
/* 183 */     this.pattern = msg;
/* 184 */     this.arguments = new Object[0];
/* 185 */     this.rootCause = rootCause;
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
/*     */   public MathException(String pattern, Object[] arguments, Throwable rootCause)
/*     */   {
/* 199 */     super(buildMessage(pattern, arguments, Locale.US));
/* 200 */     this.pattern = pattern;
/* 201 */     this.arguments = ((Object[])arguments.clone());
/* 202 */     this.rootCause = rootCause;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getPattern()
/*     */   {
/* 211 */     return this.pattern;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object[] getArguments()
/*     */   {
/* 220 */     return (Object[])this.arguments.clone();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getMessage(Locale locale)
/*     */   {
/* 231 */     return this.pattern == null ? null : buildMessage(this.pattern, this.arguments, locale);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Throwable getCause()
/*     */   {
/* 240 */     return this.rootCause;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void printStackTrace()
/*     */   {
/* 247 */     printStackTrace(System.err);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void printStackTrace(PrintStream out)
/*     */   {
/* 256 */     synchronized (out) {
/* 257 */       PrintWriter pw = new PrintWriter(out, false);
/* 258 */       printStackTrace(pw);
/*     */       
/* 260 */       pw.flush();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void printStackTrace(PrintWriter out)
/*     */   {
/* 270 */     synchronized (out) {
/* 271 */       super.printStackTrace(out);
/* 272 */       if ((this.rootCause != null) && (!JDK_SUPPORTS_NESTED)) {
/* 273 */         out.print("Caused by: ");
/* 274 */         this.rootCause.printStackTrace(out);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/MathException.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */