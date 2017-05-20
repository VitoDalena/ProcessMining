package org.processmining.plugins.pnml.extensions.opennet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.processmining.framework.util.Pair;
import org.processmining.models.graphbased.AbstractGraphElement;
import org.processmining.models.graphbased.directed.opennet.OpenNet;
import org.processmining.models.graphbased.directed.opennet.OpenNetLabel;
import org.processmining.models.graphbased.directed.opennet.OpenNetPort;
import org.processmining.models.graphbased.directed.opennet.OpenNetLabel.Type;
import org.processmining.models.graphbased.directed.petrinet.elements.ExpandableSubNet;
import org.processmining.plugins.pnml.Pnml;
import org.processmining.plugins.pnml.PnmlName;
import org.processmining.plugins.pnml.PnmlNode;
import org.xmlpull.v1.XmlPullParser;

/**
 * @author hverbeek
 * 
 *         Pnml port. Contains inputs, outputs, and synchronous elements.
 */
public class PnmlPort extends PnmlNode {

	public final static String TAG = "port";

	private final List<PnmlLabel> labelList;

	public PnmlPort() {
		super(TAG);

		labelList = new ArrayList<PnmlLabel>();
	}

	protected boolean importElements(XmlPullParser xpp, Pnml pnml) {
		if (super.importElements(xpp, pnml)) {
			/*
			 * Start tag corresponds to a known child element of a PNML
			 * annotation.
			 */
			return true;
		}
		PnmlLabel label = null;
		if (xpp.getName().equals(PnmlLabel.Input.TAG)) {
			label = new PnmlLabel.Input();
		}
		if (xpp.getName().equals(PnmlLabel.Output.TAG)) {
			label = new PnmlLabel.Output();
		}
		if (xpp.getName().equals(PnmlLabel.Sync.TAG)) {
			label = new PnmlLabel.Sync();
		}
		if (label != null) {
			label.importElement(xpp, pnml);

			labelList.add(label);
			return true;
		}
		return false;
	}

	protected String exportElements(Pnml pnml) {
		String s = super.exportElements(pnml);
		for (PnmlLabel label : labelList) {
			s += label.exportElement(pnml);
		}
		return s;
	}

	public void convertToOpenNet(OpenNet openNet) {
		/*
		 * If no name specified, use the id as label.
		 */
		OpenNetPort port = new OpenNetPort((((name != null) && (name.text != null)) ? name.text.getText() : id), id);
		openNet.getInterface().add(port);

		for (PnmlLabel label : labelList) {
			label.convertToOpenNet(port);
		}
	}

	public PnmlPort convertFromOpenNet(OpenNetPort openNetPort,
			Map<Pair<AbstractGraphElement, ExpandableSubNet>, String> map) {
		name = new PnmlName(openNetPort.getLabel());
		id = openNetPort.getId();

		for (OpenNetLabel openNetLabel : openNetPort) {

			PnmlLabel label;

			if (openNetLabel.getType().equals(Type.SYNC)) {
				label = new PnmlLabel.Sync();
			} else if (openNetLabel.getType().equals(Type.ASYNC_INPUT)) {
				label = new PnmlLabel.Input();
			} else {//if (openNetLabel.getType().equals(Type.SYNC_WRITE)) {
				label = new PnmlLabel.Output();
			}

			label.convertFromOpenNet(openNetLabel, map);
			labelList.add(label);
		}

		return this;
	}
}
