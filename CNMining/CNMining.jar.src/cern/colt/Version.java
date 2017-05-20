/*     */ package cern.colt;
/*     */ 
/*     */ import ViolinStrings.Strings;
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Version
/*     */ {
/*     */   public static String asString()
/*     */   {
/*  28 */     if (getPackage() == null) return "unknown";
/*  29 */     String str = getPackage().getImplementationVendor();
/*  30 */     if (str == null) str = "unknown";
/*  31 */     return "Version " + getMajorVersion() + "." + getMinorVersion() + "." + getMicroVersion() + "." + getBuildVersion() + " (" + getBuildTime() + ")" + "\nPlease report problems to " + str;
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
/*     */   public static String getBuildTime()
/*     */   {
/*  45 */     if (getPackage() == null) return "unknown";
/*  46 */     String str = getPackage().getImplementationVersion();
/*  47 */     if (str == null) return "unknown";
/*  48 */     int i = str.indexOf('(');
/*  49 */     return str.substring(i + 1, str.length() - 1);
/*     */   }
/*     */   
/*     */ 
/*     */   public static int getBuildVersion()
/*     */   {
/*  55 */     return numbers()[3];
/*     */   }
/*     */   
/*     */ 
/*     */   public static int getMajorVersion()
/*     */   {
/*  61 */     return numbers()[0];
/*     */   }
/*     */   
/*     */ 
/*     */   public static int getMicroVersion()
/*     */   {
/*  67 */     return numbers()[2];
/*     */   }
/*     */   
/*     */ 
/*     */   public static int getMinorVersion()
/*     */   {
/*  73 */     return numbers()[1];
/*     */   }
/*     */   
/*     */ 
/*     */   private static Package getPackage()
/*     */   {
/*  79 */     return Package.getPackage("cern.colt");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/*  86 */     System.out.println(asString());
/*     */   }
/*     */   
/*     */ 
/*     */   private static int[] numbers()
/*     */   {
/*  92 */     int i = 4;
/*  93 */     int[] arrayOfInt = new int[i];
/*     */     
/*  95 */     if (getPackage() == null) return arrayOfInt;
/*  96 */     String str = getPackage().getImplementationVersion();
/*  97 */     if (str == null) { return arrayOfInt;
/*     */     }
/*  99 */     int j = str.indexOf('(');
/* 100 */     str = str.substring(0, j);
/* 101 */     str = Strings.stripBlanks(str);
/* 102 */     str = Strings.translate(str, ".", " ");
/* 103 */     for (int k = 0; k < i; k++) {
/* 104 */       arrayOfInt[k] = Integer.parseInt(Strings.word(str, k));
/*     */     }
/*     */     
/* 107 */     return arrayOfInt;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/Version.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */