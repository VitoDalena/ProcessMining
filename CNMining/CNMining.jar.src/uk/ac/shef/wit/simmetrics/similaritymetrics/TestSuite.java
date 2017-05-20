/*     */ package uk.ac.shef.wit.simmetrics.similaritymetrics;
/*     */ 
/*     */ import junit.framework.Test;
/*     */ import junit.framework.TestCase;
/*     */ import junit.framework.TestResult;
/*     */ import junit.textui.TestRunner;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TestSuite
/*     */   extends TestCase
/*     */ {
/*     */   public TestSuite(String s)
/*     */   {
/*  61 */     super(s);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setUp() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void tearDown() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Test testAllSimilarityMetrics()
/*     */   {
/*  88 */     junit.framework.TestSuite newSuite = new junit.framework.TestSuite();
/*  89 */     newSuite.addTest(new uk.ac.shef.wit.simmetrics.similaritymetrics.costfunctions.TestSuite("testAllCostFunctions"));
/*     */     
/*     */ 
/*  92 */     newSuite.addTest(new BlockDistanceTest());
/*  93 */     newSuite.addTest(new CandidatesTest());
/*  94 */     newSuite.addTest(new ChapmanLengthDeviationTest());
/*  95 */     newSuite.addTest(new ChapmanMatchingSoundexTest());
/*  96 */     newSuite.addTest(new ChapmanMeanLengthTest());
/*  97 */     newSuite.addTest(new ChapmanOrderedNameCompoundSimilarityTest());
/*  98 */     newSuite.addTest(new CosineSimilarityTest());
/*  99 */     newSuite.addTest(new DiceSimilarityTest());
/* 100 */     newSuite.addTest(new EuclideanDistanceTest());
/* 101 */     newSuite.addTest(new JaccardSimilarityTest());
/* 102 */     newSuite.addTest(new JaroTest());
/* 103 */     newSuite.addTest(new JaroWinklerTest());
/* 104 */     newSuite.addTest(new LevenshteinTest());
/* 105 */     newSuite.addTest(new MatchingCoefficientTest());
/* 106 */     newSuite.addTest(new MongeElkanTest());
/* 107 */     newSuite.addTest(new NeedlemanWunchTest());
/* 108 */     newSuite.addTest(new OverlapCoefficientTest());
/* 109 */     newSuite.addTest(new QGramsDistanceTest());
/* 110 */     newSuite.addTest(new SmithWatermanGotohTest());
/* 111 */     newSuite.addTest(new SmithWatermanGotohWindowedAffineTest());
/* 112 */     newSuite.addTest(new SmithWatermanTest());
/* 113 */     newSuite.addTest(new SoundexTest());
/* 114 */     newSuite.addTest(new TagLinkTest());
/* 115 */     newSuite.addTest(new TagLinkTokenTest());
/* 116 */     return newSuite;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 125 */     TestRunner runner = new TestRunner();
/* 126 */     System.exit(TestRunner.run(runner.getTest(TestSuite.class.getName())).wasSuccessful() ? 0 : 1);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/similaritymetrics/TestSuite.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */