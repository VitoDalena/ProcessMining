package org.processmining.plugins.cnet2ad;

public class Settings {
	public String ontologyFilename;
	public String causalnetFilename;	
	public String jsonFilename;
	public boolean exportJson;
	
	public Settings(){
		this.ontologyFilename = "";
		this.causalnetFilename = "";
		this.jsonFilename = "cnet2ad.json";
		this.exportJson = false;
	}
}
