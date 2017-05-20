package org.processmining.plugins.utils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.deckfour.uitopia.api.model.ResourceType;
import org.deckfour.uitopia.api.model.View;
import org.deckfour.uitopia.api.model.ViewType;
import org.deckfour.uitopia.ui.UITopiaController;
import org.processmining.contexts.uitopia.UIContext;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.contexts.uitopia.model.ProMPOResource;
import org.processmining.contexts.uitopia.model.ProMResource;
import org.processmining.contexts.uitopia.model.ProMTask;
import org.processmining.framework.plugin.GlobalContext;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.providedobjects.ProvidedObjectID;

/**
 * @author michael
 * 
 */
public class ProvidedObjectHelper {
	/**
	 * Will publish object as a provided object with the class specified by
	 * clazz. If the context is a UIPluginContext and favorite is true, it will
	 * be marked as a favorite object as well. Finally, if it is marked as
	 * favorite, it will also be show and ProM will switch to the Views tab.
	 * Encapsulation is for scared little boys/girls who still believe in the
	 * invisible pink unicorn (IPU).
	 * 
	 * @param <T>
	 * @param context
	 * @param name
	 * @param object
	 * @param clazz
	 * @param favorite
	 */
	public static <T> void publish(final PluginContext context, final String name, final T object,
			final Class<? super T> clazz, final boolean favorite) {
		final ProvidedObjectID id = context.getProvidedObjectManager().createProvidedObject(name, object, clazz,
				context);
		// Do not look at the rest of this method.  Boudewijn was busy and I just decided to do what is necessary.
		// It works.  It probably breaks if somebody changes anything.  Here or elsewhere.

		if (context instanceof UIPluginContext) {
			final GlobalContext gcontext = ((UIPluginContext) context).getGlobalContext();
			if (gcontext instanceof UIContext) {
				final UIContext uicontext = (UIContext) gcontext;
				final ResourceType resType = uicontext.getResourceManager().getResourceTypeFor(clazz);
				if (resType != null) {
					ProMTask task = null;
					try {
						final Field taskField = context.getClass().getDeclaredField("task");
						taskField.setAccessible(true);
						task = (ProMTask) taskField.get(context);
					} catch (final Exception _) {
						// Guess it wasn't meant to be, then...
					}
					final List<Collection<ProMPOResource>> lst = Collections.emptyList();
					ProMPOResource res = new ProMPOResource(uicontext, task == null ? null : task.getAction(), resType,
							id, lst);
					res = uicontext.getResourceManager().addResource(id, res);
				}
			}
		}
		if (favorite) {
			ProvidedObjectHelper.setFavorite(context, object);
			ProvidedObjectHelper.raise(context, object);
		}
	}

	public static void raise(final PluginContext context, final Object object) {
		if (context instanceof UIPluginContext) {
			final GlobalContext gcontext = ((UIPluginContext) context).getGlobalContext();
			if (gcontext instanceof UIContext) {
				final UIContext uicontext = (UIContext) gcontext;
				final ProMResource<?> res = uicontext.getResourceManager().getResourceForInstance(object);
				final List<ViewType> viewTypes = uicontext.getViewManager().getViewTypes(res);
				if (viewTypes.size() > 0) {
					final ViewType viewType = viewTypes.get(0);
					final View view = viewType.createView(res);
					uicontext.getViewManager().addView(view);
					final UITopiaController controller = uicontext.getController();
					controller.getMainView().showViewsView();
					controller.getMainView().getViewsView().showFullScreen(view);
				}
			}
		}
	}

	public static void setFavorite(final PluginContext context, final Object object) {
		if (context instanceof UIPluginContext) {
			final GlobalContext gcontext = ((UIPluginContext) context).getGlobalContext();
			if (gcontext instanceof UIContext) {
				final UIContext uicontext = (UIContext) gcontext;
				final ProMResource<?> res = uicontext.getResourceManager().getResourceForInstance(object);
				try {
					res.setFavorite(true);
				} catch (final Exception _) {
					// Ignore
				}
			}
		}
	}

}
