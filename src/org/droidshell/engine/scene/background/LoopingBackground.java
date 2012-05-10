package org.droidshell.engine.scene.background;

import org.droidshell.engine.render.camera.Camera;
import org.droidshell.lang.math.Color;
import org.droidshell.lang.math.Vector2D;
import org.droidshell.node.sprite.AnimatedSprite;

public class LoopingBackground extends AnimatedSprite {

	public Camera camera;

	@SuppressWarnings("unused")
	private static final String TAG = SpriteBackground.class.getName();

	public LoopingBackground(Vector2D dimension, int frameNumber, int fPS,
			Camera camera, int textureId) {
		super(new Vector2D(frameNumber, 0), frameNumber, fPS,
				camera.screenWidth, camera.screenHeight, textureId);
		this.camera = camera;
	}

	public LoopingBackground(int frameNumber, int fPS, Camera camera,
			Color color, int textureId) {
		super(new Vector2D(frameNumber, 0), frameNumber, fPS,
				camera.screenWidth, camera.screenHeight, color, textureId);
		this.camera = camera;
	}

	private float[] createTextureArray() {

		float x = currentFrame * 1.0f / frameNumber;
		float dx = 0.5f;

		textureArray[0] = x;
		textureArray[1] = 0;
		textureArray[2] = x + dx;
		textureArray[3] = 0;
		textureArray[4] = x;
		textureArray[5] = 1;
		textureArray[6] = x + dx;
		textureArray[7] = 1;

		return textureArray;
	}

	public void onUpdate(long gameTime) {
		super.onUpdate(gameTime);

		if (isAnimated) {
			animationTime += gameTime;

			if (animationTime > lastFrameUpdateTime + framePeriod) {

				lastFrameUpdateTime = animationTime;

				currentFrame++;

				if (currentFrame >= frameNumber / 2 - 1) {
					currentFrame = 0;
				}

			}

			updateTexureCoordinates(createTextureArray());
		}

		resetPosition();
		onTranslate(camera.center.x, camera.center.y);

	}

}
