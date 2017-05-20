/*     */ package jargs.gnu;
/*     */ 
/*     */ import java.text.NumberFormat;
/*     */ import java.text.ParseException;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Locale;
/*     */ import java.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CmdLineParser
/*     */ {
/*     */   public static abstract class OptionException
/*     */     extends Exception
/*     */   {
/*     */     OptionException(String msg)
/*     */     {
/*  26 */       super();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static class UnknownOptionException
/*     */     extends CmdLineParser.OptionException
/*     */   {
/*     */     UnknownOptionException(String optionName)
/*     */     {
/*  37 */       this(optionName, "Unknown option '" + optionName + "'");
/*     */     }
/*     */     
/*     */     UnknownOptionException(String optionName, String msg) {
/*  41 */       super();
/*  42 */       this.optionName = optionName;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  48 */     public String getOptionName() { return this.optionName; }
/*  49 */     private String optionName = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static class UnknownSuboptionException
/*     */     extends CmdLineParser.UnknownOptionException
/*     */   {
/*     */     private char suboption;
/*     */     
/*     */ 
/*     */ 
/*     */     UnknownSuboptionException(String option, char suboption)
/*     */     {
/*  64 */       super("Illegal option: '" + suboption + "' in '" + option + "'");
/*  65 */       this.suboption = suboption; }
/*     */     
/*  67 */     public char getSuboption() { return this.suboption; }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static class NotFlagException
/*     */     extends CmdLineParser.UnknownOptionException
/*     */   {
/*     */     private char notflag;
/*     */     
/*     */ 
/*     */ 
/*     */     NotFlagException(String option, char unflaggish)
/*     */     {
/*  81 */       super("Illegal option: '" + option + "', '" + unflaggish + "' requires a value");
/*     */       
/*  83 */       this.notflag = unflaggish;
/*     */     }
/*     */     
/*     */ 
/*     */     public char getOptionChar()
/*     */     {
/*  89 */       return this.notflag;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class IllegalOptionValueException extends CmdLineParser.OptionException
/*     */   {
/*     */     private CmdLineParser.Option option;
/*     */     private String value;
/*     */     
/*     */     public IllegalOptionValueException(CmdLineParser.Option opt, String value)
/*     */     {
/* 100 */       super();
/*     */       
/*     */ 
/* 103 */       this.option = opt;
/* 104 */       this.value = value;
/*     */     }
/*     */     
/*     */ 
/*     */     public CmdLineParser.Option getOption()
/*     */     {
/* 110 */       return this.option;
/*     */     }
/*     */     
/*     */     public String getValue()
/*     */     {
/* 115 */       return this.value;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static abstract class Option
/*     */   {
/*     */     protected Option(String longForm, boolean wantsValue)
/*     */     {
/* 126 */       this(null, longForm, wantsValue);
/*     */     }
/*     */     
/*     */     protected Option(char shortForm, String longForm, boolean wantsValue)
/*     */     {
/* 131 */       this(new String(new char[] { shortForm }), longForm, wantsValue);
/*     */     }
/*     */     
/*     */     private Option(String shortForm, String longForm, boolean wantsValue) {
/* 135 */       if (longForm == null)
/* 136 */         throw new IllegalArgumentException("Null longForm not allowed");
/* 137 */       this.shortForm = shortForm;
/* 138 */       this.longForm = longForm;
/* 139 */       this.wantsValue = wantsValue;
/*     */     }
/*     */     
/* 142 */     public String shortForm() { return this.shortForm; }
/*     */     
/* 144 */     public String longForm() { return this.longForm; }
/*     */     
/*     */ 
/*     */     public boolean wantsValue()
/*     */     {
/* 149 */       return this.wantsValue;
/*     */     }
/*     */     
/*     */     public final Object getValue(String arg, Locale locale) throws CmdLineParser.IllegalOptionValueException {
/* 153 */       if (this.wantsValue) {
/* 154 */         if (arg == null) {
/* 155 */           throw new CmdLineParser.IllegalOptionValueException(this, "");
/*     */         }
/* 157 */         return parseValue(arg, locale);
/*     */       }
/*     */       
/* 160 */       return Boolean.TRUE;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     protected Object parseValue(String arg, Locale locale)
/*     */       throws CmdLineParser.IllegalOptionValueException
/*     */     {
/* 170 */       return null;
/*     */     }
/*     */     
/* 173 */     private String shortForm = null;
/* 174 */     private String longForm = null;
/* 175 */     private boolean wantsValue = false;
/*     */     
/*     */     public static class BooleanOption extends CmdLineParser.Option {
/*     */       public BooleanOption(char shortForm, String longForm) {
/* 179 */         super(longForm, false);
/*     */       }
/*     */       
/* 182 */       public BooleanOption(String longForm) { super(false); }
/*     */     }
/*     */     
/*     */ 
/*     */     public static class IntegerOption
/*     */       extends CmdLineParser.Option
/*     */     {
/*     */       public IntegerOption(char shortForm, String longForm)
/*     */       {
/* 191 */         super(longForm, true);
/*     */       }
/*     */       
/* 194 */       public IntegerOption(String longForm) { super(true); }
/*     */       
/*     */       protected Object parseValue(String arg, Locale locale) throws CmdLineParser.IllegalOptionValueException
/*     */       {
/*     */         try {
/* 199 */           return new Integer(arg);
/*     */         }
/*     */         catch (NumberFormatException e) {
/* 202 */           throw new CmdLineParser.IllegalOptionValueException(this, arg);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     public static class LongOption
/*     */       extends CmdLineParser.Option
/*     */     {
/*     */       public LongOption(char shortForm, String longForm)
/*     */       {
/* 212 */         super(longForm, true);
/*     */       }
/*     */       
/* 215 */       public LongOption(String longForm) { super(true); }
/*     */       
/*     */       protected Object parseValue(String arg, Locale locale) throws CmdLineParser.IllegalOptionValueException
/*     */       {
/*     */         try {
/* 220 */           return new Long(arg);
/*     */         }
/*     */         catch (NumberFormatException e) {
/* 223 */           throw new CmdLineParser.IllegalOptionValueException(this, arg);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     public static class DoubleOption
/*     */       extends CmdLineParser.Option
/*     */     {
/*     */       public DoubleOption(char shortForm, String longForm)
/*     */       {
/* 233 */         super(longForm, true);
/*     */       }
/*     */       
/* 236 */       public DoubleOption(String longForm) { super(true); }
/*     */       
/*     */       protected Object parseValue(String arg, Locale locale) throws CmdLineParser.IllegalOptionValueException
/*     */       {
/*     */         try {
/* 241 */           NumberFormat format = NumberFormat.getNumberInstance(locale);
/* 242 */           Number num = format.parse(arg);
/* 243 */           return new Double(num.doubleValue());
/*     */         }
/*     */         catch (ParseException e) {
/* 246 */           throw new CmdLineParser.IllegalOptionValueException(this, arg);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     public static class StringOption
/*     */       extends CmdLineParser.Option
/*     */     {
/*     */       public StringOption(char shortForm, String longForm)
/*     */       {
/* 256 */         super(longForm, true);
/*     */       }
/*     */       
/* 259 */       public StringOption(String longForm) { super(true); }
/*     */       
/*     */       protected Object parseValue(String arg, Locale locale) {
/* 262 */         return arg;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public final Option addOption(Option opt)
/*     */   {
/* 271 */     if (opt.shortForm() != null)
/* 272 */       this.options.put("-" + opt.shortForm(), opt);
/* 273 */     this.options.put("--" + opt.longForm(), opt);
/* 274 */     return opt;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final Option addStringOption(char shortForm, String longForm)
/*     */   {
/* 282 */     return addOption(new CmdLineParser.Option.StringOption(shortForm, longForm));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final Option addStringOption(String longForm)
/*     */   {
/* 290 */     return addOption(new CmdLineParser.Option.StringOption(longForm));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final Option addIntegerOption(char shortForm, String longForm)
/*     */   {
/* 298 */     return addOption(new CmdLineParser.Option.IntegerOption(shortForm, longForm));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final Option addIntegerOption(String longForm)
/*     */   {
/* 306 */     return addOption(new CmdLineParser.Option.IntegerOption(longForm));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final Option addLongOption(char shortForm, String longForm)
/*     */   {
/* 314 */     return addOption(new CmdLineParser.Option.LongOption(shortForm, longForm));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final Option addLongOption(String longForm)
/*     */   {
/* 322 */     return addOption(new CmdLineParser.Option.LongOption(longForm));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final Option addDoubleOption(char shortForm, String longForm)
/*     */   {
/* 330 */     return addOption(new CmdLineParser.Option.DoubleOption(shortForm, longForm));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final Option addDoubleOption(String longForm)
/*     */   {
/* 338 */     return addOption(new CmdLineParser.Option.DoubleOption(longForm));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final Option addBooleanOption(char shortForm, String longForm)
/*     */   {
/* 346 */     return addOption(new CmdLineParser.Option.BooleanOption(shortForm, longForm));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final Option addBooleanOption(String longForm)
/*     */   {
/* 354 */     return addOption(new CmdLineParser.Option.BooleanOption(longForm));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final Object getOptionValue(Option o)
/*     */   {
/* 362 */     return getOptionValue(o, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final Object getOptionValue(Option o, Object def)
/*     */   {
/* 371 */     Vector v = (Vector)this.values.get(o.longForm());
/*     */     
/* 373 */     if (v == null) {
/* 374 */       return def;
/*     */     }
/* 376 */     if (v.isEmpty()) {
/* 377 */       return null;
/*     */     }
/*     */     
/* 380 */     Object result = v.elementAt(0);
/* 381 */     v.removeElementAt(0);
/* 382 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final Vector getOptionValues(Option option)
/*     */   {
/* 392 */     Vector result = new Vector();
/*     */     for (;;)
/*     */     {
/* 395 */       Object o = getOptionValue(option, null);
/*     */       
/* 397 */       if (o == null) {
/* 398 */         return result;
/*     */       }
/*     */       
/* 401 */       result.addElement(o);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final String[] getRemainingArgs()
/*     */   {
/* 411 */     return this.remainingArgs;
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
/*     */   public final void parse(String[] argv)
/*     */     throws CmdLineParser.IllegalOptionValueException, CmdLineParser.UnknownOptionException
/*     */   {
/* 426 */     parse(argv, Locale.getDefault());
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
/*     */   public final void parse(String[] argv, Locale locale)
/*     */     throws CmdLineParser.IllegalOptionValueException, CmdLineParser.UnknownOptionException
/*     */   {
/* 441 */     Vector otherArgs = new Vector();
/* 442 */     int position = 0;
/* 443 */     this.values = new Hashtable(10);
/* 444 */     while (position < argv.length) {
/* 445 */       String curArg = argv[position];
/* 446 */       if (curArg.startsWith("-")) {
/* 447 */         if (curArg.equals("--")) {
/* 448 */           position++;
/* 449 */           break;
/*     */         }
/* 451 */         String valueArg = null;
/* 452 */         if (curArg.startsWith("--")) {
/* 453 */           int equalsPos = curArg.indexOf("=");
/* 454 */           if (equalsPos != -1) {
/* 455 */             valueArg = curArg.substring(equalsPos + 1);
/* 456 */             curArg = curArg.substring(0, equalsPos);
/*     */           }
/* 458 */         } else if (curArg.length() > 2) {
/* 459 */           for (int i = 1; i < curArg.length(); i++) {
/* 460 */             Option opt = (Option)this.options.get("-" + curArg.charAt(i));
/*     */             
/* 462 */             if (opt == null) { throw new UnknownSuboptionException(curArg, curArg.charAt(i));
/*     */             }
/* 464 */             if (opt.wantsValue()) { throw new NotFlagException(curArg, curArg.charAt(i));
/*     */             }
/* 466 */             addValue(opt, opt.getValue(null, locale));
/*     */           }
/*     */           
/* 469 */           position++;
/* 470 */           continue;
/*     */         }
/*     */         
/* 473 */         Option opt = (Option)this.options.get(curArg);
/* 474 */         if (opt == null) {
/* 475 */           throw new UnknownOptionException(curArg);
/*     */         }
/* 477 */         Object value = null;
/* 478 */         if (opt.wantsValue()) {
/* 479 */           if (valueArg == null) {
/* 480 */             position++;
/* 481 */             if (position < argv.length) {
/* 482 */               valueArg = argv[position];
/*     */             }
/*     */           }
/* 485 */           value = opt.getValue(valueArg, locale);
/*     */         }
/*     */         else {
/* 488 */           value = opt.getValue(null, locale);
/*     */         }
/*     */         
/* 491 */         addValue(opt, value);
/*     */         
/* 493 */         position++;
/*     */       }
/*     */       else {
/* 496 */         otherArgs.addElement(curArg);
/* 497 */         position++;
/*     */       }
/*     */     }
/* 500 */     for (; position < argv.length; position++) {
/* 501 */       otherArgs.addElement(argv[position]);
/*     */     }
/*     */     
/* 504 */     this.remainingArgs = new String[otherArgs.size()];
/* 505 */     otherArgs.copyInto(this.remainingArgs);
/*     */   }
/*     */   
/*     */   private void addValue(Option opt, Object value)
/*     */   {
/* 510 */     String lf = opt.longForm();
/*     */     
/* 512 */     Vector v = (Vector)this.values.get(lf);
/*     */     
/* 514 */     if (v == null) {
/* 515 */       v = new Vector();
/* 516 */       this.values.put(lf, v);
/*     */     }
/*     */     
/* 519 */     v.addElement(value);
/*     */   }
/*     */   
/*     */ 
/* 523 */   private String[] remainingArgs = null;
/* 524 */   private Hashtable options = new Hashtable(10);
/* 525 */   private Hashtable values = new Hashtable(10);
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/jargs/gnu/CmdLineParser.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */