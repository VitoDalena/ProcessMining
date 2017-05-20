package org.processmining.framework.util.ui.wizard;

import javax.swing.JComponent;

public interface ProMWizardStep<SettingsModel> {

	public SettingsModel apply(SettingsModel model, JComponent component);

	public boolean canApply(SettingsModel model, JComponent component);

	public JComponent getComponent(SettingsModel model);

	public String getTitle();
}
