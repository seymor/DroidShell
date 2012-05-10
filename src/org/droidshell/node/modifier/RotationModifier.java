package org.droidshell.node.modifier;

import org.droidshell.node.Node;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 08.04.2012
 */
public class RotationModifier implements iNodeModifier {

	@SuppressWarnings("unused")
	private static final String TAG = RotationModifier.class.getName();

	public float angleDeg;
	public long rotationTime = 0;

	public RotationModifier(float angleDeg) {
		this.angleDeg = angleDeg;

	}

	public void onUpdate(Node node, long gameTime) {
		rotationTime += gameTime;
		//node.modelMatrix.loadIdentity();
		node.onRotate(angleDeg * rotationTime/1000);
	}

}
