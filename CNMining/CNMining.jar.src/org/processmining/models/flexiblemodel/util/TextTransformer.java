/**
 * 
 */
package org.processmining.models.flexiblemodel.util;

import java.util.Set;

import org.processmining.models.flexiblemodel.FlexNode;
import org.processmining.models.flexiblemodel.SetFlex;

/**
 * @author Arya Adriansyah
 * @email a.adriansyah@tue.nl
 * @version Apr 7, 2010
 * 
 */
public class TextTransformer {
	public static String getOutNodesLabel(FlexNode flexNode) {
		int counter = 0;
		int size = 0;

		StringBuffer strBuf = new StringBuffer();
		for (SetFlex outputSet : flexNode.getOutputNodes()) {
			counter = 0;
			size = outputSet.size();
			strBuf.append("{");
			for (FlexNode out : outputSet) {
				counter++;
				strBuf.append(out.getLabel());
				if (counter < size) {
					strBuf.append(",");
				}
			}
			strBuf.append("}");
		}

		return strBuf.toString();
	}

	public static String getInNodesLabel(FlexNode flexNode) {
		int counter = 0;
		int size = 0;

		StringBuffer strBuf = new StringBuffer();
		for (Set<FlexNode> inputSet : flexNode.getInputNodes()) {
			counter = 0;
			size = inputSet.size();
			strBuf.append("{");
			for (FlexNode in : inputSet) {
				counter++;
				strBuf.append(in.getLabel());
				if (counter < size) {
					strBuf.append(",");
				}
			}
			strBuf.append("}");
		}

		return strBuf.toString();

	}
}
