package org.droidshell.test.game;

import org.droidshell.engine.Engine;
import org.droidshell.input.touch.handler.iDoubleTapEventHandler;
import org.droidshell.lang.math.Vector2D;

import android.util.Log;
import android.view.MotionEvent;

public class SpawnStars implements iDoubleTapEventHandler {
	
	public Engine engine;
	public SuperScene scene;
	
	public SpawnStars(SuperScene scene, Engine engine) {
		this.scene = scene;
		this.engine = engine;
	}

	public boolean onHandleEvent(MotionEvent e) {
		Vector2D v = new Vector2D(engine.renderContext.camera.convertScreenToWorldCoordinates(e.getX(), e.getY()));
		Log.d("TransformedCoord", v.toString());
		Star star = new Star(v);
		scene.starList.add(star);
		return false;
	}
	
}
