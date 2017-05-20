package org.processmining.plugins.pnml;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.deckfour.xes.extension.XExtension;
import org.deckfour.xes.extension.std.XConceptExtension;
import org.deckfour.xes.extension.std.XOrganizationalExtension;
import org.deckfour.xes.factory.XFactory;
import org.deckfour.xes.factory.XFactoryRegistry;
import org.deckfour.xes.model.XAttributeMap;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;
import org.deckfour.xes.model.impl.XAttributeMapImpl;
import org.processmining.framework.util.Pair;
import org.processmining.models.connections.GraphLayoutConnection;
import org.processmining.models.graphbased.AbstractGraphElement;
import org.processmining.models.graphbased.directed.opennet.OpenNet;
import org.processmining.models.graphbased.directed.petrinet.PetrinetEdge;
import org.processmining.models.graphbased.directed.petrinet.PetrinetGraph;
import org.processmining.models.graphbased.directed.petrinet.PetrinetNode;
import org.processmining.models.graphbased.directed.petrinet.configurable.impl.ConfigurableResetInhibitorNet;
import org.processmining.models.graphbased.directed.petrinet.elements.ExpandableSubNet;
import org.processmining.models.graphbased.directed.petrinet.elements.Place;
import org.processmining.models.graphbased.directed.petrinet.elements.Transition;
import org.processmining.models.semantics.petrinet.Marking;
import org.processmining.plugins.pnml.extensions.configurations.PnmlConfiguration;
import org.processmining.plugins.pnml.extensions.opennet.PnmlModule;
import org.xmlpull.v1.XmlPullParser;

/**
 * Basic (E)PNML object. Allows import, export, and conversion to a Petri net.
 * 
 * @author hverbeek
 * 
 */
public class Pnml extends PnmlElement {

	/**
	 * PNML tag.
	 */
	public final static String TAG = "pnml";

	public final static int PHASE_NODES = 0;
	public final static int PHASE_DEREFNODES = 1;
	public final static int PHASE_REFNODES = 2;
	public final static int PHASE_ARCS = 3;

	/**
	 * Type of PNML: either PNML or EPNML. In EPNML, there are no pages, and
	 * some other subtle differences. The contained URI determines whether the
	 * type is PNML or EPNML.
	 */
	public enum PnmlType {
		PNML, EPNML, LOLA;
	}

	private PnmlType type;

	/**
	 * net elements.
	 */
	private List<PnmlNet> netList;
	private PnmlModule module;
	private PnmlConfiguration configuration;

	private XLog log;
	private XTrace trace;
	private XFactory factory;
	private XExtension conceptExtension;
	private XExtension organizationalExtension;

	boolean hasErrors;

	/**
	 * Creates a fresh PNML object, given the type.
	 * 
	 * @param type
	 *            Either PNML or EPNML.
	 */
	public Pnml(PnmlType type) {
		super(TAG);
		this.type = type;
		netList = new ArrayList<PnmlNet>();
		module = null;
		configuration = null;

		initializeLog();
	}

	/**
	 * Creates a fresh default PNML object, that is, a PNML object of type PNML.
	 */
	public Pnml() {
		super(TAG);
		type = PnmlType.PNML;
		netList = new ArrayList<PnmlNet>();
		module = null;
		configuration = null;

		initializeLog();
	}

	public boolean hasModule() {
		return module != null;
	}

	/**
	 * Creates and initializes a log to throw to the framework when importing
	 * the PNML file fails. In this log, every net will have its own trace. The
	 * first net is preceded by a preamble.
	 */
	private void initializeLog() {
		factory = XFactoryRegistry.instance().currentDefault();
		conceptExtension = XConceptExtension.instance();
		organizationalExtension = XOrganizationalExtension.instance();
		log = factory.createLog();
		log.getExtensions().add(conceptExtension);
		log.getExtensions().add(organizationalExtension);

		logNet("<preamble>");

		hasErrors = false;
	}

	public XLog getLog() {
		return log;
	}

