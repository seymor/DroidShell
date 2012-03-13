package org.droidshell.node.sprite;

import org.droidshell.lang.math.Color;
import org.droidshell.lang.math.Vector2D;
import org.droidshell.opengl.texture.Texture;
import org.droidshell.opengl.texture.TextureDirectory;

import android.opengl.GLES20;
import android.util.Log;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public class AnimatedSprite extends Sprite {

	public AnimatedSprite(float width, float height, int textureId) {
		super(width, height, textureId);
		// TODO Auto-generated constructor stub
	}

//	private static final String TAG = AnimatedSprite.class.getName();
//	private static final String VBANIMSPRITE = "DS_ANIM_SPRITE";
//
//	public int frameNumber;
//	public int currentFrame = 0;
//	public long lastFrameUpdateTime;
//	public long framePeriod;
//
//	public AnimatedSprite(int frameNumber, int fPS, float width, float height,
//			int textureId, boolean noVBO) {
//		super(width, height, textureId, true);
//
//		this.frameNumber = frameNumber;
//		this.lastFrameUpdateTime = System.currentTimeMillis();
//		this.framePeriod = 1000 / fPS;
//
//		createVBO(coords, width, height, Color.WHITE,
//				TextureDirectory.get(textureId));
//
//	}
//
//	protected float[] createTextureArray() {
//
//		float x = currentFrame * 1.0f / frameNumber;
//
//		float[] textureArray = new float[] { x, 0, x + 1.0f / frameNumber, 0,
//				x, 1, x + 1.0f / frameNumber, 1 };
//
//		return textureArray;
//	}
//
//	protected void createVBO(Vector2D center, float width, float height,
//			Color color, Texture texture) {
//		vboId = VBANIMSPRITE + ":" + center.toString() + ":(" + width + ","
//				+ height + "):" + color.toString() + ":" + texture.toString();
//
//		try {
//			VertexBufferObjectFactory.build(vboId);
//		} catch (Exception e) {
//			Log.e(TAG, "Failed to create VBO: " + e.getMessage());
//		}
//
//		vbo = VertexBufferObjectDirectory.get(vboId);
//
//		vbo.add(POSITION_BUFFER_ID,
//				this.createBuffer(this.createVertexArray(center, width, height)),
//				2, GLES20.GL_FLOAT, false)
//				.add(COLOR_BUFFER_ID,
//						this.createBuffer(this.createColorArray(color)), 4,
//						GLES20.GL_FLOAT, true)
//				.add(TEXTURE_BUFFER_ID,
//						this.createBuffer(this.createTextureArray()), 2,
//						GLES20.GL_FLOAT, true);
//	}
//
//	public void update(long gameTime) {
//
//		if (gameTime > lastFrameUpdateTime + framePeriod) {
//
//			lastFrameUpdateTime = gameTime;
//
//			currentFrame++;
//
//			if (currentFrame == frameNumber)
//				currentFrame = 0;
//
//		}
//
//		vbo.textureBuffer = this.createBuffer(createTextureArray());
//
//	}

}
