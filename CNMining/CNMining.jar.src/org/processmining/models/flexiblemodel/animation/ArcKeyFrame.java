/**
 * 
 */
package org.processmining.models.flexiblemodel.animation;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.processmining.framework.util.Pair;

/**
 * @author aadrians
 * 
 */
public class ArcKeyFrame extends KeyFrame {
	private Date latestEndTime = null;
	private List<Pair<Date, Date>> listTimePair;

	public ArcKeyFrame(int numOfActivation, Date from, Date to) {
		super(0, from);
		listTimePair = new LinkedList<Pair<Date, Date>>();
		latestEndTime = to;
		addTimePair(from, to);
	}

	public List<Pair<Date, Date>> getTimePair() {
		return listTimePair;
	}

	public void setTimePair(List<Pair<Date, Date>> listTimePair) {
		this.listTimePair = listTimePair;
	}

	public void addTimePair(Date from, Date to) {
		// always insert it 
		Pair<Date, Date> newPair = new Pair<Date, Date>(from, to);
		listTimePair.add(newPair);

		// update latestEndTime
		if (to.after(latestEndTime)) {
			latestEndTime = to;
		}

		// increment number of activation
		incrementNumOfActivation();
	}

	/**
	 * @param activeAnimations
	 */
	public void addActiveAnimations(List<Pair<Date, Date>> activeAnimations) {
		listTimePair.addAll(activeAnimations);
	}

	public Date getLatestEndTime() {
		return latestEndTime;
	}

	public void setLatestEndTime(Date latestEndTime) {
		this.latestEndTime = latestEndTime;
	}
}
