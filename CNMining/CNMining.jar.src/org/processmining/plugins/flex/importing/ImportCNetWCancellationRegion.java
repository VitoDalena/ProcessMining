/**
 * 
 */
package org.processmining.plugins.flex.importing;

import java.io.InputStream;

import org.processmining.contexts.uitopia.annotations.UIImportPlugin;
import org.processmining.framework.abstractplugins.AbstractImportPlugin;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.models.connections.flexiblemodel.FlexCancellationRegionConnection;
import org.processmining.models.connections.flexiblemodel.FlexEndTaskNodeConnection;
import org.processmining.models.connections.flexiblemodel.FlexStartTaskNodeConnection;
import org.processmining.models.flexiblemodel.CancellationRegion;
import org.processmining.models.flexiblemodel.EndTaskNodesSet;
import org.processmining.models.flexiblemodel.Flex;
import org.processmining.models.flexiblemodel.StartTaskNodesSet;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

/**
 * @author aadrians
 *
 */
@Plugin(name = "Open CNet file", parameterLabels = { "Filename" }, returnLabels = { "Causal net", "Start task node", "End task node", "Cancellation region" }, returnTypes = {
		Flex.class, StartTaskNodesSet.class, EndTaskNodesSet.class, CancellationRegion.class })
@UIImportPlugin(description = "Causal Net file with cancellation region", extensions = { "cnetc" })
public class ImportCNetWCancellationRegion extends AbstractImportPlugin {

	protected Object importFromStream(PluginContext context, InputStream input, String filename, long fileSizeInBytes)
			throws Exception {
		context.log("CNet import started.");

		// start parsing
		/*
		 * Get an XML pull parser.
		 */
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		factory.setNamespaceAware(true);
		XmlPullParser xpp = factory.newPullParser();
		/*
		 * Initialize the parser on the provided input.
		 */
		xpp.setInput(input, null);
		/*
		 * Get the first event type.
		 */
		int eventType = xpp.getEventType();
		/*
		 * Skip whatever we find until we've found a start tag.
		 */
		while (eventType != XmlPullParser.START_TAG) {
			eventType = xpp.next();
		}
		
		CNetImporter importer = new CNetImporter();
		/*
		 * Check whether start tag corresponds to CNet start tag.
		 */
		if (xpp.getName().equals(CNetImporter.STARTTAG)) {
			/*
			 * Yes it does. Import the PNML element.
			 */
			importer.importElement(xpp);
		} else {
			/*
			 * No it does not. Return null to signal failure.
			 */
			context.log("no start tag is found");
			return null;
		}

		context.log("CNet Import finished.");
		
		// rename results
		Object[] result = importer.getImportResult();
		
		Flex flex = (Flex) result[0];
		StartTaskNodesSet startTaskNodesSet = (StartTaskNodesSet) result[1];
		EndTaskNodesSet endTaskNodesSet = (EndTaskNodesSet) result[2];
		CancellationRegion cancellationRegion = (CancellationRegion) result[3];
		
		context.getFutureResult(0).setLabel(flex.getLabel());
		context.getFutureResult(1).setLabel("Start task node of " + flex.getLabel());
		context.getFutureResult(2).setLabel("End task node of " + flex.getLabel());
		context.getFutureResult(3).setLabel("Cancelation region of " + flex.getLabel());

		// add connection
		context.addConnection(new FlexStartTaskNodeConnection("Start task of " + flex.getLabel(), flex, startTaskNodesSet));
		context.addConnection(new FlexEndTaskNodeConnection("End task of " + flex.getLabel(), flex, endTaskNodesSet));
		context.addConnection(new FlexCancellationRegionConnection("Cancellation region of " + flex.getLabel(), flex, cancellationRegion));
		
		return new Object[] { flex, startTaskNodesSet, endTaskNodesSet, cancellationRegion };
	}
	
}
