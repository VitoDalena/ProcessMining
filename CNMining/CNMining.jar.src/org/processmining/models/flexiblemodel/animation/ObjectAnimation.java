/**
 * 
 */
package org.processmining.models.flexiblemodel.animation;

import java.util.List;

/**
 * @author aadrians
 * 
 */
public class ObjectAnimation {
	private Object referenceObject = null;
	private List<KeyFrame> keyFrameList = null;

	public ObjectAnimation(Object referenceObject) {
		this.referenceObject = referenceObject;
	}

	public Object getReferenceObject() {
		return referenceObject;
	}

	public void setReferenceObject(Object referenceObject) {
		this.referenceObject = referenceObject;
	}

	public List<KeyFrame> getKeyFrameList() {
		return keyFrameList;
	}

	public void setKeyFrameList(List<KeyFrame> keyFrameList) {
		this.keyFrameList = keyFrameList;
	}

	public boolean addKeyFrame(KeyFrame keyFrame) {
		return keyFrameList.add(keyFrame);
	}

	public boolean removeKeyFrame(KeyFrame keyFrame) {
		return keyFrameList.remove(keyFrame);
	}
}
