package org.processmining.plugins.rttmining;

/*
 * Questa classe contiene le configurazioni generali del plugin
 */

public class Settings
{
	public String constraintsFilename = "";
	public boolean constraintsEnabled;
	public double sigmaLogNoise;
	public double fallFactor;
	public String logName;
	public double relativeToBest;
	
	
	public Settings(){
		this.constraintsFilename = this.logName = "";
		this.constraintsEnabled = false;
		this.sigmaLogNoise = this.fallFactor = this.relativeToBest = 0.0D;
	}
	
	public boolean areConstraintsAvailable(){
		return this.constraintsEnabled && this.constraintsFilename.equals("") == false;
	}
}
