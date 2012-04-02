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

	public Vector2D dimension;
	public int frameNumber;
	public int currentRow = 0;
	public int currentColumn = 0;
	public int currentFrame = 0;
	public long lastFrameUpdateTime;
	public long framePeriod;
	
	public long animationTime = 0;

	public AnimatedSprite(Vector2D dimension, int frameNumber, int fPS,
			float width, float height, int textureId) {
		super(width, height, textureId);

		this.dimension = dimension;
		this.frameNumber = frameNumber;
		this.lastFrameUpdateTime = System.currentTimeMillis();
		this.framePeriod = 1000 / fPS;
	}

	public AnimatedSprite(Vector2D center, Vector2D dimension, int frameNumber,
			int fPS, float width, float height, int textureId) {
		super(center, width, height, textureId);

		this.dimension = dimension;
		this.frameNumber = frameNumber;
		this.lastFrameUpdateTime = System.currentTimeMillis();
		this.framePeriod = 1000 / fPS;
	}

	public AnimatedSprite(Vector2D dimension, int frameNumber, int fPS,
			float width, float height, Color color, int textureId) {
		super(width, height, color, textureId);

		this.dimension = dimension;
		this.frameNumber = frameNumber;
		this.lastFrameUpdateTime = System.currentTimeMillis();
		this.framePeriod = 1000 / fPS;
	}

	public AnimatedSprite(Vector2D center, Vector2D dimension, int frameNumber,
			int fPS, float width, float height, Color color, int textureId) {
		super(center, width, height, color, textureId);

		this.dimension = dimension;
		this.frameNumber = frameNumber;
		this.lastFrameUpdateTime = System.currentTimeMillis();
		this.framePeriod = 1000 / fPS;
	}

	private float[] createTextureArray() {

		float x = currentColumn * 1.0f / dimension.x;
		float y = 1.0f - (currentRow * 1.0f / dimension.y);

		float dx = 1.0f / dimension.x;
		float dy = 1.0f / dimension.y;

		float[] textureArray = new float[] { x, y - dy, x + dx, y - dy, x, y,
				x + dx, y };

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

		animationTime += gameTime;
		
		if (animationTime > lastFrameUpdateTime + framePeriod) {

			lastFrameUpdateTime = animationTime;

			currentColumn++;

			if (currentColumn == (int) dimension.x) {
				currentRow++;
				currentColumn = 0;
			}

			currentFrame++;

			if (currentFrame == frameNumber) {
				currentFrame = 0;
				currentRow = 0;
				currentColumn = 0;
			}

		}

		this.createTextureBuffer();

	}

}
