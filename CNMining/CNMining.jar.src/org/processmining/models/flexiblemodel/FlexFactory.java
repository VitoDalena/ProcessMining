package org.processmining.models.flexiblemodel;

/**
 * @author arya
 * @email arya.adriansyah@gmail.com
 * @version Nov 19, 2009
 */
public class FlexFactory {
	private FlexFactory() {
	}

	/**
	 * Create new flexible diagram
	 * 
	 * @param label
	 * @return
	 */
	public static Flex newFlex(String label) {
		return new FlexImpl(label);
	}

	/**
	 * Clone a flexible diagram
	 * 
	 * @param diagram
	 * @return
	 */
	public static Flex cloneFlex(Flex diagram) {
		FlexImpl newDiagram = new FlexImpl(diagram.getLabel());
		newDiagram.cloneFrom(diagram);
		return newDiagram;
	}
}
