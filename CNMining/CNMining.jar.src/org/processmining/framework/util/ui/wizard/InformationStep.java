package org.processmining.framework.util.ui.wizard;

import javax.swing.JComponent;

public abstract class InformationStep<M> implements ProMWizardStep<M> {

	private final String title;

	public InformationStep(final String title) {
		this.title = title;
	}

	@Override
	public M apply(final M model, final JComponent component) {
		return model;
	}

	@Override
	public boolean canApply(final M model, final JComponent component) {
		return true;
	}

	@Override
	public String getTitle() {
		return title;
	}

}
