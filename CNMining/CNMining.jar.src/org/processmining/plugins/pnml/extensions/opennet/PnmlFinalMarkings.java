package org.processmining.plugins.pnml.extensions.opennet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.processmining.framework.util.Pair;
import org.processmining.models.graphbased.AbstractGraphElement;
import org.processmining.models.graphbased.directed.opennet.OpenNet;
import org.processmining.models.graphbased.directed.petrinet.elements.ExpandableSubNet;
import org.processmining.models.graphbased.directed.petrinet.elements.Place;
import org.processmining.models.semantics.petrinet.Marking;
import org.processmining.plugins.pnml.Pnml;
import org.processmining.plugins.pnml.PnmlElement;
import org.xmlpull.v1.XmlPullParser;

public class PnmlFinalMarkings extends PnmlElement {

	public final static String TAG = "finalmarkings";

	private final List<PnmlFinalMarking> finalMarkingList;

	public PnmlFinalMarkings() {
		super(TAG);

		finalMarkingList = new ArrayList<PnmlFinalMarking>();
	}

	protected boolean importElements(XmlPullParser xpp, Pnml pnml) {
		if (super.importElements(xpp, pnml)) {
			/*
			 * Start tag corresponds to a known child element of a PNML
			 * annotation.
			 */
			return true;
		}
		if (xpp.getName().equals(PnmlFinalMarking.TAG)) {
			PnmlFinalMarking finalMarking = new PnmlFinalMarking();
			finalMarking.importElement(xpp, pnml);
			finalMarkingList.add(finalMarking);
			return true;
		}
		return false;
	}

	protected String exportElements(Pnml pnml) {
		String s = super.exportElements(pnml);
		for (PnmlFinalMarking finalMarking : finalMarkingList) {
			s += finalMarking.exportElement(pnml);
		}
		return s;
	}

	public void convertToOpenNet(OpenNet openNet, Map<String, Place> placeMap) {
		for (PnmlFinalMarking finalMarking : finalMarkingList) {
			finalMarking.convertToOpenNet(openNet, placeMap);
		}
	}

	public PnmlFinalMarkings convertFromOpenNet(Collection<? extends Place> places, Collection<Marking> markings,
			Map<Pair<AbstractGraphElement, ExpandableSubNet>, String> map) {
		for (Marking marking : markings) {
			PnmlFinalMarking finalMarking = new PnmlFinalMarking();
			finalMarking.convertFromOpenNet(places, marking, map);
			finalMarkingList.add(finalMarking);
		}
		return this;
	}
}
