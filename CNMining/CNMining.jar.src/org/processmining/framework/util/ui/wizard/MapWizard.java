package org.processmining.framework.util.ui.wizard;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class MapWizard<SettingsModel, Key> implements ProMWizard<SettingsModel, MapWizard.MapModel<SettingsModel, Key>> {
	
	protected Map<Key, ProMWizardStep<SettingsModel>> steps;
	
	public abstract Key getInitialKey(SettingsModel settings);
	public abstract Key getNextKey(MapModel<SettingsModel, Key> currentWizardModel);
	public abstract Collection<Key> getFinalKeys(MapModel<SettingsModel, Key> currentWizardModel);
	
	
	public boolean isFinished(MapModel<SettingsModel, Key> model) {
		return model.getPrevious() != null && getFinalKeys(model).contains(model.getPrevious());
	}
	
	public boolean isLastStep(MapModel<SettingsModel, Key> model) {
		return model.getCurrent() != null && getFinalKeys(model).contains(model.getCurrent());
	}

	protected MapWizard() {
		this.steps = new HashMap<Key, ProMWizardStep<SettingsModel>>();
	}
	
	public MapWizard(Map<Key,ProMWizardStep<SettingsModel>> steps) {
		this.steps = new HashMap<Key, ProMWizardStep<SettingsModel>>(steps);
	}
		
	public ProMWizardStep<SettingsModel> getFirst(MapModel<SettingsModel, Key> model) {
		return getNext(model, null);
	}

	public ProMWizardStep<SettingsModel> getNext(MapModel<SettingsModel, Key> model, ProMWizardStep<SettingsModel> current) {
		if (steps.containsKey(model.getCurrent())) {
			return steps.get(model.getCurrent());
		}
		return null;
	}


	public SettingsModel getModel(MapModel<SettingsModel, Key> wizardModel) {
		return wizardModel.getModel();
	}

	public MapModel<SettingsModel, Key> getWizardModel(SettingsModel model, MapModel<SettingsModel, Key> currentWizardModel) {
		if (currentWizardModel == null) {
			return new MapModel<SettingsModel, Key>(getInitialKey(model), model, null);
		} else {
			return new MapModel<SettingsModel, Key>(getNextKey(currentWizardModel), model, currentWizardModel.getCurrent());
		}
	}
	
	public static class MapModel<SettingsModel, Key> {
		
		private Key previous;
		private Key current;
		private SettingsModel settings;
		
		/**
		 * @return the current
		 */
		public Key getCurrent() {
			return current;
		}
		
		public Key getPrevious() {
			return previous;
		}
		
		public SettingsModel getModel() {
			return this.settings;
		}
		
		public MapModel(Key current, SettingsModel model, Key previous) {
			super();

			this.current = current;
			this.settings = model;
			this.previous = previous;
		}
	}

}
