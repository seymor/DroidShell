package org.droidshell.node.sprite;

import java.nio.FloatBuffer;

import org.droidshell.lang.math.Color;
import org.droidshell.lang.math.Vector2D;
import org.droidshell.opengl.vertexbuffer.VertexBuffer;
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

	private float[] textureArray = new float[8];

	public Vector2D dimension;
	public int frameNumber;
	public int currentRow;
	public int currentColumn;
	public int currentFrame;
	public long lastFrameUpdateTime;
	public long framePeriod;

	public long animationTime;

	public AnimatedSprite(Vector2D dimension, int frameNumber, int fPS,
			float width, float height, int textureId) {
		super(width, height, textureId);

		this.dimension = dimension;
		this.frameNumber = frameNumber;
		currentRow = 0;
		currentColumn = 0;
		currentFrame = 0;
		lastFrameUpdateTime = 0;
		framePeriod = 1000 / fPS;
		animationTime = 0;

		createTextureBuffer();
	}

	public AnimatedSprite(Vector2D center, Vector2D dimension, int frameNumber,
			int fPS, float width, float height, int textureId) {
		super(center, width, height, textureId);

		this.dimension = dimension;
		this.frameNumber = frameNumber;
		currentRow = 0;
		currentColumn = 0;
		currentFrame = 0;
		lastFrameUpdateTime = 0;
		framePeriod = 1000 / fPS;
		animationTime = 0;

		createTextureBuffer();
	}

	public AnimatedSprite(Vector2D dimension, int frameNumber, int fPS,
			float width, float height, Color color, int textureId) {
		super(width, height, color, textureId);

		this.dimension = dimension;
		this.frameNumber = frameNumber;
		currentRow = 0;
		currentColumn = 0;
		currentFrame = 0;
		lastFrameUpdateTime = 0;
		framePeriod = 1000 / fPS;
		animationTime = 0;

		createTextureBuffer();
	}

	public AnimatedSprite(Vector2D center, Vector2D dimension, int frameNumber,
			int fPS, float width, float height, Color color, int textureId) {
		super(center, width, height, color, textureId);

		this.dimension = dimension;
		this.frameNumber = frameNumber;
		currentRow = 0;
		currentColumn = 0;
		currentFrame = 0;
		lastFrameUpdateTime = 0;
		framePeriod = 1000 / fPS;
		animationTime = 0;

		createTextureBuffer();
	}

	private float[] createTextureArray() {

		float x = currentColumn * 1.0f / dimension.x;
		float y = 1.0f - (currentRow * 1.0f / dimension.y);

		float dx = 1.0f / dimension.x;
		float dy = 1.0f / dimension.y;

		textureArray[0] = x;
		textureArray[1] = y - dy;
		textureArray[2] = x + dx;
		textureArray[3] = y - dy;
		textureArray[4] = x;
		textureArray[5] = y;
		textureArray[6] = x + dx;
		textureArray[7] = y;

		return textureArray;
	}

	private void createTextureBuffer() {
		String vbId = "TEXTURE" + ":" + "ANIM" + ":" + texture.toString();

		FloatBuffer buffer = createBuffer(createTextureArray());
		texcoordBuffer = new VertexBuffer(buffer, 2, GLES20.GL_FLOAT, true);

		try {
			VertexBufferFactory.build(vbId, texcoordBuffer);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
	}

	private void updateTexureCoordinates(float[] array) {

		texcoordBuffer.buffer.put(array);
		texcoordBuffer.buffer.position(0);

	}

	public void onUpdate(long gameTime) {

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

		updateTexureCoordinates(createTextureArray());

	}

	@Override
	public AnimatedSprite clone() {
		return (AnimatedSprite) super.clone();
	}

}
