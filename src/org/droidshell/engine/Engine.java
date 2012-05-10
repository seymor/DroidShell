package org.droidshell.engine;

import org.droidshell.engine.render.RenderContext;
import org.droidshell.engine.scene.Scene;
import org.droidshell.exception.EngineException;
import org.droidshell.input.sensor.SensorController;
import org.droidshell.input.touch.TouchController;
import org.droidshell.music.MusicFactory;
import org.droidshell.opengl.shader.ShaderFactory;
import org.droidshell.opengl.shader.program.ShaderProgramFactory;
import org.droidshell.opengl.texture.TextureFactory;
import org.droidshell.opengl.vertexbuffer.VertexBufferFactory;
import org.droidshell.screen.ScreenManager;

import android.app.Activity;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 04.04.2012
 */
public class Engine {

	@SuppressWarnings("unused")
	private static final String TAG = Engine.class.getName();

	protected boolean isRunning;

	public Activity activity;
	public RenderContext renderContext;
	public Scene scene;

	public TouchController touchController;
	public SensorController sensorController;

	public long lastTick;

	public Engine(Activity activity) {
		this.activity = activity;

		ScreenManager.onInit(activity);

		ShaderFactory.onInit(activity);
		ShaderProgramFactory.onInit();
		TextureFactory.onInit(activity);
		VertexBufferFactory.onInit();
		MusicFactory.onInit(activity);

		scene = new Scene();
		touchController = new TouchController(activity);
		sensorController = new SensorController(activity);
		isRunning = false;

	}

	public void run() {
		isRunning = true;
		lastTick = System.currentTimeMillis();
	}

	public void stop() {
		isRunning = false;
	}

	public long timeElapsed() {
		long currentTime = System.currentTimeMillis();

		return currentTime - lastTick;
	}

	public void onUpdate() throws EngineException {
		if (isRunning) {

			final long elapsedTime = timeElapsed();
			lastTick += elapsedTime;

			scene.onUpdate(elapsedTime);
			renderContext.camera.onUpdate(elapsedTime);
		}
	}

	public void onRender() throws EngineException {
		if (renderContext == null)
			throw new EngineException("RenderContext not set!");

		scene.onRender(renderContext);
	}
}
