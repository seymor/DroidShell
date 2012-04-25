package org.droidshell.engine;

import android.opengl.GLSurfaceView;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 04.04.2012
 */
public class EngineView extends GLSurfaceView {

	public EngineView(Engine engine) {
		super(engine.activity);

		setOnTouchListener(engine.touchController);

		setEGLContextClientVersion(2);
		setRenderer(new EngineRenderer(engine));
	}
}