	/**
	 * Adds a log event to the current trace in the log.
	 * 
	 * @param context
	 *            Context of the message, typically the current PNML tag.
	 * @param lineNumber
	 *            Current line number.
	 * @param message
	 *            Error message.
	 */
	public void log(String context, int lineNumber, String message) {
		XAttributeMap attributeMap = new XAttributeMapImpl();
		attributeMap.put(XConceptExtension.KEY_NAME,
				factory.createAttributeLiteral(XConceptExtension.KEY_NAME, message, conceptExtension));
		attributeMap.put(XConceptExtension.KEY_INSTANCE,
				factory.createAttributeLiteral(XConceptExtension.KEY_INSTANCE, context, conceptExtension));
		attributeMap.put(XOrganizationalExtension.KEY_RESOURCE, factory.createAttributeLiteral(
				XOrganizationalExtension.KEY_RESOURCE, "Line " + lineNumber, organizationalExtension));
		XEvent event = factory.createEvent(attributeMap);
		trace.add(event);
		hasErrors = true;
	}

	/**
	 * Adds a new trace with the given name to the log. This trace is now
	 * current.
	 * 
	 * @param name
	 *            The give name.
	 */
	public void logNet(String name) {
		trace = factory.createTrace();
		log.add(trace);
		trace.getAttributes().put(XConceptExtension.KEY_NAME,
				factory.createAttributeLiteral(XConceptExtension.KEY_NAME, name, conceptExtension));
	}

	public boolean hasErrors() {
		return hasErrors;
	}

	/**
	 * Set the type of this PNML object.
	 * 
	 * @param type
	 *            Either PNML or EPNML.
	 */
	public void setType(PnmlType type) {
		this.type = type;
	}

	/**
	 * Gets the type.
	 * 
	 * @return Either PNML or EPNML.
	 */
	public PnmlType getType() {
		return type;
	}

	/**
	 * Checks whether the current start tag is known. If known, it imports the
	 * corresponding child element and returns true. Otherwise, it returns
	 * false.
	 * 
	 * @return Whether the start tag was known.
	 */
	protected boolean importElements(XmlPullParser xpp, Pnml pnml) {
		if (super.importElements(xpp, pnml)) {
			return true;
		}
		if (xpp.getName().equals(PnmlNet.TAG)) {
			/*
			 * Found net element. Create a net object and import the net
			 * element.
			 */
			PnmlNet net = new PnmlNet();
			net.importElement(xpp, pnml);
			netList.add(net);
			return true;
		}
		if (xpp.getName().equals(PnmlModule.TAG)) {
			/*
			 * Found module element. Should be Open net. Treat accordingly.
			 */
			type = PnmlType.LOLA;
			module = new PnmlModule();
			module.importElement(xpp, pnml);
			return true;
		}
		if (xpp.getName().equals(PnmlConfiguration.TAG)) {
			/*
			 * Found group element. Should be configurable RI net net. Treat
			 * accordingly.
			 */
			configuration = new PnmlConfiguration();
			configuration.importElement(xpp, pnml);
			return true;
		}
		return false;
	}

	/**
	 * Exports the child elements to String.
	 */
	protected String exportElements(Pnml pnml) {
		String s = super.exportElements(pnml);
		for (PnmlNet net : netList) {
			s += net.exportElement(pnml);
		}
		if ((type == PnmlType.LOLA) && (module != null)) {
			s += module.exportElement(pnml);
		}
		if (configuration != null) {
			s += configuration.exportElement(pnml);
		}
		return s;
	}

	/**
	 * Converts the PNML object to a Petri net and initial marking.
	 * 
	 * @param net
	 *            Where to store the Petri net in (should be a fresh net).
	 * @param marking
	 *            Where to store the initial marking in (should be a fresh
	 *            marking).
	 */
	public void convertToNet(PetrinetGraph net, Marking marking, GraphLayoutConnection layout) {
		if (net instanceof OpenNet) {
			if (module != null) {
				module.convertToOpenNet((OpenNet) net, marking, this, layout);
			}
		} else {
			if (!netList.isEmpty()) {
				Map<String, Place> placeMap = new HashMap<String, Place>();
				Map<String, Transition> transitionMap = new HashMap<String, Transition>();
				Map<String, PetrinetEdge<? extends PetrinetNode, ? extends PetrinetNode>> edgeMap = new HashMap<String, PetrinetEdge<?, ?>>();
				netList.get(0).convertToNet(net, marking, placeMap, transitionMap, edgeMap, layout);
				setLayout(net, layout);
				if (net instanceof ConfigurableResetInhibitorNet && configuration != null) {
					configuration.convertToNet(net, placeMap, transitionMap, edgeMap);
				}
			}
		}
	}

