/**
 * 
 */
package org.processmining.models.flexiblemodel.animation;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.processmining.framework.util.Pair;
import org.processmining.models.flexiblemodel.FlexEdge;
import org.processmining.models.flexiblemodel.FlexNode;

/**
 * @author aadrians
 * 
 */
public class GraphAnimation {
	private final Map<FlexNode, List<KeyFrame>> nodeAnimation;
	private final Map<FlexEdge<? extends FlexNode, ? extends FlexNode>, List<ArcKeyFrame>> arcAnimation;

	public GraphAnimation() {
		nodeAnimation = new HashMap<FlexNode, List<KeyFrame>>();
		arcAnimation = new HashMap<FlexEdge<? extends FlexNode, ? extends FlexNode>, List<ArcKeyFrame>>();
	}

	public List<KeyFrame> getNodeAnimation(FlexNode node) {
		return nodeAnimation.get(node);
	}

	public List<ArcKeyFrame> getArcAnimation(FlexEdge<? extends FlexNode, ? extends FlexNode> edge) {
		return arcAnimation.get(edge); 
	}

	public Object[] getAnimationAt(Date time) {
		// select keyframe for node
		Map<FlexNode, KeyFrame> nodeResult = new HashMap<FlexNode, KeyFrame>();
		for (FlexNode node : nodeAnimation.keySet()) {
			// find closest predecessor 
			List<KeyFrame> tempNodeListKeyframe = nodeAnimation.get(node);
			int i = 0;
			while (i < tempNodeListKeyframe.size()) {
				if (tempNodeListKeyframe.get(i).getStartTime().after(time)) {
					nodeResult.put(node, tempNodeListKeyframe.get(i - 1));
					break;
				} else if (tempNodeListKeyframe.get(i).getStartTime().equals(time)) {
					nodeResult.put(node, tempNodeListKeyframe.get(i));
					break;
				}
				i++;
			}
		}

		// select keyframe for edge
		Map<FlexEdge<? extends FlexNode, ? extends FlexNode>, ArcKeyFrame> edgeResult = new HashMap<FlexEdge<? extends FlexNode, ? extends FlexNode>, ArcKeyFrame>();
		for (FlexEdge<? extends FlexNode, ? extends FlexNode> edge : arcAnimation.keySet()) {
			// find closest predecessor
			List<ArcKeyFrame> tempArcKeyFrame = arcAnimation.get(edge);
			int i = 0;
			while (i < tempArcKeyFrame.size()) {
				if (tempArcKeyFrame.get(i).getStartTime().after(time)) {
					edgeResult.put(edge, tempArcKeyFrame.get(i - 1));
					break;
				} else if (tempArcKeyFrame.get(i).getStartTime().equals(time)) {
					edgeResult.put(edge, tempArcKeyFrame.get(i));
					break;
				}
				i++;
			}
		}

		return new Object[] { nodeResult, edgeResult };
	}

	public void addNodeKeyFrame(FlexNode node, Date from) {
		List<KeyFrame> currListKeyframe;
		if (nodeAnimation.containsKey(node)) {
			// get the old node animation list
			currListKeyframe = nodeAnimation.get(node);
		} else {
			// create new list of keyframe
			currListKeyframe = new LinkedList<KeyFrame>();
		}

		// find the correct location to put/update keyframe
		int i = 0;
		while (i < currListKeyframe.size()) {
			if (currListKeyframe.get(i).getStartTime().after(from)) {
				// insert keyframe as new element in element i
				KeyFrame newKeyFrame = new KeyFrame(1, from);
				currListKeyframe.add(i, newKeyFrame);
				break;
			} else if (currListKeyframe.get(i).getStartTime().equals(from)) {
				// insert in the same keyframe
				currListKeyframe.get(i).incrementNumOfActivation();
				break;
			}
			i++;
		}
		if (i == currListKeyframe.size()) {
			// need to create a new keyframe
			KeyFrame newKeyFrame = new KeyFrame(1, from);
			currListKeyframe.add(newKeyFrame);
		}

		// put back listkeyframe
		nodeAnimation.put(node, currListKeyframe);
	}

	public void addArcKeyFrame(FlexEdge<? extends FlexNode, ? extends FlexNode> edge, Date from, Date to) {
		List<ArcKeyFrame> currListArcKeyframe;
		if (arcAnimation.containsKey(edge)) {
			// get the old node animation list
			currListArcKeyframe = arcAnimation.get(edge);
		} else {
			// create new list of arckeyframe
			currListArcKeyframe = new LinkedList<ArcKeyFrame>();
		}

		// find the correct location to put/update keyframe
		int i = 0;

		// set accumulator for animations that are still 'active'
		List<Pair<Date, Date>> activeAnimations = new LinkedList<Pair<Date, Date>>();
		while (i < currListArcKeyframe.size()) {
			ArcKeyFrame arcKeyFrameUndInspection = currListArcKeyframe.get(i);
			if (arcKeyFrameUndInspection.getStartTime().before(from)) {
				// insert new keyframe as new element in element i
				ArcKeyFrame newArcKeyFrame = new ArcKeyFrame(1, from, to);

				// also add 'active' arc animation
				newArcKeyFrame.addActiveAnimations(activeAnimations);

				// add it as a complete keyframe
				currListArcKeyframe.add(i, newArcKeyFrame);
				break;
			} else if (arcKeyFrameUndInspection.getStartTime().equals(from)) {
				// insert in the same keyframe
				arcKeyFrameUndInspection.addTimePair(from, to);
				break;
			} else {
				// currListArcKeyframe.get(i).getTime().after(from)
				// check if this animation is still active at currListArcKeyframe.get(i).getTime() 
				if (to.before(arcKeyFrameUndInspection.getLatestEndTime())) {
					// this animation is still active
					arcKeyFrameUndInspection.addTimePair(from, to);
				}
			}
			i++;
		}
		if (i == currListArcKeyframe.size()) {
			// need to create a new keyframe
			ArcKeyFrame newArcKeyFrame = new ArcKeyFrame(1, from, to);
			currListArcKeyframe.add(newArcKeyFrame);

			// also add 'active' arc animation
		}

		// put back listarckeyframe
		arcAnimation.put(edge, currListArcKeyframe);
	}
}
