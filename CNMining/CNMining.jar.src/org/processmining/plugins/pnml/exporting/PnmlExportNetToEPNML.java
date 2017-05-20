package org.processmining.plugins.pnml.exporting;

import java.io.File;
import java.io.IOException;

import org.processmining.contexts.uitopia.annotations.UIExportPlugin;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.framework.plugin.annotations.PluginVariant;
import org.processmining.models.graphbased.directed.opennet.OpenNet;
import org.processmining.models.graphbased.directed.petrinet.Petrinet;
import org.processmining.plugins.pnml.Pnml;

@Plugin(name = "EPNML export (Petri net)", returnLabels = {}, returnTypes = {}, parameterLabels = { "Petri net",
		"Open net", "File" }, userAccessible = true)
@UIExportPlugin(description = "EPNML files", extension = "pnml")
public class PnmlExportNetToEPNML extends PnmlExportNet {

	@PluginVariant(variantLabel = "EPNML export (Petri net)", requiredParameterLabels = { 0, 2 })
	public void exportPetriNetToEPNMLFile(PluginContext context, Petrinet net, File file) throws IOException {
		exportPetriNetToPNMLOrEPNMLFile(context, net, file, Pnml.PnmlType.EPNML);
	}

	@PluginVariant(variantLabel = "EPNML export (Open net)", requiredParameterLabels = { 1, 2 })
	public void exportPetriNetToEPNMLFile(PluginContext context, OpenNet openNet, File file) throws IOException {
		exportPetriNetToPNMLOrEPNMLFile(context, openNet, file, Pnml.PnmlType.EPNML);
	}

}
