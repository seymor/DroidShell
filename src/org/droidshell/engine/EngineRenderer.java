package org.droidshell.engine;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import org.droidshell.R;
import org.droidshell.engine.render.RenderContext;
import org.droidshell.engine.render.camera.Camera;
import org.droidshell.engine.render.camera.NodeCamera;
import org.droidshell.engine.scene.background.LoopingBackground;
import org.droidshell.engine.scene.background.SpriteBackground;
import org.droidshell.exception.EngineException;
import org.droidshell.input.touch.TouchController;
import org.droidshell.lang.math.Color;
import org.droidshell.lang.math.Math;
import org.droidshell.lang.math.Vector2D;
import org.droidshell.lang.math.Vector3D;
import org.droidshell.music.MusicFactory;
import org.droidshell.music.MusicLibrary;
import org.droidshell.node.modifier.JumpModifier;
import org.droidshell.node.modifier.VelocityModifier;
import org.droidshell.node.modifier.RotationModifier;
import org.droidshell.node.particle.ParticleSystem;
import org.droidshell.node.particle.emitter.ParticleEmitter;
import org.droidshell.node.sprite.AnimatedSprite;
import org.droidshell.node.sprite.Sprite;
import org.droidshell.opengl.GLStateManager;
import org.droidshell.opengl.shader.ShaderFactory;
import org.droidshell.opengl.shader.program.ShaderProgram;
import org.droidshell.opengl.shader.program.ShaderProgramDirectory;
import org.droidshell.opengl.shader.program.ShaderProgramFactory;
import org.droidshell.opengl.shader.program.input.ShaderProgramInput;
import org.droidshell.opengl.texture.TextureFactory;
import org.droidshell.screen.ScreenManager;
import org.droidshell.test.game.Hero;
import org.droidshell.test.game.JumpHero;
import org.droidshell.test.game.Smoke;
import org.droidshell.test.game.SpawnSmoke;
import org.droidshell.test.game.SpawnStars;
import org.droidshell.test.game.Star;
import org.droidshell.test.game.StarList;
import org.droidshell.test.game.SuperScene;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;
import android.util.Log;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 04.04.2012
 */
public class EngineRenderer implements Renderer {

	private static final String TAG = EngineRenderer.class.getName();

	public Engine engine;
	public long lastTick = 0;
	public long cucc = 0;
	public long fps = 0;
	public long count = 0;
	public long dt = 0;

	public EngineRenderer(Engine engine) {
		this.engine = engine;
	}

	public void onInit() {
		lastTick = System.currentTimeMillis();

		TextureFactory.build(R.drawable.bg);

		TextureFactory.build(R.drawable.uw);

		TextureFactory.build(R.drawable.biz);

		TextureFactory.build(R.drawable.star);

		TextureFactory.build(R.drawable.rpbg);

		TextureFactory.build(R.drawable.smoke);

		Sprite smoke = new Sprite(0.5f, 0.5f, new Color(Color.intToNorm(255),
				Color.intToNorm(193), Color.intToNorm(193)), R.drawable.smoke);

		Smoke szmok = new Smoke(smoke);

		AnimatedSprite sprite = new AnimatedSprite(new Vector2D(27, 1), 27, 30,
				0.1f, 0.12f, R.drawable.biz);

		Hero hero = new Hero(sprite, new Vector2D(0.1f, 0));

		SuperScene sc = new SuperScene();
		engine.scene = sc;
		sc.hero = hero;

		SpawnSmoke sps = new SpawnSmoke(szmok, sc, engine);

		sc.smoke = szmok;

//		Camera cam = new Camera(new Vector3D(0, 0, -1), new Vector3D(0, 0, 0),
//				new Vector3D(0, 1, 0), 45, 0.001f, 100);

		 NodeCamera cam = new NodeCamera(hero.sprite, new Vector2D(0.5f,0),
		 new Vector3D(0, 0, -1), new Vector3D(0, 0, 0), new Vector3D(0,
		 1, 0), 45, 0.001f, 100);

		ShaderFactory.build(R.raw.test_vs, ShaderFactory.VERTEX_SHADER);
		ShaderFactory.build(R.raw.test_fs, ShaderFactory.FRAGMENT_SHADER);

		ShaderProgramFactory.build("basic", R.raw.test_vs, R.raw.test_fs);

		ShaderProgram prog = ShaderProgramDirectory.get("basic");

		engine.renderContext = new RenderContext(cam, prog);
		ShaderProgramInput sI = engine.renderContext.shaderInput;

		sI.bindAttribute(sI.ATTRIBUTE_POS, "aPosition");
		sI.bindAttribute(sI.ATTRIBUTE_COLOR, "aColor");
		sI.bindAttribute(sI.ATTRIBUTE_TEXCOORD, "aTexture");

		sI.bindUniform(sI.UNIFORM_MODELMATRIX, "uModelMatrix");
		sI.bindUniform(sI.UNIFORM_VIEWPROJMATRIX, "uViewProjMatrix");
		sI.bindUniform(sI.UNIFORM_TEXTURE_SAMPLER, "uTextureSampler");

		prog.use();

		SpawnStars ss = new SpawnStars(sc, engine);
		JumpModifier jm = new JumpModifier(hero, 1, new Vector2D(0.0002f, 0.5f));
		hero.sprite.modifierList.add(jm);
		JumpHero jh = new JumpHero(jm);

		engine.touchController.flingEvents.add(jh);
		engine.touchController.longPressEvents.add(sps);
		engine.touchController.doubleTapEvents.add(ss);

//		engine.scene.background = new SpriteBackground(cam, R.drawable.bg);

		 engine.scene.background = new LoopingBackground(new Vector2D(640, 1),
		 640, 24, cam, R.drawable.rpbg);

		// engine.scene.nodeList.push(ps);

		MusicFactory.build(R.raw.supermario);
		MusicLibrary.get(R.raw.supermario).play();
		MusicLibrary.get(R.raw.supermario).setLooping(false);

		GLStateManager.enableAlphaBlending(GLES20.GL_SRC_ALPHA,
				GLES20.GL_ONE_MINUS_SRC_ALPHA);
	}

	public void onDrawFrame(GL10 glUnused) {
		ScreenManager.clearFrame(GLES20.GL_COLOR_BUFFER_BIT
				| GLES20.GL_DEPTH_BUFFER_BIT);

		fpsCounter();

		try {
			engine.onUpdate();
			engine.onRender();
		} catch (EngineException e) {
			Log.e(TAG, e.getMessage());
		}

	}

	public void onSurfaceChanged(GL10 glUnused, int width, int height) {
		ScreenManager.viewPort(0, 0, width, height);
	}

	public void onSurfaceCreated(GL10 glUnused, EGLConfig config) {
		ScreenManager.clearColor(Color.BLACK);
		onInit();

		engine.run();

	}

	public void fpsCounter() {
		fps++;
		dt = System.currentTimeMillis() - lastTick;
		count += dt;
		if (count > 1000) {
			Log.d(TAG, Long.toString(fps--));
			fps = 0;
			count = 0;
		}
		lastTick += dt;
	}

}
