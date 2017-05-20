package org.processmining.plugins.pnml;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.processmining.framework.util.Pair;
import org.processmining.models.connections.GraphLayoutConnection;
import org.processmining.models.graphbased.AbstractGraphElement;
import org.processmining.models.graphbased.directed.opennet.OpenNet;
import org.processmining.models.graphbased.directed.opennet.OpenNetLabel;
import org.processmining.models.graphbased.directed.opennet.OpenNetLabel.Type;
import org.processmining.models.graphbased.directed.petrinet.PetrinetGraph;
import org.processmining.models.graphbased.directed.petrinet.elements.ExpandableSubNet;
import org.processmining.models.graphbased.directed.petrinet.elements.Transition;
import org.processmining.plugins.pnml.extensions.opennet.PnmlLabelConnection;
import org.xmlpull.v1.XmlPullParser;

/**
 * Basic PNML transition object.
 * 
 * @author hverbeek
 */
public class PnmlTransition extends PnmlNode {

	/**
	 * PNML transition tag.
	 */
	public final static String TAG = "transition";

	/*
	 * Open net extension: receive edges, send edges, and synchronize edges.
	 */
	private final List<PnmlLabelConnection.Receive> receiveList;
	private final List<PnmlLabelConnection.Send> sendList;
	private final List<PnmlLabelConnection.Sync> syncList;

	/**
	 * Creates a fresh PNML transition.
	 */
	public PnmlTransition() {
		super(TAG);

		receiveList = new ArrayList<PnmlLabelConnection.Receive>();
		sendList = new ArrayList<PnmlLabelConnection.Send>();
		syncList = new ArrayList<PnmlLabelConnection.Sync>();
	}

	protected boolean importElements(XmlPullParser xpp, Pnml pnml) {
		if (super.importElements(xpp, pnml)) {
			/*
			 * Start tag corresponds to a known child element of a PNML
			 * annotation.
			 */
			return true;
		}
		/*
		 * If Open net, then handle Open net edges.
		 */
		if (pnml.hasModule()) {
			if (xpp.getName().equals(PnmlLabelConnection.Receive.TAG)) {
				PnmlLabelConnection.Receive receive = new PnmlLabelConnection.Receive();
				receive.importElement(xpp, pnml);
				receiveList.add(receive);
				return true;
			}
			if (xpp.getName().equals(PnmlLabelConnection.Send.TAG)) {
				PnmlLabelConnection.Send send = new PnmlLabelConnection.Send();
				send.importElement(xpp, pnml);
				sendList.add(send);
				return true;
			}
			if (xpp.getName().equals(PnmlLabelConnection.Sync.TAG)) {
				PnmlLabelConnection.Sync sync = new PnmlLabelConnection.Sync();
				sync.importElement(xpp, pnml);
				syncList.add(sync);
				return true;
			}
		}
		return false;
	}

	protected String exportElements(Pnml pnml) {
		String s = super.exportElements(pnml);
		/*
		 * Check for any Open net edges and handle them properly.
		 */
		for (PnmlLabelConnection.Receive receive : receiveList) {
			s += receive.exportElement(pnml);
		}
		for (PnmlLabelConnection.Send send : sendList) {
			s += send.exportElement(pnml);
		}
		for (PnmlLabelConnection.Sync sync : syncList) {
			s += sync.exportElement(pnml);
		}
		return s;
	}

	/**
	 * Converts this transition to a Petri net transition.
	 * 
	 * @param net
	 *            The net to add the transition to.
	 * @param subNet
	 *            The sub net to add the transition to.
	 * @param map
	 *            The transitions found so far.
	 */
	public void convertToNet(PetrinetGraph net, ExpandableSubNet subNet, Map<String, Transition> map,
			Point2D.Double displacement, GraphLayoutConnection layout) {
		/*
		 * Add the transition to the net.
		 */
		Transition transition = net.addTransition((((name != null) && (name.text != null)) ? name.text.getText() : id),
				subNet);
		super.convertToNet(subNet, transition, displacement, layout);
		/*
		 * Register new transition found.
		 */
		map.put(id, transition);

		/*
		 * If Open net, then handle Open net edges.
		 */

		if (net instanceof OpenNet) {
			OpenNet onet = (OpenNet) net;
			for (PnmlLabelConnection.Receive receive : receiveList) {
				receive.convertToOpenNet(onet, transition);
			}
			for (PnmlLabelConnection.Send send : sendList) {
				send.convertToOpenNet(onet, transition);
			}
			for (PnmlLabelConnection.Sync sync : syncList) {
				sync.convertToOpenNet(onet, transition);
			}
		}
	}

	public PnmlTransition convertFromNet(PetrinetGraph net, ExpandableSubNet parent, Transition element,
			Map<Pair<AbstractGraphElement, ExpandableSubNet>, String> idMap, GraphLayoutConnection layout) {
		super.convertFromNet(parent, element, idMap, layout);

		/*
		 * Check for any Open net edges and handle them properly.
		 */
		if (net instanceof OpenNet) {
			OpenNet oNet = (OpenNet) net;
			for (OpenNetLabel label : oNet.getLabelsFor(element)) {
				if (label.getType().equals(Type.ASYNC_INPUT)) {
					PnmlLabelConnection.Receive receive = new PnmlLabelConnection.Receive();
					receive.convertFromOpenNet(label, idMap);
					receiveList.add(receive);
				} else if (label.getType().equals(Type.ASYNC_OUTPUT)) {
					PnmlLabelConnection.Send send = new PnmlLabelConnection.Send();
					send.convertFromOpenNet(label, idMap);
					sendList.add(send);
				} else if (label.getType().equals(Type.SYNC)) {
					PnmlLabelConnection.Sync s = new PnmlLabelConnection.Sync();
					s.convertFromOpenNet(label, idMap);
					syncList.add(s);
				}

			}
		}
		return this;
	}
}
