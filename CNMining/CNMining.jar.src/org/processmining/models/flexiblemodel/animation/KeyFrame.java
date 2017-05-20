/**
 * 
 */
package org.processmining.models.flexiblemodel.animation;

import java.util.Date;

/**
 * @author aadrians
 * 
 */
public class KeyFrame {
	protected int numOfActivation = 0;
	protected Date startTime;

	@SuppressWarnings("unused")
	private KeyFrame() {
	};

	public KeyFrame(int numOfActivation, Date time) {
		this.numOfActivation = numOfActivation;
		startTime = time;
	}

	public int getNumOfActivation() {
		return numOfActivation;
	}

	public void setNumOfActivation(int numOfActivation) {
		this.numOfActivation = numOfActivation;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void incrementNumOfActivation() {
		numOfActivation++;
	}
}
