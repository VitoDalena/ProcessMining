package org.processmining.plugins.pnml.importing;

import java.io.InputStream;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.processmining.contexts.uitopia.annotations.UIImportPlugin;
import org.processmining.framework.abstractplugins.AbstractImportPlugin;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.models.graphbased.directed.petrinet.InhibitorNet;
import org.processmining.models.graphbased.directed.petrinet.PetrinetGraph;
import org.processmining.models.graphbased.directed.petrinet.impl.PetrinetFactory;
import org.processmining.models.semantics.petrinet.Marking;
import org.processmining.plugins.pnml.Pnml;

@Plugin(name = "Import Inhibitor net from PNML file", parameterLabels = { "Filename" }, returnLabels = {
		"Inhibitor Net", "Marking" }, returnTypes = { InhibitorNet.class, Marking.class })
@UIImportPlugin(description = "PNML Inhibitor net files", extensions = { "pnml" })
public class PnmlImportINet extends AbstractImportPlugin {

	protected FileFilter getFileFilter() {
		return new FileNameExtensionFilter("PNML files", "pnml");
	}

	protected Object importFromStream(PluginContext context, InputStream input, String filename, long fileSizeInBytes)
			throws Exception {
		PnmlImportUtils utils = new PnmlImportUtils();
		Pnml pnml = utils.importPnmlFromStream(context, input, filename, fileSizeInBytes);
		if (pnml == null) {
			/*
			 * No PNML found in file. Fail.
			 */
			return null;
		}
		/*
		 * PNML file has been imported. Now we need to convert the contents to
		 * an Inhibitor net.
		 */
		PetrinetGraph net = PetrinetFactory.newInhibitorNet(pnml.getLabel() + " (imported from " + filename + ")");

		return utils.connectNet(context, pnml, net);
	}
}
