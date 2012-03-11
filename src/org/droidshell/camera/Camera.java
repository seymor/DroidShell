package org.droidshell.camera;

import org.droidshell.math.Matrix;
import org.droidshell.screen.ScreenManager;

public class Camera {
	
	public float ratio;
	public Matrix viewMatrix;
	public Matrix projMatrix;
	
	public Camera() {
		this.ratio = (float) ScreenManager.width / ScreenManager.height;
	}

}
