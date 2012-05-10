package org.droidshell.test.game;

import org.droidshell.engine.Engine;
import org.droidshell.input.touch.handler.iLongPressEventHandler;
import org.droidshell.lang.math.Vector2D;

import android.view.MotionEvent;

public class SpawnSmoke implements iLongPressEventHandler {
	
	public Engine engine;
	public SuperScene scene;
	public Smoke smoke;
	
	public SpawnSmoke(Smoke smoke, SuperScene scene, Engine engine) {
		this.scene = scene;
		this.engine = engine;
		this.smoke = smoke;
	}

	public boolean onHandleEvent(MotionEvent e) {
		Vector2D v = new Vector2D(engine.renderContext.camera.convertScreenToWorldCoordinates(e.getX(), e.getY()));
		smoke.particleSystem.resetPosition();
		smoke.particleSystem.onTranslate(v.x, v.y);
		return false;
	}
	
}
