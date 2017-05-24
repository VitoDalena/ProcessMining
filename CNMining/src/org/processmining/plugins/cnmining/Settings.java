/*    */ package org.processmining.plugins.cnmining;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Settings
/*    */ {
/* 12 */   private String constr_file_name = "";
/*    */   
/*    */   private boolean constraintsEnabled;
/*    */   
/*    */   private double sigma_log_noise;
/*    */   
/*    */   private double fallFactor;
/*    */   
/*    */   private String logName;
/*    */   private double rtb;
/*    */   
/*    */   public boolean isConstraintsEnabled()
/*    */   {
/* 25 */     return this.constraintsEnabled;
/*    */   }
/*    */   
/*    */   public void setConstraintsEnabled(boolean constraintsEnabled) {
/* 29 */     this.constraintsEnabled = constraintsEnabled;
/*    */   }
/*    */   
/*    */   public double getSigmaLogNoise() {
/* 33 */     return this.sigma_log_noise;
/*    */   }
/*    */   
/*    */   public void setSigmaLogNoise(double sigmaLogNoise) {
/* 37 */     this.sigma_log_noise = sigmaLogNoise;
/*    */   }
/*    */   
/*    */   public String getConstr_file_name() {
/* 41 */     return this.constr_file_name;
/*    */   }
/*    */   
/*    */   public void setLogName(String log) {
/* 45 */     this.logName = log;
/*    */   }
/*    */   
/*    */   public String getLogName() {
/* 49 */     return this.logName;
/*    */   }
/*    */   
/*    */   public void setFallFactor(double fallFactor)
/*    */   {
/* 54 */     this.fallFactor = fallFactor;
/*    */   }
/*    */   
/*    */   public void setRelativeToBest(double rtb)
/*    */   {
/* 59 */     this.rtb = rtb;
/*    */   }
/*    */   
/*    */   public double getFallFactor()
/*    */   {
/* 64 */     return this.fallFactor;
/*    */   }
/*    */   
/*    */   public double getRelativeToBest()
/*    */   {
/* 69 */     return this.rtb;
/*    */   }
/*    */   
/*    */   public void setConstr_file_name(String constr_file_name) {
/* 73 */     this.constr_file_name = constr_file_name;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/Dipendenze/CNMining.jar!/org/processmining/plugins/core/Settings.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */