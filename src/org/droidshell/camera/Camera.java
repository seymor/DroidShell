package org.droidshell.camera;

import org.droidshell.lang.math.Matrix;
import org.droidshell.lang.math.Vector3D;
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

	public float ratio;
	public Matrix projMatrix;
	public Matrix viewMatrix;

	public Camera() {
		ratio = (float) ScreenManager.width / ScreenManager.height;
	}

	public Camera(Vector3D eye, Vector3D center, float bottom, float top,
			float near, float far) {

		if (Float.compare(near, 0) == 0) {
			near = 0.01f;
			Log.e(TAG, "Bad near value: " + near + ". Set to .01f!");
		}

		ratio = (float) ScreenManager.width / ScreenManager.height;
		projMatrix = Matrix.projMatrix(ratio, -ratio, bottom, top, near, far);
		viewMatrix = Matrix.lookAtMatrix(eye.x, eye.y, eye.z, center.x,
				center.y, center.z, 0, 1, 0);
	}

}
