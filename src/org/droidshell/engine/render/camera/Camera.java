package org.droidshell.engine.render.camera;

import org.droidshell.lang.math.Math;
import org.droidshell.lang.math.Matrix;
import org.droidshell.lang.math.Vector2D;
import org.droidshell.lang.math.Vector3D;
import org.droidshell.node.rectangle.Rectangle;
import org.droidshell.node.sprite.Sprite;
import org.droidshell.screen.ScreenManager;

import android.util.Log;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public class Camera {

	private static final String TAG = Camera.class.getName();

	protected Vector2D worldCoordinates;

	public float screenWidth;
	public float screenHeight;

	public Vector3D eye;
	public Vector3D center;
	public Vector3D up;
	public float angle;

	public float ratio;
	public Matrix projMatrix;
	public Matrix viewMatrix;
	public Matrix viewProjMatrix;

	/**
	 * Creates a new Camera with the eye arguments
	 * 
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

	public Camera(Vector3D eye, Vector3D center, Vector3D up, float angle,
			float near, float far) {

		this.eye = eye;
		this.center = center;
		this.up = up;
		this.angle = angle;

		projMatrix = new Matrix();
		viewMatrix = new Matrix();
		viewProjMatrix = new Matrix();

		if (Float.compare(near, 0) == 0) {
			near = 0.01f;
			Log.e(TAG, "Bad near value: " + near + ". Set to .01f!");
		}

		ratio = (float) ScreenManager.width / ScreenManager.height;
		worldCoordinates = new Vector2D();

		if (ratio > 1) {
			screenWidth = 2 * Math.tan(Math.degreeToRadian(angle / 2.0f))
					* ((1) - eye.z);
			screenHeight = screenWidth * 1.0f / ratio;
		} else {
			screenWidth = 2 * Math.tan(Math.degreeToRadian(angle / 2.0f))
					* ((1) - eye.z);
			screenHeight = screenWidth * ratio;
		}

		Matrix.projectionMatrix(projMatrix, ratio, angle, near, far);
		Matrix.lookAtMatrix(viewMatrix, eye, center, up);
		Matrix.multiply(viewProjMatrix, projMatrix, viewMatrix);
	}

	/**
	 * Updates the camera with a delta time
	 * 
	 * @param gameTime
	 *            the elapsed time since last update
	 * 
	 */

	public void onUpdate(long gameTime) {
		// TODO
	}

	/**
	 * Returns true if the rectangle is visible to the camera
	 * 
	 * @param rectangle
	 *            the rectangle has to be checked
	 */

	public boolean isRectangleVisible(Rectangle rectangle) {
		// TODO Rectangle 4 coords
		// final float w = screenWidth / 2;
		// final float h = screenHeight / 2;

		return true;
	}

	/**
	 * Returns true if the sprite is visible to the camera
	 * 
	 * @param sprite
	 *            the sprite has to be checked
	 */

	public boolean isSpriteVisible(Sprite sprite) {
		return isRectangleVisible(sprite);
	}

	/**
	 * Returns the screen coordinates in world coordinates
	 * 
	 * @param x
	 *            x coordinate
	 * @param y
	 *            y coordinate
	 */

	public Vector2D convertScreenToWorldCoordinates(float x, float y) {

		final float tx = x - ScreenManager.width / 2.0f;
		final float ty = y - ScreenManager.height / 2.0f;

		worldCoordinates.x = tx * (screenWidth / ScreenManager.width);
		worldCoordinates.y = ty * (screenHeight / ScreenManager.height) * (-1);

		return worldCoordinates;

	}
}
