package org.processmining.plugins.cnet2ad.semantic;

import java.util.ArrayList;

public class Activity {
	
	public String name;
	private ArrayList<String> resources;
	
	
	public Activity(String name){
		this.name = name;
		this.resources = new ArrayList<String>();
	}
	
	public ArrayList<String> resources(){
		return this.resources;
	}
	
	public void add(String resource){
		for(String r: this.resources())
			if(r.equals(resource))
				return;
		this.resources.add(resource);
	}
	
}