	public void setLayout(PetrinetGraph net, GraphLayoutConnection layout) {
		boolean doLayout = false;
		/*
		 * If any node has no position, then we need to layout the graph.
		 */
		for (PetrinetNode node : net.getNodes()) {
			if (layout.getPosition(node) == null) {
				doLayout = true;
			}
		}
		if (!doLayout) {
			/*
			 * All nodes have position (10.0,10.0) (which is the default
			 * position) we need to layout as well.
			 */
			doLayout = true;
			for (PetrinetNode node : net.getNodes()) {
				Point2D position = layout.getPosition(node);
				if ((position.getX() != 10.0) || (position.getY() != 10.0)) {
					doLayout = false;
				}
			}
		}
		layout.setLayedOut(!doLayout);
	}

	public String getLabel() {
		if (module != null) {
			return module.getName("Unlabeled net");
		} else if (!netList.isEmpty()) {
			return netList.get(0).getName("Unlabeled net");
		}
		return "Empty net";
	}

	public Pnml convertFromNet(Map<PetrinetGraph, Marking> markedNets, GraphLayoutConnection layout) {
		netList = new ArrayList<PnmlNet>();
		int netCtr = 1;
		Map<Pair<AbstractGraphElement, ExpandableSubNet>, String> map = new HashMap<Pair<AbstractGraphElement, ExpandableSubNet>, String>();
		for (PetrinetGraph net : markedNets.keySet()) {

			netList.add(new PnmlNet().convertFromNet(net, markedNets.get(net), map, netCtr++, layout));
		}
		Map<AbstractGraphElement, String> idMap = new HashMap<AbstractGraphElement, String>();
		for (Pair<AbstractGraphElement, ExpandableSubNet> pair : map.keySet()) {
			idMap.put(pair.getFirst(), map.get(pair));
		}
		if (markedNets.keySet().size() == 1
				&& markedNets.keySet().iterator().next() instanceof ConfigurableResetInhibitorNet) {
			ConfigurableResetInhibitorNet configurableNet = (ConfigurableResetInhibitorNet) markedNets.keySet()
					.iterator().next();
			configuration = new PnmlConfiguration();
			configuration.convertFromNet(configurableNet, idMap);
		}
		return this;
	}

	public Pnml convertFromNet(PetrinetGraph net, Marking marking, GraphLayoutConnection layout) {
		Map<String, AbstractGraphElement> idMap = new HashMap<String, AbstractGraphElement>();
		return convertFromNet(net, marking, idMap, layout);
	}

	public Pnml convertFromNet(PetrinetGraph net, Marking marking, Map<String, AbstractGraphElement> idMap,
			GraphLayoutConnection layout) {
		if (net instanceof OpenNet) {
			module = new PnmlModule();
			module.convertFromOpenNet((OpenNet) net, marking, idMap, layout);
		} else {
			Map<Pair<AbstractGraphElement, ExpandableSubNet>, String> map = new HashMap<Pair<AbstractGraphElement, ExpandableSubNet>, String>();
			netList.add(new PnmlNet().convertFromNet(net, marking, map, 1, idMap, layout));
			Map<AbstractGraphElement, String> map2 = new HashMap<AbstractGraphElement, String>();
			for (Pair<AbstractGraphElement, ExpandableSubNet> pair : map.keySet()) {
				map2.put(pair.getFirst(), map.get(pair));
			}
			if (net instanceof ConfigurableResetInhibitorNet) {
				ConfigurableResetInhibitorNet configurableNet = (ConfigurableResetInhibitorNet) net;
				configuration = new PnmlConfiguration();
				configuration.convertFromNet(configurableNet, map2);
			}
		}
		return this;
	}
}
