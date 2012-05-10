package org.droidshell.test.game;

import org.droidshell.engine.render.RenderContext;
import org.droidshell.lang.math.Vector2D;
import org.droidshell.node.particle.ParticleSystem;
import org.droidshell.node.particle.emitter.ParticleEmitter;
import org.droidshell.node.sprite.Sprite;

public class Smoke {

	public ParticleSystem<Sprite> particleSystem;

	public Smoke(Sprite sprite) {
		particleSystem = new ParticleSystem<Sprite>(100, sprite,
				new ParticleEmitter(2000, 10000, new Vector2D(0.05f, 0.05f),
						new Vector2D(0.04f, 0.04f)));
	}

	public void onUpdate(long gameTime) {
		particleSystem.resetPosition();
		particleSystem.onUpdate(gameTime);
	}

	public void onRender(RenderContext renderContext) {
		particleSystem.onRender(renderContext);
	}

}
