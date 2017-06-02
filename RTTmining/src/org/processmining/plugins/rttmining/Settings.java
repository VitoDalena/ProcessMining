package org.processmining.plugins.rttmining;

public class Settings {
	public String ontologyFilename;
	public String causalnetFilename;	
	public String jsonFilename;
	public boolean exportJson;
	
	public Settings(){
		this.ontologyFilename = "";
		this.causalnetFilename = "";
		this.jsonFilename = "rttmining.json";
		this.exportJson = false;
	}
}
