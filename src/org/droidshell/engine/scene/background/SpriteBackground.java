package org.droidshell.engine.scene.background;

import org.droidshell.engine.render.camera.Camera;
import org.droidshell.lang.math.Color;
import org.droidshell.node.sprite.Sprite;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.04.2012
 */
public class SpriteBackground extends Sprite {

	public Camera camera;

	@SuppressWarnings("unused")
	private static final String TAG = SpriteBackground.class.getName();

	public SpriteBackground(Camera camera, int textureId) {
		super(camera.screenWidth, camera.screenHeight, textureId);
		this.camera = camera;
	}

	public SpriteBackground(Camera camera, Color color, int textureId) {
		super(camera.screenWidth, camera.screenHeight, color, textureId);
		this.camera = camera;
	}

	public void onUpdate(long gameTime) {
		resetPosition();
		onTranslate(camera.center.x, camera.center.y);
	}

}
