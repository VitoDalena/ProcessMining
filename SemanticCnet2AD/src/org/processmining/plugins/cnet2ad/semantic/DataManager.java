package org.processmining.plugins.cnet2ad.semantic;

import java.util.ArrayList;

public class DataManager {
	
	ArrayList<Activity> activities;
	
	public DataManager(){
		this.activities = new ArrayList<Activity>();
	}
	
	public ArrayList<Activity> activities(){
		return this.activities;
	}
	
	public void add(Activity activity){
		for(Activity a: this.activities())
			if(a.name.equals(activity.name))
				return;
		this.activities.add(activity);
	}
	
	public Activity activity(String name){
		for(Activity activity:this.activities()){
			if(activity.name.equals(name))
				return activity;
		}
		return null;
	}
	
}
