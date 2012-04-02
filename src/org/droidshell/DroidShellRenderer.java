package org.droidshell;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import org.droidshell.camera.Camera;
import org.droidshell.lang.math.Color;
import org.droidshell.lang.math.Matrix;
import org.droidshell.lang.math.Vector2D;
import org.droidshell.lang.math.Vector3D;
import org.droidshell.music.MusicFactory;
import org.droidshell.music.MusicLibrary;
import org.droidshell.node.Node;
import org.droidshell.node.NodeList;
import org.droidshell.node.sprite.AnimatedSprite;
import org.droidshell.node.sprite.Sprite;
import org.droidshell.opengl.GLStateManager;
import org.droidshell.opengl.shader.ShaderDirectory;
import org.droidshell.opengl.shader.ShaderFactory;
import org.droidshell.opengl.shader.program.ShaderProgram;
import org.droidshell.opengl.shader.program.ShaderProgramDirectory;
import org.droidshell.opengl.shader.program.ShaderProgramFactory;
import org.droidshell.opengl.shader.program.input.ShaderProgramInput;
import org.droidshell.opengl.texture.TextureFactory;
import org.droidshell.render.RenderContext;
import org.droidshell.scene.Scene;
import org.droidshell.scene.background.SpriteBackground;
import org.droidshell.screen.ScreenManager;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;

public class DroidShellRenderer implements Renderer {

	private NodeList<Node> sprites;
	private Scene scene;
	private AnimatedSprite sprite;
	private Camera camera;
	private float angle = 0;
	private RenderContext renderContext;

	public void onDrawFrame(GL10 glUnused) {
		ScreenManager.clearFrame(Color.BLACK, 0);

		sprite.modelMatrix = Matrix.identity();

		// Transformation like in OpenGL 1.0
		sprite.translate(-3.0f + angle++ / 150, -1.2f);
		// sprite.rotate(angle++);
		// sprite.scale(1.5f, 1.5f);

		sprites.update(System.currentTimeMillis());
		sprites.render(renderContext);
	}

	public void onSurfaceChanged(GL10 glUnused, int width, int height) {
		ScreenManager.viewPort(0, 0, width, height);
	}

	public void onSurfaceCreated(GL10 glUnused, EGLConfig config) {
		ScreenManager.clearFrame(Color.BLACK, 0);

		TextureFactory.buildTextures();

		MusicFactory.build(R.raw.supermario);

		camera = new Camera(new Vector3D(0, 0, -1), new Vector3D(0, 0, 0), -1,
				1, 1, 100);

		sprites = new NodeList<Node>();

		scene = new Scene(new SpriteBackground(new Sprite(7, 4, R.drawable.bg)));
		sprite = new AnimatedSprite(new Vector2D(27, 1), 27, 30, 1.0f, 1.2f,
				R.drawable.biz);

		ShaderFactory.build(R.raw.test_vs, ShaderFactory.VERTEX_SHADER);
		ShaderFactory.build(R.raw.test_fs, ShaderFactory.FRAGMENT_SHADER);

		ShaderProgramFactory.build("basic",
				ShaderDirectory.getVertexShader(R.raw.test_vs),
				ShaderDirectory.getFragmentShader(R.raw.test_fs));

		ShaderProgram program = ShaderProgramDirectory.get("basic");

		renderContext = new RenderContext(camera, program);
		ShaderProgramInput sI = renderContext.shaderInput;
		sI.bindAttribute(sI.ATTRIBUTE_POS, "aPosition");
		sI.bindAttribute(sI.ATTRIBUTE_COLOR, "aColor");
		sI.bindAttribute(sI.ATTRIBUTE_TEXCOORD, "aTexture");

		sI.bindUniform(sI.UNIFORM_MODELMATRIX, "uModelMatrix");
		sI.bindUniform(sI.UNIFORM_MODELVIEWPROJMATRIX, "uModelViewProjMatrix");
		sI.bindUniform(sI.UNIFORM_TEXTURE_SAMPLER, "uTextureSampler");

		renderContext.shaderProgram.use();

		sprites.push(scene).push(sprite);

		GLStateManager.enableAlphaBlending(GLES20.GL_SRC_ALPHA,
				GLES20.GL_ONE_MINUS_SRC_ALPHA);

		MusicLibrary.get(R.raw.supermario).play();
	}
}