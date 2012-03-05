package org.droidshell;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class DroidShellView extends GLSurfaceView {
	
	public DroidShellView(Context context) {
		super(context);
		
		setEGLContextClientVersion(2);
		setRenderer(new DroidShellRenderer());
	}

}
