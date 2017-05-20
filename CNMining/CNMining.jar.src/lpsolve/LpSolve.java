/*      */ package lpsolve;
/*      */ 
/*      */ import java.util.HashMap;
/*      */ import java.util.Map;
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
/*      */ public class LpSolve
/*      */ {
/*      */   public static final int FALSE = 0;
/*      */   public static final int TRUE = 1;
/*      */   public static final int AUTOMATIC = 2;
/*      */   public static final int DYNAMIC = 4;
/*      */   public static final int FR = 0;
/*      */   public static final int LE = 1;
/*      */   public static final int GE = 2;
/*      */   public static final int EQ = 3;
/*      */   public static final int OF = 4;
/*      */   public static final int SIMPLEX_PRIMAL_PRIMAL = 5;
/*      */   public static final int SIMPLEX_DUAL_PRIMAL = 6;
/*      */   public static final int SIMPLEX_PRIMAL_DUAL = 9;
/*      */   public static final int SIMPLEX_DUAL_DUAL = 10;
/*      */   public static final int SIMPLEX_DEFAULT = 6;
/*      */   public static final int PRESOLVE_NONE = 0;
/*      */   public static final int PRESOLVE_ROWS = 1;
/*      */   public static final int PRESOLVE_COLS = 2;
/*      */   public static final int PRESOLVE_LINDEP = 4;
/*      */   public static final int PRESOLVE_SOS = 32;
/*      */   public static final int PRESOLVE_REDUCEMIP = 64;
/*      */   public static final int PRESOLVE_KNAPSACK = 128;
/*      */   public static final int PRESOLVE_ELIMEQ2 = 256;
/*      */   public static final int PRESOLVE_IMPLIEDFREE = 512;
/*      */   public static final int PRESOLVE_REDUCEGCD = 1024;
/*      */   public static final int PRESOLVE_PROBEFIX = 2048;
/*      */   public static final int PRESOLVE_PROBEREDUCE = 4096;
/*      */   public static final int PRESOLVE_ROWDOMINATE = 8192;
/*      */   public static final int PRESOLVE_COLDOMINATE = 16384;
/*      */   public static final int PRESOLVE_MERGEROWS = 32768;
/*      */   public static final int PRESOLVE_IMPLIEDSLK = 65536;
/*      */   public static final int PRESOLVE_COLFIXDUAL = 131072;
/*      */   public static final int PRESOLVE_BOUNDS = 262144;
/*      */   public static final int PRESOLVE_DUALS = 524288;
/*      */   public static final int PRESOLVE_SENSDUALS = 1048576;
/*      */   public static final int CRASH_NOTHING = 0;
/*      */   public static final int CRASH_MOSTFEASIBLE = 2;
/*      */   public static final int ANTIDEGEN_NONE = 0;
/*      */   public static final int ANTIDEGEN_FIXEDVARS = 1;
/*      */   public static final int ANTIDEGEN_COLUMNCHECK = 2;
/*      */   public static final int ANTIDEGEN_STALLING = 4;
/*      */   public static final int ANTIDEGEN_NUMFAILURE = 8;
/*      */   public static final int ANTIDEGEN_LOSTFEAS = 16;
/*      */   public static final int ANTIDEGEN_INFEASIBLE = 32;
/*      */   public static final int ANTIDEGEN_DYNAMIC = 64;
/*      */   public static final int ANTIDEGEN_DURINGBB = 128;
/*      */   public static final int ANTIDEGEN_RHSPERTURB = 256;
/*      */   public static final int ANTIDEGEN_BOUNDFLIP = 512;
/*      */   public static final int NEUTRAL = 0;
/*      */   public static final int CRITICAL = 1;
/*      */   public static final int SEVERE = 2;
/*      */   public static final int IMPORTANT = 3;
/*      */   public static final int NORMAL = 4;
/*      */   public static final int DETAILED = 5;
/*      */   public static final int FULL = 6;
/*      */   public static final int MSG_NONE = 0;
/*      */   public static final int MSG_PRESOLVE = 1;
/*      */   public static final int MSG_ITERATION = 2;
/*      */   public static final int MSG_INVERT = 4;
/*      */   public static final int MSG_LPFEASIBLE = 8;
/*      */   public static final int MSG_LPOPTIMAL = 16;
/*      */   public static final int MSG_LPEQUAL = 32;
/*      */   public static final int MSG_LPBETTER = 64;
/*      */   public static final int MSG_MILPFEASIBLE = 128;
/*      */   public static final int MSG_MILPEQUAL = 256;
/*      */   public static final int MSG_MILPBETTER = 512;
/*      */   public static final int MSG_MILPSTRATEGY = 1024;
/*      */   public static final int MSG_MILPOPTIMAL = 2048;
/*      */   public static final int MSG_PERFORMANCE = 4096;
/*      */   public static final int MSG_INITPSEUDOCOST = 8192;
/*      */   public static final int IMPROVE_NONE = 0;
/*      */   public static final int IMPROVE_SOLUTION = 1;
/*      */   public static final int IMPROVE_DUALFEAS = 2;
/*      */   public static final int IMPROVE_THETAGAP = 4;
/*      */   public static final int IMPROVE_BBSIMPLEX = 8;
/*      */   public static final int SCALE_NONE = 0;
/*      */   public static final int SCALE_EXTREME = 1;
/*      */   public static final int SCALE_RANGE = 2;
/*      */   public static final int SCALE_MEAN = 3;
/*      */   public static final int SCALE_GEOMETRIC = 4;
/*      */   public static final int SCALE_CURTISREID = 7;
/*      */   public static final int SCALE_LINEAR = 0;
/*      */   public static final int SCALE_QUADRATIC = 8;
/*      */   public static final int SCALE_LOGARITHMIC = 16;
/*      */   public static final int SCALE_USERWEIGHT = 31;
/*      */   public static final int SCALE_POWER2 = 32;
/*      */   public static final int SCALE_EQUILIBRATE = 64;
/*      */   public static final int SCALE_INTEGERS = 128;
/*      */   public static final int SCALE_DYNUPDATE = 256;
/*      */   public static final int SCALE_ROWSONLY = 512;
/*      */   public static final int SCALE_COLSONLY = 1024;
/*      */   public static final int PRICER_FIRSTINDEX = 0;
/*      */   public static final int PRICER_DANTZIG = 1;
/*      */   public static final int PRICER_DEVEX = 2;
/*      */   public static final int PRICER_STEEPESTEDGE = 3;
/*      */   public static final int PRICE_METHODDEFAULT = 0;
/*      */   public static final int PRICE_PRIMALFALLBACK = 4;
/*      */   public static final int PRICE_MULTIPLE = 8;
/*      */   public static final int PRICE_PARTIAL = 16;
/*      */   public static final int PRICE_ADAPTIVE = 32;
/*      */   public static final int PRICE_HYBRID = 64;
/*      */   public static final int PRICE_RANDOMIZE = 128;
/*      */   public static final int PRICE_AUTOPARTIAL = 512;
/*      */   public static final int PRICE_LOOPLEFT = 1024;
/*      */   public static final int PRICE_LOOPALTERNATE = 2048;
/*      */   public static final int PRICE_HARRISTWOPASS = 4096;
/*      */   public static final int PRICE_TRUENORMINIT = 16384;
/*      */   public static final int NODE_FIRSTSELECT = 0;
/*      */   public static final int NODE_GAPSELECT = 1;
/*      */   public static final int NODE_RANGESELECT = 2;
/*      */   public static final int NODE_FRACTIONSELECT = 3;
/*      */   public static final int NODE_PSEUDOCOSTSELECT = 4;
/*      */   public static final int NODE_PSEUDONONINTSELECT = 5;
/*      */   public static final int NODE_PSEUDORATIOSELECT = 6;
/*      */   public static final int NODE_USERSELECT = 7;
/*      */   public static final int NODE_WEIGHTREVERSEMODE = 8;
/*      */   public static final int NODE_BRANCHREVERSEMODE = 16;
/*      */   public static final int NODE_GREEDYMODE = 32;
/*      */   public static final int NODE_PSEUDOCOSTMODE = 64;
/*      */   public static final int NODE_DEPTHFIRSTMODE = 128;
/*      */   public static final int NODE_RANDOMIZEMODE = 256;
/*      */   public static final int NODE_DYNAMICMODE = 1024;
/*      */   public static final int NODE_RESTARTMODE = 2048;
/*      */   public static final int NODE_BREADTHFIRSTMODE = 4096;
/*      */   public static final int NODE_AUTOORDER = 8192;
/*      */   public static final int NODE_RCOSTFIXING = 16384;
/*      */   public static final int NODE_STRONGINIT = 32768;
/*      */   public static final int BRANCH_CEILING = 0;
/*      */   public static final int BRANCH_FLOOR = 1;
/*      */   public static final int BRANCH_AUTOMATIC = 2;
/*      */   public static final int BRANCH_DEFAULT = 3;
/*      */   public static final int UNKNOWNERROR = -5;
/*      */   public static final int DATAIGNORED = -4;
/*      */   public static final int NOBFP = -3;
/*      */   public static final int NOMEMORY = -2;
/*      */   public static final int NOTRUN = -1;
/*      */   public static final int OPTIMAL = 0;
/*      */   public static final int SUBOPTIMAL = 1;
/*      */   public static final int INFEASIBLE = 2;
/*      */   public static final int UNBOUNDED = 3;
/*      */   public static final int DEGENERATE = 4;
/*      */   public static final int NUMFAILURE = 5;
/*      */   public static final int USERABORT = 6;
/*      */   public static final int TIMEOUT = 7;
/*      */   public static final int RUNNING = 8;
/*      */   public static final int PRESOLVED = 9;
/*      */   public static final int PROCFAIL = 10;
/*      */   public static final int PROCBREAK = 11;
/*      */   public static final int FEASFOUND = 12;
/*      */   public static final int NOFEASFOUND = 13;
/*  219 */   private long lp = 0L;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*  224 */   private AbortListener abortListener = null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*  229 */   private Object abortUserhandle = null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*  234 */   private LogListener logListener = null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*  239 */   private Object logUserhandle = null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*  244 */   private MsgListener msgListener = null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*  249 */   private Object msgUserhandle = null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*  254 */   private BbListener bbBranchListener = null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*  259 */   private Object bbBranchUserhandle = null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*  264 */   private BbListener bbNodeListener = null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*  269 */   private Object bbNodeUserhandle = null;
/*      */   
/*      */ 
/*      */ 
/*      */   static
/*      */   {
/*  275 */     System.loadLibrary("lpsolve55j");
/*  276 */     init();
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
/*      */   private LpSolve(long paramLong)
/*      */   {
/*  293 */     this.lp = paramLong;
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
/*      */   protected void finalize()
/*      */     throws Throwable
/*      */   {
/*  357 */     if (this.lp != 0L) {
/*  358 */       removeLp(this.lp);
/*  359 */       deleteLp();
/*      */     }
/*  361 */     super.finalize();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public long getLp()
/*      */   {
/*  369 */     return this.lp;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void putAbortfunc(AbortListener paramAbortListener, Object paramObject)
/*      */     throws LpSolveException
/*      */   {
/* 1596 */     this.abortListener = paramAbortListener;
/* 1597 */     this.abortUserhandle = (paramAbortListener != null ? paramObject : null);
/* 1598 */     addLp(this);
/* 1599 */     registerAbortfunc();
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
/*      */   public void putLogfunc(LogListener paramLogListener, Object paramObject)
/*      */     throws LpSolveException
/*      */   {
/* 1614 */     this.logListener = paramLogListener;
/* 1615 */     this.logUserhandle = (paramLogListener != null ? paramObject : null);
/* 1616 */     addLp(this);
/* 1617 */     registerLogfunc();
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
/*      */   public void putMsgfunc(MsgListener paramMsgListener, Object paramObject, int paramInt)
/*      */     throws LpSolveException
/*      */   {
/* 1632 */     this.msgListener = paramMsgListener;
/* 1633 */     this.msgUserhandle = (paramMsgListener != null ? paramObject : null);
/* 1634 */     addLp(this);
/* 1635 */     registerMsgfunc(paramInt);
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
/*      */   public void putBbBranchfunc(BbListener paramBbListener, Object paramObject)
/*      */     throws LpSolveException
/*      */   {
/* 1650 */     this.bbBranchListener = paramBbListener;
/* 1651 */     this.bbBranchUserhandle = (paramBbListener != null ? paramObject : null);
/* 1652 */     addLp(this);
/* 1653 */     registerBbBranchfunc();
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
/*      */   public void putBbNodefunc(BbListener paramBbListener, Object paramObject)
/*      */     throws LpSolveException
/*      */   {
/* 1668 */     this.bbNodeListener = paramBbListener;
/* 1669 */     this.bbNodeUserhandle = (paramBbListener != null ? paramObject : null);
/* 1670 */     addLp(this);
/* 1671 */     registerBbNodefunc();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1678 */   private static Map lpMap = new HashMap();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private static synchronized void addLp(LpSolve paramLpSolve)
/*      */   {
/* 1685 */     lpMap.put(new Long(paramLpSolve.lp), paramLpSolve);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static synchronized LpSolve getLp(long paramLong)
/*      */   {
/* 1694 */     return (LpSolve)lpMap.get(new Long(paramLong));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private static synchronized void removeLp(long paramLong)
/*      */   {
/* 1702 */     lpMap.remove(new Long(paramLong));
/*      */   }
/*      */   
/*      */   private static native void init();
/*      */   
/*      */   public static native LpSolve makeLp(int paramInt1, int paramInt2)
/*      */     throws LpSolveException;
/*      */   
/*      */   public static native LpSolve readLp(String paramString1, int paramInt, String paramString2)
/*      */     throws LpSolveException;
/*      */   
/*      */   public static native LpSolve readMps(String paramString, int paramInt)
/*      */     throws LpSolveException;
/*      */   
/*      */   public static native LpSolve readFreeMps(String paramString, int paramInt)
/*      */     throws LpSolveException;
/*      */   
/*      */   public static native LpSolve readXLI(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt)
/*      */     throws LpSolveException;
/*      */   
/*      */   public static native VersionInfo lpSolveVersion();
/*      */   
/*      */   public native LpSolve copyLp()
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void setLpName(String paramString)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void resizeLp(int paramInt1, int paramInt2)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native String getLpName()
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void addConstraint(double[] paramArrayOfDouble, int paramInt, double paramDouble)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void strAddConstraint(String paramString, int paramInt, double paramDouble)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void addConstraintex(int paramInt1, double[] paramArrayOfDouble, int[] paramArrayOfInt, int paramInt2, double paramDouble)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void delConstraint(int paramInt)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native boolean isConstrType(int paramInt1, int paramInt2);
/*      */   
/*      */   public native void addLagCon(double[] paramArrayOfDouble, int paramInt, double paramDouble)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void strAddLagCon(String paramString, int paramInt, double paramDouble)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void addColumn(double[] paramArrayOfDouble)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void addColumnex(int paramInt, double[] paramArrayOfDouble, int[] paramArrayOfInt)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void strAddColumn(String paramString)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void delColumn(int paramInt)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void setRow(int paramInt, double[] paramArrayOfDouble)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void setRowex(int paramInt1, int paramInt2, double[] paramArrayOfDouble, int[] paramArrayOfInt)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void setColumn(int paramInt, double[] paramArrayOfDouble)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void setColumnex(int paramInt1, int paramInt2, double[] paramArrayOfDouble, int[] paramArrayOfInt)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native int columnInLp(double[] paramArrayOfDouble);
/*      */   
/*      */   public native void setRowName(int paramInt, String paramString)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native String getRowName(int paramInt)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native String getOrigrowName(int paramInt)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void setColName(int paramInt, String paramString)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native String getColName(int paramInt)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native String getOrigcolName(int paramInt)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void setRhVec(double[] paramArrayOfDouble)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void strSetRhVec(String paramString)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void setRh(int paramInt, double paramDouble)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native double getRh(int paramInt);
/*      */   
/*      */   public native void setConstrType(int paramInt1, int paramInt2)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native short getConstrType(int paramInt)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void addSOS(String paramString, int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt, double[] paramArrayOfDouble)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native boolean isSOSVar(int paramInt)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void setObjFn(double[] paramArrayOfDouble)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void strSetObjFn(String paramString)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void setObjFnex(int paramInt, double[] paramArrayOfDouble, int[] paramArrayOfInt)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void setObj(int paramInt, double paramDouble)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void setMat(int paramInt1, int paramInt2, double paramDouble)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native double getMat(int paramInt1, int paramInt2);
/*      */   
/*      */   public native void getRow(int paramInt, double[] paramArrayOfDouble)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native int getRowex(int paramInt, double[] paramArrayOfDouble, int[] paramArrayOfInt)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native double[] getPtrRow(int paramInt)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void getColumn(int paramInt, double[] paramArrayOfDouble)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native int getColumnex(int paramInt, double[] paramArrayOfDouble, int[] paramArrayOfInt)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native double[] getPtrColumn(int paramInt)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void setMaxim();
/*      */   
/*      */   public native void setMinim();
/*      */   
/*      */   public native void setSense(boolean paramBoolean);
/*      */   
/*      */   public native boolean isMaxim();
/*      */   
/*      */   public native void setLowbo(int paramInt, double paramDouble)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native double getLowbo(int paramInt)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void setUpbo(int paramInt, double paramDouble)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native double getUpbo(int paramInt)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void setUnbounded(int paramInt)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native boolean isUnbounded(int paramInt);
/*      */   
/*      */   public native boolean isNegative(int paramInt);
/*      */   
/*      */   public native void setBounds(int paramInt, double paramDouble1, double paramDouble2)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void setBoundsTighter(boolean paramBoolean);
/*      */   
/*      */   public native boolean getBoundsTighter();
/*      */   
/*      */   public native void setRhRange(int paramInt, double paramDouble)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native double getRhRange(int paramInt)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void setInt(int paramInt, boolean paramBoolean)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native boolean isInt(int paramInt);
/*      */   
/*      */   public native void setBinary(int paramInt, boolean paramBoolean)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native boolean isBinary(int paramInt);
/*      */   
/*      */   public native void setSemicont(int paramInt, boolean paramBoolean)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native boolean isSemicont(int paramInt);
/*      */   
/*      */   public native void setInfinite(double paramDouble);
/*      */   
/*      */   public native double getInfinite();
/*      */   
/*      */   public native boolean isInfinite(double paramDouble);
/*      */   
/*      */   public native void setEpsint(double paramDouble);
/*      */   
/*      */   public native double getEpsint();
/*      */   
/*      */   public native void setEpsb(double paramDouble);
/*      */   
/*      */   public native double getEpsb();
/*      */   
/*      */   public native void setEpsd(double paramDouble);
/*      */   
/*      */   public native double getEpsd();
/*      */   
/*      */   public native void setEpsel(double paramDouble);
/*      */   
/*      */   public native double getEpsel();
/*      */   
/*      */   public native void setEpspivot(double paramDouble);
/*      */   
/*      */   public native double getEpspivot();
/*      */   
/*      */   public native void setEpsperturb(double paramDouble);
/*      */   
/*      */   public native double getEpsperturb();
/*      */   
/*      */   public native void setEpslevel(int paramInt)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native int getStatus();
/*      */   
/*      */   public native void setMipGap(boolean paramBoolean, double paramDouble);
/*      */   
/*      */   public native double getMipGap(boolean paramBoolean);
/*      */   
/*      */   public native void setVerbose(int paramInt);
/*      */   
/*      */   public native int getVerbose();
/*      */   
/*      */   public native void setTimeout(long paramLong);
/*      */   
/*      */   public native long getTimeout();
/*      */   
/*      */   public native double timeElapsed();
/*      */   
/*      */   public native void setPrintSol(int paramInt);
/*      */   
/*      */   public native int getPrintSol();
/*      */   
/*      */   public native void setDebug(boolean paramBoolean);
/*      */   
/*      */   public native boolean isDebug();
/*      */   
/*      */   public native void setTrace(boolean paramBoolean);
/*      */   
/*      */   public native boolean isTrace();
/*      */   
/*      */   public native void setLagTrace(boolean paramBoolean);
/*      */   
/*      */   public native boolean isLagTrace();
/*      */   
/*      */   public native boolean setAddRowmode(boolean paramBoolean);
/*      */   
/*      */   public native boolean isAddRowmode();
/*      */   
/*      */   public native void setAntiDegen(int paramInt);
/*      */   
/*      */   public native boolean isAntiDegen(int paramInt);
/*      */   
/*      */   public native int getAntiDegen();
/*      */   
/*      */   public native void setPresolve(int paramInt1, int paramInt2);
/*      */   
/*      */   public native boolean isPresolve(int paramInt);
/*      */   
/*      */   public native int getPresolve();
/*      */   
/*      */   public native int getPresolveloops();
/*      */   
/*      */   public native void setMaxpivot(int paramInt);
/*      */   
/*      */   public native int getMaxpivot();
/*      */   
/*      */   public native void setBbRule(int paramInt);
/*      */   
/*      */   public native int getBbRule();
/*      */   
/*      */   public native void setBbDepthlimit(int paramInt);
/*      */   
/*      */   public native int getBbDepthlimit();
/*      */   
/*      */   public native int getSolutioncount();
/*      */   
/*      */   public native void setSolutionlimit(int paramInt);
/*      */   
/*      */   public native int getSolutionlimit();
/*      */   
/*      */   public native void setObjBound(double paramDouble);
/*      */   
/*      */   public native double getObjBound();
/*      */   
/*      */   public native void setBbFloorfirst(int paramInt);
/*      */   
/*      */   public native int getBbFloorfirst();
/*      */   
/*      */   public native void setVarBranch(int paramInt1, int paramInt2)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native int getVarBranch(int paramInt)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void setVarWeights(double[] paramArrayOfDouble)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native int getVarPriority(int paramInt)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void setBreakAtFirst(boolean paramBoolean);
/*      */   
/*      */   public native boolean isBreakAtFirst();
/*      */   
/*      */   public native void setBreakAtValue(double paramDouble);
/*      */   
/*      */   public native double getBreakAtValue();
/*      */   
/*      */   public native void setScaling(int paramInt);
/*      */   
/*      */   public native int getScaling();
/*      */   
/*      */   public native boolean isScalemode(int paramInt);
/*      */   
/*      */   public native boolean isScaletype(int paramInt);
/*      */   
/*      */   public native boolean isIntegerscaling();
/*      */   
/*      */   public native void setScalelimit(double paramDouble);
/*      */   
/*      */   public native double getScalelimit();
/*      */   
/*      */   public native void setImprove(int paramInt);
/*      */   
/*      */   public native int getImprove();
/*      */   
/*      */   public native void setPivoting(int paramInt);
/*      */   
/*      */   public native int getPivoting();
/*      */   
/*      */   public native boolean isPivMode(int paramInt);
/*      */   
/*      */   public native boolean isPivRule(int paramInt);
/*      */   
/*      */   public native void setPreferdual(boolean paramBoolean);
/*      */   
/*      */   public native void setSimplextype(int paramInt);
/*      */   
/*      */   public native int getSimplextype();
/*      */   
/*      */   public native void setNegrange(double paramDouble);
/*      */   
/*      */   public native double getNegrange();
/*      */   
/*      */   public native long getTotalIter();
/*      */   
/*      */   public native int getMaxLevel();
/*      */   
/*      */   public native long getTotalNodes();
/*      */   
/*      */   public native int getNrows();
/*      */   
/*      */   public native int getNorigRows();
/*      */   
/*      */   public native int getLrows();
/*      */   
/*      */   public native int getNcolumns();
/*      */   
/*      */   public native int getNorigColumns();
/*      */   
/*      */   public native int getNonzeros();
/*      */   
/*      */   public native int getOrigIndex(int paramInt);
/*      */   
/*      */   public native int getLpIndex(int paramInt);
/*      */   
/*      */   public native void setBasis(int[] paramArrayOfInt, boolean paramBoolean)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void guessBasis(double[] paramArrayOfDouble, int[] paramArrayOfInt)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void getBasis(int[] paramArrayOfInt, boolean paramBoolean)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void resetBasis();
/*      */   
/*      */   public native void defaultBasis();
/*      */   
/*      */   public native void setBasiscrash(int paramInt);
/*      */   
/*      */   public native int getBasiscrash();
/*      */   
/*      */   public native void unscale();
/*      */   
/*      */   public native void setBFP(String paramString)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native boolean isNativeBFP();
/*      */   
/*      */   public native boolean hasBFP();
/*      */   
/*      */   public native int solve()
/*      */     throws LpSolveException;
/*      */   
/*      */   public native String getStatustext(int paramInt);
/*      */   
/*      */   public native boolean isFeasible(double[] paramArrayOfDouble, double paramDouble)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native double getObjective()
/*      */     throws LpSolveException;
/*      */   
/*      */   public native double getWorkingObjective()
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void getVariables(double[] paramArrayOfDouble)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native double[] getPtrVariables()
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void getConstraints(double[] paramArrayOfDouble)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native double[] getPtrConstraints()
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void getPrimalSolution(double[] paramArrayOfDouble)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native double[] getPtrPrimalSolution()
/*      */     throws LpSolveException;
/*      */   
/*      */   public native double getVarPrimalresult(int paramInt)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void getSensitivityRhs(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native double[][] getPtrSensitivityRhs()
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void getDualSolution(double[] paramArrayOfDouble)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native double[] getPtrDualSolution()
/*      */     throws LpSolveException;
/*      */   
/*      */   public native double getVarDualresult(int paramInt)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void getSensitivityObj(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native double[][] getPtrSensitivityObj()
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void getSensitivityObjex(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3, double[] paramArrayOfDouble4)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native double[][] getPtrSensitivityObjex()
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void getLambda(double[] paramArrayOfDouble)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native double[] getPtrLambda()
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void deleteLp();
/*      */   
/*      */   public native void writeLp(String paramString)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void writeMps(String paramString)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void writeFreeMps(String paramString)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native String readBasis(String paramString)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void writeBasis(String paramString)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void readParams(String paramString1, String paramString2)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void writeParams(String paramString1, String paramString2)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void resetParams();
/*      */   
/*      */   public native void printLp();
/*      */   
/*      */   public native void printConstraints(int paramInt);
/*      */   
/*      */   public native void printDuals();
/*      */   
/*      */   public native void printScales();
/*      */   
/*      */   public native void printTableau();
/*      */   
/*      */   public native void printObjective();
/*      */   
/*      */   public native void printSolution(int paramInt);
/*      */   
/*      */   public native void printStr(String paramString);
/*      */   
/*      */   public native void setOutputfile(String paramString)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void printDebugdump(String paramString)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void setXLI(String paramString)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native void writeXLI(String paramString1, String paramString2, boolean paramBoolean)
/*      */     throws LpSolveException;
/*      */   
/*      */   public native boolean hasXLI();
/*      */   
/*      */   public native boolean isNativeXLI();
/*      */   
/*      */   public native int getNameindex(String paramString, boolean paramBoolean);
/*      */   
/*      */   public native void dualizeLp()
/*      */     throws LpSolveException;
/*      */   
/*      */   public native boolean isUseNames(boolean paramBoolean);
/*      */   
/*      */   public native void setUseNames(boolean paramBoolean1, boolean paramBoolean2);
/*      */   
/*      */   public native double getConstrValue(int paramInt1, int paramInt2, double[] paramArrayOfDouble, int[] paramArrayOfInt);
/*      */   
/*      */   public native int setBasisvar(int paramInt1, int paramInt2);
/*      */   
/*      */   private native void registerAbortfunc();
/*      */   
/*      */   private native void registerLogfunc();
/*      */   
/*      */   private native void registerMsgfunc(int paramInt);
/*      */   
/*      */   private native void registerBbBranchfunc();
/*      */   
/*      */   private native void registerBbNodefunc();
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/lpsolve/LpSolve.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */