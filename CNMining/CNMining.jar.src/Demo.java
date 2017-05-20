/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PrintStream;
/*     */ import lpsolve.AbortListener;
/*     */ import lpsolve.LogListener;
/*     */ import lpsolve.LpSolve;
/*     */ import lpsolve.LpSolveException;
/*     */ import lpsolve.MsgListener;
/*     */ import lpsolve.VersionInfo;
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
/*     */ public class Demo
/*     */ {
/*     */   public void execute()
/*     */     throws LpSolveException
/*     */   {
/*  38 */     VersionInfo localVersionInfo = LpSolve.lpSolveVersion();
/*  39 */     System.out.println("This demo (Java version) will show most of the features of lp_solve " + localVersionInfo.getMajorversion() + "." + localVersionInfo.getMinorversion() + "." + localVersionInfo.getRelease() + "." + localVersionInfo.getBuild());
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  44 */     pressRet();
/*     */     
/*  46 */     System.out.println("We start by creating a new problem with 4 variables and 0 constraints");
/*     */     
/*  48 */     System.out.println("We use: LpSolve problem = LpSolve.makeLp(0, 4);");
/*  49 */     LpSolve localLpSolve = LpSolve.makeLp(0, 4);
/*  50 */     pressRet();
/*     */     
/*     */ 
/*  53 */     LogListener local1 = new LogListener() {
/*     */       public void logfunc(LpSolve paramAnonymousLpSolve, Object paramAnonymousObject, String paramAnonymousString) {
/*  55 */         System.out.println(paramAnonymousString);
/*     */       }
/*  57 */     };
/*  58 */     localLpSolve.putLogfunc(local1, null);
/*  59 */     localLpSolve.solve();
/*  60 */     localLpSolve.putLogfunc(null, null);
/*     */     
/*     */ 
/*  63 */     AbortListener local2 = new AbortListener()
/*     */     {
/*     */       public boolean abortfunc(LpSolve paramAnonymousLpSolve, Object paramAnonymousObject) {
/*  66 */         return false;
/*     */       }
/*  68 */     };
/*  69 */     localLpSolve.putAbortfunc(local2, null);
/*     */     
/*     */ 
/*  72 */     MsgListener local3 = new MsgListener()
/*     */     {
/*     */       public void msgfunc(LpSolve paramAnonymousLpSolve, Object paramAnonymousObject, int paramAnonymousInt) {}
/*     */ 
/*  76 */     };
/*  77 */     localLpSolve.putMsgfunc(local3, null, 921);
/*     */     
/*     */ 
/*     */ 
/*  81 */     System.out.println("We can show the current problem with problem.printLp()");
/*  82 */     localLpSolve.printLp();
/*  83 */     pressRet();
/*     */     
/*  85 */     System.out.println("Now we add some constraints:");
/*  86 */     System.out.println("problem.strAddConstraint(\"3 2 2 1\", LpSolve.LE, 4);");
/*  87 */     localLpSolve.strAddConstraint("3 2 2 1", 1, 4.0D);
/*  88 */     localLpSolve.printLp();
/*  89 */     pressRet();
/*     */     
/*  91 */     System.out.println("problem.strAddConstraint(\"0 4 3 1\", LpSolve.GE, 3);");
/*  92 */     localLpSolve.strAddConstraint("0 4 3 1", 2, 3.0D);
/*  93 */     localLpSolve.printLp();
/*  94 */     pressRet();
/*     */     
/*  96 */     System.out.println("Set the objective function:");
/*  97 */     System.out.println("problem.strSetObjFn(\"2 3 -2 3\");");
/*  98 */     localLpSolve.strSetObjFn("2 3 -2 3");
/*  99 */     localLpSolve.printLp();
/* 100 */     pressRet();
/*     */     
/* 102 */     System.out.println("Now solve the problem with System.out.println(problem.solve());");
/* 103 */     System.out.println(localLpSolve.solve());
/* 104 */     pressRet();
/*     */     
/* 106 */     System.out.println("The value is 0, this means we found an optimal solution.");
/* 107 */     System.out.println("We can display the solution with problem.printObjective(), problem.printSolution(1), and problem.printConstraints(1)");
/*     */     
/* 109 */     localLpSolve.printObjective();
/* 110 */     localLpSolve.printSolution(1);
/* 111 */     localLpSolve.printConstraints(1);
/* 112 */     pressRet();
/*     */     
/* 114 */     System.out.println("The dual variables of the solution are printed with problem.printDuals()");
/*     */     
/* 116 */     localLpSolve.printDuals();
/* 117 */     pressRet();
/*     */     
/* 119 */     System.out.println("We can change a single element in the matix with problem.setMat(2, 1, 0.5)");
/*     */     
/* 121 */     localLpSolve.setMat(2, 1, 0.5D);
/* 122 */     localLpSolve.printLp();
/* 123 */     pressRet();
/*     */     
/* 125 */     System.out.println("If we want to maximize the objective function use problem.setMaxim()");
/*     */     
/* 127 */     localLpSolve.setMaxim();
/* 128 */     localLpSolve.printLp();
/* 129 */     pressRet();
/*     */     
/* 131 */     System.out.println("After solving this gives us:");
/* 132 */     localLpSolve.solve();
/* 133 */     localLpSolve.printObjective();
/* 134 */     localLpSolve.printSolution(1);
/* 135 */     localLpSolve.printConstraints(1);
/* 136 */     localLpSolve.printDuals();
/* 137 */     pressRet();
/*     */     
/* 139 */     System.out.println("Change the value of a rhs element with problem.setRh(1, 7.45)");
/* 140 */     localLpSolve.setRh(1, 7.45D);
/* 141 */     localLpSolve.printLp();
/* 142 */     localLpSolve.solve();
/* 143 */     localLpSolve.printObjective();
/* 144 */     localLpSolve.printSolution(1);
/* 145 */     localLpSolve.printConstraints(1);
/* 146 */     pressRet();
/*     */     
/* 148 */     System.out.println("We change " + localLpSolve.getColName(4) + " to the integer type with problem.setInt(4, true)");
/*     */     
/* 150 */     localLpSolve.setInt(4, true);
/* 151 */     localLpSolve.printLp();
/* 152 */     System.out.println("We set branch & bound debugging on with problem.setDebug(true)");
/* 153 */     localLpSolve.setDebug(true);
/* 154 */     System.out.println("and solve...");
/* 155 */     pressRet();
/*     */     
/* 157 */     localLpSolve.solve();
/* 158 */     localLpSolve.printObjective();
/* 159 */     localLpSolve.printSolution(1);
/* 160 */     localLpSolve.printConstraints(1);
/* 161 */     pressRet();
/*     */     
/* 163 */     System.out.println("We can set bounds on the variables with problem.setLowbo(2, 2) & problem.setUpbo(4, 5.3)");
/*     */     
/* 165 */     localLpSolve.setLowbo(2, 2.0D);
/* 166 */     localLpSolve.setUpbo(4, 5.3D);
/* 167 */     localLpSolve.printLp();
/* 168 */     pressRet();
/*     */     
/* 170 */     localLpSolve.solve();
/* 171 */     localLpSolve.printObjective();
/* 172 */     localLpSolve.printSolution(1);
/* 173 */     localLpSolve.printConstraints(1);
/* 174 */     pressRet();
/*     */     
/* 176 */     System.out.println("Now remove a constraint with problem.delConstraint(1)");
/* 177 */     localLpSolve.delConstraint(1);
/* 178 */     localLpSolve.printLp();
/* 179 */     pressRet();
/*     */     
/* 181 */     System.out.println("Add an equality constraint:\nproblem.strAddConstraint(\"1 2 1 4\", LpSolve.EQ, 8)");
/*     */     
/* 183 */     localLpSolve.strAddConstraint("1 2 1 4", 3, 8.0D);
/* 184 */     localLpSolve.printLp();
/* 185 */     pressRet();
/*     */     
/* 187 */     System.out.println("A column can be added with problem.strAddColumn(\"3 2 2\")");
/* 188 */     localLpSolve.strAddColumn("3 2 2");
/* 189 */     localLpSolve.printLp();
/* 190 */     pressRet();
/*     */     
/* 192 */     System.out.println("A column can be removed with problem.delColumn(3)");
/* 193 */     localLpSolve.delColumn(3);
/* 194 */     localLpSolve.printLp();
/* 195 */     pressRet();
/*     */     
/* 197 */     System.out.println("We can use automatic scaling with problem.setScaling(LpSolve.SCALE_MEAN)");
/* 198 */     localLpSolve.setScaling(3);
/* 199 */     localLpSolve.printLp();
/* 200 */     pressRet();
/*     */     
/* 202 */     System.out.println("The function matElm returns a single matrix element.");
/* 203 */     System.out.println("problem.getMat(2, 3) returns " + localLpSolve.getMat(2, 3));
/* 204 */     System.out.println("problem.getMat(1, 1) returns " + localLpSolve.getMat(1, 1));
/* 205 */     System.out.println("Notice that getMat returns the value of the original unscaled problem");
/*     */     
/* 207 */     pressRet();
/*     */     
/* 209 */     System.out.println("If there are any integer type variables, then only  the rows are scaled.");
/*     */     
/* 211 */     System.out.println("problem.setScaling(LpSolve.SCALE_MEAN);");
/* 212 */     localLpSolve.setScaling(3);
/* 213 */     System.out.println("problem.setInt(3, false);");
/* 214 */     localLpSolve.setInt(3, false);
/* 215 */     localLpSolve.printLp();
/* 216 */     pressRet();
/*     */     
/* 218 */     System.out.println("printObjective, printSolution gives the solution to the original problem");
/*     */     
/* 220 */     localLpSolve.solve();
/* 221 */     localLpSolve.printObjective();
/* 222 */     localLpSolve.printSolution(1);
/* 223 */     localLpSolve.printConstraints(1);
/* 224 */     pressRet();
/*     */     
/* 226 */     System.out.println("Scaling is turned off with problem.unscale()");
/* 227 */     localLpSolve.unscale();
/* 228 */     localLpSolve.printLp();
/* 229 */     pressRet();
/*     */     
/* 231 */     System.out.println("Now turn B&B debugging off and simplex tracing on with");
/* 232 */     System.out.println("problem.setDebug(false), problem.setTrace(true) and solve.");
/* 233 */     localLpSolve.setDebug(false);
/* 234 */     localLpSolve.setTrace(true);
/* 235 */     pressRet();
/*     */     
/* 237 */     localLpSolve.solve();
/* 238 */     System.out.println("Where possible, lp_solve will start at the last found basis.");
/* 239 */     System.out.println("We can reset the problem to the initial basis with");
/* 240 */     System.out.println("problem.resetBasis(). Now solve it again ...");
/* 241 */     pressRet();
/*     */     
/* 243 */     localLpSolve.resetBasis();
/* 244 */     localLpSolve.solve();
/* 245 */     System.out.println("It is possible to give variables and constraints names.");
/* 246 */     System.out.println("problem.setRowName(1, \"speed\") & problem.setColName(2, \"money\")");
/* 247 */     localLpSolve.setRowName(1, "speed");
/* 248 */     localLpSolve.setColName(2, "money");
/* 249 */     localLpSolve.printLp();
/* 250 */     System.out.println("As you can see, all column and rows are assigned default names");
/* 251 */     System.out.println("If a column or constraint is deleted, the names shift place also:");
/* 252 */     pressRet();
/*     */     
/* 254 */     System.out.println("problem.delColumn(1)");
/* 255 */     localLpSolve.delColumn(1);
/* 256 */     localLpSolve.printLp();
/* 257 */     pressRet();
/*     */     
/*     */ 
/* 260 */     localLpSolve.deleteLp();
/*     */   }
/*     */   
/*     */   private void pressRet() {
/* 264 */     System.out.print("\n[Press return to continue or type 'q' to quit] ");
/* 265 */     BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(System.in));
/*     */     try {
/* 267 */       String str = localBufferedReader.readLine();
/* 268 */       if ((str == null) || ("q".equals(str.trim()))) {
/* 269 */         System.out.println("Demo terminated.");
/* 270 */         System.exit(0);
/*     */       }
/*     */     }
/*     */     catch (IOException localIOException) {
/* 274 */       System.exit(0);
/*     */     }
/* 276 */     System.out.println("");
/*     */   }
/*     */   
/*     */   public static void main(String[] paramArrayOfString) {
/*     */     try {
/* 281 */       new Demo().execute();
/*     */     }
/*     */     catch (LpSolveException localLpSolveException) {
/* 284 */       localLpSolveException.printStackTrace();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/Demo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */