package org.processmining.framework.util.ui.wizard;

public interface ProMWizard<SettingsModel, WizardModel> {

	public ProMWizardStep<SettingsModel> getFirst(WizardModel model);

	public SettingsModel getModel(WizardModel wizardModel);

	public ProMWizardStep<SettingsModel> getNext(WizardModel model, ProMWizardStep<SettingsModel> current);

	public WizardModel getWizardModel(SettingsModel model, WizardModel currentWizardModel);

	//we do not have canFinish, as the ProM Wizard interface only 
	//shows the finish button if it is the last step
	//public boolean canFinish(WizardModel model);
	public boolean isFinished(WizardModel model);

	public boolean isLastStep(WizardModel model);

}
