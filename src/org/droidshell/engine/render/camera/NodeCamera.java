package org.droidshell.engine.render.camera;

import org.droidshell.lang.math.Matrix;
import org.droidshell.lang.math.Vector2D;
import org.droidshell.lang.math.Vector3D;
import org.droidshell.node.Node;
import org.droidshell.screen.ScreenManager;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 08.04.2012
 */
public class NodeCamera extends Camera {

	private Vector3D oEye;
	private Vector3D oCenter;

	public Node node;
	public Vector2D offset;

	/**
	 * Creates a new Camera which is following a {@link org.droidshell.node.Node Node} with an offset
	 * 
	 * @param node
	 *            the node has to be followed
	 * @param offset
	 *            the offset value from the center
	 * @param eye
	 *            the position of the camera
	 * @param center
	 *            the position wherever the camera looks
	 * @param up
	 *            the orientation of the camera
	 * @param angle
	 *            the angle of view in degrees
	 * @param near
	 *            the near value of depth of field
	 * @param far
	 *            the far value of depth of field
	 */

	public NodeCamera(Node node, Vector2D offset, Vector3D eye,
			Vector3D center, Vector3D up, float angleDeg, float near, float far) {
		super(eye, center, up, angleDeg, near, far);
		this.node = node;
		this.offset = offset;

		oEye = eye.clone();
		oCenter = center.clone();
	}

	private void resetMatrices() {
		eye.x = oEye.x;
		eye.y = oEye.y;
		eye.z = oEye.z;

		center.x = oCenter.x;
		center.y = oCenter.y;
		center.z = oCenter.z;
	}
	
	@Override
	public Vector2D convertScreenToWorldCoordinates(float x, float y) {

		final float tx = x - ScreenManager.width / 2.0f;
		final float ty = y - ScreenManager.height / 2.0f;

		worldCoordinates.x = tx * (screenWidth / ScreenManager.width);
		worldCoordinates.y = ty * (screenHeight / ScreenManager.height) * (-1);
		
		Vector2D.add(worldCoordinates, worldCoordinates, center);
		
		return worldCoordinates;

	}

	/**
	 * Updates the camera with a delta time
	 * 
	 * @param gameTime
	 *            the elapsed time since last update
	 * 
	 */
	public void onUpdate(long gameTime) {
		resetMatrices();

		center.x += node.modelMatrix.m[8];
		eye.x += node.modelMatrix.m[8];
		eye.add(offset);
		center.add(offset);
		Matrix.lookAtMatrix(viewMatrix, eye, center, up);
		Matrix.multiply(viewProjMatrix, projMatrix, viewMatrix);
	}
}
