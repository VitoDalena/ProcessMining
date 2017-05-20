/*      */ import java.io.File;
/*      */ import java.io.PrintStream;
/*      */ import java.util.Arrays;
/*      */ import junit.framework.Assert;
/*      */ import junit.framework.TestCase;
/*      */ import junit.framework.TestSuite;
/*      */ import junit.textui.TestRunner;
/*      */ import lpsolve.AbortListener;
/*      */ import lpsolve.LogListener;
/*      */ import lpsolve.LpSolve;
/*      */ import lpsolve.LpSolveException;
/*      */ import lpsolve.MsgListener;
/*      */ import lpsolve.VersionInfo;
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
/*      */ public class LpSolveTest
/*      */   extends TestCase
/*      */ {
/*      */   public LpSolveTest(String paramString)
/*      */   {
/*   36 */     super(paramString);
/*      */   }
/*      */   
/*      */   public static void main(String[] paramArrayOfString) {
/*   40 */     TestRunner.run(suite());
/*      */   }
/*      */   
/*      */   public static TestSuite suite() {
/*   44 */     return new TestSuite(LpSolveTest.class);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void testVersion()
/*      */     throws Exception
/*      */   {
/*   52 */     VersionInfo localVersionInfo = LpSolve.lpSolveVersion();
/*   53 */     assertEquals(5, localVersionInfo.getMajorversion());
/*   54 */     assertEquals(5, localVersionInfo.getMinorversion());
/*   55 */     assertEquals(0, localVersionInfo.getRelease());
/*      */   }
/*      */   
/*      */   public void testMakeLp() throws Exception
/*      */   {
/*   60 */     LpSolve localLpSolve = LpSolve.makeLp(3, 4);
/*   61 */     assertTrue(localLpSolve.getLp() > 0L);
/*   62 */     localLpSolve.deleteLp();
/*   63 */     assertEquals(0L, localLpSolve.getLp());
/*      */   }
/*      */   
/*      */   public void testTwoProblems() throws Exception {
/*   67 */     LpSolve localLpSolve1 = LpSolve.makeLp(3, 4);
/*   68 */     LpSolve localLpSolve2 = LpSolve.makeLp(5, 6);
/*   69 */     assertTrue(localLpSolve1.getLp() > 0L);
/*   70 */     assertTrue(localLpSolve2.getLp() > 0L);
/*   71 */     assertTrue(localLpSolve1.getLp() != localLpSolve2.getLp());
/*      */     
/*   73 */     assertEquals(3, localLpSolve1.getNrows());
/*   74 */     assertEquals(5, localLpSolve2.getNrows());
/*   75 */     assertEquals(4, localLpSolve1.getNcolumns());
/*   76 */     assertEquals(6, localLpSolve2.getNcolumns());
/*   77 */     assertEquals(3, localLpSolve1.getNorigRows());
/*   78 */     assertEquals(5, localLpSolve2.getNorigRows());
/*   79 */     assertEquals(4, localLpSolve1.getNorigColumns());
/*   80 */     assertEquals(6, localLpSolve2.getNorigColumns());
/*      */     
/*   82 */     localLpSolve1.deleteLp();
/*   83 */     localLpSolve2.deleteLp();
/*   84 */     assertEquals(0L, localLpSolve1.getLp());
/*   85 */     assertEquals(0L, localLpSolve2.getLp());
/*      */   }
/*      */   
/*      */ 
/*      */   private LpSolve setupProblem()
/*      */     throws Exception
/*      */   {
/*   92 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/*   93 */     localLpSolve.strAddConstraint("3 2 2 1", 1, 4.0D);
/*   94 */     localLpSolve.strAddConstraint("0 4 3 1", 2, 3.0D);
/*   95 */     localLpSolve.strSetObjFn("2 3 -2 3");
/*   96 */     return localLpSolve;
/*      */   }
/*      */   
/*      */ 
/*      */   private void verifyProblem(LpSolve paramLpSolve)
/*      */     throws Exception
/*      */   {
/*  103 */     assertEquals(2, paramLpSolve.getNrows());
/*  104 */     assertEquals(4, paramLpSolve.getNcolumns());
/*  105 */     double[] arrayOfDouble = paramLpSolve.getPtrRow(0);
/*  106 */     assertEquals(5, arrayOfDouble.length);
/*  107 */     assertEquals(2.0D, arrayOfDouble[1], 1.0E-8D);
/*  108 */     assertEquals(3.0D, arrayOfDouble[2], 1.0E-8D);
/*  109 */     assertEquals(-2.0D, arrayOfDouble[3], 1.0E-8D);
/*  110 */     assertEquals(3.0D, arrayOfDouble[4], 1.0E-8D);
/*      */   }
/*      */   
/*      */   public void testReadWriteLp() throws Exception {
/*  114 */     String str = "testmodel.lp";
/*  115 */     File localFile = new File(str);
/*  116 */     if (localFile.exists()) { localFile.delete();
/*      */     }
/*  118 */     LpSolve localLpSolve = setupProblem();
/*  119 */     localLpSolve.writeLp(str);
/*  120 */     assertTrue(localFile.exists());
/*  121 */     localLpSolve.deleteLp();
/*      */     
/*  123 */     localLpSolve = LpSolve.readLp(str, 4, "Testmodel LP");
/*  124 */     verifyProblem(localLpSolve);
/*  125 */     localLpSolve.deleteLp();
/*  126 */     if (localFile.exists()) localFile.delete();
/*      */   }
/*      */   
/*      */   public void testReadWriteLpNative() throws Exception {
/*  130 */     String str = "model_äüö.lp";
/*  131 */     File localFile = new File(str);
/*  132 */     if (localFile.exists()) { localFile.delete();
/*      */     }
/*  134 */     LpSolve localLpSolve = setupProblem();
/*  135 */     localLpSolve.writeLp(str);
/*  136 */     assertTrue(localFile.exists());
/*  137 */     localLpSolve.deleteLp();
/*      */     
/*  139 */     localLpSolve = LpSolve.readLp(str, 4, "Testmodel LP");
/*  140 */     verifyProblem(localLpSolve);
/*  141 */     localLpSolve.deleteLp();
/*  142 */     if (localFile.exists()) localFile.delete();
/*      */   }
/*      */   
/*      */   public void testReadWriteMps() throws Exception {
/*  146 */     String str = "testmodel.mps";
/*  147 */     File localFile = new File(str);
/*  148 */     if (localFile.exists()) { localFile.delete();
/*      */     }
/*  150 */     LpSolve localLpSolve = setupProblem();
/*  151 */     localLpSolve.writeMps(str);
/*  152 */     assertTrue(localFile.exists());
/*  153 */     localLpSolve.deleteLp();
/*      */     
/*  155 */     localLpSolve = LpSolve.readMps(str, 4);
/*  156 */     verifyProblem(localLpSolve);
/*  157 */     localLpSolve.deleteLp();
/*  158 */     if (localFile.exists()) localFile.delete();
/*      */   }
/*      */   
/*      */   public void testReadWriteFreeMps() throws Exception {
/*  162 */     String str = "testmodel.fmps";
/*  163 */     File localFile = new File(str);
/*  164 */     if (localFile.exists()) { localFile.delete();
/*      */     }
/*  166 */     LpSolve localLpSolve = setupProblem();
/*  167 */     localLpSolve.writeFreeMps(str);
/*  168 */     assertTrue(localFile.exists());
/*  169 */     localLpSolve.deleteLp();
/*      */     
/*  171 */     localLpSolve = LpSolve.readFreeMps(str, 4);
/*  172 */     verifyProblem(localLpSolve);
/*  173 */     localLpSolve.deleteLp();
/*  174 */     if (localFile.exists()) { localFile.delete();
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
/*      */   private void readWriteXLI(String paramString)
/*      */     throws Exception
/*      */   {
/*  206 */     String str1 = "testmodel_" + paramString + ".txt";
/*  207 */     File localFile = new File(str1);
/*  208 */     if (localFile.exists()) { localFile.delete();
/*      */     }
/*  210 */     String str2 = "xli_" + paramString;
/*  211 */     LpSolve localLpSolve = setupProblem();
/*  212 */     localLpSolve.setXLI(str2);
/*  213 */     localLpSolve.writeXLI(str1, null, false);
/*  214 */     assertTrue(localFile.exists());
/*  215 */     localLpSolve.deleteLp();
/*      */     
/*  217 */     localLpSolve = LpSolve.readXLI(str2, str1, null, null, 4);
/*  218 */     verifyProblem(localLpSolve);
/*  219 */     localLpSolve.deleteLp();
/*  220 */     if (localFile.exists()) localFile.delete();
/*      */   }
/*      */   
/*      */   public void testReadWriteXLI_CPLEX() throws Exception {
/*  224 */     readWriteXLI("CPLEX");
/*      */   }
/*      */   
/*      */   public void testReadWriteXLI_LINDO() throws Exception {
/*  228 */     readWriteXLI("LINDO");
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
/*      */   public void testSetXLI_Unknown()
/*      */     throws Exception
/*      */   {
/*  245 */     LpSolve localLpSolve = LpSolve.makeLp(0, 0);
/*      */     try {
/*  247 */       localLpSolve.setXLI("Unknown");
/*  248 */       fail("Setting a nonexistent XLI should throw an exception");
/*      */     }
/*      */     catch (LpSolveException localLpSolveException)
/*      */     {
/*  252 */       System.out.println("setXLI failed as expected");
/*      */     }
/*  254 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testHasXLI() throws Exception {
/*  258 */     LpSolve localLpSolve = LpSolve.makeLp(0, 0);
/*  259 */     assertFalse(localLpSolve.isNativeXLI());
/*  260 */     assertFalse(localLpSolve.hasXLI());
/*  261 */     localLpSolve.setXLI("xli_CPLEX");
/*  262 */     assertTrue(localLpSolve.hasXLI());
/*  263 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testresizeLp() throws Exception {
/*  267 */     LpSolve localLpSolve = LpSolve.makeLp(0, 0);
/*  268 */     localLpSolve.resizeLp(4, 5);
/*  269 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testAddColumnex() throws Exception {
/*  273 */     LpSolve localLpSolve = LpSolve.makeLp(3, 0);
/*  274 */     assertEquals(0, localLpSolve.getNcolumns());
/*  275 */     double[] arrayOfDouble1 = { 1.5D, 3.5D };
/*  276 */     int[] arrayOfInt = { 1, 3 };
/*  277 */     localLpSolve.addColumnex(2, arrayOfDouble1, arrayOfInt);
/*  278 */     assertEquals(1, localLpSolve.getNcolumns());
/*  279 */     double[] arrayOfDouble2 = localLpSolve.getPtrColumn(1);
/*  280 */     assertEquals(0.0D, arrayOfDouble2[0], 1.0E-8D);
/*  281 */     assertEquals(1.5D, arrayOfDouble2[1], 1.0E-8D);
/*  282 */     assertEquals(0.0D, arrayOfDouble2[2], 1.0E-8D);
/*  283 */     assertEquals(3.5D, arrayOfDouble2[3], 1.0E-8D);
/*  284 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testSetColumn() throws Exception {
/*  288 */     LpSolve localLpSolve = LpSolve.makeLp(3, 0);
/*  289 */     localLpSolve.strAddColumn("1 2 3 4");
/*  290 */     assertEquals(1, localLpSolve.getNcolumns());
/*  291 */     double[] arrayOfDouble1 = localLpSolve.getPtrColumn(1);
/*  292 */     assertEquals(1.0D, arrayOfDouble1[0], 1.0E-8D);
/*  293 */     assertEquals(2.0D, arrayOfDouble1[1], 1.0E-8D);
/*  294 */     assertEquals(3.0D, arrayOfDouble1[2], 1.0E-8D);
/*  295 */     assertEquals(4.0D, arrayOfDouble1[3], 1.0E-8D);
/*  296 */     double[] arrayOfDouble2 = { 5.0D, 6.0D, 7.0D, 8.0D };
/*  297 */     localLpSolve.setColumn(1, arrayOfDouble2);
/*  298 */     arrayOfDouble1 = localLpSolve.getPtrColumn(1);
/*  299 */     assertEquals(5.0D, arrayOfDouble1[0], 1.0E-8D);
/*  300 */     assertEquals(6.0D, arrayOfDouble1[1], 1.0E-8D);
/*  301 */     assertEquals(7.0D, arrayOfDouble1[2], 1.0E-8D);
/*  302 */     assertEquals(8.0D, arrayOfDouble1[3], 1.0E-8D);
/*  303 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testSetColumnex() throws Exception {
/*  307 */     LpSolve localLpSolve = LpSolve.makeLp(3, 0);
/*  308 */     localLpSolve.strAddColumn("1 2 3 4");
/*  309 */     assertEquals(1, localLpSolve.getNcolumns());
/*  310 */     double[] arrayOfDouble1 = localLpSolve.getPtrColumn(1);
/*  311 */     assertEquals(1.0D, arrayOfDouble1[0], 1.0E-8D);
/*  312 */     assertEquals(2.0D, arrayOfDouble1[1], 1.0E-8D);
/*  313 */     assertEquals(3.0D, arrayOfDouble1[2], 1.0E-8D);
/*  314 */     assertEquals(4.0D, arrayOfDouble1[3], 1.0E-8D);
/*  315 */     double[] arrayOfDouble2 = { 6.0D, 8.0D };
/*  316 */     int[] arrayOfInt = { 1, 3 };
/*  317 */     localLpSolve.setColumnex(1, 2, arrayOfDouble2, arrayOfInt);
/*  318 */     arrayOfDouble1 = localLpSolve.getPtrColumn(1);
/*  319 */     assertEquals(0.0D, arrayOfDouble1[0], 1.0E-8D);
/*  320 */     assertEquals(6.0D, arrayOfDouble1[1], 1.0E-8D);
/*  321 */     assertEquals(0.0D, arrayOfDouble1[2], 1.0E-8D);
/*  322 */     assertEquals(8.0D, arrayOfDouble1[3], 1.0E-8D);
/*  323 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testSetRow() throws Exception {
/*  327 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/*  328 */     localLpSolve.strAddConstraint("1 2 3 4", 1, 4.0D);
/*  329 */     double[] arrayOfDouble1 = localLpSolve.getPtrRow(1);
/*  330 */     assertEquals(5, arrayOfDouble1.length);
/*  331 */     assertEquals(1.0D, arrayOfDouble1[1], 1.0E-8D);
/*  332 */     assertEquals(2.0D, arrayOfDouble1[2], 1.0E-8D);
/*  333 */     double[] arrayOfDouble2 = { 0.0D, 5.0D, 6.0D, 7.0D, 8.0D };
/*  334 */     localLpSolve.setRow(1, arrayOfDouble2);
/*  335 */     arrayOfDouble1 = localLpSolve.getPtrRow(1);
/*  336 */     assertEquals(5.0D, arrayOfDouble1[1], 1.0E-8D);
/*  337 */     assertEquals(6.0D, arrayOfDouble1[2], 1.0E-8D);
/*  338 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testSetRowex() throws Exception {
/*  342 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/*  343 */     localLpSolve.strAddConstraint("1 2 3 4", 1, 4.0D);
/*  344 */     double[] arrayOfDouble1 = localLpSolve.getPtrRow(1);
/*  345 */     assertEquals(5, arrayOfDouble1.length);
/*  346 */     assertEquals(1.0D, arrayOfDouble1[1], 1.0E-8D);
/*  347 */     assertEquals(2.0D, arrayOfDouble1[2], 1.0E-8D);
/*  348 */     assertEquals(3.0D, arrayOfDouble1[3], 1.0E-8D);
/*  349 */     assertEquals(4.0D, arrayOfDouble1[4], 1.0E-8D);
/*  350 */     double[] arrayOfDouble2 = { 5.0D, 7.0D };
/*  351 */     int[] arrayOfInt = { 1, 3 };
/*  352 */     localLpSolve.setRowex(1, 2, arrayOfDouble2, arrayOfInt);
/*  353 */     arrayOfDouble1 = localLpSolve.getPtrRow(1);
/*  354 */     assertEquals(5.0D, arrayOfDouble1[1], 1.0E-8D);
/*  355 */     assertEquals(0.0D, arrayOfDouble1[2], 1.0E-8D);
/*  356 */     assertEquals(7.0D, arrayOfDouble1[3], 1.0E-8D);
/*  357 */     assertEquals(0.0D, arrayOfDouble1[4], 1.0E-8D);
/*  358 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetSetBasiscrash() throws Exception {
/*  362 */     LpSolve localLpSolve = LpSolve.makeLp(0, 0);
/*  363 */     assertEquals(0, localLpSolve.getBasiscrash());
/*  364 */     localLpSolve.setBasiscrash(2);
/*  365 */     assertEquals(2, localLpSolve.getBasiscrash());
/*  366 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetSetBbDepthlimit() throws Exception {
/*  370 */     LpSolve localLpSolve = LpSolve.makeLp(0, 0);
/*  371 */     assertEquals(-50, localLpSolve.getBbDepthlimit());
/*  372 */     localLpSolve.setBbDepthlimit(10);
/*  373 */     assertEquals(10, localLpSolve.getBbDepthlimit());
/*  374 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetSetSimplextype() throws Exception {
/*  378 */     LpSolve localLpSolve = LpSolve.makeLp(0, 0);
/*  379 */     assertEquals(6, localLpSolve.getSimplextype());
/*  380 */     assertEquals(6, localLpSolve.getSimplextype());
/*  381 */     localLpSolve.setSimplextype(9);
/*  382 */     assertEquals(9, localLpSolve.getSimplextype());
/*  383 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testSetPreferdual() throws Exception {
/*  387 */     LpSolve localLpSolve = LpSolve.makeLp(0, 0);
/*  388 */     localLpSolve.setPreferdual(true);
/*  389 */     assertEquals(10, localLpSolve.getSimplextype());
/*  390 */     localLpSolve.setPreferdual(false);
/*  391 */     assertEquals(5, localLpSolve.getSimplextype());
/*  392 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetSetScalelimit() throws Exception {
/*  396 */     LpSolve localLpSolve = LpSolve.makeLp(0, 0);
/*  397 */     assertEquals(5.0D, localLpSolve.getScalelimit(), 1.0E-8D);
/*  398 */     localLpSolve.setScalelimit(10.0D);
/*  399 */     assertEquals(10.0D, localLpSolve.getScalelimit(), 1.0E-8D);
/*  400 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetStatustext() throws Exception {
/*  404 */     LpSolve localLpSolve = LpSolve.makeLp(0, 0);
/*  405 */     localLpSolve.setVerbose(3);
/*  406 */     int i = localLpSolve.solve();
/*  407 */     String str = localLpSolve.getStatustext(i);
/*  408 */     assertNotNull(str);
/*  409 */     assertTrue(str.length() > 0);
/*  410 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testSetBFP() throws Exception {
/*  414 */     LpSolve localLpSolve = LpSolve.makeLp(0, 0);
/*  415 */     assertTrue(localLpSolve.hasBFP());
/*  416 */     assertTrue(localLpSolve.isNativeBFP());
/*      */     
/*  418 */     localLpSolve.setBFP("bfp_LUSOL");
/*  419 */     assertTrue(localLpSolve.hasBFP());
/*  420 */     assertFalse(localLpSolve.isNativeBFP());
/*  421 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testSetBFPException() throws Exception {
/*  425 */     LpSolve localLpSolve = LpSolve.makeLp(0, 0);
/*      */     try {
/*  427 */       localLpSolve.setBFP("nonexistent");
/*  428 */       fail("Setting a nonexistent BFP should throw an exception");
/*      */     }
/*      */     catch (LpSolveException localLpSolveException) {
/*  431 */       System.out.println("setBFP failed as expected");
/*      */     }
/*  433 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testSetUnbounded() throws Exception {
/*  437 */     LpSolve localLpSolve = LpSolve.makeLp(0, 2);
/*  438 */     assertFalse(localLpSolve.isUnbounded(1));
/*  439 */     localLpSolve.setUnbounded(1);
/*  440 */     assertTrue(localLpSolve.isUnbounded(1));
/*  441 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testIsNegative() throws Exception {
/*  445 */     LpSolve localLpSolve = LpSolve.makeLp(0, 2);
/*  446 */     assertFalse(localLpSolve.isNegative(1));
/*  447 */     localLpSolve.setLowbo(1, -10.0D);
/*  448 */     localLpSolve.setUpbo(1, -1.0D);
/*  449 */     assertTrue(localLpSolve.isNegative(1));
/*  450 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetSetUpbo() throws Exception {
/*  454 */     LpSolve localLpSolve = LpSolve.makeLp(0, 2);
/*  455 */     assertEquals(1.0E30D, localLpSolve.getUpbo(1), 1.0E-8D);
/*  456 */     localLpSolve.setUpbo(1, 1234.0D);
/*  457 */     assertEquals(1234.0D, localLpSolve.getUpbo(1), 1.0E-8D);
/*  458 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetSetLowbo() throws Exception {
/*  462 */     LpSolve localLpSolve = LpSolve.makeLp(0, 2);
/*  463 */     assertEquals(0.0D, localLpSolve.getLowbo(1), 1.0E-8D);
/*  464 */     localLpSolve.setLowbo(1, -1234.0D);
/*  465 */     assertEquals(-1234.0D, localLpSolve.getLowbo(1), 1.0E-8D);
/*  466 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testSetBounds() throws Exception {
/*  470 */     LpSolve localLpSolve = LpSolve.makeLp(0, 2);
/*  471 */     localLpSolve.setBounds(1, -1234.0D, 5555.0D);
/*  472 */     assertEquals(-1234.0D, localLpSolve.getLowbo(1), 1.0E-8D);
/*  473 */     assertEquals(5555.0D, localLpSolve.getUpbo(1), 1.0E-8D);
/*  474 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testWriteDebugdump() throws Exception {
/*  478 */     String str = "debugdump.txt";
/*  479 */     File localFile = new File(str);
/*  480 */     if (localFile.exists()) localFile.delete();
/*  481 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/*  482 */     localLpSolve.strAddConstraint("3 2 2 1", 1, 4.0D);
/*  483 */     localLpSolve.strAddConstraint("0 4 3 1", 2, 3.0D);
/*  484 */     localLpSolve.strSetObjFn("2 3 -2 3");
/*  485 */     localLpSolve.setVerbose(3);
/*  486 */     localLpSolve.solve();
/*  487 */     localLpSolve.printDebugdump(localFile.getAbsolutePath());
/*  488 */     localLpSolve.deleteLp();
/*  489 */     assertTrue(localFile.exists());
/*  490 */     if (localFile.exists()) localFile.delete();
/*      */   }
/*      */   
/*      */   public void testSetOutputfile() throws Exception {
/*  494 */     String str = "print_file.txt";
/*  495 */     File localFile = new File(str);
/*  496 */     if (localFile.exists()) localFile.delete();
/*  497 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/*  498 */     localLpSolve.setOutputfile(localFile.getAbsolutePath());
/*  499 */     localLpSolve.setOutputfile(null);
/*  500 */     localLpSolve.deleteLp();
/*  501 */     assertTrue(localFile.exists());
/*  502 */     if (localFile.exists()) localFile.delete();
/*      */   }
/*      */   
/*      */   public void testPrintSolution() throws Exception {
/*  506 */     String str = "print_solution.txt";
/*  507 */     File localFile = new File(str);
/*  508 */     if (localFile.exists()) localFile.delete();
/*  509 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/*  510 */     localLpSolve.setOutputfile(localFile.getAbsolutePath());
/*  511 */     localLpSolve.strAddConstraint("3 2 2 1", 1, 4.0D);
/*  512 */     localLpSolve.strAddConstraint("0 4 3 1", 2, 3.0D);
/*  513 */     localLpSolve.strSetObjFn("2 3 -2 3");
/*  514 */     localLpSolve.printLp();
/*  515 */     localLpSolve.setVerbose(3);
/*  516 */     localLpSolve.solve();
/*  517 */     localLpSolve.printSolution(1);
/*  518 */     localLpSolve.printConstraints(1);
/*  519 */     localLpSolve.printObjective();
/*  520 */     localLpSolve.printDuals();
/*  521 */     localLpSolve.printScales();
/*  522 */     localLpSolve.setOutputfile(null);
/*  523 */     localLpSolve.deleteLp();
/*  524 */     assertTrue(localFile.exists());
/*  525 */     if (localFile.exists()) localFile.delete();
/*      */   }
/*      */   
/*      */   public void testPrintTableau() throws Exception {
/*  529 */     String str = "print_tableau.txt";
/*  530 */     File localFile = new File(str);
/*  531 */     if (localFile.exists()) localFile.delete();
/*  532 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/*  533 */     localLpSolve.setOutputfile(localFile.getAbsolutePath());
/*  534 */     localLpSolve.strAddConstraint("3 2 2 1", 1, 4.0D);
/*  535 */     localLpSolve.strAddConstraint("0 4 3 1", 2, 3.0D);
/*  536 */     localLpSolve.strSetObjFn("2 3 -2 3");
/*  537 */     localLpSolve.setVerbose(3);
/*  538 */     localLpSolve.solve();
/*  539 */     localLpSolve.printTableau();
/*  540 */     localLpSolve.setOutputfile(null);
/*  541 */     localLpSolve.deleteLp();
/*  542 */     assertTrue(localFile.exists());
/*  543 */     assertTrue(localFile.canRead());
/*  544 */     if (localFile.exists()) localFile.delete();
/*      */   }
/*      */   
/*      */   public void testPrintStr() throws Exception {
/*  548 */     String str = "print_str.txt";
/*  549 */     File localFile = new File(str);
/*  550 */     if (localFile.exists()) localFile.delete();
/*  551 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/*  552 */     localLpSolve.setOutputfile(localFile.getAbsolutePath());
/*  553 */     localLpSolve.printStr("Testüöäß");
/*  554 */     localLpSolve.printStr(null);
/*  555 */     localLpSolve.setOutputfile(null);
/*  556 */     localLpSolve.deleteLp();
/*  557 */     assertTrue(localFile.exists());
/*  558 */     assertTrue(localFile.length() >= 8L);
/*  559 */     if (localFile.exists()) localFile.delete();
/*      */   }
/*      */   
/*      */   public void testSetObj() throws Exception {
/*  563 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/*  564 */     localLpSolve.strAddConstraint("3 2 2 1", 1, 4.0D);
/*  565 */     localLpSolve.strAddConstraint("0 4 3 1", 2, 3.0D);
/*  566 */     localLpSolve.strSetObjFn("2 3 -2 3");
/*  567 */     double[] arrayOfDouble = localLpSolve.getPtrRow(0);
/*  568 */     assertEquals(5, arrayOfDouble.length);
/*  569 */     assertEquals(2.0D, arrayOfDouble[1], 1.0E-8D);
/*  570 */     localLpSolve.setObj(1, 6.6D);
/*  571 */     arrayOfDouble = localLpSolve.getPtrRow(0);
/*  572 */     assertEquals(5, arrayOfDouble.length);
/*  573 */     assertEquals(6.6D, arrayOfDouble[1], 1.0E-8D);
/*  574 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetRow() throws Exception {
/*  578 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/*  579 */     localLpSolve.strAddConstraint("3 2 2 1", 1, 4.0D);
/*  580 */     int i = 1 + localLpSolve.getNcolumns();
/*  581 */     double[] arrayOfDouble1 = new double[i];
/*  582 */     localLpSolve.getRow(1, arrayOfDouble1);
/*  583 */     double[] arrayOfDouble2 = localLpSolve.getPtrRow(1);
/*  584 */     assertEquals(i, arrayOfDouble2.length);
/*  585 */     assertEquals(3.0D, arrayOfDouble1[1], 1.0E-8D);
/*  586 */     for (int j = 0; j < i; j++) {
/*  587 */       assertEquals(arrayOfDouble1[j], arrayOfDouble2[j], 1.0E-8D);
/*      */     }
/*  589 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetRowex() throws Exception {
/*  593 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/*  594 */     localLpSolve.strSetObjFn("2 3 -2 3");
/*  595 */     localLpSolve.strAddConstraint("3 2 2 1", 1, 4.0D);
/*  596 */     localLpSolve.strAddConstraint("0 4 0 1", 2, 3.0D);
/*  597 */     int i = localLpSolve.getNcolumns();
/*  598 */     double[] arrayOfDouble = new double[i];
/*  599 */     int[] arrayOfInt = new int[i];
/*      */     
/*  601 */     int j = localLpSolve.getRowex(1, arrayOfDouble, arrayOfInt);
/*  602 */     assertEquals(4, j);
/*  603 */     assertEquals(3.0D, arrayOfDouble[0], 1.0E-8D);
/*  604 */     assertEquals(2.0D, arrayOfDouble[1], 1.0E-8D);
/*  605 */     assertEquals(2.0D, arrayOfDouble[2], 1.0E-8D);
/*  606 */     assertEquals(1.0D, arrayOfDouble[3], 1.0E-8D);
/*  607 */     assertEquals(1, arrayOfInt[0]);
/*  608 */     assertEquals(2, arrayOfInt[1]);
/*  609 */     assertEquals(3, arrayOfInt[2]);
/*  610 */     assertEquals(4, arrayOfInt[3]);
/*      */     
/*  612 */     j = localLpSolve.getRowex(2, arrayOfDouble, arrayOfInt);
/*  613 */     assertEquals(2, j);
/*  614 */     assertEquals(4.0D, arrayOfDouble[0], 1.0E-8D);
/*  615 */     assertEquals(1.0D, arrayOfDouble[1], 1.0E-8D);
/*  616 */     assertEquals(2, arrayOfInt[0]);
/*  617 */     assertEquals(4, arrayOfInt[1]);
/*      */     
/*  619 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetColumn() throws Exception {
/*  623 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/*  624 */     localLpSolve.strSetObjFn("2 3 -2 3");
/*  625 */     localLpSolve.strAddConstraint("3 2 2 1", 1, 4.0D);
/*  626 */     localLpSolve.strAddConstraint("0 4 3 1", 2, 3.0D);
/*  627 */     int i = 1 + localLpSolve.getNrows();
/*  628 */     double[] arrayOfDouble1 = new double[i];
/*  629 */     localLpSolve.getColumn(1, arrayOfDouble1);
/*  630 */     double[] arrayOfDouble2 = localLpSolve.getPtrColumn(1);
/*  631 */     assertEquals(i, arrayOfDouble2.length);
/*  632 */     assertEquals(2.0D, arrayOfDouble1[0], 1.0E-8D);
/*  633 */     for (int j = 0; j < i; j++) {
/*  634 */       assertEquals(arrayOfDouble1[j], arrayOfDouble2[j], 1.0E-8D);
/*      */     }
/*  636 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetColumnex() throws Exception {
/*  640 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/*  641 */     localLpSolve.strSetObjFn("2 3 -2 3");
/*  642 */     localLpSolve.strAddConstraint("0 2 2 1", 1, 4.0D);
/*  643 */     localLpSolve.strAddConstraint("3 4 0 1", 2, 3.0D);
/*  644 */     int i = 1 + localLpSolve.getNrows();
/*  645 */     double[] arrayOfDouble = new double[i];
/*  646 */     int[] arrayOfInt = new int[i];
/*      */     
/*  648 */     int j = localLpSolve.getColumnex(1, arrayOfDouble, arrayOfInt);
/*  649 */     assertEquals(2, j);
/*  650 */     assertEquals(2.0D, arrayOfDouble[0], 1.0E-8D);
/*  651 */     assertEquals(3.0D, arrayOfDouble[1], 1.0E-8D);
/*  652 */     assertEquals(0, arrayOfInt[0]);
/*  653 */     assertEquals(2, arrayOfInt[1]);
/*      */     
/*  655 */     j = localLpSolve.getColumnex(2, arrayOfDouble, arrayOfInt);
/*  656 */     assertEquals(3, j);
/*  657 */     assertEquals(3.0D, arrayOfDouble[0], 1.0E-8D);
/*  658 */     assertEquals(2.0D, arrayOfDouble[1], 1.0E-8D);
/*  659 */     assertEquals(4.0D, arrayOfDouble[2], 1.0E-8D);
/*  660 */     assertEquals(0, arrayOfInt[0]);
/*  661 */     assertEquals(1, arrayOfInt[1]);
/*  662 */     assertEquals(2, arrayOfInt[2]);
/*      */     
/*  664 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testMinimMaxim() throws Exception {
/*  668 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/*  669 */     assertFalse(localLpSolve.isMaxim());
/*  670 */     localLpSolve.setMaxim();
/*  671 */     assertTrue(localLpSolve.isMaxim());
/*  672 */     localLpSolve.setMinim();
/*  673 */     assertFalse(localLpSolve.isMaxim());
/*  674 */     localLpSolve.setMaxim();
/*  675 */     assertTrue(localLpSolve.isMaxim());
/*  676 */     localLpSolve.setSense(false);
/*  677 */     assertFalse(localLpSolve.isMaxim());
/*  678 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testAddDelConstraint() throws Exception {
/*  682 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/*  683 */     assertEquals(0, localLpSolve.getNrows());
/*  684 */     localLpSolve.strAddConstraint("3 2 2 1", 1, 4.0D);
/*  685 */     assertEquals(1, localLpSolve.getNrows());
/*  686 */     double[] arrayOfDouble = { 0.0D, 1.0D, 2.0D, 3.0D, 4.0D };
/*  687 */     localLpSolve.addConstraint(arrayOfDouble, 2, 5.0D);
/*  688 */     assertEquals(2, localLpSolve.getNrows());
/*  689 */     localLpSolve.delConstraint(1);
/*  690 */     assertEquals(1, localLpSolve.getNrows());
/*  691 */     localLpSolve.delConstraint(1);
/*  692 */     assertEquals(0, localLpSolve.getNrows());
/*  693 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testAddConstraintex() throws Exception {
/*  697 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/*  698 */     assertEquals(0, localLpSolve.getNrows());
/*  699 */     double[] arrayOfDouble1 = { 1.5D, 3.5D };
/*  700 */     int[] arrayOfInt = { 1, 3 };
/*  701 */     localLpSolve.addConstraintex(2, arrayOfDouble1, arrayOfInt, 2, 10.0D);
/*  702 */     assertEquals(1, localLpSolve.getNrows());
/*  703 */     double[] arrayOfDouble2 = localLpSolve.getPtrRow(1);
/*  704 */     assertEquals(5, arrayOfDouble2.length);
/*  705 */     assertEquals(1.5D, arrayOfDouble2[1], 1.0E-8D);
/*  706 */     assertEquals(0.0D, arrayOfDouble2[2], 1.0E-8D);
/*  707 */     assertEquals(3.5D, arrayOfDouble2[3], 1.0E-8D);
/*  708 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetNonzeros() throws Exception {
/*  712 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/*  713 */     assertEquals(0, localLpSolve.getNonzeros());
/*  714 */     double[] arrayOfDouble = { 1.5D, 3.5D };
/*  715 */     int[] arrayOfInt = { 1, 3 };
/*  716 */     localLpSolve.addConstraintex(2, arrayOfDouble, arrayOfInt, 2, 10.0D);
/*  717 */     assertEquals(2, localLpSolve.getNonzeros());
/*  718 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testSetObjFn() throws Exception {
/*  722 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/*  723 */     localLpSolve.strSetObjFn("1 -2 3 -4");
/*  724 */     double[] arrayOfDouble = localLpSolve.getPtrRow(0);
/*  725 */     assertEquals(5, arrayOfDouble.length);
/*  726 */     assertEquals(1.0D, arrayOfDouble[1], 1.0E-8D);
/*  727 */     assertEquals(-2.0D, arrayOfDouble[2], 1.0E-8D);
/*  728 */     assertEquals(3.0D, arrayOfDouble[3], 1.0E-8D);
/*  729 */     assertEquals(-4.0D, arrayOfDouble[4], 1.0E-8D);
/*      */     
/*  731 */     localLpSolve.setObjFn(new double[] { 0.0D, 9.0D, -8.0D, 7.0D, -6.0D });
/*  732 */     arrayOfDouble = localLpSolve.getPtrRow(0);
/*  733 */     assertEquals(9.0D, arrayOfDouble[1], 1.0E-8D);
/*  734 */     assertEquals(-8.0D, arrayOfDouble[2], 1.0E-8D);
/*  735 */     assertEquals(7.0D, arrayOfDouble[3], 1.0E-8D);
/*  736 */     assertEquals(-6.0D, arrayOfDouble[4], 1.0E-8D);
/*  737 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testSetObjFnex() throws Exception {
/*  741 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/*  742 */     double[] arrayOfDouble1 = { 1.5D, 3.5D };
/*  743 */     int[] arrayOfInt = { 2, 4 };
/*  744 */     localLpSolve.setObjFnex(2, arrayOfDouble1, arrayOfInt);
/*  745 */     double[] arrayOfDouble2 = localLpSolve.getPtrRow(0);
/*  746 */     assertEquals(5, arrayOfDouble2.length);
/*  747 */     assertEquals(1.5D, arrayOfDouble2[2], 1.0E-8D);
/*  748 */     assertEquals(0.0D, arrayOfDouble2[3], 1.0E-8D);
/*  749 */     assertEquals(3.5D, arrayOfDouble2[4], 1.0E-8D);
/*  750 */     localLpSolve.deleteLp();
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
/*      */   public void testAddDelColumn()
/*      */     throws Exception
/*      */   {
/*  767 */     LpSolve localLpSolve = LpSolve.makeLp(3, 0);
/*  768 */     assertEquals(0, localLpSolve.getNcolumns());
/*  769 */     localLpSolve.strAddColumn("3 2 2 1");
/*  770 */     assertEquals(1, localLpSolve.getNcolumns());
/*  771 */     double[] arrayOfDouble = { 0.0D, 1.0D, 2.0D, 3.0D, 4.0D };
/*  772 */     localLpSolve.addColumn(arrayOfDouble);
/*  773 */     assertEquals(2, localLpSolve.getNcolumns());
/*  774 */     localLpSolve.delColumn(1);
/*  775 */     assertEquals(1, localLpSolve.getNcolumns());
/*  776 */     localLpSolve.delColumn(1);
/*  777 */     assertEquals(0, localLpSolve.getNcolumns());
/*  778 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testDelColumnWithException() throws Exception {
/*  782 */     LpSolve localLpSolve = LpSolve.makeLp(0, 0);
/*      */     try {
/*  784 */       localLpSolve.delColumn(1);
/*  785 */       fail("delColumn with nonexistent col shouold throw an Exception");
/*      */     }
/*      */     catch (LpSolveException localLpSolveException)
/*      */     {
/*  789 */       System.out.println("delColumn failed as expected");
/*      */     }
/*  791 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testColumnInLp() throws Exception {
/*  795 */     LpSolve localLpSolve = LpSolve.makeLp(3, 0);
/*  796 */     double[] arrayOfDouble1 = { 0.0D, 1.0D, 2.0D, 3.0D, 4.0D };
/*  797 */     double[] arrayOfDouble2 = { 0.0D, 5.0D, 6.0D, 7.0D, 8.0D };
/*  798 */     localLpSolve.addColumn(arrayOfDouble1);
/*  799 */     localLpSolve.addColumn(arrayOfDouble2);
/*  800 */     assertEquals(2, localLpSolve.getNcolumns());
/*  801 */     assertEquals(1, localLpSolve.columnInLp(arrayOfDouble1));
/*  802 */     assertEquals(2, localLpSolve.columnInLp(arrayOfDouble2));
/*  803 */     arrayOfDouble1[1] = 100.6D;
/*  804 */     assertEquals(0, localLpSolve.columnInLp(arrayOfDouble1));
/*  805 */     assertEquals(2, localLpSolve.columnInLp(arrayOfDouble2));
/*  806 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetSetLpName() throws Exception {
/*  810 */     LpSolve localLpSolve = LpSolve.makeLp(3, 4);
/*  811 */     assertEquals("", localLpSolve.getLpName());
/*  812 */     localLpSolve.setLpName("test");
/*  813 */     assertEquals("test", localLpSolve.getLpName());
/*  814 */     localLpSolve.setLpName(null);
/*  815 */     assertEquals("", localLpSolve.getLpName());
/*  816 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetSetRowName() throws Exception {
/*  820 */     LpSolve localLpSolve = LpSolve.makeLp(1, 0);
/*  821 */     assertEquals("R1", localLpSolve.getRowName(1));
/*  822 */     localLpSolve.setRowName(1, "test");
/*  823 */     assertEquals("test", localLpSolve.getRowName(1));
/*  824 */     assertEquals("test", localLpSolve.getOrigrowName(1));
/*  825 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetSetColName() throws Exception {
/*  829 */     LpSolve localLpSolve = LpSolve.makeLp(0, 1);
/*  830 */     assertEquals("C1", localLpSolve.getColName(1));
/*  831 */     localLpSolve.setColName(1, "test");
/*  832 */     assertEquals("test", localLpSolve.getColName(1));
/*  833 */     assertEquals("test", localLpSolve.getOrigcolName(1));
/*  834 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetSetMat() throws Exception {
/*  838 */     LpSolve localLpSolve = LpSolve.makeLp(3, 4);
/*  839 */     assertEquals(0.0D, localLpSolve.getMat(1, 1), 1.0E-8D);
/*  840 */     localLpSolve.setMat(1, 1, 0.5D);
/*  841 */     assertEquals(0.5D, localLpSolve.getMat(1, 1), 1.0E-8D);
/*  842 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetSetVerbose() throws Exception {
/*  846 */     LpSolve localLpSolve = LpSolve.makeLp(3, 4);
/*  847 */     assertEquals(4, localLpSolve.getVerbose());
/*  848 */     localLpSolve.setVerbose(5);
/*  849 */     assertEquals(5, localLpSolve.getVerbose());
/*  850 */     localLpSolve.setVerbose(2);
/*  851 */     assertEquals(2, localLpSolve.getVerbose());
/*  852 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetSetTimeout() throws Exception {
/*  856 */     LpSolve localLpSolve = LpSolve.makeLp(3, 4);
/*  857 */     assertEquals(0L, localLpSolve.getTimeout());
/*  858 */     localLpSolve.setTimeout(1234L);
/*  859 */     assertEquals(1234L, localLpSolve.getTimeout());
/*  860 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetSetPrintSol() throws Exception {
/*  864 */     LpSolve localLpSolve = LpSolve.makeLp(3, 4);
/*  865 */     assertEquals(0, localLpSolve.getPrintSol());
/*  866 */     localLpSolve.setPrintSol(1);
/*  867 */     assertEquals(1, localLpSolve.getPrintSol());
/*  868 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetSetDebug() throws Exception {
/*  872 */     LpSolve localLpSolve = LpSolve.makeLp(3, 4);
/*  873 */     assertEquals(false, localLpSolve.isDebug());
/*  874 */     localLpSolve.setDebug(true);
/*  875 */     assertEquals(true, localLpSolve.isDebug());
/*  876 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetSetTrace() throws Exception {
/*  880 */     LpSolve localLpSolve = LpSolve.makeLp(3, 4);
/*  881 */     assertEquals(false, localLpSolve.isTrace());
/*  882 */     localLpSolve.setTrace(true);
/*  883 */     assertEquals(true, localLpSolve.isTrace());
/*  884 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetSetLagTrace() throws Exception {
/*  888 */     LpSolve localLpSolve = LpSolve.makeLp(3, 4);
/*  889 */     assertEquals(false, localLpSolve.isLagTrace());
/*  890 */     localLpSolve.setLagTrace(true);
/*  891 */     assertEquals(true, localLpSolve.isLagTrace());
/*  892 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testSetAddRowmode() throws Exception {
/*  896 */     LpSolve localLpSolve = LpSolve.makeLp(3, 4);
/*  897 */     assertEquals(false, localLpSolve.isAddRowmode());
/*  898 */     assertEquals(false, localLpSolve.setAddRowmode(false));
/*  899 */     assertEquals(true, localLpSolve.setAddRowmode(true));
/*  900 */     assertEquals(true, localLpSolve.isAddRowmode());
/*  901 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testSetAddRowmode2()
/*      */     throws Exception
/*      */   {
/*  907 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/*  908 */     localLpSolve.setAddRowmode(true);
/*  909 */     assertEquals(true, localLpSolve.isAddRowmode());
/*  910 */     double[] arrayOfDouble1 = { 0.0D, 2.0D, 3.0D, -2.0D, 3.0D };
/*  911 */     localLpSolve.setObjFn(arrayOfDouble1);
/*  912 */     double[] arrayOfDouble2 = { 0.0D, 1.0D, 2.0D, 3.0D, 4.0D };
/*  913 */     localLpSolve.addConstraint(arrayOfDouble2, 2, 5.0D);
/*  914 */     localLpSolve.setAddRowmode(false);
/*      */     
/*  916 */     arrayOfDouble1 = localLpSolve.getPtrRow(0);
/*  917 */     assertEquals(2.0D, arrayOfDouble1[1], 1.0E-8D);
/*  918 */     assertEquals(3.0D, arrayOfDouble1[2], 1.0E-8D);
/*  919 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetSetAntiDegen() throws Exception {
/*  923 */     LpSolve localLpSolve = LpSolve.makeLp(3, 4);
/*  924 */     localLpSolve.setAntiDegen(64);
/*  925 */     assertEquals(true, localLpSolve.isAntiDegen(64));
/*  926 */     assertEquals(64, localLpSolve.getAntiDegen());
/*  927 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetSetPresolve() throws Exception {
/*  931 */     LpSolve localLpSolve = LpSolve.makeLp(3, 4);
/*  932 */     assertEquals(true, localLpSolve.isPresolve(0));
/*  933 */     localLpSolve.setPresolve(3, 2);
/*  934 */     assertTrue(localLpSolve.isPresolve(3));
/*  935 */     assertTrue(localLpSolve.isPresolve(1));
/*  936 */     assertEquals(3, localLpSolve.getPresolve());
/*  937 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetPresolveloops() throws Exception {
/*  941 */     LpSolve localLpSolve = setupProblem();
/*  942 */     localLpSolve.setPresolve(3, 1);
/*  943 */     localLpSolve.setVerbose(3);
/*  944 */     localLpSolve.solve();
/*  945 */     assertEquals(1, localLpSolve.getPresolveloops());
/*  946 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetSetMaxpivot() throws Exception {
/*  950 */     LpSolve localLpSolve = LpSolve.makeLp(3, 4);
/*  951 */     assertEquals(250, localLpSolve.getMaxpivot());
/*  952 */     localLpSolve.setMaxpivot(44);
/*  953 */     assertEquals(44, localLpSolve.getMaxpivot());
/*  954 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetSetBbRule() throws Exception {
/*  958 */     LpSolve localLpSolve = LpSolve.makeLp(3, 4);
/*  959 */     localLpSolve.setBbRule(2);
/*  960 */     assertEquals(2, localLpSolve.getBbRule());
/*  961 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetSetObjBound() throws Exception {
/*  965 */     LpSolve localLpSolve = LpSolve.makeLp(3, 4);
/*  966 */     assertEquals(localLpSolve.getInfinite(), localLpSolve.getObjBound(), 1.0E-8D);
/*  967 */     localLpSolve.setObjBound(1234.56D);
/*  968 */     assertEquals(1234.56D, localLpSolve.getObjBound(), 1.0E-8D);
/*  969 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetSetFloorFirst() throws Exception {
/*  973 */     LpSolve localLpSolve = LpSolve.makeLp(3, 4);
/*  974 */     assertEquals(2, localLpSolve.getBbFloorfirst());
/*  975 */     localLpSolve.setBbFloorfirst(0);
/*  976 */     assertEquals(0, localLpSolve.getBbFloorfirst());
/*  977 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetSetUseNames() throws Exception {
/*  981 */     LpSolve localLpSolve = LpSolve.makeLp(3, 4);
/*  982 */     assertEquals(true, localLpSolve.isUseNames(true));
/*  983 */     assertEquals(true, localLpSolve.isUseNames(false));
/*  984 */     localLpSolve.setUseNames(true, false);
/*  985 */     localLpSolve.setUseNames(false, false);
/*  986 */     assertEquals(false, localLpSolve.isUseNames(true));
/*  987 */     assertEquals(false, localLpSolve.isUseNames(false));
/*  988 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetObjectiveGetSolution() throws Exception {
/*  992 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/*  993 */     localLpSolve.strAddConstraint("3 2 2 1", 1, 4.0D);
/*  994 */     localLpSolve.strAddConstraint("0 4 3 1", 2, 3.0D);
/*  995 */     localLpSolve.strSetObjFn("2 3 -2 3");
/*  996 */     localLpSolve.setVerbose(3);
/*  997 */     int i = localLpSolve.solve();
/*  998 */     assertEquals(0, i);
/*  999 */     assertEquals(-4.0D, localLpSolve.getObjective(), 1.0E-8D);
/* 1000 */     double[] arrayOfDouble = localLpSolve.getPtrPrimalSolution();
/* 1001 */     int j = 1 + localLpSolve.getNrows() + localLpSolve.getNcolumns();
/* 1002 */     assertEquals(j, arrayOfDouble.length);
/* 1003 */     int k = 1 + localLpSolve.getNrows();
/* 1004 */     assertEquals(0.0D, arrayOfDouble[(k + 0)], 1.0E-8D);
/* 1005 */     assertEquals(0.0D, arrayOfDouble[(k + 1)], 1.0E-8D);
/* 1006 */     assertEquals(2.0D, arrayOfDouble[(k + 2)], 1.0E-8D);
/* 1007 */     assertEquals(0.0D, arrayOfDouble[(k + 3)], 1.0E-8D);
/* 1008 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testPrimalSolution() throws Exception {
/* 1012 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/* 1013 */     localLpSolve.strAddConstraint("3 2 2 1", 1, 4.0D);
/* 1014 */     localLpSolve.strAddConstraint("0 4 3 1", 2, 3.0D);
/* 1015 */     double[] arrayOfDouble1 = { 0.0D, 2.0D, 3.0D, -2.0D, 3.0D };
/* 1016 */     localLpSolve.setObjFn(arrayOfDouble1);
/* 1017 */     localLpSolve.setVerbose(3);
/* 1018 */     int i = localLpSolve.solve();
/* 1019 */     double[] arrayOfDouble2 = localLpSolve.getPtrPrimalSolution();
/* 1020 */     double[] arrayOfDouble3 = new double[7];
/* 1021 */     localLpSolve.getPrimalSolution(arrayOfDouble3);
/* 1022 */     Arrays.equals(arrayOfDouble2, arrayOfDouble3);
/* 1023 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testVarDualresult() throws Exception {
/* 1027 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/* 1028 */     localLpSolve.strAddConstraint("3 2 2 1", 1, 4.0D);
/* 1029 */     localLpSolve.strAddConstraint("0 4 3 1", 2, 3.0D);
/* 1030 */     localLpSolve.setObjFn(new double[] { 0.0D, 2.0D, 3.0D, -2.0D, 3.0D });
/* 1031 */     localLpSolve.setVerbose(3);
/* 1032 */     localLpSolve.solve();
/* 1033 */     assertEquals(4.0D, localLpSolve.getVarPrimalresult(1), 1.0E-8D);
/*      */     
/* 1035 */     assertEquals(-1.0D, localLpSolve.getVarDualresult(1), 1.0E-8D);
/* 1036 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetPtrVariables() throws Exception {
/* 1040 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/* 1041 */     localLpSolve.strAddConstraint("3 2 2 1", 1, 4.0D);
/* 1042 */     localLpSolve.strAddConstraint("0 4 3 1", 2, 3.0D);
/* 1043 */     localLpSolve.strSetObjFn("2 3 -2 3");
/* 1044 */     localLpSolve.setVerbose(3);
/* 1045 */     localLpSolve.solve();
/* 1046 */     double[] arrayOfDouble = localLpSolve.getPtrVariables();
/* 1047 */     assertEquals(localLpSolve.getNcolumns(), arrayOfDouble.length);
/* 1048 */     assertEquals(0.0D, arrayOfDouble[0], 1.0E-8D);
/* 1049 */     assertEquals(0.0D, arrayOfDouble[1], 1.0E-8D);
/* 1050 */     assertEquals(2.0D, arrayOfDouble[2], 1.0E-8D);
/* 1051 */     assertEquals(0.0D, arrayOfDouble[3], 1.0E-8D);
/* 1052 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetVariables() throws Exception {
/* 1056 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/* 1057 */     localLpSolve.strAddConstraint("3 2 2 1", 1, 4.0D);
/* 1058 */     localLpSolve.strAddConstraint("0 4 3 1", 2, 3.0D);
/* 1059 */     localLpSolve.strSetObjFn("2 3 -2 3");
/* 1060 */     localLpSolve.setVerbose(3);
/* 1061 */     localLpSolve.solve();
/* 1062 */     double[] arrayOfDouble = new double[4];
/* 1063 */     localLpSolve.getVariables(arrayOfDouble);
/* 1064 */     assertEquals(localLpSolve.getNcolumns(), arrayOfDouble.length);
/* 1065 */     assertEquals(0.0D, arrayOfDouble[0], 1.0E-8D);
/* 1066 */     assertEquals(0.0D, arrayOfDouble[1], 1.0E-8D);
/* 1067 */     assertEquals(2.0D, arrayOfDouble[2], 1.0E-8D);
/* 1068 */     assertEquals(0.0D, arrayOfDouble[3], 1.0E-8D);
/* 1069 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetPtrConstraints() throws Exception {
/* 1073 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/* 1074 */     localLpSolve.strAddConstraint("3 2 2 1", 1, 4.0D);
/* 1075 */     localLpSolve.strAddConstraint("0 4 3 1", 2, 3.0D);
/* 1076 */     localLpSolve.strSetObjFn("2 3 -2 3");
/* 1077 */     localLpSolve.setVerbose(3);
/* 1078 */     localLpSolve.solve();
/* 1079 */     double[] arrayOfDouble = localLpSolve.getPtrConstraints();
/* 1080 */     assertEquals(localLpSolve.getNrows(), arrayOfDouble.length);
/* 1081 */     assertEquals(4.0D, arrayOfDouble[0], 1.0E-8D);
/* 1082 */     assertEquals(6.0D, arrayOfDouble[1], 1.0E-8D);
/* 1083 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetConstraints() throws Exception {
/* 1087 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/* 1088 */     localLpSolve.strAddConstraint("3 2 2 1", 1, 4.0D);
/* 1089 */     localLpSolve.strAddConstraint("0 4 3 1", 2, 3.0D);
/* 1090 */     localLpSolve.strSetObjFn("2 3 -2 3");
/* 1091 */     localLpSolve.setVerbose(3);
/* 1092 */     localLpSolve.solve();
/* 1093 */     double[] arrayOfDouble = new double[2];
/* 1094 */     localLpSolve.getConstraints(arrayOfDouble);
/* 1095 */     assertEquals(localLpSolve.getNrows(), arrayOfDouble.length);
/* 1096 */     assertEquals(4.0D, arrayOfDouble[0], 1.0E-8D);
/* 1097 */     assertEquals(6.0D, arrayOfDouble[1], 1.0E-8D);
/* 1098 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testRhVec() throws Exception {
/* 1102 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/* 1103 */     localLpSolve.strAddConstraint("3 2 2 1", 1, 4.0D);
/* 1104 */     localLpSolve.strAddConstraint("0 4 3 1", 2, 3.0D);
/* 1105 */     assertEquals(4.0D, localLpSolve.getRh(1), 1.0E-8D);
/* 1106 */     assertEquals(3.0D, localLpSolve.getRh(2), 1.0E-8D);
/* 1107 */     localLpSolve.strSetRhVec("1 2");
/* 1108 */     assertEquals(1.0D, localLpSolve.getRh(1), 1.0E-8D);
/* 1109 */     assertEquals(2.0D, localLpSolve.getRh(2), 1.0E-8D);
/* 1110 */     localLpSolve.setRhVec(new double[] { 0.0D, 6.0D, 7.5D });
/* 1111 */     assertEquals(6.0D, localLpSolve.getRh(1), 1.0E-8D);
/* 1112 */     assertEquals(7.5D, localLpSolve.getRh(2), 1.0E-8D);
/* 1113 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testRh() throws Exception {
/* 1117 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/* 1118 */     localLpSolve.strAddConstraint("3 2 2 1", 1, 4.0D);
/* 1119 */     localLpSolve.strAddConstraint("0 4 3 1", 2, 3.0D);
/* 1120 */     assertEquals(4.0D, localLpSolve.getRh(1), 1.0E-8D);
/* 1121 */     assertEquals(3.0D, localLpSolve.getRh(2), 1.0E-8D);
/* 1122 */     localLpSolve.setRh(1, 1.0D);
/* 1123 */     localLpSolve.setRh(2, 2.0D);
/* 1124 */     assertEquals(1.0D, localLpSolve.getRh(1), 1.0E-8D);
/* 1125 */     assertEquals(2.0D, localLpSolve.getRh(2), 1.0E-8D);
/* 1126 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testConstrType() throws Exception {
/* 1130 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/* 1131 */     localLpSolve.strAddConstraint("3 2 2 1", 1, 4.0D);
/* 1132 */     localLpSolve.strAddConstraint("0 4 3 1", 2, 3.0D);
/* 1133 */     assertEquals(1, localLpSolve.getConstrType(1));
/* 1134 */     assertEquals(2, localLpSolve.getConstrType(2));
/* 1135 */     localLpSolve.setConstrType(1, 3);
/* 1136 */     assertEquals(3, localLpSolve.getConstrType(1));
/* 1137 */     localLpSolve.deleteLp();
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
/*      */   public void testRhRange()
/*      */     throws Exception
/*      */   {
/* 1156 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/* 1157 */     localLpSolve.strAddConstraint("3 2 2 1", 1, 4.0D);
/* 1158 */     localLpSolve.strAddConstraint("0 4 3 1", 2, 3.0D);
/* 1159 */     localLpSolve.setRhRange(1, 20.5D);
/* 1160 */     assertEquals(20.5D, localLpSolve.getRhRange(1), 1.0E-8D);
/* 1161 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testInt() throws Exception {
/* 1165 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/* 1166 */     assertEquals(false, localLpSolve.isInt(1));
/* 1167 */     localLpSolve.setInt(1, true);
/* 1168 */     assertEquals(true, localLpSolve.isInt(1));
/* 1169 */     localLpSolve.setInt(1, false);
/* 1170 */     assertEquals(false, localLpSolve.isInt(1));
/* 1171 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testBinary() throws Exception {
/* 1175 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/* 1176 */     assertEquals(false, localLpSolve.isBinary(1));
/* 1177 */     localLpSolve.setBinary(1, true);
/* 1178 */     assertEquals(true, localLpSolve.isBinary(1));
/* 1179 */     localLpSolve.setBinary(1, false);
/* 1180 */     assertEquals(false, localLpSolve.isBinary(1));
/* 1181 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testSemicont() throws Exception {
/* 1185 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/* 1186 */     assertEquals(false, localLpSolve.isSemicont(1));
/* 1187 */     localLpSolve.setSemicont(1, true);
/* 1188 */     assertEquals(true, localLpSolve.isSemicont(1));
/* 1189 */     localLpSolve.setSemicont(1, false);
/* 1190 */     assertEquals(false, localLpSolve.isSemicont(1));
/* 1191 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testInfinite() throws Exception {
/* 1195 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/* 1196 */     assertEquals(1.0E30D, localLpSolve.getInfinite(), 1.0E-8D);
/* 1197 */     assertTrue(localLpSolve.isInfinite(localLpSolve.getInfinite()));
/* 1198 */     assertFalse(localLpSolve.isInfinite(3.0E22D));
/* 1199 */     localLpSolve.setInfinite(2.0999999999999998E22D);
/* 1200 */     assertEquals(2.0999999999999998E22D, localLpSolve.getInfinite(), 1.0E-8D);
/* 1201 */     assertTrue(localLpSolve.isInfinite(3.0E22D));
/* 1202 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testEpsint() throws Exception {
/* 1206 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/* 1207 */     assertEquals(1.0E-7D, localLpSolve.getEpsint(), 1.0E-8D);
/* 1208 */     localLpSolve.setEpsint(1.0E-4D);
/* 1209 */     assertEquals(1.0E-4D, localLpSolve.getEpsint(), 1.0E-8D);
/* 1210 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testEpsb() throws Exception {
/* 1214 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/* 1215 */     assertEquals(1.0E-10D, localLpSolve.getEpsb(), 1.0E-8D);
/* 1216 */     localLpSolve.setEpsb(1.0E-4D);
/* 1217 */     assertEquals(1.0E-4D, localLpSolve.getEpsb(), 1.0E-8D);
/* 1218 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testEpsd() throws Exception {
/* 1222 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/* 1223 */     assertEquals(1.0E-9D, localLpSolve.getEpsd(), 1.0E-8D);
/* 1224 */     localLpSolve.setEpsd(1.0E-4D);
/* 1225 */     assertEquals(1.0E-4D, localLpSolve.getEpsd(), 1.0E-8D);
/* 1226 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testEpsel() throws Exception {
/* 1230 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/* 1231 */     assertEquals(1.0E-12D, localLpSolve.getEpsel(), 1.0E-8D);
/* 1232 */     localLpSolve.setEpsel(1.0E-4D);
/* 1233 */     assertEquals(1.0E-4D, localLpSolve.getEpsel(), 1.0E-8D);
/* 1234 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testEpspivot() throws Exception {
/* 1238 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/* 1239 */     assertEquals(2.0E-7D, localLpSolve.getEpspivot(), 1.0E-8D);
/* 1240 */     localLpSolve.setEpspivot(1.0E-4D);
/* 1241 */     assertEquals(1.0E-4D, localLpSolve.getEpspivot(), 1.0E-8D);
/* 1242 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testEpsperturb() throws Exception {
/* 1246 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/* 1247 */     assertEquals(1.0E-5D, localLpSolve.getEpsperturb(), 1.0E-8D);
/* 1248 */     localLpSolve.setEpsperturb(1.0E-4D);
/* 1249 */     assertEquals(1.0E-4D, localLpSolve.getEpsperturb(), 1.0E-8D);
/* 1250 */     localLpSolve.deleteLp();
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
/*      */   public void testGetStatus()
/*      */     throws Exception
/*      */   {
/* 1267 */     LpSolve localLpSolve = LpSolve.makeLp(3, 0);
/*      */     
/* 1269 */     assertEquals(-1, localLpSolve.getStatus());
/*      */     try {
/* 1271 */       localLpSolve.strAddColumn("1 2 3");
/* 1272 */       fail("this should throw an exception");
/*      */     }
/*      */     catch (LpSolveException localLpSolveException) {
/* 1275 */       System.out.println("strAddColumn failed as expected");
/* 1276 */       assertEquals(-4, localLpSolve.getStatus());
/*      */     }
/* 1278 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testMipGap() throws Exception {
/* 1282 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/* 1283 */     localLpSolve.setMipGap(true, 1.0E-4D);
/* 1284 */     assertEquals(1.0E-4D, localLpSolve.getMipGap(true), 1.0E-8D);
/* 1285 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testVarBranch() throws Exception {
/* 1289 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/* 1290 */     localLpSolve.setVarBranch(1, 2);
/* 1291 */     assertEquals(2, localLpSolve.getVarBranch(1));
/* 1292 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testVarWeights() throws Exception {
/* 1296 */     LpSolve localLpSolve = LpSolve.makeLp(0, 2);
/* 1297 */     assertEquals(1, localLpSolve.getVarPriority(1));
/* 1298 */     assertEquals(2, localLpSolve.getVarPriority(2));
/* 1299 */     double[] arrayOfDouble = { 2.0D, 1.0D };
/* 1300 */     localLpSolve.setVarWeights(arrayOfDouble);
/* 1301 */     assertEquals(2, localLpSolve.getVarPriority(1));
/* 1302 */     assertEquals(1, localLpSolve.getVarPriority(2));
/* 1303 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testBreakAtFirst() throws Exception {
/* 1307 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/* 1308 */     assertFalse(localLpSolve.isBreakAtFirst());
/* 1309 */     localLpSolve.setBreakAtFirst(true);
/* 1310 */     assertTrue(localLpSolve.isBreakAtFirst());
/* 1311 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testBreakAtValue() throws Exception {
/* 1315 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/* 1316 */     assertEquals(-localLpSolve.getInfinite(), localLpSolve.getBreakAtValue(), 1.0E-8D);
/* 1317 */     localLpSolve.setBreakAtValue(-1.2E22D);
/* 1318 */     assertEquals(-1.2E22D, localLpSolve.getBreakAtValue(), 1.0E-8D);
/* 1319 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testScaling() throws Exception {
/* 1323 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/* 1324 */     int i = 33;
/* 1325 */     localLpSolve.setScaling(i);
/* 1326 */     assertEquals(i, localLpSolve.getScaling());
/* 1327 */     assertTrue(localLpSolve.isScalemode(i));
/* 1328 */     assertTrue(localLpSolve.isScaletype(1));
/* 1329 */     assertFalse(localLpSolve.isIntegerscaling());
/* 1330 */     localLpSolve.setScaling(i | 0x80);
/* 1331 */     assertTrue(localLpSolve.isIntegerscaling());
/* 1332 */     localLpSolve.unscale();
/* 1333 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testImprove() throws Exception {
/* 1337 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/* 1338 */     assertEquals(6, localLpSolve.getImprove());
/* 1339 */     localLpSolve.setImprove(8);
/* 1340 */     assertEquals(8, localLpSolve.getImprove());
/* 1341 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testPivoting() throws Exception {
/* 1345 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/* 1346 */     assertEquals(34, localLpSolve.getPivoting());
/* 1347 */     assertTrue(localLpSolve.isPivRule(2));
/* 1348 */     assertTrue(localLpSolve.isPivMode(32));
/* 1349 */     int i = 7;
/* 1350 */     localLpSolve.setPivoting(i);
/* 1351 */     assertEquals(i, localLpSolve.getPivoting());
/* 1352 */     assertTrue(localLpSolve.isPivRule(3));
/* 1353 */     assertFalse(localLpSolve.isPivRule(1));
/* 1354 */     assertTrue(localLpSolve.isPivMode(4));
/* 1355 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testNegrange() throws Exception {
/* 1359 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/* 1360 */     assertEquals(-1000000.0D, localLpSolve.getNegrange(), 1.0E-8D);
/* 1361 */     localLpSolve.setNegrange(-1.234D);
/* 1362 */     assertEquals(-1.234D, localLpSolve.getNegrange(), 1.0E-8D);
/* 1363 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testBasis() throws Exception {
/* 1367 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/* 1368 */     localLpSolve.strAddConstraint("3 2 2 1", 1, 4.0D);
/* 1369 */     localLpSolve.strAddConstraint("0 4 3 1", 2, 3.0D);
/* 1370 */     localLpSolve.strSetObjFn("2 3 -2 3");
/* 1371 */     localLpSolve.setVerbose(3);
/* 1372 */     localLpSolve.solve();
/* 1373 */     int[] arrayOfInt1 = new int[3];
/* 1374 */     localLpSolve.getBasis(arrayOfInt1, false);
/*      */     
/* 1376 */     int[] arrayOfInt2 = { 0, -2, -3 };
/* 1377 */     localLpSolve.setBasis(arrayOfInt2, false);
/* 1378 */     localLpSolve.getBasis(arrayOfInt1, false);
/* 1379 */     assertEquals(-2, arrayOfInt1[1]);
/* 1380 */     assertEquals(-3, arrayOfInt1[2]);
/*      */     
/* 1382 */     localLpSolve.defaultBasis();
/* 1383 */     localLpSolve.getBasis(arrayOfInt1, false);
/* 1384 */     assertEquals(-1, arrayOfInt1[1]);
/* 1385 */     assertEquals(-2, arrayOfInt1[2]);
/*      */     
/* 1387 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetSensitivityRhs() throws Exception {
/* 1391 */     LpSolve localLpSolve = LpSolve.makeLp(0, 2);
/* 1392 */     localLpSolve.strSetObjFn("1 1");
/* 1393 */     localLpSolve.strAddConstraint("2 4", 2, 10.0D);
/* 1394 */     localLpSolve.setLowbo(1, 1.0D);
/* 1395 */     localLpSolve.setVerbose(3);
/* 1396 */     localLpSolve.solve();
/* 1397 */     int i = localLpSolve.getNcolumns() + localLpSolve.getNrows();
/* 1398 */     double[] arrayOfDouble1 = new double[i];
/* 1399 */     double[] arrayOfDouble2 = new double[i];
/* 1400 */     double[] arrayOfDouble3 = new double[i];
/* 1401 */     localLpSolve.getSensitivityRhs(arrayOfDouble1, arrayOfDouble2, arrayOfDouble3);
/* 1402 */     double[][] arrayOfDouble = localLpSolve.getPtrSensitivityRhs();
/* 1403 */     assertEquals(3, arrayOfDouble.length);
/* 1404 */     double[] arrayOfDouble4 = arrayOfDouble[0];
/* 1405 */     double[] arrayOfDouble5 = arrayOfDouble[1];
/* 1406 */     double[] arrayOfDouble6 = arrayOfDouble[2];
/* 1407 */     assertEquals(i, arrayOfDouble4.length);
/* 1408 */     assertEquals(i, arrayOfDouble5.length);
/* 1409 */     assertEquals(i, arrayOfDouble6.length);
/* 1410 */     for (int j = 0; j < i; j++) {
/* 1411 */       assertEquals(arrayOfDouble1[j], arrayOfDouble4[j], 1.0E-8D);
/* 1412 */       assertEquals(arrayOfDouble2[j], arrayOfDouble5[j], 1.0E-8D);
/* 1413 */       assertEquals(arrayOfDouble3[j], arrayOfDouble6[j], 1.0E-8D);
/*      */     }
/* 1415 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetDualSolution() throws Exception {
/* 1419 */     LpSolve localLpSolve = LpSolve.makeLp(0, 2);
/* 1420 */     localLpSolve.strSetObjFn("1 1");
/* 1421 */     localLpSolve.strAddConstraint("2 4", 2, 10.0D);
/* 1422 */     localLpSolve.setLowbo(1, 1.0D);
/* 1423 */     localLpSolve.setVerbose(3);
/* 1424 */     localLpSolve.solve();
/* 1425 */     int i = 1 + localLpSolve.getNcolumns() + localLpSolve.getNrows();
/* 1426 */     double[] arrayOfDouble1 = new double[i];
/* 1427 */     localLpSolve.getDualSolution(arrayOfDouble1);
/* 1428 */     double[] arrayOfDouble2 = localLpSolve.getPtrDualSolution();
/* 1429 */     assertEquals(i, arrayOfDouble2.length);
/* 1430 */     for (int j = 0; j < i; j++) {
/* 1431 */       assertEquals(arrayOfDouble1[j], arrayOfDouble2[j], 1.0E-8D);
/*      */     }
/* 1433 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetSensitivityObj() throws Exception {
/* 1437 */     LpSolve localLpSolve = LpSolve.makeLp(0, 2);
/* 1438 */     localLpSolve.strSetObjFn("1 1");
/* 1439 */     localLpSolve.strAddConstraint("2 4", 2, 10.0D);
/* 1440 */     localLpSolve.setLowbo(1, 1.0D);
/* 1441 */     localLpSolve.setVerbose(3);
/* 1442 */     localLpSolve.solve();
/* 1443 */     int i = localLpSolve.getNcolumns();
/* 1444 */     double[] arrayOfDouble1 = new double[i];
/* 1445 */     double[] arrayOfDouble2 = new double[i];
/* 1446 */     localLpSolve.getSensitivityObj(arrayOfDouble1, arrayOfDouble2);
/* 1447 */     double[][] arrayOfDouble = localLpSolve.getPtrSensitivityObj();
/* 1448 */     assertEquals(2, arrayOfDouble.length);
/* 1449 */     double[] arrayOfDouble3 = arrayOfDouble[0];
/* 1450 */     double[] arrayOfDouble4 = arrayOfDouble[1];
/* 1451 */     assertEquals(i, arrayOfDouble3.length);
/* 1452 */     assertEquals(i, arrayOfDouble4.length);
/* 1453 */     assertEquals(0.5D, arrayOfDouble1[0], 1.0E-8D);
/* 1454 */     assertEquals(localLpSolve.getInfinite(), arrayOfDouble2[0], 1.0E-8D);
/* 1455 */     assertEquals(0.0D, arrayOfDouble1[1], 1.0E-8D);
/* 1456 */     assertEquals(2.0D, arrayOfDouble2[1], 1.0E-8D);
/* 1457 */     for (int j = 0; j < i; j++) {
/* 1458 */       assertEquals(arrayOfDouble1[j], arrayOfDouble3[j], 1.0E-8D);
/* 1459 */       assertEquals(arrayOfDouble2[j], arrayOfDouble4[j], 1.0E-8D);
/*      */     }
/* 1461 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetSensitivityObjex() throws Exception {
/* 1465 */     LpSolve localLpSolve = LpSolve.makeLp(0, 2);
/* 1466 */     localLpSolve.strSetObjFn("1 1");
/* 1467 */     localLpSolve.strAddConstraint("2 4", 2, 10.0D);
/* 1468 */     localLpSolve.setLowbo(1, 1.0D);
/* 1469 */     localLpSolve.setVerbose(3);
/* 1470 */     localLpSolve.solve();
/* 1471 */     int i = localLpSolve.getNcolumns();
/* 1472 */     double[] arrayOfDouble1 = new double[i];
/* 1473 */     double[] arrayOfDouble2 = new double[i];
/* 1474 */     double[] arrayOfDouble3 = new double[i];
/* 1475 */     double[] arrayOfDouble4 = new double[i];
/* 1476 */     localLpSolve.getSensitivityObjex(arrayOfDouble1, arrayOfDouble2, arrayOfDouble3, arrayOfDouble4);
/* 1477 */     double[][] arrayOfDouble = localLpSolve.getPtrSensitivityObjex();
/* 1478 */     assertEquals(4, arrayOfDouble.length);
/* 1479 */     double[] arrayOfDouble5 = arrayOfDouble[0];
/* 1480 */     double[] arrayOfDouble6 = arrayOfDouble[1];
/* 1481 */     double[] arrayOfDouble7 = arrayOfDouble[2];
/* 1482 */     double[] arrayOfDouble8 = arrayOfDouble[3];
/* 1483 */     assertEquals(i, arrayOfDouble5.length);
/* 1484 */     assertEquals(i, arrayOfDouble6.length);
/* 1485 */     assertEquals(i, arrayOfDouble7.length);
/* 1486 */     assertEquals(i, arrayOfDouble8.length);
/* 1487 */     assertEquals(0.5D, arrayOfDouble1[0], 1.0E-8D);
/* 1488 */     assertEquals(localLpSolve.getInfinite(), arrayOfDouble2[0], 1.0E-8D);
/* 1489 */     assertEquals(0.0D, arrayOfDouble1[1], 1.0E-8D);
/* 1490 */     assertEquals(2.0D, arrayOfDouble2[1], 1.0E-8D);
/* 1491 */     for (int j = 0; j < i; j++) {
/* 1492 */       assertEquals(arrayOfDouble1[j], arrayOfDouble5[j], 1.0E-8D);
/* 1493 */       assertEquals(arrayOfDouble2[j], arrayOfDouble6[j], 1.0E-8D);
/* 1494 */       assertEquals(arrayOfDouble3[j], arrayOfDouble7[j], 1.0E-8D);
/*      */     }
/*      */     
/* 1497 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testIsConstrType() throws Exception {
/* 1501 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/* 1502 */     localLpSolve.strAddConstraint("3 2 2 1", 1, 4.0D);
/* 1503 */     assertTrue(localLpSolve.isConstrType(1, 1));
/* 1504 */     assertFalse(localLpSolve.isConstrType(1, 3));
/* 1505 */     assertFalse(localLpSolve.isConstrType(1, 2));
/* 1506 */     localLpSolve.strAddConstraint("0 4 3 1", 2, 3.0D);
/* 1507 */     assertTrue(localLpSolve.isConstrType(2, 2));
/* 1508 */     assertFalse(localLpSolve.isConstrType(2, 1));
/* 1509 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetSolutioncount()
/*      */     throws Exception
/*      */   {
/* 1515 */     LpSolve localLpSolve = LpSolve.makeLp(0, 0);
/* 1516 */     localLpSolve.setVerbose(3);
/* 1517 */     localLpSolve.solve();
/* 1518 */     assertEquals(0, localLpSolve.getSolutioncount());
/* 1519 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetSolutionlimit() throws Exception {
/* 1523 */     LpSolve localLpSolve = LpSolve.makeLp(0, 0);
/* 1524 */     localLpSolve.setSolutionlimit(10);
/* 1525 */     assertEquals(10, localLpSolve.getSolutionlimit());
/* 1526 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetTotalIter()
/*      */     throws Exception
/*      */   {
/* 1532 */     LpSolve localLpSolve = LpSolve.makeLp(0, 0);
/* 1533 */     localLpSolve.setVerbose(3);
/* 1534 */     localLpSolve.solve();
/* 1535 */     assertEquals(0L, localLpSolve.getTotalIter());
/* 1536 */     assertEquals(0L, localLpSolve.getTotalNodes());
/* 1537 */     assertEquals(0, localLpSolve.getMaxLevel());
/* 1538 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetSetBoundsTighter() throws Exception {
/* 1542 */     LpSolve localLpSolve = LpSolve.makeLp(0, 0);
/* 1543 */     assertFalse(localLpSolve.getBoundsTighter());
/* 1544 */     localLpSolve.setBoundsTighter(true);
/* 1545 */     assertTrue(localLpSolve.getBoundsTighter());
/* 1546 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetLpIndex() throws Exception {
/* 1550 */     LpSolve localLpSolve = LpSolve.makeLp(0, 1);
/* 1551 */     assertEquals(1, localLpSolve.getLpIndex(1));
/* 1552 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testIsFeasible() throws Exception {
/* 1556 */     LpSolve localLpSolve = LpSolve.makeLp(1, 1);
/* 1557 */     localLpSolve.setVerbose(3);
/* 1558 */     localLpSolve.solve();
/* 1559 */     double[] arrayOfDouble = { 0.0D, 1.0D, 2.0D };
/* 1560 */     assertTrue(localLpSolve.isFeasible(arrayOfDouble, 0.0D));
/* 1561 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testTimeElapsed() throws Exception {
/* 1565 */     LpSolve localLpSolve = LpSolve.makeLp(0, 1);
/* 1566 */     localLpSolve.setVerbose(3);
/* 1567 */     localLpSolve.solve();
/* 1568 */     localLpSolve.timeElapsed();
/* 1569 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetNameindexWithRows() throws Exception {
/* 1573 */     LpSolve localLpSolve = LpSolve.makeLp(2, 4);
/* 1574 */     assertEquals(-1, localLpSolve.getNameindex("myrow1", true));
/* 1575 */     localLpSolve.setRowName(1, "myrow1");
/* 1576 */     assertEquals(1, localLpSolve.getNameindex("myrow1", true));
/* 1577 */     assertEquals(-1, localLpSolve.getNameindex("myrow2", true));
/* 1578 */     localLpSolve.setRowName(2, "myrow2");
/* 1579 */     assertEquals(2, localLpSolve.getNameindex("myrow2", true));
/* 1580 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testGetNameindexWithCols() throws Exception {
/* 1584 */     LpSolve localLpSolve = LpSolve.makeLp(2, 4);
/* 1585 */     assertEquals(-1, localLpSolve.getNameindex("mycol1", false));
/* 1586 */     localLpSolve.setColName(1, "mycol1");
/* 1587 */     assertEquals(1, localLpSolve.getNameindex("mycol1", false));
/* 1588 */     assertEquals(-1, localLpSolve.getNameindex("mycol2", false));
/* 1589 */     localLpSolve.setColName(3, "mycol2");
/* 1590 */     assertEquals(3, localLpSolve.getNameindex("mycol2", false));
/* 1591 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testReadNonexistentBasis() throws Exception {
/* 1595 */     String str = "model.bas";
/* 1596 */     File localFile = new File(str);
/* 1597 */     if (localFile.exists()) localFile.delete();
/* 1598 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/* 1599 */     localLpSolve.strAddConstraint("3 2 2 1", 1, 4.0D);
/* 1600 */     localLpSolve.strAddConstraint("0 4 3 1", 2, 3.0D);
/* 1601 */     localLpSolve.strSetObjFn("2 3 -2 3");
/*      */     try {
/* 1603 */       localLpSolve.readBasis(localFile.getAbsolutePath());
/* 1604 */       fail("readBasis should throw an Exception on nonexistent file");
/*      */     }
/*      */     catch (LpSolveException localLpSolveException)
/*      */     {
/* 1608 */       System.out.println("readBasis failed as expected");
/*      */     }
/* 1610 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */   public void testReadWriteBasis() throws Exception {
/* 1614 */     String str1 = "model.bas";
/* 1615 */     File localFile = new File(str1);
/* 1616 */     if (localFile.exists()) localFile.delete();
/* 1617 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/* 1618 */     localLpSolve.strAddConstraint("3 2 2 1", 1, 4.0D);
/* 1619 */     localLpSolve.strAddConstraint("0 4 3 1", 2, 3.0D);
/* 1620 */     localLpSolve.strSetObjFn("2 3 -2 3");
/* 1621 */     localLpSolve.setVerbose(3);
/* 1622 */     localLpSolve.solve();
/* 1623 */     localLpSolve.writeBasis(localFile.getAbsolutePath());
/* 1624 */     assertTrue(localFile.exists());
/*      */     
/* 1626 */     String str2 = localLpSolve.readBasis(localFile.getAbsolutePath());
/* 1627 */     assertNotNull(str2);
/* 1628 */     assertTrue(str2.length() > 0);
/* 1629 */     localLpSolve.deleteLp();
/* 1630 */     if (localFile.exists()) localFile.delete();
/*      */   }
/*      */   
/*      */   public void testReadWriteParams() throws Exception {
/* 1634 */     String str = "model.params";
/* 1635 */     File localFile = new File(str);
/* 1636 */     if (localFile.exists()) localFile.delete();
/* 1637 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/* 1638 */     assertEquals(250, localLpSolve.getMaxpivot());
/* 1639 */     localLpSolve.setMaxpivot(400);
/* 1640 */     localLpSolve.writeParams(localFile.getAbsolutePath(), "");
/* 1641 */     assertTrue(localFile.exists());
/* 1642 */     localLpSolve.deleteLp();
/*      */     
/* 1644 */     localLpSolve = LpSolve.makeLp(0, 4);
/* 1645 */     assertEquals(250, localLpSolve.getMaxpivot());
/* 1646 */     localLpSolve.readParams(localFile.getAbsolutePath(), "");
/* 1647 */     assertEquals(400, localLpSolve.getMaxpivot());
/* 1648 */     localLpSolve.deleteLp();
/* 1649 */     if (localFile.exists()) localFile.delete();
/*      */   }
/*      */   
/*      */   public void testResetParams() throws Exception {
/* 1653 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/* 1654 */     assertEquals(250, localLpSolve.getMaxpivot());
/* 1655 */     localLpSolve.setMaxpivot(400);
/* 1656 */     assertEquals(400, localLpSolve.getMaxpivot());
/* 1657 */     localLpSolve.resetParams();
/* 1658 */     assertEquals(250, localLpSolve.getMaxpivot());
/* 1659 */     localLpSolve.deleteLp();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void testAbortListener()
/*      */     throws Exception
/*      */   {
/* 1671 */     final LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1681 */     AbortListener local1MyListener = new AbortListener()
/*      */     {
/* 1673 */       public int numCalls = 0;
/*      */       
/* 1675 */       public boolean abortfunc(LpSolve paramAnonymousLpSolve, Object paramAnonymousObject) { this.numCalls += 1;
/* 1676 */         Assert.assertEquals(localLpSolve, paramAnonymousLpSolve);
/* 1677 */         Assert.assertEquals(new Integer(123), paramAnonymousObject);
/* 1678 */         return false;
/*      */       }
/*      */       
/* 1681 */     };
/* 1682 */     localLpSolve.putAbortfunc(local1MyListener, new Integer(123));
/* 1683 */     localLpSolve.strAddConstraint("3 2 2 1", 1, 4.0D);
/* 1684 */     localLpSolve.strAddConstraint("0 4 3 1", 2, 3.0D);
/* 1685 */     localLpSolve.strSetObjFn("2 3 -2 3");
/* 1686 */     localLpSolve.setVerbose(3);
/* 1687 */     localLpSolve.solve();
/* 1688 */     localLpSolve.deleteLp();
/* 1689 */     assertTrue(local1MyListener.numCalls > 0);
/*      */   }
/*      */   
/*      */   public void testMsgListener() throws Exception {
/* 1693 */     final LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1703 */     MsgListener local2MyListener = new MsgListener()
/*      */     {
/* 1695 */       public int numCalls = 0;
/*      */       
/* 1697 */       public void msgfunc(LpSolve paramAnonymousLpSolve, Object paramAnonymousObject, int paramAnonymousInt) throws LpSolveException { this.numCalls += 1;
/* 1698 */         Assert.assertEquals(localLpSolve, paramAnonymousLpSolve);
/* 1699 */         Assert.assertEquals(new Integer(123), paramAnonymousObject);
/* 1700 */         localLpSolve.getWorkingObjective();
/*      */       }
/*      */       
/* 1703 */     };
/* 1704 */     int i = 24;
/* 1705 */     localLpSolve.putMsgfunc(local2MyListener, new Integer(123), i);
/* 1706 */     localLpSolve.strAddConstraint("3 2 2 1", 1, 4.0D);
/* 1707 */     localLpSolve.strAddConstraint("0 4 3 1", 2, 3.0D);
/* 1708 */     localLpSolve.strSetObjFn("2 3 -2 3");
/* 1709 */     localLpSolve.setVerbose(3);
/* 1710 */     localLpSolve.solve();
/* 1711 */     localLpSolve.deleteLp();
/* 1712 */     assertTrue(local2MyListener.numCalls > 0);
/*      */   }
/*      */   
/*      */   public void testLogListener() throws Exception {
/* 1716 */     final LpSolve localLpSolve = LpSolve.makeLp(0, 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1726 */     LogListener local3MyListener = new LogListener()
/*      */     {
/* 1718 */       public int numCalls = 0;
/*      */       
/* 1720 */       public void logfunc(LpSolve paramAnonymousLpSolve, Object paramAnonymousObject, String paramAnonymousString) { this.numCalls += 1;
/* 1721 */         Assert.assertEquals(localLpSolve, paramAnonymousLpSolve);
/* 1722 */         Assert.assertEquals(new Integer(123), paramAnonymousObject);
/* 1723 */         Assert.assertNotNull(paramAnonymousString);
/*      */       }
/*      */       
/* 1726 */     };
/* 1727 */     localLpSolve.putLogfunc(local3MyListener, new Integer(123));
/*      */     try {
/* 1729 */       localLpSolve.delColumn(1);
/*      */     }
/*      */     catch (LpSolveException localLpSolveException)
/*      */     {
/* 1733 */       System.out.println("delColumn failed as expected");
/*      */     }
/* 1735 */     localLpSolve.deleteLp();
/* 1736 */     assertTrue(local3MyListener.numCalls > 0);
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/LpSolveTest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */