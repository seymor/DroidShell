package org.droidshell.node.modifier;

import org.droidshell.lang.math.Vector2D;
import org.droidshell.node.Node;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 08.04.2012
 */
public class VelocityModifier implements iNodeModifier {

	@SuppressWarnings("unused")
	private static final String TAG = VelocityModifier.class.getName();
	
	private Vector2D displacement;

	public Vector2D velocity;
	public long movingTime = 0;

	public VelocityModifier(Vector2D velocity) {
		this.velocity = velocity;

		displacement = new Vector2D();
	}

	public void onUpdate(Node node, long gameTime) {
		movingTime += gameTime;
		Vector2D.multiply(displacement, velocity, movingTime);
		node.onTranslate(displacement.x, displacement.y);
	}

}
