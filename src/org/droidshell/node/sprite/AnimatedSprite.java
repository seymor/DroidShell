package org.droidshell.node.sprite;

import java.nio.FloatBuffer;

import org.droidshell.lang.math.Color;
import org.droidshell.lang.math.Vector2D;
import org.droidshell.opengl.vertexbuffer.VertexBufferDirectory;
import org.droidshell.opengl.vertexbuffer.VertexBufferFactory;

import android.opengl.GLES20;
import android.util.Log;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public class AnimatedSprite extends Sprite {

	private static final String TAG = AnimatedSprite.class.getName();

	public int frameNumber;
	public int currentFrame = 0;
	public long lastFrameUpdateTime;
	public long framePeriod;

	public AnimatedSprite(int frameNumber, int fPS, float width, float height,
			int textureId) {
		super(width, height, textureId);

		this.frameNumber = frameNumber;
		this.lastFrameUpdateTime = System.currentTimeMillis();
		this.framePeriod = 1000 / fPS;
	}

	public AnimatedSprite(Vector2D center, int frameNumber, int fPS,
			float width, float height, int textureId) {
		super(center, width, height, textureId);

		this.frameNumber = frameNumber;
		this.lastFrameUpdateTime = System.currentTimeMillis();
		this.framePeriod = 1000 / fPS;
	}

	public AnimatedSprite(int frameNumber, int fPS, float width, float height,
			Color color, int textureId) {
		super(width, height, color, textureId);

		this.frameNumber = frameNumber;
		this.lastFrameUpdateTime = System.currentTimeMillis();
		this.framePeriod = 1000 / fPS;
	}

	public AnimatedSprite(Vector2D center, int frameNumber, int fPS,
			float width, float height, Color color, int textureId) {
		super(center, width, height, color, textureId);

		this.frameNumber = frameNumber;
		this.lastFrameUpdateTime = System.currentTimeMillis();
		this.framePeriod = 1000 / fPS;
	}

	private float[] createTextureArray() {

		float x = currentFrame * 1.0f / frameNumber;

		float[] textureArray = new float[] { x, 0, x + 1.0f / frameNumber, 0,
				x, 1, x + 1.0f / frameNumber, 1 };

		return textureArray;
	}

	private void createTextureBuffer() {
		String vbId = "TEXTURE" + ":" + "ANIM";

		FloatBuffer buffer = this.createBuffer(this.createTextureArray());

		try {
			VertexBufferFactory.build(vbId, buffer, 2, GLES20.GL_FLOAT, true);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}

		texcoordBuffer = VertexBufferDirectory.get(vbId);
	}

	public void update(long gameTime) {

		if (gameTime > lastFrameUpdateTime + framePeriod) {

			lastFrameUpdateTime = gameTime;

			currentFrame++;

			if (currentFrame == frameNumber)
				currentFrame = 0;

		}

		this.createTextureBuffer();

	}

}
