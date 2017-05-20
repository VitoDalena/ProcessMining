package org.processmining.framework.util.ui.wizard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListWizard<SettingsModel> implements ProMWizard<SettingsModel, ListWizard.ListModel<SettingsModel>> {

	static class ListModel<SettingsModel> {
		private int step = 0;

		private final SettingsModel model;

		public ListModel(final int step, final SettingsModel model) {
			super();
			this.step = step;
			this.model = model;
		}

		public SettingsModel getModel() {
			return model;
		}

		public int getStep() {
			return step;
		}

	}

	private final List<ProMWizardStep<SettingsModel>> steps;

	public ListWizard(final List<ProMWizardStep<SettingsModel>> steps) {
		this.steps = new ArrayList<ProMWizardStep<SettingsModel>>(steps);
	}

	public ListWizard(final ProMWizardStep<SettingsModel>... steps) {
		this.steps = new ArrayList<ProMWizardStep<SettingsModel>>(Arrays.asList(steps));
	}

	@Override
	public ProMWizardStep<SettingsModel> getFirst(final ListModel<SettingsModel> model) {
		return getNext(model, null);
	}

	@Override
	public SettingsModel getModel(final ListModel<SettingsModel> wizardModel) {
		return wizardModel.getModel();
	}

	@Override
	public ProMWizardStep<SettingsModel> getNext(final ListModel<SettingsModel> model,
			final ProMWizardStep<SettingsModel> current) {

		if (steps.size() > model.getStep())
			return steps.get(model.getStep());

		return null;
	}

	@Override
	public ListModel<SettingsModel> getWizardModel(final SettingsModel model,
			final ListModel<SettingsModel> currentWizardModel) {
		if (currentWizardModel == null)
			return new ListModel<SettingsModel>(0, model);
		else
			return new ListModel<SettingsModel>(currentWizardModel.getStep() + 1, model);
	}

	@Override
	public boolean isFinished(final ListModel<SettingsModel> model) {

		return model.getStep() >= steps.size();

	}

	@Override
	public boolean isLastStep(final ListModel<SettingsModel> model) {
		return model.getStep() >= steps.size() - 1;
	}

}
